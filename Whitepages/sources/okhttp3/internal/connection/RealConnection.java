package okhttp3.internal.connection;

import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public final class RealConnection extends Http2Connection.Listener implements Connection {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private int allocationLimit = 1;
    private final List<Reference<RealCall>> calls = new ArrayList();
    private final RealConnectionPool connectionPool;
    /* access modifiers changed from: private */
    public Handshake handshake;
    private Http2Connection http2Connection;
    private long idleAtNs = Long.MAX_VALUE;
    private boolean noCoalescedConnections;
    private boolean noNewExchanges;
    private Protocol protocol;
    private Socket rawSocket;
    private int refusedStreamCount;
    private final Route route;
    private int routeFailureCount;
    private BufferedSink sink;
    /* access modifiers changed from: private */
    public Socket socket;
    private BufferedSource source;
    private int successCount;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Proxy.Type.DIRECT.ordinal()] = 1;
            iArr[Proxy.Type.HTTP.ordinal()] = 2;
        }
    }

    public final RealConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    public RealConnection(RealConnectionPool realConnectionPool, Route route2) {
        Intrinsics.checkNotNullParameter(realConnectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(route2, "route");
        this.connectionPool = realConnectionPool;
        this.route = route2;
    }

    public final boolean getNoNewExchanges() {
        return this.noNewExchanges;
    }

    public final void setNoNewExchanges(boolean z) {
        this.noNewExchanges = z;
    }

    public final int getRouteFailureCount$okhttp() {
        return this.routeFailureCount;
    }

    public final void setRouteFailureCount$okhttp(int i) {
        this.routeFailureCount = i;
    }

    public final List<Reference<RealCall>> getCalls() {
        return this.calls;
    }

    public final long getIdleAtNs$okhttp() {
        return this.idleAtNs;
    }

    public final void setIdleAtNs$okhttp(long j) {
        this.idleAtNs = j;
    }

    public final boolean isMultiplexed$okhttp() {
        return this.http2Connection != null;
    }

    public final synchronized void noNewExchanges$okhttp() {
        this.noNewExchanges = true;
    }

    public final synchronized void noCoalescedConnections$okhttp() {
        this.noCoalescedConnections = true;
    }

    public final synchronized void incrementSuccessCount$okhttp() {
        this.successCount++;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a2 A[Catch:{ IOException -> 0x00bd }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00fb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void connect(int r17, int r18, int r19, int r20, boolean r21, okhttp3.Call r22, okhttp3.EventListener r23) {
        /*
            r16 = this;
            r7 = r16
            r8 = r22
            r9 = r23
            java.lang.String r0 = "call"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "eventListener"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            okhttp3.Protocol r0 = r7.protocol
            r10 = 1
            if (r0 != 0) goto L_0x0017
            r0 = r10
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            if (r0 == 0) goto L_0x015c
            okhttp3.Route r0 = r7.route
            okhttp3.Address r0 = r0.address()
            java.util.List r0 = r0.connectionSpecs()
            okhttp3.internal.connection.ConnectionSpecSelector r11 = new okhttp3.internal.connection.ConnectionSpecSelector
            r11.<init>(r0)
            okhttp3.Route r1 = r7.route
            okhttp3.Address r1 = r1.address()
            javax.net.ssl.SSLSocketFactory r1 = r1.sslSocketFactory()
            if (r1 != 0) goto L_0x0086
            okhttp3.ConnectionSpec r1 = okhttp3.ConnectionSpec.CLEARTEXT
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x0079
            okhttp3.Route r0 = r7.route
            okhttp3.Address r0 = r0.address()
            okhttp3.HttpUrl r0 = r0.url()
            java.lang.String r0 = r0.host()
            okhttp3.internal.platform.Platform$Companion r1 = okhttp3.internal.platform.Platform.Companion
            okhttp3.internal.platform.Platform r1 = r1.get()
            boolean r1 = r1.isCleartextTrafficPermitted(r0)
            if (r1 == 0) goto L_0x0058
            goto L_0x0098
        L_0x0058:
            okhttp3.internal.connection.RouteException r1 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r2 = new java.net.UnknownServiceException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "CLEARTEXT communication to "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = " not permitted by network security policy"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            r1.<init>(r2)
            throw r1
        L_0x0079:
            okhttp3.internal.connection.RouteException r0 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r1 = new java.net.UnknownServiceException
            java.lang.String r2 = "CLEARTEXT communication not enabled for client"
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x0086:
            okhttp3.Route r0 = r7.route
            okhttp3.Address r0 = r0.address()
            java.util.List r0 = r0.protocols()
            okhttp3.Protocol r1 = okhttp3.Protocol.H2_PRIOR_KNOWLEDGE
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x014f
        L_0x0098:
            r12 = 0
            r13 = r12
        L_0x009a:
            okhttp3.Route r0 = r7.route     // Catch:{ IOException -> 0x00bd }
            boolean r0 = r0.requiresTunnel()     // Catch:{ IOException -> 0x00bd }
            if (r0 == 0) goto L_0x00c5
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r22
            r6 = r23
            r1.connectTunnel(r2, r3, r4, r5, r6)     // Catch:{ IOException -> 0x00bd }
            java.net.Socket r0 = r7.rawSocket     // Catch:{ IOException -> 0x00bd }
            if (r0 != 0) goto L_0x00b6
            goto L_0x00e1
        L_0x00b6:
            r14 = r17
            r15 = r18
        L_0x00ba:
            r6 = r20
            goto L_0x00cd
        L_0x00bd:
            r0 = move-exception
            r14 = r17
            r15 = r18
        L_0x00c2:
            r6 = r20
            goto L_0x0106
        L_0x00c5:
            r14 = r17
            r15 = r18
            r7.connectSocket(r14, r15, r8, r9)     // Catch:{ IOException -> 0x0104 }
            goto L_0x00ba
        L_0x00cd:
            r7.establishProtocol(r11, r6, r8, r9)     // Catch:{ IOException -> 0x0102 }
            okhttp3.Route r0 = r7.route     // Catch:{ IOException -> 0x0102 }
            java.net.InetSocketAddress r0 = r0.socketAddress()     // Catch:{ IOException -> 0x0102 }
            okhttp3.Route r1 = r7.route     // Catch:{ IOException -> 0x0102 }
            java.net.Proxy r1 = r1.proxy()     // Catch:{ IOException -> 0x0102 }
            okhttp3.Protocol r2 = r7.protocol     // Catch:{ IOException -> 0x0102 }
            r9.connectEnd(r8, r0, r1, r2)     // Catch:{ IOException -> 0x0102 }
        L_0x00e1:
            okhttp3.Route r0 = r7.route
            boolean r0 = r0.requiresTunnel()
            if (r0 == 0) goto L_0x00fb
            java.net.Socket r0 = r7.rawSocket
            if (r0 == 0) goto L_0x00ee
            goto L_0x00fb
        L_0x00ee:
            okhttp3.internal.connection.RouteException r0 = new okhttp3.internal.connection.RouteException
            java.net.ProtocolException r1 = new java.net.ProtocolException
            java.lang.String r2 = "Too many tunnel connections attempted: 21"
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x00fb:
            long r0 = java.lang.System.nanoTime()
            r7.idleAtNs = r0
            return
        L_0x0102:
            r0 = move-exception
            goto L_0x0106
        L_0x0104:
            r0 = move-exception
            goto L_0x00c2
        L_0x0106:
            java.net.Socket r1 = r7.socket
            if (r1 == 0) goto L_0x010d
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r1)
        L_0x010d:
            java.net.Socket r1 = r7.rawSocket
            if (r1 == 0) goto L_0x0114
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r1)
        L_0x0114:
            r7.socket = r12
            r7.rawSocket = r12
            r7.source = r12
            r7.sink = r12
            r7.handshake = r12
            r7.protocol = r12
            r7.http2Connection = r12
            r7.allocationLimit = r10
            okhttp3.Route r1 = r7.route
            java.net.InetSocketAddress r3 = r1.socketAddress()
            okhttp3.Route r1 = r7.route
            java.net.Proxy r4 = r1.proxy()
            r5 = 0
            r1 = r23
            r2 = r22
            r6 = r0
            r1.connectFailed(r2, r3, r4, r5, r6)
            if (r13 != 0) goto L_0x0141
            okhttp3.internal.connection.RouteException r13 = new okhttp3.internal.connection.RouteException
            r13.<init>(r0)
            goto L_0x0144
        L_0x0141:
            r13.addConnectException(r0)
        L_0x0144:
            if (r21 == 0) goto L_0x014e
            boolean r0 = r11.connectionFailed(r0)
            if (r0 == 0) goto L_0x014e
            goto L_0x009a
        L_0x014e:
            throw r13
        L_0x014f:
            okhttp3.internal.connection.RouteException r0 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r1 = new java.net.UnknownServiceException
            java.lang.String r2 = "H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x015c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "already connected"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, int, boolean, okhttp3.Call, okhttp3.EventListener):void");
    }

    private final void connectTunnel(int i, int i2, int i3, Call call, EventListener eventListener) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl url = createTunnelRequest.url();
        int i4 = 0;
        while (i4 < 21) {
            connectSocket(i, i2, call, eventListener);
            createTunnelRequest = createTunnel(i2, i3, createTunnelRequest, url);
            if (createTunnelRequest != null) {
                Socket socket2 = this.rawSocket;
                if (socket2 != null) {
                    Util.closeQuietly(socket2);
                }
                this.rawSocket = null;
                this.sink = null;
                this.source = null;
                eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), (Protocol) null);
                i4++;
            } else {
                return;
            }
        }
    }

    private final void connectSocket(int i, int i2, Call call, EventListener eventListener) throws IOException {
        Socket socket2;
        int i3;
        Proxy proxy = this.route.proxy();
        Address address = this.route.address();
        Proxy.Type type = proxy.type();
        if (type != null && ((i3 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) == 1 || i3 == 2)) {
            socket2 = address.socketFactory().createSocket();
            Intrinsics.checkNotNull(socket2);
        } else {
            socket2 = new Socket(proxy);
        }
        this.rawSocket = socket2;
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        socket2.setSoTimeout(i2);
        try {
            Platform.Companion.get().connectSocket(socket2, this.route.socketAddress(), i);
            try {
                this.source = Okio.buffer(Okio.source(socket2));
                this.sink = Okio.buffer(Okio.sink(socket2));
            } catch (NullPointerException e) {
                if (Intrinsics.areEqual((Object) e.getMessage(), (Object) NPE_THROW_WITH_NULL)) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.route.socketAddress());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    private final void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int i, Call call, EventListener eventListener) throws IOException {
        if (this.route.address().sslSocketFactory() == null) {
            List<Protocol> protocols = this.route.address().protocols();
            Protocol protocol2 = Protocol.H2_PRIOR_KNOWLEDGE;
            if (protocols.contains(protocol2)) {
                this.socket = this.rawSocket;
                this.protocol = protocol2;
                startHttp2(i);
                return;
            }
            this.socket = this.rawSocket;
            this.protocol = Protocol.HTTP_1_1;
            return;
        }
        eventListener.secureConnectStart(call);
        connectTls(connectionSpecSelector);
        eventListener.secureConnectEnd(call, this.handshake);
        if (this.protocol == Protocol.HTTP_2) {
            startHttp2(i);
        }
    }

    private final void startHttp2(int i) throws IOException {
        Socket socket2 = this.socket;
        Intrinsics.checkNotNull(socket2);
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        socket2.setSoTimeout(0);
        Http2Connection build = new Http2Connection.Builder(true, TaskRunner.INSTANCE).socket(socket2, this.route.address().url().host(), bufferedSource, bufferedSink).listener(this).pingIntervalMillis(i).build();
        this.http2Connection = build;
        this.allocationLimit = Http2Connection.Companion.getDEFAULT_SETTINGS().getMaxConcurrentStreams();
        Http2Connection.start$default(build, false, (TaskRunner) null, 3, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0192  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void connectTls(okhttp3.internal.connection.ConnectionSpecSelector r11) throws java.io.IOException {
        /*
            r10 = this;
            okhttp3.Route r0 = r10.route
            okhttp3.Address r0 = r0.address()
            javax.net.ssl.SSLSocketFactory r1 = r0.sslSocketFactory()
            r2 = 0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x017b }
            java.net.Socket r3 = r10.rawSocket     // Catch:{ all -> 0x017b }
            okhttp3.HttpUrl r4 = r0.url()     // Catch:{ all -> 0x017b }
            java.lang.String r4 = r4.host()     // Catch:{ all -> 0x017b }
            okhttp3.HttpUrl r5 = r0.url()     // Catch:{ all -> 0x017b }
            int r5 = r5.port()     // Catch:{ all -> 0x017b }
            r6 = 1
            java.net.Socket r1 = r1.createSocket(r3, r4, r5, r6)     // Catch:{ all -> 0x017b }
            if (r1 == 0) goto L_0x017d
            javax.net.ssl.SSLSocket r1 = (javax.net.ssl.SSLSocket) r1     // Catch:{ all -> 0x017b }
            okhttp3.ConnectionSpec r11 = r11.configureSecureSocket(r1)     // Catch:{ all -> 0x0049 }
            boolean r3 = r11.supportsTlsExtensions()     // Catch:{ all -> 0x0049 }
            if (r3 == 0) goto L_0x004d
            okhttp3.internal.platform.Platform$Companion r3 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x0049 }
            okhttp3.internal.platform.Platform r3 = r3.get()     // Catch:{ all -> 0x0049 }
            okhttp3.HttpUrl r4 = r0.url()     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = r4.host()     // Catch:{ all -> 0x0049 }
            java.util.List r5 = r0.protocols()     // Catch:{ all -> 0x0049 }
            r3.configureTlsExtensions(r1, r4, r5)     // Catch:{ all -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r11 = move-exception
            r2 = r1
            goto L_0x0185
        L_0x004d:
            r1.startHandshake()     // Catch:{ all -> 0x0049 }
            javax.net.ssl.SSLSession r3 = r1.getSession()     // Catch:{ all -> 0x0049 }
            okhttp3.Handshake$Companion r4 = okhttp3.Handshake.Companion     // Catch:{ all -> 0x0049 }
            java.lang.String r5 = "sslSocketSession"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch:{ all -> 0x0049 }
            okhttp3.Handshake r4 = r4.get(r3)     // Catch:{ all -> 0x0049 }
            javax.net.ssl.HostnameVerifier r5 = r0.hostnameVerifier()     // Catch:{ all -> 0x0049 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ all -> 0x0049 }
            okhttp3.HttpUrl r7 = r0.url()     // Catch:{ all -> 0x0049 }
            java.lang.String r7 = r7.host()     // Catch:{ all -> 0x0049 }
            boolean r3 = r5.verify(r7, r3)     // Catch:{ all -> 0x0049 }
            if (r3 != 0) goto L_0x010f
            java.util.List r11 = r4.peerCertificates()     // Catch:{ all -> 0x0049 }
            r3 = r11
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ all -> 0x0049 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0049 }
            if (r3 != 0) goto L_0x00eb
            r3 = 0
            java.lang.Object r11 = r11.get(r3)     // Catch:{ all -> 0x0049 }
            if (r11 != 0) goto L_0x0090
            java.lang.NullPointerException r11 = new java.lang.NullPointerException     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
            r11.<init>(r0)     // Catch:{ all -> 0x0049 }
            throw r11     // Catch:{ all -> 0x0049 }
        L_0x0090:
            java.security.cert.X509Certificate r11 = (java.security.cert.X509Certificate) r11     // Catch:{ all -> 0x0049 }
            javax.net.ssl.SSLPeerUnverifiedException r3 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ all -> 0x0049 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r4.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r5 = "\n              |Hostname "
            r4.append(r5)     // Catch:{ all -> 0x0049 }
            okhttp3.HttpUrl r0 = r0.url()     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r0.host()     // Catch:{ all -> 0x0049 }
            r4.append(r0)     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = " not verified:\n              |    certificate: "
            r4.append(r0)     // Catch:{ all -> 0x0049 }
            okhttp3.CertificatePinner$Companion r0 = okhttp3.CertificatePinner.Companion     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r0.pin(r11)     // Catch:{ all -> 0x0049 }
            r4.append(r0)     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = "\n              |    DN: "
            r4.append(r0)     // Catch:{ all -> 0x0049 }
            java.security.Principal r0 = r11.getSubjectDN()     // Catch:{ all -> 0x0049 }
            java.lang.String r5 = "cert.subjectDN"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r0.getName()     // Catch:{ all -> 0x0049 }
            r4.append(r0)     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = "\n              |    subjectAltNames: "
            r4.append(r0)     // Catch:{ all -> 0x0049 }
            okhttp3.internal.tls.OkHostnameVerifier r0 = okhttp3.internal.tls.OkHostnameVerifier.INSTANCE     // Catch:{ all -> 0x0049 }
            java.util.List r11 = r0.allSubjectAltNames(r11)     // Catch:{ all -> 0x0049 }
            r4.append(r11)     // Catch:{ all -> 0x0049 }
            java.lang.String r11 = "\n              "
            r4.append(r11)     // Catch:{ all -> 0x0049 }
            java.lang.String r11 = r4.toString()     // Catch:{ all -> 0x0049 }
            java.lang.String r11 = kotlin.text.StringsKt.trimMargin$default(r11, r2, r6, r2)     // Catch:{ all -> 0x0049 }
            r3.<init>(r11)     // Catch:{ all -> 0x0049 }
            throw r3     // Catch:{ all -> 0x0049 }
        L_0x00eb:
            javax.net.ssl.SSLPeerUnverifiedException r11 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ all -> 0x0049 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r2.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = "Hostname "
            r2.append(r3)     // Catch:{ all -> 0x0049 }
            okhttp3.HttpUrl r0 = r0.url()     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r0.host()     // Catch:{ all -> 0x0049 }
            r2.append(r0)     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = " not verified (no certificates)"
            r2.append(r0)     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0049 }
            r11.<init>(r0)     // Catch:{ all -> 0x0049 }
            throw r11     // Catch:{ all -> 0x0049 }
        L_0x010f:
            okhttp3.CertificatePinner r3 = r0.certificatePinner()     // Catch:{ all -> 0x0049 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ all -> 0x0049 }
            okhttp3.Handshake r5 = new okhttp3.Handshake     // Catch:{ all -> 0x0049 }
            okhttp3.TlsVersion r6 = r4.tlsVersion()     // Catch:{ all -> 0x0049 }
            okhttp3.CipherSuite r7 = r4.cipherSuite()     // Catch:{ all -> 0x0049 }
            java.util.List r8 = r4.localCertificates()     // Catch:{ all -> 0x0049 }
            okhttp3.internal.connection.RealConnection$connectTls$1 r9 = new okhttp3.internal.connection.RealConnection$connectTls$1     // Catch:{ all -> 0x0049 }
            r9.<init>(r3, r4, r0)     // Catch:{ all -> 0x0049 }
            r5.<init>(r6, r7, r8, r9)     // Catch:{ all -> 0x0049 }
            r10.handshake = r5     // Catch:{ all -> 0x0049 }
            okhttp3.HttpUrl r0 = r0.url()     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r0.host()     // Catch:{ all -> 0x0049 }
            okhttp3.internal.connection.RealConnection$connectTls$2 r4 = new okhttp3.internal.connection.RealConnection$connectTls$2     // Catch:{ all -> 0x0049 }
            r4.<init>(r10)     // Catch:{ all -> 0x0049 }
            r3.check$okhttp(r0, r4)     // Catch:{ all -> 0x0049 }
            boolean r11 = r11.supportsTlsExtensions()     // Catch:{ all -> 0x0049 }
            if (r11 == 0) goto L_0x014e
            okhttp3.internal.platform.Platform$Companion r11 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x0049 }
            okhttp3.internal.platform.Platform r11 = r11.get()     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = r11.getSelectedProtocol(r1)     // Catch:{ all -> 0x0049 }
        L_0x014e:
            r10.socket = r1     // Catch:{ all -> 0x0049 }
            okio.Source r11 = okio.Okio.source((java.net.Socket) r1)     // Catch:{ all -> 0x0049 }
            okio.BufferedSource r11 = okio.Okio.buffer((okio.Source) r11)     // Catch:{ all -> 0x0049 }
            r10.source = r11     // Catch:{ all -> 0x0049 }
            okio.Sink r11 = okio.Okio.sink((java.net.Socket) r1)     // Catch:{ all -> 0x0049 }
            okio.BufferedSink r11 = okio.Okio.buffer((okio.Sink) r11)     // Catch:{ all -> 0x0049 }
            r10.sink = r11     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x016d
            okhttp3.Protocol$Companion r11 = okhttp3.Protocol.Companion     // Catch:{ all -> 0x0049 }
            okhttp3.Protocol r11 = r11.get(r2)     // Catch:{ all -> 0x0049 }
            goto L_0x016f
        L_0x016d:
            okhttp3.Protocol r11 = okhttp3.Protocol.HTTP_1_1     // Catch:{ all -> 0x0049 }
        L_0x016f:
            r10.protocol = r11     // Catch:{ all -> 0x0049 }
            okhttp3.internal.platform.Platform$Companion r11 = okhttp3.internal.platform.Platform.Companion
            okhttp3.internal.platform.Platform r11 = r11.get()
            r11.afterHandshake(r1)
            return
        L_0x017b:
            r11 = move-exception
            goto L_0x0185
        L_0x017d:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException     // Catch:{ all -> 0x017b }
            java.lang.String r0 = "null cannot be cast to non-null type javax.net.ssl.SSLSocket"
            r11.<init>(r0)     // Catch:{ all -> 0x017b }
            throw r11     // Catch:{ all -> 0x017b }
        L_0x0185:
            if (r2 == 0) goto L_0x0190
            okhttp3.internal.platform.Platform$Companion r0 = okhttp3.internal.platform.Platform.Companion
            okhttp3.internal.platform.Platform r0 = r0.get()
            r0.afterHandshake(r2)
        L_0x0190:
            if (r2 == 0) goto L_0x0195
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r2)
        L_0x0195:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connectTls(okhttp3.internal.connection.ConnectionSpecSelector):void");
    }

    private final Request createTunnel(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + Util.toHostHeader(httpUrl, true) + " HTTP/1.1";
        while (true) {
            BufferedSource bufferedSource = this.source;
            Intrinsics.checkNotNull(bufferedSource);
            BufferedSink bufferedSink = this.sink;
            Intrinsics.checkNotNull(bufferedSink);
            Http1ExchangeCodec http1ExchangeCodec = new Http1ExchangeCodec((OkHttpClient) null, this, bufferedSource, bufferedSink);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            bufferedSource.timeout().timeout((long) i, timeUnit);
            bufferedSink.timeout().timeout((long) i2, timeUnit);
            http1ExchangeCodec.writeRequest(request.headers(), str);
            http1ExchangeCodec.finishRequest();
            Response.Builder readResponseHeaders = http1ExchangeCodec.readResponseHeaders(false);
            Intrinsics.checkNotNull(readResponseHeaders);
            Response build = readResponseHeaders.request(request).build();
            http1ExchangeCodec.skipConnectBody(build);
            int code = build.code();
            if (code != 200) {
                if (code == 407) {
                    Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                    if (authenticate == null) {
                        throw new IOException("Failed to authenticate with proxy");
                    } else if (StringsKt.equals("close", Response.header$default(build, "Connection", (String) null, 2, (Object) null), true)) {
                        return authenticate;
                    } else {
                        request = authenticate;
                    }
                } else {
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
                }
            } else if (bufferedSource.getBuffer().exhausted() && bufferedSink.getBuffer().exhausted()) {
                return null;
            } else {
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
        }
    }

    private final Request createTunnelRequest() throws IOException {
        Request build = new Request.Builder().url(this.route.address().url()).method(FirebasePerformance.HttpMethod.CONNECT, (RequestBody) null).header("Host", Util.toHostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Util.userAgent).build();
        Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, new Response.Builder().request(build).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1).receivedResponseAtMillis(-1).header("Proxy-Authenticate", "OkHttp-Preemptive").build());
        return authenticate != null ? authenticate : build;
    }

    private final boolean routeMatchesAny(List<Route> list) {
        Iterable<Route> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (Route route2 : iterable) {
            Proxy.Type type = route2.proxy().type();
            Proxy.Type type2 = Proxy.Type.DIRECT;
            if (type == type2 && this.route.proxy().type() == type2 && Intrinsics.areEqual((Object) this.route.socketAddress(), (Object) route2.socketAddress())) {
                return true;
            }
        }
        return false;
    }

    private final boolean certificateSupportHost(HttpUrl httpUrl, Handshake handshake2) {
        List<Certificate> peerCertificates = handshake2.peerCertificates();
        if (peerCertificates.isEmpty()) {
            return false;
        }
        OkHostnameVerifier okHostnameVerifier = OkHostnameVerifier.INSTANCE;
        String host = httpUrl.host();
        Certificate certificate = peerCertificates.get(0);
        if (certificate != null) {
            return okHostnameVerifier.verify(host, (X509Certificate) certificate);
        }
        throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
    }

    public final ExchangeCodec newCodec$okhttp(OkHttpClient okHttpClient, RealInterceptorChain realInterceptorChain) throws SocketException {
        Intrinsics.checkNotNullParameter(okHttpClient, "client");
        Intrinsics.checkNotNullParameter(realInterceptorChain, "chain");
        Socket socket2 = this.socket;
        Intrinsics.checkNotNull(socket2);
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        Http2Connection http2Connection2 = this.http2Connection;
        if (http2Connection2 != null) {
            return new Http2ExchangeCodec(okHttpClient, this, realInterceptorChain, http2Connection2);
        }
        socket2.setSoTimeout(realInterceptorChain.readTimeoutMillis());
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        bufferedSource.timeout().timeout((long) realInterceptorChain.getReadTimeoutMillis$okhttp(), timeUnit);
        bufferedSink.timeout().timeout((long) realInterceptorChain.getWriteTimeoutMillis$okhttp(), timeUnit);
        return new Http1ExchangeCodec(okHttpClient, this, bufferedSource, bufferedSink);
    }

    private final boolean supportsUrl(HttpUrl httpUrl) {
        Handshake handshake2;
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            HttpUrl url = this.route.address().url();
            if (httpUrl.port() != url.port()) {
                return false;
            }
            if (Intrinsics.areEqual((Object) httpUrl.host(), (Object) url.host())) {
                return true;
            }
            if (this.noCoalescedConnections || (handshake2 = this.handshake) == null) {
                return false;
            }
            Intrinsics.checkNotNull(handshake2);
            if (certificateSupportHost(httpUrl, handshake2)) {
                return true;
            }
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append(" MUST hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }

    public final boolean isEligible$okhttp(Address address, List<Route> list) {
        Intrinsics.checkNotNullParameter(address, "address");
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
            sb.append(currentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        } else if (this.calls.size() >= this.allocationLimit || this.noNewExchanges || !this.route.address().equalsNonHost$okhttp(address)) {
            return false;
        } else {
            if (Intrinsics.areEqual((Object) address.url().host(), (Object) route().address().url().host())) {
                return true;
            }
            if (this.http2Connection == null || list == null || !routeMatchesAny(list) || address.hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
                return false;
            }
            try {
                CertificatePinner certificatePinner = address.certificatePinner();
                Intrinsics.checkNotNull(certificatePinner);
                String host = address.url().host();
                Handshake handshake2 = handshake();
                Intrinsics.checkNotNull(handshake2);
                certificatePinner.check(host, (List<? extends Certificate>) handshake2.peerCertificates());
                return true;
            } catch (SSLPeerUnverifiedException unused) {
                return false;
            }
        }
    }

    public final boolean isHealthy(boolean z) {
        long j;
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            long nanoTime = System.nanoTime();
            Socket socket2 = this.rawSocket;
            Intrinsics.checkNotNull(socket2);
            Socket socket3 = this.socket;
            Intrinsics.checkNotNull(socket3);
            BufferedSource bufferedSource = this.source;
            Intrinsics.checkNotNull(bufferedSource);
            if (socket2.isClosed() || socket3.isClosed() || socket3.isInputShutdown() || socket3.isOutputShutdown()) {
                return false;
            }
            Http2Connection http2Connection2 = this.http2Connection;
            if (http2Connection2 != null) {
                return http2Connection2.isHealthy(nanoTime);
            }
            synchronized (this) {
                j = nanoTime - this.idleAtNs;
            }
            if (j < IDLE_CONNECTION_HEALTHY_NS || !z) {
                return true;
            }
            return Util.isHealthy(socket3, bufferedSource);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append(" MUST NOT hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }

    public final RealWebSocket.Streams newWebSocketStreams$okhttp(Exchange exchange) throws SocketException {
        Intrinsics.checkNotNullParameter(exchange, "exchange");
        Socket socket2 = this.socket;
        Intrinsics.checkNotNull(socket2);
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        socket2.setSoTimeout(0);
        noNewExchanges$okhttp();
        return new RealConnection$newWebSocketStreams$1(exchange, bufferedSource, bufferedSink, true, bufferedSource, bufferedSink);
    }

    public Route route() {
        return this.route;
    }

    public final void cancel() {
        Socket socket2 = this.rawSocket;
        if (socket2 != null) {
            Util.closeQuietly(socket2);
        }
    }

    public Socket socket() {
        Socket socket2 = this.socket;
        Intrinsics.checkNotNull(socket2);
        return socket2;
    }

    public void onStream(Http2Stream http2Stream) throws IOException {
        Intrinsics.checkNotNullParameter(http2Stream, "stream");
        http2Stream.close(ErrorCode.REFUSED_STREAM, (IOException) null);
    }

    public synchronized void onSettings(Http2Connection http2Connection2, Settings settings) {
        Intrinsics.checkNotNullParameter(http2Connection2, "connection");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.allocationLimit = settings.getMaxConcurrentStreams();
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public final void connectFailed$okhttp(OkHttpClient okHttpClient, Route route2, IOException iOException) {
        Intrinsics.checkNotNullParameter(okHttpClient, "client");
        Intrinsics.checkNotNullParameter(route2, "failedRoute");
        Intrinsics.checkNotNullParameter(iOException, "failure");
        if (route2.proxy().type() != Proxy.Type.DIRECT) {
            Address address = route2.address();
            address.proxySelector().connectFailed(address.url().uri(), route2.proxy().address(), iOException);
        }
        okHttpClient.getRouteDatabase().failed(route2);
    }

    public final synchronized void trackFailure$okhttp(RealCall realCall, IOException iOException) {
        try {
            Intrinsics.checkNotNullParameter(realCall, "call");
            if (iOException instanceof StreamResetException) {
                if (((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
                    int i = this.refusedStreamCount + 1;
                    this.refusedStreamCount = i;
                    if (i > 1) {
                        this.noNewExchanges = true;
                        this.routeFailureCount++;
                    }
                } else if (((StreamResetException) iOException).errorCode != ErrorCode.CANCEL || !realCall.isCanceled()) {
                    this.noNewExchanges = true;
                    this.routeFailureCount++;
                }
            } else if (!isMultiplexed$okhttp() || (iOException instanceof ConnectionShutdownException)) {
                this.noNewExchanges = true;
                if (this.successCount == 0) {
                    if (iOException != null) {
                        connectFailed$okhttp(realCall.getClient(), this.route, iOException);
                    }
                    this.routeFailureCount++;
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public Protocol protocol() {
        Protocol protocol2 = this.protocol;
        Intrinsics.checkNotNull(protocol2);
        return protocol2;
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.route.address().url().host());
        sb.append(':');
        sb.append(this.route.address().url().port());
        sb.append(',');
        sb.append(" proxy=");
        sb.append(this.route.proxy());
        sb.append(" hostAddress=");
        sb.append(this.route.socketAddress());
        sb.append(" cipherSuite=");
        Handshake handshake2 = this.handshake;
        if (handshake2 == null || (obj = handshake2.cipherSuite()) == null) {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RealConnection newTestConnection(RealConnectionPool realConnectionPool, Route route, Socket socket, long j) {
            Intrinsics.checkNotNullParameter(realConnectionPool, "connectionPool");
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(socket, "socket");
            RealConnection realConnection = new RealConnection(realConnectionPool, route);
            realConnection.socket = socket;
            realConnection.setIdleAtNs$okhttp(j);
            return realConnection;
        }
    }
}
