package okhttp3.internal.http2;

import okhttp3.internal.concurrent.Task;
import okhttp3.internal.http2.Http2Connection;

public final class Http2Connection$ReaderRunnable$ping$$inlined$execute$1 extends Task {
    final /* synthetic */ boolean $cancelable;
    final /* synthetic */ String $name;
    final /* synthetic */ int $payload1$inlined;
    final /* synthetic */ int $payload2$inlined;
    final /* synthetic */ Http2Connection.ReaderRunnable this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Http2Connection$ReaderRunnable$ping$$inlined$execute$1(String str, boolean z, String str2, boolean z2, Http2Connection.ReaderRunnable readerRunnable, int i, int i2) {
        super(str2, z2);
        this.$name = str;
        this.$cancelable = z;
        this.this$0 = readerRunnable;
        this.$payload1$inlined = i;
        this.$payload2$inlined = i2;
    }

    public long runOnce() {
        this.this$0.this$0.writePing(true, this.$payload1$inlined, this.$payload2$inlined);
        return -1;
    }
}
