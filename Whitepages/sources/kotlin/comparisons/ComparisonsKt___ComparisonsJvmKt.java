package kotlin.comparisons;

import kotlin.jvm.internal.Intrinsics;

abstract class ComparisonsKt___ComparisonsJvmKt extends ComparisonsKt__ComparisonsKt {
    public static int maxOf(int i, int... iArr) {
        Intrinsics.checkNotNullParameter(iArr, "other");
        for (int max : iArr) {
            i = Math.max(i, max);
        }
        return i;
    }

    public static int minOf(int i, int... iArr) {
        Intrinsics.checkNotNullParameter(iArr, "other");
        for (int min : iArr) {
            i = Math.min(i, min);
        }
        return i;
    }
}
