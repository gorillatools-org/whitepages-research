package com.facebook.yoga;

public class YogaNodeJNIFinalizer extends YogaNodeJNIBase {
    public YogaNodeJNIFinalizer(YogaConfig yogaConfig) {
        super(yogaConfig);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            freeNatives();
        } finally {
            super.finalize();
        }
    }

    public void freeNatives() {
        long j = this.mNativePointer;
        if (j != 0) {
            this.mNativePointer = 0;
            YogaNative.jni_YGNodeFinalizeJNI(j);
        }
    }
}
