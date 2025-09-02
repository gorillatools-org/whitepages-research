package okio.internal;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Source;

public final class FixedLengthSource extends ForwardingSource {
    private long bytesReceived;
    private final long size;
    private final boolean truncate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FixedLengthSource(Source source, long j, boolean z) {
        super(source);
        Intrinsics.checkNotNullParameter(source, "delegate");
        this.size = j;
        this.truncate = z;
    }

    public long read(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        long j2 = this.bytesReceived;
        long j3 = this.size;
        if (j2 > j3) {
            j = 0;
        } else if (this.truncate) {
            long j4 = j3 - j2;
            if (j4 == 0) {
                return -1;
            }
            j = Math.min(j, j4);
        }
        long read = super.read(buffer, j);
        int i = (read > -1 ? 1 : (read == -1 ? 0 : -1));
        if (i != 0) {
            this.bytesReceived += read;
        }
        long j5 = this.bytesReceived;
        long j6 = this.size;
        if ((j5 >= j6 || i != 0) && j5 <= j6) {
            return read;
        }
        if (read > 0 && j5 > j6) {
            truncateToSize(buffer, buffer.size() - (this.bytesReceived - this.size));
        }
        throw new IOException("expected " + this.size + " bytes but got " + this.bytesReceived);
    }

    private final void truncateToSize(Buffer buffer, long j) {
        Buffer buffer2 = new Buffer();
        buffer2.writeAll(buffer);
        buffer.write(buffer2, j);
        buffer2.clear();
    }
}
