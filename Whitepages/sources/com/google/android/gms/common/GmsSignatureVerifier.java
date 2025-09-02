package com.google.android.gms.common;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.common.zzak;
import java.util.HashMap;

@ShowFirstParty
@KeepForSdk
public class GmsSignatureVerifier {
    private static final zzaa zza;
    private static final zzaa zzb;
    private static final HashMap zzc = new HashMap();

    static {
        zzy zzy = new zzy();
        zzy.zzd("com.google.android.gms");
        zzy.zza(204200000);
        zzl zzl = zzn.zzd;
        zzy.zzc(zzak.zzn(zzl.zzf(), zzn.zzb.zzf()));
        zzl zzl2 = zzn.zzc;
        zzy.zzb(zzak.zzn(zzl2.zzf(), zzn.zza.zzf()));
        zza = zzy.zze();
        zzy zzy2 = new zzy();
        zzy2.zzd("com.android.vending");
        zzy2.zza(82240000);
        zzy2.zzc(zzak.zzm(zzl.zzf()));
        zzy2.zzb(zzak.zzm(zzl2.zzf()));
        zzb = zzy2.zze();
    }
}
