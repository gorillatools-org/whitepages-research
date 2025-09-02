package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.MarketingCloudSdk;

public final /* synthetic */ class Event$$ExternalSyntheticLambda0 implements MarketingCloudSdk.WhenReadyListener {
    public final /* synthetic */ Event f$0;

    public /* synthetic */ Event$$ExternalSyntheticLambda0(Event event) {
        this.f$0 = event;
    }

    public final void ready(MarketingCloudSdk marketingCloudSdk) {
        Event.track$lambda$0(this.f$0, marketingCloudSdk);
    }
}
