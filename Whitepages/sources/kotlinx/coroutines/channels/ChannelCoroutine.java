package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.JobSupport;

public abstract class ChannelCoroutine extends AbstractCoroutine implements Channel {
    private final Channel _channel;

    public boolean close(Throwable th) {
        return this._channel.close(th);
    }

    public void invokeOnClose(Function1 function1) {
        this._channel.invokeOnClose(function1);
    }

    public boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    public ChannelIterator iterator() {
        return this._channel.iterator();
    }

    public Object receive(Continuation continuation) {
        return this._channel.receive(continuation);
    }

    public Object send(Object obj, Continuation continuation) {
        return this._channel.send(obj, continuation);
    }

    /* renamed from: tryReceive-PtdJZtk  reason: not valid java name */
    public Object m903tryReceivePtdJZtk() {
        return this._channel.m922tryReceivePtdJZtk();
    }

    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public Object m904trySendJP2dKIU(Object obj) {
        return this._channel.m923trySendJP2dKIU(obj);
    }

    /* access modifiers changed from: protected */
    public final Channel get_channel() {
        return this._channel;
    }

    public ChannelCoroutine(CoroutineContext coroutineContext, Channel channel, boolean z, boolean z2) {
        super(coroutineContext, z, z2);
        this._channel = channel;
    }

    public final void cancel(CancellationException cancellationException) {
        if (!isCancelled()) {
            if (cancellationException == null) {
                cancellationException = new JobCancellationException(cancellationExceptionMessage(), (Throwable) null, this);
            }
            cancelInternal(cancellationException);
        }
    }

    public void cancelInternal(Throwable th) {
        CancellationException cancellationException$default = JobSupport.toCancellationException$default(this, th, (String) null, 1, (Object) null);
        this._channel.cancel(cancellationException$default);
        cancelCoroutine(cancellationException$default);
    }
}
