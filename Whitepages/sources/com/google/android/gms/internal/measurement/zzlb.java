package com.google.android.gms.internal.measurement;

import java.io.IOException;

class zzlb extends zzla {
    protected final byte[] zza;

    zzlb(byte[] bArr) {
        super((zzlc) null);
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzld) || zzd() != ((zzld) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzlb)) {
            return obj.equals(this);
        }
        zzlb zzlb = (zzlb) obj;
        int zzi = zzi();
        int zzi2 = zzlb.zzi();
        if (zzi != 0 && zzi2 != 0 && zzi != zzi2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzlb.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd <= zzlb.zzd()) {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzlb.zza;
            zzlb.zzc();
            int i = 0;
            int i2 = 0;
            while (i < zzd) {
                if (bArr[i] != bArr2[i2]) {
                    return false;
                }
                i++;
                i2++;
            }
            return true;
        } else {
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzlb.zzd());
        }
    }

    public byte zza(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: protected */
    public int zzc() {
        return 0;
    }

    public int zzd() {
        return this.zza.length;
    }

    /* access modifiers changed from: protected */
    public final int zze(int i, int i2, int i3) {
        return zzmk.zzb(i, this.zza, 0, i3);
    }

    public final zzld zzf(int i, int i2) {
        int zzh = zzld.zzh(0, i2, zzd());
        if (zzh == 0) {
            return zzld.zzb;
        }
        return new zzky(this.zza, 0, zzh);
    }

    /* access modifiers changed from: package-private */
    public final void zzg(zzkv zzkv) throws IOException {
        ((zzlh) zzkv).zzc(this.zza, 0, zzd());
    }
}
