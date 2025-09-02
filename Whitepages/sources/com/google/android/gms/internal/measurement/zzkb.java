package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzkb extends zzki {
    zzkb(zzkf zzkf, String str, Long l, boolean z) {
        super(zzkf, str, l, true, (zzkh) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String str = this.zzb;
        String obj2 = obj.toString();
        Log.e("PhenotypeFlag", "Invalid long value for " + str + ": " + obj2);
        return null;
    }
}
