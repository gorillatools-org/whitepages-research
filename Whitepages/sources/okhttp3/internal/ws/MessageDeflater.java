package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Deflater;
import okio.Buffer;
import okio.ByteString;
import okio.DeflaterSink;
import okio.Sink;

public final class MessageDeflater implements Closeable {
    private final Buffer deflatedBytes;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final boolean noContextTakeover;

    public MessageDeflater(boolean z) {
        this.noContextTakeover = z;
        Buffer buffer = new Buffer();
        this.deflatedBytes = buffer;
        Deflater deflater2 = new Deflater(-1, true);
        this.deflater = deflater2;
        this.deflaterSink = new DeflaterSink((Sink) buffer, deflater2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0054, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0058, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void deflate(okio.Buffer r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.String r0 = "buffer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            okio.Buffer r0 = r5.deflatedBytes
            long r0 = r0.size()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0015
            r0 = r2
            goto L_0x0016
        L_0x0015:
            r0 = r1
        L_0x0016:
            if (r0 == 0) goto L_0x0068
            boolean r0 = r5.noContextTakeover
            if (r0 == 0) goto L_0x0021
            java.util.zip.Deflater r0 = r5.deflater
            r0.reset()
        L_0x0021:
            okio.DeflaterSink r0 = r5.deflaterSink
            long r3 = r6.size()
            r0.write(r6, r3)
            okio.DeflaterSink r0 = r5.deflaterSink
            r0.flush()
            okio.Buffer r0 = r5.deflatedBytes
            okio.ByteString r3 = okhttp3.internal.ws.MessageDeflaterKt.EMPTY_DEFLATE_BLOCK
            boolean r0 = r5.endsWith(r0, r3)
            if (r0 == 0) goto L_0x0059
            okio.Buffer r0 = r5.deflatedBytes
            long r0 = r0.size()
            r3 = 4
            long r3 = (long) r3
            long r0 = r0 - r3
            okio.Buffer r3 = r5.deflatedBytes
            r4 = 0
            okio.Buffer$UnsafeCursor r2 = okio.Buffer.readAndWriteUnsafe$default(r3, r4, r2, r4)
            r2.resizeBuffer(r0)     // Catch:{ all -> 0x0052 }
            kotlin.io.CloseableKt.closeFinally(r2, r4)
            goto L_0x005e
        L_0x0052:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r6)
            throw r0
        L_0x0059:
            okio.Buffer r0 = r5.deflatedBytes
            r0.writeByte((int) r1)
        L_0x005e:
            okio.Buffer r0 = r5.deflatedBytes
            long r1 = r0.size()
            r6.write((okio.Buffer) r0, (long) r1)
            return
        L_0x0068:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Failed requirement."
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.MessageDeflater.deflate(okio.Buffer):void");
    }

    public void close() throws IOException {
        this.deflaterSink.close();
    }

    private final boolean endsWith(Buffer buffer, ByteString byteString) {
        return buffer.rangeEquals(buffer.size() - ((long) byteString.size()), byteString);
    }
}
