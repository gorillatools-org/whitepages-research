package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate;

final class zaab extends zag {
    private final ListenerHolder zaa;

    public zaab(ListenerHolder listenerHolder) {
        this.zaa = listenerHolder;
    }

    public final void zab(ModuleInstallStatusUpdate moduleInstallStatusUpdate) {
        this.zaa.notifyListener(new zaaa(this, moduleInstallStatusUpdate));
    }
}
