package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.InputDevice;
import android.view.ViewConfiguration;
import androidx.customview.widget.ExploreByTouchHelper;
import java.util.Objects;

public abstract class ViewConfigurationCompat {
    public static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration, Context context) {
        return Api26Impl.getScaledHorizontalScrollFactor(viewConfiguration);
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration, Context context) {
        return Api26Impl.getScaledVerticalScrollFactor(viewConfiguration);
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration, Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.shouldShowMenuShortcutsWhenKeyboardPresent(viewConfiguration);
        }
        Resources resources = context.getResources();
        int platformResId = getPlatformResId(resources, "config_showMenuShortcutsWhenKeyboardPresent", "bool");
        return platformResId != 0 && resources.getBoolean(platformResId);
    }

    public static int getScaledMinimumFlingVelocity(Context context, ViewConfiguration viewConfiguration, int i, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getScaledMinimumFlingVelocity(viewConfiguration, i, i2, i3);
        }
        if (!isInputDeviceInfoValid(i, i2, i3)) {
            return Integer.MAX_VALUE;
        }
        Resources resources = context.getResources();
        int preApi34MinimumFlingVelocityResId = getPreApi34MinimumFlingVelocityResId(resources, i3, i2);
        Objects.requireNonNull(viewConfiguration);
        return getCompatFlingVelocityThreshold(resources, preApi34MinimumFlingVelocityResId, new ViewConfigurationCompat$$ExternalSyntheticLambda1(viewConfiguration), Integer.MAX_VALUE);
    }

    public static int getScaledMaximumFlingVelocity(Context context, ViewConfiguration viewConfiguration, int i, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getScaledMaximumFlingVelocity(viewConfiguration, i, i2, i3);
        }
        if (!isInputDeviceInfoValid(i, i2, i3)) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        Resources resources = context.getResources();
        int preApi34MaximumFlingVelocityResId = getPreApi34MaximumFlingVelocityResId(resources, i3, i2);
        Objects.requireNonNull(viewConfiguration);
        return getCompatFlingVelocityThreshold(resources, preApi34MaximumFlingVelocityResId, new ViewConfigurationCompat$$ExternalSyntheticLambda0(viewConfiguration), ExploreByTouchHelper.INVALID_ID);
    }

    static class Api26Impl {
        static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHorizontalScrollFactor();
        }

        static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledVerticalScrollFactor();
        }
    }

    static class Api28Impl {
        static int getScaledHoverSlop(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHoverSlop();
        }

        static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
    }

    static class Api34Impl {
        static int getScaledMaximumFlingVelocity(ViewConfiguration viewConfiguration, int i, int i2, int i3) {
            return viewConfiguration.getScaledMaximumFlingVelocity(i, i2, i3);
        }

        static int getScaledMinimumFlingVelocity(ViewConfiguration viewConfiguration, int i, int i2, int i3) {
            return viewConfiguration.getScaledMinimumFlingVelocity(i, i2, i3);
        }
    }

    private static int getPreApi34MaximumFlingVelocityResId(Resources resources, int i, int i2) {
        if (i == 4194304 && i2 == 26) {
            return getPlatformResId(resources, "config_viewMaxRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    private static int getPreApi34MinimumFlingVelocityResId(Resources resources, int i, int i2) {
        if (i == 4194304 && i2 == 26) {
            return getPlatformResId(resources, "config_viewMinRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    private static int getPlatformResId(Resources resources, String str, String str2) {
        return resources.getIdentifier(str, str2, "android");
    }

    private static boolean isInputDeviceInfoValid(int i, int i2, int i3) {
        InputDevice device = InputDevice.getDevice(i);
        return (device == null || device.getMotionRange(i2, i3) == null) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        r1 = r1.getDimensionPixelSize(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int getCompatFlingVelocityThreshold(android.content.res.Resources r1, int r2, androidx.core.util.Supplier r3, int r4) {
        /*
            r0 = -1
            if (r2 == r0) goto L_0x000e
            if (r2 == 0) goto L_0x000d
            int r1 = r1.getDimensionPixelSize(r2)
            if (r1 >= 0) goto L_0x000c
            goto L_0x000d
        L_0x000c:
            r4 = r1
        L_0x000d:
            return r4
        L_0x000e:
            java.lang.Object r1 = r3.get()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.ViewConfigurationCompat.getCompatFlingVelocityThreshold(android.content.res.Resources, int, androidx.core.util.Supplier, int):int");
    }
}
