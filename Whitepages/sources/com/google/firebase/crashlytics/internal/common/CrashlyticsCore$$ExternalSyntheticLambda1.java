package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;

public final /* synthetic */ class CrashlyticsCore$$ExternalSyntheticLambda1 implements BreadcrumbHandler {
    public final /* synthetic */ CrashlyticsCore f$0;

    public /* synthetic */ CrashlyticsCore$$ExternalSyntheticLambda1(CrashlyticsCore crashlyticsCore) {
        this.f$0 = crashlyticsCore;
    }

    public final void handleBreadcrumb(String str) {
        this.f$0.log(str);
    }
}
