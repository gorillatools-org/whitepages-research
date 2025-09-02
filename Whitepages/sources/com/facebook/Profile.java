package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.AccessToken;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Parcelable.Creator<Profile> CREATOR = new Profile$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = Profile.class.getSimpleName();
    private final String firstName;
    private final String id;
    private final String lastName;
    private final Uri linkUri;
    private final String middleName;
    private final String name;
    private final Uri pictureUri;

    public /* synthetic */ Profile(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public Profile(String str, String str2, String str3, String str4, String str5, Uri uri, Uri uri2) {
        Validate.notNullOrEmpty(str, "id");
        this.id = str;
        this.firstName = str2;
        this.middleName = str3;
        this.lastName = str4;
        this.name = str5;
        this.linkUri = uri;
        this.pictureUri = uri2;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        Uri uri;
        Uri uri2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Profile)) {
            return false;
        }
        String str5 = this.id;
        if (((str5 != null || ((Profile) obj).id != null) && !Intrinsics.areEqual((Object) str5, (Object) ((Profile) obj).id)) || ((((str = this.firstName) != null || ((Profile) obj).firstName != null) && !Intrinsics.areEqual((Object) str, (Object) ((Profile) obj).firstName)) || ((((str2 = this.middleName) != null || ((Profile) obj).middleName != null) && !Intrinsics.areEqual((Object) str2, (Object) ((Profile) obj).middleName)) || ((((str3 = this.lastName) != null || ((Profile) obj).lastName != null) && !Intrinsics.areEqual((Object) str3, (Object) ((Profile) obj).lastName)) || ((((str4 = this.name) != null || ((Profile) obj).name != null) && !Intrinsics.areEqual((Object) str4, (Object) ((Profile) obj).name)) || ((((uri = this.linkUri) != null || ((Profile) obj).linkUri != null) && !Intrinsics.areEqual((Object) uri, (Object) ((Profile) obj).linkUri)) || (((uri2 = this.pictureUri) != null || ((Profile) obj).pictureUri != null) && !Intrinsics.areEqual((Object) uri2, (Object) ((Profile) obj).pictureUri)))))))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.id;
        int hashCode = 527 + (str != null ? str.hashCode() : 0);
        String str2 = this.firstName;
        if (str2 != null) {
            hashCode = (hashCode * 31) + str2.hashCode();
        }
        String str3 = this.middleName;
        if (str3 != null) {
            hashCode = (hashCode * 31) + str3.hashCode();
        }
        String str4 = this.lastName;
        if (str4 != null) {
            hashCode = (hashCode * 31) + str4.hashCode();
        }
        String str5 = this.name;
        if (str5 != null) {
            hashCode = (hashCode * 31) + str5.hashCode();
        }
        Uri uri = this.linkUri;
        if (uri != null) {
            hashCode = (hashCode * 31) + uri.hashCode();
        }
        Uri uri2 = this.pictureUri;
        return uri2 != null ? (hashCode * 31) + uri2.hashCode() : hashCode;
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("first_name", this.firstName);
            jSONObject.put("middle_name", this.middleName);
            jSONObject.put("last_name", this.lastName);
            jSONObject.put("name", this.name);
            Uri uri = this.linkUri;
            if (uri != null) {
                jSONObject.put("link_uri", uri.toString());
            }
            Uri uri2 = this.pictureUri;
            if (uri2 != null) {
                jSONObject.put("picture_uri", uri2.toString());
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public Profile(JSONObject jSONObject) {
        Uri uri;
        Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
        Uri uri2 = null;
        this.id = jSONObject.optString("id", (String) null);
        this.firstName = jSONObject.optString("first_name", (String) null);
        this.middleName = jSONObject.optString("middle_name", (String) null);
        this.lastName = jSONObject.optString("last_name", (String) null);
        this.name = jSONObject.optString("name", (String) null);
        String optString = jSONObject.optString("link_uri", (String) null);
        if (optString == null) {
            uri = null;
        } else {
            uri = Uri.parse(optString);
        }
        this.linkUri = uri;
        String optString2 = jSONObject.optString("picture_uri", (String) null);
        this.pictureUri = optString2 != null ? Uri.parse(optString2) : uri2;
    }

    private Profile(Parcel parcel) {
        Uri uri;
        this.id = parcel.readString();
        this.firstName = parcel.readString();
        this.middleName = parcel.readString();
        this.lastName = parcel.readString();
        this.name = parcel.readString();
        String readString = parcel.readString();
        Uri uri2 = null;
        if (readString == null) {
            uri = null;
        } else {
            uri = Uri.parse(readString);
        }
        this.linkUri = uri;
        String readString2 = parcel.readString();
        this.pictureUri = readString2 != null ? Uri.parse(readString2) : uri2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.firstName);
        parcel.writeString(this.middleName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.name);
        Uri uri = this.linkUri;
        String str = null;
        parcel.writeString(uri != null ? uri.toString() : null);
        Uri uri2 = this.pictureUri;
        if (uri2 != null) {
            str = uri2.toString();
        }
        parcel.writeString(str);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Profile getCurrentProfile() {
            return ProfileManager.Companion.getInstance().getCurrentProfile();
        }

        public final void setCurrentProfile(Profile profile) {
            ProfileManager.Companion.getInstance().setCurrentProfile(profile);
        }

        public final void fetchProfileForCurrentAccessToken() {
            AccessToken.Companion companion = AccessToken.Companion;
            AccessToken currentAccessToken = companion.getCurrentAccessToken();
            if (currentAccessToken != null) {
                if (!companion.isCurrentAccessTokenActive()) {
                    setCurrentProfile((Profile) null);
                } else {
                    Utility.getGraphMeRequestWithCacheAsync(currentAccessToken.getToken(), new Profile$Companion$fetchProfileForCurrentAccessToken$1());
                }
            }
        }
    }
}
