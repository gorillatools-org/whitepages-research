package com.facebook.react.jscexecutor;

import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import kotlin.jvm.internal.Intrinsics;

public final class JSCExecutorFactory implements JavaScriptExecutorFactory {
    private final String appName;
    private final String deviceName;

    public JSCExecutorFactory(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "appName");
        Intrinsics.checkNotNullParameter(str2, "deviceName");
        this.appName = str;
        this.deviceName = str2;
    }

    public JavaScriptExecutor create() throws Exception {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("OwnerIdentity", ReactConstants.TAG);
        writableNativeMap.putString("AppIdentity", this.appName);
        writableNativeMap.putString("DeviceIdentity", this.deviceName);
        return new JSCExecutor(writableNativeMap);
    }

    public void startSamplingProfiler() {
        throw new UnsupportedOperationException("Starting sampling profiler not supported on " + this);
    }

    public void stopSamplingProfiler(String str) {
        Intrinsics.checkNotNullParameter(str, "filename");
        throw new UnsupportedOperationException("Stopping sampling profiler not supported on " + this);
    }

    public String toString() {
        return "JSIExecutor+JSCRuntime";
    }
}
