package com.google.android.gms.internal.auth;

import java.util.Arrays;

public final class zzha {
    private static final zzha zza = new zzha(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private boolean zze;

    private zzha() {
        this(0, new int[8], new Object[8], true);
    }

    private zzha(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = z;
    }

    public static zzha zza() {
        return zza;
    }

    static zzha zzc(zzha zzha, zzha zzha2) {
        int i = zzha.zzb + zzha2.zzb;
        int[] copyOf = Arrays.copyOf(zzha.zzc, i);
        System.arraycopy(zzha2.zzc, 0, copyOf, zzha.zzb, zzha2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzha.zzd, i);
        System.arraycopy(zzha2.zzd, 0, copyOf2, zzha.zzb, zzha2.zzb);
        return new zzha(i, copyOf, copyOf2, true);
    }

    static zzha zzd() {
        return new zzha(0, new int[8], new Object[8], true);
    }

    private final void zzi(int i) {
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
        if (obj == null || !(obj instanceof zzha)) {
            return false;
        }
        zzha zzha = (zzha) obj;
        int i = this.zzb;
        if (i == zzha.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzha.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzha.zzd;
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
        int i6 = (i2 * 31) + i4;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return (i6 * 31) + i3;
    }

    /* access modifiers changed from: package-private */
    public final zzha zzb(zzha zzha) {
        if (zzha.equals(zza)) {
            return this;
        }
        zze();
        int i = this.zzb + zzha.zzb;
        zzi(i);
        System.arraycopy(zzha.zzc, 0, this.zzc, this.zzb, zzha.zzb);
        System.arraycopy(zzha.zzd, 0, this.zzd, this.zzb, zzha.zzb);
        this.zzb = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void zze() {
        if (!this.zze) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzf() {
        if (this.zze) {
            this.zze = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzg(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzfz.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzh(int i, Object obj) {
        zze();
        zzi(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }
}
