package com.facebook.imagepipeline.memory;

import java.util.HashSet;
import java.util.Set;

public abstract class LruBucketsPoolBackend implements PoolBackend {
    private final Set mCurrentItems = new HashSet();
    private final BucketMap mMap = new BucketMap();

    public Object get(int i) {
        return maybeRemoveFromCurrentItems(this.mMap.acquire(i));
    }

    public void put(Object obj) {
        boolean add;
        synchronized (this) {
            add = this.mCurrentItems.add(obj);
        }
        if (add) {
            this.mMap.release(getSize(obj), obj);
        }
    }

    public Object pop() {
        return maybeRemoveFromCurrentItems(this.mMap.removeFromEnd());
    }

    private Object maybeRemoveFromCurrentItems(Object obj) {
        if (obj != null) {
            synchronized (this) {
                this.mCurrentItems.remove(obj);
            }
        }
        return obj;
    }
}
