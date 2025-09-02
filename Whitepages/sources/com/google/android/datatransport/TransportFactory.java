package com.google.android.datatransport;

public interface TransportFactory {
    Transport getTransport(String str, Class cls, Encoding encoding, Transformer transformer);
}
