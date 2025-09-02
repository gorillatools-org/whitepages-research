package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;

final class zzf implements Parcelable.Creator {
    zzf() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        try {
            return ProtocolVersion.fromString(parcel.readString());
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ProtocolVersion[i];
    }
}
