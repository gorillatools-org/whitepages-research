package com.google.firebase.perf.v1;

import com.google.firebase.perf.v1.NetworkConnectionInfo;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkConnectionInfoOrBuilder extends MessageLiteOrBuilder {
    /* synthetic */ MessageLite getDefaultInstanceForType();

    NetworkConnectionInfo.MobileSubtype getMobileSubtype();

    NetworkConnectionInfo.NetworkType getNetworkType();

    boolean hasMobileSubtype();

    boolean hasNetworkType();

    /* synthetic */ boolean isInitialized();
}
