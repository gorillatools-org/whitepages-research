package com.facebook.react.devsupport.inspector;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStripAny;
import java.util.Map;

@DoNotStripAny
public class InspectorNetworkRequestListener {
    private final HybridData mHybridData;

    public native void onCompletion();

    public native void onData(String str);

    public native void onError(String str);

    public native void onHeaders(int i, Map<String, String> map);

    public InspectorNetworkRequestListener(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
