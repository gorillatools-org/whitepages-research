package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class zzx implements SuccessContinuation {
    public static final /* synthetic */ zzx zza = new zzx();

    private /* synthetic */ zzx() {
    }

    public final Task then(Object obj) {
        return Rpc.zza((Bundle) obj);
    }
}
