package app.notifee.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.AlarmManagerCompat;
import app.notifee.core.database.a;
import app.notifee.core.model.NotificationModel;
import app.notifee.core.model.a;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.l;
import n.o.t.i.f.e.e.q;

public class b {
    public static final ExecutorService a = Executors.newCachedThreadPool();

    public static PendingIntent a(String str) {
        try {
            Context context = e.a;
            Intent intent = new Intent(context, NotificationAlarmReceiver.class);
            intent.putExtra("notificationId", str);
            return PendingIntent.getBroadcast(context, str.hashCode(), intent, 167772160);
        } catch (Exception e) {
            Logger.e("NotifeeAlarmManager", "Unable to create AlarmManager intent", e);
            return null;
        }
    }

    public static Object b(Task task) {
        return a.a(e.a).a(Boolean.TRUE).continueWith(new b$$ExternalSyntheticLambda5());
    }

    public static /* synthetic */ void c(Task task) {
        if (!task.isSuccessful()) {
            Logger.e("NotifeeAlarmManager", "Failed to display notification", task.getException());
        }
    }

    /* access modifiers changed from: private */
    public void d(Task task) {
        byte[] bArr;
        for (q qVar : (List) task.getResult()) {
            byte[] bArr2 = qVar.b;
            if (!(bArr2 == null || (bArr = qVar.c) == null)) {
                Bundle a2 = l.a(bArr);
                NotificationModel notificationModel = new NotificationModel(l.a(bArr2));
                if (l.a(a2.get("type")) == 0) {
                    app.notifee.core.model.a aVar = new app.notifee.core.model.a(a2);
                    if (aVar.d.booleanValue()) {
                        a(notificationModel, aVar);
                    }
                }
            }
        }
    }

    public void b() {
        Logger.d("NotifeeAlarmManager", "Reschedule Notifications on reboot");
        new a(e.a).a(Boolean.TRUE).addOnCompleteListener(new b$$ExternalSyntheticLambda0(this));
    }

    public static void a(Bundle bundle) {
        String string;
        if (bundle != null && (string = bundle.getString("notificationId")) != null) {
            a aVar = new a(e.a);
            aVar.b(string).continueWithTask(a, new b$$ExternalSyntheticLambda1(string)).addOnCompleteListener(new b$$ExternalSyntheticLambda2());
        }
    }

    public static Task a(String str, Task task) {
        byte[] bArr;
        q qVar = (q) task.getResult();
        if (qVar == null || qVar.b == null || (bArr = qVar.c) == null) {
            Logger.w("NotifeeAlarmManager", "Attempted to handle doScheduledWork but no notification data was found.");
            return null;
        }
        Bundle a2 = l.a(bArr);
        NotificationModel notificationModel = new NotificationModel(l.a(qVar.b));
        return c.b(notificationModel, a2).addOnCompleteListener(new b$$ExternalSyntheticLambda3(a2, notificationModel, str, qVar));
    }

    public static void a(Bundle bundle, NotificationModel notificationModel, String str, q qVar, Task task) {
        if (!task.isSuccessful()) {
            Logger.e("NotifeeAlarmManager", "Failed to display notification", task.getException());
        } else if (!bundle.containsKey("repeatFrequency") || l.a(bundle.get("repeatFrequency")) == -1) {
            a.a(e.a).a(str);
        } else {
            app.notifee.core.model.a aVar = new app.notifee.core.model.a(bundle);
            aVar.a();
            a(notificationModel, aVar);
            a.a(e.a).d(new q(str, qVar.b, l.a(bundle), Boolean.TRUE));
        }
    }

    public static void a(NotificationModel notificationModel, app.notifee.core.model.a aVar) {
        PendingIntent a2 = a(notificationModel.c());
        AlarmManager a3 = n.o.t.i.f.e.e.a.a();
        a.C0000a aVar2 = aVar.e;
        if (Build.VERSION.SDK_INT < 31 || !Arrays.asList(new a.C0000a[]{a.C0000a.SET_EXACT, a.C0000a.SET_EXACT_AND_ALLOW_WHILE_IDLE, a.C0000a.SET_ALARM_CLOCK}).contains(aVar2) || a3.canScheduleExactAlarms()) {
            aVar.a();
            int ordinal = aVar2.ordinal();
            if (ordinal == 0) {
                a3.set(1, aVar.g.longValue(), a2);
            } else if (ordinal == 1) {
                AlarmManagerCompat.setAndAllowWhileIdle(a3, 0, aVar.g.longValue(), a2);
            } else if (ordinal == 2) {
                AlarmManagerCompat.setExact(a3, 0, aVar.g.longValue(), a2);
            } else if (ordinal == 3) {
                AlarmManagerCompat.setExactAndAllowWhileIdle(a3, 0, aVar.g.longValue(), a2);
            } else if (ordinal == 4) {
                Context context = e.a;
                AlarmManagerCompat.setAlarmClock(a3, aVar.g.longValue(), PendingIntent.getActivity(context, notificationModel.c().hashCode(), context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()), 201326592), a2);
            }
        } else {
            System.err.println("Missing SCHEDULE_EXACT_ALARM permission. Trigger not scheduled. See: https://notifee.app/react-native/docs/triggers#android-12-limitations");
        }
    }

    public static Continuation a() {
        return new b$$ExternalSyntheticLambda4();
    }

    public static Object a(Task task) {
        if (!task.isSuccessful()) {
            return null;
        }
        for (q qVar : (List) task.getResult()) {
            PendingIntent a2 = a(qVar.a);
            AlarmManager a3 = n.o.t.i.f.e.e.a.a();
            if (a2 != null) {
                a3.cancel(a2);
            }
        }
        return null;
    }
}
