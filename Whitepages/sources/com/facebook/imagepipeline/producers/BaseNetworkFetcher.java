package com.facebook.imagepipeline.producers;

public abstract class BaseNetworkFetcher implements NetworkFetcher {
    public boolean shouldPropagate(FetchState fetchState) {
        return true;
    }
}
