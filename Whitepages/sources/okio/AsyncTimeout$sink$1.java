package okio;

import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class AsyncTimeout$sink$1 implements Sink {
    final /* synthetic */ Sink $sink;
    final /* synthetic */ AsyncTimeout this$0;

    AsyncTimeout$sink$1(AsyncTimeout asyncTimeout, Sink sink) {
        this.this$0 = asyncTimeout;
        this.$sink = sink;
    }

    public void write(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "source");
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0, j);
        while (true) {
            long j2 = 0;
            if (j > 0) {
                Segment segment = buffer.head;
                Intrinsics.checkNotNull(segment);
                while (true) {
                    if (j2 >= 65536) {
                        break;
                    }
                    j2 += (long) (segment.limit - segment.pos);
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    } else {
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                    }
                }
                AsyncTimeout asyncTimeout = this.this$0;
                Sink sink = this.$sink;
                asyncTimeout.enter();
                try {
                    sink.write(buffer, j2);
                    Unit unit = Unit.INSTANCE;
                    if (!asyncTimeout.exit()) {
                        j -= j2;
                    } else {
                        throw asyncTimeout.access$newTimeoutException((IOException) null);
                    }
                } catch (IOException e) {
                    e = e;
                    if (asyncTimeout.exit()) {
                        e = asyncTimeout.access$newTimeoutException(e);
                    }
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            } else {
                return;
            }
        }
    }

    public void flush() {
        AsyncTimeout asyncTimeout = this.this$0;
        Sink sink = this.$sink;
        asyncTimeout.enter();
        try {
            sink.flush();
            Unit unit = Unit.INSTANCE;
            if (asyncTimeout.exit()) {
                throw asyncTimeout.access$newTimeoutException((IOException) null);
            }
        } catch (IOException e) {
            e = e;
            if (asyncTimeout.exit()) {
                e = asyncTimeout.access$newTimeoutException(e);
            }
            throw e;
        } finally {
            asyncTimeout.exit();
        }
    }

    public void close() {
        AsyncTimeout asyncTimeout = this.this$0;
        Sink sink = this.$sink;
        asyncTimeout.enter();
        try {
            sink.close();
            Unit unit = Unit.INSTANCE;
            if (asyncTimeout.exit()) {
                throw asyncTimeout.access$newTimeoutException((IOException) null);
            }
        } catch (IOException e) {
            e = e;
            if (asyncTimeout.exit()) {
                e = asyncTimeout.access$newTimeoutException(e);
            }
            throw e;
        } finally {
            asyncTimeout.exit();
        }
    }

    public AsyncTimeout timeout() {
        return this.this$0;
    }

    public String toString() {
        return "AsyncTimeout.sink(" + this.$sink + ')';
    }
}
