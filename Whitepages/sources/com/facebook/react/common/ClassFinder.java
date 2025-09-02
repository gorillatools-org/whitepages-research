package com.facebook.react.common;

import kotlin.jvm.internal.Intrinsics;

public final class ClassFinder {
    public static final ClassFinder INSTANCE = new ClassFinder();

    public static final boolean canLoadClassesFromAnnotationProcessors() {
        return false;
    }

    private ClassFinder() {
    }

    public static final Class<?> findClass(String str) throws ClassNotFoundException {
        Intrinsics.checkNotNullParameter(str, "className");
        if (!canLoadClassesFromAnnotationProcessors()) {
            return null;
        }
        return Class.forName(str);
    }
}
