package com.facebook.react.bridge;

import com.facebook.jni.HybridClassBase;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class CxxCallbackImpl extends HybridClassBase implements Callback {
    private native void nativeInvoke(NativeArray nativeArray);

    @DoNotStrip
    private CxxCallbackImpl() {
    }

    public void invoke(Object... objArr) {
        nativeInvoke(Arguments.fromJavaArgs(objArr));
    }
}
