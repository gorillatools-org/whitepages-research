package com.google.android.gms.internal.auth;

import android.util.Log;

final class zzcv extends zzdc {
    zzcv(zzcz zzcz, String str, Long l, boolean z) {
        super(zzcz, str, l, true, (zzdb) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj) {
        try {
            return Long.valueOf(Long.parseLong((String) obj));
        } catch (NumberFormatException unused) {
            String str = this.zzc;
            Log.e("PhenotypeFlag", "Invalid long value for " + str + ": " + ((String) obj));
            return null;
        }
    }
}
