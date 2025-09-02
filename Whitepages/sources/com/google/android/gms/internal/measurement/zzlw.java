package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzlw extends zzkq implements RandomAccess, zzmj, zzno {
    private static final float[] zza;
    private float[] zzb;
    private int zzc;

    static {
        float[] fArr = new float[0];
        zza = fArr;
        new zzlw(fArr, 0, false);
    }

    zzlw() {
        this(zza, 0, true);
    }

    private static int zzh(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    private final String zzi(int i) {
        int i2 = this.zzc;
        return "Index:" + i + ", Size:" + i2;
    }

    private final void zzj(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        float floatValue = ((Float) obj).floatValue();
        zzcE();
        if (i < 0 || i > (i2 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        int i3 = i + 1;
        float[] fArr = this.zzb;
        int length = fArr.length;
        if (i2 < length) {
            System.arraycopy(fArr, i, fArr, i3, i2 - i);
        } else {
            float[] fArr2 = new float[zzh(length)];
            System.arraycopy(this.zzb, 0, fArr2, 0, i);
            System.arraycopy(this.zzb, i, fArr2, i3, this.zzc - i);
            this.zzb = fArr2;
        }
        this.zzb[i] = floatValue;
        this.zzc++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zzcE();
        byte[] bArr = zzmk.zzb;
        collection.getClass();
        if (!(collection instanceof zzlw)) {
            return super.addAll(collection);
        }
        zzlw zzlw = (zzlw) collection;
        int i = zzlw.zzc;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzc;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.zzb;
            if (i3 > fArr.length) {
                this.zzb = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(zzlw.zzb, 0, this.zzb, this.zzc, zzlw.zzc);
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
        if (!(obj instanceof zzlw)) {
            return super.equals(obj);
        }
        zzlw zzlw = (zzlw) obj;
        if (this.zzc != zzlw.zzc) {
            return false;
        }
        float[] fArr = zzlw.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (Float.floatToIntBits(this.zzb[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzj(i);
        return Float.valueOf(this.zzb[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzb[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int i = this.zzc;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzb[i2] == floatValue) {
                return i2;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zzcE();
        zzj(i);
        float[] fArr = this.zzb;
        float f = fArr[i];
        int i2 = this.zzc;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Float.valueOf(f);
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzcE();
        if (i2 >= i) {
            float[] fArr = this.zzb;
            System.arraycopy(fArr, i2, fArr, i, this.zzc - i2);
            this.zzc -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzcE();
        zzj(i);
        float[] fArr = this.zzb;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    public final int size() {
        return this.zzc;
    }

    public final /* bridge */ /* synthetic */ zzmj zzd(int i) {
        float[] fArr;
        if (i >= this.zzc) {
            if (i == 0) {
                fArr = zza;
            } else {
                fArr = Arrays.copyOf(this.zzb, i);
            }
            return new zzlw(fArr, this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final float zze(int i) {
        zzj(i);
        return this.zzb[i];
    }

    public final void zzf(float f) {
        zzcE();
        int i = this.zzc;
        int length = this.zzb.length;
        if (i == length) {
            float[] fArr = new float[zzh(length)];
            System.arraycopy(this.zzb, 0, fArr, 0, this.zzc);
            this.zzb = fArr;
        }
        float[] fArr2 = this.zzb;
        int i2 = this.zzc;
        this.zzc = i2 + 1;
        fArr2[i2] = f;
    }

    /* access modifiers changed from: package-private */
    public final void zzg(int i) {
        int length = this.zzb.length;
        if (i > length) {
            if (length != 0) {
                while (length < i) {
                    length = zzh(length);
                }
                this.zzb = Arrays.copyOf(this.zzb, length);
                return;
            }
            this.zzb = new float[Math.max(i, 10)];
        }
    }

    private zzlw(float[] fArr, int i, boolean z) {
        super(z);
        this.zzb = fArr;
        this.zzc = i;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Float) obj).floatValue());
        return true;
    }
}
