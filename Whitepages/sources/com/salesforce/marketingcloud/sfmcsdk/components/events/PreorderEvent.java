package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PreorderEvent extends OrderEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PreorderEvent(Order order) {
        super("Preorder", order, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(order, "order");
    }
}
