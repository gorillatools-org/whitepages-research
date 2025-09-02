package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.soloader.SoLoader;
import java.io.Closeable;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStripAny
public final class ReactHostInspectorTarget implements Closeable {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HybridData mHybridData;
    private final ReactHostImpl reactHostImpl;

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    private final native HybridData initHybrid(ReactHostImpl reactHostImpl2, Executor executor);

    public final native void sendDebuggerResumeCommand();

    public ReactHostInspectorTarget(ReactHostImpl reactHostImpl2) {
        Intrinsics.checkNotNullParameter(reactHostImpl2, "reactHostImpl");
        this.reactHostImpl = reactHostImpl2;
        this.mHybridData = initHybrid(reactHostImpl2, new UIThreadConditionalSyncExecutor());
    }

    public void close() {
        this.mHybridData.resetNative();
    }

    public final boolean isValid() {
        return this.mHybridData.isValid();
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("rninstance");
    }

    private static final class UIThreadConditionalSyncExecutor implements Executor {
        public void execute(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "command");
            if (UiThreadUtil.isOnUiThread()) {
                runnable.run();
            } else {
                UiThreadUtil.runOnUiThread(runnable);
            }
        }
    }
}
