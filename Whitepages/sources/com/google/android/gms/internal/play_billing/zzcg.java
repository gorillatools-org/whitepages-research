package com.google.android.gms.internal.play_billing;

final class zzcg {
    private static final zzce zza = new zzcf();
    private static final zzce zzb;

    static {
        zzce zzce = null;
        try {
            zzce = (zzce) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        zzb = zzce;
    }

    static zzce zza() {
        zzce zzce = zzb;
        if (zzce != null) {
            return zzce;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzce zzb() {
        return zza;
    }
}
