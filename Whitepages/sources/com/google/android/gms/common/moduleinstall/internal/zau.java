package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicReference;

final class zau extends zaa {
    final /* synthetic */ AtomicReference zaa;
    final /* synthetic */ TaskCompletionSource zab;
    final /* synthetic */ InstallStatusListener zac;
    final /* synthetic */ zay zad;

    zau(zay zay, AtomicReference atomicReference, TaskCompletionSource taskCompletionSource, InstallStatusListener installStatusListener) {
        this.zad = zay;
        this.zaa = atomicReference;
        this.zab = taskCompletionSource;
        this.zac = installStatusListener;
    }

    public final void zad(Status status, ModuleInstallResponse moduleInstallResponse) {
        if (moduleInstallResponse != null) {
            this.zaa.set(moduleInstallResponse);
        }
        TaskUtil.trySetResultOrApiException(status, null, this.zab);
        if (!status.isSuccess() || (moduleInstallResponse != null && moduleInstallResponse.zaa())) {
            this.zad.doUnregisterEventListener(ListenerHolders.createListenerKey(this.zac, InstallStatusListener.class.getSimpleName()), 27306);
        }
    }
}
