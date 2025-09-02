package com.airbnb.lottie.animation.keyframe;

import android.view.animation.Interpolator;
import com.airbnb.lottie.L;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseKeyframeAnimation {
    private float cachedEndProgress = -1.0f;
    private Object cachedGetValue = null;
    private float cachedStartDelayProgress = -1.0f;
    private boolean isDiscrete = false;
    private final KeyframesWrapper keyframesWrapper;
    final List listeners = new ArrayList(1);
    protected float progress = 0.0f;
    protected LottieValueCallback valueCallback;

    public interface AnimationListener {
        void onValueChanged();
    }

    private interface KeyframesWrapper {
        Keyframe getCurrentKeyframe();

        float getEndProgress();

        float getStartDelayProgress();

        boolean isCachedValueEnabled(float f);

        boolean isEmpty();

        boolean isValueChanged(float f);
    }

    /* access modifiers changed from: package-private */
    public abstract Object getValue(Keyframe keyframe, float f);

    BaseKeyframeAnimation(List list) {
        this.keyframesWrapper = wrap(list);
    }

    public void setIsDiscrete() {
        this.isDiscrete = true;
    }

    public void addUpdateListener(AnimationListener animationListener) {
        this.listeners.add(animationListener);
    }

    public void setProgress(float f) {
        if (L.isTraceEnabled()) {
            L.beginSection("BaseKeyframeAnimation#setProgress");
        }
        if (!this.keyframesWrapper.isEmpty()) {
            if (f < getStartDelayProgress()) {
                f = getStartDelayProgress();
            } else if (f > getEndProgress()) {
                f = getEndProgress();
            }
            if (f != this.progress) {
                this.progress = f;
                if (this.keyframesWrapper.isValueChanged(f)) {
                    notifyListeners();
                }
                if (L.isTraceEnabled()) {
                    L.endSection("BaseKeyframeAnimation#setProgress");
                }
            } else if (L.isTraceEnabled()) {
                L.endSection("BaseKeyframeAnimation#setProgress");
            }
        } else if (L.isTraceEnabled()) {
            L.endSection("BaseKeyframeAnimation#setProgress");
        }
    }

    public void notifyListeners() {
        if (L.isTraceEnabled()) {
            L.beginSection("BaseKeyframeAnimation#notifyListeners");
        }
        for (int i = 0; i < this.listeners.size(); i++) {
            ((AnimationListener) this.listeners.get(i)).onValueChanged();
        }
        if (L.isTraceEnabled()) {
            L.endSection("BaseKeyframeAnimation#notifyListeners");
        }
    }

    /* access modifiers changed from: protected */
    public Keyframe getCurrentKeyframe() {
        if (L.isTraceEnabled()) {
            L.beginSection("BaseKeyframeAnimation#getCurrentKeyframe");
        }
        Keyframe currentKeyframe = this.keyframesWrapper.getCurrentKeyframe();
        if (L.isTraceEnabled()) {
            L.endSection("BaseKeyframeAnimation#getCurrentKeyframe");
        }
        return currentKeyframe;
    }

    /* access modifiers changed from: package-private */
    public float getLinearCurrentKeyframeProgress() {
        if (this.isDiscrete) {
            return 0.0f;
        }
        Keyframe currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe.isStatic()) {
            return 0.0f;
        }
        return (this.progress - currentKeyframe.getStartProgress()) / (currentKeyframe.getEndProgress() - currentKeyframe.getStartProgress());
    }

    /* access modifiers changed from: protected */
    public float getInterpolatedCurrentKeyframeProgress() {
        Interpolator interpolator;
        Keyframe currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe == null || currentKeyframe.isStatic() || (interpolator = currentKeyframe.interpolator) == null) {
            return 0.0f;
        }
        return interpolator.getInterpolation(getLinearCurrentKeyframeProgress());
    }

    private float getStartDelayProgress() {
        if (this.cachedStartDelayProgress == -1.0f) {
            this.cachedStartDelayProgress = this.keyframesWrapper.getStartDelayProgress();
        }
        return this.cachedStartDelayProgress;
    }

    /* access modifiers changed from: package-private */
    public float getEndProgress() {
        if (this.cachedEndProgress == -1.0f) {
            this.cachedEndProgress = this.keyframesWrapper.getEndProgress();
        }
        return this.cachedEndProgress;
    }

    public Object getValue() {
        Object obj;
        float linearCurrentKeyframeProgress = getLinearCurrentKeyframeProgress();
        if (this.valueCallback == null && this.keyframesWrapper.isCachedValueEnabled(linearCurrentKeyframeProgress)) {
            return this.cachedGetValue;
        }
        Keyframe currentKeyframe = getCurrentKeyframe();
        Interpolator interpolator = currentKeyframe.xInterpolator;
        if (interpolator == null || currentKeyframe.yInterpolator == null) {
            obj = getValue(currentKeyframe, getInterpolatedCurrentKeyframeProgress());
        } else {
            obj = getValue(currentKeyframe, linearCurrentKeyframeProgress, interpolator.getInterpolation(linearCurrentKeyframeProgress), currentKeyframe.yInterpolator.getInterpolation(linearCurrentKeyframeProgress));
        }
        this.cachedGetValue = obj;
        return obj;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setValueCallback(LottieValueCallback lottieValueCallback) {
        LottieValueCallback lottieValueCallback2 = this.valueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation((BaseKeyframeAnimation) null);
        }
        this.valueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public boolean hasValueCallback() {
        return this.valueCallback != null;
    }

    /* access modifiers changed from: protected */
    public Object getValue(Keyframe keyframe, float f, float f2, float f3) {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }

    private static KeyframesWrapper wrap(List list) {
        if (list.isEmpty()) {
            return new EmptyKeyframeWrapper();
        }
        if (list.size() == 1) {
            return new SingleKeyframeWrapper(list);
        }
        return new KeyframesWrapperImpl(list);
    }

    private static final class EmptyKeyframeWrapper implements KeyframesWrapper {
        public float getEndProgress() {
            return 1.0f;
        }

        public float getStartDelayProgress() {
            return 0.0f;
        }

        public boolean isEmpty() {
            return true;
        }

        public boolean isValueChanged(float f) {
            return false;
        }

        private EmptyKeyframeWrapper() {
        }

        public Keyframe getCurrentKeyframe() {
            throw new IllegalStateException("not implemented");
        }

        public boolean isCachedValueEnabled(float f) {
            throw new IllegalStateException("not implemented");
        }
    }

    private static final class SingleKeyframeWrapper implements KeyframesWrapper {
        private float cachedInterpolatedProgress = -1.0f;
        private final Keyframe keyframe;

        public boolean isEmpty() {
            return false;
        }

        SingleKeyframeWrapper(List list) {
            this.keyframe = (Keyframe) list.get(0);
        }

        public boolean isValueChanged(float f) {
            return !this.keyframe.isStatic();
        }

        public Keyframe getCurrentKeyframe() {
            return this.keyframe;
        }

        public float getStartDelayProgress() {
            return this.keyframe.getStartProgress();
        }

        public float getEndProgress() {
            return this.keyframe.getEndProgress();
        }

        public boolean isCachedValueEnabled(float f) {
            if (this.cachedInterpolatedProgress == f) {
                return true;
            }
            this.cachedInterpolatedProgress = f;
            return false;
        }
    }

    private static final class KeyframesWrapperImpl implements KeyframesWrapper {
        private Keyframe cachedCurrentKeyframe = null;
        private float cachedInterpolatedProgress = -1.0f;
        private Keyframe currentKeyframe;
        private final List keyframes;

        public boolean isEmpty() {
            return false;
        }

        KeyframesWrapperImpl(List list) {
            this.keyframes = list;
            this.currentKeyframe = findKeyframe(0.0f);
        }

        public boolean isValueChanged(float f) {
            if (this.currentKeyframe.containsProgress(f)) {
                return !this.currentKeyframe.isStatic();
            }
            this.currentKeyframe = findKeyframe(f);
            return true;
        }

        private Keyframe findKeyframe(float f) {
            List list = this.keyframes;
            Keyframe keyframe = (Keyframe) list.get(list.size() - 1);
            if (f >= keyframe.getStartProgress()) {
                return keyframe;
            }
            for (int size = this.keyframes.size() - 2; size >= 1; size--) {
                Keyframe keyframe2 = (Keyframe) this.keyframes.get(size);
                if (this.currentKeyframe != keyframe2 && keyframe2.containsProgress(f)) {
                    return keyframe2;
                }
            }
            return (Keyframe) this.keyframes.get(0);
        }

        public Keyframe getCurrentKeyframe() {
            return this.currentKeyframe;
        }

        public float getStartDelayProgress() {
            return ((Keyframe) this.keyframes.get(0)).getStartProgress();
        }

        public float getEndProgress() {
            List list = this.keyframes;
            return ((Keyframe) list.get(list.size() - 1)).getEndProgress();
        }

        public boolean isCachedValueEnabled(float f) {
            Keyframe keyframe = this.cachedCurrentKeyframe;
            Keyframe keyframe2 = this.currentKeyframe;
            if (keyframe == keyframe2 && this.cachedInterpolatedProgress == f) {
                return true;
            }
            this.cachedCurrentKeyframe = keyframe2;
            this.cachedInterpolatedProgress = f;
            return false;
        }
    }
}
