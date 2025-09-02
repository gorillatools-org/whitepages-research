package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.util.LinkedList;
import java.util.Queue;

class Bucket {
    private final boolean mFixBucketsReinitialization;
    final Queue mFreeList;
    private int mInUseLength;
    public final int mItemSize;
    public final int mMaxLength;

    public Bucket(int i, int i2, int i3, boolean z) {
        boolean z2 = false;
        Preconditions.checkState(i > 0);
        Preconditions.checkState(i2 >= 0);
        Preconditions.checkState(i3 >= 0 ? true : z2);
        this.mItemSize = i;
        this.mMaxLength = i2;
        this.mFreeList = new LinkedList();
        this.mInUseLength = i3;
        this.mFixBucketsReinitialization = z;
    }

    public boolean isMaxLengthExceeded() {
        return this.mInUseLength + getFreeListSize() > this.mMaxLength;
    }

    /* access modifiers changed from: package-private */
    public int getFreeListSize() {
        return this.mFreeList.size();
    }

    public Object get() {
        Object pop = pop();
        if (pop != null) {
            this.mInUseLength++;
        }
        return pop;
    }

    public Object pop() {
        return this.mFreeList.poll();
    }

    public void incrementInUseCount() {
        this.mInUseLength++;
    }

    public void release(Object obj) {
        Preconditions.checkNotNull(obj);
        if (this.mFixBucketsReinitialization) {
            Preconditions.checkState(this.mInUseLength > 0);
            this.mInUseLength--;
            addToFreeList(obj);
            return;
        }
        int i = this.mInUseLength;
        if (i > 0) {
            this.mInUseLength = i - 1;
            addToFreeList(obj);
            return;
        }
        FLog.e("BUCKET", "Tried to release value %s from an empty bucket!", obj);
    }

    /* access modifiers changed from: package-private */
    public void addToFreeList(Object obj) {
        this.mFreeList.add(obj);
    }

    public void decrementInUseCount() {
        Preconditions.checkState(this.mInUseLength > 0);
        this.mInUseLength--;
    }
}
