package com.facebook.drawee.controller;

import android.content.Context;
import android.graphics.drawable.Animatable;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.FirstAvailableDataSourceSupplier;
import com.facebook.datasource.IncreasingQualityDataSourceSupplier;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractDraweeControllerBuilder {
    private static final NullPointerException NO_REQUEST_EXCEPTION = new NullPointerException("No image request was specified!");
    private static final ControllerListener sAutoPlayAnimationsListener = new BaseControllerListener() {
        public void onFinalImageSet(String str, Object obj, Animatable animatable) {
            if (animatable != null) {
                animatable.start();
            }
        }
    };
    private static final AtomicLong sIdCounter = new AtomicLong();
    private boolean mAutoPlayAnimations;
    private final Set mBoundControllerListeners;
    private final Set mBoundControllerListeners2;
    private Object mCallerContext;
    private String mContentDescription;
    private final Context mContext;
    private ControllerListener mControllerListener;
    private Supplier mDataSourceSupplier;
    private Object mImageRequest;
    private boolean mLogWithHighSamplingRate = false;
    private Object mLowResImageRequest;
    private Object[] mMultiImageRequests;
    private DraweeController mOldController;
    private boolean mRetainImageOnFailure;
    private boolean mTapToRetryEnabled;
    private boolean mTryCacheOnlyFirst;

    public enum CacheLevel {
        FULL_FETCH,
        DISK_CACHE,
        BITMAP_MEMORY_CACHE
    }

    public ControllerViewportVisibilityListener getControllerViewportVisibilityListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract DataSource getDataSourceForRequest(DraweeController draweeController, String str, Object obj, Object obj2, CacheLevel cacheLevel);

    /* access modifiers changed from: protected */
    public final AbstractDraweeControllerBuilder getThis() {
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract AbstractDraweeController obtainController();

    protected AbstractDraweeControllerBuilder(Context context, Set set, Set set2) {
        this.mContext = context;
        this.mBoundControllerListeners = set;
        this.mBoundControllerListeners2 = set2;
        init();
    }

    private void init() {
        this.mCallerContext = null;
        this.mImageRequest = null;
        this.mLowResImageRequest = null;
        this.mMultiImageRequests = null;
        this.mTryCacheOnlyFirst = true;
        this.mControllerListener = null;
        this.mTapToRetryEnabled = false;
        this.mAutoPlayAnimations = false;
        this.mLogWithHighSamplingRate = false;
        this.mOldController = null;
        this.mContentDescription = null;
    }

    public AbstractDraweeControllerBuilder reset() {
        init();
        return getThis();
    }

    public AbstractDraweeControllerBuilder setCallerContext(Object obj) {
        this.mCallerContext = obj;
        return getThis();
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public AbstractDraweeControllerBuilder setImageRequest(Object obj) {
        this.mImageRequest = obj;
        return getThis();
    }

    public Object getImageRequest() {
        return this.mImageRequest;
    }

    public AbstractDraweeControllerBuilder setLowResImageRequest(Object obj) {
        this.mLowResImageRequest = obj;
        return getThis();
    }

    public Object getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    public Object[] getFirstAvailableImageRequests() {
        return this.mMultiImageRequests;
    }

    public boolean getRetainImageOnFailure() {
        return this.mRetainImageOnFailure;
    }

    public AbstractDraweeControllerBuilder setAutoPlayAnimations(boolean z) {
        this.mAutoPlayAnimations = z;
        return getThis();
    }

    public boolean isLogWithHighSamplingRate() {
        return this.mLogWithHighSamplingRate;
    }

    public AbstractDraweeControllerBuilder setControllerListener(ControllerListener controllerListener) {
        this.mControllerListener = controllerListener;
        return getThis();
    }

    public String getContentDescription() {
        return this.mContentDescription;
    }

    public AbstractDraweeControllerBuilder setOldController(DraweeController draweeController) {
        this.mOldController = draweeController;
        return getThis();
    }

    public DraweeController getOldController() {
        return this.mOldController;
    }

    public AbstractDraweeController build() {
        Object obj;
        validate();
        if (this.mImageRequest == null && this.mMultiImageRequests == null && (obj = this.mLowResImageRequest) != null) {
            this.mImageRequest = obj;
            this.mLowResImageRequest = null;
        }
        return buildController();
    }

    /* access modifiers changed from: protected */
    public void validate() {
        boolean z = true;
        Preconditions.checkState(this.mMultiImageRequests == null || this.mImageRequest == null, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        if (!(this.mDataSourceSupplier == null || (this.mMultiImageRequests == null && this.mImageRequest == null && this.mLowResImageRequest == null))) {
            z = false;
        }
        Preconditions.checkState(z, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
    }

    /* access modifiers changed from: protected */
    public AbstractDraweeController buildController() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeControllerBuilder#buildController");
        }
        AbstractDraweeController obtainController = obtainController();
        obtainController.setLogWithHighSamplingRate(isLogWithHighSamplingRate());
        obtainController.setRetainImageOnFailure(getRetainImageOnFailure());
        obtainController.setContentDescription(getContentDescription());
        getControllerViewportVisibilityListener();
        obtainController.setControllerViewportVisibilityListener((ControllerViewportVisibilityListener) null);
        maybeBuildAndSetRetryManager(obtainController);
        maybeAttachListeners(obtainController);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return obtainController;
    }

    protected static String generateUniqueControllerId() {
        return String.valueOf(sIdCounter.getAndIncrement());
    }

    /* access modifiers changed from: protected */
    public Supplier obtainDataSourceSupplier(DraweeController draweeController, String str) {
        Supplier supplier;
        Supplier supplier2 = this.mDataSourceSupplier;
        if (supplier2 != null) {
            return supplier2;
        }
        Object obj = this.mImageRequest;
        if (obj != null) {
            supplier = getDataSourceSupplierForRequest(draweeController, str, obj);
        } else {
            Object[] objArr = this.mMultiImageRequests;
            supplier = objArr != null ? getFirstAvailableDataSourceSupplier(draweeController, str, objArr, this.mTryCacheOnlyFirst) : null;
        }
        if (!(supplier == null || this.mLowResImageRequest == null)) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(supplier);
            arrayList.add(getDataSourceSupplierForRequest(draweeController, str, this.mLowResImageRequest));
            supplier = IncreasingQualityDataSourceSupplier.create(arrayList, false);
        }
        return supplier == null ? DataSources.getFailedDataSourceSupplier(NO_REQUEST_EXCEPTION) : supplier;
    }

    /* access modifiers changed from: protected */
    public Supplier getFirstAvailableDataSourceSupplier(DraweeController draweeController, String str, Object[] objArr, boolean z) {
        ArrayList arrayList = new ArrayList(objArr.length * 2);
        if (z) {
            for (Object dataSourceSupplierForRequest : objArr) {
                arrayList.add(getDataSourceSupplierForRequest(draweeController, str, dataSourceSupplierForRequest, CacheLevel.BITMAP_MEMORY_CACHE));
            }
        }
        for (Object dataSourceSupplierForRequest2 : objArr) {
            arrayList.add(getDataSourceSupplierForRequest(draweeController, str, dataSourceSupplierForRequest2));
        }
        return FirstAvailableDataSourceSupplier.create(arrayList);
    }

    /* access modifiers changed from: protected */
    public Supplier getDataSourceSupplierForRequest(DraweeController draweeController, String str, Object obj) {
        return getDataSourceSupplierForRequest(draweeController, str, obj, CacheLevel.FULL_FETCH);
    }

    /* access modifiers changed from: protected */
    public Supplier getDataSourceSupplierForRequest(DraweeController draweeController, String str, Object obj, CacheLevel cacheLevel) {
        final Object callerContext = getCallerContext();
        final DraweeController draweeController2 = draweeController;
        final String str2 = str;
        final Object obj2 = obj;
        final CacheLevel cacheLevel2 = cacheLevel;
        return new Supplier() {
            public DataSource get() {
                return AbstractDraweeControllerBuilder.this.getDataSourceForRequest(draweeController2, str2, obj2, callerContext, cacheLevel2);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("request", (Object) obj2.toString()).toString();
            }
        };
    }

    /* access modifiers changed from: protected */
    public void maybeAttachListeners(AbstractDraweeController abstractDraweeController) {
        Set<ControllerListener> set = this.mBoundControllerListeners;
        if (set != null) {
            for (ControllerListener addControllerListener : set) {
                abstractDraweeController.addControllerListener(addControllerListener);
            }
        }
        Set<ControllerListener2> set2 = this.mBoundControllerListeners2;
        if (set2 != null) {
            for (ControllerListener2 addControllerListener2 : set2) {
                abstractDraweeController.addControllerListener2(addControllerListener2);
            }
        }
        ControllerListener controllerListener = this.mControllerListener;
        if (controllerListener != null) {
            abstractDraweeController.addControllerListener(controllerListener);
        }
        if (this.mAutoPlayAnimations) {
            abstractDraweeController.addControllerListener(sAutoPlayAnimationsListener);
        }
    }

    /* access modifiers changed from: protected */
    public void maybeBuildAndSetRetryManager(AbstractDraweeController abstractDraweeController) {
        if (this.mTapToRetryEnabled) {
            abstractDraweeController.getRetryManager().setTapToRetryEnabled(this.mTapToRetryEnabled);
            maybeBuildAndSetGestureDetector(abstractDraweeController);
        }
    }

    /* access modifiers changed from: protected */
    public void maybeBuildAndSetGestureDetector(AbstractDraweeController abstractDraweeController) {
        if (abstractDraweeController.getGestureDetector() == null) {
            abstractDraweeController.setGestureDetector(GestureDetector.newInstance(this.mContext));
        }
    }
}
