package androidx.work;

import androidx.work.impl.model.WorkSpec;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class WorkRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final UUID id;
    private final Set tags;
    private final WorkSpec workSpec;

    public WorkRequest(UUID uuid, WorkSpec workSpec2, Set set) {
        Intrinsics.checkNotNullParameter(uuid, "id");
        Intrinsics.checkNotNullParameter(workSpec2, "workSpec");
        Intrinsics.checkNotNullParameter(set, k.a.g);
        this.id = uuid;
        this.workSpec = workSpec2;
        this.tags = set;
    }

    public UUID getId() {
        return this.id;
    }

    public final WorkSpec getWorkSpec() {
        return this.workSpec;
    }

    public final Set getTags() {
        return this.tags;
    }

    public final String getStringId() {
        String uuid = getId().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "id.toString()");
        return uuid;
    }

    public static abstract class Builder {
        private boolean backoffCriteriaSet;
        private UUID id;
        private final Set tags;
        private WorkSpec workSpec;
        private final Class workerClass;

        public abstract WorkRequest buildInternal$work_runtime_release();

        public abstract Builder getThisObject$work_runtime_release();

        public Builder(Class cls) {
            Intrinsics.checkNotNullParameter(cls, "workerClass");
            this.workerClass = cls;
            UUID randomUUID = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(randomUUID, "randomUUID()");
            this.id = randomUUID;
            String uuid = this.id.toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "id.toString()");
            String name = cls.getName();
            Intrinsics.checkNotNullExpressionValue(name, "workerClass.name");
            this.workSpec = new WorkSpec(uuid, name);
            String name2 = cls.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "workerClass.name");
            this.tags = SetsKt.mutableSetOf(name2);
        }

        public final boolean getBackoffCriteriaSet$work_runtime_release() {
            return this.backoffCriteriaSet;
        }

        public final UUID getId$work_runtime_release() {
            return this.id;
        }

        public final WorkSpec getWorkSpec$work_runtime_release() {
            return this.workSpec;
        }

        public final Set getTags$work_runtime_release() {
            return this.tags;
        }

        public final Builder setId(UUID uuid) {
            Intrinsics.checkNotNullParameter(uuid, "id");
            this.id = uuid;
            String uuid2 = uuid.toString();
            Intrinsics.checkNotNullExpressionValue(uuid2, "id.toString()");
            this.workSpec = new WorkSpec(uuid2, this.workSpec);
            return getThisObject$work_runtime_release();
        }

        public final Builder setInputData(Data data) {
            Intrinsics.checkNotNullParameter(data, "inputData");
            this.workSpec.input = data;
            return getThisObject$work_runtime_release();
        }

        public final Builder addTag(String str) {
            Intrinsics.checkNotNullParameter(str, "tag");
            this.tags.add(str);
            return getThisObject$work_runtime_release();
        }

        public Builder setInitialDelay(long j, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            this.workSpec.initialDelay = timeUnit.toMillis(j);
            if (Long.MAX_VALUE - System.currentTimeMillis() > this.workSpec.initialDelay) {
                return getThisObject$work_runtime_release();
            }
            throw new IllegalArgumentException("The given initial delay is too large and will cause an overflow!");
        }

        public final WorkRequest build() {
            WorkRequest buildInternal$work_runtime_release = buildInternal$work_runtime_release();
            Constraints constraints = this.workSpec.constraints;
            boolean z = constraints.hasContentUriTriggers() || constraints.requiresBatteryNotLow() || constraints.requiresCharging() || constraints.requiresDeviceIdle();
            WorkSpec workSpec2 = this.workSpec;
            if (workSpec2.expedited) {
                if (z) {
                    throw new IllegalArgumentException("Expedited jobs only support network and storage constraints");
                } else if (workSpec2.initialDelay > 0) {
                    throw new IllegalArgumentException("Expedited jobs cannot be delayed");
                }
            }
            UUID randomUUID = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(randomUUID, "randomUUID()");
            setId(randomUUID);
            return buildInternal$work_runtime_release;
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
