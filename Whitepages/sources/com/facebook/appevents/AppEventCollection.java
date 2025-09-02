package com.facebook.appevents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class AppEventCollection {
    private final HashMap stateMap = new HashMap();

    public final synchronized void addPersistedEvents(PersistedEvents persistedEvents) {
        if (persistedEvents != null) {
            for (Map.Entry entry : persistedEvents.entrySet()) {
                SessionEventsState sessionEventsState = getSessionEventsState((AccessTokenAppIdPair) entry.getKey());
                if (sessionEventsState != null) {
                    for (AppEvent addEvent : (List) entry.getValue()) {
                        sessionEventsState.addEvent(addEvent);
                    }
                }
            }
        }
    }

    public final synchronized void addEvent(AccessTokenAppIdPair accessTokenAppIdPair, AppEvent appEvent) {
        Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "accessTokenAppIdPair");
        Intrinsics.checkNotNullParameter(appEvent, "appEvent");
        SessionEventsState sessionEventsState = getSessionEventsState(accessTokenAppIdPair);
        if (sessionEventsState != null) {
            sessionEventsState.addEvent(appEvent);
        }
    }

    public final synchronized Set keySet() {
        Set keySet;
        keySet = this.stateMap.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "stateMap.keys");
        return keySet;
    }

    public final synchronized SessionEventsState get(AccessTokenAppIdPair accessTokenAppIdPair) {
        Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "accessTokenAppIdPair");
        return (SessionEventsState) this.stateMap.get(accessTokenAppIdPair);
    }

    public final synchronized int getEventCount() {
        int i;
        i = 0;
        for (SessionEventsState accumulatedEventCount : this.stateMap.values()) {
            i += accumulatedEventCount.getAccumulatedEventCount();
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        r1 = com.facebook.FacebookSdk.getApplicationContext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized com.facebook.appevents.SessionEventsState getSessionEventsState(com.facebook.appevents.AccessTokenAppIdPair r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.HashMap r0 = r4.stateMap     // Catch:{ all -> 0x0023 }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x0023 }
            com.facebook.appevents.SessionEventsState r0 = (com.facebook.appevents.SessionEventsState) r0     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0025
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ all -> 0x0023 }
            com.facebook.internal.AttributionIdentifiers$Companion r2 = com.facebook.internal.AttributionIdentifiers.Companion     // Catch:{ all -> 0x0023 }
            com.facebook.internal.AttributionIdentifiers r2 = r2.getAttributionIdentifiers(r1)     // Catch:{ all -> 0x0023 }
            if (r2 == 0) goto L_0x0025
            com.facebook.appevents.SessionEventsState r0 = new com.facebook.appevents.SessionEventsState     // Catch:{ all -> 0x0023 }
            com.facebook.appevents.AppEventsLogger$Companion r3 = com.facebook.appevents.AppEventsLogger.Companion     // Catch:{ all -> 0x0023 }
            java.lang.String r1 = r3.getAnonymousAppDeviceGUID(r1)     // Catch:{ all -> 0x0023 }
            r0.<init>(r2, r1)     // Catch:{ all -> 0x0023 }
            goto L_0x0025
        L_0x0023:
            r5 = move-exception
            goto L_0x0031
        L_0x0025:
            if (r0 != 0) goto L_0x002a
            monitor-exit(r4)
            r5 = 0
            return r5
        L_0x002a:
            java.util.HashMap r1 = r4.stateMap     // Catch:{ all -> 0x0023 }
            r1.put(r5, r0)     // Catch:{ all -> 0x0023 }
            monitor-exit(r4)
            return r0
        L_0x0031:
            monitor-exit(r4)     // Catch:{ all -> 0x0023 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventCollection.getSessionEventsState(com.facebook.appevents.AccessTokenAppIdPair):com.facebook.appevents.SessionEventsState");
    }
}
