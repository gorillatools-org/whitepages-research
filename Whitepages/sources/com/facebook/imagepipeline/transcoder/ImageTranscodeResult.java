package com.facebook.imagepipeline.transcoder;

import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class ImageTranscodeResult {
    private final int transcodeStatus;

    public ImageTranscodeResult(int i) {
        this.transcodeStatus = i;
    }

    public final int getTranscodeStatus() {
        return this.transcodeStatus;
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format((Locale) null, "Status: %d", Arrays.copyOf(new Object[]{Integer.valueOf(this.transcodeStatus)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
