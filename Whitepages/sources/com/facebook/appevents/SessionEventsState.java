package com.facebook.appevents;

import android.content.Context;
import com.facebook.GraphRequest;
import com.facebook.appevents.eventdeactivation.EventDeactivationManager;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

public final class SessionEventsState {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_ACCUMULATED_LOG_EVENTS = 1000;
    private static final String TAG = SessionEventsState.class.getSimpleName();
    private List accumulatedEvents = new ArrayList();
    private final String anonymousAppDeviceGUID;
    private final AttributionIdentifiers attributionIdentifiers;
    private final List inFlightEvents = new ArrayList();
    private int numSkippedEventsDueToFullBuffer;

    public SessionEventsState(AttributionIdentifiers attributionIdentifiers2, String str) {
        Intrinsics.checkNotNullParameter(attributionIdentifiers2, "attributionIdentifiers");
        Intrinsics.checkNotNullParameter(str, "anonymousAppDeviceGUID");
        this.attributionIdentifiers = attributionIdentifiers2;
        this.anonymousAppDeviceGUID = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void addEvent(com.facebook.appevents.AppEvent r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r2)     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r2)
            return
        L_0x0009:
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)     // Catch:{ all -> 0x0026 }
            java.util.List r0 = r2.accumulatedEvents     // Catch:{ all -> 0x0026 }
            int r0 = r0.size()     // Catch:{ all -> 0x0026 }
            java.util.List r1 = r2.inFlightEvents     // Catch:{ all -> 0x0026 }
            int r1 = r1.size()     // Catch:{ all -> 0x0026 }
            int r0 = r0 + r1
            int r1 = MAX_ACCUMULATED_LOG_EVENTS     // Catch:{ all -> 0x0026 }
            if (r0 < r1) goto L_0x0028
            int r3 = r2.numSkippedEventsDueToFullBuffer     // Catch:{ all -> 0x0026 }
            int r3 = r3 + 1
            r2.numSkippedEventsDueToFullBuffer = r3     // Catch:{ all -> 0x0026 }
            goto L_0x002d
        L_0x0026:
            r3 = move-exception
            goto L_0x002f
        L_0x0028:
            java.util.List r0 = r2.accumulatedEvents     // Catch:{ all -> 0x0026 }
            r0.add(r3)     // Catch:{ all -> 0x0026 }
        L_0x002d:
            monitor-exit(r2)
            return
        L_0x002f:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r3, r2)     // Catch:{ all -> 0x0034 }
            monitor-exit(r2)
            return
        L_0x0034:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0034 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.SessionEventsState.addEvent(com.facebook.appevents.AppEvent):void");
    }

    public final synchronized int getAccumulatedEventCount() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return 0;
        }
        try {
            return this.accumulatedEvents.size();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return 0;
        }
    }

    public final synchronized void clearInFlightAndStats(boolean z) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            if (z) {
                try {
                    this.accumulatedEvents.addAll(this.inFlightEvents);
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                    return;
                }
            }
            this.inFlightEvents.clear();
            this.numSkippedEventsDueToFullBuffer = 0;
        }
    }

    public final int populateRequest(GraphRequest graphRequest, Context context, boolean z, boolean z2) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return 0;
        }
        try {
            Intrinsics.checkNotNullParameter(graphRequest, "request");
            Intrinsics.checkNotNullParameter(context, "applicationContext");
            synchronized (this) {
                int i = this.numSkippedEventsDueToFullBuffer;
                EventDeactivationManager.processEvents(this.accumulatedEvents);
                this.inFlightEvents.addAll(this.accumulatedEvents);
                this.accumulatedEvents.clear();
                JSONArray jSONArray = new JSONArray();
                JSONArray jSONArray2 = new JSONArray();
                for (AppEvent appEvent : this.inFlightEvents) {
                    if (!z) {
                        if (!appEvent.isImplicit()) {
                        }
                    }
                    jSONArray.put(appEvent.getJsonObject());
                    jSONArray2.put(appEvent.getOperationalJsonObject());
                }
                if (jSONArray.length() == 0) {
                    return 0;
                }
                Unit unit = Unit.INSTANCE;
                populateRequest(graphRequest, context, i, jSONArray, jSONArray2, z2);
                return jSONArray.length();
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return 0;
        }
    }

    public final synchronized List getEventsToPersist() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            List list = this.accumulatedEvents;
            this.accumulatedEvents = new ArrayList();
            return list;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r5 = new org.json.JSONObject();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void populateRequest(com.facebook.GraphRequest r4, android.content.Context r5, int r6, org.json.JSONArray r7, org.json.JSONArray r8, boolean r9) {
        /*
            r3 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r3)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            com.facebook.appevents.internal.AppEventsLoggerUtility$GraphAPIActivityType r0 = com.facebook.appevents.internal.AppEventsLoggerUtility.GraphAPIActivityType.CUSTOM_APP_EVENTS     // Catch:{ JSONException -> 0x001d }
            com.facebook.internal.AttributionIdentifiers r1 = r3.attributionIdentifiers     // Catch:{ JSONException -> 0x001d }
            java.lang.String r2 = r3.anonymousAppDeviceGUID     // Catch:{ JSONException -> 0x001d }
            org.json.JSONObject r5 = com.facebook.appevents.internal.AppEventsLoggerUtility.getJSONObjectForGraphAPICall(r0, r1, r2, r9, r5)     // Catch:{ JSONException -> 0x001d }
            int r9 = r3.numSkippedEventsDueToFullBuffer     // Catch:{ JSONException -> 0x001d }
            if (r9 <= 0) goto L_0x0022
            java.lang.String r9 = "num_skipped_events"
            r5.put(r9, r6)     // Catch:{ JSONException -> 0x001d }
            goto L_0x0022
        L_0x001b:
            r4 = move-exception
            goto L_0x004f
        L_0x001d:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ all -> 0x001b }
            r5.<init>()     // Catch:{ all -> 0x001b }
        L_0x0022:
            r4.setGraphObject(r5)     // Catch:{ all -> 0x001b }
            android.os.Bundle r5 = r4.getParameters()     // Catch:{ all -> 0x001b }
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x001b }
            java.lang.String r7 = "events.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch:{ all -> 0x001b }
            java.lang.String r7 = "custom_events"
            r5.putString(r7, r6)     // Catch:{ all -> 0x001b }
            com.facebook.internal.FeatureManager$Feature r7 = com.facebook.internal.FeatureManager.Feature.IapLoggingLib5To7     // Catch:{ all -> 0x001b }
            boolean r7 = com.facebook.internal.FeatureManager.isEnabled(r7)     // Catch:{ all -> 0x001b }
            if (r7 == 0) goto L_0x0048
            java.lang.String r7 = "operational_parameters"
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x001b }
            r5.putString(r7, r8)     // Catch:{ all -> 0x001b }
        L_0x0048:
            r4.setTag(r6)     // Catch:{ all -> 0x001b }
            r4.setParameters(r5)     // Catch:{ all -> 0x001b }
            return
        L_0x004f:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r4, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.SessionEventsState.populateRequest(com.facebook.GraphRequest, android.content.Context, int, org.json.JSONArray, org.json.JSONArray, boolean):void");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
