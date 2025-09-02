package com.salesforce.marketingcloud;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions;
import com.salesforce.marketingcloud.proximity.ProximityNotificationCustomizationOptions;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleConfig;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import okhttp3.internal.http2.Http2;

@MCKeep
public final class MarketingCloudConfig extends PushModuleConfig {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = g.a("MarketingCloudConfig");
    private final String accessToken;
    private final boolean analyticsEnabled;
    private final String appPackageName;
    private final String appVersionName;
    private final String applicationId;
    private final boolean delayRegistrationUntilContactKeyIsSet;
    private final boolean geofencingEnabled;
    private final boolean inboxEnabled;
    private final boolean markMessageReadOnInboxNotificationOpen;
    private final String marketingCloudServerUrl;
    private final String mid;
    private final NotificationCustomizationOptions notificationCustomizationOptions;
    private final boolean piAnalyticsEnabled;
    private final String predictiveIntelligenceServerUrl;
    private final boolean proximityEnabled;
    private final ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions;
    private final String senderId;
    private final UrlHandler urlHandler;
    private final boolean useLegacyPiIdentifier;

    @MCKeep
    public static final class Builder {
        private static final int ACCESS_TOKEN_LENGTH = 24;
        private static final Regex APP_ID_REGEX = new Regex("[0-9a-f]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}");
        public static final a Companion = new a((DefaultConstructorMarker) null);
        private static final String INITIAL_PI_VALUE = "";
        private static final String TSE_ERROR_MSG = "An App Endpoint (the Marketing Cloud Server URL) is required in order to configure the SDK. See http://salesforce-marketingcloud.github.io/MarketingCloudSDK-Android for more information.";
        private String accessToken;
        private boolean analyticsEnabled;
        private String applicationId;
        private boolean delayRegistrationUntilContactKeyIsSet;
        private boolean geofencingEnabled;
        private boolean inboxEnabled;
        private boolean markMessageReadOnInboxNotificationOpen;
        private String marketingCloudServerUrl;
        private String mid;
        private NotificationCustomizationOptions notificationCustomizationOptions;
        private boolean piAnalyticsEnabled;
        private String predictiveIntelligenceServerUrl;
        private boolean proximityEnabled;
        private ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions;
        private String senderId;
        private UrlHandler urlHandler;
        private boolean useLegacyPiIdentifier;

        public static final class a {
            private a() {
            }

            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public Builder() {
            this.markMessageReadOnInboxNotificationOpen = true;
            this.useLegacyPiIdentifier = true;
            this.predictiveIntelligenceServerUrl = "";
        }

        private final String checkNotEmpty(String str, Function0 function0) {
            if (str == null || TextUtils.getTrimmedLength(str) != 0) {
                return str;
            }
            throw new IllegalStateException(function0.invoke().toString());
        }

        private final String checkNotNullOrEmpty(String str, Function0 function0) {
            if (str != null && TextUtils.getTrimmedLength(str) != 0) {
                return str;
            }
            throw new IllegalStateException(function0.invoke().toString());
        }

        /* renamed from: -setPredictiveIntelligenceServerUrl  reason: not valid java name */
        public final Builder m631setPredictiveIntelligenceServerUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            this.predictiveIntelligenceServerUrl = str;
            return this;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00aa, code lost:
            if (r3 == null) goto L_0x00ac;
         */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x0142  */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.salesforce.marketingcloud.MarketingCloudConfig build(android.content.Context r25) {
            /*
                r24 = this;
                r0 = r24
                java.lang.String r1 = "null"
                java.lang.String r2 = "context"
                r3 = r25
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
                java.lang.String r2 = r25.getPackageName()
                r4 = 0
                android.content.pm.PackageManager r3 = r25.getPackageManager()     // Catch:{ Exception -> 0x001c }
                android.content.pm.PackageInfo r3 = r3.getPackageInfo(r2, r4)     // Catch:{ Exception -> 0x001c }
                java.lang.String r3 = r3.versionName     // Catch:{ Exception -> 0x001c }
                if (r3 != 0) goto L_0x001f
            L_0x001c:
                r21 = r1
                goto L_0x0021
            L_0x001f:
                r21 = r3
            L_0x0021:
                java.lang.String r1 = r0.applicationId
                if (r1 == 0) goto L_0x0142
                java.util.Locale r3 = java.util.Locale.ENGLISH
                java.lang.String r5 = "ENGLISH"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
                java.lang.String r6 = r1.toLowerCase(r3)
                java.lang.String r7 = "this as java.lang.String).toLowerCase(locale)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
                kotlin.text.Regex r8 = APP_ID_REGEX
                boolean r6 = r8.matches(r6)
                if (r6 == 0) goto L_0x013a
                java.lang.String r6 = r0.accessToken
                if (r6 == 0) goto L_0x0132
                int r8 = r6.length()
                r9 = 24
                if (r8 != r9) goto L_0x012a
                java.lang.String r8 = r0.marketingCloudServerUrl
                java.lang.String r9 = "An App Endpoint (the Marketing Cloud Server URL) is required in order to configure the SDK. See http://salesforce-marketingcloud.github.io/MarketingCloudSDK-Android for more information."
                if (r8 == 0) goto L_0x0124
                int r10 = android.text.TextUtils.getTrimmedLength(r8)
                if (r10 == 0) goto L_0x0124
                boolean r10 = android.webkit.URLUtil.isNetworkUrl(r8)
                if (r10 == 0) goto L_0x011e
                java.lang.String r9 = r0.predictiveIntelligenceServerUrl
                java.lang.String r10 = ""
                boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
                if (r9 == 0) goto L_0x00b0
                java.lang.String r9 = r0.mid
                if (r9 == 0) goto L_0x00ac
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
                java.lang.String r10 = r9.toLowerCase(r3)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r7)
                r14 = 6
                r15 = 0
                java.lang.String r11 = "-"
                r12 = 0
                r13 = 0
                int r3 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r10, (java.lang.String) r11, (int) r12, (boolean) r13, (int) r14, (java.lang.Object) r15)
                r5 = -1
                if (r3 == r5) goto L_0x0089
                java.lang.CharSequence r3 = r9.subSequence(r4, r3)
                java.lang.String r3 = r3.toString()
                goto L_0x008a
            L_0x0089:
                r3 = r9
            L_0x008a:
                r0.mid = r3
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "https://"
                r4.append(r5)
                r4.append(r9)
                java.lang.String r5 = ".collect.igodigital.com/c2/"
                r4.append(r5)
                r4.append(r3)
                java.lang.String r3 = "/process_batch.json"
                r4.append(r3)
                java.lang.String r3 = r4.toString()
                if (r3 != 0) goto L_0x00ae
            L_0x00ac:
                java.lang.String r3 = "https://app.igodigital.com/api/v1/collect/process_batch"
            L_0x00ae:
                r0.predictiveIntelligenceServerUrl = r3
            L_0x00b0:
                java.lang.String r7 = r0.senderId
                if (r7 == 0) goto L_0x00c3
                int r3 = android.text.TextUtils.getTrimmedLength(r7)
                if (r3 == 0) goto L_0x00bb
                goto L_0x00c3
            L_0x00bb:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "The senderId cannot be empty."
                r1.<init>(r2)
                throw r1
            L_0x00c3:
                java.lang.String r9 = r0.mid
                if (r9 == 0) goto L_0x00d6
                int r3 = android.text.TextUtils.getTrimmedLength(r9)
                if (r3 == 0) goto L_0x00ce
                goto L_0x00d6
            L_0x00ce:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "MID must not be empty."
                r1.<init>(r2)
                throw r1
            L_0x00d6:
                boolean r10 = r0.analyticsEnabled
                boolean r11 = r0.geofencingEnabled
                boolean r12 = r0.inboxEnabled
                boolean r13 = r0.piAnalyticsEnabled
                boolean r14 = r0.proximityEnabled
                boolean r15 = r0.markMessageReadOnInboxNotificationOpen
                boolean r5 = r0.delayRegistrationUntilContactKeyIsSet
                boolean r4 = r0.useLegacyPiIdentifier
                com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions r3 = r0.notificationCustomizationOptions
                if (r3 == 0) goto L_0x0116
                r16 = r3
                com.salesforce.marketingcloud.UrlHandler r3 = r0.urlHandler
                r19 = r3
                java.lang.String r3 = r0.predictiveIntelligenceServerUrl
                r22 = r3
                com.salesforce.marketingcloud.proximity.ProximityNotificationCustomizationOptions r3 = r0.proximityNotificationCustomizationOptions
                r18 = r3
                com.salesforce.marketingcloud.MarketingCloudConfig r23 = new com.salesforce.marketingcloud.MarketingCloudConfig
                r17 = r16
                r3 = r23
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                r16 = r4
                r4 = r1
                r1 = r5
                r5 = r6
                r6 = r7
                r7 = r8
                r8 = r9
                r9 = r10
                r10 = r11
                r11 = r12
                r12 = r13
                r13 = r14
                r14 = r15
                r15 = r1
                r20 = r2
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
                return r23
            L_0x0116:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "notificationCustomizationOptions == null"
                r1.<init>(r2)
                throw r1
            L_0x011e:
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                r1.<init>(r9)
                throw r1
            L_0x0124:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                r1.<init>(r9)
                throw r1
            L_0x012a:
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r2 = "The accessToken must be 24 characters."
                r1.<init>(r2)
                throw r1
            L_0x0132:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "accessToken == null"
                r1.<init>(r2)
                throw r1
            L_0x013a:
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r2 = "The applicationId is not a valid UUID."
                r1.<init>(r2)
                throw r1
            L_0x0142:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "applicationId == null"
                r1.<init>(r2)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MarketingCloudConfig.Builder.build(android.content.Context):com.salesforce.marketingcloud.MarketingCloudConfig");
        }

        public final Builder setAccessToken(String str) {
            Intrinsics.checkNotNullParameter(str, "accessToken");
            this.accessToken = str;
            return this;
        }

        public final Builder setAnalyticsEnabled(boolean z) {
            this.analyticsEnabled = z;
            return this;
        }

        public final Builder setApplicationId(String str) {
            Intrinsics.checkNotNullParameter(str, "applicationId");
            this.applicationId = str;
            return this;
        }

        public final Builder setDelayRegistrationUntilContactKeyIsSet(boolean z) {
            this.delayRegistrationUntilContactKeyIsSet = z;
            return this;
        }

        public final Builder setGeofencingEnabled(boolean z) {
            this.geofencingEnabled = z;
            return this;
        }

        public final Builder setInboxEnabled(boolean z) {
            this.inboxEnabled = z;
            return this;
        }

        public final Builder setMarkMessageReadOnInboxNotificationOpen(boolean z) {
            this.markMessageReadOnInboxNotificationOpen = z;
            return this;
        }

        public final Builder setMarketingCloudServerUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "marketingCloudServerUrl");
            this.marketingCloudServerUrl = str;
            return this;
        }

        public final Builder setMid(String str) {
            Intrinsics.checkNotNullParameter(str, "mid");
            this.mid = str;
            return this;
        }

        public final Builder setNotificationCustomizationOptions(NotificationCustomizationOptions notificationCustomizationOptions2) {
            Intrinsics.checkNotNullParameter(notificationCustomizationOptions2, "options");
            this.notificationCustomizationOptions = notificationCustomizationOptions2;
            return this;
        }

        public final Builder setPiAnalyticsEnabled(boolean z) {
            this.piAnalyticsEnabled = z;
            return this;
        }

        public final Builder setProximityEnabled(boolean z) {
            this.proximityEnabled = z;
            return this;
        }

        public final Builder setProximityNotificationOptions(ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions2) {
            Intrinsics.checkNotNullParameter(proximityNotificationCustomizationOptions2, "options");
            this.proximityNotificationCustomizationOptions = proximityNotificationCustomizationOptions2;
            return this;
        }

        public final Builder setSenderId(String str) {
            Intrinsics.checkNotNullParameter(str, "senderId");
            this.senderId = str;
            return this;
        }

        public final Builder setUrlHandler(UrlHandler urlHandler2) {
            Intrinsics.checkNotNullParameter(urlHandler2, "urlHandler");
            this.urlHandler = urlHandler2;
            return this;
        }

        public final Builder setUseLegacyPiIdentifier(boolean z) {
            this.useLegacyPiIdentifier = z;
            return this;
        }

        public Builder(MarketingCloudConfig marketingCloudConfig) {
            Intrinsics.checkNotNullParameter(marketingCloudConfig, "config");
            this.markMessageReadOnInboxNotificationOpen = true;
            this.useLegacyPiIdentifier = true;
            this.applicationId = marketingCloudConfig.applicationId();
            this.accessToken = marketingCloudConfig.accessToken();
            this.senderId = marketingCloudConfig.senderId();
            this.marketingCloudServerUrl = marketingCloudConfig.marketingCloudServerUrl();
            this.mid = marketingCloudConfig.mid();
            this.analyticsEnabled = marketingCloudConfig.analyticsEnabled();
            this.geofencingEnabled = marketingCloudConfig.geofencingEnabled();
            this.inboxEnabled = marketingCloudConfig.inboxEnabled();
            this.piAnalyticsEnabled = marketingCloudConfig.piAnalyticsEnabled();
            this.proximityEnabled = marketingCloudConfig.proximityEnabled();
            this.markMessageReadOnInboxNotificationOpen = marketingCloudConfig.markMessageReadOnInboxNotificationOpen();
            this.delayRegistrationUntilContactKeyIsSet = marketingCloudConfig.delayRegistrationUntilContactKeyIsSet();
            this.useLegacyPiIdentifier = marketingCloudConfig.useLegacyPiIdentifier();
            this.notificationCustomizationOptions = marketingCloudConfig.notificationCustomizationOptions();
            this.proximityNotificationCustomizationOptions = marketingCloudConfig.proximityNotificationCustomizationOptions();
            this.urlHandler = marketingCloudConfig.urlHandler();
            this.predictiveIntelligenceServerUrl = marketingCloudConfig.predictiveIntelligenceServerUrl();
        }
    }

    @MCKeep
    public static final class Companion {
        private Companion() {
        }

        public final Builder builder() {
            return new Builder();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static final class a extends Lambda implements Function0 {
        final /* synthetic */ InitializationStatus a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(InitializationStatus initializationStatus) {
            super(0);
            this.a = initializationStatus;
        }

        /* renamed from: a */
        public final String invoke() {
            InitializationStatus initializationStatus = this.a;
            return "InitializationStatus: " + initializationStatus;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MarketingCloudConfig(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, NotificationCustomizationOptions notificationCustomizationOptions2, ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions2, UrlHandler urlHandler2, String str6, String str7, String str8) {
        super(str);
        NotificationCustomizationOptions notificationCustomizationOptions3 = notificationCustomizationOptions2;
        String str9 = str6;
        String str10 = str7;
        String str11 = str8;
        Intrinsics.checkNotNullParameter(str, "applicationId");
        Intrinsics.checkNotNullParameter(str2, "accessToken");
        Intrinsics.checkNotNullParameter(str4, "marketingCloudServerUrl");
        Intrinsics.checkNotNullParameter(notificationCustomizationOptions3, "notificationCustomizationOptions");
        Intrinsics.checkNotNullParameter(str9, "appPackageName");
        Intrinsics.checkNotNullParameter(str10, "appVersionName");
        Intrinsics.checkNotNullParameter(str11, "predictiveIntelligenceServerUrl");
        this.applicationId = str;
        this.accessToken = str2;
        this.senderId = str3;
        this.marketingCloudServerUrl = str4;
        this.mid = str5;
        this.analyticsEnabled = z;
        this.geofencingEnabled = z2;
        this.inboxEnabled = z3;
        this.piAnalyticsEnabled = z4;
        this.proximityEnabled = z5;
        this.markMessageReadOnInboxNotificationOpen = z6;
        this.delayRegistrationUntilContactKeyIsSet = z7;
        this.useLegacyPiIdentifier = z8;
        this.notificationCustomizationOptions = notificationCustomizationOptions3;
        this.proximityNotificationCustomizationOptions = proximityNotificationCustomizationOptions2;
        this.urlHandler = urlHandler2;
        this.appPackageName = str9;
        this.appVersionName = str10;
        this.predictiveIntelligenceServerUrl = str11;
    }

    public static final Builder builder() {
        return Companion.builder();
    }

    public static /* synthetic */ MarketingCloudConfig copy$default(MarketingCloudConfig marketingCloudConfig, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, NotificationCustomizationOptions notificationCustomizationOptions2, ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions2, UrlHandler urlHandler2, String str6, String str7, String str8, int i, Object obj) {
        MarketingCloudConfig marketingCloudConfig2 = marketingCloudConfig;
        int i2 = i;
        return marketingCloudConfig.copy((i2 & 1) != 0 ? marketingCloudConfig2.applicationId : str, (i2 & 2) != 0 ? marketingCloudConfig2.accessToken : str2, (i2 & 4) != 0 ? marketingCloudConfig2.senderId : str3, (i2 & 8) != 0 ? marketingCloudConfig2.marketingCloudServerUrl : str4, (i2 & 16) != 0 ? marketingCloudConfig2.mid : str5, (i2 & 32) != 0 ? marketingCloudConfig2.analyticsEnabled : z, (i2 & 64) != 0 ? marketingCloudConfig2.geofencingEnabled : z2, (i2 & 128) != 0 ? marketingCloudConfig2.inboxEnabled : z3, (i2 & 256) != 0 ? marketingCloudConfig2.piAnalyticsEnabled : z4, (i2 & 512) != 0 ? marketingCloudConfig2.proximityEnabled : z5, (i2 & 1024) != 0 ? marketingCloudConfig2.markMessageReadOnInboxNotificationOpen : z6, (i2 & b.u) != 0 ? marketingCloudConfig2.delayRegistrationUntilContactKeyIsSet : z7, (i2 & b.v) != 0 ? marketingCloudConfig2.useLegacyPiIdentifier : z8, (i2 & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0 ? marketingCloudConfig2.notificationCustomizationOptions : notificationCustomizationOptions2, (i2 & Http2.INITIAL_MAX_FRAME_SIZE) != 0 ? marketingCloudConfig2.proximityNotificationCustomizationOptions : proximityNotificationCustomizationOptions2, (i2 & 32768) != 0 ? marketingCloudConfig2.urlHandler : urlHandler2, (i2 & 65536) != 0 ? marketingCloudConfig2.appPackageName : str6, (i2 & 131072) != 0 ? marketingCloudConfig2.appVersionName : str7, (i2 & 262144) != 0 ? marketingCloudConfig2.predictiveIntelligenceServerUrl : str8);
    }

    /* access modifiers changed from: private */
    public static final void init$lambda$1(ModuleReadyListener moduleReadyListener, InitializationStatus initializationStatus) {
        Intrinsics.checkNotNullParameter(moduleReadyListener, "$listener");
        Intrinsics.checkNotNullParameter(initializationStatus, "it");
        g.e(g.a, TAG, (Throwable) null, new a(initializationStatus), 2, (Object) null);
        MarketingCloudSdk.requestSdk(new MarketingCloudConfig$$ExternalSyntheticLambda1(moduleReadyListener));
    }

    /* access modifiers changed from: private */
    public static final void init$lambda$1$lambda$0(ModuleReadyListener moduleReadyListener, MarketingCloudSdk marketingCloudSdk) {
        Intrinsics.checkNotNullParameter(moduleReadyListener, "$listener");
        Intrinsics.checkNotNullParameter(marketingCloudSdk, "sdk");
        moduleReadyListener.ready(marketingCloudSdk);
    }

    /* renamed from: -applicationChanged  reason: not valid java name */
    public final boolean m614applicationChanged(MarketingCloudConfig marketingCloudConfig) {
        Intrinsics.checkNotNullParameter(marketingCloudConfig, "other");
        return !Intrinsics.areEqual((Object) this.applicationId, (Object) marketingCloudConfig.applicationId) || !Intrinsics.areEqual((Object) this.accessToken, (Object) marketingCloudConfig.accessToken);
    }

    /* renamed from: -deprecated_accessToken  reason: not valid java name */
    public final String m615deprecated_accessToken() {
        return this.accessToken;
    }

    /* renamed from: -deprecated_analyticsEnabled  reason: not valid java name */
    public final boolean m616deprecated_analyticsEnabled() {
        return this.analyticsEnabled;
    }

    /* renamed from: -deprecated_applicationId  reason: not valid java name */
    public final String m617deprecated_applicationId() {
        return this.applicationId;
    }

    /* renamed from: -deprecated_delayRegistrationUntilContactKeyIsSet  reason: not valid java name */
    public final boolean m618deprecated_delayRegistrationUntilContactKeyIsSet() {
        return this.delayRegistrationUntilContactKeyIsSet;
    }

    /* renamed from: -deprecated_geofencingEnabled  reason: not valid java name */
    public final boolean m619deprecated_geofencingEnabled() {
        return this.geofencingEnabled;
    }

    /* renamed from: -deprecated_inboxEnabled  reason: not valid java name */
    public final boolean m620deprecated_inboxEnabled() {
        return this.inboxEnabled;
    }

    /* renamed from: -deprecated_markMessageReadOnInboxNotificationOpen  reason: not valid java name */
    public final boolean m621deprecated_markMessageReadOnInboxNotificationOpen() {
        return this.markMessageReadOnInboxNotificationOpen;
    }

    /* renamed from: -deprecated_marketingCloudServerUrl  reason: not valid java name */
    public final String m622deprecated_marketingCloudServerUrl() {
        return this.marketingCloudServerUrl;
    }

    /* renamed from: -deprecated_mid  reason: not valid java name */
    public final String m623deprecated_mid() {
        return this.mid;
    }

    /* renamed from: -deprecated_notificationCustomizationOptions  reason: not valid java name */
    public final NotificationCustomizationOptions m624deprecated_notificationCustomizationOptions() {
        return this.notificationCustomizationOptions;
    }

    /* renamed from: -deprecated_piAnalyticsEnabled  reason: not valid java name */
    public final boolean m625deprecated_piAnalyticsEnabled() {
        return this.piAnalyticsEnabled;
    }

    /* renamed from: -deprecated_proximityEnabled  reason: not valid java name */
    public final boolean m626deprecated_proximityEnabled() {
        return this.proximityEnabled;
    }

    /* renamed from: -deprecated_proximityNotificationCustomizationOptions  reason: not valid java name */
    public final ProximityNotificationCustomizationOptions m627deprecated_proximityNotificationCustomizationOptions() {
        return this.proximityNotificationCustomizationOptions;
    }

    /* renamed from: -deprecated_senderId  reason: not valid java name */
    public final String m628deprecated_senderId() {
        return this.senderId;
    }

    /* renamed from: -deprecated_urlHandler  reason: not valid java name */
    public final UrlHandler m629deprecated_urlHandler() {
        return this.urlHandler;
    }

    /* renamed from: -deprecated_useLegacyPiIdentifier  reason: not valid java name */
    public final boolean m630deprecated_useLegacyPiIdentifier() {
        return this.useLegacyPiIdentifier;
    }

    public final String accessToken() {
        return this.accessToken;
    }

    public final boolean analyticsEnabled() {
        return this.analyticsEnabled;
    }

    public final String appPackageName() {
        return this.appPackageName;
    }

    public final String appVersionName() {
        return this.appVersionName;
    }

    public final String applicationId() {
        return this.applicationId;
    }

    public final String component1() {
        return this.applicationId;
    }

    public final boolean component10() {
        return this.proximityEnabled;
    }

    public final boolean component11() {
        return this.markMessageReadOnInboxNotificationOpen;
    }

    public final boolean component12() {
        return this.delayRegistrationUntilContactKeyIsSet;
    }

    public final boolean component13() {
        return this.useLegacyPiIdentifier;
    }

    public final NotificationCustomizationOptions component14() {
        return this.notificationCustomizationOptions;
    }

    public final ProximityNotificationCustomizationOptions component15() {
        return this.proximityNotificationCustomizationOptions;
    }

    public final UrlHandler component16() {
        return this.urlHandler;
    }

    public final String component17$sdk_release() {
        return this.appPackageName;
    }

    public final String component18$sdk_release() {
        return this.appVersionName;
    }

    public final String component19$sdk_release() {
        return this.predictiveIntelligenceServerUrl;
    }

    public final String component2() {
        return this.accessToken;
    }

    public final String component3() {
        return this.senderId;
    }

    public final String component4() {
        return this.marketingCloudServerUrl;
    }

    public final String component5() {
        return this.mid;
    }

    public final boolean component6() {
        return this.analyticsEnabled;
    }

    public final boolean component7() {
        return this.geofencingEnabled;
    }

    public final boolean component8() {
        return this.inboxEnabled;
    }

    public final boolean component9() {
        return this.piAnalyticsEnabled;
    }

    public final MarketingCloudConfig copy(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, NotificationCustomizationOptions notificationCustomizationOptions2, ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions2, UrlHandler urlHandler2, String str6, String str7, String str8) {
        String str9 = str;
        Intrinsics.checkNotNullParameter(str9, "applicationId");
        Intrinsics.checkNotNullParameter(str2, "accessToken");
        Intrinsics.checkNotNullParameter(str4, "marketingCloudServerUrl");
        Intrinsics.checkNotNullParameter(notificationCustomizationOptions2, "notificationCustomizationOptions");
        Intrinsics.checkNotNullParameter(str6, "appPackageName");
        Intrinsics.checkNotNullParameter(str7, "appVersionName");
        Intrinsics.checkNotNullParameter(str8, "predictiveIntelligenceServerUrl");
        return new MarketingCloudConfig(str9, str2, str3, str4, str5, z, z2, z3, z4, z5, z6, z7, z8, notificationCustomizationOptions2, proximityNotificationCustomizationOptions2, urlHandler2, str6, str7, str8);
    }

    public final boolean delayRegistrationUntilContactKeyIsSet() {
        return this.delayRegistrationUntilContactKeyIsSet;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MarketingCloudConfig)) {
            return false;
        }
        MarketingCloudConfig marketingCloudConfig = (MarketingCloudConfig) obj;
        return Intrinsics.areEqual((Object) this.applicationId, (Object) marketingCloudConfig.applicationId) && Intrinsics.areEqual((Object) this.accessToken, (Object) marketingCloudConfig.accessToken) && Intrinsics.areEqual((Object) this.senderId, (Object) marketingCloudConfig.senderId) && Intrinsics.areEqual((Object) this.marketingCloudServerUrl, (Object) marketingCloudConfig.marketingCloudServerUrl) && Intrinsics.areEqual((Object) this.mid, (Object) marketingCloudConfig.mid) && this.analyticsEnabled == marketingCloudConfig.analyticsEnabled && this.geofencingEnabled == marketingCloudConfig.geofencingEnabled && this.inboxEnabled == marketingCloudConfig.inboxEnabled && this.piAnalyticsEnabled == marketingCloudConfig.piAnalyticsEnabled && this.proximityEnabled == marketingCloudConfig.proximityEnabled && this.markMessageReadOnInboxNotificationOpen == marketingCloudConfig.markMessageReadOnInboxNotificationOpen && this.delayRegistrationUntilContactKeyIsSet == marketingCloudConfig.delayRegistrationUntilContactKeyIsSet && this.useLegacyPiIdentifier == marketingCloudConfig.useLegacyPiIdentifier && Intrinsics.areEqual((Object) this.notificationCustomizationOptions, (Object) marketingCloudConfig.notificationCustomizationOptions) && Intrinsics.areEqual((Object) this.proximityNotificationCustomizationOptions, (Object) marketingCloudConfig.proximityNotificationCustomizationOptions) && Intrinsics.areEqual((Object) this.urlHandler, (Object) marketingCloudConfig.urlHandler) && Intrinsics.areEqual((Object) this.appPackageName, (Object) marketingCloudConfig.appPackageName) && Intrinsics.areEqual((Object) this.appVersionName, (Object) marketingCloudConfig.appVersionName) && Intrinsics.areEqual((Object) this.predictiveIntelligenceServerUrl, (Object) marketingCloudConfig.predictiveIntelligenceServerUrl);
    }

    public final boolean geofencingEnabled() {
        return this.geofencingEnabled;
    }

    public int hashCode() {
        int hashCode = ((this.applicationId.hashCode() * 31) + this.accessToken.hashCode()) * 31;
        String str = this.senderId;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.marketingCloudServerUrl.hashCode()) * 31;
        String str2 = this.mid;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        boolean z = this.analyticsEnabled;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i2 = (hashCode3 + (z ? 1 : 0)) * 31;
        boolean z3 = this.geofencingEnabled;
        if (z3) {
            z3 = true;
        }
        int i3 = (i2 + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.inboxEnabled;
        if (z4) {
            z4 = true;
        }
        int i4 = (i3 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.piAnalyticsEnabled;
        if (z5) {
            z5 = true;
        }
        int i5 = (i4 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.proximityEnabled;
        if (z6) {
            z6 = true;
        }
        int i6 = (i5 + (z6 ? 1 : 0)) * 31;
        boolean z7 = this.markMessageReadOnInboxNotificationOpen;
        if (z7) {
            z7 = true;
        }
        int i7 = (i6 + (z7 ? 1 : 0)) * 31;
        boolean z8 = this.delayRegistrationUntilContactKeyIsSet;
        if (z8) {
            z8 = true;
        }
        int i8 = (i7 + (z8 ? 1 : 0)) * 31;
        boolean z9 = this.useLegacyPiIdentifier;
        if (!z9) {
            z2 = z9;
        }
        int hashCode4 = (((i8 + (z2 ? 1 : 0)) * 31) + this.notificationCustomizationOptions.hashCode()) * 31;
        ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions2 = this.proximityNotificationCustomizationOptions;
        int hashCode5 = (hashCode4 + (proximityNotificationCustomizationOptions2 == null ? 0 : proximityNotificationCustomizationOptions2.hashCode())) * 31;
        UrlHandler urlHandler2 = this.urlHandler;
        if (urlHandler2 != null) {
            i = urlHandler2.hashCode();
        }
        return ((((((hashCode5 + i) * 31) + this.appPackageName.hashCode()) * 31) + this.appVersionName.hashCode()) * 31) + this.predictiveIntelligenceServerUrl.hashCode();
    }

    public final boolean inboxEnabled() {
        return this.inboxEnabled;
    }

    public void init(Context context, SFMCSdkComponents sFMCSdkComponents, ModuleReadyListener moduleReadyListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sFMCSdkComponents, "components");
        Intrinsics.checkNotNullParameter(moduleReadyListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        MarketingCloudSdk.b(context, this, sFMCSdkComponents, new MarketingCloudConfig$$ExternalSyntheticLambda0(moduleReadyListener));
    }

    public final boolean markMessageReadOnInboxNotificationOpen() {
        return this.markMessageReadOnInboxNotificationOpen;
    }

    public final String marketingCloudServerUrl() {
        return this.marketingCloudServerUrl;
    }

    public final String mid() {
        return this.mid;
    }

    public final NotificationCustomizationOptions notificationCustomizationOptions() {
        return this.notificationCustomizationOptions;
    }

    public final boolean piAnalyticsEnabled() {
        return this.piAnalyticsEnabled;
    }

    public final String predictiveIntelligenceServerUrl() {
        return this.predictiveIntelligenceServerUrl;
    }

    public final boolean proximityEnabled() {
        return this.proximityEnabled;
    }

    public final ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions() {
        return this.proximityNotificationCustomizationOptions;
    }

    public final String senderId() {
        return this.senderId;
    }

    public final Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        String str = this.applicationId;
        String str2 = this.accessToken;
        String str3 = this.senderId;
        String str4 = this.marketingCloudServerUrl;
        String str5 = this.mid;
        boolean z = this.analyticsEnabled;
        boolean z2 = this.geofencingEnabled;
        boolean z3 = this.inboxEnabled;
        boolean z4 = this.piAnalyticsEnabled;
        boolean z5 = this.proximityEnabled;
        boolean z6 = this.markMessageReadOnInboxNotificationOpen;
        boolean z7 = this.delayRegistrationUntilContactKeyIsSet;
        boolean z8 = this.useLegacyPiIdentifier;
        NotificationCustomizationOptions notificationCustomizationOptions2 = this.notificationCustomizationOptions;
        ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions2 = this.proximityNotificationCustomizationOptions;
        UrlHandler urlHandler2 = this.urlHandler;
        String str6 = this.appPackageName;
        String str7 = this.appVersionName;
        String str8 = this.predictiveIntelligenceServerUrl;
        return "MarketingCloudConfig(applicationId=" + str + ", accessToken=" + str2 + ", senderId=" + str3 + ", marketingCloudServerUrl=" + str4 + ", mid=" + str5 + ", analyticsEnabled=" + z + ", geofencingEnabled=" + z2 + ", inboxEnabled=" + z3 + ", piAnalyticsEnabled=" + z4 + ", proximityEnabled=" + z5 + ", markMessageReadOnInboxNotificationOpen=" + z6 + ", delayRegistrationUntilContactKeyIsSet=" + z7 + ", useLegacyPiIdentifier=" + z8 + ", notificationCustomizationOptions=" + notificationCustomizationOptions2 + ", proximityNotificationCustomizationOptions=" + proximityNotificationCustomizationOptions2 + ", urlHandler=" + urlHandler2 + ", appPackageName=" + str6 + ", appVersionName=" + str7 + ", predictiveIntelligenceServerUrl=" + str8 + ")";
    }

    public final UrlHandler urlHandler() {
        return this.urlHandler;
    }

    public final boolean useLegacyPiIdentifier() {
        return this.useLegacyPiIdentifier;
    }
}
