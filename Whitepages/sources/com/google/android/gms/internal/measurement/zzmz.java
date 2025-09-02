package com.google.android.gms.internal.measurement;

final class zzmz implements zznt {
    private static final zznf zza = new zzmx();
    private final zznf zzb;

    public zzmz() {
        zzly zza2 = zzly.zza();
        int i = zznp.zza;
        zzmy zzmy = new zzmy(zza2, zza);
        byte[] bArr = zzmk.zzb;
        this.zzb = zzmy;
    }

    public final zzns zza(Class cls) {
        int i = zznu.zza;
        if (!zzmd.class.isAssignableFrom(cls)) {
            int i2 = zznp.zza;
        }
        zzne zzb2 = this.zzb.zzb(cls);
        if (!zzb2.zzb()) {
            int i3 = zznp.zza;
            return zznk.zzl(cls, zzb2, zznn.zza(), zzmv.zza(), zznu.zzm(), zzb2.zzc() + -1 != 1 ? zzls.zza() : null, zznd.zza());
        }
        int i4 = zznp.zza;
        return zznl.zzc(zznu.zzm(), zzls.zza(), zzb2.zza());
    }
}
