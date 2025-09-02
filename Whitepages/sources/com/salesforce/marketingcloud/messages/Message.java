package com.salesforce.marketingcloud.messages;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.extensions.PushExtensionsKt;
import com.salesforce.marketingcloud.g;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2;
import org.json.JSONObject;

@MCKeep
public final class Message implements Parcelable {
    public static final int CONTENT_TYPE_ALERT = 1;
    public static final Parcelable.Creator<Message> CREATOR = new a();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int MESSAGE_TYPE_FENCE_ENTRY = 3;
    public static final int MESSAGE_TYPE_FENCE_EXIT = 4;
    public static final int MESSAGE_TYPE_NONE = 0;
    public static final int MESSAGE_TYPE_PROXIMITY = 5;
    public static final int PERIOD_TYPE_UNIT_DAY = 4;
    public static final int PERIOD_TYPE_UNIT_HOUR = 5;
    public static final int PERIOD_TYPE_UNIT_MONTH = 2;
    public static final int PERIOD_TYPE_UNIT_NONE = 0;
    public static final int PERIOD_TYPE_UNIT_WEEK = 3;
    public static final int PERIOD_TYPE_UNIT_YEAR = 1;
    public static final int PROXIMITY_UNKNOWN = 0;
    /* access modifiers changed from: private */
    public static final String TAG = g.a("Message");
    private final String alert;
    private final int contentType;
    private final String custom;
    private final Map<String, String> customKeys;
    private final Date endDateUtc;
    private final String id;
    private final boolean isRollingPeriod;
    private Date lastShownDate;
    private final Media media;
    private final int messageLimit;
    private final int messageType;
    private final int messagesPerPeriod;
    private Date nextAllowedShow;
    private int notificationId;
    private final int numberOfPeriods;
    private final String openDirect;
    private int periodShowCount;
    private final int periodType;
    private final int proximity;
    private int showCount;
    private final String sound;
    private final Date startDateUtc;
    private final String title;
    private final String url;

    @MCKeep
    public static final class Companion {
        private Companion() {
        }

        public final String getTAG$sdk_release() {
            return Message.TAG;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @MCKeep
    public static final class Media implements Parcelable {
        public static final Parcelable.Creator<Media> CREATOR = new b();
        public static final a Companion = new a((DefaultConstructorMarker) null);
        private final String altText;
        private final String url;

        public static final class a {
            private a() {
            }

            public final Media a(JSONObject jSONObject) {
                Media media = null;
                String stringOrNull = jSONObject != null ? PushExtensionsKt.getStringOrNull(jSONObject, "androidUrl") : null;
                String stringOrNull2 = jSONObject != null ? PushExtensionsKt.getStringOrNull(jSONObject, "alt") : null;
                if (!(stringOrNull == null && stringOrNull2 == null)) {
                    if (stringOrNull == null) {
                        stringOrNull = "";
                    }
                    media = new Media(stringOrNull, stringOrNull2);
                }
                return media;
            }

            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public static final class b implements Parcelable.Creator<Media> {
            /* renamed from: a */
            public final Media createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Media(parcel.readString(), parcel.readString());
            }

            /* renamed from: a */
            public final Media[] newArray(int i) {
                return new Media[i];
            }
        }

        public Media(String str, String str2) {
            this.url = str;
            this.altText = str2;
        }

        /* renamed from: -deprecated_altText  reason: not valid java name */
        public final String m679deprecated_altText() {
            return this.altText;
        }

        /* renamed from: -deprecated_url  reason: not valid java name */
        public final String m680deprecated_url() {
            return this.url;
        }

        public final String altText() {
            return this.altText;
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            String str = this.url;
            String str2 = this.altText;
            return "Media(url=" + str + ", altText=" + str2 + ")";
        }

        public final String url() {
            return this.url;
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.url);
            parcel.writeString(this.altText);
        }
    }

    @MCKeep
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessageType {
    }

    public static final class a implements Parcelable.Creator<Message> {
        /* renamed from: a */
        public final Message createFromParcel(Parcel parcel) {
            LinkedHashMap linkedHashMap;
            boolean z;
            Parcel parcel2 = parcel;
            Intrinsics.checkNotNullParameter(parcel2, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            Media createFromParcel = parcel.readInt() == 0 ? null : Media.CREATOR.createFromParcel(parcel2);
            Date date = (Date) parcel.readSerializable();
            Date date2 = (Date) parcel.readSerializable();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            String readString5 = parcel.readString();
            int readInt3 = parcel.readInt();
            int readInt4 = parcel.readInt();
            int readInt5 = parcel.readInt();
            boolean z2 = parcel.readInt() != 0;
            int readInt6 = parcel.readInt();
            int readInt7 = parcel.readInt();
            String readString6 = parcel.readString();
            if (parcel.readInt() == 0) {
                z = z2;
                linkedHashMap = null;
            } else {
                int readInt8 = parcel.readInt();
                linkedHashMap = new LinkedHashMap(readInt8);
                z = z2;
                int i = 0;
                while (i != readInt8) {
                    linkedHashMap.put(parcel.readString(), parcel.readString());
                    i++;
                    readInt8 = readInt8;
                    readInt5 = readInt5;
                }
            }
            return new Message(readString, readString2, readString3, readString4, createFromParcel, date, date2, readInt, readInt2, readString5, readInt3, readInt4, readInt5, z, readInt6, readInt7, readString6, linkedHashMap, parcel.readString());
        }

        /* renamed from: a */
        public final Message[] newArray(int i) {
            return new Message[i];
        }
    }

    /* renamed from: -lastShownDate$annotations  reason: not valid java name */
    public static /* synthetic */ void m646lastShownDate$annotations() {
    }

    /* renamed from: -nextAllowedShow$annotations  reason: not valid java name */
    public static /* synthetic */ void m647nextAllowedShow$annotations() {
    }

    /* renamed from: -notificationId$annotations  reason: not valid java name */
    public static /* synthetic */ void m648notificationId$annotations() {
    }

    /* renamed from: -periodShowCount$annotations  reason: not valid java name */
    public static /* synthetic */ void m649periodShowCount$annotations() {
    }

    /* renamed from: -showCount$annotations  reason: not valid java name */
    public static /* synthetic */ void m650showCount$annotations() {
    }

    public Message(String str, String str2, String str3, String str4, Media media2, Date date, Date date2, int i, int i2, String str5, int i3, int i4, int i5, boolean z, int i6, int i7, String str6, Map<String, String> map, String str7) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str3, "alert");
        this.id = str;
        this.title = str2;
        this.alert = str3;
        this.sound = str4;
        this.media = media2;
        this.startDateUtc = date;
        this.endDateUtc = date2;
        this.messageType = i;
        this.contentType = i2;
        this.url = str5;
        this.messagesPerPeriod = i3;
        this.numberOfPeriods = i4;
        this.periodType = i5;
        this.isRollingPeriod = z;
        this.messageLimit = i6;
        this.proximity = i7;
        this.openDirect = str6;
        this.customKeys = map;
        this.custom = str7;
        this.notificationId = -1;
    }

    public static /* synthetic */ Message copy$default(Message message, String str, String str2, String str3, String str4, Media media2, Date date, Date date2, int i, int i2, String str5, int i3, int i4, int i5, boolean z, int i6, int i7, String str6, Map map, String str7, int i8, Object obj) {
        Message message2 = message;
        int i9 = i8;
        return message.copy((i9 & 1) != 0 ? message2.id : str, (i9 & 2) != 0 ? message2.title : str2, (i9 & 4) != 0 ? message2.alert : str3, (i9 & 8) != 0 ? message2.sound : str4, (i9 & 16) != 0 ? message2.media : media2, (i9 & 32) != 0 ? message2.startDateUtc : date, (i9 & 64) != 0 ? message2.endDateUtc : date2, (i9 & 128) != 0 ? message2.messageType : i, (i9 & 256) != 0 ? message2.contentType : i2, (i9 & 512) != 0 ? message2.url : str5, (i9 & 1024) != 0 ? message2.messagesPerPeriod : i3, (i9 & b.u) != 0 ? message2.numberOfPeriods : i4, (i9 & b.v) != 0 ? message2.periodType : i5, (i9 & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0 ? message2.isRollingPeriod : z, (i9 & Http2.INITIAL_MAX_FRAME_SIZE) != 0 ? message2.messageLimit : i6, (i9 & 32768) != 0 ? message2.proximity : i7, (i9 & 65536) != 0 ? message2.openDirect : str6, (i9 & 131072) != 0 ? message2.customKeys : map, (i9 & 262144) != 0 ? message2.custom : str7);
    }

    /* renamed from: -deprecated_alert  reason: not valid java name */
    public final String m651deprecated_alert() {
        return this.alert;
    }

    /* renamed from: -deprecated_contentType  reason: not valid java name */
    public final int m652deprecated_contentType() {
        return this.contentType;
    }

    /* renamed from: -deprecated_custom  reason: not valid java name */
    public final String m653deprecated_custom() {
        return this.custom;
    }

    /* renamed from: -deprecated_customKeys  reason: not valid java name */
    public final Map<String, String> m654deprecated_customKeys() {
        return this.customKeys;
    }

    /* renamed from: -deprecated_endDateUtc  reason: not valid java name */
    public final Date m655deprecated_endDateUtc() {
        return this.endDateUtc;
    }

    /* renamed from: -deprecated_id  reason: not valid java name */
    public final String m656deprecated_id() {
        return this.id;
    }

    /* renamed from: -deprecated_media  reason: not valid java name */
    public final Media m657deprecated_media() {
        return this.media;
    }

    /* renamed from: -deprecated_messageLimit  reason: not valid java name */
    public final int m658deprecated_messageLimit() {
        return this.messageLimit;
    }

    /* renamed from: -deprecated_messageType  reason: not valid java name */
    public final int m659deprecated_messageType() {
        return this.messageType;
    }

    /* renamed from: -deprecated_messagesPerPeriod  reason: not valid java name */
    public final int m660deprecated_messagesPerPeriod() {
        return this.messagesPerPeriod;
    }

    /* renamed from: -deprecated_numberOfPeriods  reason: not valid java name */
    public final int m661deprecated_numberOfPeriods() {
        return this.numberOfPeriods;
    }

    /* renamed from: -deprecated_openDirect  reason: not valid java name */
    public final String m662deprecated_openDirect() {
        return this.openDirect;
    }

    /* renamed from: -deprecated_periodType  reason: not valid java name */
    public final int m663deprecated_periodType() {
        return this.periodType;
    }

    /* renamed from: -deprecated_proximity  reason: not valid java name */
    public final int m664deprecated_proximity() {
        return this.proximity;
    }

    /* renamed from: -deprecated_sound  reason: not valid java name */
    public final String m665deprecated_sound() {
        return this.sound;
    }

    /* renamed from: -deprecated_startDateUtc  reason: not valid java name */
    public final Date m666deprecated_startDateUtc() {
        return this.startDateUtc;
    }

    /* renamed from: -deprecated_title  reason: not valid java name */
    public final String m667deprecated_title() {
        return this.title;
    }

    /* renamed from: -deprecated_url  reason: not valid java name */
    public final String m668deprecated_url() {
        return this.url;
    }

    /* renamed from: -lastShownDate  reason: not valid java name */
    public final Date m669lastShownDate() {
        return this.lastShownDate;
    }

    /* renamed from: -nextAllowedShow  reason: not valid java name */
    public final Date m671nextAllowedShow() {
        return this.nextAllowedShow;
    }

    /* renamed from: -notificationId  reason: not valid java name */
    public final int m673notificationId() {
        return this.notificationId;
    }

    /* renamed from: -periodShowCount  reason: not valid java name */
    public final int m675periodShowCount() {
        return this.periodShowCount;
    }

    /* renamed from: -showCount  reason: not valid java name */
    public final int m677showCount() {
        return this.showCount;
    }

    public final String alert() {
        return this.alert;
    }

    public final String component1() {
        return this.id;
    }

    public final String component10() {
        return this.url;
    }

    public final int component11() {
        return this.messagesPerPeriod;
    }

    public final int component12() {
        return this.numberOfPeriods;
    }

    public final int component13() {
        return this.periodType;
    }

    public final boolean component14() {
        return this.isRollingPeriod;
    }

    public final int component15() {
        return this.messageLimit;
    }

    public final int component16() {
        return this.proximity;
    }

    public final String component17() {
        return this.openDirect;
    }

    public final Map<String, String> component18() {
        return this.customKeys;
    }

    public final String component19() {
        return this.custom;
    }

    public final String component2() {
        return this.title;
    }

    public final String component3() {
        return this.alert;
    }

    public final String component4() {
        return this.sound;
    }

    public final Media component5() {
        return this.media;
    }

    public final Date component6() {
        return this.startDateUtc;
    }

    public final Date component7() {
        return this.endDateUtc;
    }

    public final int component8() {
        return this.messageType;
    }

    public final int component9() {
        return this.contentType;
    }

    public final int contentType() {
        return this.contentType;
    }

    public final Message copy(String str, String str2, String str3, String str4, Media media2, Date date, Date date2, int i, int i2, String str5, int i3, int i4, int i5, boolean z, int i6, int i7, String str6, Map<String, String> map, String str7) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, "id");
        Intrinsics.checkNotNullParameter(str3, "alert");
        return new Message(str8, str2, str3, str4, media2, date, date2, i, i2, str5, i3, i4, i5, z, i6, i7, str6, map, str7);
    }

    public final String custom() {
        return this.custom;
    }

    public final Map<String, String> customKeys() {
        return this.customKeys;
    }

    public int describeContents() {
        return 0;
    }

    public final Date endDateUtc() {
        return this.endDateUtc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) message.id) && Intrinsics.areEqual((Object) this.title, (Object) message.title) && Intrinsics.areEqual((Object) this.alert, (Object) message.alert) && Intrinsics.areEqual((Object) this.sound, (Object) message.sound) && Intrinsics.areEqual((Object) this.media, (Object) message.media) && Intrinsics.areEqual((Object) this.startDateUtc, (Object) message.startDateUtc) && Intrinsics.areEqual((Object) this.endDateUtc, (Object) message.endDateUtc) && this.messageType == message.messageType && this.contentType == message.contentType && Intrinsics.areEqual((Object) this.url, (Object) message.url) && this.messagesPerPeriod == message.messagesPerPeriod && this.numberOfPeriods == message.numberOfPeriods && this.periodType == message.periodType && this.isRollingPeriod == message.isRollingPeriod && this.messageLimit == message.messageLimit && this.proximity == message.proximity && Intrinsics.areEqual((Object) this.openDirect, (Object) message.openDirect) && Intrinsics.areEqual((Object) this.customKeys, (Object) message.customKeys) && Intrinsics.areEqual((Object) this.custom, (Object) message.custom);
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        String str = this.title;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.alert.hashCode()) * 31;
        String str2 = this.sound;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Media media2 = this.media;
        int hashCode4 = (hashCode3 + (media2 == null ? 0 : media2.hashCode())) * 31;
        Date date = this.startDateUtc;
        int hashCode5 = (hashCode4 + (date == null ? 0 : date.hashCode())) * 31;
        Date date2 = this.endDateUtc;
        int hashCode6 = (((((hashCode5 + (date2 == null ? 0 : date2.hashCode())) * 31) + Integer.hashCode(this.messageType)) * 31) + Integer.hashCode(this.contentType)) * 31;
        String str3 = this.url;
        int hashCode7 = (((((((hashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31) + Integer.hashCode(this.messagesPerPeriod)) * 31) + Integer.hashCode(this.numberOfPeriods)) * 31) + Integer.hashCode(this.periodType)) * 31;
        boolean z = this.isRollingPeriod;
        if (z) {
            z = true;
        }
        int hashCode8 = (((((hashCode7 + (z ? 1 : 0)) * 31) + Integer.hashCode(this.messageLimit)) * 31) + Integer.hashCode(this.proximity)) * 31;
        String str4 = this.openDirect;
        int hashCode9 = (hashCode8 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Map<String, String> map = this.customKeys;
        int hashCode10 = (hashCode9 + (map == null ? 0 : map.hashCode())) * 31;
        String str5 = this.custom;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode10 + i;
    }

    public final String id() {
        return this.id;
    }

    public final boolean isRollingPeriod() {
        return this.isRollingPeriod;
    }

    public final Media media() {
        return this.media;
    }

    public final int messageLimit() {
        return this.messageLimit;
    }

    public final int messageType() {
        return this.messageType;
    }

    public final int messagesPerPeriod() {
        return this.messagesPerPeriod;
    }

    public final int numberOfPeriods() {
        return this.numberOfPeriods;
    }

    public final String openDirect() {
        return this.openDirect;
    }

    public final int periodType() {
        return this.periodType;
    }

    public final int proximity() {
        return this.proximity;
    }

    public final String sound() {
        return this.sound;
    }

    public final Date startDateUtc() {
        return this.startDateUtc;
    }

    public final String title() {
        return this.title;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.title;
        String str3 = this.alert;
        String str4 = this.sound;
        Media media2 = this.media;
        Date date = this.startDateUtc;
        Date date2 = this.endDateUtc;
        int i = this.messageType;
        int i2 = this.contentType;
        String str5 = this.url;
        int i3 = this.messagesPerPeriod;
        int i4 = this.numberOfPeriods;
        int i5 = this.periodType;
        boolean z = this.isRollingPeriod;
        int i6 = this.messageLimit;
        int i7 = this.proximity;
        String str6 = this.openDirect;
        Map<String, String> map = this.customKeys;
        String str7 = this.custom;
        return "Message(id=" + str + ", title=" + str2 + ", alert=" + str3 + ", sound=" + str4 + ", media=" + media2 + ", startDateUtc=" + date + ", endDateUtc=" + date2 + ", messageType=" + i + ", contentType=" + i2 + ", url=" + str5 + ", messagesPerPeriod=" + i3 + ", numberOfPeriods=" + i4 + ", periodType=" + i5 + ", isRollingPeriod=" + z + ", messageLimit=" + i6 + ", proximity=" + i7 + ", openDirect=" + str6 + ", customKeys=" + map + ", custom=" + str7 + ")";
    }

    public final String url() {
        return this.url;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.alert);
        parcel.writeString(this.sound);
        Media media2 = this.media;
        if (media2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            media2.writeToParcel(parcel, i);
        }
        parcel.writeSerializable(this.startDateUtc);
        parcel.writeSerializable(this.endDateUtc);
        parcel.writeInt(this.messageType);
        parcel.writeInt(this.contentType);
        parcel.writeString(this.url);
        parcel.writeInt(this.messagesPerPeriod);
        parcel.writeInt(this.numberOfPeriods);
        parcel.writeInt(this.periodType);
        parcel.writeInt(this.isRollingPeriod ? 1 : 0);
        parcel.writeInt(this.messageLimit);
        parcel.writeInt(this.proximity);
        parcel.writeString(this.openDirect);
        Map<String, String> map = this.customKeys;
        if (map == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(map.size());
            for (Map.Entry next : map.entrySet()) {
                parcel.writeString((String) next.getKey());
                parcel.writeString((String) next.getValue());
            }
        }
        parcel.writeString(this.custom);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Message(java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, com.salesforce.marketingcloud.messages.Message.Media r28, java.util.Date r29, java.util.Date r30, int r31, int r32, java.lang.String r33, int r34, int r35, int r36, boolean r37, int r38, int r39, java.lang.String r40, java.util.Map r41, java.lang.String r42, int r43, kotlin.jvm.internal.DefaultConstructorMarker r44) {
        /*
            r23 = this;
            r0 = r43
            r1 = r0 & 2
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r5 = r2
            goto L_0x000b
        L_0x0009:
            r5 = r25
        L_0x000b:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0011
            r7 = r2
            goto L_0x0013
        L_0x0011:
            r7 = r27
        L_0x0013:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0019
            r8 = r2
            goto L_0x001b
        L_0x0019:
            r8 = r28
        L_0x001b:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0021
            r9 = r2
            goto L_0x0023
        L_0x0021:
            r9 = r29
        L_0x0023:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0029
            r10 = r2
            goto L_0x002b
        L_0x0029:
            r10 = r30
        L_0x002b:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0031
            r13 = r2
            goto L_0x0033
        L_0x0031:
            r13 = r33
        L_0x0033:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            r3 = -1
            if (r1 == 0) goto L_0x003a
            r14 = r3
            goto L_0x003c
        L_0x003a:
            r14 = r34
        L_0x003c:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0042
            r15 = r3
            goto L_0x0044
        L_0x0042:
            r15 = r35
        L_0x0044:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            r4 = 0
            if (r1 == 0) goto L_0x004c
            r16 = r4
            goto L_0x004e
        L_0x004c:
            r16 = r36
        L_0x004e:
            r1 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r1 == 0) goto L_0x0055
            r17 = r4
            goto L_0x0057
        L_0x0055:
            r17 = r37
        L_0x0057:
            r1 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r1 == 0) goto L_0x005e
            r18 = r3
            goto L_0x0060
        L_0x005e:
            r18 = r38
        L_0x0060:
            r1 = 32768(0x8000, float:4.5918E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0069
            r19 = r4
            goto L_0x006b
        L_0x0069:
            r19 = r39
        L_0x006b:
            r1 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0073
            r20 = r2
            goto L_0x0075
        L_0x0073:
            r20 = r40
        L_0x0075:
            r1 = 131072(0x20000, float:1.83671E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x007d
            r21 = r2
            goto L_0x007f
        L_0x007d:
            r21 = r41
        L_0x007f:
            r1 = 262144(0x40000, float:3.67342E-40)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x0087
            r22 = r2
            goto L_0x0089
        L_0x0087:
            r22 = r42
        L_0x0089:
            r3 = r23
            r4 = r24
            r6 = r26
            r11 = r31
            r12 = r32
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.Message.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.salesforce.marketingcloud.messages.Message$Media, java.util.Date, java.util.Date, int, int, java.lang.String, int, int, int, boolean, int, int, java.lang.String, java.util.Map, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* renamed from: -lastShownDate  reason: not valid java name */
    public final void m670lastShownDate(Date date) {
        this.lastShownDate = date;
    }

    /* renamed from: -nextAllowedShow  reason: not valid java name */
    public final void m672nextAllowedShow(Date date) {
        this.nextAllowedShow = date;
    }

    /* renamed from: -notificationId  reason: not valid java name */
    public final void m674notificationId(int i) {
        this.notificationId = i;
    }

    /* renamed from: -periodShowCount  reason: not valid java name */
    public final void m676periodShowCount(int i) {
        this.periodShowCount = i;
    }

    /* renamed from: -showCount  reason: not valid java name */
    public final void m678showCount(int i) {
        this.showCount = i;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Message(org.json.JSONObject r25) throws org.json.JSONException {
        /*
            r24 = this;
            r0 = r25
            java.lang.String r1 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "id"
            java.lang.String r3 = r0.getString(r1)
            java.lang.String r1 = "title"
            java.lang.String r4 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.getStringOrNull(r0, r1)
            java.lang.String r1 = "alert"
            java.lang.String r5 = r0.getString(r1)
            java.lang.String r1 = "sound"
            java.lang.String r6 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.getStringOrNull(r0, r1)
            com.salesforce.marketingcloud.messages.Message$Media$a r1 = com.salesforce.marketingcloud.messages.Message.Media.Companion
            java.lang.String r2 = "media"
            org.json.JSONObject r2 = r0.optJSONObject(r2)
            com.salesforce.marketingcloud.messages.Message$Media r7 = r1.a(r2)
            java.lang.String r1 = "startDateUtc"
            java.lang.String r1 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.getStringOrNull(r0, r1)
            if (r1 == 0) goto L_0x0039
            java.util.Date r1 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r1)
            r8 = r1
            goto L_0x003a
        L_0x0039:
            r8 = 0
        L_0x003a:
            java.lang.String r1 = "endDateUtc"
            java.lang.String r1 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.getStringOrNull(r0, r1)
            if (r1 == 0) goto L_0x0048
            java.util.Date r1 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r1)
            r9 = r1
            goto L_0x0049
        L_0x0048:
            r9 = 0
        L_0x0049:
            java.lang.String r1 = "messageType"
            int r10 = r0.getInt(r1)
            java.lang.String r1 = "contentType"
            int r11 = r0.getInt(r1)
            java.lang.String r1 = "url"
            java.lang.String r12 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.getStringOrNull(r0, r1)
            java.lang.String r1 = "openDirect"
            java.lang.String r19 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.getStringOrNull(r0, r1)
            java.lang.String r1 = "messageObjectPerPeriod"
            r13 = -1
            int r1 = r0.optInt(r1, r13)
            java.lang.String r14 = "numberOfPeriods"
            int r14 = r0.optInt(r14, r13)
            java.lang.String r15 = "periodType"
            r2 = 0
            int r15 = r0.optInt(r15, r2)
            java.lang.String r2 = "isRollingPeriod"
            boolean r18 = r0.optBoolean(r2)
            java.lang.String r2 = "messageLimit"
            int r20 = r0.optInt(r2, r13)
            java.lang.String r2 = "proximity"
            r13 = 0
            int r22 = r0.optInt(r2, r13)
            java.lang.String r2 = "keys"
            org.json.JSONArray r2 = r0.optJSONArray(r2)
            if (r2 == 0) goto L_0x0097
            java.util.Map r2 = com.salesforce.marketingcloud.internal.m.b((org.json.JSONArray) r2)
            r23 = r2
            goto L_0x0099
        L_0x0097:
            r23 = 0
        L_0x0099:
            java.lang.String r2 = "custom"
            java.lang.String r21 = com.salesforce.marketingcloud.extensions.PushExtensionsKt.getStringOrNull(r0, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r2 = r24
            r13 = r1
            r16 = r18
            r17 = r20
            r18 = r22
            r20 = r23
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.Message.<init>(org.json.JSONObject):void");
    }
}
