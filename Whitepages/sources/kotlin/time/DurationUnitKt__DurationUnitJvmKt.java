package kotlin.time;

import kotlin.jvm.internal.Intrinsics;

abstract class DurationUnitKt__DurationUnitJvmKt {
    public static final long convertDurationUnitOverflow(long j, DurationUnit durationUnit, DurationUnit durationUnit2) {
        Intrinsics.checkNotNullParameter(durationUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(durationUnit2, "targetUnit");
        return durationUnit2.getTimeUnit$kotlin_stdlib().convert(j, durationUnit.getTimeUnit$kotlin_stdlib());
    }

    public static final long convertDurationUnit(long j, DurationUnit durationUnit, DurationUnit durationUnit2) {
        Intrinsics.checkNotNullParameter(durationUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(durationUnit2, "targetUnit");
        return durationUnit2.getTimeUnit$kotlin_stdlib().convert(j, durationUnit.getTimeUnit$kotlin_stdlib());
    }
}
