package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzfe extends zzdr implements RandomAccess, zzff {
    @Deprecated
    public static final zzff zza;
    private static final zzfe zzb;
    private final List zzc;

    static {
        zzfe zzfe = new zzfe(false);
        zzb = zzfe;
        zza = zzfe;
    }

    public zzfe() {
        this(10);
    }

    private static String zzh(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzef) {
            return ((zzef) obj).zzl(zzfa.zzb);
        }
        return zzfa.zzd((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzff) {
            collection = ((zzff) collection).zzg();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzh(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzh(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final /* bridge */ /* synthetic */ zzez zzd(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzfe(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final zzff zze() {
        return zzc() ? new zzhe(this) : this;
    }

    /* renamed from: zzf */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzef) {
            zzef zzef = (zzef) obj;
            String zzl = zzef.zzl(zzfa.zzb);
            if (zzef.zzh()) {
                this.zzc.set(i, zzl);
            }
            return zzl;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzfa.zzd(bArr);
        if (zzhn.zzb(bArr)) {
            this.zzc.set(i, zzd);
        }
        return zzd;
    }

    public final List zzg() {
        return Collections.unmodifiableList(this.zzc);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzfe(int i) {
        super(true);
        ArrayList arrayList = new ArrayList(i);
        this.zzc = arrayList;
    }

    private zzfe(ArrayList arrayList) {
        super(true);
        this.zzc = arrayList;
    }

    private zzfe(boolean z) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
