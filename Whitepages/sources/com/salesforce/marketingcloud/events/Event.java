package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

@MCKeep
public interface Event {
    /* access modifiers changed from: private */
    static void track$lambda$0(Event event, MarketingCloudSdk marketingCloudSdk) {
        Intrinsics.checkNotNullParameter(event, "this$0");
        Intrinsics.checkNotNullParameter(marketingCloudSdk, "it");
        marketingCloudSdk.getEventManager().track(event);
    }

    Map<String, Object> attributes();

    Event.Producer getProducer() {
        return Event.Producer.PUSH;
    }

    String name();

    void track() {
        MarketingCloudSdk.requestSdk(new Event$$ExternalSyntheticLambda0(this));
    }
}
