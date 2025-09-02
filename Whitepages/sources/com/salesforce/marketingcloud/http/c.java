package com.salesforce.marketingcloud.http;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import androidx.collection.ArrayMap;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.security.ProviderInstaller;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.f;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.util.j;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class c extends f {
    public static final String j = "com.salesforce.marketingcloud.http.RESPONSE";
    public static final String k = "http_response";
    public static final String l = "http_request";
    private static final int m = 10;

    /* renamed from: n  reason: collision with root package name */
    static final String f27n = g.a("RequestManager");
    final Map<a, C0017c> d = new ArrayMap();
    private final Map<String, String> e = new a();
    private final Context f;
    private final SharedPreferences g;
    private l h;
    private BroadcastReceiver i;

    class a extends LinkedHashMap<String, String> {
        a() {
        }

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Map.Entry<String, String> entry) {
            return size() > 10;
        }
    }

    class b extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ C0017c b;
        final /* synthetic */ b c;
        final /* synthetic */ d d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, C0017c cVar, b bVar, d dVar) {
            super(str, objArr);
            this.b = cVar;
            this.c = bVar;
            this.d = dVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.b.a(this.c, this.d);
        }
    }

    /* renamed from: com.salesforce.marketingcloud.http.c$c  reason: collision with other inner class name */
    public interface C0017c {
        void a(b bVar, d dVar);
    }

    class d extends BroadcastReceiver {
        d() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.d(c.f27n, "Received null intent", new Object[0]);
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                g.d(c.f27n, "Received null action", new Object[0]);
            } else if (!action.equals(c.j)) {
                g.a(c.f27n, "Received unknown action: %s", action);
            } else {
                b a2 = b.a(intent.getBundleExtra(c.l));
                d dVar = (d) intent.getParcelableExtra(c.k);
                if (a2 == null || dVar == null) {
                    g.d(c.f27n, "Received null request/response", new Object[0]);
                } else {
                    c.this.a(a2, dVar);
                }
            }
        }
    }

    public c(Context context, SharedPreferences sharedPreferences, l lVar) {
        this.f = (Context) j.a(context, "Context is null");
        this.g = (SharedPreferences) j.a(sharedPreferences, "SharedPreferences is null");
        this.h = lVar;
    }

    public void a(a aVar) {
        synchronized (this.d) {
            this.d.remove(aVar);
        }
    }

    public final String componentName() {
        return "RequestManager";
    }

    public final JSONObject componentState() {
        return new JSONObject(this.e);
    }

    public final void tearDown(boolean z) {
        synchronized (this.d) {
            this.d.clear();
        }
        Context context = this.f;
        if (context != null && this.i != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this.i);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(InitializationStatus.a aVar) {
        try {
            a();
        } catch (Exception e2) {
            aVar.e(true);
            aVar.a("Failed to install providers: " + e2.getMessage());
        }
        this.i = new d();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(j);
        LocalBroadcastManager.getInstance(this.f).registerReceiver(this.i, intentFilter);
    }

    private void a() throws Exception {
        ProviderInstaller.installIfNeeded(this.f);
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar, d dVar) {
        a p = bVar.p();
        g.d(f27n, "%s request took %dms with code: %d", p.name(), Long.valueOf(dVar.h()), Integer.valueOf(dVar.b()));
        p.a(this.g, dVar);
        try {
            this.e.put(bVar.r(), String.format(Locale.ENGLISH, "%s - %d", new Object[]{dVar.e(), Integer.valueOf(dVar.b())}));
        } catch (Exception e2) {
            g.b(f27n, e2, "Failed to record response.", new Object[0]);
        }
        synchronized (this.d) {
            C0017c cVar = this.d.get(p);
            if (cVar != null) {
                try {
                    this.h.a().execute(new b("onResponse", new Object[0], cVar, bVar, dVar));
                } catch (Exception e3) {
                    g.b(f27n, e3, "Failed to deliver response.", new Object[0]);
                }
            } else {
                g.e(f27n, "Request %s complete, but no listener was present to handle response %d.", bVar.r(), Integer.valueOf(dVar.b()));
            }
        }
    }

    public void a(a aVar, C0017c cVar) {
        synchronized (this.d) {
            try {
                if (this.d.put(aVar, cVar) != null) {
                    g.a(f27n, "%s replaces previous listener for $s requests", cVar.getClass().getName(), aVar.name());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:1|2|3|4|5|7|8|9|(1:14)(1:13)|15) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.salesforce.marketingcloud.http.b r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.String r0 = "request is null"
            com.salesforce.marketingcloud.util.j.a(r7, (java.lang.String) r0)     // Catch:{ all -> 0x000a }
            r6.a()     // Catch:{ Exception -> 0x000c }
            goto L_0x0016
        L_0x000a:
            r7 = move-exception
            goto L_0x0052
        L_0x000c:
            java.lang.String r0 = f27n     // Catch:{ all -> 0x000a }
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x000a }
            java.lang.String r2 = "Failed to verify SSL providers via Google Play Services."
            com.salesforce.marketingcloud.g.e((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object[]) r1)     // Catch:{ all -> 0x000a }
        L_0x0016:
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x000a }
            com.salesforce.marketingcloud.http.a r2 = r7.p()     // Catch:{ all -> 0x000a }
            android.content.SharedPreferences r3 = r6.g     // Catch:{ all -> 0x000a }
            long r2 = r2.c(r3)     // Catch:{ all -> 0x000a }
            com.salesforce.marketingcloud.http.a r4 = r7.p()     // Catch:{ all -> 0x000a }
            android.content.SharedPreferences r5 = r6.g     // Catch:{ all -> 0x000a }
            long r4 = r4.a((android.content.SharedPreferences) r5)     // Catch:{ all -> 0x000a }
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x0045
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0045
            com.salesforce.marketingcloud.http.a r0 = r7.p()     // Catch:{ all -> 0x000a }
            android.content.SharedPreferences r1 = r6.g     // Catch:{ all -> 0x000a }
            r0.b((android.content.SharedPreferences) r1)     // Catch:{ all -> 0x000a }
            android.content.Context r0 = r6.f     // Catch:{ all -> 0x000a }
            com.salesforce.marketingcloud.MCService.a((android.content.Context) r0, (com.salesforce.marketingcloud.http.b) r7)     // Catch:{ all -> 0x000a }
            goto L_0x0050
        L_0x0045:
            java.lang.String r0 = "Too Many Requests"
            r1 = 429(0x1ad, float:6.01E-43)
            com.salesforce.marketingcloud.http.d r0 = com.salesforce.marketingcloud.http.d.a(r0, r1)     // Catch:{ all -> 0x000a }
            r6.a((com.salesforce.marketingcloud.http.b) r7, (com.salesforce.marketingcloud.http.d) r0)     // Catch:{ all -> 0x000a }
        L_0x0050:
            monitor-exit(r6)
            return
        L_0x0052:
            monitor-exit(r6)     // Catch:{ all -> 0x000a }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.http.c.a(com.salesforce.marketingcloud.http.b):void");
    }
}
