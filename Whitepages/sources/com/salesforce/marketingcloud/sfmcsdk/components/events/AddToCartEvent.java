package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AddToCartEvent extends CartEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddToCartEvent(LineItem lineItem) {
        super("Add To Cart", CollectionsKt.listOf(lineItem), (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(lineItem, "lineItem");
    }
}
