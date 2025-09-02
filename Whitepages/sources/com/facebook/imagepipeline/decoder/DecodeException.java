package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.jvm.internal.Intrinsics;

public final class DecodeException extends RuntimeException {
    private final EncodedImage encodedImage;

    public final EncodedImage getEncodedImage() {
        return this.encodedImage;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DecodeException(String str, EncodedImage encodedImage2) {
        super(str);
        Intrinsics.checkNotNullParameter(encodedImage2, "encodedImage");
        this.encodedImage = encodedImage2;
    }
}
