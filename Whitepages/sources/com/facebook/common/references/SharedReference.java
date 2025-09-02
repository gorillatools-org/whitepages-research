package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.util.IdentityHashMap;
import java.util.Map;

public class SharedReference {
    private static final Map sLiveObjects = new IdentityHashMap();
    private int mRefCount = 1;
    private final ResourceReleaser mResourceReleaser;
    private Object mValue;

    public SharedReference(Object obj, ResourceReleaser resourceReleaser, boolean z) {
        this.mValue = Preconditions.checkNotNull(obj);
        this.mResourceReleaser = resourceReleaser;
        if (z) {
            addLiveReference(obj);
        }
    }

    private static void addLiveReference(Object obj) {
        Map map = sLiveObjects;
        synchronized (map) {
            try {
                Integer num = (Integer) map.get(obj);
                if (num == null) {
                    map.put(obj, 1);
                } else {
                    map.put(obj, Integer.valueOf(num.intValue() + 1));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static void removeLiveReference(Object obj) {
        Map map = sLiveObjects;
        synchronized (map) {
            try {
                Integer num = (Integer) map.get(obj);
                if (num == null) {
                    FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", obj.getClass());
                } else if (num.intValue() == 1) {
                    map.remove(obj);
                } else {
                    map.put(obj, Integer.valueOf(num.intValue() - 1));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized Object get() {
        return this.mValue;
    }

    public synchronized boolean isValid() {
        return this.mRefCount > 0;
    }

    public static boolean isValid(SharedReference sharedReference) {
        return sharedReference != null && sharedReference.isValid();
    }

    public synchronized void addReference() {
        ensureValid();
        this.mRefCount++;
    }

    public void deleteReference() {
        Object obj;
        if (decreaseRefCount() == 0) {
            synchronized (this) {
                obj = this.mValue;
                this.mValue = null;
            }
            if (obj != null) {
                ResourceReleaser resourceReleaser = this.mResourceReleaser;
                if (resourceReleaser != null) {
                    resourceReleaser.release(obj);
                }
                removeLiveReference(obj);
            }
        }
    }

    private synchronized int decreaseRefCount() {
        int i;
        ensureValid();
        Preconditions.checkArgument(Boolean.valueOf(this.mRefCount > 0));
        i = this.mRefCount - 1;
        this.mRefCount = i;
        return i;
    }

    private void ensureValid() {
        if (!isValid(this)) {
            throw new NullReferenceException();
        }
    }

    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }
}
