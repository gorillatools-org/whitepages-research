package com.facebook.imagepipeline.producers;

public interface Consumer {
    void onCancellation();

    void onFailure(Throwable th);

    void onNewResult(Object obj, int i);

    void onProgressUpdate(float f);
}
