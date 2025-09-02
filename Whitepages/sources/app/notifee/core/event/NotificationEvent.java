package app.notifee.core.event;

import android.os.Bundle;
import app.notifee.core.KeepForSdk;
import app.notifee.core.model.NotificationModel;

@KeepForSdk
public class NotificationEvent {
    @KeepForSdk
    public static final int TYPE_ACTION_PRESS = 2;
    @KeepForSdk
    public static final int TYPE_DELIVERED = 3;
    @KeepForSdk
    public static final int TYPE_DISMISSED = 0;
    @KeepForSdk
    public static final int TYPE_FG_ALREADY_EXIST = 8;
    @KeepForSdk
    public static final int TYPE_PRESS = 1;
    @KeepForSdk
    public static final int TYPE_TRIGGER_NOTIFICATION_CREATED = 7;
    public final int a;
    public final Bundle b;
    public final NotificationModel c;

    public NotificationEvent(int i, NotificationModel notificationModel) {
        this.a = i;
        this.c = notificationModel;
        this.b = null;
    }

    @KeepForSdk
    public Bundle getExtras() {
        return this.b;
    }

    @KeepForSdk
    public NotificationModel getNotification() {
        return this.c;
    }

    @KeepForSdk
    public int getType() {
        return this.a;
    }

    public NotificationEvent(int i, NotificationModel notificationModel, Bundle bundle) {
        this.a = i;
        this.c = notificationModel;
        this.b = bundle;
    }
}
