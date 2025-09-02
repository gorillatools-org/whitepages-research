package com.salesforce.marketingcloud.config;

import android.content.SharedPreferences;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.analytics.m;
import com.salesforce.marketingcloud.config.b;
import com.salesforce.marketingcloud.extensions.PushExtensionsKt;
import com.salesforce.marketingcloud.k;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a extends com.salesforce.marketingcloud.f implements k.e {
    private static final String A = "invalidConfigurationValue";
    private static final String B = "event";
    private static final String C = "activeEvents";
    private static final String D = "enableEngagementEvents";
    private static final String E = "enableSystemEvents";
    private static final String F = "enableAppEvents";
    private static final String G = "enableIdentityEvents";
    private static final String H = "enableDebugInfo";
    private static final String I = "enableTelemetryInfo";
    private static final String J = "endpoints";
    private static final String K = "dataTypes";
    private static final int L = 1000;
    private static final String M = "version";
    /* access modifiers changed from: private */
    public static a N = null;
    public static final C0010a d = new C0010a((DefaultConstructorMarker) null);
    public static final String e = "correlationIds";
    public static final String f = "gateEventProcessingMs";
    public static final int g = 0;
    public static final String h = "eventName";
    public static final String i = "endpoint";
    public static final String j = "path";
    public static final String k = "maxBatchSize";
    /* access modifiers changed from: private */
    public static final EnumSet<k.d> l;
    /* access modifiers changed from: private */
    public static final Object m = new Object();

    /* renamed from: n  reason: collision with root package name */
    private static final String f23n = "~!ConfigComponent";
    private static final int o = 1;
    private static final boolean p = true;
    private static final boolean q = false;
    private static final boolean r = false;
    private static final boolean s = false;
    private static final boolean t = false;
    private static final boolean u = false;
    private static final String v = "items";
    private static final String w = "inApp";
    private static final String x = "maxDisplay";
    private static final String y = "timeBetweenDisplaySec";
    private static final String z = "invalidConfigurationKey";
    private final k O;
    private final com.salesforce.marketingcloud.storage.j P;
    private final m Q;
    private Map<String, b> R;
    private Boolean S;
    private Boolean T;
    private Boolean U;
    private Boolean V;
    private Boolean W;
    private Boolean X;
    private Map<String, String> Y;

    /* renamed from: com.salesforce.marketingcloud.config.a$a  reason: collision with other inner class name */
    public static final class C0010a {
        private C0010a() {
        }

        public static /* synthetic */ void b() {
        }

        public final a a() {
            return a.N;
        }

        public final Object c() {
            return a.m;
        }

        public final EnumSet<k.d> d() {
            return a.l;
        }

        public /* synthetic */ C0010a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(a aVar) {
            a.N = aVar;
        }
    }

    static final class b extends Lambda implements Function0 {
        public static final b a = new b();

        b() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Unable to generate complete SDK state output for component.";
        }
    }

    static final class c extends Lambda implements Function0 {
        public static final c a = new c();

        c() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Failed to parse [Endpoint Config] sync data.";
        }
    }

    static final class d extends Lambda implements Function0 {
        public static final d a = new d();

        d() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Failed to parse [Event Config] sync data";
        }
    }

    static final class e extends Lambda implements Function0 {
        public static final e a = new e();

        e() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Failed to parse [InApp Config] sync data";
        }
    }

    static final class f extends Lambda implements Function0 {
        final /* synthetic */ String a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        f(String str) {
            super(0);
            this.a = str;
        }

        /* renamed from: a */
        public final String invoke() {
            String str = this.a;
            return "Unknown endpoint '" + str + "' in config.";
        }
    }

    static final class g extends Lambda implements Function0 {
        public static final g a = new g();

        g() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Failed to parse endpoint from sync response.";
        }
    }

    static final class h extends Lambda implements Function0 {
        public static final h a = new h();

        h() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Unable to handle sync payload due to version mismatch";
        }
    }

    static final class i extends Lambda implements Function0 {
        public static final i a = new i();

        i() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Could not process [AppConfig Node] from Sync.";
        }
    }

    static final class j extends Lambda implements Function0 {
        final /* synthetic */ String a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        j(String str) {
            super(0);
            this.a = str;
        }

        /* renamed from: a */
        public final String invoke() {
            String str = this.a;
            return "Failed to log analytics for InvalidConfig [" + str + "]";
        }
    }

    static {
        EnumSet<k.d> of = EnumSet.of(k.d.appConfig);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        l = of;
    }

    public a(k kVar, com.salesforce.marketingcloud.storage.j jVar, m mVar) {
        Intrinsics.checkNotNullParameter(kVar, "syncRouteComponent");
        Intrinsics.checkNotNullParameter(jVar, "storage");
        Intrinsics.checkNotNullParameter(mVar, "triggerAnalytics");
        this.O = kVar;
        this.P = jVar;
        this.Q = mVar;
        N = this;
    }

    public static final a e() {
        return d.a();
    }

    private final Map<String, String> f() {
        return PushExtensionsKt.toMap(new JSONArray(this.P.f().getString(C, new JSONArray().toString())));
    }

    public String componentName() {
        return "ConfigComponent";
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        com.salesforce.marketingcloud.g.e(com.salesforce.marketingcloud.g.a, f23n, (java.lang.Throwable) null, com.salesforce.marketingcloud.config.a.b.a, 2, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0128, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0080, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0117 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject componentState() {
        /*
            r12 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.Object r1 = m
            monitor-enter(r1)
            java.lang.String r2 = "event"
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x0117 }
            r3.<init>()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "enableEngagementEvents"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "enableEngagementEvents"
            r7 = 1
            boolean r5 = r5.getBoolean(r6, r7)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "enableSystemEvents"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "enableSystemEvents"
            r7 = 0
            boolean r5 = r5.getBoolean(r6, r7)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "enableAppEvents"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "enableAppEvents"
            boolean r5 = r5.getBoolean(r6, r7)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "enableIdentityEvents"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "enableIdentityEvents"
            boolean r5 = r5.getBoolean(r6, r7)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "enableTelemetryInfo"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "enableTelemetryInfo"
            boolean r5 = r5.getBoolean(r6, r7)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "enableDebugInfo"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "enableDebugInfo"
            boolean r5 = r5.getBoolean(r6, r7)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            java.util.Map<java.lang.String, java.lang.String> r4 = r12.Y     // Catch:{ Exception -> 0x0117 }
            if (r4 != 0) goto L_0x0083
            java.util.Map r4 = kotlin.collections.MapsKt.emptyMap()     // Catch:{ Exception -> 0x0117 }
            goto L_0x0083
        L_0x0080:
            r0 = move-exception
            goto L_0x0127
        L_0x0083:
            java.lang.String r5 = "activeEvents"
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ Exception -> 0x0117 }
            r6.<init>()     // Catch:{ Exception -> 0x0117 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ Exception -> 0x0117 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x0117 }
        L_0x0092:
            boolean r8 = r4.hasNext()     // Catch:{ Exception -> 0x0117 }
            if (r8 == 0) goto L_0x00bd
            java.lang.Object r8 = r4.next()     // Catch:{ Exception -> 0x0117 }
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8     // Catch:{ Exception -> 0x0117 }
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ Exception -> 0x0117 }
            r9.<init>()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r10 = "eventName"
            java.lang.Object r11 = r8.getKey()     // Catch:{ Exception -> 0x0117 }
            r9.put(r10, r11)     // Catch:{ Exception -> 0x0117 }
            java.lang.Object r8 = r8.getValue()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x0117 }
            if (r8 == 0) goto L_0x00b9
            java.lang.String r10 = "correlationIds"
            r9.put(r10, r8)     // Catch:{ Exception -> 0x0117 }
        L_0x00b9:
            r6.put(r9)     // Catch:{ Exception -> 0x0117 }
            goto L_0x0092
        L_0x00bd:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0117 }
            r3.put(r5, r6)     // Catch:{ Exception -> 0x0117 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r2 = "inApp"
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x0117 }
            r3.<init>()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "gateEventProcessingMs"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "gateEventProcessingMs"
            int r5 = r5.getInt(r6, r7)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "maxDisplay"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "maxDisplay"
            r8 = 2147483647(0x7fffffff, float:NaN)
            int r5 = r5.getInt(r6, r8)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r4 = "timeBetweenDisplaySec"
            com.salesforce.marketingcloud.storage.j r5 = r12.P     // Catch:{ Exception -> 0x0117 }
            android.content.SharedPreferences r5 = r5.f()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "timeBetweenDisplaySec"
            int r5 = r5.getInt(r6, r7)     // Catch:{ Exception -> 0x0117 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0117 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x0117 }
            java.util.Map<java.lang.String, com.salesforce.marketingcloud.config.b> r2 = r12.R     // Catch:{ Exception -> 0x0117 }
            if (r2 != 0) goto L_0x010d
            java.util.Map r2 = kotlin.collections.MapsKt.emptyMap()     // Catch:{ Exception -> 0x0117 }
        L_0x010d:
            java.lang.String r3 = "endpoints"
            org.json.JSONArray r2 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.toJSONArray(r2)     // Catch:{ Exception -> 0x0117 }
            r0.put(r3, r2)     // Catch:{ Exception -> 0x0117 }
            goto L_0x0123
        L_0x0117:
            com.salesforce.marketingcloud.g r2 = com.salesforce.marketingcloud.g.a     // Catch:{ all -> 0x0080 }
            java.lang.String r3 = "~!ConfigComponent"
            com.salesforce.marketingcloud.config.a$b r5 = com.salesforce.marketingcloud.config.a.b.a     // Catch:{ all -> 0x0080 }
            r6 = 2
            r7 = 0
            r4 = 0
            com.salesforce.marketingcloud.g.e(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0080 }
        L_0x0123:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0080 }
            monitor-exit(r1)
            return r0
        L_0x0127:
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.config.a.componentState():org.json.JSONObject");
    }

    public final Map<String, String> d() {
        return this.Y;
    }

    public final boolean g() {
        boolean z2;
        synchronized (m) {
            try {
                Boolean bool = this.U;
                if (bool != null) {
                    z2 = bool.booleanValue();
                } else {
                    z2 = this.P.f().getBoolean(F, false);
                    this.U = Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public final boolean h() {
        boolean z2;
        synchronized (m) {
            try {
                Boolean bool = this.X;
                if (bool != null) {
                    z2 = bool.booleanValue();
                } else {
                    z2 = this.P.f().getBoolean(H, false);
                    this.X = Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public final boolean i() {
        boolean z2;
        synchronized (m) {
            try {
                Boolean bool = this.S;
                if (bool != null) {
                    z2 = bool.booleanValue();
                } else {
                    z2 = this.P.f().getBoolean(D, true);
                    this.S = Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public final boolean j() {
        boolean z2;
        synchronized (m) {
            try {
                Boolean bool = this.V;
                if (bool != null) {
                    z2 = bool.booleanValue();
                } else {
                    z2 = this.P.f().getBoolean(G, false);
                    this.V = Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public final boolean k() {
        boolean z2;
        synchronized (m) {
            try {
                Boolean bool = this.T;
                if (bool != null) {
                    z2 = bool.booleanValue();
                } else {
                    z2 = this.P.f().getBoolean(E, false);
                    this.T = Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public final boolean l() {
        boolean z2;
        synchronized (m) {
            try {
                Boolean bool = this.W;
                if (bool != null) {
                    z2 = bool.booleanValue();
                } else {
                    z2 = this.P.f().getBoolean(I, false);
                    this.W = Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public void onSyncReceived(k.d dVar, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(dVar, "node");
        Intrinsics.checkNotNullParameter(jSONObject, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (l.contains(dVar)) {
            if (jSONObject.optInt(M) != 1) {
                com.salesforce.marketingcloud.g.b(com.salesforce.marketingcloud.g.a, f23n, (Throwable) null, h.a, 2, (Object) null);
                return;
            }
            try {
                if (dVar == k.d.appConfig) {
                    c(jSONObject);
                }
            } catch (Throwable th) {
                com.salesforce.marketingcloud.g.a.b(f23n, th, (Function0) i.a);
            }
        }
    }

    public void tearDown(boolean z2) {
        this.O.a(l, (k.e) null);
        N = null;
    }

    private final void b(JSONObject jSONObject) {
        synchronized (m) {
            try {
                int optInt = jSONObject.optInt(f, 0);
                int optInt2 = jSONObject.optInt(x, Integer.MAX_VALUE);
                int optInt3 = jSONObject.optInt(y, 0);
                SharedPreferences.Editor edit = this.P.f().edit();
                Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
                if (optInt >= 0) {
                    edit.putInt(com.salesforce.marketingcloud.events.c.r, optInt);
                }
                if (optInt2 >= 0) {
                    edit.putInt(com.salesforce.marketingcloud.events.c.s, optInt2);
                }
                if (optInt3 >= 0) {
                    edit.putInt(com.salesforce.marketingcloud.events.c.t, optInt3);
                }
                edit.apply();
                if (optInt < 0) {
                    a(f, String.valueOf(optInt));
                }
                if (optInt2 < 0) {
                    a(x, String.valueOf(optInt2));
                }
                if (optInt3 < 0) {
                    a(y, String.valueOf(optInt3));
                }
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.a.b(f23n, (Throwable) e2, (Function0) e.a);
            } catch (Throwable th) {
                throw th;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void c(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        JSONObject optJSONObject = jSONObject.optJSONObject("items");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject(B);
        if (optJSONObject2 == null) {
            optJSONObject2 = new JSONObject();
        } else {
            Intrinsics.checkNotNull(optJSONObject2);
        }
        a(optJSONObject2);
        JSONObject optJSONObject3 = optJSONObject.optJSONObject(w);
        if (optJSONObject3 == null) {
            optJSONObject3 = new JSONObject();
        } else {
            Intrinsics.checkNotNull(optJSONObject3);
        }
        b(optJSONObject3);
        JSONArray optJSONArray = optJSONObject.optJSONArray(J);
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        } else {
            Intrinsics.checkNotNull(optJSONArray);
        }
        a(optJSONArray);
    }

    private final Map<String, b> b(JSONArray jSONArray) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (jSONArray.length() != 0) {
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    Object obj = jSONArray.get(i2);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                    JSONObject jSONObject = (JSONObject) obj;
                    JSONArray optJSONArray = jSONObject.optJSONArray(K);
                    if (optJSONArray != null) {
                        int length2 = optJSONArray.length();
                        for (int i3 = 0; i3 < length2; i3++) {
                            Object obj2 = optJSONArray.get(i3);
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                            String str = (String) obj2;
                            if (Intrinsics.areEqual((Object) str, (Object) "EVENTS")) {
                                b.a aVar = b.d;
                                String stringOrNull = PushExtensionsKt.getStringOrNull(jSONObject, j);
                                Integer intOrNull = PushExtensionsKt.getIntOrNull(jSONObject, k);
                                linkedHashMap.put(str, aVar.a(str, stringOrNull, Integer.valueOf(intOrNull != null ? intOrNull.intValue() : 1000)));
                            } else {
                                com.salesforce.marketingcloud.g.e(com.salesforce.marketingcloud.g.a, f23n, (Throwable) null, new f(str), 2, (Object) null);
                            }
                        }
                    }
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.a.e(f23n, (Throwable) e2, (Function0) g.a);
                }
            }
        }
        return linkedHashMap;
    }

    public final b a(com.salesforce.marketingcloud.storage.j jVar, String str) {
        b bVar;
        if (jVar == null || str == null || str.length() == 0) {
            return null;
        }
        synchronized (m) {
            try {
                Map<String, b> map = this.R;
                if (map != null) {
                    bVar = map.get(str);
                    if (bVar == null) {
                    }
                }
                Map<String, b> b2 = b(new JSONArray(jVar.f().getString(J, new JSONArray().toString())));
                this.R = b2;
                bVar = b2.get(str);
            } catch (Throwable th) {
                throw th;
            }
        }
        return bVar;
    }

    public static final void b(a aVar) {
        d.a(aVar);
    }

    public final String a(String str) {
        String str2;
        Intrinsics.checkNotNullParameter(str, h);
        synchronized (m) {
            try {
                Map<String, String> map = this.Y;
                if (map != null) {
                    String lowerCase = str.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    str2 = map.get(lowerCase);
                    if (str2 == null) {
                    }
                }
                Map<String, String> f2 = f();
                this.Y = f2;
                String lowerCase2 = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                str2 = f2.get(lowerCase2);
            } catch (Throwable th) {
                throw th;
            }
        }
        return str2;
    }

    private final void a(JSONArray jSONArray) {
        synchronized (m) {
            try {
                this.R = b(jSONArray);
                this.P.f().edit().putString(J, jSONArray.toString()).apply();
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.a.b(f23n, (Throwable) e2, (Function0) c.a);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean b(String str) {
        String lowerCase;
        String str2;
        boolean containsKey;
        Intrinsics.checkNotNullParameter(str, h);
        synchronized (m) {
            try {
                Map<String, String> map = this.Y;
                if (map != null) {
                    lowerCase = str.toLowerCase(Locale.ROOT);
                    str2 = "this as java.lang.String).toLowerCase(Locale.ROOT)";
                } else {
                    map = f();
                    this.Y = map;
                    lowerCase = str.toLowerCase(Locale.ROOT);
                    str2 = "this as java.lang.String).toLowerCase(Locale.ROOT)";
                }
                Intrinsics.checkNotNullExpressionValue(lowerCase, str2);
                containsKey = map.containsKey(lowerCase);
            } catch (Throwable th) {
                throw th;
            }
        }
        return containsKey;
    }

    private final void a(JSONObject jSONObject) {
        synchronized (m) {
            try {
                SharedPreferences.Editor edit = this.P.f().edit();
                Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
                boolean optBoolean = jSONObject.optBoolean(D, true);
                Boolean valueOf = Boolean.valueOf(optBoolean);
                edit.putBoolean(D, optBoolean);
                this.S = valueOf;
                boolean optBoolean2 = jSONObject.optBoolean(E, false);
                Boolean valueOf2 = Boolean.valueOf(optBoolean2);
                edit.putBoolean(E, optBoolean2);
                this.T = valueOf2;
                boolean optBoolean3 = jSONObject.optBoolean(F, false);
                Boolean valueOf3 = Boolean.valueOf(optBoolean3);
                edit.putBoolean(F, optBoolean3);
                this.U = valueOf3;
                boolean optBoolean4 = jSONObject.optBoolean(G, false);
                Boolean valueOf4 = Boolean.valueOf(optBoolean4);
                edit.putBoolean(G, optBoolean4);
                this.V = valueOf4;
                boolean optBoolean5 = jSONObject.optBoolean(H, false);
                Boolean valueOf5 = Boolean.valueOf(optBoolean5);
                edit.putBoolean(H, optBoolean5);
                this.X = valueOf5;
                boolean optBoolean6 = jSONObject.optBoolean(I, false);
                Boolean valueOf6 = Boolean.valueOf(optBoolean6);
                edit.putBoolean(I, optBoolean6);
                this.W = valueOf6;
                JSONArray optJSONArray = jSONObject.optJSONArray(C);
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                } else {
                    Intrinsics.checkNotNull(optJSONArray);
                }
                this.Y = PushExtensionsKt.toMap(optJSONArray);
                edit.putString(C, optJSONArray.toString());
                edit.apply();
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.a.b(f23n, (Throwable) e2, (Function0) d.a);
            } catch (Throwable th) {
                throw th;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* access modifiers changed from: protected */
    public void a(InitializationStatus.a aVar) {
        Intrinsics.checkNotNullParameter(aVar, "statusBuilder");
        this.O.a(l, (k.e) this);
    }

    private final void a(String str, String str2) {
        try {
            if (h()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(z, str);
                jSONObject.put(A, str2);
                this.Q.a(jSONObject);
            }
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.a.b(f23n, (Throwable) e2, (Function0) new j(str));
        }
    }
}
