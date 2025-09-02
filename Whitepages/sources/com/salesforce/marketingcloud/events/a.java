package com.salesforce.marketingcloud.events;

import java.util.Map;
import kotlin.collections.MapsKt;

public final class a implements Event {
    public Map<String, Object> attributes() {
        return MapsKt.emptyMap();
    }

    public String name() {
        return "$appOpen";
    }
}
