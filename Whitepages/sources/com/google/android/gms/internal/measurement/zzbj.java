package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzbj extends zzaw {
    public final zzap zza(String str, zzg zzg, List list) {
        if (str == null || str.isEmpty() || !zzg.zzh(str)) {
            throw new IllegalArgumentException(String.format("Command not found: %s", new Object[]{str}));
        }
        zzap zzd = zzg.zzd(str);
        if (zzd instanceof zzai) {
            return ((zzai) zzd).zza(zzg, list);
        }
        throw new IllegalArgumentException(String.format("Function %s is not defined", new Object[]{str}));
    }
}
