package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.common.internal.Objects;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.react.views.image.ReactImageView;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher {
    private final ExecutorService mExecutorService;
    private int mHttpConnectionTimeout;
    private final MonotonicClock mMonotonicClock;
    private final Map mRequestHeaders;
    private String mUserAgent;

    private static boolean isHttpRedirect(int i) {
        if (i == 307 || i == 308) {
            return true;
        }
        switch (i) {
            case ReactImageView.REMOTE_IMAGE_FADE_DURATION_MS /*300*/:
            case 301:
            case 302:
            case 303:
                return true;
            default:
                return false;
        }
    }

    private static boolean isHttpSuccess(int i) {
        return i >= 200 && i < 300;
    }

    public static class HttpUrlConnectionNetworkFetchState extends FetchState {
        /* access modifiers changed from: private */
        public long fetchCompleteTime;
        /* access modifiers changed from: private */
        public long responseTime;
        /* access modifiers changed from: private */
        public long submitTime;

        public HttpUrlConnectionNetworkFetchState(Consumer consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    public HttpUrlConnectionNetworkFetcher(int i) {
        this((String) null, (Map) null, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = i;
    }

    HttpUrlConnectionNetworkFetcher(String str, Map map, MonotonicClock monotonicClock) {
        this.mExecutorService = Executors.newFixedThreadPool(3);
        this.mMonotonicClock = monotonicClock;
        this.mRequestHeaders = map;
        this.mUserAgent = str;
    }

    public HttpUrlConnectionNetworkFetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        return new HttpUrlConnectionNetworkFetchState(consumer, producerContext);
    }

    public void fetch(final HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, final NetworkFetcher.Callback callback) {
        httpUrlConnectionNetworkFetchState.submitTime = this.mMonotonicClock.now();
        final Future<?> submit = this.mExecutorService.submit(new Runnable() {
            public void run() {
                HttpUrlConnectionNetworkFetcher.this.fetchSync(httpUrlConnectionNetworkFetchState, callback);
            }
        });
        httpUrlConnectionNetworkFetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                if (submit.cancel(false)) {
                    callback.onCancellation();
                }
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* access modifiers changed from: package-private */
    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v7 ?
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.collectCodeVars(ProcessVariables.java:122)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:45)
        */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0037 A[SYNTHETIC, Splitter:B:22:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0040 A[SYNTHETIC, Splitter:B:27:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    public void fetchSync(com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.HttpUrlConnectionNetworkFetchState r5, com.facebook.imagepipeline.producers.NetworkFetcher.Callback r6) {
        /*
            r4 = this;
            r0 = 0
            android.net.Uri r1 = r5.getUri()     // Catch:{ IOException -> 0x0030, all -> 0x002d }
            r2 = 5
            java.net.HttpURLConnection r1 = r4.downloadFrom(r1, r2)     // Catch:{ IOException -> 0x0030, all -> 0x002d }
            com.facebook.common.time.MonotonicClock r2 = r4.mMonotonicClock     // Catch:{ IOException -> 0x0020 }
            long r2 = r2.now()     // Catch:{ IOException -> 0x0020 }
            r5.responseTime = r2     // Catch:{ IOException -> 0x0020 }
            if (r1 == 0) goto L_0x0022
            java.io.InputStream r0 = r1.getInputStream()     // Catch:{ IOException -> 0x0020 }
            r5 = -1
            r6.onResponse(r0, r5)     // Catch:{ IOException -> 0x0020 }
            goto L_0x0022
        L_0x001e:
            r5 = move-exception
            goto L_0x003e
        L_0x0020:
            r5 = move-exception
            goto L_0x0032
        L_0x0022:
            if (r0 == 0) goto L_0x0027
            r0.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            if (r1 == 0) goto L_0x003d
        L_0x0029:
            r1.disconnect()
            goto L_0x003d
        L_0x002d:
            r5 = move-exception
            r1 = r0
            goto L_0x003e
        L_0x0030:
            r5 = move-exception
            r1 = r0
        L_0x0032:
            r6.onFailure(r5)     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x003a
            r0.close()     // Catch:{ IOException -> 0x003a }
        L_0x003a:
            if (r1 == 0) goto L_0x003d
            goto L_0x0029
        L_0x003d:
            return
        L_0x003e:
            if (r0 == 0) goto L_0x0043
            r0.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.disconnect()
        L_0x0048:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.fetchSync(com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$HttpUrlConnectionNetworkFetchState, com.facebook.imagepipeline.producers.NetworkFetcher$Callback):void");
    }

    private HttpURLConnection downloadFrom(Uri uri, int i) {
        Uri uri2;
        String str;
        HttpURLConnection openConnectionTo = openConnectionTo(uri);
        String str2 = this.mUserAgent;
        if (str2 != null) {
            openConnectionTo.setRequestProperty("User-Agent", str2);
        }
        Map map = this.mRequestHeaders;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                openConnectionTo.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        openConnectionTo.setConnectTimeout(this.mHttpConnectionTimeout);
        int responseCode = openConnectionTo.getResponseCode();
        if (isHttpSuccess(responseCode)) {
            return openConnectionTo;
        }
        if (isHttpRedirect(responseCode)) {
            String headerField = openConnectionTo.getHeaderField("Location");
            openConnectionTo.disconnect();
            if (headerField == null) {
                uri2 = null;
            } else {
                uri2 = Uri.parse(headerField);
            }
            String scheme = uri.getScheme();
            if (i > 0 && uri2 != null && !Objects.equal(uri2.getScheme(), scheme)) {
                return downloadFrom(uri2, i - 1);
            }
            if (i == 0) {
                str = error("URL %s follows too many redirects", uri.toString());
            } else {
                str = error("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode));
            }
            throw new IOException(str);
        }
        openConnectionTo.disconnect();
        throw new IOException(String.format("Image URL %s returned HTTP code %d", new Object[]{uri.toString(), Integer.valueOf(responseCode)}));
    }

    static HttpURLConnection openConnectionTo(Uri uri) {
        return (HttpURLConnection) UriUtil.uriToUrl(uri).openConnection();
    }

    public void onFetchCompletion(HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, int i) {
        httpUrlConnectionNetworkFetchState.fetchCompleteTime = this.mMonotonicClock.now();
    }

    private static String error(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }

    public Map getExtraMap(HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, int i) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("queue_time", Long.toString(httpUrlConnectionNetworkFetchState.responseTime - httpUrlConnectionNetworkFetchState.submitTime));
        hashMap.put("fetch_time", Long.toString(httpUrlConnectionNetworkFetchState.fetchCompleteTime - httpUrlConnectionNetworkFetchState.responseTime));
        hashMap.put("total_time", Long.toString(httpUrlConnectionNetworkFetchState.fetchCompleteTime - httpUrlConnectionNetworkFetchState.submitTime));
        hashMap.put("image_size", Integer.toString(i));
        return hashMap;
    }
}
