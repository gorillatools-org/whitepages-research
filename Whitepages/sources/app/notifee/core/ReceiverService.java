package app.notifee.core;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.model.NotificationAndroidModel;
import app.notifee.core.model.NotificationAndroidPressActionModel;
import app.notifee.core.model.NotificationModel;
import java.util.concurrent.atomic.AtomicInteger;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import n.o.t.i.f.e.e.g;
import n.o.t.i.f.e.e.h;
import n.o.t.i.f.e.e.i;

public class ReceiverService extends Service {
    public static final AtomicInteger a = new AtomicInteger(0);

    public static PendingIntent a(String str, String[] strArr, Bundle... bundleArr) {
        Context context = e.a;
        Intent intent = new Intent(context, ReceiverService.class);
        intent.setAction(str);
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            if (i <= bundleArr.length - 1) {
                intent.putExtra(str2, bundleArr[i]);
            } else {
                intent.putExtra(str2, (String) null);
            }
        }
        return PendingIntent.getService(context, a.getAndIncrement(), intent, 167772160);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        CharSequence charSequence;
        NotificationAndroidPressActionModel notificationAndroidPressActionModel;
        String action = intent.getAction();
        if (action == null) {
            return 2;
        }
        char c = 65535;
        switch (action.hashCode()) {
            case -2049703147:
                if (action.equals("app.notifee.core.ReceiverService.ACTION_PRESS_INTENT")) {
                    c = 0;
                    break;
                }
                break;
            case -2034314204:
                if (action.equals("app.notifee.core.ReceiverService.DELETE_INTENT")) {
                    c = 1;
                    break;
                }
                break;
            case -1961135292:
                if (action.equals("app.notifee.core.ReceiverService.PRESS_INTENT")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Bundle bundleExtra = intent.getBundleExtra("notification");
                Bundle bundleExtra2 = intent.getBundleExtra("pressAction");
                if (!(bundleExtra == null || bundleExtra2 == null)) {
                    NotificationModel notificationModel = new NotificationModel(bundleExtra);
                    NotificationAndroidModel a2 = notificationModel.a();
                    NotificationAndroidPressActionModel fromBundle = NotificationAndroidPressActionModel.fromBundle(bundleExtra2);
                    Bundle bundle = new Bundle();
                    bundle.putBundle("pressAction", fromBundle.toBundle());
                    Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
                    if (!(resultsFromIntent == null || (charSequence = resultsFromIntent.getCharSequence("app.notifee.core.ReceiverService.REMOTE_INPUT_RECEIVER_KEY")) == null)) {
                        bundle.putString("input", charSequence.toString());
                    }
                    f.a(new NotificationEvent(2, notificationModel, bundle));
                    if (notificationModel.a().getAutoCancel().booleanValue()) {
                        NotificationManagerCompat.from(getApplicationContext()).cancel(a2.getTag(), notificationModel.c().hashCode());
                    }
                    String launchActivity = fromBundle.getLaunchActivity();
                    String mainComponent = fromBundle.getMainComponent();
                    if (!(launchActivity == null && mainComponent == null)) {
                        a(new g(notificationModel, bundle), launchActivity, mainComponent, fromBundle.getLaunchActivityFlags());
                        int i3 = e.a.getApplicationInfo().targetSdkVersion;
                        if (Build.VERSION.SDK_INT < 31) {
                            e.a.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                            break;
                        }
                    }
                }
                break;
            case 1:
                Bundle bundleExtra3 = intent.getBundleExtra("notification");
                if (bundleExtra3 != null) {
                    f.a(new NotificationEvent(0, new NotificationModel(bundleExtra3)));
                    break;
                }
                break;
            case 2:
                Bundle bundleExtra4 = intent.getBundleExtra("notification");
                if (bundleExtra4 != null) {
                    NotificationModel notificationModel2 = new NotificationModel(bundleExtra4);
                    Bundle bundleExtra5 = intent.getBundleExtra("pressAction");
                    Bundle bundle2 = new Bundle();
                    if (bundleExtra5 != null) {
                        notificationAndroidPressActionModel = NotificationAndroidPressActionModel.fromBundle(bundleExtra5);
                        bundle2.putBundle("pressAction", notificationAndroidPressActionModel.toBundle());
                    } else {
                        notificationAndroidPressActionModel = null;
                    }
                    f.a(new NotificationEvent(1, notificationModel2, bundle2));
                    if (notificationAndroidPressActionModel != null) {
                        String launchActivity2 = notificationAndroidPressActionModel.getLaunchActivity();
                        String mainComponent2 = notificationAndroidPressActionModel.getMainComponent();
                        if (!(launchActivity2 == null && mainComponent2 == null)) {
                            a(new g(notificationModel2, bundle2), launchActivity2, mainComponent2, notificationAndroidPressActionModel.getLaunchActivityFlags());
                            break;
                        }
                    }
                }
                break;
        }
        return 2;
    }

    public final void a(g gVar, String str, String str2, int i) {
        Class a2 = h.a(str);
        if (a2 == null) {
            Logger.e("ReceiverService", "Failed to get launch activity");
            return;
        }
        Intent intent = new Intent(getApplicationContext(), a2);
        if (i != -1) {
            intent.addFlags(i);
        }
        if (str2 != null) {
            intent.putExtra("mainComponent", str2);
        }
        try {
            PendingIntent.getActivity(getApplicationContext(), gVar.a.b().intValue(), intent, 167772160).send();
            f.b(gVar);
            if (str2 != null) {
                f.b(new i(str2));
            }
        } catch (Exception e) {
            Logger.e("ReceiverService", "Failed to send PendingIntent from launchPendingIntentActivity for notification " + gVar.a.c(), e);
        }
    }
}
