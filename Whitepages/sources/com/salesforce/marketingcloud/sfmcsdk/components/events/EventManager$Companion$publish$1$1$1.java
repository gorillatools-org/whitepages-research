package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

final class EventManager$Companion$publish$1$1$1 extends Lambda implements Function0 {
    final /* synthetic */ Event[] $events;
    final /* synthetic */ EventSubscriber $subscriber;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventManager$Companion$publish$1$1$1(Event[] eventArr, EventSubscriber eventSubscriber) {
        super(0);
        this.$events = eventArr;
        this.$subscriber = eventSubscriber;
    }

    public final String invoke() {
        StringBuilder sb = new StringBuilder();
        sb.append("Publishing events: ");
        String str = "";
        for (Event event : ArraysKt.filterNotNull(this.$events)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(StringsKt.isBlank(str) ? "" : ", ");
            sb2.append(Reflection.getOrCreateKotlinClass(event.getClass()).getSimpleName());
            sb2.append("( ");
            sb2.append(event.name());
            sb2.append(" )");
            str = sb2.toString();
        }
        sb.append(str);
        sb.append(" to subscriber: ");
        sb.append(this.$subscriber);
        return sb.toString();
    }
}
