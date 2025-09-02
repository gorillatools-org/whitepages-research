package com.facebook.react.views.view;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;

public final class CanvasUtil {
    public static final CanvasUtil INSTANCE = new CanvasUtil();
    private static Method inorderBarrierMethod;
    private static boolean orderMethodsFetched;
    private static Method reorderBarrierMethod;

    private CanvasUtil() {
    }

    @SuppressLint({"SoonBlockedPrivateApi", "PrivateApi"})
    public static final void enableZ(Canvas canvas, boolean z) {
        Method method;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (Build.VERSION.SDK_INT < 29) {
            INSTANCE.fetchOrderMethods();
            if (z) {
                try {
                    Method method2 = reorderBarrierMethod;
                    if (method2 != null) {
                        if (method2 != null) {
                            method2.invoke(canvas, (Object[]) null);
                        } else {
                            throw new IllegalStateException("Required value was null.");
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException unused) {
                    return;
                }
            }
            if (!z && (method = inorderBarrierMethod) != null) {
                if (method != null) {
                    method.invoke(canvas, (Object[]) null);
                    return;
                }
                throw new IllegalStateException("Required value was null.");
            }
        } else if (z) {
            canvas.enableZ();
        } else {
            canvas.disableZ();
        }
    }

    private final void fetchOrderMethods() {
        if (!orderMethodsFetched) {
            try {
                Class<Canvas> cls = Canvas.class;
                if (Build.VERSION.SDK_INT == 28) {
                    Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", new Class[]{String.class, Object[].class});
                    Object invoke = declaredMethod.invoke(cls, new Object[]{"insertReorderBarrier", new Class[0]});
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type java.lang.reflect.Method");
                    reorderBarrierMethod = (Method) invoke;
                    Object invoke2 = declaredMethod.invoke(cls, new Object[]{"insertInorderBarrier", new Class[0]});
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type java.lang.reflect.Method");
                    inorderBarrierMethod = (Method) invoke2;
                } else {
                    reorderBarrierMethod = cls.getDeclaredMethod("insertReorderBarrier", (Class[]) null);
                    inorderBarrierMethod = cls.getDeclaredMethod("insertInorderBarrier", (Class[]) null);
                }
                Method method = reorderBarrierMethod;
                if (method == null) {
                    return;
                }
                if (inorderBarrierMethod != null) {
                    if (method != null) {
                        method.setAccessible(true);
                    }
                    Method method2 = inorderBarrierMethod;
                    if (method2 != null) {
                        method2.setAccessible(true);
                    }
                    orderMethodsFetched = true;
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
        }
    }
}
