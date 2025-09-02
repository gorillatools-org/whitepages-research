package com.facebook.internal.instrument;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.InstrumentData;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ExceptionAnalyzer {
    public static final ExceptionAnalyzer INSTANCE = new ExceptionAnalyzer();
    private static boolean enabled;

    public static final boolean isDebug$facebook_core_release() {
        return false;
    }

    private ExceptionAnalyzer() {
    }

    public static final void enable() {
        enabled = true;
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            INSTANCE.sendExceptionAnalysisReports$facebook_core_release();
        }
    }

    public static final void execute(Throwable th) {
        if (enabled && !isDebug$facebook_core_release() && th != null) {
            HashSet hashSet = new HashSet();
            StackTraceElement[] stackTrace = th.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "e.stackTrace");
            for (StackTraceElement className : stackTrace) {
                String className2 = className.getClassName();
                Intrinsics.checkNotNullExpressionValue(className2, "it.className");
                FeatureManager.Feature feature = FeatureManager.getFeature(className2);
                if (feature != FeatureManager.Feature.Unknown) {
                    FeatureManager.disableFeature(feature);
                    hashSet.add(feature.toString());
                }
            }
            if (FacebookSdk.getAutoLogAppEventsEnabled() && !hashSet.isEmpty()) {
                InstrumentData.Builder.build(new JSONArray(hashSet)).save();
            }
        }
    }

    public final void sendExceptionAnalysisReports$facebook_core_release() {
        if (!Utility.isDataProcessingRestricted()) {
            File[] listExceptionAnalysisReportFiles = InstrumentUtility.listExceptionAnalysisReportFiles();
            ArrayList arrayList = new ArrayList();
            for (File load : listExceptionAnalysisReportFiles) {
                InstrumentData load2 = InstrumentData.Builder.load(load);
                if (load2.isValid()) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("crash_shield", load2.toString());
                        GraphRequest.Companion companion = GraphRequest.Companion;
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("%s/instruments", Arrays.copyOf(new Object[]{FacebookSdk.getApplicationId()}, 1));
                        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                        arrayList.add(companion.newPostRequest((AccessToken) null, format, jSONObject, new ExceptionAnalyzer$$ExternalSyntheticLambda0(load2)));
                    } catch (JSONException unused) {
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                new GraphRequestBatch((Collection) arrayList).executeAsync();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void sendExceptionAnalysisReports$lambda$1(InstrumentData instrumentData, GraphResponse graphResponse) {
        JSONObject jsonObject;
        Intrinsics.checkNotNullParameter(instrumentData, "$instrumentData");
        Intrinsics.checkNotNullParameter(graphResponse, "response");
        try {
            if (graphResponse.getError() == null && (jsonObject = graphResponse.getJsonObject()) != null && jsonObject.getBoolean(FirebaseAnalytics.Param.SUCCESS)) {
                instrumentData.clear();
            }
        } catch (JSONException unused) {
        }
    }
}
