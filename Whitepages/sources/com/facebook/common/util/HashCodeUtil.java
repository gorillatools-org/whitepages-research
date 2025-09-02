package com.facebook.common.util;

public abstract class HashCodeUtil {
    public static int hashCode(int i, int i2) {
        return ((i + 31) * 31) + i2;
    }

    public static int hashCode(Object obj, Object obj2) {
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode(hashCode, i);
    }
}
