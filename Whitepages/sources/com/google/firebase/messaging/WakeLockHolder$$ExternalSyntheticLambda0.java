package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class WakeLockHolder$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ Intent f$0;

    public /* synthetic */ WakeLockHolder$$ExternalSyntheticLambda0(Intent intent) {
        this.f$0 = intent;
    }

    public final void onComplete(Task task) {
        WakeLockHolder.completeWakefulIntent(this.f$0);
    }
}
