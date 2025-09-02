package com.google.android.gms.cloudmessaging;

import android.util.Log;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;

public final /* synthetic */ class zzac implements Runnable {
    public final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzac(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void run() {
        if (this.zza.trySetException(new IOException(InstanceID.ERROR_TIMEOUT))) {
            Log.w("Rpc", "No response");
        }
    }
}
