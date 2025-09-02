package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CommentCatalogEvent extends CatalogEvent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CommentCatalogEvent(CatalogObject catalogObject) {
        super("Comment Catalog Object", catalogObject, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
    }
}
