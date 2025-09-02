package com.salesforce.marketingcloud.sfmcsdk.components.events;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReplaceCartEvent extends CartEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReplaceCartEvent(List<LineItem> list) {
        super("Replace Cart", list, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "lineItems");
    }
}
