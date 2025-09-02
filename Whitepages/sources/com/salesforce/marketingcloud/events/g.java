package com.salesforce.marketingcloud.events;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class g {
    private final int a;
    private final String b;
    private final a c;
    private final b d;
    private final String e;

    public enum a {
        EQ,
        NEQ,
        LT,
        GT,
        LTEQ,
        GTEQ,
        REGEX;

        static {
            a[] a;
            i = EnumEntriesKt.enumEntries(a);
        }

        public static EnumEntries b() {
            return i;
        }
    }

    public enum b {
        INT,
        DOUBLE,
        BOOL,
        STRING;

        static {
            b[] a;
            f = EnumEntriesKt.enumEntries(a);
        }

        public static EnumEntries b() {
            return f;
        }
    }

    public g(int i, String str, a aVar, b bVar, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(aVar, "operator");
        Intrinsics.checkNotNullParameter(bVar, "valueType");
        Intrinsics.checkNotNullParameter(str2, "value");
        this.a = i;
        this.b = str;
        this.c = aVar;
        this.d = bVar;
        this.e = str2;
    }

    public final int a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final a c() {
        return this.c;
    }

    public final b d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return this.a == gVar.a && Intrinsics.areEqual((Object) this.b, (Object) gVar.b) && this.c == gVar.c && this.d == gVar.d && Intrinsics.areEqual((Object) this.e, (Object) gVar.e);
    }

    public final int f() {
        return this.a;
    }

    public final String g() {
        return this.b;
    }

    public final a h() {
        return this.c;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }

    public final String i() {
        return this.e;
    }

    public final b j() {
        return this.d;
    }

    public final JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(FirebaseAnalytics.Param.INDEX, this.a);
        jSONObject.put("key", this.b);
        jSONObject.put("operator", this.c.name());
        jSONObject.put("valueType", this.d.name());
        jSONObject.put("value", this.e);
        return jSONObject;
    }

    public String toString() {
        int i = this.a;
        String str = this.b;
        a aVar = this.c;
        b bVar = this.d;
        String str2 = this.e;
        return "Rule(index=" + i + ", key=" + str + ", operator=" + aVar + ", valueType=" + bVar + ", value=" + str2 + ")";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public g(org.json.JSONObject r9) {
        /*
            r8 = this;
            java.lang.String r0 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "index"
            r1 = 0
            int r3 = r9.optInt(r0, r1)
            java.lang.String r0 = "key"
            java.lang.String r4 = r9.getString(r0)
            java.lang.String r0 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
            java.lang.String r1 = "operator"
            java.lang.String r1 = r9.getString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            com.salesforce.marketingcloud.events.g$a r5 = com.salesforce.marketingcloud.events.g.a.valueOf(r1)
            java.lang.String r1 = "valueType"
            java.lang.String r1 = r9.getString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            com.salesforce.marketingcloud.events.g$b r6 = com.salesforce.marketingcloud.events.g.b.valueOf(r1)
            java.lang.String r1 = "value"
            java.lang.String r7 = r9.getString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.g.<init>(org.json.JSONObject):void");
    }

    public final g a(int i, String str, a aVar, b bVar, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(aVar, "operator");
        Intrinsics.checkNotNullParameter(bVar, "valueType");
        Intrinsics.checkNotNullParameter(str2, "value");
        return new g(i, str, aVar, bVar, str2);
    }

    public static /* synthetic */ g a(g gVar, int i, String str, a aVar, b bVar, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = gVar.a;
        }
        if ((i2 & 2) != 0) {
            str = gVar.b;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            aVar = gVar.c;
        }
        a aVar2 = aVar;
        if ((i2 & 8) != 0) {
            bVar = gVar.d;
        }
        b bVar2 = bVar;
        if ((i2 & 16) != 0) {
            str2 = gVar.e;
        }
        return gVar.a(i, str3, aVar2, bVar2, str2);
    }
}
