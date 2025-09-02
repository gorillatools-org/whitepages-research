package com.facebook.imagepipeline.backends.okhttp3;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpNetworkFetcher extends BaseNetworkFetcher {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String FETCH_TIME = "fetch_time";
    private static final String IMAGE_SIZE = "image_size";
    private static final String QUEUE_TIME = "queue_time";
    private static final String TOTAL_TIME = "total_time";
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    /* access modifiers changed from: private */
    public final Executor cancellationExecutor;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OkHttpNetworkFetcher(Call.Factory factory, Executor executor, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(factory, executor, (i & 4) != 0 ? true : z);
    }

    public OkHttpNetworkFetcher(Call.Factory factory, Executor executor, boolean z) {
        Intrinsics.checkNotNullParameter(factory, "callFactory");
        Intrinsics.checkNotNullParameter(executor, "cancellationExecutor");
        this.callFactory = factory;
        this.cancellationExecutor = executor;
        this.cacheControl = z ? new CacheControl.Builder().noStore().build() : null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OkHttpNetworkFetcher(okhttp3.OkHttpClient r8) {
        /*
            r7 = this;
            java.lang.String r0 = "okHttpClient"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            okhttp3.Dispatcher r0 = r8.dispatcher()
            java.util.concurrent.ExecutorService r3 = r0.executorService()
            java.lang.String r0 = "executorService(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            r5 = 4
            r6 = 0
            r4 = 0
            r1 = r7
            r2 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.<init>(okhttp3.OkHttpClient):void");
    }

    public static final class OkHttpNetworkFetchState extends FetchState {
        public long fetchCompleteTime;
        public long responseTime;
        public long submitTime;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OkHttpNetworkFetchState(Consumer consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        }
    }

    public OkHttpNetworkFetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(producerContext, "context");
        return new OkHttpNetworkFetchState(consumer, producerContext);
    }

    public void fetch(OkHttpNetworkFetchState okHttpNetworkFetchState, NetworkFetcher.Callback callback) {
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        Intrinsics.checkNotNullParameter(callback, "callback");
        okHttpNetworkFetchState.submitTime = SystemClock.elapsedRealtime();
        Uri uri = okHttpNetworkFetchState.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        try {
            Request.Builder builder = new Request.Builder().url(uri.toString()).get();
            CacheControl cacheControl2 = this.cacheControl;
            if (cacheControl2 != null) {
                builder.cacheControl(cacheControl2);
            }
            BytesRange bytesRange = okHttpNetworkFetchState.getContext().getImageRequest().getBytesRange();
            if (bytesRange != null) {
                builder.addHeader("Range", bytesRange.toHttpRangeHeaderValue());
            }
            Request build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            fetchWithRequest(okHttpNetworkFetchState, callback, build);
        } catch (Exception e) {
            callback.onFailure(e);
        }
    }

    public void onFetchCompletion(OkHttpNetworkFetchState okHttpNetworkFetchState, int i) {
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        okHttpNetworkFetchState.fetchCompleteTime = SystemClock.elapsedRealtime();
    }

    public Map<String, String> getExtraMap(OkHttpNetworkFetchState okHttpNetworkFetchState, int i) {
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        return MapsKt.mapOf(TuplesKt.to(QUEUE_TIME, String.valueOf(okHttpNetworkFetchState.responseTime - okHttpNetworkFetchState.submitTime)), TuplesKt.to(FETCH_TIME, String.valueOf(okHttpNetworkFetchState.fetchCompleteTime - okHttpNetworkFetchState.responseTime)), TuplesKt.to(TOTAL_TIME, String.valueOf(okHttpNetworkFetchState.fetchCompleteTime - okHttpNetworkFetchState.submitTime)), TuplesKt.to(IMAGE_SIZE, String.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void fetchWithRequest(OkHttpNetworkFetchState okHttpNetworkFetchState, NetworkFetcher.Callback callback, Request request) {
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(request, "request");
        Call newCall = this.callFactory.newCall(request);
        okHttpNetworkFetchState.getContext().addCallbacks(new OkHttpNetworkFetcher$fetchWithRequest$1(newCall, this));
        newCall.enqueue(new OkHttpNetworkFetcher$fetchWithRequest$2(okHttpNetworkFetchState, this, callback));
    }

    /* access modifiers changed from: private */
    public final IOException makeExceptionFromResponse(String str, Response response) {
        return new IOException(str, OkHttpNetworkFetcherException.Companion.fromResponse(response));
    }

    /* access modifiers changed from: private */
    public final void handleException(Call call, Exception exc, NetworkFetcher.Callback callback) {
        if (call.isCanceled()) {
            callback.onCancellation();
        } else {
            callback.onFailure(exc);
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
