package androidx.work;

import androidx.work.WorkRequest;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PeriodicWorkRequest extends WorkRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PeriodicWorkRequest(Builder builder) {
        super(builder.getId$work_runtime_release(), builder.getWorkSpec$work_runtime_release(), builder.getTags$work_runtime_release());
        Intrinsics.checkNotNullParameter(builder, "builder");
    }

    public static final class Builder extends WorkRequest.Builder {
        public Builder getThisObject$work_runtime_release() {
            return this;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Builder(Class cls, long j, TimeUnit timeUnit) {
            super(cls);
            Intrinsics.checkNotNullParameter(cls, "workerClass");
            Intrinsics.checkNotNullParameter(timeUnit, "repeatIntervalTimeUnit");
            getWorkSpec$work_runtime_release().setPeriodic(timeUnit.toMillis(j));
        }

        public PeriodicWorkRequest buildInternal$work_runtime_release() {
            if (getBackoffCriteriaSet$work_runtime_release() && getWorkSpec$work_runtime_release().constraints.requiresDeviceIdle()) {
                throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
            } else if (!getWorkSpec$work_runtime_release().expedited) {
                return new PeriodicWorkRequest(this);
            } else {
                throw new IllegalArgumentException("PeriodicWorkRequests cannot be expedited");
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
