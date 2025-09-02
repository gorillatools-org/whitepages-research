package okhttp3.internal.http2;

import okhttp3.internal.concurrent.Task;
import okhttp3.internal.http2.Http2Connection;

public final class Http2Connection$ReaderRunnable$settings$$inlined$execute$1 extends Task {
    final /* synthetic */ boolean $cancelable;
    final /* synthetic */ boolean $clearPrevious$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ Settings $settings$inlined;
    final /* synthetic */ Http2Connection.ReaderRunnable this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Http2Connection$ReaderRunnable$settings$$inlined$execute$1(String str, boolean z, String str2, boolean z2, Http2Connection.ReaderRunnable readerRunnable, boolean z3, Settings settings) {
        super(str2, z2);
        this.$name = str;
        this.$cancelable = z;
        this.this$0 = readerRunnable;
        this.$clearPrevious$inlined = z3;
        this.$settings$inlined = settings;
    }

    public long runOnce() {
        this.this$0.applyAndAckSettings(this.$clearPrevious$inlined, this.$settings$inlined);
        return -1;
    }
}
