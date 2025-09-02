package app.notifee.core;

import android.os.Bundle;
import app.notifee.core.model.NotificationModel;
import java.util.concurrent.Callable;

public final /* synthetic */ class c$$ExternalSyntheticLambda5 implements Callable {
    public final /* synthetic */ Bundle f$0;
    public final /* synthetic */ NotificationModel f$1;

    public /* synthetic */ c$$ExternalSyntheticLambda5(Bundle bundle, NotificationModel notificationModel) {
        this.f$0 = bundle;
        this.f$1 = notificationModel;
    }

    public final Object call() {
        return c.a(this.f$0, this.f$1);
    }
}
