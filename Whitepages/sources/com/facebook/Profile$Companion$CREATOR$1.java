package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Profile$Companion$CREATOR$1 implements Parcelable.Creator {
    Profile$Companion$CREATOR$1() {
    }

    public Profile createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new Profile(parcel, (DefaultConstructorMarker) null);
    }

    public Profile[] newArray(int i) {
        return new Profile[i];
    }
}
