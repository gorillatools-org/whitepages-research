package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.login.LoginClient;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LoginClient$Result$Companion$CREATOR$1 implements Parcelable.Creator {
    LoginClient$Result$Companion$CREATOR$1() {
    }

    public LoginClient.Result createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new LoginClient.Result(parcel, (DefaultConstructorMarker) null);
    }

    public LoginClient.Result[] newArray(int i) {
        return new LoginClient.Result[i];
    }
}
