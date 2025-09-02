package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ViewCatalogDetailEvent extends CatalogEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewCatalogDetailEvent(CatalogObject catalogObject) {
        super("View Catalog Object Detail", catalogObject, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
    }
}
