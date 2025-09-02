package kotlin.text;

import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;

abstract class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    public static char last(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() != 0) {
            return charSequence.charAt(StringsKt__StringsKt.getLastIndex(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static char random(CharSequence charSequence, Random random) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (charSequence.length() != 0) {
            return charSequence.charAt(random.nextInt(charSequence.length()));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static final String drop(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i >= 0) {
            String substring = str.substring(RangesKt.coerceAtMost(i, str.length()));
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    public static String take(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i >= 0) {
            String substring = str.substring(0, RangesKt.coerceAtMost(i, str.length()));
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }
}
