package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

abstract class Utf8 {
    private static final Processor processor;

    /* access modifiers changed from: private */
    public static int incompleteStateFor(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    static {
        Processor processor2;
        if (!UnsafeProcessor.isAvailable() || Android.isOnAndroidDevice()) {
            processor2 = new SafeProcessor();
        } else {
            processor2 = new UnsafeProcessor();
        }
        processor = processor2;
    }

    static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return processor.isValidUtf8(bArr, i, i2);
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            return incompleteStateFor(b);
        }
        if (i3 == 1) {
            return incompleteStateFor(b, bArr[i]);
        }
        if (i3 == 2) {
            return incompleteStateFor((int) b, (int) bArr[i], (int) bArr[i + 1]);
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: private */
    public static int incompleteStateFor(ByteBuffer byteBuffer, int i, int i2, int i3) {
        if (i3 == 0) {
            return incompleteStateFor(i);
        }
        if (i3 == 1) {
            return incompleteStateFor(i, byteBuffer.get(i2));
        }
        if (i3 == 2) {
            return incompleteStateFor(i, (int) byteBuffer.get(i2), (int) byteBuffer.get(i2 + 1));
        }
        throw new AssertionError();
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += encodedLengthGeneral(charSequence, i);
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

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) >= 65536) {
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

    static int encode(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(charSequence, bArr, i, i2);
    }

    static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    static String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
        return processor.decodeUtf8(byteBuffer, i, i2);
    }

    static String decodeUtf8(byte[] bArr, int i, int i2) {
        return processor.decodeUtf8(bArr, i, i2);
    }

    /* access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 - 7;
        int i4 = i;
        while (i4 < i3 && (byteBuffer.getLong(i4) & -9187201950435737472L) == 0) {
            i4 += 8;
        }
        return i4 - i;
    }

    static abstract class Processor {
        /* access modifiers changed from: package-private */
        public abstract String decodeUtf8(byte[] bArr, int i, int i2);

        /* access modifiers changed from: package-private */
        public abstract String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2);

        /* access modifiers changed from: package-private */
        public abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2);

        /* access modifiers changed from: package-private */
        public abstract int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3);

        /* access modifiers changed from: package-private */
        public abstract int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3);

        Processor() {
        }

        /* access modifiers changed from: package-private */
        public final boolean isValidUtf8(byte[] bArr, int i, int i2) {
            return partialIsValidUtf8(0, bArr, i, i2) == 0;
        }

        /* access modifiers changed from: package-private */
        public final boolean isValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            return partialIsValidUtf8(0, byteBuffer, i, i2) == 0;
        }

        /* access modifiers changed from: package-private */
        public final int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                return partialIsValidUtf8(i, byteBuffer.array(), i2 + arrayOffset, arrayOffset + i3);
            } else if (byteBuffer.isDirect()) {
                return partialIsValidUtf8Direct(i, byteBuffer, i2, i3);
            } else {
                return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x004c, code lost:
            if (r8.get(r9) > -65) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x008f, code lost:
            if (r8.get(r7) > -65) goto L_0x0091;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
            if (r8.get(r9) > -65) goto L_0x001d;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final int partialIsValidUtf8Default(int r7, java.nio.ByteBuffer r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L_0x0092
                if (r9 < r10) goto L_0x0005
                return r7
            L_0x0005:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L_0x001e
                r7 = -62
                if (r0 < r7) goto L_0x001d
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L_0x001a
                goto L_0x001d
            L_0x001a:
                r9 = r7
                goto L_0x0092
            L_0x001d:
                return r2
            L_0x001e:
                r4 = -16
                if (r0 >= r4) goto L_0x004f
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L_0x0038
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r7 < r10) goto L_0x0035
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r9)
                return r7
            L_0x0035:
                r5 = r9
                r9 = r7
                r7 = r5
            L_0x0038:
                if (r7 > r3) goto L_0x004e
                r4 = -96
                if (r0 != r1) goto L_0x0040
                if (r7 < r4) goto L_0x004e
            L_0x0040:
                r1 = -19
                if (r0 != r1) goto L_0x0046
                if (r7 >= r4) goto L_0x004e
            L_0x0046:
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L_0x001a
            L_0x004e:
                return r2
            L_0x004f:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                if (r1 != 0) goto L_0x0064
                int r7 = r9 + 1
                byte r1 = r8.get(r9)
                if (r7 < r10) goto L_0x0062
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r1)
                return r7
            L_0x0062:
                r9 = 0
                goto L_0x006a
            L_0x0064:
                int r7 = r7 >> 16
                byte r7 = (byte) r7
                r5 = r9
                r9 = r7
                r7 = r5
            L_0x006a:
                if (r9 != 0) goto L_0x007c
                int r9 = r7 + 1
                byte r7 = r8.get(r7)
                if (r9 < r10) goto L_0x0079
                int r7 = com.google.protobuf.Utf8.incompleteStateFor((int) r0, (int) r1, (int) r7)
                return r7
            L_0x0079:
                r5 = r9
                r9 = r7
                r7 = r5
            L_0x007c:
                if (r1 > r3) goto L_0x0091
                int r0 = r0 << 28
                int r1 = r1 + 112
                int r0 = r0 + r1
                int r0 = r0 >> 30
                if (r0 != 0) goto L_0x0091
                if (r9 > r3) goto L_0x0091
                int r9 = r7 + 1
                byte r7 = r8.get(r7)
                if (r7 <= r3) goto L_0x0092
            L_0x0091:
                return r2
            L_0x0092:
                int r7 = partialIsValidUtf8(r8, r9, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.Processor.partialIsValidUtf8Default(int, java.nio.ByteBuffer, int, int):int");
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            int access$200 = i + Utf8.estimateConsecutiveAscii(byteBuffer, i, i2);
            while (access$200 < i2) {
                int i3 = access$200 + 1;
                byte b = byteBuffer.get(access$200);
                if (b >= 0) {
                    access$200 = i3;
                } else if (b < -32) {
                    if (i3 >= i2) {
                        return b;
                    }
                    if (b < -62 || byteBuffer.get(i3) > -65) {
                        return -1;
                    }
                    access$200 += 2;
                } else if (b < -16) {
                    if (i3 >= i2 - 1) {
                        return Utf8.incompleteStateFor(byteBuffer, b, i3, i2 - i3);
                    }
                    int i4 = access$200 + 2;
                    byte b2 = byteBuffer.get(i3);
                    if (b2 > -65 || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || byteBuffer.get(i4) > -65))) {
                        return -1;
                    }
                    access$200 += 3;
                } else if (i3 >= i2 - 2) {
                    return Utf8.incompleteStateFor(byteBuffer, b, i3, i2 - i3);
                } else {
                    int i5 = access$200 + 2;
                    byte b3 = byteBuffer.get(i3);
                    if (b3 <= -65 && (((b << 28) + (b3 + 112)) >> 30) == 0) {
                        int i6 = access$200 + 3;
                        if (byteBuffer.get(i5) <= -65) {
                            access$200 += 4;
                            if (byteBuffer.get(i6) > -65) {
                            }
                        }
                    }
                    return -1;
                }
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public final String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
            if (byteBuffer.hasArray()) {
                return decodeUtf8(byteBuffer.array(), byteBuffer.arrayOffset() + i, i2);
            } else if (byteBuffer.isDirect()) {
                return decodeUtf8Direct(byteBuffer, i, i2);
            } else {
                return decodeUtf8Default(byteBuffer, i, i2);
            }
        }

        /* access modifiers changed from: package-private */
        public final String decodeUtf8Default(ByteBuffer byteBuffer, int i, int i2) {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) >= 0) {
                int i3 = i + i2;
                char[] cArr = new char[i2];
                int i4 = 0;
                while (r11 < i3) {
                    byte b = byteBuffer.get(r11);
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
                    byte b2 = byteBuffer.get(r11);
                    if (DecodeUtil.isOneByte(b2)) {
                        int i7 = i5 + 1;
                        DecodeUtil.handleOneByte(b2, cArr, i5);
                        while (i6 < i3) {
                            byte b3 = byteBuffer.get(i6);
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
                            DecodeUtil.handleTwoBytes(b2, byteBuffer.get(i6), cArr, i5);
                            i5++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (DecodeUtil.isThreeBytes(b2)) {
                        if (i6 < i3 - 1) {
                            int i8 = r11 + 2;
                            r11 += 3;
                            DecodeUtil.handleThreeBytes(b2, byteBuffer.get(i6), byteBuffer.get(i8), cArr, i5);
                            i5++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (i6 < i3 - 2) {
                        byte b4 = byteBuffer.get(i6);
                        int i9 = r11 + 3;
                        byte b5 = byteBuffer.get(r11 + 2);
                        r11 += 4;
                        DecodeUtil.handleFourBytes(b2, b4, b5, byteBuffer.get(i9), cArr, i5);
                        i5 += 2;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                }
                return new String(cArr, 0, i5);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0046, code lost:
            if (r8[r9] > -65) goto L_0x0048;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0083, code lost:
            if (r8[r7] > -65) goto L_0x0085;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r8[r9] > -65) goto L_0x001b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int partialIsValidUtf8(int r7, byte[] r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L_0x0086
                if (r9 < r10) goto L_0x0005
                return r7
            L_0x0005:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L_0x001c
                r7 = -62
                if (r0 < r7) goto L_0x001b
                int r7 = r9 + 1
                byte r9 = r8[r9]
                if (r9 <= r3) goto L_0x0018
                goto L_0x001b
            L_0x0018:
                r9 = r7
                goto L_0x0086
            L_0x001b:
                return r2
            L_0x001c:
                r4 = -16
                if (r0 >= r4) goto L_0x0049
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L_0x0034
                int r7 = r9 + 1
                byte r9 = r8[r9]
                if (r7 < r10) goto L_0x0031
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r9)
                return r7
            L_0x0031:
                r5 = r9
                r9 = r7
                r7 = r5
            L_0x0034:
                if (r7 > r3) goto L_0x0048
                r4 = -96
                if (r0 != r1) goto L_0x003c
                if (r7 < r4) goto L_0x0048
            L_0x003c:
                r1 = -19
                if (r0 != r1) goto L_0x0042
                if (r7 >= r4) goto L_0x0048
            L_0x0042:
                int r7 = r9 + 1
                byte r9 = r8[r9]
                if (r9 <= r3) goto L_0x0018
            L_0x0048:
                return r2
            L_0x0049:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                if (r1 != 0) goto L_0x005c
                int r7 = r9 + 1
                byte r1 = r8[r9]
                if (r7 < r10) goto L_0x005a
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r1)
                return r7
            L_0x005a:
                r9 = 0
                goto L_0x0062
            L_0x005c:
                int r7 = r7 >> 16
                byte r7 = (byte) r7
                r5 = r9
                r9 = r7
                r7 = r5
            L_0x0062:
                if (r9 != 0) goto L_0x0072
                int r9 = r7 + 1
                byte r7 = r8[r7]
                if (r9 < r10) goto L_0x006f
                int r7 = com.google.protobuf.Utf8.incompleteStateFor((int) r0, (int) r1, (int) r7)
                return r7
            L_0x006f:
                r5 = r9
                r9 = r7
                r7 = r5
            L_0x0072:
                if (r1 > r3) goto L_0x0085
                int r0 = r0 << 28
                int r1 = r1 + 112
                int r0 = r0 + r1
                int r0 = r0 >> 30
                if (r0 != 0) goto L_0x0085
                if (r9 > r3) goto L_0x0085
                int r9 = r7 + 1
                byte r7 = r8[r7]
                if (r7 <= r3) goto L_0x0086
            L_0x0085:
                return r2
            L_0x0086:
                int r7 = partialIsValidUtf8(r8, r9, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        /* access modifiers changed from: package-private */
        public int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3) {
            return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
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
        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2) {
            return decodeUtf8Default(byteBuffer, i, i2);
        }

        /* access modifiers changed from: package-private */
        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            char charAt;
            int length = charSequence.length();
            int i5 = i2 + i;
            int i6 = 0;
            while (i6 < length && (i4 = i6 + i) < i5 && (charAt = charSequence.charAt(i6)) < 128) {
                bArr[i4] = (byte) charAt;
                i6++;
            }
            if (i6 == length) {
                return i + length;
            }
            int i7 = i + i6;
            while (i6 < length) {
                char charAt2 = charSequence.charAt(i6);
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
                    if (i10 != charSequence.length()) {
                        char charAt3 = charSequence.charAt(i10);
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
                } else if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i6 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i7);
                } else {
                    throw new UnpairedSurrogateException(i6, length);
                }
                i6++;
            }
            return i7;
        }

        private static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= 0) {
                i++;
            }
            if (i >= i2) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i, i2);
        }

        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    i = i3;
                } else if (b < -32) {
                    if (i3 >= i2) {
                        return b;
                    }
                    if (b >= -62) {
                        i += 2;
                        if (bArr[i3] > -65) {
                        }
                    }
                    return -1;
                } else if (b < -16) {
                    if (i3 >= i2 - 1) {
                        return Utf8.incompleteStateFor(bArr, i3, i2);
                    }
                    int i4 = i + 2;
                    byte b2 = bArr[i3];
                    if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                        i += 3;
                        if (bArr[i4] > -65) {
                        }
                    }
                    return -1;
                } else if (i3 >= i2 - 2) {
                    return Utf8.incompleteStateFor(bArr, i3, i2);
                } else {
                    int i5 = i + 2;
                    byte b3 = bArr[i3];
                    if (b3 <= -65 && (((b << 28) + (b3 + 112)) >> 30) == 0) {
                        int i6 = i + 3;
                        if (bArr[i5] <= -65) {
                            i += 4;
                            if (bArr[i6] > -65) {
                            }
                        }
                    }
                    return -1;
                }
            }
            return 0;
        }
    }

    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0058, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r12, r0) > -65) goto L_0x005d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x009e, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r12, r0) > -65) goto L_0x00a0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int partialIsValidUtf8(int r11, byte[] r12, int r13, int r14) {
            /*
                r10 = this;
                r0 = r13 | r14
                int r1 = r12.length
                int r1 = r1 - r14
                r0 = r0 | r1
                if (r0 < 0) goto L_0x00a8
                long r0 = (long) r13
                long r13 = (long) r14
                if (r11 == 0) goto L_0x00a1
                int r2 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
                if (r2 < 0) goto L_0x0010
                return r11
            L_0x0010:
                byte r2 = (byte) r11
                r3 = -32
                r4 = -1
                r5 = -65
                r6 = 1
                if (r2 >= r3) goto L_0x002a
                r11 = -62
                if (r2 < r11) goto L_0x0029
                long r6 = r6 + r0
                byte r11 = com.google.protobuf.UnsafeUtil.getByte(r12, r0)
                if (r11 <= r5) goto L_0x0026
                goto L_0x0029
            L_0x0026:
                r0 = r6
                goto L_0x00a1
            L_0x0029:
                return r4
            L_0x002a:
                r8 = -16
                if (r2 >= r8) goto L_0x005e
                int r11 = r11 >> 8
                int r11 = ~r11
                byte r11 = (byte) r11
                if (r11 != 0) goto L_0x0044
                long r8 = r0 + r6
                byte r11 = com.google.protobuf.UnsafeUtil.getByte(r12, r0)
                int r0 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
                if (r0 < 0) goto L_0x0043
                int r11 = com.google.protobuf.Utf8.incompleteStateFor(r2, r11)
                return r11
            L_0x0043:
                r0 = r8
            L_0x0044:
                if (r11 > r5) goto L_0x005d
                r8 = -96
                if (r2 != r3) goto L_0x004c
                if (r11 < r8) goto L_0x005d
            L_0x004c:
                r3 = -19
                if (r2 != r3) goto L_0x0052
                if (r11 >= r8) goto L_0x005d
            L_0x0052:
                long r2 = r0 + r6
                byte r11 = com.google.protobuf.UnsafeUtil.getByte(r12, r0)
                if (r11 <= r5) goto L_0x005b
                goto L_0x005d
            L_0x005b:
                r0 = r2
                goto L_0x00a1
            L_0x005d:
                return r4
            L_0x005e:
                int r3 = r11 >> 8
                int r3 = ~r3
                byte r3 = (byte) r3
                if (r3 != 0) goto L_0x0076
                long r8 = r0 + r6
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r12, r0)
                int r11 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
                if (r11 < 0) goto L_0x0073
                int r11 = com.google.protobuf.Utf8.incompleteStateFor(r2, r3)
                return r11
            L_0x0073:
                r11 = 0
                r0 = r8
                goto L_0x0079
            L_0x0076:
                int r11 = r11 >> 16
                byte r11 = (byte) r11
            L_0x0079:
                if (r11 != 0) goto L_0x008b
                long r8 = r0 + r6
                byte r11 = com.google.protobuf.UnsafeUtil.getByte(r12, r0)
                int r0 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
                if (r0 < 0) goto L_0x008a
                int r11 = com.google.protobuf.Utf8.incompleteStateFor((int) r2, (int) r3, (int) r11)
                return r11
            L_0x008a:
                r0 = r8
            L_0x008b:
                if (r3 > r5) goto L_0x00a0
                int r2 = r2 << 28
                int r3 = r3 + 112
                int r2 = r2 + r3
                int r2 = r2 >> 30
                if (r2 != 0) goto L_0x00a0
                if (r11 > r5) goto L_0x00a0
                long r2 = r0 + r6
                byte r11 = com.google.protobuf.UnsafeUtil.getByte(r12, r0)
                if (r11 <= r5) goto L_0x005b
            L_0x00a0:
                return r4
            L_0x00a1:
                long r13 = r13 - r0
                int r11 = (int) r13
                int r11 = partialIsValidUtf8(r12, r0, r11)
                return r11
            L_0x00a8:
                java.lang.ArrayIndexOutOfBoundsException r11 = new java.lang.ArrayIndexOutOfBoundsException
                int r12 = r12.length
                java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
                java.lang.Object[] r12 = new java.lang.Object[]{r12, r13, r14}
                java.lang.String r13 = "Array length=%d, index=%d, limit=%d"
                java.lang.String r12 = java.lang.String.format(r13, r12)
                r11.<init>(r12)
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r0) > -65) goto L_0x0033;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0061, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r0) > -65) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a3, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r0) > -65) goto L_0x00a5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int partialIsValidUtf8Direct(int r10, java.nio.ByteBuffer r11, int r12, int r13) {
            /*
                r9 = this;
                r0 = r12 | r13
                int r1 = r11.limit()
                int r1 = r1 - r13
                r0 = r0 | r1
                if (r0 < 0) goto L_0x00ad
                long r0 = com.google.protobuf.UnsafeUtil.addressOffset(r11)
                long r2 = (long) r12
                long r0 = r0 + r2
                int r13 = r13 - r12
                long r11 = (long) r13
                long r11 = r11 + r0
                if (r10 == 0) goto L_0x00a6
                int r13 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
                if (r13 < 0) goto L_0x001a
                return r10
            L_0x001a:
                byte r13 = (byte) r10
                r2 = -32
                r3 = -1
                r4 = -65
                r5 = 1
                if (r13 >= r2) goto L_0x0034
                r10 = -62
                if (r13 < r10) goto L_0x0033
                long r5 = r5 + r0
                byte r10 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r10 <= r4) goto L_0x0030
                goto L_0x0033
            L_0x0030:
                r0 = r5
                goto L_0x00a6
            L_0x0033:
                return r3
            L_0x0034:
                r7 = -16
                if (r13 >= r7) goto L_0x0064
                int r10 = r10 >> 8
                int r10 = ~r10
                byte r10 = (byte) r10
                if (r10 != 0) goto L_0x004e
                long r7 = r0 + r5
                byte r10 = com.google.protobuf.UnsafeUtil.getByte(r0)
                int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
                if (r0 < 0) goto L_0x004d
                int r10 = com.google.protobuf.Utf8.incompleteStateFor(r13, r10)
                return r10
            L_0x004d:
                r0 = r7
            L_0x004e:
                if (r10 > r4) goto L_0x0063
                r7 = -96
                if (r13 != r2) goto L_0x0056
                if (r10 < r7) goto L_0x0063
            L_0x0056:
                r2 = -19
                if (r13 != r2) goto L_0x005c
                if (r10 >= r7) goto L_0x0063
            L_0x005c:
                long r5 = r5 + r0
                byte r10 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r10 <= r4) goto L_0x0030
            L_0x0063:
                return r3
            L_0x0064:
                int r2 = r10 >> 8
                int r2 = ~r2
                byte r2 = (byte) r2
                if (r2 != 0) goto L_0x007c
                long r7 = r0 + r5
                byte r2 = com.google.protobuf.UnsafeUtil.getByte(r0)
                int r10 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
                if (r10 < 0) goto L_0x0079
                int r10 = com.google.protobuf.Utf8.incompleteStateFor(r13, r2)
                return r10
            L_0x0079:
                r10 = 0
                r0 = r7
                goto L_0x007f
            L_0x007c:
                int r10 = r10 >> 16
                byte r10 = (byte) r10
            L_0x007f:
                if (r10 != 0) goto L_0x0091
                long r7 = r0 + r5
                byte r10 = com.google.protobuf.UnsafeUtil.getByte(r0)
                int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
                if (r0 < 0) goto L_0x0090
                int r10 = com.google.protobuf.Utf8.incompleteStateFor((int) r13, (int) r2, (int) r10)
                return r10
            L_0x0090:
                r0 = r7
            L_0x0091:
                if (r2 > r4) goto L_0x00a5
                int r13 = r13 << 28
                int r2 = r2 + 112
                int r13 = r13 + r2
                int r13 = r13 >> 30
                if (r13 != 0) goto L_0x00a5
                if (r10 > r4) goto L_0x00a5
                long r5 = r5 + r0
                byte r10 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r10 <= r4) goto L_0x0030
            L_0x00a5:
                return r3
            L_0x00a6:
                long r11 = r11 - r0
                int r10 = (int) r11
                int r10 = partialIsValidUtf8(r0, r10)
                return r10
            L_0x00ad:
                java.lang.ArrayIndexOutOfBoundsException r10 = new java.lang.ArrayIndexOutOfBoundsException
                int r11 = r11.limit()
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                java.lang.Object[] r11 = new java.lang.Object[]{r11, r12, r13}
                java.lang.String r12 = "buffer limit=%d, index=%d, limit=%d"
                java.lang.String r11 = java.lang.String.format(r12, r11)
                r10.<init>(r11)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8Direct(int, java.nio.ByteBuffer, int, int):int");
        }

        /* access modifiers changed from: package-private */
        public String decodeUtf8(byte[] bArr, int i, int i2) {
            Charset charset = Internal.UTF_8;
            String str = new String(bArr, i, i2, charset);
            if (!str.contains("�") || Arrays.equals(str.getBytes(charset), Arrays.copyOfRange(bArr, i, i2 + i))) {
                return str;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        /* access modifiers changed from: package-private */
        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2) {
            long j;
            int i3 = i;
            int i4 = i2;
            if ((i3 | i4 | ((byteBuffer.limit() - i3) - i4)) >= 0) {
                long addressOffset = UnsafeUtil.addressOffset(byteBuffer) + ((long) i3);
                long j2 = ((long) i4) + addressOffset;
                char[] cArr = new char[i4];
                int i5 = 0;
                while (j < j2) {
                    byte b = UnsafeUtil.getByte(j);
                    if (!DecodeUtil.isOneByte(b)) {
                        break;
                    }
                    addressOffset = j + 1;
                    DecodeUtil.handleOneByte(b, cArr, i5);
                    i5++;
                }
                int i6 = i5;
                while (j < j2) {
                    long j3 = j + 1;
                    byte b2 = UnsafeUtil.getByte(j);
                    if (DecodeUtil.isOneByte(b2)) {
                        int i7 = i6 + 1;
                        DecodeUtil.handleOneByte(b2, cArr, i6);
                        while (j3 < j2) {
                            byte b3 = UnsafeUtil.getByte(j3);
                            if (!DecodeUtil.isOneByte(b3)) {
                                break;
                            }
                            j3++;
                            DecodeUtil.handleOneByte(b3, cArr, i7);
                            i7++;
                        }
                        i6 = i7;
                        j = j3;
                    } else if (DecodeUtil.isTwoBytes(b2)) {
                        if (j3 < j2) {
                            j += 2;
                            DecodeUtil.handleTwoBytes(b2, UnsafeUtil.getByte(j3), cArr, i6);
                            i6++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (DecodeUtil.isThreeBytes(b2)) {
                        if (j3 < j2 - 1) {
                            long j4 = 2 + j;
                            j += 3;
                            DecodeUtil.handleThreeBytes(b2, UnsafeUtil.getByte(j3), UnsafeUtil.getByte(j4), cArr, i6);
                            i6++;
                        } else {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                    } else if (j3 < j2 - 2) {
                        byte b4 = UnsafeUtil.getByte(j3);
                        long j5 = 3 + j;
                        byte b5 = UnsafeUtil.getByte(2 + j);
                        j += 4;
                        DecodeUtil.handleFourBytes(b2, b4, b5, UnsafeUtil.getByte(j5), cArr, i6);
                        i6 += 2;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                }
                return new String(cArr, 0, i6);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)}));
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
        public int encodeUtf8(java.lang.CharSequence r25, byte[] r26, int r27, int r28) {
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
                com.google.protobuf.UnsafeUtil.putByte(r1, r4, r3)
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
                com.google.protobuf.UnsafeUtil.putByte(r1, r4, r13)
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
                com.google.protobuf.UnsafeUtil.putByte(r1, r4, r11)
                long r4 = r4 + r15
                r11 = r13 & 63
                r11 = r11 | r3
                byte r11 = (byte) r11
                com.google.protobuf.UnsafeUtil.putByte(r1, r9, r11)
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
                com.google.protobuf.UnsafeUtil.putByte(r1, r4, r11)
                long r11 = r4 + r15
                int r15 = r13 >>> 6
                r15 = r15 & 63
                r15 = r15 | r3
                byte r15 = (byte) r15
                com.google.protobuf.UnsafeUtil.putByte(r1, r9, r15)
                r9 = 3
                long r4 = r4 + r9
                r9 = r13 & 63
                r9 = r9 | r3
                byte r9 = (byte) r9
                com.google.protobuf.UnsafeUtil.putByte(r1, r11, r9)
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
                com.google.protobuf.UnsafeUtil.putByte(r1, r4, r10)
                r22 = r6
                long r6 = r4 + r15
                int r10 = r2 >>> 12
                r10 = r10 & 63
                r10 = r10 | r3
                byte r10 = (byte) r10
                com.google.protobuf.UnsafeUtil.putByte(r1, r11, r10)
                r10 = 3
                long r11 = r4 + r10
                int r10 = r2 >>> 6
                r10 = r10 & 63
                r10 = r10 | r3
                byte r10 = (byte) r10
                com.google.protobuf.UnsafeUtil.putByte(r1, r6, r10)
                r6 = 4
                long r4 = r4 + r6
                r2 = r2 & 63
                r2 = r2 | r3
                byte r2 = (byte) r2
                com.google.protobuf.UnsafeUtil.putByte(r1, r11, r2)
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
                com.google.protobuf.Utf8$UnpairedSurrogateException r0 = new com.google.protobuf.Utf8$UnpairedSurrogateException
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
                com.google.protobuf.Utf8$UnpairedSurrogateException r0 = new com.google.protobuf.Utf8$UnpairedSurrogateException
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.encodeUtf8(java.lang.CharSequence, byte[], int, int):int");
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j, int i) {
            int i2 = 0;
            if (i < 16) {
                return 0;
            }
            int i3 = 8 - (((int) j) & 7);
            while (i2 < i3) {
                long j2 = 1 + j;
                if (UnsafeUtil.getByte(bArr, j) < 0) {
                    return i2;
                }
                i2++;
                j = j2;
            }
            while (true) {
                int i4 = i2 + 8;
                if (i4 <= i && (UnsafeUtil.getLong(bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j) & -9187201950435737472L) == 0) {
                    j += 8;
                    i2 = i4;
                }
            }
            while (i2 < i) {
                long j3 = j + 1;
                if (UnsafeUtil.getByte(bArr, j) < 0) {
                    return i2;
                }
                i2++;
                j = j3;
            }
            return i;
        }

        private static int unsafeEstimateConsecutiveAscii(long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = (int) ((-j) & 7);
            int i3 = i2;
            while (i3 > 0) {
                long j2 = 1 + j;
                if (UnsafeUtil.getByte(j) < 0) {
                    return i2 - i3;
                }
                i3--;
                j = j2;
            }
            int i4 = i - i2;
            while (i4 >= 8 && (UnsafeUtil.getLong(j) & -9187201950435737472L) == 0) {
                j += 8;
                i4 -= 8;
            }
            return i - i4;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int partialIsValidUtf8(byte[] r10, long r11, int r13) {
            /*
                int r0 = unsafeEstimateConsecutiveAscii(r10, r11, r13)
                int r13 = r13 - r0
                long r0 = (long) r0
                long r11 = r11 + r0
            L_0x0007:
                r0 = 0
                r1 = r0
            L_0x0009:
                r2 = 1
                if (r13 <= 0) goto L_0x001a
                long r4 = r11 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r10, r11)
                if (r1 < 0) goto L_0x0019
                int r13 = r13 + -1
                r11 = r4
                goto L_0x0009
            L_0x0019:
                r11 = r4
            L_0x001a:
                if (r13 != 0) goto L_0x001d
                return r0
            L_0x001d:
                int r0 = r13 + -1
                r4 = -32
                r5 = -1
                r6 = -65
                if (r1 >= r4) goto L_0x003a
                if (r0 != 0) goto L_0x0029
                return r1
            L_0x0029:
                int r13 = r13 + -2
                r0 = -62
                if (r1 < r0) goto L_0x0039
                long r2 = r2 + r11
                byte r11 = com.google.protobuf.UnsafeUtil.getByte(r10, r11)
                if (r11 <= r6) goto L_0x0037
                goto L_0x0039
            L_0x0037:
                r11 = r2
                goto L_0x0007
            L_0x0039:
                return r5
            L_0x003a:
                r7 = -16
                r8 = 2
                if (r1 >= r7) goto L_0x0065
                r7 = 2
                if (r0 >= r7) goto L_0x0048
                int r10 = unsafeIncompleteStateFor(r10, r1, r11, r0)
                return r10
            L_0x0048:
                int r13 = r13 + -3
                long r2 = r2 + r11
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10, r11)
                if (r0 > r6) goto L_0x0064
                r7 = -96
                if (r1 != r4) goto L_0x0057
                if (r0 < r7) goto L_0x0064
            L_0x0057:
                r4 = -19
                if (r1 != r4) goto L_0x005d
                if (r0 >= r7) goto L_0x0064
            L_0x005d:
                long r11 = r11 + r8
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10, r2)
                if (r0 <= r6) goto L_0x0007
            L_0x0064:
                return r5
            L_0x0065:
                r4 = 3
                if (r0 >= r4) goto L_0x006d
                int r10 = unsafeIncompleteStateFor(r10, r1, r11, r0)
                return r10
            L_0x006d:
                int r13 = r13 + -4
                long r2 = r2 + r11
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10, r11)
                if (r0 > r6) goto L_0x008f
                int r1 = r1 << 28
                int r0 = r0 + 112
                int r1 = r1 + r0
                int r0 = r1 >> 30
                if (r0 != 0) goto L_0x008f
                long r8 = r8 + r11
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10, r2)
                if (r0 > r6) goto L_0x008f
                r0 = 3
                long r11 = r11 + r0
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10, r8)
                if (r0 <= r6) goto L_0x0007
            L_0x008f:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(byte[], long, int):int");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int partialIsValidUtf8(long r10, int r12) {
            /*
                int r0 = unsafeEstimateConsecutiveAscii(r10, r12)
                long r1 = (long) r0
                long r10 = r10 + r1
                int r12 = r12 - r0
            L_0x0007:
                r0 = 0
                r1 = r0
            L_0x0009:
                r2 = 1
                if (r12 <= 0) goto L_0x001a
                long r4 = r10 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r10)
                if (r1 < 0) goto L_0x0019
                int r12 = r12 + -1
                r10 = r4
                goto L_0x0009
            L_0x0019:
                r10 = r4
            L_0x001a:
                if (r12 != 0) goto L_0x001d
                return r0
            L_0x001d:
                int r0 = r12 + -1
                r4 = -32
                r5 = -1
                r6 = -65
                if (r1 >= r4) goto L_0x003a
                if (r0 != 0) goto L_0x0029
                return r1
            L_0x0029:
                int r12 = r12 + -2
                r0 = -62
                if (r1 < r0) goto L_0x0039
                long r2 = r2 + r10
                byte r10 = com.google.protobuf.UnsafeUtil.getByte(r10)
                if (r10 <= r6) goto L_0x0037
                goto L_0x0039
            L_0x0037:
                r10 = r2
                goto L_0x0007
            L_0x0039:
                return r5
            L_0x003a:
                r7 = -16
                r8 = 2
                if (r1 >= r7) goto L_0x0065
                r7 = 2
                if (r0 >= r7) goto L_0x0048
                int r10 = unsafeIncompleteStateFor(r10, r1, r0)
                return r10
            L_0x0048:
                int r12 = r12 + -3
                long r2 = r2 + r10
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10)
                if (r0 > r6) goto L_0x0064
                r7 = -96
                if (r1 != r4) goto L_0x0057
                if (r0 < r7) goto L_0x0064
            L_0x0057:
                r4 = -19
                if (r1 != r4) goto L_0x005d
                if (r0 >= r7) goto L_0x0064
            L_0x005d:
                long r10 = r10 + r8
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r2)
                if (r0 <= r6) goto L_0x0007
            L_0x0064:
                return r5
            L_0x0065:
                r4 = 3
                if (r0 >= r4) goto L_0x006d
                int r10 = unsafeIncompleteStateFor(r10, r1, r0)
                return r10
            L_0x006d:
                int r12 = r12 + -4
                long r2 = r2 + r10
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r10)
                if (r0 > r6) goto L_0x008f
                int r1 = r1 << 28
                int r0 = r0 + 112
                int r1 = r1 + r0
                int r0 = r1 >> 30
                if (r0 != 0) goto L_0x008f
                long r8 = r8 + r10
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r2)
                if (r0 > r6) goto L_0x008f
                r0 = 3
                long r10 = r10 + r0
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r8)
                if (r0 <= r6) goto L_0x0007
            L_0x008f:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(long, int):int");
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i, long j, int i2) {
            if (i2 == 0) {
                return Utf8.incompleteStateFor(i);
            }
            if (i2 == 1) {
                return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j));
            }
            if (i2 == 2) {
                return Utf8.incompleteStateFor(i, (int) UnsafeUtil.getByte(bArr, j), (int) UnsafeUtil.getByte(bArr, j + 1));
            }
            throw new AssertionError();
        }

        private static int unsafeIncompleteStateFor(long j, int i, int i2) {
            if (i2 == 0) {
                return Utf8.incompleteStateFor(i);
            }
            if (i2 == 1) {
                return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j));
            }
            if (i2 == 2) {
                return Utf8.incompleteStateFor(i, (int) UnsafeUtil.getByte(j), (int) UnsafeUtil.getByte(j + 1));
            }
            throw new AssertionError();
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
