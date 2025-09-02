package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzkq extends AbstractList implements zzmj {
    private boolean zza;

    zzkq(boolean z) {
        this.zza = z;
    }

    public void add(int i, Object obj) {
        zzcE();
        super.add(i, obj);
    }

    public final boolean addAll(int i, Collection collection) {
        zzcE();
        return super.addAll(i, collection);
    }

    public final void clear() {
        zzcE();
        super.clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public Object remove(int i) {
        zzcE();
        return super.remove(i);
    }

    public final boolean removeAll(Collection collection) {
        zzcE();
        return super.removeAll(collection);
    }

    public final boolean retainAll(Collection collection) {
        zzcE();
        return super.retainAll(collection);
    }

    public Object set(int i, Object obj) {
        zzcE();
        return super.set(i, obj);
    }

    public final void zzb() {
        if (this.zza) {
            this.zza = false;
        }
    }

    public final boolean zzc() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzcE() {
        if (!this.zza) {
            throw new UnsupportedOperationException();
        }
    }

    public boolean add(Object obj) {
        zzcE();
        return super.add(obj);
    }

    public boolean addAll(Collection collection) {
        zzcE();
        return super.addAll(collection);
    }

    public final boolean remove(Object obj) {
        zzcE();
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }
}
