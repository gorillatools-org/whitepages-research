package com.facebook.imageutils;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;

public final class HeifExifUtil {
    public static final HeifExifUtil INSTANCE = new HeifExifUtil();

    private HeifExifUtil() {
    }

    public static final int getOrientation(InputStream inputStream) {
        if (inputStream == null) {
            FLog.d("HeifExifUtil", "Trying to read Heif Exif from null inputStream -> ignoring");
            return 0;
        }
        try {
            return new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
        } catch (IOException e) {
            FLog.d("HeifExifUtil", "Failed reading Heif Exif orientation -> ignoring", (Throwable) e);
            return 0;
        }
    }
}
