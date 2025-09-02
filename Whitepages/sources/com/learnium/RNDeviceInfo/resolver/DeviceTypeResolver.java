package com.learnium.RNDeviceInfo.resolver;

import android.app.UiModeManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.learnium.RNDeviceInfo.DeviceType;

public class DeviceTypeResolver {
    private final Context context;

    public DeviceTypeResolver(Context context2) {
        this.context = context2;
    }

    public boolean isTablet() {
        return getDeviceType() == DeviceType.TABLET;
    }

    public DeviceType getDeviceType() {
        if (this.context.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv")) {
            return DeviceType.TV;
        }
        UiModeManager uiModeManager = (UiModeManager) this.context.getSystemService("uimode");
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
            return DeviceType.TV;
        }
        DeviceType deviceTypeFromResourceConfiguration = getDeviceTypeFromResourceConfiguration();
        if (deviceTypeFromResourceConfiguration == null || deviceTypeFromResourceConfiguration == DeviceType.UNKNOWN) {
            return getDeviceTypeFromPhysicalSize();
        }
        return deviceTypeFromResourceConfiguration;
    }

    private DeviceType getDeviceTypeFromResourceConfiguration() {
        int i = this.context.getResources().getConfiguration().smallestScreenWidthDp;
        if (i == 0) {
            return DeviceType.UNKNOWN;
        }
        return i >= 600 ? DeviceType.TABLET : DeviceType.HANDSET;
    }

    private DeviceType getDeviceTypeFromPhysicalSize() {
        WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
        if (windowManager == null) {
            return DeviceType.UNKNOWN;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        double sqrt = Math.sqrt(Math.pow(((double) displayMetrics.widthPixels) / ((double) displayMetrics.xdpi), 2.0d) + Math.pow(((double) displayMetrics.heightPixels) / ((double) displayMetrics.ydpi), 2.0d));
        if (sqrt >= 3.0d && sqrt <= 6.9d) {
            return DeviceType.HANDSET;
        }
        if (sqrt <= 6.9d || sqrt > 18.0d) {
            return DeviceType.UNKNOWN;
        }
        return DeviceType.TABLET;
    }
}
