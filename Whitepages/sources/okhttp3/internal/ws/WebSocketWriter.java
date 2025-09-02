package okhttp3.internal.ws;

import com.google.firebase.messaging.Constants;
import java.io.Closeable;
import java.io.IOException;
import java.util.Random;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

public final class WebSocketWriter implements Closeable {
    private final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    private final Buffer messageBuffer = new Buffer();
    private MessageDeflater messageDeflater;
    private final long minimumDeflateSize;
    private final boolean noContextTakeover;
    private final boolean perMessageDeflate;
    private final Random random;
    private final BufferedSink sink;
    private final Buffer sinkBuffer;
    private boolean writerClosed;

    public WebSocketWriter(boolean z, BufferedSink bufferedSink, Random random2, boolean z2, boolean z3, long j) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        Intrinsics.checkNotNullParameter(random2, "random");
        this.isClient = z;
        this.sink = bufferedSink;
        this.random = random2;
        this.perMessageDeflate = z2;
        this.noContextTakeover = z3;
        this.minimumDeflateSize = j;
        this.sinkBuffer = bufferedSink.getBuffer();
        Buffer.UnsafeCursor unsafeCursor = null;
        this.maskKey = z ? new byte[4] : null;
        this.maskCursor = z ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    public final BufferedSink getSink() {
        return this.sink;
    }

    public final Random getRandom() {
        return this.random;
    }

    public final void writePing(ByteString byteString) throws IOException {
        Intrinsics.checkNotNullParameter(byteString, "payload");
        writeControlFrame(9, byteString);
    }

    public final void writePong(ByteString byteString) throws IOException {
        Intrinsics.checkNotNullParameter(byteString, "payload");
        writeControlFrame(10, byteString);
    }

    public final void writeClose(int i, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (!(i == 0 && byteString == null)) {
            if (i != 0) {
                WebSocketProtocol.INSTANCE.validateCloseCode(i);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(i);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        try {
            writeControlFrame(8, byteString2);
        } finally {
            this.writerClosed = true;
        }
    }

    private final void writeControlFrame(int i, ByteString byteString) throws IOException {
        if (!this.writerClosed) {
            int size = byteString.size();
            if (((long) size) <= 125) {
                this.sinkBuffer.writeByte(i | 128);
                if (this.isClient) {
                    this.sinkBuffer.writeByte(size | 128);
                    Random random2 = this.random;
                    byte[] bArr = this.maskKey;
                    Intrinsics.checkNotNull(bArr);
                    random2.nextBytes(bArr);
                    this.sinkBuffer.write(this.maskKey);
                    if (size > 0) {
                        long size2 = this.sinkBuffer.size();
                        this.sinkBuffer.write(byteString);
                        Buffer buffer = this.sinkBuffer;
                        Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                        Intrinsics.checkNotNull(unsafeCursor);
                        buffer.readAndWriteUnsafe(unsafeCursor);
                        this.maskCursor.seek(size2);
                        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                } else {
                    this.sinkBuffer.writeByte(size);
                    this.sinkBuffer.write(byteString);
                }
                this.sink.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        throw new IOException("closed");
    }

    public final void writeMessageFrame(int i, ByteString byteString) throws IOException {
        Intrinsics.checkNotNullParameter(byteString, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (!this.writerClosed) {
            this.messageBuffer.write(byteString);
            int i2 = i | 128;
            if (this.perMessageDeflate && ((long) byteString.size()) >= this.minimumDeflateSize) {
                MessageDeflater messageDeflater2 = this.messageDeflater;
                if (messageDeflater2 == null) {
                    messageDeflater2 = new MessageDeflater(this.noContextTakeover);
                    this.messageDeflater = messageDeflater2;
                }
                messageDeflater2.deflate(this.messageBuffer);
                i2 = i | 192;
            }
            long size = this.messageBuffer.size();
            this.sinkBuffer.writeByte(i2);
            int i3 = this.isClient ? 128 : 0;
            if (size <= 125) {
                this.sinkBuffer.writeByte(i3 | ((int) size));
            } else if (size <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                this.sinkBuffer.writeByte(i3 | WebSocketProtocol.PAYLOAD_SHORT);
                this.sinkBuffer.writeShort((int) size);
            } else {
                this.sinkBuffer.writeByte(i3 | 127);
                this.sinkBuffer.writeLong(size);
            }
            if (this.isClient) {
                Random random2 = this.random;
                byte[] bArr = this.maskKey;
                Intrinsics.checkNotNull(bArr);
                random2.nextBytes(bArr);
                this.sinkBuffer.write(this.maskKey);
                if (size > 0) {
                    Buffer buffer = this.messageBuffer;
                    Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                    Intrinsics.checkNotNull(unsafeCursor);
                    buffer.readAndWriteUnsafe(unsafeCursor);
                    this.maskCursor.seek(0);
                    WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            this.sinkBuffer.write(this.messageBuffer, size);
            this.sink.emit();
            return;
        }
        throw new IOException("closed");
    }

    public void close() {
        MessageDeflater messageDeflater2 = this.messageDeflater;
        if (messageDeflater2 != null) {
            messageDeflater2.close();
        }
    }
}
