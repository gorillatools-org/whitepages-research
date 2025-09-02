package kotlin.time;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public abstract class DurationKt {
    /* access modifiers changed from: private */
    public static final long millisToNanos(long j) {
        return j * ((long) 1000000);
    }

    public static final long toDuration(int i, DurationUnit durationUnit) {
        Intrinsics.checkNotNullParameter(durationUnit, "unit");
        if (durationUnit.compareTo(DurationUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow((long) i, durationUnit, DurationUnit.NANOSECONDS));
        }
        return toDuration((long) i, durationUnit);
    }

    public static final long toDuration(long j, DurationUnit durationUnit) {
        Intrinsics.checkNotNullParameter(durationUnit, "unit");
        DurationUnit durationUnit2 = DurationUnit.NANOSECONDS;
        long convertDurationUnitOverflow = DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(4611686018426999999L, durationUnit2, durationUnit);
        if ((-convertDurationUnitOverflow) > j || j > convertDurationUnitOverflow) {
            return durationOfMillis(RangesKt.coerceIn(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(j, durationUnit, DurationUnit.MILLISECONDS), -4611686018427387903L, 4611686018427387903L));
        }
        return durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(j, durationUnit, durationUnit2));
    }

    private static final long durationOfNanos(long j) {
        return Duration.m875constructorimpl(j << 1);
    }

    /* access modifiers changed from: private */
    public static final long durationOfMillis(long j) {
        return Duration.m875constructorimpl((j << 1) + 1);
    }

    /* access modifiers changed from: private */
    public static final long durationOf(long j, int i) {
        return Duration.m875constructorimpl((j << 1) + ((long) i));
    }
}
