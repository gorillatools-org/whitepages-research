package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SavePasswordResultCreator")
@Deprecated
public class SavePasswordResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SavePasswordResult> CREATOR = new zbq();
    @SafeParcelable.Field(getter = "getPendingIntent", id = 1)
    private final PendingIntent zba;

    @SafeParcelable.Constructor
    public SavePasswordResult(@SafeParcelable.Param(id = 1) PendingIntent pendingIntent) {
        this.zba = (PendingIntent) Preconditions.checkNotNull(pendingIntent);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SavePasswordResult)) {
            return false;
        }
        return Objects.equal(this.zba, ((SavePasswordResult) obj).zba);
    }

    public PendingIntent getPendingIntent() {
        return this.zba;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getPendingIntent(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
