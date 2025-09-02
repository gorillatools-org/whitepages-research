package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.google.android.gms.common.Scopes;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AuthenticationTokenClaims implements Parcelable {
    public static final Parcelable.Creator<AuthenticationTokenClaims> CREATOR = new AuthenticationTokenClaims$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String aud;
    private final String email;
    private final long exp;
    private final String familyName;
    private final String givenName;
    private final long iat;
    private final String iss;
    private final String jti;
    private final String middleName;
    private final String name;
    private final String nonce;
    private final String picture;
    private final String sub;
    private final Map userAgeRange;
    private final String userBirthday;
    private final Set userFriends;
    private final String userGender;
    private final Map userHometown;
    private final String userLink;
    private final Map userLocation;

    public int describeContents() {
        return 0;
    }

    public AuthenticationTokenClaims(String str, String str2) {
        Set set;
        Map map;
        Map map2;
        Intrinsics.checkNotNullParameter(str, "encodedClaims");
        Intrinsics.checkNotNullParameter(str2, "expectedNonce");
        Validate.notEmpty(str, "encodedClaims");
        byte[] decode = Base64.decode(str, 8);
        Intrinsics.checkNotNullExpressionValue(decode, "decodedBytes");
        JSONObject jSONObject = new JSONObject(new String(decode, Charsets.UTF_8));
        if (isValidClaims(jSONObject, str2)) {
            String string = jSONObject.getString("jti");
            Intrinsics.checkNotNullExpressionValue(string, "jsonObj.getString(JSON_KEY_JIT)");
            this.jti = string;
            String string2 = jSONObject.getString("iss");
            Intrinsics.checkNotNullExpressionValue(string2, "jsonObj.getString(JSON_KEY_ISS)");
            this.iss = string2;
            String string3 = jSONObject.getString("aud");
            Intrinsics.checkNotNullExpressionValue(string3, "jsonObj.getString(JSON_KEY_AUD)");
            this.aud = string3;
            String string4 = jSONObject.getString("nonce");
            Intrinsics.checkNotNullExpressionValue(string4, "jsonObj.getString(JSON_KEY_NONCE)");
            this.nonce = string4;
            this.exp = jSONObject.getLong("exp");
            this.iat = jSONObject.getLong("iat");
            String string5 = jSONObject.getString("sub");
            Intrinsics.checkNotNullExpressionValue(string5, "jsonObj.getString(JSON_KEY_SUB)");
            this.sub = string5;
            Companion companion = Companion;
            this.name = companion.getNullableString$facebook_core_release(jSONObject, "name");
            this.givenName = companion.getNullableString$facebook_core_release(jSONObject, "given_name");
            this.middleName = companion.getNullableString$facebook_core_release(jSONObject, "middle_name");
            this.familyName = companion.getNullableString$facebook_core_release(jSONObject, "family_name");
            this.email = companion.getNullableString$facebook_core_release(jSONObject, Scopes.EMAIL);
            this.picture = companion.getNullableString$facebook_core_release(jSONObject, "picture");
            JSONArray optJSONArray = jSONObject.optJSONArray("user_friends");
            Map map3 = null;
            if (optJSONArray == null) {
                set = null;
            } else {
                set = Collections.unmodifiableSet(Utility.jsonArrayToSet(optJSONArray));
            }
            this.userFriends = set;
            this.userBirthday = companion.getNullableString$facebook_core_release(jSONObject, "user_birthday");
            JSONObject optJSONObject = jSONObject.optJSONObject("user_age_range");
            if (optJSONObject == null) {
                map = null;
            } else {
                map = Collections.unmodifiableMap(Utility.convertJSONObjectToHashMap(optJSONObject));
            }
            this.userAgeRange = map;
            JSONObject optJSONObject2 = jSONObject.optJSONObject("user_hometown");
            if (optJSONObject2 == null) {
                map2 = null;
            } else {
                map2 = Collections.unmodifiableMap(Utility.convertJSONObjectToStringMap(optJSONObject2));
            }
            this.userHometown = map2;
            JSONObject optJSONObject3 = jSONObject.optJSONObject("user_location");
            this.userLocation = optJSONObject3 != null ? Collections.unmodifiableMap(Utility.convertJSONObjectToStringMap(optJSONObject3)) : map3;
            this.userGender = companion.getNullableString$facebook_core_release(jSONObject, "user_gender");
            this.userLink = companion.getNullableString$facebook_core_release(jSONObject, "user_link");
            return;
        }
        throw new IllegalArgumentException("Invalid claims");
    }

    public AuthenticationTokenClaims(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.jti = Validate.notNullOrEmpty(parcel.readString(), "jti");
        this.iss = Validate.notNullOrEmpty(parcel.readString(), "iss");
        this.aud = Validate.notNullOrEmpty(parcel.readString(), "aud");
        this.nonce = Validate.notNullOrEmpty(parcel.readString(), "nonce");
        this.exp = parcel.readLong();
        this.iat = parcel.readLong();
        this.sub = Validate.notNullOrEmpty(parcel.readString(), "sub");
        this.name = parcel.readString();
        this.givenName = parcel.readString();
        this.middleName = parcel.readString();
        this.familyName = parcel.readString();
        this.email = parcel.readString();
        this.picture = parcel.readString();
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        Map map = null;
        this.userFriends = createStringArrayList != null ? Collections.unmodifiableSet(new HashSet(createStringArrayList)) : null;
        this.userBirthday = parcel.readString();
        HashMap readHashMap = parcel.readHashMap(IntCompanionObject.INSTANCE.getClass().getClassLoader());
        readHashMap = readHashMap == null ? null : readHashMap;
        this.userAgeRange = readHashMap != null ? Collections.unmodifiableMap(readHashMap) : null;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        HashMap readHashMap2 = parcel.readHashMap(stringCompanionObject.getClass().getClassLoader());
        readHashMap2 = readHashMap2 == null ? null : readHashMap2;
        this.userHometown = readHashMap2 != null ? Collections.unmodifiableMap(readHashMap2) : null;
        HashMap readHashMap3 = parcel.readHashMap(stringCompanionObject.getClass().getClassLoader());
        readHashMap3 = readHashMap3 == null ? null : readHashMap3;
        this.userLocation = readHashMap3 != null ? Collections.unmodifiableMap(readHashMap3) : map;
        this.userGender = parcel.readString();
        this.userLink = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeString(this.jti);
        parcel.writeString(this.iss);
        parcel.writeString(this.aud);
        parcel.writeString(this.nonce);
        parcel.writeLong(this.exp);
        parcel.writeLong(this.iat);
        parcel.writeString(this.sub);
        parcel.writeString(this.name);
        parcel.writeString(this.givenName);
        parcel.writeString(this.middleName);
        parcel.writeString(this.familyName);
        parcel.writeString(this.email);
        parcel.writeString(this.picture);
        if (this.userFriends == null) {
            parcel.writeStringList((List) null);
        } else {
            parcel.writeStringList(new ArrayList(this.userFriends));
        }
        parcel.writeString(this.userBirthday);
        parcel.writeMap(this.userAgeRange);
        parcel.writeMap(this.userHometown);
        parcel.writeMap(this.userLocation);
        parcel.writeString(this.userGender);
        parcel.writeString(this.userLink);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthenticationTokenClaims)) {
            return false;
        }
        AuthenticationTokenClaims authenticationTokenClaims = (AuthenticationTokenClaims) obj;
        if (!Intrinsics.areEqual((Object) this.jti, (Object) authenticationTokenClaims.jti) || !Intrinsics.areEqual((Object) this.iss, (Object) authenticationTokenClaims.iss) || !Intrinsics.areEqual((Object) this.aud, (Object) authenticationTokenClaims.aud) || !Intrinsics.areEqual((Object) this.nonce, (Object) authenticationTokenClaims.nonce) || this.exp != authenticationTokenClaims.exp || this.iat != authenticationTokenClaims.iat || !Intrinsics.areEqual((Object) this.sub, (Object) authenticationTokenClaims.sub) || !Intrinsics.areEqual((Object) this.name, (Object) authenticationTokenClaims.name) || !Intrinsics.areEqual((Object) this.givenName, (Object) authenticationTokenClaims.givenName) || !Intrinsics.areEqual((Object) this.middleName, (Object) authenticationTokenClaims.middleName) || !Intrinsics.areEqual((Object) this.familyName, (Object) authenticationTokenClaims.familyName) || !Intrinsics.areEqual((Object) this.email, (Object) authenticationTokenClaims.email) || !Intrinsics.areEqual((Object) this.picture, (Object) authenticationTokenClaims.picture) || !Intrinsics.areEqual((Object) this.userFriends, (Object) authenticationTokenClaims.userFriends) || !Intrinsics.areEqual((Object) this.userBirthday, (Object) authenticationTokenClaims.userBirthday) || !Intrinsics.areEqual((Object) this.userAgeRange, (Object) authenticationTokenClaims.userAgeRange) || !Intrinsics.areEqual((Object) this.userHometown, (Object) authenticationTokenClaims.userHometown) || !Intrinsics.areEqual((Object) this.userLocation, (Object) authenticationTokenClaims.userLocation) || !Intrinsics.areEqual((Object) this.userGender, (Object) authenticationTokenClaims.userGender) || !Intrinsics.areEqual((Object) this.userLink, (Object) authenticationTokenClaims.userLink)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((((((((((((527 + this.jti.hashCode()) * 31) + this.iss.hashCode()) * 31) + this.aud.hashCode()) * 31) + this.nonce.hashCode()) * 31) + Long.hashCode(this.exp)) * 31) + Long.hashCode(this.iat)) * 31) + this.sub.hashCode()) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.givenName;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.middleName;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.familyName;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.email;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.picture;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        Set set = this.userFriends;
        int hashCode8 = (hashCode7 + (set != null ? set.hashCode() : 0)) * 31;
        String str7 = this.userBirthday;
        int hashCode9 = (hashCode8 + (str7 != null ? str7.hashCode() : 0)) * 31;
        Map map = this.userAgeRange;
        int hashCode10 = (hashCode9 + (map != null ? map.hashCode() : 0)) * 31;
        Map map2 = this.userHometown;
        int hashCode11 = (hashCode10 + (map2 != null ? map2.hashCode() : 0)) * 31;
        Map map3 = this.userLocation;
        int hashCode12 = (hashCode11 + (map3 != null ? map3.hashCode() : 0)) * 31;
        String str8 = this.userGender;
        int hashCode13 = (hashCode12 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.userLink;
        if (str9 != null) {
            i = str9.hashCode();
        }
        return hashCode13 + i;
    }

    public String toString() {
        String jSONObject = toJSONObject$facebook_core_release().toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "claimsJsonObject.toString()");
        return jSONObject;
    }

    private final boolean isValidClaims(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return false;
        }
        String optString = jSONObject.optString("jti");
        Intrinsics.checkNotNullExpressionValue(optString, "jti");
        if (optString.length() == 0) {
            return false;
        }
        try {
            String optString2 = jSONObject.optString("iss");
            Intrinsics.checkNotNullExpressionValue(optString2, "iss");
            if (optString2.length() != 0) {
                if (Intrinsics.areEqual((Object) new URL(optString2).getHost(), (Object) "facebook.com") || Intrinsics.areEqual((Object) new URL(optString2).getHost(), (Object) "www.facebook.com")) {
                    String optString3 = jSONObject.optString("aud");
                    Intrinsics.checkNotNullExpressionValue(optString3, "aud");
                    if (optString3.length() == 0 || !Intrinsics.areEqual((Object) optString3, (Object) FacebookSdk.getApplicationId())) {
                        return false;
                    }
                    long j = (long) 1000;
                    if (new Date().after(new Date(jSONObject.optLong("exp") * j))) {
                        return false;
                    }
                    if (new Date().after(new Date((jSONObject.optLong("iat") * j) + 600000))) {
                        return false;
                    }
                    String optString4 = jSONObject.optString("sub");
                    Intrinsics.checkNotNullExpressionValue(optString4, "sub");
                    if (optString4.length() == 0) {
                        return false;
                    }
                    String optString5 = jSONObject.optString("nonce");
                    Intrinsics.checkNotNullExpressionValue(optString5, "nonce");
                    if (optString5.length() != 0 && Intrinsics.areEqual((Object) optString5, (Object) str)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    public final JSONObject toJSONObject$facebook_core_release() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("jti", this.jti);
        jSONObject.put("iss", this.iss);
        jSONObject.put("aud", this.aud);
        jSONObject.put("nonce", this.nonce);
        jSONObject.put("exp", this.exp);
        jSONObject.put("iat", this.iat);
        String str = this.sub;
        if (str != null) {
            jSONObject.put("sub", str);
        }
        String str2 = this.name;
        if (str2 != null) {
            jSONObject.put("name", str2);
        }
        String str3 = this.givenName;
        if (str3 != null) {
            jSONObject.put("given_name", str3);
        }
        String str4 = this.middleName;
        if (str4 != null) {
            jSONObject.put("middle_name", str4);
        }
        String str5 = this.familyName;
        if (str5 != null) {
            jSONObject.put("family_name", str5);
        }
        String str6 = this.email;
        if (str6 != null) {
            jSONObject.put(Scopes.EMAIL, str6);
        }
        String str7 = this.picture;
        if (str7 != null) {
            jSONObject.put("picture", str7);
        }
        if (this.userFriends != null) {
            jSONObject.put("user_friends", new JSONArray(this.userFriends));
        }
        String str8 = this.userBirthday;
        if (str8 != null) {
            jSONObject.put("user_birthday", str8);
        }
        if (this.userAgeRange != null) {
            jSONObject.put("user_age_range", new JSONObject(this.userAgeRange));
        }
        if (this.userHometown != null) {
            jSONObject.put("user_hometown", new JSONObject(this.userHometown));
        }
        if (this.userLocation != null) {
            jSONObject.put("user_location", new JSONObject(this.userLocation));
        }
        String str9 = this.userGender;
        if (str9 != null) {
            jSONObject.put("user_gender", str9);
        }
        String str10 = this.userLink;
        if (str10 != null) {
            jSONObject.put("user_link", str10);
        }
        return jSONObject;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getNullableString$facebook_core_release(JSONObject jSONObject, String str) {
            Intrinsics.checkNotNullParameter(jSONObject, "<this>");
            Intrinsics.checkNotNullParameter(str, "name");
            if (jSONObject.has(str)) {
                return jSONObject.getString(str);
            }
            return null;
        }
    }
}
