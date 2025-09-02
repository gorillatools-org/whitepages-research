package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;

public interface ReceiveChannel {
    void cancel(CancellationException cancellationException);

    ChannelIterator iterator();

    Object receive(Continuation continuation);

    /* renamed from: tryReceive-PtdJZtk  reason: not valid java name */
    Object m922tryReceivePtdJZtk();
}
