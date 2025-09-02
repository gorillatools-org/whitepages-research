package com.google.android.play.core.review;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzc extends ResultReceiver {
    final /* synthetic */ TaskCompletionSource zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzc(zzd zzd, Handler handler, TaskCompletionSource taskCompletionSource) {
        super(handler);
        this.zza = taskCompletionSource;
    }

    public final void onReceiveResult(int i, Bundle bundle) {
        this.zza.trySetResult(null);
    }
}
