package app.notifee.core;

import android.os.Bundle;
import app.notifee.core.model.NotificationModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class c$$ExternalSyntheticLambda6 implements Continuation {
    public final /* synthetic */ NotificationModel f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ c$$ExternalSyntheticLambda6(NotificationModel notificationModel, Bundle bundle) {
        this.f$0 = notificationModel;
        this.f$1 = bundle;
    }

    public final Object then(Task task) {
        return c.a(this.f$0, this.f$1, task);
    }
}
