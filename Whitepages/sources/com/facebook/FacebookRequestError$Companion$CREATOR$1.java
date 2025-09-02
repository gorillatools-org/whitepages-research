package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FacebookRequestError$Companion$CREATOR$1 implements Parcelable.Creator {
    FacebookRequestError$Companion$CREATOR$1() {
    }

    public FacebookRequestError createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        return new FacebookRequestError(parcel, (DefaultConstructorMarker) null);
    }

    public FacebookRequestError[] newArray(int i) {
        return new FacebookRequestError[i];
    }
}
