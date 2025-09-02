package com.google.firebase.perf.v1;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CpuMetricReadingOrBuilder extends MessageLiteOrBuilder {
    long getClientTimeUs();

    /* synthetic */ MessageLite getDefaultInstanceForType();

    long getSystemTimeUs();

    long getUserTimeUs();

    boolean hasClientTimeUs();

    boolean hasSystemTimeUs();

    boolean hasUserTimeUs();

    /* synthetic */ boolean isInitialized();
}
