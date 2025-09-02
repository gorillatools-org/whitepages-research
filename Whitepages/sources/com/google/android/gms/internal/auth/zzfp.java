package com.google.android.gms.internal.auth;

final class zzfp implements zzgj {
    private static final zzfv zza = new zzfn();
    private final zzfv zzb;

    public zzfp() {
        zzfv zzfv;
        zzes zza2 = zzes.zza();
        try {
            zzfv = (zzfv) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            zzfv = zza;
        }
        zzfo zzfo = new zzfo(zza2, zzfv);
        byte[] bArr = zzfa.zzd;
        this.zzb = zzfo;
    }

    private static boolean zzb(zzfu zzfu) {
        return zzfu.zzc() + -1 != 1;
    }

    public final zzgi zza(Class cls) {
        zzgk.zze(cls);
        zzfu zzb2 = this.zzb.zzb(cls);
        Class<zzev> cls2 = zzev.class;
        if (zzb2.zzb()) {
            if (cls2.isAssignableFrom(cls)) {
                return zzgb.zzb(zzgk.zzb(), zzeo.zzb(), zzb2.zza());
            }
            return zzgb.zzb(zzgk.zza(), zzeo.zza(), zzb2.zza());
        } else if (cls2.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzga.zzj(cls, zzb2, zzgd.zzb(), zzfl.zzd(), zzgk.zzb(), zzeo.zzb(), zzft.zzb());
            }
            return zzga.zzj(cls, zzb2, zzgd.zzb(), zzfl.zzd(), zzgk.zzb(), (zzem) null, zzft.zzb());
        } else if (zzb(zzb2)) {
            return zzga.zzj(cls, zzb2, zzgd.zza(), zzfl.zzc(), zzgk.zza(), zzeo.zza(), zzft.zza());
        } else {
            return zzga.zzj(cls, zzb2, zzgd.zza(), zzfl.zzc(), zzgk.zza(), (zzem) null, zzft.zza());
        }
    }
}
