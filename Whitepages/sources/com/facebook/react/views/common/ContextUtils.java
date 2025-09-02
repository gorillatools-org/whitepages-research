package com.facebook.react.views.common;

import android.content.ContextWrapper;
import kotlin.jvm.internal.Intrinsics;

public final class ContextUtils {
    public static final ContextUtils INSTANCE = new ContextUtils();

    private ContextUtils() {
    }

    public static final <T> T findContextOfType(T t, Class<? extends T> cls) {
        T baseContext;
        Intrinsics.checkNotNullParameter(cls, "clazz");
        while (!cls.isInstance(t)) {
            if (!(t instanceof ContextWrapper) || t == (baseContext = ((ContextWrapper) t).getBaseContext())) {
                return null;
            }
            t = baseContext;
        }
        return t;
    }
}
