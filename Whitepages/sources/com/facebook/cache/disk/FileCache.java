package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.common.disk.DiskTrimmable;

public interface FileCache extends DiskTrimmable {
    void clearAll();

    BinaryResource getResource(CacheKey cacheKey);

    boolean hasKey(CacheKey cacheKey);

    boolean hasKeySync(CacheKey cacheKey);

    BinaryResource insert(CacheKey cacheKey, WriterCallback writerCallback);

    boolean probe(CacheKey cacheKey);

    void remove(CacheKey cacheKey);
}
