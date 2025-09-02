package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class CustomTabLoginMethodHandler$Companion$CREATOR$1 implements Parcelable.Creator {
    CustomTabLoginMethodHandler$Companion$CREATOR$1() {
    }

    public CustomTabLoginMethodHandler createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new CustomTabLoginMethodHandler(parcel);
    }

    public CustomTabLoginMethodHandler[] newArray(int i) {
        return new CustomTabLoginMethodHandler[i];
    }
}
