package app.notifee.core;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.service.notification.StatusBarNotification;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.text.HtmlCompat;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import app.notifee.core.database.a;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.interfaces.MethodCallResult;
import app.notifee.core.model.NotificationAndroidModel;
import app.notifee.core.model.NotificationAndroidPressActionModel;
import app.notifee.core.model.NotificationAndroidStyleModel;
import app.notifee.core.model.NotificationModel;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import n.o.t.i.f.e.e.h;
import n.o.t.i.f.e.e.i;
import n.o.t.i.f.e.e.j;
import n.o.t.i.f.e.e.l;
import n.o.t.i.f.e.e.n;
import n.o.t.i.f.e.e.q;

public abstract class c {
    public static final ExecutorService a = Executors.newCachedThreadPool();

    public static Object a(Task task) {
        a.a(e.a).a();
        return null;
    }

    public static Object b(int i) {
        NotificationManagerCompat from = NotificationManagerCompat.from(e.a);
        if (i == 1 || i == 0) {
            from.cancelAll();
        }
        if (i != 2 && i != 0) {
            return null;
        }
        WorkManager instance = WorkManager.getInstance(e.a);
        instance.cancelAllWorkByTag("app.notifee.core.NotificationManager.TRIGGER");
        instance.pruneWork();
        return null;
    }

    public static Void a(int i, List list, Task task) {
        if (i == 1) {
            return null;
        }
        a.a(e.a).a(list);
        return null;
    }

    public static Object a(List list, int i, String str) {
        WorkManager instance = WorkManager.getInstance(e.a);
        NotificationManagerCompat from = NotificationManagerCompat.from(e.a);
        Iterator it = list.iterator();
        while (true) {
            Integer num = null;
            if (!it.hasNext()) {
                return null;
            }
            String str2 = (String) it.next();
            Logger.i("NotificationManager", "Removing notification with id " + str2);
            if (i != 2) {
                if (str != null && str2.equals("0")) {
                    try {
                        num = Integer.valueOf(Integer.parseInt(str2));
                    } catch (Exception unused) {
                        Logger.e("NotificationManager", "cancelAllNotificationsWithIds -> Failed to parse id as integer  " + str2);
                    }
                    if (num != null) {
                        from.cancel(str, num.intValue());
                    }
                }
                from.cancel(str, str2.hashCode());
            }
            if (i != 1) {
                Logger.i("NotificationManager", "Removing notification with id " + str2);
                instance.cancelUniqueWork("trigger:" + str2);
                instance.pruneWork();
                PendingIntent a2 = b.a(str2);
                AlarmManager a3 = n.o.t.i.f.e.e.a.a();
                if (a2 != null) {
                    a3.cancel(a2);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00dc A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.core.app.NotificationCompat.Builder b(app.notifee.core.model.NotificationAndroidModel r9, app.notifee.core.model.NotificationModel r10, com.google.android.gms.tasks.Task r11) {
        /*
            java.lang.String r0 = "NotificationManager"
            java.lang.Object r11 = r11.getResult()
            androidx.core.app.NotificationCompat$Builder r11 = (androidx.core.app.NotificationCompat.Builder) r11
            java.util.ArrayList r9 = r9.getActions()
            if (r9 != 0) goto L_0x000f
            return r11
        L_0x000f:
            java.util.Iterator r9 = r9.iterator()
        L_0x0013:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L_0x00e5
            java.lang.Object r1 = r9.next()
            app.notifee.core.model.NotificationAndroidActionModel r1 = (app.notifee.core.model.NotificationAndroidActionModel) r1
            android.content.Context r2 = n.o.t.i.f.e.e.e.a
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo()
            int r2 = r2.targetSdkVersion
            java.lang.String r3 = "pressAction"
            java.lang.String r4 = "notification"
            r5 = 31
            if (r2 < r5) goto L_0x005d
            int r2 = android.os.Build.VERSION.SDK_INT
            if (r2 < r5) goto L_0x005d
            java.lang.Integer r2 = r10.b()
            int r2 = r2.intValue()
            app.notifee.core.model.NotificationAndroidPressActionModel r5 = r1.getPressAction()
            android.os.Bundle r5 = r5.toBundle()
            java.lang.String[] r3 = new java.lang.String[]{r4, r3}
            android.os.Bundle r4 = r10.toBundle()
            app.notifee.core.model.NotificationAndroidPressActionModel r6 = r1.getPressAction()
            android.os.Bundle r6 = r6.toBundle()
            android.os.Bundle[] r4 = new android.os.Bundle[]{r4, r6}
            r6 = 2
            android.app.PendingIntent r2 = n.o.t.i.f.e.e.j.a((int) r2, (android.os.Bundle) r5, (int) r6, (java.lang.String[]) r3, (android.os.Bundle[]) r4)
            goto L_0x0077
        L_0x005d:
            java.lang.String[] r2 = new java.lang.String[]{r4, r3}
            android.os.Bundle r3 = r10.toBundle()
            app.notifee.core.model.NotificationAndroidPressActionModel r4 = r1.getPressAction()
            android.os.Bundle r4 = r4.toBundle()
            android.os.Bundle[] r3 = new android.os.Bundle[]{r3, r4}
            java.lang.String r4 = "app.notifee.core.ReceiverService.ACTION_PRESS_INTENT"
            android.app.PendingIntent r2 = app.notifee.core.ReceiverService.a(r4, r2, r3)
        L_0x0077:
            java.lang.String r3 = r1.getIcon()
            r4 = 0
            if (r3 == 0) goto L_0x00be
            java.lang.String r5 = r1.getIcon()     // Catch:{ TimeoutException -> 0x0093, Exception -> 0x0091 }
            com.google.android.gms.tasks.Task r5 = n.o.t.i.f.e.e.n.a(r5)     // Catch:{ TimeoutException -> 0x0093, Exception -> 0x0091 }
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ TimeoutException -> 0x0093, Exception -> 0x0091 }
            r7 = 10
            java.lang.Object r5 = com.google.android.gms.tasks.Tasks.await(r5, r7, r6)     // Catch:{ TimeoutException -> 0x0093, Exception -> 0x0091 }
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5     // Catch:{ TimeoutException -> 0x0093, Exception -> 0x0091 }
            goto L_0x00bf
        L_0x0091:
            r5 = move-exception
            goto L_0x0095
        L_0x0093:
            r5 = move-exception
            goto L_0x00aa
        L_0x0095:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "An error occurred whilst trying to retrieve an action icon: "
            r6.append(r7)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            app.notifee.core.Logger.e((java.lang.String) r0, (java.lang.String) r3, (java.lang.Exception) r5)
            goto L_0x00be
        L_0x00aa:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Timeout occurred whilst trying to retrieve an action icon: "
            r6.append(r7)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            app.notifee.core.Logger.e((java.lang.String) r0, (java.lang.String) r3, (java.lang.Exception) r5)
        L_0x00be:
            r5 = r4
        L_0x00bf:
            if (r5 == 0) goto L_0x00c5
            androidx.core.graphics.drawable.IconCompat r4 = androidx.core.graphics.drawable.IconCompat.createWithAdaptiveBitmap(r5)
        L_0x00c5:
            androidx.core.app.NotificationCompat$Action$Builder r3 = new androidx.core.app.NotificationCompat$Action$Builder
            java.lang.String r5 = r1.getTitle()
            r6 = 0
            android.text.Spanned r5 = androidx.core.text.HtmlCompat.fromHtml(r5, r6)
            r3.<init>(r4, r5, r2)
            androidx.core.app.RemoteInput r1 = r1.getRemoteInput(r3)
            if (r1 == 0) goto L_0x00dc
            r3.addRemoteInput(r1)
        L_0x00dc:
            androidx.core.app.NotificationCompat$Action r1 = r3.build()
            r11.addAction(r1)
            goto L_0x0013
        L_0x00e5:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.c.b(app.notifee.core.model.NotificationAndroidModel, app.notifee.core.model.NotificationModel, com.google.android.gms.tasks.Task):androidx.core.app.NotificationCompat$Builder");
    }

    public static Task a(NotificationModel notificationModel) {
        NotificationAndroidModel a2 = notificationModel.a();
        c$$ExternalSyntheticLambda10 c__externalsyntheticlambda10 = new c$$ExternalSyntheticLambda10(a2, notificationModel);
        c$$ExternalSyntheticLambda11 c__externalsyntheticlambda11 = new c$$ExternalSyntheticLambda11(a2);
        c$$ExternalSyntheticLambda12 c__externalsyntheticlambda12 = new c$$ExternalSyntheticLambda12(a2, notificationModel);
        c$$ExternalSyntheticLambda13 c__externalsyntheticlambda13 = new c$$ExternalSyntheticLambda13(a2, notificationModel);
        c$$ExternalSyntheticLambda14 c__externalsyntheticlambda14 = new c$$ExternalSyntheticLambda14(a2);
        ExecutorService executorService = a;
        return Tasks.call(executorService, c__externalsyntheticlambda10).continueWith(executorService, c__externalsyntheticlambda11).continueWith(executorService, c__externalsyntheticlambda13).continueWith(executorService, c__externalsyntheticlambda14).continueWith(executorService, c__externalsyntheticlambda12);
    }

    public static /* synthetic */ NotificationCompat.Builder b(NotificationAndroidModel notificationAndroidModel, Task task) {
        Task styleTask;
        NotificationCompat.Style style;
        NotificationCompat.Builder builder = (NotificationCompat.Builder) task.getResult();
        NotificationAndroidStyleModel style2 = notificationAndroidModel.getStyle();
        if (!(style2 == null || (styleTask = style2.getStyleTask(a)) == null || (style = (NotificationCompat.Style) Tasks.await(styleTask)) == null)) {
            builder.setStyle(style);
        }
        return builder;
    }

    public static Task b(NotificationModel notificationModel, Bundle bundle) {
        return a(notificationModel).continueWith(a, new c$$ExternalSyntheticLambda6(notificationModel, bundle));
    }

    public static List b() {
        ArrayList arrayList = new ArrayList();
        for (StatusBarNotification statusBarNotification : ((NotificationManager) e.a.getSystemService("notification")).getActiveNotifications()) {
            Notification notification = statusBarNotification.getNotification();
            Bundle bundle = notification.extras;
            Bundle bundle2 = new Bundle();
            Bundle bundle3 = bundle.getBundle("notifee.notification");
            Bundle bundle4 = bundle.getBundle("notifee.trigger");
            if (bundle3 == null) {
                bundle3 = new Bundle();
                bundle3.putString("id", "" + statusBarNotification.getId());
                Object obj = bundle.get("android.title");
                if (obj != null) {
                    bundle3.putString("title", obj.toString());
                }
                Object obj2 = bundle.get("android.text");
                if (obj2 != null) {
                    bundle3.putString("body", obj2.toString());
                }
                Object obj3 = bundle.get("android.subText");
                if (obj3 != null) {
                    bundle3.putString(NotificationMessage.NOTIF_KEY_SUB_TITLE, obj3.toString());
                }
                Bundle bundle5 = new Bundle();
                bundle5.putString("channelId", notification.getChannelId());
                bundle5.putString("tag", statusBarNotification.getTag());
                bundle5.putString("group", notification.getGroup());
                bundle3.putBundle("android", bundle5);
                bundle2.putString("id", "" + statusBarNotification.getId());
            } else {
                bundle2.putString("id", "" + bundle3.get("id"));
            }
            if (bundle4 != null) {
                bundle2.putBundle("trigger", bundle4);
            }
            bundle2.putBundle("notification", bundle3);
            bundle2.putString("date", "" + statusBarNotification.getPostTime());
            arrayList.add(bundle2);
        }
        return arrayList;
    }

    public static void b(MethodCallResult methodCallResult) {
        new a(e.a).b().addOnCompleteListener(new c$$ExternalSyntheticLambda0(methodCallResult));
    }

    public static void b(MethodCallResult methodCallResult, Task task) {
        ArrayList arrayList = new ArrayList();
        if (task.isSuccessful()) {
            for (q qVar : (List) task.getResult()) {
                Bundle bundle = new Bundle();
                bundle.putBundle("notification", l.a(qVar.b));
                bundle.putBundle("trigger", l.a(qVar.c));
                arrayList.add(bundle);
            }
            methodCallResult.onComplete((Exception) null, arrayList);
            return;
        }
        methodCallResult.onComplete(task.getException(), arrayList);
    }

    public static NotificationCompat.Builder a(NotificationAndroidModel notificationAndroidModel, NotificationModel notificationModel) {
        Bundle bundle;
        Boolean bool = Boolean.FALSE;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(e.a, notificationAndroidModel.getChannelId());
        Bundle bundle2 = notificationModel.a.getBundle(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (bundle2 != null) {
            bundle = (Bundle) bundle2.clone();
        } else {
            bundle = new Bundle();
        }
        builder.setExtras(bundle);
        builder.setDeleteIntent(ReceiverService.a("app.notifee.core.ReceiverService.DELETE_INTENT", new String[]{"notification"}, notificationModel.toBundle()));
        if (e.a.getApplicationInfo().targetSdkVersion < 31 || Build.VERSION.SDK_INT < 31) {
            builder.setContentIntent(ReceiverService.a("app.notifee.core.ReceiverService.PRESS_INTENT", new String[]{"notification", "pressAction"}, notificationModel.toBundle(), notificationAndroidModel.getPressAction()));
        } else {
            builder.setContentIntent(j.a(notificationModel.b().intValue(), notificationAndroidModel.getPressAction(), 1, new String[]{"notification", "pressAction"}, notificationModel.toBundle(), notificationAndroidModel.getPressAction()));
        }
        if (notificationModel.a.getString("title") != null) {
            builder.setContentTitle(HtmlCompat.fromHtml(notificationModel.a.getString("title"), 0));
        }
        if (notificationModel.a.getString(NotificationMessage.NOTIF_KEY_SUB_TITLE) != null) {
            builder.setSubText(HtmlCompat.fromHtml(notificationModel.a.getString(NotificationMessage.NOTIF_KEY_SUB_TITLE), 0));
        }
        if (notificationModel.a.getString("body") != null) {
            builder.setContentText(HtmlCompat.fromHtml(notificationModel.a.getString("body"), 0));
        }
        if (notificationAndroidModel.getBadgeIconType() != null) {
            builder.setBadgeIconType(notificationAndroidModel.getBadgeIconType().intValue());
        }
        if (notificationAndroidModel.getCategory() != null) {
            builder.setCategory(notificationAndroidModel.getCategory());
        }
        if (notificationAndroidModel.getColor() != null) {
            builder.setColor(notificationAndroidModel.getColor().intValue());
        }
        builder.setColorized(notificationAndroidModel.getColorized().booleanValue());
        builder.setChronometerCountDown(notificationAndroidModel.getChronometerCountDown().booleanValue());
        if (notificationAndroidModel.getGroup() != null) {
            builder.setGroup(notificationAndroidModel.getGroup());
        }
        builder.setGroupAlertBehavior(notificationAndroidModel.getGroupAlertBehaviour());
        builder.setGroupSummary(notificationAndroidModel.getGroupSummary().booleanValue());
        if (notificationAndroidModel.getInputHistory() != null) {
            builder.setRemoteInputHistory(notificationAndroidModel.getInputHistory());
        }
        if (notificationAndroidModel.getLights() != null) {
            ArrayList<Integer> lights = notificationAndroidModel.getLights();
            builder.setLights(lights.get(0).intValue(), lights.get(1).intValue(), lights.get(2).intValue());
        }
        builder.setLocalOnly(notificationAndroidModel.getLocalOnly().booleanValue());
        if (notificationAndroidModel.getNumber() != null) {
            builder.setNumber(notificationAndroidModel.getNumber().intValue());
        }
        if (notificationAndroidModel.getSound() != null) {
            Uri c = n.c(notificationAndroidModel.getSound());
            if (c != null) {
                bool = Boolean.TRUE;
                builder.setSound(c);
            } else {
                Logger.w("NotificationManager", "Unable to retrieve sound for notification, sound was specified as: " + notificationAndroidModel.getSound());
            }
        }
        builder.setDefaults(notificationAndroidModel.getDefaults(bool).intValue());
        builder.setOngoing(notificationAndroidModel.getOngoing().booleanValue());
        builder.setOnlyAlertOnce(notificationAndroidModel.getOnlyAlertOnce().booleanValue());
        builder.setPriority(notificationAndroidModel.getPriority());
        NotificationAndroidModel.a progress = notificationAndroidModel.getProgress();
        if (progress != null) {
            builder.setProgress(progress.a, progress.b, progress.c);
        }
        if (notificationAndroidModel.getShortcutId() != null) {
            builder.setShortcutId(notificationAndroidModel.getShortcutId());
        }
        builder.setShowWhen(notificationAndroidModel.getShowTimestamp().booleanValue());
        Integer smallIcon = notificationAndroidModel.getSmallIcon();
        if (smallIcon != null) {
            Integer smallIconLevel = notificationAndroidModel.getSmallIconLevel();
            if (smallIconLevel != null) {
                builder.setSmallIcon(smallIcon.intValue(), smallIconLevel.intValue());
            } else {
                builder.setSmallIcon(smallIcon.intValue());
            }
        }
        if (notificationAndroidModel.getSortKey() != null) {
            builder.setSortKey(notificationAndroidModel.getSortKey());
        }
        if (notificationAndroidModel.getTicker() != null) {
            builder.setTicker(notificationAndroidModel.getTicker());
        }
        if (notificationAndroidModel.getTimeoutAfter() != null) {
            builder.setTimeoutAfter(notificationAndroidModel.getTimeoutAfter().longValue());
        }
        builder.setUsesChronometer(notificationAndroidModel.getShowChronometer().booleanValue());
        long[] vibrationPattern = notificationAndroidModel.getVibrationPattern();
        if (vibrationPattern.length > 0) {
            builder.setVibrate(vibrationPattern);
        }
        builder.setVisibility(notificationAndroidModel.getVisibility());
        long timestamp = notificationAndroidModel.getTimestamp();
        if (timestamp > -1) {
            builder.setWhen(timestamp);
        }
        builder.setAutoCancel(notificationAndroidModel.getAutoCancel().booleanValue());
        return builder;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.core.app.NotificationCompat.Builder a(app.notifee.core.model.NotificationAndroidModel r9, com.google.android.gms.tasks.Task r10) {
        /*
            java.lang.String r0 = "NotificationManager"
            java.lang.Object r10 = r10.getResult()
            androidx.core.app.NotificationCompat$Builder r10 = (androidx.core.app.NotificationCompat.Builder) r10
            java.lang.Boolean r1 = r9.hasLargeIcon()
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00c8
            java.lang.String r1 = r9.getLargeIcon()
            com.google.android.gms.tasks.Task r2 = n.o.t.i.f.e.e.n.a(r1)     // Catch:{ TimeoutException -> 0x0027, Exception -> 0x0025 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ TimeoutException -> 0x0027, Exception -> 0x0025 }
            r4 = 10
            java.lang.Object r2 = com.google.android.gms.tasks.Tasks.await(r2, r4, r3)     // Catch:{ TimeoutException -> 0x0027, Exception -> 0x0025 }
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2     // Catch:{ TimeoutException -> 0x0027, Exception -> 0x0025 }
            goto L_0x0053
        L_0x0025:
            r2 = move-exception
            goto L_0x0029
        L_0x0027:
            r2 = move-exception
            goto L_0x003e
        L_0x0029:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "An error occurred whilst trying to retrieve a largeIcon image: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            app.notifee.core.Logger.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Exception) r2)
            goto L_0x0052
        L_0x003e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Timeout occurred whilst trying to retrieve a largeIcon image: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            app.notifee.core.Logger.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Exception) r2)
        L_0x0052:
            r2 = 0
        L_0x0053:
            if (r2 == 0) goto L_0x00c8
            java.lang.Boolean r9 = r9.getCircularLargeIcon()
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00c5
            int r9 = r2.getWidth()
            int r0 = r2.getHeight()
            r1 = 0
            if (r9 <= r0) goto L_0x0083
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r0, r0, r3)
            int r9 = r9 - r0
            int r9 = r9 / 2
            int r4 = r9 + r0
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>(r9, r1, r4, r0)
            android.graphics.Rect r9 = new android.graphics.Rect
            r9.<init>(r1, r1, r0, r0)
            int r0 = r0 / 2
            float r0 = (float) r0
            goto L_0x009e
        L_0x0083:
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r9, r9, r3)
            int r0 = r0 - r9
            int r0 = r0 / 2
            int r4 = r0 + r9
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>(r1, r0, r9, r4)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>(r1, r1, r9, r9)
            int r9 = r9 / 2
            float r9 = (float) r9
            r8 = r0
            r0 = r9
            r9 = r8
        L_0x009e:
            android.graphics.Canvas r4 = new android.graphics.Canvas
            r4.<init>(r3)
            android.graphics.Paint r6 = new android.graphics.Paint
            r6.<init>()
            r7 = 1
            r6.setAntiAlias(r7)
            r4.drawARGB(r1, r1, r1, r1)
            r1 = -65536(0xffffffffffff0000, float:NaN)
            r6.setColor(r1)
            r4.drawCircle(r0, r0, r0, r6)
            android.graphics.PorterDuffXfermode r0 = new android.graphics.PorterDuffXfermode
            android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.SRC_IN
            r0.<init>(r1)
            r6.setXfermode(r0)
            r4.drawBitmap(r2, r5, r9, r6)
            r2 = r3
        L_0x00c5:
            r10.setLargeIcon(r2)
        L_0x00c8:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.c.a(app.notifee.core.model.NotificationAndroidModel, com.google.android.gms.tasks.Task):androidx.core.app.NotificationCompat$Builder");
    }

    public static NotificationCompat.Builder a(NotificationAndroidModel notificationAndroidModel, NotificationModel notificationModel, Task task) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) task.getResult();
        if (notificationAndroidModel.hasFullScreenAction().booleanValue()) {
            NotificationAndroidPressActionModel fullScreenAction = notificationAndroidModel.getFullScreenAction();
            String launchActivity = fullScreenAction.getLaunchActivity();
            Class a2 = h.a(launchActivity);
            if (a2 == null) {
                Logger.e("NotificationManager", String.format("Launch Activity for full-screen action does not exist ('%s').", new Object[]{launchActivity}));
                return builder;
            }
            Intent intent = new Intent(e.a, a2);
            if (fullScreenAction.getLaunchActivityFlags() != -1) {
                intent.addFlags(fullScreenAction.getLaunchActivityFlags());
            }
            if (fullScreenAction.getMainComponent() != null) {
                intent.putExtra("mainComponent", fullScreenAction.getMainComponent());
                intent.putExtra("notification", notificationModel.toBundle());
                f.b(new i(fullScreenAction.getMainComponent()));
            }
            builder.setFullScreenIntent(PendingIntent.getActivity(e.a, notificationModel.b().intValue(), intent, 167772160), true);
        }
        return builder;
    }

    public static Task a(int i) {
        return Tasks.call(new c$$ExternalSyntheticLambda7(i)).continueWith(a, new c$$ExternalSyntheticLambda8(i));
    }

    public static /* synthetic */ Void a(int i, Task task) {
        if (i != 2 && i != 0) {
            return null;
        }
        task.continueWith(b.a()).addOnSuccessListener(new c$$ExternalSyntheticLambda9());
        return null;
    }

    public static Task a(int i, List list, String str) {
        return Tasks.call(new c$$ExternalSyntheticLambda2(list, i, str)).continueWith(new c$$ExternalSyntheticLambda3(i, list));
    }

    public static Void a(NotificationModel notificationModel, Bundle bundle, Task task) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) task.getResult();
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("notifee.notification", notificationModel.toBundle());
        if (bundle != null) {
            bundle2.putBundle("notifee.trigger", bundle);
        }
        builder.addExtras(bundle2);
        Notification build = builder.build();
        int intValue = notificationModel.b().intValue();
        NotificationAndroidModel a2 = notificationModel.a();
        if (a2.getLoopSound().booleanValue()) {
            build.flags |= 4;
        }
        if (a2.getFlags() != null && a2.getFlags().length > 0) {
            for (int i : a2.getFlags()) {
                build.flags = i | build.flags;
            }
        }
        if (a2.getLightUpScreen().booleanValue()) {
            PowerManager powerManager = (PowerManager) e.a.getSystemService("power");
            if (!powerManager.isInteractive()) {
                powerManager.newWakeLock(805306394, "Notifee:lock").acquire();
                powerManager.newWakeLock(1, "Notifee:cpuLock").acquire();
            }
        }
        if (a2.getAsForegroundService().booleanValue()) {
            Bundle bundle3 = notificationModel.toBundle();
            String str = ForegroundService.a;
            Intent intent = new Intent(e.a, ForegroundService.class);
            intent.setAction("app.notifee.core.ForegroundService.START");
            intent.putExtra("hashCode", intValue);
            intent.putExtra("notification", build);
            intent.putExtra("notificationBundle", bundle3);
            e.a.startForegroundService(intent);
        } else {
            NotificationManagerCompat.from(e.a).notify(a2.getTag(), intValue, build);
        }
        f.a(new NotificationEvent(3, notificationModel));
        return null;
    }

    public static Task a(NotificationModel notificationModel, Bundle bundle) {
        return Tasks.call(a, new c$$ExternalSyntheticLambda5(bundle, notificationModel));
    }

    public static Void a(Bundle bundle, NotificationModel notificationModel) {
        TimeUnit timeUnit;
        TimeUnit timeUnit2;
        Bundle bundle2 = bundle;
        NotificationModel notificationModel2 = notificationModel;
        int a2 = l.a(bundle2.get("type"));
        Class<Worker> cls = Worker.class;
        if (a2 == 0) {
            app.notifee.core.model.a aVar = new app.notifee.core.model.a(bundle2);
            String str = "trigger:" + notificationModel.c();
            long j = 0;
            if (aVar.a.containsKey("timestamp")) {
                long b = l.b(aVar.a.get("timestamp"));
                if (b > 0) {
                    j = (long) Math.round((float) ((b - System.currentTimeMillis()) / 1000));
                }
            }
            int i = aVar.b;
            Data.Builder putString = new Data.Builder().putString("workType", "app.notifee.core.NotificationManager.TRIGGER").putString("id", notificationModel.c());
            Boolean bool = aVar.d;
            a.a(e.a);
            a.b.a(new q(notificationModel.c(), l.a(notificationModel.toBundle()), l.a(bundle), bool));
            if (bool.booleanValue()) {
                b.a(notificationModel2, aVar);
            } else {
                WorkManager instance = WorkManager.getInstance(e.a);
                if (i == -1) {
                    OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(cls);
                    builder.addTag("app.notifee.core.NotificationManager.TRIGGER");
                    builder.addTag(str);
                    putString.putString("workRequestType", "OneTime");
                    builder.setInputData(putString.build());
                    builder.setInitialDelay(j, TimeUnit.SECONDS);
                    instance.enqueueUniqueWork(str, ExistingWorkPolicy.REPLACE, (OneTimeWorkRequest) builder.build());
                } else {
                    PeriodicWorkRequest.Builder builder2 = new PeriodicWorkRequest.Builder(cls, (long) aVar.b, aVar.c);
                    builder2.addTag("app.notifee.core.NotificationManager.TRIGGER");
                    builder2.addTag(str);
                    builder2.setInitialDelay(j, TimeUnit.SECONDS);
                    putString.putString("workRequestType", "Periodic");
                    builder2.setInputData(putString.build());
                    instance.enqueueUniquePeriodicWork(str, ExistingPeriodicWorkPolicy.UPDATE, (PeriodicWorkRequest) builder2.build());
                }
            }
        } else if (a2 == 1) {
            String str2 = "trigger:" + notificationModel.c();
            WorkManager instance2 = WorkManager.getInstance(e.a);
            Data.Builder putString2 = new Data.Builder().putString("workType", "app.notifee.core.NotificationManager.TRIGGER").putString("workRequestType", "Periodic").putString("id", notificationModel.c());
            a.a(e.a);
            a.b.a(new q(notificationModel.c(), l.a(notificationModel.toBundle()), l.a(bundle), Boolean.FALSE));
            long a3 = (long) (bundle2.containsKey("interval") ? l.a(bundle2.get("interval")) : -1);
            TimeUnit timeUnit3 = TimeUnit.SECONDS;
            if (bundle2.containsKey("timeUnit")) {
                String string = bundle2.getString("timeUnit");
                try {
                    timeUnit = TimeUnit.valueOf(string);
                } catch (IllegalArgumentException e) {
                    timeUnit2 = timeUnit3;
                    Logger.e("IntervalTriggerModel", "An error occurred whilst trying to convert interval time unit: " + string, (Exception) e);
                }
            } else {
                timeUnit2 = timeUnit3;
                timeUnit = timeUnit2;
            }
            PeriodicWorkRequest.Builder builder3 = new PeriodicWorkRequest.Builder(cls, a3, timeUnit);
            TimeUnit timeUnit4 = TimeUnit.SECONDS;
            if (bundle2.containsKey("timeUnit")) {
                String string2 = bundle2.getString("timeUnit");
                try {
                    timeUnit4 = TimeUnit.valueOf(string2);
                } catch (IllegalArgumentException e2) {
                    Logger.e("IntervalTriggerModel", "An error occurred whilst trying to convert interval time unit: " + string2, (Exception) e2);
                }
            }
            PeriodicWorkRequest.Builder builder4 = (PeriodicWorkRequest.Builder) builder3.setInitialDelay(a3, timeUnit4);
            builder4.addTag("app.notifee.core.NotificationManager.TRIGGER");
            builder4.addTag(str2);
            builder4.setInputData(putString2.build());
            instance2.enqueueUniquePeriodicWork(str2, ExistingPeriodicWorkPolicy.UPDATE, (PeriodicWorkRequest) builder4.build());
        }
        f.a(new NotificationEvent(7, notificationModel2));
        return null;
    }

    public static Task a() {
        return Tasks.call(new c$$ExternalSyntheticLambda4());
    }

    public static void a(MethodCallResult methodCallResult) {
        new a(e.a).b().addOnCompleteListener(new c$$ExternalSyntheticLambda1(methodCallResult));
    }

    public static void a(MethodCallResult methodCallResult, Task task) {
        ArrayList arrayList = new ArrayList();
        if (task.isSuccessful()) {
            for (q qVar : (List) task.getResult()) {
                arrayList.add(qVar.a);
            }
            methodCallResult.onComplete((Exception) null, arrayList);
            return;
        }
        methodCallResult.onComplete(task.getException(), null);
    }

    public static void a(Data data, CallbackToFutureAdapter.Completer completer) {
        String string = data.getString("id");
        a aVar = new a(e.a);
        aVar.b(string).continueWithTask(a, new c$$ExternalSyntheticLambda16(data, completer)).addOnCompleteListener(new c$$ExternalSyntheticLambda17(completer, data, string));
    }

    public static Task a(Data data, CallbackToFutureAdapter.Completer completer, Task task) {
        byte[] bArr;
        q qVar = (q) task.getResult();
        Bundle bundle = null;
        if (qVar == null || (bArr = qVar.b) == null) {
            bArr = data.getByteArray("notification");
            if (bArr != null) {
                Logger.w("NotificationManager", "The trigger notification was created using an older version, please consider recreating the notification.");
            } else {
                Logger.w("NotificationManager", "Attempted to handle doScheduledWork but no notification data was found.");
                completer.set(ListenableWorker.Result.success());
                return null;
            }
        }
        NotificationModel notificationModel = new NotificationModel(l.a(bArr));
        byte[] bArr2 = qVar.c;
        if (bArr2 != null) {
            bundle = l.a(bArr2);
        }
        return b(notificationModel, bundle);
    }

    public static void a(CallbackToFutureAdapter.Completer completer, Data data, String str, Task task) {
        completer.set(ListenableWorker.Result.success());
        if (!task.isSuccessful()) {
            Logger.e("NotificationManager", "Failed to display notification", task.getException());
            return;
        }
        String string = data.getString("workRequestType");
        if (string != null && string.equals("OneTime")) {
            a.a(e.a).a(str);
        }
    }
}
