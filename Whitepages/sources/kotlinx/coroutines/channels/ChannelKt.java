package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function1;

public abstract class ChannelKt {
    public static /* synthetic */ Channel Channel$default(int i, BufferOverflow bufferOverflow, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        return Channel(i, bufferOverflow, function1);
    }

    public static final Channel Channel(int i, BufferOverflow bufferOverflow, Function1 function1) {
        Channel conflatedBufferedChannel;
        if (i != -2) {
            if (i != -1) {
                if (i != 0) {
                    if (i == Integer.MAX_VALUE) {
                        return new BufferedChannel(Integer.MAX_VALUE, function1);
                    }
                    if (bufferOverflow == BufferOverflow.SUSPEND) {
                        return new BufferedChannel(i, function1);
                    }
                    return new ConflatedBufferedChannel(i, bufferOverflow, function1);
                } else if (bufferOverflow == BufferOverflow.SUSPEND) {
                    conflatedBufferedChannel = new BufferedChannel(0, function1);
                } else {
                    conflatedBufferedChannel = new ConflatedBufferedChannel(1, bufferOverflow, function1);
                }
            } else if (bufferOverflow == BufferOverflow.SUSPEND) {
                return new ConflatedBufferedChannel(1, BufferOverflow.DROP_OLDEST, function1);
            } else {
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
            }
        } else if (bufferOverflow == BufferOverflow.SUSPEND) {
            conflatedBufferedChannel = new BufferedChannel(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core(), function1);
        } else {
            conflatedBufferedChannel = new ConflatedBufferedChannel(1, bufferOverflow, function1);
        }
        return conflatedBufferedChannel;
    }
}
