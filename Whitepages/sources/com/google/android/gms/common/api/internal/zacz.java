package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zau;

final class zacz extends zau {
    final /* synthetic */ zada zaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zacz(zada zada, Looper looper) {
        super(looper);
        this.zaa = zada;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            PendingResult pendingResult = (PendingResult) message.obj;
            synchronized (this.zaa.zae) {
                try {
                    zada zada = (zada) Preconditions.checkNotNull(this.zaa.zab);
                    if (pendingResult == null) {
                        zada.zaj(new Status(13, "Transform returned null"));
                    } else if (pendingResult instanceof zacp) {
                        zada.zaj(((zacp) pendingResult).zaa());
                    } else {
                        zada.zai(pendingResult);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else if (i != 1) {
            Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + i);
        } else {
            RuntimeException runtimeException = (RuntimeException) message.obj;
            Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: ".concat(String.valueOf(runtimeException.getMessage())));
            throw runtimeException;
        }
    }
}
