package com.facebook.appevents.codeless;

import java.util.TimerTask;

public final /* synthetic */ class ViewIndexer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ViewIndexer f$0;
    public final /* synthetic */ TimerTask f$1;

    public /* synthetic */ ViewIndexer$$ExternalSyntheticLambda0(ViewIndexer viewIndexer, TimerTask timerTask) {
        this.f$0 = viewIndexer;
        this.f$1 = timerTask;
    }

    public final void run() {
        ViewIndexer.schedule$lambda$0(this.f$0, this.f$1);
    }
}
