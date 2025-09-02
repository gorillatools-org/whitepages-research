package com.salesforce.marketingcloud.sfmcsdk.components.events;

public interface EventSubscriber {
    void onEventPublished(Event... eventArr);
}
