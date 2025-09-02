package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.configurations.reducemotion.ReducedMotionMode;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.parser.LayerParser;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieThreadFactory;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LottieDrawable extends Drawable implements Drawable.Callback, Animatable {
    private static final List ALLOWED_REDUCED_MOTION_MARKERS = Arrays.asList(new String[]{"reduced motion", "reduced_motion", "reduced-motion", "reducedmotion"});
    private static final boolean invalidateSelfOnMainThread = false;
    private static final Executor setProgressExecutor = new ThreadPoolExecutor(0, 2, 35, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new LottieThreadFactory());
    private int alpha = 255;
    private final LottieValueAnimator animator;
    private AsyncUpdates asyncUpdates;
    private Rect canvasClipBounds;
    private RectF canvasClipBoundsRectF;
    private boolean clipTextToBoundingBox = false;
    private boolean clipToCompositionBounds = true;
    private LottieComposition composition;
    private CompositionLayer compositionLayer;
    String defaultFontFileExtension;
    FontAssetDelegate fontAssetDelegate;
    private FontAssetManager fontAssetManager;
    private Map fontMap;
    private boolean ignoreSystemAnimationsDisabled = false;
    private ImageAssetManager imageAssetManager;
    private String imageAssetsFolder;
    private Runnable invalidateSelfRunnable;
    private boolean isApplyingOpacityToLayersEnabled;
    private boolean isDirty = false;
    private float lastDrawnProgress;
    private final ArrayList lazyCompositionTasks = new ArrayList();
    private final LottieFeatureFlags lottieFeatureFlags = new LottieFeatureFlags();
    private Handler mainThreadHandler;
    private boolean maintainOriginalImageBounds = false;
    private OnVisibleAction onVisibleAction = OnVisibleAction.NONE;
    private boolean outlineMasksAndMattes;
    private boolean performanceTrackingEnabled;
    private final ValueAnimator.AnimatorUpdateListener progressUpdateListener;
    private RenderMode renderMode = RenderMode.AUTOMATIC;
    private final Matrix renderingMatrix = new Matrix();
    private boolean safeMode = false;
    private final Semaphore setProgressDrawLock;
    private Bitmap softwareRenderingBitmap;
    private Canvas softwareRenderingCanvas;
    private Rect softwareRenderingDstBoundsRect;
    private RectF softwareRenderingDstBoundsRectF;
    private Matrix softwareRenderingOriginalCanvasMatrix;
    private Matrix softwareRenderingOriginalCanvasMatrixInverse;
    private Paint softwareRenderingPaint;
    private Rect softwareRenderingSrcBoundsRect;
    private RectF softwareRenderingTransformedBounds;
    private boolean systemAnimationsEnabled = true;
    TextDelegate textDelegate;
    private final Runnable updateProgressRunnable;
    private boolean useSoftwareRendering = false;

    private interface LazyCompositionTask {
        void run(LottieComposition lottieComposition);
    }

    private enum OnVisibleAction {
        NONE,
        PLAY,
        RESUME
    }

    public int getOpacity() {
        return -3;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ValueAnimator valueAnimator) {
        if (getAsyncUpdatesEnabled()) {
            invalidateSelf();
            return;
        }
        CompositionLayer compositionLayer2 = this.compositionLayer;
        if (compositionLayer2 != null) {
            compositionLayer2.setProgress(this.animator.getAnimatedValueAbsolute());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2() {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        if (compositionLayer2 != null) {
            try {
                this.setProgressDrawLock.acquire();
                compositionLayer2.setProgress(this.animator.getAnimatedValueAbsolute());
                if (invalidateSelfOnMainThread && this.isDirty) {
                    if (this.mainThreadHandler == null) {
                        this.mainThreadHandler = new Handler(Looper.getMainLooper());
                        this.invalidateSelfRunnable = new LottieDrawable$$ExternalSyntheticLambda15(this);
                    }
                    this.mainThreadHandler.post(this.invalidateSelfRunnable);
                }
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                this.setProgressDrawLock.release();
                throw th;
            }
            this.setProgressDrawLock.release();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public LottieDrawable() {
        LottieValueAnimator lottieValueAnimator = new LottieValueAnimator();
        this.animator = lottieValueAnimator;
        LottieDrawable$$ExternalSyntheticLambda4 lottieDrawable$$ExternalSyntheticLambda4 = new LottieDrawable$$ExternalSyntheticLambda4(this);
        this.progressUpdateListener = lottieDrawable$$ExternalSyntheticLambda4;
        this.setProgressDrawLock = new Semaphore(1);
        this.updateProgressRunnable = new LottieDrawable$$ExternalSyntheticLambda5(this);
        this.lastDrawnProgress = -3.4028235E38f;
        lottieValueAnimator.addUpdateListener(lottieDrawable$$ExternalSyntheticLambda4);
    }

    public void enableFeatureFlag(LottieFeatureFlag lottieFeatureFlag, boolean z) {
        boolean enableFlag = this.lottieFeatureFlags.enableFlag(lottieFeatureFlag, z);
        if (this.composition != null && enableFlag) {
            buildCompositionLayer();
        }
    }

    public boolean isFeatureFlagEnabled(LottieFeatureFlag lottieFeatureFlag) {
        return this.lottieFeatureFlags.isFlagEnabled(lottieFeatureFlag);
    }

    public void setClipToCompositionBounds(boolean z) {
        if (z != this.clipToCompositionBounds) {
            this.clipToCompositionBounds = z;
            CompositionLayer compositionLayer2 = this.compositionLayer;
            if (compositionLayer2 != null) {
                compositionLayer2.setClipToCompositionBounds(z);
            }
            invalidateSelf();
        }
    }

    public boolean getClipToCompositionBounds() {
        return this.clipToCompositionBounds;
    }

    public void setImagesAssetsFolder(String str) {
        this.imageAssetsFolder = str;
    }

    public String getImageAssetsFolder() {
        return this.imageAssetsFolder;
    }

    public void setMaintainOriginalImageBounds(boolean z) {
        this.maintainOriginalImageBounds = z;
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.maintainOriginalImageBounds;
    }

    public boolean setComposition(LottieComposition lottieComposition) {
        if (this.composition == lottieComposition) {
            return false;
        }
        this.isDirty = true;
        clearComposition();
        this.composition = lottieComposition;
        buildCompositionLayer();
        this.animator.setComposition(lottieComposition);
        setProgress(this.animator.getAnimatedFraction());
        Iterator it = new ArrayList(this.lazyCompositionTasks).iterator();
        while (it.hasNext()) {
            LazyCompositionTask lazyCompositionTask = (LazyCompositionTask) it.next();
            if (lazyCompositionTask != null) {
                lazyCompositionTask.run(lottieComposition);
            }
            it.remove();
        }
        this.lazyCompositionTasks.clear();
        lottieComposition.setPerformanceTrackingEnabled(this.performanceTrackingEnabled);
        computeRenderMode();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ImageView imageView = (ImageView) callback;
            imageView.setImageDrawable((Drawable) null);
            imageView.setImageDrawable(this);
        }
        return true;
    }

    public void setRenderMode(RenderMode renderMode2) {
        this.renderMode = renderMode2;
        computeRenderMode();
    }

    public AsyncUpdates getAsyncUpdates() {
        AsyncUpdates asyncUpdates2 = this.asyncUpdates;
        if (asyncUpdates2 != null) {
            return asyncUpdates2;
        }
        return L.getDefaultAsyncUpdates();
    }

    public boolean getAsyncUpdatesEnabled() {
        return getAsyncUpdates() == AsyncUpdates.ENABLED;
    }

    public void setAsyncUpdates(AsyncUpdates asyncUpdates2) {
        this.asyncUpdates = asyncUpdates2;
    }

    public RenderMode getRenderMode() {
        return this.useSoftwareRendering ? RenderMode.SOFTWARE : RenderMode.HARDWARE;
    }

    private void computeRenderMode() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            this.useSoftwareRendering = this.renderMode.useSoftwareRendering(Build.VERSION.SDK_INT, lottieComposition.hasDashPattern(), lottieComposition.getMaskAndMatteCount());
        }
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.performanceTrackingEnabled = z;
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            lottieComposition.setPerformanceTrackingEnabled(z);
        }
    }

    public void setOutlineMasksAndMattes(boolean z) {
        if (this.outlineMasksAndMattes != z) {
            this.outlineMasksAndMattes = z;
            CompositionLayer compositionLayer2 = this.compositionLayer;
            if (compositionLayer2 != null) {
                compositionLayer2.setOutlineMasksAndMattes(z);
            }
        }
    }

    public PerformanceTracker getPerformanceTracker() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            return lottieComposition.getPerformanceTracker();
        }
        return null;
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.isApplyingOpacityToLayersEnabled = z;
    }

    public boolean isApplyingOpacityToLayersEnabled() {
        return this.isApplyingOpacityToLayersEnabled;
    }

    public boolean getClipTextToBoundingBox() {
        return this.clipTextToBoundingBox;
    }

    public void setClipTextToBoundingBox(boolean z) {
        if (z != this.clipTextToBoundingBox) {
            this.clipTextToBoundingBox = z;
            invalidateSelf();
        }
    }

    private void buildCompositionLayer() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            CompositionLayer compositionLayer2 = new CompositionLayer(this, LayerParser.parse(lottieComposition), lottieComposition.getLayers(), lottieComposition);
            this.compositionLayer = compositionLayer2;
            if (this.outlineMasksAndMattes) {
                compositionLayer2.setOutlineMasksAndMattes(true);
            }
            this.compositionLayer.setClipToCompositionBounds(this.clipToCompositionBounds);
        }
    }

    public void clearComposition() {
        if (this.animator.isRunning()) {
            this.animator.cancel();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
        this.composition = null;
        this.compositionLayer = null;
        this.imageAssetManager = null;
        this.lastDrawnProgress = -3.4028235E38f;
        this.animator.clearComposition();
        invalidateSelf();
    }

    public void setSafeMode(boolean z) {
        this.safeMode = z;
    }

    public void invalidateSelf() {
        Drawable.Callback callback;
        if (!this.isDirty) {
            this.isDirty = true;
            if ((!invalidateSelfOnMainThread || Looper.getMainLooper() == Looper.myLooper()) && (callback = getCallback()) != null) {
                callback.invalidateDrawable(this);
            }
        }
    }

    public void setAlpha(int i) {
        this.alpha = i;
        invalidateSelf();
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Logger.warning("Use addColorFilter instead.");
    }

    private boolean shouldSetProgressBeforeDrawing() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return false;
        }
        float f = this.lastDrawnProgress;
        float animatedValueAbsolute = this.animator.getAnimatedValueAbsolute();
        this.lastDrawnProgress = animatedValueAbsolute;
        if (Math.abs(animatedValueAbsolute - f) * lottieComposition.getDuration() >= 50.0f) {
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        if (compositionLayer2 != null) {
            boolean asyncUpdatesEnabled = getAsyncUpdatesEnabled();
            if (asyncUpdatesEnabled) {
                try {
                    this.setProgressDrawLock.acquire();
                } catch (InterruptedException unused) {
                    if (L.isTraceEnabled()) {
                        L.endSection("Drawable#draw");
                    }
                    if (asyncUpdatesEnabled) {
                        this.setProgressDrawLock.release();
                        if (compositionLayer2.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    if (L.isTraceEnabled()) {
                        L.endSection("Drawable#draw");
                    }
                    if (asyncUpdatesEnabled) {
                        this.setProgressDrawLock.release();
                        if (compositionLayer2.getProgress() != this.animator.getAnimatedValueAbsolute()) {
                            setProgressExecutor.execute(this.updateProgressRunnable);
                        }
                    }
                    throw th;
                }
            }
            if (L.isTraceEnabled()) {
                L.beginSection("Drawable#draw");
            }
            if (asyncUpdatesEnabled && shouldSetProgressBeforeDrawing()) {
                setProgress(this.animator.getAnimatedValueAbsolute());
            }
            if (this.safeMode) {
                try {
                    if (this.useSoftwareRendering) {
                        renderAndDrawAsBitmap(canvas, compositionLayer2);
                    } else {
                        drawDirectlyToCanvas(canvas);
                    }
                } catch (Throwable th2) {
                    Logger.error("Lottie crashed in draw!", th2);
                }
            } else if (this.useSoftwareRendering) {
                renderAndDrawAsBitmap(canvas, compositionLayer2);
            } else {
                drawDirectlyToCanvas(canvas);
            }
            this.isDirty = false;
            if (L.isTraceEnabled()) {
                L.endSection("Drawable#draw");
            }
            if (asyncUpdatesEnabled) {
                this.setProgressDrawLock.release();
                if (compositionLayer2.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                    return;
                }
                setProgressExecutor.execute(this.updateProgressRunnable);
            }
        }
    }

    public void start() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View) || !((View) callback).isInEditMode()) {
            playAnimation();
        }
    }

    public void stop() {
        endAnimation();
    }

    public boolean isRunning() {
        return isAnimating();
    }

    public void playAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda6(this));
            return;
        }
        computeRenderMode();
        if (animationsEnabled(getContext()) || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.playAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.PLAY;
            }
        }
        if (!animationsEnabled(getContext())) {
            Marker markerForAnimationsDisabled = getMarkerForAnimationsDisabled();
            if (markerForAnimationsDisabled != null) {
                setFrame((int) markerForAnimationsDisabled.startFrame);
            } else {
                setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
            }
            this.animator.endAnimation();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playAnimation$3(LottieComposition lottieComposition) {
        playAnimation();
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0007 A[LOOP:0: B:1:0x0007->B:4:0x0019, LOOP_START, PHI: r1 
      PHI: (r1v1 com.airbnb.lottie.model.Marker) = (r1v0 com.airbnb.lottie.model.Marker), (r1v5 com.airbnb.lottie.model.Marker) binds: [B:0:0x0000, B:4:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.airbnb.lottie.model.Marker getMarkerForAnimationsDisabled() {
        /*
            r3 = this;
            java.util.List r0 = ALLOWED_REDUCED_MOTION_MARKERS
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L_0x0007:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x001b
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            com.airbnb.lottie.LottieComposition r2 = r3.composition
            com.airbnb.lottie.model.Marker r1 = r2.getMarker(r1)
            if (r1 == 0) goto L_0x0007
        L_0x001b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.LottieDrawable.getMarkerForAnimationsDisabled():com.airbnb.lottie.model.Marker");
    }

    public void endAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.endAnimation();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public void resumeAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda1(this));
            return;
        }
        computeRenderMode();
        if (animationsEnabled(getContext()) || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.resumeAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.RESUME;
            }
        }
        if (!animationsEnabled(getContext())) {
            setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
            this.animator.endAnimation();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resumeAnimation$4(LottieComposition lottieComposition) {
        resumeAnimation();
    }

    public void setMinFrame(int i) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda12(this, i));
        } else {
            this.animator.setMinFrame(i);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinFrame$5(int i, LottieComposition lottieComposition) {
        setMinFrame(i);
    }

    public float getMinFrame() {
        return this.animator.getMinFrame();
    }

    public void setMinProgress(float f) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda7(this, f));
        } else {
            setMinFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), f));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinProgress$6(float f, LottieComposition lottieComposition) {
        setMinProgress(f);
    }

    public void setMaxFrame(int i) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda11(this, i));
        } else {
            this.animator.setMaxFrame(((float) i) + 0.99f);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMaxFrame$7(int i, LottieComposition lottieComposition) {
        setMaxFrame(i);
    }

    public float getMaxFrame() {
        return this.animator.getMaxFrame();
    }

    public void setMaxProgress(float f) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda14(this, f));
        } else {
            this.animator.setMaxFrame(MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), f));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMaxProgress$8(float f, LottieComposition lottieComposition) {
        setMaxProgress(f);
    }

    public void setMinFrame(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda3(this, str));
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            setMinFrame((int) marker.startFrame);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinFrame$9(String str, LottieComposition lottieComposition) {
        setMinFrame(str);
    }

    public void setMaxFrame(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda2(this, str));
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            setMaxFrame((int) (marker.startFrame + marker.durationFrames));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMaxFrame$10(String str, LottieComposition lottieComposition) {
        setMaxFrame(str);
    }

    public void setMinAndMaxFrame(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda0(this, str));
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            int i = (int) marker.startFrame;
            setMinAndMaxFrame(i, ((int) marker.durationFrames) + i);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinAndMaxFrame$11(String str, LottieComposition lottieComposition) {
        setMinAndMaxFrame(str);
    }

    public void setMinAndMaxFrame(int i, int i2) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda10(this, i, i2));
        } else {
            this.animator.setMinAndMaxFrames((float) i, ((float) i2) + 0.99f);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinAndMaxFrame$13(int i, int i2, LottieComposition lottieComposition) {
        setMinAndMaxFrame(i, i2);
    }

    public void reverseAnimationSpeed() {
        this.animator.reverseAnimationSpeed();
    }

    public void setSpeed(float f) {
        this.animator.setSpeed(f);
    }

    public float getSpeed() {
        return this.animator.getSpeed();
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animator.addListener(animatorListener);
    }

    public void setFrame(int i) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda9(this, i));
        } else {
            this.animator.setFrame((float) i);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFrame$15(int i, LottieComposition lottieComposition) {
        setFrame(i);
    }

    public int getFrame() {
        return (int) this.animator.getFrame();
    }

    public void setProgress(float f) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda8(this, f));
            return;
        }
        if (L.isTraceEnabled()) {
            L.beginSection("Drawable#setProgress");
        }
        this.animator.setFrame(this.composition.getFrameForProgress(f));
        if (L.isTraceEnabled()) {
            L.endSection("Drawable#setProgress");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setProgress$16(float f, LottieComposition lottieComposition) {
        setProgress(f);
    }

    public void setRepeatMode(int i) {
        this.animator.setRepeatMode(i);
    }

    public int getRepeatMode() {
        return this.animator.getRepeatMode();
    }

    public void setRepeatCount(int i) {
        this.animator.setRepeatCount(i);
    }

    public int getRepeatCount() {
        return this.animator.getRepeatCount();
    }

    public boolean isAnimating() {
        LottieValueAnimator lottieValueAnimator = this.animator;
        if (lottieValueAnimator == null) {
            return false;
        }
        return lottieValueAnimator.isRunning();
    }

    /* access modifiers changed from: package-private */
    public boolean isAnimatingOrWillAnimateOnVisible() {
        if (isVisible()) {
            return this.animator.isRunning();
        }
        OnVisibleAction onVisibleAction2 = this.onVisibleAction;
        return onVisibleAction2 == OnVisibleAction.PLAY || onVisibleAction2 == OnVisibleAction.RESUME;
    }

    public boolean animationsEnabled(Context context) {
        if (this.ignoreSystemAnimationsDisabled) {
            return true;
        }
        if (!this.systemAnimationsEnabled || L.getReducedMotionOption().getCurrentReducedMotionMode(context) != ReducedMotionMode.STANDARD_MOTION) {
            return false;
        }
        return true;
    }

    public void setIgnoreDisabledSystemAnimations(boolean z) {
        this.ignoreSystemAnimationsDisabled = z;
    }

    public void setUseCompositionFrameRate(boolean z) {
        this.animator.setUseCompositionFrameRate(z);
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate) {
        ImageAssetManager imageAssetManager2 = this.imageAssetManager;
        if (imageAssetManager2 != null) {
            imageAssetManager2.setDelegate(imageAssetDelegate);
        }
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate2) {
        this.fontAssetDelegate = fontAssetDelegate2;
        FontAssetManager fontAssetManager2 = this.fontAssetManager;
        if (fontAssetManager2 != null) {
            fontAssetManager2.setDelegate(fontAssetDelegate2);
        }
    }

    public void setFontMap(Map map) {
        if (map != this.fontMap) {
            this.fontMap = map;
            invalidateSelf();
        }
    }

    public void setTextDelegate(TextDelegate textDelegate2) {
        this.textDelegate = textDelegate2;
    }

    public TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public boolean useTextGlyphs() {
        return this.fontMap == null && this.textDelegate == null && this.composition.getCharacters().size() > 0;
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    public void cancelAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.cancel();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public void pauseAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.pauseAnimation();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public float getProgress() {
        return this.animator.getAnimatedValueAbsolute();
    }

    public int getIntrinsicWidth() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().width();
    }

    public int getIntrinsicHeight() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().height();
    }

    public List resolveKeyPath(KeyPath keyPath) {
        if (this.compositionLayer == null) {
            Logger.warning("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.compositionLayer.resolveKeyPath(keyPath, 0, arrayList, new KeyPath(new String[0]));
        return arrayList;
    }

    public void addValueCallback(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        if (compositionLayer2 == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda13(this, keyPath, obj, lottieValueCallback));
            return;
        }
        boolean z = true;
        if (keyPath == KeyPath.COMPOSITION) {
            compositionLayer2.addValueCallback(obj, lottieValueCallback);
        } else if (keyPath.getResolvedElement() != null) {
            keyPath.getResolvedElement().addValueCallback(obj, lottieValueCallback);
        } else {
            List resolveKeyPath = resolveKeyPath(keyPath);
            for (int i = 0; i < resolveKeyPath.size(); i++) {
                ((KeyPath) resolveKeyPath.get(i)).getResolvedElement().addValueCallback(obj, lottieValueCallback);
            }
            z = true ^ resolveKeyPath.isEmpty();
        }
        if (z) {
            invalidateSelf();
            if (obj == LottieProperty.TIME_REMAP) {
                setProgress(getProgress());
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addValueCallback$17(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback, LottieComposition lottieComposition) {
        addValueCallback(keyPath, obj, lottieValueCallback);
    }

    public Bitmap getBitmapForId(String str) {
        ImageAssetManager imageAssetManager2 = getImageAssetManager();
        if (imageAssetManager2 != null) {
            return imageAssetManager2.bitmapForId(str);
        }
        return null;
    }

    public LottieImageAsset getLottieImageAssetForId(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return null;
        }
        return (LottieImageAsset) lottieComposition.getImages().get(str);
    }

    private ImageAssetManager getImageAssetManager() {
        ImageAssetManager imageAssetManager2 = this.imageAssetManager;
        if (imageAssetManager2 != null && !imageAssetManager2.hasSameContext(getContext())) {
            this.imageAssetManager = null;
        }
        if (this.imageAssetManager == null) {
            this.imageAssetManager = new ImageAssetManager(getCallback(), this.imageAssetsFolder, (ImageAssetDelegate) null, this.composition.getImages());
        }
        return this.imageAssetManager;
    }

    public Typeface getTypeface(Font font) {
        Map map = this.fontMap;
        if (map != null) {
            String family = font.getFamily();
            if (map.containsKey(family)) {
                return (Typeface) map.get(family);
            }
            String name = font.getName();
            if (map.containsKey(name)) {
                return (Typeface) map.get(name);
            }
            String str = font.getFamily() + "-" + font.getStyle();
            if (map.containsKey(str)) {
                return (Typeface) map.get(str);
            }
        }
        FontAssetManager fontAssetManager2 = getFontAssetManager();
        if (fontAssetManager2 != null) {
            return fontAssetManager2.getTypeface(font);
        }
        return null;
    }

    private FontAssetManager getFontAssetManager() {
        if (getCallback() == null) {
            return null;
        }
        if (this.fontAssetManager == null) {
            FontAssetManager fontAssetManager2 = new FontAssetManager(getCallback(), this.fontAssetDelegate);
            this.fontAssetManager = fontAssetManager2;
            String str = this.defaultFontFileExtension;
            if (str != null) {
                fontAssetManager2.setDefaultFontFileExtension(str);
            }
        }
        return this.fontAssetManager;
    }

    public void setDefaultFontFileExtension(String str) {
        this.defaultFontFileExtension = str;
        FontAssetManager fontAssetManager2 = getFontAssetManager();
        if (fontAssetManager2 != null) {
            fontAssetManager2.setDefaultFontFileExtension(str);
        }
    }

    private Context getContext() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean isVisible = isVisible();
        boolean visible = super.setVisible(z, z2);
        if (z) {
            OnVisibleAction onVisibleAction2 = this.onVisibleAction;
            if (onVisibleAction2 == OnVisibleAction.PLAY) {
                playAnimation();
            } else if (onVisibleAction2 == OnVisibleAction.RESUME) {
                resumeAnimation();
            }
        } else if (this.animator.isRunning()) {
            pauseAnimation();
            this.onVisibleAction = OnVisibleAction.RESUME;
        } else if (isVisible) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
        return visible;
    }

    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    private void drawDirectlyToCanvas(Canvas canvas) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        LottieComposition lottieComposition = this.composition;
        if (compositionLayer2 != null && lottieComposition != null) {
            this.renderingMatrix.reset();
            Rect bounds = getBounds();
            if (!bounds.isEmpty()) {
                this.renderingMatrix.preScale(((float) bounds.width()) / ((float) lottieComposition.getBounds().width()), ((float) bounds.height()) / ((float) lottieComposition.getBounds().height()));
                this.renderingMatrix.preTranslate((float) bounds.left, (float) bounds.top);
            }
            compositionLayer2.draw(canvas, this.renderingMatrix, this.alpha);
        }
    }

    private void renderAndDrawAsBitmap(Canvas canvas, CompositionLayer compositionLayer2) {
        if (this.composition != null && compositionLayer2 != null) {
            ensureSoftwareRenderingObjectsInitialized();
            canvas.getMatrix(this.softwareRenderingOriginalCanvasMatrix);
            canvas.getClipBounds(this.canvasClipBounds);
            convertRect(this.canvasClipBounds, this.canvasClipBoundsRectF);
            this.softwareRenderingOriginalCanvasMatrix.mapRect(this.canvasClipBoundsRectF);
            convertRect(this.canvasClipBoundsRectF, this.canvasClipBounds);
            if (this.clipToCompositionBounds) {
                this.softwareRenderingTransformedBounds.set(0.0f, 0.0f, (float) getIntrinsicWidth(), (float) getIntrinsicHeight());
            } else {
                compositionLayer2.getBounds(this.softwareRenderingTransformedBounds, (Matrix) null, false);
            }
            this.softwareRenderingOriginalCanvasMatrix.mapRect(this.softwareRenderingTransformedBounds);
            Rect bounds = getBounds();
            float width = ((float) bounds.width()) / ((float) getIntrinsicWidth());
            float height = ((float) bounds.height()) / ((float) getIntrinsicHeight());
            scaleRect(this.softwareRenderingTransformedBounds, width, height);
            if (!ignoreCanvasClipBounds()) {
                RectF rectF = this.softwareRenderingTransformedBounds;
                Rect rect = this.canvasClipBounds;
                rectF.intersect((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
            }
            int ceil = (int) Math.ceil((double) this.softwareRenderingTransformedBounds.width());
            int ceil2 = (int) Math.ceil((double) this.softwareRenderingTransformedBounds.height());
            if (ceil > 0 && ceil2 > 0) {
                ensureSoftwareRenderingBitmap(ceil, ceil2);
                if (this.isDirty) {
                    this.renderingMatrix.set(this.softwareRenderingOriginalCanvasMatrix);
                    this.renderingMatrix.preScale(width, height);
                    Matrix matrix = this.renderingMatrix;
                    RectF rectF2 = this.softwareRenderingTransformedBounds;
                    matrix.postTranslate(-rectF2.left, -rectF2.top);
                    this.softwareRenderingBitmap.eraseColor(0);
                    compositionLayer2.draw(this.softwareRenderingCanvas, this.renderingMatrix, this.alpha);
                    this.softwareRenderingOriginalCanvasMatrix.invert(this.softwareRenderingOriginalCanvasMatrixInverse);
                    this.softwareRenderingOriginalCanvasMatrixInverse.mapRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingTransformedBounds);
                    convertRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingDstBoundsRect);
                }
                this.softwareRenderingSrcBoundsRect.set(0, 0, ceil, ceil2);
                canvas.drawBitmap(this.softwareRenderingBitmap, this.softwareRenderingSrcBoundsRect, this.softwareRenderingDstBoundsRect, this.softwareRenderingPaint);
            }
        }
    }

    private void ensureSoftwareRenderingObjectsInitialized() {
        if (this.softwareRenderingCanvas == null) {
            this.softwareRenderingCanvas = new Canvas();
            this.softwareRenderingTransformedBounds = new RectF();
            this.softwareRenderingOriginalCanvasMatrix = new Matrix();
            this.softwareRenderingOriginalCanvasMatrixInverse = new Matrix();
            this.canvasClipBounds = new Rect();
            this.canvasClipBoundsRectF = new RectF();
            this.softwareRenderingPaint = new LPaint();
            this.softwareRenderingSrcBoundsRect = new Rect();
            this.softwareRenderingDstBoundsRect = new Rect();
            this.softwareRenderingDstBoundsRectF = new RectF();
        }
    }

    private void ensureSoftwareRenderingBitmap(int i, int i2) {
        Bitmap bitmap = this.softwareRenderingBitmap;
        if (bitmap == null || bitmap.getWidth() < i || this.softwareRenderingBitmap.getHeight() < i2) {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            this.softwareRenderingBitmap = createBitmap;
            this.softwareRenderingCanvas.setBitmap(createBitmap);
            this.isDirty = true;
        } else if (this.softwareRenderingBitmap.getWidth() > i || this.softwareRenderingBitmap.getHeight() > i2) {
            Bitmap createBitmap2 = Bitmap.createBitmap(this.softwareRenderingBitmap, 0, 0, i, i2);
            this.softwareRenderingBitmap = createBitmap2;
            this.softwareRenderingCanvas.setBitmap(createBitmap2);
            this.isDirty = true;
        }
    }

    private void convertRect(RectF rectF, Rect rect) {
        rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
    }

    private void convertRect(Rect rect, RectF rectF) {
        rectF.set((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
    }

    private void scaleRect(RectF rectF, float f, float f2) {
        rectF.set(rectF.left * f, rectF.top * f2, rectF.right * f, rectF.bottom * f2);
    }

    private boolean ignoreCanvasClipBounds() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View)) {
            return false;
        }
        ViewParent parent = ((View) callback).getParent();
        if (parent instanceof ViewGroup) {
            return !((ViewGroup) parent).getClipChildren();
        }
        return false;
    }
}
