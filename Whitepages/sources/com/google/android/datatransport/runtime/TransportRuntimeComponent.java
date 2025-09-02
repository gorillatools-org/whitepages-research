package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import java.io.Closeable;

abstract class TransportRuntimeComponent implements Closeable {

    interface Builder {
        TransportRuntimeComponent build();

        Builder setApplicationContext(Context context);
    }

    /* access modifiers changed from: package-private */
    public abstract EventStore getEventStore();

    /* access modifiers changed from: package-private */
    public abstract TransportRuntime getTransportRuntime();

    TransportRuntimeComponent() {
    }

    public void close() {
        getEventStore().close();
    }
}
