package com.salesforce.marketingcloud.messages.inbox;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.internal.m;
import com.salesforce.marketingcloud.storage.db.i;
import java.util.Date;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2;
import org.json.JSONObject;

@MCKeep
public final class InboxMessage {
    private final String alert;
    private final String custom;
    private final Map<String, String> customKeys;
    private boolean deleted;
    private boolean dirty;
    private final Date endDateUtc;
    private final String id;
    private final Media media;
    private final String messageHash;
    private boolean read;
    private final String requestId;
    private final Date sendDateUtc;
    private final String sound;
    private final Date startDateUtc;
    private final String subject;
    private final String title;
    private final String url;
    private final int viewCount;

    @MCKeep
    public static final class Media {
        private final String altText;
        private final String url;

        public Media(String str, String str2) {
            this.url = str;
            this.altText = str2;
        }

        public static /* synthetic */ Media copy$default(Media media, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = media.url;
            }
            if ((i & 2) != 0) {
                str2 = media.altText;
            }
            return media.copy(str, str2);
        }

        public final String altText() {
            return this.altText;
        }

        public final String component1() {
            return this.url;
        }

        public final String component2() {
            return this.altText;
        }

        public final Media copy(String str, String str2) {
            return new Media(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Media)) {
                return false;
            }
            Media media = (Media) obj;
            return Intrinsics.areEqual((Object) this.url, (Object) media.url) && Intrinsics.areEqual((Object) this.altText, (Object) media.altText);
        }

        public final String getAltText() {
            return this.altText;
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            String str = this.url;
            int i = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.altText;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            String str = this.url;
            String str2 = this.altText;
            return "Media(url=" + str + ", altText=" + str2 + ")";
        }

        public final String url() {
            return this.url;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InboxMessage(android.os.Bundle r21) {
        /*
            r20 = this;
            r0 = r21
            java.lang.String r1 = "bundle"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "_m"
            java.lang.String r3 = r0.getString(r1)
            java.lang.String r1 = "Required value was null."
            if (r3 == 0) goto L_0x00e9
            java.lang.String r2 = "title"
            java.lang.String r7 = r0.getString(r2)
            java.lang.String r2 = "alert"
            java.lang.String r8 = r0.getString(r2)
            java.lang.String r2 = "sound"
            java.lang.String r9 = r0.getString(r2)
            java.lang.String r2 = "_h"
            java.lang.String r5 = r0.getString(r2)
            java.lang.String r2 = "_r"
            java.lang.String r4 = r0.getString(r2)
            java.lang.String r2 = "_x"
            java.lang.String r14 = r0.getString(r2)
            if (r14 == 0) goto L_0x00e3
            java.lang.String r1 = "_mediaUrl"
            java.lang.String r1 = r0.getString(r1)
            java.lang.String r2 = "_mediaAlt"
            java.lang.String r2 = r0.getString(r2)
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            r10 = 0
            if (r6 != 0) goto L_0x0051
            com.salesforce.marketingcloud.messages.inbox.InboxMessage$Media r6 = new com.salesforce.marketingcloud.messages.inbox.InboxMessage$Media
            r6.<init>(r1, r2)
            r1 = r6
            goto L_0x0052
        L_0x0051:
            r1 = r10
        L_0x0052:
            java.util.Set r2 = r21.keySet()
            java.lang.String r6 = "keySet(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x0064:
            boolean r11 = r2.hasNext()
            if (r11 == 0) goto L_0x0096
            java.lang.Object r11 = r2.next()
            r12 = r11
            java.lang.String r12 = (java.lang.String) r12
            com.salesforce.marketingcloud.notifications.NotificationMessage$a r13 = com.salesforce.marketingcloud.notifications.NotificationMessage.Companion
            java.lang.String[] r13 = r13.a()
            boolean r13 = kotlin.collections.ArraysKt.contains(r13, r12)
            if (r13 == 0) goto L_0x0090
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
            r13 = 0
            r15 = 2
            r16 = r2
            java.lang.String r2 = ".google"
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r12, r2, r13, r15, r10)
            if (r2 == 0) goto L_0x008d
            goto L_0x0092
        L_0x008d:
            r2 = r16
            goto L_0x0064
        L_0x0090:
            r16 = r2
        L_0x0092:
            r6.add(r11)
            goto L_0x008d
        L_0x0096:
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r6, r2)
            int r2 = kotlin.collections.MapsKt.mapCapacity(r2)
            r11 = 16
            int r2 = kotlin.ranges.RangesKt.coerceAtLeast((int) r2, (int) r11)
            r10.<init>(r2)
            java.util.Iterator r2 = r6.iterator()
        L_0x00af:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x00c8
            java.lang.Object r6 = r2.next()
            r11 = r6
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r11 = r0.getString(r11)
            java.lang.String r11 = java.lang.String.valueOf(r11)
            r10.put(r6, r11)
            goto L_0x00af
        L_0x00c8:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            r18 = 22280(0x5708, float:3.1221E-41)
            r19 = 0
            r6 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r15 = 0
            r17 = 0
            r2 = r20
            r0 = r10
            r10 = r1
            r16 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return
        L_0x00e3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x00e9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.inbox.InboxMessage.<init>(android.os.Bundle):void");
    }

    public static /* synthetic */ InboxMessage copy$default(InboxMessage inboxMessage, String str, String str2, String str3, String str4, String str5, String str6, String str7, Media media2, Date date, Date date2, Date date3, String str8, String str9, Map map, int i, int i2, Object obj) {
        InboxMessage inboxMessage2 = inboxMessage;
        int i3 = i2;
        return inboxMessage.copy((i3 & 1) != 0 ? inboxMessage2.id : str, (i3 & 2) != 0 ? inboxMessage2.requestId : str2, (i3 & 4) != 0 ? inboxMessage2.messageHash : str3, (i3 & 8) != 0 ? inboxMessage2.subject : str4, (i3 & 16) != 0 ? inboxMessage2.title : str5, (i3 & 32) != 0 ? inboxMessage2.alert : str6, (i3 & 64) != 0 ? inboxMessage2.sound : str7, (i3 & 128) != 0 ? inboxMessage2.media : media2, (i3 & 256) != 0 ? inboxMessage2.startDateUtc : date, (i3 & 512) != 0 ? inboxMessage2.endDateUtc : date2, (i3 & 1024) != 0 ? inboxMessage2.sendDateUtc : date3, (i3 & b.u) != 0 ? inboxMessage2.url : str8, (i3 & b.v) != 0 ? inboxMessage2.custom : str9, (i3 & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0 ? inboxMessage2.customKeys : map, (i3 & Http2.INITIAL_MAX_FRAME_SIZE) != 0 ? inboxMessage2.viewCount : i);
    }

    /* renamed from: -deleted  reason: not valid java name */
    public final void m747deleted(boolean z) {
        this.deleted = z;
    }

    /* renamed from: -deprecated_alert  reason: not valid java name */
    public final String m748deprecated_alert() {
        return this.alert;
    }

    /* renamed from: -deprecated_custom  reason: not valid java name */
    public final String m749deprecated_custom() {
        return this.custom;
    }

    /* renamed from: -deprecated_customKeys  reason: not valid java name */
    public final Map<String, String> m750deprecated_customKeys() {
        return this.customKeys;
    }

    /* renamed from: -deprecated_deleted  reason: not valid java name */
    public final boolean m751deprecated_deleted() {
        return this.deleted;
    }

    /* renamed from: -deprecated_endDateUtc  reason: not valid java name */
    public final Date m752deprecated_endDateUtc() {
        return this.endDateUtc;
    }

    /* renamed from: -deprecated_id  reason: not valid java name */
    public final String m753deprecated_id() {
        return this.id;
    }

    /* renamed from: -deprecated_media  reason: not valid java name */
    public final Media m754deprecated_media() {
        return this.media;
    }

    /* renamed from: -deprecated_read  reason: not valid java name */
    public final boolean m755deprecated_read() {
        return this.read;
    }

    /* renamed from: -deprecated_sendDateUtc  reason: not valid java name */
    public final Date m756deprecated_sendDateUtc() {
        return this.sendDateUtc;
    }

    /* renamed from: -deprecated_sound  reason: not valid java name */
    public final String m757deprecated_sound() {
        return this.sound;
    }

    /* renamed from: -deprecated_startDateUtc  reason: not valid java name */
    public final Date m758deprecated_startDateUtc() {
        return this.startDateUtc;
    }

    /* renamed from: -deprecated_subject  reason: not valid java name */
    public final String m759deprecated_subject() {
        return this.subject;
    }

    /* renamed from: -deprecated_title  reason: not valid java name */
    public final String m760deprecated_title() {
        return this.title;
    }

    /* renamed from: -deprecated_url  reason: not valid java name */
    public final String m761deprecated_url() {
        return this.url;
    }

    /* renamed from: -dirty  reason: not valid java name */
    public final void m762dirty(boolean z) {
        this.dirty = z;
    }

    /* renamed from: -messageHash  reason: not valid java name */
    public final String m764messageHash() {
        return this.messageHash;
    }

    /* renamed from: -read  reason: not valid java name */
    public final void m765read(boolean z) {
        this.read = z;
    }

    /* renamed from: -requestId  reason: not valid java name */
    public final String m766requestId() {
        return this.requestId;
    }

    /* renamed from: -viewCount  reason: not valid java name */
    public final int m767viewCount() {
        return this.viewCount;
    }

    public final String alert() {
        return this.alert;
    }

    public final String component1() {
        return this.id;
    }

    public final Date component10() {
        return this.endDateUtc;
    }

    public final Date component11() {
        return this.sendDateUtc;
    }

    public final String component12() {
        return this.url;
    }

    public final String component13() {
        return this.custom;
    }

    public final Map<String, String> component14() {
        return this.customKeys;
    }

    public final int component15$sdk_release() {
        return this.viewCount;
    }

    public final String component2$sdk_release() {
        return this.requestId;
    }

    public final String component3$sdk_release() {
        return this.messageHash;
    }

    public final String component4() {
        return this.subject;
    }

    public final String component5() {
        return this.title;
    }

    public final String component6() {
        return this.alert;
    }

    public final String component7() {
        return this.sound;
    }

    public final Media component8() {
        return this.media;
    }

    public final Date component9() {
        return this.startDateUtc;
    }

    public final InboxMessage copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, Media media2, Date date, Date date2, Date date3, String str8, String str9, Map<String, String> map, int i) {
        String str10 = str;
        Intrinsics.checkNotNullParameter(str10, "id");
        String str11 = str8;
        Intrinsics.checkNotNullParameter(str11, "url");
        return new InboxMessage(str10, str2, str3, str4, str5, str6, str7, media2, date, date2, date3, str11, str9, map, i);
    }

    public final String custom() {
        return this.custom;
    }

    public final Map<String, String> customKeys() {
        return this.customKeys;
    }

    public final boolean deleted() {
        return this.deleted;
    }

    public final Date endDateUtc() {
        return this.endDateUtc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InboxMessage)) {
            return false;
        }
        InboxMessage inboxMessage = (InboxMessage) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) inboxMessage.id) && Intrinsics.areEqual((Object) this.requestId, (Object) inboxMessage.requestId) && Intrinsics.areEqual((Object) this.messageHash, (Object) inboxMessage.messageHash) && Intrinsics.areEqual((Object) this.subject, (Object) inboxMessage.subject) && Intrinsics.areEqual((Object) this.title, (Object) inboxMessage.title) && Intrinsics.areEqual((Object) this.alert, (Object) inboxMessage.alert) && Intrinsics.areEqual((Object) this.sound, (Object) inboxMessage.sound) && Intrinsics.areEqual((Object) this.media, (Object) inboxMessage.media) && Intrinsics.areEqual((Object) this.startDateUtc, (Object) inboxMessage.startDateUtc) && Intrinsics.areEqual((Object) this.endDateUtc, (Object) inboxMessage.endDateUtc) && Intrinsics.areEqual((Object) this.sendDateUtc, (Object) inboxMessage.sendDateUtc) && Intrinsics.areEqual((Object) this.url, (Object) inboxMessage.url) && Intrinsics.areEqual((Object) this.custom, (Object) inboxMessage.custom) && Intrinsics.areEqual((Object) this.customKeys, (Object) inboxMessage.customKeys) && this.viewCount == inboxMessage.viewCount;
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        String str = this.requestId;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.messageHash;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.subject;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.title;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.alert;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.sound;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Media media2 = this.media;
        int hashCode8 = (hashCode7 + (media2 == null ? 0 : media2.hashCode())) * 31;
        Date date = this.startDateUtc;
        int hashCode9 = (hashCode8 + (date == null ? 0 : date.hashCode())) * 31;
        Date date2 = this.endDateUtc;
        int hashCode10 = (hashCode9 + (date2 == null ? 0 : date2.hashCode())) * 31;
        Date date3 = this.sendDateUtc;
        int hashCode11 = (((hashCode10 + (date3 == null ? 0 : date3.hashCode())) * 31) + this.url.hashCode()) * 31;
        String str7 = this.custom;
        int hashCode12 = (hashCode11 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Map<String, String> map = this.customKeys;
        if (map != null) {
            i = map.hashCode();
        }
        return ((hashCode12 + i) * 31) + Integer.hashCode(this.viewCount);
    }

    public final String id() {
        return this.id;
    }

    public final Media media() {
        return this.media;
    }

    public final boolean read() {
        return this.read;
    }

    public final Date sendDateUtc() {
        return this.sendDateUtc;
    }

    public final String sound() {
        return this.sound;
    }

    public final Date startDateUtc() {
        return this.startDateUtc;
    }

    public final String subject() {
        return this.subject;
    }

    public final String title() {
        return this.title;
    }

    public final JSONObject toJson$sdk_release() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        String str = this.messageHash;
        if (str != null) {
            jSONObject.put("hash", str);
        }
        String str2 = this.requestId;
        if (str2 != null) {
            jSONObject.put("requestId", str2);
        }
        String str3 = this.subject;
        if (str3 != null) {
            jSONObject.put("subject", str3);
        }
        String str4 = this.title;
        if (str4 != null) {
            jSONObject.put("title", str4);
        }
        String str5 = this.alert;
        if (str5 != null) {
            jSONObject.put("alert", str5);
        }
        String str6 = this.sound;
        if (str6 != null) {
            jSONObject.put("sound", str6);
        }
        Media media2 = this.media;
        if (media2 != null) {
            jSONObject.put("media", b.a(media2));
        }
        Date date = this.startDateUtc;
        if (date != null) {
            jSONObject.put("startDateUtc", m.a(date));
        }
        Date date2 = this.endDateUtc;
        if (date2 != null) {
            jSONObject.put("endDateUtc", m.a(date2));
        }
        Date date3 = this.sendDateUtc;
        if (date3 != null) {
            jSONObject.put("sendDateUtc", m.a(date3));
        }
        jSONObject.put("url", this.url);
        String str7 = this.custom;
        if (str7 != null) {
            jSONObject.put(i.a.m, str7);
        }
        Map<String, String> map = this.customKeys;
        if (map != null) {
            jSONObject.put("keys", m.a(map));
        }
        jSONObject.put("viewCount", this.viewCount);
        return jSONObject;
    }

    public final String toJsonString() {
        String jSONObject = toJson$sdk_release().toString(2);
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        return jSONObject;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.requestId;
        String str3 = this.messageHash;
        String str4 = this.subject;
        String str5 = this.title;
        String str6 = this.alert;
        String str7 = this.sound;
        Media media2 = this.media;
        Date date = this.startDateUtc;
        Date date2 = this.endDateUtc;
        Date date3 = this.sendDateUtc;
        String str8 = this.url;
        String str9 = this.custom;
        Map<String, String> map = this.customKeys;
        int i = this.viewCount;
        return "InboxMessage(id=" + str + ", requestId=" + str2 + ", messageHash=" + str3 + ", subject=" + str4 + ", title=" + str5 + ", alert=" + str6 + ", sound=" + str7 + ", media=" + media2 + ", startDateUtc=" + date + ", endDateUtc=" + date2 + ", sendDateUtc=" + date3 + ", url=" + str8 + ", custom=" + str9 + ", customKeys=" + map + ", viewCount=" + i + ")";
    }

    public final String url() {
        return this.url;
    }

    public InboxMessage(String str, String str2, String str3, String str4, String str5, String str6, String str7, Media media2, Date date, Date date2, Date date3, String str8, String str9, Map<String, String> map, int i) {
        String str10 = str8;
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str10, "url");
        this.id = str;
        this.requestId = str2;
        this.messageHash = str3;
        this.subject = str4;
        this.title = str5;
        this.alert = str6;
        this.sound = str7;
        this.media = media2;
        this.startDateUtc = date;
        this.endDateUtc = date2;
        this.sendDateUtc = date3;
        this.url = str10;
        this.custom = str9;
        this.customKeys = map;
        this.viewCount = i;
    }

    /* renamed from: -dirty  reason: not valid java name */
    public final boolean m763dirty() {
        return this.dirty;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ InboxMessage(java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, com.salesforce.marketingcloud.messages.inbox.InboxMessage.Media r27, java.util.Date r28, java.util.Date r29, java.util.Date r30, java.lang.String r31, java.lang.String r32, java.util.Map r33, int r34, int r35, kotlin.jvm.internal.DefaultConstructorMarker r36) {
        /*
            r19 = this;
            r0 = r35
            r1 = r0 & 2
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r5 = r2
            goto L_0x000b
        L_0x0009:
            r5 = r21
        L_0x000b:
            r1 = r0 & 4
            if (r1 == 0) goto L_0x0011
            r6 = r2
            goto L_0x0013
        L_0x0011:
            r6 = r22
        L_0x0013:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0019
            r7 = r2
            goto L_0x001b
        L_0x0019:
            r7 = r23
        L_0x001b:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0021
            r8 = r2
            goto L_0x0023
        L_0x0021:
            r8 = r24
        L_0x0023:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0029
            r9 = r2
            goto L_0x002b
        L_0x0029:
            r9 = r25
        L_0x002b:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0031
            r10 = r2
            goto L_0x0033
        L_0x0031:
            r10 = r26
        L_0x0033:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0039
            r11 = r2
            goto L_0x003b
        L_0x0039:
            r11 = r27
        L_0x003b:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0041
            r12 = r2
            goto L_0x0043
        L_0x0041:
            r12 = r28
        L_0x0043:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0049
            r13 = r2
            goto L_0x004b
        L_0x0049:
            r13 = r29
        L_0x004b:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L_0x0051
            r14 = r2
            goto L_0x0053
        L_0x0051:
            r14 = r30
        L_0x0053:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x005a
            r16 = r2
            goto L_0x005c
        L_0x005a:
            r16 = r32
        L_0x005c:
            r1 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r1 == 0) goto L_0x0063
            r17 = r2
            goto L_0x0065
        L_0x0063:
            r17 = r33
        L_0x0065:
            r0 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r0 == 0) goto L_0x006d
            r0 = 0
            r18 = r0
            goto L_0x006f
        L_0x006d:
            r18 = r34
        L_0x006f:
            r3 = r19
            r4 = r20
            r15 = r31
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.inbox.InboxMessage.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.salesforce.marketingcloud.messages.inbox.InboxMessage$Media, java.util.Date, java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.util.Map, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InboxMessage(org.json.JSONObject r19) throws org.json.JSONException {
        /*
            r18 = this;
            r0 = r19
            java.lang.String r1 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "id"
            java.lang.String r3 = r0.getString(r1)
            java.lang.String r1 = "hash"
            java.lang.String r1 = r0.optString(r1)
            java.lang.String r2 = "optString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r5 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r1)
            java.lang.String r1 = "requestId"
            java.lang.String r1 = r0.optString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r4 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r1)
            java.lang.String r1 = "subject"
            java.lang.String r1 = r0.optString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r6 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r1)
            java.lang.String r1 = "title"
            java.lang.String r1 = r0.optString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r7 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r1)
            java.lang.String r1 = "alert"
            java.lang.String r1 = r0.optString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r8 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r1)
            java.lang.String r1 = "sound"
            java.lang.String r1 = r0.optString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r9 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r1)
            java.lang.String r1 = "media"
            org.json.JSONObject r1 = r0.optJSONObject(r1)
            r10 = 0
            if (r1 == 0) goto L_0x006b
            com.salesforce.marketingcloud.messages.inbox.InboxMessage$Media r1 = com.salesforce.marketingcloud.messages.inbox.b.a((org.json.JSONObject) r1)
            goto L_0x006c
        L_0x006b:
            r1 = r10
        L_0x006c:
            java.lang.String r11 = "startDateUtc"
            java.lang.String r11 = r0.optString(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)
            java.lang.String r11 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r11)
            if (r11 == 0) goto L_0x0081
            java.util.Date r11 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r11)
            if (r11 != 0) goto L_0x0086
        L_0x0081:
            java.util.Date r11 = new java.util.Date
            r11.<init>()
        L_0x0086:
            java.lang.String r12 = "endDateUtc"
            java.lang.String r12 = r0.optString(r12)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r2)
            java.lang.String r12 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r12)
            if (r12 == 0) goto L_0x009a
            java.util.Date r12 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r12)
            goto L_0x009b
        L_0x009a:
            r12 = r10
        L_0x009b:
            java.lang.String r13 = "sendDateUtc"
            java.lang.String r13 = r0.optString(r13)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            java.lang.String r13 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r13)
            if (r13 == 0) goto L_0x00af
            java.util.Date r13 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r13)
            goto L_0x00b0
        L_0x00af:
            r13 = r10
        L_0x00b0:
            java.lang.String r14 = "url"
            java.lang.String r14 = r0.getString(r14)
            java.lang.String r15 = "custom"
            java.lang.String r15 = r0.optString(r15)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r2)
            java.lang.String r15 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r15)
            java.lang.String r2 = "keys"
            org.json.JSONArray r2 = r0.optJSONArray(r2)
            if (r2 == 0) goto L_0x00d2
            java.util.Map r2 = com.salesforce.marketingcloud.internal.m.b((org.json.JSONArray) r2)
            r16 = r2
            goto L_0x00d4
        L_0x00d2:
            r16 = r10
        L_0x00d4:
            java.lang.String r2 = "viewCount"
            r10 = 0
            int r17 = r0.optInt(r2, r10)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            r2 = r18
            r10 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.inbox.InboxMessage.<init>(org.json.JSONObject):void");
    }
}
