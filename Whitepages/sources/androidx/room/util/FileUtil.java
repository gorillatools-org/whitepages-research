package androidx.room.util;

import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import kotlin.jvm.internal.Intrinsics;

public abstract class FileUtil {
    public static final void copy(ReadableByteChannel readableByteChannel, FileChannel fileChannel) {
        Intrinsics.checkNotNullParameter(readableByteChannel, "input");
        Intrinsics.checkNotNullParameter(fileChannel, "output");
        try {
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            fileChannel.force(false);
        } finally {
            readableByteChannel.close();
            fileChannel.close();
        }
    }
}
