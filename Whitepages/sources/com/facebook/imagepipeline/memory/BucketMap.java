package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import java.util.LinkedList;

public class BucketMap {
    LinkedEntry mHead;
    protected final SparseArray mMap = new SparseArray();
    LinkedEntry mTail;

    static class LinkedEntry {
        int key;
        LinkedEntry next;
        LinkedEntry prev;
        LinkedList value;

        private LinkedEntry(LinkedEntry linkedEntry, int i, LinkedList linkedList, LinkedEntry linkedEntry2) {
            this.prev = linkedEntry;
            this.key = i;
            this.value = linkedList;
            this.next = linkedEntry2;
        }

        public String toString() {
            return "LinkedEntry(key: " + this.key + ")";
        }
    }

    public synchronized Object acquire(int i) {
        LinkedEntry linkedEntry = (LinkedEntry) this.mMap.get(i);
        if (linkedEntry == null) {
            return null;
        }
        Object pollFirst = linkedEntry.value.pollFirst();
        moveToFront(linkedEntry);
        return pollFirst;
    }

    public synchronized void release(int i, Object obj) {
        try {
            LinkedEntry linkedEntry = (LinkedEntry) this.mMap.get(i);
            if (linkedEntry == null) {
                linkedEntry = new LinkedEntry((LinkedEntry) null, i, new LinkedList(), (LinkedEntry) null);
                this.mMap.put(i, linkedEntry);
            }
            linkedEntry.value.addLast(obj);
            moveToFront(linkedEntry);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private synchronized void prune(LinkedEntry linkedEntry) {
        try {
            LinkedEntry linkedEntry2 = linkedEntry.prev;
            LinkedEntry linkedEntry3 = linkedEntry.next;
            if (linkedEntry2 != null) {
                linkedEntry2.next = linkedEntry3;
            }
            if (linkedEntry3 != null) {
                linkedEntry3.prev = linkedEntry2;
            }
            linkedEntry.prev = null;
            linkedEntry.next = null;
            if (linkedEntry == this.mHead) {
                this.mHead = linkedEntry3;
            }
            if (linkedEntry == this.mTail) {
                this.mTail = linkedEntry2;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private void moveToFront(LinkedEntry linkedEntry) {
        if (this.mHead != linkedEntry) {
            prune(linkedEntry);
            LinkedEntry linkedEntry2 = this.mHead;
            if (linkedEntry2 == null) {
                this.mHead = linkedEntry;
                this.mTail = linkedEntry;
                return;
            }
            linkedEntry.next = linkedEntry2;
            linkedEntry2.prev = linkedEntry;
            this.mHead = linkedEntry;
        }
    }

    public synchronized Object removeFromEnd() {
        LinkedEntry linkedEntry = this.mTail;
        if (linkedEntry == null) {
            return null;
        }
        Object pollLast = linkedEntry.value.pollLast();
        maybePrune(linkedEntry);
        return pollLast;
    }

    private void maybePrune(LinkedEntry linkedEntry) {
        if (linkedEntry != null && linkedEntry.value.isEmpty()) {
            prune(linkedEntry);
            this.mMap.remove(linkedEntry.key);
        }
    }
}
