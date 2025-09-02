package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.Closeable;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

class CxxInspectorPackagerConnection implements IInspectorPackagerConnection {
    @DoNotStrip
    private final HybridData mHybridData;

    private interface IWebSocket extends Closeable {
        void close();

        void send(String str);
    }

    private static native HybridData initHybrid(String str, String str2, String str3, DelegateImpl delegateImpl);

    public native void closeQuietly();

    public native void connect();

    public native void sendEventToAllConnections(String str);

    static {
        DevSupportSoLoader.staticInit();
    }

    public CxxInspectorPackagerConnection(String str, String str2, String str3) {
        this.mHybridData = initHybrid(str, str2, str3, new DelegateImpl());
    }

    @DoNotStrip
    private static class WebSocketDelegate implements Closeable {
        private final HybridData mHybridData;

        public native void didClose();

        public native void didFailWithError(Integer num, String str);

        public native void didReceiveMessage(String str);

        public void close() {
            this.mHybridData.resetNative();
        }

        @DoNotStrip
        private WebSocketDelegate(HybridData hybridData) {
            this.mHybridData = hybridData;
        }
    }

    private static class DelegateImpl {
        private final Handler mHandler;
        private final OkHttpClient mHttpClient;

        private DelegateImpl() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            this.mHttpClient = builder.connectTimeout(10, timeUnit).writeTimeout(10, timeUnit).readTimeout(0, TimeUnit.MINUTES).build();
            this.mHandler = new Handler(Looper.getMainLooper());
        }

        @DoNotStrip
        public IWebSocket connectWebSocket(String str, final WebSocketDelegate webSocketDelegate) {
            final WebSocket newWebSocket = this.mHttpClient.newWebSocket(new Request.Builder().url(str).build(), new WebSocketListener() {
                public void onFailure(WebSocket webSocket, final Throwable th, Response response) {
                    DelegateImpl.this.scheduleCallback(new Runnable() {
                        public void run() {
                            String message = th.getMessage();
                            WebSocketDelegate webSocketDelegate = webSocketDelegate;
                            if (message == null) {
                                message = "<Unknown error>";
                            }
                            webSocketDelegate.didFailWithError((Integer) null, message);
                            webSocketDelegate.close();
                        }
                    }, 0);
                }

                public void onMessage(WebSocket webSocket, final String str) {
                    DelegateImpl.this.scheduleCallback(new Runnable() {
                        public void run() {
                            webSocketDelegate.didReceiveMessage(str);
                        }
                    }, 0);
                }

                public void onClosed(WebSocket webSocket, int i, String str) {
                    DelegateImpl.this.scheduleCallback(new Runnable() {
                        public void run() {
                            webSocketDelegate.didClose();
                            webSocketDelegate.close();
                        }
                    }, 0);
                }
            });
            return new IWebSocket() {
                public void send(String str) {
                    newWebSocket.send(str);
                }

                public void close() {
                    newWebSocket.close(1000, "End of session");
                }
            };
        }

        @DoNotStrip
        public void scheduleCallback(Runnable runnable, long j) {
            this.mHandler.postDelayed(runnable, j);
        }
    }
}
