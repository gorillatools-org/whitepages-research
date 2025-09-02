package com.salesforce.marketingcloud.http;

import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.m;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

public final class b {
    public static final C0016b i = new C0016b((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String j = g.a("Request");
    public static final String k = "GET";
    public static final String l = "POST";
    public static final String m = "PATCH";

    /* renamed from: n  reason: collision with root package name */
    public static final int f26n = -100;
    private static final int o = 30000;
    private final String a;
    private final String b;
    private final int c;
    private final String d;
    private final String e;
    private final List<String> f;
    private final a g;
    private String h;

    public static final class a {
        private String a;
        private String b;
        private int c = b.o;
        private String d;
        private String e;
        private a f;
        private Map<String, String> g = new LinkedHashMap();
        private List<String> h;

        public final a a(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "value");
            this.g.put(str, StringsKt.trim(str2).toString());
            return this;
        }

        public final a b(String str) {
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
            this.a = str;
            return this;
        }

        public final a c(String str) {
            Intrinsics.checkNotNullParameter(str, "requestBody");
            this.d = str;
            return this;
        }

        public final a d(String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            this.b = str;
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x004a  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0054  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x007b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.salesforce.marketingcloud.http.b a() {
            /*
                r9 = this;
                java.util.List<java.lang.String> r0 = r9.h
                if (r0 != 0) goto L_0x0045
                java.util.Map<java.lang.String, java.lang.String> r0 = r9.g
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x0041
                java.util.Map<java.lang.String, java.lang.String> r0 = r9.g
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Set r0 = r0.entrySet()
                java.util.Iterator r0 = r0.iterator()
            L_0x001b:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x003f
                java.lang.Object r2 = r0.next()
                java.util.Map$Entry r2 = (java.util.Map.Entry) r2
                java.lang.Object r3 = r2.getKey()
                java.lang.String r3 = (java.lang.String) r3
                java.lang.Object r2 = r2.getValue()
                java.lang.String r2 = (java.lang.String) r2
                java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
                java.util.List r2 = kotlin.collections.CollectionsKt.plus((java.util.Collection) r3, (java.lang.Object) r2)
                kotlin.collections.CollectionsKt.addAll((java.util.Collection) r1, (java.lang.Iterable) r2)
                goto L_0x001b
            L_0x003f:
                r7 = r1
                goto L_0x0046
            L_0x0041:
                java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            L_0x0045:
                r7 = r0
            L_0x0046:
                java.lang.String r3 = r9.d
                if (r3 != 0) goto L_0x004e
                java.lang.String r0 = ""
                r9.e = r0
            L_0x004e:
                java.lang.String r2 = r9.a
                java.lang.String r0 = "Required value was null."
                if (r2 == 0) goto L_0x007b
                java.lang.String r6 = r9.b
                if (r6 == 0) goto L_0x0075
                int r4 = r9.c
                java.lang.String r5 = r9.e
                if (r5 == 0) goto L_0x006f
                com.salesforce.marketingcloud.http.a r8 = r9.f
                if (r8 == 0) goto L_0x0069
                com.salesforce.marketingcloud.http.b r0 = new com.salesforce.marketingcloud.http.b
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
                return r0
            L_0x0069:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                r1.<init>(r0)
                throw r1
            L_0x006f:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                r1.<init>(r0)
                throw r1
            L_0x0075:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                r1.<init>(r0)
                throw r1
            L_0x007b:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.http.b.a.a():com.salesforce.marketingcloud.http.b");
        }

        public final a a(int i) {
            this.c = i;
            return this;
        }

        public final a a(String str) {
            Intrinsics.checkNotNullParameter(str, "contentType");
            this.e = str;
            return this;
        }

        public final void a(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "headers");
            this.h = list;
        }

        public final a a(a aVar) {
            Intrinsics.checkNotNullParameter(aVar, "requestId");
            this.f = aVar;
            return this;
        }
    }

    /* renamed from: com.salesforce.marketingcloud.http.b$b  reason: collision with other inner class name */
    public static final class C0016b {
        private C0016b() {
        }

        public final a a() {
            return new a();
        }

        public final String b() {
            return b.j;
        }

        public /* synthetic */ C0016b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final b a(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
            a a = b.i.a();
            String string = bundle.getString(FirebaseAnalytics.Param.METHOD);
            if (string != null) {
                Intrinsics.checkNotNull(string);
                a.b(string);
            }
            String string2 = bundle.getString("requestBody");
            if (string2 != null) {
                Intrinsics.checkNotNull(string2);
                a.c(string2);
            }
            a.a(bundle.getInt("connectionTimeout"));
            String string3 = bundle.getString("contentType");
            if (string3 != null) {
                Intrinsics.checkNotNull(string3);
                a.a(string3);
            }
            String string4 = bundle.getString("url");
            if (string4 != null) {
                Intrinsics.checkNotNull(string4);
                a.d(string4);
            }
            ArrayList<String> stringArrayList = bundle.getStringArrayList("headers");
            if (stringArrayList != null) {
                Intrinsics.checkNotNull(stringArrayList);
                a.a((List<String>) stringArrayList);
            }
            a.a(a.values()[bundle.getInt("mcRequestId", 0)]);
            b a2 = a.a();
            a2.a(bundle.getString("tag"));
            return a2;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface c {
    }

    static final class d extends Lambda implements Function0 {
        public static final d a = new d();

        d() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Unable to complete request";
        }
    }

    public b(String str, String str2, int i2, String str3, String str4, List<String> list, a aVar) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "contentType");
        Intrinsics.checkNotNullParameter(str4, "url");
        Intrinsics.checkNotNullParameter(list, "headers");
        Intrinsics.checkNotNullParameter(aVar, "requestId");
        this.a = str;
        this.b = str2;
        this.c = i2;
        this.d = str3;
        this.e = str4;
        this.f = list;
        this.g = aVar;
    }

    public static final a b() {
        return i.a();
    }

    public final String c() {
        return this.a;
    }

    public final String d() {
        return this.b;
    }

    public final int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) bVar.a) && Intrinsics.areEqual((Object) this.b, (Object) bVar.b) && this.c == bVar.c && Intrinsics.areEqual((Object) this.d, (Object) bVar.d) && Intrinsics.areEqual((Object) this.e, (Object) bVar.e) && Intrinsics.areEqual((Object) this.f, (Object) bVar.f) && this.g == bVar.g;
    }

    public final String f() {
        return this.d;
    }

    public final String g() {
        return this.e;
    }

    public final List<String> h() {
        return this.f;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        String str = this.b;
        return ((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.c)) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode();
    }

    public final a i() {
        return this.g;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:35|36|(1:38)) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009e, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r3 = a(r4.getErrorStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00da, code lost:
        if (r3 != null) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00dc, code lost:
        r0.a(r3);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00d2 */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x010f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.salesforce.marketingcloud.http.d j() {
        /*
            r11 = this;
            r0 = 1
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r5 = r11.e     // Catch:{ Exception -> 0x00f3 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x00f3 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r5 = "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r5)     // Catch:{ Exception -> 0x00f3 }
            javax.net.ssl.HttpsURLConnection r4 = (javax.net.ssl.HttpsURLConnection) r4     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r5 = r11.a     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r4.setRequestMethod(r5)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r4.setDoInput(r0)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r5 = 0
            r4.setUseCaches(r5)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r4.setAllowUserInteraction(r5)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            int r6 = r11.c     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r4.setConnectTimeout(r6)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.util.List<java.lang.String> r6 = r11.f     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            int r6 = r6.size()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            kotlin.ranges.IntRange r5 = kotlin.ranges.RangesKt.until(r5, r6)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r6 = 2
            kotlin.ranges.IntProgression r5 = kotlin.ranges.RangesKt.step(r5, r6)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            int r6 = r5.getFirst()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            int r7 = r5.getLast()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            int r5 = r5.getStep()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            if (r5 <= 0) goto L_0x004b
            if (r6 <= r7) goto L_0x004f
        L_0x004b:
            if (r5 >= 0) goto L_0x0070
            if (r7 > r6) goto L_0x0070
        L_0x004f:
            java.util.List<java.lang.String> r8 = r11.f     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.lang.Object r8 = r8.get(r6)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.util.List<java.lang.String> r9 = r11.f     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            int r10 = r6 + 1
            java.lang.Object r9 = r9.get(r10)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r4.setRequestProperty(r8, r9)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            if (r6 == r7) goto L_0x0070
            int r6 = r6 + r5
            goto L_0x004f
        L_0x0068:
            r0 = move-exception
            r3 = r4
            goto L_0x010d
        L_0x006c:
            r0 = move-exception
            r3 = r4
            goto L_0x00f4
        L_0x0070:
            java.lang.String r5 = r11.b     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            if (r5 == 0) goto L_0x009f
            r4.setDoOutput(r0)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.lang.String r0 = "Content-Type"
            java.lang.String r6 = r11.d     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r4.setRequestProperty(r0, r6)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.io.OutputStream r0 = r4.getOutputStream()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.nio.charset.Charset r6 = com.salesforce.marketingcloud.internal.m.b()     // Catch:{ all -> 0x0098 }
            byte[] r5 = r5.getBytes(r6)     // Catch:{ all -> 0x0098 }
            java.lang.String r6 = "this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch:{ all -> 0x0098 }
            r0.write(r5)     // Catch:{ all -> 0x0098 }
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0098 }
            kotlin.io.CloseableKt.closeFinally(r0, r3)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            goto L_0x009f
        L_0x0098:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x009a }
        L_0x009a:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            throw r2     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
        L_0x009f:
            com.salesforce.marketingcloud.http.d$b r0 = com.salesforce.marketingcloud.http.d.g     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            com.salesforce.marketingcloud.http.d$a r0 = r0.a()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            int r3 = r4.getResponseCode()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r0.a((int) r3)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.lang.String r3 = r4.getResponseMessage()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.lang.String r5 = "getResponseMessage(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r0.b((java.lang.String) r3)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.util.Map r3 = r4.getHeaderFields()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.lang.String r5 = "getHeaderFields(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r0.a((java.util.Map<java.lang.String, ? extends java.util.List<java.lang.String>>) r3)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.io.InputStream r3 = r4.getInputStream()     // Catch:{ IOException -> 0x00d2 }
            java.lang.String r3 = r11.a((java.io.InputStream) r3)     // Catch:{ IOException -> 0x00d2 }
            if (r3 == 0) goto L_0x00df
            r0.a((java.lang.String) r3)     // Catch:{ IOException -> 0x00d2 }
            goto L_0x00df
        L_0x00d2:
            java.io.InputStream r3 = r4.getErrorStream()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            java.lang.String r3 = r11.a((java.io.InputStream) r3)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            if (r3 == 0) goto L_0x00df
            r0.a((java.lang.String) r3)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
        L_0x00df:
            r0.b((long) r1)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r0.a((long) r1)     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            com.salesforce.marketingcloud.http.d r0 = r0.a()     // Catch:{ Exception -> 0x006c, all -> 0x0068 }
            r4.disconnect()
            goto L_0x010c
        L_0x00f1:
            r0 = move-exception
            goto L_0x010d
        L_0x00f3:
            r0 = move-exception
        L_0x00f4:
            com.salesforce.marketingcloud.g r1 = com.salesforce.marketingcloud.g.a     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = j     // Catch:{ all -> 0x00f1 }
            com.salesforce.marketingcloud.http.b$d r4 = com.salesforce.marketingcloud.http.b.d.a     // Catch:{ all -> 0x00f1 }
            r1.b((java.lang.String) r2, (java.lang.Throwable) r0, (kotlin.jvm.functions.Function0) r4)     // Catch:{ all -> 0x00f1 }
            com.salesforce.marketingcloud.http.d$b r0 = com.salesforce.marketingcloud.http.d.g     // Catch:{ all -> 0x00f1 }
            java.lang.String r1 = "ERROR"
            r2 = -100
            com.salesforce.marketingcloud.http.d r0 = r0.a(r1, r2)     // Catch:{ all -> 0x00f1 }
            if (r3 == 0) goto L_0x010c
            r3.disconnect()
        L_0x010c:
            return r0
        L_0x010d:
            if (r3 == 0) goto L_0x0112
            r3.disconnect()
        L_0x0112:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.http.b.j():com.salesforce.marketingcloud.http.d");
    }

    public final int k() {
        return this.c;
    }

    public final String l() {
        return this.d;
    }

    public final List<String> m() {
        return this.f;
    }

    public final String n() {
        return this.a;
    }

    public final String o() {
        return this.b;
    }

    public final a p() {
        return this.g;
    }

    public final String q() {
        return this.h;
    }

    public final String r() {
        return this.e;
    }

    public final Bundle s() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, this.a);
        bundle.putString("requestBody", this.b);
        bundle.putInt("connectionTimeout", this.c);
        bundle.putString("contentType", this.d);
        bundle.putString("url", this.e);
        List<String> list = this.f;
        bundle.putStringArrayList("headers", list instanceof ArrayList ? (ArrayList) list : new ArrayList(this.f));
        bundle.putInt("mcRequestId", this.g.ordinal());
        bundle.putString("tag", this.h);
        return bundle;
    }

    public String toString() {
        String str = this.a;
        String str2 = this.b;
        int i2 = this.c;
        String str3 = this.d;
        String str4 = this.e;
        List<String> list = this.f;
        a aVar = this.g;
        return "Request(method=" + str + ", requestBody=" + str2 + ", connectionTimeout=" + i2 + ", contentType=" + str3 + ", url=" + str4 + ", headers=" + list + ", requestId=" + aVar + ")";
    }

    public final b a(String str, String str2, int i2, String str3, String str4, List<String> list, a aVar) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "contentType");
        Intrinsics.checkNotNullParameter(str4, "url");
        Intrinsics.checkNotNullParameter(list, "headers");
        a aVar2 = aVar;
        Intrinsics.checkNotNullParameter(aVar2, "requestId");
        return new b(str, str2, i2, str3, str4, list, aVar2);
    }

    public static /* synthetic */ b a(b bVar, String str, String str2, int i2, String str3, String str4, List<String> list, a aVar, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = bVar.a;
        }
        if ((i3 & 2) != 0) {
            str2 = bVar.b;
        }
        String str5 = str2;
        if ((i3 & 4) != 0) {
            i2 = bVar.c;
        }
        int i4 = i2;
        if ((i3 & 8) != 0) {
            str3 = bVar.d;
        }
        String str6 = str3;
        if ((i3 & 16) != 0) {
            str4 = bVar.e;
        }
        String str7 = str4;
        if ((i3 & 32) != 0) {
            list = bVar.f;
        }
        List<String> list2 = list;
        if ((i3 & 64) != 0) {
            aVar = bVar.g;
        }
        return bVar.a(str, str5, i4, str6, str7, list2, aVar);
    }

    public static final b a(Bundle bundle) {
        return i.a(bundle);
    }

    private final String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, m.b()));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append(10);
                } else {
                    String sb2 = sb.toString();
                    CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                    return sb2;
                }
            }
        } catch (Throwable th) {
            CloseableKt.closeFinally(bufferedReader, th);
            throw th;
        }
    }

    public final void a(String str) {
        this.h = str;
    }
}
