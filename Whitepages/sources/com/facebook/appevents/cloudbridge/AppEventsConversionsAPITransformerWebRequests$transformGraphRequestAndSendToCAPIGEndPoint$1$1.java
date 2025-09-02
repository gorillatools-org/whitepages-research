package com.facebook.appevents.cloudbridge;

import com.facebook.internal.Utility;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class AppEventsConversionsAPITransformerWebRequests$transformGraphRequestAndSendToCAPIGEndPoint$1$1 extends Lambda implements Function2 {
    final /* synthetic */ List $processedEvents;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AppEventsConversionsAPITransformerWebRequests$transformGraphRequestAndSendToCAPIGEndPoint$1$1(List list) {
        super(2);
        this.$processedEvents = list;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (Integer) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(String str, Integer num) {
        Utility.runOnNonUiThread(new AppEventsConversionsAPITransformerWebRequests$transformGraphRequestAndSendToCAPIGEndPoint$1$1$$ExternalSyntheticLambda0(num, this.$processedEvents));
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$0(Integer num, List list) {
        Intrinsics.checkNotNullParameter(list, "$processedEvents");
        if (!CollectionsKt.contains(AppEventsConversionsAPITransformerWebRequests.ACCEPTABLE_HTTP_RESPONSE, num)) {
            AppEventsConversionsAPITransformerWebRequests.INSTANCE.handleError$facebook_core_release(num, list, 5);
        }
    }
}
