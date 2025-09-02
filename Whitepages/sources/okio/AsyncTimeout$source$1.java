package okio;

import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class AsyncTimeout$source$1 implements Source {
    final /* synthetic */ Source $source;
    final /* synthetic */ AsyncTimeout this$0;

    AsyncTimeout$source$1(AsyncTimeout asyncTimeout, Source source) {
        this.this$0 = asyncTimeout;
        this.$source = source;
    }

    public long read(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        AsyncTimeout asyncTimeout = this.this$0;
        Source source = this.$source;
        asyncTimeout.enter();
        try {
            long read = source.read(buffer, j);
            if (!asyncTimeout.exit()) {
                return read;
            }
            throw asyncTimeout.access$newTimeoutException((IOException) null);
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
        Source source = this.$source;
        asyncTimeout.enter();
        try {
            source.close();
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
        return "AsyncTimeout.source(" + this.$source + ')';
    }
}
