package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class zznl implements zzns {
    private final zznh zza;
    private final zzoe zzb;
    private final boolean zzc;
    private final zzlq zzd;

    private zznl(zzoe zzoe, zzlq zzlq, zznh zznh) {
        this.zzb = zzoe;
        this.zzc = zznh instanceof zzma;
        this.zzd = zzlq;
        this.zza = zznh;
    }

    static zznl zzc(zzoe zzoe, zzlq zzlq, zznh zznh) {
        return new zznl(zzoe, zzlq, zznh);
    }

    public final int zza(Object obj) {
        int zzb2 = ((zzmd) obj).zzc.zzb();
        return this.zzc ? zzb2 + ((zzma) obj).zzb.zzc() : zzb2;
    }

    public final int zzb(Object obj) {
        int hashCode = ((zzmd) obj).zzc.hashCode();
        return this.zzc ? (hashCode * 53) + ((zzma) obj).zzb.zza.hashCode() : hashCode;
    }

    public final Object zze() {
        zznh zznh = this.zza;
        if (zznh instanceof zzmd) {
            return ((zzmd) zznh).zzcj();
        }
        return zznh.zzcA().zzbc();
    }

    public final void zzf(Object obj) {
        this.zzb.zza(obj);
        this.zzd.zza(obj);
    }

    public final void zzg(Object obj, Object obj2) {
        zznu.zzp(this.zzb, obj, obj2);
        if (this.zzc) {
            zznu.zzo(this.zzd, obj, obj2);
        }
    }

    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzks zzks) throws IOException {
        zzmd zzmd = (zzmd) obj;
        if (zzmd.zzc == zzof.zzc()) {
            zzmd.zzc = zzof.zzf();
        }
        zzma zzma = (zzma) obj;
        throw null;
    }

    public final void zzi(Object obj, zzor zzor) throws IOException {
        Iterator zze = ((zzma) obj).zzb.zze();
        while (zze.hasNext()) {
            Map.Entry entry = (Map.Entry) zze.next();
            zzlt zzlt = (zzlt) entry.getKey();
            if (zzlt.zzc() != zzoq.MESSAGE || zzlt.zze() || zzlt.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzmo) {
                zzor.zzw(zzlt.zza(), ((zzmo) entry).zza().zzb());
            } else {
                zzor.zzw(zzlt.zza(), entry.getValue());
            }
        }
        ((zzmd) obj).zzc.zzk(zzor);
    }

    public final boolean zzj(Object obj, Object obj2) {
        if (!((zzmd) obj).zzc.equals(((zzmd) obj2).zzc)) {
            return false;
        }
        if (this.zzc) {
            return ((zzma) obj).zzb.equals(((zzma) obj2).zzb);
        }
        return true;
    }

    public final boolean zzk(Object obj) {
        return ((zzma) obj).zzb.zzh();
    }
}
