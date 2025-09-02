package com.facebook.react.modules.websocket;

import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.CustomClientBuilder;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.salesforce.marketingcloud.config.a;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okio.ByteString;

@ReactModule(name = "WebSocketModule")
public final class WebSocketModule extends NativeWebSocketModuleSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "WebSocketModule";
    /* access modifiers changed from: private */
    public static CustomClientBuilder customClientBuilder;
    /* access modifiers changed from: private */
    public final Map<Integer, ContentHandler> contentHandlers = new ConcurrentHashMap();
    private final ForwardingCookieHandler cookieHandler = new ForwardingCookieHandler();
    /* access modifiers changed from: private */
    public final Map<Integer, WebSocket> webSocketConnections = new ConcurrentHashMap();

    public interface ContentHandler {
        void onMessage(String str, WritableMap writableMap);

        void onMessage(ByteString byteString, WritableMap writableMap);
    }

    public static final void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        Companion.setCustomClientBuilder(customClientBuilder2);
    }

    public void addListener(String str) {
        Intrinsics.checkNotNullParameter(str, a.h);
    }

    public void removeListeners(double d) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
    }

    public void invalidate() {
        for (WebSocket close : this.webSocketConnections.values()) {
            close.close(1001, (String) null);
        }
        this.webSocketConnections.clear();
        this.contentHandlers.clear();
    }

    /* access modifiers changed from: private */
    public final void sendEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext.hasActiveReactInstance()) {
            reactApplicationContext.emitDeviceEvent(str, writableMap);
        }
    }

    public final void setContentHandler(int i, ContentHandler contentHandler) {
        if (contentHandler != null) {
            this.contentHandlers.put(Integer.valueOf(i), contentHandler);
            return;
        }
        this.contentHandlers.remove(Integer.valueOf(i));
    }

    public void connect(String str, ReadableArray readableArray, ReadableMap readableMap, double d) {
        boolean z;
        Intrinsics.checkNotNullParameter(str, "url");
        int i = (int) d;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        OkHttpClient.Builder readTimeout = builder.connectTimeout(10, timeUnit).writeTimeout(10, timeUnit).readTimeout(0, TimeUnit.MINUTES);
        Companion.applyCustomBuilder(readTimeout);
        OkHttpClient build = readTimeout.build();
        Request.Builder url = new Request.Builder().tag(Integer.valueOf(i)).url(str);
        String cookie = getCookie(str);
        if (cookie != null) {
            url.addHeader("Cookie", cookie);
        }
        if (readableMap != null && readableMap.hasKey("headers") && readableMap.getType("headers") == ReadableType.Map) {
            ReadableMap map = readableMap.getMap("headers");
            if (map != null) {
                ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                z = false;
                while (keySetIterator.hasNextKey()) {
                    String nextKey = keySetIterator.nextKey();
                    if (ReadableType.String == map.getType(nextKey)) {
                        if (StringsKt.equals(nextKey, "origin", true)) {
                            z = true;
                        }
                        String string = map.getString(nextKey);
                        if (string != null) {
                            url.addHeader(nextKey, string);
                        } else {
                            throw new IllegalStateException(("value for name " + nextKey + " == null").toString());
                        }
                    } else {
                        FLog.w(ReactConstants.TAG, "Ignoring: requested " + nextKey + ", value not a string");
                    }
                }
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        } else {
            z = false;
        }
        if (!z) {
            url.addHeader("origin", Companion.getDefaultOrigin(str));
        }
        if (readableArray != null && readableArray.size() > 0) {
            StringBuilder sb = new StringBuilder("");
            int size = readableArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                String string2 = readableArray.getString(i2);
                String obj = string2 != null ? StringsKt.trim(string2).toString() : null;
                if (!(obj == null || obj.length() == 0) && !StringsKt.contains$default((CharSequence) obj, (CharSequence) ",", false, 2, (Object) null)) {
                    sb.append(obj);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.replace(sb.length() - 1, sb.length(), "");
                String sb2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                url.addHeader("Sec-WebSocket-Protocol", sb2);
            }
        }
        build.newWebSocket(url.build(), new WebSocketModule$connect$2(this, i));
        build.m1024deprecated_dispatcher().m971deprecated_executorService().shutdown();
    }

    public void close(double d, String str, double d2) {
        int i = (int) d2;
        WebSocket webSocket = this.webSocketConnections.get(Integer.valueOf(i));
        if (webSocket != null) {
            try {
                webSocket.close((int) d, str);
                this.webSocketConnections.remove(Integer.valueOf(i));
                this.contentHandlers.remove(Integer.valueOf(i));
            } catch (Exception e) {
                FLog.e(ReactConstants.TAG, "Could not close WebSocket connection for id " + i, (Throwable) e);
            }
        }
    }

    public void send(String str, double d) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        int i = (int) d;
        WebSocket webSocket = this.webSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
            createMap.putInt("id", i);
            createMap.putString(StackTraceHelper.MESSAGE_KEY, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.webSocketConnections.remove(Integer.valueOf(i));
            this.contentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void sendBinary(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "base64String");
        int i = (int) d;
        WebSocket webSocket = this.webSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
            createMap.putInt("id", i);
            createMap.putString(StackTraceHelper.MESSAGE_KEY, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.webSocketConnections.remove(Integer.valueOf(i));
            this.contentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            ByteString r3 = ByteString.Companion.m1066deprecated_decodeBase64(str);
            if (r3 != null) {
                webSocket.send(r3);
                return;
            }
            throw new IllegalStateException("bytes == null");
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public final void sendBinary(ByteString byteString, int i) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        WebSocket webSocket = this.webSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
            createMap.putInt("id", i);
            createMap.putString(StackTraceHelper.MESSAGE_KEY, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.webSocketConnections.remove(Integer.valueOf(i));
            this.contentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(byteString);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void ping(double d) {
        int i = (int) d;
        WebSocket webSocket = this.webSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
            createMap.putInt("id", i);
            createMap.putString(StackTraceHelper.MESSAGE_KEY, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.webSocketConnections.remove(Integer.valueOf(i));
            this.contentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public final void notifyWebSocketFailed(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", i);
        createMap.putString(StackTraceHelper.MESSAGE_KEY, str);
        Intrinsics.checkNotNull(createMap);
        sendEvent("websocketFailed", createMap);
    }

    private final String getCookie(String str) {
        try {
            List list = this.cookieHandler.get(new URI(Companion.getDefaultOrigin(str)), new HashMap()).get("Cookie");
            Collection collection = list;
            if (collection == null) {
                return null;
            }
            if (collection.isEmpty()) {
                return null;
            }
            return (String) list.get(0);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        } catch (IOException unused2) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void setCustomClientBuilder(CustomClientBuilder customClientBuilder) {
            WebSocketModule.customClientBuilder = customClientBuilder;
        }

        /* access modifiers changed from: private */
        public final void applyCustomBuilder(OkHttpClient.Builder builder) {
            CustomClientBuilder access$getCustomClientBuilder$cp = WebSocketModule.customClientBuilder;
            if (access$getCustomClientBuilder$cp != null) {
                access$getCustomClientBuilder$cp.apply(builder);
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
            if (r1.equals("ws") == false) goto L_0x004d;
         */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0058 A[SYNTHETIC, Splitter:B:30:0x0058] */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0079 A[Catch:{ URISyntaxException -> 0x0092 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.String getDefaultOrigin(java.lang.String r7) {
            /*
                r6 = this;
                java.net.URI r0 = new java.net.URI     // Catch:{ URISyntaxException -> 0x0092 }
                r0.<init>(r7)     // Catch:{ URISyntaxException -> 0x0092 }
                java.lang.String r1 = r0.getScheme()     // Catch:{ URISyntaxException -> 0x0092 }
                if (r1 == 0) goto L_0x004d
                int r2 = r1.hashCode()     // Catch:{ URISyntaxException -> 0x0092 }
                r3 = 3804(0xedc, float:5.33E-42)
                java.lang.String r4 = "http"
                if (r2 == r3) goto L_0x0045
                r3 = 118039(0x1cd17, float:1.65408E-40)
                java.lang.String r5 = "https"
                if (r2 == r3) goto L_0x003a
                r3 = 3213448(0x310888, float:4.503E-39)
                if (r2 == r3) goto L_0x002e
                r3 = 99617003(0x5f008eb, float:2.2572767E-35)
                if (r2 == r3) goto L_0x0027
                goto L_0x004d
            L_0x0027:
                boolean r1 = r1.equals(r5)     // Catch:{ URISyntaxException -> 0x0092 }
                if (r1 != 0) goto L_0x0035
                goto L_0x004d
            L_0x002e:
                boolean r1 = r1.equals(r4)     // Catch:{ URISyntaxException -> 0x0092 }
                if (r1 != 0) goto L_0x0035
                goto L_0x004d
            L_0x0035:
                java.lang.String r4 = r0.getScheme()     // Catch:{ URISyntaxException -> 0x0092 }
                goto L_0x004f
            L_0x003a:
                java.lang.String r2 = "wss"
                boolean r1 = r1.equals(r2)     // Catch:{ URISyntaxException -> 0x0092 }
                if (r1 != 0) goto L_0x0043
                goto L_0x004d
            L_0x0043:
                r4 = r5
                goto L_0x004f
            L_0x0045:
                java.lang.String r2 = "ws"
                boolean r1 = r1.equals(r2)     // Catch:{ URISyntaxException -> 0x0092 }
                if (r1 != 0) goto L_0x004f
            L_0x004d:
                java.lang.String r4 = ""
            L_0x004f:
                int r1 = r0.getPort()     // Catch:{ URISyntaxException -> 0x0092 }
                r2 = -1
                java.lang.String r3 = "format(...)"
                if (r1 == r2) goto L_0x0079
                kotlin.jvm.internal.StringCompanionObject r1 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch:{ URISyntaxException -> 0x0092 }
                java.lang.String r1 = "%s://%s:%s"
                java.lang.String r2 = r0.getHost()     // Catch:{ URISyntaxException -> 0x0092 }
                int r0 = r0.getPort()     // Catch:{ URISyntaxException -> 0x0092 }
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ URISyntaxException -> 0x0092 }
                java.lang.Object[] r0 = new java.lang.Object[]{r4, r2, r0}     // Catch:{ URISyntaxException -> 0x0092 }
                r2 = 3
                java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r2)     // Catch:{ URISyntaxException -> 0x0092 }
                java.lang.String r0 = java.lang.String.format(r1, r0)     // Catch:{ URISyntaxException -> 0x0092 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)     // Catch:{ URISyntaxException -> 0x0092 }
                goto L_0x0091
            L_0x0079:
                kotlin.jvm.internal.StringCompanionObject r1 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch:{ URISyntaxException -> 0x0092 }
                java.lang.String r1 = "%s://%s"
                java.lang.String r0 = r0.getHost()     // Catch:{ URISyntaxException -> 0x0092 }
                java.lang.Object[] r0 = new java.lang.Object[]{r4, r0}     // Catch:{ URISyntaxException -> 0x0092 }
                r2 = 2
                java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r2)     // Catch:{ URISyntaxException -> 0x0092 }
                java.lang.String r0 = java.lang.String.format(r1, r0)     // Catch:{ URISyntaxException -> 0x0092 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)     // Catch:{ URISyntaxException -> 0x0092 }
            L_0x0091:
                return r0
            L_0x0092:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Unable to set "
                r1.append(r2)
                r1.append(r7)
                java.lang.String r7 = " as default origin header"
                r1.append(r7)
                java.lang.String r7 = r1.toString()
                r0.<init>(r7)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.websocket.WebSocketModule.Companion.getDefaultOrigin(java.lang.String):java.lang.String");
        }
    }
}
