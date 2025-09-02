package com.salesforce.marketingcloud.analytics;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.MCKeep;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@MCKeep
public final class PiOrder implements Parcelable {
    public static final Parcelable.Creator<PiOrder> CREATOR = new b();
    public static final a Companion = new a((DefaultConstructorMarker) null);
    private final PiCart cart;
    private final double discount;
    private final String orderNumber;
    private final double shipping;

    public static final class a {
        private a() {
        }

        public final PiOrder a(PiCart piCart, String str, double d, double d2) {
            Intrinsics.checkNotNullParameter(piCart, "cart");
            Intrinsics.checkNotNullParameter(str, "orderNumber");
            return new PiOrder(piCart, str, d, d2);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class b implements Parcelable.Creator<PiOrder> {
        /* renamed from: a */
        public final PiOrder createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PiOrder(PiCart.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readDouble(), parcel.readDouble());
        }

        /* renamed from: a */
        public final PiOrder[] newArray(int i) {
            return new PiOrder[i];
        }
    }

    public PiOrder(PiCart piCart, String str, double d, double d2) {
        Intrinsics.checkNotNullParameter(piCart, "cart");
        Intrinsics.checkNotNullParameter(str, "orderNumber");
        this.cart = piCart;
        this.orderNumber = str;
        this.shipping = d;
        this.discount = d2;
    }

    public static /* synthetic */ PiOrder copy$default(PiOrder piOrder, PiCart piCart, String str, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            piCart = piOrder.cart;
        }
        if ((i & 2) != 0) {
            str = piOrder.orderNumber;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            d = piOrder.shipping;
        }
        double d3 = d;
        if ((i & 8) != 0) {
            d2 = piOrder.discount;
        }
        return piOrder.copy(piCart, str2, d3, d2);
    }

    public static final PiOrder create(PiCart piCart, String str, double d, double d2) {
        return Companion.a(piCart, str, d, d2);
    }

    /* renamed from: -deprecated_cart  reason: not valid java name */
    public final PiCart m639deprecated_cart() {
        return this.cart;
    }

    /* renamed from: -deprecated_discount  reason: not valid java name */
    public final double m640deprecated_discount() {
        return this.discount;
    }

    /* renamed from: -deprecated_orderNumber  reason: not valid java name */
    public final String m641deprecated_orderNumber() {
        return this.orderNumber;
    }

    /* renamed from: -deprecated_shipping  reason: not valid java name */
    public final double m642deprecated_shipping() {
        return this.shipping;
    }

    /* renamed from: -toJson  reason: not valid java name */
    public final JSONObject m643toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cart", this.cart.m633toJson());
        jSONObject.put("order_number", this.orderNumber);
        jSONObject.put(FirebaseAnalytics.Param.SHIPPING, this.shipping);
        jSONObject.put(FirebaseAnalytics.Param.DISCOUNT, this.discount);
        return jSONObject;
    }

    public final PiCart cart() {
        return this.cart;
    }

    public final PiCart component1() {
        return this.cart;
    }

    public final String component2() {
        return this.orderNumber;
    }

    public final double component3() {
        return this.shipping;
    }

    public final double component4() {
        return this.discount;
    }

    public final PiOrder copy(PiCart piCart, String str, double d, double d2) {
        Intrinsics.checkNotNullParameter(piCart, "cart");
        Intrinsics.checkNotNullParameter(str, "orderNumber");
        return new PiOrder(piCart, str, d, d2);
    }

    public int describeContents() {
        return 0;
    }

    public final double discount() {
        return this.discount;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PiOrder)) {
            return false;
        }
        PiOrder piOrder = (PiOrder) obj;
        return Intrinsics.areEqual((Object) this.cart, (Object) piOrder.cart) && Intrinsics.areEqual((Object) this.orderNumber, (Object) piOrder.orderNumber) && Double.compare(this.shipping, piOrder.shipping) == 0 && Double.compare(this.discount, piOrder.discount) == 0;
    }

    public int hashCode() {
        return (((((this.cart.hashCode() * 31) + this.orderNumber.hashCode()) * 31) + Double.hashCode(this.shipping)) * 31) + Double.hashCode(this.discount);
    }

    public final String orderNumber() {
        return this.orderNumber;
    }

    public final double shipping() {
        return this.shipping;
    }

    public String toString() {
        PiCart piCart = this.cart;
        String str = this.orderNumber;
        double d = this.shipping;
        double d2 = this.discount;
        return "PiOrder(cart=" + piCart + ", orderNumber=" + str + ", shipping=" + d + ", discount=" + d2 + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        this.cart.writeToParcel(parcel, i);
        parcel.writeString(this.orderNumber);
        parcel.writeDouble(this.shipping);
        parcel.writeDouble(this.discount);
    }
}
