package com.google.android.play.core.review;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;

public final class zzd implements ReviewManager {
    private final zzi zza;
    private final Handler zzb = new Handler(Looper.getMainLooper());

    zzd(zzi zzi) {
        this.zza = zzi;
    }

    public final Task launchReviewFlow(Activity activity, ReviewInfo reviewInfo) {
        if (reviewInfo.zzb()) {
            return Tasks.forResult(null);
        }
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", reviewInfo.zza());
        intent.putExtra("window_flags", activity.getWindow().getDecorView().getWindowSystemUiVisibility());
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        intent.putExtra("result_receiver", new zzc(this, this.zzb, taskCompletionSource));
        activity.startActivity(intent);
        return taskCompletionSource.getTask();
    }

    public final Task requestReviewFlow() {
        return this.zza.zza();
    }
}
