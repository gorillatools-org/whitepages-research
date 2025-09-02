package com.google.android.datatransport.runtime.retries;

public interface RetryStrategy {
    Object shouldRetry(Object obj, Object obj2);
}
