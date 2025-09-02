package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class zzeg implements zzeo {
    private final zzec zza;
    private final zzff zzb;
    private final boolean zzc;
    private final zzce zzd;

    private zzeg(zzff zzff, zzce zzce, zzec zzec) {
        this.zzb = zzff;
        this.zzc = zzce.zzf(zzec);
        this.zzd = zzce;
        this.zza = zzec;
    }

    static zzeg zzc(zzff zzff, zzce zzce, zzec zzec) {
        return new zzeg(zzff, zzce, zzec);
    }

    public final int zza(Object obj) {
        zzff zzff = this.zzb;
        int zzb2 = zzff.zzb(zzff.zzd(obj));
        return this.zzc ? zzb2 + this.zzd.zzb(obj).zzc() : zzb2;
    }

    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zzb(obj).zza.hashCode() : hashCode;
    }

    public final Object zze() {
        zzec zzec = this.zza;
        if (zzec instanceof zzcs) {
            return ((zzcs) zzec).zzl();
        }
        return zzec.zzE().zzg();
    }

    public final void zzf(Object obj) {
        this.zzb.zzg(obj);
        this.zzd.zzd(obj);
    }

    public final void zzg(Object obj, Object obj2) {
        zzeq.zzq(this.zzb, obj, obj2);
        if (this.zzc) {
            zzeq.zzp(this.zzd, obj, obj2);
        }
    }

    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzbc zzbc) throws IOException {
        zzcs zzcs = (zzcs) obj;
        if (zzcs.zzc == zzfg.zzc()) {
            zzcs.zzc = zzfg.zzf();
        }
        zzco zzco = (zzco) obj;
        throw null;
    }

    public final void zzi(Object obj, zzfx zzfx) throws IOException {
        Iterator zzf = this.zzd.zzb(obj).zzf();
        while (zzf.hasNext()) {
            Map.Entry entry = (Map.Entry) zzf.next();
            zzch zzch = (zzch) entry.getKey();
            if (zzch.zze() != zzfw.MESSAGE || zzch.zzg() || zzch.zzf()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzdf) {
                zzfx.zzw(zzch.zza(), ((zzdf) entry).zza().zzb());
            } else {
                zzfx.zzw(zzch.zza(), entry.getValue());
            }
        }
        zzff zzff = this.zzb;
        zzff.zzi(zzff.zzd(obj), zzfx);
    }

    public final boolean zzj(Object obj, Object obj2) {
        zzff zzff = this.zzb;
        if (!zzff.zzd(obj).equals(zzff.zzd(obj2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zzb(obj).equals(this.zzd.zzb(obj2));
        }
        return true;
    }

    public final boolean zzk(Object obj) {
        return this.zzd.zzb(obj).zzj();
    }
}
