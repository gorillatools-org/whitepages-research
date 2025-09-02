package com.google.android.gms.internal.play_billing;

final class zzej {
    private static final zzei zza;
    private static final zzei zzb = new zzei();

    static {
        zzei zzei = null;
        try {
            zzei = (zzei) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        zza = zzei;
    }

    static zzei zza() {
        return zza;
    }

    static zzei zzb() {
        return zzb;
    }
}
