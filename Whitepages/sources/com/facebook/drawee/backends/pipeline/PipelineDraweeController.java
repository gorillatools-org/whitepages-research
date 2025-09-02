package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.debug.DebugControllerOverlayDrawable;
import com.facebook.drawee.debug.listener.ImageLoadingTimeControllerListener;
import com.facebook.drawee.drawable.ArrayDrawable;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.fresco.ui.common.MultiUriHelper;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

public class PipelineDraweeController extends AbstractDraweeController {
    private static final Class TAG = PipelineDraweeController.class;
    private CacheKey mCacheKey;
    private ImmutableList mCustomDrawableFactories;
    private Supplier mDataSourceSupplier;
    private final DrawableFactory mDefaultDrawableFactory;
    private boolean mDrawDebugOverlay;
    private ImageRequest[] mFirstAvailableImageRequests;
    private final ImmutableList mGlobalDrawableFactories;
    private ImagePerfMonitor mImagePerfMonitor;
    private ImageRequest mImageRequest;
    private ImageRequest mLowResImageRequest;
    private final MemoryCache mMemoryCache;
    private Set mRequestListeners;
    private final Resources mResources;

    /* access modifiers changed from: protected */
    public void releaseDrawable(Drawable drawable) {
    }

    public PipelineDraweeController(Resources resources, DeferredReleaser deferredReleaser, DrawableFactory drawableFactory, DrawableFactory drawableFactory2, Executor executor, MemoryCache memoryCache, ImmutableList immutableList) {
        super(deferredReleaser, executor, (String) null, (Object) null);
        this.mResources = resources;
        this.mDefaultDrawableFactory = new DefaultDrawableFactory(resources, drawableFactory, drawableFactory2);
        this.mGlobalDrawableFactories = immutableList;
        this.mMemoryCache = memoryCache;
    }

    public void initialize(Supplier supplier, String str, CacheKey cacheKey, Object obj, ImmutableList immutableList) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PipelineDraweeController#initialize");
        }
        super.initialize(str, obj);
        init(supplier);
        this.mCacheKey = cacheKey;
        setCustomDrawableFactories(immutableList);
        maybeUpdateDebugOverlay((CloseableImage) null);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void initializePerformanceMonitoring(ImagePerfDataListener imagePerfDataListener, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder) {
        try {
            ImagePerfMonitor imagePerfMonitor = this.mImagePerfMonitor;
            if (imagePerfMonitor != null) {
                imagePerfMonitor.reset();
            }
            if (imagePerfDataListener != null) {
                if (this.mImagePerfMonitor == null) {
                    this.mImagePerfMonitor = new ImagePerfMonitor(AwakeTimeSinceBootClock.get(), this);
                }
                this.mImagePerfMonitor.addImagePerfDataListener(imagePerfDataListener);
                this.mImagePerfMonitor.setEnabled(true);
            }
            this.mImageRequest = (ImageRequest) abstractDraweeControllerBuilder.getImageRequest();
            this.mFirstAvailableImageRequests = (ImageRequest[]) abstractDraweeControllerBuilder.getFirstAvailableImageRequests();
            this.mLowResImageRequest = (ImageRequest) abstractDraweeControllerBuilder.getLowResImageRequest();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void setDrawDebugOverlay(boolean z) {
        this.mDrawDebugOverlay = z;
    }

    public void setCustomDrawableFactories(ImmutableList immutableList) {
        this.mCustomDrawableFactories = immutableList;
    }

    public synchronized void addRequestListener(RequestListener requestListener) {
        try {
            if (this.mRequestListeners == null) {
                this.mRequestListeners = new HashSet();
            }
            this.mRequestListeners.add(requestListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void removeRequestListener(RequestListener requestListener) {
        Set set = this.mRequestListeners;
        if (set != null) {
            set.remove(requestListener);
        }
    }

    private void init(Supplier supplier) {
        this.mDataSourceSupplier = supplier;
        maybeUpdateDebugOverlay((CloseableImage) null);
    }

    public synchronized RequestListener getRequestListener() {
        Set set = this.mRequestListeners;
        if (set == null) {
            return null;
        }
        return new ForwardingRequestListener(set);
    }

    /* access modifiers changed from: protected */
    public DataSource getDataSource() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PipelineDraweeController#getDataSource");
        }
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x: getDataSource", (Object) Integer.valueOf(System.identityHashCode(this)));
        }
        DataSource dataSource = (DataSource) this.mDataSourceSupplier.get();
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return dataSource;
    }

    /* access modifiers changed from: protected */
    public Drawable createDrawable(CloseableReference closeableReference) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("PipelineDraweeController#createDrawable");
            }
            Preconditions.checkState(CloseableReference.isValid(closeableReference));
            CloseableImage closeableImage = (CloseableImage) closeableReference.get();
            maybeUpdateDebugOverlay(closeableImage);
            Drawable maybeCreateDrawableFromFactories = maybeCreateDrawableFromFactories(this.mCustomDrawableFactories, closeableImage);
            if (maybeCreateDrawableFromFactories != null) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return maybeCreateDrawableFromFactories;
            }
            Drawable maybeCreateDrawableFromFactories2 = maybeCreateDrawableFromFactories(this.mGlobalDrawableFactories, closeableImage);
            if (maybeCreateDrawableFromFactories2 != null) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return maybeCreateDrawableFromFactories2;
            }
            Drawable createDrawable = this.mDefaultDrawableFactory.createDrawable(closeableImage);
            if (createDrawable != null) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return createDrawable;
            }
            throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    private Drawable maybeCreateDrawableFromFactories(ImmutableList immutableList, CloseableImage closeableImage) {
        Drawable createDrawable;
        if (immutableList == null) {
            return null;
        }
        Iterator it = immutableList.iterator();
        while (it.hasNext()) {
            DrawableFactory drawableFactory = (DrawableFactory) it.next();
            if (drawableFactory.supportsImageType(closeableImage) && (createDrawable = drawableFactory.createDrawable(closeableImage)) != null) {
                return createDrawable;
            }
        }
        return null;
    }

    public void setHierarchy(DraweeHierarchy draweeHierarchy) {
        super.setHierarchy(draweeHierarchy);
        maybeUpdateDebugOverlay((CloseableImage) null);
    }

    private void maybeUpdateDebugOverlay(CloseableImage closeableImage) {
        if (this.mDrawDebugOverlay) {
            if (getControllerOverlay() == null) {
                DebugControllerOverlayDrawable debugControllerOverlayDrawable = new DebugControllerOverlayDrawable();
                addControllerListener(new ImageLoadingTimeControllerListener(debugControllerOverlayDrawable));
                setControllerOverlay(debugControllerOverlayDrawable);
            }
            if (getControllerOverlay() instanceof DebugControllerOverlayDrawable) {
                updateDebugOverlay(closeableImage, (DebugControllerOverlayDrawable) getControllerOverlay());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateDebugOverlay(CloseableImage closeableImage, DebugControllerOverlayDrawable debugControllerOverlayDrawable) {
        ScaleTypeDrawable activeScaleTypeDrawable;
        debugControllerOverlayDrawable.setControllerId(getId());
        DraweeHierarchy hierarchy = getHierarchy();
        ScalingUtils$ScaleType scalingUtils$ScaleType = null;
        if (!(hierarchy == null || (activeScaleTypeDrawable = getActiveScaleTypeDrawable(hierarchy.getTopLevelDrawable())) == null)) {
            scalingUtils$ScaleType = activeScaleTypeDrawable.getScaleType();
        }
        debugControllerOverlayDrawable.setScaleType(scalingUtils$ScaleType);
        String callerContextString = getCallerContextString();
        if (callerContextString != null) {
            debugControllerOverlayDrawable.addAdditionalData("cc", callerContextString);
        }
        if (closeableImage != null) {
            debugControllerOverlayDrawable.setDimensions(closeableImage.getWidth(), closeableImage.getHeight());
            debugControllerOverlayDrawable.setImageSize(closeableImage.getSizeInBytes());
            return;
        }
        debugControllerOverlayDrawable.reset();
    }

    /* access modifiers changed from: protected */
    public ImageInfo getImageInfo(CloseableReference closeableReference) {
        Preconditions.checkState(CloseableReference.isValid(closeableReference));
        return ((CloseableImage) closeableReference.get()).getImageInfo();
    }

    /* access modifiers changed from: protected */
    public int getImageHash(CloseableReference closeableReference) {
        if (closeableReference != null) {
            return closeableReference.getValueHash();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void releaseImage(CloseableReference closeableReference) {
        CloseableReference.closeSafely(closeableReference);
    }

    /* access modifiers changed from: protected */
    public CloseableReference getCachedImage() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PipelineDraweeController#getCachedImage");
        }
        try {
            MemoryCache memoryCache = this.mMemoryCache;
            if (memoryCache != null) {
                CacheKey cacheKey = this.mCacheKey;
                if (cacheKey != null) {
                    CloseableReference closeableReference = memoryCache.get(cacheKey);
                    if (closeableReference == null || ((CloseableImage) closeableReference.get()).getQualityInfo().isOfFullQuality()) {
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                        }
                        return closeableReference;
                    }
                    closeableReference.close();
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return null;
                }
            }
            return null;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onImageLoadedFromCacheImmediately(String str, CloseableReference closeableReference) {
        super.onImageLoadedFromCacheImmediately(str, closeableReference);
        synchronized (this) {
        }
    }

    public String toString() {
        return Objects.toStringHelper(this).add("super", (Object) super.toString()).add("dataSourceSupplier", (Object) this.mDataSourceSupplier).toString();
    }

    public Map obtainExtrasFromImage(ImageInfo imageInfo) {
        if (imageInfo == null) {
            return null;
        }
        return imageInfo.getExtras();
    }

    /* access modifiers changed from: protected */
    public Uri getMainUri() {
        return MultiUriHelper.getMainUri(this.mImageRequest, this.mLowResImageRequest, this.mFirstAvailableImageRequests, ImageRequest.REQUEST_TO_URI_FN);
    }

    /* access modifiers changed from: protected */
    public String getCallerContextString() {
        Object callerContext = getCallerContext();
        if (callerContext == null) {
            return null;
        }
        return callerContext.toString();
    }

    public static ScaleTypeDrawable getActiveScaleTypeDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable) drawable;
        }
        if (drawable instanceof DrawableParent) {
            return getActiveScaleTypeDrawable(((DrawableParent) drawable).getDrawable());
        }
        if (drawable instanceof ArrayDrawable) {
            ArrayDrawable arrayDrawable = (ArrayDrawable) drawable;
            int numberOfLayers = arrayDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                ScaleTypeDrawable activeScaleTypeDrawable = getActiveScaleTypeDrawable(arrayDrawable.getDrawable(i));
                if (activeScaleTypeDrawable != null) {
                    return activeScaleTypeDrawable;
                }
            }
        }
        return null;
    }
}
