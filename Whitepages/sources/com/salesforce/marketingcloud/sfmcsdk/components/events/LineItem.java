package com.salesforce.marketingcloud.sfmcsdk.components.events;

import com.salesforce.marketingcloud.storage.db.k;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LineItem {
    private Map<String, ? extends Object> attributes;
    private final String catalogObjectId;
    private final String catalogObjectType;
    private String currency;
    private Double price;
    private final int quantity;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LineItem(String str, String str2, int i) {
        this(str, str2, i, (Double) null, (String) null, (Map) null, 56, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "catalogObjectType");
        Intrinsics.checkNotNullParameter(str2, "catalogObjectId");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LineItem(String str, String str2, int i, Double d) {
        this(str, str2, i, d, (String) null, (Map) null, 48, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "catalogObjectType");
        Intrinsics.checkNotNullParameter(str2, "catalogObjectId");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LineItem(String str, String str2, int i, Double d, String str3) {
        this(str, str2, i, d, str3, (Map) null, 32, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "catalogObjectType");
        Intrinsics.checkNotNullParameter(str2, "catalogObjectId");
    }

    public static /* synthetic */ LineItem copy$default(LineItem lineItem, String str, String str2, int i, Double d, String str3, Map<String, ? extends Object> map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = lineItem.catalogObjectType;
        }
        if ((i2 & 2) != 0) {
            str2 = lineItem.catalogObjectId;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            i = lineItem.quantity;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            d = lineItem.price;
        }
        Double d2 = d;
        if ((i2 & 16) != 0) {
            str3 = lineItem.currency;
        }
        String str5 = str3;
        if ((i2 & 32) != 0) {
            map = lineItem.attributes;
        }
        return lineItem.copy(str, str4, i3, d2, str5, map);
    }

    public final String component1() {
        return this.catalogObjectType;
    }

    public final String component2() {
        return this.catalogObjectId;
    }

    public final int component3() {
        return this.quantity;
    }

    public final Double component4() {
        return this.price;
    }

    public final String component5() {
        return this.currency;
    }

    public final Map<String, Object> component6() {
        return this.attributes;
    }

    public final LineItem copy(String str, String str2, int i, Double d, String str3, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "catalogObjectType");
        Intrinsics.checkNotNullParameter(str2, "catalogObjectId");
        Intrinsics.checkNotNullParameter(map, k.a.h);
        return new LineItem(str, str2, i, d, str3, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LineItem)) {
            return false;
        }
        LineItem lineItem = (LineItem) obj;
        return Intrinsics.areEqual((Object) this.catalogObjectType, (Object) lineItem.catalogObjectType) && Intrinsics.areEqual((Object) this.catalogObjectId, (Object) lineItem.catalogObjectId) && this.quantity == lineItem.quantity && Intrinsics.areEqual((Object) this.price, (Object) lineItem.price) && Intrinsics.areEqual((Object) this.currency, (Object) lineItem.currency) && Intrinsics.areEqual((Object) this.attributes, (Object) lineItem.attributes);
    }

    public int hashCode() {
        int hashCode = ((((this.catalogObjectType.hashCode() * 31) + this.catalogObjectId.hashCode()) * 31) + Integer.hashCode(this.quantity)) * 31;
        Double d = this.price;
        int i = 0;
        int hashCode2 = (hashCode + (d == null ? 0 : d.hashCode())) * 31;
        String str = this.currency;
        if (str != null) {
            i = str.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.attributes.hashCode();
    }

    public String toString() {
        return "LineItem(catalogObjectType=" + this.catalogObjectType + ", catalogObjectId=" + this.catalogObjectId + ", quantity=" + this.quantity + ", price=" + this.price + ", currency=" + this.currency + ", attributes=" + this.attributes + ')';
    }

    public LineItem(String str, String str2, int i, Double d, String str3, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "catalogObjectType");
        Intrinsics.checkNotNullParameter(str2, "catalogObjectId");
        Intrinsics.checkNotNullParameter(map, k.a.h);
        this.catalogObjectType = str;
        this.catalogObjectId = str2;
        this.quantity = i;
        this.price = d;
        this.currency = str3;
        this.attributes = map;
    }

    public final String getCatalogObjectType() {
        return this.catalogObjectType;
    }

    public final String getCatalogObjectId() {
        return this.catalogObjectId;
    }

    public final int getQuantity() {
        return this.quantity;
    }

    public final Double getPrice() {
        return this.price;
    }

    public final void setPrice(Double d) {
        this.price = d;
    }

    public final String getCurrency() {
        return this.currency;
    }

    public final void setCurrency(String str) {
        this.currency = str;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ LineItem(java.lang.String r10, java.lang.String r11, int r12, java.lang.Double r13, java.lang.String r14, java.util.Map r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r9 = this;
            r0 = r16 & 8
            r1 = 0
            if (r0 == 0) goto L_0x0007
            r6 = r1
            goto L_0x0008
        L_0x0007:
            r6 = r13
        L_0x0008:
            r0 = r16 & 16
            if (r0 == 0) goto L_0x000e
            r7 = r1
            goto L_0x000f
        L_0x000e:
            r7 = r14
        L_0x000f:
            r0 = r16 & 32
            if (r0 == 0) goto L_0x0019
            java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
            r8 = r0
            goto L_0x001a
        L_0x0019:
            r8 = r15
        L_0x001a:
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.components.events.LineItem.<init>(java.lang.String, java.lang.String, int, java.lang.Double, java.lang.String, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.attributes = map;
    }
}
