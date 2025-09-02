package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;

public final class FileOperator {
    private final FileChannel fileChannel;

    public FileOperator(FileChannel fileChannel2) {
        Intrinsics.checkNotNullParameter(fileChannel2, "fileChannel");
        this.fileChannel = fileChannel2;
    }

    public final void write(long j, Buffer buffer, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "source");
        if (j2 < 0 || j2 > buffer.size()) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long transferFrom = this.fileChannel.transferFrom(buffer, j, j2);
            j += transferFrom;
            j2 -= transferFrom;
        }
    }

    public final void read(long j, Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        if (j2 >= 0) {
            while (j2 > 0) {
                long transferTo = this.fileChannel.transferTo(j, j2, buffer);
                j += transferTo;
                j2 -= transferTo;
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
