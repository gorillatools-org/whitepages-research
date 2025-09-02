package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.logging.Logging;

public abstract class ForcedSender {
    public static void sendBlocking(Transport transport, Priority priority) {
        if (transport instanceof TransportImpl) {
            TransportRuntime.getInstance().getUploader().logAndUpdateState(((TransportImpl) transport).getTransportContext().withPriority(priority), 1);
            return;
        }
        Logging.w("ForcedSender", "Expected instance of `TransportImpl`, got `%s`.", transport);
    }
}
