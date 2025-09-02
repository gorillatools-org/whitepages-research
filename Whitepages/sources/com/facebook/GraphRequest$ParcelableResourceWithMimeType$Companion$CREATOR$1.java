package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.GraphRequest;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class GraphRequest$ParcelableResourceWithMimeType$Companion$CREATOR$1 implements Parcelable.Creator {
    GraphRequest$ParcelableResourceWithMimeType$Companion$CREATOR$1() {
    }

    public GraphRequest.ParcelableResourceWithMimeType createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        return new GraphRequest.ParcelableResourceWithMimeType(parcel, (DefaultConstructorMarker) null);
    }

    public GraphRequest.ParcelableResourceWithMimeType[] newArray(int i) {
        return new GraphRequest.ParcelableResourceWithMimeType[i];
    }
}
