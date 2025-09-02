package com.google.firebase.remoteconfig.internal.rollouts;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;

public final /* synthetic */ class RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ RolloutsStateSubscriptionsHandler f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ RolloutsStateSubscriber f$2;

    public /* synthetic */ RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda0(RolloutsStateSubscriptionsHandler rolloutsStateSubscriptionsHandler, Task task, RolloutsStateSubscriber rolloutsStateSubscriber) {
        this.f$0 = rolloutsStateSubscriptionsHandler;
        this.f$1 = task;
        this.f$2 = rolloutsStateSubscriber;
    }

    public final void onSuccess(Object obj) {
        this.f$0.lambda$registerRolloutsStateSubscriber$1(this.f$1, this.f$2, (ConfigContainer) obj);
    }
}
