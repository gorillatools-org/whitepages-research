package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;

public final class JfifUtil {
    public static final JfifUtil INSTANCE = new JfifUtil();

    private final boolean isSOFn(int i) {
        switch (i) {
            case 192:
            case 193:
            case 194:
            case 195:
            case 197:
            case 198:
            case 199:
            case 201:
            case 202:
            case 203:
            case 205:
            case 206:
            case 207:
                return true;
            default:
                return false;
        }
    }

    private JfifUtil() {
    }

    public static final int getAutoRotateAngleFromOrientation(int i) {
        return TiffUtil.getAutoRotateAngleFromOrientation(i);
    }

    public static final int getOrientation(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            int moveToAPP1EXIF = INSTANCE.moveToAPP1EXIF(inputStream);
            if (moveToAPP1EXIF == 0) {
                return 0;
            }
            return TiffUtil.readOrientationFromTIFF(inputStream, moveToAPP1EXIF);
        } catch (IOException unused) {
            return 0;
        }
    }

    public static final boolean moveToMarker(InputStream inputStream, int i) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        while (StreamProcessor.readPackedInt(inputStream, 1, false) == 255) {
            int i2 = 255;
            while (i2 == 255) {
                i2 = StreamProcessor.readPackedInt(inputStream, 1, false);
            }
            if ((i != 192 || !INSTANCE.isSOFn(i2)) && i2 != i) {
                if (!(i2 == 1 || i2 == 216)) {
                    if (i2 == 217 || i2 == 218) {
                        break;
                    }
                    inputStream.skip((long) (StreamProcessor.readPackedInt(inputStream, 2, false) - 2));
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private final int moveToAPP1EXIF(InputStream inputStream) {
        if (moveToMarker(inputStream, 225)) {
            int readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, false);
            if (readPackedInt - 2 > 6) {
                int readPackedInt2 = StreamProcessor.readPackedInt(inputStream, 4, false);
                int readPackedInt3 = StreamProcessor.readPackedInt(inputStream, 2, false);
                int i = readPackedInt - 8;
                if (readPackedInt2 == 1165519206 && readPackedInt3 == 0) {
                    return i;
                }
                return 0;
            }
        }
        return 0;
    }
}
