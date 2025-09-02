package com.reactnativecommunity.webview;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract /* synthetic */ class RNCWebView$$ExternalSyntheticBackport1 {
    public static /* synthetic */ Set m(Object[] objArr) {
        HashSet hashSet = new HashSet(objArr.length);
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            Objects.requireNonNull(obj);
            if (hashSet.add(obj)) {
                i++;
            } else {
                throw new IllegalArgumentException("duplicate element: " + obj);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }
}
