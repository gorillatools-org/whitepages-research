package com.facebook.yoga;

public class YogaConfigJNIFinalizer extends YogaConfigJNIBase {
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
            YogaNative.jni_YGConfigFreeJNI(j);
        }
    }
}
