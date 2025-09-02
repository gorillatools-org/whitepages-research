package com.google.android.gms.internal.auth;

import java.util.Comparator;

final class zzdx implements Comparator {
    zzdx() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzef zzef = (zzef) obj;
        zzef zzef2 = (zzef) obj2;
        zzdw zzdw = new zzdw(zzef);
        zzdw zzdw2 = new zzdw(zzef2);
        while (zzdw.hasNext() && zzdw2.hasNext()) {
            int compareTo = Integer.valueOf(zzdw.zza() & 255).compareTo(Integer.valueOf(zzdw2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzef.zzd()).compareTo(Integer.valueOf(zzef2.zzd()));
    }
}
