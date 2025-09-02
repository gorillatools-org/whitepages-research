package okio;

import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;

public final class RealBufferedSink$outputStream$1 extends OutputStream {
    final /* synthetic */ RealBufferedSink this$0;

    RealBufferedSink$outputStream$1(RealBufferedSink realBufferedSink) {
        this.this$0 = realBufferedSink;
    }

    public void write(int i) {
        RealBufferedSink realBufferedSink = this.this$0;
        if (!realBufferedSink.closed) {
            realBufferedSink.bufferField.writeByte((int) (byte) i);
            this.this$0.emitCompleteSegments();
            return;
        }
        throw new IOException("closed");
    }

    public void write(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        RealBufferedSink realBufferedSink = this.this$0;
        if (!realBufferedSink.closed) {
            realBufferedSink.bufferField.write(bArr, i, i2);
            this.this$0.emitCompleteSegments();
            return;
        }
        throw new IOException("closed");
    }

    public void flush() {
        RealBufferedSink realBufferedSink = this.this$0;
        if (!realBufferedSink.closed) {
            realBufferedSink.flush();
        }
    }

    public void close() {
        this.this$0.close();
    }

    public String toString() {
        return this.this$0 + ".outputStream()";
    }
}
