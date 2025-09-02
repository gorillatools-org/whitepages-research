package io.branch.rnbranch;

public class AgingItem {
    private long mAccessTime = System.currentTimeMillis();
    private Object mItem;

    public AgingItem(Object obj) {
        this.mItem = obj;
    }

    public Object get() {
        this.mAccessTime = System.currentTimeMillis();
        return this.mItem;
    }

    public long getAccessTime() {
        return this.mAccessTime;
    }
}
