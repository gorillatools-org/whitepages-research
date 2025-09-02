package com.facebook.react.modules.websocket;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public final class WebSocketModule$connect$2 extends WebSocketListener {
    final /* synthetic */ int $id;
    final /* synthetic */ WebSocketModule this$0;

    WebSocketModule$connect$2(WebSocketModule webSocketModule, int i) {
        this.this$0 = webSocketModule;
        this.$id = i;
    }

    public void onOpen(WebSocket webSocket, Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, "response");
        this.this$0.webSocketConnections.put(Integer.valueOf(this.$id), webSocket);
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.$id);
        createMap.putString("protocol", response.header("Sec-WebSocket-Protocol", ""));
        WebSocketModule webSocketModule = this.this$0;
        Intrinsics.checkNotNull(createMap);
        webSocketModule.sendEvent("websocketOpen", createMap);
    }

    public void onClosing(WebSocket webSocket, int i, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "websocket");
        Intrinsics.checkNotNullParameter(str, "reason");
        webSocket.close(i, str);
    }

    public void onClosed(WebSocket webSocket, int i, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.$id);
        createMap.putInt("code", i);
        createMap.putString("reason", str);
        WebSocketModule webSocketModule = this.this$0;
        Intrinsics.checkNotNull(createMap);
        webSocketModule.sendEvent("websocketClosed", createMap);
    }

    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(th, "t");
        this.this$0.notifyWebSocketFailed(this.$id, th.getMessage());
    }

    public void onMessage(WebSocket webSocket, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "text");
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.$id);
        createMap.putString("type", "text");
        WebSocketModule.ContentHandler contentHandler = (WebSocketModule.ContentHandler) this.this$0.contentHandlers.get(Integer.valueOf(this.$id));
        if (contentHandler != null) {
            Intrinsics.checkNotNull(createMap);
            contentHandler.onMessage(str, createMap);
        } else {
            createMap.putString(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, str);
        }
        WebSocketModule webSocketModule = this.this$0;
        Intrinsics.checkNotNull(createMap);
        webSocketModule.sendEvent("websocketMessage", createMap);
    }

    public void onMessage(WebSocket webSocket, ByteString byteString) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.$id);
        createMap.putString("type", "binary");
        WebSocketModule.ContentHandler contentHandler = (WebSocketModule.ContentHandler) this.this$0.contentHandlers.get(Integer.valueOf(this.$id));
        if (contentHandler != null) {
            Intrinsics.checkNotNull(createMap);
            contentHandler.onMessage(byteString, createMap);
        } else {
            createMap.putString(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, byteString.base64());
        }
        WebSocketModule webSocketModule = this.this$0;
        Intrinsics.checkNotNull(createMap);
        webSocketModule.sendEvent("websocketMessage", createMap);
    }
}
