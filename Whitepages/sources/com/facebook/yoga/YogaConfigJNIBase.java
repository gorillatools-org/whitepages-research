package com.facebook.yoga;

public abstract class YogaConfigJNIBase extends YogaConfig {
    long mNativePointer;

    private YogaConfigJNIBase(long j) {
        if (j != 0) {
            this.mNativePointer = j;
            return;
        }
        throw new IllegalStateException("Failed to allocate native memory");
    }

    YogaConfigJNIBase() {
        this(YogaNative.jni_YGConfigNewJNI());
    }

    public void setPointScaleFactor(float f) {
        YogaNative.jni_YGConfigSetPointScaleFactorJNI(this.mNativePointer, f);
    }

    public void setErrata(YogaErrata yogaErrata) {
        YogaNative.jni_YGConfigSetErrataJNI(this.mNativePointer, yogaErrata.intValue());
    }
}
