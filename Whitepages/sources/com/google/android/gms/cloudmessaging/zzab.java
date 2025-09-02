package com.google.android.gms.cloudmessaging;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class zzab implements Continuation {
    public static final /* synthetic */ zzab zza = new zzab();

    private /* synthetic */ zzab() {
    }

    public final Object then(Task task) {
        Intent intent = (Intent) ((Bundle) task.getResult()).getParcelable("notification_data");
        if (intent != null) {
            return new CloudMessage(intent);
        }
        return null;
    }
}
