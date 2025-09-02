package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.internal.m;
import java.util.Date;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

public final class h {
    private final String a;
    private final String b;
    private final Date c;
    private final List<g> d;
    private final List<e> e;
    private String f;

    public h(String str, String str2, Date date, List<g> list, List<e> list2, String str3) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "key");
        Intrinsics.checkNotNullParameter(list2, "outcomes");
        this.a = str;
        this.b = str2;
        this.c = date;
        this.d = list;
        this.e = list2;
        this.f = str3;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final Date c() {
        return this.c;
    }

    public final List<g> d() {
        return this.d;
    }

    public final List<e> e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) hVar.a) && Intrinsics.areEqual((Object) this.b, (Object) hVar.b) && Intrinsics.areEqual((Object) this.c, (Object) hVar.c) && Intrinsics.areEqual((Object) this.d, (Object) hVar.d) && Intrinsics.areEqual((Object) this.e, (Object) hVar.e) && Intrinsics.areEqual((Object) this.f, (Object) hVar.f);
    }

    public final String f() {
        return this.f;
    }

    public final String g() {
        return this.f;
    }

    public final String h() {
        return this.a;
    }

    public int hashCode() {
        int hashCode = ((this.a.hashCode() * 31) + this.b.hashCode()) * 31;
        Date date = this.c;
        int i = 0;
        int hashCode2 = (hashCode + (date == null ? 0 : date.hashCode())) * 31;
        List<g> list = this.d;
        int hashCode3 = (((hashCode2 + (list == null ? 0 : list.hashCode())) * 31) + this.e.hashCode()) * 31;
        String str = this.f;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode3 + i;
    }

    public final String i() {
        return this.b;
    }

    public final List<e> j() {
        return this.e;
    }

    public final List<g> k() {
        return this.d;
    }

    public final Date l() {
        return this.c;
    }

    public final JSONObject m() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.a);
        jSONObject.put("key", this.b);
        Date date = this.c;
        if (date != null) {
            jSONObject.put("startDateUtc", m.a(date));
        }
        List<g> list = this.d;
        if (list != null) {
            JSONArray jSONArray = new JSONArray();
            for (g k : list) {
                jSONArray.put(k.k());
            }
            Unit unit = Unit.INSTANCE;
            jSONObject.put("rules", jSONArray);
        }
        JSONArray jSONArray2 = new JSONArray();
        for (e g : this.e) {
            jSONArray2.put(g.g());
        }
        Unit unit2 = Unit.INSTANCE;
        jSONObject.put("outcomes", jSONArray2);
        String str = this.f;
        if (str != null) {
            jSONObject.put("evalLogic", str);
        }
        return jSONObject;
    }

    public String toString() {
        String str = this.a;
        String str2 = this.b;
        Date date = this.c;
        List<g> list = this.d;
        List<e> list2 = this.e;
        String str3 = this.f;
        return "Trigger(id=" + str + ", key=" + str2 + ", startDateUtc=" + date + ", rules=" + list + ", outcomes=" + list2 + ", evalLogic=" + str3 + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(String str, String str2, Date date, List list, List list2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : date, (i & 8) != 0 ? null : list, list2, (i & 32) != 0 ? null : str3);
    }

    public final h a(String str, String str2, Date date, List<g> list, List<e> list2, String str3) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "key");
        Intrinsics.checkNotNullParameter(list2, "outcomes");
        return new h(str, str2, date, list, list2, str3);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public h(org.json.JSONObject r17) {
        /*
            r16 = this;
            r0 = r17
            java.lang.String r1 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "id"
            java.lang.String r3 = r0.getString(r1)
            java.lang.String r1 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            java.lang.String r2 = "key"
            java.lang.String r4 = r0.getString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r1)
            java.lang.String r1 = "startDateUtc"
            java.lang.String r1 = r0.optString(r1)
            java.lang.String r2 = "optString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r1 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r1)
            r2 = 0
            if (r1 == 0) goto L_0x0033
            java.util.Date r1 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r1)
            r5 = r1
            goto L_0x0034
        L_0x0033:
            r5 = r2
        L_0x0034:
            java.lang.String r1 = "rules"
            org.json.JSONArray r1 = r0.optJSONArray(r1)
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r7 = 10
            r8 = 0
            java.lang.Class<org.json.JSONObject> r9 = org.json.JSONObject.class
            java.lang.String r10 = "null cannot be cast to non-null type org.json.JSONObject"
            if (r1 == 0) goto L_0x0124
            int r11 = r1.length()
            kotlin.ranges.IntRange r11 = kotlin.ranges.RangesKt.until(r8, r11)
            java.util.ArrayList r12 = new java.util.ArrayList
            int r13 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r11, r7)
            r12.<init>(r13)
            java.util.Iterator r11 = r11.iterator()
        L_0x005a:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x0102
            r13 = r11
            kotlin.collections.IntIterator r13 = (kotlin.collections.IntIterator) r13
            int r13 = r13.nextInt()
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x0083
            org.json.JSONObject r13 = r1.getJSONObject(r13)
            if (r13 == 0) goto L_0x007d
            goto L_0x00f7
        L_0x007d:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r10)
            throw r0
        L_0x0083:
            java.lang.Class r15 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x009a
            int r13 = r1.getInt(r13)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
        L_0x0097:
            org.json.JSONObject r13 = (org.json.JSONObject) r13
            goto L_0x00f7
        L_0x009a:
            java.lang.Class r15 = java.lang.Double.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x00af
            double r13 = r1.getDouble(r13)
            java.lang.Double r13 = java.lang.Double.valueOf(r13)
            goto L_0x0097
        L_0x00af:
            java.lang.Class r15 = java.lang.Long.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x00c4
            long r13 = r1.getLong(r13)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            goto L_0x0097
        L_0x00c4:
            java.lang.Class r15 = java.lang.Boolean.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x00d9
            boolean r13 = r1.getBoolean(r13)
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)
            goto L_0x0097
        L_0x00d9:
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r14 == 0) goto L_0x00f0
            java.lang.String r13 = r1.getString(r13)
            if (r13 == 0) goto L_0x00ea
            goto L_0x00f6
        L_0x00ea:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r10)
            throw r0
        L_0x00f0:
            java.lang.Object r13 = r1.get(r13)
            if (r13 == 0) goto L_0x00fc
        L_0x00f6:
            goto L_0x0097
        L_0x00f7:
            r12.add(r13)
            goto L_0x005a
        L_0x00fc:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r10)
            throw r0
        L_0x0102:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r11 = r12.iterator()
        L_0x010b:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0125
            java.lang.Object r12 = r11.next()
            org.json.JSONObject r12 = (org.json.JSONObject) r12
            com.salesforce.marketingcloud.events.g r13 = new com.salesforce.marketingcloud.events.g     // Catch:{ Exception -> 0x011d }
            r13.<init>(r12)     // Catch:{ Exception -> 0x011d }
            goto L_0x011e
        L_0x011d:
            r13 = r2
        L_0x011e:
            if (r13 == 0) goto L_0x010b
            r1.add(r13)
            goto L_0x010b
        L_0x0124:
            r1 = r2
        L_0x0125:
            java.lang.String r11 = "outcomes"
            org.json.JSONArray r11 = r0.getJSONArray(r11)
            java.lang.String r12 = "getJSONArray(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r12)
            int r12 = r11.length()
            kotlin.ranges.IntRange r12 = kotlin.ranges.RangesKt.until(r8, r12)
            java.util.ArrayList r13 = new java.util.ArrayList
            int r7 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r12, r7)
            r13.<init>(r7)
            java.util.Iterator r7 = r12.iterator()
        L_0x0145:
            boolean r12 = r7.hasNext()
            if (r12 == 0) goto L_0x01ed
            r12 = r7
            kotlin.collections.IntIterator r12 = (kotlin.collections.IntIterator) r12
            int r12 = r12.nextInt()
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x016e
            org.json.JSONObject r12 = r11.getJSONObject(r12)
            if (r12 == 0) goto L_0x0168
            goto L_0x01e2
        L_0x0168:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r10)
            throw r0
        L_0x016e:
            java.lang.Class r15 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x0185
            int r12 = r11.getInt(r12)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
        L_0x0182:
            org.json.JSONObject r12 = (org.json.JSONObject) r12
            goto L_0x01e2
        L_0x0185:
            java.lang.Class r15 = java.lang.Double.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x019a
            double r14 = r11.getDouble(r12)
            java.lang.Double r12 = java.lang.Double.valueOf(r14)
            goto L_0x0182
        L_0x019a:
            java.lang.Class r15 = java.lang.Long.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x01af
            long r14 = r11.getLong(r12)
            java.lang.Long r12 = java.lang.Long.valueOf(r14)
            goto L_0x0182
        L_0x01af:
            java.lang.Class r15 = java.lang.Boolean.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x01c4
            boolean r12 = r11.getBoolean(r12)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            goto L_0x0182
        L_0x01c4:
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r14 == 0) goto L_0x01db
            java.lang.String r12 = r11.getString(r12)
            if (r12 == 0) goto L_0x01d5
            goto L_0x01e1
        L_0x01d5:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r10)
            throw r0
        L_0x01db:
            java.lang.Object r12 = r11.get(r12)
            if (r12 == 0) goto L_0x01e7
        L_0x01e1:
            goto L_0x0182
        L_0x01e2:
            r13.add(r12)
            goto L_0x0145
        L_0x01e7:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r10)
            throw r0
        L_0x01ed:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r6 = r13.iterator()
        L_0x01f6:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x020f
            java.lang.Object r9 = r6.next()
            org.json.JSONObject r9 = (org.json.JSONObject) r9
            com.salesforce.marketingcloud.events.e r10 = new com.salesforce.marketingcloud.events.e     // Catch:{ Exception -> 0x0208 }
            r10.<init>(r9)     // Catch:{ Exception -> 0x0208 }
            goto L_0x0209
        L_0x0208:
            r10 = r2
        L_0x0209:
            if (r10 == 0) goto L_0x01f6
            r7.add(r10)
            goto L_0x01f6
        L_0x020f:
            java.lang.String r6 = "evalLogic"
            java.lang.String r0 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.getStringOrNull(r0, r6)
            if (r0 == 0) goto L_0x02a5
            java.lang.String r2 = "&&"
            java.lang.String[] r10 = new java.lang.String[]{r2}
            r13 = 6
            r14 = 0
            r11 = 0
            r12 = 0
            r9 = r0
            java.util.List r2 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r9, (java.lang.String[]) r10, (boolean) r11, (int) r12, (int) r13, (java.lang.Object) r14)
            java.lang.String r6 = "||"
            java.lang.String[] r10 = new java.lang.String[]{r6}
            java.util.List r6 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r9, (java.lang.String[]) r10, (boolean) r11, (int) r12, (int) r13, (java.lang.Object) r14)
            boolean r9 = kotlin.text.StringsKt.isBlank(r0)
            if (r9 != 0) goto L_0x029d
            int r9 = r2.size()
            r10 = 1
            if (r9 <= r10) goto L_0x0243
            int r9 = r6.size()
            if (r9 > r10) goto L_0x029d
        L_0x0243:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            int r11 = r2.size()
            if (r11 <= r10) goto L_0x0252
            r9.addAll(r2)
            goto L_0x0255
        L_0x0252:
            r9.addAll(r6)
        L_0x0255:
            java.util.Iterator r2 = r9.iterator()
        L_0x0259:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x0285
            java.lang.Object r6 = r2.next()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.CharSequence r6 = kotlin.text.StringsKt.trim(r6)
            java.lang.String r6 = r6.toString()
            boolean r9 = kotlin.text.StringsKt.isBlank(r6)
            if (r9 != 0) goto L_0x0283
            java.lang.Long r9 = kotlin.text.StringsKt.toLongOrNull(r6)
            if (r9 == 0) goto L_0x0283
            long r11 = java.lang.Long.parseLong(r6)
            r13 = 0
            int r6 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x0259
        L_0x0283:
            r10 = r8
            goto L_0x0259
        L_0x0285:
            if (r10 == 0) goto L_0x0295
            r13 = 4
            r14 = 0
            java.lang.String r10 = " "
            java.lang.String r11 = ""
            r12 = 0
            r9 = r0
            java.lang.String r0 = kotlin.text.StringsKt.replace$default((java.lang.String) r9, (java.lang.String) r10, (java.lang.String) r11, (boolean) r12, (int) r13, (java.lang.Object) r14)
            r8 = r0
            goto L_0x02a6
        L_0x0295:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "evalLogic contained non-numeric indexes."
            r0.<init>(r1)
            throw r0
        L_0x029d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "evalLogic was blank or contained both && and || operators"
            r0.<init>(r1)
            throw r0
        L_0x02a5:
            r8 = r2
        L_0x02a6:
            r2 = r16
            r6 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.h.<init>(org.json.JSONObject):void");
    }

    public static /* synthetic */ h a(h hVar, String str, String str2, Date date, List<g> list, List<e> list2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hVar.a;
        }
        if ((i & 2) != 0) {
            str2 = hVar.b;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            date = hVar.c;
        }
        Date date2 = date;
        if ((i & 8) != 0) {
            list = hVar.d;
        }
        List<g> list3 = list;
        if ((i & 16) != 0) {
            list2 = hVar.e;
        }
        List<e> list4 = list2;
        if ((i & 32) != 0) {
            str3 = hVar.f;
        }
        return hVar.a(str, str4, date2, list3, list4, str3);
    }

    public final void a(String str) {
        this.f = str;
    }
}
