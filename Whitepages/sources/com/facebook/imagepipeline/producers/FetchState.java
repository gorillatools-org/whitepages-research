package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.common.BytesRange;

public abstract class FetchState {
    private final Consumer mConsumer;
    private final ProducerContext mContext;
    private long mLastIntermediateResultTimeMs = 0;
    private int mOnNewResultStatusFlags;
    private BytesRange mResponseBytesRange;

    public FetchState(Consumer consumer, ProducerContext producerContext) {
        this.mConsumer = consumer;
        this.mContext = producerContext;
    }

    public Consumer getConsumer() {
        return this.mConsumer;
    }

    public ProducerContext getContext() {
        return this.mContext;
    }

    public ProducerListener2 getListener() {
        return this.mContext.getProducerListener();
    }

    public Uri getUri() {
        return this.mContext.getImageRequest().getSourceUri();
    }

    public long getLastIntermediateResultTimeMs() {
        return this.mLastIntermediateResultTimeMs;
    }

    public void setLastIntermediateResultTimeMs(long j) {
        this.mLastIntermediateResultTimeMs = j;
    }

    public int getOnNewResultStatusFlags() {
        return this.mOnNewResultStatusFlags;
    }

    public void setOnNewResultStatusFlags(int i) {
        this.mOnNewResultStatusFlags = i;
    }

    public BytesRange getResponseBytesRange() {
        return this.mResponseBytesRange;
    }

    public void setResponseBytesRange(BytesRange bytesRange) {
        this.mResponseBytesRange = bytesRange;
    }
}
