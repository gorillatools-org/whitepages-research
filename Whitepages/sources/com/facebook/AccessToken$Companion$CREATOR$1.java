package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class AccessToken$Companion$CREATOR$1 implements Parcelable.Creator {
    AccessToken$Companion$CREATOR$1() {
    }

    public AccessToken createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new AccessToken(parcel);
    }

    public AccessToken[] newArray(int i) {
        return new AccessToken[i];
    }
}
