package com.google.android.play.core.review;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.review.internal.zzo;
import com.google.android.play.core.review.internal.zzt;
import com.google.android.play.core.review.internal.zzw;

public final class zzi {
    /* access modifiers changed from: private */
    public static final com.google.android.play.core.review.internal.zzi zzb = new com.google.android.play.core.review.internal.zzi("ReviewService");
    zzt zza;
    /* access modifiers changed from: private */
    public final String zzc;

    public zzi(Context context) {
        this.zzc = context.getPackageName();
        if (zzw.zza(context)) {
            Context context2 = context;
            this.zza = new zzt(context2, zzb, "com.google.android.finsky.inappreviewservice.InAppReviewService", new Intent("com.google.android.finsky.BIND_IN_APP_REVIEW_SERVICE").setPackage("com.android.vending"), zze.zza, (zzo) null, (byte[]) null);
        }
    }

    public final Task zza() {
        com.google.android.play.core.review.internal.zzi zzi = zzb;
        zzi.zzd("requestInAppReview (%s)", this.zzc);
        if (this.zza == null) {
            zzi.zzb("Play Store app is either not installed or not the official version", new Object[0]);
            return Tasks.forException(new ReviewException(-1));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.zzp(new zzf(this, taskCompletionSource, taskCompletionSource), taskCompletionSource);
        return taskCompletionSource.getTask();
    }
}
