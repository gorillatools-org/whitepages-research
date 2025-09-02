package com.google.common.util.concurrent;

public interface FutureCallback {
    void onFailure(Throwable th);

    void onSuccess(Object obj);
}
