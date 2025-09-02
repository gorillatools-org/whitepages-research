package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CountingLruMap {
    private final LinkedHashMap mMap = new LinkedHashMap();
    private int mSizeInBytes = 0;
    private final ValueDescriptor mValueDescriptor;

    public CountingLruMap(ValueDescriptor valueDescriptor) {
        this.mValueDescriptor = valueDescriptor;
    }

    public synchronized int getCount() {
        return this.mMap.size();
    }

    public synchronized int getSizeInBytes() {
        return this.mSizeInBytes;
    }

    public synchronized Object getFirstKey() {
        return this.mMap.isEmpty() ? null : this.mMap.keySet().iterator().next();
    }

    public synchronized ArrayList getMatchingEntries(Predicate predicate) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList(this.mMap.entrySet().size());
            for (Map.Entry entry : this.mMap.entrySet()) {
                if (predicate != null) {
                    if (predicate.apply(entry.getKey())) {
                    }
                }
                arrayList.add(entry);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return arrayList;
    }

    public synchronized Object get(Object obj) {
        return this.mMap.get(obj);
    }

    public synchronized Object put(Object obj, Object obj2) {
        Object remove;
        remove = this.mMap.remove(obj);
        this.mSizeInBytes -= getValueSizeInBytes(remove);
        this.mMap.put(obj, obj2);
        this.mSizeInBytes += getValueSizeInBytes(obj2);
        return remove;
    }

    public synchronized Object remove(Object obj) {
        Object remove;
        remove = this.mMap.remove(obj);
        this.mSizeInBytes -= getValueSizeInBytes(remove);
        return remove;
    }

    public synchronized ArrayList removeAll(Predicate predicate) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            Iterator it = this.mMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (predicate != null) {
                    if (predicate.apply(entry.getKey())) {
                    }
                }
                arrayList.add(entry.getValue());
                this.mSizeInBytes -= getValueSizeInBytes(entry.getValue());
                it.remove();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return arrayList;
    }

    public synchronized void resetSize() {
        if (this.mMap.isEmpty()) {
            this.mSizeInBytes = 0;
        }
    }

    private int getValueSizeInBytes(Object obj) {
        if (obj == null) {
            return 0;
        }
        return this.mValueDescriptor.getSizeInBytes(obj);
    }
}
