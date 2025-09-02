package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;

@SafeParcelable.Class(creator = "PublicKeyCredentialCreator")
public class PublicKeyCredential extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PublicKeyCredential> CREATOR = new zzal();
    @SafeParcelable.Field(getter = "getId", id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getType", id = 2)
    private final String zzb;
    @SafeParcelable.Field(getter = "getRawId", id = 3)
    private final byte[] zzc;
    @SafeParcelable.Field(getter = "getRegisterResponse", id = 4)
    private final AuthenticatorAttestationResponse zzd;
    @SafeParcelable.Field(getter = "getSignResponse", id = 5)
    private final AuthenticatorAssertionResponse zze;
    @SafeParcelable.Field(getter = "getErrorResponse", id = 6)
    private final AuthenticatorErrorResponse zzf;
    @SafeParcelable.Field(getter = "getClientExtensionResults", id = 7)
    private final AuthenticationExtensionsClientOutputs zzg;
    @SafeParcelable.Field(getter = "getAuthenticatorAttachment", id = 8)
    private final String zzh;

    public static class Builder {
        private String zza;
        private byte[] zzb;
        private AuthenticatorResponse zzc;
        private AuthenticationExtensionsClientOutputs zzd;
        private String zze;

        public PublicKeyCredential build() {
            AuthenticatorResponse authenticatorResponse = this.zzc;
            AuthenticatorErrorResponse authenticatorErrorResponse = null;
            AuthenticatorAttestationResponse authenticatorAttestationResponse = authenticatorResponse instanceof AuthenticatorAttestationResponse ? (AuthenticatorAttestationResponse) authenticatorResponse : null;
            AuthenticatorAssertionResponse authenticatorAssertionResponse = authenticatorResponse instanceof AuthenticatorAssertionResponse ? (AuthenticatorAssertionResponse) authenticatorResponse : null;
            if (authenticatorResponse instanceof AuthenticatorErrorResponse) {
                authenticatorErrorResponse = (AuthenticatorErrorResponse) authenticatorResponse;
            }
            return new PublicKeyCredential(this.zza, PublicKeyCredentialType.PUBLIC_KEY.toString(), this.zzb, authenticatorAttestationResponse, authenticatorAssertionResponse, authenticatorErrorResponse, this.zzd, this.zze);
        }

        public Builder setAuthenticationExtensionsClientOutputs(AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs) {
            this.zzd = authenticationExtensionsClientOutputs;
            return this;
        }

        public Builder setAuthenticatorAttachment(String str) {
            this.zze = str;
            return this;
        }

        public Builder setId(String str) {
            this.zza = str;
            return this;
        }

        public Builder setRawId(byte[] bArr) {
            this.zzb = bArr;
            return this;
        }

        public Builder setResponse(AuthenticatorResponse authenticatorResponse) {
            this.zzc = authenticatorResponse;
            return this;
        }
    }

    @SafeParcelable.Constructor
    PublicKeyCredential(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) byte[] bArr, @SafeParcelable.Param(id = 4) AuthenticatorAttestationResponse authenticatorAttestationResponse, @SafeParcelable.Param(id = 5) AuthenticatorAssertionResponse authenticatorAssertionResponse, @SafeParcelable.Param(id = 6) AuthenticatorErrorResponse authenticatorErrorResponse, @SafeParcelable.Param(id = 7) AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs, @SafeParcelable.Param(id = 8) String str3) {
        boolean z = true;
        if (!((authenticatorAttestationResponse != null && authenticatorAssertionResponse == null && authenticatorErrorResponse == null) || ((authenticatorAttestationResponse == null && authenticatorAssertionResponse != null && authenticatorErrorResponse == null) || (authenticatorAttestationResponse == null && authenticatorAssertionResponse == null && authenticatorErrorResponse != null)))) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.zza = str;
        this.zzb = str2;
        this.zzc = bArr;
        this.zzd = authenticatorAttestationResponse;
        this.zze = authenticatorAssertionResponse;
        this.zzf = authenticatorErrorResponse;
        this.zzg = authenticationExtensionsClientOutputs;
        this.zzh = str3;
    }

    public static PublicKeyCredential deserializeFromBytes(byte[] bArr) {
        return (PublicKeyCredential) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PublicKeyCredential)) {
            return false;
        }
        PublicKeyCredential publicKeyCredential = (PublicKeyCredential) obj;
        if (!Objects.equal(this.zza, publicKeyCredential.zza) || !Objects.equal(this.zzb, publicKeyCredential.zzb) || !Arrays.equals(this.zzc, publicKeyCredential.zzc) || !Objects.equal(this.zzd, publicKeyCredential.zzd) || !Objects.equal(this.zze, publicKeyCredential.zze) || !Objects.equal(this.zzf, publicKeyCredential.zzf) || !Objects.equal(this.zzg, publicKeyCredential.zzg) || !Objects.equal(this.zzh, publicKeyCredential.zzh)) {
            return false;
        }
        return true;
    }

    public String getAuthenticatorAttachment() {
        return this.zzh;
    }

    public AuthenticationExtensionsClientOutputs getClientExtensionResults() {
        return this.zzg;
    }

    public String getId() {
        return this.zza;
    }

    public byte[] getRawId() {
        return this.zzc;
    }

    public AuthenticatorResponse getResponse() {
        AuthenticatorAttestationResponse authenticatorAttestationResponse = this.zzd;
        if (authenticatorAttestationResponse != null) {
            return authenticatorAttestationResponse;
        }
        AuthenticatorAssertionResponse authenticatorAssertionResponse = this.zze;
        if (authenticatorAssertionResponse != null) {
            return authenticatorAssertionResponse;
        }
        AuthenticatorErrorResponse authenticatorErrorResponse = this.zzf;
        if (authenticatorErrorResponse != null) {
            return authenticatorErrorResponse;
        }
        throw new IllegalStateException("No response set.");
    }

    public String getType() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, this.zze, this.zzd, this.zzf, this.zzg, this.zzh);
    }

    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getType(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getRawId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, getClientExtensionResults(), i, false);
        SafeParcelWriter.writeString(parcel, 8, getAuthenticatorAttachment(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
