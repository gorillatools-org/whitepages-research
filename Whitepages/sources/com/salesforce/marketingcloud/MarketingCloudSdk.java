package com.salesforce.marketingcloud;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.salesforce.marketingcloud.MCLogListener;
import com.salesforce.marketingcloud.analytics.AnalyticsManager;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.events.EventManager;
import com.salesforce.marketingcloud.internal.e;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.location.f;
import com.salesforce.marketingcloud.messages.RegionMessageManager;
import com.salesforce.marketingcloud.messages.iam.InAppMessageComponent;
import com.salesforce.marketingcloud.messages.iam.InAppMessageManager;
import com.salesforce.marketingcloud.messages.inbox.InboxMessageManager;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.registration.d;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface;
import com.salesforce.marketingcloud.storage.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public final class MarketingCloudSdk extends PushModuleInterface implements b.C0008b {
    private static volatile boolean A = false;
    private static volatile boolean B = true;
    static final String s = "MarketingCloudPrefs";
    static final String t = "InitConfig";
    static final String u = g.a("MarketingCloudSdk");
    private static final Object v = new Object();
    private static final List<c> w = new ArrayList();
    static MarketingCloudSdk x;
    private static Context y;
    private static volatile boolean z;
    private final MarketingCloudConfig a;
    private final List<d> b = new ArrayList();
    private final SFMCSdkComponents c;
    f d;
    com.salesforce.marketingcloud.behaviors.c e;
    private b f;
    private j g;
    private com.salesforce.marketingcloud.http.c h;
    private com.salesforce.marketingcloud.messages.inbox.a i;
    private d j;
    private com.salesforce.marketingcloud.notifications.a k;
    private com.salesforce.marketingcloud.messages.push.a l;
    private com.salesforce.marketingcloud.messages.d m;

    /* renamed from: n  reason: collision with root package name */
    private com.salesforce.marketingcloud.events.c f15n;
    private AnalyticsManager o;
    private InitializationStatus p;
    private InAppMessageComponent q;
    private l r;

    @MCKeep
    public interface InitializationListener {
        void complete(InitializationStatus initializationStatus);
    }

    @MCKeep
    public interface WhenReadyListener {
        void ready(MarketingCloudSdk marketingCloudSdk);
    }

    class a implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ MarketingCloudConfig b;
        final /* synthetic */ SFMCSdkComponents c;
        final /* synthetic */ InitializationListener d;

        a(Context context, MarketingCloudConfig marketingCloudConfig, SFMCSdkComponents sFMCSdkComponents, InitializationListener initializationListener) {
            this.a = context;
            this.b = marketingCloudConfig;
            this.c = sFMCSdkComponents;
            this.d = initializationListener;
        }

        public void run() {
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("SFMC_init");
            try {
                String str = MarketingCloudSdk.u;
                g.d(str, "Starting init thread", new Object[0]);
                MarketingCloudSdk.a(this.a, this.b, this.c, this.d);
                Thread.currentThread().setName(name);
                g.d(str, "~~ MarketingCloudSdk v%s init complete ~~", MarketingCloudSdk.getSdkVersionName());
            } catch (Throwable th) {
                Thread.currentThread().setName(name);
                g.d(MarketingCloudSdk.u, "~~ MarketingCloudSdk v%s init complete ~~", MarketingCloudSdk.getSdkVersionName());
                throw th;
            }
        }
    }

    class b extends c {
        b(Looper looper, WhenReadyListener whenReadyListener) {
            super(looper, whenReadyListener);
        }

        /* access modifiers changed from: protected */
        public void a(WhenReadyListener whenReadyListener) {
            if (whenReadyListener != null) {
                try {
                    whenReadyListener.ready(MarketingCloudSdk.x);
                } catch (Exception e) {
                    g.b(MarketingCloudSdk.u, e, "Error occurred in %s", whenReadyListener.getClass().getName());
                }
            }
        }
    }

    static abstract class c {
        private final Handler a;
        WhenReadyListener b;
        volatile boolean c;
        private final Runnable d = new a();
        private volatile boolean e;

        class a implements Runnable {
            a() {
            }

            public void run() {
                synchronized (c.this) {
                    try {
                        if (!c.this.c) {
                            c cVar = c.this;
                            cVar.a(cVar.b);
                            c.this.c = true;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        c(Looper looper, WhenReadyListener whenReadyListener) {
            looper = looper == null ? Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper() : looper;
            this.b = whenReadyListener;
            this.a = new Handler(looper);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r2 = this;
                monitor-enter(r2)
                boolean r0 = r2.c     // Catch:{ all -> 0x0016 }
                if (r0 != 0) goto L_0x0018
                boolean r0 = r2.e     // Catch:{ all -> 0x0016 }
                if (r0 == 0) goto L_0x000a
                goto L_0x0018
            L_0x000a:
                r0 = 1
                r2.e = r0     // Catch:{ all -> 0x0016 }
                android.os.Handler r0 = r2.a     // Catch:{ all -> 0x0016 }
                java.lang.Runnable r1 = r2.d     // Catch:{ all -> 0x0016 }
                r0.post(r1)     // Catch:{ all -> 0x0016 }
                monitor-exit(r2)     // Catch:{ all -> 0x0016 }
                return
            L_0x0016:
                r0 = move-exception
                goto L_0x001a
            L_0x0018:
                monitor-exit(r2)     // Catch:{ all -> 0x0016 }
                return
            L_0x001a:
                monitor-exit(r2)     // Catch:{ all -> 0x0016 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MarketingCloudSdk.c.a():void");
        }

        /* access modifiers changed from: protected */
        public abstract void a(WhenReadyListener whenReadyListener);
    }

    private MarketingCloudSdk(MarketingCloudConfig marketingCloudConfig, SFMCSdkComponents sFMCSdkComponents) {
        this.a = marketingCloudConfig;
        this.c = sFMCSdkComponents;
    }

    static void c() {
        MarketingCloudSdk marketingCloudSdk = x;
        if (marketingCloudSdk != null) {
            marketingCloudSdk.a(false);
        }
        x = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x003f, code lost:
        return r2;
     */
    @com.salesforce.marketingcloud.MCKeep
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.salesforce.marketingcloud.MarketingCloudSdk getInstance() {
        /*
            boolean r0 = z
            if (r0 != 0) goto L_0x0011
            boolean r0 = A
            if (r0 == 0) goto L_0x0009
            goto L_0x0011
        L_0x0009:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "MarketingCloudSdk#init must be called before calling MarketingCloudSdk#getInstance."
            r0.<init>(r1)
            throw r0
        L_0x0011:
            java.lang.Object r0 = v
            monitor-enter(r0)
            boolean r1 = A     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x001e
            com.salesforce.marketingcloud.MarketingCloudSdk r1 = x     // Catch:{ all -> 0x001c }
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            return r1
        L_0x001c:
            r1 = move-exception
            goto L_0x004a
        L_0x001e:
            r1 = 0
        L_0x001f:
            boolean r2 = A     // Catch:{ all -> 0x002f }
            if (r2 != 0) goto L_0x0033
            boolean r2 = z     // Catch:{ all -> 0x002f }
            if (r2 == 0) goto L_0x0033
            java.lang.Object r2 = v     // Catch:{ InterruptedException -> 0x0031 }
            r3 = 0
            r2.wait(r3)     // Catch:{ InterruptedException -> 0x0031 }
            goto L_0x001f
        L_0x002f:
            r2 = move-exception
            goto L_0x0040
        L_0x0031:
            r1 = 1
            goto L_0x001f
        L_0x0033:
            com.salesforce.marketingcloud.MarketingCloudSdk r2 = x     // Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x003e
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x001c }
            r1.interrupt()     // Catch:{ all -> 0x001c }
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            return r2
        L_0x0040:
            if (r1 == 0) goto L_0x0049
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x001c }
            r1.interrupt()     // Catch:{ all -> 0x001c }
        L_0x0049:
            throw r2     // Catch:{ all -> 0x001c }
        L_0x004a:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MarketingCloudSdk.getInstance():com.salesforce.marketingcloud.MarketingCloudSdk");
    }

    @MCLogListener.LogLevel
    @MCKeep
    public static int getLogLevel() {
        return com.salesforce.marketingcloud.internal.d.a();
    }

    @MCKeep
    public static int getSdkVersionCode() {
        return a.d;
    }

    @MCKeep
    public static String getSdkVersionName() {
        return a.e;
    }

    @MCKeep
    public static void init(Context context, MarketingCloudConfig marketingCloudConfig, InitializationListener initializationListener) {
        b(context, marketingCloudConfig, (SFMCSdkComponents) null, initializationListener);
    }

    @MCKeep
    public static boolean isInitializing() {
        return z;
    }

    @MCKeep
    public static boolean isReady() {
        return A && x != null;
    }

    @MCKeep
    public static void requestSdk(Looper looper, WhenReadyListener whenReadyListener) {
        b bVar = new b(looper, whenReadyListener);
        List<c> list = w;
        synchronized (list) {
            try {
                if (B) {
                    list.add(bVar);
                } else {
                    bVar.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @MCKeep
    public static void setLogLevel(@MCLogListener.LogLevel int i2) {
        com.salesforce.marketingcloud.internal.d.a(i2);
    }

    @MCKeep
    public static void setLogListener(MCLogListener mCLogListener) {
        com.salesforce.marketingcloud.internal.d.a(mCLogListener);
    }

    @MCKeep
    public static void unregisterWhenReadyListener(WhenReadyListener whenReadyListener) {
        if (whenReadyListener != null) {
            List<c> list = w;
            synchronized (list) {
                try {
                    Iterator<c> it = list.iterator();
                    while (it.hasNext()) {
                        if (whenReadyListener == it.next().b) {
                            it.remove();
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void a(int i2) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            try {
                d dVar = this.b.get(size);
                if (dVar instanceof e) {
                    ((e) dVar).controlChannelInit(i2);
                }
            } catch (Exception e2) {
                g.b(u, e2, "Error encountered during control channel init.", new Object[0]);
            }
        }
    }

    public j b() {
        return this.g;
    }

    @MCKeep
    public AnalyticsManager getAnalyticsManager() {
        return this.o;
    }

    @MCKeep
    public EventManager getEventManager() {
        return this.f15n;
    }

    @MCKeep
    public InAppMessageManager getInAppMessageManager() {
        return this.q;
    }

    @MCKeep
    public InboxMessageManager getInboxMessageManager() {
        return this.i;
    }

    @MCKeep
    public InitializationStatus getInitializationStatus() {
        return this.p;
    }

    @MCKeep
    public MarketingCloudConfig getMarketingCloudConfig() {
        return this.a;
    }

    public ModuleIdentity getModuleIdentity() {
        return i.a(this.a.applicationId(), getRegistrationManager());
    }

    @MCKeep
    public NotificationManager getNotificationManager() {
        return this.k;
    }

    @MCKeep
    public PushMessageManager getPushMessageManager() {
        return this.l;
    }

    @MCKeep
    public RegionMessageManager getRegionMessageManager() {
        return this.m;
    }

    @MCKeep
    public RegistrationManager getRegistrationManager() {
        return this.j;
    }

    @MCKeep
    public JSONObject getSdkState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("initConfig", this.a.toString());
            jSONObject.put("initStatus", this.p.toString());
            for (d next : this.b) {
                if (next != null) {
                    try {
                        jSONObject.put(next.componentName(), next.componentState());
                    } catch (Exception e2) {
                        g.b(u, e2, "Failed to create component state for %s", next);
                    }
                }
            }
        } catch (Exception e3) {
            g.b(u, e3, "Unable to create Sdk state json", new Object[0]);
        }
        return jSONObject;
    }

    public JSONObject getState() {
        return getSdkState();
    }

    static void a(Context context, MarketingCloudConfig marketingCloudConfig, SFMCSdkComponents sFMCSdkComponents, InitializationListener initializationListener) {
        String str = u;
        g.d(str, "executeInit %s", marketingCloudConfig);
        synchronized (v) {
            try {
                MarketingCloudSdk marketingCloudSdk = x;
                if (marketingCloudSdk != null) {
                    marketingCloudSdk.b(e.a(marketingCloudConfig, marketingCloudSdk.a));
                }
                x = new MarketingCloudSdk(marketingCloudConfig, sFMCSdkComponents);
                InitializationStatus a2 = x.a(sFMCSdkComponents != null ? sFMCSdkComponents.getRegistrationId() : null);
                g.a(str, "MarketingCloudSdk init finished with status: %s", a2);
                A = a2.isUsable();
                z = false;
                if (A) {
                    x.a(a2);
                    MarketingCloudSdk marketingCloudSdk2 = x;
                    marketingCloudSdk2.f.a((b.C0008b) marketingCloudSdk2);
                    List<c> list = w;
                    synchronized (list) {
                        B = false;
                        g.d(str, "Delivering queued SDK requests to %s listeners", Integer.valueOf(list.size()));
                        if (!list.isEmpty()) {
                            for (c a3 : list) {
                                a3.a();
                            }
                            w.clear();
                        }
                    }
                } else {
                    x.a(false);
                    x = null;
                    List<c> list2 = w;
                    synchronized (list2) {
                        list2.clear();
                    }
                }
                v.notifyAll();
                if (initializationListener != null) {
                    initializationListener.complete(a2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0064, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void b(android.content.Context r5, com.salesforce.marketingcloud.MarketingCloudConfig r6, com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents r7, com.salesforce.marketingcloud.MarketingCloudSdk.InitializationListener r8) {
        /*
            java.lang.String r0 = u
            java.lang.String r1 = getSdkVersionName()
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "~~ MarketingCloudSdk v%s init() ~~"
            com.salesforce.marketingcloud.g.d((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object[]) r1)
            java.lang.String r1 = "Context cannot be null."
            com.salesforce.marketingcloud.util.j.a(r5, (java.lang.String) r1)
            java.lang.String r1 = "Config cannot be null."
            com.salesforce.marketingcloud.util.j.a(r6, (java.lang.String) r1)
            java.lang.String r1 = r6.applicationId()
            java.lang.String r2 = r6.accessToken()
            java.lang.String r3 = r6.senderId()
            com.salesforce.marketingcloud.internal.d.a(r1, r2, r3)
            java.lang.Object r1 = v
            monitor-enter(r1)
            boolean r2 = A     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0036
            boolean r2 = z     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x0065
            goto L_0x0036
        L_0x0034:
            r5 = move-exception
            goto L_0x0089
        L_0x0036:
            com.salesforce.marketingcloud.MarketingCloudSdk r2 = x     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x0065
            com.salesforce.marketingcloud.MarketingCloudConfig r2 = r2.a     // Catch:{ all -> 0x0034 }
            boolean r2 = r6.equals(r2)     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x0065
            java.lang.String r5 = "MarketingCloudSdk is already %s"
            boolean r6 = A     // Catch:{ all -> 0x0034 }
            if (r6 == 0) goto L_0x004b
            java.lang.String r6 = "initialized"
            goto L_0x004d
        L_0x004b:
            java.lang.String r6 = "initializing"
        L_0x004d:
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ all -> 0x0034 }
            com.salesforce.marketingcloud.g.d((java.lang.String) r0, (java.lang.String) r5, (java.lang.Object[]) r6)     // Catch:{ all -> 0x0034 }
            boolean r5 = isReady()     // Catch:{ all -> 0x0034 }
            if (r5 == 0) goto L_0x0063
            if (r8 == 0) goto L_0x0063
            com.salesforce.marketingcloud.MarketingCloudSdk r5 = x     // Catch:{ all -> 0x0034 }
            com.salesforce.marketingcloud.InitializationStatus r5 = r5.p     // Catch:{ all -> 0x0034 }
            r8.complete(r5)     // Catch:{ all -> 0x0034 }
        L_0x0063:
            monitor-exit(r1)     // Catch:{ all -> 0x0034 }
            return
        L_0x0065:
            java.lang.String r2 = "Starting initialization"
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0034 }
            com.salesforce.marketingcloud.g.d((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object[]) r4)     // Catch:{ all -> 0x0034 }
            A = r3     // Catch:{ all -> 0x0034 }
            r0 = 1
            z = r0     // Catch:{ all -> 0x0034 }
            B = r0     // Catch:{ all -> 0x0034 }
            android.content.Context r0 = r5.getApplicationContext()     // Catch:{ all -> 0x0034 }
            y = r0     // Catch:{ all -> 0x0034 }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ all -> 0x0034 }
            com.salesforce.marketingcloud.MarketingCloudSdk$a r2 = new com.salesforce.marketingcloud.MarketingCloudSdk$a     // Catch:{ all -> 0x0034 }
            r2.<init>(r5, r6, r7, r8)     // Catch:{ all -> 0x0034 }
            r0.<init>(r2)     // Catch:{ all -> 0x0034 }
            r0.start()     // Catch:{ all -> 0x0034 }
            monitor-exit(r1)     // Catch:{ all -> 0x0034 }
            return
        L_0x0089:
            monitor-exit(r1)     // Catch:{ all -> 0x0034 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MarketingCloudSdk.b(android.content.Context, com.salesforce.marketingcloud.MarketingCloudConfig, com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents, com.salesforce.marketingcloud.MarketingCloudSdk$InitializationListener):void");
    }

    @MCKeep
    public static void requestSdk(WhenReadyListener whenReadyListener) {
        requestSdk((Looper) null, whenReadyListener);
    }

    private void b(boolean z2) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            try {
                this.b.get(size).tearDown(z2);
            } catch (Exception e2) {
                g.b(u, e2, "Error encountered tearing down component.", new Object[0]);
            }
        }
        this.b.clear();
        l lVar = this.r;
        if (lVar != null) {
            lVar.c();
        }
        j jVar = this.g;
        if (jVar != null) {
            try {
                jVar.x();
            } catch (Exception e3) {
                g.b(u, e3, "Error encountered tearing down storage.", new Object[0]);
            }
            this.g = null;
        }
        List<c> list = w;
        synchronized (list) {
            list.clear();
        }
        A = false;
        B = true;
    }

    public com.salesforce.marketingcloud.http.c a() {
        return this.h;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: com.salesforce.marketingcloud.messages.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: com.salesforce.marketingcloud.InitializationStatus$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v44, resolved type: com.salesforce.marketingcloud.InitializationStatus$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v46, resolved type: com.salesforce.marketingcloud.InitializationStatus$a} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.salesforce.marketingcloud.InitializationStatus a(java.lang.String r31) {
        /*
            r30 = this;
            r1 = r30
            r2 = r31
            boolean r0 = com.salesforce.marketingcloud.util.b.a()
            if (r0 == 0) goto L_0x000f
            com.salesforce.marketingcloud.InitializationStatus r0 = com.salesforce.marketingcloud.internal.c.a()
            return r0
        L_0x000f:
            com.salesforce.marketingcloud.InitializationStatus$a r3 = com.salesforce.marketingcloud.internal.c.b()
            r4 = 0
            android.content.Context r0 = y     // Catch:{ Exception -> 0x006f }
            java.lang.String r15 = com.salesforce.marketingcloud.util.e.a((android.content.Context) r0, (java.lang.String) r2)     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.internal.l r0 = new com.salesforce.marketingcloud.internal.l     // Catch:{ all -> 0x0050 }
            r0.<init>()     // Catch:{ all -> 0x0050 }
            r1.r = r0     // Catch:{ all -> 0x0050 }
            com.salesforce.marketingcloud.util.a r7 = new com.salesforce.marketingcloud.util.a     // Catch:{ all -> 0x0050 }
            android.content.Context r0 = y     // Catch:{ all -> 0x0050 }
            com.salesforce.marketingcloud.MarketingCloudConfig r5 = r1.a     // Catch:{ all -> 0x0050 }
            java.lang.String r5 = r5.applicationId()     // Catch:{ all -> 0x0050 }
            com.salesforce.marketingcloud.MarketingCloudConfig r6 = r1.a     // Catch:{ all -> 0x0050 }
            java.lang.String r6 = r6.accessToken()     // Catch:{ all -> 0x0050 }
            r7.<init>(r0, r5, r6, r15)     // Catch:{ all -> 0x0050 }
            com.salesforce.marketingcloud.storage.j r0 = new com.salesforce.marketingcloud.storage.j     // Catch:{ all -> 0x0050 }
            android.content.Context r6 = y     // Catch:{ all -> 0x0050 }
            com.salesforce.marketingcloud.MarketingCloudConfig r5 = r1.a     // Catch:{ all -> 0x0050 }
            java.lang.String r8 = r5.applicationId()     // Catch:{ all -> 0x0050 }
            com.salesforce.marketingcloud.MarketingCloudConfig r5 = r1.a     // Catch:{ all -> 0x0050 }
            java.lang.String r9 = r5.accessToken()     // Catch:{ all -> 0x0050 }
            com.salesforce.marketingcloud.internal.l r10 = r1.r     // Catch:{ all -> 0x0050 }
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0050 }
            r1.g = r0     // Catch:{ all -> 0x0050 }
            r0.a((com.salesforce.marketingcloud.InitializationStatus.a) r3)     // Catch:{ all -> 0x0050 }
            goto L_0x005d
        L_0x0050:
            r0 = move-exception
            java.lang.String r5 = u     // Catch:{ Exception -> 0x006f }
            java.lang.String r6 = "Unable to initialize SDK storage."
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.g.a((java.lang.String) r5, (java.lang.Throwable) r0, (java.lang.String) r6, (java.lang.Object[]) r7)     // Catch:{ Exception -> 0x006f }
            r3.a((java.lang.Throwable) r0)     // Catch:{ Exception -> 0x006f }
        L_0x005d:
            boolean r0 = r3.b()     // Catch:{ Exception -> 0x006f }
            if (r0 != 0) goto L_0x0073
            com.salesforce.marketingcloud.MarketingCloudConfig r0 = r1.a     // Catch:{ Exception -> 0x006f }
            android.content.Context r5 = y     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.registration.d.a(r0, r5, r15, r2)     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.InitializationStatus r0 = r3.a()     // Catch:{ Exception -> 0x006f }
            return r0
        L_0x006f:
            r0 = move-exception
            r5 = r3
            goto L_0x02c3
        L_0x0073:
            com.salesforce.marketingcloud.behaviors.c r0 = new com.salesforce.marketingcloud.behaviors.c     // Catch:{ Exception -> 0x006f }
            android.content.Context r2 = y     // Catch:{ Exception -> 0x006f }
            java.util.concurrent.ExecutorService r5 = java.util.concurrent.Executors.newSingleThreadExecutor()     // Catch:{ Exception -> 0x006f }
            r0.<init>(r2, r5)     // Catch:{ Exception -> 0x006f }
            r1.e = r0     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.http.c r0 = new com.salesforce.marketingcloud.http.c     // Catch:{ Exception -> 0x006f }
            android.content.Context r2 = y     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.j r5 = r1.g     // Catch:{ Exception -> 0x006f }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.internal.l r6 = r1.r     // Catch:{ Exception -> 0x006f }
            r0.<init>(r2, r5, r6)     // Catch:{ Exception -> 0x006f }
            r1.h = r0     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.alarms.b r0 = new com.salesforce.marketingcloud.alarms.b     // Catch:{ Exception -> 0x006f }
            android.content.Context r2 = y     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.j r5 = r1.g     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.behaviors.c r6 = r1.e     // Catch:{ Exception -> 0x006f }
            r0.<init>(r2, r5, r6)     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.analytics.h r2 = new com.salesforce.marketingcloud.analytics.h     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.MarketingCloudConfig r6 = r1.a     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.j r7 = r1.g     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.behaviors.c r10 = r1.e     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.http.c r11 = r1.h     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.internal.l r12 = r1.r     // Catch:{ Exception -> 0x006f }
            r5 = r2
            r8 = r15
            r9 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x006f }
            r1.o = r2     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.k r14 = new com.salesforce.marketingcloud.k     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.MarketingCloudConfig r7 = r1.a     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.j r8 = r1.g     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.http.c r9 = r1.h     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.behaviors.c r10 = r1.e     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.internal.l r12 = r1.r     // Catch:{ Exception -> 0x006f }
            r5 = r14
            r6 = r15
            r11 = r0
            r13 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.b r5 = new com.salesforce.marketingcloud.b     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.j r6 = r1.g     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.f r6 = r6.o()     // Catch:{ Exception -> 0x006f }
            r5.<init>(r14, r6)     // Catch:{ Exception -> 0x006f }
            r1.f = r5     // Catch:{ Exception -> 0x006f }
            android.content.Context r5 = y     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.MarketingCloudConfig r6 = r1.a     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.location.f r5 = com.salesforce.marketingcloud.location.f.a((android.content.Context) r5, (com.salesforce.marketingcloud.MarketingCloudConfig) r6)     // Catch:{ Exception -> 0x006f }
            r1.d = r5     // Catch:{ Exception -> 0x006f }
            android.content.Context r5 = y     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.MarketingCloudConfig r6 = r1.a     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.proximity.e r13 = com.salesforce.marketingcloud.proximity.e.a((android.content.Context) r5, (com.salesforce.marketingcloud.MarketingCloudConfig) r6)     // Catch:{ Exception -> 0x006f }
            android.content.Context r5 = y     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.j r6 = r1.g     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.MarketingCloudConfig r7 = r1.a     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions r7 = r7.notificationCustomizationOptions()     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.notifications.a r5 = com.salesforce.marketingcloud.notifications.a.a((android.content.Context) r5, (com.salesforce.marketingcloud.storage.j) r6, (com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions) r7, (com.salesforce.marketingcloud.analytics.j) r2)     // Catch:{ Exception -> 0x006f }
            r1.k = r5     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.messages.inbox.a r12 = new com.salesforce.marketingcloud.messages.inbox.a     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.MarketingCloudConfig r6 = r1.a     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.j r7 = r1.g     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.behaviors.c r9 = r1.e     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.http.c r11 = r1.h     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.internal.l r10 = r1.r     // Catch:{ Exception -> 0x006f }
            r5 = r12
            r8 = r15
            r16 = r10
            r10 = r0
            r4 = r12
            r12 = r16
            r31 = r13
            r13 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x006f }
            r1.i = r4     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.messages.d r4 = new com.salesforce.marketingcloud.messages.d     // Catch:{ Exception -> 0x006f }
            android.content.Context r6 = y     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.MarketingCloudConfig r7 = r1.a     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.storage.j r8 = r1.g     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.location.f r10 = r1.d     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.behaviors.c r12 = r1.e     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.http.c r13 = r1.h     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.notifications.a r11 = r1.k     // Catch:{ Exception -> 0x006f }
            com.salesforce.marketingcloud.internal.l r9 = r1.r     // Catch:{ Exception -> 0x006f }
            r5 = r4
            r16 = r9
            r9 = r15
            r17 = r11
            r11 = r31
            r18 = r13
            r13 = r0
            r28 = r3
            r3 = r14
            r14 = r18
            r29 = r3
            r3 = r15
            r15 = r17
            r17 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ Exception -> 0x02aa }
            r1.m = r4     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.messages.push.a r4 = new com.salesforce.marketingcloud.messages.push.a     // Catch:{ Exception -> 0x02aa }
            android.content.Context r8 = y     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.storage.j r9 = r1.g     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.notifications.a r10 = r1.k     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.MarketingCloudConfig r5 = r1.a     // Catch:{ Exception -> 0x02aa }
            java.lang.String r12 = r5.senderId()     // Catch:{ Exception -> 0x02aa }
            r7 = r4
            r11 = r0
            r7.<init>(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x02aa }
            r1.l = r4     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.registration.f r4 = new com.salesforce.marketingcloud.registration.f     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.MarketingCloudConfig r5 = r1.a     // Catch:{ Exception -> 0x02aa }
            java.lang.String r5 = r5.applicationId()     // Catch:{ Exception -> 0x02aa }
            android.content.Context r6 = y     // Catch:{ Exception -> 0x02aa }
            java.lang.String r6 = com.salesforce.marketingcloud.util.h.a(r6)     // Catch:{ Exception -> 0x02aa }
            r4.<init>(r3, r5, r6)     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.registration.d r3 = new com.salesforce.marketingcloud.registration.d     // Catch:{ Exception -> 0x02aa }
            android.content.Context r17 = y     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.MarketingCloudConfig r5 = r1.a     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.storage.j r6 = r1.g     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.behaviors.c r7 = r1.e     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.http.c r8 = r1.h     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.messages.push.a r9 = r1.l     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.internal.l r10 = r1.r     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents r11 = r1.c     // Catch:{ Exception -> 0x02aa }
            r16 = r3
            r18 = r5
            r19 = r6
            r20 = r4
            r21 = r7
            r22 = r0
            r23 = r8
            r24 = r9
            r25 = r10
            r26 = r11
            r16.<init>((android.content.Context) r17, (com.salesforce.marketingcloud.MarketingCloudConfig) r18, (com.salesforce.marketingcloud.storage.j) r19, (com.salesforce.marketingcloud.registration.f) r20, (com.salesforce.marketingcloud.behaviors.c) r21, (com.salesforce.marketingcloud.alarms.b) r22, (com.salesforce.marketingcloud.http.c) r23, (com.salesforce.marketingcloud.messages.push.PushMessageManager) r24, (com.salesforce.marketingcloud.internal.l) r25, (com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents) r26)     // Catch:{ Exception -> 0x02aa }
            r1.j = r3     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.config.a r3 = new com.salesforce.marketingcloud.config.a     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.storage.j r5 = r1.g     // Catch:{ Exception -> 0x02aa }
            r6 = r29
            r3.<init>(r6, r5, r2)     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.messages.iam.InAppMessageComponent r5 = new com.salesforce.marketingcloud.messages.iam.InAppMessageComponent     // Catch:{ Exception -> 0x02aa }
            android.content.Context r7 = y     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.storage.j r8 = r1.g     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.behaviors.c r9 = r1.e     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.media.o r22 = com.salesforce.marketingcloud.media.o.a((android.content.Context) r7, (com.salesforce.marketingcloud.storage.j) r8)     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.MarketingCloudConfig r10 = r1.a     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.UrlHandler r23 = r10.urlHandler()     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.internal.l r10 = r1.r     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents r11 = r1.c     // Catch:{ Exception -> 0x02aa }
            r16 = r5
            r17 = r7
            r18 = r8
            r19 = r0
            r20 = r6
            r21 = r9
            r24 = r10
            r25 = r2
            r26 = r11
            r27 = r3
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)     // Catch:{ Exception -> 0x02aa }
            r1.q = r5     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.events.c r5 = new com.salesforce.marketingcloud.events.c     // Catch:{ Exception -> 0x02aa }
            android.content.Context r17 = y     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.storage.j r7 = r1.g     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.behaviors.c r8 = r1.e     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.internal.l r9 = r1.r     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents r10 = r1.c     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.messages.iam.InAppMessageComponent r11 = r1.q     // Catch:{ Exception -> 0x02aa }
            r16 = r5
            r18 = r4
            r19 = r7
            r20 = r6
            r21 = r8
            r22 = r2
            r23 = r9
            r24 = r10
            r25 = r3
            r26 = r11
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)     // Catch:{ Exception -> 0x02aa }
            r1.f15n = r5     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r4 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.behaviors.c r5 = r1.e     // Catch:{ Exception -> 0x02aa }
            r4.add(r5)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r4 = r1.b     // Catch:{ Exception -> 0x02aa }
            android.content.Context r5 = y     // Catch:{ Exception -> 0x02aa }
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ Exception -> 0x02aa }
            android.app.Application r5 = (android.app.Application) r5     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.behaviors.LifecycleManager r5 = com.salesforce.marketingcloud.behaviors.LifecycleManager.a((android.app.Application) r5)     // Catch:{ Exception -> 0x02aa }
            r4.add(r5)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r4 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.http.c r5 = r1.h     // Catch:{ Exception -> 0x02aa }
            r4.add(r5)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r4 = r1.b     // Catch:{ Exception -> 0x02aa }
            r4.add(r0)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            r0.add(r6)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.b r2 = r1.f     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.location.f r2 = r1.d     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            r2 = r31
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.messages.inbox.a r2 = r1.i     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.notifications.a r2 = r1.k     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.messages.d r2 = r1.m     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.messages.push.a r2 = r1.l     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.registration.d r2 = r1.j     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            r0.add(r3)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.messages.iam.InAppMessageComponent r2 = r1.q     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r0 = r1.b     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.events.c r2 = r1.f15n     // Catch:{ Exception -> 0x02aa }
            r0.add(r2)     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.b r0 = r1.f     // Catch:{ Exception -> 0x02aa }
            int r0 = r0.a()     // Catch:{ Exception -> 0x02aa }
            java.lang.String r2 = u     // Catch:{ Exception -> 0x02aa }
            java.lang.String r3 = "Initializing all components with control channel flag [%d]"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x02aa }
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.g.d((java.lang.String) r2, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ Exception -> 0x02aa }
            java.util.List<com.salesforce.marketingcloud.d> r2 = r1.b     // Catch:{ Exception -> 0x02aa }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x02aa }
        L_0x0280:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x02aa }
            if (r3 == 0) goto L_0x02c0
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.d r3 = (com.salesforce.marketingcloud.d) r3     // Catch:{ Exception -> 0x02aa }
            java.lang.String r4 = u     // Catch:{ Exception -> 0x02aa }
            java.lang.String r5 = "init called for %s"
            java.lang.String r6 = r3.componentName()     // Catch:{ Exception -> 0x02aa }
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ Exception -> 0x02aa }
            com.salesforce.marketingcloud.g.d((java.lang.String) r4, (java.lang.String) r5, (java.lang.Object[]) r6)     // Catch:{ Exception -> 0x02aa }
            boolean r4 = r3 instanceof com.salesforce.marketingcloud.e     // Catch:{ Exception -> 0x02aa }
            if (r4 == 0) goto L_0x02ae
            r4 = r3
            com.salesforce.marketingcloud.e r4 = (com.salesforce.marketingcloud.e) r4     // Catch:{ Exception -> 0x02aa }
            r5 = r28
            r4.init(r5, r0)     // Catch:{ Exception -> 0x02a8 }
            goto L_0x02ba
        L_0x02a8:
            r0 = move-exception
            goto L_0x02c3
        L_0x02aa:
            r0 = move-exception
            r5 = r28
            goto L_0x02c3
        L_0x02ae:
            r5 = r28
            boolean r4 = r3 instanceof com.salesforce.marketingcloud.f     // Catch:{ Exception -> 0x02a8 }
            if (r4 == 0) goto L_0x02ba
            r4 = r3
            com.salesforce.marketingcloud.f r4 = (com.salesforce.marketingcloud.f) r4     // Catch:{ Exception -> 0x02a8 }
            r4.a(r5)     // Catch:{ Exception -> 0x02a8 }
        L_0x02ba:
            r5.a((com.salesforce.marketingcloud.d) r3)     // Catch:{ Exception -> 0x02a8 }
            r28 = r5
            goto L_0x0280
        L_0x02c0:
            r5 = r28
            goto L_0x02d0
        L_0x02c3:
            r5.a((java.lang.Throwable) r0)
            java.lang.String r2 = u
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = "Something wrong with internal init"
            com.salesforce.marketingcloud.g.b(r2, r0, r4, r3)
        L_0x02d0:
            com.salesforce.marketingcloud.InitializationStatus r0 = r5.a()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MarketingCloudSdk.a(java.lang.String):com.salesforce.marketingcloud.InitializationStatus");
    }

    private void a(boolean z2) {
        b(z2);
        z = false;
    }

    private void a(InitializationStatus initializationStatus) {
        this.p = initializationStatus;
    }
}
