package com.salesforce.marketingcloud;

import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener;

public final /* synthetic */ class MarketingCloudConfig$$ExternalSyntheticLambda1 implements MarketingCloudSdk.WhenReadyListener {
    public final /* synthetic */ ModuleReadyListener f$0;

    public /* synthetic */ MarketingCloudConfig$$ExternalSyntheticLambda1(ModuleReadyListener moduleReadyListener) {
        this.f$0 = moduleReadyListener;
    }

    public final void ready(MarketingCloudSdk marketingCloudSdk) {
        MarketingCloudConfig.init$lambda$1$lambda$0(this.f$0, marketingCloudSdk);
    }
}
