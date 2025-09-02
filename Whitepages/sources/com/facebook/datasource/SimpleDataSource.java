package com.facebook.datasource;

import com.facebook.common.internal.Preconditions;

public class SimpleDataSource extends AbstractDataSource {
    private SimpleDataSource() {
    }

    public static SimpleDataSource create() {
        return new SimpleDataSource();
    }

    public boolean setFailure(Throwable th) {
        return super.setFailure((Throwable) Preconditions.checkNotNull(th));
    }
}
