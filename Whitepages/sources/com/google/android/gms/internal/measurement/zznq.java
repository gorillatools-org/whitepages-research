package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.RandomAccess;

final class zznq extends zzkq implements RandomAccess {
    private static final Object[] zza;
    private static final zznq zzb;
    private Object[] zzc;
    private int zzd;

    static {
        Object[] objArr = new Object[0];
        zza = objArr;
        zzb = new zznq(objArr, 0, false);
    }

    zznq() {
        this(zza, 0, true);
    }

    public static zznq zze() {
        return zzb;
    }

    private static int zzg(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    private final String zzh(int i) {
        int i2 = this.zzd;
        return "Index:" + i + ", Size:" + i2;
    }

    private final void zzi(int i) {
        if (i < 0 || i >= this.zzd) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
    }

    public final void add(int i, Object obj) {
        int i2;
        zzcE();
        if (i < 0 || i > (i2 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
        int i3 = i + 1;
        Object[] objArr = this.zzc;
        int length = objArr.length;
        if (i2 < length) {
            System.arraycopy(objArr, i, objArr, i3, i2 - i);
        } else {
            Object[] objArr2 = new Object[zzg(length)];
            System.arraycopy(this.zzc, 0, objArr2, 0, i);
            System.arraycopy(this.zzc, i, objArr2, i3, this.zzd - i);
            this.zzc = objArr2;
        }
        this.zzc[i] = obj;
        this.zzd++;
        this.modCount++;
    }

    public final Object get(int i) {
        zzi(i);
        return this.zzc[i];
    }

    public final Object remove(int i) {
        zzcE();
        zzi(i);
        Object[] objArr = this.zzc;
        Object obj = objArr[i];
        int i2 = this.zzd;
        if (i < i2 - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (i2 - i) - 1);
        }
        this.zzd--;
        this.modCount++;
        return obj;
    }

    public final Object set(int i, Object obj) {
        zzcE();
        zzi(i);
        Object[] objArr = this.zzc;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        this.modCount++;
        return obj2;
    }

    public final int size() {
        return this.zzd;
    }

    public final /* bridge */ /* synthetic */ zzmj zzd(int i) {
        Object[] objArr;
        if (i >= this.zzd) {
            if (i == 0) {
                objArr = zza;
            } else {
                objArr = Arrays.copyOf(this.zzc, i);
            }
            return new zznq(objArr, this.zzd, true);
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public final void zzf(int i) {
        int length = this.zzc.length;
        if (i > length) {
            if (length != 0) {
                while (length < i) {
                    length = zzg(length);
                }
                this.zzc = Arrays.copyOf(this.zzc, length);
                return;
            }
            this.zzc = new Object[Math.max(i, 10)];
        }
    }

    private zznq(Object[] objArr, int i, boolean z) {
        super(z);
        this.zzc = objArr;
        this.zzd = i;
    }

    public final boolean add(Object obj) {
        zzcE();
        int i = this.zzd;
        int length = this.zzc.length;
        if (i == length) {
            this.zzc = Arrays.copyOf(this.zzc, zzg(length));
        }
        Object[] objArr = this.zzc;
        int i2 = this.zzd;
        this.zzd = i2 + 1;
        objArr[i2] = obj;
        this.modCount++;
        return true;
    }
}
