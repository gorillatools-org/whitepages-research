package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class EventManager$Companion$publish$1$1$3 extends Lambda implements Function0 {
    final /* synthetic */ Event[] $events;
    final /* synthetic */ EventSubscriber $subscriber;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventManager$Companion$publish$1$1$3(Event[] eventArr, EventSubscriber eventSubscriber) {
        super(0);
        this.$events = eventArr;
        this.$subscriber = eventSubscriber;
    }

    public final String invoke() {
        return "Failed to publish event(s) " + this.$events + " to subscriber " + this.$subscriber + '.';
    }
}
