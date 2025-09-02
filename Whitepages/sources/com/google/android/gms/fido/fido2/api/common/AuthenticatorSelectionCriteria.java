package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.fido2.api.common.Attachment;
import com.google.android.gms.fido.fido2.api.common.ResidentKeyRequirement;

@SafeParcelable.Class(creator = "AuthenticatorSelectionCriteriaCreator")
@SafeParcelable.Reserved({1})
public class AuthenticatorSelectionCriteria extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthenticatorSelectionCriteria> CREATOR = new zzm();
    @SafeParcelable.Field(getter = "getAttachmentAsString", id = 2, type = "java.lang.String")
    private final Attachment zza;
    @SafeParcelable.Field(getter = "getRequireResidentKey", id = 3)
    private final Boolean zzb;
    @SafeParcelable.Field(getter = "getRequireUserVerificationAsString", id = 4, type = "java.lang.String")
    private final zzay zzc;
    @SafeParcelable.Field(getter = "getResidentKeyRequirementAsString", id = 5, type = "java.lang.String")
    private final ResidentKeyRequirement zzd;

    public static class Builder {
        private Attachment zza;
        private Boolean zzb;
        private ResidentKeyRequirement zzc;

        public AuthenticatorSelectionCriteria build() {
            String str;
            String str2;
            Attachment attachment = this.zza;
            if (attachment == null) {
                str = null;
            } else {
                str = attachment.toString();
            }
            Boolean bool = this.zzb;
            ResidentKeyRequirement residentKeyRequirement = this.zzc;
            if (residentKeyRequirement == null) {
                str2 = null;
            } else {
                str2 = residentKeyRequirement.toString();
            }
            return new AuthenticatorSelectionCriteria(str, bool, (String) null, str2);
        }

        public Builder setAttachment(Attachment attachment) {
            this.zza = attachment;
            return this;
        }

        public Builder setRequireResidentKey(Boolean bool) {
            this.zzb = bool;
            return this;
        }

        public Builder setResidentKeyRequirement(ResidentKeyRequirement residentKeyRequirement) {
            this.zzc = residentKeyRequirement;
            return this;
        }
    }

    @SafeParcelable.Constructor
    AuthenticatorSelectionCriteria(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) Boolean bool, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) String str3) {
        Attachment attachment;
        zzay zzay;
        ResidentKeyRequirement residentKeyRequirement = null;
        if (str == null) {
            attachment = null;
        } else {
            try {
                attachment = Attachment.fromString(str);
            } catch (Attachment.UnsupportedAttachmentException | ResidentKeyRequirement.UnsupportedResidentKeyRequirementException | zzax e) {
                throw new IllegalArgumentException(e);
            }
        }
        this.zza = attachment;
        this.zzb = bool;
        if (str2 == null) {
            zzay = null;
        } else {
            zzay = zzay.zza(str2);
        }
        this.zzc = zzay;
        if (str3 != null) {
            residentKeyRequirement = ResidentKeyRequirement.fromString(str3);
        }
        this.zzd = residentKeyRequirement;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticatorSelectionCriteria)) {
            return false;
        }
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria = (AuthenticatorSelectionCriteria) obj;
        if (!Objects.equal(this.zza, authenticatorSelectionCriteria.zza) || !Objects.equal(this.zzb, authenticatorSelectionCriteria.zzb) || !Objects.equal(this.zzc, authenticatorSelectionCriteria.zzc) || !Objects.equal(this.zzd, authenticatorSelectionCriteria.zzd)) {
            return false;
        }
        return true;
    }

    public Attachment getAttachment() {
        return this.zza;
    }

    public String getAttachmentAsString() {
        Attachment attachment = this.zza;
        if (attachment == null) {
            return null;
        }
        return attachment.toString();
    }

    public Boolean getRequireResidentKey() {
        return this.zzb;
    }

    public ResidentKeyRequirement getResidentKeyRequirement() {
        return this.zzd;
    }

    public String getResidentKeyRequirementAsString() {
        ResidentKeyRequirement residentKeyRequirement = this.zzd;
        if (residentKeyRequirement == null) {
            return null;
        }
        return residentKeyRequirement.toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, this.zzd);
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAttachmentAsString(), false);
        SafeParcelWriter.writeBooleanObject(parcel, 3, getRequireResidentKey(), false);
        zzay zzay = this.zzc;
        if (zzay == null) {
            str = null;
        } else {
            str = zzay.toString();
        }
        SafeParcelWriter.writeString(parcel, 4, str, false);
        SafeParcelWriter.writeString(parcel, 5, getResidentKeyRequirementAsString(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
