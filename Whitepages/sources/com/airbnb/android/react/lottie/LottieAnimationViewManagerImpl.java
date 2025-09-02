package com.airbnb.android.react.lottie;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.RenderMode;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class LottieAnimationViewManagerImpl {
    public static final LottieAnimationViewManagerImpl INSTANCE = new LottieAnimationViewManagerImpl();

    private LottieAnimationViewManagerImpl() {
    }

    public static final Map getExportedViewConstants() {
        Map build = MapBuilder.builder().put("VERSION", 1).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final LottieAnimationView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        LottieAnimationView lottieAnimationView = new LottieAnimationView(themedReactContext);
        lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return lottieAnimationView;
    }

    public static final void sendOnAnimationFinishEvent(LottieAnimationView lottieAnimationView, boolean z) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        Context context = lottieAnimationView.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, lottieAnimationView.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationFinishEvent(themedReactContext.getSurfaceId(), lottieAnimationView.getId(), z));
        }
    }

    public static final void sendAnimationFailureEvent(LottieAnimationView lottieAnimationView, Throwable th) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        Intrinsics.checkNotNullParameter(th, "error");
        Context context = lottieAnimationView.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, lottieAnimationView.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationFailureEvent(themedReactContext.getSurfaceId(), lottieAnimationView.getId(), th));
        }
    }

    public static final void sendAnimationLoadedEvent(LottieAnimationView lottieAnimationView) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        Context context = lottieAnimationView.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, lottieAnimationView.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationLoadedEvent(themedReactContext.getSurfaceId(), lottieAnimationView.getId()));
        }
    }

    public static final Map getExportedCustomDirectEventTypeConstants() {
        Map of = MapBuilder.of("topAnimationFinish", MapBuilder.of("registrationName", "onAnimationFinish"), "topAnimationFailure", MapBuilder.of("registrationName", "onAnimationFailure"), "topAnimationLoaded", MapBuilder.of("registrationName", "onAnimationLoaded"));
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        return of;
    }

    public static final void play(LottieAnimationView lottieAnimationView, int i, int i2) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        new Handler(Looper.getMainLooper()).post(new LottieAnimationViewManagerImpl$$ExternalSyntheticLambda2((i == -1 || i2 == -1) ? false : true, i, i2, lottieAnimationView));
    }

    /* access modifiers changed from: private */
    public static final void play$lambda$1(boolean z, int i, int i2, LottieAnimationView lottieAnimationView) {
        if (!z) {
            LottieComposition composition = lottieAnimationView.getComposition();
            Integer num = null;
            Integer valueOf = composition != null ? Integer.valueOf((int) composition.getStartFrame()) : null;
            LottieComposition composition2 = lottieAnimationView.getComposition();
            if (composition2 != null) {
                num = Integer.valueOf((int) composition2.getEndFrame());
            }
            int minFrame = (int) lottieAnimationView.getMinFrame();
            int maxFrame = (int) lottieAnimationView.getMaxFrame();
            if (!(valueOf == null || num == null || (minFrame == valueOf.intValue() && maxFrame == num.intValue()))) {
                lottieAnimationView.setMinAndMaxFrame(valueOf.intValue(), num.intValue());
            }
        } else if (i > i2) {
            lottieAnimationView.setMinAndMaxFrame(i2, i);
            if (lottieAnimationView.getSpeed() > 0.0f) {
                lottieAnimationView.reverseAnimationSpeed();
            }
        } else {
            lottieAnimationView.setMinAndMaxFrame(i, i2);
            if (lottieAnimationView.getSpeed() < 0.0f) {
                lottieAnimationView.reverseAnimationSpeed();
            }
        }
        if (!ViewCompat.isAttachedToWindow(lottieAnimationView)) {
            lottieAnimationView.addOnAttachStateChangeListener(new LottieAnimationViewManagerImpl$play$1$1(z, lottieAnimationView));
        } else if (z) {
            lottieAnimationView.playAnimation();
        } else {
            lottieAnimationView.resumeAnimation();
        }
    }

    public static final void reset(LottieAnimationView lottieAnimationView) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        new Handler(Looper.getMainLooper()).post(new LottieAnimationViewManagerImpl$$ExternalSyntheticLambda0(lottieAnimationView));
    }

    /* access modifiers changed from: private */
    public static final void reset$lambda$2(LottieAnimationView lottieAnimationView) {
        if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
            lottieAnimationView.cancelAnimation();
            lottieAnimationView.setProgress(0.0f);
        }
    }

    public static final void pause(LottieAnimationView lottieAnimationView) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        new Handler(Looper.getMainLooper()).post(new LottieAnimationViewManagerImpl$$ExternalSyntheticLambda3(lottieAnimationView));
    }

    /* access modifiers changed from: private */
    public static final void pause$lambda$3(LottieAnimationView lottieAnimationView) {
        if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
            lottieAnimationView.pauseAnimation();
        }
    }

    public static final void resume(LottieAnimationView lottieAnimationView) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        new Handler(Looper.getMainLooper()).post(new LottieAnimationViewManagerImpl$$ExternalSyntheticLambda1(lottieAnimationView));
    }

    /* access modifiers changed from: private */
    public static final void resume$lambda$4(LottieAnimationView lottieAnimationView) {
        if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
            lottieAnimationView.resumeAnimation();
        }
    }

    public static final void setSourceName(String str, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        if (str != null && !StringsKt.contains$default((CharSequence) str, (CharSequence) ".", false, 2, (Object) null)) {
            str = str + ".json";
        }
        lottieAnimationViewPropertyManager.setAnimationName(str);
        lottieAnimationViewPropertyManager.commitChanges();
    }

    public static final void setSourceJson(String str, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setAnimationJson(str);
        lottieAnimationViewPropertyManager.commitChanges();
    }

    public static final void setSourceURL(String str, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setAnimationURL(str);
        lottieAnimationViewPropertyManager.commitChanges();
    }

    public static final void setSourceDotLottieURI(String str, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setSourceDotLottie(str);
        lottieAnimationViewPropertyManager.commitChanges();
    }

    public static final void setCacheComposition(LottieAnimationView lottieAnimationView, boolean z) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        lottieAnimationView.setCacheComposition(z);
    }

    public static final void setResizeMode(String str, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        ImageView.ScaleType scaleType;
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1364013995) {
                if (hashCode != 94852023) {
                    if (hashCode == 951526612 && str.equals("contain")) {
                        scaleType = ImageView.ScaleType.FIT_CENTER;
                        lottieAnimationViewPropertyManager.setScaleType(scaleType);
                    }
                } else if (str.equals("cover")) {
                    scaleType = ImageView.ScaleType.CENTER_CROP;
                    lottieAnimationViewPropertyManager.setScaleType(scaleType);
                }
            } else if (str.equals("center")) {
                scaleType = ImageView.ScaleType.CENTER_INSIDE;
                lottieAnimationViewPropertyManager.setScaleType(scaleType);
            }
        }
        scaleType = null;
        lottieAnimationViewPropertyManager.setScaleType(scaleType);
    }

    public static final void setRenderMode(String str, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        RenderMode renderMode;
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != 165298699) {
                if (hashCode != 899536360) {
                    if (hashCode == 2101957031 && str.equals("SOFTWARE")) {
                        renderMode = RenderMode.SOFTWARE;
                        lottieAnimationViewPropertyManager.setRenderMode(renderMode);
                    }
                } else if (str.equals("HARDWARE")) {
                    renderMode = RenderMode.HARDWARE;
                    lottieAnimationViewPropertyManager.setRenderMode(renderMode);
                }
            } else if (str.equals("AUTOMATIC")) {
                renderMode = RenderMode.AUTOMATIC;
                lottieAnimationViewPropertyManager.setRenderMode(renderMode);
            }
        }
        renderMode = null;
        lottieAnimationViewPropertyManager.setRenderMode(renderMode);
    }

    public static final void setHardwareAcceleration(boolean z, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        int i = 1;
        if (z) {
            i = 2;
        }
        lottieAnimationViewPropertyManager.setLayerType(i);
    }

    public static final void setProgress(float f, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setProgress(Float.valueOf(f));
    }

    public static final void setSpeed(double d, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setSpeed(Float.valueOf((float) d));
    }

    public static final void setLoop(boolean z, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setLoop(Boolean.valueOf(z));
    }

    public static final void setAutoPlay(boolean z, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setAutoPlay(Boolean.valueOf(z));
    }

    public static final void setEnableMergePaths(boolean z, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setEnableMergePaths(Boolean.valueOf(z));
    }

    public static final void setEnableSafeMode(boolean z, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setEnableSafeMode(Boolean.valueOf(z));
    }

    public static final void setImageAssetsFolder(String str, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setImageAssetsFolder(str);
    }

    public static final void setColorFilters(ReadableArray readableArray, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setColorFilters(readableArray);
    }

    public static final void setTextFilters(ReadableArray readableArray, LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager) {
        Intrinsics.checkNotNullParameter(lottieAnimationViewPropertyManager, "viewManager");
        lottieAnimationViewPropertyManager.setTextFilters(readableArray);
    }
}
