package com.salesforce.marketingcloud.sfmcsdk;

import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

final class SFMCSdk$internalTrack$1$1 extends Lambda implements Function0 {
    final /* synthetic */ Event[] $events;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdk$internalTrack$1$1(Event[] eventArr) {
        super(0);
        this.$events = eventArr;
    }

    public final String invoke() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tracking events: ");
        String str = "";
        for (Event event : ArraysKt.filterNotNull(this.$events)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(StringsKt.isBlank(str) ? "" : ", ");
            sb2.append(Reflection.getOrCreateKotlinClass(event.getClass()).getSimpleName());
            sb2.append("( ");
            sb2.append(event.name());
            sb2.append(" )");
            str = sb2.toString();
        }
        sb.append(str);
        return sb.toString();
    }
}
