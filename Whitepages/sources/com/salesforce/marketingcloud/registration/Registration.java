package com.salesforce.marketingcloud.registration;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.internal.m;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2;
import org.json.JSONArray;
import org.json.JSONObject;

@MCKeep
public final class Registration {
    private final String appId;
    private final String appVersion;
    private final Map<String, String> attributes;
    private final String contactKey;
    private final String deviceId;
    private final boolean dst;
    private final String hwid;
    private int id;
    private final String locale;
    private final boolean locationEnabled;
    private final String platform;
    private final String platformVersion;
    private final boolean proximityEnabled;
    private final boolean pushEnabled;
    private final String sdkVersion;
    private final String signedString;
    private final String systemToken;
    private final Set<String> tags;
    private final int timeZone;

    public Registration(int i, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, boolean z4, int i2, String str7, String str8, String str9, String str10, String str11, Set<String> set, Map<String, String> map) {
        String str12 = str4;
        String str13 = str5;
        String str14 = str6;
        String str15 = str8;
        String str16 = str9;
        String str17 = str10;
        String str18 = str11;
        Set<String> set2 = set;
        Map<String, String> map2 = map;
        Intrinsics.checkNotNullParameter(str2, "deviceId");
        Intrinsics.checkNotNullParameter(str12, RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        Intrinsics.checkNotNullParameter(str13, RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        Intrinsics.checkNotNullParameter(str14, RemoteConfigConstants.RequestFieldKey.PLATFORM_VERSION);
        Intrinsics.checkNotNullParameter(str15, k.a.b);
        Intrinsics.checkNotNullParameter(str16, k.a.m);
        Intrinsics.checkNotNullParameter(str17, RemoteConfigConstants.RequestFieldKey.APP_ID);
        Intrinsics.checkNotNullParameter(str18, "locale");
        Intrinsics.checkNotNullParameter(set2, k.a.g);
        Intrinsics.checkNotNullParameter(map2, k.a.h);
        this.id = i;
        this.signedString = str;
        this.deviceId = str2;
        this.systemToken = str3;
        this.sdkVersion = str12;
        this.appVersion = str13;
        this.dst = z;
        this.locationEnabled = z2;
        this.proximityEnabled = z3;
        this.platformVersion = str14;
        this.pushEnabled = z4;
        this.timeZone = i2;
        this.contactKey = str7;
        this.platform = str15;
        this.hwid = str16;
        this.appId = str17;
        this.locale = str18;
        this.tags = set2;
        this.attributes = map2;
    }

    public static /* synthetic */ Registration copy$default(Registration registration, int i, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, boolean z4, int i2, String str7, String str8, String str9, String str10, String str11, Set set, Map map, int i3, Object obj) {
        Registration registration2 = registration;
        int i4 = i3;
        return registration.copy((i4 & 1) != 0 ? registration2.id : i, (i4 & 2) != 0 ? registration2.signedString : str, (i4 & 4) != 0 ? registration2.deviceId : str2, (i4 & 8) != 0 ? registration2.systemToken : str3, (i4 & 16) != 0 ? registration2.sdkVersion : str4, (i4 & 32) != 0 ? registration2.appVersion : str5, (i4 & 64) != 0 ? registration2.dst : z, (i4 & 128) != 0 ? registration2.locationEnabled : z2, (i4 & 256) != 0 ? registration2.proximityEnabled : z3, (i4 & 512) != 0 ? registration2.platformVersion : str6, (i4 & 1024) != 0 ? registration2.pushEnabled : z4, (i4 & b.u) != 0 ? registration2.timeZone : i2, (i4 & b.v) != 0 ? registration2.contactKey : str7, (i4 & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0 ? registration2.platform : str8, (i4 & Http2.INITIAL_MAX_FRAME_SIZE) != 0 ? registration2.hwid : str9, (i4 & 32768) != 0 ? registration2.appId : str10, (i4 & 65536) != 0 ? registration2.locale : str11, (i4 & 131072) != 0 ? registration2.tags : set, (i4 & 262144) != 0 ? registration2.attributes : map);
    }

    /* renamed from: -deprecated_appId  reason: not valid java name */
    public final String m788deprecated_appId() {
        return this.appId;
    }

    /* renamed from: -deprecated_appVersion  reason: not valid java name */
    public final String m789deprecated_appVersion() {
        return this.appVersion;
    }

    /* renamed from: -deprecated_attributes  reason: not valid java name */
    public final Map<String, String> m790deprecated_attributes() {
        return this.attributes;
    }

    /* renamed from: -deprecated_contactKey  reason: not valid java name */
    public final String m791deprecated_contactKey() {
        return this.contactKey;
    }

    /* renamed from: -deprecated_deviceId  reason: not valid java name */
    public final String m792deprecated_deviceId() {
        return this.deviceId;
    }

    /* renamed from: -deprecated_hwid  reason: not valid java name */
    public final String m793deprecated_hwid() {
        return this.hwid;
    }

    /* renamed from: -deprecated_locale  reason: not valid java name */
    public final String m794deprecated_locale() {
        return this.locale;
    }

    /* renamed from: -deprecated_locationEnabled  reason: not valid java name */
    public final boolean m795deprecated_locationEnabled() {
        return this.locationEnabled;
    }

    /* renamed from: -deprecated_platform  reason: not valid java name */
    public final String m796deprecated_platform() {
        return this.platform;
    }

    /* renamed from: -deprecated_platformVersion  reason: not valid java name */
    public final String m797deprecated_platformVersion() {
        return this.platformVersion;
    }

    /* renamed from: -deprecated_proximityEnabled  reason: not valid java name */
    public final boolean m798deprecated_proximityEnabled() {
        return this.proximityEnabled;
    }

    /* renamed from: -deprecated_pushEnabled  reason: not valid java name */
    public final boolean m799deprecated_pushEnabled() {
        return this.pushEnabled;
    }

    /* renamed from: -deprecated_sdkVersion  reason: not valid java name */
    public final String m800deprecated_sdkVersion() {
        return this.sdkVersion;
    }

    /* renamed from: -deprecated_signedString  reason: not valid java name */
    public final String m801deprecated_signedString() {
        return this.signedString;
    }

    /* renamed from: -deprecated_systemToken  reason: not valid java name */
    public final String m802deprecated_systemToken() {
        return this.systemToken;
    }

    /* renamed from: -deprecated_tags  reason: not valid java name */
    public final Set<String> m803deprecated_tags() {
        return this.tags;
    }

    /* renamed from: -deprecated_timeZone  reason: not valid java name */
    public final int m804deprecated_timeZone() {
        return this.timeZone;
    }

    /* renamed from: -id  reason: not valid java name */
    public final int m805id() {
        return this.id;
    }

    public final String appId() {
        return this.appId;
    }

    public final String appVersion() {
        return this.appVersion;
    }

    public final Map<String, String> attributes() {
        return this.attributes;
    }

    public final int component1$sdk_release() {
        return this.id;
    }

    public final String component10() {
        return this.platformVersion;
    }

    public final boolean component11() {
        return this.pushEnabled;
    }

    public final int component12() {
        return this.timeZone;
    }

    public final String component13() {
        return this.contactKey;
    }

    public final String component14() {
        return this.platform;
    }

    public final String component15() {
        return this.hwid;
    }

    public final String component16() {
        return this.appId;
    }

    public final String component17() {
        return this.locale;
    }

    public final Set<String> component18() {
        return this.tags;
    }

    public final Map<String, String> component19() {
        return this.attributes;
    }

    public final String component2() {
        return this.signedString;
    }

    public final String component3() {
        return this.deviceId;
    }

    public final String component4() {
        return this.systemToken;
    }

    public final String component5() {
        return this.sdkVersion;
    }

    public final String component6() {
        return this.appVersion;
    }

    public final boolean component7() {
        return this.dst;
    }

    public final boolean component8() {
        return this.locationEnabled;
    }

    public final boolean component9() {
        return this.proximityEnabled;
    }

    public final String contactKey() {
        return this.contactKey;
    }

    public final Registration copy(int i, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, boolean z4, int i2, String str7, String str8, String str9, String str10, String str11, Set<String> set, Map<String, String> map) {
        int i3 = i;
        Intrinsics.checkNotNullParameter(str2, "deviceId");
        Intrinsics.checkNotNullParameter(str4, RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        Intrinsics.checkNotNullParameter(str5, RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        Intrinsics.checkNotNullParameter(str6, RemoteConfigConstants.RequestFieldKey.PLATFORM_VERSION);
        Intrinsics.checkNotNullParameter(str8, k.a.b);
        Intrinsics.checkNotNullParameter(str9, k.a.m);
        Intrinsics.checkNotNullParameter(str10, RemoteConfigConstants.RequestFieldKey.APP_ID);
        Intrinsics.checkNotNullParameter(str11, "locale");
        Intrinsics.checkNotNullParameter(set, k.a.g);
        Intrinsics.checkNotNullParameter(map, k.a.h);
        return new Registration(i, str, str2, str3, str4, str5, z, z2, z3, str6, z4, i2, str7, str8, str9, str10, str11, set, map);
    }

    public final String deviceId() {
        return this.deviceId;
    }

    public final boolean dst() {
        return this.dst;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Registration)) {
            return false;
        }
        Registration registration = (Registration) obj;
        return this.id == registration.id && Intrinsics.areEqual((Object) this.signedString, (Object) registration.signedString) && Intrinsics.areEqual((Object) this.deviceId, (Object) registration.deviceId) && Intrinsics.areEqual((Object) this.systemToken, (Object) registration.systemToken) && Intrinsics.areEqual((Object) this.sdkVersion, (Object) registration.sdkVersion) && Intrinsics.areEqual((Object) this.appVersion, (Object) registration.appVersion) && this.dst == registration.dst && this.locationEnabled == registration.locationEnabled && this.proximityEnabled == registration.proximityEnabled && Intrinsics.areEqual((Object) this.platformVersion, (Object) registration.platformVersion) && this.pushEnabled == registration.pushEnabled && this.timeZone == registration.timeZone && Intrinsics.areEqual((Object) this.contactKey, (Object) registration.contactKey) && Intrinsics.areEqual((Object) this.platform, (Object) registration.platform) && Intrinsics.areEqual((Object) this.hwid, (Object) registration.hwid) && Intrinsics.areEqual((Object) this.appId, (Object) registration.appId) && Intrinsics.areEqual((Object) this.locale, (Object) registration.locale) && Intrinsics.areEqual((Object) this.tags, (Object) registration.tags) && Intrinsics.areEqual((Object) this.attributes, (Object) registration.attributes);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.id) * 31;
        String str = this.signedString;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.deviceId.hashCode()) * 31;
        String str2 = this.systemToken;
        int hashCode3 = (((((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.sdkVersion.hashCode()) * 31) + this.appVersion.hashCode()) * 31;
        boolean z = this.dst;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i2 = (hashCode3 + (z ? 1 : 0)) * 31;
        boolean z3 = this.locationEnabled;
        if (z3) {
            z3 = true;
        }
        int i3 = (i2 + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.proximityEnabled;
        if (z4) {
            z4 = true;
        }
        int hashCode4 = (((i3 + (z4 ? 1 : 0)) * 31) + this.platformVersion.hashCode()) * 31;
        boolean z5 = this.pushEnabled;
        if (!z5) {
            z2 = z5;
        }
        int hashCode5 = (((hashCode4 + (z2 ? 1 : 0)) * 31) + Integer.hashCode(this.timeZone)) * 31;
        String str3 = this.contactKey;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return ((((((((((((hashCode5 + i) * 31) + this.platform.hashCode()) * 31) + this.hwid.hashCode()) * 31) + this.appId.hashCode()) * 31) + this.locale.hashCode()) * 31) + this.tags.hashCode()) * 31) + this.attributes.hashCode();
    }

    public final String hwid() {
        return this.hwid;
    }

    public final String locale() {
        return this.locale;
    }

    public final boolean locationEnabled() {
        return this.locationEnabled;
    }

    public final String platform() {
        return this.platform;
    }

    public final String platformVersion() {
        return this.platformVersion;
    }

    public final boolean proximityEnabled() {
        return this.proximityEnabled;
    }

    public final boolean pushEnabled() {
        return this.pushEnabled;
    }

    public final String sdkVersion() {
        return this.sdkVersion;
    }

    public final void setId$sdk_release(int i) {
        this.id = i;
    }

    public final String signedString() {
        return this.signedString;
    }

    public final String systemToken() {
        return this.systemToken;
    }

    public final Set<String> tags() {
        return this.tags;
    }

    public final int timeZone() {
        return this.timeZone;
    }

    public final JSONObject toJson$sdk_release() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("signedString", this.signedString);
        jSONObject.put("deviceID", this.deviceId);
        String str = this.systemToken;
        if (str != null) {
            jSONObject.put("device_Token", str);
        }
        jSONObject.put("sdk_Version", this.sdkVersion);
        jSONObject.put("app_Version", this.appVersion);
        jSONObject.put("dST", this.dst);
        jSONObject.put("location_Enabled", this.locationEnabled);
        jSONObject.put("proximity_Enabled", this.proximityEnabled);
        jSONObject.put("platform_Version", this.platformVersion);
        jSONObject.put("push_Enabled", this.pushEnabled);
        jSONObject.put(RemoteConfigConstants.RequestFieldKey.TIME_ZONE, String.valueOf(this.timeZone));
        String str2 = this.contactKey;
        if (str2 != null) {
            jSONObject.put("subscriberKey", str2);
        }
        jSONObject.put(k.a.b, this.platform);
        jSONObject.put(k.a.m, this.hwid);
        jSONObject.put("etAppId", this.appId);
        jSONObject.put("locale", this.locale);
        jSONObject.put(k.a.g, new JSONArray(new TreeSet(this.tags)));
        jSONObject.put(k.a.h, m.a((Map<String, String>) MapsKt.toSortedMap(this.attributes)));
        return jSONObject;
    }

    public String toString() {
        int i = this.id;
        String str = this.signedString;
        String str2 = this.deviceId;
        String str3 = this.systemToken;
        String str4 = this.sdkVersion;
        String str5 = this.appVersion;
        boolean z = this.dst;
        boolean z2 = this.locationEnabled;
        boolean z3 = this.proximityEnabled;
        String str6 = this.platformVersion;
        boolean z4 = this.pushEnabled;
        int i2 = this.timeZone;
        String str7 = this.contactKey;
        String str8 = this.platform;
        String str9 = this.hwid;
        String str10 = this.appId;
        String str11 = this.locale;
        Set<String> set = this.tags;
        Map<String, String> map = this.attributes;
        return "Registration(id=" + i + ", signedString=" + str + ", deviceId=" + str2 + ", systemToken=" + str3 + ", sdkVersion=" + str4 + ", appVersion=" + str5 + ", dst=" + z + ", locationEnabled=" + z2 + ", proximityEnabled=" + z3 + ", platformVersion=" + str6 + ", pushEnabled=" + z4 + ", timeZone=" + i2 + ", contactKey=" + str7 + ", platform=" + str8 + ", hwid=" + str9 + ", appId=" + str10 + ", locale=" + str11 + ", tags=" + set + ", attributes=" + map + ")";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Registration(int r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, boolean r29, boolean r30, boolean r31, java.lang.String r32, boolean r33, int r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, java.lang.String r38, java.lang.String r39, java.util.Set r40, java.util.Map r41, int r42, kotlin.jvm.internal.DefaultConstructorMarker r43) {
        /*
            r22 = this;
            r0 = r42
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0009
            r1 = 0
            r3 = r1
            goto L_0x000b
        L_0x0009:
            r3 = r23
        L_0x000b:
            r1 = r0 & 8
            r2 = 0
            if (r1 == 0) goto L_0x0012
            r6 = r2
            goto L_0x0014
        L_0x0012:
            r6 = r26
        L_0x0014:
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x001a
            r15 = r2
            goto L_0x001c
        L_0x001a:
            r15 = r35
        L_0x001c:
            r2 = r22
            r4 = r24
            r5 = r25
            r7 = r27
            r8 = r28
            r9 = r29
            r10 = r30
            r11 = r31
            r12 = r32
            r13 = r33
            r14 = r34
            r16 = r36
            r17 = r37
            r18 = r38
            r19 = r39
            r20 = r40
            r21 = r41
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.Registration.<init>(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, boolean, java.lang.String, boolean, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Set, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Registration(org.json.JSONObject r27) throws org.json.JSONException {
        /*
            r26 = this;
            r0 = r27
            java.lang.String r1 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "signedString"
            java.lang.String r1 = r0.optString(r1)
            java.lang.String r2 = "optString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r5 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r1)
            java.lang.String r1 = "deviceID"
            java.lang.String r6 = r0.getString(r1)
            java.lang.String r1 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)
            java.lang.String r3 = "device_Token"
            java.lang.String r3 = r0.optString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            java.lang.String r7 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r3)
            java.lang.String r3 = "sdk_Version"
            java.lang.String r8 = r0.getString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)
            java.lang.String r3 = "app_Version"
            java.lang.String r9 = r0.getString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)
            java.lang.String r3 = "dST"
            boolean r10 = r0.getBoolean(r3)
            java.lang.String r3 = "location_Enabled"
            boolean r11 = r0.getBoolean(r3)
            java.lang.String r3 = "proximity_Enabled"
            boolean r12 = r0.getBoolean(r3)
            java.lang.String r3 = "platform_Version"
            java.lang.String r13 = r0.getString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r1)
            java.lang.String r3 = "push_Enabled"
            boolean r14 = r0.getBoolean(r3)
            java.lang.String r3 = "timeZone"
            java.lang.String r3 = r0.getString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            int r15 = java.lang.Integer.parseInt(r3)
            java.lang.String r3 = "subscriberKey"
            java.lang.String r3 = r0.optString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            java.lang.String r16 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r3)
            java.lang.String r2 = "platform"
            java.lang.String r2 = r0.getString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            java.lang.String r3 = "hwid"
            java.lang.String r3 = r0.getString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            java.lang.String r4 = "etAppId"
            java.lang.String r4 = r0.getString(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r1)
            r17 = r3
            java.lang.String r3 = "locale"
            java.lang.String r3 = r0.getString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            java.lang.String r1 = "tags"
            org.json.JSONArray r1 = r0.getJSONArray(r1)
            r18 = r3
            java.lang.String r3 = "getJSONArray(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            r19 = r4
            int r4 = r1.length()
            r20 = r2
            r2 = 0
            kotlin.ranges.IntRange r2 = kotlin.ranges.RangesKt.until(r2, r4)
            java.util.ArrayList r4 = new java.util.ArrayList
            r23 = r15
            r15 = 10
            int r15 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r15)
            r4.<init>(r15)
            java.util.Iterator r2 = r2.iterator()
        L_0x00cc:
            boolean r15 = r2.hasNext()
            if (r15 == 0) goto L_0x0186
            r15 = r2
            kotlin.collections.IntIterator r15 = (kotlin.collections.IntIterator) r15
            int r15 = r15.nextInt()
            java.lang.Class<java.lang.String> r21 = java.lang.String.class
            r22 = r2
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r21)
            java.lang.Class<org.json.JSONObject> r24 = org.json.JSONObject.class
            r25 = r14
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r24)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r14)
            r24 = r13
            java.lang.String r13 = "null cannot be cast to non-null type kotlin.String"
            if (r14 == 0) goto L_0x0101
            org.json.JSONObject r2 = r1.getJSONObject(r15)
            if (r2 == 0) goto L_0x00fb
            goto L_0x0174
        L_0x00fb:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r13)
            throw r0
        L_0x0101:
            java.lang.Class r14 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x0118
            int r2 = r1.getInt(r15)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L_0x0115:
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x0175
        L_0x0118:
            java.lang.Class r14 = java.lang.Double.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x012d
            double r13 = r1.getDouble(r15)
            java.lang.Double r2 = java.lang.Double.valueOf(r13)
            goto L_0x0115
        L_0x012d:
            java.lang.Class r14 = java.lang.Long.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x0142
            long r13 = r1.getLong(r15)
            java.lang.Long r2 = java.lang.Long.valueOf(r13)
            goto L_0x0115
        L_0x0142:
            java.lang.Class r14 = java.lang.Boolean.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x0157
            boolean r2 = r1.getBoolean(r15)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            goto L_0x0115
        L_0x0157:
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r21)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r14)
            if (r2 == 0) goto L_0x016e
            java.lang.String r2 = r1.getString(r15)
            if (r2 == 0) goto L_0x0168
            goto L_0x0175
        L_0x0168:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r13)
            throw r0
        L_0x016e:
            java.lang.Object r2 = r1.get(r15)
            if (r2 == 0) goto L_0x0180
        L_0x0174:
            goto L_0x0115
        L_0x0175:
            r4.add(r2)
            r2 = r22
            r13 = r24
            r14 = r25
            goto L_0x00cc
        L_0x0180:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r13)
            throw r0
        L_0x0186:
            r24 = r13
            r25 = r14
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r2 = r4.iterator()
        L_0x0193:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x01aa
            java.lang.Object r4 = r2.next()
            r13 = r4
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x0193
            r1.add(r4)
            goto L_0x0193
        L_0x01aa:
            java.util.Set r21 = kotlin.collections.CollectionsKt.toSet(r1)
            java.lang.String r1 = "attributes"
            org.json.JSONArray r0 = r0.getJSONArray(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            java.util.Map r22 = com.salesforce.marketingcloud.internal.m.b((org.json.JSONArray) r0)
            r4 = 0
            r0 = r19
            r1 = r17
            r2 = r18
            r3 = r26
            r13 = r24
            r14 = r25
            r15 = r23
            r17 = r20
            r18 = r1
            r20 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.Registration.<init>(org.json.JSONObject):void");
    }
}
