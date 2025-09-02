package com.google.android.gms.internal.play_billing;

final class zzdu implements zzep {
    private static final zzea zza = new zzds();
    private final zzea zzb;

    public zzdu() {
        zzea zzea;
        zzcm zza2 = zzcm.zza();
        try {
            zzea = (zzea) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            zzea = zza;
        }
        zzdt zzdt = new zzdt(zza2, zzea);
        byte[] bArr = zzda.zzd;
        this.zzb = zzdt;
    }

    private static boolean zzb(zzdz zzdz) {
        return zzdz.zzc() + -1 != 1;
    }

    public final zzeo zza(Class cls) {
        zzeq.zzr(cls);
        zzdz zzb2 = this.zzb.zzb(cls);
        Class<zzcs> cls2 = zzcs.class;
        if (zzb2.zzb()) {
            if (cls2.isAssignableFrom(cls)) {
                return zzeg.zzc(zzeq.zzn(), zzcg.zzb(), zzb2.zza());
            }
            return zzeg.zzc(zzeq.zzm(), zzcg.zza(), zzb2.zza());
        } else if (cls2.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzef.zzl(cls, zzb2, zzej.zzb(), zzdq.zzd(), zzeq.zzn(), zzcg.zzb(), zzdy.zzb());
            }
            return zzef.zzl(cls, zzb2, zzej.zzb(), zzdq.zzd(), zzeq.zzn(), (zzce) null, zzdy.zzb());
        } else if (zzb(zzb2)) {
            return zzef.zzl(cls, zzb2, zzej.zza(), zzdq.zzc(), zzeq.zzm(), zzcg.zza(), zzdy.zza());
        } else {
            return zzef.zzl(cls, zzb2, zzej.zza(), zzdq.zzc(), zzeq.zzm(), (zzce) null, zzdy.zza());
        }
    }
}
