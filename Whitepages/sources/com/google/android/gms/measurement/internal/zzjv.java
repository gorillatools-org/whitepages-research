package com.google.android.gms.measurement.internal;

public enum zzjv {
    STORAGE(zzjw.AD_STORAGE, zzjw.ANALYTICS_STORAGE),
    DMA(zzjw.AD_USER_DATA);
    
    /* access modifiers changed from: private */
    public final zzjw[] zzd;

    private zzjv(zzjw... zzjwArr) {
        this.zzd = zzjwArr;
    }

    public final zzjw[] zzb() {
        return this.zzd;
    }
}
