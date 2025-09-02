package com.airbnb.lottie.network;

import java.io.Closeable;
import java.io.InputStream;

public interface LottieFetchResult extends Closeable {
    InputStream bodyByteStream();

    String contentType();

    String error();

    boolean isSuccessful();
}
