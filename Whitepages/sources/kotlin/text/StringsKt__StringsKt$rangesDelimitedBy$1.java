package kotlin.text;

import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class StringsKt__StringsKt$rangesDelimitedBy$1 extends Lambda implements Function2 {
    final /* synthetic */ char[] $delimiters;
    final /* synthetic */ boolean $ignoreCase;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StringsKt__StringsKt$rangesDelimitedBy$1(char[] cArr, boolean z) {
        super(2);
        this.$delimiters = cArr;
        this.$ignoreCase = z;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((CharSequence) obj, ((Number) obj2).intValue());
    }

    public final Pair invoke(CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$$receiver");
        int indexOfAny = StringsKt__StringsKt.indexOfAny(charSequence, this.$delimiters, i, this.$ignoreCase);
        if (indexOfAny < 0) {
            return null;
        }
        return TuplesKt.to(Integer.valueOf(indexOfAny), 1);
    }
}
