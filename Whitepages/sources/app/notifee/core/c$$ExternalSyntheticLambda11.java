package app.notifee.core;

import app.notifee.core.model.NotificationAndroidModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class c$$ExternalSyntheticLambda11 implements Continuation {
    public final /* synthetic */ NotificationAndroidModel f$0;

    public /* synthetic */ c$$ExternalSyntheticLambda11(NotificationAndroidModel notificationAndroidModel) {
        this.f$0 = notificationAndroidModel;
    }

    public final Object then(Task task) {
        return c.a(this.f$0, task);
    }
}
