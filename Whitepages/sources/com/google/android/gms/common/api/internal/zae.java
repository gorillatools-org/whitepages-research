package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;

public final class zae extends zai {
    protected final BaseImplementation.ApiMethodImpl zaa;

    public zae(int i, BaseImplementation.ApiMethodImpl apiMethodImpl) {
        super(i);
        this.zaa = (BaseImplementation.ApiMethodImpl) Preconditions.checkNotNull(apiMethodImpl, "Null methods are not runnable.");
    }

    public final void zad(Status status) {
        try {
            this.zaa.setFailedResult(status);
        } catch (IllegalStateException e) {
            Log.w("ApiCallRunner", "Exception reporting failure", e);
        }
    }

    public final void zae(Exception exc) {
        String simpleName = exc.getClass().getSimpleName();
        String localizedMessage = exc.getLocalizedMessage();
        try {
            this.zaa.setFailedResult(new Status(10, simpleName + ": " + localizedMessage));
        } catch (IllegalStateException e) {
            Log.w("ApiCallRunner", "Exception reporting failure", e);
        }
    }

    public final void zaf(zabq zabq) throws DeadObjectException {
        try {
            this.zaa.run(zabq.zaf());
        } catch (RuntimeException e) {
            zae(e);
        }
    }

    public final void zag(zaad zaad, boolean z) {
        zaad.zac(this.zaa, z);
    }
}
