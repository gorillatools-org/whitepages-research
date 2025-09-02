package okio;

import java.security.MessageDigest;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okio.internal.SegmentedByteString;

/* renamed from: okio.SegmentedByteString  reason: case insensitive filesystem */
public final class C0035SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    public final byte[][] getSegments$okio() {
        return this.segments;
    }

    public final int[] getDirectory$okio() {
        return this.directory;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0035SegmentedByteString(byte[][] bArr, int[] iArr) {
        super(ByteString.EMPTY.getData$okio());
        Intrinsics.checkNotNullParameter(bArr, "segments");
        Intrinsics.checkNotNullParameter(iArr, "directory");
        this.segments = bArr;
        this.directory = iArr;
    }

    public String base64() {
        return toByteString().base64();
    }

    public String hex() {
        return toByteString().hex();
    }

    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    public ByteString digest$okio(String str) {
        Intrinsics.checkNotNullParameter(str, "algorithm");
        MessageDigest instance = MessageDigest.getInstance(str);
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = getDirectory$okio()[length + i];
            int i4 = getDirectory$okio()[i];
            instance.update(getSegments$okio()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
        byte[] digest = instance.digest();
        Intrinsics.checkNotNullExpressionValue(digest, "digestBytes");
        return new ByteString(digest);
    }

    public void write$okio(Buffer buffer, int i, int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int i4 = i + i2;
        int segment = SegmentedByteString.segment(this, i);
        while (i < i4) {
            if (segment == 0) {
                i3 = 0;
            } else {
                i3 = getDirectory$okio()[segment - 1];
            }
            int i5 = getDirectory$okio()[getSegments$okio().length + segment];
            int min = Math.min(i4, (getDirectory$okio()[segment] - i3) + i3) - i;
            int i6 = i5 + (i - i3);
            Segment segment2 = new Segment(getSegments$okio()[segment], i6, i6 + min, true, false);
            Segment segment3 = buffer.head;
            if (segment3 == null) {
                segment2.prev = segment2;
                segment2.next = segment2;
                buffer.head = segment2;
            } else {
                Intrinsics.checkNotNull(segment3);
                Segment segment4 = segment3.prev;
                Intrinsics.checkNotNull(segment4);
                segment4.push(segment2);
            }
            i += min;
            segment++;
        }
        buffer.setSize$okio(buffer.size() + ((long) i2));
    }

    public ByteString substring(int i, int i2) {
        int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter((ByteString) this, i2);
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex=" + i + " < 0").toString());
        } else if (resolveDefaultParameter <= size()) {
            int i3 = resolveDefaultParameter - i;
            if (i3 < 0) {
                throw new IllegalArgumentException(("endIndex=" + resolveDefaultParameter + " < beginIndex=" + i).toString());
            } else if (i == 0 && resolveDefaultParameter == size()) {
                return this;
            } else {
                if (i == resolveDefaultParameter) {
                    return ByteString.EMPTY;
                }
                int segment = SegmentedByteString.segment(this, i);
                int segment2 = SegmentedByteString.segment(this, resolveDefaultParameter - 1);
                byte[][] bArr = (byte[][]) ArraysKt.copyOfRange((Object[]) getSegments$okio(), segment, segment2 + 1);
                int[] iArr = new int[(bArr.length * 2)];
                int i4 = 0;
                if (segment <= segment2) {
                    int i5 = segment;
                    int i6 = 0;
                    while (true) {
                        iArr[i6] = Math.min(getDirectory$okio()[i5] - i, i3);
                        int i7 = i6 + 1;
                        iArr[i6 + bArr.length] = getDirectory$okio()[getSegments$okio().length + i5];
                        if (i5 == segment2) {
                            break;
                        }
                        i5++;
                        i6 = i7;
                    }
                }
                if (segment != 0) {
                    i4 = getDirectory$okio()[segment - 1];
                }
                int length = bArr.length;
                iArr[length] = iArr[length] + (i - i4);
                return new C0035SegmentedByteString(bArr, iArr);
            }
        } else {
            throw new IllegalArgumentException(("endIndex=" + resolveDefaultParameter + " > length(" + size() + ')').toString());
        }
    }

    public int indexOf(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "other");
        return toByteString().indexOf(bArr, i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "other");
        return toByteString().lastIndexOf(bArr, i);
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    public byte[] internalArray$okio() {
        return toByteArray();
    }

    public byte internalGet$okio(int i) {
        int i2;
        SegmentedByteString.checkOffsetAndCount((long) getDirectory$okio()[getSegments$okio().length - 1], (long) i, 1);
        int segment = SegmentedByteString.segment(this, i);
        if (segment == 0) {
            i2 = 0;
        } else {
            i2 = getDirectory$okio()[segment - 1];
        }
        return getSegments$okio()[segment][(i - i2) + getDirectory$okio()[getSegments$okio().length + segment]];
    }

    public String toString() {
        return toByteString().toString();
    }

    private final Object writeReplace() {
        ByteString byteString = toByteString();
        Intrinsics.checkNotNull(byteString, "null cannot be cast to non-null type java.lang.Object");
        return byteString;
    }

    public int getSize$okio() {
        return getDirectory$okio()[getSegments$okio().length - 1];
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[size()];
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory$okio()[length + i];
            int i5 = getDirectory$okio()[i];
            int i6 = i5 - i2;
            ArraysKt.copyInto(getSegments$okio()[i], bArr, i3, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        int i4;
        Intrinsics.checkNotNullParameter(byteString, "other");
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int i5 = i3 + i;
        int segment = SegmentedByteString.segment(this, i);
        while (i < i5) {
            if (segment == 0) {
                i4 = 0;
            } else {
                i4 = getDirectory$okio()[segment - 1];
            }
            int i6 = getDirectory$okio()[getSegments$okio().length + segment];
            int min = Math.min(i5, (getDirectory$okio()[segment] - i4) + i4) - i;
            if (!byteString.rangeEquals(i2, getSegments$okio()[segment], i6 + (i - i4), min)) {
                return false;
            }
            i2 += min;
            i += min;
            segment++;
        }
        return true;
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        int i4;
        Intrinsics.checkNotNullParameter(bArr, "other");
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i5 = i3 + i;
        int segment = SegmentedByteString.segment(this, i);
        while (i < i5) {
            if (segment == 0) {
                i4 = 0;
            } else {
                i4 = getDirectory$okio()[segment - 1];
            }
            int i6 = getDirectory$okio()[getSegments$okio().length + segment];
            int min = Math.min(i5, (getDirectory$okio()[segment] - i4) + i4) - i;
            if (!SegmentedByteString.arrayRangeEquals(getSegments$okio()[segment], i6 + (i - i4), bArr, i2, min)) {
                return false;
            }
            i2 += min;
            i += min;
            segment++;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode$okio = getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory$okio()[length + i];
            int i5 = getDirectory$okio()[i];
            byte[] bArr = getSegments$okio()[i];
            int i6 = (i5 - i3) + i4;
            while (i4 < i6) {
                i2 = (i2 * 31) + bArr[i4];
                i4++;
            }
            i++;
            i3 = i5;
        }
        setHashCode$okio(i2);
        return i2;
    }
}
