package kotlin.internal.jdk7;

import kotlin.internal.PlatformImplementations;
import kotlin.jvm.internal.Intrinsics;

public abstract class JDK7PlatformImplementations extends PlatformImplementations {

    private static final class ReflectSdkVersion {
        public static final ReflectSdkVersion INSTANCE = new ReflectSdkVersion();
        public static final Integer sdkVersion;

        private ReflectSdkVersion() {
        }

        static {
            Integer num;
            Integer num2 = null;
            try {
                Object obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null);
                if (obj instanceof Integer) {
                    num = (Integer) obj;
                    if (num != null && num.intValue() > 0) {
                        num2 = num;
                    }
                    sdkVersion = num2;
                }
            } catch (Throwable unused) {
            }
            num = null;
            num2 = num;
            sdkVersion = num2;
        }
    }

    private final boolean sdkIsNullOrAtLeast(int i) {
        Integer num = ReflectSdkVersion.sdkVersion;
        return num == null || num.intValue() >= i;
    }

    public void addSuppressed(Throwable th, Throwable th2) {
        Intrinsics.checkNotNullParameter(th, "cause");
        Intrinsics.checkNotNullParameter(th2, "exception");
        if (sdkIsNullOrAtLeast(19)) {
            th.addSuppressed(th2);
        } else {
            super.addSuppressed(th, th2);
        }
    }
}
