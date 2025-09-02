package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zad extends zac {
    protected final TaskCompletionSource zaa;

    public zad(int i, TaskCompletionSource taskCompletionSource) {
        super(i);
        this.zaa = taskCompletionSource;
    }

    /* access modifiers changed from: protected */
    public abstract void zac(zabq zabq) throws RemoteException;

    public final void zad(Status status) {
        this.zaa.trySetException(new ApiException(status));
    }

    public final void zae(Exception exc) {
        this.zaa.trySetException(exc);
    }

    public final void zaf(zabq zabq) throws DeadObjectException {
        try {
            zac(zabq);
        } catch (DeadObjectException e) {
            zad(zai.zah(e));
            throw e;
        } catch (RemoteException e2) {
            zad(zai.zah(e2));
        } catch (RuntimeException e3) {
            this.zaa.trySetException(e3);
        }
    }

    public void zag(zaad zaad, boolean z) {
    }
}
