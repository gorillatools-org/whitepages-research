package com.google.firebase.perf.network;

import com.google.firebase.perf.metrics.NetworkRequestMetricBuilder;
import com.google.firebase.perf.transport.TransportManager;
import com.google.firebase.perf.util.Timer;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class InstrumentOkHttpEnqueueCallback implements Callback {
    private final Callback callback;
    private final NetworkRequestMetricBuilder networkMetricBuilder;
    private final long startTimeMicros;
    private final Timer timer;

    public InstrumentOkHttpEnqueueCallback(Callback callback2, TransportManager transportManager, Timer timer2, long j) {
        this.callback = callback2;
        this.networkMetricBuilder = NetworkRequestMetricBuilder.builder(transportManager);
        this.startTimeMicros = j;
        this.timer = timer2;
    }

    public void onFailure(Call call, IOException iOException) {
        Request request = call.request();
        if (request != null) {
            HttpUrl url = request.url();
            if (url != null) {
                this.networkMetricBuilder.setUrl(url.url().toString());
            }
            if (request.method() != null) {
                this.networkMetricBuilder.setHttpMethod(request.method());
            }
        }
        this.networkMetricBuilder.setRequestStartTimeMicros(this.startTimeMicros);
        this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros());
        NetworkRequestMetricBuilderUtil.logError(this.networkMetricBuilder);
        this.callback.onFailure(call, iOException);
    }

    public void onResponse(Call call, Response response) throws IOException {
        Response response2 = response;
        FirebasePerfOkHttpClient.sendNetworkMetric(response2, this.networkMetricBuilder, this.startTimeMicros, this.timer.getDurationMicros());
        this.callback.onResponse(call, response);
    }
}
