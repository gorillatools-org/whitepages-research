package com.google.firebase.perf.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PerfSessionOrBuilder extends MessageLiteOrBuilder {
    /* synthetic */ MessageLite getDefaultInstanceForType();

    String getSessionId();

    ByteString getSessionIdBytes();

    SessionVerbosity getSessionVerbosity(int i);

    int getSessionVerbosityCount();

    List<SessionVerbosity> getSessionVerbosityList();

    boolean hasSessionId();

    /* synthetic */ boolean isInitialized();
}
