package com.facebook.react.modules.network;

import android.content.Context;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class OkHttpClientProvider {
    private static OkHttpClient sClient;
    private static OkHttpClientFactory sFactory;

    public static void setOkHttpClientFactory(OkHttpClientFactory okHttpClientFactory) {
        sFactory = okHttpClientFactory;
    }

    public static OkHttpClient getOkHttpClient() {
        if (sClient == null) {
            sClient = createClient();
        }
        return sClient;
    }

    public static OkHttpClient createClient() {
        OkHttpClientFactory okHttpClientFactory = sFactory;
        if (okHttpClientFactory != null) {
            return okHttpClientFactory.createNewNetworkModuleClient();
        }
        return createClientBuilder().build();
    }

    public static OkHttpClient createClient(Context context) {
        OkHttpClientFactory okHttpClientFactory = sFactory;
        if (okHttpClientFactory != null) {
            return okHttpClientFactory.createNewNetworkModuleClient();
        }
        return createClientBuilder(context).build();
    }

    public static OkHttpClient.Builder createClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        return builder.connectTimeout(0, timeUnit).readTimeout(0, timeUnit).writeTimeout(0, timeUnit).cookieJar(new ReactCookieJarContainer());
    }

    public static OkHttpClient.Builder createClientBuilder(Context context) {
        return createClientBuilder(context, 10485760);
    }

    public static OkHttpClient.Builder createClientBuilder(Context context, int i) {
        OkHttpClient.Builder createClientBuilder = createClientBuilder();
        if (i == 0) {
            return createClientBuilder;
        }
        return createClientBuilder.cache(new Cache(new File(context.getCacheDir(), "http-cache"), (long) i));
    }
}
