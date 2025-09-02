package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.sessions.api.SessionSubscriber;

public class CrashlyticsAppQualitySessionsSubscriber implements SessionSubscriber {
    private final CrashlyticsAppQualitySessionsStore appQualitySessionsStore;
    private final DataCollectionArbiter dataCollectionArbiter;

    public CrashlyticsAppQualitySessionsSubscriber(DataCollectionArbiter dataCollectionArbiter2, FileStore fileStore) {
        this.dataCollectionArbiter = dataCollectionArbiter2;
        this.appQualitySessionsStore = new CrashlyticsAppQualitySessionsStore(fileStore);
    }

    public String getAppQualitySessionId(String str) {
        return this.appQualitySessionsStore.getAppQualitySessionId(str);
    }

    public void setSessionId(String str) {
        this.appQualitySessionsStore.rotateSessionId(str);
    }

    public void onSessionChanged(SessionSubscriber.SessionDetails sessionDetails) {
        Logger logger = Logger.getLogger();
        logger.d("App Quality Sessions session changed: " + sessionDetails);
        this.appQualitySessionsStore.rotateAppQualitySessionId(sessionDetails.getSessionId());
    }

    public boolean isDataCollectionEnabled() {
        return this.dataCollectionArbiter.isAutomaticDataCollectionEnabled();
    }

    public SessionSubscriber.Name getSessionSubscriberName() {
        return SessionSubscriber.Name.CRASHLYTICS;
    }
}
