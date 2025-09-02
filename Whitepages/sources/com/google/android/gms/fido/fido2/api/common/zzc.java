package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzc implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        UvmEntries uvmEntries = null;
        zzf zzf = null;
        AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs = null;
        zzh zzh = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                uvmEntries = (UvmEntries) SafeParcelReader.createParcelable(parcel, readHeader, UvmEntries.CREATOR);
            } else if (fieldId == 2) {
                zzf = (zzf) SafeParcelReader.createParcelable(parcel, readHeader, zzf.CREATOR);
            } else if (fieldId == 3) {
                authenticationExtensionsCredPropsOutputs = (AuthenticationExtensionsCredPropsOutputs) SafeParcelReader.createParcelable(parcel, readHeader, AuthenticationExtensionsCredPropsOutputs.CREATOR);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzh = (zzh) SafeParcelReader.createParcelable(parcel, readHeader, zzh.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new AuthenticationExtensionsClientOutputs(uvmEntries, zzf, authenticationExtensionsCredPropsOutputs, zzh);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AuthenticationExtensionsClientOutputs[i];
    }
}
