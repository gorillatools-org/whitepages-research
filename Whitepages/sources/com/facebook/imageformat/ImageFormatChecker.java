package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Throwables;
import com.facebook.imageformat.ImageFormat;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ImageFormatChecker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Lazy instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new ImageFormatChecker$$ExternalSyntheticLambda0());
    private boolean binaryXmlEnabled;
    private List customImageFormatCheckers;
    private final DefaultImageFormatChecker defaultFormatChecker = new DefaultImageFormatChecker();
    private int maxHeaderLength;

    public static final ImageFormat getImageFormat_WrapIOException(InputStream inputStream) {
        return Companion.getImageFormat_WrapIOException(inputStream);
    }

    public static final ImageFormatChecker getInstance() {
        return Companion.getInstance();
    }

    private ImageFormatChecker() {
        updateMaxHeaderLength();
    }

    public final ImageFormatChecker setBinaryXmlEnabled(boolean z) {
        this.binaryXmlEnabled = z;
        return this;
    }

    public final ImageFormat determineImageFormat(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "is");
        int i = this.maxHeaderLength;
        byte[] bArr = new byte[i];
        int access$readHeaderFromStream = Companion.readHeaderFromStream(i, inputStream, bArr);
        ImageFormat determineFormat = this.defaultFormatChecker.determineFormat(bArr, access$readHeaderFromStream);
        if (Intrinsics.areEqual((Object) determineFormat, (Object) DefaultImageFormats.BINARY_XML) && !this.binaryXmlEnabled) {
            determineFormat = ImageFormat.UNKNOWN;
        }
        if (determineFormat != ImageFormat.UNKNOWN) {
            return determineFormat;
        }
        List<ImageFormat.FormatChecker> list = this.customImageFormatCheckers;
        if (list != null) {
            for (ImageFormat.FormatChecker determineFormat2 : list) {
                ImageFormat determineFormat3 = determineFormat2.determineFormat(bArr, access$readHeaderFromStream);
                if (determineFormat3 != ImageFormat.UNKNOWN) {
                    return determineFormat3;
                }
            }
        }
        return ImageFormat.UNKNOWN;
    }

    private final void updateMaxHeaderLength() {
        this.maxHeaderLength = this.defaultFormatChecker.getHeaderSize();
        List<ImageFormat.FormatChecker> list = this.customImageFormatCheckers;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            for (ImageFormat.FormatChecker headerSize : list) {
                this.maxHeaderLength = Math.max(this.maxHeaderLength, headerSize.getHeaderSize());
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final int readHeaderFromStream(int i, InputStream inputStream, byte[] bArr) {
            if (bArr.length < i) {
                throw new IllegalStateException("Check failed.");
            } else if (!inputStream.markSupported()) {
                return ByteStreams.read(inputStream, bArr, 0, i);
            } else {
                try {
                    inputStream.mark(i);
                    return ByteStreams.read(inputStream, bArr, 0, i);
                } finally {
                    inputStream.reset();
                }
            }
        }

        public final ImageFormatChecker getInstance() {
            return (ImageFormatChecker) ImageFormatChecker.instance$delegate.getValue();
        }

        public final ImageFormat getImageFormat(InputStream inputStream) {
            Intrinsics.checkNotNullParameter(inputStream, "is");
            return getInstance().determineImageFormat(inputStream);
        }

        public final ImageFormat getImageFormat_WrapIOException(InputStream inputStream) {
            Intrinsics.checkNotNullParameter(inputStream, "is");
            try {
                return getImageFormat(inputStream);
            } catch (IOException e) {
                throw Throwables.propagate(e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final ImageFormatChecker instance_delegate$lambda$2() {
        return new ImageFormatChecker();
    }
}
