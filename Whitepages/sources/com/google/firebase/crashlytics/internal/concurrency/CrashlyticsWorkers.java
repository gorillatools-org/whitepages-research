package com.google.firebase.crashlytics.internal.concurrency;

import android.os.Looper;
import com.google.firebase.crashlytics.internal.Logger;
import java.util.concurrent.ExecutorService;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class CrashlyticsWorkers {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static boolean enforcement;
    public final CrashlyticsWorker common;
    public final CrashlyticsWorker dataCollect;
    public final CrashlyticsWorker diskWrite;
    public final CrashlyticsWorker network;

    public static final void checkBackgroundThread() {
        Companion.checkBackgroundThread();
    }

    public static final void checkBlockingThread() {
        Companion.checkBlockingThread();
    }

    public static final void checkNotMainThread() {
        Companion.checkNotMainThread();
    }

    public static final boolean getEnforcement() {
        return Companion.getEnforcement();
    }

    public static final void setEnforcement(boolean z) {
        Companion.setEnforcement(z);
    }

    public CrashlyticsWorkers(ExecutorService executorService, ExecutorService executorService2) {
        Intrinsics.checkNotNullParameter(executorService, "backgroundExecutorService");
        Intrinsics.checkNotNullParameter(executorService2, "blockingExecutorService");
        this.common = new CrashlyticsWorker(executorService);
        this.diskWrite = new CrashlyticsWorker(executorService);
        this.dataCollect = new CrashlyticsWorker(executorService);
        this.network = new CrashlyticsWorker(executorService2);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getEnforcement$annotations() {
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final String getThreadName() {
            return Thread.currentThread().getName();
        }

        public final boolean getEnforcement() {
            return CrashlyticsWorkers.enforcement;
        }

        public final void setEnforcement(boolean z) {
            CrashlyticsWorkers.enforcement = z;
        }

        public final void checkNotMainThread() {
            checkThread(new CrashlyticsWorkers$Companion$checkNotMainThread$1(this), CrashlyticsWorkers$Companion$checkNotMainThread$2.INSTANCE);
        }

        public final void checkBlockingThread() {
            checkThread(new CrashlyticsWorkers$Companion$checkBlockingThread$1(this), CrashlyticsWorkers$Companion$checkBlockingThread$2.INSTANCE);
        }

        public final void checkBackgroundThread() {
            checkThread(new CrashlyticsWorkers$Companion$checkBackgroundThread$1(this), CrashlyticsWorkers$Companion$checkBackgroundThread$2.INSTANCE);
        }

        /* access modifiers changed from: private */
        public final boolean isNotMainThread() {
            return !Looper.getMainLooper().isCurrentThread();
        }

        /* access modifiers changed from: private */
        public final boolean isBlockingThread() {
            String threadName = getThreadName();
            Intrinsics.checkNotNullExpressionValue(threadName, "threadName");
            return StringsKt.contains$default((CharSequence) threadName, (CharSequence) "Firebase Blocking Thread #", false, 2, (Object) null);
        }

        /* access modifiers changed from: private */
        public final boolean isBackgroundThread() {
            String threadName = getThreadName();
            Intrinsics.checkNotNullExpressionValue(threadName, "threadName");
            return StringsKt.contains$default((CharSequence) threadName, (CharSequence) "Firebase Background Thread #", false, 2, (Object) null);
        }

        private final void checkThread(Function0 function0, Function0 function02) {
            if (!((Boolean) function0.invoke()).booleanValue()) {
                Logger.getLogger().d((String) function02.invoke());
                getEnforcement();
            }
        }
    }
}
