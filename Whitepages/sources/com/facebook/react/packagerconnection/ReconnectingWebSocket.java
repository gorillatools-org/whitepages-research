package com.facebook.react.packagerconnection;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public final class ReconnectingWebSocket extends WebSocketListener {
    private static final int RECONNECT_DELAY_MS = 2000;
    private static final String TAG = "ReconnectingWebSocket";
    private boolean mClosed = false;
    private ConnectionCallback mConnectionCallback;
    private final Handler mHandler;
    private MessageCallback mMessageCallback;
    private final OkHttpClient mOkHttpClient;
    private boolean mSuppressConnectionErrors;
    private final String mUrl;
    private WebSocket mWebSocket;

    public interface ConnectionCallback {
        void onConnected();

        void onDisconnected();
    }

    public interface MessageCallback {
        void onMessage(String str);

        void onMessage(ByteString byteString);
    }

    public ReconnectingWebSocket(String str, MessageCallback messageCallback, ConnectionCallback connectionCallback) {
        this.mUrl = str;
        this.mMessageCallback = messageCallback;
        this.mConnectionCallback = connectionCallback;
        this.mHandler = new Handler(Looper.getMainLooper());
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.mOkHttpClient = builder.connectTimeout(10, timeUnit).writeTimeout(10, timeUnit).readTimeout(0, TimeUnit.MINUTES).build();
    }

    public void connect() {
        if (!this.mClosed) {
            this.mOkHttpClient.newWebSocket(new Request.Builder().url(this.mUrl).build(), this);
            return;
        }
        throw new IllegalStateException("Can't connect closed client");
    }

    /* access modifiers changed from: private */
    public synchronized void delayedReconnect() {
        if (!this.mClosed) {
            connect();
        }
    }

    private void reconnect() {
        if (!this.mClosed) {
            if (!this.mSuppressConnectionErrors) {
                String str = TAG;
                FLog.w(str, "Couldn't connect to \"" + this.mUrl + "\", will silently retry");
                this.mSuppressConnectionErrors = true;
            }
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    ReconnectingWebSocket.this.delayedReconnect();
                }
            }, 2000);
            return;
        }
        throw new IllegalStateException("Can't reconnect closed client");
    }

    public void closeQuietly() {
        this.mClosed = true;
        closeWebSocketQuietly();
        this.mMessageCallback = null;
        ConnectionCallback connectionCallback = this.mConnectionCallback;
        if (connectionCallback != null) {
            connectionCallback.onDisconnected();
        }
    }

    private void closeWebSocketQuietly() {
        WebSocket webSocket = this.mWebSocket;
        if (webSocket != null) {
            try {
                webSocket.close(1000, "End of session");
            } catch (Exception unused) {
            }
            this.mWebSocket = null;
        }
    }

    private void abort(String str, Throwable th) {
        String str2 = TAG;
        FLog.e(str2, "Error occurred, shutting down websocket connection: " + str, th);
        closeWebSocketQuietly();
    }

    public synchronized void onOpen(WebSocket webSocket, Response response) {
        this.mWebSocket = webSocket;
        this.mSuppressConnectionErrors = false;
        ConnectionCallback connectionCallback = this.mConnectionCallback;
        if (connectionCallback != null) {
            connectionCallback.onConnected();
        }
    }

    public synchronized void onFailure(WebSocket webSocket, Throwable th, Response response) {
        try {
            if (this.mWebSocket != null) {
                abort("Websocket exception", th);
            }
            if (!this.mClosed) {
                ConnectionCallback connectionCallback = this.mConnectionCallback;
                if (connectionCallback != null) {
                    connectionCallback.onDisconnected();
                }
                reconnect();
            }
        } catch (Throwable th2) {
            while (true) {
                throw th2;
            }
        }
    }

    public synchronized void onMessage(WebSocket webSocket, String str) {
        MessageCallback messageCallback = this.mMessageCallback;
        if (messageCallback != null) {
            messageCallback.onMessage(str);
        }
    }

    public synchronized void onMessage(WebSocket webSocket, ByteString byteString) {
        MessageCallback messageCallback = this.mMessageCallback;
        if (messageCallback != null) {
            messageCallback.onMessage(byteString);
        }
    }

    public synchronized void onClosed(WebSocket webSocket, int i, String str) {
        try {
            this.mWebSocket = null;
            if (!this.mClosed) {
                ConnectionCallback connectionCallback = this.mConnectionCallback;
                if (connectionCallback != null) {
                    connectionCallback.onDisconnected();
                }
                reconnect();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void sendMessage(String str) throws IOException {
        WebSocket webSocket = this.mWebSocket;
        if (webSocket != null) {
            webSocket.send(str);
        } else {
            throw new ClosedChannelException();
        }
    }

    public synchronized void sendMessage(ByteString byteString) throws IOException {
        WebSocket webSocket = this.mWebSocket;
        if (webSocket != null) {
            webSocket.send(byteString);
        } else {
            throw new ClosedChannelException();
        }
    }
}
