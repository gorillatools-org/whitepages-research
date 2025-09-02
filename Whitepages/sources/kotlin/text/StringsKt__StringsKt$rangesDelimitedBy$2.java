package kotlin.text;

import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class StringsKt__StringsKt$rangesDelimitedBy$2 extends Lambda implements Function2 {
    final /* synthetic */ List $delimitersList;
    final /* synthetic */ boolean $ignoreCase;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StringsKt__StringsKt$rangesDelimitedBy$2(List list, boolean z) {
        super(2);
        this.$delimitersList = list;
        this.$ignoreCase = z;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((CharSequence) obj, ((Number) obj2).intValue());
    }

    public final Pair invoke(CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$$receiver");
        Pair access$findAnyOf = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(charSequence, this.$delimitersList, i, this.$ignoreCase, false);
        if (access$findAnyOf != null) {
            return TuplesKt.to(access$findAnyOf.getFirst(), Integer.valueOf(((String) access$findAnyOf.getSecond()).length()));
        }
        return null;
    }
}
