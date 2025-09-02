package com.facebook.internal.instrument.anrreport;

import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.InstrumentData;
import com.facebook.internal.instrument.InstrumentUtility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ANRHandler {
    public static final ANRHandler INSTANCE = new ANRHandler();
    private static final AtomicBoolean enabled = new AtomicBoolean(false);

    private ANRHandler() {
    }

    public static final synchronized void enable() {
        synchronized (ANRHandler.class) {
            if (!CrashShieldHandler.isObjectCrashing(ANRHandler.class)) {
                try {
                    if (!enabled.getAndSet(true)) {
                        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                            sendANRReports();
                        }
                        ANRDetector.start();
                    }
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, ANRHandler.class);
                }
            }
        }
    }

    public static final void sendANRReports() {
        Class<ANRHandler> cls = ANRHandler.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (!Utility.isDataProcessingRestricted()) {
                    File[] listAnrReportFiles = InstrumentUtility.listAnrReportFiles();
                    ArrayList arrayList = new ArrayList(listAnrReportFiles.length);
                    for (File load : listAnrReportFiles) {
                        arrayList.add(InstrumentData.Builder.load(load));
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Object next : arrayList) {
                        if (((InstrumentData) next).isValid()) {
                            arrayList2.add(next);
                        }
                    }
                    List sortedWith = CollectionsKt.sortedWith(arrayList2, new ANRHandler$$ExternalSyntheticLambda0());
                    JSONArray jSONArray = new JSONArray();
                    Iterator it = RangesKt.until(0, Math.min(sortedWith.size(), 5)).iterator();
                    while (it.hasNext()) {
                        jSONArray.put(sortedWith.get(((IntIterator) it).nextInt()));
                    }
                    InstrumentUtility.sendReports("anr_reports", jSONArray, new ANRHandler$$ExternalSyntheticLambda1(sortedWith));
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final int sendANRReports$lambda$2(InstrumentData instrumentData, InstrumentData instrumentData2) {
        Class<ANRHandler> cls = ANRHandler.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return 0;
        }
        try {
            Intrinsics.checkNotNullExpressionValue(instrumentData2, "o2");
            return instrumentData.compareTo(instrumentData2);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static final void sendANRReports$lambda$5(List list, GraphResponse graphResponse) {
        JSONObject jsonObject;
        Class<ANRHandler> cls = ANRHandler.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
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
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
