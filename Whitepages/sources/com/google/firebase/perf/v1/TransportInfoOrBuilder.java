package com.google.firebase.perf.v1;

import com.google.firebase.perf.v1.TransportInfo;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

public interface TransportInfoOrBuilder extends MessageLiteOrBuilder {
    /* synthetic */ MessageLite getDefaultInstanceForType();

    TransportInfo.DispatchDestination getDispatchDestination();

    boolean hasDispatchDestination();

    /* synthetic */ boolean isInitialized();
}
