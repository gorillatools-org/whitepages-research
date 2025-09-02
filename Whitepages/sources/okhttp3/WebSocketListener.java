package okhttp3;

import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;

public abstract class WebSocketListener {
    public void onClosed(WebSocket webSocket, int i, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
    }

    public void onClosing(WebSocket webSocket, int i, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
    }

    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(th, "t");
    }

    public void onMessage(WebSocket webSocket, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "text");
    }

    public void onMessage(WebSocket webSocket, ByteString byteString) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(byteString, "bytes");
    }

    public void onOpen(WebSocket webSocket, Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, "response");
    }
}
