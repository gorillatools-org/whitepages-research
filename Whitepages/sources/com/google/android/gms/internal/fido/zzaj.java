package com.google.android.gms.internal.fido;

import java.util.Arrays;

public final class zzaj {
    private final String zza;
    private final zzah zzb;
    private zzah zzc;

    /* synthetic */ zzaj(String str, zzai zzai) {
        zzah zzah = new zzah((zzag) null);
        this.zzb = zzah;
        this.zzc = zzah;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzah zzah = this.zzb.zzc;
        String str = "";
        while (zzah != null) {
            Object obj = zzah.zzb;
            boolean z = zzah instanceof zzaf;
            sb.append(str);
            String str2 = zzah.zza;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            zzah = zzah.zzc;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzaj zza(String str, int i) {
        String valueOf = String.valueOf(i);
        zzaf zzaf = new zzaf((zzae) null);
        this.zzc.zzc = zzaf;
        this.zzc = zzaf;
        zzaf.zzb = valueOf;
        zzaf.zza = "errorCode";
        return this;
    }

    public final zzaj zzb(String str, Object obj) {
        zzah zzah = new zzah((zzag) null);
        this.zzc.zzc = zzah;
        this.zzc = zzah;
        zzah.zzb = obj;
        zzah.zza = str;
        return this;
    }
}
