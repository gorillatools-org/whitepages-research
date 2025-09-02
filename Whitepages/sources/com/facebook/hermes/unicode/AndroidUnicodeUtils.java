package com.facebook.hermes.unicode;

import com.facebook.proguard.annotations.DoNotStrip;
import java.text.Collator;
import java.text.DateFormat;
import java.text.Normalizer;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class AndroidUnicodeUtils {
    private static final int FORM_C = 0;
    private static final int FORM_D = 1;
    private static final int FORM_KC = 2;
    private static final int FORM_KD = 3;
    public static final AndroidUnicodeUtils INSTANCE = new AndroidUnicodeUtils();
    private static final int TARGET_LOWERCASE = 1;
    private static final int TARGET_UPPERCASE = 0;

    private AndroidUnicodeUtils() {
    }

    @DoNotStrip
    public static final int localeCompare(String str, String str2) {
        return Collator.getInstance().compare(str, str2);
    }

    @DoNotStrip
    public static final String dateFormat(double d, boolean z, boolean z2) {
        DateFormat dateFormat;
        if (z && z2) {
            dateFormat = DateFormat.getDateTimeInstance(2, 2);
        } else if (z) {
            dateFormat = DateFormat.getDateInstance(2);
        } else if (z2) {
            dateFormat = DateFormat.getTimeInstance(2);
        } else {
            throw new IllegalStateException("Bad dateFormat configuration");
        }
        return dateFormat.format(Long.valueOf((long) d)).toString();
    }

    @DoNotStrip
    public static final String convertToCase(String str, int i, boolean z) {
        Intrinsics.checkNotNullParameter(str, "input");
        Locale locale = z ? Locale.getDefault() : Locale.ENGLISH;
        if (i == 0) {
            Intrinsics.checkNotNull(locale);
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return upperCase;
        } else if (i == 1) {
            Intrinsics.checkNotNull(locale);
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return lowerCase;
        } else {
            throw new IllegalStateException("Invalid target case");
        }
    }

    @DoNotStrip
    public static final String normalize(String str, int i) {
        if (i == 0) {
            String normalize = Normalizer.normalize(str, Normalizer.Form.NFC);
            Intrinsics.checkNotNullExpressionValue(normalize, "normalize(...)");
            return normalize;
        } else if (i == 1) {
            String normalize2 = Normalizer.normalize(str, Normalizer.Form.NFD);
            Intrinsics.checkNotNullExpressionValue(normalize2, "normalize(...)");
            return normalize2;
        } else if (i == 2) {
            String normalize3 = Normalizer.normalize(str, Normalizer.Form.NFKC);
            Intrinsics.checkNotNullExpressionValue(normalize3, "normalize(...)");
            return normalize3;
        } else if (i == 3) {
            String normalize4 = Normalizer.normalize(str, Normalizer.Form.NFKD);
            Intrinsics.checkNotNullExpressionValue(normalize4, "normalize(...)");
            return normalize4;
        } else {
            throw new IllegalStateException("Invalid form");
        }
    }
}
