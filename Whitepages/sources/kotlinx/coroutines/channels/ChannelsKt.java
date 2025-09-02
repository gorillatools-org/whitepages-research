package kotlinx.coroutines.channels;

public abstract class ChannelsKt {
    public static final void cancelConsumed(ReceiveChannel receiveChannel, Throwable th) {
        ChannelsKt__Channels_commonKt.cancelConsumed(receiveChannel, th);
    }

    public static final Object trySendBlocking(SendChannel sendChannel, Object obj) {
        return ChannelsKt__ChannelsKt.trySendBlocking(sendChannel, obj);
    }
}
