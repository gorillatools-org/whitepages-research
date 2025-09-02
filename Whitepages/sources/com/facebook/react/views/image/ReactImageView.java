package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.ForwardingControllerListener;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.DownsampleMode;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.fresco.ImageCacheControl;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.util.RNLog;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.MultiSourceHelper;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactImageView extends GenericDraweeView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int REMOTE_IMAGE_FADE_DURATION_MS = 300;
    /* access modifiers changed from: private */
    public static final Matrix tileMatrix = new Matrix();
    private ImageSource cachedImageSource;
    private Object callerContext;
    private ControllerListener controllerForTesting;
    private Drawable defaultImageDrawable;
    private ReactImageDownloadListener<ImageInfo> downloadListener;
    private final AbstractDraweeControllerBuilder draweeControllerBuilder;
    private int fadeDurationMs = -1;
    private final GlobalImageLoadListener globalImageLoadListener;
    private ReadableMap headers;
    private ImageSource imageSource;
    private boolean isDirty;
    private IterativeBoxBlurPostProcessor iterativeBoxBlurPostProcessor;
    private Drawable loadingImageDrawable;
    private int overlayColor;
    private boolean progressiveRenderingEnabled;
    private ImageResizeMethod resizeMethod = ImageResizeMethod.AUTO;
    private float resizeMultiplier = 1.0f;
    /* access modifiers changed from: private */
    public ScalingUtils$ScaleType scaleType = ImageResizeMode.defaultValue();
    private final List<ImageSource> sources = new ArrayList();
    /* access modifiers changed from: private */
    public Shader.TileMode tileMode = ImageResizeMode.defaultTileMode();
    private TilePostprocessor tilePostprocessor;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0021 */
        static {
            /*
                com.facebook.react.modules.fresco.ImageCacheControl[] r0 = com.facebook.react.modules.fresco.ImageCacheControl.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.facebook.react.modules.fresco.ImageCacheControl r2 = com.facebook.react.modules.fresco.ImageCacheControl.ONLY_IF_CACHED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                $EnumSwitchMapping$0 = r0
                com.facebook.react.views.image.ImageResizeMethod[] r0 = com.facebook.react.views.image.ImageResizeMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.views.image.ImageResizeMethod r2 = com.facebook.react.views.image.ImageResizeMethod.AUTO     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0021 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                com.facebook.react.views.image.ImageResizeMethod r1 = com.facebook.react.views.image.ImageResizeMethod.RESIZE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ReactImageView.WhenMappings.<clinit>():void");
        }
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactImageView(Context context, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener2, Object obj) {
        super(context, Companion.buildHierarchy(context));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(abstractDraweeControllerBuilder, "draweeControllerBuilder");
        this.draweeControllerBuilder = abstractDraweeControllerBuilder;
        this.globalImageLoadListener = globalImageLoadListener2;
        this.callerContext = obj;
        setLegacyVisibilityHandlingEnabled(true);
    }

    public final ImageSource getImageSource$ReactAndroid_release() {
        return this.imageSource;
    }

    public final void setImageSource$ReactAndroid_release(ImageSource imageSource2) {
        this.imageSource = imageSource2;
    }

    public final void updateCallerContext(Object obj) {
        if (!Intrinsics.areEqual(this.callerContext, obj)) {
            this.callerContext = obj;
            this.isDirty = true;
        }
    }

    public final void setShouldNotifyLoadEvents(boolean z) {
        if (z != (this.downloadListener != null)) {
            if (!z) {
                this.downloadListener = null;
            } else {
                Context context = getContext();
                Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                this.downloadListener = new ReactImageView$setShouldNotifyLoadEvents$1(UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId()), this);
            }
            this.isDirty = true;
        }
    }

    public final void setBlurRadius(float f) {
        IterativeBoxBlurPostProcessor iterativeBoxBlurPostProcessor2;
        int dpToPx = ((int) PixelUtil.INSTANCE.dpToPx(f)) / 2;
        if (dpToPx == 0) {
            iterativeBoxBlurPostProcessor2 = null;
        } else {
            iterativeBoxBlurPostProcessor2 = new IterativeBoxBlurPostProcessor(2, dpToPx);
        }
        this.iterativeBoxBlurPostProcessor = iterativeBoxBlurPostProcessor2;
        this.isDirty = true;
    }

    public void setBackgroundColor(int i) {
        BackgroundStyleApplicator.setBackgroundColor(this, Integer.valueOf(i));
    }

    public final void setBorderColor(int i) {
        BackgroundStyleApplicator.setBorderColor(this, LogicalEdge.ALL, Integer.valueOf(i));
    }

    public final void setOverlayColor(int i) {
        if (this.overlayColor != i) {
            this.overlayColor = i;
            this.isDirty = true;
        }
    }

    public final void setBorderWidth(float f) {
        BackgroundStyleApplicator.setBorderWidth(this, LogicalEdge.ALL, Float.valueOf(f));
    }

    public final void setBorderRadius(float f) {
        LengthPercentage lengthPercentage;
        if (Float.isNaN(f)) {
            lengthPercentage = null;
        } else {
            lengthPercentage = new LengthPercentage(PixelUtil.INSTANCE.pxToDp(f), LengthPercentageType.POINT);
        }
        BackgroundStyleApplicator.setBorderRadius(this, BorderRadiusProp.BORDER_RADIUS, lengthPercentage);
    }

    public final void setBorderRadius(float f, int i) {
        LengthPercentage lengthPercentage;
        if (Float.isNaN(f)) {
            lengthPercentage = null;
        } else {
            lengthPercentage = new LengthPercentage(PixelUtil.INSTANCE.pxToDp(f), LengthPercentageType.POINT);
        }
        BackgroundStyleApplicator.setBorderRadius(this, BorderRadiusProp.values()[i], lengthPercentage);
    }

    public final void setScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        Intrinsics.checkNotNullParameter(scalingUtils$ScaleType, "scaleType");
        if (this.scaleType != scalingUtils$ScaleType) {
            this.scaleType = scalingUtils$ScaleType;
            this.isDirty = true;
        }
    }

    public final void setTileMode(Shader.TileMode tileMode2) {
        Intrinsics.checkNotNullParameter(tileMode2, "tileMode");
        if (this.tileMode != tileMode2) {
            this.tileMode = tileMode2;
            this.tilePostprocessor = isTiled() ? new TilePostprocessor() : null;
            this.isDirty = true;
        }
    }

    public final void setResizeMethod(ImageResizeMethod imageResizeMethod) {
        Intrinsics.checkNotNullParameter(imageResizeMethod, ViewProps.RESIZE_METHOD);
        if (this.resizeMethod != imageResizeMethod) {
            this.resizeMethod = imageResizeMethod;
            this.isDirty = true;
        }
    }

    public final void setResizeMultiplier(float f) {
        if (Math.abs((double) (this.resizeMultiplier - f)) > 9.999999747378752E-5d) {
            this.resizeMultiplier = f;
            this.isDirty = true;
        }
    }

    public final void setSource(ReadableArray readableArray) {
        ReadableArray readableArray2 = readableArray;
        ArrayList arrayList = new ArrayList();
        if (readableArray2 == null || readableArray.size() == 0) {
            ImageSource.Companion companion = ImageSource.Companion;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            arrayList.add(companion.getTransparentBitmapImageSource(context));
        } else {
            if (readableArray.size() == 1) {
                ReadableMap map = readableArray2.getMap(0);
                if (map != null) {
                    ImageCacheControl computeCacheControl = computeCacheControl(map.getString("cache"));
                    Context context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    ImageSource imageSource2 = new ImageSource(context2, map.getString("uri"), 0.0d, 0.0d, computeCacheControl, 12, (DefaultConstructorMarker) null);
                    if (Intrinsics.areEqual((Object) Uri.EMPTY, (Object) imageSource2.getUri())) {
                        warnImageSource(map.getString("uri"));
                        ImageSource.Companion companion2 = ImageSource.Companion;
                        Context context3 = getContext();
                        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
                        imageSource2 = companion2.getTransparentBitmapImageSource(context3);
                    }
                    arrayList.add(imageSource2);
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            } else {
                int size = readableArray.size();
                for (int i = 0; i < size; i++) {
                    ReadableMap map2 = readableArray2.getMap(i);
                    if (map2 != null) {
                        ImageCacheControl computeCacheControl2 = computeCacheControl(map2.getString("cache"));
                        Context context4 = getContext();
                        Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
                        ImageSource imageSource3 = new ImageSource(context4, map2.getString("uri"), map2.getDouble("width"), map2.getDouble("height"), computeCacheControl2);
                        if (Intrinsics.areEqual((Object) Uri.EMPTY, (Object) imageSource3.getUri())) {
                            warnImageSource(map2.getString("uri"));
                            ImageSource.Companion companion3 = ImageSource.Companion;
                            Context context5 = getContext();
                            Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
                            imageSource3 = companion3.getTransparentBitmapImageSource(context5);
                        }
                        arrayList.add(imageSource3);
                    }
                }
            }
        }
        if (!Intrinsics.areEqual((Object) this.sources, (Object) arrayList)) {
            this.sources.clear();
            this.sources.addAll(arrayList);
            this.isDirty = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return com.facebook.react.modules.fresco.ImageCacheControl.DEFAULT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r2.equals(com.facebook.hermes.intl.Constants.COLLATION_DEFAULT) == false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.facebook.react.modules.fresco.ImageCacheControl computeCacheControl(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x0039
            int r0 = r2.hashCode()
            switch(r0) {
                case -1564134880: goto L_0x002b;
                case -934641255: goto L_0x001f;
                case 706834161: goto L_0x0013;
                case 1544803905: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0033
        L_0x000a:
            java.lang.String r0 = "default"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0039
            goto L_0x0033
        L_0x0013:
            java.lang.String r0 = "only-if-cached"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x001c
            goto L_0x0033
        L_0x001c:
            com.facebook.react.modules.fresco.ImageCacheControl r2 = com.facebook.react.modules.fresco.ImageCacheControl.ONLY_IF_CACHED
            goto L_0x003b
        L_0x001f:
            java.lang.String r0 = "reload"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0028
            goto L_0x0033
        L_0x0028:
            com.facebook.react.modules.fresco.ImageCacheControl r2 = com.facebook.react.modules.fresco.ImageCacheControl.RELOAD
            goto L_0x003b
        L_0x002b:
            java.lang.String r0 = "force-cache"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0036
        L_0x0033:
            com.facebook.react.modules.fresco.ImageCacheControl r2 = com.facebook.react.modules.fresco.ImageCacheControl.DEFAULT
            goto L_0x003b
        L_0x0036:
            com.facebook.react.modules.fresco.ImageCacheControl r2 = com.facebook.react.modules.fresco.ImageCacheControl.FORCE_CACHE
            goto L_0x003b
        L_0x0039:
            com.facebook.react.modules.fresco.ImageCacheControl r2 = com.facebook.react.modules.fresco.ImageCacheControl.DEFAULT
        L_0x003b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ReactImageView.computeCacheControl(java.lang.String):com.facebook.react.modules.fresco.ImageCacheControl");
    }

    private final ImageRequest.RequestLevel computeRequestLevel(ImageCacheControl imageCacheControl) {
        if (WhenMappings.$EnumSwitchMapping$0[imageCacheControl.ordinal()] == 1) {
            return ImageRequest.RequestLevel.DISK_CACHE;
        }
        return ImageRequest.RequestLevel.FULL_FETCH;
    }

    public final void setDefaultSource(String str) {
        ResourceDrawableIdHelper instance = ResourceDrawableIdHelper.Companion.getInstance();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Drawable resourceDrawable = instance.getResourceDrawable(context, str);
        if (!Intrinsics.areEqual((Object) this.defaultImageDrawable, (Object) resourceDrawable)) {
            this.defaultImageDrawable = resourceDrawable;
            this.isDirty = true;
        }
    }

    public final void setLoadingIndicatorSource(String str) {
        ResourceDrawableIdHelper instance = ResourceDrawableIdHelper.Companion.getInstance();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Drawable resourceDrawable = instance.getResourceDrawable(context, str);
        AutoRotateDrawable autoRotateDrawable = resourceDrawable != null ? new AutoRotateDrawable(resourceDrawable, 1000) : null;
        if (!Intrinsics.areEqual((Object) this.loadingImageDrawable, (Object) autoRotateDrawable)) {
            this.loadingImageDrawable = autoRotateDrawable;
            this.isDirty = true;
        }
    }

    public final void setProgressiveRenderingEnabled(boolean z) {
        this.progressiveRenderingEnabled = z;
    }

    public final void setFadeDuration(int i) {
        this.fadeDurationMs = i;
    }

    public final void setHeaders(ReadableMap readableMap) {
        this.headers = readableMap;
    }

    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
        try {
            super.onDraw(canvas);
        } catch (RuntimeException e) {
            if (this.downloadListener != null) {
                Context context = getContext();
                Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
                if (eventDispatcherForReactTag != null) {
                    eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.Companion.createErrorEvent(UIManagerHelper.getSurfaceId((View) this), getId(), e));
                }
            }
        }
    }

    public final void maybeUpdateView() {
        if (this.isDirty) {
            if (!hasMultipleSources() || (getWidth() > 0 && getHeight() > 0)) {
                setSourceImage();
                ImageSource imageSource2 = this.imageSource;
                if (imageSource2 != null) {
                    boolean shouldResize = shouldResize(imageSource2);
                    if (shouldResize && (getWidth() <= 0 || getHeight() <= 0)) {
                        return;
                    }
                    if (!isTiled() || (getWidth() > 0 && getHeight() > 0)) {
                        GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) getHierarchy();
                        genericDraweeHierarchy.setActualImageScaleType(this.scaleType);
                        Drawable drawable = this.defaultImageDrawable;
                        if (drawable != null) {
                            genericDraweeHierarchy.setPlaceholderImage(drawable, this.scaleType);
                        }
                        Drawable drawable2 = this.loadingImageDrawable;
                        if (drawable2 != null) {
                            genericDraweeHierarchy.setPlaceholderImage(drawable2, ScalingUtils$ScaleType.CENTER);
                        }
                        RoundingParams roundingParams = genericDraweeHierarchy.getRoundingParams();
                        if (roundingParams != null) {
                            int i = this.overlayColor;
                            if (i != 0) {
                                roundingParams.setOverlayColor(i);
                            } else {
                                roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
                            }
                            genericDraweeHierarchy.setRoundingParams(roundingParams);
                        }
                        int i2 = this.fadeDurationMs;
                        if (i2 < 0) {
                            i2 = imageSource2.isResource() ? 0 : REMOTE_IMAGE_FADE_DURATION_MS;
                        }
                        genericDraweeHierarchy.setFadeDuration(i2);
                        maybeUpdateViewFromRequest(shouldResize);
                        this.isDirty = false;
                    }
                }
            }
        }
    }

    private final void maybeUpdateViewFromRequest(boolean z) {
        ImageSource imageSource2 = this.imageSource;
        if (imageSource2 != null) {
            Uri uri = imageSource2.getUri();
            ImageCacheControl cacheControl = imageSource2.getCacheControl();
            ImageRequest.RequestLevel computeRequestLevel = computeRequestLevel(cacheControl);
            ArrayList arrayList = new ArrayList();
            IterativeBoxBlurPostProcessor iterativeBoxBlurPostProcessor2 = this.iterativeBoxBlurPostProcessor;
            if (iterativeBoxBlurPostProcessor2 != null) {
                arrayList.add(iterativeBoxBlurPostProcessor2);
            }
            TilePostprocessor tilePostprocessor2 = this.tilePostprocessor;
            if (tilePostprocessor2 != null) {
                arrayList.add(tilePostprocessor2);
            }
            Postprocessor from = MultiPostprocessor.Companion.from(arrayList);
            ResizeOptions resizeOptions = z ? getResizeOptions() : null;
            if (cacheControl == ImageCacheControl.RELOAD) {
                Fresco.getImagePipeline().evictFromCache(uri);
            }
            ImageRequestBuilder lowestPermittedRequestLevel = ImageRequestBuilder.newBuilderWithSource(uri).setPostprocessor(from).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.progressiveRenderingEnabled).setLowestPermittedRequestLevel(computeRequestLevel);
            ImageResizeMethod imageResizeMethod = this.resizeMethod;
            ImageResizeMethod imageResizeMethod2 = ImageResizeMethod.NONE;
            if (imageResizeMethod == imageResizeMethod2) {
                lowestPermittedRequestLevel.setDownsampleOverride(DownsampleMode.NEVER);
            }
            ReactNetworkImageRequest.Companion companion = ReactNetworkImageRequest.Companion;
            Intrinsics.checkNotNull(lowestPermittedRequestLevel);
            ReactNetworkImageRequest fromBuilderWithHeaders = companion.fromBuilderWithHeaders(lowestPermittedRequestLevel, this.headers, cacheControl);
            GlobalImageLoadListener globalImageLoadListener2 = this.globalImageLoadListener;
            if (globalImageLoadListener2 != null) {
                globalImageLoadListener2.onLoadAttempt(uri);
            }
            AbstractDraweeControllerBuilder abstractDraweeControllerBuilder = this.draweeControllerBuilder;
            Intrinsics.checkNotNull(abstractDraweeControllerBuilder, "null cannot be cast to non-null type com.facebook.drawee.controller.AbstractDraweeControllerBuilder<*, com.facebook.imagepipeline.request.ImageRequest, com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>, com.facebook.imagepipeline.image.ImageInfo>");
            abstractDraweeControllerBuilder.reset();
            abstractDraweeControllerBuilder.setImageRequest(fromBuilderWithHeaders).setAutoPlayAnimations(true).setOldController(getController());
            Object obj = this.callerContext;
            if (obj != null) {
                Intrinsics.checkNotNullExpressionValue(abstractDraweeControllerBuilder.setCallerContext(obj), "setCallerContext(...)");
            }
            ImageSource imageSource3 = this.cachedImageSource;
            if (imageSource3 != null) {
                ImageRequestBuilder progressiveRenderingEnabled2 = ImageRequestBuilder.newBuilderWithSource(imageSource3.getUri()).setPostprocessor(from).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.progressiveRenderingEnabled);
                if (this.resizeMethod == imageResizeMethod2) {
                    progressiveRenderingEnabled2.setDownsampleOverride(DownsampleMode.NEVER);
                }
                Intrinsics.checkNotNullExpressionValue(abstractDraweeControllerBuilder.setLowResImageRequest(progressiveRenderingEnabled2.build()), "setLowResImageRequest(...)");
            }
            ReactImageDownloadListener<ImageInfo> reactImageDownloadListener = this.downloadListener;
            if (reactImageDownloadListener == null || this.controllerForTesting == null) {
                ControllerListener controllerListener = this.controllerForTesting;
                if (controllerListener != null) {
                    abstractDraweeControllerBuilder.setControllerListener(controllerListener);
                } else if (reactImageDownloadListener != null) {
                    abstractDraweeControllerBuilder.setControllerListener(reactImageDownloadListener);
                }
            } else {
                ForwardingControllerListener forwardingControllerListener = new ForwardingControllerListener();
                forwardingControllerListener.addListener(this.downloadListener);
                forwardingControllerListener.addListener(this.controllerForTesting);
                abstractDraweeControllerBuilder.setControllerListener(forwardingControllerListener);
            }
            if (this.downloadListener != null) {
                ((GenericDraweeHierarchy) getHierarchy()).setProgressBarImage(this.downloadListener);
            }
            setController(abstractDraweeControllerBuilder.build());
            abstractDraweeControllerBuilder.reset();
        }
    }

    @VisibleForTesting
    public final void setControllerListener(ControllerListener controllerListener) {
        this.controllerForTesting = controllerListener;
        this.isDirty = true;
        maybeUpdateView();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0) {
            this.isDirty = this.isDirty || hasMultipleSources() || isTiled();
            maybeUpdateView();
        }
    }

    private final boolean hasMultipleSources() {
        return this.sources.size() > 1;
    }

    private final boolean isTiled() {
        return this.tileMode != Shader.TileMode.CLAMP;
    }

    private final void setSourceImage() {
        this.imageSource = null;
        if (this.sources.isEmpty()) {
            List<ImageSource> list = this.sources;
            ImageSource.Companion companion = ImageSource.Companion;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            list.add(companion.getTransparentBitmapImageSource(context));
        } else if (hasMultipleSources()) {
            MultiSourceHelper.MultiSourceResult bestSourceForSize = MultiSourceHelper.getBestSourceForSize(getWidth(), getHeight(), this.sources);
            this.imageSource = bestSourceForSize.bestResult;
            this.cachedImageSource = bestSourceForSize.bestResultInCache;
            return;
        }
        this.imageSource = this.sources.get(0);
    }

    private final boolean shouldResize(ImageSource imageSource2) {
        int i = WhenMappings.$EnumSwitchMapping$1[this.resizeMethod.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return false;
            }
        } else if (!UriUtil.isLocalContentUri(imageSource2.getUri()) && !UriUtil.isLocalFileUri(imageSource2.getUri())) {
            return false;
        }
        return true;
    }

    private final ResizeOptions getResizeOptions() {
        int round = Math.round(((float) getWidth()) * this.resizeMultiplier);
        int round2 = Math.round(((float) getHeight()) * this.resizeMultiplier);
        if (round <= 0 || round2 <= 0) {
            return null;
        }
        return new ResizeOptions(round, round2, 0.0f, 0.0f, 12, (DefaultConstructorMarker) null);
    }

    private final void warnImageSource(String str) {
        if (ReactBuildConfig.DEBUG && !ReactNativeFeatureFlags.enableBridgelessArchitecture()) {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            RNLog.w((ReactContext) context, "ReactImageView: Image source \"" + str + "\" doesn't exist");
        }
    }

    private final class TilePostprocessor extends BasePostprocessor {
        public TilePostprocessor() {
        }

        public CloseableReference process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
            Intrinsics.checkNotNullParameter(bitmap, "source");
            Intrinsics.checkNotNullParameter(platformBitmapFactory, "bitmapFactory");
            Rect rect = new Rect(0, 0, ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            ReactImageView.this.scaleType.getTransform(ReactImageView.tileMatrix, rect, bitmap.getWidth(), bitmap.getHeight(), 0.0f, 0.0f);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            BitmapShader bitmapShader = new BitmapShader(bitmap, ReactImageView.this.tileMode, ReactImageView.this.tileMode);
            bitmapShader.setLocalMatrix(ReactImageView.tileMatrix);
            paint.setShader(bitmapShader);
            CloseableReference createBitmap = platformBitmapFactory.createBitmap(ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            try {
                new Canvas((Bitmap) createBitmap.get()).drawRect(rect, paint);
                CloseableReference clone = createBitmap.clone();
                Intrinsics.checkNotNullExpressionValue(clone, "clone(...)");
                return clone;
            } finally {
                CloseableReference.closeSafely(createBitmap);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final GenericDraweeHierarchy buildHierarchy(Context context) {
            GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(context.getResources());
            RoundingParams fromCornersRadius = RoundingParams.fromCornersRadius(0.0f);
            fromCornersRadius.setPaintFilterBitmap(true);
            GenericDraweeHierarchy build = genericDraweeHierarchyBuilder.setRoundingParams(fromCornersRadius).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            return build;
        }
    }
}
