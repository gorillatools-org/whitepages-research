package com.android.billingclient.api;

import android.content.Context;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzga;
import com.google.android.gms.internal.play_billing.zzge;
import com.google.android.gms.internal.play_billing.zzgt;
import com.google.android.gms.internal.play_billing.zzgu;
import com.google.android.gms.internal.play_billing.zzhd;
import com.google.android.gms.internal.play_billing.zzhe;
import com.google.android.gms.internal.play_billing.zzhl;

final class zzch implements zzcc {
    private zzgu zzb;
    private final zzcj zzc;

    zzch(Context context, zzgu zzgu) {
        this.zzc = new zzcj(context);
        this.zzb = zzgu;
    }

    public final void zza(zzga zzga) {
        if (zzga != null) {
            try {
                zzhd zzy = zzhe.zzy();
                zzy.zzn(this.zzb);
                zzy.zzl(zzga);
                this.zzc.zza((zzhe) zzy.zzf());
            } catch (Throwable th) {
                zzb.zzl("BillingLogger", "Unable to log.", th);
            }
        }
    }

    public final void zzb(zzga zzga, int i) {
        try {
            zzgt zzgt = (zzgt) this.zzb.zzi();
            zzgt.zzl(i);
            this.zzb = (zzgu) zzgt.zzf();
            zza(zzga);
        } catch (Throwable th) {
            zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }

    public final void zzc(zzge zzge) {
        if (zzge != null) {
            try {
                zzhd zzy = zzhe.zzy();
                zzy.zzn(this.zzb);
                zzy.zzm(zzge);
                this.zzc.zza((zzhe) zzy.zzf());
            } catch (Throwable th) {
                zzb.zzl("BillingLogger", "Unable to log.", th);
            }
        }
    }

    public final void zzd(zzge zzge, int i) {
        try {
            zzgt zzgt = (zzgt) this.zzb.zzi();
            zzgt.zzl(i);
            this.zzb = (zzgu) zzgt.zzf();
            zzc(zzge);
        } catch (Throwable th) {
            zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }

    public final void zze(zzhl zzhl) {
        if (zzhl != null) {
            try {
                zzhd zzy = zzhe.zzy();
                zzy.zzn(this.zzb);
                zzy.zzo(zzhl);
                this.zzc.zza((zzhe) zzy.zzf());
            } catch (Throwable th) {
                zzb.zzl("BillingLogger", "Unable to log.", th);
            }
        }
    }
}
