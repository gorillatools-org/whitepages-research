package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;

public final class TiffUtil {
    public static final TiffUtil INSTANCE = new TiffUtil();
    private static final Class TAG = TiffUtil.class;

    public static final int getAutoRotateAngleFromOrientation(int i) {
        if (i == 0 || i == 1) {
            return 0;
        }
        if (i == 3) {
            return 180;
        }
        if (i != 6) {
            return i != 8 ? 0 : 270;
        }
        return 90;
    }

    private TiffUtil() {
    }

    public static final int readOrientationFromTIFF(InputStream inputStream, int i) {
        Intrinsics.checkNotNullParameter(inputStream, "stream");
        TiffHeader tiffHeader = new TiffHeader();
        TiffUtil tiffUtil = INSTANCE;
        int readTiffHeader = tiffUtil.readTiffHeader(inputStream, i, tiffHeader);
        int firstIfdOffset = tiffHeader.getFirstIfdOffset() - 8;
        if (readTiffHeader == 0 || firstIfdOffset > readTiffHeader) {
            return 0;
        }
        inputStream.skip((long) firstIfdOffset);
        return tiffUtil.getOrientationFromTiffEntry(inputStream, tiffUtil.moveToTiffEntryWithTag(inputStream, readTiffHeader - firstIfdOffset, tiffHeader.isLittleEndian(), 274), tiffHeader.isLittleEndian());
    }

    private final int readTiffHeader(InputStream inputStream, int i, TiffHeader tiffHeader) {
        if (i <= 8) {
            return 0;
        }
        tiffHeader.setByteOrder(StreamProcessor.readPackedInt(inputStream, 4, false));
        if (tiffHeader.getByteOrder() == 1229531648 || tiffHeader.getByteOrder() == 1296891946) {
            tiffHeader.setLittleEndian(tiffHeader.getByteOrder() == 1229531648);
            tiffHeader.setFirstIfdOffset(StreamProcessor.readPackedInt(inputStream, 4, tiffHeader.isLittleEndian()));
            int i2 = i - 8;
            if (tiffHeader.getFirstIfdOffset() >= 8 && tiffHeader.getFirstIfdOffset() - 8 <= i2) {
                return i2;
            }
            FLog.e(TAG, "Invalid offset");
            return 0;
        }
        FLog.e(TAG, "Invalid TIFF header");
        return 0;
    }

    private final int moveToTiffEntryWithTag(InputStream inputStream, int i, boolean z, int i2) {
        if (i < 14) {
            return 0;
        }
        int readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, z);
        int i3 = i - 2;
        while (true) {
            int i4 = readPackedInt - 1;
            if (readPackedInt <= 0 || i3 < 12) {
                return 0;
            }
            int i5 = i3 - 2;
            if (StreamProcessor.readPackedInt(inputStream, 2, z) == i2) {
                return i5;
            }
            inputStream.skip(10);
            i3 -= 12;
            readPackedInt = i4;
        }
        return 0;
    }

    private final int getOrientationFromTiffEntry(InputStream inputStream, int i, boolean z) {
        if (i >= 10 && StreamProcessor.readPackedInt(inputStream, 2, z) == 3 && StreamProcessor.readPackedInt(inputStream, 4, z) == 1) {
            return StreamProcessor.readPackedInt(inputStream, 2, z);
        }
        return 0;
    }

    private static final class TiffHeader {
        private int byteOrder;
        private int firstIfdOffset;
        private boolean isLittleEndian;

        public final boolean isLittleEndian() {
            return this.isLittleEndian;
        }

        public final void setLittleEndian(boolean z) {
            this.isLittleEndian = z;
        }

        public final int getByteOrder() {
            return this.byteOrder;
        }

        public final void setByteOrder(int i) {
            this.byteOrder = i;
        }

        public final int getFirstIfdOffset() {
            return this.firstIfdOffset;
        }

        public final void setFirstIfdOffset(int i) {
            this.firstIfdOffset = i;
        }
    }
}
