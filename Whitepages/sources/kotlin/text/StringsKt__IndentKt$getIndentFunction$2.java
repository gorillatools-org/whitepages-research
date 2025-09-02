package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class StringsKt__IndentKt$getIndentFunction$2 extends Lambda implements Function1 {
    final /* synthetic */ String $indent;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StringsKt__IndentKt$getIndentFunction$2(String str) {
        super(1);
        this.$indent = str;
    }

    public final String invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "line");
        return this.$indent + str;
    }
}
