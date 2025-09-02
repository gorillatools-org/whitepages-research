package com.salesforce.marketingcloud;

import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener;

public final /* synthetic */ class MarketingCloudConfig$$ExternalSyntheticLambda0 implements MarketingCloudSdk.InitializationListener {
    public final /* synthetic */ ModuleReadyListener f$0;

    public /* synthetic */ MarketingCloudConfig$$ExternalSyntheticLambda0(ModuleReadyListener moduleReadyListener) {
        this.f$0 = moduleReadyListener;
    }

    public final void complete(InitializationStatus initializationStatus) {
        MarketingCloudConfig.init$lambda$1(this.f$0, initializationStatus);
    }
}
