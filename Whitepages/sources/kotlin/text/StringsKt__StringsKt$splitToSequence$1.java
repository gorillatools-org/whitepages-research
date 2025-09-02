package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;

final class StringsKt__StringsKt$splitToSequence$1 extends Lambda implements Function1 {
    final /* synthetic */ CharSequence $this_splitToSequence;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StringsKt__StringsKt$splitToSequence$1(CharSequence charSequence) {
        super(1);
        this.$this_splitToSequence = charSequence;
    }

    public final String invoke(IntRange intRange) {
        Intrinsics.checkNotNullParameter(intRange, "it");
        return StringsKt__StringsKt.substring(this.$this_splitToSequence, intRange);
    }
}
