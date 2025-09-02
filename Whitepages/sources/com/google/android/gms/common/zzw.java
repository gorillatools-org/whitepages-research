package com.google.android.gms.common;

import android.util.Log;

class zzw {
    private static final zzw zze = new zzw(true, 3, 1, (String) null, (Throwable) null);
    final boolean zza;
    final String zzb;
    final Throwable zzc;
    final int zzd;

    private zzw(boolean z, int i, int i2, String str, Throwable th) {
        this.zza = z;
        this.zzd = i;
        this.zzb = str;
        this.zzc = th;
    }

    /* synthetic */ zzw(boolean z, int i, int i2, String str, Throwable th, zzv zzv) {
        this(false, 1, 5, (String) null, (Throwable) null);
    }

    @Deprecated
    static zzw zzb() {
        return zze;
    }

    static zzw zzc(String str) {
        return new zzw(false, 1, 5, str, (Throwable) null);
    }

    static zzw zzd(String str, Throwable th) {
        return new zzw(false, 1, 5, str, th);
    }

    static zzw zzf(int i) {
        return new zzw(true, i, 1, (String) null, (Throwable) null);
    }

    static zzw zzg(int i, int i2, String str, Throwable th) {
        return new zzw(false, i, i2, str, th);
    }

    /* access modifiers changed from: package-private */
    public String zza() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final void zze() {
        if (!this.zza && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if (this.zzc != null) {
                Log.d("GoogleCertificatesRslt", zza(), this.zzc);
            } else {
                Log.d("GoogleCertificatesRslt", zza());
            }
        }
    }
}
