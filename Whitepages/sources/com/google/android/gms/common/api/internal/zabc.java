package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zau;

final class zabc extends zau {
    final /* synthetic */ zabe zaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zabc(zabe zabe, Looper looper) {
        super(looper);
        this.zaa = zabe;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            zabe.zaj(this.zaa);
        } else if (i != 2) {
            Log.w("GoogleApiClientImpl", "Unknown message id: " + i);
        } else {
            zabe.zai(this.zaa);
        }
    }
}
