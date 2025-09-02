package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Settings;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

public final class WebSocketReader implements Closeable {
    private boolean closed;
    private final Buffer controlFrameBuffer = new Buffer();
    private final FrameCallback frameCallback;
    private long frameLength;
    private final boolean isClient;
    private boolean isControlFrame;
    private boolean isFinalFrame;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    private final Buffer messageFrameBuffer = new Buffer();
    private MessageInflater messageInflater;
    private final boolean noContextTakeover;
    private int opcode;
    private final boolean perMessageDeflate;
    private boolean readingCompressedMessage;
    private final BufferedSource source;

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    public WebSocketReader(boolean z, BufferedSource bufferedSource, FrameCallback frameCallback2, boolean z2, boolean z3) {
        byte[] bArr;
        Intrinsics.checkNotNullParameter(bufferedSource, "source");
        Intrinsics.checkNotNullParameter(frameCallback2, "frameCallback");
        this.isClient = z;
        this.source = bufferedSource;
        this.frameCallback = frameCallback2;
        this.perMessageDeflate = z2;
        this.noContextTakeover = z3;
        Buffer.UnsafeCursor unsafeCursor = null;
        if (z) {
            bArr = null;
        } else {
            bArr = new byte[4];
        }
        this.maskKey = bArr;
        this.maskCursor = !z ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    public final BufferedSource getSource() {
        return this.source;
    }

    public final void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    /* JADX INFO: finally extract failed */
    private final void readHeader() throws IOException, ProtocolException {
        String str;
        boolean z;
        if (!this.closed) {
            long timeoutNanos = this.source.timeout().timeoutNanos();
            this.source.timeout().clearTimeout();
            try {
                int and = Util.and(this.source.readByte(), 255);
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                int i = and & 15;
                this.opcode = i;
                boolean z2 = false;
                boolean z3 = (and & 128) != 0;
                this.isFinalFrame = z3;
                boolean z4 = (and & 8) != 0;
                this.isControlFrame = z4;
                if (!z4 || z3) {
                    boolean z5 = (and & 64) != 0;
                    if (i == 1 || i == 2) {
                        if (!z5) {
                            z = false;
                        } else if (this.perMessageDeflate) {
                            z = true;
                        } else {
                            throw new ProtocolException("Unexpected rsv1 flag");
                        }
                        this.readingCompressedMessage = z;
                    } else if (z5) {
                        throw new ProtocolException("Unexpected rsv1 flag");
                    }
                    if ((and & 32) != 0) {
                        throw new ProtocolException("Unexpected rsv2 flag");
                    } else if ((and & 16) == 0) {
                        int and2 = Util.and(this.source.readByte(), 255);
                        if ((and2 & 128) != 0) {
                            z2 = true;
                        }
                        if (z2 == this.isClient) {
                            if (this.isClient) {
                                str = "Server-sent frames must not be masked.";
                            } else {
                                str = "Client-sent frames must be masked.";
                            }
                            throw new ProtocolException(str);
                        }
                        long j = (long) (and2 & 127);
                        this.frameLength = j;
                        if (j == ((long) WebSocketProtocol.PAYLOAD_SHORT)) {
                            this.frameLength = (long) Util.and(this.source.readShort(), (int) Settings.DEFAULT_INITIAL_WINDOW_SIZE);
                        } else if (j == ((long) 127)) {
                            long readLong = this.source.readLong();
                            this.frameLength = readLong;
                            if (readLong < 0) {
                                throw new ProtocolException("Frame length 0x" + Util.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
                            }
                        }
                        if (this.isControlFrame && this.frameLength > 125) {
                            throw new ProtocolException("Control frame must be less than 125B.");
                        } else if (z2) {
                            BufferedSource bufferedSource = this.source;
                            byte[] bArr = this.maskKey;
                            Intrinsics.checkNotNull(bArr);
                            bufferedSource.readFully(bArr);
                        }
                    } else {
                        throw new ProtocolException("Unexpected rsv3 flag");
                    }
                } else {
                    throw new ProtocolException("Control frames must be final.");
                }
            } catch (Throwable th) {
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                throw th;
            }
        } else {
            throw new IOException("closed");
        }
    }

    private final void readControlFrame() throws IOException {
        String str;
        short s;
        long j = this.frameLength;
        if (j > 0) {
            this.source.readFully(this.controlFrameBuffer, j);
            if (!this.isClient) {
                Buffer buffer = this.controlFrameBuffer;
                Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                Intrinsics.checkNotNull(unsafeCursor);
                buffer.readAndWriteUnsafe(unsafeCursor);
                this.maskCursor.seek(0);
                WebSocketProtocol webSocketProtocol = WebSocketProtocol.INSTANCE;
                Buffer.UnsafeCursor unsafeCursor2 = this.maskCursor;
                byte[] bArr = this.maskKey;
                Intrinsics.checkNotNull(bArr);
                webSocketProtocol.toggleMask(unsafeCursor2, bArr);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                long size = this.controlFrameBuffer.size();
                if (size != 1) {
                    if (size != 0) {
                        s = this.controlFrameBuffer.readShort();
                        str = this.controlFrameBuffer.readUtf8();
                        String closeCodeExceptionMessage = WebSocketProtocol.INSTANCE.closeCodeExceptionMessage(s);
                        if (closeCodeExceptionMessage != null) {
                            throw new ProtocolException(closeCodeExceptionMessage);
                        }
                    } else {
                        s = 1005;
                        str = "";
                    }
                    this.frameCallback.onReadClose(s, str);
                    this.closed = true;
                    return;
                }
                throw new ProtocolException("Malformed close payload length of 1.");
            case 9:
                this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Util.toHexString(this.opcode));
        }
    }

    private final void readMessageFrame() throws IOException {
        int i = this.opcode;
        if (i == 1 || i == 2) {
            readMessage();
            if (this.readingCompressedMessage) {
                MessageInflater messageInflater2 = this.messageInflater;
                if (messageInflater2 == null) {
                    messageInflater2 = new MessageInflater(this.noContextTakeover);
                    this.messageInflater = messageInflater2;
                }
                messageInflater2.inflate(this.messageFrameBuffer);
            }
            if (i == 1) {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
            } else {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
            }
        } else {
            throw new ProtocolException("Unknown opcode: " + Util.toHexString(i));
        }
    }

    private final void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }

    private final void readMessage() throws IOException {
        while (!this.closed) {
            long j = this.frameLength;
            if (j > 0) {
                this.source.readFully(this.messageFrameBuffer, j);
                if (!this.isClient) {
                    Buffer buffer = this.messageFrameBuffer;
                    Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                    Intrinsics.checkNotNull(unsafeCursor);
                    buffer.readAndWriteUnsafe(unsafeCursor);
                    this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
                    WebSocketProtocol webSocketProtocol = WebSocketProtocol.INSTANCE;
                    Buffer.UnsafeCursor unsafeCursor2 = this.maskCursor;
                    byte[] bArr = this.maskKey;
                    Intrinsics.checkNotNull(bArr);
                    webSocketProtocol.toggleMask(unsafeCursor2, bArr);
                    this.maskCursor.close();
                }
            }
            if (!this.isFinalFrame) {
                readUntilNonControlFrame();
                if (this.opcode != 0) {
                    throw new ProtocolException("Expected continuation opcode. Got: " + Util.toHexString(this.opcode));
                }
            } else {
                return;
            }
        }
        throw new IOException("closed");
    }

    public void close() throws IOException {
        MessageInflater messageInflater2 = this.messageInflater;
        if (messageInflater2 != null) {
            messageInflater2.close();
        }
    }
}
