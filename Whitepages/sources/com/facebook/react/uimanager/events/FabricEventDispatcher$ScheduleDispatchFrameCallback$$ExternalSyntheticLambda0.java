package com.facebook.react.uimanager.events;

import com.facebook.react.uimanager.events.FabricEventDispatcher;

public final /* synthetic */ class FabricEventDispatcher$ScheduleDispatchFrameCallback$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ FabricEventDispatcher.ScheduleDispatchFrameCallback f$0;

    public /* synthetic */ FabricEventDispatcher$ScheduleDispatchFrameCallback$$ExternalSyntheticLambda0(FabricEventDispatcher.ScheduleDispatchFrameCallback scheduleDispatchFrameCallback) {
        this.f$0 = scheduleDispatchFrameCallback;
    }

    public final void run() {
        FabricEventDispatcher.ScheduleDispatchFrameCallback.maybeScheduleDispatchOfBatchedEvents$lambda$0(this.f$0);
    }
}
