package app.notifee.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationAlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        b.a(intent.getExtras());
    }
}
