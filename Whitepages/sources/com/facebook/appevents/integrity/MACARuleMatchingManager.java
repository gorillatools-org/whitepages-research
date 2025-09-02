package com.facebook.appevents.integrity;

import android.os.Build;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import io.branch.rnbranch.RNBranchModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONObject;

public final class MACARuleMatchingManager {
    public static final MACARuleMatchingManager INSTANCE = new MACARuleMatchingManager();
    private static JSONArray MACARules;
    private static boolean enabled;
    private static String[] keys = {"event", "_locale", "_appVersion", "_deviceOS", "_platform", "_deviceModel", "_nativeAppID", "_nativeAppShortVersion", "_timezone", "_carrier", "_deviceOSTypeName", "_deviceOSVersion", "_remainingDiskGB"};

    private MACARuleMatchingManager() {
    }

    public static final void enable() {
        Class<MACARuleMatchingManager> cls = MACARuleMatchingManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                INSTANCE.loadMACARules();
                if (MACARules != null) {
                    enabled = true;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void loadMACARules() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null) {
                    MACARules = queryAppSettings.getMACARuleMatchingSetting();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final String getKey(JSONObject jSONObject) {
        Class<MACARuleMatchingManager> cls = MACARuleMatchingManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(jSONObject, "logic");
            Iterator<String> keys2 = jSONObject.keys();
            if (keys2.hasNext()) {
                return keys2.next();
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01bf, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7.toString(), (java.lang.Object) r3) != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01db, code lost:
        if (java.lang.Double.parseDouble(r7.toString()) <= java.lang.Double.parseDouble(r3)) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x020b, code lost:
        if (java.lang.Double.parseDouble(r7.toString()) >= java.lang.Double.parseDouble(r3)) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0219, code lost:
        if (r9 != null) goto L_0x021c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x021b, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0220, code lost:
        if (r9.isEmpty() == false) goto L_0x0224;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0224, code lost:
        r8 = r9.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x022c, code lost:
        if (r8.hasNext() == false) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x022e, code lost:
        r10 = java.util.Locale.ROOT;
        r9 = ((java.lang.String) r8.next()).toLowerCase(r10);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        r10 = r7.toString().toLowerCase(r10);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, "this as java.lang.String).toLowerCase(Locale.ROOT)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x024c, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10) == false) goto L_0x0228;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x025a, code lost:
        if (r9 != null) goto L_0x025d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x025c, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0261, code lost:
        if (r9.isEmpty() == false) goto L_0x0265;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0265, code lost:
        r8 = r9.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x026d, code lost:
        if (r8.hasNext() == false) goto L_0x034e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x026f, code lost:
        r10 = java.util.Locale.ROOT;
        r9 = ((java.lang.String) r8.next()).toLowerCase(r10);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        r10 = r7.toString().toLowerCase(r10);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, "this as java.lang.String).toLowerCase(Locale.ROOT)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x028d, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10) == false) goto L_0x0269;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0308, code lost:
        if (r9 != null) goto L_0x030b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x030a, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x031d, code lost:
        if (r9 != null) goto L_0x0320;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x031f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0058, code lost:
        if (r7 == null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:?, code lost:
        return kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7.toString(), (java.lang.Object) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:?, code lost:
        return r9.contains(r7.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:?, code lost:
        return r9.contains(r7.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0183, code lost:
        if (java.lang.Double.parseDouble(r7.toString()) < java.lang.Double.parseDouble(r3)) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01a9, code lost:
        if (java.lang.Double.parseDouble(r7.toString()) > java.lang.Double.parseDouble(r3)) goto L_?;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean stringComparison(java.lang.String r8, org.json.JSONObject r9, android.os.Bundle r10) {
        /*
            java.lang.Class<com.facebook.appevents.integrity.MACARuleMatchingManager> r0 = com.facebook.appevents.integrity.MACARuleMatchingManager.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            r2 = 0
            if (r1 == 0) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r1 = "variable"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r1)     // Catch:{ all -> 0x0042 }
            java.lang.String r1 = "values"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r1)     // Catch:{ all -> 0x0042 }
            java.lang.String r1 = getKey(r9)     // Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x001b
            return r2
        L_0x001b:
            java.lang.Object r3 = r9.get(r1)     // Catch:{ all -> 0x0042 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0042 }
            org.json.JSONArray r9 = r9.optJSONArray(r1)     // Catch:{ all -> 0x0042 }
            java.util.ArrayList r9 = getStringArrayList(r9)     // Catch:{ all -> 0x0042 }
            java.lang.String r4 = "exists"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ all -> 0x0042 }
            r5 = 1
            if (r4 == 0) goto L_0x0046
            if (r10 == 0) goto L_0x0045
            boolean r8 = r10.containsKey(r8)     // Catch:{ all -> 0x0042 }
            boolean r9 = java.lang.Boolean.parseBoolean(r3)     // Catch:{ all -> 0x0042 }
            if (r8 != r9) goto L_0x0045
            r2 = r5
            goto L_0x0045
        L_0x0042:
            r8 = move-exception
            goto L_0x034f
        L_0x0045:
            return r2
        L_0x0046:
            r4 = 0
            java.lang.String r6 = "this as java.lang.String).toLowerCase(Locale.ROOT)"
            if (r10 == 0) goto L_0x005a
            java.util.Locale r7 = java.util.Locale.ROOT     // Catch:{ all -> 0x0042 }
            java.lang.String r7 = r8.toLowerCase(r7)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r6)     // Catch:{ all -> 0x0042 }
            java.lang.Object r7 = r10.get(r7)     // Catch:{ all -> 0x0042 }
            if (r7 != 0) goto L_0x0066
        L_0x005a:
            if (r10 == 0) goto L_0x0062
            java.lang.Object r8 = r10.get(r8)     // Catch:{ all -> 0x0042 }
            r7 = r8
            goto L_0x0063
        L_0x0062:
            r7 = r4
        L_0x0063:
            if (r7 != 0) goto L_0x0066
            return r2
        L_0x0066:
            int r8 = r1.hashCode()     // Catch:{ all -> 0x0042 }
            r10 = 2
            switch(r8) {
                case -1729128927: goto L_0x0329;
                case -1179774633: goto L_0x0314;
                case -1039699439: goto L_0x02ff;
                case -969266188: goto L_0x02ed;
                case -966353971: goto L_0x02d5;
                case -665609109: goto L_0x02cb;
                case -567445985: goto L_0x02b7;
                case -327990090: goto L_0x0291;
                case -159812115: goto L_0x0250;
                case -92753547: goto L_0x020f;
                case 60: goto L_0x01f3;
                case 61: goto L_0x01df;
                case 62: goto L_0x01c3;
                case 1084: goto L_0x01ad;
                case 1921: goto L_0x0191;
                case 1952: goto L_0x0187;
                case 1983: goto L_0x016b;
                case 3244: goto L_0x0161;
                case 3294: goto L_0x0157;
                case 3309: goto L_0x014d;
                case 3365: goto L_0x0143;
                case 3449: goto L_0x0139;
                case 3464: goto L_0x012f;
                case 3511: goto L_0x0125;
                case 102680: goto L_0x011b;
                case 107485: goto L_0x0111;
                case 108954: goto L_0x0107;
                case 127966736: goto L_0x00e3;
                case 127966857: goto L_0x00d9;
                case 363990325: goto L_0x00b5;
                case 1091487233: goto L_0x00ab;
                case 1918401035: goto L_0x0094;
                case 1961112862: goto L_0x0070;
                default: goto L_0x006e;
            }     // Catch:{ all -> 0x0042 }
        L_0x006e:
            goto L_0x034e
        L_0x0070:
            java.lang.String r8 = "i_starts_with"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x007a
            goto L_0x034e
        L_0x007a:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            java.util.Locale r9 = java.util.Locale.ROOT     // Catch:{ all -> 0x0042 }
            java.lang.String r8 = r8.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = r3.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)     // Catch:{ all -> 0x0042 }
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r8, r9, r2, r10, r4)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x0094:
            java.lang.String r8 = "not_contains"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x009e
            goto L_0x034e
        L_0x009e:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            boolean r8 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r8, (java.lang.CharSequence) r3, (boolean) r2, (int) r10, (java.lang.Object) r4)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x034e
        L_0x00a8:
            r2 = r5
            goto L_0x034e
        L_0x00ab:
            java.lang.String r8 = "i_is_not_any"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x0219
            goto L_0x034e
        L_0x00b5:
            java.lang.String r8 = "i_contains"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x00bf
            goto L_0x034e
        L_0x00bf:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            java.util.Locale r9 = java.util.Locale.ROOT     // Catch:{ all -> 0x0042 }
            java.lang.String r8 = r8.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = r3.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)     // Catch:{ all -> 0x0042 }
            boolean r2 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r8, (java.lang.CharSequence) r9, (boolean) r2, (int) r10, (java.lang.Object) r4)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x00d9:
            java.lang.String r8 = "i_str_in"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x025a
            goto L_0x034e
        L_0x00e3:
            java.lang.String r8 = "i_str_eq"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x00ed
            goto L_0x034e
        L_0x00ed:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            java.util.Locale r9 = java.util.Locale.ROOT     // Catch:{ all -> 0x0042 }
            java.lang.String r8 = r8.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = r3.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)     // Catch:{ all -> 0x0042 }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x0107:
            java.lang.String r8 = "neq"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01b7
            goto L_0x034e
        L_0x0111:
            java.lang.String r8 = "lte"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x019b
            goto L_0x034e
        L_0x011b:
            java.lang.String r8 = "gte"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x0175
            goto L_0x034e
        L_0x0125:
            java.lang.String r8 = "ne"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01b7
            goto L_0x034e
        L_0x012f:
            java.lang.String r8 = "lt"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01fd
            goto L_0x034e
        L_0x0139:
            java.lang.String r8 = "le"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x019b
            goto L_0x034e
        L_0x0143:
            java.lang.String r8 = "in"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x031d
            goto L_0x034e
        L_0x014d:
            java.lang.String r8 = "gt"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01cd
            goto L_0x034e
        L_0x0157:
            java.lang.String r8 = "ge"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x0175
            goto L_0x034e
        L_0x0161:
            java.lang.String r8 = "eq"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01e9
            goto L_0x034e
        L_0x016b:
            java.lang.String r8 = ">="
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x0175
            goto L_0x034e
        L_0x0175:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            double r8 = java.lang.Double.parseDouble(r8)     // Catch:{ all -> 0x0042 }
            double r0 = java.lang.Double.parseDouble(r3)     // Catch:{ all -> 0x0042 }
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 < 0) goto L_0x034e
            goto L_0x00a8
        L_0x0187:
            java.lang.String r8 = "=="
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01e9
            goto L_0x034e
        L_0x0191:
            java.lang.String r8 = "<="
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x019b
            goto L_0x034e
        L_0x019b:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            double r8 = java.lang.Double.parseDouble(r8)     // Catch:{ all -> 0x0042 }
            double r0 = java.lang.Double.parseDouble(r3)     // Catch:{ all -> 0x0042 }
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 > 0) goto L_0x034e
            goto L_0x00a8
        L_0x01ad:
            java.lang.String r8 = "!="
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01b7
            goto L_0x034e
        L_0x01b7:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r3)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x034e
            goto L_0x00a8
        L_0x01c3:
            java.lang.String r8 = ">"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01cd
            goto L_0x034e
        L_0x01cd:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            double r8 = java.lang.Double.parseDouble(r8)     // Catch:{ all -> 0x0042 }
            double r0 = java.lang.Double.parseDouble(r3)     // Catch:{ all -> 0x0042 }
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 <= 0) goto L_0x034e
            goto L_0x00a8
        L_0x01df:
            java.lang.String r8 = "="
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01e9
            goto L_0x034e
        L_0x01e9:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r3)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x01f3:
            java.lang.String r8 = "<"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x01fd
            goto L_0x034e
        L_0x01fd:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            double r8 = java.lang.Double.parseDouble(r8)     // Catch:{ all -> 0x0042 }
            double r0 = java.lang.Double.parseDouble(r3)     // Catch:{ all -> 0x0042 }
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 >= 0) goto L_0x034e
            goto L_0x00a8
        L_0x020f:
            java.lang.String r8 = "i_str_not_in"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x0219
            goto L_0x034e
        L_0x0219:
            if (r9 != 0) goto L_0x021c
            return r2
        L_0x021c:
            boolean r8 = r9.isEmpty()     // Catch:{ all -> 0x0042 }
            if (r8 == 0) goto L_0x0224
            goto L_0x00a8
        L_0x0224:
            java.util.Iterator r8 = r9.iterator()     // Catch:{ all -> 0x0042 }
        L_0x0228:
            boolean r9 = r8.hasNext()     // Catch:{ all -> 0x0042 }
            if (r9 == 0) goto L_0x00a8
            java.lang.Object r9 = r8.next()     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0042 }
            java.util.Locale r10 = java.util.Locale.ROOT     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = r9.toLowerCase(r10)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x0042 }
            java.lang.String r10 = r1.toLowerCase(r10)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r6)     // Catch:{ all -> 0x0042 }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x0042 }
            if (r9 == 0) goto L_0x0228
            goto L_0x034e
        L_0x0250:
            java.lang.String r8 = "i_is_any"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x025a
            goto L_0x034e
        L_0x025a:
            if (r9 != 0) goto L_0x025d
            return r2
        L_0x025d:
            boolean r8 = r9.isEmpty()     // Catch:{ all -> 0x0042 }
            if (r8 == 0) goto L_0x0265
            goto L_0x034e
        L_0x0265:
            java.util.Iterator r8 = r9.iterator()     // Catch:{ all -> 0x0042 }
        L_0x0269:
            boolean r9 = r8.hasNext()     // Catch:{ all -> 0x0042 }
            if (r9 == 0) goto L_0x034e
            java.lang.Object r9 = r8.next()     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0042 }
            java.util.Locale r10 = java.util.Locale.ROOT     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = r9.toLowerCase(r10)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x0042 }
            java.lang.String r10 = r1.toLowerCase(r10)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r6)     // Catch:{ all -> 0x0042 }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x0042 }
            if (r9 == 0) goto L_0x0269
            goto L_0x00a8
        L_0x0291:
            java.lang.String r8 = "i_str_neq"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x029b
            goto L_0x034e
        L_0x029b:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            java.util.Locale r9 = java.util.Locale.ROOT     // Catch:{ all -> 0x0042 }
            java.lang.String r8 = r8.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = r3.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)     // Catch:{ all -> 0x0042 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x034e
            goto L_0x00a8
        L_0x02b7:
            java.lang.String r8 = "contains"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x02c1
            goto L_0x034e
        L_0x02c1:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            boolean r2 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r8, (java.lang.CharSequence) r3, (boolean) r2, (int) r10, (java.lang.Object) r4)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x02cb:
            java.lang.String r8 = "is_not_any"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x0308
            goto L_0x034e
        L_0x02d5:
            java.lang.String r8 = "regex_match"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x02df
            goto L_0x034e
        L_0x02df:
            kotlin.text.Regex r8 = new kotlin.text.Regex     // Catch:{ all -> 0x0042 }
            r8.<init>((java.lang.String) r3)     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = r7.toString()     // Catch:{ all -> 0x0042 }
            boolean r2 = r8.matches(r9)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x02ed:
            java.lang.String r8 = "starts_with"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x02f6
            goto L_0x034e
        L_0x02f6:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r8, r3, r2, r10, r4)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x02ff:
            java.lang.String r8 = "not_in"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x0308
            goto L_0x034e
        L_0x0308:
            if (r9 != 0) goto L_0x030b
            return r2
        L_0x030b:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            boolean r2 = r9.contains(r8)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x0314:
            java.lang.String r8 = "is_any"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x031d
            goto L_0x034e
        L_0x031d:
            if (r9 != 0) goto L_0x0320
            return r2
        L_0x0320:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            boolean r2 = r9.contains(r8)     // Catch:{ all -> 0x0042 }
            goto L_0x034e
        L_0x0329:
            java.lang.String r8 = "i_not_contains"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x0332
            goto L_0x034e
        L_0x0332:
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0042 }
            java.util.Locale r9 = java.util.Locale.ROOT     // Catch:{ all -> 0x0042 }
            java.lang.String r8 = r8.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r9 = r3.toLowerCase(r9)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)     // Catch:{ all -> 0x0042 }
            boolean r8 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r8, (java.lang.CharSequence) r9, (boolean) r2, (int) r10, (java.lang.Object) r4)     // Catch:{ all -> 0x0042 }
            if (r8 != 0) goto L_0x034e
            goto L_0x00a8
        L_0x034e:
            return r2
        L_0x034f:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r8, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.integrity.MACARuleMatchingManager.stringComparison(java.lang.String, org.json.JSONObject, android.os.Bundle):boolean");
    }

    public static final ArrayList getStringArrayList(JSONArray jSONArray) {
        Class<MACARuleMatchingManager> cls = MACARuleMatchingManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls) || jSONArray == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArray.get(i).toString());
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final boolean isMatchCCRule(String str, Bundle bundle) {
        Class<MACARuleMatchingManager> cls = MACARuleMatchingManager.class;
        if (!(CrashShieldHandler.isObjectCrashing(cls) || str == null || bundle == null)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String key = getKey(jSONObject);
                if (key == null) {
                    return false;
                }
                Object obj = jSONObject.get(key);
                int hashCode = key.hashCode();
                if (hashCode != 3555) {
                    if (hashCode != 96727) {
                        if (hashCode == 109267) {
                            if (key.equals("not")) {
                                return !isMatchCCRule(obj.toString(), bundle);
                            }
                        }
                    } else if (key.equals("and")) {
                        JSONArray jSONArray = (JSONArray) obj;
                        if (jSONArray == null) {
                            return false;
                        }
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            if (!isMatchCCRule(jSONArray.get(i).toString(), bundle)) {
                                return false;
                            }
                        }
                        return true;
                    }
                } else if (key.equals("or")) {
                    JSONArray jSONArray2 = (JSONArray) obj;
                    if (jSONArray2 == null) {
                        return false;
                    }
                    int length2 = jSONArray2.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        if (isMatchCCRule(jSONArray2.get(i2).toString(), bundle)) {
                            return true;
                        }
                    }
                    return false;
                }
                JSONObject jSONObject2 = (JSONObject) obj;
                if (jSONObject2 == null) {
                    return false;
                }
                return stringComparison(key, jSONObject2, bundle);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
        return false;
    }

    public static final String getMatchPropertyIDs(Bundle bundle) {
        Class<MACARuleMatchingManager> cls = MACARuleMatchingManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            JSONArray jSONArray = MACARules;
            if (jSONArray == null) {
                return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            }
            if (jSONArray != null && jSONArray.length() == 0) {
                return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            }
            JSONArray jSONArray2 = MACARules;
            Intrinsics.checkNotNull(jSONArray2, "null cannot be cast to non-null type org.json.JSONArray");
            ArrayList arrayList = new ArrayList();
            int length = jSONArray2.length();
            for (int i = 0; i < length; i++) {
                String optString = jSONArray2.optString(i);
                if (optString != null) {
                    JSONObject jSONObject = new JSONObject(optString);
                    long optLong = jSONObject.optLong("id");
                    if (optLong != 0) {
                        String optString2 = jSONObject.optString("rule");
                        if (optString2 != null) {
                            if (isMatchCCRule(optString2, bundle)) {
                                arrayList.add(Long.valueOf(optLong));
                            }
                        }
                    }
                }
            }
            String jSONArray3 = new JSONArray(arrayList).toString();
            Intrinsics.checkNotNullExpressionValue(jSONArray3, "JSONArray(res).toString()");
            return jSONArray3;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final void processParameters(Bundle bundle, String str) {
        Class<MACARuleMatchingManager> cls = MACARuleMatchingManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "event");
                if (enabled && bundle != null) {
                    try {
                        generateInfo(bundle, str);
                        bundle.putString("_audiencePropertyIds", getMatchPropertyIDs(bundle));
                        bundle.putString("cs_maca", "1");
                        removeGeneratedInfo(bundle);
                    } catch (Exception unused) {
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void generateInfo(Bundle bundle, String str) {
        Class<MACARuleMatchingManager> cls = MACARuleMatchingManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(bundle, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
                Intrinsics.checkNotNullParameter(str, "event");
                bundle.putString("event", str);
                StringBuilder sb = new StringBuilder();
                Utility utility = Utility.INSTANCE;
                Locale locale = utility.getLocale();
                String str2 = null;
                String language = locale != null ? locale.getLanguage() : null;
                String str3 = "";
                if (language == null) {
                    language = str3;
                }
                sb.append(language);
                sb.append('_');
                Locale locale2 = utility.getLocale();
                if (locale2 != null) {
                    str2 = locale2.getCountry();
                }
                if (str2 == null) {
                    str2 = str3;
                }
                sb.append(str2);
                bundle.putString("_locale", sb.toString());
                String versionName = utility.getVersionName();
                if (versionName == null) {
                    versionName = str3;
                }
                bundle.putString("_appVersion", versionName);
                bundle.putString("_deviceOS", "ANDROID");
                bundle.putString("_platform", "mobile");
                String str4 = Build.MODEL;
                if (str4 == null) {
                    str4 = str3;
                }
                bundle.putString("_deviceModel", str4);
                bundle.putString("_nativeAppID", FacebookSdk.getApplicationId());
                String versionName2 = utility.getVersionName();
                if (versionName2 != null) {
                    str3 = versionName2;
                }
                bundle.putString("_nativeAppShortVersion", str3);
                bundle.putString("_timezone", utility.getDeviceTimeZoneName());
                bundle.putString("_carrier", utility.getCarrierName());
                bundle.putString("_deviceOSTypeName", "ANDROID");
                bundle.putString("_deviceOSVersion", Build.VERSION.RELEASE);
                bundle.putLong("_remainingDiskGB", utility.getAvailableExternalStorageGB());
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void removeGeneratedInfo(Bundle bundle) {
        Class<MACARuleMatchingManager> cls = MACARuleMatchingManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(bundle, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
                for (String remove : keys) {
                    bundle.remove(remove);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
