package com.google.android.gms.measurement.internal;

abstract class zzjr extends zzjq {
    private boolean zza;

    zzjr(zzio zzio) {
        super(zzio);
        this.zzu.zzE();
    }

    /* access modifiers changed from: protected */
    public void zzaZ() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzc();

    /* access modifiers changed from: protected */
    public final void zzv() {
        if (!zzy()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzw() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzc()) {
            this.zzu.zzD();
            this.zza = true;
        }
    }

    public final void zzx() {
        if (!this.zza) {
            zzaZ();
            this.zzu.zzD();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzy() {
        return this.zza;
    }
}
