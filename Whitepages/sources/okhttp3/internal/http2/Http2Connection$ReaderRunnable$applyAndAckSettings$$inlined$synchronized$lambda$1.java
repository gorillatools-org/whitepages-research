package okhttp3.internal.http2;

import kotlin.jvm.internal.Ref$LongRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.http2.Http2Connection;

public final class Http2Connection$ReaderRunnable$applyAndAckSettings$$inlined$synchronized$lambda$1 extends Task {
    final /* synthetic */ boolean $cancelable;
    final /* synthetic */ boolean $clearPrevious$inlined;
    final /* synthetic */ Ref$LongRef $delta$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ Ref$ObjectRef $newPeerSettings$inlined;
    final /* synthetic */ Settings $settings$inlined;
    final /* synthetic */ Ref$ObjectRef $streamsToNotify$inlined;
    final /* synthetic */ Http2Connection.ReaderRunnable this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Http2Connection$ReaderRunnable$applyAndAckSettings$$inlined$synchronized$lambda$1(String str, boolean z, String str2, boolean z2, Http2Connection.ReaderRunnable readerRunnable, Ref$ObjectRef ref$ObjectRef, boolean z3, Settings settings, Ref$LongRef ref$LongRef, Ref$ObjectRef ref$ObjectRef2) {
        super(str2, z2);
        this.$name = str;
        this.$cancelable = z;
        this.this$0 = readerRunnable;
        this.$newPeerSettings$inlined = ref$ObjectRef;
        this.$clearPrevious$inlined = z3;
        this.$settings$inlined = settings;
        this.$delta$inlined = ref$LongRef;
        this.$streamsToNotify$inlined = ref$ObjectRef2;
    }

    public long runOnce() {
        this.this$0.this$0.getListener$okhttp().onSettings(this.this$0.this$0, (Settings) this.$newPeerSettings$inlined.element);
        return -1;
    }
}
