package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

public final /* synthetic */ class zzaa implements Continuation {
    public static final /* synthetic */ zzaa zza = new zzaa();

    private /* synthetic */ zzaa() {
    }

    public final Object then(Task task) {
        if (task.isSuccessful()) {
            return (Bundle) task.getResult();
        }
        if (Log.isLoggable("Rpc", 3)) {
            Log.d("Rpc", "Error making request: ".concat(String.valueOf(task.getException())));
        }
        throw new IOException(InstanceID.ERROR_SERVICE_NOT_AVAILABLE, task.getException());
    }
}
