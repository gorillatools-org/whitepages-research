package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.util.List;

public class FirstAvailableDataSourceSupplier implements Supplier {
    /* access modifiers changed from: private */
    public final List mDataSourceSuppliers;

    private FirstAvailableDataSourceSupplier(List list) {
        Preconditions.checkArgument(!list.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = list;
    }

    public static FirstAvailableDataSourceSupplier create(List list) {
        return new FirstAvailableDataSourceSupplier(list);
    }

    public DataSource get() {
        return new FirstAvailableDataSource();
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirstAvailableDataSourceSupplier)) {
            return false;
        }
        return Objects.equal(this.mDataSourceSuppliers, ((FirstAvailableDataSourceSupplier) obj).mDataSourceSuppliers);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("list", (Object) this.mDataSourceSuppliers).toString();
    }

    private class FirstAvailableDataSource extends AbstractDataSource {
        private DataSource mCurrentDataSource = null;
        private DataSource mDataSourceWithResult = null;
        private int mIndex = 0;

        public FirstAvailableDataSource() {
            if (!startNextDataSource()) {
                setFailure(new RuntimeException("No data source supplier or supplier returned null."));
            }
        }

        public synchronized Object getResult() {
            DataSource dataSourceWithResult;
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null ? dataSourceWithResult.getResult() : null;
        }

        public synchronized boolean hasResult() {
            DataSource dataSourceWithResult;
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null && dataSourceWithResult.hasResult();
        }

        public boolean close() {
            synchronized (this) {
                try {
                    if (!super.close()) {
                        return false;
                    }
                    DataSource dataSource = this.mCurrentDataSource;
                    this.mCurrentDataSource = null;
                    DataSource dataSource2 = this.mDataSourceWithResult;
                    this.mDataSourceWithResult = null;
                    closeSafely(dataSource2);
                    closeSafely(dataSource);
                    return true;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }

        private boolean startNextDataSource() {
            Supplier nextSupplier = getNextSupplier();
            DataSource dataSource = nextSupplier != null ? (DataSource) nextSupplier.get() : null;
            if (!setCurrentDataSource(dataSource) || dataSource == null) {
                closeSafely(dataSource);
                return false;
            }
            dataSource.subscribe(new InternalDataSubscriber(), CallerThreadExecutor.getInstance());
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized com.facebook.common.internal.Supplier getNextSupplier() {
            /*
                r3 = this;
                monitor-enter(r3)
                boolean r0 = r3.isClosed()     // Catch:{ all -> 0x0029 }
                if (r0 != 0) goto L_0x002b
                int r0 = r3.mIndex     // Catch:{ all -> 0x0029 }
                com.facebook.datasource.FirstAvailableDataSourceSupplier r1 = com.facebook.datasource.FirstAvailableDataSourceSupplier.this     // Catch:{ all -> 0x0029 }
                java.util.List r1 = r1.mDataSourceSuppliers     // Catch:{ all -> 0x0029 }
                int r1 = r1.size()     // Catch:{ all -> 0x0029 }
                if (r0 >= r1) goto L_0x002b
                com.facebook.datasource.FirstAvailableDataSourceSupplier r0 = com.facebook.datasource.FirstAvailableDataSourceSupplier.this     // Catch:{ all -> 0x0029 }
                java.util.List r0 = r0.mDataSourceSuppliers     // Catch:{ all -> 0x0029 }
                int r1 = r3.mIndex     // Catch:{ all -> 0x0029 }
                int r2 = r1 + 1
                r3.mIndex = r2     // Catch:{ all -> 0x0029 }
                java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0029 }
                com.facebook.common.internal.Supplier r0 = (com.facebook.common.internal.Supplier) r0     // Catch:{ all -> 0x0029 }
                monitor-exit(r3)
                return r0
            L_0x0029:
                r0 = move-exception
                goto L_0x002e
            L_0x002b:
                monitor-exit(r3)
                r0 = 0
                return r0
            L_0x002e:
                monitor-exit(r3)     // Catch:{ all -> 0x0029 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.FirstAvailableDataSourceSupplier.FirstAvailableDataSource.getNextSupplier():com.facebook.common.internal.Supplier");
        }

        private synchronized boolean setCurrentDataSource(DataSource dataSource) {
            if (isClosed()) {
                return false;
            }
            this.mCurrentDataSource = dataSource;
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized boolean clearCurrentDataSource(com.facebook.datasource.DataSource r2) {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.isClosed()     // Catch:{ all -> 0x0012 }
                if (r0 != 0) goto L_0x0014
                com.facebook.datasource.DataSource r0 = r1.mCurrentDataSource     // Catch:{ all -> 0x0012 }
                if (r2 == r0) goto L_0x000c
                goto L_0x0014
            L_0x000c:
                r2 = 0
                r1.mCurrentDataSource = r2     // Catch:{ all -> 0x0012 }
                monitor-exit(r1)
                r2 = 1
                return r2
            L_0x0012:
                r2 = move-exception
                goto L_0x0017
            L_0x0014:
                monitor-exit(r1)
                r2 = 0
                return r2
            L_0x0017:
                monitor-exit(r1)     // Catch:{ all -> 0x0012 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.FirstAvailableDataSourceSupplier.FirstAvailableDataSource.clearCurrentDataSource(com.facebook.datasource.DataSource):boolean");
        }

        private synchronized DataSource getDataSourceWithResult() {
            return this.mDataSourceWithResult;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0014, code lost:
            closeSafely(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0017, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void maybeSetDataSourceWithResult(com.facebook.datasource.DataSource r2, boolean r3) {
            /*
                r1 = this;
                monitor-enter(r1)
                com.facebook.datasource.DataSource r0 = r1.mCurrentDataSource     // Catch:{ all -> 0x0018 }
                if (r2 != r0) goto L_0x001a
                com.facebook.datasource.DataSource r0 = r1.mDataSourceWithResult     // Catch:{ all -> 0x0018 }
                if (r2 != r0) goto L_0x000a
                goto L_0x001a
            L_0x000a:
                if (r0 == 0) goto L_0x0011
                if (r3 == 0) goto L_0x000f
                goto L_0x0011
            L_0x000f:
                r0 = 0
                goto L_0x0013
            L_0x0011:
                r1.mDataSourceWithResult = r2     // Catch:{ all -> 0x0018 }
            L_0x0013:
                monitor-exit(r1)     // Catch:{ all -> 0x0018 }
                r1.closeSafely(r0)
                return
            L_0x0018:
                r2 = move-exception
                goto L_0x001c
            L_0x001a:
                monitor-exit(r1)     // Catch:{ all -> 0x0018 }
                return
            L_0x001c:
                monitor-exit(r1)     // Catch:{ all -> 0x0018 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.FirstAvailableDataSourceSupplier.FirstAvailableDataSource.maybeSetDataSourceWithResult(com.facebook.datasource.DataSource, boolean):void");
        }

        /* access modifiers changed from: private */
        public void onDataSourceFailed(DataSource dataSource) {
            if (clearCurrentDataSource(dataSource)) {
                if (dataSource != getDataSourceWithResult()) {
                    closeSafely(dataSource);
                }
                if (!startNextDataSource()) {
                    setFailure(dataSource.getFailureCause(), dataSource.getExtras());
                }
            }
        }

        /* access modifiers changed from: private */
        public void onDataSourceNewResult(DataSource dataSource) {
            maybeSetDataSourceWithResult(dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                setResult((Object) null, dataSource.isFinished(), dataSource.getExtras());
            }
        }

        private void closeSafely(DataSource dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        private class InternalDataSubscriber implements DataSubscriber {
            public void onCancellation(DataSource dataSource) {
            }

            private InternalDataSubscriber() {
            }

            public void onFailure(DataSource dataSource) {
                FirstAvailableDataSource.this.onDataSourceFailed(dataSource);
            }

            public void onNewResult(DataSource dataSource) {
                if (dataSource.hasResult()) {
                    FirstAvailableDataSource.this.onDataSourceNewResult(dataSource);
                } else if (dataSource.isFinished()) {
                    FirstAvailableDataSource.this.onDataSourceFailed(dataSource);
                }
            }

            public void onProgressUpdate(DataSource dataSource) {
                FirstAvailableDataSource.this.setProgress(Math.max(FirstAvailableDataSource.this.getProgress(), dataSource.getProgress()));
            }
        }
    }
}
