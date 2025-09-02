package com.facebook.drawee.controller;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.MotionEvent;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.fresco.middleware.MiddlewareUtils;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.ForwardingControllerListener2;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import java.util.concurrent.Executor;

public abstract class AbstractDraweeController implements DraweeController, DeferredReleaser.Releasable, GestureDetector.ClickListener {
    private static final Map COMPONENT_EXTRAS = ImmutableMap.of("component_tag", "drawee");
    private static final Map SHORTCUT_EXTRAS = ImmutableMap.of("origin", "memory_bitmap", "origin_sub", "shortcut");
    private static final Class TAG = AbstractDraweeController.class;
    private Object mCallerContext;
    private String mContentDescription;
    protected ControllerListener mControllerListener;
    protected ForwardingControllerListener2 mControllerListener2 = new ForwardingControllerListener2();
    private Drawable mControllerOverlay;
    private DataSource mDataSource;
    private final DeferredReleaser mDeferredReleaser;
    protected Drawable mDrawable;
    private final DraweeEventTracker mEventTracker = DraweeEventTracker.newInstance();
    private Object mFetchedImage;
    private GestureDetector mGestureDetector;
    private boolean mHasFetchFailed;
    private String mId;
    private boolean mIsAttached;
    private boolean mIsRequestSubmitted;
    private boolean mIsVisibleInViewportHint;
    private boolean mJustConstructed = true;
    private boolean mLogWithHighSamplingRate = false;
    private boolean mRetainImageOnFailure;
    private RetryManager mRetryManager;
    private SettableDraweeHierarchy mSettableDraweeHierarchy;
    private final Executor mUiThreadImmediateExecutor;

    /* access modifiers changed from: protected */
    public abstract Drawable createDrawable(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object getCachedImage();

    /* access modifiers changed from: protected */
    public abstract DataSource getDataSource();

    /* access modifiers changed from: protected */
    public abstract int getImageHash(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object getImageInfo(Object obj);

    /* access modifiers changed from: protected */
    public abstract Uri getMainUri();

    public abstract Map obtainExtrasFromImage(Object obj);

    /* access modifiers changed from: protected */
    public void onImageLoadedFromCacheImmediately(String str, Object obj) {
    }

    /* access modifiers changed from: protected */
    public abstract void releaseDrawable(Drawable drawable);

    /* access modifiers changed from: protected */
    public abstract void releaseImage(Object obj);

    public void setControllerViewportVisibilityListener(ControllerViewportVisibilityListener controllerViewportVisibilityListener) {
    }

    private static class InternalForwardingListener extends ForwardingControllerListener {
        private InternalForwardingListener() {
        }

        public static InternalForwardingListener createInternal(ControllerListener controllerListener, ControllerListener controllerListener2) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("AbstractDraweeController#createInternal");
            }
            InternalForwardingListener internalForwardingListener = new InternalForwardingListener();
            internalForwardingListener.addListener(controllerListener);
            internalForwardingListener.addListener(controllerListener2);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            return internalForwardingListener;
        }
    }

    public AbstractDraweeController(DeferredReleaser deferredReleaser, Executor executor, String str, Object obj) {
        this.mDeferredReleaser = deferredReleaser;
        this.mUiThreadImmediateExecutor = executor;
        init(str, obj);
    }

    /* access modifiers changed from: protected */
    public void initialize(String str, Object obj) {
        init(str, obj);
        this.mJustConstructed = false;
        this.mLogWithHighSamplingRate = false;
    }

    private synchronized void init(String str, Object obj) {
        DeferredReleaser deferredReleaser;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("AbstractDraweeController#init");
            }
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_INIT_CONTROLLER);
            if (!this.mJustConstructed && (deferredReleaser = this.mDeferredReleaser) != null) {
                deferredReleaser.cancelDeferredRelease(this);
            }
            this.mIsAttached = false;
            this.mIsVisibleInViewportHint = false;
            releaseFetch();
            this.mRetainImageOnFailure = false;
            RetryManager retryManager = this.mRetryManager;
            if (retryManager != null) {
                retryManager.init();
            }
            GestureDetector gestureDetector = this.mGestureDetector;
            if (gestureDetector != null) {
                gestureDetector.init();
                this.mGestureDetector.setClickListener(this);
            }
            ControllerListener controllerListener = this.mControllerListener;
            if (controllerListener instanceof InternalForwardingListener) {
                ((InternalForwardingListener) controllerListener).clearListeners();
            } else {
                this.mControllerListener = null;
            }
            SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
            if (settableDraweeHierarchy != null) {
                settableDraweeHierarchy.reset();
                this.mSettableDraweeHierarchy.setControllerOverlay((Drawable) null);
                this.mSettableDraweeHierarchy = null;
            }
            this.mControllerOverlay = null;
            if (FLog.isLoggable(2)) {
                FLog.v(TAG, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.mId, str);
            }
            this.mId = str;
            this.mCallerContext = obj;
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void release() {
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_RELEASE_CONTROLLER);
        RetryManager retryManager = this.mRetryManager;
        if (retryManager != null) {
            retryManager.reset();
        }
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.reset();
        }
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy != null) {
            settableDraweeHierarchy.reset();
        }
        releaseFetch();
    }

    private void releaseFetch() {
        Map map;
        boolean z = this.mIsRequestSubmitted;
        this.mIsRequestSubmitted = false;
        this.mHasFetchFailed = false;
        DataSource dataSource = this.mDataSource;
        Map map2 = null;
        if (dataSource != null) {
            map = dataSource.getExtras();
            this.mDataSource.close();
            this.mDataSource = null;
        } else {
            map = null;
        }
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            releaseDrawable(drawable);
        }
        if (this.mContentDescription != null) {
            this.mContentDescription = null;
        }
        this.mDrawable = null;
        Object obj = this.mFetchedImage;
        if (obj != null) {
            Map obtainExtrasFromImage = obtainExtrasFromImage(getImageInfo(obj));
            logMessageAndImage("release", this.mFetchedImage);
            releaseImage(this.mFetchedImage);
            this.mFetchedImage = null;
            map2 = obtainExtrasFromImage;
        }
        if (z) {
            reportRelease(map, map2);
        }
    }

    public String getId() {
        return this.mId;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    /* access modifiers changed from: protected */
    public RetryManager getRetryManager() {
        if (this.mRetryManager == null) {
            this.mRetryManager = new RetryManager();
        }
        return this.mRetryManager;
    }

    /* access modifiers changed from: protected */
    public GestureDetector getGestureDetector() {
        return this.mGestureDetector;
    }

    /* access modifiers changed from: protected */
    public void setGestureDetector(GestureDetector gestureDetector) {
        this.mGestureDetector = gestureDetector;
        if (gestureDetector != null) {
            gestureDetector.setClickListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void setRetainImageOnFailure(boolean z) {
        this.mRetainImageOnFailure = z;
    }

    /* access modifiers changed from: protected */
    public boolean isLogWithHighSamplingRate() {
        return this.mLogWithHighSamplingRate;
    }

    /* access modifiers changed from: protected */
    public void setLogWithHighSamplingRate(boolean z) {
        this.mLogWithHighSamplingRate = z;
    }

    public void setContentDescription(String str) {
        this.mContentDescription = str;
    }

    public void addControllerListener(ControllerListener controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        ControllerListener controllerListener2 = this.mControllerListener;
        if (controllerListener2 instanceof InternalForwardingListener) {
            ((InternalForwardingListener) controllerListener2).addListener(controllerListener);
        } else if (controllerListener2 != null) {
            this.mControllerListener = InternalForwardingListener.createInternal(controllerListener2, controllerListener);
        } else {
            this.mControllerListener = controllerListener;
        }
    }

    public void addControllerListener2(ControllerListener2 controllerListener2) {
        this.mControllerListener2.addListener(controllerListener2);
    }

    public void removeControllerListener2(ControllerListener2 controllerListener2) {
        this.mControllerListener2.removeListener(controllerListener2);
    }

    /* access modifiers changed from: protected */
    public ControllerListener getControllerListener() {
        ControllerListener controllerListener = this.mControllerListener;
        return controllerListener == null ? BaseControllerListener.getNoOpListener() : controllerListener;
    }

    /* access modifiers changed from: protected */
    public ControllerListener2 getControllerListener2() {
        return this.mControllerListener2;
    }

    public DraweeHierarchy getHierarchy() {
        return this.mSettableDraweeHierarchy;
    }

    public void setHierarchy(DraweeHierarchy draweeHierarchy) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, draweeHierarchy);
        }
        this.mEventTracker.recordEvent(draweeHierarchy != null ? DraweeEventTracker.Event.ON_SET_HIERARCHY : DraweeEventTracker.Event.ON_CLEAR_HIERARCHY);
        if (this.mIsRequestSubmitted) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
            release();
        }
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy != null) {
            settableDraweeHierarchy.setControllerOverlay((Drawable) null);
            this.mSettableDraweeHierarchy = null;
        }
        if (draweeHierarchy != null) {
            Preconditions.checkArgument(Boolean.valueOf(draweeHierarchy instanceof SettableDraweeHierarchy));
            SettableDraweeHierarchy settableDraweeHierarchy2 = (SettableDraweeHierarchy) draweeHierarchy;
            this.mSettableDraweeHierarchy = settableDraweeHierarchy2;
            settableDraweeHierarchy2.setControllerOverlay(this.mControllerOverlay);
        }
    }

    /* access modifiers changed from: protected */
    public void setControllerOverlay(Drawable drawable) {
        this.mControllerOverlay = drawable;
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy != null) {
            settableDraweeHierarchy.setControllerOverlay(drawable);
        }
    }

    /* access modifiers changed from: protected */
    public Drawable getControllerOverlay() {
        return this.mControllerOverlay;
    }

    public void onAttach() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#onAttach");
        }
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onAttach: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, this.mIsRequestSubmitted ? "request already submitted" : "request needs submit");
        }
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_ATTACH_CONTROLLER);
        Preconditions.checkNotNull(this.mSettableDraweeHierarchy);
        this.mDeferredReleaser.cancelDeferredRelease(this);
        this.mIsAttached = true;
        if (!this.mIsRequestSubmitted) {
            submitRequest();
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    public void onDetach() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#onDetach");
        }
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onDetach", Integer.valueOf(System.identityHashCode(this)), this.mId);
        }
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_DETACH_CONTROLLER);
        this.mIsAttached = false;
        this.mDeferredReleaser.scheduleDeferredRelease(this);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(this)), this.mId, motionEvent);
        }
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector == null) {
            return false;
        }
        if (!gestureDetector.isCapturingGesture() && !shouldHandleGesture()) {
            return false;
        }
        this.mGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldHandleGesture() {
        return shouldRetryOnTap();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.mRetryManager;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean shouldRetryOnTap() {
        /*
            r1 = this;
            boolean r0 = r1.mHasFetchFailed
            if (r0 == 0) goto L_0x0010
            com.facebook.drawee.components.RetryManager r0 = r1.mRetryManager
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.shouldRetryOnTap()
            if (r0 == 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.controller.AbstractDraweeController.shouldRetryOnTap():boolean");
    }

    public boolean onClick() {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onClick", Integer.valueOf(System.identityHashCode(this)), this.mId);
        }
        if (!shouldRetryOnTap()) {
            return false;
        }
        this.mRetryManager.notifyTapToRetry();
        this.mSettableDraweeHierarchy.reset();
        submitRequest();
        return true;
    }

    /* access modifiers changed from: protected */
    public void submitRequest() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#submitRequest");
        }
        Object cachedImage = getCachedImage();
        if (cachedImage != null) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("AbstractDraweeController#submitRequest->cache");
            }
            this.mDataSource = null;
            this.mIsRequestSubmitted = true;
            this.mHasFetchFailed = false;
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_SUBMIT_CACHE_HIT);
            reportSubmit(this.mDataSource, getImageInfo(cachedImage));
            onImageLoadedFromCacheImmediately(this.mId, cachedImage);
            onNewResultInternal(this.mId, this.mDataSource, cachedImage, 1.0f, true, true, true);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_DATASOURCE_SUBMIT);
        this.mSettableDraweeHierarchy.setProgress(0.0f, true);
        this.mIsRequestSubmitted = true;
        this.mHasFetchFailed = false;
        DataSource dataSource = getDataSource();
        this.mDataSource = dataSource;
        reportSubmit(dataSource, (Object) null);
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.mId, Integer.valueOf(System.identityHashCode(this.mDataSource)));
        }
        final String str = this.mId;
        final boolean hasResult = this.mDataSource.hasResult();
        this.mDataSource.subscribe(new BaseDataSubscriber() {
            public void onNewResultImpl(DataSource dataSource) {
                boolean isFinished = dataSource.isFinished();
                boolean hasMultipleResults = dataSource.hasMultipleResults();
                float progress = dataSource.getProgress();
                Object result = dataSource.getResult();
                if (result != null) {
                    AbstractDraweeController.this.onNewResultInternal(str, dataSource, result, progress, isFinished, hasResult, hasMultipleResults);
                } else if (isFinished) {
                    AbstractDraweeController.this.onFailureInternal(str, dataSource, new NullPointerException(), true);
                }
            }

            public void onFailureImpl(DataSource dataSource) {
                AbstractDraweeController.this.onFailureInternal(str, dataSource, dataSource.getFailureCause(), true);
            }

            public void onProgressUpdate(DataSource dataSource) {
                boolean isFinished = dataSource.isFinished();
                AbstractDraweeController.this.onProgressUpdateInternal(str, dataSource, dataSource.getProgress(), isFinished);
            }
        }, this.mUiThreadImmediateExecutor);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public void onNewResultInternal(String str, DataSource dataSource, Object obj, float f, boolean z, boolean z2, boolean z3) {
        Drawable createDrawable;
        Object obj2;
        Drawable drawable;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("AbstractDraweeController#onNewResultInternal");
            }
            if (!isExpectedDataSource(str, dataSource)) {
                logMessageAndImage("ignore_old_datasource @ onNewResult", obj);
                releaseImage(obj);
                dataSource.close();
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                    return;
                }
                return;
            }
            this.mEventTracker.recordEvent(z ? DraweeEventTracker.Event.ON_DATASOURCE_RESULT : DraweeEventTracker.Event.ON_DATASOURCE_RESULT_INT);
            try {
                createDrawable = createDrawable(obj);
                obj2 = this.mFetchedImage;
                drawable = this.mDrawable;
                this.mFetchedImage = obj;
                this.mDrawable = createDrawable;
                if (z) {
                    logMessageAndImage("set_final_result @ onNewResult", obj);
                    this.mDataSource = null;
                    getSettableDraweeHierarchy().setImage(createDrawable, 1.0f, z2);
                    reportSuccess(str, obj, dataSource);
                } else if (z3) {
                    logMessageAndImage("set_temporary_result @ onNewResult", obj);
                    getSettableDraweeHierarchy().setImage(createDrawable, 1.0f, z2);
                    reportSuccess(str, obj, dataSource);
                } else {
                    logMessageAndImage("set_intermediate_result @ onNewResult", obj);
                    getSettableDraweeHierarchy().setImage(createDrawable, f, z2);
                    reportIntermediateSet(str, obj);
                }
                if (!(drawable == null || drawable == createDrawable)) {
                    releaseDrawable(drawable);
                }
                if (!(obj2 == null || obj2 == obj)) {
                    logMessageAndImage("release_previous_result @ onNewResult", obj2);
                    releaseImage(obj2);
                }
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } catch (Exception e) {
                logMessageAndImage("drawable_failed @ onNewResult", obj);
                releaseImage(obj);
                onFailureInternal(str, dataSource, e, z);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void onFailureInternal(String str, DataSource dataSource, Throwable th, boolean z) {
        Drawable drawable;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#onFailureInternal");
        }
        if (!isExpectedDataSource(str, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onFailure", th);
            dataSource.close();
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        this.mEventTracker.recordEvent(z ? DraweeEventTracker.Event.ON_DATASOURCE_FAILURE : DraweeEventTracker.Event.ON_DATASOURCE_FAILURE_INT);
        if (z) {
            logMessageAndFailure("final_failed @ onFailure", th);
            this.mDataSource = null;
            this.mHasFetchFailed = true;
            SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
            if (settableDraweeHierarchy != null) {
                if (this.mRetainImageOnFailure && (drawable = this.mDrawable) != null) {
                    settableDraweeHierarchy.setImage(drawable, 1.0f, true);
                } else if (shouldRetryOnTap()) {
                    settableDraweeHierarchy.setRetry(th);
                } else {
                    settableDraweeHierarchy.setFailure(th);
                }
            }
            reportFailure(th, dataSource);
        } else {
            logMessageAndFailure("intermediate_failed @ onFailure", th);
            reportIntermediateFailure(th);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public void onProgressUpdateInternal(String str, DataSource dataSource, float f, boolean z) {
        if (!isExpectedDataSource(str, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onProgress", (Throwable) null);
            dataSource.close();
        } else if (!z) {
            this.mSettableDraweeHierarchy.setProgress(f, false);
        }
    }

    private boolean isExpectedDataSource(String str, DataSource dataSource) {
        if (dataSource == null && this.mDataSource == null) {
            return true;
        }
        if (!str.equals(this.mId) || dataSource != this.mDataSource || !this.mIsRequestSubmitted) {
            return false;
        }
        return true;
    }

    private void logMessageAndImage(String str, Object obj) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: %s: image: %s %x", Integer.valueOf(System.identityHashCode(this)), this.mId, str, getImageClass(obj), Integer.valueOf(getImageHash(obj)));
        }
    }

    private void logMessageAndFailure(String str, Throwable th) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: %s: failure: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, str, th);
        }
    }

    public Animatable getAnimatable() {
        Drawable drawable = this.mDrawable;
        if (drawable instanceof Animatable) {
            return (Animatable) drawable;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String getImageClass(Object obj) {
        return obj != null ? obj.getClass().getSimpleName() : "<null>";
    }

    public String toString() {
        return Objects.toStringHelper(this).add("isAttached", this.mIsAttached).add("isRequestSubmitted", this.mIsRequestSubmitted).add("hasFetchFailed", this.mHasFetchFailed).add("fetchedImage", getImageHash(this.mFetchedImage)).add("events", (Object) this.mEventTracker.toString()).toString();
    }

    /* access modifiers changed from: protected */
    public void reportSubmit(DataSource dataSource, Object obj) {
        getControllerListener().onSubmit(this.mId, this.mCallerContext);
        getControllerListener2().onSubmit(this.mId, this.mCallerContext, obtainExtras(dataSource, obj, getMainUri()));
    }

    private void reportIntermediateSet(String str, Object obj) {
        Object imageInfo = getImageInfo(obj);
        getControllerListener().onIntermediateImageSet(str, imageInfo);
        getControllerListener2().onIntermediateImageSet(str, imageInfo);
    }

    private void reportIntermediateFailure(Throwable th) {
        getControllerListener().onIntermediateImageFailed(this.mId, th);
        getControllerListener2().onIntermediateImageFailed(this.mId);
    }

    private void reportSuccess(String str, Object obj, DataSource dataSource) {
        Object imageInfo = getImageInfo(obj);
        getControllerListener().onFinalImageSet(str, imageInfo, getAnimatable());
        getControllerListener2().onFinalImageSet(str, imageInfo, obtainExtras(dataSource, imageInfo, (Uri) null));
    }

    private void reportFailure(Throwable th, DataSource dataSource) {
        ControllerListener2.Extras obtainExtras = obtainExtras(dataSource, (Object) null, (Uri) null);
        getControllerListener().onFailure(this.mId, th);
        getControllerListener2().onFailure(this.mId, th, obtainExtras);
    }

    private void reportRelease(Map map, Map map2) {
        getControllerListener().onRelease(this.mId);
        getControllerListener2().onRelease(this.mId, obtainExtras(map, map2, (Uri) null));
    }

    private ControllerListener2.Extras obtainExtras(Map map, Map map2, Uri uri) {
        PointF pointF;
        String str;
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy instanceof GenericDraweeHierarchy) {
            GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) settableDraweeHierarchy;
            String valueOf = String.valueOf(genericDraweeHierarchy.getActualImageScaleType());
            pointF = genericDraweeHierarchy.getActualImageFocusPoint();
            str = valueOf;
        } else {
            str = null;
            pointF = null;
        }
        return MiddlewareUtils.obtainExtras(COMPONENT_EXTRAS, SHORTCUT_EXTRAS, map, (Map) null, getDimensions(), str, pointF, map2, getCallerContext(), isLogWithHighSamplingRate(), uri);
    }

    private ControllerListener2.Extras obtainExtras(DataSource dataSource, Object obj, Uri uri) {
        return obtainExtras(dataSource == null ? null : dataSource.getExtras(), obtainExtrasFromImage(obj), uri);
    }

    private Rect getDimensions() {
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy == null) {
            return null;
        }
        return settableDraweeHierarchy.getBounds();
    }

    private SettableDraweeHierarchy getSettableDraweeHierarchy() {
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy != null) {
            return settableDraweeHierarchy;
        }
        throw new IllegalStateException("mSettableDraweeHierarchy is null; Caller context: " + this.mCallerContext);
    }
}
