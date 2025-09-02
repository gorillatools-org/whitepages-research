package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.ResidentKeyRequirement;

final class zzas implements Parcelable.Creator {
    zzas() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        String readString = parcel.readString();
        if (readString == null) {
            readString = "";
        }
        try {
            return ResidentKeyRequirement.fromString(readString);
        } catch (ResidentKeyRequirement.UnsupportedResidentKeyRequirementException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ResidentKeyRequirement[i];
    }
}
