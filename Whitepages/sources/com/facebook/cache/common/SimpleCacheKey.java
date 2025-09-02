package com.facebook.cache.common;

import android.net.Uri;
import com.facebook.common.internal.Preconditions;

public class SimpleCacheKey implements CacheKey {
    final boolean mIsResourceIdForDebugging;
    final String mKey;

    public SimpleCacheKey(String str) {
        this(str, false);
    }

    public SimpleCacheKey(String str, boolean z) {
        this.mKey = (String) Preconditions.checkNotNull(str);
        this.mIsResourceIdForDebugging = z;
    }

    public String toString() {
        return this.mKey;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SimpleCacheKey) {
            return this.mKey.equals(((SimpleCacheKey) obj).mKey);
        }
        return false;
    }

    public int hashCode() {
        return this.mKey.hashCode();
    }

    public boolean containsUri(Uri uri) {
        return this.mKey.contains(uri.toString());
    }

    public String getUriString() {
        return this.mKey;
    }

    public boolean isResourceIdForDebugging() {
        return this.mIsResourceIdForDebugging;
    }
}
