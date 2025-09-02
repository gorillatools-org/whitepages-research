package com.facebook.react.modules.fresco;

import android.util.Pair;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.systrace.Systrace;
import com.salesforce.marketingcloud.config.a;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class SystraceRequestListener extends BaseRequestListener {
    private int currentId;
    private final Map<String, Pair<Integer, String>> producerId = new LinkedHashMap();
    private final Map<String, Pair<Integer, String>> requestsId = new LinkedHashMap();

    public boolean requiresExtraMap(String str) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        return false;
    }

    public void onProducerStart(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        if (Systrace.isTracing(0)) {
            Pair create = Pair.create(Integer.valueOf(this.currentId), "FRESCO_PRODUCER_" + StringsKt.replace$default(str2, ':', '_', false, 4, (Object) null));
            Object obj = create.second;
            Intrinsics.checkNotNullExpressionValue(obj, "second");
            Systrace.beginAsyncSection(0, (String) obj, this.currentId);
            this.producerId.put(str, create);
            this.currentId++;
        }
    }

    public void onProducerFinishWithSuccess(String str, String str2, Map<String, String> map) {
        Pair pair;
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        if (Systrace.isTracing(0) && (pair = this.producerId.get(str)) != null) {
            Object obj = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj, "second");
            Object obj2 = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj2, "first");
            Systrace.endAsyncSection(0, (String) obj, ((Number) obj2).intValue());
            this.producerId.remove(str);
        }
    }

    public void onProducerFinishWithFailure(String str, String str2, Throwable th, Map<String, String> map) {
        Pair pair;
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        Intrinsics.checkNotNullParameter(th, "t");
        if (Systrace.isTracing(0) && (pair = this.producerId.get(str)) != null) {
            Object obj = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj, "second");
            Object obj2 = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj2, "first");
            Systrace.endAsyncSection(0, (String) obj, ((Number) obj2).intValue());
            this.producerId.remove(str);
        }
    }

    public void onProducerFinishWithCancellation(String str, String str2, Map<String, String> map) {
        Pair pair;
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        if (Systrace.isTracing(0) && (pair = this.producerId.get(str)) != null) {
            Object obj = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj, "second");
            Object obj2 = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj2, "first");
            Systrace.endAsyncSection(0, (String) obj, ((Number) obj2).intValue());
            this.producerId.remove(str);
        }
    }

    public void onProducerEvent(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        Intrinsics.checkNotNullParameter(str3, a.h);
        if (Systrace.isTracing(0)) {
            Systrace.traceInstant(0, "FRESCO_PRODUCER_EVENT_" + StringsKt.replace$default(str, ':', '_', false, 4, (Object) null) + "_" + StringsKt.replace$default(str2, ':', '_', false, 4, (Object) null) + "_" + StringsKt.replace$default(str3, ':', '_', false, 4, (Object) null), Systrace.EventScope.THREAD);
        }
    }

    public void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) {
        Intrinsics.checkNotNullParameter(imageRequest, "request");
        Intrinsics.checkNotNullParameter(obj, "callerContext");
        Intrinsics.checkNotNullParameter(str, "requestId");
        if (Systrace.isTracing(0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("FRESCO_REQUEST_");
            String uri = imageRequest.getSourceUri().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            sb.append(StringsKt.replace$default(uri, ':', '_', false, 4, (Object) null));
            Pair create = Pair.create(Integer.valueOf(this.currentId), sb.toString());
            Object obj2 = create.second;
            Intrinsics.checkNotNullExpressionValue(obj2, "second");
            Systrace.beginAsyncSection(0, (String) obj2, this.currentId);
            this.requestsId.put(str, create);
            this.currentId++;
        }
    }

    public void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) {
        Pair pair;
        Intrinsics.checkNotNullParameter(imageRequest, "request");
        Intrinsics.checkNotNullParameter(str, "requestId");
        if (Systrace.isTracing(0) && (pair = this.requestsId.get(str)) != null) {
            Object obj = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj, "second");
            Object obj2 = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj2, "first");
            Systrace.endAsyncSection(0, (String) obj, ((Number) obj2).intValue());
            this.requestsId.remove(str);
        }
    }

    public void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
        Pair pair;
        Intrinsics.checkNotNullParameter(imageRequest, "request");
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(th, "throwable");
        if (Systrace.isTracing(0) && (pair = this.requestsId.get(str)) != null) {
            Object obj = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj, "second");
            Object obj2 = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj2, "first");
            Systrace.endAsyncSection(0, (String) obj, ((Number) obj2).intValue());
            this.requestsId.remove(str);
        }
    }

    public void onRequestCancellation(String str) {
        Pair pair;
        Intrinsics.checkNotNullParameter(str, "requestId");
        if (Systrace.isTracing(0) && (pair = this.requestsId.get(str)) != null) {
            Object obj = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj, "second");
            Object obj2 = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj2, "first");
            Systrace.endAsyncSection(0, (String) obj, ((Number) obj2).intValue());
            this.requestsId.remove(str);
        }
    }
}
