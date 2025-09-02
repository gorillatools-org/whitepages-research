package com.salesforce.marketingcloud.messages.inbox;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.g;
import com.salesforce.marketingcloud.behaviors.b;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.inbox.InboxMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.j;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class a implements e, InboxMessageManager, b, c.C0017c, b.C0003b {

    /* renamed from: n  reason: collision with root package name */
    private static final String f34n = "InboxMessagingEnabled";
    private static final String o = "8";
    private static final Object p = new Object();
    private final MarketingCloudConfig d;
    private final j e;
    private final String f;
    private final com.salesforce.marketingcloud.behaviors.c g;
    private final com.salesforce.marketingcloud.alarms.b h;
    private final c i;
    private final g j;
    private c k;
    private l l;
    private com.salesforce.marketingcloud.toggles.a m;

    /* renamed from: com.salesforce.marketingcloud.messages.inbox.a$a  reason: collision with other inner class name */
    static /* synthetic */ class C0025a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] c;

        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
        static {
            /*
                com.salesforce.marketingcloud.alarms.a$a[] r0 = com.salesforce.marketingcloud.alarms.a.C0001a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                c = r0
                r1 = 1
                com.salesforce.marketingcloud.alarms.a$a r2 = com.salesforce.marketingcloud.alarms.a.C0001a.UPDATE_INBOX_MESSAGE_STATUS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.salesforce.marketingcloud.http.a[] r0 = com.salesforce.marketingcloud.http.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                com.salesforce.marketingcloud.http.a r2 = com.salesforce.marketingcloud.http.a.INBOX_MESSAGE     // Catch:{ NoSuchFieldError -> 0x0023 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0023 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0023 }
            L_0x0023:
                r0 = 2
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x002e }
                com.salesforce.marketingcloud.http.a r3 = com.salesforce.marketingcloud.http.a.USER_INITIATED_INBOX_MESSAGE     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                r2 = 3
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x0039 }
                com.salesforce.marketingcloud.http.a r4 = com.salesforce.marketingcloud.http.a.INBOX_STATUS     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                com.salesforce.marketingcloud.behaviors.a[] r3 = com.salesforce.marketingcloud.behaviors.a.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                a = r3
                com.salesforce.marketingcloud.behaviors.a r4 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED     // Catch:{ NoSuchFieldError -> 0x004a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004a }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x004a }
            L_0x004a:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.salesforce.marketingcloud.behaviors.a r3 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x005e }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED     // Catch:{ NoSuchFieldError -> 0x005e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0069 }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_NOTIFICATION_OPENED     // Catch:{ NoSuchFieldError -> 0x0069 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0069 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0069 }
            L_0x0069:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.inbox.a.C0025a.<clinit>():void");
        }
    }

    public a(MarketingCloudConfig marketingCloudConfig, j jVar, String str, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, c cVar2, l lVar, g gVar) {
        this.d = (MarketingCloudConfig) com.salesforce.marketingcloud.util.j.a(marketingCloudConfig, "MarketingCloudConfig is null.");
        this.e = (j) com.salesforce.marketingcloud.util.j.a(jVar, "Storage is null.");
        this.f = (String) com.salesforce.marketingcloud.util.j.a(str, "You must provide the Device ID.");
        this.g = (com.salesforce.marketingcloud.behaviors.c) com.salesforce.marketingcloud.util.j.a(cVar, "BehaviorManager is null.");
        this.h = (com.salesforce.marketingcloud.alarms.b) com.salesforce.marketingcloud.util.j.a(bVar, "AlarmScheduler is null.");
        this.i = (c) com.salesforce.marketingcloud.util.j.a(cVar2, "RequestManager is null.");
        this.j = (g) com.salesforce.marketingcloud.util.j.a(gVar, "InboxAnalyticEventListener is null.");
        this.l = lVar;
    }

    private void a() {
        this.k = new c(this.d, this.e, this.f, this.h, this.i, this.j, this.l);
        this.i.a(com.salesforce.marketingcloud.http.a.INBOX_MESSAGE, (c.C0017c) this);
        this.i.a(com.salesforce.marketingcloud.http.a.USER_INITIATED_INBOX_MESSAGE, (c.C0017c) this);
        this.i.a(com.salesforce.marketingcloud.http.a.INBOX_STATUS, (c.C0017c) this);
        this.h.a((b.C0003b) this, a.C0001a.UPDATE_INBOX_MESSAGE_STATUS);
        this.g.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_NOTIFICATION_OPENED));
    }

    public final String componentName() {
        return "InboxMessageManager";
    }

    public JSONObject componentState() {
        try {
            c cVar = this.k;
            JSONObject c = cVar != null ? cVar.c() : new JSONObject();
            c.put("inboxEnabled", isInboxEnabled());
            return c;
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void controlChannelInit(int i2) {
        if (com.salesforce.marketingcloud.b.a(i2, 128)) {
            this.k = null;
            c.a(this.e, this.h, this.l, com.salesforce.marketingcloud.b.c(i2, 128));
            this.g.a((com.salesforce.marketingcloud.behaviors.b) this);
            this.h.e(a.C0001a.UPDATE_INBOX_MESSAGE_STATUS);
            this.i.a(com.salesforce.marketingcloud.http.a.INBOX_MESSAGE);
            this.i.a(com.salesforce.marketingcloud.http.a.USER_INITIATED_INBOX_MESSAGE);
            this.i.a(com.salesforce.marketingcloud.http.a.INBOX_STATUS);
        } else if (this.k == null && isInboxEnabled()) {
            a();
        }
    }

    public void deleteMessage(InboxMessage inboxMessage) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.deleteMessage(inboxMessage);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to deleteMessage() was ignored.", new Object[0]);
        }
    }

    public void disableInbox() {
        synchronized (p) {
            com.salesforce.marketingcloud.toggles.a aVar = com.salesforce.marketingcloud.toggles.a.DISABLED;
            this.m = aVar;
            this.e.f().edit().putString(f34n, this.m.name()).apply();
            String str = InboxMessageManager.TAG;
            com.salesforce.marketingcloud.g.a(str, "Inbox runtime toggle set to " + aVar.name(), new Object[0]);
            this.k = null;
            tearDown(false);
        }
    }

    public void enableInbox() {
        synchronized (p) {
            try {
                if (!com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.e.o()), 128)) {
                    String str = InboxMessageManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Inbox runtime toggle set to ");
                    com.salesforce.marketingcloud.toggles.a aVar = com.salesforce.marketingcloud.toggles.a.ENABLED;
                    sb.append(aVar.name());
                    com.salesforce.marketingcloud.g.a(str, sb.toString(), new Object[0]);
                    this.m = aVar;
                    this.e.f().edit().putString(f34n, this.m.name()).apply();
                    a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getDeletedMessageCount() {
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getDeletedMessageCount();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getDeletedMessageCount() was ignored.", new Object[0]);
        return 0;
    }

    public List<InboxMessage> getDeletedMessages() {
        List<InboxMessage> emptyList = Collections.emptyList();
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getDeletedMessages();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getDeletedMessages() was ignored.", new Object[0]);
        return emptyList;
    }

    public int getMessageCount() {
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getMessageCount();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getMessageCount() was ignored.", new Object[0]);
        return 0;
    }

    public List<InboxMessage> getMessages() {
        List<InboxMessage> emptyList = Collections.emptyList();
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getMessages();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getMessages() was ignored.", new Object[0]);
        return emptyList;
    }

    public int getReadMessageCount() {
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getReadMessageCount();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getReadMessageCount() was ignored.", new Object[0]);
        return 0;
    }

    public List<InboxMessage> getReadMessages() {
        List<InboxMessage> emptyList = Collections.emptyList();
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getReadMessages();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getReadMessages() was ignored.", new Object[0]);
        return emptyList;
    }

    public int getUnreadMessageCount() {
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getUnreadMessageCount();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getUnreadMessageCount() was ignored.", new Object[0]);
        return 0;
    }

    public List<InboxMessage> getUnreadMessages() {
        List<InboxMessage> emptyList = Collections.emptyList();
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getUnreadMessages();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getUnreadMessages() was ignored.", new Object[0]);
        return emptyList;
    }

    public void init(InitializationStatus.a aVar, int i2) {
        if (a(i2)) {
            a();
        }
    }

    public boolean isInboxEnabled() {
        return a(com.salesforce.marketingcloud.b.a(this.e.o()));
    }

    public void markAllMessagesDeleted() {
        c cVar = this.k;
        if (cVar != null) {
            cVar.markAllMessagesDeleted();
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to markAllMessagesDeleted() was ignored.", new Object[0]);
        }
    }

    public void markAllMessagesRead() {
        c cVar = this.k;
        if (cVar != null) {
            cVar.markAllMessagesRead();
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to markAllMessagesRead() was ignored.", new Object[0]);
        }
    }

    public void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        NotificationMessage notificationMessage;
        if (this.k != null) {
            int i2 = C0025a.a[aVar.ordinal()];
            if (i2 == 1) {
                this.k.b();
            } else if (i2 == 2) {
                this.k.a();
            } else if (i2 != 3) {
                if (i2 == 4 && (notificationMessage = (NotificationMessage) bundle.get(com.salesforce.marketingcloud.notifications.a.p)) != null) {
                    this.k.a(notificationMessage);
                }
            } else if (a(bundle)) {
                try {
                    this.k.a(new InboxMessage(bundle));
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "Failed to seed inbox_messages table with message: %s.", bundle.getString(NotificationMessage.NOTIF_KEY_ID));
                }
            }
        }
    }

    public void refreshInbox(InboxMessageManager.InboxRefreshListener inboxRefreshListener) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.refreshInbox(inboxRefreshListener);
            return;
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to refreshInbox() was ignored.", new Object[0]);
        if (inboxRefreshListener != null) {
            try {
                inboxRefreshListener.onRefreshComplete(false);
            } catch (Exception unused) {
                com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, "InboxRefreshListener threw an exception.", new Object[0]);
            }
        }
    }

    public void registerInboxResponseListener(InboxMessageManager.InboxResponseListener inboxResponseListener) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.registerInboxResponseListener(inboxResponseListener);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to registerInboxResponseListener() was ignored.", new Object[0]);
        }
    }

    public void setMessageRead(InboxMessage inboxMessage) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.setMessageRead(inboxMessage);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to setMessageRead() was ignored.", new Object[0]);
        }
    }

    public void tearDown(boolean z) {
        com.salesforce.marketingcloud.alarms.b bVar = this.h;
        if (bVar != null) {
            bVar.e(a.C0001a.UPDATE_INBOX_MESSAGE_STATUS);
        }
        com.salesforce.marketingcloud.behaviors.c cVar = this.g;
        if (cVar != null) {
            cVar.a((com.salesforce.marketingcloud.behaviors.b) this);
        }
    }

    public void unregisterInboxResponseListener(InboxMessageManager.InboxResponseListener inboxResponseListener) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.unregisterInboxResponseListener(inboxResponseListener);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to unregisterInboxResponseListener() was ignored.", new Object[0]);
        }
    }

    a(c cVar) {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = cVar;
    }

    private com.salesforce.marketingcloud.toggles.a a(com.salesforce.marketingcloud.toggles.a aVar) {
        if (aVar != null) {
            return aVar;
        }
        String string = this.e.f().getString(f34n, (String) null);
        return string == null ? com.salesforce.marketingcloud.toggles.a.UNKNOWN : com.salesforce.marketingcloud.toggles.a.valueOf(string);
    }

    public void deleteMessage(String str) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.deleteMessage(str);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to deleteMessage() was ignored.", new Object[0]);
        }
    }

    public void setMessageRead(String str) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.setMessageRead(str);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to setMessageRead() was ignored.", new Object[0]);
        }
    }

    private boolean a(int i2) {
        if (com.salesforce.marketingcloud.b.a(i2, 128)) {
            return false;
        }
        if (this.m == null) {
            this.m = a((com.salesforce.marketingcloud.toggles.a) null);
        }
        com.salesforce.marketingcloud.toggles.a aVar = this.m;
        return aVar == com.salesforce.marketingcloud.toggles.a.ENABLED || (aVar == com.salesforce.marketingcloud.toggles.a.UNKNOWN && this.d.inboxEnabled());
    }

    private static boolean a(Bundle bundle) {
        return o.equals(bundle.getString(NotificationMessage.NOTIF_KEY_MESSAGE_TYPE));
    }

    public static boolean a(Map<String, String> map) {
        return o.equals(map.get(NotificationMessage.NOTIF_KEY_MESSAGE_TYPE));
    }

    public void a(a.C0001a aVar) {
        if (this.k != null && C0025a.c[aVar.ordinal()] == 1) {
            this.k.d();
        }
    }

    public void a(com.salesforce.marketingcloud.http.b bVar, d dVar) {
        if (this.k != null) {
            int i2 = C0025a.b[bVar.p().ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (dVar.g()) {
                    this.k.a(dVar);
                } else {
                    this.k.a(dVar.b(), dVar.e());
                }
            } else if (i2 == 3) {
                if (dVar.g()) {
                    this.k.a(bVar);
                } else {
                    this.k.b(dVar.b(), dVar.e());
                }
            }
        }
    }
}
