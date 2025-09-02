package com.google.android.gms.common.api.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public final /* synthetic */ class zabk implements Executor {
    public final /* synthetic */ Handler zaa;

    public /* synthetic */ zabk(Handler handler) {
        this.zaa = handler;
    }

    public final void execute(Runnable runnable) {
        this.zaa.post(runnable);
    }
}
