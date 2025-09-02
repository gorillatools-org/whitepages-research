package com.facebook.react.modules.systeminfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.facebook.react.R;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

public final class AndroidInfoHelpers {
    public static final String DEVICE_LOCALHOST = "localhost";
    public static final String EMULATOR_LOCALHOST = "10.0.2.2";
    public static final String GENYMOTION_LOCALHOST = "10.0.3.2";
    public static final AndroidInfoHelpers INSTANCE = new AndroidInfoHelpers();
    public static final String METRO_HOST_PROP_NAME = "metro.host";
    private static final String TAG = AndroidInfoHelpers.class.getSimpleName();
    private static String metroHostPropValue;

    private AndroidInfoHelpers() {
    }

    private final boolean isRunningOnGenymotion() {
        String str = Build.FINGERPRINT;
        Intrinsics.checkNotNullExpressionValue(str, "FINGERPRINT");
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "vbox", false, 2, (Object) null);
    }

    private final boolean isRunningOnStockEmulator() {
        String str = Build.FINGERPRINT;
        Intrinsics.checkNotNullExpressionValue(str, "FINGERPRINT");
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "generic", false, 2, (Object) null)) {
            Intrinsics.checkNotNullExpressionValue(str, "FINGERPRINT");
            return StringsKt.startsWith$default(str, "google/sdk_gphone", false, 2, (Object) null);
        }
    }

    public static final String getServerHost(int i) {
        return INSTANCE.getServerIpAddress(i);
    }

    public static final String getServerHost(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AndroidInfoHelpers androidInfoHelpers = INSTANCE;
        return androidInfoHelpers.getServerIpAddress(androidInfoHelpers.getDevServerPort(context));
    }

    public static final String getAdbReverseTcpCommand(int i) {
        return "adb reverse tcp:" + i + " tcp:" + i;
    }

    public static final String getAdbReverseTcpCommand(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getAdbReverseTcpCommand(INSTANCE.getDevServerPort(context));
    }

    public static final String getFriendlyDeviceName() {
        if (INSTANCE.isRunningOnGenymotion()) {
            String str = Build.MODEL;
            Intrinsics.checkNotNull(str);
            return str;
        }
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.RELEASE;
        int i = Build.VERSION.SDK_INT;
        return str2 + " - " + str3 + " - API " + i;
    }

    public static final Map<String, String> getInspectorHostMetadata(Context context) {
        String str;
        String str2;
        if (context != null) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i = applicationInfo.labelRes;
            str2 = context.getPackageName();
            if (i == 0) {
                str = applicationInfo.nonLocalizedLabel.toString();
            } else {
                str = context.getString(i);
                Intrinsics.checkNotNull(str);
            }
        } else {
            str2 = null;
            str = null;
        }
        return MapsKt.mapOf(TuplesKt.to("appDisplayName", str), TuplesKt.to("appIdentifier", str2), TuplesKt.to(k.a.b, "android"), TuplesKt.to("deviceName", Build.MODEL), TuplesKt.to("reactNativeVersion", INSTANCE.getReactNativeVersionString()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x002d, code lost:
        if (r0 == null) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String getReactNativeVersionString() {
        /*
            r6 = this;
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.facebook.react.modules.systeminfo.ReactNativeVersion.VERSION
            java.lang.String r1 = "major"
            java.lang.Object r1 = r0.get(r1)
            java.lang.String r2 = "minor"
            java.lang.Object r2 = r0.get(r2)
            java.lang.String r3 = "patch"
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r4 = "prerelease"
            java.lang.Object r0 = r0.get(r4)
            if (r0 == 0) goto L_0x002f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "-"
            r4.append(r5)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            if (r0 != 0) goto L_0x0031
        L_0x002f:
            java.lang.String r0 = ""
        L_0x0031:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r1)
            java.lang.String r1 = "."
            r4.append(r1)
            r4.append(r2)
            r4.append(r1)
            r4.append(r3)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoHelpers.getReactNativeVersionString():java.lang.String");
    }

    private final int getDevServerPort(Context context) {
        return context.getResources().getInteger(R.integer.react_native_dev_server_port);
    }

    private final String getServerIpAddress(int i) {
        String str;
        if (getMetroHostPropValue().length() > 0) {
            str = getMetroHostPropValue();
        } else if (isRunningOnGenymotion()) {
            str = GENYMOTION_LOCALHOST;
        } else if (isRunningOnStockEmulator()) {
            str = EMULATOR_LOCALHOST;
        } else {
            str = DEVICE_LOCALHOST;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%s:%d", Arrays.copyOf(new Object[]{str, Integer.valueOf(i)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A[SYNTHETIC, Splitter:B:35:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0079 A[SYNTHETIC, Splitter:B:45:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized java.lang.String getMetroHostPropValue() {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = metroHostPropValue     // Catch:{ all -> 0x000a }
            if (r0 == 0) goto L_0x000d
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x000a }
            monitor-exit(r7)
            return r0
        L_0x000a:
            r0 = move-exception
            goto L_0x0082
        L_0x000d:
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            java.lang.String r2 = "/system/bin/getprop"
            java.lang.String r3 = "metro.host"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.io.InputStream r4 = r1.getInputStream()     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.lang.String r5 = "UTF-8"
            java.nio.charset.Charset r5 = java.nio.charset.Charset.forName(r5)     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            r3.<init>(r4, r5)     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.lang.String r0 = ""
        L_0x0034:
            java.lang.String r3 = r2.readLine()     // Catch:{ Exception -> 0x0047 }
            if (r3 == 0) goto L_0x003c
            r0 = r3
            goto L_0x0034
        L_0x003c:
            metroHostPropValue = r0     // Catch:{ Exception -> 0x0047 }
            r2.close()     // Catch:{ all -> 0x000a }
        L_0x0041:
            r1.destroy()     // Catch:{ all -> 0x000a }
            goto L_0x006f
        L_0x0045:
            r0 = move-exception
            goto L_0x0077
        L_0x0047:
            r0 = move-exception
            goto L_0x005c
        L_0x0049:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x0077
        L_0x004e:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x005c
        L_0x0053:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L_0x0077
        L_0x0058:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L_0x005c:
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0045 }
            java.lang.String r4 = "Failed to query for metro.host prop:"
            com.facebook.common.logging.FLog.w((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0045 }
            java.lang.String r0 = ""
            metroHostPropValue = r0     // Catch:{ all -> 0x0045 }
            if (r2 == 0) goto L_0x006c
            r2.close()     // Catch:{ all -> 0x000a }
        L_0x006c:
            if (r1 == 0) goto L_0x006f
            goto L_0x0041
        L_0x006f:
            java.lang.String r0 = metroHostPropValue     // Catch:{ all -> 0x000a }
            if (r0 != 0) goto L_0x0075
            java.lang.String r0 = ""
        L_0x0075:
            monitor-exit(r7)
            return r0
        L_0x0077:
            if (r2 == 0) goto L_0x007c
            r2.close()     // Catch:{ all -> 0x000a }
        L_0x007c:
            if (r1 == 0) goto L_0x0081
            r1.destroy()     // Catch:{ all -> 0x000a }
        L_0x0081:
            throw r0     // Catch:{ all -> 0x000a }
        L_0x0082:
            monitor-exit(r7)     // Catch:{ all -> 0x000a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoHelpers.getMetroHostPropValue():java.lang.String");
    }
}
