package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class StringsKt__IndentKt$getIndentFunction$1 extends Lambda implements Function1 {
    public static final StringsKt__IndentKt$getIndentFunction$1 INSTANCE = new StringsKt__IndentKt$getIndentFunction$1();

    StringsKt__IndentKt$getIndentFunction$1() {
        super(1);
    }

    public final String invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "line");
        return str;
    }
}
