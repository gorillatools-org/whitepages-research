package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Locale;

final class zzlh extends zzlk {
    private final byte[] zzc;
    private final int zzd;
    private int zze;

    zzlh(byte[] bArr, int i, int i2) {
        super((zzlj) null);
        int length = bArr.length;
        if (((length - i2) | i2) >= 0) {
            this.zzc = bArr;
            this.zze = 0;
            this.zzd = i2;
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(length), 0, Integer.valueOf(i2)}));
    }

    public final int zza() {
        return this.zzd - this.zze;
    }

    public final void zzb(byte b) throws IOException {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i = this.zze;
        try {
            int i2 = i + 1;
            try {
                this.zzc[i] = b;
                this.zze = i2;
            } catch (IndexOutOfBoundsException e) {
                indexOutOfBoundsException = e;
                i = i2;
                throw new zzli((long) i, (long) this.zzd, 1, indexOutOfBoundsException);
            }
        } catch (IndexOutOfBoundsException e2) {
            indexOutOfBoundsException = e2;
            throw new zzli((long) i, (long) this.zzd, 1, indexOutOfBoundsException);
        }
    }

    public final void zzc(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zzc, this.zze, i2);
            this.zze += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new zzli((long) this.zze, (long) this.zzd, i2, e);
        }
    }

    public final void zzd(int i, boolean z) throws IOException {
        zzt(i << 3);
        zzb(z ? (byte) 1 : 0);
    }

    public final void zze(int i, zzld zzld) throws IOException {
        zzt((i << 3) | 2);
        zzt(zzld.zzd());
        zzld.zzg(this);
    }

    public final void zzf(int i, int i2) throws IOException {
        zzt((i << 3) | 5);
        zzg(i2);
    }

    public final void zzg(int i) throws IOException {
        int i2 = this.zze;
        try {
            byte[] bArr = this.zzc;
            bArr[i2] = (byte) i;
            bArr[i2 + 1] = (byte) (i >> 8);
            bArr[i2 + 2] = (byte) (i >> 16);
            bArr[i2 + 3] = (byte) (i >> 24);
            this.zze = i2 + 4;
        } catch (IndexOutOfBoundsException e) {
            throw new zzli((long) i2, (long) this.zzd, 4, e);
        }
    }

    public final void zzh(int i, long j) throws IOException {
        zzt((i << 3) | 1);
        zzi(j);
    }

    public final void zzi(long j) throws IOException {
        int i = this.zze;
        try {
            byte[] bArr = this.zzc;
            bArr[i] = (byte) ((int) j);
            bArr[i + 1] = (byte) ((int) (j >> 8));
            bArr[i + 2] = (byte) ((int) (j >> 16));
            bArr[i + 3] = (byte) ((int) (j >> 24));
            bArr[i + 4] = (byte) ((int) (j >> 32));
            bArr[i + 5] = (byte) ((int) (j >> 40));
            bArr[i + 6] = (byte) ((int) (j >> 48));
            bArr[i + 7] = (byte) ((int) (j >> 56));
            this.zze = i + 8;
        } catch (IndexOutOfBoundsException e) {
            throw new zzli((long) i, (long) this.zzd, 8, e);
        }
    }

    public final void zzj(int i, int i2) throws IOException {
        zzt(i << 3);
        zzk(i2);
    }

    public final void zzk(int i) throws IOException {
        if (i >= 0) {
            zzt(i);
        } else {
            zzv((long) i);
        }
    }

    public final void zzl(byte[] bArr, int i, int i2) throws IOException {
        zzc(bArr, 0, i2);
    }

    /* access modifiers changed from: package-private */
    public final void zzm(int i, zznh zznh, zzns zzns) throws IOException {
        zzt((i << 3) | 2);
        zzt(((zzko) zznh).zzca(zzns));
        zzns.zzi(zznh, this.zza);
    }

    public final void zzn(int i, zznh zznh) throws IOException {
        zzt(11);
        zzs(2, i);
        zzt(26);
        zzt(zznh.zzcf());
        zznh.zzcB(this);
        zzt(12);
    }

    public final void zzo(int i, zzld zzld) throws IOException {
        zzt(11);
        zzs(2, i);
        zze(3, zzld);
        zzt(12);
    }

    public final void zzp(int i, String str) throws IOException {
        zzt((i << 3) | 2);
        zzq(str);
    }

    public final void zzq(String str) throws IOException {
        int i = this.zze;
        try {
            int zzz = zzlk.zzz(str.length() * 3);
            int zzz2 = zzlk.zzz(str.length());
            if (zzz2 == zzz) {
                int i2 = i + zzz2;
                this.zze = i2;
                int zzb = zzoo.zzb(str, this.zzc, i2, this.zzd - i2);
                this.zze = i;
                zzt((zzb - i) - zzz2);
                this.zze = zzb;
                return;
            }
            zzt(zzoo.zzc(str));
            byte[] bArr = this.zzc;
            int i3 = this.zze;
            this.zze = zzoo.zzb(str, bArr, i3, this.zzd - i3);
        } catch (zzon e) {
            this.zze = i;
            zzC(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzli(e2);
        }
    }

    public final void zzr(int i, int i2) throws IOException {
        zzt((i << 3) | i2);
    }

    public final void zzs(int i, int i2) throws IOException {
        zzt(i << 3);
        zzt(i2);
    }

    public final void zzu(int i, long j) throws IOException {
        zzt(i << 3);
        zzv(j);
    }

    public final void zzt(int i) throws IOException {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i2;
        int i3 = this.zze;
        while ((i & -128) != 0) {
            i2 = i3 + 1;
            this.zzc[i3] = (byte) (i | 128);
            i >>>= 7;
            i3 = i2;
        }
        try {
            i2 = i3 + 1;
            try {
                this.zzc[i3] = (byte) i;
                this.zze = i2;
            } catch (IndexOutOfBoundsException e) {
                indexOutOfBoundsException = e;
                i3 = i2;
                throw new zzli((long) i3, (long) this.zzd, 1, indexOutOfBoundsException);
            }
        } catch (IndexOutOfBoundsException e2) {
            indexOutOfBoundsException = e2;
            throw new zzli((long) i3, (long) this.zzd, 1, indexOutOfBoundsException);
        }
    }

    public final void zzv(long j) throws IOException {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i;
        int i2 = this.zze;
        if (!zzlk.zzd || this.zzd - i2 < 10) {
            while ((j & -128) != 0) {
                int i3 = i2 + 1;
                try {
                    this.zzc[i2] = (byte) (((int) j) | 128);
                    j >>>= 7;
                    i2 = i3;
                } catch (IndexOutOfBoundsException e) {
                    e = e;
                    i2 = i3;
                    indexOutOfBoundsException = e;
                    throw new zzli((long) i2, (long) this.zzd, 1, indexOutOfBoundsException);
                }
            }
            try {
                i = i2 + 1;
            } catch (IndexOutOfBoundsException e2) {
                e = e2;
                indexOutOfBoundsException = e;
                throw new zzli((long) i2, (long) this.zzd, 1, indexOutOfBoundsException);
            }
            try {
                this.zzc[i2] = (byte) ((int) j);
            } catch (IndexOutOfBoundsException e3) {
                indexOutOfBoundsException = e3;
                i2 = i;
                throw new zzli((long) i2, (long) this.zzd, 1, indexOutOfBoundsException);
            }
        } else {
            while ((j & -128) != 0) {
                zzol.zzn(this.zzc, (long) i2, (byte) (((int) j) | 128));
                j >>>= 7;
                i2++;
            }
            i = i2 + 1;
            zzol.zzn(this.zzc, (long) i2, (byte) ((int) j));
        }
        this.zze = i;
    }
}
