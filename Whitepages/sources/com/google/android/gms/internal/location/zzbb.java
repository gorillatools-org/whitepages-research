package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

public final class zzbb implements Parcelable.Creator<zzba> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<ClientIdentity> arrayList = zzba.zza;
        LocationRequest locationRequest = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        long j = Long.MAX_VALUE;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1) {
                switch (fieldId) {
                    case 5:
                        arrayList = SafeParcelReader.createTypedList(parcel2, readHeader, ClientIdentity.CREATOR);
                        break;
                    case 6:
                        str = SafeParcelReader.createString(parcel2, readHeader);
                        break;
                    case 7:
                        z = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 8:
                        z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 9:
                        z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 10:
                        str2 = SafeParcelReader.createString(parcel2, readHeader);
                        break;
                    case 11:
                        z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 12:
                        z5 = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 13:
                        str3 = SafeParcelReader.createString(parcel2, readHeader);
                        break;
                    case 14:
                        j = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel2, readHeader);
                        break;
                }
            } else {
                locationRequest = (LocationRequest) SafeParcelReader.createParcelable(parcel2, readHeader, LocationRequest.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzba(locationRequest, arrayList, str, z, z2, z3, str2, z4, z5, str3, j);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzba[i];
    }
}
