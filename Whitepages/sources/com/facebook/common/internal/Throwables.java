package com.facebook.common.internal;

public abstract class Throwables {
    public static void propagateIfInstanceOf(Throwable th, Class cls) {
        if (th != null && cls.isInstance(th)) {
            throw ((Throwable) cls.cast(th));
        }
    }

    public static void propagateIfPossible(Throwable th) {
        propagateIfInstanceOf(th, Error.class);
        propagateIfInstanceOf(th, RuntimeException.class);
    }

    public static RuntimeException propagate(Throwable th) {
        propagateIfPossible((Throwable) Preconditions.checkNotNull(th));
        throw new RuntimeException(th);
    }
}
