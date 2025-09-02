package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

public final class zzhl extends zzlz implements zzni {
    private zzhl() {
        throw null;
    }

    /* synthetic */ zzhl(zzip zzip) {
        super(zzhm.zzb);
    }

    public final int zza() {
        return ((zzhm) this.zza).zzb();
    }

    public final long zzb() {
        return ((zzhm) this.zza).zzc();
    }

    public final long zzc() {
        return ((zzhm) this.zza).zzd();
    }

    public final zzhl zzd(Iterable iterable) {
        zzbe();
        zzhm.zzj((zzhm) this.zza, iterable);
        return this;
    }

    public final zzhl zze(zzhp zzhp) {
        zzbe();
        zzhm.zzk((zzhm) this.zza, (zzhq) zzhp.zzba());
        return this;
    }

    public final zzhl zzf(zzhq zzhq) {
        zzbe();
        zzhm.zzk((zzhm) this.zza, zzhq);
        return this;
    }

    public final zzhl zzg() {
        zzbe();
        ((zzhm) this.zza).zze = zzmd.zzcn();
        return this;
    }

    public final zzhl zzh(int i) {
        zzbe();
        zzhm.zzn((zzhm) this.zza, i);
        return this;
    }

    public final zzhl zzi(String str) {
        zzbe();
        zzhm.zzo((zzhm) this.zza, str);
        return this;
    }

    public final zzhl zzj(int i, zzhp zzhp) {
        zzbe();
        zzhm.zzp((zzhm) this.zza, i, (zzhq) zzhp.zzba());
        return this;
    }

    public final zzhl zzk(int i, zzhq zzhq) {
        zzbe();
        zzhm.zzp((zzhm) this.zza, i, zzhq);
        return this;
    }

    public final zzhl zzl(long j) {
        zzbe();
        zzhm.zzq((zzhm) this.zza, j);
        return this;
    }

    public final zzhl zzm(long j) {
        zzbe();
        zzhm.zzr((zzhm) this.zza, j);
        return this;
    }

    public final zzhq zzn(int i) {
        return ((zzhm) this.zza).zzg(i);
    }

    public final String zzo() {
        return ((zzhm) this.zza).zzh();
    }

    public final List zzp() {
        return Collections.unmodifiableList(((zzhm) this.zza).zzi());
    }

    public final boolean zzq() {
        return ((zzhm) this.zza).zzu();
    }
}
