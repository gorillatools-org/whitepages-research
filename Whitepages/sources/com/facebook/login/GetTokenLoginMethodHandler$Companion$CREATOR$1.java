package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class GetTokenLoginMethodHandler$Companion$CREATOR$1 implements Parcelable.Creator {
    GetTokenLoginMethodHandler$Companion$CREATOR$1() {
    }

    public GetTokenLoginMethodHandler createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new GetTokenLoginMethodHandler(parcel);
    }

    public GetTokenLoginMethodHandler[] newArray(int i) {
        return new GetTokenLoginMethodHandler[i];
    }
}
