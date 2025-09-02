package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.login.LoginClient;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LoginClient$Request$Companion$CREATOR$1 implements Parcelable.Creator {
    LoginClient$Request$Companion$CREATOR$1() {
    }

    public LoginClient.Request createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new LoginClient.Request(parcel, (DefaultConstructorMarker) null);
    }

    public LoginClient.Request[] newArray(int i) {
        return new LoginClient.Request[i];
    }
}
