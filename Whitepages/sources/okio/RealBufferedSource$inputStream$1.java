package okio;

import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;

public final class RealBufferedSource$inputStream$1 extends InputStream {
    final /* synthetic */ RealBufferedSource this$0;

    RealBufferedSource$inputStream$1(RealBufferedSource realBufferedSource) {
        this.this$0 = realBufferedSource;
    }

    public int read() {
        RealBufferedSource realBufferedSource = this.this$0;
        if (!realBufferedSource.closed) {
            if (realBufferedSource.bufferField.size() == 0) {
                RealBufferedSource realBufferedSource2 = this.this$0;
                if (realBufferedSource2.source.read(realBufferedSource2.bufferField, 8192) == -1) {
                    return -1;
                }
            }
            return this.this$0.bufferField.readByte() & 255;
        }
        throw new IOException("closed");
    }

    public int read(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (!this.this$0.closed) {
            SegmentedByteString.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
            if (this.this$0.bufferField.size() == 0) {
                RealBufferedSource realBufferedSource = this.this$0;
                if (realBufferedSource.source.read(realBufferedSource.bufferField, 8192) == -1) {
                    return -1;
                }
            }
            return this.this$0.bufferField.read(bArr, i, i2);
        }
        throw new IOException("closed");
    }

    public int available() {
        RealBufferedSource realBufferedSource = this.this$0;
        if (!realBufferedSource.closed) {
            return (int) Math.min(realBufferedSource.bufferField.size(), (long) Integer.MAX_VALUE);
        }
        throw new IOException("closed");
    }

    public void close() {
        this.this$0.close();
    }

    public String toString() {
        return this.this$0 + ".inputStream()";
    }
}
