package io.invertase.notifee;

import app.notifee.core.event.ForegroundServiceEvent;
import io.invertase.notifee.NotifeeReactUtils;

public final /* synthetic */ class NotifeeEventSubscriber$$ExternalSyntheticLambda1 implements NotifeeReactUtils.GenericCallback {
    public final /* synthetic */ ForegroundServiceEvent f$0;

    public /* synthetic */ NotifeeEventSubscriber$$ExternalSyntheticLambda1(ForegroundServiceEvent foregroundServiceEvent) {
        this.f$0 = foregroundServiceEvent;
    }

    public final void call() {
        this.f$0.setCompletionResult();
    }
}
