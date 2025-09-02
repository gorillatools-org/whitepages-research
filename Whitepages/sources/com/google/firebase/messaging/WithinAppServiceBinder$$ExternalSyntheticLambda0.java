package com.google.firebase.messaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.WithinAppServiceConnection;

public final /* synthetic */ class WithinAppServiceBinder$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ WithinAppServiceConnection.BindRequest f$0;

    public /* synthetic */ WithinAppServiceBinder$$ExternalSyntheticLambda0(WithinAppServiceConnection.BindRequest bindRequest) {
        this.f$0 = bindRequest;
    }

    public final void onComplete(Task task) {
        this.f$0.finish();
    }
}
