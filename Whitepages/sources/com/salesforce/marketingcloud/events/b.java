package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class b implements Event {
    private final String a;
    private final Map<String, Object> b;
    private final Event.Producer c;

    public b(String str, Map<String, ? extends Object> map, Event.Producer producer) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, k.a.h);
        Intrinsics.checkNotNullParameter(producer, "producer");
        this.a = str;
        this.b = map;
        this.c = producer;
    }

    public final Map<String, Object> a() {
        return this.b;
    }

    public Map<String, Object> attributes() {
        return this.b;
    }

    public Event.Producer getProducer() {
        return this.c;
    }

    public String name() {
        return this.a;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(String str, Map map, Event.Producer producer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? MapsKt.emptyMap() : map, (i & 4) != 0 ? Event.Producer.PUSH : producer);
    }
}
