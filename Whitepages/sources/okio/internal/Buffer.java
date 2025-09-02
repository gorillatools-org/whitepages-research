package okio.internal;

import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Options;
import okio.Segment;
import okio.SegmentedByteString;
import okio._JvmPlatformKt;

/* renamed from: okio.internal.-Buffer  reason: invalid class name */
public abstract class Buffer {
    private static final byte[] HEX_DIGIT_BYTES = _JvmPlatformKt.asUtf8ToByteArray("0123456789abcdef");

    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static final boolean rangeEquals(Segment segment, int i, byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(bArr, "bytes");
        int i4 = segment.limit;
        byte[] bArr2 = segment.data;
        while (i2 < i3) {
            if (i == i4) {
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                byte[] bArr3 = segment.data;
                int i5 = segment.pos;
                bArr2 = bArr3;
                i = i5;
                i4 = segment.limit;
            }
            if (bArr2[i] != bArr[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public static final String readUtf8Line(okio.Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (j > 0) {
            long j2 = j - 1;
            if (buffer.getByte(j2) == 13) {
                String readUtf8 = buffer.readUtf8(j2);
                buffer.skip(2);
                return readUtf8;
            }
        }
        String readUtf82 = buffer.readUtf8(j);
        buffer.skip(1);
        return readUtf82;
    }

    public static /* synthetic */ int selectPrefix$default(okio.Buffer buffer, Options options, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return selectPrefix(buffer, options, z);
    }

    public static final int selectPrefix(okio.Buffer buffer, Options options, boolean z) {
        int i;
        int i2;
        int i3;
        Segment segment;
        int i4;
        okio.Buffer buffer2 = buffer;
        Intrinsics.checkNotNullParameter(buffer2, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Segment segment2 = buffer2.head;
        if (segment2 == null) {
            return z ? -2 : -1;
        }
        byte[] bArr = segment2.data;
        int i5 = segment2.pos;
        int i6 = segment2.limit;
        int[] trie$okio = options.getTrie$okio();
        Segment segment3 = segment2;
        int i7 = -1;
        int i8 = 0;
        loop0:
        while (true) {
            int i9 = i8 + 1;
            int i10 = trie$okio[i8];
            int i11 = i8 + 2;
            int i12 = trie$okio[i9];
            if (i12 != -1) {
                i7 = i12;
            }
            if (segment3 == null) {
                break;
            }
            if (i10 < 0) {
                int i13 = i11 + (i10 * -1);
                while (true) {
                    int i14 = i5 + 1;
                    int i15 = i11 + 1;
                    if ((bArr[i5] & 255) != trie$okio[i11]) {
                        return i7;
                    }
                    boolean z2 = i15 == i13;
                    if (i14 == i6) {
                        Intrinsics.checkNotNull(segment3);
                        Segment segment4 = segment3.next;
                        Intrinsics.checkNotNull(segment4);
                        i4 = segment4.pos;
                        byte[] bArr2 = segment4.data;
                        i3 = segment4.limit;
                        if (segment4 == segment2) {
                            if (!z2) {
                                break loop0;
                            }
                            bArr = bArr2;
                            segment = null;
                        } else {
                            byte[] bArr3 = bArr2;
                            segment = segment4;
                            bArr = bArr3;
                        }
                    } else {
                        segment = segment3;
                        i3 = i6;
                        i4 = i14;
                    }
                    if (z2) {
                        i2 = trie$okio[i15];
                        i = i4;
                        i6 = i3;
                        segment3 = segment;
                        break;
                    }
                    i5 = i4;
                    i6 = i3;
                    segment3 = segment;
                    i11 = i15;
                }
            } else {
                i = i5 + 1;
                byte b = bArr[i5] & 255;
                int i16 = i11 + i10;
                while (i11 != i16) {
                    if (b == trie$okio[i11]) {
                        i2 = trie$okio[i11 + i10];
                        if (i == i6) {
                            segment3 = segment3.next;
                            Intrinsics.checkNotNull(segment3);
                            i = segment3.pos;
                            bArr = segment3.data;
                            i6 = segment3.limit;
                            if (segment3 == segment2) {
                                segment3 = null;
                            }
                        }
                    } else {
                        i11++;
                    }
                }
                return i7;
            }
            if (i2 >= 0) {
                return i2;
            }
            i8 = -i2;
            i5 = i;
        }
        if (z) {
            return -2;
        }
        return i7;
    }

    public static final Buffer.UnsafeCursor commonReadAndWriteUnsafe(okio.Buffer buffer, Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        Buffer.UnsafeCursor resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(unsafeCursor);
        if (resolveDefaultParameter.buffer == null) {
            resolveDefaultParameter.buffer = buffer;
            resolveDefaultParameter.readWrite = true;
            return resolveDefaultParameter;
        }
        throw new IllegalStateException("already attached to a buffer");
    }
}
