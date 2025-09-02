package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class ReactSoftExceptionLogger {
    public static final ReactSoftExceptionLogger INSTANCE = new ReactSoftExceptionLogger();
    private static final List<ReactSoftExceptionListener> listeners = new CopyOnWriteArrayList();

    public interface ReactSoftExceptionListener {
        void logSoftException(String str, Throwable th);
    }

    private ReactSoftExceptionLogger() {
    }

    public static final class Categories {
        public static final Categories INSTANCE = new Categories();
        public static final String RVG_IS_VIEW_CLIPPED = "ReactViewGroup.isViewClipped";
        public static final String RVG_ON_VIEW_REMOVED = "ReactViewGroup.onViewRemoved";
        public static final String SOFT_ASSERTIONS = "SoftAssertions";

        private Categories() {
        }
    }

    public static final void addListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        Intrinsics.checkNotNullParameter(reactSoftExceptionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        List<ReactSoftExceptionListener> list = listeners;
        if (!list.contains(reactSoftExceptionListener)) {
            list.add(reactSoftExceptionListener);
        }
    }

    public static final void removeListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        Intrinsics.checkNotNullParameter(reactSoftExceptionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        listeners.remove(reactSoftExceptionListener);
    }

    public static final void clearListeners() {
        listeners.clear();
    }

    public static final void logSoftExceptionVerbose(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "category");
        Intrinsics.checkNotNullParameter(th, "cause");
        String simpleName = th.getClass().getSimpleName();
        String message = th.getMessage();
        logSoftException(str + "|" + simpleName + ":" + message, th);
    }

    public static final void logSoftException(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "category");
        Intrinsics.checkNotNullParameter(th, "cause");
        List<ReactSoftExceptionListener> list = listeners;
        if (!list.isEmpty()) {
            for (ReactSoftExceptionListener logSoftException : list) {
                logSoftException.logSoftException(str, th);
            }
            return;
        }
        FLog.e(str, "Unhandled SoftException", th);
    }

    @DoNotStrip
    private static final void logNoThrowSoftExceptionWithMessage(String str, String str2) {
        logSoftException(str, new ReactNoCrashSoftException(str2));
    }
}
