package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "SleepSegmentRequestCreator")
@SafeParcelable.Reserved({1000})
public class SleepSegmentRequest extends AbstractSafeParcelable {
    public static final int CLASSIFY_EVENTS_ONLY = 2;
    public static final Parcelable.Creator<SleepSegmentRequest> CREATOR = new zzbw();
    public static final int SEGMENT_AND_CLASSIFY_EVENTS = 0;
    public static final int SEGMENT_EVENTS_ONLY = 1;
    @SafeParcelable.Field(getter = "getUserPreferredSleepWindow", id = 1)
    private final List<zzbx> zza;
    @SafeParcelable.Field(defaultValue = "0", getter = "getRequestedDataType", id = 2)
    private final int zzb;

    public SleepSegmentRequest(int i) {
        this((List<zzbx>) null, i);
    }

    public static SleepSegmentRequest getDefaultSleepSegmentRequest() {
        return new SleepSegmentRequest((List<zzbx>) null, 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SleepSegmentRequest)) {
            return false;
        }
        SleepSegmentRequest sleepSegmentRequest = (SleepSegmentRequest) obj;
        return Objects.equal(this.zza, sleepSegmentRequest.zza) && this.zzb == sleepSegmentRequest.zzb;
    }

    public int getRequestedDataType() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, Integer.valueOf(this.zzb));
    }

    public void writeToParcel(Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeInt(parcel, 2, getRequestedDataType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepSegmentRequest(@SafeParcelable.Param(id = 1) List<zzbx> list, @SafeParcelable.Param(id = 2) int i) {
        this.zza = list;
        this.zzb = i;
    }
}
