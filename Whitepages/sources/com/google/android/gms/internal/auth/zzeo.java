package com.google.android.gms.internal.auth;

final class zzeo {
    private static final zzem zza = new zzen();
    private static final zzem zzb;

    static {
        zzem zzem = null;
        try {
            zzem = (zzem) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        zzb = zzem;
    }

    static zzem zza() {
        zzem zzem = zzb;
        if (zzem != null) {
            return zzem;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzem zzb() {
        return zza;
    }
}
