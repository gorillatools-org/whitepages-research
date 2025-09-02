package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.ChannelResult;

abstract /* synthetic */ class ChannelsKt__ChannelsKt {
    public static final Object trySendBlocking(SendChannel sendChannel, Object obj) {
        Object r0 = sendChannel.m923trySendJP2dKIU(obj);
        if (r0 instanceof ChannelResult.Failed) {
            return ((ChannelResult) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new ChannelsKt__ChannelsKt$trySendBlocking$2(sendChannel, obj, (Continuation) null), 1, (Object) null)).m914unboximpl();
        }
        Unit unit = (Unit) r0;
        return ChannelResult.Companion.m917successJP2dKIU(Unit.INSTANCE);
    }
}
