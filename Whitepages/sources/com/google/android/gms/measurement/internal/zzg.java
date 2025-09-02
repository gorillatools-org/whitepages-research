package com.google.android.gms.measurement.internal;

abstract class zzg extends zzf {
    private boolean zza;

    zzg(zzio zzio) {
        super(zzio);
        this.zzu.zzE();
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        if (!zze()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzb() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzf()) {
            this.zzu.zzD();
            this.zza = true;
        }
    }

    public final void zzc() {
        if (!this.zza) {
            zzd();
            this.zzu.zzD();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: protected */
    public void zzd() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zze() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzf();
}
