package okio;

import java.io.RandomAccessFile;
import kotlin.jvm.internal.Intrinsics;

public final class JvmFileHandle extends FileHandle {
    private final RandomAccessFile randomAccessFile;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JvmFileHandle(boolean z, RandomAccessFile randomAccessFile2) {
        super(z);
        Intrinsics.checkNotNullParameter(randomAccessFile2, "randomAccessFile");
        this.randomAccessFile = randomAccessFile2;
    }

    /* access modifiers changed from: protected */
    public synchronized long protectedSize() {
        return this.randomAccessFile.length();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int protectedRead(long r2, byte[] r4, int r5, int r6) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = "array"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)     // Catch:{ all -> 0x001f }
            java.io.RandomAccessFile r0 = r1.randomAccessFile     // Catch:{ all -> 0x001f }
            r0.seek(r2)     // Catch:{ all -> 0x001f }
            r2 = 0
        L_0x000c:
            if (r2 >= r6) goto L_0x0021
            java.io.RandomAccessFile r3 = r1.randomAccessFile     // Catch:{ all -> 0x001f }
            int r0 = r6 - r2
            int r3 = r3.read(r4, r5, r0)     // Catch:{ all -> 0x001f }
            r0 = -1
            if (r3 != r0) goto L_0x001d
            if (r2 != 0) goto L_0x0021
            monitor-exit(r1)
            return r0
        L_0x001d:
            int r2 = r2 + r3
            goto L_0x000c
        L_0x001f:
            r2 = move-exception
            goto L_0x0023
        L_0x0021:
            monitor-exit(r1)
            return r2
        L_0x0023:
            monitor-exit(r1)     // Catch:{ all -> 0x001f }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.JvmFileHandle.protectedRead(long, byte[], int, int):int");
    }

    /* access modifiers changed from: protected */
    public synchronized void protectedWrite(long j, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "array");
        this.randomAccessFile.seek(j);
        this.randomAccessFile.write(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public synchronized void protectedFlush() {
        this.randomAccessFile.getFD().sync();
    }

    /* access modifiers changed from: protected */
    public synchronized void protectedClose() {
        this.randomAccessFile.close();
    }
}
