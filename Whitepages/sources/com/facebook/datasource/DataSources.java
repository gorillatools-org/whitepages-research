package com.facebook.datasource;

import com.facebook.common.internal.Supplier;

public abstract class DataSources {
    public static DataSource immediateFailedDataSource(Throwable th) {
        SimpleDataSource create = SimpleDataSource.create();
        create.setFailure(th);
        return create;
    }

    public static Supplier getFailedDataSourceSupplier(final Throwable th) {
        return new Supplier() {
            public DataSource get() {
                return DataSources.immediateFailedDataSource(th);
            }
        };
    }
}
