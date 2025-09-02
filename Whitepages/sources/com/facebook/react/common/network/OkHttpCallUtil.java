package com.facebook.react.common.network;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public final class OkHttpCallUtil {
    public static final OkHttpCallUtil INSTANCE = new OkHttpCallUtil();

    private OkHttpCallUtil() {
    }

    public static final void cancelTag(OkHttpClient okHttpClient, Object obj) {
        Intrinsics.checkNotNullParameter(okHttpClient, "client");
        Intrinsics.checkNotNullParameter(obj, "tag");
        Dispatcher r3 = okHttpClient.m1024deprecated_dispatcher();
        for (Call next : r3.queuedCalls()) {
            if (Intrinsics.areEqual(obj, next.request().tag())) {
                next.cancel();
                return;
            }
        }
        for (Call next2 : r3.runningCalls()) {
            if (Intrinsics.areEqual(obj, next2.request().tag())) {
                next2.cancel();
                return;
            }
        }
    }
}
