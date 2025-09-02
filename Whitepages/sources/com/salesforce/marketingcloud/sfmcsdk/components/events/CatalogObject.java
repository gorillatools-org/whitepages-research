package com.salesforce.marketingcloud.sfmcsdk.components.events;

import com.salesforce.marketingcloud.storage.db.k;
import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CatalogObject {
    private final Map<String, Object> attributes;
    private final String id;
    private final Map<String, List<String>> relatedCatalogObjects;
    private final String type;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CatalogObject(String str, String str2) {
        this(str, str2, (Map) null, (Map) null, 12, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(str2, "id");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CatalogObject(String str, String str2, Map<String, ? extends Object> map) {
        this(str, str2, map, (Map) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(str2, "id");
        Intrinsics.checkNotNullParameter(map, k.a.h);
    }

    public CatalogObject(String str, String str2, Map<String, ? extends Object> map, Map<String, ? extends List<String>> map2) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(str2, "id");
        Intrinsics.checkNotNullParameter(map, k.a.h);
        Intrinsics.checkNotNullParameter(map2, "relatedCatalogObjects");
        this.type = str;
        this.id = str2;
        this.attributes = map;
        this.relatedCatalogObjects = map2;
    }

    public final String getType() {
        return this.type;
    }

    public final String getId() {
        return this.id;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CatalogObject(String str, String str2, Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? MapsKt.emptyMap() : map2);
    }

    public final Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public final Map<String, List<String>> getRelatedCatalogObjects() {
        return this.relatedCatalogObjects;
    }
}
