package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.ws.RealWebSocket;

public final class RealWebSocket$connect$1 implements Callback {
    final /* synthetic */ Request $request;
    final /* synthetic */ RealWebSocket this$0;

    RealWebSocket$connect$1(RealWebSocket realWebSocket, Request request) {
        this.this$0 = realWebSocket;
        this.$request = request;
    }

    public void onResponse(Call call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        Exchange exchange = response.exchange();
        try {
            this.this$0.checkUpgradeSuccess$okhttp(response, exchange);
            Intrinsics.checkNotNull(exchange);
            RealWebSocket.Streams newWebSocketStreams = exchange.newWebSocketStreams();
            WebSocketExtensions parse = WebSocketExtensions.Companion.parse(response.headers());
            this.this$0.extensions = parse;
            if (!this.this$0.isValid(parse)) {
                synchronized (this.this$0) {
                    this.this$0.messageAndCloseQueue.clear();
                    this.this$0.close(1010, "unexpected Sec-WebSocket-Extensions in response header");
                }
            }
            try {
                this.this$0.initReaderAndWriter(Util.okHttpName + " WebSocket " + this.$request.url().redact(), newWebSocketStreams);
                this.this$0.getListener$okhttp().onOpen(this.this$0, response);
                this.this$0.loopReader();
            } catch (Exception e) {
                this.this$0.failWebSocket(e, (Response) null);
            }
        } catch (IOException e2) {
            if (exchange != null) {
                exchange.webSocketUpgradeFailed();
            }
            this.this$0.failWebSocket(e2, response);
            Util.closeQuietly((Closeable) response);
        }
    }

    public void onFailure(Call call, IOException iOException) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(iOException, "e");
        this.this$0.failWebSocket(iOException, (Response) null);
    }
}
