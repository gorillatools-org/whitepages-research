package com.facebook.jni;

import com.facebook.jni.DestructorThread;
import com.facebook.jni.annotations.DoNotStripAny;
import com.facebook.soloader.nativeloader.NativeLoader;

@DoNotStripAny
public class HybridData {
    private final Destructor mDestructor = new Destructor(this);

    static {
        NativeLoader.loadLibrary("fbjni");
    }

    public synchronized void resetNative() {
        this.mDestructor.destruct();
    }

    public boolean isValid() {
        return this.mDestructor.mNativePointer != 0;
    }

    @DoNotStripAny
    public static class Destructor extends DestructorThread.Destructor {
        /* access modifiers changed from: private */
        public volatile long mNativePointer;

        static native void deleteNative(long j);

        Destructor(Object obj) {
            super(obj);
        }

        /* access modifiers changed from: protected */
        public final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }
    }
}
