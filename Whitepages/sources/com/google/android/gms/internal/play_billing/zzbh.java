package com.google.android.gms.internal.play_billing;

import java.util.Comparator;

final class zzbh implements Comparator {
    zzbh() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzbq zzbq = (zzbq) obj;
        zzbq zzbq2 = (zzbq) obj2;
        zzbg zzbg = new zzbg(zzbq);
        zzbg zzbg2 = new zzbg(zzbq2);
        while (zzbg.hasNext() && zzbg2.hasNext()) {
            int compareTo = Integer.valueOf(zzbg.zza() & 255).compareTo(Integer.valueOf(zzbg2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzbq.zzd()).compareTo(Integer.valueOf(zzbq2.zzd()));
    }
}
