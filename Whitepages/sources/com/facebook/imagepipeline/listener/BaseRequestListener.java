package com.facebook.imagepipeline.listener;

import com.salesforce.marketingcloud.config.a;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseRequestListener implements RequestListener {
    public void onProducerEvent(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        Intrinsics.checkNotNullParameter(str3, a.h);
    }

    public void onProducerFinishWithCancellation(String str, String str2, Map map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
    }

    public void onProducerFinishWithFailure(String str, String str2, Throwable th, Map map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        Intrinsics.checkNotNullParameter(th, "t");
    }

    public void onProducerFinishWithSuccess(String str, String str2, Map map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
    }

    public void onProducerStart(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
    }

    public void onUltimateProducerReached(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
    }

    public boolean requiresExtraMap(String str) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        return false;
    }
}
