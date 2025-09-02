package androidx.datastore.preferences.protobuf;

import java.util.Iterator;
import java.util.Map;

class LazyField$LazyIterator implements Iterator {
    private Iterator iterator;

    public LazyField$LazyIterator(Iterator it) {
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
