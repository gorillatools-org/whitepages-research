package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.metadata.EventMetadata;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

public final /* synthetic */ class SessionReportingCoordinator$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ SessionReportingCoordinator f$0;
    public final /* synthetic */ CrashlyticsReport.Session.Event f$1;
    public final /* synthetic */ EventMetadata f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ SessionReportingCoordinator$$ExternalSyntheticLambda7(SessionReportingCoordinator sessionReportingCoordinator, CrashlyticsReport.Session.Event event, EventMetadata eventMetadata, boolean z) {
        this.f$0 = sessionReportingCoordinator;
        this.f$1 = event;
        this.f$2 = eventMetadata;
        this.f$3 = z;
    }

    public final void run() {
        this.f$0.lambda$persistEvent$0(this.f$1, this.f$2, this.f$3);
    }
}
