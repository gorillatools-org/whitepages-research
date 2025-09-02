package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbn extends zzbm {
    protected final byte[] zza;

    zzbn(byte[] bArr) {
        super((zzbl) null);
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbq) || zzd() != ((zzbq) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzbn)) {
            return obj.equals(this);
        }
        zzbn zzbn = (zzbn) obj;
        int zzk = zzk();
        int zzk2 = zzbn.zzk();
        if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzbn.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd <= zzbn.zzd()) {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzbn.zza;
            zzbn.zzc();
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
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzbn.zzd());
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
        return zzda.zzb(i, this.zza, 0, i3);
    }

    public final zzbq zzf(int i, int i2) {
        int zzj = zzbq.zzj(0, i2, zzd());
        if (zzj == 0) {
            return zzbq.zzb;
        }
        return new zzbj(this.zza, 0, zzj);
    }

    /* access modifiers changed from: protected */
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzh(zzbf zzbf) throws IOException {
        ((zzbv) zzbf).zzc(this.zza, 0, zzd());
    }

    public final boolean zzi() {
        return zzfu.zze(this.zza, 0, zzd());
    }
}
