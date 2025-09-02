package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzku extends zzkq implements RandomAccess, zzmj, zzno {
    private static final boolean[] zza;
    private boolean[] zzb;
    private int zzc;

    static {
        boolean[] zArr = new boolean[0];
        zza = zArr;
        new zzku(zArr, 0, false);
    }

    zzku() {
        this(zza, 0, true);
    }

    private static int zzg(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    private final String zzh(int i) {
        int i2 = this.zzc;
        return "Index:" + i + ", Size:" + i2;
    }

    private final void zzi(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzcE();
        if (i < 0 || i > (i2 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
        int i3 = i + 1;
        boolean[] zArr = this.zzb;
        int length = zArr.length;
        if (i2 < length) {
            System.arraycopy(zArr, i, zArr, i3, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[zzg(length)];
            System.arraycopy(this.zzb, 0, zArr2, 0, i);
            System.arraycopy(this.zzb, i, zArr2, i3, this.zzc - i);
            this.zzb = zArr2;
        }
        this.zzb[i] = booleanValue;
        this.zzc++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zzcE();
        byte[] bArr = zzmk.zzb;
        collection.getClass();
        if (!(collection instanceof zzku)) {
            return super.addAll(collection);
        }
        zzku zzku = (zzku) collection;
        int i = zzku.zzc;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzc;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzb;
            if (i3 > zArr.length) {
                this.zzb = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzku.zzb, 0, this.zzb, this.zzc, zzku.zzc);
            this.zzc = i3;
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
        if (!(obj instanceof zzku)) {
            return super.equals(obj);
        }
        zzku zzku = (zzku) obj;
        if (this.zzc != zzku.zzc) {
            return false;
        }
        boolean[] zArr = zzku.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (this.zzb[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzi(i);
        return Boolean.valueOf(this.zzb[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            i = (i * 31) + zzmk.zza(this.zzb[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int i = this.zzc;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzb[i2] == booleanValue) {
                return i2;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zzcE();
        zzi(i);
        boolean[] zArr = this.zzb;
        boolean z = zArr[i];
        int i2 = this.zzc;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzcE();
        if (i2 >= i) {
            boolean[] zArr = this.zzb;
            System.arraycopy(zArr, i2, zArr, i, this.zzc - i2);
            this.zzc -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzcE();
        zzi(i);
        boolean[] zArr = this.zzb;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final int size() {
        return this.zzc;
    }

    public final /* bridge */ /* synthetic */ zzmj zzd(int i) {
        boolean[] zArr;
        if (i >= this.zzc) {
            if (i == 0) {
                zArr = zza;
            } else {
                zArr = Arrays.copyOf(this.zzb, i);
            }
            return new zzku(zArr, this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(boolean z) {
        zzcE();
        int i = this.zzc;
        int length = this.zzb.length;
        if (i == length) {
            boolean[] zArr = new boolean[zzg(length)];
            System.arraycopy(this.zzb, 0, zArr, 0, this.zzc);
            this.zzb = zArr;
        }
        boolean[] zArr2 = this.zzb;
        int i2 = this.zzc;
        this.zzc = i2 + 1;
        zArr2[i2] = z;
    }

    public final boolean zzf(int i) {
        zzi(i);
        return this.zzb[i];
    }

    private zzku(boolean[] zArr, int i, boolean z) {
        super(z);
        this.zzb = zArr;
        this.zzc = i;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Boolean) obj).booleanValue());
        return true;
    }
}
