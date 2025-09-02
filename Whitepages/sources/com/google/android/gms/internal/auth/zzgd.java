package com.google.android.gms.internal.auth;

final class zzgd {
    private static final zzgc zza;
    private static final zzgc zzb = new zzgc();

    static {
        zzgc zzgc = null;
        try {
            zzgc = (zzgc) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        zza = zzgc;
    }

    static zzgc zza() {
        return zza;
    }

    static zzgc zzb() {
        return zzb;
    }
}
