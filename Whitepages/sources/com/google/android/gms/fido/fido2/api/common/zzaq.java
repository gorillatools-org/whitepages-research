package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;

final class zzaq implements Parcelable.Creator {
    zzaq() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        try {
            return PublicKeyCredentialType.fromString(parcel.readString());
        } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredentialType[i];
    }
}
