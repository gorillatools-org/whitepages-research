package com.facebook.appevents;

import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import kotlin.jvm.internal.Intrinsics;

public final class AppEventStore {
    public static final AppEventStore INSTANCE = new AppEventStore();
    private static final String TAG = AppEventStore.class.getName();

    private AppEventStore() {
    }

    public static final synchronized void persistEvents(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState) {
        synchronized (AppEventStore.class) {
            if (!CrashShieldHandler.isObjectCrashing(AppEventStore.class)) {
                try {
                    Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "accessTokenAppIdPair");
                    Intrinsics.checkNotNullParameter(sessionEventsState, "appEvents");
                    AppEventUtility.assertIsNotMainThread();
                    PersistedEvents readAndClearStore = AppEventDiskStore.readAndClearStore();
                    readAndClearStore.addEvents(accessTokenAppIdPair, sessionEventsState.getEventsToPersist());
                    AppEventDiskStore.saveEventsToDisk$facebook_core_release(readAndClearStore);
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, AppEventStore.class);
                }
            }
        }
    }

    public static final synchronized void persistEvents(AppEventCollection appEventCollection) {
        synchronized (AppEventStore.class) {
            if (!CrashShieldHandler.isObjectCrashing(AppEventStore.class)) {
                try {
                    Intrinsics.checkNotNullParameter(appEventCollection, "eventsToPersist");
                    AppEventUtility.assertIsNotMainThread();
                    PersistedEvents readAndClearStore = AppEventDiskStore.readAndClearStore();
                    for (AccessTokenAppIdPair accessTokenAppIdPair : appEventCollection.keySet()) {
                        SessionEventsState sessionEventsState = appEventCollection.get(accessTokenAppIdPair);
                        if (sessionEventsState != null) {
                            readAndClearStore.addEvents(accessTokenAppIdPair, sessionEventsState.getEventsToPersist());
                        } else {
                            throw new IllegalStateException("Required value was null.");
                        }
                    }
                    AppEventDiskStore.saveEventsToDisk$facebook_core_release(readAndClearStore);
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, AppEventStore.class);
                }
            }
        }
    }
}
