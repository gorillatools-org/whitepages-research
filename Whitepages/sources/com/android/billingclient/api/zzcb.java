package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzal;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzfz;
import com.google.android.gms.internal.play_billing.zzga;
import com.google.android.gms.internal.play_billing.zzgd;
import com.google.android.gms.internal.play_billing.zzge;
import com.google.android.gms.internal.play_billing.zzgg;
import com.google.android.gms.internal.play_billing.zzgk;

public abstract /* synthetic */ class zzcb {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        zzal zzal = zzcc.zza;
    }

    public static zzga zza(int i, int i2, BillingResult billingResult) {
        try {
            zzfz zzy = zzga.zzy();
            zzgg zzy2 = zzgk.zzy();
            zzy2.zzn(billingResult.getResponseCode());
            zzy2.zzm(billingResult.getDebugMessage());
            zzy2.zzo(i);
            zzy.zzl(zzy2);
            zzy.zzn(i2);
            return (zzga) zzy.zzf();
        } catch (Exception e) {
            zzb.zzl("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }

    public static zzga zzb(int i, int i2, BillingResult billingResult, String str) {
        try {
            zzgg zzy = zzgk.zzy();
            zzy.zzn(billingResult.getResponseCode());
            zzy.zzm(billingResult.getDebugMessage());
            zzy.zzo(i);
            if (str != null) {
                zzy.zzl(str);
            }
            zzfz zzy2 = zzga.zzy();
            zzy2.zzl(zzy);
            zzy2.zzn(i2);
            return (zzga) zzy2.zzf();
        } catch (Exception e) {
            zzb.zzl("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }

    public static zzge zzc(int i) {
        try {
            zzgd zzy = zzge.zzy();
            zzy.zzm(i);
            return (zzge) zzy.zzf();
        } catch (Exception e) {
            zzb.zzl("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }
}
