package com.google.protobuf;

import java.util.Iterator;
import java.util.Map;

public abstract class LazyField extends LazyFieldLite {

    static class LazyEntry implements Map.Entry {
        public abstract LazyField getField();
    }

    static class LazyIterator implements Iterator {
        private Iterator iterator;

        public LazyIterator(Iterator it) {
            this.iterator = it;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public Map.Entry next() {
            Map.Entry entry = (Map.Entry) this.iterator.next();
            entry.getValue();
            return entry;
        }

        public void remove() {
            this.iterator.remove();
        }
    }
}
