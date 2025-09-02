package app.notifee.core.event;

import app.notifee.core.KeepForSdk;
import app.notifee.core.interfaces.MethodCallResult;
import app.notifee.core.model.NotificationModel;

@KeepForSdk
public class ForegroundServiceEvent {
    public final NotificationModel a;
    public MethodCallResult b;
    public boolean c = false;

    public ForegroundServiceEvent(NotificationModel notificationModel, MethodCallResult methodCallResult) {
        this.a = notificationModel;
        this.b = methodCallResult;
    }

    @KeepForSdk
    public NotificationModel getNotification() {
        return this.a;
    }

    @KeepForSdk
    public void setCompletionResult() {
        if (!this.c) {
            this.c = true;
            this.b.onComplete((Exception) null, null);
        }
    }
}
