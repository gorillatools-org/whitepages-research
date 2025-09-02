package okio;

import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public abstract class FileHandle implements Closeable {
    /* access modifiers changed from: private */
    public boolean closed;
    private final ReentrantLock lock = _JvmPlatformKt.newLock();
    /* access modifiers changed from: private */
    public int openStreamCount;
    private final boolean readWrite;

    /* access modifiers changed from: protected */
    public abstract void protectedClose();

    /* access modifiers changed from: protected */
    public abstract void protectedFlush();

    /* access modifiers changed from: protected */
    public abstract int protectedRead(long j, byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract long protectedSize();

    /* access modifiers changed from: protected */
    public abstract void protectedWrite(long j, byte[] bArr, int i, int i2);

    public FileHandle(boolean z) {
        this.readWrite = z;
    }

    public final ReentrantLock getLock() {
        return this.lock;
    }

    /* JADX INFO: finally extract failed */
    public final long size() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.closed) {
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                return protectedSize();
            }
            throw new IllegalStateException("closed");
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public final void flush() {
        if (this.readWrite) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                if (!this.closed) {
                    Unit unit = Unit.INSTANCE;
                    reentrantLock.unlock();
                    protectedFlush();
                    return;
                }
                throw new IllegalStateException("closed");
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        } else {
            throw new IllegalStateException("file handle is read-only");
        }
    }

    /* JADX INFO: finally extract failed */
    public final Source source(long j) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.closed) {
                this.openStreamCount++;
                reentrantLock.unlock();
                return new FileHandleSource(this, j);
            }
            throw new IllegalStateException("closed");
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public static /* synthetic */ Sink sink$default(FileHandle fileHandle, long j, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                j = 0;
            }
            return fileHandle.sink(j);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
    }

    /* JADX INFO: finally extract failed */
    public final Sink sink(long j) {
        if (this.readWrite) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                if (!this.closed) {
                    this.openStreamCount++;
                    reentrantLock.unlock();
                    return new FileHandleSink(this, j);
                }
                throw new IllegalStateException("closed");
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        } else {
            throw new IllegalStateException("file handle is read-only");
        }
    }

    public final void close() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.closed) {
                this.closed = true;
                if (this.openStreamCount != 0) {
                    reentrantLock.unlock();
                    return;
                }
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                protectedClose();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    /* access modifiers changed from: private */
    public final long readNoCloseCheck(long j, Buffer buffer, long j2) {
        Buffer buffer2 = buffer;
        long j3 = j2;
        if (j3 >= 0) {
            long j4 = j3 + j;
            long j5 = j;
            while (true) {
                if (j5 >= j4) {
                    break;
                }
                Segment writableSegment$okio = buffer2.writableSegment$okio(1);
                byte[] bArr = writableSegment$okio.data;
                int i = writableSegment$okio.limit;
                int protectedRead = protectedRead(j5, bArr, i, (int) Math.min(j4 - j5, (long) (8192 - i)));
                if (protectedRead == -1) {
                    if (writableSegment$okio.pos == writableSegment$okio.limit) {
                        buffer2.head = writableSegment$okio.pop();
                        SegmentPool.recycle(writableSegment$okio);
                    }
                    if (j == j5) {
                        return -1;
                    }
                } else {
                    writableSegment$okio.limit += protectedRead;
                    long j6 = (long) protectedRead;
                    j5 += j6;
                    buffer2.setSize$okio(buffer.size() + j6);
                }
            }
            return j5 - j;
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j3).toString());
    }

    /* access modifiers changed from: private */
    public final void writeNoCloseCheck(long j, Buffer buffer, long j2) {
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0, j2);
        long j3 = j2 + j;
        while (j < j3) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j3 - j, (long) (segment.limit - segment.pos));
            protectedWrite(j, segment.data, segment.pos, min);
            segment.pos += min;
            long j4 = (long) min;
            j += j4;
            buffer.setSize$okio(buffer.size() - j4);
            if (segment.pos == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    private static final class FileHandleSink implements Sink {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSink(FileHandle fileHandle2, long j) {
            Intrinsics.checkNotNullParameter(fileHandle2, "fileHandle");
            this.fileHandle = fileHandle2;
            this.position = j;
        }

        public void write(Buffer buffer, long j) {
            Intrinsics.checkNotNullParameter(buffer, "source");
            if (!this.closed) {
                this.fileHandle.writeNoCloseCheck(this.position, buffer, j);
                this.position += j;
                return;
            }
            throw new IllegalStateException("closed");
        }

        public void flush() {
            if (!this.closed) {
                this.fileHandle.protectedFlush();
                return;
            }
            throw new IllegalStateException("closed");
        }

        public Timeout timeout() {
            return Timeout.NONE;
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                ReentrantLock lock = this.fileHandle.getLock();
                lock.lock();
                try {
                    FileHandle fileHandle2 = this.fileHandle;
                    fileHandle2.openStreamCount = fileHandle2.openStreamCount - 1;
                    if (this.fileHandle.openStreamCount == 0) {
                        if (this.fileHandle.closed) {
                            Unit unit = Unit.INSTANCE;
                            lock.unlock();
                            this.fileHandle.protectedClose();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static final class FileHandleSource implements Source {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSource(FileHandle fileHandle2, long j) {
            Intrinsics.checkNotNullParameter(fileHandle2, "fileHandle");
            this.fileHandle = fileHandle2;
            this.position = j;
        }

        public long read(Buffer buffer, long j) {
            Intrinsics.checkNotNullParameter(buffer, "sink");
            if (!this.closed) {
                long access$readNoCloseCheck = this.fileHandle.readNoCloseCheck(this.position, buffer, j);
                if (access$readNoCloseCheck != -1) {
                    this.position += access$readNoCloseCheck;
                }
                return access$readNoCloseCheck;
            }
            throw new IllegalStateException("closed");
        }

        public Timeout timeout() {
            return Timeout.NONE;
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                ReentrantLock lock = this.fileHandle.getLock();
                lock.lock();
                try {
                    FileHandle fileHandle2 = this.fileHandle;
                    fileHandle2.openStreamCount = fileHandle2.openStreamCount - 1;
                    if (this.fileHandle.openStreamCount == 0) {
                        if (this.fileHandle.closed) {
                            Unit unit = Unit.INSTANCE;
                            lock.unlock();
                            this.fileHandle.protectedClose();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
