package com.facebook.datasource;

import android.util.Pair;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public abstract class AbstractDataSource implements DataSource {
    private DataSourceStatus mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
    private Map mExtras;
    private Throwable mFailureThrowable = null;
    private boolean mIsClosed = false;
    private float mProgress = 0.0f;
    private Object mResult = null;
    private final ConcurrentLinkedQueue mSubscribers = new ConcurrentLinkedQueue();

    public interface DataSourceInstrumenter {
    }

    private enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    public static DataSourceInstrumenter getDataSourceInstrumenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void closeResult(Object obj) {
    }

    public boolean hasMultipleResults() {
        return false;
    }

    protected AbstractDataSource() {
    }

    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    public synchronized Object getResult() {
        return this.mResult;
    }

    public Map getExtras() {
        return this.mExtras;
    }

    /* access modifiers changed from: protected */
    public void setExtras(Map map) {
        this.mExtras = map;
    }

    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    public synchronized float getProgress() {
        return this.mProgress;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r1 == null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        closeResult(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        if (isFinished() != false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        notifyDataSubscribers();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.mSubscribers.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0027, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0028, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean close() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.mIsClosed     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x000a
            monitor-exit(r3)     // Catch:{ all -> 0x0008 }
            r0 = 0
            return r0
        L_0x0008:
            r0 = move-exception
            goto L_0x002c
        L_0x000a:
            r0 = 1
            r3.mIsClosed = r0     // Catch:{ all -> 0x0008 }
            java.lang.Object r1 = r3.mResult     // Catch:{ all -> 0x0008 }
            r2 = 0
            r3.mResult = r2     // Catch:{ all -> 0x0008 }
            monitor-exit(r3)     // Catch:{ all -> 0x0008 }
            if (r1 == 0) goto L_0x0018
            r3.closeResult(r1)
        L_0x0018:
            boolean r1 = r3.isFinished()
            if (r1 != 0) goto L_0x0021
            r3.notifyDataSubscribers()
        L_0x0021:
            monitor-enter(r3)
            java.util.concurrent.ConcurrentLinkedQueue r1 = r3.mSubscribers     // Catch:{ all -> 0x0029 }
            r1.clear()     // Catch:{ all -> 0x0029 }
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            return r0
        L_0x0029:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            monitor-exit(r3)     // Catch:{ all -> 0x0008 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.close():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0037, code lost:
        notifyDataSubscriber(r3, r4, hasFailed(), wasCancelled());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subscribe(com.facebook.datasource.DataSubscriber r3, java.util.concurrent.Executor r4) {
        /*
            r2 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r3)
            com.facebook.common.internal.Preconditions.checkNotNull(r4)
            monitor-enter(r2)
            boolean r0 = r2.mIsClosed     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x000f
            monitor-exit(r2)     // Catch:{ all -> 0x000d }
            return
        L_0x000d:
            r3 = move-exception
            goto L_0x0043
        L_0x000f:
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r2.mDataSourceStatus     // Catch:{ all -> 0x000d }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x000d }
            if (r0 != r1) goto L_0x001e
            java.util.concurrent.ConcurrentLinkedQueue r0 = r2.mSubscribers     // Catch:{ all -> 0x000d }
            android.util.Pair r1 = android.util.Pair.create(r3, r4)     // Catch:{ all -> 0x000d }
            r0.add(r1)     // Catch:{ all -> 0x000d }
        L_0x001e:
            boolean r0 = r2.hasResult()     // Catch:{ all -> 0x000d }
            if (r0 != 0) goto L_0x0033
            boolean r0 = r2.isFinished()     // Catch:{ all -> 0x000d }
            if (r0 != 0) goto L_0x0033
            boolean r0 = r2.wasCancelled()     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r0 = 0
            goto L_0x0034
        L_0x0033:
            r0 = 1
        L_0x0034:
            monitor-exit(r2)     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x0042
            boolean r0 = r2.hasFailed()
            boolean r1 = r2.wasCancelled()
            r2.notifyDataSubscriber(r3, r4, r0, r1)
        L_0x0042:
            return
        L_0x0043:
            monitor-exit(r2)     // Catch:{ all -> 0x000d }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.subscribe(com.facebook.datasource.DataSubscriber, java.util.concurrent.Executor):void");
    }

    private void notifyDataSubscribers() {
        boolean hasFailed = hasFailed();
        boolean wasCancelled = wasCancelled();
        Iterator it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            notifyDataSubscriber((DataSubscriber) pair.first, (Executor) pair.second, hasFailed, wasCancelled);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyDataSubscriber(final DataSubscriber dataSubscriber, Executor executor, final boolean z, final boolean z2) {
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                if (z) {
                    dataSubscriber.onFailure(AbstractDataSource.this);
                } else if (z2) {
                    dataSubscriber.onCancellation(AbstractDataSource.this);
                } else {
                    dataSubscriber.onNewResult(AbstractDataSource.this);
                }
            }
        };
        getDataSourceInstrumenter();
        executor.execute(r0);
    }

    private synchronized boolean wasCancelled() {
        return isClosed() && !isFinished();
    }

    /* access modifiers changed from: protected */
    public boolean setResult(Object obj, boolean z, Map map) {
        setExtras(map);
        boolean resultInternal = setResultInternal(obj, z);
        if (resultInternal) {
            notifyDataSubscribers();
        }
        return resultInternal;
    }

    /* access modifiers changed from: protected */
    public boolean setFailure(Throwable th) {
        return setFailure(th, (Map) null);
    }

    /* access modifiers changed from: protected */
    public boolean setFailure(Throwable th, Map map) {
        boolean failureInternal = setFailureInternal(th, map);
        if (failureInternal) {
            notifyDataSubscribers();
        }
        return failureInternal;
    }

    /* access modifiers changed from: protected */
    public boolean setProgress(float f) {
        boolean progressInternal = setProgressInternal(f);
        if (progressInternal) {
            notifyProgressUpdate();
        }
        return progressInternal;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0027, code lost:
        if (r4 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0029, code lost:
        closeResult(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return false;
     */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:27:0x0032=Splitter:B:27:0x0032, B:21:0x0026=Splitter:B:21:0x0026} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean setResultInternal(java.lang.Object r4, boolean r5) {
        /*
            r3 = this;
            r0 = 0
            monitor-enter(r3)     // Catch:{ all -> 0x003c }
            boolean r1 = r3.mIsClosed     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x0032
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = r3.mDataSourceStatus     // Catch:{ all -> 0x0018 }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x0018 }
            if (r1 == r2) goto L_0x000d
            goto L_0x0032
        L_0x000d:
            if (r5 == 0) goto L_0x001a
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r5 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.SUCCESS     // Catch:{ all -> 0x0018 }
            r3.mDataSourceStatus = r5     // Catch:{ all -> 0x0018 }
            r5 = 1065353216(0x3f800000, float:1.0)
            r3.mProgress = r5     // Catch:{ all -> 0x0018 }
            goto L_0x001a
        L_0x0018:
            r4 = move-exception
            goto L_0x003a
        L_0x001a:
            java.lang.Object r5 = r3.mResult     // Catch:{ all -> 0x0018 }
            if (r5 == r4) goto L_0x0025
            r3.mResult = r4     // Catch:{ all -> 0x0022 }
            r4 = r5
            goto L_0x0026
        L_0x0022:
            r4 = move-exception
            r0 = r5
            goto L_0x003a
        L_0x0025:
            r4 = r0
        L_0x0026:
            monitor-exit(r3)     // Catch:{ all -> 0x002e }
            if (r4 == 0) goto L_0x002c
            r3.closeResult(r4)
        L_0x002c:
            r4 = 1
            return r4
        L_0x002e:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x003a
        L_0x0032:
            monitor-exit(r3)     // Catch:{ all -> 0x002e }
            if (r4 == 0) goto L_0x0038
            r3.closeResult(r4)
        L_0x0038:
            r4 = 0
            return r4
        L_0x003a:
            monitor-exit(r3)     // Catch:{ all -> 0x0018 }
            throw r4     // Catch:{ all -> 0x003c }
        L_0x003c:
            r4 = move-exception
            if (r0 == 0) goto L_0x0042
            r3.closeResult(r0)
        L_0x0042:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setResultInternal(java.lang.Object, boolean):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean setFailureInternal(java.lang.Throwable r3, java.util.Map r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.mIsClosed     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x0019
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r2.mDataSourceStatus     // Catch:{ all -> 0x0017 }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x0017 }
            if (r0 == r1) goto L_0x000c
            goto L_0x0019
        L_0x000c:
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.FAILURE     // Catch:{ all -> 0x0017 }
            r2.mDataSourceStatus = r0     // Catch:{ all -> 0x0017 }
            r2.mFailureThrowable = r3     // Catch:{ all -> 0x0017 }
            r2.mExtras = r4     // Catch:{ all -> 0x0017 }
            monitor-exit(r2)
            r3 = 1
            return r3
        L_0x0017:
            r3 = move-exception
            goto L_0x001c
        L_0x0019:
            monitor-exit(r2)
            r3 = 0
            return r3
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setFailureInternal(java.lang.Throwable, java.util.Map):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean setProgressInternal(float r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.mIsClosed     // Catch:{ all -> 0x001a }
            r1 = 0
            if (r0 != 0) goto L_0x001c
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r3.mDataSourceStatus     // Catch:{ all -> 0x001a }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x001a }
            if (r0 == r2) goto L_0x000d
            goto L_0x001c
        L_0x000d:
            float r0 = r3.mProgress     // Catch:{ all -> 0x001a }
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0015
            monitor-exit(r3)
            return r1
        L_0x0015:
            r3.mProgress = r4     // Catch:{ all -> 0x001a }
            monitor-exit(r3)
            r4 = 1
            return r4
        L_0x001a:
            r4 = move-exception
            goto L_0x001e
        L_0x001c:
            monitor-exit(r3)
            return r1
        L_0x001e:
            monitor-exit(r3)     // Catch:{ all -> 0x001a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setProgressInternal(float):boolean");
    }

    /* access modifiers changed from: protected */
    public void notifyProgressUpdate() {
        Iterator it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            final DataSubscriber dataSubscriber = (DataSubscriber) pair.first;
            ((Executor) pair.second).execute(new Runnable() {
                public void run() {
                    dataSubscriber.onProgressUpdate(AbstractDataSource.this);
                }
            });
        }
    }
}
