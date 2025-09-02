package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbn;
import com.google.android.gms.internal.measurement.zzbo;
import java.util.ArrayList;

public abstract class zzgn extends zzbn implements zzgo {
    public zzgn() {
        super("com.google.android.gms.measurement.internal.ITriggerUrisCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        ArrayList<zzov> createTypedArrayList = parcel.createTypedArrayList(zzov.CREATOR);
        zzbo.zzc(parcel);
        zze(createTypedArrayList);
        return true;
    }
}
