package com.facebook.internal.instrument.errorreport;

import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.InstrumentUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ErrorReportHandler {
    public static final ErrorReportHandler INSTANCE = new ErrorReportHandler();

    private ErrorReportHandler() {
    }

    public static final void save(String str) {
        try {
            new ErrorReportData(str).save();
        } catch (Exception unused) {
        }
    }

    public static final void enable() {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            sendErrorReports();
        }
    }

    public static final void sendErrorReports() {
        if (!Utility.isDataProcessingRestricted()) {
            File[] listErrorReportFiles = listErrorReportFiles();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (File errorReportData : listErrorReportFiles) {
                ErrorReportData errorReportData2 = new ErrorReportData(errorReportData);
                if (errorReportData2.isValid()) {
                    arrayList.add(errorReportData2);
                }
            }
            CollectionsKt.sortWith(arrayList, new ErrorReportHandler$$ExternalSyntheticLambda0());
            JSONArray jSONArray = new JSONArray();
            while (i < arrayList.size() && i < 1000) {
                jSONArray.put(arrayList.get(i));
                i++;
            }
            InstrumentUtility.sendReports("error_reports", jSONArray, new ErrorReportHandler$$ExternalSyntheticLambda1(arrayList));
        }
    }

    /* access modifiers changed from: private */
    public static final int sendErrorReports$lambda$0(ErrorReportData errorReportData, ErrorReportData errorReportData2) {
        Intrinsics.checkNotNullExpressionValue(errorReportData2, "o2");
        return errorReportData.compareTo(errorReportData2);
    }

    /* access modifiers changed from: private */
    public static final void sendErrorReports$lambda$2(ArrayList arrayList, GraphResponse graphResponse) {
        JSONObject jsonObject;
        Intrinsics.checkNotNullParameter(arrayList, "$validReports");
        Intrinsics.checkNotNullParameter(graphResponse, "response");
        try {
            if (graphResponse.getError() == null && (jsonObject = graphResponse.getJsonObject()) != null && jsonObject.getBoolean(FirebaseAnalytics.Param.SUCCESS)) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((ErrorReportData) it.next()).clear();
                }
            }
        } catch (JSONException unused) {
        }
    }

    public static final File[] listErrorReportFiles() {
        File instrumentReportDir = InstrumentUtility.getInstrumentReportDir();
        if (instrumentReportDir == null) {
            return new File[0];
        }
        File[] listFiles = instrumentReportDir.listFiles(new ErrorReportHandler$$ExternalSyntheticLambda2());
        Intrinsics.checkNotNullExpressionValue(listFiles, "reportDir.listFiles { di…OR_REPORT_PREFIX)))\n    }");
        return listFiles;
    }

    /* access modifiers changed from: private */
    public static final boolean listErrorReportFiles$lambda$3(File file, String str) {
        Intrinsics.checkNotNullExpressionValue(str, "name");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("^%s[0-9]+.json$", Arrays.copyOf(new Object[]{"error_log_"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return new Regex(format).matches(str);
    }
}
