package com.facebook.cache.common;

import android.net.Uri;

public interface CacheKey {
    boolean containsUri(Uri uri);

    String getUriString();

    boolean isResourceIdForDebugging();
}
