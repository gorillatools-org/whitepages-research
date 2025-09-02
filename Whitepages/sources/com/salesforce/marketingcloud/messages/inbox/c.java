package com.salesforce.marketingcloud.messages.inbox;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.UrlHandler;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.inbox.InboxMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class c implements InboxMessageManager {
    final com.salesforce.marketingcloud.storage.j d;
    final com.salesforce.marketingcloud.analytics.g e;
    final com.salesforce.marketingcloud.http.c f;
    final MarketingCloudConfig g;
    final String h;
    private final Set<InboxMessageManager.InboxResponseListener> i = new ArraySet();
    private final com.salesforce.marketingcloud.alarms.b j;
    private final l k;
    private final Object l = new Object();
    private InboxMessageManager.InboxRefreshListener m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f35n;

    class a extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.b = str2;
        }

        /* access modifiers changed from: protected */
        public void a() {
            c.this.d.q().b(TextUtils.split(this.b, ","));
        }
    }

    class b implements MarketingCloudSdk.WhenReadyListener {
        final /* synthetic */ com.salesforce.marketingcloud.http.a a;

        b(com.salesforce.marketingcloud.http.a aVar) {
            this.a = aVar;
        }

        public void ready(MarketingCloudSdk marketingCloudSdk) {
            c cVar = c.this;
            cVar.f.a(this.a.a(cVar.g, cVar.d.c(), com.salesforce.marketingcloud.http.a.b(c.this.g.applicationId(), c.this.h)));
        }
    }

    /* renamed from: com.salesforce.marketingcloud.messages.inbox.c$c  reason: collision with other inner class name */
    class C0026c extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ com.salesforce.marketingcloud.storage.h b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        C0026c(String str, Object[] objArr, com.salesforce.marketingcloud.storage.h hVar) {
            super(str, objArr);
            this.b = hVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.b.h();
        }
    }

    class d extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ InboxMessage b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, InboxMessage inboxMessage) {
            super(str, objArr);
            this.b = inboxMessage;
        }

        /* access modifiers changed from: protected */
        public void a() {
            c.this.d.q().a(this.b, c.this.d.b());
        }
    }

    class e extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.b = str2;
        }

        /* access modifiers changed from: protected */
        public void a() {
            c.this.setMessageRead(this.b);
        }
    }

    class f extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        f(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.b = str2;
        }

        /* access modifiers changed from: protected */
        public void a() {
            c.this.d.q().c(this.b);
        }
    }

    class g extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        g(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.b = str2;
        }

        /* access modifiers changed from: protected */
        public void a() {
            c.this.d.q().d(this.b);
        }
    }

    class h extends com.salesforce.marketingcloud.internal.g {
        h(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            c.this.d.q().j();
        }
    }

    class i extends com.salesforce.marketingcloud.internal.g {
        i(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            c.this.d.q().b();
        }
    }

    class j implements Runnable {
        j() {
        }

        public void run() {
            c.this.b(false);
        }
    }

    class k extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ List b;

        class a implements Runnable {
            a() {
            }

            public void run() {
                k kVar = k.this;
                c.this.a((List<InboxMessage>) kVar.b);
                c.this.b(true);
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        k(String str, Object[] objArr, List list) {
            super(str, objArr);
            this.b = list;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x007c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r9 = this;
                com.salesforce.marketingcloud.messages.inbox.c r0 = com.salesforce.marketingcloud.messages.inbox.c.this
                com.salesforce.marketingcloud.storage.j r0 = r0.d
                com.salesforce.marketingcloud.storage.h r0 = r0.q()
                com.salesforce.marketingcloud.messages.inbox.c r1 = com.salesforce.marketingcloud.messages.inbox.c.this
                com.salesforce.marketingcloud.storage.j r1 = r1.d
                com.salesforce.marketingcloud.util.c r1 = r1.b()
                java.util.ArrayList r2 = new java.util.ArrayList
                java.util.List r3 = r9.b
                int r3 = r3.size()
                r2.<init>(r3)
                java.util.List r3 = r9.b
                boolean r3 = r3.isEmpty()
                if (r3 != 0) goto L_0x009e
                java.util.List r3 = r9.b
                java.util.Iterator r3 = r3.iterator()
            L_0x0029:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x009e
                java.lang.Object r4 = r3.next()
                com.salesforce.marketingcloud.messages.inbox.InboxMessage r4 = (com.salesforce.marketingcloud.messages.inbox.InboxMessage) r4
                java.lang.String r5 = r4.id()
                r2.add(r5)
                java.lang.String r5 = r4.id()
                com.salesforce.marketingcloud.storage.h$b r5 = r0.f(r5)
                r6 = 1
                if (r5 == 0) goto L_0x0091
                java.lang.String r7 = r5.b
                if (r7 != 0) goto L_0x0056
                boolean r7 = r5.e
                com.salesforce.marketingcloud.internal.b.a(r4, r7)
                boolean r7 = r5.d
                com.salesforce.marketingcloud.internal.b.c(r4, r7)
                goto L_0x006e
            L_0x0056:
                java.lang.String r8 = com.salesforce.marketingcloud.internal.b.a(r4)
                boolean r7 = r7.equals(r8)
                if (r7 == 0) goto L_0x006e
                boolean r7 = r5.e
                com.salesforce.marketingcloud.internal.b.a(r4, r7)
                boolean r7 = r5.d
                com.salesforce.marketingcloud.internal.b.c(r4, r7)
                java.util.Date r7 = r5.c
                if (r7 != 0) goto L_0x0070
            L_0x006e:
                r7 = r6
                goto L_0x0071
            L_0x0070:
                r7 = 0
            L_0x0071:
                boolean r8 = r5.f
                com.salesforce.marketingcloud.internal.b.b(r4, r8)
                int r8 = com.salesforce.marketingcloud.internal.b.c(r4)
                if (r8 <= 0) goto L_0x007f
                com.salesforce.marketingcloud.internal.b.c(r4, r6)
            L_0x007f:
                boolean r8 = r5.e
                if (r8 != 0) goto L_0x008d
                boolean r5 = r5.d
                if (r5 == 0) goto L_0x0090
                int r5 = com.salesforce.marketingcloud.internal.b.c(r4)
                if (r5 != 0) goto L_0x0090
            L_0x008d:
                com.salesforce.marketingcloud.internal.b.b(r4, r6)
            L_0x0090:
                r6 = r7
            L_0x0091:
                r0.a((com.salesforce.marketingcloud.messages.inbox.InboxMessage) r4, (com.salesforce.marketingcloud.util.c) r1)
                if (r6 == 0) goto L_0x0029
                com.salesforce.marketingcloud.messages.inbox.c r5 = com.salesforce.marketingcloud.messages.inbox.c.this
                com.salesforce.marketingcloud.analytics.g r5 = r5.e
                r5.a(r4)
                goto L_0x0029
            L_0x009e:
                r0.a((java.util.List<java.lang.String>) r2)
                android.os.Handler r0 = new android.os.Handler
                android.os.Looper r1 = android.os.Looper.getMainLooper()
                r0.<init>(r1)
                com.salesforce.marketingcloud.messages.inbox.c$k$a r1 = new com.salesforce.marketingcloud.messages.inbox.c$k$a
                r1.<init>()
                r0.post(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.inbox.c.k.a():void");
        }
    }

    c(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.j jVar, String str, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.c cVar, com.salesforce.marketingcloud.analytics.g gVar, l lVar) {
        this.g = marketingCloudConfig;
        this.d = jVar;
        this.h = str;
        this.j = bVar;
        this.f = cVar;
        this.e = gVar;
        this.k = lVar;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.f35n = false;
        d();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.f35n = true;
        a(false);
    }

    /* access modifiers changed from: package-private */
    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.salesforce.marketingcloud.storage.db.g.e, this.d.q().n(this.d.b()));
            return jSONObject;
        } catch (JSONException e2) {
            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "Failed to create our component state JSONObject.", new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        List<h.b> i2 = this.d.q().i();
        int size = i2.size();
        if (size > 0) {
            ArrayList arrayList = new ArrayList(size);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("deviceId", this.h);
                String a2 = com.salesforce.marketingcloud.util.l.a(new Date());
                for (h.b next : i2) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("actionParameters", jSONObject);
                    jSONObject2.put("messageId", next.a);
                    jSONObject2.put("actionDate", a2);
                    jSONObject2.put(UrlHandler.ACTION, next.e ? "Deleted" : "Viewed");
                    jSONArray.put(jSONObject2);
                    arrayList.add(next.a);
                }
                com.salesforce.marketingcloud.http.b a3 = com.salesforce.marketingcloud.http.a.INBOX_STATUS.a(this.g, this.d.c(), com.salesforce.marketingcloud.http.a.a(this.g.applicationId()), jSONArray.toString());
                a3.a(TextUtils.join(",", arrayList));
                this.f.a(a3);
            } catch (JSONException e2) {
                com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "Failed to create Inbox status payload.  Status updates not sent to Marketing Cloud", new Object[0]);
            }
        }
    }

    public void deleteMessage(InboxMessage inboxMessage) {
        if (inboxMessage == null) {
            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, "InboxMessage was null and could not be updated.  Call to deleteMessage() ignored.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.internal.b.a(inboxMessage, true);
        deleteMessage(inboxMessage.id());
    }

    public void disableInbox() {
    }

    public void enableInbox() {
    }

    public int getDeletedMessageCount() {
        return this.d.q().a(h.a.DELETED);
    }

    public List<InboxMessage> getDeletedMessages() {
        return this.d.q().a(this.d.b(), h.a.DELETED);
    }

    public int getMessageCount() {
        return this.d.q().a(h.a.NOT_DELETED);
    }

    public List<InboxMessage> getMessages() {
        return this.d.q().a(this.d.b(), h.a.NOT_DELETED);
    }

    public int getReadMessageCount() {
        return this.d.q().a(h.a.READ);
    }

    public List<InboxMessage> getReadMessages() {
        return this.d.q().a(this.d.b(), h.a.READ);
    }

    public int getUnreadMessageCount() {
        return this.d.q().a(h.a.UNREAD);
    }

    public List<InboxMessage> getUnreadMessages() {
        return this.d.q().a(this.d.b(), h.a.UNREAD);
    }

    public boolean isInboxEnabled() {
        return true;
    }

    public void markAllMessagesDeleted() {
        this.k.b().execute(new i("delete_all", new Object[0]));
    }

    public void markAllMessagesRead() {
        this.k.b().execute(new h("mark_all_read", new Object[0]));
    }

    public void refreshInbox(InboxMessageManager.InboxRefreshListener inboxRefreshListener) {
        synchronized (this.l) {
            if (this.m != null) {
                com.salesforce.marketingcloud.g.d(InboxMessageManager.TAG, "Refresh already in progress.", new Object[0]);
                try {
                    inboxRefreshListener.onRefreshComplete(false);
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "Error delivering Refresh Complete result to %s", inboxRefreshListener.getClass().getName());
                }
            } else {
                this.m = inboxRefreshListener;
                com.salesforce.marketingcloud.g.d(InboxMessageManager.TAG, "Refreshing inbox messages", new Object[0]);
                a(true);
            }
        }
    }

    public void registerInboxResponseListener(InboxMessageManager.InboxResponseListener inboxResponseListener) {
        if (inboxResponseListener != null) {
            synchronized (this.i) {
                this.i.add(inboxResponseListener);
            }
        }
    }

    public void setMessageRead(InboxMessage inboxMessage) {
        if (inboxMessage == null) {
            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, "InboxMessage was null and could not be updated.  Call to setMessageRead() ignored.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.internal.b.c(inboxMessage, true);
        setMessageRead(inboxMessage.id());
    }

    public void unregisterInboxResponseListener(InboxMessageManager.InboxResponseListener inboxResponseListener) {
        synchronized (this.i) {
            this.i.remove(inboxResponseListener);
        }
    }

    private void a(boolean z) {
        MarketingCloudSdk.requestSdk(new b(z ? com.salesforce.marketingcloud.http.a.USER_INITIATED_INBOX_MESSAGE : com.salesforce.marketingcloud.http.a.INBOX_MESSAGE));
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        synchronized (this.l) {
            InboxMessageManager.InboxRefreshListener inboxRefreshListener = this.m;
            if (inboxRefreshListener != null) {
                try {
                    inboxRefreshListener.onRefreshComplete(z);
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "InboxRefreshListener threw an exception", new Object[0]);
                }
                this.m = null;
            }
        }
    }

    public void deleteMessage(String str) {
        this.k.b().execute(new f("inbox_delete", new Object[0], str));
    }

    public void setMessageRead(String str) {
        this.k.b().execute(new g("mark_read", new Object[0], str));
    }

    /* access modifiers changed from: package-private */
    public void a(InboxMessage inboxMessage) {
        this.k.b().execute(new d("inbox_push_received", new Object[0], inboxMessage));
        if (this.f35n) {
            a(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(List<InboxMessage> list) {
        this.k.b().execute(new k("inbox_updated", new Object[0], list));
    }

    /* access modifiers changed from: package-private */
    public void a(NotificationMessage notificationMessage) {
        if (this.g.markMessageReadOnInboxNotificationOpen()) {
            this.k.b().execute(new e("inbox_notification_opened", new Object[0], notificationMessage.id()));
        }
    }

    /* access modifiers changed from: package-private */
    public void b(int i2, String str) {
        com.salesforce.marketingcloud.g.c(InboxMessageManager.TAG, "Request failed: %d - %s", Integer.valueOf(i2), str);
        this.j.b(a.C0001a.UPDATE_INBOX_MESSAGE_STATUS);
    }

    /* access modifiers changed from: package-private */
    public void a(List<InboxMessage> list) {
        synchronized (this.i) {
            if (!this.i.isEmpty()) {
                for (InboxMessageManager.InboxResponseListener next : this.i) {
                    if (next != null) {
                        try {
                            next.onInboxMessagesChanged(list);
                        } catch (Exception e2) {
                            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "%s threw an exception while processing the inbox messages response", next.getClass().getName());
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, String str) {
        com.salesforce.marketingcloud.g.c(InboxMessageManager.TAG, "Request failed: %d - %s", Integer.valueOf(i2), str);
        new Handler(Looper.getMainLooper()).post(new j());
    }

    /* access modifiers changed from: package-private */
    public void a(com.salesforce.marketingcloud.http.b bVar) {
        if (bVar.q() != null) {
            this.j.c(a.C0001a.UPDATE_INBOX_MESSAGE_STATUS);
            this.k.b().execute(new a("inbox_status_updated", new Object[0], bVar.q()));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(com.salesforce.marketingcloud.http.d dVar) {
        int length;
        try {
            JSONArray optJSONArray = new JSONObject(dVar.a()).optJSONArray(com.salesforce.marketingcloud.storage.db.i.e);
            List emptyList = Collections.emptyList();
            if (optJSONArray != null && (length = optJSONArray.length()) > 0) {
                emptyList = new ArrayList(length);
                for (int i2 = 0; i2 < length; i2++) {
                    try {
                        emptyList.add(new InboxMessage(optJSONArray.getJSONObject(i2)));
                    } catch (Exception e2) {
                        com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "Failed to parse inbox message", new Object[0]);
                    }
                }
            }
            b((List<InboxMessage>) emptyList);
        } catch (Exception e3) {
            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e3, "Failed to parse inbox messages response", new Object[0]);
            a(-1, "Failed to parse response");
        }
    }

    static void a(com.salesforce.marketingcloud.storage.j jVar, com.salesforce.marketingcloud.alarms.b bVar, l lVar, boolean z) {
        bVar.d(a.C0001a.UPDATE_INBOX_MESSAGE_STATUS);
        if (z) {
            lVar.b().execute(new C0026c("inbox_shutdown", new Object[0], jVar.q()));
        }
    }
}
