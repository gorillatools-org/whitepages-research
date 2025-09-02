package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.Set;

public abstract class BasePool implements Pool {
    private final Class TAG;
    private boolean mAllowNewBuckets;
    final SparseArray mBuckets;
    final Counter mFree;
    private boolean mIgnoreHardCap;
    final Set mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    final Counter mUsed;

    /* access modifiers changed from: protected */
    public abstract Object alloc(int i);

    /* access modifiers changed from: protected */
    public abstract void free(Object obj);

    /* access modifiers changed from: protected */
    public abstract int getBucketedSize(int i);

    /* access modifiers changed from: protected */
    public abstract int getBucketedSizeForValue(Object obj);

    /* access modifiers changed from: protected */
    public abstract int getSizeInBytes(int i);

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.TAG = getClass();
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        PoolParams poolParams2 = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolParams = poolParams2;
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray();
        if (poolParams2.fixBucketsReinitialization) {
            initBuckets();
        } else {
            legacyInitBuckets(new SparseIntArray(0));
        }
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker, boolean z) {
        this(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        this.mIgnoreHardCap = z;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    /* access modifiers changed from: protected */
    public synchronized Object getValue(Bucket bucket) {
        return bucket.get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0056, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0 = alloc(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0071, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0072, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r4.mUsed.decrement(r2);
        r3 = getBucket(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007c, code lost:
        if (r3 != null) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007e, code lost:
        r3.decrementInUseCount();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0082, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0085, code lost:
        com.facebook.common.internal.Throwables.propagateIfPossible(r0);
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0089, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        com.facebook.common.internal.Preconditions.checkState(r4.mInUseValues.add(r0));
        trimToSoftCap();
        r4.mPoolStatsTracker.onAlloc(r2);
        logStats();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a2, code lost:
        if (com.facebook.common.logging.FLog.isLoggable(2) == false) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a4, code lost:
        com.facebook.common.logging.FLog.v(r4.TAG, "get (alloc) (object, size) = (%x, %s)", java.lang.Integer.valueOf(java.lang.System.identityHashCode(r0)), java.lang.Integer.valueOf(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b8, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00bb, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bd, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bf, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object get(int r5) {
        /*
            r4 = this;
            r4.ensurePoolSizeInvariant()
            int r5 = r4.getBucketedSize(r5)
            monitor-enter(r4)
            com.facebook.imagepipeline.memory.Bucket r0 = r4.getBucket(r5)     // Catch:{ all -> 0x0052 }
            r1 = 2
            if (r0 == 0) goto L_0x0057
            java.lang.Object r2 = r4.getValue(r0)     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x0057
            java.util.Set r5 = r4.mInUseValues     // Catch:{ all -> 0x0052 }
            boolean r5 = r5.add(r2)     // Catch:{ all -> 0x0052 }
            com.facebook.common.internal.Preconditions.checkState(r5)     // Catch:{ all -> 0x0052 }
            int r5 = r4.getBucketedSizeForValue(r2)     // Catch:{ all -> 0x0052 }
            int r0 = r4.getSizeInBytes(r5)     // Catch:{ all -> 0x0052 }
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r4.mUsed     // Catch:{ all -> 0x0052 }
            r3.increment(r0)     // Catch:{ all -> 0x0052 }
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r4.mFree     // Catch:{ all -> 0x0052 }
            r3.decrement(r0)     // Catch:{ all -> 0x0052 }
            com.facebook.imagepipeline.memory.PoolStatsTracker r3 = r4.mPoolStatsTracker     // Catch:{ all -> 0x0052 }
            r3.onValueReuse(r0)     // Catch:{ all -> 0x0052 }
            r4.logStats()     // Catch:{ all -> 0x0052 }
            boolean r0 = com.facebook.common.logging.FLog.isLoggable(r1)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0055
            java.lang.Class r0 = r4.TAG     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = "get (reuse) (object, size) = (%x, %s)"
            int r3 = java.lang.System.identityHashCode(r2)     // Catch:{ all -> 0x0052 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0052 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0052 }
            com.facebook.common.logging.FLog.v(r0, r1, r3, r5)     // Catch:{ all -> 0x0052 }
            goto L_0x0055
        L_0x0052:
            r5 = move-exception
            goto L_0x00d2
        L_0x0055:
            monitor-exit(r4)     // Catch:{ all -> 0x0052 }
            return r2
        L_0x0057:
            int r2 = r4.getSizeInBytes(r5)     // Catch:{ all -> 0x0052 }
            boolean r3 = r4.canAllocate(r2)     // Catch:{ all -> 0x0052 }
            if (r3 == 0) goto L_0x00c0
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r4.mUsed     // Catch:{ all -> 0x0052 }
            r3.increment(r2)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x006b
            r0.incrementInUseCount()     // Catch:{ all -> 0x0052 }
        L_0x006b:
            monitor-exit(r4)     // Catch:{ all -> 0x0052 }
            java.lang.Object r0 = r4.alloc(r5)     // Catch:{ all -> 0x0071 }
            goto L_0x0089
        L_0x0071:
            r0 = move-exception
            monitor-enter(r4)
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r4.mUsed     // Catch:{ all -> 0x0082 }
            r3.decrement(r2)     // Catch:{ all -> 0x0082 }
            com.facebook.imagepipeline.memory.Bucket r3 = r4.getBucket(r5)     // Catch:{ all -> 0x0082 }
            if (r3 == 0) goto L_0x0084
            r3.decrementInUseCount()     // Catch:{ all -> 0x0082 }
            goto L_0x0084
        L_0x0082:
            r5 = move-exception
            goto L_0x00be
        L_0x0084:
            monitor-exit(r4)     // Catch:{ all -> 0x0082 }
            com.facebook.common.internal.Throwables.propagateIfPossible(r0)
            r0 = 0
        L_0x0089:
            monitor-enter(r4)
            java.util.Set r3 = r4.mInUseValues     // Catch:{ all -> 0x00b8 }
            boolean r3 = r3.add(r0)     // Catch:{ all -> 0x00b8 }
            com.facebook.common.internal.Preconditions.checkState(r3)     // Catch:{ all -> 0x00b8 }
            r4.trimToSoftCap()     // Catch:{ all -> 0x00b8 }
            com.facebook.imagepipeline.memory.PoolStatsTracker r3 = r4.mPoolStatsTracker     // Catch:{ all -> 0x00b8 }
            r3.onAlloc(r2)     // Catch:{ all -> 0x00b8 }
            r4.logStats()     // Catch:{ all -> 0x00b8 }
            boolean r1 = com.facebook.common.logging.FLog.isLoggable(r1)     // Catch:{ all -> 0x00b8 }
            if (r1 == 0) goto L_0x00ba
            java.lang.Class r1 = r4.TAG     // Catch:{ all -> 0x00b8 }
            java.lang.String r2 = "get (alloc) (object, size) = (%x, %s)"
            int r3 = java.lang.System.identityHashCode(r0)     // Catch:{ all -> 0x00b8 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00b8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00b8 }
            com.facebook.common.logging.FLog.v(r1, r2, r3, r5)     // Catch:{ all -> 0x00b8 }
            goto L_0x00ba
        L_0x00b8:
            r5 = move-exception
            goto L_0x00bc
        L_0x00ba:
            monitor-exit(r4)     // Catch:{ all -> 0x00b8 }
            return r0
        L_0x00bc:
            monitor-exit(r4)     // Catch:{ all -> 0x00b8 }
            throw r5
        L_0x00be:
            monitor-exit(r4)     // Catch:{ all -> 0x0082 }
            throw r5
        L_0x00c0:
            com.facebook.imagepipeline.memory.BasePool$PoolSizeViolationException r5 = new com.facebook.imagepipeline.memory.BasePool$PoolSizeViolationException     // Catch:{ all -> 0x0052 }
            com.facebook.imagepipeline.memory.PoolParams r0 = r4.mPoolParams     // Catch:{ all -> 0x0052 }
            int r0 = r0.maxSizeHardCap     // Catch:{ all -> 0x0052 }
            com.facebook.imagepipeline.memory.BasePool$Counter r1 = r4.mUsed     // Catch:{ all -> 0x0052 }
            int r1 = r1.mNumBytes     // Catch:{ all -> 0x0052 }
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r4.mFree     // Catch:{ all -> 0x0052 }
            int r3 = r3.mNumBytes     // Catch:{ all -> 0x0052 }
            r5.<init>(r0, r1, r3, r2)     // Catch:{ all -> 0x0052 }
            throw r5     // Catch:{ all -> 0x0052 }
        L_0x00d2:
            monitor-exit(r4)     // Catch:{ all -> 0x0052 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.get(int):java.lang.Object");
    }

    public void release(Object obj) {
        Preconditions.checkNotNull(obj);
        int bucketedSizeForValue = getBucketedSizeForValue(obj);
        int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
        synchronized (this) {
            try {
                Bucket bucketIfPresent = getBucketIfPresent(bucketedSizeForValue);
                if (!this.mInUseValues.remove(obj)) {
                    FLog.e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(obj)), Integer.valueOf(bucketedSizeForValue));
                    free(obj);
                    this.mPoolStatsTracker.onFree(sizeInBytes);
                } else {
                    if (bucketIfPresent != null && !bucketIfPresent.isMaxLengthExceeded() && !isMaxSizeSoftCapExceeded()) {
                        if (isReusable(obj)) {
                            bucketIfPresent.release(obj);
                            this.mFree.increment(sizeInBytes);
                            this.mUsed.decrement(sizeInBytes);
                            this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                            if (FLog.isLoggable(2)) {
                                FLog.v(this.TAG, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(obj)), Integer.valueOf(bucketedSizeForValue));
                            }
                        }
                    }
                    if (bucketIfPresent != null) {
                        bucketIfPresent.decrementInUseCount();
                    }
                    if (FLog.isLoggable(2)) {
                        FLog.v(this.TAG, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(obj)), Integer.valueOf(bucketedSizeForValue));
                    }
                    free(obj);
                    this.mUsed.decrement(sizeInBytes);
                    this.mPoolStatsTracker.onFree(sizeInBytes);
                }
                logStats();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isReusable(Object obj) {
        Preconditions.checkNotNull(obj);
        return true;
    }

    private synchronized void ensurePoolSizeInvariant() {
        boolean z;
        try {
            if (isMaxSizeSoftCapExceeded()) {
                if (this.mFree.mNumBytes != 0) {
                    z = false;
                    Preconditions.checkState(z);
                }
            }
            z = true;
            Preconditions.checkState(z);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private synchronized void legacyInitBuckets(SparseIntArray sparseIntArray) {
        try {
            Preconditions.checkNotNull(sparseIntArray);
            this.mBuckets.clear();
            SparseIntArray sparseIntArray2 = this.mPoolParams.bucketSizes;
            if (sparseIntArray2 != null) {
                for (int i = 0; i < sparseIntArray2.size(); i++) {
                    int keyAt = sparseIntArray2.keyAt(i);
                    this.mBuckets.put(keyAt, new Bucket(getSizeInBytes(keyAt), sparseIntArray2.valueAt(i), sparseIntArray.get(keyAt, 0), this.mPoolParams.fixBucketsReinitialization));
                }
                this.mAllowNewBuckets = false;
            } else {
                this.mAllowNewBuckets = true;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private synchronized void initBuckets() {
        try {
            SparseIntArray sparseIntArray = this.mPoolParams.bucketSizes;
            if (sparseIntArray != null) {
                fillBuckets(sparseIntArray);
                this.mAllowNewBuckets = false;
            } else {
                this.mAllowNewBuckets = true;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private void fillBuckets(SparseIntArray sparseIntArray) {
        this.mBuckets.clear();
        for (int i = 0; i < sparseIntArray.size(); i++) {
            int keyAt = sparseIntArray.keyAt(i);
            this.mBuckets.put(keyAt, new Bucket(getSizeInBytes(keyAt), sparseIntArray.valueAt(i), 0, this.mPoolParams.fixBucketsReinitialization));
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0090, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void trimToSize(int r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.facebook.imagepipeline.memory.BasePool$Counter r0 = r7.mUsed     // Catch:{ all -> 0x0037 }
            int r0 = r0.mNumBytes     // Catch:{ all -> 0x0037 }
            com.facebook.imagepipeline.memory.BasePool$Counter r1 = r7.mFree     // Catch:{ all -> 0x0037 }
            int r1 = r1.mNumBytes     // Catch:{ all -> 0x0037 }
            int r0 = r0 + r1
            int r0 = r0 - r8
            int r0 = java.lang.Math.min(r0, r1)     // Catch:{ all -> 0x0037 }
            if (r0 > 0) goto L_0x0013
            monitor-exit(r7)
            return
        L_0x0013:
            r1 = 2
            boolean r2 = com.facebook.common.logging.FLog.isLoggable(r1)     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0039
            java.lang.Class r2 = r7.TAG     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0037 }
            com.facebook.imagepipeline.memory.BasePool$Counter r5 = r7.mUsed     // Catch:{ all -> 0x0037 }
            int r5 = r5.mNumBytes     // Catch:{ all -> 0x0037 }
            com.facebook.imagepipeline.memory.BasePool$Counter r6 = r7.mFree     // Catch:{ all -> 0x0037 }
            int r6 = r6.mNumBytes     // Catch:{ all -> 0x0037 }
            int r5 = r5 + r6
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0037 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0037 }
            com.facebook.common.logging.FLog.v(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0037 }
            goto L_0x0039
        L_0x0037:
            r8 = move-exception
            goto L_0x0091
        L_0x0039:
            r7.logStats()     // Catch:{ all -> 0x0037 }
            r2 = 0
        L_0x003d:
            android.util.SparseArray r3 = r7.mBuckets     // Catch:{ all -> 0x0037 }
            int r3 = r3.size()     // Catch:{ all -> 0x0037 }
            if (r2 >= r3) goto L_0x006e
            if (r0 > 0) goto L_0x0048
            goto L_0x006e
        L_0x0048:
            android.util.SparseArray r3 = r7.mBuckets     // Catch:{ all -> 0x0037 }
            java.lang.Object r3 = r3.valueAt(r2)     // Catch:{ all -> 0x0037 }
            com.facebook.imagepipeline.memory.Bucket r3 = (com.facebook.imagepipeline.memory.Bucket) r3     // Catch:{ all -> 0x0037 }
            java.lang.Object r3 = com.facebook.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x0037 }
            com.facebook.imagepipeline.memory.Bucket r3 = (com.facebook.imagepipeline.memory.Bucket) r3     // Catch:{ all -> 0x0037 }
        L_0x0056:
            if (r0 <= 0) goto L_0x006b
            java.lang.Object r4 = r3.pop()     // Catch:{ all -> 0x0037 }
            if (r4 != 0) goto L_0x005f
            goto L_0x006b
        L_0x005f:
            r7.free(r4)     // Catch:{ all -> 0x0037 }
            int r4 = r3.mItemSize     // Catch:{ all -> 0x0037 }
            int r0 = r0 - r4
            com.facebook.imagepipeline.memory.BasePool$Counter r5 = r7.mFree     // Catch:{ all -> 0x0037 }
            r5.decrement(r4)     // Catch:{ all -> 0x0037 }
            goto L_0x0056
        L_0x006b:
            int r2 = r2 + 1
            goto L_0x003d
        L_0x006e:
            r7.logStats()     // Catch:{ all -> 0x0037 }
            boolean r0 = com.facebook.common.logging.FLog.isLoggable(r1)     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x008f
            java.lang.Class r0 = r7.TAG     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = "trimToSize: TargetSize = %d; Final Size = %d"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0037 }
            com.facebook.imagepipeline.memory.BasePool$Counter r2 = r7.mUsed     // Catch:{ all -> 0x0037 }
            int r2 = r2.mNumBytes     // Catch:{ all -> 0x0037 }
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r7.mFree     // Catch:{ all -> 0x0037 }
            int r3 = r3.mNumBytes     // Catch:{ all -> 0x0037 }
            int r2 = r2 + r3
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0037 }
            com.facebook.common.logging.FLog.v(r0, r1, r8, r2)     // Catch:{ all -> 0x0037 }
        L_0x008f:
            monitor-exit(r7)
            return
        L_0x0091:
            monitor-exit(r7)     // Catch:{ all -> 0x0037 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.trimToSize(int):void");
    }

    private synchronized Bucket getBucketIfPresent(int i) {
        return (Bucket) this.mBuckets.get(i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.facebook.imagepipeline.memory.Bucket getBucket(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            android.util.SparseArray r0 = r3.mBuckets     // Catch:{ all -> 0x0023 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0023 }
            com.facebook.imagepipeline.memory.Bucket r0 = (com.facebook.imagepipeline.memory.Bucket) r0     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0030
            boolean r1 = r3.mAllowNewBuckets     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0010
            goto L_0x0030
        L_0x0010:
            r0 = 2
            boolean r0 = com.facebook.common.logging.FLog.isLoggable(r0)     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0025
            java.lang.Class r0 = r3.TAG     // Catch:{ all -> 0x0023 }
            java.lang.String r1 = "creating new bucket %s"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0023 }
            com.facebook.common.logging.FLog.v((java.lang.Class) r0, (java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0023 }
            goto L_0x0025
        L_0x0023:
            r4 = move-exception
            goto L_0x0032
        L_0x0025:
            com.facebook.imagepipeline.memory.Bucket r0 = r3.newBucket(r4)     // Catch:{ all -> 0x0023 }
            android.util.SparseArray r1 = r3.mBuckets     // Catch:{ all -> 0x0023 }
            r1.put(r4, r0)     // Catch:{ all -> 0x0023 }
            monitor-exit(r3)
            return r0
        L_0x0030:
            monitor-exit(r3)
            return r0
        L_0x0032:
            monitor-exit(r3)     // Catch:{ all -> 0x0023 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.getBucket(int):com.facebook.imagepipeline.memory.Bucket");
    }

    /* access modifiers changed from: package-private */
    public Bucket newBucket(int i) {
        return new Bucket(getSizeInBytes(i), Integer.MAX_VALUE, 0, this.mPoolParams.fixBucketsReinitialization);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean canAllocate(int i) {
        if (this.mIgnoreHardCap) {
            return true;
        }
        PoolParams poolParams = this.mPoolParams;
        int i2 = poolParams.maxSizeHardCap;
        int i3 = this.mUsed.mNumBytes;
        if (i > i2 - i3) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        int i4 = poolParams.maxSizeSoftCap;
        if (i > i4 - (i3 + this.mFree.mNumBytes)) {
            trimToSize(i4 - i);
        }
        if (i <= i2 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            return true;
        }
        this.mPoolStatsTracker.onHardCapReached();
        return false;
    }

    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    static class Counter {
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void increment(int i) {
            this.mCount++;
            this.mNumBytes += i;
        }

        public void decrement(int i) {
            int i2;
            int i3 = this.mNumBytes;
            if (i3 < i || (i2 = this.mCount) <= 0) {
                FLog.wtf("com.facebook.imagepipeline.memory.BasePool.Counter", "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
                return;
            }
            this.mCount = i2 - 1;
            this.mNumBytes = i3 - i;
        }
    }

    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object obj) {
            super("Invalid size: " + obj.toString());
        }
    }

    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int i, int i2, int i3, int i4) {
            super("Pool hard cap violation? Hard cap = " + i + " Used size = " + i2 + " Free size = " + i3 + " Request size = " + i4);
        }
    }
}
