package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzkl;
import com.google.android.gms.internal.measurement.zzkm;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;

public final class zzot {
    static final ImmutableList zza = ImmutableList.of("Version", "GoogleConsent", "VendorConsent", "VendorLegitimateInterest", "gdprApplies", "EnableAdvertiserConsentMode", "PolicyVersion", "PurposeConsents", "PurposeOneTreatment", "Purpose1", "Purpose3", "Purpose4", "Purpose7", "CmpSdkID", "PublisherCC", "PublisherRestrictions1", "PublisherRestrictions3", "PublisherRestrictions4", "PublisherRestrictions7", "AuthorizePurpose1", "AuthorizePurpose3", "AuthorizePurpose4", "AuthorizePurpose7", "PurposeDiagnostics");
    public static final /* synthetic */ int zzb = 0;

    static int zza(SharedPreferences sharedPreferences, String str) {
        try {
            return sharedPreferences.getInt(str, -1);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    static String zzb(SharedPreferences sharedPreferences, String str) {
        try {
            return sharedPreferences.getString(str, "");
        } catch (ClassCastException unused) {
            return "";
        }
    }

    public static final Map zzc(ImmutableMap immutableMap, ImmutableMap immutableMap2, ImmutableSet immutableSet, char[] cArr, int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, boolean z, boolean z2) {
        String str4;
        String str5;
        String str6;
        String str7;
        int i6;
        int i7;
        int i8;
        int i9;
        String str8;
        String str9;
        String str10;
        ImmutableMap immutableMap3 = immutableMap2;
        zzkl zzkl = zzkl.IAB_TCF_PURPOSE_STORE_AND_ACCESS_INFORMATION_ON_A_DEVICE;
        zzkm zzkm = (zzkm) immutableMap3.get(zzkl);
        zzkl zzkl2 = zzkl.IAB_TCF_PURPOSE_CREATE_A_PERSONALISED_ADS_PROFILE;
        zzkm zzkm2 = (zzkm) immutableMap3.get(zzkl2);
        zzkl zzkl3 = zzkl.IAB_TCF_PURPOSE_SELECT_PERSONALISED_ADS;
        zzkm zzkm3 = (zzkm) immutableMap3.get(zzkl3);
        zzkl zzkl4 = zzkl.IAB_TCF_PURPOSE_MEASURE_AD_PERFORMANCE;
        zzkm zzkm4 = (zzkm) immutableMap3.get(zzkl4);
        String str11 = "1";
        ImmutableMap.Builder put = ImmutableMap.builder().put("Version", "2").put("VendorConsent", true != z ? "0" : str11);
        if (true != z2) {
            str4 = "0";
        } else {
            str4 = str11;
        }
        ImmutableMap.Builder put2 = put.put("VendorLegitimateInterest", str4);
        if (i3 != 1) {
            str5 = "0";
        } else {
            str5 = str11;
        }
        ImmutableMap.Builder put3 = put2.put("gdprApplies", str5);
        if (i2 != 1) {
            str6 = "0";
        } else {
            str6 = str11;
        }
        ImmutableMap.Builder put4 = put3.put("EnableAdvertiserConsentMode", str6).put("PolicyVersion", String.valueOf(i4)).put("CmpSdkID", String.valueOf(i));
        if (i5 != 1) {
            str7 = "0";
        } else {
            str7 = str11;
        }
        ImmutableMap.Builder put5 = put4.put("PurposeOneTreatment", str7).put("PublisherCC", str);
        if (zzkm != null) {
            i6 = zzkm.zza();
        } else {
            i6 = zzkm.PURPOSE_RESTRICTION_UNDEFINED.zza();
        }
        ImmutableMap.Builder put6 = put5.put("PublisherRestrictions1", String.valueOf(i6));
        if (zzkm2 != null) {
            i7 = zzkm2.zza();
        } else {
            i7 = zzkm.PURPOSE_RESTRICTION_UNDEFINED.zza();
        }
        ImmutableMap.Builder put7 = put6.put("PublisherRestrictions3", String.valueOf(i7));
        if (zzkm3 != null) {
            i8 = zzkm3.zza();
        } else {
            i8 = zzkm.PURPOSE_RESTRICTION_UNDEFINED.zza();
        }
        ImmutableMap.Builder put8 = put7.put("PublisherRestrictions4", String.valueOf(i8));
        if (zzkm4 != null) {
            i9 = zzkm4.zza();
        } else {
            i9 = zzkm.PURPOSE_RESTRICTION_UNDEFINED.zza();
        }
        ImmutableMap.Builder put9 = put8.put("PublisherRestrictions7", String.valueOf(i9));
        zzkl zzkl5 = zzkl;
        ImmutableMap immutableMap4 = immutableMap;
        ImmutableMap immutableMap5 = immutableMap2;
        ImmutableSet immutableSet2 = immutableSet;
        char[] cArr2 = cArr;
        ImmutableMap.Builder builder = put9;
        int i10 = i;
        int i11 = i2;
        int i12 = i3;
        int i13 = i4;
        int i14 = i5;
        String str12 = str;
        zzkl zzkl6 = zzkl4;
        String str13 = str2;
        zzkl zzkl7 = zzkl3;
        String str14 = str3;
        zzkl zzkl8 = zzkl2;
        boolean z3 = z;
        zzkl zzkl9 = zzkl;
        boolean z4 = z2;
        ImmutableMap.Builder putAll = builder.putAll((Map) ImmutableMap.of("Purpose1", zzg(zzkl5, immutableMap4, immutableMap5, immutableSet2, cArr2, i10, i11, i12, i13, i14, str12, str13, str14, z3, z4), "Purpose3", zzg(zzkl8, immutableMap4, immutableMap5, immutableSet2, cArr2, i10, i11, i12, i13, i14, str12, str13, str14, z3, z4), "Purpose4", zzg(zzkl7, immutableMap4, immutableMap5, immutableSet2, cArr2, i10, i11, i12, i13, i14, str12, str13, str14, z3, z4), "Purpose7", zzg(zzkl6, immutableMap4, immutableMap5, immutableSet2, cArr2, i10, i11, i12, i13, i14, str12, str13, str14, z3, z4)));
        if (true != zzd(zzkl9, immutableMap4, immutableMap5, immutableSet2, cArr2, i10, i11, i12, i13, i14, str12, str13, str14, z3, z4)) {
            str8 = "0";
        } else {
            str8 = str11;
        }
        ImmutableMap.Builder builder2 = putAll;
        if (true != zzd(zzkl8, immutableMap, immutableMap2, immutableSet, cArr, i, i2, i3, i4, i5, str, str2, str3, z, z2)) {
            str9 = "0";
        } else {
            str9 = str11;
        }
        if (true != zzd(zzkl7, immutableMap, immutableMap2, immutableSet, cArr, i, i2, i3, i4, i5, str, str2, str3, z, z2)) {
            str10 = "0";
        } else {
            str10 = str11;
        }
        if (true != zzd(zzkl6, immutableMap, immutableMap2, immutableSet, cArr, i, i2, i3, i4, i5, str, str2, str3, z, z2)) {
            str11 = "0";
        }
        return builder2.putAll((Map) ImmutableMap.of("AuthorizePurpose1", str8, "AuthorizePurpose3", str9, "AuthorizePurpose4", str10, "AuthorizePurpose7", str11, "PurposeDiagnostics", new String(cArr))).buildOrThrow();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0204  */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final boolean zzd(com.google.android.gms.internal.measurement.zzkl r21, com.google.common.collect.ImmutableMap r22, com.google.common.collect.ImmutableMap r23, com.google.common.collect.ImmutableSet r24, char[] r25, int r26, int r27, int r28, int r29, int r30, java.lang.String r31, java.lang.String r32, java.lang.String r33, boolean r34, boolean r35) {
        /*
            r15 = r21
            int r16 = zze(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35)
            r14 = 50
            r13 = 1
            if (r16 <= 0) goto L_0x0023
            r0 = r28
            if (r0 != r13) goto L_0x001a
            r1 = r27
            if (r1 == r13) goto L_0x0015
            r0 = r13
            goto L_0x001c
        L_0x0015:
            r17 = r13
            r18 = r17
            goto L_0x0028
        L_0x001a:
            r1 = r27
        L_0x001c:
            r25[r16] = r14
        L_0x001e:
            r18 = r0
            r17 = r1
            goto L_0x0028
        L_0x0023:
            r1 = r27
            r0 = r28
            goto L_0x001e
        L_0x0028:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r30
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            com.google.android.gms.internal.measurement.zzkm r0 = zzf(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.google.android.gms.internal.measurement.zzkm r1 = com.google.android.gms.internal.measurement.zzkm.PURPOSE_RESTRICTION_NOT_ALLOWED
            if (r0 != r1) goto L_0x0054
            r0 = 51
            r15 = 50
            goto L_0x01fe
        L_0x0054:
            com.google.android.gms.internal.measurement.zzkl r0 = com.google.android.gms.internal.measurement.zzkl.IAB_TCF_PURPOSE_STORE_AND_ACCESS_INFORMATION_ON_A_DEVICE
            if (r15 != r0) goto L_0x007e
            r0 = r30
            r1 = 1
            r14 = r24
            r13 = r31
            if (r0 != r1) goto L_0x007b
            boolean r0 = r14.contains(r13)
            if (r0 == 0) goto L_0x0074
            if (r16 <= 0) goto L_0x0073
            char r0 = r25[r16]
            r12 = 50
            if (r0 == r12) goto L_0x0073
            r0 = 49
            r25[r16] = r0
        L_0x0073:
            return r1
        L_0x0074:
            r12 = 50
            r11 = r22
            r19 = r1
            goto L_0x008a
        L_0x007b:
            r12 = 50
            goto L_0x0086
        L_0x007e:
            r14 = r24
            r0 = r30
            r13 = r31
            r1 = 1
            goto L_0x007b
        L_0x0086:
            r11 = r22
            r19 = r0
        L_0x008a:
            boolean r0 = r11.containsKey(r15)
            r2 = 48
            if (r0 != 0) goto L_0x0096
        L_0x0092:
            r0 = r2
            r15 = r12
            goto L_0x01fe
        L_0x0096:
            java.lang.Object r0 = r11.get(r15)
            com.google.android.gms.measurement.internal.zzos r0 = (com.google.android.gms.measurement.internal.zzos) r0
            if (r0 != 0) goto L_0x009f
            goto L_0x0092
        L_0x009f:
            int r0 = r0.ordinal()
            r20 = 56
            if (r0 == 0) goto L_0x01d6
            if (r0 == r1) goto L_0x0188
            r1 = 2
            if (r0 == r1) goto L_0x011c
            r1 = 3
            if (r0 == r1) goto L_0x00b0
            goto L_0x0092
        L_0x00b0:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            com.google.android.gms.internal.measurement.zzkm r0 = zzf(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.google.android.gms.internal.measurement.zzkm r1 = com.google.android.gms.internal.measurement.zzkm.PURPOSE_RESTRICTION_REQUIRE_CONSENT
            if (r0 != r1) goto L_0x00f9
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            boolean r0 = zzh(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        L_0x00f9:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            boolean r0 = zzi(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        L_0x011c:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            com.google.android.gms.internal.measurement.zzkm r0 = zzf(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.google.android.gms.internal.measurement.zzkm r1 = com.google.android.gms.internal.measurement.zzkm.PURPOSE_RESTRICTION_REQUIRE_LEGITIMATE_INTEREST
            if (r0 != r1) goto L_0x0165
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            boolean r0 = zzi(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        L_0x0165:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            boolean r0 = zzh(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        L_0x0188:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r14 = r12
            r12 = r33
            r13 = r34
            r15 = r14
            r14 = r35
            com.google.android.gms.internal.measurement.zzkm r0 = zzf(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.google.android.gms.internal.measurement.zzkm r1 = com.google.android.gms.internal.measurement.zzkm.PURPOSE_RESTRICTION_REQUIRE_CONSENT
            if (r0 != r1) goto L_0x01b3
        L_0x01b0:
            r0 = r20
            goto L_0x01fe
        L_0x01b3:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            boolean r0 = zzi(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        L_0x01d6:
            r15 = r12
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            com.google.android.gms.internal.measurement.zzkm r0 = zzf(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.google.android.gms.internal.measurement.zzkm r1 = com.google.android.gms.internal.measurement.zzkm.PURPOSE_RESTRICTION_REQUIRE_LEGITIMATE_INTEREST
            if (r0 != r1) goto L_0x0208
            goto L_0x01b0
        L_0x01fe:
            if (r16 <= 0) goto L_0x0206
            char r1 = r25[r16]
            if (r1 == r15) goto L_0x0206
            r25[r16] = r0
        L_0x0206:
            r0 = 0
            return r0
        L_0x0208:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r17
            r7 = r18
            r8 = r29
            r9 = r19
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            boolean r0 = zzh(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzot.zzd(com.google.android.gms.internal.measurement.zzkl, com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableSet, char[], int, int, int, int, int, java.lang.String, java.lang.String, java.lang.String, boolean, boolean):boolean");
    }

    private static final int zze(zzkl zzkl, ImmutableMap immutableMap, ImmutableMap immutableMap2, ImmutableSet immutableSet, char[] cArr, int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, boolean z, boolean z2) {
        if (zzkl == zzkl.IAB_TCF_PURPOSE_STORE_AND_ACCESS_INFORMATION_ON_A_DEVICE) {
            return 1;
        }
        if (zzkl == zzkl.IAB_TCF_PURPOSE_CREATE_A_PERSONALISED_ADS_PROFILE) {
            return 2;
        }
        if (zzkl == zzkl.IAB_TCF_PURPOSE_SELECT_PERSONALISED_ADS) {
            return 3;
        }
        return zzkl == zzkl.IAB_TCF_PURPOSE_MEASURE_AD_PERFORMANCE ? 4 : -1;
    }

    private static final zzkm zzf(zzkl zzkl, ImmutableMap immutableMap, ImmutableMap immutableMap2, ImmutableSet immutableSet, char[] cArr, int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, boolean z, boolean z2) {
        return (zzkm) immutableMap2.getOrDefault(zzkl, zzkm.PURPOSE_RESTRICTION_UNDEFINED);
    }

    private static final String zzg(zzkl zzkl, ImmutableMap immutableMap, ImmutableMap immutableMap2, ImmutableSet immutableSet, char[] cArr, int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, boolean z, boolean z2) {
        String str4 = "0";
        String valueOf = (TextUtils.isEmpty(str2) || str2.length() < zzkl.zza()) ? str4 : String.valueOf(str2.charAt(zzkl.zza() - 1));
        if (!TextUtils.isEmpty(str3) && str3.length() >= zzkl.zza()) {
            str4 = String.valueOf(str3.charAt(zzkl.zza() - 1));
        }
        return String.valueOf(valueOf).concat(String.valueOf(str4));
    }

    private static final boolean zzh(zzkl zzkl, ImmutableMap immutableMap, ImmutableMap immutableMap2, ImmutableSet immutableSet, char[] cArr, int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, boolean z, boolean z2) {
        char c;
        int zze = zze(zzkl, immutableMap, immutableMap2, immutableSet, cArr, i, i2, i3, i4, i5, str, str2, str3, z, z2);
        boolean z3 = false;
        if (!z) {
            c = '4';
        } else if (str2.length() < zzkl.zza()) {
            c = '0';
        } else {
            char charAt = str2.charAt(zzkl.zza() - 1);
            char c2 = '1';
            if (charAt == '1') {
                z3 = true;
            }
            if (zze > 0 && cArr[zze] != '2') {
                if (charAt != '1') {
                    c2 = '6';
                }
                cArr[zze] = c2;
            }
            return z3;
        }
        if (zze > 0 && cArr[zze] != '2') {
            cArr[zze] = c;
        }
        return false;
    }

    private static final boolean zzi(zzkl zzkl, ImmutableMap immutableMap, ImmutableMap immutableMap2, ImmutableSet immutableSet, char[] cArr, int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, boolean z, boolean z2) {
        char c;
        int zze = zze(zzkl, immutableMap, immutableMap2, immutableSet, cArr, i, i2, i3, i4, i5, str, str2, str3, z, z2);
        boolean z3 = false;
        if (!z2) {
            c = '5';
        } else if (str3.length() < zzkl.zza()) {
            c = '0';
        } else {
            char charAt = str3.charAt(zzkl.zza() - 1);
            char c2 = '1';
            if (charAt == '1') {
                z3 = true;
            }
            if (zze > 0 && cArr[zze] != '2') {
                if (charAt != '1') {
                    c2 = '7';
                }
                cArr[zze] = c2;
            }
            return z3;
        }
        if (zze > 0 && cArr[zze] != '2') {
            cArr[zze] = c;
        }
        return false;
    }
}
