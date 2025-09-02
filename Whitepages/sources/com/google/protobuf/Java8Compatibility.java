package com.google.protobuf;

import java.nio.Buffer;

abstract class Java8Compatibility {
    static void limit(Buffer buffer, int i) {
        buffer.limit(i);
    }

    static void position(Buffer buffer, int i) {
        buffer.position(i);
    }
}
