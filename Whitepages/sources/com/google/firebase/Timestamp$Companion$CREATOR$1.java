package com.google.firebase;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class Timestamp$Companion$CREATOR$1 implements Parcelable.Creator<Timestamp> {
    Timestamp$Companion$CREATOR$1() {
    }

    public Timestamp createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new Timestamp(parcel.readLong(), parcel.readInt());
    }

    public Timestamp[] newArray(int i) {
        return new Timestamp[i];
    }
}
