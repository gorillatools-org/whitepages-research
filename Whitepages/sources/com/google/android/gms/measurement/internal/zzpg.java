package com.google.android.gms.measurement.internal;

abstract class zzpg extends zzoz {
    private boolean zza;

    zzpg(zzpv zzpv) {
        super(zzpv);
        this.zzg.zzad();
    }

    /* access modifiers changed from: protected */
    public final void zzav() {
        if (!zzax()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzaw() {
        if (!this.zza) {
            zzb();
            this.zzg.zzV();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzax() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzb();
}
