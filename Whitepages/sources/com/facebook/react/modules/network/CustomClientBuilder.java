package com.facebook.react.modules.network;

import okhttp3.OkHttpClient;

public interface CustomClientBuilder {
    void apply(OkHttpClient.Builder builder);
}
