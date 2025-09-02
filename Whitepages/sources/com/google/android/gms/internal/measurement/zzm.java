package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzm extends zzai {
    final /* synthetic */ zzo zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzm(zzn zzn, String str, zzo zzo) {
        super("getValue");
        this.zza = zzo;
    }

    public final zzap zza(zzg zzg, List list) {
        zzh.zzh("getValue", 2, list);
        zzap zzb = zzg.zzb((zzap) list.get(0));
        zzap zzb2 = zzg.zzb((zzap) list.get(1));
        String zza2 = this.zza.zza(zzb.zzi());
        return zza2 != null ? new zzat(zza2) : zzb2;
    }
}
