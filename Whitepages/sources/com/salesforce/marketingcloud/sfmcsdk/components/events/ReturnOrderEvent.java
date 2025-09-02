package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReturnOrderEvent extends OrderEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReturnOrderEvent(Order order) {
        super("Return", order, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(order, "order");
    }
}
