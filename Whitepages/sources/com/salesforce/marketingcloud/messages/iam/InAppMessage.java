package com.salesforce.marketingcloud.messages.iam;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.internal.m;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2;
import org.json.JSONArray;
import org.json.JSONObject;

@MCKeep
public final class InAppMessage implements Parcelable {
    public static final Parcelable.Creator<InAppMessage> CREATOR = new a();
    private final String activityInstanceId;
    private final String backgroundColor;
    private final TextField body;
    private final String borderColor;
    private final Size borderWidth;
    private final ButtonConfig buttonConfiguration;
    private final List<Button> buttons;
    private final CloseButton closeButton;
    private final Size cornerRadius;
    private final long displayDuration;
    private final int displayLimit;
    private final boolean displayLimitOverride;
    private final Date endDateUtc;
    private final String id;
    private final LayoutOrder layoutOrder;
    private final Media media;
    private final int messageDelaySec;
    private final Date modifiedDateUtc;
    private final int priority;
    private final Date startDateUtc;
    private final TextField title;
    private final Type type;
    private final String windowColor;

    @MCKeep
    public enum Alignment {
        beginning,
        center,
        end;

        static {
            Alignment[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }
    }

    @MCKeep
    public static final class Button implements Parcelable {
        public static final Parcelable.Creator<Button> CREATOR = new a();
        private final String action;
        private final ActionType actionType;
        private final String backgroundColor;
        private final String borderColor;
        private final Size borderWidth;
        private final Size cornerRadius;
        private final String fontColor;
        private final Size fontSize;
        private final String id;
        private final int index;
        private final String text;

        @MCKeep
        public enum ActionType {
            close,
            url,
            pushSettings,
            locationSettings;

            static {
                ActionType[] $values;
                $ENTRIES = EnumEntriesKt.enumEntries($values);
            }

            public static EnumEntries getEntries() {
                return $ENTRIES;
            }
        }

        public static final class a implements Parcelable.Creator<Button> {
            /* renamed from: a */
            public final Button createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Button(parcel.readString(), parcel.readInt(), parcel.readString(), ActionType.valueOf(parcel.readString()), parcel.readString(), parcel.readString(), Size.valueOf(parcel.readString()), parcel.readString(), parcel.readString(), Size.valueOf(parcel.readString()), Size.valueOf(parcel.readString()));
            }

            /* renamed from: a */
            public final Button[] newArray(int i) {
                return new Button[i];
            }
        }

        public Button(String str, int i, String str2, ActionType actionType2, String str3, String str4, Size size, String str5, String str6, Size size2, Size size3) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str2, "text");
            Intrinsics.checkNotNullParameter(actionType2, "actionType");
            Intrinsics.checkNotNullParameter(size, ViewProps.FONT_SIZE);
            Intrinsics.checkNotNullParameter(size2, ViewProps.BORDER_WIDTH);
            Intrinsics.checkNotNullParameter(size3, "cornerRadius");
            this.id = str;
            this.index = i;
            this.text = str2;
            this.actionType = actionType2;
            this.action = str3;
            this.fontColor = str4;
            this.fontSize = size;
            this.backgroundColor = str5;
            this.borderColor = str6;
            this.borderWidth = size2;
            this.cornerRadius = size3;
        }

        public static /* synthetic */ Button copy$default(Button button, String str, int i, String str2, ActionType actionType2, String str3, String str4, Size size, String str5, String str6, Size size2, Size size3, int i2, Object obj) {
            Button button2 = button;
            int i3 = i2;
            return button.copy((i3 & 1) != 0 ? button2.id : str, (i3 & 2) != 0 ? button2.index : i, (i3 & 4) != 0 ? button2.text : str2, (i3 & 8) != 0 ? button2.actionType : actionType2, (i3 & 16) != 0 ? button2.action : str3, (i3 & 32) != 0 ? button2.fontColor : str4, (i3 & 64) != 0 ? button2.fontSize : size, (i3 & 128) != 0 ? button2.backgroundColor : str5, (i3 & 256) != 0 ? button2.borderColor : str6, (i3 & 512) != 0 ? button2.borderWidth : size2, (i3 & 1024) != 0 ? button2.cornerRadius : size3);
        }

        /* renamed from: -deprecated_action  reason: not valid java name */
        public final String m721deprecated_action() {
            return this.action;
        }

        /* renamed from: -deprecated_actionType  reason: not valid java name */
        public final ActionType m722deprecated_actionType() {
            return this.actionType;
        }

        /* renamed from: -deprecated_backgroundColor  reason: not valid java name */
        public final String m723deprecated_backgroundColor() {
            return this.backgroundColor;
        }

        /* renamed from: -deprecated_borderColor  reason: not valid java name */
        public final String m724deprecated_borderColor() {
            return this.borderColor;
        }

        /* renamed from: -deprecated_borderWidth  reason: not valid java name */
        public final Size m725deprecated_borderWidth() {
            return this.borderWidth;
        }

        /* renamed from: -deprecated_cornerRadius  reason: not valid java name */
        public final Size m726deprecated_cornerRadius() {
            return this.cornerRadius;
        }

        /* renamed from: -deprecated_fontColor  reason: not valid java name */
        public final String m727deprecated_fontColor() {
            return this.fontColor;
        }

        /* renamed from: -deprecated_fontSize  reason: not valid java name */
        public final Size m728deprecated_fontSize() {
            return this.fontSize;
        }

        /* renamed from: -deprecated_id  reason: not valid java name */
        public final String m729deprecated_id() {
            return this.id;
        }

        /* renamed from: -deprecated_index  reason: not valid java name */
        public final int m730deprecated_index() {
            return this.index;
        }

        /* renamed from: -deprecated_text  reason: not valid java name */
        public final String m731deprecated_text() {
            return this.text;
        }

        /* renamed from: -toJson  reason: not valid java name */
        public final JSONObject m732toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.id);
            jSONObject.put(FirebaseAnalytics.Param.INDEX, this.index);
            jSONObject.put("text", this.text);
            jSONObject.put("actionType", this.actionType.name());
            String str = this.action;
            if (str != null) {
                jSONObject.put("actionAndroid", str);
            }
            String str2 = this.fontColor;
            if (str2 != null) {
                jSONObject.put("fontColor", str2);
            }
            jSONObject.put(ViewProps.FONT_SIZE, this.fontSize.name());
            String str3 = this.backgroundColor;
            if (str3 != null) {
                jSONObject.put(ViewProps.BACKGROUND_COLOR, str3);
            }
            String str4 = this.borderColor;
            if (str4 != null) {
                jSONObject.put(ViewProps.BORDER_COLOR, str4);
            }
            jSONObject.put(ViewProps.BORDER_WIDTH, this.borderWidth.name());
            jSONObject.put("cornerRadius", this.cornerRadius.name());
            return jSONObject;
        }

        public final String action() {
            return this.action;
        }

        public final ActionType actionType() {
            return this.actionType;
        }

        public final String backgroundColor() {
            return this.backgroundColor;
        }

        public final String borderColor() {
            return this.borderColor;
        }

        public final Size borderWidth() {
            return this.borderWidth;
        }

        public final String component1() {
            return this.id;
        }

        public final Size component10() {
            return this.borderWidth;
        }

        public final Size component11() {
            return this.cornerRadius;
        }

        public final int component2() {
            return this.index;
        }

        public final String component3() {
            return this.text;
        }

        public final ActionType component4() {
            return this.actionType;
        }

        public final String component5() {
            return this.action;
        }

        public final String component6() {
            return this.fontColor;
        }

        public final Size component7() {
            return this.fontSize;
        }

        public final String component8() {
            return this.backgroundColor;
        }

        public final String component9() {
            return this.borderColor;
        }

        public final Button copy(String str, int i, String str2, ActionType actionType2, String str3, String str4, Size size, String str5, String str6, Size size2, Size size3) {
            Intrinsics.checkNotNullParameter(str, "id");
            String str7 = str2;
            Intrinsics.checkNotNullParameter(str7, "text");
            ActionType actionType3 = actionType2;
            Intrinsics.checkNotNullParameter(actionType3, "actionType");
            Size size4 = size;
            Intrinsics.checkNotNullParameter(size4, ViewProps.FONT_SIZE);
            Size size5 = size2;
            Intrinsics.checkNotNullParameter(size5, ViewProps.BORDER_WIDTH);
            Size size6 = size3;
            Intrinsics.checkNotNullParameter(size6, "cornerRadius");
            return new Button(str, i, str7, actionType3, str3, str4, size4, str5, str6, size5, size6);
        }

        public final Size cornerRadius() {
            return this.cornerRadius;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Button)) {
                return false;
            }
            Button button = (Button) obj;
            return Intrinsics.areEqual((Object) this.id, (Object) button.id) && this.index == button.index && Intrinsics.areEqual((Object) this.text, (Object) button.text) && this.actionType == button.actionType && Intrinsics.areEqual((Object) this.action, (Object) button.action) && Intrinsics.areEqual((Object) this.fontColor, (Object) button.fontColor) && this.fontSize == button.fontSize && Intrinsics.areEqual((Object) this.backgroundColor, (Object) button.backgroundColor) && Intrinsics.areEqual((Object) this.borderColor, (Object) button.borderColor) && this.borderWidth == button.borderWidth && this.cornerRadius == button.cornerRadius;
        }

        public final String fontColor() {
            return this.fontColor;
        }

        public final Size fontSize() {
            return this.fontSize;
        }

        public int hashCode() {
            int hashCode = ((((((this.id.hashCode() * 31) + Integer.hashCode(this.index)) * 31) + this.text.hashCode()) * 31) + this.actionType.hashCode()) * 31;
            String str = this.action;
            int i = 0;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.fontColor;
            int hashCode3 = (((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.fontSize.hashCode()) * 31;
            String str3 = this.backgroundColor;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.borderColor;
            if (str4 != null) {
                i = str4.hashCode();
            }
            return ((((hashCode4 + i) * 31) + this.borderWidth.hashCode()) * 31) + this.cornerRadius.hashCode();
        }

        public final String id() {
            return this.id;
        }

        public final int index() {
            return this.index;
        }

        public final String text() {
            return this.text;
        }

        public String toString() {
            String str = this.id;
            int i = this.index;
            String str2 = this.text;
            ActionType actionType2 = this.actionType;
            String str3 = this.action;
            String str4 = this.fontColor;
            Size size = this.fontSize;
            String str5 = this.backgroundColor;
            String str6 = this.borderColor;
            Size size2 = this.borderWidth;
            Size size3 = this.cornerRadius;
            return "Button(id=" + str + ", index=" + i + ", text=" + str2 + ", actionType=" + actionType2 + ", action=" + str3 + ", fontColor=" + str4 + ", fontSize=" + size + ", backgroundColor=" + str5 + ", borderColor=" + str6 + ", borderWidth=" + size2 + ", cornerRadius=" + size3 + ")";
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.id);
            parcel.writeInt(this.index);
            parcel.writeString(this.text);
            parcel.writeString(this.actionType.name());
            parcel.writeString(this.action);
            parcel.writeString(this.fontColor);
            parcel.writeString(this.fontSize.name());
            parcel.writeString(this.backgroundColor);
            parcel.writeString(this.borderColor);
            parcel.writeString(this.borderWidth.name());
            parcel.writeString(this.cornerRadius.name());
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ Button(java.lang.String r15, int r16, java.lang.String r17, com.salesforce.marketingcloud.messages.iam.InAppMessage.Button.ActionType r18, java.lang.String r19, java.lang.String r20, com.salesforce.marketingcloud.messages.iam.InAppMessage.Size r21, java.lang.String r22, java.lang.String r23, com.salesforce.marketingcloud.messages.iam.InAppMessage.Size r24, com.salesforce.marketingcloud.messages.iam.InAppMessage.Size r25, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
            /*
                r14 = this;
                r0 = r26
                r1 = r0 & 2
                if (r1 == 0) goto L_0x0009
                r1 = 0
                r4 = r1
                goto L_0x000b
            L_0x0009:
                r4 = r16
            L_0x000b:
                r1 = r0 & 8
                if (r1 == 0) goto L_0x0013
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Button$ActionType r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Button.ActionType.close
                r6 = r1
                goto L_0x0015
            L_0x0013:
                r6 = r18
            L_0x0015:
                r1 = r0 & 16
                r2 = 0
                if (r1 == 0) goto L_0x001c
                r7 = r2
                goto L_0x001e
            L_0x001c:
                r7 = r19
            L_0x001e:
                r1 = r0 & 32
                if (r1 == 0) goto L_0x0024
                r8 = r2
                goto L_0x0026
            L_0x0024:
                r8 = r20
            L_0x0026:
                r1 = r0 & 64
                if (r1 == 0) goto L_0x002e
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.s
                r9 = r1
                goto L_0x0030
            L_0x002e:
                r9 = r21
            L_0x0030:
                r1 = r0 & 128(0x80, float:1.794E-43)
                if (r1 == 0) goto L_0x0036
                r10 = r2
                goto L_0x0038
            L_0x0036:
                r10 = r22
            L_0x0038:
                r1 = r0 & 256(0x100, float:3.59E-43)
                if (r1 == 0) goto L_0x003e
                r11 = r2
                goto L_0x0040
            L_0x003e:
                r11 = r23
            L_0x0040:
                r1 = r0 & 512(0x200, float:7.175E-43)
                if (r1 == 0) goto L_0x0048
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.s
                r12 = r1
                goto L_0x004a
            L_0x0048:
                r12 = r24
            L_0x004a:
                r0 = r0 & 1024(0x400, float:1.435E-42)
                if (r0 == 0) goto L_0x0052
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r0 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.s
                r13 = r0
                goto L_0x0054
            L_0x0052:
                r13 = r25
            L_0x0054:
                r2 = r14
                r3 = r15
                r5 = r17
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.InAppMessage.Button.<init>(java.lang.String, int, java.lang.String, com.salesforce.marketingcloud.messages.iam.InAppMessage$Button$ActionType, java.lang.String, java.lang.String, com.salesforce.marketingcloud.messages.iam.InAppMessage$Size, java.lang.String, java.lang.String, com.salesforce.marketingcloud.messages.iam.InAppMessage$Size, com.salesforce.marketingcloud.messages.iam.InAppMessage$Size, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }
    }

    @MCKeep
    public enum ButtonConfig {
        stacked,
        twoUp;

        static {
            ButtonConfig[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }
    }

    @MCKeep
    public static final class CloseButton implements Parcelable {
        public static final Parcelable.Creator<CloseButton> CREATOR = new b();
        public static final a Companion = new a((DefaultConstructorMarker) null);
        private final Alignment alignment;

        public static final class a {
            private a() {
            }

            public final CloseButton a() {
                return new CloseButton((Alignment) null, 1, (DefaultConstructorMarker) null);
            }

            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public static final class b implements Parcelable.Creator<CloseButton> {
            /* renamed from: a */
            public final CloseButton createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new CloseButton(Alignment.valueOf(parcel.readString()));
            }

            /* renamed from: a */
            public final CloseButton[] newArray(int i) {
                return new CloseButton[i];
            }
        }

        public CloseButton() {
            this((Alignment) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ CloseButton copy$default(CloseButton closeButton, Alignment alignment2, int i, Object obj) {
            if ((i & 1) != 0) {
                alignment2 = closeButton.alignment;
            }
            return closeButton.copy(alignment2);
        }

        public static final CloseButton defaultCloseButton() {
            return Companion.a();
        }

        /* renamed from: -deprecated_alignment  reason: not valid java name */
        public final Alignment m733deprecated_alignment() {
            return this.alignment;
        }

        /* renamed from: -toJson  reason: not valid java name */
        public final JSONObject m734toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("alignment", this.alignment.name());
            return jSONObject;
        }

        public final Alignment alignment() {
            return this.alignment;
        }

        public final Alignment component1() {
            return this.alignment;
        }

        public final CloseButton copy(Alignment alignment2) {
            Intrinsics.checkNotNullParameter(alignment2, "alignment");
            return new CloseButton(alignment2);
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CloseButton) && this.alignment == ((CloseButton) obj).alignment;
        }

        public int hashCode() {
            return this.alignment.hashCode();
        }

        public String toString() {
            Alignment alignment2 = this.alignment;
            return "CloseButton(alignment=" + alignment2 + ")";
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.alignment.name());
        }

        public CloseButton(Alignment alignment2) {
            Intrinsics.checkNotNullParameter(alignment2, "alignment");
            this.alignment = alignment2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ CloseButton(Alignment alignment2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? Alignment.end : alignment2);
        }
    }

    @MCKeep
    public enum LayoutOrder {
        ImageTitleBody,
        TitleImageBody;

        static {
            LayoutOrder[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }
    }

    @MCKeep
    public static final class Media implements Parcelable {
        public static final Parcelable.Creator<Media> CREATOR = new a();
        private final String altText;
        private final String borderColor;
        private final Size borderWidth;
        private final Size cornerRadius;
        private final ImageSize size;
        private final String url;

        @MCKeep
        public enum ImageSize {
            full,
            e2e,
            inset;

            static {
                ImageSize[] $values;
                $ENTRIES = EnumEntriesKt.enumEntries($values);
            }

            public static EnumEntries getEntries() {
                return $ENTRIES;
            }
        }

        public static final class a implements Parcelable.Creator<Media> {
            /* renamed from: a */
            public final Media createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Media(parcel.readString(), ImageSize.valueOf(parcel.readString()), parcel.readString(), Size.valueOf(parcel.readString()), parcel.readString(), Size.valueOf(parcel.readString()));
            }

            /* renamed from: a */
            public final Media[] newArray(int i) {
                return new Media[i];
            }
        }

        public Media(String str, ImageSize imageSize, String str2, Size size2, String str3, Size size3) {
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(imageSize, "size");
            Intrinsics.checkNotNullParameter(size2, ViewProps.BORDER_WIDTH);
            Intrinsics.checkNotNullParameter(size3, "cornerRadius");
            this.url = str;
            this.size = imageSize;
            this.altText = str2;
            this.borderWidth = size2;
            this.borderColor = str3;
            this.cornerRadius = size3;
        }

        public static /* synthetic */ Media copy$default(Media media, String str, ImageSize imageSize, String str2, Size size2, String str3, Size size3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = media.url;
            }
            if ((i & 2) != 0) {
                imageSize = media.size;
            }
            ImageSize imageSize2 = imageSize;
            if ((i & 4) != 0) {
                str2 = media.altText;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                size2 = media.borderWidth;
            }
            Size size4 = size2;
            if ((i & 16) != 0) {
                str3 = media.borderColor;
            }
            String str5 = str3;
            if ((i & 32) != 0) {
                size3 = media.cornerRadius;
            }
            return media.copy(str, imageSize2, str4, size4, str5, size3);
        }

        /* renamed from: -deprecated_altText  reason: not valid java name */
        public final String m735deprecated_altText() {
            return this.altText;
        }

        /* renamed from: -deprecated_borderColor  reason: not valid java name */
        public final String m736deprecated_borderColor() {
            return this.borderColor;
        }

        /* renamed from: -deprecated_borderWidth  reason: not valid java name */
        public final Size m737deprecated_borderWidth() {
            return this.borderWidth;
        }

        /* renamed from: -deprecated_cornerRadius  reason: not valid java name */
        public final Size m738deprecated_cornerRadius() {
            return this.cornerRadius;
        }

        /* renamed from: -deprecated_size  reason: not valid java name */
        public final ImageSize m739deprecated_size() {
            return this.size;
        }

        /* renamed from: -deprecated_url  reason: not valid java name */
        public final String m740deprecated_url() {
            return this.url;
        }

        /* renamed from: -toJson  reason: not valid java name */
        public final JSONObject m741toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", this.url);
            jSONObject.put("size", this.size.name());
            String str = this.altText;
            if (str != null) {
                jSONObject.put("altText", str);
            }
            jSONObject.put(ViewProps.BORDER_WIDTH, this.borderWidth.name());
            String str2 = this.borderColor;
            if (str2 != null) {
                jSONObject.put(ViewProps.BORDER_COLOR, str2);
            }
            jSONObject.put("cornerRadius", this.cornerRadius);
            return jSONObject;
        }

        public final String altText() {
            return this.altText;
        }

        public final String borderColor() {
            return this.borderColor;
        }

        public final Size borderWidth() {
            return this.borderWidth;
        }

        public final String component1() {
            return this.url;
        }

        public final ImageSize component2() {
            return this.size;
        }

        public final String component3() {
            return this.altText;
        }

        public final Size component4() {
            return this.borderWidth;
        }

        public final String component5() {
            return this.borderColor;
        }

        public final Size component6() {
            return this.cornerRadius;
        }

        public final Media copy(String str, ImageSize imageSize, String str2, Size size2, String str3, Size size3) {
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(imageSize, "size");
            Intrinsics.checkNotNullParameter(size2, ViewProps.BORDER_WIDTH);
            Intrinsics.checkNotNullParameter(size3, "cornerRadius");
            return new Media(str, imageSize, str2, size2, str3, size3);
        }

        public final Size cornerRadius() {
            return this.cornerRadius;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Media)) {
                return false;
            }
            Media media = (Media) obj;
            return Intrinsics.areEqual((Object) this.url, (Object) media.url) && this.size == media.size && Intrinsics.areEqual((Object) this.altText, (Object) media.altText) && this.borderWidth == media.borderWidth && Intrinsics.areEqual((Object) this.borderColor, (Object) media.borderColor) && this.cornerRadius == media.cornerRadius;
        }

        public int hashCode() {
            int hashCode = ((this.url.hashCode() * 31) + this.size.hashCode()) * 31;
            String str = this.altText;
            int i = 0;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.borderWidth.hashCode()) * 31;
            String str2 = this.borderColor;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return ((hashCode2 + i) * 31) + this.cornerRadius.hashCode();
        }

        public final ImageSize size() {
            return this.size;
        }

        public String toString() {
            String str = this.url;
            ImageSize imageSize = this.size;
            String str2 = this.altText;
            Size size2 = this.borderWidth;
            String str3 = this.borderColor;
            Size size3 = this.cornerRadius;
            return "Media(url=" + str + ", size=" + imageSize + ", altText=" + str2 + ", borderWidth=" + size2 + ", borderColor=" + str3 + ", cornerRadius=" + size3 + ")";
        }

        public final String url() {
            return this.url;
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.url);
            parcel.writeString(this.size.name());
            parcel.writeString(this.altText);
            parcel.writeString(this.borderWidth.name());
            parcel.writeString(this.borderColor);
            parcel.writeString(this.cornerRadius.name());
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Media(String str, ImageSize imageSize, String str2, Size size2, String str3, Size size3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? ImageSize.e2e : imageSize, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? Size.s : size2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? Size.s : size3);
        }
    }

    @MCKeep
    public enum Size {
        s,
        m,
        l;

        static {
            Size[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }
    }

    @MCKeep
    public static final class TextField implements Parcelable {
        public static final Parcelable.Creator<TextField> CREATOR = new a();
        private final Alignment alignment;
        private final String fontColor;
        private final Size fontSize;
        private final String text;

        public static final class a implements Parcelable.Creator<TextField> {
            /* renamed from: a */
            public final TextField createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new TextField(parcel.readString(), Size.valueOf(parcel.readString()), parcel.readString(), Alignment.valueOf(parcel.readString()));
            }

            /* renamed from: a */
            public final TextField[] newArray(int i) {
                return new TextField[i];
            }
        }

        public TextField(String str, Size size, String str2, Alignment alignment2) {
            Intrinsics.checkNotNullParameter(str, "text");
            Intrinsics.checkNotNullParameter(size, ViewProps.FONT_SIZE);
            Intrinsics.checkNotNullParameter(alignment2, "alignment");
            this.text = str;
            this.fontSize = size;
            this.fontColor = str2;
            this.alignment = alignment2;
        }

        public static /* synthetic */ TextField copy$default(TextField textField, String str, Size size, String str2, Alignment alignment2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = textField.text;
            }
            if ((i & 2) != 0) {
                size = textField.fontSize;
            }
            if ((i & 4) != 0) {
                str2 = textField.fontColor;
            }
            if ((i & 8) != 0) {
                alignment2 = textField.alignment;
            }
            return textField.copy(str, size, str2, alignment2);
        }

        /* renamed from: -deprecated_alignment  reason: not valid java name */
        public final Alignment m742deprecated_alignment() {
            return this.alignment;
        }

        /* renamed from: -deprecated_fontColor  reason: not valid java name */
        public final String m743deprecated_fontColor() {
            return this.fontColor;
        }

        /* renamed from: -deprecated_fontSize  reason: not valid java name */
        public final Size m744deprecated_fontSize() {
            return this.fontSize;
        }

        /* renamed from: -deprecated_text  reason: not valid java name */
        public final String m745deprecated_text() {
            return this.text;
        }

        /* renamed from: -toJson  reason: not valid java name */
        public final JSONObject m746toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("text", this.text);
            jSONObject.put(ViewProps.FONT_SIZE, this.fontSize.name());
            String str = this.fontColor;
            if (str != null) {
                jSONObject.put("fontColor", str);
            }
            jSONObject.put("alignment", this.alignment.name());
            return jSONObject;
        }

        public final Alignment alignment() {
            return this.alignment;
        }

        public final String component1() {
            return this.text;
        }

        public final Size component2() {
            return this.fontSize;
        }

        public final String component3() {
            return this.fontColor;
        }

        public final Alignment component4() {
            return this.alignment;
        }

        public final TextField copy(String str, Size size, String str2, Alignment alignment2) {
            Intrinsics.checkNotNullParameter(str, "text");
            Intrinsics.checkNotNullParameter(size, ViewProps.FONT_SIZE);
            Intrinsics.checkNotNullParameter(alignment2, "alignment");
            return new TextField(str, size, str2, alignment2);
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TextField)) {
                return false;
            }
            TextField textField = (TextField) obj;
            return Intrinsics.areEqual((Object) this.text, (Object) textField.text) && this.fontSize == textField.fontSize && Intrinsics.areEqual((Object) this.fontColor, (Object) textField.fontColor) && this.alignment == textField.alignment;
        }

        public final String fontColor() {
            return this.fontColor;
        }

        public final Size fontSize() {
            return this.fontSize;
        }

        public int hashCode() {
            int hashCode = ((this.text.hashCode() * 31) + this.fontSize.hashCode()) * 31;
            String str = this.fontColor;
            return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.alignment.hashCode();
        }

        public final String text() {
            return this.text;
        }

        public String toString() {
            String str = this.text;
            Size size = this.fontSize;
            String str2 = this.fontColor;
            Alignment alignment2 = this.alignment;
            return "TextField(text=" + str + ", fontSize=" + size + ", fontColor=" + str2 + ", alignment=" + alignment2 + ")";
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.text);
            parcel.writeString(this.fontSize.name());
            parcel.writeString(this.fontColor);
            parcel.writeString(this.alignment.name());
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ TextField(String str, Size size, String str2, Alignment alignment2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? Size.s : size, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? Alignment.center : alignment2);
        }
    }

    @MCKeep
    public enum Type {
        bannerTop,
        bannerBottom,
        modal,
        full,
        fullImageFill;

        static {
            Type[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }
    }

    public static final class a implements Parcelable.Creator<InAppMessage> {
        /* renamed from: a */
        public final InAppMessage createFromParcel(Parcel parcel) {
            Size size;
            ArrayList arrayList;
            Parcel parcel2 = parcel;
            Intrinsics.checkNotNullParameter(parcel2, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            int readInt = parcel.readInt();
            Date date = (Date) parcel.readSerializable();
            Date date2 = (Date) parcel.readSerializable();
            Date date3 = (Date) parcel.readSerializable();
            int readInt2 = parcel.readInt();
            Type valueOf = Type.valueOf(parcel.readString());
            String readString3 = parcel.readString();
            long readLong = parcel.readLong();
            String readString4 = parcel.readString();
            Size valueOf2 = Size.valueOf(parcel.readString());
            String readString5 = parcel.readString();
            Size valueOf3 = Size.valueOf(parcel.readString());
            LayoutOrder valueOf4 = LayoutOrder.valueOf(parcel.readString());
            Media createFromParcel = parcel.readInt() == 0 ? null : Media.CREATOR.createFromParcel(parcel2);
            TextField createFromParcel2 = parcel.readInt() == 0 ? null : TextField.CREATOR.createFromParcel(parcel2);
            TextField createFromParcel3 = parcel.readInt() == 0 ? null : TextField.CREATOR.createFromParcel(parcel2);
            CloseButton createFromParcel4 = parcel.readInt() == 0 ? null : CloseButton.CREATOR.createFromParcel(parcel2);
            ButtonConfig valueOf5 = ButtonConfig.valueOf(parcel.readString());
            if (parcel.readInt() == 0) {
                arrayList = null;
                size = valueOf2;
            } else {
                int readInt3 = parcel.readInt();
                ArrayList arrayList2 = new ArrayList(readInt3);
                size = valueOf2;
                int i = 0;
                while (i != readInt3) {
                    arrayList2.add(Button.CREATOR.createFromParcel(parcel2));
                    i++;
                    readInt3 = readInt3;
                }
                arrayList = arrayList2;
            }
            return new InAppMessage(readString, readString2, readInt, date, date2, date3, readInt2, valueOf, readString3, readLong, readString4, size, readString5, valueOf3, valueOf4, createFromParcel, createFromParcel2, createFromParcel3, createFromParcel4, valueOf5, arrayList, parcel.readInt(), parcel.readInt() != 0);
        }

        /* renamed from: a */
        public final InAppMessage[] newArray(int i) {
            return new InAppMessage[i];
        }
    }

    public InAppMessage(String str, String str2, int i, Date date, Date date2, Date date3, int i2, Type type2, String str3, long j, String str4, Size size, String str5, Size size2, LayoutOrder layoutOrder2, Media media2, TextField textField, TextField textField2, CloseButton closeButton2, ButtonConfig buttonConfig, List<Button> list, int i3, boolean z) {
        Type type3 = type2;
        Size size3 = size;
        Size size4 = size2;
        LayoutOrder layoutOrder3 = layoutOrder2;
        ButtonConfig buttonConfig2 = buttonConfig;
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "activityInstanceId");
        Intrinsics.checkNotNullParameter(type3, "type");
        Intrinsics.checkNotNullParameter(size3, ViewProps.BORDER_WIDTH);
        Intrinsics.checkNotNullParameter(size4, "cornerRadius");
        Intrinsics.checkNotNullParameter(layoutOrder3, "layoutOrder");
        Intrinsics.checkNotNullParameter(buttonConfig2, "buttonConfiguration");
        this.id = str;
        this.activityInstanceId = str2;
        this.priority = i;
        this.startDateUtc = date;
        this.endDateUtc = date2;
        this.modifiedDateUtc = date3;
        this.displayLimit = i2;
        this.type = type3;
        this.windowColor = str3;
        this.displayDuration = j;
        this.backgroundColor = str4;
        this.borderWidth = size3;
        this.borderColor = str5;
        this.cornerRadius = size4;
        this.layoutOrder = layoutOrder3;
        this.media = media2;
        this.title = textField;
        this.body = textField2;
        this.closeButton = closeButton2;
        this.buttonConfiguration = buttonConfig2;
        this.buttons = list;
        this.messageDelaySec = i3;
        this.displayLimitOverride = z;
    }

    public static /* synthetic */ InAppMessage copy$default(InAppMessage inAppMessage, String str, String str2, int i, Date date, Date date2, Date date3, int i2, Type type2, String str3, long j, String str4, Size size, String str5, Size size2, LayoutOrder layoutOrder2, Media media2, TextField textField, TextField textField2, CloseButton closeButton2, ButtonConfig buttonConfig, List list, int i3, boolean z, int i4, Object obj) {
        InAppMessage inAppMessage2 = inAppMessage;
        int i5 = i4;
        return inAppMessage.copy((i5 & 1) != 0 ? inAppMessage2.id : str, (i5 & 2) != 0 ? inAppMessage2.activityInstanceId : str2, (i5 & 4) != 0 ? inAppMessage2.priority : i, (i5 & 8) != 0 ? inAppMessage2.startDateUtc : date, (i5 & 16) != 0 ? inAppMessage2.endDateUtc : date2, (i5 & 32) != 0 ? inAppMessage2.modifiedDateUtc : date3, (i5 & 64) != 0 ? inAppMessage2.displayLimit : i2, (i5 & 128) != 0 ? inAppMessage2.type : type2, (i5 & 256) != 0 ? inAppMessage2.windowColor : str3, (i5 & 512) != 0 ? inAppMessage2.displayDuration : j, (i5 & 1024) != 0 ? inAppMessage2.backgroundColor : str4, (i5 & b.u) != 0 ? inAppMessage2.borderWidth : size, (i5 & b.v) != 0 ? inAppMessage2.borderColor : str5, (i5 & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0 ? inAppMessage2.cornerRadius : size2, (i5 & Http2.INITIAL_MAX_FRAME_SIZE) != 0 ? inAppMessage2.layoutOrder : layoutOrder2, (i5 & 32768) != 0 ? inAppMessage2.media : media2, (i5 & 65536) != 0 ? inAppMessage2.title : textField, (i5 & 131072) != 0 ? inAppMessage2.body : textField2, (i5 & 262144) != 0 ? inAppMessage2.closeButton : closeButton2, (i5 & 524288) != 0 ? inAppMessage2.buttonConfiguration : buttonConfig, (i5 & 1048576) != 0 ? inAppMessage2.buttons : list, (i5 & 2097152) != 0 ? inAppMessage2.messageDelaySec : i3, (i5 & 4194304) != 0 ? inAppMessage2.displayLimitOverride : z);
    }

    /* renamed from: -activityInstanceId  reason: not valid java name */
    public final String m699activityInstanceId() {
        return this.activityInstanceId;
    }

    /* renamed from: -deprecated_backgroundColor  reason: not valid java name */
    public final String m700deprecated_backgroundColor() {
        return this.backgroundColor;
    }

    /* renamed from: -deprecated_body  reason: not valid java name */
    public final TextField m701deprecated_body() {
        return this.body;
    }

    /* renamed from: -deprecated_borderColor  reason: not valid java name */
    public final String m702deprecated_borderColor() {
        return this.borderColor;
    }

    /* renamed from: -deprecated_borderWidth  reason: not valid java name */
    public final Size m703deprecated_borderWidth() {
        return this.borderWidth;
    }

    /* renamed from: -deprecated_buttonConfiguration  reason: not valid java name */
    public final ButtonConfig m704deprecated_buttonConfiguration() {
        return this.buttonConfiguration;
    }

    /* renamed from: -deprecated_buttons  reason: not valid java name */
    public final List<Button> m705deprecated_buttons() {
        return this.buttons;
    }

    /* renamed from: -deprecated_closeButton  reason: not valid java name */
    public final CloseButton m706deprecated_closeButton() {
        return this.closeButton;
    }

    /* renamed from: -deprecated_cornerRadius  reason: not valid java name */
    public final Size m707deprecated_cornerRadius() {
        return this.cornerRadius;
    }

    /* renamed from: -deprecated_displayDuration  reason: not valid java name */
    public final long m708deprecated_displayDuration() {
        return this.displayDuration;
    }

    /* renamed from: -deprecated_displayLimit  reason: not valid java name */
    public final int m709deprecated_displayLimit() {
        return this.displayLimit;
    }

    /* renamed from: -deprecated_endDateUtc  reason: not valid java name */
    public final Date m710deprecated_endDateUtc() {
        return this.endDateUtc;
    }

    /* renamed from: -deprecated_id  reason: not valid java name */
    public final String m711deprecated_id() {
        return this.id;
    }

    /* renamed from: -deprecated_layoutOrder  reason: not valid java name */
    public final LayoutOrder m712deprecated_layoutOrder() {
        return this.layoutOrder;
    }

    /* renamed from: -deprecated_media  reason: not valid java name */
    public final Media m713deprecated_media() {
        return this.media;
    }

    /* renamed from: -deprecated_modifiedDateUtc  reason: not valid java name */
    public final Date m714deprecated_modifiedDateUtc() {
        return this.modifiedDateUtc;
    }

    /* renamed from: -deprecated_priority  reason: not valid java name */
    public final int m715deprecated_priority() {
        return this.priority;
    }

    /* renamed from: -deprecated_startDateUtc  reason: not valid java name */
    public final Date m716deprecated_startDateUtc() {
        return this.startDateUtc;
    }

    /* renamed from: -deprecated_title  reason: not valid java name */
    public final TextField m717deprecated_title() {
        return this.title;
    }

    /* renamed from: -deprecated_type  reason: not valid java name */
    public final Type m718deprecated_type() {
        return this.type;
    }

    /* renamed from: -deprecated_windowColor  reason: not valid java name */
    public final String m719deprecated_windowColor() {
        return this.windowColor;
    }

    /* renamed from: -toJson  reason: not valid java name */
    public final JSONObject m720toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put("activityInstanceId", this.activityInstanceId);
        jSONObject.put("priority", this.priority);
        Date date = this.startDateUtc;
        if (date != null) {
            jSONObject.put("startDateUtc", m.a(date));
        }
        Date date2 = this.endDateUtc;
        if (date2 != null) {
            jSONObject.put("endDateUtc", m.a(date2));
        }
        Date date3 = this.modifiedDateUtc;
        if (date3 != null) {
            jSONObject.put("modifiedDateUtc", m.a(date3));
        }
        jSONObject.put("displayLimit", this.displayLimit);
        jSONObject.put("type", this.type.name());
        String str = this.windowColor;
        if (str != null) {
            jSONObject.put("windowColor", str);
        }
        jSONObject.put("displayDuration", this.displayDuration);
        String str2 = this.backgroundColor;
        if (str2 != null) {
            jSONObject.put(ViewProps.BACKGROUND_COLOR, str2);
        }
        jSONObject.put(ViewProps.BORDER_WIDTH, this.borderWidth.name());
        String str3 = this.borderColor;
        if (str3 != null) {
            jSONObject.put(ViewProps.BORDER_COLOR, str3);
        }
        jSONObject.put("cornerRadius", this.cornerRadius.name());
        jSONObject.put("layoutOrder", this.layoutOrder.name());
        Media media2 = this.media;
        if (media2 != null) {
            jSONObject.put("media", media2.m741toJson());
        }
        TextField textField = this.title;
        if (textField != null) {
            jSONObject.put("title", textField.m746toJson());
        }
        TextField textField2 = this.body;
        if (textField2 != null) {
            jSONObject.put("body", textField2.m746toJson());
        }
        CloseButton closeButton2 = this.closeButton;
        if (closeButton2 != null) {
            jSONObject.put("closeButton", closeButton2.m734toJson());
        }
        jSONObject.put("buttonConfiguration", this.buttonConfiguration.name());
        if (this.buttons != null) {
            JSONArray jSONArray = new JSONArray();
            for (Button r3 : this.buttons) {
                jSONArray.put(r3.m732toJson());
            }
            Unit unit = Unit.INSTANCE;
            jSONObject.put("buttons", jSONArray);
        }
        jSONObject.put("messageDelaySec", this.messageDelaySec);
        jSONObject.put("displayLimitOverride", this.displayLimitOverride);
        return jSONObject;
    }

    public final String backgroundColor() {
        return this.backgroundColor;
    }

    public final TextField body() {
        return this.body;
    }

    public final String borderColor() {
        return this.borderColor;
    }

    public final Size borderWidth() {
        return this.borderWidth;
    }

    public final ButtonConfig buttonConfiguration() {
        return this.buttonConfiguration;
    }

    public final List<Button> buttons() {
        return this.buttons;
    }

    public final CloseButton closeButton() {
        return this.closeButton;
    }

    public final String component1() {
        return this.id;
    }

    public final long component10() {
        return this.displayDuration;
    }

    public final String component11() {
        return this.backgroundColor;
    }

    public final Size component12() {
        return this.borderWidth;
    }

    public final String component13() {
        return this.borderColor;
    }

    public final Size component14() {
        return this.cornerRadius;
    }

    public final LayoutOrder component15() {
        return this.layoutOrder;
    }

    public final Media component16() {
        return this.media;
    }

    public final TextField component17() {
        return this.title;
    }

    public final TextField component18() {
        return this.body;
    }

    public final CloseButton component19() {
        return this.closeButton;
    }

    public final String component2$sdk_release() {
        return this.activityInstanceId;
    }

    public final ButtonConfig component20() {
        return this.buttonConfiguration;
    }

    public final List<Button> component21() {
        return this.buttons;
    }

    public final int component22() {
        return this.messageDelaySec;
    }

    public final boolean component23() {
        return this.displayLimitOverride;
    }

    public final int component3() {
        return this.priority;
    }

    public final Date component4() {
        return this.startDateUtc;
    }

    public final Date component5() {
        return this.endDateUtc;
    }

    public final Date component6() {
        return this.modifiedDateUtc;
    }

    public final int component7() {
        return this.displayLimit;
    }

    public final Type component8() {
        return this.type;
    }

    public final String component9() {
        return this.windowColor;
    }

    public final InAppMessage copy(String str, String str2, int i, Date date, Date date2, Date date3, int i2, Type type2, String str3, long j, String str4, Size size, String str5, Size size2, LayoutOrder layoutOrder2, Media media2, TextField textField, TextField textField2, CloseButton closeButton2, ButtonConfig buttonConfig, List<Button> list, int i3, boolean z) {
        String str6 = str;
        Intrinsics.checkNotNullParameter(str6, "id");
        Intrinsics.checkNotNullParameter(str2, "activityInstanceId");
        Intrinsics.checkNotNullParameter(type2, "type");
        Intrinsics.checkNotNullParameter(size, ViewProps.BORDER_WIDTH);
        Intrinsics.checkNotNullParameter(size2, "cornerRadius");
        Intrinsics.checkNotNullParameter(layoutOrder2, "layoutOrder");
        Intrinsics.checkNotNullParameter(buttonConfig, "buttonConfiguration");
        return new InAppMessage(str6, str2, i, date, date2, date3, i2, type2, str3, j, str4, size, str5, size2, layoutOrder2, media2, textField, textField2, closeButton2, buttonConfig, list, i3, z);
    }

    public final Size cornerRadius() {
        return this.cornerRadius;
    }

    public int describeContents() {
        return 0;
    }

    public final long displayDuration() {
        return this.displayDuration;
    }

    public final int displayLimit() {
        return this.displayLimit;
    }

    public final boolean displayLimitOverride() {
        return this.displayLimitOverride;
    }

    public final Date endDateUtc() {
        return this.endDateUtc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InAppMessage)) {
            return false;
        }
        InAppMessage inAppMessage = (InAppMessage) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) inAppMessage.id) && Intrinsics.areEqual((Object) this.activityInstanceId, (Object) inAppMessage.activityInstanceId) && this.priority == inAppMessage.priority && Intrinsics.areEqual((Object) this.startDateUtc, (Object) inAppMessage.startDateUtc) && Intrinsics.areEqual((Object) this.endDateUtc, (Object) inAppMessage.endDateUtc) && Intrinsics.areEqual((Object) this.modifiedDateUtc, (Object) inAppMessage.modifiedDateUtc) && this.displayLimit == inAppMessage.displayLimit && this.type == inAppMessage.type && Intrinsics.areEqual((Object) this.windowColor, (Object) inAppMessage.windowColor) && this.displayDuration == inAppMessage.displayDuration && Intrinsics.areEqual((Object) this.backgroundColor, (Object) inAppMessage.backgroundColor) && this.borderWidth == inAppMessage.borderWidth && Intrinsics.areEqual((Object) this.borderColor, (Object) inAppMessage.borderColor) && this.cornerRadius == inAppMessage.cornerRadius && this.layoutOrder == inAppMessage.layoutOrder && Intrinsics.areEqual((Object) this.media, (Object) inAppMessage.media) && Intrinsics.areEqual((Object) this.title, (Object) inAppMessage.title) && Intrinsics.areEqual((Object) this.body, (Object) inAppMessage.body) && Intrinsics.areEqual((Object) this.closeButton, (Object) inAppMessage.closeButton) && this.buttonConfiguration == inAppMessage.buttonConfiguration && Intrinsics.areEqual((Object) this.buttons, (Object) inAppMessage.buttons) && this.messageDelaySec == inAppMessage.messageDelaySec && this.displayLimitOverride == inAppMessage.displayLimitOverride;
    }

    public int hashCode() {
        int hashCode = ((((this.id.hashCode() * 31) + this.activityInstanceId.hashCode()) * 31) + Integer.hashCode(this.priority)) * 31;
        Date date = this.startDateUtc;
        int i = 0;
        int hashCode2 = (hashCode + (date == null ? 0 : date.hashCode())) * 31;
        Date date2 = this.endDateUtc;
        int hashCode3 = (hashCode2 + (date2 == null ? 0 : date2.hashCode())) * 31;
        Date date3 = this.modifiedDateUtc;
        int hashCode4 = (((((hashCode3 + (date3 == null ? 0 : date3.hashCode())) * 31) + Integer.hashCode(this.displayLimit)) * 31) + this.type.hashCode()) * 31;
        String str = this.windowColor;
        int hashCode5 = (((hashCode4 + (str == null ? 0 : str.hashCode())) * 31) + Long.hashCode(this.displayDuration)) * 31;
        String str2 = this.backgroundColor;
        int hashCode6 = (((hashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.borderWidth.hashCode()) * 31;
        String str3 = this.borderColor;
        int hashCode7 = (((((hashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.cornerRadius.hashCode()) * 31) + this.layoutOrder.hashCode()) * 31;
        Media media2 = this.media;
        int hashCode8 = (hashCode7 + (media2 == null ? 0 : media2.hashCode())) * 31;
        TextField textField = this.title;
        int hashCode9 = (hashCode8 + (textField == null ? 0 : textField.hashCode())) * 31;
        TextField textField2 = this.body;
        int hashCode10 = (hashCode9 + (textField2 == null ? 0 : textField2.hashCode())) * 31;
        CloseButton closeButton2 = this.closeButton;
        int hashCode11 = (((hashCode10 + (closeButton2 == null ? 0 : closeButton2.hashCode())) * 31) + this.buttonConfiguration.hashCode()) * 31;
        List<Button> list = this.buttons;
        if (list != null) {
            i = list.hashCode();
        }
        int hashCode12 = (((hashCode11 + i) * 31) + Integer.hashCode(this.messageDelaySec)) * 31;
        boolean z = this.displayLimitOverride;
        if (z) {
            z = true;
        }
        return hashCode12 + (z ? 1 : 0);
    }

    public final String id() {
        return this.id;
    }

    public final LayoutOrder layoutOrder() {
        return this.layoutOrder;
    }

    public final Media media() {
        return this.media;
    }

    public final int messageDelaySec() {
        return this.messageDelaySec;
    }

    public final Date modifiedDateUtc() {
        return this.modifiedDateUtc;
    }

    public final int priority() {
        return this.priority;
    }

    public final Date startDateUtc() {
        return this.startDateUtc;
    }

    public final TextField title() {
        return this.title;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.activityInstanceId;
        int i = this.priority;
        Date date = this.startDateUtc;
        Date date2 = this.endDateUtc;
        Date date3 = this.modifiedDateUtc;
        int i2 = this.displayLimit;
        Type type2 = this.type;
        String str3 = this.windowColor;
        long j = this.displayDuration;
        String str4 = this.backgroundColor;
        Size size = this.borderWidth;
        String str5 = this.borderColor;
        Size size2 = this.cornerRadius;
        LayoutOrder layoutOrder2 = this.layoutOrder;
        Media media2 = this.media;
        TextField textField = this.title;
        TextField textField2 = this.body;
        CloseButton closeButton2 = this.closeButton;
        ButtonConfig buttonConfig = this.buttonConfiguration;
        List<Button> list = this.buttons;
        int i3 = this.messageDelaySec;
        boolean z = this.displayLimitOverride;
        return "InAppMessage(id=" + str + ", activityInstanceId=" + str2 + ", priority=" + i + ", startDateUtc=" + date + ", endDateUtc=" + date2 + ", modifiedDateUtc=" + date3 + ", displayLimit=" + i2 + ", type=" + type2 + ", windowColor=" + str3 + ", displayDuration=" + j + ", backgroundColor=" + str4 + ", borderWidth=" + size + ", borderColor=" + str5 + ", cornerRadius=" + size2 + ", layoutOrder=" + layoutOrder2 + ", media=" + media2 + ", title=" + textField + ", body=" + textField2 + ", closeButton=" + closeButton2 + ", buttonConfiguration=" + buttonConfig + ", buttons=" + list + ", messageDelaySec=" + i3 + ", displayLimitOverride=" + z + ")";
    }

    public final Type type() {
        return this.type;
    }

    public final String windowColor() {
        return this.windowColor;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.id);
        parcel.writeString(this.activityInstanceId);
        parcel.writeInt(this.priority);
        parcel.writeSerializable(this.startDateUtc);
        parcel.writeSerializable(this.endDateUtc);
        parcel.writeSerializable(this.modifiedDateUtc);
        parcel.writeInt(this.displayLimit);
        parcel.writeString(this.type.name());
        parcel.writeString(this.windowColor);
        parcel.writeLong(this.displayDuration);
        parcel.writeString(this.backgroundColor);
        parcel.writeString(this.borderWidth.name());
        parcel.writeString(this.borderColor);
        parcel.writeString(this.cornerRadius.name());
        parcel.writeString(this.layoutOrder.name());
        Media media2 = this.media;
        if (media2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            media2.writeToParcel(parcel, i);
        }
        TextField textField = this.title;
        if (textField == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            textField.writeToParcel(parcel, i);
        }
        TextField textField2 = this.body;
        if (textField2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            textField2.writeToParcel(parcel, i);
        }
        CloseButton closeButton2 = this.closeButton;
        if (closeButton2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            closeButton2.writeToParcel(parcel, i);
        }
        parcel.writeString(this.buttonConfiguration.name());
        List<Button> list = this.buttons;
        if (list == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(list.size());
            for (Button writeToParcel : list) {
                writeToParcel.writeToParcel(parcel, i);
            }
        }
        parcel.writeInt(this.messageDelaySec);
        parcel.writeInt(this.displayLimitOverride ? 1 : 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ InAppMessage(java.lang.String r28, java.lang.String r29, int r30, java.util.Date r31, java.util.Date r32, java.util.Date r33, int r34, com.salesforce.marketingcloud.messages.iam.InAppMessage.Type r35, java.lang.String r36, long r37, java.lang.String r39, com.salesforce.marketingcloud.messages.iam.InAppMessage.Size r40, java.lang.String r41, com.salesforce.marketingcloud.messages.iam.InAppMessage.Size r42, com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder r43, com.salesforce.marketingcloud.messages.iam.InAppMessage.Media r44, com.salesforce.marketingcloud.messages.iam.InAppMessage.TextField r45, com.salesforce.marketingcloud.messages.iam.InAppMessage.TextField r46, com.salesforce.marketingcloud.messages.iam.InAppMessage.CloseButton r47, com.salesforce.marketingcloud.messages.iam.InAppMessage.ButtonConfig r48, java.util.List r49, int r50, boolean r51, int r52, kotlin.jvm.internal.DefaultConstructorMarker r53) {
        /*
            r27 = this;
            r0 = r52
            r1 = r0 & 4
            if (r1 == 0) goto L_0x000a
            r1 = 999(0x3e7, float:1.4E-42)
            r5 = r1
            goto L_0x000c
        L_0x000a:
            r5 = r30
        L_0x000c:
            r1 = r0 & 8
            r2 = 0
            if (r1 == 0) goto L_0x0013
            r6 = r2
            goto L_0x0015
        L_0x0013:
            r6 = r31
        L_0x0015:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x001b
            r7 = r2
            goto L_0x001d
        L_0x001b:
            r7 = r32
        L_0x001d:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0023
            r8 = r2
            goto L_0x0025
        L_0x0023:
            r8 = r33
        L_0x0025:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x002c
            r1 = 1
            r9 = r1
            goto L_0x002e
        L_0x002c:
            r9 = r34
        L_0x002e:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0034
            r11 = r2
            goto L_0x0036
        L_0x0034:
            r11 = r36
        L_0x0036:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x003e
            r3 = -1
            r12 = r3
            goto L_0x0040
        L_0x003e:
            r12 = r37
        L_0x0040:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0048
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.s
            r15 = r1
            goto L_0x004a
        L_0x0048:
            r15 = r40
        L_0x004a:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x0051
            r16 = r2
            goto L_0x0053
        L_0x0051:
            r16 = r41
        L_0x0053:
            r1 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r1 == 0) goto L_0x005c
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.s
            r17 = r1
            goto L_0x005e
        L_0x005c:
            r17 = r42
        L_0x005e:
            r1 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r1 == 0) goto L_0x0067
            com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.ImageTitleBody
            r18 = r1
            goto L_0x0069
        L_0x0067:
            r18 = r43
        L_0x0069:
            r1 = 32768(0x8000, float:4.5918E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0072
            r19 = r2
            goto L_0x0074
        L_0x0072:
            r19 = r44
        L_0x0074:
            r1 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x007c
            r20 = r2
            goto L_0x007e
        L_0x007c:
            r20 = r45
        L_0x007e:
            r1 = 131072(0x20000, float:1.83671E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0086
            r21 = r2
            goto L_0x0088
        L_0x0086:
            r21 = r46
        L_0x0088:
            r1 = 262144(0x40000, float:3.67342E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0090
            r22 = r2
            goto L_0x0092
        L_0x0090:
            r22 = r47
        L_0x0092:
            r1 = 524288(0x80000, float:7.34684E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x009c
            com.salesforce.marketingcloud.messages.iam.InAppMessage$ButtonConfig r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.ButtonConfig.twoUp
            r23 = r1
            goto L_0x009e
        L_0x009c:
            r23 = r48
        L_0x009e:
            r1 = 1048576(0x100000, float:1.469368E-39)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00a6
            r24 = r2
            goto L_0x00a8
        L_0x00a6:
            r24 = r49
        L_0x00a8:
            r1 = 2097152(0x200000, float:2.938736E-39)
            r1 = r1 & r0
            r2 = 0
            if (r1 == 0) goto L_0x00b1
            r25 = r2
            goto L_0x00b3
        L_0x00b1:
            r25 = r50
        L_0x00b3:
            r1 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x00bb
            r26 = r2
            goto L_0x00bd
        L_0x00bb:
            r26 = r51
        L_0x00bd:
            r2 = r27
            r3 = r28
            r4 = r29
            r10 = r35
            r14 = r39
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.InAppMessage.<init>(java.lang.String, java.lang.String, int, java.util.Date, java.util.Date, java.util.Date, int, com.salesforce.marketingcloud.messages.iam.InAppMessage$Type, java.lang.String, long, java.lang.String, com.salesforce.marketingcloud.messages.iam.InAppMessage$Size, java.lang.String, com.salesforce.marketingcloud.messages.iam.InAppMessage$Size, com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder, com.salesforce.marketingcloud.messages.iam.InAppMessage$Media, com.salesforce.marketingcloud.messages.iam.InAppMessage$TextField, com.salesforce.marketingcloud.messages.iam.InAppMessage$TextField, com.salesforce.marketingcloud.messages.iam.InAppMessage$CloseButton, com.salesforce.marketingcloud.messages.iam.InAppMessage$ButtonConfig, java.util.List, int, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InAppMessage(org.json.JSONObject r30) throws org.json.JSONException {
        /*
            r29 = this;
            r0 = r30
            java.lang.String r1 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "id"
            java.lang.String r3 = r0.getString(r1)
            java.lang.String r1 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            java.lang.String r2 = "activityInstanceId"
            java.lang.String r4 = r0.getString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r1)
            java.lang.String r2 = "priority"
            r5 = 999(0x3e7, float:1.4E-42)
            int r5 = r0.optInt(r2, r5)
            java.lang.String r2 = "startDateUtc"
            java.lang.String r2 = r0.optString(r2)
            java.lang.String r6 = "optString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r2 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            if (r2 == 0) goto L_0x003a
            java.util.Date r2 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r2)
            r8 = r2
            goto L_0x003b
        L_0x003a:
            r8 = 0
        L_0x003b:
            java.lang.String r2 = "endDateUtc"
            java.lang.String r2 = r0.optString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r2 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            if (r2 == 0) goto L_0x0050
            java.util.Date r2 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r2)
            r9 = r2
            goto L_0x0051
        L_0x0050:
            r9 = 0
        L_0x0051:
            java.lang.String r2 = "modifiedDateUtc"
            java.lang.String r2 = r0.optString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r2 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            if (r2 == 0) goto L_0x0066
            java.util.Date r2 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r2)
            r10 = r2
            goto L_0x0067
        L_0x0066:
            r10 = 0
        L_0x0067:
            java.lang.String r2 = "displayLimit"
            r11 = 1
            int r11 = r0.optInt(r2, r11)
            java.lang.String r2 = "type"
            java.lang.String r2 = r0.getString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Type r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.valueOf(r2)
            java.lang.String r2 = "windowColor"
            java.lang.String r2 = r0.optString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r12 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            java.lang.String r2 = "displayDuration"
            r13 = -1
            long r13 = r0.optLong(r2, r13)
            java.lang.String r2 = "backgroundColor"
            java.lang.String r2 = r0.optString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r15 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r2)
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.s
            java.lang.String r7 = "borderWidth"
            java.lang.String r7 = r0.optString(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r6)
            java.lang.String r7 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r7)
            if (r7 == 0) goto L_0x00b5
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r7 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.valueOf(r7)
            r17 = r7
            goto L_0x00b7
        L_0x00b5:
            r17 = r2
        L_0x00b7:
            java.lang.String r7 = "borderColor"
            java.lang.String r7 = r0.optString(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r6)
            java.lang.String r18 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r7)
            java.lang.String r7 = "cornerRadius"
            java.lang.String r7 = r0.optString(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r6)
            java.lang.String r7 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r7)
            if (r7 == 0) goto L_0x00d7
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.valueOf(r7)
        L_0x00d7:
            r19 = r2
            com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.ImageTitleBody
            java.lang.String r7 = "layoutOrder"
            java.lang.String r7 = r0.optString(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r6)
            java.lang.String r7 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r7)
            if (r7 == 0) goto L_0x00ee
            com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.valueOf(r7)
        L_0x00ee:
            r20 = r2
            java.lang.String r2 = "media"
            org.json.JSONObject r2 = r0.optJSONObject(r2)
            if (r2 == 0) goto L_0x00ff
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Media r2 = com.salesforce.marketingcloud.messages.iam.h.b(r2)
            r21 = r2
            goto L_0x0101
        L_0x00ff:
            r21 = 0
        L_0x0101:
            java.lang.String r2 = "title"
            org.json.JSONObject r2 = r0.optJSONObject(r2)
            if (r2 == 0) goto L_0x0110
            com.salesforce.marketingcloud.messages.iam.InAppMessage$TextField r2 = com.salesforce.marketingcloud.messages.iam.h.c(r2)
            r22 = r2
            goto L_0x0112
        L_0x0110:
            r22 = 0
        L_0x0112:
            java.lang.String r2 = "body"
            org.json.JSONObject r2 = r0.optJSONObject(r2)
            if (r2 == 0) goto L_0x0121
            com.salesforce.marketingcloud.messages.iam.InAppMessage$TextField r2 = com.salesforce.marketingcloud.messages.iam.h.c(r2)
            r23 = r2
            goto L_0x0123
        L_0x0121:
            r23 = 0
        L_0x0123:
            java.lang.String r2 = "closeButton"
            org.json.JSONObject r2 = r0.optJSONObject(r2)
            if (r2 == 0) goto L_0x0132
            com.salesforce.marketingcloud.messages.iam.InAppMessage$CloseButton r2 = com.salesforce.marketingcloud.messages.iam.h.a((org.json.JSONObject) r2)
            r24 = r2
            goto L_0x0134
        L_0x0132:
            r24 = 0
        L_0x0134:
            com.salesforce.marketingcloud.messages.iam.InAppMessage$ButtonConfig r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.ButtonConfig.twoUp
            java.lang.String r7 = "buttonConfiguration"
            java.lang.String r7 = r0.optString(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r6)
            java.lang.String r6 = com.salesforce.marketingcloud.internal.m.b((java.lang.String) r7)
            if (r6 == 0) goto L_0x0149
            com.salesforce.marketingcloud.messages.iam.InAppMessage$ButtonConfig r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.ButtonConfig.valueOf(r6)
        L_0x0149:
            r27 = r2
            java.lang.String r2 = "buttons"
            org.json.JSONArray r2 = r0.optJSONArray(r2)
            if (r2 == 0) goto L_0x015a
            java.util.List r2 = com.salesforce.marketingcloud.messages.iam.h.a((org.json.JSONArray) r2)
            r28 = r2
            goto L_0x015c
        L_0x015a:
            r28 = 0
        L_0x015c:
            java.lang.String r2 = "messageDelaySec"
            r6 = 0
            int r25 = r0.optInt(r2, r6)
            java.lang.String r2 = "displayLimitOverride"
            boolean r26 = r0.optBoolean(r2, r6)
            r2 = r29
            r6 = r8
            r7 = r9
            r8 = r10
            r9 = r11
            r10 = r1
            r11 = r12
            r12 = r13
            r14 = r15
            r15 = r17
            r16 = r18
            r17 = r19
            r18 = r20
            r19 = r21
            r20 = r22
            r21 = r23
            r22 = r24
            r23 = r27
            r24 = r28
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.InAppMessage.<init>(org.json.JSONObject):void");
    }
}
