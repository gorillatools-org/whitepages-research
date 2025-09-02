package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zaj implements RemoteCall {
    public final /* synthetic */ zay zaa;
    public final /* synthetic */ zaab zab;

    public /* synthetic */ zaj(zay zay, zaab zaab) {
        this.zaa = zay;
        this.zab = zaab;
    }

    public final void accept(Object obj, Object obj2) {
        ((zaf) ((zaz) obj).getService()).zai(new zav(this.zaa, (TaskCompletionSource) obj2), this.zab);
    }
}
