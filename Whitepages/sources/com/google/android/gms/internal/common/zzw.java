package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;

final class zzw extends zzz {
    final /* synthetic */ zzr zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzw(zzx zzx, zzaa zzaa, CharSequence charSequence, zzr zzr) {
        super(zzaa, charSequence);
        this.zza = zzr;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(int i) {
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(int i) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzv.zzb(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (this.zza.zza(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
