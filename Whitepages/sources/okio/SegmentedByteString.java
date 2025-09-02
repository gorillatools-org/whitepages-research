package okio;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Buffer;
import okio.internal.ByteString;

/* renamed from: okio.-SegmentedByteString  reason: invalid class name */
public abstract class SegmentedByteString {
    private static final int DEFAULT__ByteString_size = -1234567890;
    private static final Buffer.UnsafeCursor DEFAULT__new_UnsafeCursor = new Buffer.UnsafeCursor();

    public static final int reverseBytes(int i) {
        return ((i & 255) << 24) | ((-16777216 & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    public static final long reverseBytes(long j) {
        return ((j & 255) << 56) | ((-72057594037927936L & j) >>> 56) | ((71776119061217280L & j) >>> 40) | ((280375465082880L & j) >>> 24) | ((1095216660480L & j) >>> 8) | ((4278190080L & j) << 8) | ((16711680 & j) << 24) | ((65280 & j) << 40);
    }

    public static final short reverseBytes(short s) {
        return (short) (((s & 255) << 8) | ((65280 & s) >>> 8));
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException("size=" + j + " offset=" + j2 + " byteCount=" + j3);
        }
    }

    public static final boolean arrayRangeEquals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "a");
        Intrinsics.checkNotNullParameter(bArr2, "b");
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    public static final String toHexString(byte b) {
        return StringsKt.concatToString(new char[]{ByteString.getHEX_DIGIT_CHARS()[(b >> 4) & 15], ByteString.getHEX_DIGIT_CHARS()[b & 15]});
    }

    public static final String toHexString(int i) {
        int i2 = 0;
        if (i == 0) {
            return "0";
        }
        char[] cArr = {ByteString.getHEX_DIGIT_CHARS()[(i >> 28) & 15], ByteString.getHEX_DIGIT_CHARS()[(i >> 24) & 15], ByteString.getHEX_DIGIT_CHARS()[(i >> 20) & 15], ByteString.getHEX_DIGIT_CHARS()[(i >> 16) & 15], ByteString.getHEX_DIGIT_CHARS()[(i >> 12) & 15], ByteString.getHEX_DIGIT_CHARS()[(i >> 8) & 15], ByteString.getHEX_DIGIT_CHARS()[(i >> 4) & 15], ByteString.getHEX_DIGIT_CHARS()[i & 15]};
        while (i2 < 8 && cArr[i2] == '0') {
            i2++;
        }
        return StringsKt.concatToString(cArr, i2, 8);
    }

    public static final Buffer.UnsafeCursor getDEFAULT__new_UnsafeCursor() {
        return DEFAULT__new_UnsafeCursor;
    }

    public static final Buffer.UnsafeCursor resolveDefaultParameter(Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return unsafeCursor == DEFAULT__new_UnsafeCursor ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    public static final int getDEFAULT__ByteString_size() {
        return DEFAULT__ByteString_size;
    }

    public static final int resolveDefaultParameter(ByteString byteString, int i) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return i == DEFAULT__ByteString_size ? byteString.size() : i;
    }

    public static final int resolveDefaultParameter(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return i == DEFAULT__ByteString_size ? bArr.length : i;
    }
}
