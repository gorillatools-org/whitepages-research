package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zah extends zad {
    public final ListenerHolder.ListenerKey zab;

    public zah(ListenerHolder.ListenerKey listenerKey, TaskCompletionSource taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zab = listenerKey;
    }

    public final boolean zaa(zabq zabq) {
        zaci zaci = (zaci) zabq.zah().get(this.zab);
        return zaci != null && zaci.zaa.zab();
    }

    public final Feature[] zab(zabq zabq) {
        zaci zaci = (zaci) zabq.zah().get(this.zab);
        if (zaci == null) {
            return null;
        }
        return zaci.zaa.getRequiredFeatures();
    }

    public final void zac(zabq zabq) throws RemoteException {
        zaci zaci = (zaci) zabq.zah().remove(this.zab);
        if (zaci != null) {
            zaci.zab.unregisterListener(zabq.zaf(), this.zaa);
            zaci.zaa.clearListener();
            return;
        }
        this.zaa.trySetResult(Boolean.FALSE);
    }

    public final /* bridge */ /* synthetic */ void zag(zaad zaad, boolean z) {
    }
}
