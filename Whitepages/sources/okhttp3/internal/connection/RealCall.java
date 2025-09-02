package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.Dispatcher;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;

public final class RealCall implements Call {
    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private RealConnection connection;
    private final RealConnectionPool connectionPool;
    private volatile RealConnection connectionToCancel;
    private final EventListener eventListener;
    private volatile Exchange exchange;
    private ExchangeFinder exchangeFinder;
    private final AtomicBoolean executed = new AtomicBoolean();
    private boolean expectMoreExchanges = true;
    private final boolean forWebSocket;
    private Exchange interceptorScopedExchange;
    private final Request originalRequest;
    private boolean requestBodyOpen;
    private boolean responseBodyOpen;
    /* access modifiers changed from: private */
    public final RealCall$timeout$1 timeout;
    private boolean timeoutEarlyExit;

    public RealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        Intrinsics.checkNotNullParameter(okHttpClient, "client");
        Intrinsics.checkNotNullParameter(request, "originalRequest");
        this.client = okHttpClient;
        this.originalRequest = request;
        this.forWebSocket = z;
        this.connectionPool = okHttpClient.connectionPool().getDelegate$okhttp();
        this.eventListener = okHttpClient.eventListenerFactory().create(this);
        RealCall$timeout$1 realCall$timeout$1 = new RealCall$timeout$1(this);
        realCall$timeout$1.timeout((long) okHttpClient.callTimeoutMillis(), TimeUnit.MILLISECONDS);
        Unit unit = Unit.INSTANCE;
        this.timeout = realCall$timeout$1;
    }

    public final OkHttpClient getClient() {
        return this.client;
    }

    public final Request getOriginalRequest() {
        return this.originalRequest;
    }

    public final boolean getForWebSocket() {
        return this.forWebSocket;
    }

    public final EventListener getEventListener$okhttp() {
        return this.eventListener;
    }

    public final RealConnection getConnection() {
        return this.connection;
    }

    public final Exchange getInterceptorScopedExchange$okhttp() {
        return this.interceptorScopedExchange;
    }

    public final RealConnection getConnectionToCancel() {
        return this.connectionToCancel;
    }

    public final void setConnectionToCancel(RealConnection realConnection) {
        this.connectionToCancel = realConnection;
    }

    public AsyncTimeout timeout() {
        return this.timeout;
    }

    public RealCall clone() {
        return new RealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    public Request request() {
        return this.originalRequest;
    }

    public void cancel() {
        if (!this.canceled) {
            this.canceled = true;
            Exchange exchange2 = this.exchange;
            if (exchange2 != null) {
                exchange2.cancel();
            }
            RealConnection realConnection = this.connectionToCancel;
            if (realConnection != null) {
                realConnection.cancel();
            }
            this.eventListener.canceled(this);
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public Response execute() {
        if (this.executed.compareAndSet(false, true)) {
            this.timeout.enter();
            callStart();
            try {
                this.client.dispatcher().executed$okhttp(this);
                return getResponseWithInterceptorChain$okhttp();
            } finally {
                this.client.dispatcher().finished$okhttp(this);
            }
        } else {
            throw new IllegalStateException("Already Executed");
        }
    }

    public void enqueue(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "responseCallback");
        if (this.executed.compareAndSet(false, true)) {
            callStart();
            this.client.dispatcher().enqueue$okhttp(new AsyncCall(this, callback));
            return;
        }
        throw new IllegalStateException("Already Executed");
    }

    public boolean isExecuted() {
        return this.executed.get();
    }

    private final void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final okhttp3.Response getResponseWithInterceptorChain$okhttp() throws java.io.IOException {
        /*
            r11 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            okhttp3.OkHttpClient r0 = r11.client
            java.util.List r0 = r0.interceptors()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.collections.CollectionsKt.addAll((java.util.Collection) r2, (java.lang.Iterable) r0)
            okhttp3.internal.http.RetryAndFollowUpInterceptor r0 = new okhttp3.internal.http.RetryAndFollowUpInterceptor
            okhttp3.OkHttpClient r1 = r11.client
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.http.BridgeInterceptor r0 = new okhttp3.internal.http.BridgeInterceptor
            okhttp3.OkHttpClient r1 = r11.client
            okhttp3.CookieJar r1 = r1.cookieJar()
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.cache.CacheInterceptor r0 = new okhttp3.internal.cache.CacheInterceptor
            okhttp3.OkHttpClient r1 = r11.client
            okhttp3.Cache r1 = r1.cache()
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.connection.ConnectInterceptor r0 = okhttp3.internal.connection.ConnectInterceptor.INSTANCE
            r2.add(r0)
            boolean r0 = r11.forWebSocket
            if (r0 != 0) goto L_0x004a
            okhttp3.OkHttpClient r0 = r11.client
            java.util.List r0 = r0.networkInterceptors()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.collections.CollectionsKt.addAll((java.util.Collection) r2, (java.lang.Iterable) r0)
        L_0x004a:
            okhttp3.internal.http.CallServerInterceptor r0 = new okhttp3.internal.http.CallServerInterceptor
            boolean r1 = r11.forWebSocket
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.http.RealInterceptorChain r9 = new okhttp3.internal.http.RealInterceptorChain
            okhttp3.Request r5 = r11.originalRequest
            okhttp3.OkHttpClient r0 = r11.client
            int r6 = r0.connectTimeoutMillis()
            okhttp3.OkHttpClient r0 = r11.client
            int r7 = r0.readTimeoutMillis()
            okhttp3.OkHttpClient r0 = r11.client
            int r8 = r0.writeTimeoutMillis()
            r3 = 0
            r4 = 0
            r0 = r9
            r1 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            r1 = 0
            okhttp3.Request r2 = r11.originalRequest     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            okhttp3.Response r2 = r9.proceed(r2)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            boolean r3 = r11.isCanceled()     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            if (r3 != 0) goto L_0x0083
            r11.noMoreExchanges$okhttp(r0)
            return r2
        L_0x0083:
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            throw r2     // Catch:{ IOException -> 0x0090, all -> 0x008e }
        L_0x008e:
            r2 = move-exception
            goto L_0x00a6
        L_0x0090:
            r1 = move-exception
            r2 = 1
            java.io.IOException r1 = r11.noMoreExchanges$okhttp(r1)     // Catch:{ all -> 0x00a0 }
            if (r1 != 0) goto L_0x00a5
            java.lang.NullPointerException r1 = new java.lang.NullPointerException     // Catch:{ all -> 0x00a0 }
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Throwable"
            r1.<init>(r3)     // Catch:{ all -> 0x00a0 }
            throw r1     // Catch:{ all -> 0x00a0 }
        L_0x00a0:
            r1 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x00a6
        L_0x00a5:
            throw r1     // Catch:{ all -> 0x00a0 }
        L_0x00a6:
            if (r1 != 0) goto L_0x00ab
            r11.noMoreExchanges$okhttp(r0)
        L_0x00ab:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealCall.getResponseWithInterceptorChain$okhttp():okhttp3.Response");
    }

    public final void enterNetworkInterceptorExchange(Request request, boolean z) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.interceptorScopedExchange == null) {
            synchronized (this) {
                if (this.responseBodyOpen) {
                    throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
                } else if (!this.requestBodyOpen) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.");
                }
            }
            if (z) {
                this.exchangeFinder = new ExchangeFinder(this.connectionPool, createAddress(request.url()), this, this.eventListener);
                return;
            }
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final Exchange initExchange$okhttp(RealInterceptorChain realInterceptorChain) {
        Intrinsics.checkNotNullParameter(realInterceptorChain, "chain");
        synchronized (this) {
            if (!this.expectMoreExchanges) {
                throw new IllegalStateException("released");
            } else if (this.responseBodyOpen) {
                throw new IllegalStateException("Check failed.");
            } else if (!this.requestBodyOpen) {
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Check failed.");
            }
        }
        ExchangeFinder exchangeFinder2 = this.exchangeFinder;
        Intrinsics.checkNotNull(exchangeFinder2);
        Exchange exchange2 = new Exchange(this, this.eventListener, exchangeFinder2, exchangeFinder2.find(this.client, realInterceptorChain));
        this.interceptorScopedExchange = exchange2;
        this.exchange = exchange2;
        synchronized (this) {
            this.requestBodyOpen = true;
            this.responseBodyOpen = true;
        }
        if (!this.canceled) {
            return exchange2;
        }
        throw new IOException("Canceled");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0021 A[Catch:{ all -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0025 A[Catch:{ all -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0030 A[Catch:{ all -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0032 A[Catch:{ all -> 0x0017 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <E extends java.io.IOException> E messageDone$okhttp(okhttp3.internal.connection.Exchange r2, boolean r3, boolean r4, E r5) {
        /*
            r1 = this;
            java.lang.String r0 = "exchange"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            okhttp3.internal.connection.Exchange r0 = r1.exchange
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r0)
            if (r2 != 0) goto L_0x000e
            return r5
        L_0x000e:
            monitor-enter(r1)
            r2 = 0
            if (r3 == 0) goto L_0x0019
            boolean r0 = r1.requestBodyOpen     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x001f
            goto L_0x0019
        L_0x0017:
            r2 = move-exception
            goto L_0x0059
        L_0x0019:
            if (r4 == 0) goto L_0x0041
            boolean r0 = r1.responseBodyOpen     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0041
        L_0x001f:
            if (r3 == 0) goto L_0x0023
            r1.requestBodyOpen = r2     // Catch:{ all -> 0x0017 }
        L_0x0023:
            if (r4 == 0) goto L_0x0027
            r1.responseBodyOpen = r2     // Catch:{ all -> 0x0017 }
        L_0x0027:
            boolean r3 = r1.requestBodyOpen     // Catch:{ all -> 0x0017 }
            r4 = 1
            if (r3 != 0) goto L_0x0032
            boolean r0 = r1.responseBodyOpen     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x0032
            r0 = r4
            goto L_0x0033
        L_0x0032:
            r0 = r2
        L_0x0033:
            if (r3 != 0) goto L_0x003e
            boolean r3 = r1.responseBodyOpen     // Catch:{ all -> 0x0017 }
            if (r3 != 0) goto L_0x003e
            boolean r3 = r1.expectMoreExchanges     // Catch:{ all -> 0x0017 }
            if (r3 != 0) goto L_0x003e
            r2 = r4
        L_0x003e:
            r3 = r2
            r2 = r0
            goto L_0x0042
        L_0x0041:
            r3 = r2
        L_0x0042:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0017 }
            monitor-exit(r1)
            if (r2 == 0) goto L_0x0051
            r2 = 0
            r1.exchange = r2
            okhttp3.internal.connection.RealConnection r2 = r1.connection
            if (r2 == 0) goto L_0x0051
            r2.incrementSuccessCount$okhttp()
        L_0x0051:
            if (r3 == 0) goto L_0x0058
            java.io.IOException r2 = r1.callDone(r5)
            return r2
        L_0x0058:
            return r5
        L_0x0059:
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealCall.messageDone$okhttp(okhttp3.internal.connection.Exchange, boolean, boolean, java.io.IOException):java.io.IOException");
    }

    public final IOException noMoreExchanges$okhttp(IOException iOException) {
        boolean z;
        synchronized (this) {
            try {
                z = false;
                if (this.expectMoreExchanges) {
                    this.expectMoreExchanges = false;
                    if (!this.requestBodyOpen && !this.responseBodyOpen) {
                        z = true;
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z) {
            return callDone(iOException);
        }
        return iOException;
    }

    public final Socket releaseConnectionNoEvents$okhttp() {
        RealConnection realConnection = this.connection;
        Intrinsics.checkNotNull(realConnection);
        if (!Util.assertionsEnabled || Thread.holdsLock(realConnection)) {
            List<Reference<RealCall>> calls = realConnection.getCalls();
            Iterator<Reference<RealCall>> it = calls.iterator();
            boolean z = false;
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual((Object) (RealCall) it.next().get(), (Object) this)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i != -1) {
                z = true;
            }
            if (z) {
                calls.remove(i);
                this.connection = null;
                if (calls.isEmpty()) {
                    realConnection.setIdleAtNs$okhttp(System.nanoTime());
                    if (this.connectionPool.connectionBecameIdle(realConnection)) {
                        return realConnection.socket();
                    }
                }
                return null;
            }
            throw new IllegalStateException("Check failed.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append(" MUST hold lock on ");
        sb.append(realConnection);
        throw new AssertionError(sb.toString());
    }

    private final <E extends IOException> E timeoutExit(E e) {
        if (this.timeoutEarlyExit || !this.timeout.exit()) {
            return e;
        }
        E interruptedIOException = new InterruptedIOException("timeout");
        if (e != null) {
            interruptedIOException.initCause(e);
        }
        return interruptedIOException;
    }

    public final void timeoutEarlyExit() {
        if (!this.timeoutEarlyExit) {
            this.timeoutEarlyExit = true;
            this.timeout.exit();
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final void exitNetworkInterceptorExchange$okhttp(boolean z) {
        Exchange exchange2;
        synchronized (this) {
            if (this.expectMoreExchanges) {
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("released");
            }
        }
        if (z && (exchange2 = this.exchange) != null) {
            exchange2.detachWithViolence();
        }
        this.interceptorScopedExchange = null;
    }

    private final Address createAddress(HttpUrl httpUrl) {
        CertificatePinner certificatePinner;
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        if (httpUrl.isHttps()) {
            sSLSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.client.dns(), this.client.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    public final boolean retryAfterFailure() {
        ExchangeFinder exchangeFinder2 = this.exchangeFinder;
        Intrinsics.checkNotNull(exchangeFinder2);
        return exchangeFinder2.retryAfterFailure();
    }

    /* access modifiers changed from: private */
    public final String toLoggableString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.forWebSocket ? "web socket" : "call");
        sb.append(" to ");
        sb.append(redactedUrl$okhttp());
        return sb.toString();
    }

    public final String redactedUrl$okhttp() {
        return this.originalRequest.url().redact();
    }

    public final class AsyncCall implements Runnable {
        private volatile AtomicInteger callsPerHost = new AtomicInteger(0);
        private final Callback responseCallback;
        final /* synthetic */ RealCall this$0;

        public AsyncCall(RealCall realCall, Callback callback) {
            Intrinsics.checkNotNullParameter(callback, "responseCallback");
            this.this$0 = realCall;
            this.responseCallback = callback;
        }

        public final AtomicInteger getCallsPerHost() {
            return this.callsPerHost;
        }

        public final void reuseCallsPerHostFrom(AsyncCall asyncCall) {
            Intrinsics.checkNotNullParameter(asyncCall, "other");
            this.callsPerHost = asyncCall.callsPerHost;
        }

        public final String getHost() {
            return this.this$0.getOriginalRequest().url().host();
        }

        public final Request getRequest() {
            return this.this$0.getOriginalRequest();
        }

        public final RealCall getCall() {
            return this.this$0;
        }

        public final void executeOn(ExecutorService executorService) {
            Intrinsics.checkNotNullParameter(executorService, "executorService");
            Dispatcher dispatcher = this.this$0.getClient().dispatcher();
            if (!Util.assertionsEnabled || !Thread.holdsLock(dispatcher)) {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e);
                    this.this$0.noMoreExchanges$okhttp(interruptedIOException);
                    this.responseCallback.onFailure(this.this$0, interruptedIOException);
                    this.this$0.getClient().dispatcher().finished$okhttp(this);
                } catch (Throwable th) {
                    this.this$0.getClient().dispatcher().finished$okhttp(this);
                    throw th;
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Thread ");
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
                sb.append(currentThread.getName());
                sb.append(" MUST NOT hold lock on ");
                sb.append(dispatcher);
                throw new AssertionError(sb.toString());
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x0066 A[Catch:{ all -> 0x0087, all -> 0x004e }] */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x008c A[Catch:{ all -> 0x0087, all -> 0x004e }] */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x00ae A[Catch:{ all -> 0x0087, all -> 0x004e }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r7 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "OkHttp "
                r0.append(r1)
                okhttp3.internal.connection.RealCall r1 = r7.this$0
                java.lang.String r1 = r1.redactedUrl$okhttp()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                java.lang.String r2 = "currentThread"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                java.lang.String r2 = r1.getName()
                r1.setName(r0)
                okhttp3.internal.connection.RealCall r0 = r7.this$0     // Catch:{ all -> 0x004e }
                okhttp3.internal.connection.RealCall$timeout$1 r0 = r0.timeout     // Catch:{ all -> 0x004e }
                r0.enter()     // Catch:{ all -> 0x004e }
                r0 = 0
                okhttp3.internal.connection.RealCall r3 = r7.this$0     // Catch:{ IOException -> 0x005a, all -> 0x0055 }
                okhttp3.Response r0 = r3.getResponseWithInterceptorChain$okhttp()     // Catch:{ IOException -> 0x005a, all -> 0x0055 }
                r3 = 1
                okhttp3.Callback r4 = r7.responseCallback     // Catch:{ IOException -> 0x0053, all -> 0x0051 }
                okhttp3.internal.connection.RealCall r5 = r7.this$0     // Catch:{ IOException -> 0x0053, all -> 0x0051 }
                r4.onResponse(r5, r0)     // Catch:{ IOException -> 0x0053, all -> 0x0051 }
                okhttp3.internal.connection.RealCall r0 = r7.this$0     // Catch:{ all -> 0x004e }
                okhttp3.OkHttpClient r0 = r0.getClient()     // Catch:{ all -> 0x004e }
                okhttp3.Dispatcher r0 = r0.dispatcher()     // Catch:{ all -> 0x004e }
            L_0x0049:
                r0.finished$okhttp((okhttp3.internal.connection.RealCall.AsyncCall) r7)     // Catch:{ all -> 0x004e }
                goto L_0x00c0
            L_0x004e:
                r0 = move-exception
                goto L_0x00d2
            L_0x0051:
                r0 = move-exception
                goto L_0x005f
            L_0x0053:
                r0 = move-exception
                goto L_0x008a
            L_0x0055:
                r3 = move-exception
                r6 = r3
                r3 = r0
                r0 = r6
                goto L_0x005f
            L_0x005a:
                r3 = move-exception
                r6 = r3
                r3 = r0
                r0 = r6
                goto L_0x008a
            L_0x005f:
                okhttp3.internal.connection.RealCall r4 = r7.this$0     // Catch:{ all -> 0x0087 }
                r4.cancel()     // Catch:{ all -> 0x0087 }
                if (r3 != 0) goto L_0x0089
                java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x0087 }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
                r4.<init>()     // Catch:{ all -> 0x0087 }
                java.lang.String r5 = "canceled due to "
                r4.append(r5)     // Catch:{ all -> 0x0087 }
                r4.append(r0)     // Catch:{ all -> 0x0087 }
                java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0087 }
                r3.<init>(r4)     // Catch:{ all -> 0x0087 }
                kotlin.ExceptionsKt.addSuppressed(r3, r0)     // Catch:{ all -> 0x0087 }
                okhttp3.Callback r4 = r7.responseCallback     // Catch:{ all -> 0x0087 }
                okhttp3.internal.connection.RealCall r5 = r7.this$0     // Catch:{ all -> 0x0087 }
                r4.onFailure(r5, r3)     // Catch:{ all -> 0x0087 }
                goto L_0x0089
            L_0x0087:
                r0 = move-exception
                goto L_0x00c4
            L_0x0089:
                throw r0     // Catch:{ all -> 0x0087 }
            L_0x008a:
                if (r3 == 0) goto L_0x00ae
                okhttp3.internal.platform.Platform$Companion r3 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x0087 }
                okhttp3.internal.platform.Platform r3 = r3.get()     // Catch:{ all -> 0x0087 }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
                r4.<init>()     // Catch:{ all -> 0x0087 }
                java.lang.String r5 = "Callback failure for "
                r4.append(r5)     // Catch:{ all -> 0x0087 }
                okhttp3.internal.connection.RealCall r5 = r7.this$0     // Catch:{ all -> 0x0087 }
                java.lang.String r5 = r5.toLoggableString()     // Catch:{ all -> 0x0087 }
                r4.append(r5)     // Catch:{ all -> 0x0087 }
                java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0087 }
                r5 = 4
                r3.log(r4, r5, r0)     // Catch:{ all -> 0x0087 }
                goto L_0x00b5
            L_0x00ae:
                okhttp3.Callback r3 = r7.responseCallback     // Catch:{ all -> 0x0087 }
                okhttp3.internal.connection.RealCall r4 = r7.this$0     // Catch:{ all -> 0x0087 }
                r3.onFailure(r4, r0)     // Catch:{ all -> 0x0087 }
            L_0x00b5:
                okhttp3.internal.connection.RealCall r0 = r7.this$0     // Catch:{ all -> 0x004e }
                okhttp3.OkHttpClient r0 = r0.getClient()     // Catch:{ all -> 0x004e }
                okhttp3.Dispatcher r0 = r0.dispatcher()     // Catch:{ all -> 0x004e }
                goto L_0x0049
            L_0x00c0:
                r1.setName(r2)
                return
            L_0x00c4:
                okhttp3.internal.connection.RealCall r3 = r7.this$0     // Catch:{ all -> 0x004e }
                okhttp3.OkHttpClient r3 = r3.getClient()     // Catch:{ all -> 0x004e }
                okhttp3.Dispatcher r3 = r3.dispatcher()     // Catch:{ all -> 0x004e }
                r3.finished$okhttp((okhttp3.internal.connection.RealCall.AsyncCall) r7)     // Catch:{ all -> 0x004e }
                throw r0     // Catch:{ all -> 0x004e }
            L_0x00d2:
                r1.setName(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealCall.AsyncCall.run():void");
        }
    }

    public static final class CallReference extends WeakReference<RealCall> {
        private final Object callStackTrace;

        public final Object getCallStackTrace() {
            return this.callStackTrace;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CallReference(RealCall realCall, Object obj) {
            super(realCall);
            Intrinsics.checkNotNullParameter(realCall, "referent");
            this.callStackTrace = obj;
        }
    }

    public final void acquireConnectionNoEvents(RealConnection realConnection) {
        Intrinsics.checkNotNullParameter(realConnection, "connection");
        if (!Util.assertionsEnabled || Thread.holdsLock(realConnection)) {
            if (this.connection == null) {
                this.connection = realConnection;
                realConnection.getCalls().add(new CallReference(this, this.callStackTrace));
                return;
            }
            throw new IllegalStateException("Check failed.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append(" MUST hold lock on ");
        sb.append(realConnection);
        throw new AssertionError(sb.toString());
    }

    private final <E extends IOException> E callDone(E e) {
        Socket releaseConnectionNoEvents$okhttp;
        boolean z = Util.assertionsEnabled;
        if (!z || !Thread.holdsLock(this)) {
            RealConnection realConnection = this.connection;
            if (realConnection != null) {
                if (!z || !Thread.holdsLock(realConnection)) {
                    synchronized (realConnection) {
                        releaseConnectionNoEvents$okhttp = releaseConnectionNoEvents$okhttp();
                    }
                    if (this.connection == null) {
                        if (releaseConnectionNoEvents$okhttp != null) {
                            Util.closeQuietly(releaseConnectionNoEvents$okhttp);
                        }
                        this.eventListener.connectionReleased(this, realConnection);
                    } else {
                        if (!(releaseConnectionNoEvents$okhttp == null)) {
                            throw new IllegalStateException("Check failed.");
                        }
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Thread ");
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
                    sb.append(currentThread.getName());
                    sb.append(" MUST NOT hold lock on ");
                    sb.append(realConnection);
                    throw new AssertionError(sb.toString());
                }
            }
            E timeoutExit = timeoutExit(e);
            if (e != null) {
                EventListener eventListener2 = this.eventListener;
                Intrinsics.checkNotNull(timeoutExit);
                eventListener2.callFailed(this, timeoutExit);
            } else {
                this.eventListener.callEnd(this);
            }
            return timeoutExit;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Thread ");
        Thread currentThread2 = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread2, "Thread.currentThread()");
        sb2.append(currentThread2.getName());
        sb2.append(" MUST NOT hold lock on ");
        sb2.append(this);
        throw new AssertionError(sb2.toString());
    }
}
