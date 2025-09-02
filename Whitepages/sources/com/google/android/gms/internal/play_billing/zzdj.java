package com.google.android.gms.internal.play_billing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzdj extends zzba implements RandomAccess, zzdk {
    @Deprecated
    public static final zzdk zza;
    private static final zzdj zzb;
    private final List zzc;

    static {
        zzdj zzdj = new zzdj(false);
        zzb = zzdj;
        zza = zzdj;
    }

    public zzdj() {
        this(10);
    }

    private static String zzi(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbq) {
            return ((zzbq) obj).zzm(zzda.zzb);
        }
        return zzda.zzd((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzdk) {
            collection = ((zzdk) collection).zzh();
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
        return zzi(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzi(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final /* bridge */ /* synthetic */ zzcz zzd(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzdj(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final zzdk zze() {
        return zzc() ? new zzfk(this) : this;
    }

    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    /* renamed from: zzg */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbq) {
            zzbq zzbq = (zzbq) obj;
            String zzm = zzbq.zzm(zzda.zzb);
            if (zzbq.zzi()) {
                this.zzc.set(i, zzm);
            }
            return zzm;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzda.zzd(bArr);
        if (zzfu.zzd(bArr)) {
            this.zzc.set(i, zzd);
        }
        return zzd;
    }

    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzdj(int i) {
        super(true);
        ArrayList arrayList = new ArrayList(i);
        this.zzc = arrayList;
    }

    private zzdj(ArrayList arrayList) {
        super(true);
        this.zzc = arrayList;
    }

    private zzdj(boolean z) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
