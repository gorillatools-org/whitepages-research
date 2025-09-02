package com.facebook.appevents.codeless;

public final /* synthetic */ class ViewIndexer$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ ViewIndexer f$1;

    public /* synthetic */ ViewIndexer$$ExternalSyntheticLambda1(String str, ViewIndexer viewIndexer) {
        this.f$0 = str;
        this.f$1 = viewIndexer;
    }

    public final void run() {
        ViewIndexer.sendToServer$lambda$1(this.f$0, this.f$1);
    }
}
