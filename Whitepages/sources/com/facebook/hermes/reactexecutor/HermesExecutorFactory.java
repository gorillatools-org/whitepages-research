package com.facebook.hermes.reactexecutor;

import com.facebook.hermes.instrumentation.HermesSamplingProfiler;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;

public class HermesExecutorFactory implements JavaScriptExecutorFactory {
    private static final String TAG = "Hermes";
    private final RuntimeConfig mConfig;
    private String mDebuggerName;
    private boolean mEnableDebugger;

    public HermesExecutorFactory() {
        this((RuntimeConfig) null);
    }

    public HermesExecutorFactory(RuntimeConfig runtimeConfig) {
        this.mEnableDebugger = true;
        this.mDebuggerName = "";
        this.mConfig = runtimeConfig;
    }

    public void setEnableDebugger(boolean z) {
        this.mEnableDebugger = z;
    }

    public void setDebuggerName(String str) {
        this.mDebuggerName = str;
    }

    public JavaScriptExecutor create() {
        return new HermesExecutor(this.mConfig, this.mEnableDebugger, this.mDebuggerName);
    }

    public void startSamplingProfiler() {
        HermesSamplingProfiler.enable();
    }

    public void stopSamplingProfiler(String str) {
        HermesSamplingProfiler.dumpSampledTraceToFile(str);
        HermesSamplingProfiler.disable();
    }

    public String toString() {
        return "JSIExecutor+HermesRuntime";
    }
}
