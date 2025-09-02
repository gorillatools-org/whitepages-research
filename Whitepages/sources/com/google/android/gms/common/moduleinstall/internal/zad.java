package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public abstract class zad extends zab implements zae {
    public zad() {
        super("com.google.android.gms.common.moduleinstall.internal.IModuleInstallCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zaa(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zac.zab(parcel);
            zae((Status) zac.zaa(parcel, Status.CREATOR), (ModuleAvailabilityResponse) zac.zaa(parcel, ModuleAvailabilityResponse.CREATOR));
        } else if (i == 2) {
            zac.zab(parcel);
            zad((Status) zac.zaa(parcel, Status.CREATOR), (ModuleInstallResponse) zac.zaa(parcel, ModuleInstallResponse.CREATOR));
        } else if (i == 3) {
            zac.zab(parcel);
            zac((Status) zac.zaa(parcel, Status.CREATOR), (ModuleInstallIntentResponse) zac.zaa(parcel, ModuleInstallIntentResponse.CREATOR));
        } else if (i != 4) {
            return false;
        } else {
            zac.zab(parcel);
            zab((Status) zac.zaa(parcel, Status.CREATOR));
        }
        return true;
    }
}
