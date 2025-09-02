package com.google.firebase.inject;

import com.google.firebase.annotations.DeferredApi;

public interface Deferred<T> {

    public interface DeferredHandler<T> {
        @DeferredApi
        void handle(Provider<T> provider);
    }

    void whenAvailable(DeferredHandler<T> deferredHandler);
}
