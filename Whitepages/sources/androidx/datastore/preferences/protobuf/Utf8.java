package androidx.datastore.preferences.protobuf;

import java.nio.charset.Charset;
import java.util.Arrays;

abstract class Utf8 {
    private static final Processor processor;

    static {
        Processor processor2;
        if (!UnsafeProcessor.isAvailable() || Android.isOnAndroidDevice()) {
            processor2 = new SafeProcessor();
        } else {
            processor2 = new UnsafeProcessor();
        }
        processor = processor2;
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    static int encodedLength(String str) {
        int length = str.length();
        int i = 0;
        while (i < length && str.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = str.charAt(i);
                if (charAt >= 2048) {
                    i2 += encodedLengthGeneral(str, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
    }

    private static int encodedLengthGeneral(String str, int i) {
        int length = str.length();
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(str, i) >= 65536) {
                        i++;
                    } else {
                        throw new UnpairedSurrogateException(i, length);
                    }
                }
            }
            i++;
        }
        return i2;
    }

    static int encode(String str, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(str, bArr, i, i2);
    }

    static String decodeUtf8(byte[] bArr, int i, int i2) {
        return processor.decodeUtf8(bArr, i, i2);
    }

    static abstract class Processor {
        /* access modifiers changed from: package-private */
        public abstract String decodeUtf8(byte[] bArr, int i, int i2);

        /* access modifiers changed from: package-private */
        public abstract int encodeUtf8(String str, byte[] bArr, int i, int i2);

        Processor() {
        }
    }

    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        /* access modifiers changed from: package-private */
        public String decodeUtf8(byte[] bArr, int i, int i2) {
            if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
                int i3 = i + i2;
                char[] cArr = new char[i2];
                int i4 = 0;
                while (r11 < i3) {
                    byte b = bArr[r11];
                    if (!DecodeUtil.isOneByte(b)) {
                        break;
                    }
                    i = r11 + 1;
                    DecodeUtil.handleOneByte(b, cArr, i4);
                    i4++;
                }
                int i5 = i4;
                while (r11 < i3) {
                    int i6 = r11 + 1;
                    byte b2 = bArr[r11];
                    if (DecodeUtil.isOneByte(b2)) {
                        int i7 = i5 + 1;
                        DecodeUtil.handleOneByte(b2, cArr, i5);
                        while (i6 < i3) {
                            byte b3 = bArr[i6];
                            if (!DecodeUtil.isOneByte(b3)) {
                                break;
                            }
                            i6++;
                            DecodeUtil.handleOneByte(b3, cArr, i7);
                            i7++;
                        }
                        i5 = i7;
                        r11 = i6;
                    } else if (DecodeUtil.isTwoBytes(b2)) {
                        if (i6 < i3) {
                            r11 += 2;
                            DecodeUtil.handleTwoBytes(b2, bArr[i6], cArr, i5);
                            i5++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (DecodeUtil.isThreeBytes(b2)) {
                        if (i6 < i3 - 1) {
                            int i8 = r11 + 2;
                            r11 += 3;
                            DecodeUtil.handleThreeBytes(b2, bArr[i6], bArr[i8], cArr, i5);
                            i5++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (i6 < i3 - 2) {
                        byte b4 = bArr[i6];
                        int i9 = r11 + 3;
                        byte b5 = bArr[r11 + 2];
                        r11 += 4;
                        DecodeUtil.handleFourBytes(b2, b4, b5, bArr[i9], cArr, i5);
                        i5 += 2;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                }
                return new String(cArr, 0, i5);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        }

        /* access modifiers changed from: package-private */
        public int encodeUtf8(String str, byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            char charAt;
            int length = str.length();
            int i5 = i2 + i;
            int i6 = 0;
            while (i6 < length && (i4 = i6 + i) < i5 && (charAt = str.charAt(i6)) < 128) {
                bArr[i4] = (byte) charAt;
                i6++;
            }
            if (i6 == length) {
                return i + length;
            }
            int i7 = i + i6;
            while (i6 < length) {
                char charAt2 = str.charAt(i6);
                if (charAt2 < 128 && i7 < i5) {
                    bArr[i7] = (byte) charAt2;
                    i7++;
                } else if (charAt2 < 2048 && i7 <= i5 - 2) {
                    int i8 = i7 + 1;
                    bArr[i7] = (byte) ((charAt2 >>> 6) | 960);
                    i7 += 2;
                    bArr[i8] = (byte) ((charAt2 & '?') | 128);
                } else if ((charAt2 < 55296 || 57343 < charAt2) && i7 <= i5 - 3) {
                    bArr[i7] = (byte) ((charAt2 >>> 12) | 480);
                    int i9 = i7 + 2;
                    bArr[i7 + 1] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    i7 += 3;
                    bArr[i9] = (byte) ((charAt2 & '?') | 128);
                } else if (i7 <= i5 - 4) {
                    int i10 = i6 + 1;
                    if (i10 != str.length()) {
                        char charAt3 = str.charAt(i10);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            bArr[i7] = (byte) ((codePoint >>> 18) | 240);
                            bArr[i7 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                            int i11 = i7 + 3;
                            bArr[i7 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i7 += 4;
                            bArr[i11] = (byte) ((codePoint & 63) | 128);
                            i6 = i10;
                        } else {
                            i6 = i10;
                        }
                    }
                    throw new UnpairedSurrogateException(i6 - 1, length);
                } else if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i6 + 1) != str.length() && Character.isSurrogatePair(charAt2, str.charAt(i3)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i7);
                } else {
                    throw new UnpairedSurrogateException(i6, length);
                }
                i6++;
            }
            return i7;
        }
    }

    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        /* access modifiers changed from: package-private */
        public String decodeUtf8(byte[] bArr, int i, int i2) {
            Charset charset = Internal.UTF_8;
            String str = new String(bArr, i, i2, charset);
            if (str.indexOf(65533) < 0 || Arrays.equals(str.getBytes(charset), Arrays.copyOfRange(bArr, i, i2 + i))) {
                return str;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0033 A[LOOP:1: B:13:0x0033->B:38:0x00f8, LOOP_START, PHI: r2 r4 r6 r9 r10 r11 
          PHI: (r2v3 int) = (r2v2 int), (r2v5 int) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r4v3 long) = (r4v2 long), (r4v4 long) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r6v3 long) = (r6v1 long), (r6v4 long) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r9v1 java.lang.String) = (r9v0 java.lang.String), (r9v2 java.lang.String) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r10v1 java.lang.String) = (r10v0 java.lang.String), (r10v2 java.lang.String) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r11v3 long) = (r11v2 long), (r11v4 long) binds: [B:10:0x002f, B:38:0x00f8] A[DONT_GENERATE, DONT_INLINE]] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int encodeUtf8(java.lang.String r25, byte[] r26, int r27, int r28) {
            /*
                r24 = this;
                r0 = r25
                r1 = r26
                r2 = r27
                r3 = r28
                long r4 = (long) r2
                long r6 = (long) r3
                long r6 = r6 + r4
                int r8 = r25.length()
                java.lang.String r9 = " at index "
                java.lang.String r10 = "Failed writing "
                if (r8 > r3) goto L_0x0144
                int r11 = r1.length
                int r11 = r11 - r3
                if (r11 < r2) goto L_0x0144
                r2 = 0
            L_0x001a:
                r11 = 1
                r3 = 128(0x80, float:1.794E-43)
                if (r2 >= r8) goto L_0x002f
                char r13 = r0.charAt(r2)
                if (r13 >= r3) goto L_0x002f
                long r11 = r11 + r4
                byte r3 = (byte) r13
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r4, r3)
                int r2 = r2 + 1
                r4 = r11
                goto L_0x001a
            L_0x002f:
                if (r2 != r8) goto L_0x0033
                int r0 = (int) r4
                return r0
            L_0x0033:
                if (r2 >= r8) goto L_0x0142
                char r13 = r0.charAt(r2)
                if (r13 >= r3) goto L_0x004f
                int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r14 >= 0) goto L_0x004f
                long r14 = r4 + r11
                byte r13 = (byte) r13
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r4, r13)
                r22 = r6
                r17 = r10
                r20 = r11
                r4 = r14
                r14 = r9
                goto L_0x00f8
            L_0x004f:
                r14 = 2048(0x800, float:2.87E-42)
                r15 = 2
                if (r13 >= r14) goto L_0x0076
                long r17 = r6 - r15
                int r14 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
                if (r14 > 0) goto L_0x0076
                r14 = r9
                r17 = r10
                long r9 = r4 + r11
                int r11 = r13 >>> 6
                r11 = r11 | 960(0x3c0, float:1.345E-42)
                byte r11 = (byte) r11
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r4, r11)
                long r4 = r4 + r15
                r11 = r13 & 63
                r11 = r11 | r3
                byte r11 = (byte) r11
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r9, r11)
            L_0x0070:
                r22 = r6
                r20 = 1
                goto L_0x00f8
            L_0x0076:
                r14 = r9
                r17 = r10
                r9 = 57343(0xdfff, float:8.0355E-41)
                r10 = 55296(0xd800, float:7.7486E-41)
                r11 = 3
                if (r13 < r10) goto L_0x0085
                if (r9 >= r13) goto L_0x00ad
            L_0x0085:
                long r18 = r6 - r11
                int r18 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
                if (r18 > 0) goto L_0x00ad
                r18 = 1
                long r9 = r4 + r18
                int r11 = r13 >>> 12
                r11 = r11 | 480(0x1e0, float:6.73E-43)
                byte r11 = (byte) r11
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r4, r11)
                long r11 = r4 + r15
                int r15 = r13 >>> 6
                r15 = r15 & 63
                r15 = r15 | r3
                byte r15 = (byte) r15
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r9, r15)
                r9 = 3
                long r4 = r4 + r9
                r9 = r13 & 63
                r9 = r9 | r3
                byte r9 = (byte) r9
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r11, r9)
                goto L_0x0070
            L_0x00ad:
                r11 = 4
                long r20 = r6 - r11
                int r20 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
                if (r20 > 0) goto L_0x010c
                int r9 = r2 + 1
                if (r9 == r8) goto L_0x0104
                char r2 = r0.charAt(r9)
                boolean r10 = java.lang.Character.isSurrogatePair(r13, r2)
                if (r10 == 0) goto L_0x0103
                int r2 = java.lang.Character.toCodePoint(r13, r2)
                r20 = 1
                long r11 = r4 + r20
                int r10 = r2 >>> 18
                r10 = r10 | 240(0xf0, float:3.36E-43)
                byte r10 = (byte) r10
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r4, r10)
                r22 = r6
                long r6 = r4 + r15
                int r10 = r2 >>> 12
                r10 = r10 & 63
                r10 = r10 | r3
                byte r10 = (byte) r10
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r11, r10)
                r10 = 3
                long r11 = r4 + r10
                int r10 = r2 >>> 6
                r10 = r10 & 63
                r10 = r10 | r3
                byte r10 = (byte) r10
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r6, r10)
                r6 = 4
                long r4 = r4 + r6
                r2 = r2 & 63
                r2 = r2 | r3
                byte r2 = (byte) r2
                androidx.datastore.preferences.protobuf.UnsafeUtil.putByte(r1, r11, r2)
                r2 = r9
            L_0x00f8:
                int r2 = r2 + 1
                r9 = r14
                r10 = r17
                r11 = r20
                r6 = r22
                goto L_0x0033
            L_0x0103:
                r2 = r9
            L_0x0104:
                androidx.datastore.preferences.protobuf.Utf8$UnpairedSurrogateException r0 = new androidx.datastore.preferences.protobuf.Utf8$UnpairedSurrogateException
                int r2 = r2 + -1
                r0.<init>(r2, r8)
                throw r0
            L_0x010c:
                if (r10 > r13) goto L_0x0124
                if (r13 > r9) goto L_0x0124
                int r1 = r2 + 1
                if (r1 == r8) goto L_0x011e
                char r0 = r0.charAt(r1)
                boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
                if (r0 != 0) goto L_0x0124
            L_0x011e:
                androidx.datastore.preferences.protobuf.Utf8$UnpairedSurrogateException r0 = new androidx.datastore.preferences.protobuf.Utf8$UnpairedSurrogateException
                r0.<init>(r2, r8)
                throw r0
            L_0x0124:
                java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r6 = r17
                r1.append(r6)
                r1.append(r13)
                r7 = r14
                r1.append(r7)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0142:
                int r0 = (int) r4
                return r0
            L_0x0144:
                r7 = r9
                r6 = r10
                java.lang.ArrayIndexOutOfBoundsException r1 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r6)
                int r8 = r8 + -1
                char r0 = r0.charAt(r8)
                r4.append(r0)
                r4.append(r7)
                int r0 = r2 + r3
                r4.append(r0)
                java.lang.String r0 = r4.toString()
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.Utf8.UnsafeProcessor.encodeUtf8(java.lang.String, byte[], int, int):int");
        }
    }

    private static class DecodeUtil {
        private static char highSurrogate(int i) {
            return (char) ((i >>> 10) + 55232);
        }

        private static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        /* access modifiers changed from: private */
        public static boolean isOneByte(byte b) {
            return b >= 0;
        }

        /* access modifiers changed from: private */
        public static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        /* access modifiers changed from: private */
        public static boolean isTwoBytes(byte b) {
            return b < -32;
        }

        private static char lowSurrogate(int i) {
            return (char) ((i & 1023) + 56320);
        }

        private static int trailingByteValue(byte b) {
            return b & 63;
        }

        /* access modifiers changed from: private */
        public static void handleOneByte(byte b, char[] cArr, int i) {
            cArr[i] = (char) b;
        }

        /* access modifiers changed from: private */
        public static void handleTwoBytes(byte b, byte b2, char[] cArr, int i) {
            if (b < -62 || isNotTrailingByte(b2)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i] = (char) (((b & 31) << 6) | trailingByteValue(b2));
        }

        /* access modifiers changed from: private */
        public static void handleThreeBytes(byte b, byte b2, byte b3, char[] cArr, int i) {
            if (isNotTrailingByte(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || isNotTrailingByte(b3)))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i] = (char) (((b & 15) << 12) | (trailingByteValue(b2) << 6) | trailingByteValue(b3));
        }

        /* access modifiers changed from: private */
        public static void handleFourBytes(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) {
            if (isNotTrailingByte(b2) || (((b << 28) + (b2 + 112)) >> 30) != 0 || isNotTrailingByte(b3) || isNotTrailingByte(b4)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            int trailingByteValue = ((b & 7) << 18) | (trailingByteValue(b2) << 12) | (trailingByteValue(b3) << 6) | trailingByteValue(b4);
            cArr[i] = highSurrogate(trailingByteValue);
            cArr[i + 1] = lowSurrogate(trailingByteValue);
        }
    }
}
