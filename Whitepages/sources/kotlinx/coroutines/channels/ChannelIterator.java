package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;

public interface ChannelIterator {
    Object hasNext(Continuation continuation);

    Object next();
}
