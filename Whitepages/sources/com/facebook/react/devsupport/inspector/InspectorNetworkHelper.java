package com.facebook.react.devsupport.inspector;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class InspectorNetworkHelper {
    private static OkHttpClient client;

    private InspectorNetworkHelper() {
    }

    public static void loadNetworkResource(String str, final InspectorNetworkRequestListener inspectorNetworkRequestListener) {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            client = builder.connectTimeout(10, timeUnit).writeTimeout(10, timeUnit).readTimeout(0, TimeUnit.MINUTES).build();
        }
        try {
            client.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    if (!call.isCanceled()) {
                        InspectorNetworkRequestListener.this.onError(iOException.getMessage());
                    }
                }

                public void onResponse(Call call, Response response) {
                    InputStream byteStream;
                    Headers headers = response.headers();
                    HashMap hashMap = new HashMap();
                    for (String next : headers.names()) {
                        hashMap.put(next, headers.get(next));
                    }
                    InspectorNetworkRequestListener.this.onHeaders(response.code(), hashMap);
                    try {
                        ResponseBody body = response.body();
                        if (body != null) {
                            try {
                                byteStream = body.byteStream();
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = byteStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    InspectorNetworkRequestListener.this.onData(new String(bArr, 0, read));
                                }
                                byteStream.close();
                            } catch (Throwable th) {
                                if (body != null) {
                                    body.close();
                                }
                                throw th;
                            }
                        }
                        InspectorNetworkRequestListener.this.onCompletion();
                        if (body != null) {
                            body.close();
                        }
                    } catch (IOException e) {
                        InspectorNetworkRequestListener.this.onError(e.getMessage());
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            });
        } catch (IllegalArgumentException unused) {
            inspectorNetworkRequestListener.onError("Not a valid URL: " + str);
        }
    }
}
