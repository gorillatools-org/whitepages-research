package io.branch.referral;

import android.net.Uri;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class ReferringUrlUtility {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    private PrefHelper prefHelper;
    private final Map urlQueryParameters;

    public ReferringUrlUtility(PrefHelper prefHelper2) {
        Intrinsics.checkNotNullParameter(prefHelper2, "prefHelper");
        this.prefHelper = prefHelper2;
        JSONObject referringURLQueryParameters = prefHelper2.getReferringURLQueryParameters();
        Intrinsics.checkNotNullExpressionValue(referringURLQueryParameters, "prefHelper.referringURLQueryParameters");
        this.urlQueryParameters = deserializeFromJson$Branch_SDK_release(referringURLQueryParameters);
        checkForAndMigrateOldGclid();
    }

    public final void parseReferringURL(String str) {
        Intrinsics.checkNotNullParameter(str, "urlString");
        if (!Branch.getInstance().isTrackingDisabled()) {
            Uri parse = Uri.parse(str);
            if (parse.isHierarchical()) {
                for (String next : parse.getQueryParameterNames()) {
                    Intrinsics.checkNotNullExpressionValue(next, "originalParamName");
                    String lowerCase = next.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    String queryParameter = parse.getQueryParameter(next);
                    BranchLogger.v("Found URL Query Parameter - Key: " + lowerCase + ", Value: " + queryParameter);
                    if (isSupportedQueryParameter(lowerCase)) {
                        BranchUrlQueryParameter findUrlQueryParam = findUrlQueryParam(lowerCase);
                        findUrlQueryParam.setValue(queryParameter);
                        findUrlQueryParam.setTimestamp(new Date());
                        findUrlQueryParam.setDeepLink(true);
                        if (findUrlQueryParam.getValidityWindow() == 0) {
                            findUrlQueryParam.setValidityWindow(defaultValidityWindowForParam(lowerCase));
                        }
                        this.urlQueryParameters.put(lowerCase, findUrlQueryParam);
                    }
                }
                this.prefHelper.setReferringUrlQueryParameters(serializeToJson$Branch_SDK_release(this.urlQueryParameters));
                BranchLogger.v("Current referringURLQueryParameters: " + this.prefHelper.getReferringURLQueryParameters());
                return;
            }
            BranchLogger.d("Skipping referring URL query parameter parsing because the URI is not hierarchical. URI: " + str);
            return;
        }
        BranchLogger.d("Skipping referring URL query parameter parsing due to disabled tracking.");
    }

    public final JSONObject getURLQueryParamsForRequest(ServerRequest serverRequest) {
        Intrinsics.checkNotNullParameter(serverRequest, "request");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONObject addGclidValueFor = addGclidValueFor(serverRequest);
        if (addGclidValueFor.length() > 0) {
            Iterator<String> keys = addGclidValueFor.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Intrinsics.checkNotNullExpressionValue(next, "key");
                Object obj = addGclidValueFor.get(next);
                Intrinsics.checkNotNullExpressionValue(obj, "gclid.get(key)");
                linkedHashMap.put(next, obj);
            }
        }
        return new JSONObject(linkedHashMap);
    }

    private final JSONObject addGclidValueFor(ServerRequest serverRequest) {
        JSONObject jSONObject = new JSONObject();
        if ((serverRequest instanceof ServerRequestLogEvent) || (serverRequest instanceof ServerRequestRegisterOpen)) {
            Map map = this.urlQueryParameters;
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Gclid;
            BranchUrlQueryParameter branchUrlQueryParameter = (BranchUrlQueryParameter) map.get(defines$Jsonkey.getKey());
            if (!(branchUrlQueryParameter == null || branchUrlQueryParameter.getValue() == null || Intrinsics.areEqual((Object) branchUrlQueryParameter.getValue(), (Object) "bnc_no_value"))) {
                long time = new Date().getTime();
                Date timestamp = branchUrlQueryParameter.getTimestamp();
                Long valueOf = timestamp != null ? Long.valueOf(timestamp.getTime()) : null;
                long validityWindow = branchUrlQueryParameter.getValidityWindow() * 1000;
                if (valueOf != null) {
                    if (branchUrlQueryParameter.getValidityWindow() == 0 || time < valueOf.longValue() + validityWindow) {
                        jSONObject.put(defines$Jsonkey.getKey(), branchUrlQueryParameter.getValue());
                        if (serverRequest instanceof ServerRequestRegisterOpen) {
                            jSONObject.put(Defines$Jsonkey.IsDeeplinkGclid.getKey(), branchUrlQueryParameter.isDeepLink());
                        }
                        branchUrlQueryParameter.setDeepLink(false);
                        this.prefHelper.setReferringUrlQueryParameters(serializeToJson$Branch_SDK_release(this.urlQueryParameters));
                    } else {
                        this.urlQueryParameters.remove(defines$Jsonkey.getKey());
                        this.prefHelper.setReferringUrlQueryParameters(serializeToJson$Branch_SDK_release(this.urlQueryParameters));
                    }
                }
            }
        }
        return jSONObject;
    }

    private final boolean isSupportedQueryParameter(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return CollectionsKt.listOf(Defines$Jsonkey.Gclid.getKey()).contains(lowerCase);
    }

    private final BranchUrlQueryParameter findUrlQueryParam(String str) {
        BranchUrlQueryParameter branchUrlQueryParameter = (BranchUrlQueryParameter) this.urlQueryParameters.get(str);
        return branchUrlQueryParameter == null ? new BranchUrlQueryParameter(str, (String) null, (Date) null, false, 0, 30, (DefaultConstructorMarker) null) : branchUrlQueryParameter;
    }

    private final long defaultValidityWindowForParam(String str) {
        if (Intrinsics.areEqual((Object) str, (Object) Defines$Jsonkey.Gclid.getKey())) {
            return this.prefHelper.getReferrerGclidValidForWindow() / 1000;
        }
        return 0;
    }

    public final JSONObject serializeToJson$Branch_SDK_release(Map map) {
        Intrinsics.checkNotNullParameter(map, "urlQueryParameters");
        JSONObject jSONObject = new JSONObject();
        try {
            for (BranchUrlQueryParameter branchUrlQueryParameter : map.values()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("name", branchUrlQueryParameter.getName());
                Object value = branchUrlQueryParameter.getValue();
                if (value == null) {
                    value = JSONObject.NULL;
                }
                jSONObject2.put("value", value);
                Date timestamp = branchUrlQueryParameter.getTimestamp();
                jSONObject2.put("timestamp", timestamp != null ? this.dateFormat.format(timestamp) : null);
                jSONObject2.put("isDeeplink", branchUrlQueryParameter.isDeepLink());
                jSONObject2.put("validityWindow", branchUrlQueryParameter.getValidityWindow());
                jSONObject.put(String.valueOf(branchUrlQueryParameter.getName()), jSONObject2);
            }
        } catch (JSONException e) {
            BranchLogger.e("Caught JSONException when serializing JSON for referring URL query parameters " + e.getMessage());
        }
        return jSONObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0097 A[Catch:{ JSONException -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a4 A[Catch:{ JSONException -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ac A[Catch:{ JSONException -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b6 A[Catch:{ JSONException -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0018 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map deserializeFromJson$Branch_SDK_release(org.json.JSONObject r22) {
        /*
            r21 = this;
            r1 = r22
            java.lang.String r2 = "isDeeplink"
            java.lang.String r3 = "validityWindow"
            java.lang.String r4 = "timestamp"
            java.lang.String r5 = "value"
            java.lang.String r0 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
            java.util.Iterator r7 = r22.keys()     // Catch:{ JSONException -> 0x0055 }
        L_0x0018:
            boolean r0 = r7.hasNext()     // Catch:{ JSONException -> 0x0055 }
            if (r0 == 0) goto L_0x00bb
            java.lang.Object r0 = r7.next()     // Catch:{ JSONException -> 0x0055 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x0055 }
            org.json.JSONObject r8 = r1.getJSONObject(r0)     // Catch:{ JSONException -> 0x0055 }
            io.branch.referral.BranchUrlQueryParameter r14 = new io.branch.referral.BranchUrlQueryParameter     // Catch:{ JSONException -> 0x0055 }
            r16 = 31
            r17 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r18 = 0
            r9 = r14
            r20 = r14
            r14 = r18
            r9.<init>(r10, r11, r12, r13, r14, r16, r17)     // Catch:{ JSONException -> 0x0055 }
            java.lang.String r0 = "name"
            java.lang.String r0 = r8.getString(r0)     // Catch:{ JSONException -> 0x0055 }
            r9 = r20
            r9.setName(r0)     // Catch:{ JSONException -> 0x0055 }
            boolean r0 = r8.has(r5)     // Catch:{ JSONException -> 0x0055 }
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r8.getString(r5)     // Catch:{ JSONException -> 0x0055 }
            r9.setValue(r0)     // Catch:{ JSONException -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r0 = move-exception
            r10 = r21
            goto L_0x00be
        L_0x0059:
            boolean r0 = r8.has(r4)     // Catch:{ JSONException -> 0x0055 }
            if (r0 == 0) goto L_0x008f
            java.lang.String r0 = r8.getString(r4)     // Catch:{ ParseException -> 0x0073 }
            r10 = r21
            java.text.SimpleDateFormat r11 = r10.dateFormat     // Catch:{ ParseException -> 0x0071 }
            java.util.Date r0 = r11.parse(r0)     // Catch:{ ParseException -> 0x0071 }
            r9.setTimestamp(r0)     // Catch:{ ParseException -> 0x0071 }
            goto L_0x0091
        L_0x006f:
            r0 = move-exception
            goto L_0x00be
        L_0x0071:
            r0 = move-exception
            goto L_0x0076
        L_0x0073:
            r0 = move-exception
            r10 = r21
        L_0x0076:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x006f }
            r11.<init>()     // Catch:{ JSONException -> 0x006f }
            java.lang.String r12 = "Caught JSONException when parsing referring URL query parameter timestamp "
            r11.append(r12)     // Catch:{ JSONException -> 0x006f }
            java.lang.String r0 = r0.getMessage()     // Catch:{ JSONException -> 0x006f }
            r11.append(r0)     // Catch:{ JSONException -> 0x006f }
            java.lang.String r0 = r11.toString()     // Catch:{ JSONException -> 0x006f }
            io.branch.referral.BranchLogger.e(r0)     // Catch:{ JSONException -> 0x006f }
            goto L_0x0091
        L_0x008f:
            r10 = r21
        L_0x0091:
            boolean r0 = r8.has(r3)     // Catch:{ JSONException -> 0x006f }
            if (r0 == 0) goto L_0x009e
            long r11 = r8.getLong(r3)     // Catch:{ JSONException -> 0x006f }
            r9.setValidityWindow(r11)     // Catch:{ JSONException -> 0x006f }
        L_0x009e:
            boolean r0 = r8.has(r2)     // Catch:{ JSONException -> 0x006f }
            if (r0 == 0) goto L_0x00ac
            boolean r0 = r8.getBoolean(r2)     // Catch:{ JSONException -> 0x006f }
            r9.setDeepLink(r0)     // Catch:{ JSONException -> 0x006f }
            goto L_0x00b0
        L_0x00ac:
            r0 = 0
            r9.setDeepLink(r0)     // Catch:{ JSONException -> 0x006f }
        L_0x00b0:
            java.lang.String r0 = r9.getName()     // Catch:{ JSONException -> 0x006f }
            if (r0 == 0) goto L_0x0018
            r6.put(r0, r9)     // Catch:{ JSONException -> 0x006f }
            goto L_0x0018
        L_0x00bb:
            r10 = r21
            goto L_0x00d6
        L_0x00be:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Caught JSONException when deserializing JSON for referring URL query parameters "
            r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            io.branch.referral.BranchLogger.e(r0)
        L_0x00d6:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ReferringUrlUtility.deserializeFromJson$Branch_SDK_release(org.json.JSONObject):java.util.Map");
    }

    private final void checkForAndMigrateOldGclid() {
        String referrerGclid;
        Map map = this.urlQueryParameters;
        Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Gclid;
        BranchUrlQueryParameter branchUrlQueryParameter = (BranchUrlQueryParameter) map.get(defines$Jsonkey.getKey());
        if ((branchUrlQueryParameter != null ? branchUrlQueryParameter.getValue() : null) == null && (referrerGclid = this.prefHelper.getReferrerGclid()) != null && !Intrinsics.areEqual((Object) referrerGclid, (Object) "bnc_no_value")) {
            BranchUrlQueryParameter branchUrlQueryParameter2 = new BranchUrlQueryParameter(defines$Jsonkey.getKey(), referrerGclid, new Date(), false, this.prefHelper.getReferrerGclidValidForWindow());
            Map map2 = this.urlQueryParameters;
            String key = defines$Jsonkey.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "Gclid.key");
            map2.put(key, branchUrlQueryParameter2);
            this.prefHelper.setReferringUrlQueryParameters(serializeToJson$Branch_SDK_release(this.urlQueryParameters));
            this.prefHelper.clearGclid();
            BranchLogger.v("Updated old Gclid (" + referrerGclid + ") to new BranchUrlQueryParameter (" + branchUrlQueryParameter2 + ')');
        }
    }
}
