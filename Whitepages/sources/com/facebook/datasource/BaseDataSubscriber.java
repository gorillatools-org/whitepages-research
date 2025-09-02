package com.facebook.datasource;

public abstract class BaseDataSubscriber implements DataSubscriber {
    public void onCancellation(DataSource dataSource) {
    }

    /* access modifiers changed from: protected */
    public abstract void onFailureImpl(DataSource dataSource);

    /* access modifiers changed from: protected */
    public abstract void onNewResultImpl(DataSource dataSource);

    public void onProgressUpdate(DataSource dataSource) {
    }

    public void onNewResult(DataSource dataSource) {
        boolean isFinished = dataSource.isFinished();
        try {
            onNewResultImpl(dataSource);
        } finally {
            if (isFinished) {
                dataSource.close();
            }
        }
    }

    public void onFailure(DataSource dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }
}
