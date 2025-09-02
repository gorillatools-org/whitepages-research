package com.salesforce.marketingcloud.notifications;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.storage.j;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class a extends NotificationManager implements e {

    /* renamed from: n  reason: collision with root package name */
    public static final String f37n = "com.salesforce.marketingcloud.notifications.OPENED";
    public static final String o = "com.salesforce.marketingcloud.notifications.open.RECEIVED";
    public static final String p = "com.salesforce.marketingcloud.notifications.MESSAGE";
    static final String q = "com.salesforce.marketingcloud.notifications.EXTRA_OPEN_INTENT";
    static final String r = "com.salesforce.marketingcloud.notifications.EXTRA_AUTO_CANCEL";
    public static final int s = -1;
    static final String t = "com.marketingcloud.salesforce.notifications.TAG";
    static final String u = "com.marketingcloud.salesforce.notifications.ENABLED";
    static final String v = "notification_id_key";
    final b f;
    final Context g;
    private final j h;
    private final Set<NotificationManager.NotificationMessageDisplayedListener> i;
    private final com.salesforce.marketingcloud.analytics.j j;
    private NotificationManager.ShouldShowNotificationListener k;
    private BroadcastReceiver l;
    private boolean m = true;

    /* renamed from: com.salesforce.marketingcloud.notifications.a$a  reason: collision with other inner class name */
    class C0029a extends Thread {
        final /* synthetic */ NotificationMessage a;
        final /* synthetic */ b b;

        C0029a(NotificationMessage notificationMessage, b bVar) {
            this.a = notificationMessage;
            this.b = bVar;
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        @android.annotation.SuppressLint({"NewApi"})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r4 = this;
                com.salesforce.marketingcloud.notifications.a r0 = com.salesforce.marketingcloud.notifications.a.this
                com.salesforce.marketingcloud.notifications.b r1 = r0.f
                android.content.Context r0 = r0.g
                com.salesforce.marketingcloud.notifications.NotificationMessage r2 = r4.a
                androidx.core.app.NotificationCompat$Builder r0 = r1.setupNotificationBuilder(r0, r2)
                com.salesforce.marketingcloud.notifications.a r1 = com.salesforce.marketingcloud.notifications.a.this     // Catch:{ Exception -> 0x0037 }
                android.content.Context r1 = r1.g     // Catch:{ Exception -> 0x0037 }
                java.lang.String r2 = "notification"
                java.lang.Object r1 = r1.getSystemService(r2)     // Catch:{ Exception -> 0x0037 }
                android.app.NotificationManager r1 = (android.app.NotificationManager) r1     // Catch:{ Exception -> 0x0037 }
                if (r1 == 0) goto L_0x0042
                java.lang.String r2 = "com.marketingcloud.salesforce.notifications.TAG"
                com.salesforce.marketingcloud.notifications.NotificationMessage r3 = r4.a     // Catch:{ Exception -> 0x0037 }
                int r3 = r3.notificationId()     // Catch:{ Exception -> 0x0037 }
                android.app.Notification r0 = r0.build()     // Catch:{ Exception -> 0x0037 }
                r1.notify(r2, r3, r0)     // Catch:{ Exception -> 0x0037 }
                com.salesforce.marketingcloud.notifications.a r0 = com.salesforce.marketingcloud.notifications.a.this     // Catch:{ Exception -> 0x0037 }
                com.salesforce.marketingcloud.notifications.NotificationMessage r1 = r4.a     // Catch:{ Exception -> 0x0037 }
                r0.a((com.salesforce.marketingcloud.notifications.NotificationMessage) r1)     // Catch:{ Exception -> 0x0037 }
                com.salesforce.marketingcloud.notifications.NotificationMessage r0 = r4.a     // Catch:{ Exception -> 0x0037 }
                int r0 = r0.notificationId()     // Catch:{ Exception -> 0x0037 }
                goto L_0x0043
            L_0x0037:
                r0 = move-exception
                java.lang.String r1 = com.salesforce.marketingcloud.notifications.NotificationManager.d
                r2 = 0
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.String r3 = "Unable to show notification due to an exception thrown by Android."
                com.salesforce.marketingcloud.g.b(r1, r0, r3, r2)
            L_0x0042:
                r0 = -1
            L_0x0043:
                com.salesforce.marketingcloud.notifications.a$b r1 = r4.b
                if (r1 == 0) goto L_0x004a
                r1.a(r0)
            L_0x004a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.a.C0029a.run():void");
        }
    }

    public interface b {
        void a(int i);
    }

    class c extends BroadcastReceiver {
        c() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.a(NotificationManager.d, "Received null intent", new Object[0]);
                return;
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(a.o));
            String action = intent.getAction();
            if (action == null) {
                g.a(NotificationManager.d, "Received null action", new Object[0]);
            } else if (a.f37n.equals(action)) {
                a.this.a(context, NotificationManager.extractMessage(intent), (PendingIntent) intent.getParcelableExtra(a.q), intent.getBooleanExtra(a.r, true));
            } else {
                g.a(NotificationManager.d, "Received unknown action: %s", action);
            }
        }
    }

    a(Context context, j jVar, b bVar, com.salesforce.marketingcloud.analytics.j jVar2) {
        this.g = context;
        this.h = jVar;
        this.f = bVar;
        this.j = (com.salesforce.marketingcloud.analytics.j) com.salesforce.marketingcloud.util.j.a(jVar2, "MessageAnalyticEventListener is null.");
        this.i = new ArraySet();
    }

    private void a(Context context) {
        if (this.h != null) {
            NotificationManagerCompat from = NotificationManagerCompat.from(context);
            int i2 = this.h.f().getInt(v, -1);
            int i3 = 0;
            while (i2 >= 0 && i3 < 100) {
                from.cancel(t, i2);
                i2--;
                i3++;
            }
        }
    }

    public final synchronized boolean areNotificationsEnabled() {
        return this.m;
    }

    public final String componentName() {
        return "NotificationManager";
    }

    public final JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("notificationsEnabled", areNotificationsEnabled());
            NotificationManager.ShouldShowNotificationListener shouldShowNotificationListener = this.k;
            if (shouldShowNotificationListener != null) {
                jSONObject.put("shouldShowNotificationListener", shouldShowNotificationListener.getClass().getName());
            }
        } catch (JSONException e) {
            g.b(NotificationManager.d, e, "Unable to create component state for %s", componentName());
        }
        return jSONObject;
    }

    public void controlChannelInit(int i2) {
    }

    public final synchronized void disableNotifications() {
        if (this.m) {
            this.m = false;
            a();
        }
    }

    public final synchronized void enableNotifications() {
        if (!this.m) {
            this.m = true;
            a();
        }
    }

    public final void init(InitializationStatus.a aVar, int i2) {
        this.m = this.h.f().getBoolean(u, true);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(f37n);
        this.l = new c();
        LocalBroadcastManager.getInstance(this.g).registerReceiver(this.l, intentFilter);
    }

    public final void registerNotificationMessageDisplayedListener(NotificationManager.NotificationMessageDisplayedListener notificationMessageDisplayedListener) {
        if (notificationMessageDisplayedListener != null) {
            synchronized (this.i) {
                this.i.add(notificationMessageDisplayedListener);
            }
        }
    }

    public void setShouldShowNotificationListener(NotificationManager.ShouldShowNotificationListener shouldShowNotificationListener) {
        this.k = shouldShowNotificationListener;
    }

    public final void tearDown(boolean z) {
        if (z) {
            a(this.g);
        }
        Context context = this.g;
        if (context != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this.l);
        }
    }

    public final void unregisterNotificationMessageDisplayedListener(NotificationManager.NotificationMessageDisplayedListener notificationMessageDisplayedListener) {
        synchronized (this.i) {
            this.i.remove(notificationMessageDisplayedListener);
        }
    }

    @SuppressLint({"LambdaLast"})
    public static a a(Context context, j jVar, NotificationCustomizationOptions notificationCustomizationOptions, com.salesforce.marketingcloud.analytics.j jVar2) {
        return new a(context, jVar, new b(notificationCustomizationOptions.smallIconResId, notificationCustomizationOptions.launchIntentProvider, notificationCustomizationOptions.notificationBuilder, notificationCustomizationOptions.channelIdProvider), jVar2);
    }

    /* access modifiers changed from: package-private */
    public void a(NotificationMessage notificationMessage) {
        synchronized (this.i) {
            if (!this.i.isEmpty()) {
                for (NotificationManager.NotificationMessageDisplayedListener next : this.i) {
                    if (next != null) {
                        try {
                            next.onNotificationMessageDisplayed(notificationMessage);
                        } catch (Exception e) {
                            g.b(NotificationManager.d, e, "%s threw an exception while processing notification message (%s)", next.getClass().getName(), notificationMessage.id());
                        }
                    }
                }
            }
        }
        try {
            this.j.b(notificationMessage);
        } catch (Exception e2) {
            g.b(NotificationManager.d, e2, "Failed to log analytics for message displayed.", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, NotificationMessage notificationMessage, PendingIntent pendingIntent, boolean z) {
        g.a(NotificationManager.d, "Notification open Event Logged for id : (%s)", notificationMessage.id());
        this.j.a(notificationMessage);
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                g.b(NotificationManager.d, e, "Failed to send notification's open action PendingIntent.", new Object[0]);
            }
        }
        if (z) {
            NotificationManager.cancelNotificationMessage(context, notificationMessage);
        }
        if (Build.VERSION.SDK_INT <= 30) {
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(p, notificationMessage);
        com.salesforce.marketingcloud.behaviors.c.a(context, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_NOTIFICATION_OPENED, bundle);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00da, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.salesforce.marketingcloud.notifications.NotificationMessage r9, com.salesforce.marketingcloud.notifications.a.b r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.areNotificationsEnabled()     // Catch:{ all -> 0x001d }
            r1 = -1
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = com.salesforce.marketingcloud.notifications.NotificationManager.d     // Catch:{ all -> 0x001d }
            java.lang.String r9 = r9.id()     // Catch:{ all -> 0x001d }
            java.lang.Object[] r9 = new java.lang.Object[]{r9}     // Catch:{ all -> 0x001d }
            java.lang.String r2 = "Notifications are not enabled.  Message %s will not be displayed"
            com.salesforce.marketingcloud.g.a((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object[]) r9)     // Catch:{ all -> 0x001d }
            if (r10 == 0) goto L_0x0020
            r10.a(r1)     // Catch:{ all -> 0x001d }
            goto L_0x0020
        L_0x001d:
            r9 = move-exception
            goto L_0x00db
        L_0x0020:
            monitor-exit(r8)
            return
        L_0x0022:
            java.lang.String r0 = r9.alert()     // Catch:{ all -> 0x001d }
            int r0 = android.text.TextUtils.getTrimmedLength(r0)     // Catch:{ all -> 0x001d }
            r2 = 0
            if (r0 != 0) goto L_0x003d
            java.lang.String r9 = com.salesforce.marketingcloud.notifications.NotificationManager.d     // Catch:{ all -> 0x001d }
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ all -> 0x001d }
            java.lang.String r2 = "Notifications with no alert message are not shown."
            com.salesforce.marketingcloud.g.a((java.lang.String) r9, (java.lang.String) r2, (java.lang.Object[]) r0)     // Catch:{ all -> 0x001d }
            if (r10 == 0) goto L_0x003b
            r10.a(r1)     // Catch:{ all -> 0x001d }
        L_0x003b:
            monitor-exit(r8)
            return
        L_0x003d:
            int r0 = r9.notificationId()     // Catch:{ all -> 0x001d }
            if (r0 < 0) goto L_0x004a
            if (r10 == 0) goto L_0x0048
            r10.a(r1)     // Catch:{ all -> 0x001d }
        L_0x0048:
            monitor-exit(r8)
            return
        L_0x004a:
            com.salesforce.marketingcloud.notifications.NotificationManager$ShouldShowNotificationListener r0 = r8.k     // Catch:{ all -> 0x001d }
            r3 = 1
            if (r0 == 0) goto L_0x00a7
            boolean r0 = r0.shouldShowNotification(r9)     // Catch:{ Exception -> 0x0054 }
            goto L_0x006f
        L_0x0054:
            r0 = move-exception
            java.lang.String r4 = com.salesforce.marketingcloud.notifications.NotificationManager.d     // Catch:{ all -> 0x001d }
            com.salesforce.marketingcloud.notifications.NotificationManager$ShouldShowNotificationListener r5 = r8.k     // Catch:{ all -> 0x001d }
            java.lang.Class r5 = r5.getClass()     // Catch:{ all -> 0x001d }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x001d }
            java.lang.String r6 = r9.id()     // Catch:{ all -> 0x001d }
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}     // Catch:{ all -> 0x001d }
            java.lang.String r6 = "%s threw an exception while processing shouldShowNotification() for messageId: %s"
            com.salesforce.marketingcloud.g.b(r4, r0, r6, r5)     // Catch:{ all -> 0x001d }
            r0 = r3
        L_0x006f:
            com.salesforce.marketingcloud.analytics.j r4 = r8.j     // Catch:{ Exception -> 0x0075 }
            r4.a(r9, r0)     // Catch:{ Exception -> 0x0075 }
            goto L_0x0085
        L_0x0075:
            r4 = move-exception
            java.lang.String r5 = com.salesforce.marketingcloud.notifications.NotificationManager.d     // Catch:{ all -> 0x001d }
            java.lang.String r6 = r9.id()     // Catch:{ all -> 0x001d }
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ all -> 0x001d }
            java.lang.String r7 = "Failed to log Should Show Notification analytic for messageId: %s"
            com.salesforce.marketingcloud.g.b(r5, r4, r7, r6)     // Catch:{ all -> 0x001d }
        L_0x0085:
            if (r0 == 0) goto L_0x0088
            goto L_0x00a7
        L_0x0088:
            java.lang.String r0 = com.salesforce.marketingcloud.notifications.NotificationManager.d     // Catch:{ all -> 0x001d }
            com.salesforce.marketingcloud.notifications.NotificationManager$ShouldShowNotificationListener r2 = r8.k     // Catch:{ all -> 0x001d }
            java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x001d }
            java.lang.String r2 = r2.getName()     // Catch:{ all -> 0x001d }
            java.lang.String r9 = r9.id()     // Catch:{ all -> 0x001d }
            java.lang.Object[] r9 = new java.lang.Object[]{r2, r9}     // Catch:{ all -> 0x001d }
            java.lang.String r2 = "%s responded false to shouldShowNotification() for messageId: %s"
            com.salesforce.marketingcloud.g.a((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object[]) r9)     // Catch:{ all -> 0x001d }
            if (r10 == 0) goto L_0x00d9
            r10.a(r1)     // Catch:{ all -> 0x001d }
            goto L_0x00d9
        L_0x00a7:
            com.salesforce.marketingcloud.storage.j r0 = r8.h     // Catch:{ all -> 0x001d }
            android.content.SharedPreferences r0 = r0.f()     // Catch:{ all -> 0x001d }
            java.lang.String r1 = "notification_id_key"
            int r1 = r0.getInt(r1, r2)     // Catch:{ all -> 0x001d }
            com.salesforce.marketingcloud.internal.h.a((com.salesforce.marketingcloud.notifications.NotificationMessage) r9, (int) r1)     // Catch:{ all -> 0x001d }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x001d }
            java.lang.String r1 = "notification_id_key"
            int r4 = r9.notificationId()     // Catch:{ all -> 0x001d }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 >= r5) goto L_0x00ca
            int r2 = r9.notificationId()     // Catch:{ all -> 0x001d }
            int r2 = r2 + r3
        L_0x00ca:
            android.content.SharedPreferences$Editor r0 = r0.putInt(r1, r2)     // Catch:{ all -> 0x001d }
            r0.apply()     // Catch:{ all -> 0x001d }
            com.salesforce.marketingcloud.notifications.a$a r0 = new com.salesforce.marketingcloud.notifications.a$a     // Catch:{ all -> 0x001d }
            r0.<init>(r9, r10)     // Catch:{ all -> 0x001d }
            r0.start()     // Catch:{ all -> 0x001d }
        L_0x00d9:
            monitor-exit(r8)
            return
        L_0x00db:
            monitor-exit(r8)     // Catch:{ all -> 0x001d }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.a.a(com.salesforce.marketingcloud.notifications.NotificationMessage, com.salesforce.marketingcloud.notifications.a$b):void");
    }

    private void a() {
        j jVar = this.h;
        if (jVar != null) {
            jVar.f().edit().putBoolean(u, this.m).apply();
        }
    }
}
