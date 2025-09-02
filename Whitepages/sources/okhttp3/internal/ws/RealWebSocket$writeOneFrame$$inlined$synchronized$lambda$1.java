package okhttp3.internal.ws;

import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import okhttp3.internal.concurrent.Task;
import okio.ByteString;

public final class RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1 extends Task {
    final /* synthetic */ boolean $cancelable;
    final /* synthetic */ Ref$ObjectRef $messageOrClose$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ ByteString $pong$inlined;
    final /* synthetic */ Ref$ObjectRef $readerToClose$inlined;
    final /* synthetic */ Ref$IntRef $receivedCloseCode$inlined;
    final /* synthetic */ Ref$ObjectRef $receivedCloseReason$inlined;
    final /* synthetic */ Ref$ObjectRef $streamsToClose$inlined;
    final /* synthetic */ WebSocketWriter $writer$inlined;
    final /* synthetic */ Ref$ObjectRef $writerToClose$inlined;
    final /* synthetic */ RealWebSocket this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1(String str, boolean z, String str2, boolean z2, RealWebSocket realWebSocket, WebSocketWriter webSocketWriter, ByteString byteString, Ref$ObjectRef ref$ObjectRef, Ref$IntRef ref$IntRef, Ref$ObjectRef ref$ObjectRef2, Ref$ObjectRef ref$ObjectRef3, Ref$ObjectRef ref$ObjectRef4, Ref$ObjectRef ref$ObjectRef5) {
        super(str2, z2);
        this.$name = str;
        this.$cancelable = z;
        this.this$0 = realWebSocket;
        this.$writer$inlined = webSocketWriter;
        this.$pong$inlined = byteString;
        this.$messageOrClose$inlined = ref$ObjectRef;
        this.$receivedCloseCode$inlined = ref$IntRef;
        this.$receivedCloseReason$inlined = ref$ObjectRef2;
        this.$streamsToClose$inlined = ref$ObjectRef3;
        this.$readerToClose$inlined = ref$ObjectRef4;
        this.$writerToClose$inlined = ref$ObjectRef5;
    }

    public long runOnce() {
        this.this$0.cancel();
        return -1;
    }
}
