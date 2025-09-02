package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzaw;
import com.google.android.gms.location.zzax;

@SafeParcelable.Class(creator = "DeviceOrientationRequestUpdateDataCreator")
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestUpdateData.OPERATION_ADD", id = 1)
    final int zza;
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 2)
    final zzj zzb;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getDeviceOrientationListenerBinder", id = 3, type = "android.os.IBinder")
    final zzax zzc;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 4, type = "android.os.IBinder")
    final zzai zzd;

    @SafeParcelable.Constructor
    zzl(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) zzj zzj, @SafeParcelable.Param(id = 3) IBinder iBinder, @SafeParcelable.Param(id = 4) IBinder iBinder2) {
        zzax zzax;
        this.zza = i;
        this.zzb = zzj;
        zzai zzai = null;
        if (iBinder == null) {
            zzax = null;
        } else {
            zzax = zzaw.zzb(iBinder);
        }
        this.zzc = zzax;
        if (iBinder2 != null) {
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            if (queryLocalInterface instanceof zzai) {
                zzai = (zzai) queryLocalInterface;
            } else {
                zzai = new zzag(iBinder2);
            }
        }
        this.zzd = zzai;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        zzax zzax = this.zzc;
        IBinder iBinder2 = null;
        if (zzax == null) {
            iBinder = null;
        } else {
            iBinder = zzax.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        zzai zzai = this.zzd;
        if (zzai != null) {
            iBinder2 = zzai.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 4, iBinder2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
