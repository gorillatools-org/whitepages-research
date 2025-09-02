package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import kotlin.jvm.internal.Intrinsics;

public final class InternalRequestListener extends InternalProducerListener implements RequestListener2 {
    private final RequestListener requestListener;
    private final RequestListener2 requestListener2;

    public InternalRequestListener(RequestListener requestListener3, RequestListener2 requestListener22) {
        super(requestListener3, requestListener22);
        this.requestListener = requestListener3;
        this.requestListener2 = requestListener22;
    }

    public void onRequestStart(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        RequestListener requestListener3 = this.requestListener;
        if (requestListener3 != null) {
            requestListener3.onRequestStart(producerContext.getImageRequest(), producerContext.getCallerContext(), producerContext.getId(), producerContext.isPrefetch());
        }
        RequestListener2 requestListener22 = this.requestListener2;
        if (requestListener22 != null) {
            requestListener22.onRequestStart(producerContext);
        }
    }

    public void onRequestSuccess(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        RequestListener requestListener3 = this.requestListener;
        if (requestListener3 != null) {
            requestListener3.onRequestSuccess(producerContext.getImageRequest(), producerContext.getId(), producerContext.isPrefetch());
        }
        RequestListener2 requestListener22 = this.requestListener2;
        if (requestListener22 != null) {
            requestListener22.onRequestSuccess(producerContext);
        }
    }

    public void onRequestFailure(ProducerContext producerContext, Throwable th) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        RequestListener requestListener3 = this.requestListener;
        if (requestListener3 != null) {
            requestListener3.onRequestFailure(producerContext.getImageRequest(), producerContext.getId(), th, producerContext.isPrefetch());
        }
        RequestListener2 requestListener22 = this.requestListener2;
        if (requestListener22 != null) {
            requestListener22.onRequestFailure(producerContext, th);
        }
    }

    public void onRequestCancellation(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        RequestListener requestListener3 = this.requestListener;
        if (requestListener3 != null) {
            requestListener3.onRequestCancellation(producerContext.getId());
        }
        RequestListener2 requestListener22 = this.requestListener2;
        if (requestListener22 != null) {
            requestListener22.onRequestCancellation(producerContext);
        }
    }
}
