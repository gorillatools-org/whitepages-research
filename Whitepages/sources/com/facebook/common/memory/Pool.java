package com.facebook.common.memory;

import com.facebook.common.references.ResourceReleaser;

public interface Pool extends ResourceReleaser, MemoryTrimmable {
    Object get(int i);

    void release(Object obj);
}
