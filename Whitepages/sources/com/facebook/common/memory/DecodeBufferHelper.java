package com.facebook.common.memory;

import androidx.core.util.Pools$Pool;
import java.nio.ByteBuffer;
import okhttp3.internal.http2.Http2;

public class DecodeBufferHelper implements Pools$Pool {
    public static final DecodeBufferHelper INSTANCE = new DecodeBufferHelper();
    private static final ThreadLocal sBuffer = new ThreadLocal() {
        /* access modifiers changed from: protected */
        public ByteBuffer initialValue() {
            return ByteBuffer.allocate(DecodeBufferHelper.sRecommendedDecodeBufferSize);
        }
    };
    /* access modifiers changed from: private */
    public static int sRecommendedDecodeBufferSize = Http2.INITIAL_MAX_FRAME_SIZE;

    public boolean release(ByteBuffer byteBuffer) {
        return true;
    }

    public static int getRecommendedDecodeBufferSize() {
        return sRecommendedDecodeBufferSize;
    }

    public ByteBuffer acquire() {
        return (ByteBuffer) sBuffer.get();
    }
}
