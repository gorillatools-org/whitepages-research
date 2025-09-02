package com.google.firebase.messaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledFuture;

public final /* synthetic */ class WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda1 implements OnCompleteListener {
    public final /* synthetic */ ScheduledFuture f$0;

    public /* synthetic */ WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda1(ScheduledFuture scheduledFuture) {
        this.f$0 = scheduledFuture;
    }

    public final void onComplete(Task task) {
        this.f$0.cancel(false);
    }
}
