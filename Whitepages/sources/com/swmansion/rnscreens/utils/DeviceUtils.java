package com.swmansion.rnscreens.utils;

public final class DeviceUtils {
    public static final DeviceUtils INSTANCE = new DeviceUtils();

    private DeviceUtils() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r3 = r3.getPackageManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isPlatformAndroidTV(android.content.Context r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L_0x0013
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            if (r3 == 0) goto L_0x0013
            java.lang.String r1 = "android.software.leanback"
            boolean r3 = r3.hasSystemFeature(r1)
            r1 = 1
            if (r3 != r1) goto L_0x0013
            r0 = r1
        L_0x0013:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.utils.DeviceUtils.isPlatformAndroidTV(android.content.Context):boolean");
    }
}
