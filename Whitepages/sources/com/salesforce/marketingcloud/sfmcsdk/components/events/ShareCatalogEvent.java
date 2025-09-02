package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ShareCatalogEvent extends CatalogEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ShareCatalogEvent(CatalogObject catalogObject) {
        super("Share Catalog Object", catalogObject, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
    }
}
