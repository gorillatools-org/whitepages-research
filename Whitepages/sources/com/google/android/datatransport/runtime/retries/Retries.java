package com.google.android.datatransport.runtime.retries;

public abstract class Retries {
    public static Object retry(int i, Object obj, Function function, RetryStrategy retryStrategy) {
        Object apply;
        if (i < 1) {
            return function.apply(obj);
        }
        do {
            apply = function.apply(obj);
            obj = retryStrategy.shouldRetry(obj, apply);
            if (obj == null || i - 1 < 1) {
                return apply;
            }
            apply = function.apply(obj);
            obj = retryStrategy.shouldRetry(obj, apply);
            break;
        } while (i - 1 < 1);
        return apply;
    }
}
