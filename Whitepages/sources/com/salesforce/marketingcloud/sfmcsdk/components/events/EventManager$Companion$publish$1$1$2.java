package com.salesforce.marketingcloud.sfmcsdk.components.events;

import java.util.Arrays;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class EventManager$Companion$publish$1$1$2 extends Lambda implements Function0 {
    final /* synthetic */ Event[] $events;
    final /* synthetic */ EventSubscriber $subscriber;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventManager$Companion$publish$1$1$2(EventSubscriber eventSubscriber, Event[] eventArr) {
        super(0);
        this.$subscriber = eventSubscriber;
        this.$events = eventArr;
    }

    public final void invoke() {
        EventSubscriber eventSubscriber = this.$subscriber;
        Event[] eventArr = this.$events;
        eventSubscriber.onEventPublished((Event[]) Arrays.copyOf(eventArr, eventArr.length));
    }
}
