package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ExecutorService;

@KeepForSdk
public final class CallbackExecutor {
    private CallbackExecutor() {
    }

    @KeepForSdk
    public static ExecutorService executorService() {
        return zzj.zza;
    }
}
