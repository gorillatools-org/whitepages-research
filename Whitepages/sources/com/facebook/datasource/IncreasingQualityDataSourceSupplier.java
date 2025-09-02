package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class IncreasingQualityDataSourceSupplier implements Supplier {
    /* access modifiers changed from: private */
    public final boolean mDataSourceLazy;
    /* access modifiers changed from: private */
    public final List mDataSourceSuppliers;

    private IncreasingQualityDataSourceSupplier(List list, boolean z) {
        Preconditions.checkArgument(!list.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = list;
        this.mDataSourceLazy = z;
    }

    public static IncreasingQualityDataSourceSupplier create(List list, boolean z) {
        return new IncreasingQualityDataSourceSupplier(list, z);
    }

    public DataSource get() {
        return new IncreasingQualityDataSource();
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IncreasingQualityDataSourceSupplier)) {
            return false;
        }
        return Objects.equal(this.mDataSourceSuppliers, ((IncreasingQualityDataSourceSupplier) obj).mDataSourceSuppliers);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("list", (Object) this.mDataSourceSuppliers).toString();
    }

    private class IncreasingQualityDataSource extends AbstractDataSource {
        private ArrayList mDataSources;
        private Throwable mDelayedError;
        private Map mDelayedExtras;
        private AtomicInteger mFinishedDataSources;
        private int mIndexOfDataSourceWithResult;
        private int mNumberOfDataSources;

        public IncreasingQualityDataSource() {
            if (!IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
        }

        private void ensureDataSourceInitialized() {
            if (this.mFinishedDataSources == null) {
                synchronized (this) {
                    try {
                        if (this.mFinishedDataSources == null) {
                            int i = 0;
                            this.mFinishedDataSources = new AtomicInteger(0);
                            int size = IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.size();
                            this.mNumberOfDataSources = size;
                            this.mIndexOfDataSourceWithResult = size;
                            this.mDataSources = new ArrayList(size);
                            while (true) {
                                if (i >= size) {
                                    break;
                                }
                                DataSource dataSource = (DataSource) ((Supplier) IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.get(i)).get();
                                this.mDataSources.add(dataSource);
                                dataSource.subscribe(new InternalDataSubscriber(i), CallerThreadExecutor.getInstance());
                                if (dataSource.hasResult()) {
                                    break;
                                }
                                i++;
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        private synchronized DataSource getDataSource(int i) {
            ArrayList arrayList;
            arrayList = this.mDataSources;
            return (arrayList == null || i >= arrayList.size()) ? null : (DataSource) this.mDataSources.get(i);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.facebook.datasource.DataSource} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized com.facebook.datasource.DataSource getAndClearDataSource(int r3) {
            /*
                r2 = this;
                monitor-enter(r2)
                java.util.ArrayList r0 = r2.mDataSources     // Catch:{ all -> 0x0016 }
                r1 = 0
                if (r0 == 0) goto L_0x0018
                int r0 = r0.size()     // Catch:{ all -> 0x0016 }
                if (r3 >= r0) goto L_0x0018
                java.util.ArrayList r0 = r2.mDataSources     // Catch:{ all -> 0x0016 }
                java.lang.Object r3 = r0.set(r3, r1)     // Catch:{ all -> 0x0016 }
                r1 = r3
                com.facebook.datasource.DataSource r1 = (com.facebook.datasource.DataSource) r1     // Catch:{ all -> 0x0016 }
                goto L_0x0018
            L_0x0016:
                r3 = move-exception
                goto L_0x001a
            L_0x0018:
                monitor-exit(r2)
                return r1
            L_0x001a:
                monitor-exit(r2)     // Catch:{ all -> 0x0016 }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.getAndClearDataSource(int):com.facebook.datasource.DataSource");
        }

        private synchronized DataSource getDataSourceWithResult() {
            return getDataSource(this.mIndexOfDataSourceWithResult);
        }

        public synchronized Object getResult() {
            DataSource dataSourceWithResult;
            try {
                if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                    ensureDataSourceInitialized();
                }
                dataSourceWithResult = getDataSourceWithResult();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
            return dataSourceWithResult != null ? dataSourceWithResult.getResult() : null;
        }

        public synchronized boolean hasResult() {
            DataSource dataSourceWithResult;
            try {
                if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                    ensureDataSourceInitialized();
                }
                dataSourceWithResult = getDataSourceWithResult();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
            return dataSourceWithResult != null && dataSourceWithResult.hasResult();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
            if (r0 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
            if (r1 >= r0.size()) goto L_0x0031;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
            closeSafely((com.facebook.datasource.DataSource) r0.get(r1));
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            return true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean close() {
            /*
                r3 = this;
                com.facebook.datasource.IncreasingQualityDataSourceSupplier r0 = com.facebook.datasource.IncreasingQualityDataSourceSupplier.this
                boolean r0 = r0.mDataSourceLazy
                if (r0 == 0) goto L_0x000b
                r3.ensureDataSourceInitialized()
            L_0x000b:
                monitor-enter(r3)
                boolean r0 = super.close()     // Catch:{ all -> 0x0015 }
                r1 = 0
                if (r0 != 0) goto L_0x0017
                monitor-exit(r3)     // Catch:{ all -> 0x0015 }
                return r1
            L_0x0015:
                r0 = move-exception
                goto L_0x0033
            L_0x0017:
                java.util.ArrayList r0 = r3.mDataSources     // Catch:{ all -> 0x0015 }
                r2 = 0
                r3.mDataSources = r2     // Catch:{ all -> 0x0015 }
                monitor-exit(r3)     // Catch:{ all -> 0x0015 }
                if (r0 == 0) goto L_0x0031
            L_0x001f:
                int r2 = r0.size()
                if (r1 >= r2) goto L_0x0031
                java.lang.Object r2 = r0.get(r1)
                com.facebook.datasource.DataSource r2 = (com.facebook.datasource.DataSource) r2
                r3.closeSafely(r2)
                int r1 = r1 + 1
                goto L_0x001f
            L_0x0031:
                r0 = 1
                return r0
            L_0x0033:
                monitor-exit(r3)     // Catch:{ all -> 0x0015 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.close():boolean");
        }

        /* access modifiers changed from: private */
        public void onDataSourceNewResult(int i, DataSource dataSource) {
            maybeSetIndexOfDataSourceWithResult(i, dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                setResult((Object) null, i == 0 && dataSource.isFinished(), dataSource.getExtras());
            }
            maybeSetFailure();
        }

        /* access modifiers changed from: private */
        public void onDataSourceFailed(int i, DataSource dataSource) {
            closeSafely(tryGetAndClearDataSource(i, dataSource));
            if (i == 0) {
                this.mDelayedError = dataSource.getFailureCause();
                this.mDelayedExtras = dataSource.getExtras();
            }
            maybeSetFailure();
        }

        private void maybeSetFailure() {
            Throwable th;
            if (this.mFinishedDataSources.incrementAndGet() == this.mNumberOfDataSources && (th = this.mDelayedError) != null) {
                setFailure(th, this.mDelayedExtras);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
            if (r0 <= r3) goto L_0x002e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0024, code lost:
            closeSafely(getAndClearDataSource(r0));
            r0 = r0 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x002e, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void maybeSetIndexOfDataSourceWithResult(int r3, com.facebook.datasource.DataSource r4, boolean r5) {
            /*
                r2 = this;
                monitor-enter(r2)
                int r0 = r2.mIndexOfDataSourceWithResult     // Catch:{ all -> 0x001b }
                com.facebook.datasource.DataSource r1 = r2.getDataSource(r3)     // Catch:{ all -> 0x001b }
                if (r4 != r1) goto L_0x002f
                int r4 = r2.mIndexOfDataSourceWithResult     // Catch:{ all -> 0x001b }
                if (r3 != r4) goto L_0x000e
                goto L_0x002f
            L_0x000e:
                com.facebook.datasource.DataSource r4 = r2.getDataSourceWithResult()     // Catch:{ all -> 0x001b }
                if (r4 == 0) goto L_0x001f
                if (r5 == 0) goto L_0x001d
                int r4 = r2.mIndexOfDataSourceWithResult     // Catch:{ all -> 0x001b }
                if (r3 >= r4) goto L_0x001d
                goto L_0x001f
            L_0x001b:
                r3 = move-exception
                goto L_0x0031
            L_0x001d:
                r3 = r0
                goto L_0x0021
            L_0x001f:
                r2.mIndexOfDataSourceWithResult = r3     // Catch:{ all -> 0x001b }
            L_0x0021:
                monitor-exit(r2)     // Catch:{ all -> 0x001b }
            L_0x0022:
                if (r0 <= r3) goto L_0x002e
                com.facebook.datasource.DataSource r4 = r2.getAndClearDataSource(r0)
                r2.closeSafely(r4)
                int r0 = r0 + -1
                goto L_0x0022
            L_0x002e:
                return
            L_0x002f:
                monitor-exit(r2)     // Catch:{ all -> 0x001b }
                return
            L_0x0031:
                monitor-exit(r2)     // Catch:{ all -> 0x001b }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.maybeSetIndexOfDataSourceWithResult(int, com.facebook.datasource.DataSource, boolean):void");
        }

        private synchronized DataSource tryGetAndClearDataSource(int i, DataSource dataSource) {
            if (dataSource == getDataSourceWithResult()) {
                return null;
            }
            if (dataSource != getDataSource(i)) {
                return dataSource;
            }
            return getAndClearDataSource(i);
        }

        private void closeSafely(DataSource dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        private class InternalDataSubscriber implements DataSubscriber {
            private int mIndex;

            public void onCancellation(DataSource dataSource) {
            }

            public InternalDataSubscriber(int i) {
                this.mIndex = i;
            }

            public void onNewResult(DataSource dataSource) {
                if (dataSource.hasResult()) {
                    IncreasingQualityDataSource.this.onDataSourceNewResult(this.mIndex, dataSource);
                } else if (dataSource.isFinished()) {
                    IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
                }
            }

            public void onFailure(DataSource dataSource) {
                IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
            }

            public void onProgressUpdate(DataSource dataSource) {
                if (this.mIndex == 0) {
                    IncreasingQualityDataSource.this.setProgress(dataSource.getProgress());
                }
            }
        }
    }
}
