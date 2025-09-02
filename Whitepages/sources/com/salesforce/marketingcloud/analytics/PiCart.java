package com.salesforce.marketingcloud.analytics;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.g;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONException;

@MCKeep
public final class PiCart implements Parcelable {
    public static final Parcelable.Creator<PiCart> CREATOR = new b();
    public static final a Companion = new a((DefaultConstructorMarker) null);
    private final List<PiCartItem> cartItems;

    public static final class a {
        private a() {
        }

        public final PiCart a(List<PiCartItem> list) {
            Intrinsics.checkNotNullParameter(list, "cartItems");
            return new PiCart(list);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class b implements Parcelable.Creator<PiCart> {
        /* renamed from: a */
        public final PiCart createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i = 0; i != readInt; i++) {
                arrayList.add(PiCartItem.CREATOR.createFromParcel(parcel));
            }
            return new PiCart(arrayList);
        }

        /* renamed from: a */
        public final PiCart[] newArray(int i) {
            return new PiCart[i];
        }
    }

    static final class c extends Lambda implements Function0 {
        public static final c a = new c();

        c() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Failed to convert List<PiCartItem> into JSONArray for PiCart payload.";
        }
    }

    public PiCart(List<PiCartItem> list) {
        Intrinsics.checkNotNullParameter(list, "cartItems");
        this.cartItems = list;
    }

    public static /* synthetic */ PiCart copy$default(PiCart piCart, List<PiCartItem> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = piCart.cartItems;
        }
        return piCart.copy(list);
    }

    public static final PiCart create(List<PiCartItem> list) {
        return Companion.a(list);
    }

    /* renamed from: -deprecated_cartItems  reason: not valid java name */
    public final List<PiCartItem> m632deprecated_cartItems() {
        return this.cartItems;
    }

    /* renamed from: -toJson  reason: not valid java name */
    public final JSONArray m633toJson() {
        JSONArray jSONArray = new JSONArray();
        try {
            for (PiCartItem r2 : this.cartItems) {
                jSONArray.put(r2.m638toJson());
            }
        } catch (JSONException e) {
            g gVar = g.a;
            String str = AnalyticsManager.TAG;
            Intrinsics.checkNotNullExpressionValue(str, "TAG");
            gVar.b(str, (Throwable) e, (Function0) c.a);
        }
        return jSONArray;
    }

    public final List<PiCartItem> cartItems() {
        return this.cartItems;
    }

    public final List<PiCartItem> component1() {
        return this.cartItems;
    }

    public final PiCart copy(List<PiCartItem> list) {
        Intrinsics.checkNotNullParameter(list, "cartItems");
        return new PiCart(list);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PiCart) && Intrinsics.areEqual((Object) this.cartItems, (Object) ((PiCart) obj).cartItems);
    }

    public int hashCode() {
        return this.cartItems.hashCode();
    }

    public String toString() {
        List<PiCartItem> list = this.cartItems;
        return "PiCart(cartItems=" + list + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        List<PiCartItem> list = this.cartItems;
        parcel.writeInt(list.size());
        for (PiCartItem writeToParcel : list) {
            writeToParcel.writeToParcel(parcel, i);
        }
    }
}
