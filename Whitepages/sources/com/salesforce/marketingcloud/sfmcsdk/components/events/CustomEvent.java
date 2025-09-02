package com.salesforce.marketingcloud.sfmcsdk.components.events;

import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomEvent extends Event {
    private final Map<String, Object> attributes;
    private final Event.Category category;
    private final String name;
    private final Event.Producer producer;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CustomEvent(String str, Map map, Event.Producer producer2, Event.Category category2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, map, (i & 4) != 0 ? Event.Producer.SFMC_SDK : producer2, (i & 8) != 0 ? Event.Category.ENGAGEMENT : category2);
    }

    public Event.Producer getProducer() {
        return this.producer;
    }

    public Event.Category getCategory() {
        return this.category;
    }

    public CustomEvent(String str, Map<String, ? extends Object> map, Event.Producer producer2, Event.Category category2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, k.a.h);
        Intrinsics.checkNotNullParameter(producer2, "producer");
        Intrinsics.checkNotNullParameter(category2, "category");
        this.name = str;
        this.attributes = map;
        this.producer = producer2;
        this.category = category2;
    }

    public String name() {
        return this.name;
    }

    public Map<String, Object> attributes() {
        return this.attributes;
    }

    public String toString() {
        return "CustomEvent(name='" + this.name + "', producer=" + getProducer() + ", category=" + getCategory() + ", attributes=" + this.attributes + ')';
    }
}
