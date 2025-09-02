package com.facebook.imagepipeline.backends.okhttp3;

import android.os.SystemClock;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.io.IOException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class OkHttpNetworkFetcher$fetchWithRequest$2 implements Callback {
    final /* synthetic */ NetworkFetcher.Callback $callback;
    final /* synthetic */ OkHttpNetworkFetcher.OkHttpNetworkFetchState $fetchState;
    final /* synthetic */ OkHttpNetworkFetcher this$0;

    OkHttpNetworkFetcher$fetchWithRequest$2(OkHttpNetworkFetcher.OkHttpNetworkFetchState okHttpNetworkFetchState, OkHttpNetworkFetcher okHttpNetworkFetcher, NetworkFetcher.Callback callback) {
        this.$fetchState = okHttpNetworkFetchState;
        this.this$0 = okHttpNetworkFetcher;
        this.$callback = callback;
    }

    public void onResponse(Call call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        this.$fetchState.responseTime = SystemClock.elapsedRealtime();
        ResponseBody body = response.body();
        if (body != null) {
            OkHttpNetworkFetcher okHttpNetworkFetcher = this.this$0;
            NetworkFetcher.Callback callback = this.$callback;
            OkHttpNetworkFetcher.OkHttpNetworkFetchState okHttpNetworkFetchState = this.$fetchState;
            try {
                if (!response.isSuccessful()) {
                    okHttpNetworkFetcher.handleException(call, okHttpNetworkFetcher.makeExceptionFromResponse("Unexpected HTTP code " + response, response), callback);
                } else {
                    BytesRange fromContentRangeHeader = BytesRange.Companion.fromContentRangeHeader(response.header("Content-Range"));
                    if (!(fromContentRangeHeader == null || (fromContentRangeHeader.from == 0 && fromContentRangeHeader.to == Integer.MAX_VALUE))) {
                        okHttpNetworkFetchState.setResponseBytesRange(fromContentRangeHeader);
                        okHttpNetworkFetchState.setOnNewResultStatusFlags(8);
                    }
                    callback.onResponse(body.byteStream(), body.contentLength() < 0 ? 0 : (int) body.contentLength());
                }
            } catch (Exception e) {
                try {
                    okHttpNetworkFetcher.handleException(call, e, callback);
                } catch (Throwable th) {
                    CloseableKt.closeFinally(body, th);
                    throw th;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(body, (Throwable) null);
            return;
        }
        OkHttpNetworkFetcher okHttpNetworkFetcher2 = this.this$0;
        okHttpNetworkFetcher2.handleException(call, okHttpNetworkFetcher2.makeExceptionFromResponse("Response body null: " + response, response), this.$callback);
    }

    public void onFailure(Call call, IOException iOException) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(iOException, "e");
        this.this$0.handleException(call, iOException, this.$callback);
    }
}
