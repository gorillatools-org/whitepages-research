package com.salesforce.marketingcloud.sfmcsdk.components.events;

import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class EngagementEvent extends Event {
    private Event.Category category;
    private final String name;

    public /* synthetic */ EngagementEvent(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private EngagementEvent(String str) {
        this.name = str;
        this.category = Event.Category.ENGAGEMENT;
    }

    public Map<String, Object> attributes() {
        return MapsKt.emptyMap();
    }

    public String name() {
        return this.name;
    }

    public Event.Category getCategory() {
        return this.category;
    }

    public void setCategory(Event.Category category2) {
        Intrinsics.checkNotNullParameter(category2, "<set-?>");
        this.category = category2;
    }
}
