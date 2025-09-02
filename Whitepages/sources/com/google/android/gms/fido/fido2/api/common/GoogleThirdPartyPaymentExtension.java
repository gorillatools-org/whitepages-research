package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GoogleThirdPartyPaymentExtensionCreator")
public class GoogleThirdPartyPaymentExtension extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleThirdPartyPaymentExtension> CREATOR = new zzaf();
    @SafeParcelable.Field(getter = "getThirdPartyPayment", id = 1)
    private final boolean zza;

    @SafeParcelable.Constructor
    public GoogleThirdPartyPaymentExtension(@SafeParcelable.Param(id = 1) boolean z) {
        this.zza = z;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof GoogleThirdPartyPaymentExtension) && this.zza == ((GoogleThirdPartyPaymentExtension) obj).getThirdPartyPayment()) {
            return true;
        }
        return false;
    }

    public boolean getThirdPartyPayment() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, getThirdPartyPayment());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
