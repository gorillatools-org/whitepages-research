package com.facebook.common.util;

import com.facebook.common.internal.Preconditions;
import java.io.InputStream;

public abstract class StreamUtil {
    public static long skip(InputStream inputStream, long j) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkArgument(Boolean.valueOf(j >= 0));
        long j2 = j;
        while (j2 > 0) {
            long skip = inputStream.skip(j2);
            if (skip <= 0) {
                if (inputStream.read() == -1) {
                    return j - j2;
                }
                skip = 1;
            }
            j2 -= skip;
        }
        return j;
    }
}
