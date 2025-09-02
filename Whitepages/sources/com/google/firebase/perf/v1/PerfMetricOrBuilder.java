package com.google.firebase.perf.v1;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PerfMetricOrBuilder extends MessageLiteOrBuilder {
    ApplicationInfo getApplicationInfo();

    /* synthetic */ MessageLite getDefaultInstanceForType();

    GaugeMetric getGaugeMetric();

    NetworkRequestMetric getNetworkRequestMetric();

    TraceMetric getTraceMetric();

    TransportInfo getTransportInfo();

    boolean hasApplicationInfo();

    boolean hasGaugeMetric();

    boolean hasNetworkRequestMetric();

    boolean hasTraceMetric();

    boolean hasTransportInfo();

    /* synthetic */ boolean isInitialized();
}
