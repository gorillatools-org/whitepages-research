package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.zzs;
import java.util.List;

public final class zzk implements Parcelable.Creator<zzj> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzs zzs = zzj.zzb;
        List<ClientIdentity> list = zzj.zza;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                zzs = (zzs) SafeParcelReader.createParcelable(parcel, readHeader, zzs.CREATOR);
            } else if (fieldId == 2) {
                list = SafeParcelReader.createTypedList(parcel, readHeader, ClientIdentity.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzj(zzs, list, str);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzj[i];
    }
}
