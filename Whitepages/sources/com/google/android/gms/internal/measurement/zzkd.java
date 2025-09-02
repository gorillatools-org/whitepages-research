package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzkd extends zzki {
    zzkd(zzkf zzkf, String str, Double d, boolean z) {
        super(zzkf, "measurement.test.double_flag", d, true, (zzkh) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String str = this.zzb;
        String obj2 = obj.toString();
        Log.e("PhenotypeFlag", "Invalid double value for " + str + ": " + obj2);
        return null;
    }
}
