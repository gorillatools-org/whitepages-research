package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class LoginClient$Companion$CREATOR$1 implements Parcelable.Creator {
    LoginClient$Companion$CREATOR$1() {
    }

    public LoginClient createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new LoginClient(parcel);
    }

    public LoginClient[] newArray(int i) {
        return new LoginClient[i];
    }
}
