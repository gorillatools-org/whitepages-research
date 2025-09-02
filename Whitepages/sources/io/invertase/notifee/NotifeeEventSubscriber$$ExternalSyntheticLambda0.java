package io.invertase.notifee;

import app.notifee.core.event.BlockStateEvent;
import io.invertase.notifee.NotifeeReactUtils;

public final /* synthetic */ class NotifeeEventSubscriber$$ExternalSyntheticLambda0 implements NotifeeReactUtils.GenericCallback {
    public final /* synthetic */ BlockStateEvent f$0;

    public /* synthetic */ NotifeeEventSubscriber$$ExternalSyntheticLambda0(BlockStateEvent blockStateEvent) {
        this.f$0 = blockStateEvent;
    }

    public final void call() {
        this.f$0.setCompletionResult();
    }
}
