package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzj implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        byte[] bArr = null;
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        byte[] bArr5 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                bArr = SafeParcelReader.createByteArray(parcel, readHeader);
            } else if (fieldId == 3) {
                bArr2 = SafeParcelReader.createByteArray(parcel, readHeader);
            } else if (fieldId == 4) {
                bArr3 = SafeParcelReader.createByteArray(parcel, readHeader);
            } else if (fieldId == 5) {
                bArr4 = SafeParcelReader.createByteArray(parcel, readHeader);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                bArr5 = SafeParcelReader.createByteArray(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new AuthenticatorAssertionResponse(bArr, bArr2, bArr3, bArr4, bArr5);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AuthenticatorAssertionResponse[i];
    }
}
