package com.facebook.react.uimanager;

import java.util.List;

public final /* synthetic */ class ViewManagerRegistry$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ ViewManagerRegistry$$ExternalSyntheticLambda1(List list, int i) {
        this.f$0 = list;
        this.f$1 = i;
    }

    public final void run() {
        ViewManagerRegistry.lambda$onSurfaceStopped$0(this.f$0, this.f$1);
    }
}
