package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Set;

public interface ComponentContainer {
    <T> Deferred<T> getDeferred(Qualified<T> qualified);

    <T> Provider<T> getProvider(Qualified<T> qualified);

    <T> Provider<Set<T>> setOfProvider(Qualified<T> qualified);

    <T> T get(Class<T> cls) {
        return get(Qualified.unqualified(cls));
    }

    <T> Provider<T> getProvider(Class<T> cls) {
        return getProvider(Qualified.unqualified(cls));
    }

    <T> Deferred<T> getDeferred(Class<T> cls) {
        return getDeferred(Qualified.unqualified(cls));
    }

    <T> Set<T> setOf(Class<T> cls) {
        return setOf(Qualified.unqualified(cls));
    }

    <T> Provider<Set<T>> setOfProvider(Class<T> cls) {
        return setOfProvider(Qualified.unqualified(cls));
    }

    <T> T get(Qualified<T> qualified) {
        Provider<T> provider = getProvider(qualified);
        if (provider == null) {
            return null;
        }
        return provider.get();
    }

    <T> Set<T> setOf(Qualified<T> qualified) {
        return setOfProvider(qualified).get();
    }
}
