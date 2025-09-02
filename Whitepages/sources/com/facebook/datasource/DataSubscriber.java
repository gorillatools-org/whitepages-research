package com.facebook.datasource;

public interface DataSubscriber {
    void onCancellation(DataSource dataSource);

    void onFailure(DataSource dataSource);

    void onNewResult(DataSource dataSource);

    void onProgressUpdate(DataSource dataSource);
}
