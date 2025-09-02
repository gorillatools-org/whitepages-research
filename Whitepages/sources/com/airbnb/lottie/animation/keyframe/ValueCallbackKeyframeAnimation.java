package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

public class ValueCallbackKeyframeAnimation extends BaseKeyframeAnimation {
    private final Object valueCallbackValue;

    /* access modifiers changed from: package-private */
    public float getEndProgress() {
        return 1.0f;
    }

    public ValueCallbackKeyframeAnimation(LottieValueCallback lottieValueCallback) {
        this(lottieValueCallback, (Object) null);
    }

    public ValueCallbackKeyframeAnimation(LottieValueCallback lottieValueCallback, Object obj) {
        super(Collections.emptyList());
        setValueCallback(lottieValueCallback);
        this.valueCallbackValue = obj;
    }

    public void setProgress(float f) {
        this.progress = f;
    }

    public void notifyListeners() {
        if (this.valueCallback != null) {
            super.notifyListeners();
        }
    }

    public Object getValue() {
        LottieValueCallback lottieValueCallback = this.valueCallback;
        Object obj = this.valueCallbackValue;
        return lottieValueCallback.getValueInternal(0.0f, 0.0f, obj, obj, getProgress(), getProgress(), getProgress());
    }

    /* access modifiers changed from: package-private */
    public Object getValue(Keyframe keyframe, float f) {
        return getValue();
    }
}
