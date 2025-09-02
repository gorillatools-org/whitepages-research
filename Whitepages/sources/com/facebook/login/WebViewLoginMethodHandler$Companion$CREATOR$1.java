package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class WebViewLoginMethodHandler$Companion$CREATOR$1 implements Parcelable.Creator {
    WebViewLoginMethodHandler$Companion$CREATOR$1() {
    }

    public WebViewLoginMethodHandler createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new WebViewLoginMethodHandler(parcel);
    }

    public WebViewLoginMethodHandler[] newArray(int i) {
        return new WebViewLoginMethodHandler[i];
    }
}
