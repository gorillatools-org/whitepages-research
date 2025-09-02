package com.google.firebase.perf.v1;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

public interface AndroidMemoryReadingOrBuilder extends MessageLiteOrBuilder {
    long getClientTimeUs();

    /* synthetic */ MessageLite getDefaultInstanceForType();

    int getUsedAppJavaHeapMemoryKb();

    boolean hasClientTimeUs();

    boolean hasUsedAppJavaHeapMemoryKb();

    /* synthetic */ boolean isInitialized();
}
