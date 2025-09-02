package com.salesforce.marketingcloud.messages;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.storage.db.i;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@MCKeep
public final class Region implements Parcelable, Comparable<Region> {
    public static final Parcelable.Creator<Region> CREATOR = new c();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String MAGIC_REGION_ID = "~~m@g1c_f3nc3~~";
    public static final int REGION_TYPE_FENCE = 1;
    public static final int REGION_TYPE_PROXIMITY = 3;
    /* access modifiers changed from: private */
    public static final String TAG = g.a("Region");
    private final LatLon center;
    private final String description;
    private final String id;
    private boolean isInside;
    private final int major;
    private final List<Message> messages;
    private final int minor;
    private final String name;
    private final String proximityUuid;
    private final int radius;
    private final int regionType;

    @MCKeep
    public static final class Companion {
        private Companion() {
        }

        /* renamed from: -TAG  reason: not valid java name */
        public final String m694TAG() {
            return Region.TAG;
        }

        /* renamed from: -magicFence  reason: not valid java name */
        public final Region m695magicFence(LatLon latLon, int i) {
            Intrinsics.checkNotNullParameter(latLon, "center");
            return new Region(Region.MAGIC_REGION_ID, latLon, i, (String) null, 0, 0, -1, (String) null, (String) null, (List) null, 952, (DefaultConstructorMarker) null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @MCKeep
    @Retention(RetentionPolicy.SOURCE)
    public @interface RegionType {
    }

    static final class a extends Lambda implements Function0 {
        public static final a a = new a();

        a() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Unable to parse Message from region payload.";
        }
    }

    static final class b extends Lambda implements Function0 {
        public static final b a = new b();

        b() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Unable to parse region messages.";
        }
    }

    public static final class c implements Parcelable.Creator<Region> {
        /* renamed from: a */
        public final Region createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            LatLon createFromParcel = LatLon.CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            String readString2 = parcel.readString();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            int readInt4 = parcel.readInt();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            int readInt5 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt5);
            for (int i = 0; i != readInt5; i++) {
                arrayList.add(Message.CREATOR.createFromParcel(parcel));
            }
            return new Region(readString, createFromParcel, readInt, readString2, readInt2, readInt3, readInt4, readString3, readString4, arrayList);
        }

        /* renamed from: a */
        public final Region[] newArray(int i) {
            return new Region[i];
        }
    }

    /* renamed from: -isInside$annotations  reason: not valid java name */
    public static /* synthetic */ void m681isInside$annotations() {
    }

    public Region(String str, LatLon latLon, int i, String str2, int i2, int i3, int i4, String str3, String str4, List<Message> list) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(latLon, "center");
        Intrinsics.checkNotNullParameter(list, i.e);
        this.id = str;
        this.center = latLon;
        this.radius = i;
        this.proximityUuid = str2;
        this.major = i2;
        this.minor = i3;
        this.regionType = i4;
        this.name = str3;
        this.description = str4;
        this.messages = list;
    }

    public static /* synthetic */ Region copy$default(Region region, String str, LatLon latLon, int i, String str2, int i2, int i3, int i4, String str3, String str4, List list, int i5, Object obj) {
        Region region2 = region;
        int i6 = i5;
        return region.copy((i6 & 1) != 0 ? region2.id : str, (i6 & 2) != 0 ? region2.center : latLon, (i6 & 4) != 0 ? region2.radius : i, (i6 & 8) != 0 ? region2.proximityUuid : str2, (i6 & 16) != 0 ? region2.major : i2, (i6 & 32) != 0 ? region2.minor : i3, (i6 & 64) != 0 ? region2.regionType : i4, (i6 & 128) != 0 ? region2.name : str3, (i6 & 256) != 0 ? region2.description : str4, (i6 & 512) != 0 ? region2.messages : list);
    }

    /* renamed from: -deprecated_center  reason: not valid java name */
    public final LatLon m682deprecated_center() {
        return this.center;
    }

    /* renamed from: -deprecated_description  reason: not valid java name */
    public final String m683deprecated_description() {
        return this.description;
    }

    /* renamed from: -deprecated_id  reason: not valid java name */
    public final String m684deprecated_id() {
        return this.id;
    }

    /* renamed from: -deprecated_major  reason: not valid java name */
    public final int m685deprecated_major() {
        return this.major;
    }

    /* renamed from: -deprecated_messages  reason: not valid java name */
    public final List<Message> m686deprecated_messages() {
        return this.messages;
    }

    /* renamed from: -deprecated_minor  reason: not valid java name */
    public final int m687deprecated_minor() {
        return this.minor;
    }

    /* renamed from: -deprecated_name  reason: not valid java name */
    public final String m688deprecated_name() {
        return this.name;
    }

    /* renamed from: -deprecated_proximityUuid  reason: not valid java name */
    public final String m689deprecated_proximityUuid() {
        return this.proximityUuid;
    }

    /* renamed from: -deprecated_radius  reason: not valid java name */
    public final int m690deprecated_radius() {
        return this.radius;
    }

    /* renamed from: -deprecated_regionType  reason: not valid java name */
    public final int m691deprecated_regionType() {
        return this.regionType;
    }

    /* renamed from: -isInside  reason: not valid java name */
    public final void m692isInside(boolean z) {
        this.isInside = z;
    }

    public final LatLon center() {
        return this.center;
    }

    public int compareTo(Region region) {
        Intrinsics.checkNotNullParameter(region, "other");
        return this.id.compareTo(region.id);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Message> component10() {
        return this.messages;
    }

    public final LatLon component2() {
        return this.center;
    }

    public final int component3() {
        return this.radius;
    }

    public final String component4() {
        return this.proximityUuid;
    }

    public final int component5() {
        return this.major;
    }

    public final int component6() {
        return this.minor;
    }

    public final int component7() {
        return this.regionType;
    }

    public final String component8() {
        return this.name;
    }

    public final String component9() {
        return this.description;
    }

    public final Region copy(String str, LatLon latLon, int i, String str2, int i2, int i3, int i4, String str3, String str4, List<Message> list) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(latLon, "center");
        List<Message> list2 = list;
        Intrinsics.checkNotNullParameter(list2, i.e);
        return new Region(str, latLon, i, str2, i2, i3, i4, str3, str4, list2);
    }

    public int describeContents() {
        return 0;
    }

    public final String description() {
        return this.description;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Region)) {
            return false;
        }
        Region region = (Region) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) region.id) && Intrinsics.areEqual((Object) this.center, (Object) region.center) && this.radius == region.radius && Intrinsics.areEqual((Object) this.proximityUuid, (Object) region.proximityUuid) && this.major == region.major && this.minor == region.minor && this.regionType == region.regionType && Intrinsics.areEqual((Object) this.name, (Object) region.name) && Intrinsics.areEqual((Object) this.description, (Object) region.description) && Intrinsics.areEqual((Object) this.messages, (Object) region.messages);
    }

    public int hashCode() {
        int hashCode = ((((this.id.hashCode() * 31) + this.center.hashCode()) * 31) + Integer.hashCode(this.radius)) * 31;
        String str = this.proximityUuid;
        int i = 0;
        int hashCode2 = (((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.major)) * 31) + Integer.hashCode(this.minor)) * 31) + Integer.hashCode(this.regionType)) * 31;
        String str2 = this.name;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.description;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return ((hashCode3 + i) * 31) + this.messages.hashCode();
    }

    public final String id() {
        return this.id;
    }

    public final int major() {
        return this.major;
    }

    public final List<Message> messages() {
        return this.messages;
    }

    public final int minor() {
        return this.minor;
    }

    public final String name() {
        return this.name;
    }

    public final String proximityUuid() {
        return this.proximityUuid;
    }

    public final int radius() {
        return this.radius;
    }

    public final int regionType() {
        return this.regionType;
    }

    public String toString() {
        String str = this.id;
        LatLon latLon = this.center;
        int i = this.radius;
        String str2 = this.proximityUuid;
        int i2 = this.major;
        int i3 = this.minor;
        int i4 = this.regionType;
        String str3 = this.name;
        String str4 = this.description;
        List<Message> list = this.messages;
        return "Region(id=" + str + ", center=" + latLon + ", radius=" + i + ", proximityUuid=" + str2 + ", major=" + i2 + ", minor=" + i3 + ", regionType=" + i4 + ", name=" + str3 + ", description=" + str4 + ", messages=" + list + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.id);
        this.center.writeToParcel(parcel, i);
        parcel.writeInt(this.radius);
        parcel.writeString(this.proximityUuid);
        parcel.writeInt(this.major);
        parcel.writeInt(this.minor);
        parcel.writeInt(this.regionType);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        List<Message> list = this.messages;
        parcel.writeInt(list.size());
        for (Message writeToParcel : list) {
            writeToParcel.writeToParcel(parcel, i);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Region(java.lang.String r15, com.salesforce.marketingcloud.location.LatLon r16, int r17, java.lang.String r18, int r19, int r20, int r21, java.lang.String r22, java.lang.String r23, java.util.List r24, int r25, kotlin.jvm.internal.DefaultConstructorMarker r26) {
        /*
            r14 = this;
            r0 = r25
            r1 = r0 & 8
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r7 = r2
            goto L_0x000b
        L_0x0009:
            r7 = r18
        L_0x000b:
            r1 = r0 & 16
            r3 = 0
            if (r1 == 0) goto L_0x0012
            r8 = r3
            goto L_0x0014
        L_0x0012:
            r8 = r19
        L_0x0014:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x001a
            r9 = r3
            goto L_0x001c
        L_0x001a:
            r9 = r20
        L_0x001c:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0022
            r11 = r2
            goto L_0x0024
        L_0x0022:
            r11 = r22
        L_0x0024:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x002a
            r12 = r2
            goto L_0x002c
        L_0x002a:
            r12 = r23
        L_0x002c:
            r0 = r0 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L_0x0036
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            r13 = r0
            goto L_0x0038
        L_0x0036:
            r13 = r24
        L_0x0038:
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r10 = r21
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.Region.<init>(java.lang.String, com.salesforce.marketingcloud.location.LatLon, int, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* renamed from: -isInside  reason: not valid java name */
    public final boolean m693isInside() {
        return this.isInside;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Region(org.json.JSONObject r18) throws org.json.JSONException {
        /*
            r17 = this;
            r0 = r18
            java.lang.Class<org.json.JSONObject> r1 = org.json.JSONObject.class
            java.lang.String r2 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            java.lang.String r2 = "id"
            java.lang.String r4 = r0.getString(r2)
            java.lang.String r2 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            com.salesforce.marketingcloud.location.LatLon r5 = new com.salesforce.marketingcloud.location.LatLon
            java.lang.String r2 = "center"
            org.json.JSONObject r2 = r0.getJSONObject(r2)
            java.lang.String r3 = "getJSONObject(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r5.<init>(r2)
            java.lang.String r2 = "radius"
            int r6 = r0.optInt(r2)
            java.lang.String r2 = "proximityUuid"
            java.lang.String r2 = r0.optString(r2)
            java.lang.String r3 = "optString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.String r7 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            java.lang.String r2 = "major"
            int r8 = r0.optInt(r2)
            java.lang.String r2 = "minor"
            int r9 = r0.optInt(r2)
            java.lang.String r2 = "locationType"
            int r10 = r0.getInt(r2)
            java.lang.String r2 = "name"
            java.lang.String r2 = r0.optString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.String r11 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            java.lang.String r2 = "description"
            java.lang.String r2 = r0.optString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.String r12 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            java.lang.String r2 = "messages"
            org.json.JSONArray r0 = r0.optJSONArray(r2)     // Catch:{ JSONException -> 0x00b2 }
            if (r0 == 0) goto L_0x0188
            int r2 = r0.length()     // Catch:{ JSONException -> 0x00b2 }
            r3 = 0
            kotlin.ranges.IntRange r2 = kotlin.ranges.RangesKt.until(r3, r2)     // Catch:{ JSONException -> 0x00b2 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ JSONException -> 0x00b2 }
            r13 = 10
            int r13 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r13)     // Catch:{ JSONException -> 0x00b2 }
            r3.<init>(r13)     // Catch:{ JSONException -> 0x00b2 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ JSONException -> 0x00b2 }
        L_0x0085:
            boolean r13 = r2.hasNext()     // Catch:{ JSONException -> 0x00b2 }
            if (r13 == 0) goto L_0x013a
            r13 = r2
            kotlin.collections.IntIterator r13 = (kotlin.collections.IntIterator) r13     // Catch:{ JSONException -> 0x00b2 }
            int r13 = r13.nextInt()     // Catch:{ JSONException -> 0x00b2 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ JSONException -> 0x00b2 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ JSONException -> 0x00b2 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)     // Catch:{ JSONException -> 0x00b2 }
            r16 = r1
            java.lang.String r1 = "null cannot be cast to non-null type org.json.JSONObject"
            if (r15 == 0) goto L_0x00b5
            org.json.JSONObject r13 = r0.getJSONObject(r13)     // Catch:{ JSONException -> 0x00b2 }
            if (r13 == 0) goto L_0x00ac
            goto L_0x012d
        L_0x00ac:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x00b2 }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x00b2 }
            throw r0     // Catch:{ JSONException -> 0x00b2 }
        L_0x00b2:
            r0 = move-exception
            goto L_0x018d
        L_0x00b5:
            java.lang.Class r15 = java.lang.Integer.TYPE     // Catch:{ JSONException -> 0x00b2 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ JSONException -> 0x00b2 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)     // Catch:{ JSONException -> 0x00b2 }
            if (r15 == 0) goto L_0x00cd
            int r1 = r0.getInt(r13)     // Catch:{ JSONException -> 0x00b2 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ JSONException -> 0x00b2 }
        L_0x00c9:
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ JSONException -> 0x00b2 }
            r13 = r1
            goto L_0x012d
        L_0x00cd:
            java.lang.Class r15 = java.lang.Double.TYPE     // Catch:{ JSONException -> 0x00b2 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ JSONException -> 0x00b2 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)     // Catch:{ JSONException -> 0x00b2 }
            if (r15 == 0) goto L_0x00e2
            double r13 = r0.getDouble(r13)     // Catch:{ JSONException -> 0x00b2 }
            java.lang.Double r1 = java.lang.Double.valueOf(r13)     // Catch:{ JSONException -> 0x00b2 }
            goto L_0x00c9
        L_0x00e2:
            java.lang.Class r15 = java.lang.Long.TYPE     // Catch:{ JSONException -> 0x00b2 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ JSONException -> 0x00b2 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)     // Catch:{ JSONException -> 0x00b2 }
            if (r15 == 0) goto L_0x00f7
            long r13 = r0.getLong(r13)     // Catch:{ JSONException -> 0x00b2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r13)     // Catch:{ JSONException -> 0x00b2 }
            goto L_0x00c9
        L_0x00f7:
            java.lang.Class r15 = java.lang.Boolean.TYPE     // Catch:{ JSONException -> 0x00b2 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ JSONException -> 0x00b2 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)     // Catch:{ JSONException -> 0x00b2 }
            if (r15 == 0) goto L_0x010c
            boolean r1 = r0.getBoolean(r13)     // Catch:{ JSONException -> 0x00b2 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ JSONException -> 0x00b2 }
            goto L_0x00c9
        L_0x010c:
            java.lang.Class<java.lang.String> r15 = java.lang.String.class
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ JSONException -> 0x00b2 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)     // Catch:{ JSONException -> 0x00b2 }
            if (r14 == 0) goto L_0x0125
            java.lang.String r13 = r0.getString(r13)     // Catch:{ JSONException -> 0x00b2 }
            if (r13 == 0) goto L_0x011f
            goto L_0x012b
        L_0x011f:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x00b2 }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x00b2 }
            throw r0     // Catch:{ JSONException -> 0x00b2 }
        L_0x0125:
            java.lang.Object r13 = r0.get(r13)     // Catch:{ JSONException -> 0x00b2 }
            if (r13 == 0) goto L_0x0134
        L_0x012b:
            org.json.JSONObject r13 = (org.json.JSONObject) r13     // Catch:{ JSONException -> 0x00b2 }
        L_0x012d:
            r3.add(r13)     // Catch:{ JSONException -> 0x00b2 }
            r1 = r16
            goto L_0x0085
        L_0x0134:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x00b2 }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x00b2 }
            throw r0     // Catch:{ JSONException -> 0x00b2 }
        L_0x013a:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ JSONException -> 0x00b2 }
            r1.<init>()     // Catch:{ JSONException -> 0x00b2 }
            java.util.Iterator r2 = r3.iterator()     // Catch:{ JSONException -> 0x00b2 }
        L_0x0143:
            boolean r0 = r2.hasNext()     // Catch:{ JSONException -> 0x00b2 }
            if (r0 == 0) goto L_0x0166
            java.lang.Object r0 = r2.next()     // Catch:{ JSONException -> 0x00b2 }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x00b2 }
            com.salesforce.marketingcloud.messages.Message r3 = new com.salesforce.marketingcloud.messages.Message     // Catch:{ Exception -> 0x0155 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0155 }
            goto L_0x0160
        L_0x0155:
            r0 = move-exception
            com.salesforce.marketingcloud.g r3 = com.salesforce.marketingcloud.g.a     // Catch:{ JSONException -> 0x00b2 }
            java.lang.String r13 = TAG     // Catch:{ JSONException -> 0x00b2 }
            com.salesforce.marketingcloud.messages.Region$a r14 = com.salesforce.marketingcloud.messages.Region.a.a     // Catch:{ JSONException -> 0x00b2 }
            r3.b((java.lang.String) r13, (java.lang.Throwable) r0, (kotlin.jvm.functions.Function0) r14)     // Catch:{ JSONException -> 0x00b2 }
            r3 = 0
        L_0x0160:
            if (r3 == 0) goto L_0x0143
            r1.add(r3)     // Catch:{ JSONException -> 0x00b2 }
            goto L_0x0143
        L_0x0166:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ JSONException -> 0x00b2 }
            r0.<init>()     // Catch:{ JSONException -> 0x00b2 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ JSONException -> 0x00b2 }
        L_0x016f:
            boolean r2 = r1.hasNext()     // Catch:{ JSONException -> 0x00b2 }
            if (r2 == 0) goto L_0x0186
            java.lang.Object r2 = r1.next()     // Catch:{ JSONException -> 0x00b2 }
            r3 = r2
            com.salesforce.marketingcloud.messages.Message r3 = (com.salesforce.marketingcloud.messages.Message) r3     // Catch:{ JSONException -> 0x00b2 }
            boolean r3 = com.salesforce.marketingcloud.messages.b.a(r3)     // Catch:{ JSONException -> 0x00b2 }
            if (r3 == 0) goto L_0x016f
            r0.add(r2)     // Catch:{ JSONException -> 0x00b2 }
            goto L_0x016f
        L_0x0186:
            r13 = r0
            goto L_0x019b
        L_0x0188:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ JSONException -> 0x00b2 }
            goto L_0x0186
        L_0x018d:
            com.salesforce.marketingcloud.g r1 = com.salesforce.marketingcloud.g.a
            java.lang.String r2 = TAG
            com.salesforce.marketingcloud.messages.Region$b r3 = com.salesforce.marketingcloud.messages.Region.b.a
            r1.b((java.lang.String) r2, (java.lang.Throwable) r0, (kotlin.jvm.functions.Function0) r3)
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x0186
        L_0x019b:
            r3 = r17
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.Region.<init>(org.json.JSONObject):void");
    }
}
