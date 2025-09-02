package com.facebook.react.devsupport;

import com.facebook.react.common.ShakeDetector;

public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda14 implements ShakeDetector.ShakeListener {
    public final /* synthetic */ DevSupportManagerBase f$0;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda14(DevSupportManagerBase devSupportManagerBase) {
        this.f$0 = devSupportManagerBase;
    }

    public final void onShake() {
        this.f$0.showDevOptionsDialog();
    }
}
