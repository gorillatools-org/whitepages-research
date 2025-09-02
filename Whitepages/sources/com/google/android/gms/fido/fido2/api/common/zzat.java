package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.TokenBinding;

final class zzat implements Parcelable.Creator {
    zzat() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        try {
            return TokenBinding.TokenBindingStatus.fromString(parcel.readString());
        } catch (TokenBinding.UnsupportedTokenBindingStatusException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new TokenBinding.TokenBindingStatus[i];
    }
}
