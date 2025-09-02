package com.google.firebase.perf.config;

abstract class ConfigurationFlag<T> {
    /* access modifiers changed from: protected */
    public abstract T getDefault();

    /* access modifiers changed from: package-private */
    public String getDeviceCacheFlag() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getMetadataFlag() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getRemoteConfigFlag() {
        return null;
    }

    ConfigurationFlag() {
    }

    /* access modifiers changed from: protected */
    public T getDefaultOnRcFetchFail() {
        return getDefault();
    }
}
