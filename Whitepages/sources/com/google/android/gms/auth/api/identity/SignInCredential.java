package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;

@SafeParcelable.Class(creator = "SignInCredentialCreator")
@Deprecated
public final class SignInCredential extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInCredential> CREATOR = new zbr();
    @SafeParcelable.Field(getter = "getId", id = 1)
    private final String zba;
    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    private final String zbb;
    @SafeParcelable.Field(getter = "getGivenName", id = 3)
    private final String zbc;
    @SafeParcelable.Field(getter = "getFamilyName", id = 4)
    private final String zbd;
    @SafeParcelable.Field(getter = "getProfilePictureUri", id = 5)
    private final Uri zbe;
    @SafeParcelable.Field(getter = "getPassword", id = 6)
    private final String zbf;
    @SafeParcelable.Field(getter = "getGoogleIdToken", id = 7)
    private final String zbg;
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 8)
    private final String zbh;
    @SafeParcelable.Field(getter = "getPublicKeyCredential", id = 9)
    private final PublicKeyCredential zbi;

    @SafeParcelable.Constructor
    SignInCredential(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) String str3, @SafeParcelable.Param(id = 4) String str4, @SafeParcelable.Param(id = 5) Uri uri, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) String str6, @SafeParcelable.Param(id = 8) String str7, @SafeParcelable.Param(id = 9) PublicKeyCredential publicKeyCredential) {
        this.zba = (String) Preconditions.checkNotNull(str);
        this.zbb = str2;
        this.zbc = str3;
        this.zbd = str4;
        this.zbe = uri;
        this.zbf = str5;
        this.zbg = str6;
        this.zbh = str7;
        this.zbi = publicKeyCredential;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignInCredential)) {
            return false;
        }
        SignInCredential signInCredential = (SignInCredential) obj;
        if (!Objects.equal(this.zba, signInCredential.zba) || !Objects.equal(this.zbb, signInCredential.zbb) || !Objects.equal(this.zbc, signInCredential.zbc) || !Objects.equal(this.zbd, signInCredential.zbd) || !Objects.equal(this.zbe, signInCredential.zbe) || !Objects.equal(this.zbf, signInCredential.zbf) || !Objects.equal(this.zbg, signInCredential.zbg) || !Objects.equal(this.zbh, signInCredential.zbh) || !Objects.equal(this.zbi, signInCredential.zbi)) {
            return false;
        }
        return true;
    }

    public String getDisplayName() {
        return this.zbb;
    }

    public String getFamilyName() {
        return this.zbd;
    }

    public String getGivenName() {
        return this.zbc;
    }

    public String getGoogleIdToken() {
        return this.zbg;
    }

    public String getId() {
        return this.zba;
    }

    public String getPassword() {
        return this.zbf;
    }

    @Deprecated
    public String getPhoneNumber() {
        return this.zbh;
    }

    public Uri getProfilePictureUri() {
        return this.zbe;
    }

    public PublicKeyCredential getPublicKeyCredential() {
        return this.zbi;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg, this.zbh, this.zbi);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 4, getFamilyName(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getProfilePictureUri(), i, false);
        SafeParcelWriter.writeString(parcel, 6, getPassword(), false);
        SafeParcelWriter.writeString(parcel, 7, getGoogleIdToken(), false);
        SafeParcelWriter.writeString(parcel, 8, getPhoneNumber(), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getPublicKeyCredential(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
