package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.channels.SendChannel;

final class ProducerCoroutine extends ChannelCoroutine implements ProducerScope {
    public ProducerCoroutine(CoroutineContext coroutineContext, Channel channel) {
        super(coroutineContext, channel, true, true);
    }

    public boolean isActive() {
        return super.isActive();
    }

    /* access modifiers changed from: protected */
    public void onCompleted(Unit unit) {
        SendChannel.DefaultImpls.close$default(get_channel(), (Throwable) null, 1, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Throwable th, boolean z) {
        if (!get_channel().close(th) && !z) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), th);
        }
    }
}
