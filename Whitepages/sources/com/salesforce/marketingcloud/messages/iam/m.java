package com.salesforce.marketingcloud.messages.iam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.UrlHandler;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.events.f;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.media.b;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.iam.InAppMessageManager;
import com.salesforce.marketingcloud.storage.db.i;
import com.salesforce.marketingcloud.storage.j;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class m implements InAppMessageManager, i, b.C0003b, b.a, f {
    private static final String A = "minDurationBetweenMessages";
    private static final int v = 1;
    private static final int w = 111;
    static final String x = g.a("InAppMessageManager");
    private static final String y = "messagesAttemptedInSession";
    private static final String z = "maxMessagesPerSession";
    private final com.salesforce.marketingcloud.alarms.b d;
    private final UrlHandler e;
    private final l f;
    final Context g;
    final j h;
    final com.salesforce.marketingcloud.analytics.f i;
    final Object j = new Object();
    private final com.salesforce.marketingcloud.config.a k;
    private Typeface l;
    private int m;

    /* renamed from: n  reason: collision with root package name */
    private com.salesforce.marketingcloud.media.b f33n;
    private InAppMessage o;
    private final AtomicInteger p;
    final AtomicInteger q;
    final Handler r;
    private final Handler s;
    InAppMessageManager.EventListener t;
    o u;

    class a extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.b = str2;
        }

        /* access modifiers changed from: protected */
        public void a() {
            InAppMessage a = m.this.h.p().a((Collection<String>) Collections.singletonList(this.b), m.this.h.b());
            if (a != null) {
                m.this.d(a);
            } else {
                g.a(m.x, "Unable to find InAppMessage for message id [%s]", this.b);
            }
        }
    }

    class b extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ InAppMessage b;

        class a implements Runnable {
            a() {
            }

            public void run() {
                b bVar = b.this;
                m.this.t.didShowMessage(bVar.b);
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, InAppMessage inAppMessage) {
            super(str, objArr);
            this.b = inAppMessage;
        }

        /* access modifiers changed from: protected */
        public void a() {
            m.this.h.p().a(this.b);
            m.this.i.a(this.b);
            m.this.d();
            synchronized (m.this.j) {
                if (m.this.t != null) {
                    try {
                        new Handler(Looper.getMainLooper()).post(new a());
                    } catch (Exception e) {
                        g.b(m.x, e, "InAppMessage EventListener threw an exception", new Object[0]);
                    }
                }
            }
        }
    }

    class c extends com.salesforce.marketingcloud.internal.g {
        c(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            m mVar = m.this;
            mVar.a(mVar.h.p().e(m.this.h.b()));
        }
    }

    class d implements Runnable {
        final /* synthetic */ InAppMessage a;

        d(InAppMessage inAppMessage) {
            this.a = inAppMessage;
        }

        public void run() {
            synchronized (m.this.j) {
                try {
                    InAppMessageManager.EventListener eventListener = m.this.t;
                    if (eventListener != null) {
                        if (!eventListener.shouldShowMessage(this.a)) {
                            g.a(m.x, "InAppMessage EventListener[%s] returned false for shouldShowMessage [%s]", m.this.t.getClass().getName(), this.a.id());
                            return;
                        }
                    }
                } catch (Exception e) {
                    g.b(m.x, e, "InAppMessage EventListener threw exception during shouldShowMessage", new Object[0]);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            try {
                Class<? extends f> a2 = m.this.a(this.a);
                if (a2 != null) {
                    m mVar = m.this;
                    if (mVar.a(a2, this.a, mVar.g)) {
                        m.this.g.startActivity(new Intent(m.this.g, a2).setFlags(276889600).putExtra("messageHandler", new k(this.a)));
                        return;
                    }
                    return;
                }
                g.a(m.x, "Not supported", new Object[0]);
            } catch (Exception e2) {
                g.b(m.x, e2, "Failed to display InAppMessage [%s]", this.a.id());
            }
        }
    }

    static /* synthetic */ class e {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type[] r0 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.bannerTop     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.bannerBottom     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.modal     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.fullImageFill     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.full     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.m.e.<clinit>():void");
        }
    }

    m(Context context, j jVar, com.salesforce.marketingcloud.alarms.b bVar, o oVar, UrlHandler urlHandler, l lVar, com.salesforce.marketingcloud.analytics.f fVar, Handler handler, com.salesforce.marketingcloud.config.a aVar) {
        this.g = context;
        this.h = jVar;
        this.d = bVar;
        this.u = oVar;
        this.e = urlHandler;
        this.i = fVar;
        this.f = lVar;
        this.k = aVar;
        bVar.a((b.C0003b) this, a.C0001a.IAM_IMAGE_BATCH);
        this.q = new AtomicInteger();
        this.p = new AtomicInteger();
        this.r = new Handler(Looper.getMainLooper());
        this.s = handler;
    }

    /* access modifiers changed from: package-private */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(i.e, this.h.p().d(this.h.b()));
            InAppMessageManager.EventListener eventListener = this.t;
            if (eventListener != null) {
                jSONObject.put("eventListener", eventListener.getClass().getName());
            }
            jSONObject.put("subscriberToken", this.h.c().b(com.salesforce.marketingcloud.storage.c.j, "null"));
            jSONObject.put("custom_font_set", this.l != null);
            jSONObject.put("status_bar_color", this.m);
        } catch (Exception e2) {
            g.b(x, e2, "Unable to compile componentState for InAppMessageManager", new Object[0]);
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    public void b(InAppMessage inAppMessage) {
        try {
            this.i.b(inAppMessage);
        } catch (Exception e2) {
            g.b(x, e2, "Failed to log download analytics for IAM %s", inAppMessage.id());
        }
    }

    public void c() {
        this.s.removeCallbacksAndMessages((Object) null);
    }

    public boolean canDisplay(InAppMessage inAppMessage) {
        InAppMessage inAppMessage2 = this.o;
        if (inAppMessage2 == null) {
            this.f.b().execute(new b("can_display", new Object[0], inAppMessage));
            this.o = inAppMessage;
        } else if (inAppMessage != inAppMessage2) {
            g.a(x, "In App Message [%s] not displayed because [%s] is currently being displayed", inAppMessage.id(), this.o.id());
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void d(InAppMessage inAppMessage) {
        if (inAppMessage != null && !c(inAppMessage)) {
            this.s.postDelayed(new d(inAppMessage), TimeUnit.SECONDS.toMillis((long) inAppMessage.messageDelaySec()));
        }
    }

    public int getStatusBarColor() {
        return this.m;
    }

    public Typeface getTypeface() {
        return this.l;
    }

    public void handleMessageFinished(InAppMessage inAppMessage, j jVar) {
        InAppMessage inAppMessage2 = this.o;
        if (inAppMessage2 != null && inAppMessage2.id().equals(inAppMessage.id())) {
            com.salesforce.marketingcloud.analytics.f fVar = this.i;
            if (fVar != null) {
                fVar.a(inAppMessage, jVar);
            }
            synchronized (this.j) {
                InAppMessageManager.EventListener eventListener = this.t;
                if (eventListener != null) {
                    try {
                        eventListener.didCloseMessage(inAppMessage);
                    } catch (Exception e2) {
                        g.b(x, e2, "InAppMessageEventListener threw an exception", new Object[0]);
                    }
                }
            }
        }
        this.o = null;
    }

    public void handleOutcomes(Collection<String> collection) {
        if (collection != null && !collection.isEmpty()) {
            String str = x;
            g.d(str, "Resolving IAM from outcomes %s", collection.toString());
            InAppMessage a2 = this.h.p().a(collection, this.h.b());
            if (a2 != null) {
                g.d(str, "Outcomes resolved to message[%s]", a2.id());
                d(a2);
                return;
            }
            g.d(str, "No message resolved.", new Object[0]);
        }
    }

    public o imageHandler() {
        return this.u;
    }

    public void setInAppMessageListener(InAppMessageManager.EventListener eventListener) {
        synchronized (this.j) {
            this.t = eventListener;
        }
    }

    public void setStatusBarColor(int i2) {
        this.m = i2;
    }

    public void setTypeface(Typeface typeface) {
        this.l = typeface;
    }

    public void showMessage(String str) {
        if (str != null) {
            this.f.b().execute(new a("iam_showMessage", new Object[0], str));
        }
    }

    public UrlHandler urlHandler() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public Class<? extends f> a(InAppMessage inAppMessage) {
        int i2 = e.a[inAppMessage.type().ordinal()];
        if (i2 == 1 || i2 == 2) {
            return IamBannerActivity.class;
        }
        if (i2 == 3) {
            return IamModalActivity.class;
        }
        if (i2 == 4) {
            return IamFullImageFillActivity.class;
        }
        if (i2 != 5) {
            return null;
        }
        return IamFullscreenActivity.class;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.q.set(0);
        this.p.set(0);
        this.r.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073 A[SYNTHETIC, Splitter:B:23:0x0073] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(com.salesforce.marketingcloud.messages.iam.InAppMessage r8) {
        /*
            r7 = this;
            boolean r0 = r8.displayLimitOverride()
            r1 = 0
            if (r0 == 0) goto L_0x0017
            java.lang.String r0 = x
            java.lang.String r8 = r8.id()
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r2 = "InAppMessage [%s] has displayLimit Override set. The message will not honour displayLimit settings"
            com.salesforce.marketingcloud.g.a((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object[]) r8)
            return r1
        L_0x0017:
            com.salesforce.marketingcloud.storage.j r0 = r7.h
            android.content.SharedPreferences r0 = r0.f()
            r2 = 2147483647(0x7fffffff, float:NaN)
            java.lang.String r3 = "event_max_display_in_session"
            int r0 = r0.getInt(r3, r2)
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0081 }
            r2.<init>()     // Catch:{ Exception -> 0x0081 }
            java.util.concurrent.atomic.AtomicInteger r4 = r7.q     // Catch:{ Exception -> 0x0081 }
            int r4 = r4.get()     // Catch:{ Exception -> 0x0081 }
            java.lang.String r5 = "maxMessagesPerSession"
            r6 = 1
            if (r4 < r0) goto L_0x004f
            com.salesforce.marketingcloud.storage.j r0 = r7.h     // Catch:{ Exception -> 0x004c }
            android.content.SharedPreferences r0 = r0.f()     // Catch:{ Exception -> 0x004c }
            int r0 = r0.getInt(r3, r1)     // Catch:{ Exception -> 0x004c }
            r2.put(r5, r0)     // Catch:{ Exception -> 0x004c }
            java.lang.String r5 = "messagesAttemptedInSession"
            java.util.concurrent.atomic.AtomicInteger r0 = r7.p     // Catch:{ Exception -> 0x004c }
        L_0x0047:
            int r0 = r0.incrementAndGet()     // Catch:{ Exception -> 0x004c }
            goto L_0x006d
        L_0x004c:
            r0 = move-exception
            r1 = r6
            goto L_0x0082
        L_0x004f:
            android.os.Handler r0 = r7.r     // Catch:{ Exception -> 0x0081 }
            r3 = 111(0x6f, float:1.56E-43)
            boolean r0 = r0.hasMessages(r3)     // Catch:{ Exception -> 0x0081 }
            if (r0 == 0) goto L_0x0071
            java.lang.String r0 = "minDurationBetweenMessages"
            com.salesforce.marketingcloud.storage.j r3 = r7.h     // Catch:{ Exception -> 0x004c }
            android.content.SharedPreferences r3 = r3.f()     // Catch:{ Exception -> 0x004c }
            java.lang.String r4 = "event_min_time_sec_in_session"
            int r1 = r3.getInt(r4, r1)     // Catch:{ Exception -> 0x004c }
            r2.put(r0, r1)     // Catch:{ Exception -> 0x004c }
            java.util.concurrent.atomic.AtomicInteger r0 = r7.p     // Catch:{ Exception -> 0x004c }
            goto L_0x0047
        L_0x006d:
            r2.put(r5, r0)     // Catch:{ Exception -> 0x004c }
            r1 = r6
        L_0x0071:
            if (r1 == 0) goto L_0x0091
            com.salesforce.marketingcloud.config.a r0 = r7.k     // Catch:{ Exception -> 0x0081 }
            boolean r0 = r0.h()     // Catch:{ Exception -> 0x0081 }
            if (r0 == 0) goto L_0x0091
            com.salesforce.marketingcloud.analytics.f r0 = r7.i     // Catch:{ Exception -> 0x0081 }
            r0.a((com.salesforce.marketingcloud.messages.iam.InAppMessage) r8, (org.json.JSONObject) r2)     // Catch:{ Exception -> 0x0081 }
            goto L_0x0091
        L_0x0081:
            r0 = move-exception
        L_0x0082:
            java.lang.String r2 = x
            java.lang.String r8 = r8.id()
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r3 = "Failed to log message Debug Analytics for IAM %s"
            com.salesforce.marketingcloud.g.b(r2, r0, r3, r8)
        L_0x0091:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.m.c(com.salesforce.marketingcloud.messages.iam.InAppMessage):boolean");
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.q.incrementAndGet();
        int i2 = this.h.f().getInt(com.salesforce.marketingcloud.events.c.t, 0);
        if (i2 > 0) {
            this.r.sendMessageDelayed(this.r.obtainMessage(w), TimeUnit.SECONDS.toMillis((long) i2));
        }
    }

    private boolean a(String str) {
        try {
            com.salesforce.marketingcloud.util.l.f(str);
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
        this.d.e(a.C0001a.IAM_IMAGE_BATCH);
        this.r.removeCallbacksAndMessages((Object) null);
        com.salesforce.marketingcloud.media.b bVar = this.f33n;
        if (bVar != null) {
            bVar.b();
        }
        if (z2) {
            com.salesforce.marketingcloud.storage.g p2 = this.h.p();
            this.u.a((Collection<String>) p2.e(this.h.b()));
            p2.a((Collection<String>) Collections.emptyList());
        }
    }

    public void a(a.C0001a aVar) {
        if (aVar == a.C0001a.IAM_IMAGE_BATCH) {
            this.f.b().execute(new c("iam_image_cache", new Object[0]));
        }
    }

    /* access modifiers changed from: package-private */
    public String b(JSONObject jSONObject) {
        String optString = jSONObject.optString("id");
        String optString2 = jSONObject.optString("activityInstanceId");
        if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
            return "";
        }
        String optString3 = jSONObject.optString("endDateUtc", (String) null);
        if (optString3 != null) {
            try {
                if (com.salesforce.marketingcloud.util.l.f(optString3).getTime() < System.currentTimeMillis()) {
                    return "ExpiredMessage";
                }
            } catch (Exception unused) {
                return "InvalidDate";
            }
        }
        String optString4 = jSONObject.optString("startDateUtc", (String) null);
        if (optString4 != null && a(optString4)) {
            return "InvalidDate";
        }
        String optString5 = jSONObject.optString("modifiedDateUtc", (String) null);
        if (optString5 == null) {
            return "NoModifiedDate";
        }
        if (a(optString5)) {
            return "InvalidDate";
        }
        try {
            InAppMessage.Type.valueOf(jSONObject.getString("type"));
            JSONObject optJSONObject = jSONObject.optJSONObject("media");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("title");
            JSONObject optJSONObject3 = jSONObject.optJSONObject("body");
            JSONArray optJSONArray = jSONObject.optJSONArray("buttons");
            if (optJSONObject == null && optJSONObject2 == null && optJSONObject3 == null && (optJSONArray == null || optJSONArray.length() == 0)) {
                return "NoContent";
            }
            if (optJSONObject != null) {
                Object opt = optJSONObject.opt("url");
                if (!(opt instanceof String) || TextUtils.isEmpty((String) opt)) {
                    return "InvalidMedia";
                }
            }
            if (optJSONObject2 != null && TextUtils.isEmpty(optJSONObject2.optString("text", (String) null))) {
                return "InvalidTitle";
            }
            if (optJSONObject3 != null && TextUtils.isEmpty(optJSONObject3.optString("text", (String) null))) {
                return "InvalidBody";
            }
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject optJSONObject4 = optJSONArray.optJSONObject(i2);
                    if (optJSONObject4 == null || TextUtils.isEmpty(optJSONObject4.optString("id")) || TextUtils.isEmpty(optJSONObject4.optString("text"))) {
                        return "InvalidButton";
                    }
                }
            }
            return null;
        } catch (Exception unused2) {
            return "NoMessageType";
        }
    }

    public void a(boolean z2) {
        if (z2) {
            this.d.d(a.C0001a.IAM_IMAGE_BATCH);
        } else {
            this.d.b(a.C0001a.IAM_IMAGE_BATCH);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        if (jSONObject.optInt("version") != 1) {
            g.b(x, "Unable to handle sync payload due to version mismatch", new Object[0]);
            return;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(FirebaseAnalytics.Param.ITEMS);
            int length = jSONArray.length();
            g.a(x, "%d in app message(s) received from sync.", Integer.valueOf(length));
            TreeSet treeSet = new TreeSet();
            com.salesforce.marketingcloud.storage.g p2 = this.h.p();
            com.salesforce.marketingcloud.util.c b2 = this.h.b();
            List<String> e2 = p2.e(b2);
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    String b3 = b(jSONObject2);
                    if (b3 == null) {
                        InAppMessage inAppMessage = new InAppMessage(jSONObject2);
                        if (p2.a(inAppMessage, b2) == 1) {
                            b(inAppMessage);
                        }
                        p2.b(inAppMessage.id(), jSONObject2.optInt("displayCount", 0));
                        treeSet.add(inAppMessage.id());
                    } else if (!b3.isEmpty()) {
                        this.i.a(jSONObject2.optString("id"), jSONObject2.optString("activityInstanceId"), Collections.singletonList(b3));
                    }
                } catch (Exception e3) {
                    g.b(x, e3, "Unable to parse in app message payload", new Object[0]);
                }
            }
            p2.a((Collection<String>) treeSet);
            List<String> e4 = p2.e(b2);
            a(e4);
            TreeSet treeSet2 = new TreeSet(e2);
            treeSet2.removeAll(e4);
            this.u.a((Collection<String>) treeSet2);
        } catch (JSONException e5) {
            g.b(x, e5, "Unable to get InAppMessages from sync payload", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(Class<? extends f> cls, InAppMessage inAppMessage, Context context) throws ClassNotFoundException {
        return (cls == Class.forName(IamFullscreenActivity.class.getName()) && inAppMessage.type() == InAppMessage.Type.fullImageFill && context.getResources().getConfiguration().orientation != 1) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list) {
        if (!list.isEmpty()) {
            com.salesforce.marketingcloud.media.b bVar = this.f33n;
            if (bVar != null) {
                bVar.b();
            }
            com.salesforce.marketingcloud.media.b a2 = this.u.a(list);
            this.f33n = a2;
            a2.a((b.a) this);
        }
    }
}
