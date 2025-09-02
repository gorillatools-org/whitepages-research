package com.salesforce.marketingcloud.messages.push;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MCService;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.behaviors.c;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.h;
import com.salesforce.marketingcloud.k;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.notifications.a;
import com.salesforce.marketingcloud.storage.j;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class a extends PushMessageManager implements e, b.C0003b {
    static final String t = "et_push_enabled";
    private static final String u = "last_push_token_refresh";
    private static final String v = "content-available";
    private static final String w = "_c";
    private static final String x = "_p";
    private static final long y = TimeUnit.HOURS.toMillis(48);
    private final Context j;
    private final com.salesforce.marketingcloud.notifications.a k;
    private final b l;
    private final Set<PushMessageManager.SilentPushListener> m = new ArraySet();

    /* renamed from: n  reason: collision with root package name */
    private final j f36n;
    private final String o;
    private final Set<PushMessageManager.PushTokenRefreshListener> p = new ArraySet();
    private int q;
    private BroadcastReceiver r;
    private boolean s;

    /* renamed from: com.salesforce.marketingcloud.messages.push.a$a  reason: collision with other inner class name */
    class C0028a extends BroadcastReceiver {
        C0028a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.d(PushMessageManager.d, "Received null intent", new Object[0]);
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                g.d(PushMessageManager.d, "Received null action", new Object[0]);
            } else if (!action.equals(PushMessageManager.e)) {
                g.a(PushMessageManager.d, "Received unknown action: %s", action);
            } else {
                a.this.a(intent.getExtras());
            }
        }
    }

    public a(Context context, j jVar, com.salesforce.marketingcloud.notifications.a aVar, b bVar, String str) {
        this.j = (Context) com.salesforce.marketingcloud.util.j.a(context, "Content is null");
        this.f36n = (j) com.salesforce.marketingcloud.util.j.a(jVar, "Storage is null");
        this.k = (com.salesforce.marketingcloud.notifications.a) com.salesforce.marketingcloud.util.j.a(aVar, "NotificationManager is null");
        this.l = (b) com.salesforce.marketingcloud.util.j.a(bVar, "AlarmScheduler is null");
        this.o = str;
    }

    private void a() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(PushMessageManager.h, this.s);
        c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED, bundle);
    }

    private void b() {
        JSONArray optJSONArray = getPushDebugInfo().optJSONArray("messagingService");
        if (optJSONArray != null && optJSONArray.length() > 2) {
            g.e(PushMessageManager.d, "Possible Multiple Push Provider implementation issue detected in your application. This may lead to the malfunctioning of the Push SDK.", new Object[0]);
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                int optInt = optJSONObject.optInt("priority");
                String optString = optJSONObject.optString("name");
                if (optInt > -1) {
                    g.d(PushMessageManager.d, optString + " is having higher priority than the Push SDK", new Object[0]);
                }
            }
        }
    }

    private void c(Map<String, String> map) {
        map.remove(w);
        map.remove(x);
        d(map);
    }

    private void d(Map<String, String> map) {
        synchronized (this.m) {
            for (PushMessageManager.SilentPushListener next : this.m) {
                if (next != null) {
                    try {
                        next.silentPushReceived(map);
                    } catch (Exception e) {
                        g.b(PushMessageManager.d, e, "%s threw an exception while processing the silent push message", next.getClass().getName());
                    }
                }
            }
        }
    }

    private void e(Map<String, String> map) {
        if (map != null && !f(map)) {
            c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED, a(map));
            if (k.a(map)) {
                g.d(PushMessageManager.d, "Sync handler push received.", new Object[0]);
            } else if (!isPushEnabled()) {
                g.a(PushMessageManager.d, "Push Messaging is disabled.  Ignoring message.", new Object[0]);
            } else if (map.containsKey(v)) {
                b(map);
            } else if (map.containsKey(w)) {
                c(map);
            } else {
                try {
                    NotificationMessage a = h.a(map);
                    if (TextUtils.isEmpty(a.alert().trim())) {
                        g.a(PushMessageManager.d, "Message (%s) was received but does not have an alert message.", a.id());
                    } else {
                        this.k.a(a, (a.b) null);
                    }
                } catch (Exception e) {
                    g.b(PushMessageManager.d, e, "Unable to show push notification", new Object[0]);
                }
            }
        }
    }

    private boolean f(Map<String, String> map) {
        if (com.salesforce.marketingcloud.b.a(this.q, 4)) {
            g.a(PushMessageManager.d, "Blocking push message.  Received a push message when the push feature is blocked.", new Object[0]);
            return true;
        } else if (!com.salesforce.marketingcloud.b.a(this.q, 128) || !com.salesforce.marketingcloud.messages.inbox.a.a(map)) {
            return false;
        } else {
            g.a(PushMessageManager.d, "Blocking push message.  Received an inbox message when the inbox feature is blocked.", new Object[0]);
            return true;
        }
    }

    public String componentName() {
        return "PushMessageManager";
    }

    public JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pushEnabled", this.s);
            jSONObject.put("pushPermissionsAllowed", NotificationManagerCompat.from(this.j).areNotificationsEnabled());
            synchronized (this.p) {
                if (!this.p.isEmpty()) {
                    JSONArray jSONArray = new JSONArray();
                    for (PushMessageManager.PushTokenRefreshListener next : this.p) {
                        if (next != null) {
                            jSONArray.put(next.getClass().getName());
                        }
                    }
                    jSONObject.put("tokenRefreshListeners", jSONArray);
                }
            }
            jSONObject.put("debugInfo", getPushDebugInfo());
        } catch (JSONException e) {
            g.b(PushMessageManager.d, e, "Unable to create component state for $s", componentName());
        } catch (Throwable th) {
            while (true) {
            }
            throw th;
        }
        return jSONObject;
    }

    public void controlChannelInit(int i) {
        if (com.salesforce.marketingcloud.b.a(i, 4)) {
            disablePush();
            if (this.r != null) {
                LocalBroadcastManager.getInstance(this.j).unregisterReceiver(this.r);
            }
            b bVar = this.l;
            a.C0001a aVar = a.C0001a.FETCH_PUSH_TOKEN;
            bVar.e(aVar);
            this.l.d(aVar);
            if (com.salesforce.marketingcloud.b.c(i, 4)) {
                com.salesforce.marketingcloud.storage.c c = this.f36n.c();
                c.a(com.salesforce.marketingcloud.storage.c.i);
                c.a(com.salesforce.marketingcloud.storage.c.e);
            }
            this.q = i;
        } else if (com.salesforce.marketingcloud.b.a(this.q, 4)) {
            this.q = i;
            c();
            this.l.a((b.C0003b) this, a.C0001a.FETCH_PUSH_TOKEN);
            enablePush();
            String str = this.o;
            if (str != null) {
                MCService.b(this.j, str);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void disablePush() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.s     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x001c
            int r0 = r2.q     // Catch:{ all -> 0x001a }
            r1 = 4
            boolean r0 = com.salesforce.marketingcloud.b.a(r0, r1)     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000f
            goto L_0x001c
        L_0x000f:
            r0 = 0
            r2.s = r0     // Catch:{ all -> 0x001a }
            r2.a()     // Catch:{ all -> 0x001a }
            r2.d()     // Catch:{ all -> 0x001a }
            monitor-exit(r2)
            return
        L_0x001a:
            r0 = move-exception
            goto L_0x001e
        L_0x001c:
            monitor-exit(r2)
            return
        L_0x001e:
            monitor-exit(r2)     // Catch:{ all -> 0x001a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.push.a.disablePush():void");
    }

    public synchronized void enablePush() {
        if (!com.salesforce.marketingcloud.b.a(this.q, 4)) {
            this.s = true;
            a();
            d();
        }
    }

    public JSONObject getPushDebugInfo() {
        try {
            return b.a(this.j, this.o, this.f36n.c().b(com.salesforce.marketingcloud.storage.c.e, (String) null));
        } catch (Exception e) {
            g.b(PushMessageManager.d, e, "Unable to acquire push debug info.", new Object[0]);
            return new JSONObject();
        }
    }

    public String getPushToken() {
        return this.f36n.c().b(com.salesforce.marketingcloud.storage.c.e, (String) null);
    }

    public boolean handleMessage(RemoteMessage remoteMessage) {
        if (!PushMessageManager.isMarketingCloudPush(remoteMessage)) {
            g.a(PushMessageManager.d, "Message was not sent from the Marketing Cloud.  Message ignored.", new Object[0]);
            return false;
        }
        e(remoteMessage.getData());
        return true;
    }

    public void init(InitializationStatus.a aVar, int i) {
        this.q = i;
        if (com.salesforce.marketingcloud.b.b(i, 4)) {
            this.s = this.f36n.f().getBoolean(t, true);
            c();
            b bVar = this.l;
            a.C0001a aVar2 = a.C0001a.FETCH_PUSH_TOKEN;
            bVar.a((b.C0003b) this, aVar2);
            if (this.o != null) {
                b();
                if (!this.o.equals(this.f36n.c().b(com.salesforce.marketingcloud.storage.c.i, (String) null))) {
                    g.d(PushMessageManager.d, "Sender Id has changed.  Refresh system token.", new Object[0]);
                } else if (this.f36n.f().getLong(u, 0) + y < System.currentTimeMillis()) {
                    g.d(PushMessageManager.d, "Push token refresh cool down expired.  Refresh system token.", new Object[0]);
                } else {
                    return;
                }
                MCService.b(this.j, this.o);
                return;
            }
            g.e(PushMessageManager.d, "No sender id was provided during initialization.  You will not receive push messages until a token is manually set.", new Object[0]);
            this.l.d(aVar2);
            this.f36n.c().a(com.salesforce.marketingcloud.storage.c.i);
        }
    }

    public synchronized boolean isPushEnabled() {
        return this.s;
    }

    public void registerSilentPushListener(PushMessageManager.SilentPushListener silentPushListener) {
        if (silentPushListener != null) {
            synchronized (this.m) {
                this.m.add(silentPushListener);
            }
        }
    }

    public void registerTokenRefreshListener(PushMessageManager.PushTokenRefreshListener pushTokenRefreshListener) {
        if (pushTokenRefreshListener != null) {
            synchronized (this.p) {
                this.p.add(pushTokenRefreshListener);
            }
        }
    }

    public void setPushToken(String str) {
        if (!com.salesforce.marketingcloud.b.b(this.q, 4)) {
            return;
        }
        if (str == null) {
            g.b(PushMessageManager.d, "Provided pushToken was null", new Object[0]);
            return;
        }
        if (this.o != null) {
            g.a(PushMessageManager.d, "Setting the SenderId during SDK initialization and setting the push token will cause conflicts in the system and could prevent the device from receiving push messages.", new Object[0]);
        }
        com.salesforce.marketingcloud.storage.c c = this.f36n.c();
        c.a(com.salesforce.marketingcloud.storage.c.i);
        c.a(com.salesforce.marketingcloud.storage.c.e, str);
        this.l.d(a.C0001a.FETCH_PUSH_TOKEN);
        b(str);
    }

    public void tearDown(boolean z) {
        if (this.r != null) {
            LocalBroadcastManager.getInstance(this.j).unregisterReceiver(this.r);
        }
    }

    public void unregisterSilentPushListener(PushMessageManager.SilentPushListener silentPushListener) {
        synchronized (this.m) {
            this.m.remove(silentPushListener);
        }
    }

    public void unregisterTokenRefreshListener(PushMessageManager.PushTokenRefreshListener pushTokenRefreshListener) {
        synchronized (this.p) {
            this.p.remove(pushTokenRefreshListener);
        }
    }

    public static void a(Context context, boolean z, String str, String str2) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(PushMessageManager.e).putExtra(PushMessageManager.f, z).putExtra(PushMessageManager.g, str).putExtra(PushMessageManager.i, str2));
    }

    private void b(Map<String, String> map) {
        String str = map.get(v);
        if (str != null) {
            try {
                if (Integer.parseInt(str) == 1) {
                    d(map);
                }
            } catch (Exception e) {
                g.b(PushMessageManager.d, e, "Unable to parse content available flag: %s", str);
            }
        }
    }

    private void c() {
        this.r = new C0028a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PushMessageManager.e);
        LocalBroadcastManager.getInstance(this.j).registerReceiver(this.r, intentFilter);
    }

    private void d() {
        j jVar = this.f36n;
        if (jVar != null) {
            jVar.f().edit().putBoolean(t, this.s).apply();
        }
    }

    public boolean handleMessage(Map<String, String> map) {
        if (!PushMessageManager.isMarketingCloudPush(map)) {
            g.a(PushMessageManager.d, "Message was not sent from the Marketing Cloud.  Message ignored.", new Object[0]);
            return false;
        }
        e(map);
        return true;
    }

    private static Bundle a(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (!map.isEmpty()) {
            for (Map.Entry next : map.entrySet()) {
                bundle.putString((String) next.getKey(), (String) next.getValue());
            }
        }
        return bundle;
    }

    private void b(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PushMessageManager.i, str);
        c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_TOKEN_REFRESHED, bundle);
    }

    private void a(String str) {
        synchronized (this.p) {
            for (PushMessageManager.PushTokenRefreshListener next : this.p) {
                if (next != null) {
                    try {
                        next.onTokenRefreshed(str);
                    } catch (Exception e) {
                        g.b(PushMessageManager.d, e, "%s threw an exception while processing the token refresh", next.getClass().getName());
                    }
                }
            }
        }
    }

    public void a(a.C0001a aVar) {
        String str;
        if (aVar == a.C0001a.FETCH_PUSH_TOKEN && (str = this.o) != null) {
            MCService.b(this.j, str);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Bundle bundle) {
        com.salesforce.marketingcloud.storage.c c = this.f36n.c();
        if (bundle.getBoolean(PushMessageManager.f, false)) {
            String string = bundle.getString(PushMessageManager.i, "");
            c.a(com.salesforce.marketingcloud.storage.c.e, string);
            c.a(com.salesforce.marketingcloud.storage.c.i, bundle.getString(PushMessageManager.g, ""));
            b(string);
            this.l.d(a.C0001a.FETCH_PUSH_TOKEN);
            this.f36n.f().edit().putLong(u, System.currentTimeMillis()).apply();
            a(string);
            return;
        }
        c.a(com.salesforce.marketingcloud.storage.c.i);
        this.l.b(a.C0001a.FETCH_PUSH_TOKEN);
    }
}
