package com.google.firebase.perf.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

public interface AndroidApplicationInfoOrBuilder extends MessageLiteOrBuilder {
    /* synthetic */ MessageLite getDefaultInstanceForType();

    String getPackageName();

    ByteString getPackageNameBytes();

    String getSdkVersion();

    ByteString getSdkVersionBytes();

    String getVersionName();

    ByteString getVersionNameBytes();

    boolean hasPackageName();

    boolean hasSdkVersion();

    boolean hasVersionName();

    /* synthetic */ boolean isInitialized();
}
