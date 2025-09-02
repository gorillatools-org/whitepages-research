package com.facebook.internal;

import android.net.Uri;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

public final class FetchedAppSettings {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final JSONArray MACARuleMatchingSetting;
    private final boolean automaticLoggingEnabled;
    private final JSONArray bannedParams;
    private final JSONArray blocklistEvents;
    private final boolean codelessEventsEnabled;
    private final List currencyDedupeParameters;
    private final Long dedupeWindow;
    private final Map dialogConfigurations;
    private final FacebookRequestErrorClassification errorClassification;
    private final JSONArray eventBindings;
    private final boolean iAPAutomaticLoggingEnabled;
    private final Map migratedAutoLogValues;
    private final boolean monitorViaDialogEnabled;
    private final String nuxContent;
    private final boolean nuxEnabled;
    private final List prodDedupeParameters;
    private final JSONArray protectedModeStandardParamsSetting;
    private final List purchaseValueDedupeParameters;
    private final String rawAamRules;
    private final JSONArray redactedEvents;
    private final String restrictiveDataSetting;
    private final JSONArray schemaRestrictions;
    private final String sdkUpdateMessage;
    private final JSONArray sensitiveParams;
    private final int sessionTimeoutInSeconds;
    private final String smartLoginBookmarkIconURL;
    private final String smartLoginMenuIconURL;
    private final EnumSet smartLoginOptions;
    private final String suggestedEventsSetting;
    private final boolean supportsImplicitLogging;
    private final List testDedupeParameters;
    private final boolean trackUninstallEnabled;

    public FetchedAppSettings(boolean z, String str, boolean z2, int i, EnumSet enumSet, Map map, boolean z3, FacebookRequestErrorClassification facebookRequestErrorClassification, String str2, String str3, boolean z4, boolean z5, JSONArray jSONArray, String str4, boolean z6, boolean z7, String str5, String str6, String str7, JSONArray jSONArray2, JSONArray jSONArray3, Map map2, JSONArray jSONArray4, JSONArray jSONArray5, JSONArray jSONArray6, JSONArray jSONArray7, JSONArray jSONArray8, List list, List list2, List list3, List list4, Long l) {
        FacebookRequestErrorClassification facebookRequestErrorClassification2 = facebookRequestErrorClassification;
        String str8 = str2;
        String str9 = str3;
        String str10 = str4;
        Intrinsics.checkNotNullParameter(str, "nuxContent");
        Intrinsics.checkNotNullParameter(enumSet, "smartLoginOptions");
        Intrinsics.checkNotNullParameter(map, "dialogConfigurations");
        Intrinsics.checkNotNullParameter(facebookRequestErrorClassification2, "errorClassification");
        Intrinsics.checkNotNullParameter(str8, "smartLoginBookmarkIconURL");
        Intrinsics.checkNotNullParameter(str9, "smartLoginMenuIconURL");
        Intrinsics.checkNotNullParameter(str10, "sdkUpdateMessage");
        this.supportsImplicitLogging = z;
        this.nuxContent = str;
        this.nuxEnabled = z2;
        this.sessionTimeoutInSeconds = i;
        this.smartLoginOptions = enumSet;
        this.dialogConfigurations = map;
        this.automaticLoggingEnabled = z3;
        this.errorClassification = facebookRequestErrorClassification2;
        this.smartLoginBookmarkIconURL = str8;
        this.smartLoginMenuIconURL = str9;
        this.iAPAutomaticLoggingEnabled = z4;
        this.codelessEventsEnabled = z5;
        this.eventBindings = jSONArray;
        this.sdkUpdateMessage = str10;
        this.trackUninstallEnabled = z6;
        this.monitorViaDialogEnabled = z7;
        this.rawAamRules = str5;
        this.suggestedEventsSetting = str6;
        this.restrictiveDataSetting = str7;
        this.protectedModeStandardParamsSetting = jSONArray2;
        this.MACARuleMatchingSetting = jSONArray3;
        this.migratedAutoLogValues = map2;
        this.blocklistEvents = jSONArray4;
        this.redactedEvents = jSONArray5;
        this.sensitiveParams = jSONArray6;
        this.schemaRestrictions = jSONArray7;
        this.bannedParams = jSONArray8;
        this.currencyDedupeParameters = list;
        this.purchaseValueDedupeParameters = list2;
        this.prodDedupeParameters = list3;
        this.testDedupeParameters = list4;
        this.dedupeWindow = l;
    }

    public final int getSessionTimeoutInSeconds() {
        return this.sessionTimeoutInSeconds;
    }

    public final EnumSet getSmartLoginOptions() {
        return this.smartLoginOptions;
    }

    public final boolean getAutomaticLoggingEnabled() {
        return this.automaticLoggingEnabled;
    }

    public final FacebookRequestErrorClassification getErrorClassification() {
        return this.errorClassification;
    }

    public final boolean getIAPAutomaticLoggingEnabled() {
        return this.iAPAutomaticLoggingEnabled;
    }

    public final boolean getCodelessEventsEnabled() {
        return this.codelessEventsEnabled;
    }

    public final JSONArray getEventBindings() {
        return this.eventBindings;
    }

    public final String getSdkUpdateMessage() {
        return this.sdkUpdateMessage;
    }

    public final String getRawAamRules() {
        return this.rawAamRules;
    }

    public final String getSuggestedEventsSetting() {
        return this.suggestedEventsSetting;
    }

    public final String getRestrictiveDataSetting() {
        return this.restrictiveDataSetting;
    }

    public final JSONArray getProtectedModeStandardParamsSetting() {
        return this.protectedModeStandardParamsSetting;
    }

    public final JSONArray getMACARuleMatchingSetting() {
        return this.MACARuleMatchingSetting;
    }

    public final JSONArray getBlocklistEvents() {
        return this.blocklistEvents;
    }

    public final JSONArray getRedactedEvents() {
        return this.redactedEvents;
    }

    public final JSONArray getSensitiveParams() {
        return this.sensitiveParams;
    }

    public final JSONArray getSchemaRestrictions() {
        return this.schemaRestrictions;
    }

    public final JSONArray getBannedParams() {
        return this.bannedParams;
    }

    public final List getCurrencyDedupeParameters() {
        return this.currencyDedupeParameters;
    }

    public final List getPurchaseValueDedupeParameters() {
        return this.purchaseValueDedupeParameters;
    }

    public final List getProdDedupeParameters() {
        return this.prodDedupeParameters;
    }

    public final List getTestDedupeParameters() {
        return this.testDedupeParameters;
    }

    public final Long getDedupeWindow() {
        return this.dedupeWindow;
    }

    public final boolean supportsImplicitLogging() {
        return this.supportsImplicitLogging;
    }

    public static final class DialogFeatureConfig {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String dialogName;
        private final Uri fallbackUrl;
        private final String featureName;
        private final int[] versionSpec;

        public /* synthetic */ DialogFeatureConfig(String str, String str2, Uri uri, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, uri, iArr);
        }

        private DialogFeatureConfig(String str, String str2, Uri uri, int[] iArr) {
            this.dialogName = str;
            this.featureName = str2;
            this.fallbackUrl = uri;
            this.versionSpec = iArr;
        }

        public final String getDialogName() {
            return this.dialogName;
        }

        public final String getFeatureName() {
            return this.featureName;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final DialogFeatureConfig parseDialogConfig(JSONObject jSONObject) {
                Intrinsics.checkNotNullParameter(jSONObject, "dialogConfigJSON");
                String optString = jSONObject.optString("name");
                Uri uri = null;
                if (Utility.isNullOrEmpty(optString)) {
                    return null;
                }
                Intrinsics.checkNotNullExpressionValue(optString, "dialogNameWithFeature");
                List split$default = StringsKt.split$default((CharSequence) optString, new String[]{"|"}, false, 0, 6, (Object) null);
                if (split$default.size() != 2) {
                    return null;
                }
                String str = (String) CollectionsKt.first(split$default);
                String str2 = (String) CollectionsKt.last(split$default);
                if (Utility.isNullOrEmpty(str) || Utility.isNullOrEmpty(str2)) {
                    return null;
                }
                String optString2 = jSONObject.optString("url");
                if (!Utility.isNullOrEmpty(optString2)) {
                    uri = Uri.parse(optString2);
                }
                return new DialogFeatureConfig(str, str2, uri, parseVersionSpec(jSONObject.optJSONArray("versions")), (DefaultConstructorMarker) null);
            }

            private final int[] parseVersionSpec(JSONArray jSONArray) {
                if (jSONArray == null) {
                    return null;
                }
                int length = jSONArray.length();
                int[] iArr = new int[length];
                for (int i = 0; i < length; i++) {
                    int i2 = -1;
                    int optInt = jSONArray.optInt(i, -1);
                    if (optInt == -1) {
                        String optString = jSONArray.optString(i);
                        if (!Utility.isNullOrEmpty(optString)) {
                            try {
                                Intrinsics.checkNotNullExpressionValue(optString, "versionString");
                                i2 = Integer.parseInt(optString);
                            } catch (NumberFormatException e) {
                                Utility.logd("FacebookSDK", (Exception) e);
                            }
                            optInt = i2;
                        }
                    }
                    iArr[i] = optInt;
                }
                return iArr;
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
