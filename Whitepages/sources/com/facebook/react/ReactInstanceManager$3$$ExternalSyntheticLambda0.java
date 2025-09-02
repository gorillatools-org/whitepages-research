package com.facebook.react;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;

public final /* synthetic */ class ReactInstanceManager$3$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ReactInstanceManager.AnonymousClass3 f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ DeveloperSettings f$2;

    public /* synthetic */ ReactInstanceManager$3$$ExternalSyntheticLambda0(ReactInstanceManager.AnonymousClass3 r1, boolean z, DeveloperSettings developerSettings) {
        this.f$0 = r1;
        this.f$1 = z;
        this.f$2 = developerSettings;
    }

    public final void run() {
        this.f$0.lambda$onPackagerStatusFetched$0(this.f$1, this.f$2);
    }
}
