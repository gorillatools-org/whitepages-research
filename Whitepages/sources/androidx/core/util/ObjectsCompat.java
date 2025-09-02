package androidx.core.util;

import java.util.Objects;

public abstract class ObjectsCompat {
    public static boolean equals(Object obj, Object obj2) {
        return Objects.equals(obj, obj2);
    }

    public static int hash(Object... objArr) {
        return Objects.hash(objArr);
    }

    public static Object requireNonNull(Object obj) {
        obj.getClass();
        return obj;
    }

    public static Object requireNonNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }
}
