package com.facebook.react.devsupport;

import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;

public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ DevSupportManagerBase f$0;
    public final /* synthetic */ PackagerStatusCallback f$1;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda16(DevSupportManagerBase devSupportManagerBase, PackagerStatusCallback packagerStatusCallback) {
        this.f$0 = devSupportManagerBase;
        this.f$1 = packagerStatusCallback;
    }

    public final void run() {
        this.f$0.lambda$isPackagerRunning$10(this.f$1);
    }
}
