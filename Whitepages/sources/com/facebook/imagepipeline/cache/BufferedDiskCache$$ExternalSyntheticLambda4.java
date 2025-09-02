package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.WriterCallback;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.OutputStream;

public final /* synthetic */ class BufferedDiskCache$$ExternalSyntheticLambda4 implements WriterCallback {
    public final /* synthetic */ EncodedImage f$0;
    public final /* synthetic */ BufferedDiskCache f$1;

    public /* synthetic */ BufferedDiskCache$$ExternalSyntheticLambda4(EncodedImage encodedImage, BufferedDiskCache bufferedDiskCache) {
        this.f$0 = encodedImage;
        this.f$1 = bufferedDiskCache;
    }

    public final void write(OutputStream outputStream) {
        BufferedDiskCache.writeToDiskCache$lambda$9(this.f$0, this.f$1, outputStream);
    }
}
