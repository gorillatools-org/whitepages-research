package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzme extends zzkq implements RandomAccess, zzmh, zzno {
    private static final int[] zza;
    private static final zzme zzb;
    private int[] zzc;
    private int zzd;

    static {
        int[] iArr = new int[0];
        zza = iArr;
        zzb = new zzme(iArr, 0, false);
    }

    zzme() {
        this(zza, 0, true);
    }

    public static zzme zzf() {
        return zzb;
    }

    private static int zzj(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    private final String zzk(int i) {
        int i2 = this.zzd;
        return "Index:" + i + ", Size:" + i2;
    }

    private final void zzl(int i) {
        if (i < 0 || i >= this.zzd) {
            throw new IndexOutOfBoundsException(zzk(i));
        }
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        int intValue = ((Integer) obj).intValue();
        zzcE();
        if (i < 0 || i > (i2 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzk(i));
        }
        int i3 = i + 1;
        int[] iArr = this.zzc;
        int length = iArr.length;
        if (i2 < length) {
            System.arraycopy(iArr, i, iArr, i3, i2 - i);
        } else {
            int[] iArr2 = new int[zzj(length)];
            System.arraycopy(this.zzc, 0, iArr2, 0, i);
            System.arraycopy(this.zzc, i, iArr2, i3, this.zzd - i);
            this.zzc = iArr2;
        }
        this.zzc[i] = intValue;
        this.zzd++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zzcE();
        byte[] bArr = zzmk.zzb;
        collection.getClass();
        if (!(collection instanceof zzme)) {
            return super.addAll(collection);
        }
        zzme zzme = (zzme) collection;
        int i = zzme.zzd;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzd;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzc;
            if (i3 > iArr.length) {
                this.zzc = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzme.zzc, 0, this.zzc, this.zzd, zzme.zzd);
            this.zzd = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzme)) {
            return super.equals(obj);
        }
        zzme zzme = (zzme) obj;
        if (this.zzd != zzme.zzd) {
            return false;
        }
        int[] iArr = zzme.zzc;
        for (int i = 0; i < this.zzd; i++) {
            if (this.zzc[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzl(i);
        return Integer.valueOf(this.zzc[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzd; i2++) {
            i = (i * 31) + this.zzc[i2];
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int i = this.zzd;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzc[i2] == intValue) {
                return i2;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zzcE();
        zzl(i);
        int[] iArr = this.zzc;
        int i2 = iArr[i];
        int i3 = this.zzd;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.zzd--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzcE();
        if (i2 >= i) {
            int[] iArr = this.zzc;
            System.arraycopy(iArr, i2, iArr, i, this.zzd - i2);
            this.zzd -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzcE();
        zzl(i);
        int[] iArr = this.zzc;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final int size() {
        return this.zzd;
    }

    public final int zze(int i) {
        zzl(i);
        return this.zzc[i];
    }

    /* renamed from: zzg */
    public final zzmh zzd(int i) {
        int[] iArr;
        if (i >= this.zzd) {
            if (i == 0) {
                iArr = zza;
            } else {
                iArr = Arrays.copyOf(this.zzc, i);
            }
            return new zzme(iArr, this.zzd, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zzh(int i) {
        zzcE();
        int i2 = this.zzd;
        int length = this.zzc.length;
        if (i2 == length) {
            int[] iArr = new int[zzj(length)];
            System.arraycopy(this.zzc, 0, iArr, 0, this.zzd);
            this.zzc = iArr;
        }
        int[] iArr2 = this.zzc;
        int i3 = this.zzd;
        this.zzd = i3 + 1;
        iArr2[i3] = i;
    }

    /* access modifiers changed from: package-private */
    public final void zzi(int i) {
        int length = this.zzc.length;
        if (i > length) {
            if (length != 0) {
                while (length < i) {
                    length = zzj(length);
                }
                this.zzc = Arrays.copyOf(this.zzc, length);
                return;
            }
            this.zzc = new int[Math.max(i, 10)];
        }
    }

    private zzme(int[] iArr, int i, boolean z) {
        super(z);
        this.zzc = iArr;
        this.zzd = i;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzh(((Integer) obj).intValue());
        return true;
    }
}
