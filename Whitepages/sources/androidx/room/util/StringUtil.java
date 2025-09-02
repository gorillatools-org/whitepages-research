package androidx.room.util;

import kotlin.jvm.internal.Intrinsics;

public abstract class StringUtil {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static final StringBuilder newStringBuilder() {
        return new StringBuilder();
    }

    public static final void appendPlaceholders(StringBuilder sb, int i) {
        Intrinsics.checkNotNullParameter(sb, "builder");
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("?");
            if (i2 < i - 1) {
                sb.append(",");
            }
        }
    }
}
