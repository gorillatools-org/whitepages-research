package com.facebook.react.views.text;

import java.text.BreakIterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

public final class TextTransformKt {

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.react.views.text.TextTransform[] r0 = com.facebook.react.views.text.TextTransform.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.views.text.TextTransform r1 = com.facebook.react.views.text.TextTransform.UPPERCASE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.views.text.TextTransform r1 = com.facebook.react.views.text.TextTransform.LOWERCASE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.views.text.TextTransform r1 = com.facebook.react.views.text.TextTransform.CAPITALIZE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextTransformKt.WhenMappings.<clinit>():void");
        }
    }

    public static final String applyTextTransform(String str, TextTransform textTransform) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int i = textTransform == null ? -1 : WhenMappings.$EnumSwitchMapping$0[textTransform.ordinal()];
        if (i == 1) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return upperCase;
        } else if (i == 2) {
            Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
            String lowerCase = str.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return lowerCase;
        } else if (i != 3) {
            return str;
        } else {
            BreakIterator wordInstance = BreakIterator.getWordInstance();
            wordInstance.setText(str);
            StringBuilder sb = new StringBuilder(str.length());
            int first = wordInstance.first();
            int next = wordInstance.next();
            while (true) {
                int i2 = next;
                int i3 = first;
                first = i2;
                if (first != -1) {
                    String substring = str.substring(i3, first);
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    if (substring.length() > 0) {
                        char upperCase2 = Character.toUpperCase(substring.charAt(0));
                        String substring2 = substring.substring(1);
                        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                        substring = upperCase2 + substring2;
                    }
                    sb.append(substring);
                    next = wordInstance.next();
                } else {
                    String sb2 = sb.toString();
                    Intrinsics.checkNotNull(sb2);
                    return sb2;
                }
            }
        }
    }
}
