package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PublicKeyCredentialRequestOptionsCreator")
@SafeParcelable.Reserved({1})
public class PublicKeyCredentialRequestOptions extends RequestOptions {
    public static final Parcelable.Creator<PublicKeyCredentialRequestOptions> CREATOR = new zzao();
    @SafeParcelable.Field(getter = "getChallenge", id = 2)
    private final byte[] zza;
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 3)
    private final Double zzb;
    @SafeParcelable.Field(getter = "getRpId", id = 4)
    private final String zzc;
    @SafeParcelable.Field(getter = "getAllowList", id = 5)
    private final List zzd;
    @SafeParcelable.Field(getter = "getRequestId", id = 6)
    private final Integer zze;
    @SafeParcelable.Field(getter = "getTokenBinding", id = 7)
    private final TokenBinding zzf;
    @SafeParcelable.Field(getter = "getUserVerificationAsString", id = 8, type = "java.lang.String")
    private final zzay zzg;
    @SafeParcelable.Field(getter = "getAuthenticationExtensions", id = 9)
    private final AuthenticationExtensions zzh;
    @SafeParcelable.Field(getter = "getLongRequestId", id = 10)
    private final Long zzi;

    public static final class Builder {
        private byte[] zza;
        private Double zzb;
        private String zzc;
        private List zzd;
        private Integer zze;
        private TokenBinding zzf;
        private zzay zzg;
        private AuthenticationExtensions zzh;

        public Builder() {
        }

        public Builder(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) {
            if (publicKeyCredentialRequestOptions != null) {
                this.zza = publicKeyCredentialRequestOptions.getChallenge();
                this.zzb = publicKeyCredentialRequestOptions.getTimeoutSeconds();
                this.zzc = publicKeyCredentialRequestOptions.getRpId();
                this.zzd = publicKeyCredentialRequestOptions.getAllowList();
                this.zze = publicKeyCredentialRequestOptions.getRequestId();
                this.zzf = publicKeyCredentialRequestOptions.getTokenBinding();
                this.zzg = publicKeyCredentialRequestOptions.zza();
                this.zzh = publicKeyCredentialRequestOptions.getAuthenticationExtensions();
            }
        }

        public PublicKeyCredentialRequestOptions build() {
            String zzay;
            byte[] bArr = this.zza;
            Double d = this.zzb;
            String str = this.zzc;
            List list = this.zzd;
            Integer num = this.zze;
            TokenBinding tokenBinding = this.zzf;
            zzay zzay2 = this.zzg;
            if (zzay2 == null) {
                zzay = null;
            } else {
                zzay = zzay2.toString();
            }
            return new PublicKeyCredentialRequestOptions(bArr, d, str, list, num, tokenBinding, zzay, this.zzh, (Long) null);
        }

        public Builder setAllowList(List<PublicKeyCredentialDescriptor> list) {
            this.zzd = list;
            return this;
        }

        public Builder setAuthenticationExtensions(AuthenticationExtensions authenticationExtensions) {
            this.zzh = authenticationExtensions;
            return this;
        }

        public Builder setChallenge(byte[] bArr) {
            this.zza = (byte[]) Preconditions.checkNotNull(bArr);
            return this;
        }

        public Builder setRequestId(Integer num) {
            this.zze = num;
            return this;
        }

        public Builder setRpId(String str) {
            this.zzc = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public Builder setTimeoutSeconds(Double d) {
            this.zzb = d;
            return this;
        }

        public Builder setTokenBinding(TokenBinding tokenBinding) {
            this.zzf = tokenBinding;
            return this;
        }
    }

    @SafeParcelable.Constructor
    PublicKeyCredentialRequestOptions(@SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) Double d, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) List list, @SafeParcelable.Param(id = 6) Integer num, @SafeParcelable.Param(id = 7) TokenBinding tokenBinding, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) AuthenticationExtensions authenticationExtensions, @SafeParcelable.Param(id = 10) Long l) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = d;
        this.zzc = (String) Preconditions.checkNotNull(str);
        this.zzd = list;
        this.zze = num;
        this.zzf = tokenBinding;
        this.zzi = l;
        if (str2 != null) {
            try {
                this.zzg = zzay.zza(str2);
            } catch (zzax e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            this.zzg = null;
        }
        this.zzh = authenticationExtensions;
    }

    public static PublicKeyCredentialRequestOptions deserializeFromBytes(byte[] bArr) {
        return (PublicKeyCredentialRequestOptions) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(Object obj) {
        List list;
        List list2;
        if (!(obj instanceof PublicKeyCredentialRequestOptions)) {
            return false;
        }
        PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions = (PublicKeyCredentialRequestOptions) obj;
        if (!Arrays.equals(this.zza, publicKeyCredentialRequestOptions.zza) || !Objects.equal(this.zzb, publicKeyCredentialRequestOptions.zzb) || !Objects.equal(this.zzc, publicKeyCredentialRequestOptions.zzc) || ((((list = this.zzd) != null || publicKeyCredentialRequestOptions.zzd != null) && (list == null || (list2 = publicKeyCredentialRequestOptions.zzd) == null || !list.containsAll(list2) || !publicKeyCredentialRequestOptions.zzd.containsAll(this.zzd))) || !Objects.equal(this.zze, publicKeyCredentialRequestOptions.zze) || !Objects.equal(this.zzf, publicKeyCredentialRequestOptions.zzf) || !Objects.equal(this.zzg, publicKeyCredentialRequestOptions.zzg) || !Objects.equal(this.zzh, publicKeyCredentialRequestOptions.zzh) || !Objects.equal(this.zzi, publicKeyCredentialRequestOptions.zzi))) {
            return false;
        }
        return true;
    }

    public List<PublicKeyCredentialDescriptor> getAllowList() {
        return this.zzd;
    }

    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zzh;
    }

    public byte[] getChallenge() {
        return this.zza;
    }

    public Integer getRequestId() {
        return this.zze;
    }

    public String getRpId() {
        return this.zzc;
    }

    public Double getTimeoutSeconds() {
        return this.zzb;
    }

    public TokenBinding getTokenBinding() {
        return this.zzf;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.zza)), this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi);
    }

    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getChallenge(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 3, getTimeoutSeconds(), false);
        SafeParcelWriter.writeString(parcel, 4, getRpId(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getAllowList(), false);
        SafeParcelWriter.writeIntegerObject(parcel, 6, getRequestId(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getTokenBinding(), i, false);
        zzay zzay = this.zzg;
        if (zzay == null) {
            str = null;
        } else {
            str = zzay.toString();
        }
        SafeParcelWriter.writeString(parcel, 8, str, false);
        SafeParcelWriter.writeParcelable(parcel, 9, getAuthenticationExtensions(), i, false);
        SafeParcelWriter.writeLongObject(parcel, 10, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzay zza() {
        return this.zzg;
    }
}
