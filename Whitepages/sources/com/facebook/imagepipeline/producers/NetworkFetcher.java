package com.facebook.imagepipeline.producers;

import java.io.InputStream;
import java.util.Map;

public interface NetworkFetcher {

    public interface Callback {
        void onCancellation();

        void onFailure(Throwable th);

        void onResponse(InputStream inputStream, int i);
    }

    FetchState createFetchState(Consumer consumer, ProducerContext producerContext);

    void fetch(FetchState fetchState, Callback callback);

    Map getExtraMap(FetchState fetchState, int i);

    void onFetchCompletion(FetchState fetchState, int i);

    boolean shouldPropagate(FetchState fetchState);
}
