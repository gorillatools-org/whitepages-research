package com.facebook.infer.annotation;

public abstract class Assertions {
    public static Object assumeNotNull(Object obj) {
        return obj;
    }

    public static Object nullsafeFIXME(Object obj, String str) {
        return obj;
    }

    public static Object assertNotNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new AssertionError(str);
    }

    public static Object assertNotNull(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new AssertionError();
    }

    public static void assertCondition(boolean z) {
        if (!z) {
            throw new AssertionError();
        }
    }

    public static void assertCondition(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }
}
