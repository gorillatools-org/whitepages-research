package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

@SafeParcelable.Class(creator = "BrowserPublicKeyCredentialCreationOptionsCreator")
@SafeParcelable.Reserved({1})
public class BrowserPublicKeyCredentialCreationOptions extends BrowserRequestOptions {
    public static final Parcelable.Creator<BrowserPublicKeyCredentialCreationOptions> CREATOR = new zzn();
    @SafeParcelable.Field(getter = "getPublicKeyCredentialCreationOptions", id = 2)
    private final PublicKeyCredentialCreationOptions zza;
    @SafeParcelable.Field(getter = "getOrigin", id = 3)
    private final Uri zzb;
    @SafeParcelable.Field(getter = "getClientDataHash", id = 4)
    private final byte[] zzc;

    public static final class Builder {
        private PublicKeyCredentialCreationOptions zza;
        private Uri zzb;
        private byte[] zzc;

        public BrowserPublicKeyCredentialCreationOptions build() {
            return new BrowserPublicKeyCredentialCreationOptions(this.zza, this.zzb, this.zzc);
        }

        public Builder setClientDataHash(byte[] bArr) {
            byte[] unused = BrowserPublicKeyCredentialCreationOptions.zzd(bArr);
            this.zzc = bArr;
            return this;
        }

        public Builder setOrigin(Uri uri) {
            Uri unused = BrowserPublicKeyCredentialCreationOptions.zzc(uri);
            this.zzb = uri;
            return this;
        }

        public Builder setPublicKeyCredentialCreationOptions(PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions) {
            this.zza = publicKeyCredentialCreationOptions;
            return this;
        }
    }

    @SafeParcelable.Constructor
    BrowserPublicKeyCredentialCreationOptions(@SafeParcelable.Param(id = 2) PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions, @SafeParcelable.Param(id = 3) Uri uri, @SafeParcelable.Param(id = 4) byte[] bArr) {
        this.zza = (PublicKeyCredentialCreationOptions) Preconditions.checkNotNull(publicKeyCredentialCreationOptions);
        zzc(uri);
        this.zzb = uri;
        zzd(bArr);
        this.zzc = bArr;
    }

    public static BrowserPublicKeyCredentialCreationOptions deserializeFromBytes(byte[] bArr) {
        return (BrowserPublicKeyCredentialCreationOptions) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    /* access modifiers changed from: private */
    public static Uri zzc(Uri uri) {
        Preconditions.checkNotNull(uri);
        boolean z = false;
        Preconditions.checkArgument(uri.getScheme() != null, "origin scheme must be non-empty");
        if (uri.getAuthority() != null) {
            z = true;
        }
        Preconditions.checkArgument(z, "origin authority must be non-empty");
        return uri;
    }

    /* access modifiers changed from: private */
    public static byte[] zzd(byte[] bArr) {
        boolean z = true;
        if (!(bArr == null || bArr.length == 32)) {
            z = false;
        }
        Preconditions.checkArgument(z, "clientDataHash must be 32 bytes long");
        return bArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BrowserPublicKeyCredentialCreationOptions)) {
            return false;
        }
        BrowserPublicKeyCredentialCreationOptions browserPublicKeyCredentialCreationOptions = (BrowserPublicKeyCredentialCreationOptions) obj;
        if (!Objects.equal(this.zza, browserPublicKeyCredentialCreationOptions.zza) || !Objects.equal(this.zzb, browserPublicKeyCredentialCreationOptions.zzb)) {
            return false;
        }
        return true;
    }

    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zza.getAuthenticationExtensions();
    }

    public byte[] getChallenge() {
        return this.zza.getChallenge();
    }

    public byte[] getClientDataHash() {
        return this.zzc;
    }

    public Uri getOrigin() {
        return this.zzb;
    }

    public PublicKeyCredentialCreationOptions getPublicKeyCredentialCreationOptions() {
        return this.zza;
    }

    public Integer getRequestId() {
        return this.zza.getRequestId();
    }

    public Double getTimeoutSeconds() {
        return this.zza.getTimeoutSeconds();
    }

    public TokenBinding getTokenBinding() {
        return this.zza.getTokenBinding();
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getPublicKeyCredentialCreationOptions(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getOrigin(), i, false);
        SafeParcelWriter.writeByteArray(parcel, 4, getClientDataHash(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
