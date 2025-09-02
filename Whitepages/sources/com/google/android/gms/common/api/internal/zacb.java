package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;

public final /* synthetic */ class zacb implements Runnable {
    public final /* synthetic */ ListenerHolder zaa;
    public final /* synthetic */ ListenerHolder.Notifier zab;

    public /* synthetic */ zacb(ListenerHolder listenerHolder, ListenerHolder.Notifier notifier) {
        this.zaa = listenerHolder;
        this.zab = notifier;
    }

    public final void run() {
        this.zaa.zaa(this.zab);
    }
}
