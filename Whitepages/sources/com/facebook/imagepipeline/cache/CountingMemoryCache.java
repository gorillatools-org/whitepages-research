package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.references.CloseableReference;

public interface CountingMemoryCache extends MemoryCache, MemoryTrimmable {

    public interface EntryStateObserver {
    }

    public static class Entry {
        public int accessCount = 0;
        public int clientCount = 0;
        public boolean isOrphan = false;
        public final Object key;
        public int size;
        public final CloseableReference valueRef;

        private Entry(Object obj, CloseableReference closeableReference, EntryStateObserver entryStateObserver, int i) {
            this.key = Preconditions.checkNotNull(obj);
            this.valueRef = (CloseableReference) Preconditions.checkNotNull(CloseableReference.cloneOrNull(closeableReference));
            this.size = i;
        }

        public static Entry of(Object obj, CloseableReference closeableReference, EntryStateObserver entryStateObserver) {
            return of(obj, closeableReference, -1, entryStateObserver);
        }

        public static Entry of(Object obj, CloseableReference closeableReference, int i, EntryStateObserver entryStateObserver) {
            return new Entry(obj, closeableReference, entryStateObserver, i);
        }
    }
}
