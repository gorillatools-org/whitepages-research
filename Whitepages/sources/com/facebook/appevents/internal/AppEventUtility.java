package com.facebook.appevents.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.Window;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;

public final class AppEventUtility {
    public static final AppEventUtility INSTANCE = new AppEventUtility();

    public static final void assertIsMainThread() {
    }

    public static final void assertIsNotMainThread() {
    }

    private AppEventUtility() {
    }

    public static final double normalizePrice(String str) {
        try {
            Matcher matcher = Pattern.compile("[-+]*\\d+([.,]\\d+)*([.,]\\d+)?", 8).matcher(str);
            if (!matcher.find()) {
                return 0.0d;
            }
            return NumberFormat.getNumberInstance(Utility.getCurrentLocale()).parse(matcher.group(0)).doubleValue();
        } catch (ParseException unused) {
            return 0.0d;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0069, code lost:
        if (kotlin.text.StringsKt.startsWith$default(r0, "generic", false, 2, (java.lang.Object) null) == false) goto L_0x006b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isEmulator() {
        /*
            java.lang.String r0 = android.os.Build.FINGERPRINT
            java.lang.String r1 = "FINGERPRINT"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r2 = "generic"
            r3 = 0
            r4 = 2
            r5 = 0
            boolean r6 = kotlin.text.StringsKt.startsWith$default(r0, r2, r3, r4, r5)
            if (r6 != 0) goto L_0x0073
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r1 = "unknown"
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r3, r4, r5)
            if (r0 != 0) goto L_0x0073
            java.lang.String r0 = android.os.Build.MODEL
            java.lang.String r1 = "MODEL"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r6 = "google_sdk"
            boolean r7 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r6, (boolean) r3, (int) r4, (java.lang.Object) r5)
            if (r7 != 0) goto L_0x0073
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r7 = "Emulator"
            boolean r7 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r7, (boolean) r3, (int) r4, (java.lang.Object) r5)
            if (r7 != 0) goto L_0x0073
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r1 = "Android SDK built for x86"
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r1, (boolean) r3, (int) r4, (java.lang.Object) r5)
            if (r0 != 0) goto L_0x0073
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = "MANUFACTURER"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r1 = "Genymotion"
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r1, (boolean) r3, (int) r4, (java.lang.Object) r5)
            if (r0 != 0) goto L_0x0073
            java.lang.String r0 = android.os.Build.BRAND
            java.lang.String r1 = "BRAND"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x006b
            java.lang.String r0 = android.os.Build.DEVICE
            java.lang.String r1 = "DEVICE"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x0073
        L_0x006b:
            java.lang.String r0 = android.os.Build.PRODUCT
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r0)
            if (r0 == 0) goto L_0x0074
        L_0x0073:
            r3 = 1
        L_0x0074:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.internal.AppEventUtility.isEmulator():boolean");
    }

    public static final String getAppVersion() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        try {
            String str = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionName;
            Intrinsics.checkNotNullExpressionValue(str, "{\n      val packageInfo …ageInfo.versionName\n    }");
            return str;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static final View getRootView(Activity activity) {
        Class<AppEventUtility> cls = AppEventUtility.class;
        if (CrashShieldHandler.isObjectCrashing(cls) || activity == null) {
            return null;
        }
        try {
            Window window = activity.getWindow();
            if (window == null) {
                return null;
            }
            return window.getDecorView().getRootView();
        } catch (Exception unused) {
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }
}
