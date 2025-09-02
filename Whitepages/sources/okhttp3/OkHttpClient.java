package okhttp3;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.net.Proxy;
import java.net.ProxySelector;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.WebSocket;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.ws.WebSocketExtensions;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public class OkHttpClient implements Cloneable, Call.Factory, WebSocket.Factory {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS = Util.immutableListOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT);
    /* access modifiers changed from: private */
    public static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableListOf(Protocol.HTTP_2, Protocol.HTTP_1_1);
    private final Authenticator authenticator;
    private final Cache cache;
    private final int callTimeoutMillis;
    private final CertificateChainCleaner certificateChainCleaner;
    private final CertificatePinner certificatePinner;
    private final int connectTimeoutMillis;
    private final ConnectionPool connectionPool;
    private final List<ConnectionSpec> connectionSpecs;
    private final CookieJar cookieJar;
    private final Dispatcher dispatcher;
    private final Dns dns;
    private final EventListener.Factory eventListenerFactory;
    private final boolean followRedirects;
    private final boolean followSslRedirects;
    private final HostnameVerifier hostnameVerifier;
    private final List<Interceptor> interceptors;
    private final long minWebSocketMessageToCompress;
    private final List<Interceptor> networkInterceptors;
    private final int pingIntervalMillis;
    private final List<Protocol> protocols;
    private final Proxy proxy;
    private final Authenticator proxyAuthenticator;
    private final ProxySelector proxySelector;
    private final int readTimeoutMillis;
    private final boolean retryOnConnectionFailure;
    private final RouteDatabase routeDatabase;
    private final SocketFactory socketFactory;
    /* access modifiers changed from: private */
    public final SSLSocketFactory sslSocketFactoryOrNull;
    private final int writeTimeoutMillis;
    private final X509TrustManager x509TrustManager;

    public OkHttpClient(Builder builder) {
        ProxySelector proxySelector2;
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.dispatcher = builder.getDispatcher$okhttp();
        this.connectionPool = builder.getConnectionPool$okhttp();
        this.interceptors = Util.toImmutableList(builder.getInterceptors$okhttp());
        this.networkInterceptors = Util.toImmutableList(builder.getNetworkInterceptors$okhttp());
        this.eventListenerFactory = builder.getEventListenerFactory$okhttp();
        this.retryOnConnectionFailure = builder.getRetryOnConnectionFailure$okhttp();
        this.authenticator = builder.getAuthenticator$okhttp();
        this.followRedirects = builder.getFollowRedirects$okhttp();
        this.followSslRedirects = builder.getFollowSslRedirects$okhttp();
        this.cookieJar = builder.getCookieJar$okhttp();
        this.cache = builder.getCache$okhttp();
        this.dns = builder.getDns$okhttp();
        this.proxy = builder.getProxy$okhttp();
        if (builder.getProxy$okhttp() != null) {
            proxySelector2 = NullProxySelector.INSTANCE;
        } else {
            proxySelector2 = builder.getProxySelector$okhttp();
            proxySelector2 = proxySelector2 == null ? ProxySelector.getDefault() : proxySelector2;
            if (proxySelector2 == null) {
                proxySelector2 = NullProxySelector.INSTANCE;
            }
        }
        this.proxySelector = proxySelector2;
        this.proxyAuthenticator = builder.getProxyAuthenticator$okhttp();
        this.socketFactory = builder.getSocketFactory$okhttp();
        List<ConnectionSpec> connectionSpecs$okhttp = builder.getConnectionSpecs$okhttp();
        this.connectionSpecs = connectionSpecs$okhttp;
        this.protocols = builder.getProtocols$okhttp();
        this.hostnameVerifier = builder.getHostnameVerifier$okhttp();
        this.callTimeoutMillis = builder.getCallTimeout$okhttp();
        this.connectTimeoutMillis = builder.getConnectTimeout$okhttp();
        this.readTimeoutMillis = builder.getReadTimeout$okhttp();
        this.writeTimeoutMillis = builder.getWriteTimeout$okhttp();
        this.pingIntervalMillis = builder.getPingInterval$okhttp();
        this.minWebSocketMessageToCompress = builder.getMinWebSocketMessageToCompress$okhttp();
        RouteDatabase routeDatabase$okhttp = builder.getRouteDatabase$okhttp();
        this.routeDatabase = routeDatabase$okhttp == null ? new RouteDatabase() : routeDatabase$okhttp;
        Iterable iterable = connectionSpecs$okhttp;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((ConnectionSpec) it.next()).isTls()) {
                    if (builder.getSslSocketFactoryOrNull$okhttp() != null) {
                        this.sslSocketFactoryOrNull = builder.getSslSocketFactoryOrNull$okhttp();
                        CertificateChainCleaner certificateChainCleaner$okhttp = builder.getCertificateChainCleaner$okhttp();
                        Intrinsics.checkNotNull(certificateChainCleaner$okhttp);
                        this.certificateChainCleaner = certificateChainCleaner$okhttp;
                        X509TrustManager x509TrustManagerOrNull$okhttp = builder.getX509TrustManagerOrNull$okhttp();
                        Intrinsics.checkNotNull(x509TrustManagerOrNull$okhttp);
                        this.x509TrustManager = x509TrustManagerOrNull$okhttp;
                        CertificatePinner certificatePinner$okhttp = builder.getCertificatePinner$okhttp();
                        Intrinsics.checkNotNull(certificateChainCleaner$okhttp);
                        this.certificatePinner = certificatePinner$okhttp.withCertificateChainCleaner$okhttp(certificateChainCleaner$okhttp);
                    } else {
                        Platform.Companion companion = Platform.Companion;
                        X509TrustManager platformTrustManager = companion.get().platformTrustManager();
                        this.x509TrustManager = platformTrustManager;
                        Platform platform = companion.get();
                        Intrinsics.checkNotNull(platformTrustManager);
                        this.sslSocketFactoryOrNull = platform.newSslSocketFactory(platformTrustManager);
                        CertificateChainCleaner.Companion companion2 = CertificateChainCleaner.Companion;
                        Intrinsics.checkNotNull(platformTrustManager);
                        CertificateChainCleaner certificateChainCleaner2 = companion2.get(platformTrustManager);
                        this.certificateChainCleaner = certificateChainCleaner2;
                        CertificatePinner certificatePinner$okhttp2 = builder.getCertificatePinner$okhttp();
                        Intrinsics.checkNotNull(certificateChainCleaner2);
                        this.certificatePinner = certificatePinner$okhttp2.withCertificateChainCleaner$okhttp(certificateChainCleaner2);
                    }
                }
            }
            verifyClientState();
        }
        this.sslSocketFactoryOrNull = null;
        this.certificateChainCleaner = null;
        this.x509TrustManager = null;
        this.certificatePinner = CertificatePinner.DEFAULT;
        verifyClientState();
    }

    public Object clone() {
        return super.clone();
    }

    public final Dispatcher dispatcher() {
        return this.dispatcher;
    }

    public final ConnectionPool connectionPool() {
        return this.connectionPool;
    }

    public final List<Interceptor> interceptors() {
        return this.interceptors;
    }

    public final List<Interceptor> networkInterceptors() {
        return this.networkInterceptors;
    }

    public final EventListener.Factory eventListenerFactory() {
        return this.eventListenerFactory;
    }

    public final boolean retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    public final Authenticator authenticator() {
        return this.authenticator;
    }

    public final boolean followRedirects() {
        return this.followRedirects;
    }

    public final boolean followSslRedirects() {
        return this.followSslRedirects;
    }

    public final CookieJar cookieJar() {
        return this.cookieJar;
    }

    public final Cache cache() {
        return this.cache;
    }

    public final Dns dns() {
        return this.dns;
    }

    public final Proxy proxy() {
        return this.proxy;
    }

    public final ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public final Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    public final SocketFactory socketFactory() {
        return this.socketFactory;
    }

    public final SSLSocketFactory sslSocketFactory() {
        SSLSocketFactory sSLSocketFactory = this.sslSocketFactoryOrNull;
        if (sSLSocketFactory != null) {
            return sSLSocketFactory;
        }
        throw new IllegalStateException("CLEARTEXT-only client");
    }

    public final X509TrustManager x509TrustManager() {
        return this.x509TrustManager;
    }

    public final List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    public final List<Protocol> protocols() {
        return this.protocols;
    }

    public final HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    public final CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    public final CertificateChainCleaner certificateChainCleaner() {
        return this.certificateChainCleaner;
    }

    public final int callTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    public final int connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    public final int readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    public final int writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    public final int pingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    public final long minWebSocketMessageToCompress() {
        return this.minWebSocketMessageToCompress;
    }

    public final RouteDatabase getRouteDatabase() {
        return this.routeDatabase;
    }

    public OkHttpClient() {
        this(new Builder());
    }

    private final void verifyClientState() {
        List<Interceptor> list = this.interceptors;
        if (list == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        } else if (!list.contains((Object) null)) {
            List<Interceptor> list2 = this.networkInterceptors;
            if (list2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
            } else if (!list2.contains((Object) null)) {
                Iterable<ConnectionSpec> iterable = this.connectionSpecs;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    for (ConnectionSpec isTls : iterable) {
                        if (isTls.isTls()) {
                            if (this.sslSocketFactoryOrNull == null) {
                                throw new IllegalStateException("sslSocketFactory == null");
                            } else if (this.certificateChainCleaner == null) {
                                throw new IllegalStateException("certificateChainCleaner == null");
                            } else if (this.x509TrustManager == null) {
                                throw new IllegalStateException("x509TrustManager == null");
                            } else {
                                return;
                            }
                        }
                    }
                }
                boolean z = false;
                if (this.sslSocketFactoryOrNull == null) {
                    if (this.certificateChainCleaner == null) {
                        if (this.x509TrustManager == null) {
                            z = true;
                        }
                        if (!z) {
                            throw new IllegalStateException("Check failed.");
                        } else if (!Intrinsics.areEqual((Object) this.certificatePinner, (Object) CertificatePinner.DEFAULT)) {
                            throw new IllegalStateException("Check failed.");
                        }
                    } else {
                        throw new IllegalStateException("Check failed.");
                    }
                } else {
                    throw new IllegalStateException("Check failed.");
                }
            } else {
                throw new IllegalStateException(("Null network interceptor: " + this.networkInterceptors).toString());
            }
        } else {
            throw new IllegalStateException(("Null interceptor: " + this.interceptors).toString());
        }
    }

    public Call newCall(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new RealCall(this, request, false);
    }

    public WebSocket newWebSocket(Request request, WebSocketListener webSocketListener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(webSocketListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        RealWebSocket realWebSocket = new RealWebSocket(TaskRunner.INSTANCE, request, webSocketListener, new Random(), (long) this.pingIntervalMillis, (WebSocketExtensions) null, this.minWebSocketMessageToCompress);
        realWebSocket.connect(this);
        return realWebSocket;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    /* renamed from: -deprecated_dispatcher  reason: not valid java name */
    public final Dispatcher m1024deprecated_dispatcher() {
        return this.dispatcher;
    }

    /* renamed from: -deprecated_connectionPool  reason: not valid java name */
    public final ConnectionPool m1021deprecated_connectionPool() {
        return this.connectionPool;
    }

    /* renamed from: -deprecated_interceptors  reason: not valid java name */
    public final List<Interceptor> m1030deprecated_interceptors() {
        return this.interceptors;
    }

    /* renamed from: -deprecated_networkInterceptors  reason: not valid java name */
    public final List<Interceptor> m1031deprecated_networkInterceptors() {
        return this.networkInterceptors;
    }

    /* renamed from: -deprecated_eventListenerFactory  reason: not valid java name */
    public final EventListener.Factory m1026deprecated_eventListenerFactory() {
        return this.eventListenerFactory;
    }

    /* renamed from: -deprecated_retryOnConnectionFailure  reason: not valid java name */
    public final boolean m1038deprecated_retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    /* renamed from: -deprecated_authenticator  reason: not valid java name */
    public final Authenticator m1016deprecated_authenticator() {
        return this.authenticator;
    }

    /* renamed from: -deprecated_followRedirects  reason: not valid java name */
    public final boolean m1027deprecated_followRedirects() {
        return this.followRedirects;
    }

    /* renamed from: -deprecated_followSslRedirects  reason: not valid java name */
    public final boolean m1028deprecated_followSslRedirects() {
        return this.followSslRedirects;
    }

    /* renamed from: -deprecated_cookieJar  reason: not valid java name */
    public final CookieJar m1023deprecated_cookieJar() {
        return this.cookieJar;
    }

    /* renamed from: -deprecated_cache  reason: not valid java name */
    public final Cache m1017deprecated_cache() {
        return this.cache;
    }

    /* renamed from: -deprecated_dns  reason: not valid java name */
    public final Dns m1025deprecated_dns() {
        return this.dns;
    }

    /* renamed from: -deprecated_proxy  reason: not valid java name */
    public final Proxy m1034deprecated_proxy() {
        return this.proxy;
    }

    /* renamed from: -deprecated_proxySelector  reason: not valid java name */
    public final ProxySelector m1036deprecated_proxySelector() {
        return this.proxySelector;
    }

    /* renamed from: -deprecated_proxyAuthenticator  reason: not valid java name */
    public final Authenticator m1035deprecated_proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    /* renamed from: -deprecated_socketFactory  reason: not valid java name */
    public final SocketFactory m1039deprecated_socketFactory() {
        return this.socketFactory;
    }

    /* renamed from: -deprecated_sslSocketFactory  reason: not valid java name */
    public final SSLSocketFactory m1040deprecated_sslSocketFactory() {
        return sslSocketFactory();
    }

    /* renamed from: -deprecated_connectionSpecs  reason: not valid java name */
    public final List<ConnectionSpec> m1022deprecated_connectionSpecs() {
        return this.connectionSpecs;
    }

    /* renamed from: -deprecated_protocols  reason: not valid java name */
    public final List<Protocol> m1033deprecated_protocols() {
        return this.protocols;
    }

    /* renamed from: -deprecated_hostnameVerifier  reason: not valid java name */
    public final HostnameVerifier m1029deprecated_hostnameVerifier() {
        return this.hostnameVerifier;
    }

    /* renamed from: -deprecated_certificatePinner  reason: not valid java name */
    public final CertificatePinner m1019deprecated_certificatePinner() {
        return this.certificatePinner;
    }

    /* renamed from: -deprecated_callTimeoutMillis  reason: not valid java name */
    public final int m1018deprecated_callTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    /* renamed from: -deprecated_connectTimeoutMillis  reason: not valid java name */
    public final int m1020deprecated_connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    /* renamed from: -deprecated_readTimeoutMillis  reason: not valid java name */
    public final int m1037deprecated_readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    /* renamed from: -deprecated_writeTimeoutMillis  reason: not valid java name */
    public final int m1041deprecated_writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    /* renamed from: -deprecated_pingIntervalMillis  reason: not valid java name */
    public final int m1032deprecated_pingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    public static final class Builder {
        private Authenticator authenticator;
        private Cache cache;
        private int callTimeout;
        private CertificateChainCleaner certificateChainCleaner;
        private CertificatePinner certificatePinner;
        private int connectTimeout;
        private ConnectionPool connectionPool;
        private List<ConnectionSpec> connectionSpecs;
        private CookieJar cookieJar;
        private Dispatcher dispatcher;
        private Dns dns;
        private EventListener.Factory eventListenerFactory;
        private boolean followRedirects;
        private boolean followSslRedirects;
        private HostnameVerifier hostnameVerifier;
        private final List<Interceptor> interceptors;
        private long minWebSocketMessageToCompress;
        private final List<Interceptor> networkInterceptors;
        private int pingInterval;
        private List<? extends Protocol> protocols;
        private Proxy proxy;
        private Authenticator proxyAuthenticator;
        private ProxySelector proxySelector;
        private int readTimeout;
        private boolean retryOnConnectionFailure;
        private RouteDatabase routeDatabase;
        private SocketFactory socketFactory;
        private SSLSocketFactory sslSocketFactoryOrNull;
        private int writeTimeout;
        private X509TrustManager x509TrustManagerOrNull;

        public Builder() {
            this.dispatcher = new Dispatcher();
            this.connectionPool = new ConnectionPool();
            this.interceptors = new ArrayList();
            this.networkInterceptors = new ArrayList();
            this.eventListenerFactory = Util.asFactory(EventListener.NONE);
            this.retryOnConnectionFailure = true;
            Authenticator authenticator2 = Authenticator.NONE;
            this.authenticator = authenticator2;
            this.followRedirects = true;
            this.followSslRedirects = true;
            this.cookieJar = CookieJar.NO_COOKIES;
            this.dns = Dns.SYSTEM;
            this.proxyAuthenticator = authenticator2;
            SocketFactory socketFactory2 = SocketFactory.getDefault();
            Intrinsics.checkNotNullExpressionValue(socketFactory2, "SocketFactory.getDefault()");
            this.socketFactory = socketFactory2;
            Companion companion = OkHttpClient.Companion;
            this.connectionSpecs = companion.getDEFAULT_CONNECTION_SPECS$okhttp();
            this.protocols = companion.getDEFAULT_PROTOCOLS$okhttp();
            this.hostnameVerifier = OkHostnameVerifier.INSTANCE;
            this.certificatePinner = CertificatePinner.DEFAULT;
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
            this.minWebSocketMessageToCompress = RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
        }

        public final Dispatcher getDispatcher$okhttp() {
            return this.dispatcher;
        }

        public final void setDispatcher$okhttp(Dispatcher dispatcher2) {
            Intrinsics.checkNotNullParameter(dispatcher2, "<set-?>");
            this.dispatcher = dispatcher2;
        }

        public final ConnectionPool getConnectionPool$okhttp() {
            return this.connectionPool;
        }

        public final void setConnectionPool$okhttp(ConnectionPool connectionPool2) {
            Intrinsics.checkNotNullParameter(connectionPool2, "<set-?>");
            this.connectionPool = connectionPool2;
        }

        public final List<Interceptor> getInterceptors$okhttp() {
            return this.interceptors;
        }

        public final List<Interceptor> getNetworkInterceptors$okhttp() {
            return this.networkInterceptors;
        }

        public final EventListener.Factory getEventListenerFactory$okhttp() {
            return this.eventListenerFactory;
        }

        public final void setEventListenerFactory$okhttp(EventListener.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "<set-?>");
            this.eventListenerFactory = factory;
        }

        public final boolean getRetryOnConnectionFailure$okhttp() {
            return this.retryOnConnectionFailure;
        }

        public final void setRetryOnConnectionFailure$okhttp(boolean z) {
            this.retryOnConnectionFailure = z;
        }

        public final Authenticator getAuthenticator$okhttp() {
            return this.authenticator;
        }

        public final void setAuthenticator$okhttp(Authenticator authenticator2) {
            Intrinsics.checkNotNullParameter(authenticator2, "<set-?>");
            this.authenticator = authenticator2;
        }

        public final boolean getFollowRedirects$okhttp() {
            return this.followRedirects;
        }

        public final void setFollowRedirects$okhttp(boolean z) {
            this.followRedirects = z;
        }

        public final boolean getFollowSslRedirects$okhttp() {
            return this.followSslRedirects;
        }

        public final void setFollowSslRedirects$okhttp(boolean z) {
            this.followSslRedirects = z;
        }

        public final CookieJar getCookieJar$okhttp() {
            return this.cookieJar;
        }

        public final void setCookieJar$okhttp(CookieJar cookieJar2) {
            Intrinsics.checkNotNullParameter(cookieJar2, "<set-?>");
            this.cookieJar = cookieJar2;
        }

        public final Cache getCache$okhttp() {
            return this.cache;
        }

        public final void setCache$okhttp(Cache cache2) {
            this.cache = cache2;
        }

        public final Dns getDns$okhttp() {
            return this.dns;
        }

        public final void setDns$okhttp(Dns dns2) {
            Intrinsics.checkNotNullParameter(dns2, "<set-?>");
            this.dns = dns2;
        }

        public final Proxy getProxy$okhttp() {
            return this.proxy;
        }

        public final void setProxy$okhttp(Proxy proxy2) {
            this.proxy = proxy2;
        }

        public final ProxySelector getProxySelector$okhttp() {
            return this.proxySelector;
        }

        public final void setProxySelector$okhttp(ProxySelector proxySelector2) {
            this.proxySelector = proxySelector2;
        }

        public final Authenticator getProxyAuthenticator$okhttp() {
            return this.proxyAuthenticator;
        }

        public final void setProxyAuthenticator$okhttp(Authenticator authenticator2) {
            Intrinsics.checkNotNullParameter(authenticator2, "<set-?>");
            this.proxyAuthenticator = authenticator2;
        }

        public final SocketFactory getSocketFactory$okhttp() {
            return this.socketFactory;
        }

        public final void setSocketFactory$okhttp(SocketFactory socketFactory2) {
            Intrinsics.checkNotNullParameter(socketFactory2, "<set-?>");
            this.socketFactory = socketFactory2;
        }

        public final SSLSocketFactory getSslSocketFactoryOrNull$okhttp() {
            return this.sslSocketFactoryOrNull;
        }

        public final void setSslSocketFactoryOrNull$okhttp(SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactoryOrNull = sSLSocketFactory;
        }

        public final X509TrustManager getX509TrustManagerOrNull$okhttp() {
            return this.x509TrustManagerOrNull;
        }

        public final void setX509TrustManagerOrNull$okhttp(X509TrustManager x509TrustManager) {
            this.x509TrustManagerOrNull = x509TrustManager;
        }

        public final List<ConnectionSpec> getConnectionSpecs$okhttp() {
            return this.connectionSpecs;
        }

        public final void setConnectionSpecs$okhttp(List<ConnectionSpec> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.connectionSpecs = list;
        }

        public final List<Protocol> getProtocols$okhttp() {
            return this.protocols;
        }

        public final void setProtocols$okhttp(List<? extends Protocol> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.protocols = list;
        }

        public final HostnameVerifier getHostnameVerifier$okhttp() {
            return this.hostnameVerifier;
        }

        public final void setHostnameVerifier$okhttp(HostnameVerifier hostnameVerifier2) {
            Intrinsics.checkNotNullParameter(hostnameVerifier2, "<set-?>");
            this.hostnameVerifier = hostnameVerifier2;
        }

        public final CertificatePinner getCertificatePinner$okhttp() {
            return this.certificatePinner;
        }

        public final void setCertificatePinner$okhttp(CertificatePinner certificatePinner2) {
            Intrinsics.checkNotNullParameter(certificatePinner2, "<set-?>");
            this.certificatePinner = certificatePinner2;
        }

        public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
            return this.certificateChainCleaner;
        }

        public final void setCertificateChainCleaner$okhttp(CertificateChainCleaner certificateChainCleaner2) {
            this.certificateChainCleaner = certificateChainCleaner2;
        }

        public final int getCallTimeout$okhttp() {
            return this.callTimeout;
        }

        public final void setCallTimeout$okhttp(int i) {
            this.callTimeout = i;
        }

        public final int getConnectTimeout$okhttp() {
            return this.connectTimeout;
        }

        public final void setConnectTimeout$okhttp(int i) {
            this.connectTimeout = i;
        }

        public final int getReadTimeout$okhttp() {
            return this.readTimeout;
        }

        public final void setReadTimeout$okhttp(int i) {
            this.readTimeout = i;
        }

        public final int getWriteTimeout$okhttp() {
            return this.writeTimeout;
        }

        public final void setWriteTimeout$okhttp(int i) {
            this.writeTimeout = i;
        }

        public final int getPingInterval$okhttp() {
            return this.pingInterval;
        }

        public final void setPingInterval$okhttp(int i) {
            this.pingInterval = i;
        }

        public final long getMinWebSocketMessageToCompress$okhttp() {
            return this.minWebSocketMessageToCompress;
        }

        public final void setMinWebSocketMessageToCompress$okhttp(long j) {
            this.minWebSocketMessageToCompress = j;
        }

        public final RouteDatabase getRouteDatabase$okhttp() {
            return this.routeDatabase;
        }

        public final void setRouteDatabase$okhttp(RouteDatabase routeDatabase2) {
            this.routeDatabase = routeDatabase2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder(OkHttpClient okHttpClient) {
            this();
            Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
            this.dispatcher = okHttpClient.dispatcher();
            this.connectionPool = okHttpClient.connectionPool();
            CollectionsKt.addAll((Collection) this.interceptors, (Iterable) okHttpClient.interceptors());
            CollectionsKt.addAll((Collection) this.networkInterceptors, (Iterable) okHttpClient.networkInterceptors());
            this.eventListenerFactory = okHttpClient.eventListenerFactory();
            this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure();
            this.authenticator = okHttpClient.authenticator();
            this.followRedirects = okHttpClient.followRedirects();
            this.followSslRedirects = okHttpClient.followSslRedirects();
            this.cookieJar = okHttpClient.cookieJar();
            this.cache = okHttpClient.cache();
            this.dns = okHttpClient.dns();
            this.proxy = okHttpClient.proxy();
            this.proxySelector = okHttpClient.proxySelector();
            this.proxyAuthenticator = okHttpClient.proxyAuthenticator();
            this.socketFactory = okHttpClient.socketFactory();
            this.sslSocketFactoryOrNull = okHttpClient.sslSocketFactoryOrNull;
            this.x509TrustManagerOrNull = okHttpClient.x509TrustManager();
            this.connectionSpecs = okHttpClient.connectionSpecs();
            this.protocols = okHttpClient.protocols();
            this.hostnameVerifier = okHttpClient.hostnameVerifier();
            this.certificatePinner = okHttpClient.certificatePinner();
            this.certificateChainCleaner = okHttpClient.certificateChainCleaner();
            this.callTimeout = okHttpClient.callTimeoutMillis();
            this.connectTimeout = okHttpClient.connectTimeoutMillis();
            this.readTimeout = okHttpClient.readTimeoutMillis();
            this.writeTimeout = okHttpClient.writeTimeoutMillis();
            this.pingInterval = okHttpClient.pingIntervalMillis();
            this.minWebSocketMessageToCompress = okHttpClient.minWebSocketMessageToCompress();
            this.routeDatabase = okHttpClient.getRouteDatabase();
        }

        public final Builder dispatcher(Dispatcher dispatcher2) {
            Intrinsics.checkNotNullParameter(dispatcher2, "dispatcher");
            this.dispatcher = dispatcher2;
            return this;
        }

        public final Builder connectionPool(ConnectionPool connectionPool2) {
            Intrinsics.checkNotNullParameter(connectionPool2, "connectionPool");
            this.connectionPool = connectionPool2;
            return this;
        }

        public final List<Interceptor> interceptors() {
            return this.interceptors;
        }

        public final Builder addInterceptor(Interceptor interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            this.interceptors.add(interceptor);
            return this;
        }

        /* renamed from: -addInterceptor  reason: not valid java name */
        public final Builder m1042addInterceptor(Function1 function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            return addInterceptor(new OkHttpClient$Builder$addInterceptor$2(function1));
        }

        public final List<Interceptor> networkInterceptors() {
            return this.networkInterceptors;
        }

        public final Builder addNetworkInterceptor(Interceptor interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            this.networkInterceptors.add(interceptor);
            return this;
        }

        /* renamed from: -addNetworkInterceptor  reason: not valid java name */
        public final Builder m1043addNetworkInterceptor(Function1 function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            return addNetworkInterceptor(new OkHttpClient$Builder$addNetworkInterceptor$2(function1));
        }

        public final Builder eventListener(EventListener eventListener) {
            Intrinsics.checkNotNullParameter(eventListener, "eventListener");
            this.eventListenerFactory = Util.asFactory(eventListener);
            return this;
        }

        public final Builder eventListenerFactory(EventListener.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "eventListenerFactory");
            this.eventListenerFactory = factory;
            return this;
        }

        public final Builder retryOnConnectionFailure(boolean z) {
            this.retryOnConnectionFailure = z;
            return this;
        }

        public final Builder authenticator(Authenticator authenticator2) {
            Intrinsics.checkNotNullParameter(authenticator2, "authenticator");
            this.authenticator = authenticator2;
            return this;
        }

        public final Builder followRedirects(boolean z) {
            this.followRedirects = z;
            return this;
        }

        public final Builder followSslRedirects(boolean z) {
            this.followSslRedirects = z;
            return this;
        }

        public final Builder cookieJar(CookieJar cookieJar2) {
            Intrinsics.checkNotNullParameter(cookieJar2, "cookieJar");
            this.cookieJar = cookieJar2;
            return this;
        }

        public final Builder cache(Cache cache2) {
            this.cache = cache2;
            return this;
        }

        public final Builder dns(Dns dns2) {
            Intrinsics.checkNotNullParameter(dns2, "dns");
            if (!Intrinsics.areEqual((Object) dns2, (Object) this.dns)) {
                this.routeDatabase = null;
            }
            this.dns = dns2;
            return this;
        }

        public final Builder proxy(Proxy proxy2) {
            if (!Intrinsics.areEqual((Object) proxy2, (Object) this.proxy)) {
                this.routeDatabase = null;
            }
            this.proxy = proxy2;
            return this;
        }

        public final Builder proxySelector(ProxySelector proxySelector2) {
            Intrinsics.checkNotNullParameter(proxySelector2, "proxySelector");
            if (!Intrinsics.areEqual((Object) proxySelector2, (Object) this.proxySelector)) {
                this.routeDatabase = null;
            }
            this.proxySelector = proxySelector2;
            return this;
        }

        public final Builder proxyAuthenticator(Authenticator authenticator2) {
            Intrinsics.checkNotNullParameter(authenticator2, "proxyAuthenticator");
            if (!Intrinsics.areEqual((Object) authenticator2, (Object) this.proxyAuthenticator)) {
                this.routeDatabase = null;
            }
            this.proxyAuthenticator = authenticator2;
            return this;
        }

        public final Builder socketFactory(SocketFactory socketFactory2) {
            Intrinsics.checkNotNullParameter(socketFactory2, "socketFactory");
            if (!(socketFactory2 instanceof SSLSocketFactory)) {
                if (!Intrinsics.areEqual((Object) socketFactory2, (Object) this.socketFactory)) {
                    this.routeDatabase = null;
                }
                this.socketFactory = socketFactory2;
                return this;
            }
            throw new IllegalArgumentException("socketFactory instanceof SSLSocketFactory");
        }

        public final Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            Intrinsics.checkNotNullParameter(sSLSocketFactory, "sslSocketFactory");
            if (!Intrinsics.areEqual((Object) sSLSocketFactory, (Object) this.sslSocketFactoryOrNull)) {
                this.routeDatabase = null;
            }
            this.sslSocketFactoryOrNull = sSLSocketFactory;
            Platform.Companion companion = Platform.Companion;
            X509TrustManager trustManager = companion.get().trustManager(sSLSocketFactory);
            if (trustManager != null) {
                this.x509TrustManagerOrNull = trustManager;
                Platform platform = companion.get();
                X509TrustManager x509TrustManager = this.x509TrustManagerOrNull;
                Intrinsics.checkNotNull(x509TrustManager);
                this.certificateChainCleaner = platform.buildCertificateChainCleaner(x509TrustManager);
                return this;
            }
            throw new IllegalStateException("Unable to extract the trust manager on " + companion.get() + ", " + "sslSocketFactory is " + sSLSocketFactory.getClass());
        }

        public final Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            Intrinsics.checkNotNullParameter(sSLSocketFactory, "sslSocketFactory");
            Intrinsics.checkNotNullParameter(x509TrustManager, "trustManager");
            if (!Intrinsics.areEqual((Object) sSLSocketFactory, (Object) this.sslSocketFactoryOrNull) || !Intrinsics.areEqual((Object) x509TrustManager, (Object) this.x509TrustManagerOrNull)) {
                this.routeDatabase = null;
            }
            this.sslSocketFactoryOrNull = sSLSocketFactory;
            this.certificateChainCleaner = CertificateChainCleaner.Companion.get(x509TrustManager);
            this.x509TrustManagerOrNull = x509TrustManager;
            return this;
        }

        public final Builder connectionSpecs(List<ConnectionSpec> list) {
            Intrinsics.checkNotNullParameter(list, "connectionSpecs");
            if (!Intrinsics.areEqual((Object) list, (Object) this.connectionSpecs)) {
                this.routeDatabase = null;
            }
            this.connectionSpecs = Util.toImmutableList(list);
            return this;
        }

        public final Builder protocols(List<? extends Protocol> list) {
            Intrinsics.checkNotNullParameter(list, "protocols");
            List mutableList = CollectionsKt.toMutableList((Collection) list);
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            boolean z = false;
            if (mutableList.contains(protocol) || mutableList.contains(Protocol.HTTP_1_1)) {
                if (!mutableList.contains(protocol) || mutableList.size() <= 1) {
                    z = true;
                }
                if (!z) {
                    throw new IllegalArgumentException(("protocols containing h2_prior_knowledge cannot use other protocols: " + mutableList).toString());
                } else if (mutableList.contains(Protocol.HTTP_1_0)) {
                    throw new IllegalArgumentException(("protocols must not contain http/1.0: " + mutableList).toString());
                } else if (!mutableList.contains((Object) null)) {
                    mutableList.remove(Protocol.SPDY_3);
                    if (!Intrinsics.areEqual((Object) mutableList, (Object) this.protocols)) {
                        this.routeDatabase = null;
                    }
                    List<? extends Protocol> unmodifiableList = Collections.unmodifiableList(mutableList);
                    Intrinsics.checkNotNullExpressionValue(unmodifiableList, "Collections.unmodifiableList(protocolsCopy)");
                    this.protocols = unmodifiableList;
                    return this;
                } else {
                    throw new IllegalArgumentException("protocols must not contain null");
                }
            } else {
                throw new IllegalArgumentException(("protocols must contain h2_prior_knowledge or http/1.1: " + mutableList).toString());
            }
        }

        public final Builder hostnameVerifier(HostnameVerifier hostnameVerifier2) {
            Intrinsics.checkNotNullParameter(hostnameVerifier2, "hostnameVerifier");
            if (!Intrinsics.areEqual((Object) hostnameVerifier2, (Object) this.hostnameVerifier)) {
                this.routeDatabase = null;
            }
            this.hostnameVerifier = hostnameVerifier2;
            return this;
        }

        public final Builder certificatePinner(CertificatePinner certificatePinner2) {
            Intrinsics.checkNotNullParameter(certificatePinner2, "certificatePinner");
            if (!Intrinsics.areEqual((Object) certificatePinner2, (Object) this.certificatePinner)) {
                this.routeDatabase = null;
            }
            this.certificatePinner = certificatePinner2;
            return this;
        }

        public final Builder callTimeout(long j, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "unit");
            this.callTimeout = Util.checkDuration("timeout", j, timeUnit);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder callTimeout(Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            callTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder connectTimeout(long j, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "unit");
            this.connectTimeout = Util.checkDuration("timeout", j, timeUnit);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder connectTimeout(Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            connectTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder readTimeout(long j, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "unit");
            this.readTimeout = Util.checkDuration("timeout", j, timeUnit);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder readTimeout(Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            readTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder writeTimeout(long j, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "unit");
            this.writeTimeout = Util.checkDuration("timeout", j, timeUnit);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder writeTimeout(Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            writeTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder pingInterval(long j, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "unit");
            this.pingInterval = Util.checkDuration("interval", j, timeUnit);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder pingInterval(Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            pingInterval(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder minWebSocketMessageToCompress(long j) {
            if (j >= 0) {
                this.minWebSocketMessageToCompress = j;
                return this;
            }
            throw new IllegalArgumentException(("minWebSocketMessageToCompress must be positive: " + j).toString());
        }

        public final OkHttpClient build() {
            return new OkHttpClient(this);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<Protocol> getDEFAULT_PROTOCOLS$okhttp() {
            return OkHttpClient.DEFAULT_PROTOCOLS;
        }

        public final List<ConnectionSpec> getDEFAULT_CONNECTION_SPECS$okhttp() {
            return OkHttpClient.DEFAULT_CONNECTION_SPECS;
        }
    }
}
