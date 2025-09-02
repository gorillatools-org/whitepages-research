package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzkc extends zzki {
    zzkc(zzkf zzkf, String str, Boolean bool, boolean z) {
        super(zzkf, str, bool, true, (zzkh) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzjc.zzc.matcher(str).matches()) {
                return Boolean.TRUE;
            }
            if (zzjc.zzd.matcher(str).matches()) {
                return Boolean.FALSE;
            }
        }
        String str2 = this.zzb;
        String obj2 = obj.toString();
        Log.e("PhenotypeFlag", "Invalid boolean value for " + str2 + ": " + obj2);
        return null;
    }
}
