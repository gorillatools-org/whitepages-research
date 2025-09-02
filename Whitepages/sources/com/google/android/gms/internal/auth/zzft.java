package com.google.android.gms.internal.auth;

final class zzft {
    private static final zzfs zza;
    private static final zzfs zzb = new zzfs();

    static {
        zzfs zzfs = null;
        try {
            zzfs = (zzfs) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        zza = zzfs;
    }

    static zzfs zza() {
        return zza;
    }

    static zzfs zzb() {
        return zzb;
    }
}
