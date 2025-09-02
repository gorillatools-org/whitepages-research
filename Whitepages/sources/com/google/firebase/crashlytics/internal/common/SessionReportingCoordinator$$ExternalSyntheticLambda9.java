package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class SessionReportingCoordinator$$ExternalSyntheticLambda9 implements Continuation {
    public final /* synthetic */ SessionReportingCoordinator f$0;

    public /* synthetic */ SessionReportingCoordinator$$ExternalSyntheticLambda9(SessionReportingCoordinator sessionReportingCoordinator) {
        this.f$0 = sessionReportingCoordinator;
    }

    public final Object then(Task task) {
        return Boolean.valueOf(this.f$0.onReportSendComplete(task));
    }
}
