package com.facebook.imageformat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public final class ImageFormatCheckerUtils {
    public static final ImageFormatCheckerUtils INSTANCE = new ImageFormatCheckerUtils();

    private ImageFormatCheckerUtils() {
    }

    public static final byte[] asciiBytes(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        try {
            Charset forName = Charset.forName("ASCII");
            Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
            byte[] bytes = str.getBytes(forName);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return bytes;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static final boolean startsWithPattern(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNullParameter(bArr, "byteArray");
        Intrinsics.checkNotNullParameter(bArr2, "pattern");
        return hasPatternAt(bArr, bArr2, 0);
    }

    public static final boolean hasPatternAt(byte[] bArr, byte[] bArr2, int i) {
        Intrinsics.checkNotNullParameter(bArr, "byteArray");
        Intrinsics.checkNotNullParameter(bArr2, "pattern");
        if (bArr2.length + i > bArr.length) {
            return false;
        }
        IntRange indices = ArraysKt.getIndices(bArr2);
        if (!(indices instanceof Collection) || !((Collection) indices).isEmpty()) {
            Iterator it = indices.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                if (bArr[i + nextInt] != bArr2[nextInt]) {
                    return false;
                }
            }
        }
        return true;
    }
}
