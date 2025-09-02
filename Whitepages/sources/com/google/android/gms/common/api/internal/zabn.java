package com.google.android.gms.common.api.internal;

final class zabn implements Runnable {
    final /* synthetic */ int zaa;
    final /* synthetic */ zabq zab;

    zabn(zabq zabq, int i) {
        this.zab = zabq;
        this.zaa = i;
    }

    public final void run() {
        this.zab.zaI(this.zaa);
    }
}
