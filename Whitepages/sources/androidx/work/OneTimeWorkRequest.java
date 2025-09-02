package androidx.work;

import androidx.work.WorkRequest;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class OneTimeWorkRequest extends WorkRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final OneTimeWorkRequest from(Class cls) {
        return Companion.from(cls);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OneTimeWorkRequest(Builder builder) {
        super(builder.getId$work_runtime_release(), builder.getWorkSpec$work_runtime_release(), builder.getTags$work_runtime_release());
        Intrinsics.checkNotNullParameter(builder, "builder");
    }

    public static final class Builder extends WorkRequest.Builder {
        public Builder getThisObject$work_runtime_release() {
            return this;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Builder(Class cls) {
            super(cls);
            Intrinsics.checkNotNullParameter(cls, "workerClass");
            getWorkSpec$work_runtime_release().inputMergerClassName = OverwritingInputMerger.class.getName();
        }

        public OneTimeWorkRequest buildInternal$work_runtime_release() {
            if (!getBackoffCriteriaSet$work_runtime_release() || !getWorkSpec$work_runtime_release().constraints.requiresDeviceIdle()) {
                return new OneTimeWorkRequest(this);
            }
            throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OneTimeWorkRequest from(Class cls) {
            Intrinsics.checkNotNullParameter(cls, "workerClass");
            return (OneTimeWorkRequest) new Builder(cls).build();
        }
    }
}
