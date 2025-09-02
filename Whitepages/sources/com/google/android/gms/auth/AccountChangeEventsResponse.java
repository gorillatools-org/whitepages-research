package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "AccountChangeEventsResponseCreator")
public class AccountChangeEventsResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AccountChangeEventsResponse> CREATOR = new zzc();
    @SafeParcelable.VersionField(id = 1)
    final int zza;
    @SafeParcelable.Field(id = 2)
    final List zzb;

    @SafeParcelable.Constructor
    AccountChangeEventsResponse(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) List list) {
        this.zza = i;
        this.zzb = (List) Preconditions.checkNotNull(list);
    }

    public List<AccountChangeEvent> getEvents() {
        return this.zzb;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public AccountChangeEventsResponse(List<AccountChangeEvent> list) {
        this.zza = 1;
        this.zzb = (List) Preconditions.checkNotNull(list);
    }
}
