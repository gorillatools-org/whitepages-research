package com.salesforce.marketingcloud.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class e {
    private final String a;
    private final String b;
    private final String c;

    public e(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str3, "type");
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.b;
    }

    public final String e() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) eVar.a) && Intrinsics.areEqual((Object) this.b, (Object) eVar.b) && Intrinsics.areEqual((Object) this.c, (Object) eVar.c);
    }

    public final String f() {
        return this.c;
    }

    public final JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.a);
        String str = this.b;
        if (str != null) {
            jSONObject.put("activityInstanceId", str);
        }
        jSONObject.put("type", this.c);
        return jSONObject;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        String str = this.b;
        return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.c.hashCode();
    }

    public String toString() {
        String str = this.a;
        String str2 = this.b;
        String str3 = this.c;
        return "Outcome(id=" + str + ", activityInstanceId=" + str2 + ", type=" + str3 + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, str3);
    }

    public final e a(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str3, "type");
        return new e(str, str2, str3);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public e(org.json.JSONObject r5) {
        /*
            r4 = this;
            java.lang.String r0 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "id"
            java.lang.String r0 = r5.getString(r0)
            java.lang.String r1 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r2 = "activityInstanceId"
            java.lang.String r2 = r5.optString(r2)
            java.lang.String r3 = "optString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.String r2 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            java.lang.String r3 = "type"
            java.lang.String r5 = r5.getString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r1)
            r4.<init>(r0, r2, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.e.<init>(org.json.JSONObject):void");
    }

    public static /* synthetic */ e a(e eVar, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = eVar.a;
        }
        if ((i & 2) != 0) {
            str2 = eVar.b;
        }
        if ((i & 4) != 0) {
            str3 = eVar.c;
        }
        return eVar.a(str, str2, str3);
    }
}
