package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

final class zad extends zaa {
    private final BaseImplementation.ResultHolder zaa;

    public zad(BaseImplementation.ResultHolder resultHolder) {
        this.zaa = resultHolder;
    }

    public final void zab(int i) throws RemoteException {
        this.zaa.setResult(new Status(i));
    }
}
