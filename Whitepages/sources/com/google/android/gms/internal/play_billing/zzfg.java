package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.Arrays;

public final class zzfg {
    private static final zzfg zza = new zzfg(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzfg() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfg(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzfg zzc() {
        return zza;
    }

    static zzfg zze(zzfg zzfg, zzfg zzfg2) {
        int i = zzfg.zzb + zzfg2.zzb;
        int[] copyOf = Arrays.copyOf(zzfg.zzc, i);
        System.arraycopy(zzfg2.zzc, 0, copyOf, zzfg.zzb, zzfg2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzfg.zzd, i);
        System.arraycopy(zzfg2.zzd, 0, copyOf2, zzfg.zzb, zzfg2.zzb);
        return new zzfg(i, copyOf, copyOf2, true);
    }

    static zzfg zzf() {
        return new zzfg(0, new int[8], new Object[8], true);
    }

    private final void zzm(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzfg)) {
            return false;
        }
        zzfg zzfg = (zzfg) obj;
        int i = this.zzb;
        if (i == zzfg.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzfg.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzfg.zzd;
                    int i3 = this.zzb;
                    int i4 = 0;
                    while (i4 < i3) {
                        if (objArr[i4].equals(objArr2[i4])) {
                            i4++;
                        }
                    }
                    return true;
                } else if (iArr[i2] != iArr2[i2]) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = i + 527;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = ((i2 * 31) + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zza() {
        int zzw;
        int zzx;
        int i;
        int i2 = this.zze;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            int i5 = this.zzc[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                int i8 = i6 << 3;
                long longValue = ((Long) this.zzd[i4]).longValue();
                zzw = zzby.zzw(i8);
                zzx = zzby.zzx(longValue);
                i = zzw + zzx;
            } else if (i7 == 1) {
                ((Long) this.zzd[i4]).longValue();
                i = zzby.zzw(i6 << 3) + 8;
            } else if (i7 == 2) {
                int zzw2 = zzby.zzw(i6 << 3);
                int zzd2 = ((zzbq) this.zzd[i4]).zzd();
                i = zzw2 + zzby.zzw(zzd2) + zzd2;
            } else if (i7 == 3) {
                int zzw3 = zzby.zzw(i6 << 3);
                zzw = zzw3 + zzw3;
                zzx = ((zzfg) this.zzd[i4]).zza();
                i = zzw + zzx;
            } else if (i7 == 5) {
                ((Integer) this.zzd[i4]).intValue();
                i = zzby.zzw(i6 << 3) + 4;
            } else {
                throw new IllegalStateException(zzdc.zza());
            }
            i3 += i;
        }
        this.zze = i3;
        return i3;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int zzw = zzby.zzw(8);
            int zzw2 = zzby.zzw(16) + zzby.zzw(this.zzc[i3] >>> 3);
            int zzw3 = zzby.zzw(24);
            int zzd2 = ((zzbq) this.zzd[i3]).zzd();
            i2 += zzw + zzw + zzw2 + zzw3 + zzby.zzw(zzd2) + zzd2;
        }
        this.zze = i2;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public final zzfg zzd(zzfg zzfg) {
        if (zzfg.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzfg.zzb;
        zzm(i);
        System.arraycopy(zzfg.zzc, 0, this.zzc, this.zzb, zzfg.zzb);
        System.arraycopy(zzfg.zzd, 0, this.zzd, this.zzb, zzfg.zzb);
        this.zzb = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzee.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(int i, Object obj) {
        zzg();
        zzm(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    /* access modifiers changed from: package-private */
    public final void zzk(zzfx zzfx) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzfx.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzfx zzfx) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    zzfx.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    zzfx.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    zzfx.zzd(i4, (zzbq) obj);
                } else if (i3 == 3) {
                    zzfx.zzF(i4);
                    ((zzfg) obj).zzl(zzfx);
                    zzfx.zzh(i4);
                } else if (i3 == 5) {
                    zzfx.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzdc.zza());
                }
            }
        }
    }
}
