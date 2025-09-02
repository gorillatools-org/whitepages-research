package com.google.android.gms.internal.p000authapiphone;

import com.google.android.gms.common.Feature;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzac  reason: invalid package */
public final class zzac {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature zzc;
    public static final Feature zzd;
    public static final Feature[] zze;

    static {
        Feature feature = new Feature("sms_code_autofill", 2);
        zza = feature;
        Feature feature2 = new Feature("sms_code_browser", 2);
        zzb = feature2;
        Feature feature3 = new Feature("sms_retrieve", 1);
        zzc = feature3;
        Feature feature4 = new Feature("user_consent", 3);
        zzd = feature4;
        zze = new Feature[]{feature, feature2, feature3, feature4};
    }
}
