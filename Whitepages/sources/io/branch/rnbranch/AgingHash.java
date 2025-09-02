package io.branch.rnbranch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AgingHash {
    private HashMap mHash = new HashMap();
    private long mTtlMillis;

    public AgingHash(long j) {
        this.mTtlMillis = j;
    }

    public void put(Object obj, Object obj2) {
        ageItems();
        this.mHash.put(obj, new AgingItem(obj2));
    }

    public Object get(Object obj) {
        AgingItem agingItem = (AgingItem) this.mHash.get(obj);
        if (agingItem == null) {
            return null;
        }
        return agingItem.get();
    }

    public void remove(Object obj) {
        this.mHash.remove(obj);
    }

    private void ageItems() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator it = this.mHash.entrySet().iterator();
        while (it.hasNext()) {
            if (currentTimeMillis - ((AgingItem) ((Map.Entry) it.next()).getValue()).getAccessTime() >= this.mTtlMillis) {
                it.remove();
            }
        }
    }
}
