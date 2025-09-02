package com.google.android.play.core.review;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.review.internal.zzi;

final class zzh extends zzg {
    final String zzd;

    zzh(zzi zzi, TaskCompletionSource taskCompletionSource, String str) {
        super(zzi, new zzi("OnRequestInstallCallback"), taskCompletionSource);
        this.zzd = str;
    }

    public final void zzb(Bundle bundle) {
        super.zzb(bundle);
        this.zzb.trySetResult(new zza((PendingIntent) bundle.get("confirmation_intent"), bundle.getBoolean("is_review_no_op")));
    }
}
