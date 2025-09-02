package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzpb implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        byte[] bArr = null;
        String str = null;
        Bundle bundle = null;
        String str2 = null;
        long j = 0;
        long j2 = 0;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 2:
                    bArr = SafeParcelReader.createByteArray(parcel2, readHeader);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    bundle = SafeParcelReader.createBundle(parcel2, readHeader);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 6:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 7:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzpa(j, bArr, str, bundle, i, j2, str2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzpa[i];
    }
}
