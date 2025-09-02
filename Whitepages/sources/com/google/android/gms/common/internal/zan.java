package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zan implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = -1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        String str = null;
        String str2 = null;
        long j = 0;
        long j2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 2:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 3:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 4:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 5:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 6:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 7:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 8:
                    i5 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 9:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new MethodInvocation(i2, i3, i4, j, j2, str, str2, i5, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new MethodInvocation[i];
    }
}
