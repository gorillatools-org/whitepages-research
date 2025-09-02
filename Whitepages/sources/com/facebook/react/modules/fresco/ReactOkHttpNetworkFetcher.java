package com.facebook.react.modules.fresco;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.modules.network.OkHttpCompat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public final class ReactOkHttpNetworkFetcher extends OkHttpNetworkFetcher {
    private final OkHttpClient okHttpClient;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.facebook.react.modules.fresco.ImageCacheControl[] r0 = com.facebook.react.modules.fresco.ImageCacheControl.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.modules.fresco.ImageCacheControl r1 = com.facebook.react.modules.fresco.ImageCacheControl.RELOAD     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.modules.fresco.ImageCacheControl r1 = com.facebook.react.modules.fresco.ImageCacheControl.FORCE_CACHE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.modules.fresco.ImageCacheControl r1 = com.facebook.react.modules.fresco.ImageCacheControl.ONLY_IF_CACHED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.modules.fresco.ImageCacheControl r1 = com.facebook.react.modules.fresco.ImageCacheControl.DEFAULT     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.fresco.ReactOkHttpNetworkFetcher.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactOkHttpNetworkFetcher(OkHttpClient okHttpClient2) {
        super(okHttpClient2);
        Intrinsics.checkNotNullParameter(okHttpClient2, "okHttpClient");
        this.okHttpClient = okHttpClient2;
    }

    private final Map<String, String> getHeaders(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        HashMap hashMap = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            String string = readableMap.getString(nextKey);
            if (string != null) {
                hashMap.put(nextKey, string);
            }
        }
        return hashMap;
    }

    public void fetch(OkHttpNetworkFetcher.OkHttpNetworkFetchState okHttpNetworkFetchState, NetworkFetcher.Callback callback) {
        Map<String, String> map;
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        Intrinsics.checkNotNullParameter(callback, "callback");
        okHttpNetworkFetchState.submitTime = SystemClock.elapsedRealtime();
        Uri uri = okHttpNetworkFetchState.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        CacheControl.Builder builder = new CacheControl.Builder();
        if (okHttpNetworkFetchState.getContext().getImageRequest() instanceof ReactNetworkImageRequest) {
            ImageRequest imageRequest = okHttpNetworkFetchState.getContext().getImageRequest();
            Intrinsics.checkNotNull(imageRequest, "null cannot be cast to non-null type com.facebook.react.modules.fresco.ReactNetworkImageRequest");
            ReactNetworkImageRequest reactNetworkImageRequest = (ReactNetworkImageRequest) imageRequest;
            map = getHeaders(reactNetworkImageRequest.getHeaders$ReactAndroid_release());
            int i = WhenMappings.$EnumSwitchMapping$0[reactNetworkImageRequest.getCacheControl$ReactAndroid_release().ordinal()];
            if (i == 1) {
                builder.noStore().noCache();
            } else if (i == 2) {
                builder.maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS);
            } else if (i == 3) {
                builder.onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS);
            } else if (i == 4) {
                builder.noStore();
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } else {
            builder.noStore();
            map = null;
        }
        Headers headersFromMap = OkHttpCompat.getHeadersFromMap(map);
        Request.Builder builder2 = new Request.Builder();
        Intrinsics.checkNotNull(headersFromMap);
        Request.Builder cacheControl = builder2.headers(headersFromMap).cacheControl(builder.build());
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        fetchWithRequest(okHttpNetworkFetchState, callback, cacheControl.url(uri2).get().build());
    }
}
