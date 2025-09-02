package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.UShort;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public final class WebpUtil {
    public static final WebpUtil INSTANCE = new WebpUtil();

    private WebpUtil() {
    }

    public static final Pair getSize(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "stream");
        byte[] bArr = new byte[4];
        try {
            inputStream.read(bArr);
            WebpUtil webpUtil = INSTANCE;
            if (!webpUtil.compare(bArr, "RIFF")) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            webpUtil.getInt(inputStream);
            inputStream.read(bArr);
            if (!webpUtil.compare(bArr, "WEBP")) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return null;
            }
            inputStream.read(bArr);
            String header = webpUtil.getHeader(bArr);
            int hashCode = header.hashCode();
            if (hashCode != 2640674) {
                if (hashCode != 2640718) {
                    if (hashCode == 2640730) {
                        if (header.equals("VP8X")) {
                            Pair vP8XDimension = webpUtil.getVP8XDimension(inputStream);
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            return vP8XDimension;
                        }
                    }
                } else if (header.equals("VP8L")) {
                    Pair vP8LDimension = webpUtil.getVP8LDimension(inputStream);
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return vP8LDimension;
                }
            } else if (header.equals("VP8 ")) {
                Pair vP8Dimension = webpUtil.getVP8Dimension(inputStream);
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return vP8Dimension;
            }
            try {
                inputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            return null;
        } catch (IOException e7) {
            e7.printStackTrace();
            inputStream.close();
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            throw th;
        }
    }

    private final Pair getVP8Dimension(InputStream inputStream) {
        inputStream.skip(7);
        int nextByteAsInt = getNextByteAsInt(inputStream);
        int nextByteAsInt2 = getNextByteAsInt(inputStream);
        int nextByteAsInt3 = getNextByteAsInt(inputStream);
        if (nextByteAsInt == 157 && nextByteAsInt2 == 1 && nextByteAsInt3 == 42) {
            return new Pair(Integer.valueOf(get2BytesAsInt(inputStream)), Integer.valueOf(get2BytesAsInt(inputStream)));
        }
        return null;
    }

    private final Pair getVP8LDimension(InputStream inputStream) {
        getInt(inputStream);
        if (getNextByteAsInt(inputStream) != 47) {
            return null;
        }
        int read = inputStream.read();
        return new Pair(Integer.valueOf(((inputStream.read() & 255) | ((read & 63) << 8)) + 1), Integer.valueOf((((inputStream.read() & 15) << 10) | ((inputStream.read() & 255) << 2) | ((read & 192) >> 6)) + 1));
    }

    private final Pair getVP8XDimension(InputStream inputStream) {
        inputStream.skip(8);
        return new Pair(Integer.valueOf(read3Bytes(inputStream) + 1), Integer.valueOf(read3Bytes(inputStream) + 1));
    }

    private final boolean compare(byte[] bArr, String str) {
        if (bArr.length != str.length()) {
            return false;
        }
        IntRange indices = ArraysKt.getIndices(bArr);
        if (!(indices instanceof Collection) || !((Collection) indices).isEmpty()) {
            Iterator it = indices.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                if (((byte) str.charAt(nextInt)) != bArr[nextInt]) {
                    return false;
                }
            }
        }
        return true;
    }

    private final String getHeader(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append((char) (UShort.m870constructorimpl((short) b) & 65535));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private final int getInt(InputStream inputStream) {
        int nextByteAsInt = getNextByteAsInt(inputStream);
        int nextByteAsInt2 = getNextByteAsInt(inputStream);
        return (getNextByteAsInt(inputStream) << 24) | (getNextByteAsInt(inputStream) << 16) | (nextByteAsInt2 << 8) | nextByteAsInt;
    }

    public static final int get2BytesAsInt(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "stream");
        WebpUtil webpUtil = INSTANCE;
        return (webpUtil.getNextByteAsInt(inputStream) << 8) | webpUtil.getNextByteAsInt(inputStream);
    }

    private final int read3Bytes(InputStream inputStream) {
        return (getNextByteAsInt(inputStream) << 16) | (getNextByteAsInt(inputStream) << 8) | getNextByteAsInt(inputStream);
    }

    private final int getNextByteAsInt(InputStream inputStream) {
        return inputStream.read() & 255;
    }
}
