package com.google.android.gms.internal.measurement;

final class zzle extends zzlg {
    private int zzb = 0;
    private int zzc;
    private int zzd = Integer.MAX_VALUE;

    /* synthetic */ zzle(byte[] bArr, int i, int i2, boolean z, zzlf zzlf) {
        super((zzlf) null);
    }

    public final int zza(int i) throws zzmm {
        int i2 = this.zzd;
        this.zzd = 0;
        int i3 = this.zzb + this.zzc;
        this.zzb = i3;
        if (i3 > 0) {
            this.zzc = i3;
            this.zzb = 0;
        } else {
            this.zzc = 0;
        }
        return i2;
    }
}
