package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

public final class BuildCompat {
    public static final int AD_SERVICES_EXTENSION_INT;
    public static final BuildCompat INSTANCE = new BuildCompat();
    public static final int R_EXTENSION_INT;
    public static final int S_EXTENSION_INT;
    public static final int T_EXTENSION_INT;

    private BuildCompat() {
    }

    public static final boolean isAtLeastPreReleaseCodename(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "codename");
        Intrinsics.checkNotNullParameter(str2, "buildCodename");
        if (Intrinsics.areEqual((Object) "REL", (Object) str2)) {
            return false;
        }
        Locale locale = Locale.ROOT;
        String upperCase = str2.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        String upperCase2 = str.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        if (upperCase.compareTo(upperCase2) >= 0) {
            return true;
        }
        return false;
    }

    public static final boolean isAtLeastT() {
        int i = Build.VERSION.SDK_INT;
        if (i < 33) {
            if (i >= 32) {
                String str = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(str, "CODENAME");
                if (isAtLeastPreReleaseCodename("Tiramisu", str)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static final boolean isAtLeastV() {
        if (Build.VERSION.SDK_INT >= 34) {
            String str = Build.VERSION.CODENAME;
            Intrinsics.checkNotNullExpressionValue(str, "CODENAME");
            if (isAtLeastPreReleaseCodename("VanillaIceCream", str)) {
                return true;
            }
        }
        return false;
    }

    static {
        int i = Build.VERSION.SDK_INT;
        int i2 = 0;
        R_EXTENSION_INT = i >= 30 ? Api30Impl.INSTANCE.getExtensionVersion(30) : 0;
        S_EXTENSION_INT = i >= 30 ? Api30Impl.INSTANCE.getExtensionVersion(31) : 0;
        T_EXTENSION_INT = i >= 30 ? Api30Impl.INSTANCE.getExtensionVersion(33) : 0;
        if (i >= 30) {
            i2 = Api30Impl.INSTANCE.getExtensionVersion(1000000);
        }
        AD_SERVICES_EXTENSION_INT = i2;
    }

    private static final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final int getExtensionVersion(int i) {
            return SdkExtensions.getExtensionVersion(i);
        }
    }
}
