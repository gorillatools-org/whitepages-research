package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;

public final class MemoryChunkUtil {
    public static final MemoryChunkUtil INSTANCE = new MemoryChunkUtil();

    private MemoryChunkUtil() {
    }

    public static final int adjustByteCount(int i, int i2, int i3) {
        return Math.min(Math.max(0, i3 - i), i2);
    }

    public static final void checkBounds(int i, int i2, int i3, int i4, int i5) {
        boolean z = false;
        Preconditions.checkArgument(i4 >= 0, "count (%d) ! >= 0", Integer.valueOf(i4));
        Preconditions.checkArgument(i >= 0, "offset (%d) ! >= 0", Integer.valueOf(i));
        Preconditions.checkArgument(i3 >= 0, "otherOffset (%d) ! >= 0", Integer.valueOf(i3));
        Preconditions.checkArgument(i + i4 <= i5, "offset (%d) + count (%d) ! <= %d", Integer.valueOf(i), Integer.valueOf(i4), Integer.valueOf(i5));
        if (i3 + i4 <= i2) {
            z = true;
        }
        Preconditions.checkArgument(z, "otherOffset (%d) + count (%d) ! <= %d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i2));
    }
}
