package com.salesforce.marketingcloud.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.storage.db.h;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@MCKeep
public final class LatLon implements Parcelable {
    public static final Parcelable.Creator<LatLon> CREATOR = new a();
    private final double latitude;
    private final double longitude;

    public static final class a implements Parcelable.Creator<LatLon> {
        /* renamed from: a */
        public final LatLon createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LatLon(parcel.readDouble(), parcel.readDouble());
        }

        /* renamed from: a */
        public final LatLon[] newArray(int i) {
            return new LatLon[i];
        }
    }

    public LatLon(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public static /* synthetic */ LatLon copy$default(LatLon latLon, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = latLon.latitude;
        }
        if ((i & 2) != 0) {
            d2 = latLon.longitude;
        }
        return latLon.copy(d, d2);
    }

    /* renamed from: -deprecated_latitude  reason: not valid java name */
    public final double m644deprecated_latitude() {
        return this.latitude;
    }

    /* renamed from: -deprecated_longitude  reason: not valid java name */
    public final double m645deprecated_longitude() {
        return this.longitude;
    }

    public final double component1() {
        return this.latitude;
    }

    public final double component2() {
        return this.longitude;
    }

    public final LatLon copy(double d, double d2) {
        return new LatLon(d, d2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLon)) {
            return false;
        }
        LatLon latLon = (LatLon) obj;
        return Double.compare(this.latitude, latLon.latitude) == 0 && Double.compare(this.longitude, latLon.longitude) == 0;
    }

    public int hashCode() {
        return (Double.hashCode(this.latitude) * 31) + Double.hashCode(this.longitude);
    }

    public final double latitude() {
        return this.latitude;
    }

    public final double longitude() {
        return this.longitude;
    }

    public String toString() {
        double d = this.latitude;
        double d2 = this.longitude;
        return "LatLon(latitude=" + d + ", longitude=" + d2 + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LatLon(JSONObject jSONObject) throws JSONException {
        this(jSONObject.getDouble(h.a.b), jSONObject.getDouble(h.a.c));
        Intrinsics.checkNotNullParameter(jSONObject, "json");
    }
}
