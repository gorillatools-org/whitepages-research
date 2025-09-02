package okio;

import com.google.firebase.messaging.Constants;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;

public final class Buffer$outputStream$1 extends OutputStream {
    final /* synthetic */ Buffer this$0;

    public void close() {
    }

    public void flush() {
    }

    Buffer$outputStream$1(Buffer buffer) {
        this.this$0 = buffer;
    }

    public void write(int i) {
        this.this$0.writeByte(i);
    }

    public void write(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        this.this$0.write(bArr, i, i2);
    }

    public String toString() {
        return this.this$0 + ".outputStream()";
    }
}
