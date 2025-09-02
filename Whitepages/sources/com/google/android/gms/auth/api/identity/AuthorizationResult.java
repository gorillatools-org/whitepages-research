package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "AuthorizationResultCreator")
public final class AuthorizationResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthorizationResult> CREATOR = new zbc();
    @SafeParcelable.Field(getter = "getServerAuthCode", id = 1)
    private final String zba;
    @SafeParcelable.Field(getter = "getAccessToken", id = 2)
    private final String zbb;
    @SafeParcelable.Field(getter = "getIdToken", id = 3)
    private final String zbc;
    @SafeParcelable.Field(getter = "getGrantedScopes", id = 4)
    private final List zbd;
    @SafeParcelable.Field(getter = "toGoogleSignInAccount", id = 5)
    private final GoogleSignInAccount zbe;
    @SafeParcelable.Field(getter = "getPendingIntent", id = 6)
    private final PendingIntent zbf;

    @SafeParcelable.Constructor
    public AuthorizationResult(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) String str3, @SafeParcelable.Param(id = 4) List<String> list, @SafeParcelable.Param(id = 5) GoogleSignInAccount googleSignInAccount, @SafeParcelable.Param(id = 6) PendingIntent pendingIntent) {
        this.zba = str;
        this.zbb = str2;
        this.zbc = str3;
        this.zbd = (List) Preconditions.checkNotNull(list);
        this.zbf = pendingIntent;
        this.zbe = googleSignInAccount;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthorizationResult)) {
            return false;
        }
        AuthorizationResult authorizationResult = (AuthorizationResult) obj;
        if (!Objects.equal(this.zba, authorizationResult.zba) || !Objects.equal(this.zbb, authorizationResult.zbb) || !Objects.equal(this.zbc, authorizationResult.zbc) || !Objects.equal(this.zbd, authorizationResult.zbd) || !Objects.equal(this.zbf, authorizationResult.zbf) || !Objects.equal(this.zbe, authorizationResult.zbe)) {
            return false;
        }
        return true;
    }

    public String getAccessToken() {
        return this.zbb;
    }

    public List<String> getGrantedScopes() {
        return this.zbd;
    }

    public PendingIntent getPendingIntent() {
        return this.zbf;
    }

    public String getServerAuthCode() {
        return this.zba;
    }

    public boolean hasResolution() {
        return this.zbf != null;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba, this.zbb, this.zbc, this.zbd, this.zbf, this.zbe);
    }

    public GoogleSignInAccount toGoogleSignInAccount() {
        return this.zbe;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getServerAuthCode(), false);
        SafeParcelWriter.writeString(parcel, 2, getAccessToken(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zbc, false);
        SafeParcelWriter.writeStringList(parcel, 4, getGrantedScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, toGoogleSignInAccount(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, getPendingIntent(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
