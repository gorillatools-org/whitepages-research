package androidx.work.impl.model;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.arch.core.util.Function;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.salesforce.marketingcloud.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okhttp3.internal.http2.Http2;

public final class WorkSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG;
    public static final Function WORK_INFO_MAPPER = new WorkSpec$$ExternalSyntheticLambda0();
    public long backoffDelayDuration;
    public BackoffPolicy backoffPolicy;
    public Constraints constraints;
    public boolean expedited;
    public long flexDuration;
    private final int generation;
    public final String id;
    public long initialDelay;
    public Data input;
    public String inputMergerClassName;
    public long intervalDuration;
    public long lastEnqueueTime;
    public long minimumRetentionDuration;
    public OutOfQuotaPolicy outOfQuotaPolicy;
    public Data output;
    private int periodCount;
    public int runAttemptCount;
    public long scheduleRequestedAt;
    public WorkInfo$State state;
    public String workerClassName;

    public static /* synthetic */ WorkSpec copy$default(WorkSpec workSpec, String str, WorkInfo$State workInfo$State, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints2, int i, BackoffPolicy backoffPolicy2, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy2, int i2, int i3, int i4, Object obj) {
        WorkSpec workSpec2 = workSpec;
        int i5 = i4;
        return workSpec.copy((i5 & 1) != 0 ? workSpec2.id : str, (i5 & 2) != 0 ? workSpec2.state : workInfo$State, (i5 & 4) != 0 ? workSpec2.workerClassName : str2, (i5 & 8) != 0 ? workSpec2.inputMergerClassName : str3, (i5 & 16) != 0 ? workSpec2.input : data, (i5 & 32) != 0 ? workSpec2.output : data2, (i5 & 64) != 0 ? workSpec2.initialDelay : j, (i5 & 128) != 0 ? workSpec2.intervalDuration : j2, (i5 & 256) != 0 ? workSpec2.flexDuration : j3, (i5 & 512) != 0 ? workSpec2.constraints : constraints2, (i5 & 1024) != 0 ? workSpec2.runAttemptCount : i, (i5 & b.u) != 0 ? workSpec2.backoffPolicy : backoffPolicy2, (i5 & b.v) != 0 ? workSpec2.backoffDelayDuration : j4, (i5 & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0 ? workSpec2.lastEnqueueTime : j5, (i5 & Http2.INITIAL_MAX_FRAME_SIZE) != 0 ? workSpec2.minimumRetentionDuration : j6, (i5 & 32768) != 0 ? workSpec2.scheduleRequestedAt : j7, (i5 & 65536) != 0 ? workSpec2.expedited : z, (131072 & i5) != 0 ? workSpec2.outOfQuotaPolicy : outOfQuotaPolicy2, (i5 & 262144) != 0 ? workSpec2.periodCount : i2, (i5 & 524288) != 0 ? workSpec2.generation : i3);
    }

    public final WorkSpec copy(String str, WorkInfo$State workInfo$State, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints2, int i, BackoffPolicy backoffPolicy2, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy2, int i2, int i3) {
        String str4 = str;
        Intrinsics.checkNotNullParameter(str4, "id");
        Intrinsics.checkNotNullParameter(workInfo$State, RemoteConfigConstants.ResponseFieldKey.STATE);
        Intrinsics.checkNotNullParameter(str2, "workerClassName");
        Intrinsics.checkNotNullParameter(data, "input");
        Intrinsics.checkNotNullParameter(data2, "output");
        Intrinsics.checkNotNullParameter(constraints2, "constraints");
        Intrinsics.checkNotNullParameter(backoffPolicy2, "backoffPolicy");
        Intrinsics.checkNotNullParameter(outOfQuotaPolicy2, "outOfQuotaPolicy");
        return new WorkSpec(str4, workInfo$State, str2, str3, data, data2, j, j2, j3, constraints2, i, backoffPolicy2, j4, j5, j6, j7, z, outOfQuotaPolicy2, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkSpec)) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) workSpec.id) && this.state == workSpec.state && Intrinsics.areEqual((Object) this.workerClassName, (Object) workSpec.workerClassName) && Intrinsics.areEqual((Object) this.inputMergerClassName, (Object) workSpec.inputMergerClassName) && Intrinsics.areEqual((Object) this.input, (Object) workSpec.input) && Intrinsics.areEqual((Object) this.output, (Object) workSpec.output) && this.initialDelay == workSpec.initialDelay && this.intervalDuration == workSpec.intervalDuration && this.flexDuration == workSpec.flexDuration && Intrinsics.areEqual((Object) this.constraints, (Object) workSpec.constraints) && this.runAttemptCount == workSpec.runAttemptCount && this.backoffPolicy == workSpec.backoffPolicy && this.backoffDelayDuration == workSpec.backoffDelayDuration && this.lastEnqueueTime == workSpec.lastEnqueueTime && this.minimumRetentionDuration == workSpec.minimumRetentionDuration && this.scheduleRequestedAt == workSpec.scheduleRequestedAt && this.expedited == workSpec.expedited && this.outOfQuotaPolicy == workSpec.outOfQuotaPolicy && this.periodCount == workSpec.periodCount && this.generation == workSpec.generation;
    }

    public int hashCode() {
        int hashCode = ((((this.id.hashCode() * 31) + this.state.hashCode()) * 31) + this.workerClassName.hashCode()) * 31;
        String str = this.inputMergerClassName;
        int hashCode2 = (((((((((((((((((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.input.hashCode()) * 31) + this.output.hashCode()) * 31) + Long.hashCode(this.initialDelay)) * 31) + Long.hashCode(this.intervalDuration)) * 31) + Long.hashCode(this.flexDuration)) * 31) + this.constraints.hashCode()) * 31) + Integer.hashCode(this.runAttemptCount)) * 31) + this.backoffPolicy.hashCode()) * 31) + Long.hashCode(this.backoffDelayDuration)) * 31) + Long.hashCode(this.lastEnqueueTime)) * 31) + Long.hashCode(this.minimumRetentionDuration)) * 31) + Long.hashCode(this.scheduleRequestedAt)) * 31;
        boolean z = this.expedited;
        if (z) {
            z = true;
        }
        return ((((((hashCode2 + (z ? 1 : 0)) * 31) + this.outOfQuotaPolicy.hashCode()) * 31) + Integer.hashCode(this.periodCount)) * 31) + Integer.hashCode(this.generation);
    }

    public WorkSpec(String str, WorkInfo$State workInfo$State, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints2, int i, BackoffPolicy backoffPolicy2, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy2, int i2, int i3) {
        Data data3 = data2;
        Constraints constraints3 = constraints2;
        BackoffPolicy backoffPolicy3 = backoffPolicy2;
        OutOfQuotaPolicy outOfQuotaPolicy3 = outOfQuotaPolicy2;
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(workInfo$State, RemoteConfigConstants.ResponseFieldKey.STATE);
        Intrinsics.checkNotNullParameter(str2, "workerClassName");
        Intrinsics.checkNotNullParameter(data, "input");
        Intrinsics.checkNotNullParameter(data3, "output");
        Intrinsics.checkNotNullParameter(constraints3, "constraints");
        Intrinsics.checkNotNullParameter(backoffPolicy3, "backoffPolicy");
        Intrinsics.checkNotNullParameter(outOfQuotaPolicy3, "outOfQuotaPolicy");
        this.id = str;
        this.state = workInfo$State;
        this.workerClassName = str2;
        this.inputMergerClassName = str3;
        this.input = data;
        this.output = data3;
        this.initialDelay = j;
        this.intervalDuration = j2;
        this.flexDuration = j3;
        this.constraints = constraints3;
        this.runAttemptCount = i;
        this.backoffPolicy = backoffPolicy3;
        this.backoffDelayDuration = j4;
        this.lastEnqueueTime = j5;
        this.minimumRetentionDuration = j6;
        this.scheduleRequestedAt = j7;
        this.expedited = z;
        this.outOfQuotaPolicy = outOfQuotaPolicy3;
        this.periodCount = i2;
        this.generation = i3;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ WorkSpec(java.lang.String r31, androidx.work.WorkInfo$State r32, java.lang.String r33, java.lang.String r34, androidx.work.Data r35, androidx.work.Data r36, long r37, long r39, long r41, androidx.work.Constraints r43, int r44, androidx.work.BackoffPolicy r45, long r46, long r48, long r50, long r52, boolean r54, androidx.work.OutOfQuotaPolicy r55, int r56, int r57, int r58, kotlin.jvm.internal.DefaultConstructorMarker r59) {
        /*
            r30 = this;
            r0 = r58
            r1 = r0 & 2
            if (r1 == 0) goto L_0x000a
            androidx.work.WorkInfo$State r1 = androidx.work.WorkInfo$State.ENQUEUED
            r4 = r1
            goto L_0x000c
        L_0x000a:
            r4 = r32
        L_0x000c:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0013
            r1 = 0
            r6 = r1
            goto L_0x0015
        L_0x0013:
            r6 = r34
        L_0x0015:
            r1 = r0 & 16
            java.lang.String r2 = "EMPTY"
            if (r1 == 0) goto L_0x0022
            androidx.work.Data r1 = androidx.work.Data.EMPTY
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r7 = r1
            goto L_0x0024
        L_0x0022:
            r7 = r35
        L_0x0024:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x002f
            androidx.work.Data r1 = androidx.work.Data.EMPTY
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r8 = r1
            goto L_0x0031
        L_0x002f:
            r8 = r36
        L_0x0031:
            r1 = r0 & 64
            r2 = 0
            if (r1 == 0) goto L_0x0039
            r9 = r2
            goto L_0x003b
        L_0x0039:
            r9 = r37
        L_0x003b:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0041
            r11 = r2
            goto L_0x0043
        L_0x0041:
            r11 = r39
        L_0x0043:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0049
            r13 = r2
            goto L_0x004b
        L_0x0049:
            r13 = r41
        L_0x004b:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0053
            androidx.work.Constraints r1 = androidx.work.Constraints.NONE
            r15 = r1
            goto L_0x0055
        L_0x0053:
            r15 = r43
        L_0x0055:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            r5 = 0
            if (r1 == 0) goto L_0x005d
            r16 = r5
            goto L_0x005f
        L_0x005d:
            r16 = r44
        L_0x005f:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0068
            androidx.work.BackoffPolicy r1 = androidx.work.BackoffPolicy.EXPONENTIAL
            r17 = r1
            goto L_0x006a
        L_0x0068:
            r17 = r45
        L_0x006a:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x0071
            r18 = 30000(0x7530, double:1.4822E-319)
            goto L_0x0073
        L_0x0071:
            r18 = r46
        L_0x0073:
            r1 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r1 == 0) goto L_0x007a
            r20 = r2
            goto L_0x007c
        L_0x007a:
            r20 = r48
        L_0x007c:
            r1 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r1 == 0) goto L_0x0083
            r22 = r2
            goto L_0x0085
        L_0x0083:
            r22 = r50
        L_0x0085:
            r1 = 32768(0x8000, float:4.5918E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0090
            r1 = -1
            r24 = r1
            goto L_0x0092
        L_0x0090:
            r24 = r52
        L_0x0092:
            r1 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x009a
            r26 = r5
            goto L_0x009c
        L_0x009a:
            r26 = r54
        L_0x009c:
            r1 = 131072(0x20000, float:1.83671E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00a6
            androidx.work.OutOfQuotaPolicy r1 = androidx.work.OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
            r27 = r1
            goto L_0x00a8
        L_0x00a6:
            r27 = r55
        L_0x00a8:
            r1 = 262144(0x40000, float:3.67342E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00b0
            r28 = r5
            goto L_0x00b2
        L_0x00b0:
            r28 = r56
        L_0x00b2:
            r1 = 524288(0x80000, float:7.34684E-40)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x00ba
            r29 = r5
            goto L_0x00bc
        L_0x00ba:
            r29 = r57
        L_0x00bc:
            r2 = r30
            r3 = r31
            r5 = r33
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r11, r13, r15, r16, r17, r18, r20, r22, r24, r26, r27, r28, r29)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpec.<init>(java.lang.String, androidx.work.WorkInfo$State, java.lang.String, java.lang.String, androidx.work.Data, androidx.work.Data, long, long, long, androidx.work.Constraints, int, androidx.work.BackoffPolicy, long, long, long, long, boolean, androidx.work.OutOfQuotaPolicy, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final int getPeriodCount() {
        return this.periodCount;
    }

    public final int getGeneration() {
        return this.generation;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WorkSpec(String str, String str2) {
        this(str, (WorkInfo$State) null, str2, (String) null, (Data) null, (Data) null, 0, 0, 0, (Constraints) null, 0, (BackoffPolicy) null, 0, 0, 0, 0, false, (OutOfQuotaPolicy) null, 0, 0, 1048570, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "workerClassName_");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WorkSpec(java.lang.String r34, androidx.work.impl.model.WorkSpec r35) {
        /*
            r33 = this;
            r0 = r35
            r1 = r33
            r2 = r34
            java.lang.String r3 = "newId"
            r4 = r34
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
            java.lang.String r3 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r4 = r0.workerClassName
            androidx.work.WorkInfo$State r3 = r0.state
            java.lang.String r5 = r0.inputMergerClassName
            androidx.work.Data r7 = new androidx.work.Data
            r6 = r7
            androidx.work.Data r8 = r0.input
            r7.<init>((androidx.work.Data) r8)
            androidx.work.Data r8 = new androidx.work.Data
            r7 = r8
            androidx.work.Data r9 = r0.output
            r8.<init>((androidx.work.Data) r9)
            long r8 = r0.initialDelay
            long r10 = r0.intervalDuration
            long r12 = r0.flexDuration
            androidx.work.Constraints r15 = new androidx.work.Constraints
            r14 = r15
            r31 = r1
            androidx.work.Constraints r1 = r0.constraints
            r15.<init>(r1)
            int r15 = r0.runAttemptCount
            androidx.work.BackoffPolicy r1 = r0.backoffPolicy
            r16 = r1
            r32 = r2
            long r1 = r0.backoffDelayDuration
            r17 = r1
            long r1 = r0.lastEnqueueTime
            r19 = r1
            long r1 = r0.minimumRetentionDuration
            r21 = r1
            long r1 = r0.scheduleRequestedAt
            r23 = r1
            boolean r1 = r0.expedited
            r25 = r1
            androidx.work.OutOfQuotaPolicy r1 = r0.outOfQuotaPolicy
            r26 = r1
            int r0 = r0.periodCount
            r27 = r0
            r29 = 524288(0x80000, float:7.34684E-40)
            r30 = 0
            r28 = 0
            r1 = r31
            r2 = r32
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r10, r12, r14, r15, r16, r17, r19, r21, r23, r25, r26, r27, r28, r29, r30)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpec.<init>(java.lang.String, androidx.work.impl.model.WorkSpec):void");
    }

    public final boolean isPeriodic() {
        return this.intervalDuration != 0;
    }

    public final boolean isBackedOff() {
        return this.state == WorkInfo$State.ENQUEUED && this.runAttemptCount > 0;
    }

    public final void setPeriodic(long j) {
        if (j < 900000) {
            Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        setPeriodic(RangesKt.coerceAtLeast(j, 900000), RangesKt.coerceAtLeast(j, 900000));
    }

    public final void setPeriodic(long j, long j2) {
        if (j < 900000) {
            Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        this.intervalDuration = RangesKt.coerceAtLeast(j, 900000);
        if (j2 < 300000) {
            Logger.get().warning(TAG, "Flex duration lesser than minimum allowed value; Changed to 300000");
        }
        if (j2 > this.intervalDuration) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.warning(str, "Flex duration greater than interval duration; Changed to " + j);
        }
        this.flexDuration = RangesKt.coerceIn(j2, 300000, this.intervalDuration);
    }

    public final long calculateNextRunTime() {
        if (isBackedOff()) {
            return this.lastEnqueueTime + RangesKt.coerceAtMost(this.backoffPolicy == BackoffPolicy.LINEAR ? this.backoffDelayDuration * ((long) this.runAttemptCount) : (long) Math.scalb((float) this.backoffDelayDuration, this.runAttemptCount - 1), 18000000);
        }
        long j = 0;
        if (isPeriodic()) {
            int i = this.periodCount;
            long j2 = this.lastEnqueueTime;
            if (i == 0) {
                j2 += this.initialDelay;
            }
            long j3 = this.flexDuration;
            long j4 = this.intervalDuration;
            if (j3 != j4) {
                if (i == 0) {
                    j = ((long) -1) * j3;
                }
                j2 += j4;
            } else if (i != 0) {
                j = j4;
            }
            return j2 + j;
        }
        long j5 = this.lastEnqueueTime;
        if (j5 == 0) {
            j5 = System.currentTimeMillis();
        }
        return j5 + this.initialDelay;
    }

    public final boolean hasConstraints() {
        return !Intrinsics.areEqual((Object) Constraints.NONE, (Object) this.constraints);
    }

    public String toString() {
        return "{WorkSpec: " + this.id + '}';
    }

    public static final class IdAndState {
        public String id;
        public WorkInfo$State state;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IdAndState)) {
                return false;
            }
            IdAndState idAndState = (IdAndState) obj;
            return Intrinsics.areEqual((Object) this.id, (Object) idAndState.id) && this.state == idAndState.state;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.state.hashCode();
        }

        public String toString() {
            return "IdAndState(id=" + this.id + ", state=" + this.state + ')';
        }

        public IdAndState(String str, WorkInfo$State workInfo$State) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(workInfo$State, RemoteConfigConstants.ResponseFieldKey.STATE);
            this.id = str;
            this.state = workInfo$State;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagWithPrefix = Logger.tagWithPrefix("WorkSpec");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"WorkSpec\")");
        TAG = tagWithPrefix;
    }

    /* access modifiers changed from: private */
    public static final List WORK_INFO_MAPPER$lambda$1(List list) {
        if (list == null) {
            return null;
        }
        Iterable iterable = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return arrayList;
        }
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        throw null;
    }
}
