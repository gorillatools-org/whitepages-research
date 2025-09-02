package androidx.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public final class FullyDrawnReporter {
    private final Executor executor;
    private final Object lock = new Object();
    private final List onReportCallbacks = new ArrayList();
    private final Function0 reportFullyDrawn;
    private boolean reportPosted;
    private final Runnable reportRunnable = new FullyDrawnReporter$$ExternalSyntheticLambda0(this);
    private boolean reportedFullyDrawn;
    private int reporterCount;

    public FullyDrawnReporter(Executor executor2, Function0 function0) {
        Intrinsics.checkNotNullParameter(executor2, "executor");
        Intrinsics.checkNotNullParameter(function0, "reportFullyDrawn");
        this.executor = executor2;
        this.reportFullyDrawn = function0;
    }

    public final boolean isFullyDrawnReported() {
        boolean z;
        synchronized (this.lock) {
            z = this.reportedFullyDrawn;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public static final void reportRunnable$lambda$2(FullyDrawnReporter fullyDrawnReporter) {
        Intrinsics.checkNotNullParameter(fullyDrawnReporter, "this$0");
        synchronized (fullyDrawnReporter.lock) {
            try {
                fullyDrawnReporter.reportPosted = false;
                if (fullyDrawnReporter.reporterCount == 0 && !fullyDrawnReporter.reportedFullyDrawn) {
                    fullyDrawnReporter.reportFullyDrawn.invoke();
                    fullyDrawnReporter.fullyDrawnReported();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void fullyDrawnReported() {
        synchronized (this.lock) {
            try {
                this.reportedFullyDrawn = true;
                for (Function0 invoke : this.onReportCallbacks) {
                    invoke.invoke();
                }
                this.onReportCallbacks.clear();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
