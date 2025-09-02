package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate;

final class zaaa implements ListenerHolder.Notifier {
    final /* synthetic */ ModuleInstallStatusUpdate zaa;

    zaaa(zaab zaab, ModuleInstallStatusUpdate moduleInstallStatusUpdate) {
        this.zaa = moduleInstallStatusUpdate;
    }

    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        ((InstallStatusListener) obj).onInstallStatusUpdated(this.zaa);
    }

    public final void onNotifyListenerFailed() {
    }
}
