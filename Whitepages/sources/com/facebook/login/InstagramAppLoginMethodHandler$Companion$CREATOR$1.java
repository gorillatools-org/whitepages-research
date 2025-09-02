package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class InstagramAppLoginMethodHandler$Companion$CREATOR$1 implements Parcelable.Creator {
    InstagramAppLoginMethodHandler$Companion$CREATOR$1() {
    }

    public InstagramAppLoginMethodHandler createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new InstagramAppLoginMethodHandler(parcel);
    }

    public InstagramAppLoginMethodHandler[] newArray(int i) {
        return new InstagramAppLoginMethodHandler[i];
    }
}
