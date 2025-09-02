package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zacq implements Runnable {
    final /* synthetic */ zact zaa;

    zacq(zact zact) {
        this.zaa = zact;
    }

    public final void run() {
        this.zaa.zah.zae(new ConnectionResult(4));
    }
}
