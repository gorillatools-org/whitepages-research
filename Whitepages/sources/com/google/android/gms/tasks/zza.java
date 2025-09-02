package com.google.android.gms.tasks;

final class zza implements OnSuccessListener {
    final /* synthetic */ OnTokenCanceledListener zza;

    zza(zzb zzb, OnTokenCanceledListener onTokenCanceledListener) {
        this.zza = onTokenCanceledListener;
    }

    public final /* bridge */ /* synthetic */ void onSuccess(Object obj) {
        Void voidR = (Void) obj;
        this.zza.onCanceled();
    }
}
