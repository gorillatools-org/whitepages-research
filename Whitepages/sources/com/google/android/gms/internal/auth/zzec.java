package com.google.android.gms.internal.auth;

import java.nio.charset.Charset;

class zzec extends zzeb {
    protected final byte[] zza;

    zzec(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzef) || zzd() != ((zzef) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzec)) {
            return obj.equals(this);
        }
        zzec zzec = (zzec) obj;
        int zzj = zzj();
        int zzj2 = zzec.zzj();
        if (zzj != 0 && zzj2 != 0 && zzj != zzj2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzec.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd <= zzec.zzd()) {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzec.zza;
            zzec.zzc();
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
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzec.zzd());
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
        return zzfa.zzb(i, this.zza, 0, i3);
    }

    public final zzef zzf(int i, int i2) {
        int zzi = zzef.zzi(0, i2, zzd());
        if (zzi == 0) {
            return zzef.zzb;
        }
        return new zzdz(this.zza, 0, zzi);
    }

    /* access modifiers changed from: protected */
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    public final boolean zzh() {
        return zzhn.zzc(this.zza, 0, zzd());
    }
}
