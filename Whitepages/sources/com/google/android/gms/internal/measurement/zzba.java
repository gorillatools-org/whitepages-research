package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Comparator;

final class zzba implements Comparator {
    final /* synthetic */ zzai zza;
    final /* synthetic */ zzg zzb;

    zzba(zzai zzai, zzg zzg) {
        this.zza = zzai;
        this.zzb = zzg;
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        zzap zzap = (zzap) obj;
        zzap zzap2 = (zzap) obj2;
        if (zzap instanceof zzau) {
            return !(zzap2 instanceof zzau) ? 1 : 0;
        }
        if (zzap2 instanceof zzau) {
            return -1;
        }
        zzai zzai = this.zza;
        if (zzai == null) {
            return zzap.zzi().compareTo(zzap2.zzi());
        }
        return (int) zzh.zza(zzai.zza(this.zzb, Arrays.asList(new zzap[]{zzap, zzap2})).zzh().doubleValue());
    }
}
