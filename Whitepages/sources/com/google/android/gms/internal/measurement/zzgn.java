package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

public final class zzgn extends zzlz implements zzni {
    private zzgn() {
        throw null;
    }

    /* synthetic */ zzgn(zzgz zzgz) {
        super(zzgo.zzb);
    }

    public final int zza() {
        return ((zzgo) this.zza).zzb();
    }

    public final zzgm zzb(int i) {
        return ((zzgo) this.zza).zze(i);
    }

    public final zzgn zzc() {
        zzbe();
        ((zzgo) this.zza).zzj = zzmd.zzcn();
        return this;
    }

    public final zzgn zzd() {
        zzbe();
        ((zzgo) this.zza).zzm = zzmd.zzcn();
        return this;
    }

    public final zzgn zze(int i, zzgl zzgl) {
        zzbe();
        zzgo.zzs((zzgo) this.zza, i, (zzgm) zzgl.zzba());
        return this;
    }

    public final String zzf() {
        return ((zzgo) this.zza).zzk();
    }

    public final List zzg() {
        return Collections.unmodifiableList(((zzgo) this.zza).zzm());
    }

    public final List zzh() {
        return Collections.unmodifiableList(((zzgo) this.zza).zzn());
    }
}
