package com.google.firebase.perf.session;

import android.content.Context;

public final /* synthetic */ class SessionManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SessionManager f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ PerfSession f$2;

    public /* synthetic */ SessionManager$$ExternalSyntheticLambda0(SessionManager sessionManager, Context context, PerfSession perfSession) {
        this.f$0 = sessionManager;
        this.f$1 = context;
        this.f$2 = perfSession;
    }

    public final void run() {
        this.f$0.lambda$setApplicationContext$0(this.f$1, this.f$2);
    }
}
