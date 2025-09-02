package com.facebook.common.references;

import com.facebook.common.references.CloseableReference;

public class NoOpCloseableReference extends CloseableReference {
    public CloseableReference clone() {
        return this;
    }

    public CloseableReference cloneOrNull() {
        return this;
    }

    public void close() {
    }

    public boolean isValid() {
        return true;
    }

    NoOpCloseableReference(Object obj) {
        super(obj, (ResourceReleaser) null, (CloseableReference.LeakHandler) null, (Throwable) null, false);
    }
}
