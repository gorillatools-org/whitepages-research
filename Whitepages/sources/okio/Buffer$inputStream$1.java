package okio;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;

public final class Buffer$inputStream$1 extends InputStream {
    final /* synthetic */ Buffer this$0;

    public void close() {
    }

    Buffer$inputStream$1(Buffer buffer) {
        this.this$0 = buffer;
    }

    public int read() {
        if (this.this$0.size() > 0) {
            return this.this$0.readByte() & 255;
        }
        return -1;
    }

    public int read(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "sink");
        return this.this$0.read(bArr, i, i2);
    }

    public int available() {
        return (int) Math.min(this.this$0.size(), (long) Integer.MAX_VALUE);
    }

    public String toString() {
        return this.this$0 + ".inputStream()";
    }
}
