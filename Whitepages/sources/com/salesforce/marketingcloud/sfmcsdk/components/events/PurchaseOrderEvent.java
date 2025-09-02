package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PurchaseOrderEvent extends OrderEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PurchaseOrderEvent(Order order) {
        super("Purchase", order, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(order, "order");
    }
}
