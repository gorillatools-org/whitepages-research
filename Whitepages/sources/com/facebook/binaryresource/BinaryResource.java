package com.facebook.binaryresource;

import java.io.InputStream;

public interface BinaryResource {
    InputStream openStream();

    long size();
}
