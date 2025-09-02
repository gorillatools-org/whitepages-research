package com.facebook.cache.common;

import com.facebook.common.util.SecureHashUtil;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class CacheKeyUtil {
    public static final CacheKeyUtil INSTANCE = new CacheKeyUtil();

    private CacheKeyUtil() {
    }

    public static final List getResourceIds(CacheKey cacheKey) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        try {
            if (cacheKey instanceof MultiCacheKey) {
                List cacheKeys = ((MultiCacheKey) cacheKey).getCacheKeys();
                Intrinsics.checkNotNullExpressionValue(cacheKeys, "getCacheKeys(...)");
                arrayList = new ArrayList(cacheKeys.size());
                int size = cacheKeys.size();
                for (int i = 0; i < size; i++) {
                    CacheKeyUtil cacheKeyUtil = INSTANCE;
                    Object obj = cacheKeys.get(i);
                    Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                    arrayList.add(cacheKeyUtil.secureHashKey((CacheKey) obj));
                }
            } else {
                arrayList = new ArrayList(1);
                arrayList.add(cacheKey.isResourceIdForDebugging() ? cacheKey.getUriString() : INSTANCE.secureHashKey(cacheKey));
            }
            return arrayList;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String getFirstResourceId(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        try {
            if (!(cacheKey instanceof MultiCacheKey)) {
                return INSTANCE.secureHashKey(cacheKey);
            }
            List cacheKeys = ((MultiCacheKey) cacheKey).getCacheKeys();
            Intrinsics.checkNotNullExpressionValue(cacheKeys, "getCacheKeys(...)");
            CacheKeyUtil cacheKeyUtil = INSTANCE;
            Object obj = cacheKeys.get(0);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return cacheKeyUtil.secureHashKey((CacheKey) obj);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private final String secureHashKey(CacheKey cacheKey) {
        String uriString = cacheKey.getUriString();
        Intrinsics.checkNotNullExpressionValue(uriString, "getUriString(...)");
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
        byte[] bytes = uriString.getBytes(forName);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String makeSHA1HashBase64 = SecureHashUtil.makeSHA1HashBase64(bytes);
        Intrinsics.checkNotNullExpressionValue(makeSHA1HashBase64, "makeSHA1HashBase64(...)");
        return makeSHA1HashBase64;
    }
}
