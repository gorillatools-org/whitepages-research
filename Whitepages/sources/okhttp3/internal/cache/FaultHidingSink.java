package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

public class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;
    private final Function1 onException;

    public final Function1 getOnException() {
        return this.onException;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FaultHidingSink(Sink sink, Function1 function1) {
        super(sink);
        Intrinsics.checkNotNullParameter(sink, "delegate");
        Intrinsics.checkNotNullParameter(function1, "onException");
        this.onException = function1;
    }

    public void write(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "source");
        if (this.hasErrors) {
            buffer.skip(j);
            return;
        }
        try {
            super.write(buffer, j);
        } catch (IOException e) {
            this.hasErrors = true;
            this.onException.invoke(e);
        }
    }

    public void flush() {
        if (!this.hasErrors) {
            try {
                super.flush();
            } catch (IOException e) {
                this.hasErrors = true;
                this.onException.invoke(e);
            }
        }
    }

    public void close() {
        if (!this.hasErrors) {
            try {
                super.close();
            } catch (IOException e) {
                this.hasErrors = true;
                this.onException.invoke(e);
            }
        }
    }
}
