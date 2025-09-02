package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicReference;

public final /* synthetic */ class zai implements RemoteCall {
    public final /* synthetic */ zay zaa;
    public final /* synthetic */ AtomicReference zab;
    public final /* synthetic */ InstallStatusListener zac;
    public final /* synthetic */ ApiFeatureRequest zad;
    public final /* synthetic */ zaab zae;

    public /* synthetic */ zai(zay zay, AtomicReference atomicReference, InstallStatusListener installStatusListener, ApiFeatureRequest apiFeatureRequest, zaab zaab) {
        this.zaa = zay;
        this.zab = atomicReference;
        this.zac = installStatusListener;
        this.zad = apiFeatureRequest;
        this.zae = zaab;
    }

    public final void accept(Object obj, Object obj2) {
        ((zaf) ((zaz) obj).getService()).zag(new zau(this.zaa, this.zab, (TaskCompletionSource) obj2, this.zac), this.zad, this.zae);
    }
}
