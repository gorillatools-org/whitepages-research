package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzaj implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        zzqb zzqb = null;
        String str3 = null;
        zzbh zzbh = null;
        zzbh zzbh2 = null;
        zzbh zzbh3 = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    zzqb = (zzqb) SafeParcelReader.createParcelable(parcel2, readHeader, zzqb.CREATOR);
                    break;
                case 5:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 7:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 8:
                    zzbh = (zzbh) SafeParcelReader.createParcelable(parcel2, readHeader, zzbh.CREATOR);
                    break;
                case 9:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 10:
                    zzbh2 = (zzbh) SafeParcelReader.createParcelable(parcel2, readHeader, zzbh.CREATOR);
                    break;
                case 11:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    zzbh3 = (zzbh) SafeParcelReader.createParcelable(parcel2, readHeader, zzbh.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzai(str, str2, zzqb, j, z, str3, zzbh, j2, zzbh2, j3, zzbh3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzai[i];
    }
}
