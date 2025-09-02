package androidx.arch.core.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class SafeIterableMap implements Iterable {
    private Entry mEnd;
    private final WeakHashMap mIterators = new WeakHashMap();
    private int mSize = 0;
    Entry mStart;

    public static abstract class SupportRemove {
        /* access modifiers changed from: package-private */
        public abstract void supportRemove(Entry entry);
    }

    /* access modifiers changed from: protected */
    public Entry get(Object obj) {
        Entry entry = this.mStart;
        while (entry != null && !entry.mKey.equals(obj)) {
            entry = entry.mNext;
        }
        return entry;
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        Entry entry = get(obj);
        if (entry != null) {
            return entry.mValue;
        }
        put(obj, obj2);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Entry put(Object obj, Object obj2) {
        Entry entry = new Entry(obj, obj2);
        this.mSize++;
        Entry entry2 = this.mEnd;
        if (entry2 == null) {
            this.mStart = entry;
            this.mEnd = entry;
            return entry;
        }
        entry2.mNext = entry;
        entry.mPrevious = entry2;
        this.mEnd = entry;
        return entry;
    }

    public Object remove(Object obj) {
        Entry entry = get(obj);
        if (entry == null) {
            return null;
        }
        this.mSize--;
        if (!this.mIterators.isEmpty()) {
            for (SupportRemove supportRemove : this.mIterators.keySet()) {
                supportRemove.supportRemove(entry);
            }
        }
        Entry entry2 = entry.mPrevious;
        if (entry2 != null) {
            entry2.mNext = entry.mNext;
        } else {
            this.mStart = entry.mNext;
        }
        Entry entry3 = entry.mNext;
        if (entry3 != null) {
            entry3.mPrevious = entry2;
        } else {
            this.mEnd = entry2;
        }
        entry.mNext = null;
        entry.mPrevious = null;
        return entry.mValue;
    }

    public int size() {
        return this.mSize;
    }

    public Iterator iterator() {
        AscendingIterator ascendingIterator = new AscendingIterator(this.mStart, this.mEnd);
        this.mIterators.put(ascendingIterator, Boolean.FALSE);
        return ascendingIterator;
    }

    public Iterator descendingIterator() {
        DescendingIterator descendingIterator = new DescendingIterator(this.mEnd, this.mStart);
        this.mIterators.put(descendingIterator, Boolean.FALSE);
        return descendingIterator;
    }

    public IteratorWithAdditions iteratorWithAdditions() {
        IteratorWithAdditions iteratorWithAdditions = new IteratorWithAdditions();
        this.mIterators.put(iteratorWithAdditions, Boolean.FALSE);
        return iteratorWithAdditions;
    }

    public Map.Entry eldest() {
        return this.mStart;
    }

    public Map.Entry newest() {
        return this.mEnd;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
        if (size() != safeIterableMap.size()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = safeIterableMap.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object next = it2.next();
            if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                return false;
            }
        }
        if (it.hasNext() || it2.hasNext()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((Map.Entry) it.next()).hashCode();
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((Map.Entry) it.next()).toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static abstract class ListIterator extends SupportRemove implements Iterator {
        Entry mExpectedEnd;
        Entry mNext;

        /* access modifiers changed from: package-private */
        public abstract Entry backward(Entry entry);

        /* access modifiers changed from: package-private */
        public abstract Entry forward(Entry entry);

        ListIterator(Entry entry, Entry entry2) {
            this.mExpectedEnd = entry2;
            this.mNext = entry;
        }

        public boolean hasNext() {
            return this.mNext != null;
        }

        public void supportRemove(Entry entry) {
            if (this.mExpectedEnd == entry && entry == this.mNext) {
                this.mNext = null;
                this.mExpectedEnd = null;
            }
            Entry entry2 = this.mExpectedEnd;
            if (entry2 == entry) {
                this.mExpectedEnd = backward(entry2);
            }
            if (this.mNext == entry) {
                this.mNext = nextNode();
            }
        }

        private Entry nextNode() {
            Entry entry = this.mNext;
            Entry entry2 = this.mExpectedEnd;
            if (entry == entry2 || entry2 == null) {
                return null;
            }
            return forward(entry);
        }

        public Map.Entry next() {
            Entry entry = this.mNext;
            this.mNext = nextNode();
            return entry;
        }
    }

    static class AscendingIterator extends ListIterator {
        AscendingIterator(Entry entry, Entry entry2) {
            super(entry, entry2);
        }

        /* access modifiers changed from: package-private */
        public Entry forward(Entry entry) {
            return entry.mNext;
        }

        /* access modifiers changed from: package-private */
        public Entry backward(Entry entry) {
            return entry.mPrevious;
        }
    }

    private static class DescendingIterator extends ListIterator {
        DescendingIterator(Entry entry, Entry entry2) {
            super(entry, entry2);
        }

        /* access modifiers changed from: package-private */
        public Entry forward(Entry entry) {
            return entry.mPrevious;
        }

        /* access modifiers changed from: package-private */
        public Entry backward(Entry entry) {
            return entry.mNext;
        }
    }

    public class IteratorWithAdditions extends SupportRemove implements Iterator {
        private boolean mBeforeStart = true;
        private Entry mCurrent;

        IteratorWithAdditions() {
        }

        /* access modifiers changed from: package-private */
        public void supportRemove(Entry entry) {
            Entry entry2 = this.mCurrent;
            if (entry == entry2) {
                Entry entry3 = entry2.mPrevious;
                this.mCurrent = entry3;
                this.mBeforeStart = entry3 == null;
            }
        }

        public boolean hasNext() {
            if (!this.mBeforeStart) {
                Entry entry = this.mCurrent;
                if (entry == null || entry.mNext == null) {
                    return false;
                }
                return true;
            } else if (SafeIterableMap.this.mStart != null) {
                return true;
            } else {
                return false;
            }
        }

        public Map.Entry next() {
            if (this.mBeforeStart) {
                this.mBeforeStart = false;
                this.mCurrent = SafeIterableMap.this.mStart;
            } else {
                Entry entry = this.mCurrent;
                this.mCurrent = entry != null ? entry.mNext : null;
            }
            return this.mCurrent;
        }
    }

    static class Entry implements Map.Entry {
        final Object mKey;
        Entry mNext;
        Entry mPrevious;
        final Object mValue;

        Entry(Object obj, Object obj2) {
            this.mKey = obj;
            this.mValue = obj2;
        }

        public Object getKey() {
            return this.mKey;
        }

        public Object getValue() {
            return this.mValue;
        }

        public Object setValue(Object obj) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.mKey + "=" + this.mValue;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (!this.mKey.equals(entry.mKey) || !this.mValue.equals(entry.mValue)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.mKey.hashCode() ^ this.mValue.hashCode();
        }
    }
}
