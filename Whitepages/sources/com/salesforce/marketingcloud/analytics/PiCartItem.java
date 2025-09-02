package com.salesforce.marketingcloud.analytics;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.MCKeep;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@MCKeep
public final class PiCartItem implements Parcelable {
    public static final Parcelable.Creator<PiCartItem> CREATOR = new b();
    public static final a Companion = new a((DefaultConstructorMarker) null);
    private final String item;
    private final double price;
    private final int quantity;
    private final String uniqueId;

    public static final class a {
        private a() {
        }

        public final PiCartItem a(String str, int i, double d) {
            Intrinsics.checkNotNullParameter(str, "item");
            return new PiCartItem(str, i, d);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PiCartItem a(String str, int i, double d, String str2) {
            Intrinsics.checkNotNullParameter(str, "item");
            return new PiCartItem(str, i, d, str2);
        }
    }

    public static final class b implements Parcelable.Creator<PiCartItem> {
        /* renamed from: a */
        public final PiCartItem createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PiCartItem(parcel.readString(), parcel.readInt(), parcel.readDouble(), parcel.readString());
        }

        /* renamed from: a */
        public final PiCartItem[] newArray(int i) {
            return new PiCartItem[i];
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PiCartItem(String str, int i, double d) {
        this(str, i, d, (String) null);
        Intrinsics.checkNotNullParameter(str, "item");
    }

    public static /* synthetic */ PiCartItem copy$default(PiCartItem piCartItem, String str, int i, double d, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = piCartItem.item;
        }
        if ((i2 & 2) != 0) {
            i = piCartItem.quantity;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            d = piCartItem.price;
        }
        double d2 = d;
        if ((i2 & 8) != 0) {
            str2 = piCartItem.uniqueId;
        }
        return piCartItem.copy(str, i3, d2, str2);
    }

    public static final PiCartItem create(String str, int i, double d) {
        return Companion.a(str, i, d);
    }

    /* renamed from: -deprecated_item  reason: not valid java name */
    public final String m634deprecated_item() {
        return this.item;
    }

    /* renamed from: -deprecated_price  reason: not valid java name */
    public final double m635deprecated_price() {
        return this.price;
    }

    /* renamed from: -deprecated_quantity  reason: not valid java name */
    public final int m636deprecated_quantity() {
        return this.quantity;
    }

    /* renamed from: -deprecated_uniqueId  reason: not valid java name */
    public final String m637deprecated_uniqueId() {
        return this.uniqueId;
    }

    /* renamed from: -toJson  reason: not valid java name */
    public final JSONObject m638toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("item", this.item);
        jSONObject.put(FirebaseAnalytics.Param.QUANTITY, this.quantity);
        jSONObject.put(FirebaseAnalytics.Param.PRICE, this.price);
        String str = this.uniqueId;
        if (str != null) {
            jSONObject.put("unique_id", str);
        }
        return jSONObject;
    }

    public final String component1() {
        return this.item;
    }

    public final int component2() {
        return this.quantity;
    }

    public final double component3() {
        return this.price;
    }

    public final String component4() {
        return this.uniqueId;
    }

    public final PiCartItem copy(String str, int i, double d, String str2) {
        Intrinsics.checkNotNullParameter(str, "item");
        return new PiCartItem(str, i, d, str2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PiCartItem)) {
            return false;
        }
        PiCartItem piCartItem = (PiCartItem) obj;
        return Intrinsics.areEqual((Object) this.item, (Object) piCartItem.item) && this.quantity == piCartItem.quantity && Double.compare(this.price, piCartItem.price) == 0 && Intrinsics.areEqual((Object) this.uniqueId, (Object) piCartItem.uniqueId);
    }

    public int hashCode() {
        int hashCode = ((((this.item.hashCode() * 31) + Integer.hashCode(this.quantity)) * 31) + Double.hashCode(this.price)) * 31;
        String str = this.uniqueId;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final String item() {
        return this.item;
    }

    public final double price() {
        return this.price;
    }

    public final int quantity() {
        return this.quantity;
    }

    public String toString() {
        String str = this.item;
        int i = this.quantity;
        double d = this.price;
        String str2 = this.uniqueId;
        return "PiCartItem(item=" + str + ", quantity=" + i + ", price=" + d + ", uniqueId=" + str2 + ")";
    }

    public final String uniqueId() {
        return this.uniqueId;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.item);
        parcel.writeInt(this.quantity);
        parcel.writeDouble(this.price);
        parcel.writeString(this.uniqueId);
    }

    public PiCartItem(String str, int i, double d, String str2) {
        Intrinsics.checkNotNullParameter(str, "item");
        this.item = str;
        this.quantity = i;
        this.price = d;
        this.uniqueId = str2;
    }

    public static final PiCartItem create(String str, int i, double d, String str2) {
        return Companion.a(str, i, d, str2);
    }
}
