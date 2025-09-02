package com.google.android.gms.internal.measurement;

public class zzms {
    protected volatile zznh zza;
    private volatile zzld zzb;
    private volatile boolean zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzms)) {
            return false;
        }
        zzms zzms = (zzms) obj;
        zznh zznh = this.zza;
        zznh zznh2 = zzms.zza;
        if (zznh == null && zznh2 == null) {
            return zzb().equals(zzms.zzb());
        }
        if (zznh != null && zznh2 != null) {
            return zznh.equals(zznh2);
        }
        if (zznh != null) {
            zzms.zzd(zznh.zzcC());
            return zznh.equals(zzms.zza);
        }
        zzd(zznh2.zzcC());
        return this.zza.equals(zznh2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return ((zzlb) this.zzb).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzcf();
        }
        return 0;
    }

    public final zzld zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            try {
                if (this.zzb != null) {
                    zzld zzld = this.zzb;
                    return zzld;
                }
                if (this.zza == null) {
                    this.zzb = zzld.zzb;
                } else {
                    this.zzb = this.zza.zzcb();
                }
                zzld zzld2 = this.zzb;
                return zzld2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zznh zzc(zznh zznh) {
        zznh zznh2 = this.zza;
        this.zzb = null;
        this.zza = zznh;
        return zznh2;
    }

    /* access modifiers changed from: protected */
    public final void zzd(zznh zznh) {
        if (this.zza == null) {
            synchronized (this) {
                if (this.zza == null) {
                    try {
                        this.zza = zznh;
                        this.zzb = zzld.zzb;
                    } catch (zzmm unused) {
                        this.zzc = true;
                        this.zza = zznh;
                        this.zzb = zzld.zzb;
                    }
                }
            }
        }
    }
}
