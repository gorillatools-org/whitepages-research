package kotlin.internal;

import java.lang.reflect.Method;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;

public abstract class PlatformImplementations {

    private static final class ReflectThrowable {
        public static final ReflectThrowable INSTANCE = new ReflectThrowable();
        public static final Method addSuppressed;
        public static final Method getSuppressed;

        private ReflectThrowable() {
        }

        static {
            Method method;
            Method method2;
            Class<Throwable> cls = Throwable.class;
            Method[] methods = cls.getMethods();
            Intrinsics.checkNotNull(methods);
            int length = methods.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                method = null;
                if (i2 >= length) {
                    method2 = null;
                    break;
                }
                method2 = methods[i2];
                if (Intrinsics.areEqual((Object) method2.getName(), (Object) "addSuppressed")) {
                    Class[] parameterTypes = method2.getParameterTypes();
                    Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
                    if (Intrinsics.areEqual(ArraysKt.singleOrNull(parameterTypes), (Object) cls)) {
                        break;
                    }
                }
                i2++;
            }
            addSuppressed = method2;
            int length2 = methods.length;
            while (true) {
                if (i >= length2) {
                    break;
                }
                Method method3 = methods[i];
                if (Intrinsics.areEqual((Object) method3.getName(), (Object) "getSuppressed")) {
                    method = method3;
                    break;
                }
                i++;
            }
            getSuppressed = method;
        }
    }

    public void addSuppressed(Throwable th, Throwable th2) {
        Intrinsics.checkNotNullParameter(th, "cause");
        Intrinsics.checkNotNullParameter(th2, "exception");
        Method method = ReflectThrowable.addSuppressed;
        if (method != null) {
            method.invoke(th, new Object[]{th2});
        }
    }

    public Random defaultPlatformRandom() {
        return new FallbackThreadLocalRandom();
    }
}
