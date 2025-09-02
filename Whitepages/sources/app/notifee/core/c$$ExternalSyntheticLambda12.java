package app.notifee.core;

import app.notifee.core.model.NotificationAndroidModel;
import app.notifee.core.model.NotificationModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class c$$ExternalSyntheticLambda12 implements Continuation {
    public final /* synthetic */ NotificationAndroidModel f$0;
    public final /* synthetic */ NotificationModel f$1;

    public /* synthetic */ c$$ExternalSyntheticLambda12(NotificationAndroidModel notificationAndroidModel, NotificationModel notificationModel) {
        this.f$0 = notificationAndroidModel;
        this.f$1 = notificationModel;
    }

    public final Object then(Task task) {
        return c.a(this.f$0, this.f$1, task);
    }
}
