package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;

public class zzh extends Handler {
    private final Looper zza = Looper.getMainLooper();

    public zzh() {
    }

    public zzh(Looper looper) {
        super(looper);
    }

    public zzh(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
