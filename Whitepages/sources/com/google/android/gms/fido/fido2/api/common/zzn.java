package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzn implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions = null;
        Uri uri = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                publicKeyCredentialCreationOptions = (PublicKeyCredentialCreationOptions) SafeParcelReader.createParcelable(parcel, readHeader, PublicKeyCredentialCreationOptions.CREATOR);
            } else if (fieldId == 3) {
                uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                bArr = SafeParcelReader.createByteArray(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new BrowserPublicKeyCredentialCreationOptions(publicKeyCredentialCreationOptions, uri, bArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new BrowserPublicKeyCredentialCreationOptions[i];
    }
}
