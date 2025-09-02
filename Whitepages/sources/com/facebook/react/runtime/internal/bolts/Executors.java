package com.facebook.react.runtime.internal.bolts;

import com.facebook.react.bridge.UiThreadUtil;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

public final class Executors {
    public static final Executor IMMEDIATE = new ImmediateExecutor();
    public static final Executors INSTANCE = new Executors();
    public static final Executor UI_THREAD = new UIThreadExecutor();

    private Executors() {
    }

    private static final class UIThreadExecutor implements Executor {
        public void execute(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "command");
            UiThreadUtil.runOnUiThread(runnable);
        }
    }

    private static final class ImmediateExecutor implements Executor {
        public void execute(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "command");
            runnable.run();
        }
    }
}
