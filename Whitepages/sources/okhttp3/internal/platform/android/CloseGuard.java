package okhttp3.internal.platform.android;

import java.lang.reflect.Method;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CloseGuard {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Method getMethod;
    private final Method openMethod;
    private final Method warnIfOpenMethod;

    public CloseGuard(Method method, Method method2, Method method3) {
        this.getMethod = method;
        this.openMethod = method2;
        this.warnIfOpenMethod = method3;
    }

    public final Object createAndOpen(String str) {
        Intrinsics.checkNotNullParameter(str, "closer");
        Method method = this.getMethod;
        if (method != null) {
            try {
                Object invoke = method.invoke((Object) null, (Object[]) null);
                Method method2 = this.openMethod;
                Intrinsics.checkNotNull(method2);
                method2.invoke(invoke, new Object[]{str});
                return invoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final boolean warnIfOpen(Object obj) {
        if (obj != null) {
            try {
                Method method = this.warnIfOpenMethod;
                Intrinsics.checkNotNull(method);
                method.invoke(obj, (Object[]) null);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CloseGuard get() {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                Method method4 = cls.getMethod("get", (Class[]) null);
                method = cls.getMethod("open", new Class[]{String.class});
                method2 = cls.getMethod("warnIfOpen", (Class[]) null);
                method3 = method4;
            } catch (Exception unused) {
                method2 = null;
                method = null;
            }
            return new CloseGuard(method3, method, method2);
        }
    }
}
