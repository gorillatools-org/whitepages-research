package com.facebook.react.devsupport;

import com.facebook.react.devsupport.DevSupportManagerBase;
import java.io.File;

public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DevSupportManagerBase f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ File f$2;
    public final /* synthetic */ DevSupportManagerBase.CallbackWithBundleLoader f$3;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda0(DevSupportManagerBase devSupportManagerBase, String str, File file, DevSupportManagerBase.CallbackWithBundleLoader callbackWithBundleLoader) {
        this.f$0 = devSupportManagerBase;
        this.f$1 = str;
        this.f$2 = file;
        this.f$3 = callbackWithBundleLoader;
    }

    public final void run() {
        this.f$0.lambda$fetchSplitBundleAndCreateBundleLoader$9(this.f$1, this.f$2, this.f$3);
    }
}
