package io.invertase.notifee;

import app.notifee.core.InitProvider;
import app.notifee.core.Notifee;

public class NotifeeInitProvider extends InitProvider {
    public boolean onCreate() {
        boolean onCreate = super.onCreate();
        Notifee.initialize(new NotifeeEventSubscriber());
        return onCreate;
    }
}
