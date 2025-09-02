package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class zzoq {
    private final Map zza;

    zzoq(Map map) {
        HashMap hashMap = new HashMap();
        this.zza = hashMap;
        hashMap.putAll(map);
    }

    private final int zzf() {
        try {
            String str = (String) this.zza.get("PolicyVersion");
            if (!TextUtils.isEmpty(str)) {
                return Integer.parseInt(str);
            }
            return -1;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private final Bundle zzg() {
        int zzf;
        String str;
        String str2;
        Map map = this.zza;
        if ("1".equals(map.get("GoogleConsent")) && (zzf = zzf()) >= 0) {
            String str3 = (String) map.get("PurposeConsents");
            if (!TextUtils.isEmpty(str3)) {
                Bundle bundle = new Bundle();
                String str4 = "denied";
                if (str3.length() > 0) {
                    String str5 = zzjw.AD_STORAGE.zze;
                    if (str3.charAt(0) == '1') {
                        str2 = "granted";
                    } else {
                        str2 = str4;
                    }
                    bundle.putString(str5, str2);
                }
                if (str3.length() > 3) {
                    String str6 = zzjw.AD_PERSONALIZATION.zze;
                    if (str3.charAt(2) == '1' && str3.charAt(3) == '1') {
                        str = "granted";
                    } else {
                        str = str4;
                    }
                    bundle.putString(str6, str);
                }
                if (str3.length() > 6 && zzf >= 4) {
                    String str7 = zzjw.AD_USER_DATA.zze;
                    if (str3.charAt(0) == '1' && str3.charAt(6) == '1') {
                        str4 = "granted";
                    }
                    bundle.putString(str7, str4);
                }
                return bundle;
            }
        }
        return Bundle.EMPTY;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzoq)) {
            return false;
        }
        return zze().equalsIgnoreCase(((zzoq) obj).zze());
    }

    public final int hashCode() {
        return zze().hashCode();
    }

    public final String toString() {
        return zze();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004c, code lost:
        if ("1".equals(r2.get("EnableAdvertiserConsentMode")) != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0029, code lost:
        if ("1".equals(r2.get("EnableAdvertiserConsentMode")) != false) goto L_0x004e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zza() {
        /*
            r9 = this;
            com.google.android.gms.measurement.internal.zzgg r0 = com.google.android.gms.measurement.internal.zzgi.zzbj
            r1 = 0
            java.lang.Object r2 = r0.zza(r1)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            java.lang.String r3 = "EnableAdvertiserConsentMode"
            java.lang.String r4 = "gdprApplies"
            java.lang.String r5 = "1"
            if (r2 == 0) goto L_0x002c
            java.util.Map r2 = r9.zza
            java.lang.Object r4 = r2.get(r4)
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x00df
            java.lang.Object r2 = r2.get(r3)
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x00df
            goto L_0x004e
        L_0x002c:
            java.util.Map r2 = r9.zza
            java.lang.String r6 = "GoogleConsent"
            java.lang.Object r6 = r2.get(r6)
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x00df
            java.lang.Object r4 = r2.get(r4)
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x00df
            java.lang.Object r2 = r2.get(r3)
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x00df
        L_0x004e:
            java.lang.Object r0 = r0.zza(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x00da
            java.util.Map r0 = r9.zza
            java.lang.String r1 = "Version"
            java.lang.Object r1 = r0.get(r1)
            if (r1 != 0) goto L_0x0069
            android.os.Bundle r0 = r9.zzg()
            return r0
        L_0x0069:
            int r1 = r9.zzf()
            if (r1 >= 0) goto L_0x0071
            goto L_0x00df
        L_0x0071:
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            com.google.android.gms.measurement.internal.zzjw r2 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE
            java.lang.String r2 = r2.zze
            java.lang.String r3 = "AuthorizePurpose1"
            java.lang.Object r4 = r0.get(r3)
            boolean r4 = java.util.Objects.equals(r4, r5)
            java.lang.String r6 = "granted"
            java.lang.String r7 = "denied"
            r8 = 1
            if (r8 == r4) goto L_0x008d
            r4 = r7
            goto L_0x008e
        L_0x008d:
            r4 = r6
        L_0x008e:
            r1.putString(r2, r4)
            com.google.android.gms.measurement.internal.zzjw r2 = com.google.android.gms.measurement.internal.zzjw.AD_PERSONALIZATION
            java.lang.String r2 = r2.zze
            java.lang.String r4 = "AuthorizePurpose3"
            java.lang.Object r4 = r0.get(r4)
            boolean r4 = java.util.Objects.equals(r4, r5)
            if (r4 == 0) goto L_0x00af
            java.lang.String r4 = "AuthorizePurpose4"
            java.lang.Object r4 = r0.get(r4)
            boolean r4 = java.util.Objects.equals(r4, r5)
            if (r4 == 0) goto L_0x00af
            r4 = r6
            goto L_0x00b0
        L_0x00af:
            r4 = r7
        L_0x00b0:
            r1.putString(r2, r4)
            int r2 = r9.zzf()
            r4 = 4
            if (r2 < r4) goto L_0x00d9
            com.google.android.gms.measurement.internal.zzjw r2 = com.google.android.gms.measurement.internal.zzjw.AD_USER_DATA
            java.lang.String r2 = r2.zze
            java.lang.Object r3 = r0.get(r3)
            boolean r3 = java.util.Objects.equals(r3, r5)
            if (r3 == 0) goto L_0x00d5
            java.lang.String r3 = "AuthorizePurpose7"
            java.lang.Object r0 = r0.get(r3)
            boolean r0 = java.util.Objects.equals(r0, r5)
            if (r0 == 0) goto L_0x00d5
            goto L_0x00d6
        L_0x00d5:
            r6 = r7
        L_0x00d6:
            r1.putString(r2, r6)
        L_0x00d9:
            return r1
        L_0x00da:
            android.os.Bundle r0 = r9.zzg()
            return r0
        L_0x00df:
            android.os.Bundle r0 = android.os.Bundle.EMPTY
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzoq.zza():android.os.Bundle");
    }

    public final String zzb() {
        String str = (String) this.zza.get("PurposeDiagnostics");
        return TextUtils.isEmpty(str) ? "200000" : str;
    }

    public final String zzc(zzoq zzoq) {
        String str;
        Map map = zzoq.zza;
        String str2 = "0";
        if (map.isEmpty() || ((String) map.get("Version")) != null) {
            str = str2;
        } else {
            str = "1";
        }
        Bundle zza2 = zza();
        Bundle zza3 = zzoq.zza();
        if (zza2.size() != zza3.size() || !Objects.equals(zza2.getString("ad_storage"), zza3.getString("ad_storage")) || !Objects.equals(zza2.getString("ad_personalization"), zza3.getString("ad_personalization")) || !Objects.equals(zza2.getString("ad_user_data"), zza3.getString("ad_user_data"))) {
            str2 = "1";
        }
        return str.concat(str2);
    }

    public final String zzd() {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        int i = -1;
        try {
            String str = (String) this.zza.get("CmpSdkID");
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (NumberFormatException unused) {
        }
        if (i < 0 || i > 4095) {
            sb.append("00");
        } else {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i >> 6));
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i & 63));
        }
        int zzf = zzf();
        if (zzf < 0 || zzf > 63) {
            sb.append("0");
        } else {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(zzf));
        }
        Preconditions.checkArgument(true);
        Map map = this.zza;
        int i2 = true != "1".equals(map.get("gdprApplies")) ? 0 : 2;
        boolean equals = "1".equals(map.get("EnableAdvertiserConsentMode"));
        int i3 = i2 | 4;
        if (equals) {
            i3 = i2 | 12;
        }
        sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i3));
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zze() {
        StringBuilder sb = new StringBuilder();
        ImmutableList immutableList = zzot.zza;
        int size = immutableList.size();
        for (int i = 0; i < size; i++) {
            String str = (String) immutableList.get(i);
            Map map = this.zza;
            if (map.containsKey(str)) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(str);
                sb.append("=");
                sb.append((String) map.get(str));
            }
        }
        return sb.toString();
    }
}
