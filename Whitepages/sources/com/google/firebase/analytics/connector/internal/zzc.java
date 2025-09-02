package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzka;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.notifications.NotificationMessage;

public final class zzc {
    public static final /* synthetic */ int zza = 0;
    private static final ImmutableSet zzb = ImmutableSet.of("_in", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", FirebaseAnalytics.Event.CAMPAIGN_DETAILS, "_ug", "_iapx", "_exp_set", "_exp_clear", "_exp_activate", "_exp_timeout", "_exp_expire");
    private static final ImmutableList zzc = ImmutableList.of("_e", "_f", "_iap", "_s", "_au", "_ui", "_cd");
    private static final ImmutableList zzd = ImmutableList.of("auto", "app", "am");
    private static final ImmutableList zze = ImmutableList.of(NotificationMessage.NOTIF_KEY_REQUEST_ID, "_dbg");
    private static final ImmutableList zzf = new ImmutableList.Builder().add(zzka.zza).add(zzka.zzb).build();
    private static final ImmutableList zzg = ImmutableList.of("^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$");

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zza(java.lang.String r5, java.lang.String r6, android.os.Bundle r7) {
        /*
            java.lang.String r0 = "_cmp"
            boolean r6 = r0.equals(r6)
            r0 = 1
            if (r6 != 0) goto L_0x000a
            return r0
        L_0x000a:
            boolean r6 = zzd(r5)
            r1 = 0
            if (r6 != 0) goto L_0x0012
            return r1
        L_0x0012:
            if (r7 != 0) goto L_0x0015
            return r1
        L_0x0015:
            com.google.common.collect.ImmutableList r6 = zze
            int r2 = r6.size()
            r3 = r1
        L_0x001c:
            if (r3 >= r2) goto L_0x002d
            java.lang.Object r4 = r6.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            boolean r4 = r7.containsKey(r4)
            int r3 = r3 + 1
            if (r4 == 0) goto L_0x001c
            return r1
        L_0x002d:
            int r6 = r5.hashCode()
            r2 = 101200(0x18b50, float:1.41811E-40)
            r3 = 2
            if (r6 == r2) goto L_0x0056
            r2 = 101230(0x18b6e, float:1.41853E-40)
            if (r6 == r2) goto L_0x004c
            r2 = 3142703(0x2ff42f, float:4.403865E-39)
            if (r6 == r2) goto L_0x0042
            goto L_0x0060
        L_0x0042:
            java.lang.String r6 = "fiam"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0060
            r5 = r3
            goto L_0x0061
        L_0x004c:
            java.lang.String r6 = "fdl"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0060
            r5 = r0
            goto L_0x0061
        L_0x0056:
            java.lang.String r6 = "fcm"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0060
            r5 = r1
            goto L_0x0061
        L_0x0060:
            r5 = -1
        L_0x0061:
            java.lang.String r6 = "_cis"
            if (r5 == 0) goto L_0x0076
            if (r5 == r0) goto L_0x0070
            if (r5 == r3) goto L_0x006a
            return r1
        L_0x006a:
            java.lang.String r5 = "fiam_integration"
            r7.putString(r6, r5)
            return r0
        L_0x0070:
            java.lang.String r5 = "fdl_integration"
            r7.putString(r6, r5)
            return r0
        L_0x0076:
            java.lang.String r5 = "fcm_integration"
            r7.putString(r6, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.analytics.connector.internal.zzc.zza(java.lang.String, java.lang.String, android.os.Bundle):boolean");
    }

    public static boolean zzb(String str, Bundle bundle) {
        if (zzc.contains(str)) {
            return false;
        }
        if (bundle == null) {
            return true;
        }
        ImmutableList immutableList = zze;
        int size = immutableList.size();
        int i = 0;
        while (i < size) {
            boolean containsKey = bundle.containsKey((String) immutableList.get(i));
            i++;
            if (containsKey) {
                return false;
            }
        }
        return true;
    }

    public static boolean zzc(String str) {
        return !zzb.contains(str);
    }

    public static boolean zzd(String str) {
        return !zzd.contains(str);
    }

    public static boolean zze(String str, String str2) {
        if ("_ce1".equals(str2) || "_ce2".equals(str2)) {
            return str.equals("fcm") || str.equals(FirebaseABTesting.OriginService.REMOTE_CONFIG);
        }
        if (Constants.ScionAnalytics.USER_PROPERTY_FIREBASE_LAST_NOTIFICATION.equals(str2)) {
            return str.equals("fcm") || str.equals("fiam");
        }
        if (zzf.contains(str2)) {
            return false;
        }
        ImmutableList immutableList = zzg;
        int size = immutableList.size();
        int i = 0;
        while (i < size) {
            boolean matches = str2.matches((String) immutableList.get(i));
            i++;
            if (matches) {
                return false;
            }
        }
        return true;
    }
}
