package com.facebook.appevents;

import android.os.Bundle;
import com.facebook.FacebookException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONObject;

public final class OperationalData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Set iapOperationalAndCustomParameters;
    private static final Set iapOperationalParameters;
    /* access modifiers changed from: private */
    public static final Map parameterClassifications;
    private final Map operationalData = new LinkedHashMap();

    public final OperationalData copy() {
        OperationalData operationalData2 = new OperationalData();
        for (OperationalDataEnum operationalDataEnum : this.operationalData.keySet()) {
            Map map = (Map) this.operationalData.get(operationalDataEnum);
            if (map != null) {
                for (String str : map.keySet()) {
                    Object obj = map.get(str);
                    if (obj != null) {
                        operationalData2.addParameter(operationalDataEnum, str, obj);
                    }
                }
            }
        }
        return operationalData2;
    }

    public final void addParameter(OperationalDataEnum operationalDataEnum, String str, Object obj) {
        Intrinsics.checkNotNullParameter(operationalDataEnum, "type");
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(obj, "value");
        try {
            AppEvent.Companion.validateIdentifier(str);
            if (!(obj instanceof String)) {
                if (!(obj instanceof Number)) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", Arrays.copyOf(new Object[]{obj, str}, 2));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    throw new FacebookException(format);
                }
            }
            if (!this.operationalData.containsKey(operationalDataEnum)) {
                this.operationalData.put(operationalDataEnum, new LinkedHashMap());
            }
            Map map = (Map) this.operationalData.get(operationalDataEnum);
            if (map != null) {
                map.put(str, obj);
            }
        } catch (Exception unused) {
        }
    }

    public final Object getParameter(OperationalDataEnum operationalDataEnum, String str) {
        Map map;
        Intrinsics.checkNotNullParameter(operationalDataEnum, "type");
        Intrinsics.checkNotNullParameter(str, "key");
        if (this.operationalData.containsKey(operationalDataEnum) && (map = (Map) this.operationalData.get(operationalDataEnum)) != null) {
            return map.get(str);
        }
        return null;
    }

    public final JSONObject toJSON() {
        JSONObject jSONObject;
        try {
            Map map = this.operationalData;
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            for (Object next : map.entrySet()) {
                linkedHashMap.put(((OperationalDataEnum) ((Map.Entry) next).getKey()).getValue(), ((Map.Entry) next).getValue());
            }
            jSONObject = new JSONObject(MapsKt.toMap((Map) linkedHashMap));
        } catch (Exception unused) {
            jSONObject = null;
        }
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    public static final class Companion {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            static {
                /*
                    com.facebook.appevents.ParameterClassification[] r0 = com.facebook.appevents.ParameterClassification.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.appevents.ParameterClassification r1 = com.facebook.appevents.ParameterClassification.CustomData     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.appevents.ParameterClassification r1 = com.facebook.appevents.ParameterClassification.OperationalData     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.facebook.appevents.ParameterClassification r1 = com.facebook.appevents.ParameterClassification.CustomAndOperationalData     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.OperationalData.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.util.Set} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.facebook.appevents.ParameterClassification getParameterClassification(com.facebook.appevents.OperationalDataEnum r4, java.lang.String r5) {
            /*
                r3 = this;
                java.lang.String r0 = "typeOfParameter"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "parameter"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                java.util.Map r0 = com.facebook.appevents.OperationalData.parameterClassifications
                java.lang.Object r0 = r0.get(r4)
                kotlin.Pair r0 = (kotlin.Pair) r0
                r1 = 0
                if (r0 == 0) goto L_0x001e
                java.lang.Object r0 = r0.getFirst()
                java.util.Set r0 = (java.util.Set) r0
                goto L_0x001f
            L_0x001e:
                r0 = r1
            L_0x001f:
                java.util.Map r2 = com.facebook.appevents.OperationalData.parameterClassifications
                java.lang.Object r4 = r2.get(r4)
                kotlin.Pair r4 = (kotlin.Pair) r4
                if (r4 == 0) goto L_0x0032
                java.lang.Object r4 = r4.getSecond()
                r1 = r4
                java.util.Set r1 = (java.util.Set) r1
            L_0x0032:
                if (r0 == 0) goto L_0x003d
                boolean r4 = r0.contains(r5)
                if (r4 == 0) goto L_0x003d
                com.facebook.appevents.ParameterClassification r4 = com.facebook.appevents.ParameterClassification.OperationalData
                return r4
            L_0x003d:
                if (r1 == 0) goto L_0x0048
                boolean r4 = r1.contains(r5)
                if (r4 == 0) goto L_0x0048
                com.facebook.appevents.ParameterClassification r4 = com.facebook.appevents.ParameterClassification.CustomAndOperationalData
                return r4
            L_0x0048:
                com.facebook.appevents.ParameterClassification r4 = com.facebook.appevents.ParameterClassification.CustomData
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.OperationalData.Companion.getParameterClassification(com.facebook.appevents.OperationalDataEnum, java.lang.String):com.facebook.appevents.ParameterClassification");
        }

        public final void addParameter(OperationalDataEnum operationalDataEnum, String str, String str2, Bundle bundle, OperationalData operationalData) {
            Intrinsics.checkNotNullParameter(operationalDataEnum, "typeOfParameter");
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "value");
            Intrinsics.checkNotNullParameter(bundle, "customEventsParams");
            Intrinsics.checkNotNullParameter(operationalData, "operationalData");
            int i = WhenMappings.$EnumSwitchMapping$0[getParameterClassification(operationalDataEnum, str).ordinal()];
            if (i == 1) {
                bundle.putCharSequence(str, str2);
            } else if (i == 2) {
                operationalData.addParameter(operationalDataEnum, str, str2);
            } else if (i == 3) {
                operationalData.addParameter(operationalDataEnum, str, str2);
                bundle.putCharSequence(str, str2);
            }
        }

        public final Pair addParameterAndReturn(OperationalDataEnum operationalDataEnum, String str, String str2, Bundle bundle, OperationalData operationalData) {
            Intrinsics.checkNotNullParameter(operationalDataEnum, "typeOfParameter");
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "value");
            int i = WhenMappings.$EnumSwitchMapping$0[getParameterClassification(operationalDataEnum, str).ordinal()];
            if (i == 1) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putCharSequence(str, str2);
            } else if (i == 2) {
                if (operationalData == null) {
                    operationalData = new OperationalData();
                }
                operationalData.addParameter(operationalDataEnum, str, str2);
            } else if (i == 3) {
                if (operationalData == null) {
                    operationalData = new OperationalData();
                }
                if (bundle == null) {
                    bundle = new Bundle();
                }
                operationalData.addParameter(operationalDataEnum, str, str2);
                bundle.putCharSequence(str, str2);
            }
            return new Pair(bundle, operationalData);
        }

        public final Object getParameter(OperationalDataEnum operationalDataEnum, String str, Bundle bundle, OperationalData operationalData) {
            Intrinsics.checkNotNullParameter(operationalDataEnum, "typeOfParameter");
            Intrinsics.checkNotNullParameter(str, "key");
            CharSequence charSequence = null;
            Object parameter = operationalData != null ? operationalData.getParameter(operationalDataEnum, str) : null;
            if (bundle != null) {
                charSequence = bundle.getCharSequence(str);
            }
            return parameter == null ? charSequence : parameter;
        }
    }

    static {
        Set of = SetsKt.setOf("fb_iap_package_name", "fb_iap_subs_auto_renewing", "fb_free_trial_period", "fb_intro_price_amount_micros", "fb_intro_price_cycles", "fb_iap_base_plan", "is_implicit_purchase_logging_enabled", "fb_iap_sdk_supported_library_versions", "is_autolog_app_events_enabled", "fb_iap_client_library_version", "fb_iap_subs_period", "fb_iap_purchase_token", "fb_iap_non_deduped_event_time", "fb_iap_actual_dedup_result", "fb_iap_actual_dedup_key_used", "fb_iap_test_dedup_result", "fb_iap_test_dedup_key_used");
        iapOperationalParameters = of;
        Set of2 = SetsKt.setOf("fb_iap_product_id", "fb_iap_product_type", "fb_iap_purchase_time");
        iapOperationalAndCustomParameters = of2;
        parameterClassifications = MapsKt.mapOf(TuplesKt.to(OperationalDataEnum.IAPParameters, new Pair(of, of2)));
    }
}
