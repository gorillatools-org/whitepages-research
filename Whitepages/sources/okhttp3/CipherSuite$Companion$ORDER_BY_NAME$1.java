package okhttp3;

import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

public final class CipherSuite$Companion$ORDER_BY_NAME$1 implements Comparator<String> {
    CipherSuite$Companion$ORDER_BY_NAME$1() {
    }

    public int compare(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "a");
        Intrinsics.checkNotNullParameter(str2, "b");
        int min = Math.min(str.length(), str2.length());
        int i = 4;
        while (i < min) {
            char charAt = str.charAt(i);
            char charAt2 = str2.charAt(i);
            if (charAt == charAt2) {
                i++;
            } else if (Intrinsics.compare((int) charAt, (int) charAt2) < 0) {
                return -1;
            } else {
                return 1;
            }
        }
        int length = str.length();
        int length2 = str2.length();
        if (length == length2) {
            return 0;
        }
        if (length < length2) {
            return -1;
        }
        return 1;
    }
}
