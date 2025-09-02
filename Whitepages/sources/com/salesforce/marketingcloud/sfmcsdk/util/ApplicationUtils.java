package com.salesforce.marketingcloud.sfmcsdk.util;

import android.content.Context;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class ApplicationUtils {
    public static final ApplicationUtils INSTANCE = new ApplicationUtils();
    public static final String TAG = "~$ApplicationUtils";

    private ApplicationUtils() {
    }

    public static final String getApplicationName(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        } catch (Exception e) {
            SFMCSdkLogger.INSTANCE.e(TAG, e, ApplicationUtils$getApplicationName$1.INSTANCE);
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x005b, code lost:
        com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE.w(TAG, r4, com.salesforce.marketingcloud.sfmcsdk.util.ApplicationUtils$getApplicationVersion$1.INSTANCE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r0 = r4.getPackageManager().getPackageInfo(r4.getPackageName(), 0).versionName;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x005a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x004a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getApplicationVersion(android.content.Context r4) {
        /*
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 0
            com.salesforce.marketingcloud.sfmcsdk.util.ApplicationUtils r1 = INSTANCE     // Catch:{ all -> 0x004a }
            java.lang.String r2 = r4.getPackageName()     // Catch:{ all -> 0x004a }
            java.lang.String r3 = "context.packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x004a }
            java.lang.String r1 = r1.findBuildConfig(r2)     // Catch:{ all -> 0x004a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004a }
            r2.<init>()     // Catch:{ all -> 0x004a }
            r2.append(r1)     // Catch:{ all -> 0x004a }
            java.lang.String r1 = ".BuildConfig"
            r2.append(r1)     // Catch:{ all -> 0x004a }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x004a }
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ all -> 0x004a }
            java.lang.String r2 = "VERSION_NAME"
            java.lang.reflect.Field r1 = r1.getField(r2)     // Catch:{ all -> 0x004a }
            java.lang.String r2 = "clazz.getField(\"VERSION_NAME\")"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch:{ all -> 0x004a }
            r2 = 1
            r1.setAccessible(r2)     // Catch:{ all -> 0x004a }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x0042
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x004a }
            goto L_0x0065
        L_0x0042:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException     // Catch:{ all -> 0x004a }
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.String"
            r1.<init>(r2)     // Catch:{ all -> 0x004a }
            throw r1     // Catch:{ all -> 0x004a }
        L_0x004a:
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch:{ Exception -> 0x005a }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ Exception -> 0x005a }
            r2 = 0
            android.content.pm.PackageInfo r4 = r1.getPackageInfo(r4, r2)     // Catch:{ Exception -> 0x005a }
            java.lang.String r0 = r4.versionName     // Catch:{ Exception -> 0x005a }
            goto L_0x0064
        L_0x005a:
            r4 = move-exception
            com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r1 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE
            java.lang.String r2 = "~$ApplicationUtils"
            com.salesforce.marketingcloud.sfmcsdk.util.ApplicationUtils$getApplicationVersion$1 r3 = com.salesforce.marketingcloud.sfmcsdk.util.ApplicationUtils$getApplicationVersion$1.INSTANCE
            r1.w(r2, r4, r3)
        L_0x0064:
            r1 = r0
        L_0x0065:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.util.ApplicationUtils.getApplicationVersion(android.content.Context):java.lang.String");
    }

    private final String findBuildConfig(String str) {
        try {
            Class.forName(str + ".BuildConfig");
            return str;
        } catch (Exception unused) {
            if (StringsKt.lastIndexOf$default((CharSequence) str, ".", 0, false, 6, (Object) null) <= 0) {
                return null;
            }
            String substring = str.substring(0, StringsKt.lastIndexOf$default((CharSequence) str, ".", 0, false, 6, (Object) null));
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return findBuildConfig(substring);
        }
    }
}
