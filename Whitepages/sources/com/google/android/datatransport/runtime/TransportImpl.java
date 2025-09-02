package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;

final class TransportImpl implements Transport {
    private final String name;
    private final Encoding payloadEncoding;
    private final Transformer transformer;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$send$0(Exception exc) {
    }

    TransportImpl(TransportContext transportContext2, String str, Encoding encoding, Transformer transformer2, TransportInternal transportInternal2) {
        this.transportContext = transportContext2;
        this.name = str;
        this.payloadEncoding = encoding;
        this.transformer = transformer2;
        this.transportInternal = transportInternal2;
    }

    public void send(Event event) {
        schedule(event, new TransportImpl$$ExternalSyntheticLambda0());
    }

    public void schedule(Event event, TransportScheduleCallback transportScheduleCallback) {
        this.transportInternal.send(SendRequest.builder().setTransportContext(this.transportContext).setEvent(event).setTransportName(this.name).setTransformer(this.transformer).setEncoding(this.payloadEncoding).build(), transportScheduleCallback);
    }

    /* access modifiers changed from: package-private */
    public TransportContext getTransportContext() {
        return this.transportContext;
    }
}
