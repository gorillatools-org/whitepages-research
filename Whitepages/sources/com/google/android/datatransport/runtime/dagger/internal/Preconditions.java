package com.google.android.datatransport.runtime.dagger.internal;

public abstract class Preconditions {
    public static Object checkNotNull(Object obj) {
        obj.getClass();
        return obj;
    }

    public static Object checkNotNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    public static Object checkNotNullFromProvides(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }

    public static void checkBuilderRequirement(Object obj, Class cls) {
        if (obj == null) {
            throw new IllegalStateException(cls.getCanonicalName() + " must be set");
        }
    }
}
