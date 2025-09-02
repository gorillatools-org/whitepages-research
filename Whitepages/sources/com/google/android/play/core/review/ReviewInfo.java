package com.google.android.play.core.review;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class ReviewInfo implements Parcelable {
    public static final Parcelable.Creator<ReviewInfo> CREATOR = new zzb();

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(zza(), 0);
        parcel.writeInt(zzb() ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public abstract PendingIntent zza();

    /* access modifiers changed from: package-private */
    public abstract boolean zzb();
}
