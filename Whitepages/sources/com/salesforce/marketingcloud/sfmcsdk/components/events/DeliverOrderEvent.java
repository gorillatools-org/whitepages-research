package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DeliverOrderEvent extends OrderEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DeliverOrderEvent(Order order) {
        super("Deliver", order, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(order, "order");
    }
}
