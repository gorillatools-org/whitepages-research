package app.notifee.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.core.app.NotificationManagerCompat;
import app.notifee.core.interfaces.EventListener;
import app.notifee.core.interfaces.MethodCallResult;
import app.notifee.core.model.NotificationModel;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import n.o.t.i.f.e.e.a;
import n.o.t.i.f.e.e.b;
import n.o.t.i.f.e.e.c;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import n.o.t.i.f.e.e.g;
import n.o.t.i.f.e.e.h;
import n.o.t.i.f.e.e.i;
import n.o.t.i.f.e.e.m;

@KeepForSdk
public class Notifee {
    @KeepForSdk
    public static final int REQUEST_CODE_NOTIFICATION_PERMISSION = 11111;
    public static Notifee b = null;
    public static boolean c = false;
    public MethodCallResult a;

    public static /* synthetic */ void a(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void b(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void c(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void d(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void e(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void f(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void g(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, null);
            return;
        }
        Logger.e("API", "createTriggerNotification", task.getException());
        methodCallResult.onComplete(task.getException(), null);
    }

    @KeepForSdk
    public static Context getContext() {
        return e.a;
    }

    @KeepForSdk
    public static Notifee getInstance() {
        if (!c) {
            Logger.w("API", "getInstance() accessed before event listener is initialized");
            b = new Notifee();
        }
        return b;
    }

    public static /* synthetic */ void h(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, null);
            return;
        }
        Logger.e("API", "displayNotification", task.getException());
        methodCallResult.onComplete(task.getException(), null);
    }

    public static /* synthetic */ void i(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Bundle) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    @KeepForSdk
    public static void initialize(EventListener eventListener) {
        synchronized (Notifee.class) {
            try {
                if (!c) {
                    if (b == null) {
                        b = new Notifee();
                    }
                    if (eventListener != null) {
                        EventSubscriber.register(eventListener);
                    }
                    c = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static /* synthetic */ void j(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Bundle) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void k(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (List) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void l(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (List) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void m(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (List) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void n(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Boolean) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void o(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete((Exception) null, (Boolean) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    @KeepForSdk
    public void cancelAllNotifications(int i, MethodCallResult<Void> methodCallResult) {
        c.a(i).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda12(methodCallResult));
    }

    @KeepForSdk
    public void cancelAllNotificationsWithIds(int i, List<String> list, String str, MethodCallResult<Void> methodCallResult) {
        c.a(i, (List) list, str).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda14(methodCallResult));
    }

    @KeepForSdk
    public void createChannel(Bundle bundle, MethodCallResult<Void> methodCallResult) {
        a.a(new c(bundle)).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda5(methodCallResult));
    }

    @KeepForSdk
    public void createChannelGroup(Bundle bundle, MethodCallResult<Void> methodCallResult) {
        a.a(new b(bundle)).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda8(methodCallResult));
    }

    @KeepForSdk
    public void createChannelGroups(List<Bundle> list, MethodCallResult<Void> methodCallResult) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Bundle bVar : list) {
            arrayList.add(new b(bVar));
        }
        a.a((List) arrayList).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda7(methodCallResult));
    }

    @KeepForSdk
    public void createChannels(List<Bundle> list, MethodCallResult<Void> methodCallResult) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Bundle cVar : list) {
            arrayList.add(new c(cVar));
        }
        a.b((List) arrayList).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda9(methodCallResult));
    }

    @KeepForSdk
    public void createTriggerNotification(Bundle bundle, Bundle bundle2, MethodCallResult<Void> methodCallResult) {
        c.a(new NotificationModel(bundle), bundle2).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda16(methodCallResult));
    }

    @KeepForSdk
    public void deleteChannel(String str, MethodCallResult<Void> methodCallResult) {
        ExecutorService executorService = a.a;
        NotificationManagerCompat.from(e.a).deleteNotificationChannel(str);
        methodCallResult.onComplete((Exception) null, null);
    }

    @KeepForSdk
    public void deleteChannelGroup(String str, MethodCallResult<Void> methodCallResult) {
        ExecutorService executorService = a.a;
        NotificationManagerCompat.from(e.a).deleteNotificationChannelGroup(str);
        methodCallResult.onComplete((Exception) null, null);
    }

    @KeepForSdk
    public void displayNotification(Bundle bundle, MethodCallResult<Void> methodCallResult) {
        c.b(new NotificationModel(bundle), (Bundle) null).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda4(methodCallResult));
    }

    @KeepForSdk
    public void getChannel(String str, MethodCallResult<Bundle> methodCallResult) {
        a.a(str).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda2(methodCallResult));
    }

    @KeepForSdk
    public void getChannelGroup(String str, MethodCallResult<Bundle> methodCallResult) {
        a.b(str).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda10(methodCallResult));
    }

    @KeepForSdk
    public void getChannelGroups(MethodCallResult<List<Bundle>> methodCallResult) {
        a.a().addOnCompleteListener(new Notifee$$ExternalSyntheticLambda11(methodCallResult));
    }

    @KeepForSdk
    public void getChannels(MethodCallResult<List<Bundle>> methodCallResult) {
        a.b().addOnCompleteListener(new Notifee$$ExternalSyntheticLambda3(methodCallResult));
    }

    @KeepForSdk
    public void getDisplayedNotifications(MethodCallResult<List<Bundle>> methodCallResult) {
        c.a().addOnCompleteListener(new Notifee$$ExternalSyntheticLambda1(methodCallResult));
    }

    @KeepForSdk
    public void getInitialNotification(Activity activity, MethodCallResult<Bundle> methodCallResult) {
        g gVar = (g) f.b.a.removeStickyEvent(g.class);
        Bundle bundle = new Bundle();
        if (gVar != null) {
            bundle.putAll(gVar.b);
            bundle.putBundle("notification", gVar.a.toBundle());
            methodCallResult.onComplete((Exception) null, bundle);
            return;
        }
        if (activity != null) {
            try {
                Intent intent = activity.getIntent();
                if (!(intent == null || intent.getExtras() == null || !intent.hasExtra("notification"))) {
                    bundle.putBundle("notification", intent.getBundleExtra("notification"));
                    methodCallResult.onComplete((Exception) null, bundle);
                    return;
                }
            } catch (Exception e) {
                Logger.e("API", "getInitialNotification", e);
            }
        }
        methodCallResult.onComplete((Exception) null, null);
    }

    @KeepForSdk
    public String getMainComponent(String str) {
        i iVar = (i) f.b.a.removeStickyEvent(i.class);
        if (iVar == null) {
            return str;
        }
        return iVar.a;
    }

    @KeepForSdk
    public void getNotificationSettings(MethodCallResult<Bundle> methodCallResult) {
        boolean areNotificationsEnabled = NotificationManagerCompat.from(e.a).areNotificationsEnabled();
        Bundle bundle = new Bundle();
        if (areNotificationsEnabled) {
            bundle.putInt("authorizationStatus", 1);
        } else {
            bundle.putInt("authorizationStatus", 0);
        }
        boolean m = Build.VERSION.SDK_INT >= 31 ? a.a().canScheduleExactAlarms() : true;
        Bundle bundle2 = new Bundle();
        if (m) {
            bundle2.putInt("alarm", 1);
        } else {
            bundle2.putInt("alarm", 0);
        }
        bundle.putBundle("android", bundle2);
        methodCallResult.onComplete((Exception) null, bundle);
    }

    @KeepForSdk
    public void getPowerManagerInfo(MethodCallResult<Bundle> methodCallResult) {
        String a2 = h.a(m.a(e.a));
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.RELEASE;
        Bundle bundle = new Bundle();
        bundle.putString("manufacturer", str);
        bundle.putString("model", str2);
        bundle.putString("version", str3);
        bundle.putString("activity", a2);
        methodCallResult.onComplete((Exception) null, bundle);
    }

    @KeepForSdk
    public void getTriggerNotificationIds(MethodCallResult<List<String>> methodCallResult) {
        c.a((MethodCallResult) methodCallResult);
    }

    @KeepForSdk
    public void getTriggerNotifications(MethodCallResult<List<Bundle>> methodCallResult) {
        c.b((MethodCallResult) methodCallResult);
    }

    @KeepForSdk
    public void isBatteryOptimizationEnabled(MethodCallResult<Boolean> methodCallResult) {
        Context context = e.a;
        methodCallResult.onComplete((Exception) null, Boolean.valueOf(!((PowerManager) context.getSystemService("power")).isIgnoringBatteryOptimizations(context.getPackageName())));
    }

    @KeepForSdk
    public void isChannelBlocked(String str, MethodCallResult<Boolean> methodCallResult) {
        a.c(str).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda6(methodCallResult));
    }

    @KeepForSdk
    public void isChannelCreated(String str, MethodCallResult<Boolean> methodCallResult) {
        a.d(str).addOnCompleteListener(new Notifee$$ExternalSyntheticLambda15(methodCallResult));
    }

    @KeepForSdk
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        MethodCallResult methodCallResult;
        if (i != 11111 || (methodCallResult = this.a) == null) {
            return false;
        }
        getNotificationSettings(methodCallResult);
        return true;
    }

    @KeepForSdk
    public void openAlarmPermissionSettings(Activity activity, MethodCallResult<Void> methodCallResult) {
        if (Build.VERSION.SDK_INT >= 31) {
            try {
                Intent intent = new Intent();
                intent.setFlags(268435456);
                intent.setAction("android.settings.REQUEST_SCHEDULE_EXACT_ALARM");
                String packageName = e.a.getPackageName();
                intent.setData(Uri.parse("package:" + packageName));
                h.a(activity, intent);
            } catch (Exception e) {
                Logger.e("AlarmUtils", "An error occurred whilst trying to open alarm permission settings", e);
            }
        }
        methodCallResult.onComplete((Exception) null, null);
    }

    @KeepForSdk
    public void openBatteryOptimizationSettings(Activity activity, MethodCallResult<Void> methodCallResult) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS");
            intent.setFlags(268435456);
            if (activity != null) {
                if (!h.a(e.a, intent)) {
                    Logger.d("PowerManagerUtils", "battery optimization settings is not available on device");
                } else {
                    h.a(activity, intent);
                }
            }
        } catch (Exception e) {
            Logger.e("PowerManagerUtils", "An error occurred whilst trying to open battery optimization settings", e);
        }
        methodCallResult.onComplete((Exception) null, null);
    }

    @KeepForSdk
    public void openNotificationSettings(String str, Activity activity, MethodCallResult<Void> methodCallResult) {
        Intent intent;
        if (getContext() == null || activity == null) {
            Logger.d("openNotificationSettings", "attempted to start activity but no current activity or context was available.");
            methodCallResult.onComplete((Exception) null, null);
            return;
        }
        if (str != null) {
            intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.CHANNEL_ID", str);
        } else {
            intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
        }
        intent.putExtra("android.provider.extra.APP_PACKAGE", getContext().getPackageName());
        intent.setFlags(268435456);
        activity.runOnUiThread(new Notifee$$ExternalSyntheticLambda13(intent));
        methodCallResult.onComplete((Exception) null, null);
    }

    @KeepForSdk
    public void openPowerManagerSettings(Activity activity, MethodCallResult<Void> methodCallResult) {
        Intent intent;
        synchronized (m.class) {
            intent = m.a;
        }
        if (intent == null) {
            intent = m.a(e.a);
        }
        if (intent != null) {
            try {
                intent.setFlags(268435456);
                h.a(activity, intent);
            } catch (Exception e) {
                Logger.e("PowerManagerUtils", "Unable to start activity: " + h.a(intent), e);
            }
        } else {
            Logger.w("PowerManagerUtils", "Unable to find an activity to open the device's power manager");
        }
        methodCallResult.onComplete((Exception) null, null);
    }

    @KeepForSdk
    public void setRequestPermissionCallback(MethodCallResult<Bundle> methodCallResult) {
        this.a = methodCallResult;
    }

    @KeepForSdk
    public void stopForegroundService(MethodCallResult<Void> methodCallResult) {
        String str = ForegroundService.a;
        Intent intent = new Intent(e.a, ForegroundService.class);
        intent.setAction("app.notifee.core.ForegroundService.STOP");
        try {
            e.a.startService(intent);
        } catch (IllegalStateException unused) {
            e.a.stopService(intent);
        } catch (Exception e) {
            Logger.e("ForegroundService", "Unable to stop foreground service", e);
        }
        methodCallResult.onComplete((Exception) null, null);
    }
}
