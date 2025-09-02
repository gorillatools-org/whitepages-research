package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class AuthenticationTokenHeader$Companion$CREATOR$1 implements Parcelable.Creator {
    AuthenticationTokenHeader$Companion$CREATOR$1() {
    }

    public AuthenticationTokenHeader createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new AuthenticationTokenHeader(parcel);
    }

    public AuthenticationTokenHeader[] newArray(int i) {
        return new AuthenticationTokenHeader[i];
    }
}
