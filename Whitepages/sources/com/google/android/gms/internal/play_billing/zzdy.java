package com.google.android.gms.internal.play_billing;

final class zzdy {
    private static final zzdx zza;
    private static final zzdx zzb = new zzdx();

    static {
        zzdx zzdx = null;
        try {
            zzdx = (zzdx) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        zza = zzdx;
    }

    static zzdx zza() {
        return zza;
    }

    static zzdx zzb() {
        return zzb;
    }
}
