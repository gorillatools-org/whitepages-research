package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.devsupport.inspector.InspectorNetworkRequestListener;
import java.util.Map;
import java.util.concurrent.Executor;

@DoNotStripAny
public class ReactInstanceManagerInspectorTarget implements AutoCloseable {
    private final HybridData mHybridData;

    @DoNotStripAny
    public interface TargetDelegate {
        Map<String, String> getMetadata();

        void loadNetworkResource(String str, InspectorNetworkRequestListener inspectorNetworkRequestListener);

        void onReload();

        void onSetPausedInDebuggerMessage(String str);
    }

    private native HybridData initHybrid(Executor executor, TargetDelegate targetDelegate);

    public native void sendDebuggerResumeCommand();

    public ReactInstanceManagerInspectorTarget(TargetDelegate targetDelegate) {
        this.mHybridData = initHybrid(new Executor() {
            public void execute(Runnable runnable) {
                if (UiThreadUtil.isOnUiThread()) {
                    runnable.run();
                } else {
                    UiThreadUtil.runOnUiThread(runnable);
                }
            }
        }, targetDelegate);
    }

    public void close() {
        this.mHybridData.resetNative();
    }

    /* access modifiers changed from: package-private */
    public boolean isValid() {
        return this.mHybridData.isValid();
    }

    static {
        ReactBridge.staticInit();
    }
}
