package kotlin.text;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

abstract class StringsKt__IndentKt extends StringsKt__AppendableKt {
    public static /* synthetic */ String trimMargin$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return trimMargin(str, str2);
    }

    public static final String trimMargin(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(str2, "marginPrefix");
        return replaceIndentByMargin(str, "", str2);
    }

    public static final String replaceIndentByMargin(String str, String str2, String str3) {
        int i;
        String str4;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(str2, "newIndent");
        Intrinsics.checkNotNullParameter(str3, "marginPrefix");
        if (!StringsKt.isBlank(str3)) {
            List lines = StringsKt__StringsKt.lines(str);
            int length = str.length() + (str2.length() * lines.size());
            Function1 indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(str2);
            int lastIndex = CollectionsKt.getLastIndex(lines);
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (Object next : lines) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str5 = (String) next;
                String str6 = null;
                if ((i2 == 0 || i2 == lastIndex) && StringsKt.isBlank(str5)) {
                    str5 = null;
                } else {
                    int length2 = str5.length();
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            i = -1;
                            break;
                        } else if (!CharsKt__CharJVMKt.isWhitespace(str5.charAt(i4))) {
                            i = i4;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (i != -1) {
                        int i5 = i;
                        if (StringsKt.startsWith$default(str5, str3, i, false, 4, (Object) null)) {
                            Intrinsics.checkNotNull(str5, "null cannot be cast to non-null type java.lang.String");
                            str6 = str5.substring(i5 + str3.length());
                            Intrinsics.checkNotNullExpressionValue(str6, "substring(...)");
                        }
                    }
                    if (!(str6 == null || (str4 = (String) indentFunction$StringsKt__IndentKt.invoke(str6)) == null)) {
                        str5 = str4;
                    }
                }
                if (str5 != null) {
                    arrayList.add(str5);
                }
                i2 = i3;
            }
            String sb = ((StringBuilder) CollectionsKt.joinTo$default(arrayList, new StringBuilder(length), ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null)).toString();
            Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
            return sb;
        }
        throw new IllegalArgumentException("marginPrefix must be non-blank string.");
    }

    public static String trimIndent(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return replaceIndent(str, "");
    }

    public static final String replaceIndent(String str, String str2) {
        String str3;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(str2, "newIndent");
        List lines = StringsKt__StringsKt.lines(str);
        ArrayList<String> arrayList = new ArrayList<>();
        for (Object next : lines) {
            if (!StringsKt.isBlank((String) next)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (String indentWidth$StringsKt__IndentKt : arrayList) {
            arrayList2.add(Integer.valueOf(indentWidth$StringsKt__IndentKt(indentWidth$StringsKt__IndentKt)));
        }
        Integer num = (Integer) CollectionsKt.minOrNull(arrayList2);
        int i = 0;
        int intValue = num != null ? num.intValue() : 0;
        int length = str.length() + (str2.length() * lines.size());
        Function1 indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(str2);
        int lastIndex = CollectionsKt.getLastIndex(lines);
        ArrayList arrayList3 = new ArrayList();
        for (Object next2 : lines) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str4 = (String) next2;
            if ((i == 0 || i == lastIndex) && StringsKt.isBlank(str4)) {
                str4 = null;
            } else {
                String drop = StringsKt___StringsKt.drop(str4, intValue);
                if (!(drop == null || (str3 = (String) indentFunction$StringsKt__IndentKt.invoke(drop)) == null)) {
                    str4 = str3;
                }
            }
            if (str4 != null) {
                arrayList3.add(str4);
            }
            i = i2;
        }
        String sb = ((StringBuilder) CollectionsKt.joinTo$default(arrayList3, new StringBuilder(length), ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    private static final Function1 getIndentFunction$StringsKt__IndentKt(String str) {
        if (str.length() == 0) {
            return StringsKt__IndentKt$getIndentFunction$1.INSTANCE;
        }
        return new StringsKt__IndentKt$getIndentFunction$2(str);
    }

    private static final int indentWidth$StringsKt__IndentKt(String str) {
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (!CharsKt__CharJVMKt.isWhitespace(str.charAt(i))) {
                break;
            } else {
                i++;
            }
        }
        return i == -1 ? str.length() : i;
    }
}
