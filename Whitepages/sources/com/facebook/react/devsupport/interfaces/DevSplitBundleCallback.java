package com.facebook.react.devsupport.interfaces;

public interface DevSplitBundleCallback {
    void onError(String str, Throwable th);

    void onSuccess();
}
