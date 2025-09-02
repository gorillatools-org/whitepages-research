package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowCollector;

public final class SendingCollector implements FlowCollector {
    private final SendChannel channel;

    public SendingCollector(SendChannel sendChannel) {
        this.channel = sendChannel;
    }

    public Object emit(Object obj, Continuation continuation) {
        Object send = this.channel.send(obj, continuation);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }
}
