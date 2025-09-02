package com.salesforce.marketingcloud.messages.geofence;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.messages.MessageResponse;
import com.salesforce.marketingcloud.messages.Region;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@MCKeep
public final class GeofenceMessageResponse implements MessageResponse {
    private final List<Region> fences;
    private final LatLon refreshCenter;
    private final int refreshRadius;

    static final class a extends Lambda implements Function0 {
        public static final a a = new a();

        a() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Unable to parse Region from geofence message payload.";
        }
    }

    public GeofenceMessageResponse(LatLon latLon, int i, List<Region> list) {
        Intrinsics.checkNotNullParameter(latLon, "refreshCenter");
        Intrinsics.checkNotNullParameter(list, "fences");
        this.refreshCenter = latLon;
        this.refreshRadius = i;
        this.fences = list;
    }

    /* renamed from: -deprecated_fences  reason: not valid java name */
    public final List<Region> m696deprecated_fences() {
        return this.fences;
    }

    /* renamed from: -deprecated_refreshCenter  reason: not valid java name */
    public final LatLon m697deprecated_refreshCenter() {
        return getRefreshCenter();
    }

    /* renamed from: -deprecated_refreshRadius  reason: not valid java name */
    public final int m698deprecated_refreshRadius() {
        return getRefreshRadius();
    }

    public final List<Region> fences() {
        return this.fences;
    }

    /* renamed from: refreshCenter */
    public LatLon getRefreshCenter() {
        return this.refreshCenter;
    }

    /* renamed from: refreshRadius */
    public int getRefreshRadius() {
        return this.refreshRadius;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GeofenceMessageResponse(org.json.JSONObject r9) throws org.json.JSONException {
        /*
            r8 = this;
            java.lang.String r0 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.salesforce.marketingcloud.location.LatLon r0 = com.salesforce.marketingcloud.messages.a.a(r9)
            int r1 = com.salesforce.marketingcloud.messages.a.b(r9)
            java.lang.String r2 = "fences"
            org.json.JSONArray r9 = r9.optJSONArray(r2)
            if (r9 == 0) goto L_0x010b
            int r2 = r9.length()
            r3 = 0
            kotlin.ranges.IntRange r2 = kotlin.ranges.RangesKt.until(r3, r2)
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r4)
            r3.<init>(r4)
            java.util.Iterator r2 = r2.iterator()
        L_0x002d:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x00db
            r4 = r2
            kotlin.collections.IntIterator r4 = (kotlin.collections.IntIterator) r4
            int r4 = r4.nextInt()
            java.lang.Class<org.json.JSONObject> r5 = org.json.JSONObject.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r5)
            java.lang.String r7 = "null cannot be cast to non-null type org.json.JSONObject"
            if (r5 == 0) goto L_0x005a
            org.json.JSONObject r4 = r9.getJSONObject(r4)
            if (r4 == 0) goto L_0x0054
            goto L_0x00d0
        L_0x0054:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            r9.<init>(r7)
            throw r9
        L_0x005a:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0071
            int r4 = r9.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x006e:
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            goto L_0x00d0
        L_0x0071:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0086
            double r4 = r9.getDouble(r4)
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            goto L_0x006e
        L_0x0086:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x009b
            long r4 = r9.getLong(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            goto L_0x006e
        L_0x009b:
            java.lang.Class r5 = java.lang.Boolean.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x00b0
            boolean r4 = r9.getBoolean(r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            goto L_0x006e
        L_0x00b0:
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x00c9
            java.lang.String r4 = r9.getString(r4)
            if (r4 == 0) goto L_0x00c3
            goto L_0x00cf
        L_0x00c3:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            r9.<init>(r7)
            throw r9
        L_0x00c9:
            java.lang.Object r4 = r9.get(r4)
            if (r4 == 0) goto L_0x00d5
        L_0x00cf:
            goto L_0x006e
        L_0x00d0:
            r3.add(r4)
            goto L_0x002d
        L_0x00d5:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            r9.<init>(r7)
            throw r9
        L_0x00db:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r2 = r3.iterator()
        L_0x00e4:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x010f
            java.lang.Object r3 = r2.next()
            org.json.JSONObject r3 = (org.json.JSONObject) r3
            com.salesforce.marketingcloud.messages.Region r4 = new com.salesforce.marketingcloud.messages.Region     // Catch:{ Exception -> 0x00f6 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x00f6 }
            goto L_0x0105
        L_0x00f6:
            r3 = move-exception
            com.salesforce.marketingcloud.g r4 = com.salesforce.marketingcloud.g.a
            com.salesforce.marketingcloud.messages.Region$Companion r5 = com.salesforce.marketingcloud.messages.Region.Companion
            java.lang.String r5 = r5.m694TAG()
            com.salesforce.marketingcloud.messages.geofence.GeofenceMessageResponse$a r6 = com.salesforce.marketingcloud.messages.geofence.GeofenceMessageResponse.a.a
            r4.b((java.lang.String) r5, (java.lang.Throwable) r3, (kotlin.jvm.functions.Function0) r6)
            r4 = 0
        L_0x0105:
            if (r4 == 0) goto L_0x00e4
            r9.add(r4)
            goto L_0x00e4
        L_0x010b:
            java.util.List r9 = kotlin.collections.CollectionsKt.emptyList()
        L_0x010f:
            r8.<init>(r0, r1, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.geofence.GeofenceMessageResponse.<init>(org.json.JSONObject):void");
    }
}
