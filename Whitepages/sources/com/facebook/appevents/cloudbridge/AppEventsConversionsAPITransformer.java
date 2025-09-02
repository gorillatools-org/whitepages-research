package com.facebook.appevents.cloudbridge;

import com.facebook.LoggingBehavior;
import com.facebook.appevents.cloudbridge.AppEventType;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AppEventsConversionsAPITransformer {
    public static final AppEventsConversionsAPITransformer INSTANCE = new AppEventsConversionsAPITransformer();
    public static final Map customEventTransformations;
    public static final Map standardEventTransformations = MapsKt.mapOf(TuplesKt.to("fb_mobile_achievement_unlocked", ConversionsAPIEventName.UNLOCKED_ACHIEVEMENT), TuplesKt.to("fb_mobile_activate_app", ConversionsAPIEventName.ACTIVATED_APP), TuplesKt.to("fb_mobile_add_payment_info", ConversionsAPIEventName.ADDED_PAYMENT_INFO), TuplesKt.to("fb_mobile_add_to_cart", ConversionsAPIEventName.ADDED_TO_CART), TuplesKt.to("fb_mobile_add_to_wishlist", ConversionsAPIEventName.ADDED_TO_WISHLIST), TuplesKt.to("fb_mobile_complete_registration", ConversionsAPIEventName.COMPLETED_REGISTRATION), TuplesKt.to("fb_mobile_content_view", ConversionsAPIEventName.VIEWED_CONTENT), TuplesKt.to("fb_mobile_initiated_checkout", ConversionsAPIEventName.INITIATED_CHECKOUT), TuplesKt.to("fb_mobile_level_achieved", ConversionsAPIEventName.ACHIEVED_LEVEL), TuplesKt.to("fb_mobile_purchase", ConversionsAPIEventName.PURCHASED), TuplesKt.to("fb_mobile_rate", ConversionsAPIEventName.RATED), TuplesKt.to("fb_mobile_search", ConversionsAPIEventName.SEARCHED), TuplesKt.to("fb_mobile_spent_credits", ConversionsAPIEventName.SPENT_CREDITS), TuplesKt.to("fb_mobile_tutorial_completion", ConversionsAPIEventName.COMPLETED_TUTORIAL));
    private static final Map topLevelTransformations;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(2:1|2)|3|5|6|(2:7|8)|9|11|12|13|14|15|17|18|19|20|21|23) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0019 */
        static {
            /*
                com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer$ValueTransformationType[] r0 = com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.ValueTransformationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer$ValueTransformationType r2 = com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.ValueTransformationType.ARRAY     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer$ValueTransformationType r3 = com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.ValueTransformationType.BOOL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer$ValueTransformationType r3 = com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.ValueTransformationType.INT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r4 = 3
                r0[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                com.facebook.appevents.cloudbridge.ConversionsAPISection[] r0 = com.facebook.appevents.cloudbridge.ConversionsAPISection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.appevents.cloudbridge.ConversionsAPISection r3 = com.facebook.appevents.cloudbridge.ConversionsAPISection.APP_DATA     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.facebook.appevents.cloudbridge.ConversionsAPISection r3 = com.facebook.appevents.cloudbridge.ConversionsAPISection.USER_DATA     // Catch:{ NoSuchFieldError -> 0x003b }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                $EnumSwitchMapping$1 = r0
                com.facebook.appevents.cloudbridge.AppEventType[] r0 = com.facebook.appevents.cloudbridge.AppEventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.appevents.cloudbridge.AppEventType r3 = com.facebook.appevents.cloudbridge.AppEventType.MOBILE_APP_INSTALL     // Catch:{ NoSuchFieldError -> 0x004c }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x004c }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x004c }
            L_0x004c:
                com.facebook.appevents.cloudbridge.AppEventType r1 = com.facebook.appevents.cloudbridge.AppEventType.CUSTOM     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                $EnumSwitchMapping$2 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.WhenMappings.<clinit>():void");
        }
    }

    private AppEventsConversionsAPITransformer() {
    }

    public static final class SectionFieldMapping {
        private ConversionsAPIUserAndAppDataField field;
        private ConversionsAPISection section;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SectionFieldMapping)) {
                return false;
            }
            SectionFieldMapping sectionFieldMapping = (SectionFieldMapping) obj;
            return this.section == sectionFieldMapping.section && this.field == sectionFieldMapping.field;
        }

        public int hashCode() {
            int hashCode = this.section.hashCode() * 31;
            ConversionsAPIUserAndAppDataField conversionsAPIUserAndAppDataField = this.field;
            return hashCode + (conversionsAPIUserAndAppDataField == null ? 0 : conversionsAPIUserAndAppDataField.hashCode());
        }

        public String toString() {
            return "SectionFieldMapping(section=" + this.section + ", field=" + this.field + ')';
        }

        public SectionFieldMapping(ConversionsAPISection conversionsAPISection, ConversionsAPIUserAndAppDataField conversionsAPIUserAndAppDataField) {
            Intrinsics.checkNotNullParameter(conversionsAPISection, "section");
            this.section = conversionsAPISection;
            this.field = conversionsAPIUserAndAppDataField;
        }

        public final ConversionsAPISection getSection() {
            return this.section;
        }

        public final ConversionsAPIUserAndAppDataField getField() {
            return this.field;
        }
    }

    static {
        AppEventUserAndAppDataField appEventUserAndAppDataField = AppEventUserAndAppDataField.ANON_ID;
        ConversionsAPISection conversionsAPISection = ConversionsAPISection.USER_DATA;
        Pair pair = TuplesKt.to(appEventUserAndAppDataField, new SectionFieldMapping(conversionsAPISection, ConversionsAPIUserAndAppDataField.ANON_ID));
        Pair pair2 = TuplesKt.to(AppEventUserAndAppDataField.APP_USER_ID, new SectionFieldMapping(conversionsAPISection, ConversionsAPIUserAndAppDataField.FB_LOGIN_ID));
        Pair pair3 = TuplesKt.to(AppEventUserAndAppDataField.ADVERTISER_ID, new SectionFieldMapping(conversionsAPISection, ConversionsAPIUserAndAppDataField.MAD_ID));
        Pair pair4 = TuplesKt.to(AppEventUserAndAppDataField.PAGE_ID, new SectionFieldMapping(conversionsAPISection, ConversionsAPIUserAndAppDataField.PAGE_ID));
        Pair pair5 = TuplesKt.to(AppEventUserAndAppDataField.PAGE_SCOPED_USER_ID, new SectionFieldMapping(conversionsAPISection, ConversionsAPIUserAndAppDataField.PAGE_SCOPED_USER_ID));
        AppEventUserAndAppDataField appEventUserAndAppDataField2 = AppEventUserAndAppDataField.ADV_TE;
        ConversionsAPISection conversionsAPISection2 = ConversionsAPISection.APP_DATA;
        topLevelTransformations = MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, TuplesKt.to(appEventUserAndAppDataField2, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.ADV_TE)), TuplesKt.to(AppEventUserAndAppDataField.APP_TE, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.APP_TE)), TuplesKt.to(AppEventUserAndAppDataField.CONSIDER_VIEWS, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.CONSIDER_VIEWS)), TuplesKt.to(AppEventUserAndAppDataField.DEVICE_TOKEN, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.DEVICE_TOKEN)), TuplesKt.to(AppEventUserAndAppDataField.EXT_INFO, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.EXT_INFO)), TuplesKt.to(AppEventUserAndAppDataField.INCLUDE_DWELL_DATA, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.INCLUDE_DWELL_DATA)), TuplesKt.to(AppEventUserAndAppDataField.INCLUDE_VIDEO_DATA, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.INCLUDE_VIDEO_DATA)), TuplesKt.to(AppEventUserAndAppDataField.INSTALL_REFERRER, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.INSTALL_REFERRER)), TuplesKt.to(AppEventUserAndAppDataField.INSTALLER_PACKAGE, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.INSTALLER_PACKAGE)), TuplesKt.to(AppEventUserAndAppDataField.RECEIPT_DATA, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.RECEIPT_DATA)), TuplesKt.to(AppEventUserAndAppDataField.URL_SCHEMES, new SectionFieldMapping(conversionsAPISection2, ConversionsAPIUserAndAppDataField.URL_SCHEMES)), TuplesKt.to(AppEventUserAndAppDataField.USER_DATA, new SectionFieldMapping(conversionsAPISection, (ConversionsAPIUserAndAppDataField) null)));
        Pair pair6 = TuplesKt.to(CustomEventField.EVENT_TIME, new SectionCustomEventFieldMapping((ConversionsAPISection) null, ConversionsAPICustomEventField.EVENT_TIME));
        Pair pair7 = TuplesKt.to(CustomEventField.EVENT_NAME, new SectionCustomEventFieldMapping((ConversionsAPISection) null, ConversionsAPICustomEventField.EVENT_NAME));
        CustomEventField customEventField = CustomEventField.VALUE_TO_SUM;
        ConversionsAPISection conversionsAPISection3 = ConversionsAPISection.CUSTOM_DATA;
        customEventTransformations = MapsKt.mapOf(pair6, pair7, TuplesKt.to(customEventField, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.VALUE_TO_SUM)), TuplesKt.to(CustomEventField.CONTENT_IDS, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.CONTENT_IDS)), TuplesKt.to(CustomEventField.CONTENTS, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.CONTENTS)), TuplesKt.to(CustomEventField.CONTENT_TYPE, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.CONTENT_TYPE)), TuplesKt.to(CustomEventField.CURRENCY, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.CURRENCY)), TuplesKt.to(CustomEventField.DESCRIPTION, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.DESCRIPTION)), TuplesKt.to(CustomEventField.LEVEL, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.LEVEL)), TuplesKt.to(CustomEventField.MAX_RATING_VALUE, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.MAX_RATING_VALUE)), TuplesKt.to(CustomEventField.NUM_ITEMS, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.NUM_ITEMS)), TuplesKt.to(CustomEventField.PAYMENT_INFO_AVAILABLE, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.PAYMENT_INFO_AVAILABLE)), TuplesKt.to(CustomEventField.REGISTRATION_METHOD, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.REGISTRATION_METHOD)), TuplesKt.to(CustomEventField.SEARCH_STRING, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.SEARCH_STRING)), TuplesKt.to(CustomEventField.SUCCESS, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.SUCCESS)), TuplesKt.to(CustomEventField.ORDER_ID, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.ORDER_ID)), TuplesKt.to(CustomEventField.AD_TYPE, new SectionCustomEventFieldMapping(conversionsAPISection3, ConversionsAPICustomEventField.AD_TYPE)));
    }

    public static final class SectionCustomEventFieldMapping {
        private ConversionsAPICustomEventField field;
        private ConversionsAPISection section;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SectionCustomEventFieldMapping)) {
                return false;
            }
            SectionCustomEventFieldMapping sectionCustomEventFieldMapping = (SectionCustomEventFieldMapping) obj;
            return this.section == sectionCustomEventFieldMapping.section && this.field == sectionCustomEventFieldMapping.field;
        }

        public int hashCode() {
            ConversionsAPISection conversionsAPISection = this.section;
            return ((conversionsAPISection == null ? 0 : conversionsAPISection.hashCode()) * 31) + this.field.hashCode();
        }

        public String toString() {
            return "SectionCustomEventFieldMapping(section=" + this.section + ", field=" + this.field + ')';
        }

        public SectionCustomEventFieldMapping(ConversionsAPISection conversionsAPISection, ConversionsAPICustomEventField conversionsAPICustomEventField) {
            Intrinsics.checkNotNullParameter(conversionsAPICustomEventField, "field");
            this.section = conversionsAPISection;
            this.field = conversionsAPICustomEventField;
        }

        public final ConversionsAPISection getSection() {
            return this.section;
        }

        public final ConversionsAPICustomEventField getField() {
            return this.field;
        }
    }

    public enum DataProcessingParameterName {
        OPTIONS("data_processing_options"),
        COUNTRY("data_processing_options_country"),
        STATE("data_processing_options_state");
        
        public static final Companion Companion = null;
        private final String rawValue;

        private DataProcessingParameterName(String str) {
            this.rawValue = str;
        }

        public final String getRawValue() {
            return this.rawValue;
        }

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final DataProcessingParameterName invoke(String str) {
                Intrinsics.checkNotNullParameter(str, "rawValue");
                for (DataProcessingParameterName dataProcessingParameterName : DataProcessingParameterName.values()) {
                    if (Intrinsics.areEqual((Object) dataProcessingParameterName.getRawValue(), (Object) str)) {
                        return dataProcessingParameterName;
                    }
                }
                return null;
            }
        }
    }

    public enum ValueTransformationType {
        ARRAY,
        BOOL,
        INT;
        
        public static final Companion Companion = null;

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ValueTransformationType invoke(String str) {
                Intrinsics.checkNotNullParameter(str, "rawValue");
                if (Intrinsics.areEqual((Object) str, (Object) AppEventUserAndAppDataField.EXT_INFO.getRawValue())) {
                    return ValueTransformationType.ARRAY;
                }
                if (Intrinsics.areEqual((Object) str, (Object) AppEventUserAndAppDataField.URL_SCHEMES.getRawValue())) {
                    return ValueTransformationType.ARRAY;
                }
                if (Intrinsics.areEqual((Object) str, (Object) CustomEventField.CONTENT_IDS.getRawValue())) {
                    return ValueTransformationType.ARRAY;
                }
                if (Intrinsics.areEqual((Object) str, (Object) CustomEventField.CONTENTS.getRawValue())) {
                    return ValueTransformationType.ARRAY;
                }
                if (Intrinsics.areEqual((Object) str, (Object) DataProcessingParameterName.OPTIONS.getRawValue())) {
                    return ValueTransformationType.ARRAY;
                }
                if (Intrinsics.areEqual((Object) str, (Object) AppEventUserAndAppDataField.ADV_TE.getRawValue())) {
                    return ValueTransformationType.BOOL;
                }
                if (Intrinsics.areEqual((Object) str, (Object) AppEventUserAndAppDataField.APP_TE.getRawValue())) {
                    return ValueTransformationType.BOOL;
                }
                if (Intrinsics.areEqual((Object) str, (Object) CustomEventField.EVENT_TIME.getRawValue())) {
                    return ValueTransformationType.INT;
                }
                return null;
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:29|30) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:26|27|28|31|32|24) */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0081, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r1 = com.facebook.internal.Utility.convertJSONArrayToList(new org.json.JSONArray(r1));
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0081 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x008a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object transformValue$facebook_core_release(java.lang.String r4, java.lang.Object r5) {
        /*
            java.lang.String r0 = "field"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer$ValueTransformationType$Companion r0 = com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.ValueTransformationType.Companion
            com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer$ValueTransformationType r4 = r0.invoke(r4)
            boolean r0 = r5 instanceof java.lang.String
            r1 = 0
            if (r0 == 0) goto L_0x0019
            r0 = r5
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x001a
        L_0x0019:
            r0 = r1
        L_0x001a:
            if (r4 == 0) goto L_0x00a3
            if (r0 != 0) goto L_0x0020
            goto L_0x00a3
        L_0x0020:
            int[] r2 = com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.WhenMappings.$EnumSwitchMapping$0
            int r4 = r4.ordinal()
            r4 = r2[r4]
            r2 = 1
            if (r4 == r2) goto L_0x0057
            r3 = 2
            if (r4 == r3) goto L_0x0040
            r0 = 3
            if (r4 != r0) goto L_0x003a
            java.lang.String r4 = r5.toString()
            java.lang.Integer r4 = kotlin.text.StringsKt.toIntOrNull(r4)
            return r4
        L_0x003a:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L_0x0040:
            java.lang.String r4 = r0.toString()
            java.lang.Integer r4 = kotlin.text.StringsKt.toIntOrNull(r4)
            if (r4 == 0) goto L_0x0056
            int r4 = r4.intValue()
            if (r4 == 0) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r2 = 0
        L_0x0052:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
        L_0x0056:
            return r1
        L_0x0057:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x008e }
            r4.<init>(r0)     // Catch:{ JSONException -> 0x008e }
            java.util.List r4 = com.facebook.internal.Utility.convertJSONArrayToList(r4)     // Catch:{ JSONException -> 0x008e }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ JSONException -> 0x008e }
            r0.<init>()     // Catch:{ JSONException -> 0x008e }
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ JSONException -> 0x008e }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ JSONException -> 0x008e }
        L_0x006b:
            boolean r1 = r4.hasNext()     // Catch:{ JSONException -> 0x008e }
            if (r1 == 0) goto L_0x0090
            java.lang.Object r1 = r4.next()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ JSONException -> 0x008e }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0081 }
            r2.<init>(r1)     // Catch:{ JSONException -> 0x0081 }
            java.util.Map r1 = com.facebook.internal.Utility.convertJSONObjectToHashMap(r2)     // Catch:{ JSONException -> 0x0081 }
            goto L_0x008a
        L_0x0081:
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x008a }
            r2.<init>(r1)     // Catch:{ JSONException -> 0x008a }
            java.util.List r1 = com.facebook.internal.Utility.convertJSONArrayToList(r2)     // Catch:{ JSONException -> 0x008a }
        L_0x008a:
            r0.add(r1)     // Catch:{ JSONException -> 0x008e }
            goto L_0x006b
        L_0x008e:
            r4 = move-exception
            goto L_0x0091
        L_0x0090:
            return r0
        L_0x0091:
            com.facebook.internal.Logger$Companion r0 = com.facebook.internal.Logger.Companion
            com.facebook.LoggingBehavior r1 = com.facebook.LoggingBehavior.APP_EVENTS
            java.lang.String r2 = "\n transformEvents JSONException: \n%s\n%s"
            java.lang.Object[] r4 = new java.lang.Object[]{r5, r4}
            java.lang.String r5 = "AppEventsConversionsAPITransformer"
            r0.log((com.facebook.LoggingBehavior) r1, (java.lang.String) r5, (java.lang.String) r2, (java.lang.Object[]) r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L_0x00a3:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.transformValue$facebook_core_release(java.lang.String, java.lang.Object):java.lang.Object");
    }

    public static final ArrayList transformEvents$facebook_core_release(String str) {
        Intrinsics.checkNotNullParameter(str, "appEvents");
        ArrayList<Map> arrayList = new ArrayList<>();
        try {
            for (String jSONObject : Utility.convertJSONArrayToList(new JSONArray(str))) {
                arrayList.add(Utility.convertJSONObjectToHashMap(new JSONObject(jSONObject)));
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            for (Map map : arrayList) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (String str2 : map.keySet()) {
                    CustomEventField invoke = CustomEventField.Companion.invoke(str2);
                    SectionCustomEventFieldMapping sectionCustomEventFieldMapping = (SectionCustomEventFieldMapping) customEventTransformations.get(invoke);
                    if (!(invoke == null || sectionCustomEventFieldMapping == null)) {
                        ConversionsAPISection section = sectionCustomEventFieldMapping.getSection();
                        if (section == null) {
                            try {
                                String rawValue = sectionCustomEventFieldMapping.getField().getRawValue();
                                if (invoke == CustomEventField.EVENT_NAME && ((String) map.get(str2)) != null) {
                                    AppEventsConversionsAPITransformer appEventsConversionsAPITransformer = INSTANCE;
                                    Object obj = map.get(str2);
                                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                                    linkedHashMap2.put(rawValue, appEventsConversionsAPITransformer.transformEventName((String) obj));
                                } else if (invoke == CustomEventField.EVENT_TIME && ((Integer) map.get(str2)) != null) {
                                    Object obj2 = map.get(str2);
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Any");
                                    Object transformValue$facebook_core_release = transformValue$facebook_core_release(str2, obj2);
                                    Intrinsics.checkNotNull(transformValue$facebook_core_release, "null cannot be cast to non-null type kotlin.Any");
                                    linkedHashMap2.put(rawValue, transformValue$facebook_core_release);
                                }
                            } catch (ClassCastException e) {
                                Logger.Companion.log(LoggingBehavior.APP_EVENTS, "AppEventsConversionsAPITransformer", "\n transformEvents ClassCastException: \n %s ", ExceptionsKt.stackTraceToString(e));
                            }
                        } else if (section == ConversionsAPISection.CUSTOM_DATA) {
                            String rawValue2 = sectionCustomEventFieldMapping.getField().getRawValue();
                            Object obj3 = map.get(str2);
                            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Any");
                            Object transformValue$facebook_core_release2 = transformValue$facebook_core_release(str2, obj3);
                            Intrinsics.checkNotNull(transformValue$facebook_core_release2, "null cannot be cast to non-null type kotlin.Any");
                            linkedHashMap.put(rawValue2, transformValue$facebook_core_release2);
                        }
                    }
                }
                if (!linkedHashMap.isEmpty()) {
                    linkedHashMap2.put(ConversionsAPISection.CUSTOM_DATA.getRawValue(), linkedHashMap);
                }
                arrayList2.add(linkedHashMap2);
            }
            return arrayList2;
        } catch (JSONException e2) {
            Logger.Companion.log(LoggingBehavior.APP_EVENTS, "AppEventsConversionsAPITransformer", "\n transformEvents JSONException: \n%s\n%s", str, e2);
            return null;
        }
    }

    private final void transformAndUpdateAppData(Map map, AppEventUserAndAppDataField appEventUserAndAppDataField, Object obj) {
        ConversionsAPIUserAndAppDataField field;
        String rawValue;
        SectionFieldMapping sectionFieldMapping = (SectionFieldMapping) topLevelTransformations.get(appEventUserAndAppDataField);
        if (sectionFieldMapping != null && (field = sectionFieldMapping.getField()) != null && (rawValue = field.getRawValue()) != null) {
            map.put(rawValue, obj);
        }
    }

    private final void transformAndUpdateUserData(Map map, AppEventUserAndAppDataField appEventUserAndAppDataField, Object obj) {
        ConversionsAPIUserAndAppDataField field;
        String rawValue;
        if (appEventUserAndAppDataField == AppEventUserAndAppDataField.USER_DATA) {
            try {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                map.putAll(Utility.convertJSONObjectToHashMap(new JSONObject((String) obj)));
            } catch (JSONException e) {
                Logger.Companion.log(LoggingBehavior.APP_EVENTS, "AppEventsConversionsAPITransformer", "\n transformEvents JSONException: \n%s\n%s", obj, e);
            }
        } else {
            SectionFieldMapping sectionFieldMapping = (SectionFieldMapping) topLevelTransformations.get(appEventUserAndAppDataField);
            if (sectionFieldMapping != null && (field = sectionFieldMapping.getField()) != null && (rawValue = field.getRawValue()) != null) {
                map.put(rawValue, obj);
            }
        }
    }

    public final void transformAndUpdateAppAndUserData$facebook_core_release(Map map, Map map2, AppEventUserAndAppDataField appEventUserAndAppDataField, Object obj) {
        ConversionsAPISection section;
        Intrinsics.checkNotNullParameter(map, "userData");
        Intrinsics.checkNotNullParameter(map2, "appData");
        Intrinsics.checkNotNullParameter(appEventUserAndAppDataField, "field");
        Intrinsics.checkNotNullParameter(obj, "value");
        SectionFieldMapping sectionFieldMapping = (SectionFieldMapping) topLevelTransformations.get(appEventUserAndAppDataField);
        if (sectionFieldMapping != null && (section = sectionFieldMapping.getSection()) != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[section.ordinal()];
            if (i == 1) {
                transformAndUpdateAppData(map2, appEventUserAndAppDataField, obj);
            } else if (i == 2) {
                transformAndUpdateUserData(map, appEventUserAndAppDataField, obj);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r3 = r3.getRawValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String transformEventName(java.lang.String r3) {
        /*
            r2 = this;
            java.util.Map r0 = standardEventTransformations
            boolean r1 = r0.containsKey(r3)
            if (r1 == 0) goto L_0x0018
            java.lang.Object r3 = r0.get(r3)
            com.facebook.appevents.cloudbridge.ConversionsAPIEventName r3 = (com.facebook.appevents.cloudbridge.ConversionsAPIEventName) r3
            if (r3 == 0) goto L_0x0016
            java.lang.String r3 = r3.getRawValue()
            if (r3 != 0) goto L_0x0018
        L_0x0016:
            java.lang.String r3 = ""
        L_0x0018:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformer.transformEventName(java.lang.String):java.lang.String");
    }

    public final Map combineCommonFields$facebook_core_release(Map map, Map map2, Map map3) {
        Intrinsics.checkNotNullParameter(map, "userData");
        Intrinsics.checkNotNullParameter(map2, "appData");
        Intrinsics.checkNotNullParameter(map3, "restOfData");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(OtherEventConstants.ACTION_SOURCE.getRawValue(), OtherEventConstants.APP.getRawValue());
        linkedHashMap.put(ConversionsAPISection.USER_DATA.getRawValue(), map);
        linkedHashMap.put(ConversionsAPISection.APP_DATA.getRawValue(), map2);
        linkedHashMap.putAll(map3);
        return linkedHashMap;
    }

    private final List combineAllTransformedDataForMobileAppInstall(Map map, Object obj) {
        if (obj == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        linkedHashMap.put(ConversionsAPICustomEventField.EVENT_NAME.getRawValue(), OtherEventConstants.MOBILE_APP_INSTALL.getRawValue());
        linkedHashMap.put(ConversionsAPICustomEventField.EVENT_TIME.getRawValue(), obj);
        return CollectionsKt.listOf(linkedHashMap);
    }

    private final List combineAllTransformedDataForCustom(Map map, List list) {
        if (list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(map);
            linkedHashMap.putAll((Map) it.next());
            arrayList.add(linkedHashMap);
        }
        return arrayList;
    }

    public final List combineAllTransformedData$facebook_core_release(AppEventType appEventType, Map map, Map map2, Map map3, List list, Object obj) {
        Intrinsics.checkNotNullParameter(appEventType, "eventType");
        Intrinsics.checkNotNullParameter(map, "userData");
        Intrinsics.checkNotNullParameter(map2, "appData");
        Intrinsics.checkNotNullParameter(map3, "restOfData");
        Intrinsics.checkNotNullParameter(list, "customEvents");
        Map combineCommonFields$facebook_core_release = combineCommonFields$facebook_core_release(map, map2, map3);
        int i = WhenMappings.$EnumSwitchMapping$2[appEventType.ordinal()];
        if (i == 1) {
            return combineAllTransformedDataForMobileAppInstall(combineCommonFields$facebook_core_release, obj);
        }
        if (i != 2) {
            return null;
        }
        return combineAllTransformedDataForCustom(combineCommonFields$facebook_core_release, list);
    }

    private final AppEventType splitAppEventParameters(Map map, Map map2, Map map3, ArrayList arrayList, Map map4) {
        Object obj = map.get(OtherEventConstants.EVENT.getRawValue());
        AppEventType.Companion companion = AppEventType.Companion;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        AppEventType invoke = companion.invoke((String) obj);
        if (invoke == AppEventType.OTHER) {
            return invoke;
        }
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            AppEventUserAndAppDataField invoke2 = AppEventUserAndAppDataField.Companion.invoke(str);
            if (invoke2 != null) {
                INSTANCE.transformAndUpdateAppAndUserData$facebook_core_release(map2, map3, invoke2, value);
            } else {
                boolean areEqual = Intrinsics.areEqual((Object) str, (Object) ConversionsAPISection.CUSTOM_EVENTS.getRawValue());
                boolean z = value instanceof String;
                if (invoke == AppEventType.CUSTOM && areEqual && z) {
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.String");
                    ArrayList transformEvents$facebook_core_release = transformEvents$facebook_core_release((String) value);
                    if (transformEvents$facebook_core_release != null) {
                        arrayList.addAll(transformEvents$facebook_core_release);
                    }
                } else if (DataProcessingParameterName.Companion.invoke(str) != null) {
                    map4.put(str, value);
                }
            }
        }
        return invoke;
    }

    public final List conversionsAPICompatibleEvent$facebook_core_release(Map map) {
        Intrinsics.checkNotNullParameter(map, "parameters");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        AppEventType splitAppEventParameters = splitAppEventParameters(map, linkedHashMap, linkedHashMap2, arrayList, linkedHashMap3);
        if (splitAppEventParameters == AppEventType.OTHER) {
            return null;
        }
        return combineAllTransformedData$facebook_core_release(splitAppEventParameters, linkedHashMap, linkedHashMap2, linkedHashMap3, arrayList, map.get(OtherEventConstants.INSTALL_EVENT_TIME.getRawValue()));
    }
}
