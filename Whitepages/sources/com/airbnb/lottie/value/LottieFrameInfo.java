package com.airbnb.lottie.value;

public class LottieFrameInfo {
    private float endFrame;
    private Object endValue;
    private float interpolatedKeyframeProgress;
    private float linearKeyframeProgress;
    private float overallProgress;
    private float startFrame;
    private Object startValue;

    public LottieFrameInfo set(float f, float f2, Object obj, Object obj2, float f3, float f4, float f5) {
        this.startFrame = f;
        this.endFrame = f2;
        this.startValue = obj;
        this.endValue = obj2;
        this.linearKeyframeProgress = f3;
        this.interpolatedKeyframeProgress = f4;
        this.overallProgress = f5;
        return this;
    }

    public float getStartFrame() {
        return this.startFrame;
    }

    public float getEndFrame() {
        return this.endFrame;
    }

    public Object getStartValue() {
        return this.startValue;
    }

    public Object getEndValue() {
        return this.endValue;
    }

    public float getLinearKeyframeProgress() {
        return this.linearKeyframeProgress;
    }

    public float getInterpolatedKeyframeProgress() {
        return this.interpolatedKeyframeProgress;
    }

    public float getOverallProgress() {
        return this.overallProgress;
    }
}
