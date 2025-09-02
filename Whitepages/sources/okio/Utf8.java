package okio;

import kotlin.jvm.internal.Intrinsics;

public abstract class Utf8 {
    public static /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }

    public static final long size(String str, int i, int i2) {
        int i3;
        char c;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
        } else if (i2 < i) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        } else if (i2 <= str.length()) {
            long j = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    j++;
                } else {
                    if (charAt < 2048) {
                        i3 = 2;
                    } else if (charAt < 55296 || charAt > 57343) {
                        i3 = 3;
                    } else {
                        int i4 = i + 1;
                        if (i4 < i2) {
                            c = str.charAt(i4);
                        } else {
                            c = 0;
                        }
                        if (charAt > 56319 || c < 56320 || c > 57343) {
                            j++;
                            i = i4;
                        } else {
                            j += (long) 4;
                            i += 2;
                        }
                    }
                    j += (long) i3;
                }
                i++;
            }
            return j;
        } else {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
        }
    }
}
