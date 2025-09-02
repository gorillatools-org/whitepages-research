package com.google.android.gms.internal.play_billing;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zze extends zzw implements zzf {
    public zze() {
        super("com.android.vending.billing.IInAppBillingCreateExternalPaymentReportingDetailsCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zzb(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzx.zzb(parcel);
        zza((Bundle) zzx.zza(parcel, Bundle.CREATOR));
        return true;
    }
}
