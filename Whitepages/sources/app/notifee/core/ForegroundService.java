package app.notifee.core;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationManagerCompat;
import app.notifee.core.event.ForegroundServiceEvent;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.model.NotificationModel;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;

public class ForegroundService extends Service {
    public static String a;

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Exception exc, Void voidR) {
        stopForeground(true);
        a = null;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        boolean z = false;
        if (intent == null || "app.notifee.core.ForegroundService.STOP".equals(intent.getAction())) {
            stopSelf();
            a = null;
            return 0;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return 2;
        }
        int i3 = extras.getInt("hashCode");
        Notification notification = (Notification) extras.getParcelable("notification");
        Bundle bundle = extras.getBundle("notificationBundle");
        boolean z2 = notification != null;
        if (bundle != null) {
            z = true;
        }
        if (!z || !z2) {
            return 2;
        }
        NotificationModel notificationModel = new NotificationModel(bundle);
        String str = a;
        if (str == null) {
            a = notificationModel.c();
            startForeground(i3, notification);
            f.a(new ForegroundServiceEvent(notificationModel, new ForegroundService$$ExternalSyntheticLambda0(this)));
            return 2;
        } else if (str.equals(notificationModel.c())) {
            NotificationManagerCompat.from(e.a).notify(i3, notification);
            return 2;
        } else {
            f.a(new NotificationEvent(8, notificationModel));
            return 2;
        }
    }
}
