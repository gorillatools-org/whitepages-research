package com.google.android.gms.internal.p000authapiphone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzl  reason: invalid package */
public final /* synthetic */ class zzl implements RemoteCall {
    public final /* synthetic */ zzr zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzl(zzr zzr, String str) {
        this.zza = zzr;
        this.zzb = str;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzh) ((zzw) obj).getService()).zzd(this.zzb, new zzq(this.zza, (TaskCompletionSource) obj2));
    }
}
