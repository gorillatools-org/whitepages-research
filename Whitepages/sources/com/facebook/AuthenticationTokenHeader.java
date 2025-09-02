package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.facebook.internal.Validate;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.json.JSONException;
import org.json.JSONObject;

public final class AuthenticationTokenHeader implements Parcelable {
    public static final Parcelable.Creator<AuthenticationTokenHeader> CREATOR = new AuthenticationTokenHeader$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String alg;
    private final String kid;
    private final String typ;

    public int describeContents() {
        return 0;
    }

    public final String getKid() {
        return this.kid;
    }

    public AuthenticationTokenHeader(String str) {
        Intrinsics.checkNotNullParameter(str, "encodedHeaderString");
        if (isValidHeader(str)) {
            byte[] decode = Base64.decode(str, 0);
            Intrinsics.checkNotNullExpressionValue(decode, "decodedBytes");
            JSONObject jSONObject = new JSONObject(new String(decode, Charsets.UTF_8));
            String string = jSONObject.getString("alg");
            Intrinsics.checkNotNullExpressionValue(string, "jsonObj.getString(\"alg\")");
            this.alg = string;
            String string2 = jSONObject.getString(ClientData.KEY_TYPE);
            Intrinsics.checkNotNullExpressionValue(string2, "jsonObj.getString(\"typ\")");
            this.typ = string2;
            String string3 = jSONObject.getString("kid");
            Intrinsics.checkNotNullExpressionValue(string3, "jsonObj.getString(\"kid\")");
            this.kid = string3;
            return;
        }
        throw new IllegalArgumentException("Invalid Header");
    }

    public AuthenticationTokenHeader(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.alg = Validate.notNullOrEmpty(parcel.readString(), "alg");
        this.typ = Validate.notNullOrEmpty(parcel.readString(), ClientData.KEY_TYPE);
        this.kid = Validate.notNullOrEmpty(parcel.readString(), "kid");
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeString(this.alg);
        parcel.writeString(this.typ);
        parcel.writeString(this.kid);
    }

    public String toString() {
        String jSONObject = toJSONObject$facebook_core_release().toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "headerJsonObject.toString()");
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthenticationTokenHeader)) {
            return false;
        }
        AuthenticationTokenHeader authenticationTokenHeader = (AuthenticationTokenHeader) obj;
        if (!Intrinsics.areEqual((Object) this.alg, (Object) authenticationTokenHeader.alg) || !Intrinsics.areEqual((Object) this.typ, (Object) authenticationTokenHeader.typ) || !Intrinsics.areEqual((Object) this.kid, (Object) authenticationTokenHeader.kid)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((527 + this.alg.hashCode()) * 31) + this.typ.hashCode()) * 31) + this.kid.hashCode();
    }

    private final boolean isValidHeader(String str) {
        Validate.notEmpty(str, "encodedHeaderString");
        byte[] decode = Base64.decode(str, 0);
        Intrinsics.checkNotNullExpressionValue(decode, "decodedBytes");
        try {
            JSONObject jSONObject = new JSONObject(new String(decode, Charsets.UTF_8));
            String optString = jSONObject.optString("alg");
            Intrinsics.checkNotNullExpressionValue(optString, "alg");
            boolean z = optString.length() > 0 && Intrinsics.areEqual((Object) optString, (Object) "RS256");
            String optString2 = jSONObject.optString("kid");
            Intrinsics.checkNotNullExpressionValue(optString2, "jsonObj.optString(\"kid\")");
            boolean z2 = optString2.length() > 0;
            String optString3 = jSONObject.optString(ClientData.KEY_TYPE);
            Intrinsics.checkNotNullExpressionValue(optString3, "jsonObj.optString(\"typ\")");
            boolean z3 = optString3.length() > 0;
            if (!z || !z2 || !z3) {
                return false;
            }
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    public final JSONObject toJSONObject$facebook_core_release() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("alg", this.alg);
        jSONObject.put(ClientData.KEY_TYPE, this.typ);
        jSONObject.put("kid", this.kid);
        return jSONObject;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
