package com.facebook.internal.instrument.crashreport;

import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.ExceptionAnalyzer;
import com.facebook.internal.instrument.InstrumentData;
import com.facebook.internal.instrument.InstrumentUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = CrashHandler.class.getCanonicalName();
    /* access modifiers changed from: private */
    public static CrashHandler instance;
    private final Thread.UncaughtExceptionHandler previousHandler;

    public /* synthetic */ CrashHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, DefaultConstructorMarker defaultConstructorMarker) {
        this(uncaughtExceptionHandler);
    }

    private CrashHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.previousHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Intrinsics.checkNotNullParameter(thread, "t");
        Intrinsics.checkNotNullParameter(th, "e");
        if (InstrumentUtility.isSDKRelatedException(th)) {
            ExceptionAnalyzer.execute(th);
            InstrumentData.Builder.build(th, InstrumentData.Type.CrashReport).save();
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.previousHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized void enable() {
            try {
                if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                    sendExceptionReports();
                }
                if (CrashHandler.instance != null) {
                    Log.w(CrashHandler.TAG, "Already enabled!");
                    return;
                }
                CrashHandler.instance = new CrashHandler(Thread.getDefaultUncaughtExceptionHandler(), (DefaultConstructorMarker) null);
                Thread.setDefaultUncaughtExceptionHandler(CrashHandler.instance);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }

        private final void sendExceptionReports() {
            if (!Utility.isDataProcessingRestricted()) {
                File[] listExceptionReportFiles = InstrumentUtility.listExceptionReportFiles();
                ArrayList arrayList = new ArrayList(listExceptionReportFiles.length);
                for (File load : listExceptionReportFiles) {
                    arrayList.add(InstrumentData.Builder.load(load));
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object next : arrayList) {
                    if (((InstrumentData) next).isValid()) {
                        arrayList2.add(next);
                    }
                }
                List sortedWith = CollectionsKt.sortedWith(arrayList2, new CrashHandler$Companion$$ExternalSyntheticLambda0());
                JSONArray jSONArray = new JSONArray();
                Iterator it = RangesKt.until(0, Math.min(sortedWith.size(), 5)).iterator();
                while (it.hasNext()) {
                    jSONArray.put(sortedWith.get(((IntIterator) it).nextInt()));
                }
                InstrumentUtility.sendReports("crash_reports", jSONArray, new CrashHandler$Companion$$ExternalSyntheticLambda1(sortedWith));
            }
        }

        /* access modifiers changed from: private */
        public static final int sendExceptionReports$lambda$2(InstrumentData instrumentData, InstrumentData instrumentData2) {
            Intrinsics.checkNotNullExpressionValue(instrumentData2, "o2");
            return instrumentData.compareTo(instrumentData2);
        }

        /* access modifiers changed from: private */
        public static final void sendExceptionReports$lambda$5(List list, GraphResponse graphResponse) {
            JSONObject jsonObject;
            Intrinsics.checkNotNullParameter(list, "$validReports");
            Intrinsics.checkNotNullParameter(graphResponse, "response");
            try {
                if (graphResponse.getError() == null && (jsonObject = graphResponse.getJsonObject()) != null && jsonObject.getBoolean(FirebaseAnalytics.Param.SUCCESS)) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((InstrumentData) it.next()).clear();
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }
}
