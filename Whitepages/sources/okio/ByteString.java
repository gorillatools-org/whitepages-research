package okio;

import com.google.firebase.messaging.Constants;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public class ByteString implements Serializable, Comparable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private static final long serialVersionUID = 1;
    private final byte[] data;
    private transient int hashCode;
    private transient String utf8;

    public static final ByteString decodeBase64(String str) {
        return Companion.decodeBase64(str);
    }

    public static final ByteString encodeUtf8(String str) {
        return Companion.encodeUtf8(str);
    }

    public static final ByteString of(byte... bArr) {
        return Companion.of(bArr);
    }

    public String utf8() {
        String utf8$okio = getUtf8$okio();
        if (utf8$okio != null) {
            return utf8$okio;
        }
        String utf8String = _JvmPlatformKt.toUtf8String(internalArray$okio());
        setUtf8$okio(utf8String);
        return utf8String;
    }

    public String base64() {
        return Base64.encodeBase64$default(getData$okio(), (byte[]) null, 1, (Object) null);
    }

    public ByteString(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        this.data = bArr;
    }

    public final byte[] getData$okio() {
        return this.data;
    }

    public final int getHashCode$okio() {
        return this.hashCode;
    }

    public final void setHashCode$okio(int i) {
        this.hashCode = i;
    }

    public final String getUtf8$okio() {
        return this.utf8;
    }

    public String hex() {
        char[] cArr = new char[(getData$okio().length * 2)];
        int i = 0;
        for (byte b : getData$okio()) {
            int i2 = i + 1;
            cArr[i] = okio.internal.ByteString.getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            i += 2;
            cArr[i2] = okio.internal.ByteString.getHEX_DIGIT_CHARS()[b & 15];
        }
        return StringsKt.concatToString(cArr);
    }

    public final void setUtf8$okio(String str) {
        this.utf8 = str;
    }

    public final ByteString md5() {
        return digest$okio("MD5");
    }

    public final ByteString sha1() {
        return digest$okio("SHA-1");
    }

    public final ByteString sha256() {
        return digest$okio("SHA-256");
    }

    public ByteString toAsciiLowercase() {
        int i = 0;
        while (i < getData$okio().length) {
            byte b = getData$okio()[i];
            if (b < 65 || b > 90) {
                i++;
            } else {
                byte[] data$okio = getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                copyOf[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b2 = copyOf[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        copyOf[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return this;
    }

    public ByteString digest$okio(String str) {
        Intrinsics.checkNotNullParameter(str, "algorithm");
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(this.data, 0, size());
        byte[] digest = instance.digest();
        Intrinsics.checkNotNullExpressionValue(digest, "digestBytes");
        return new ByteString(digest);
    }

    public static /* synthetic */ ByteString substring$default(ByteString byteString, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = SegmentedByteString.getDEFAULT__ByteString_size();
            }
            return byteString.substring(i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: substring");
    }

    public final byte getByte(int i) {
        return internalGet$okio(i);
    }

    public final int size() {
        return getSize$okio();
    }

    public ByteString substring(int i, int i2) {
        int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(this, i2);
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (resolveDefaultParameter > getData$okio().length) {
            throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + ')').toString());
        } else if (resolveDefaultParameter - i < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        } else if (i == 0 && resolveDefaultParameter == getData$okio().length) {
            return this;
        } else {
            return new ByteString(ArraysKt.copyOfRange(getData$okio(), i, resolveDefaultParameter));
        }
    }

    public byte internalGet$okio(int i) {
        return getData$okio()[i];
    }

    public void write$okio(Buffer buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        okio.internal.ByteString.commonWrite(this, buffer, i, i2);
    }

    public int getSize$okio() {
        return getData$okio().length;
    }

    public byte[] toByteArray() {
        byte[] data$okio = getData$okio();
        byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    public byte[] internalArray$okio() {
        return getData$okio();
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteString, "other");
        return byteString.rangeEquals(i2, getData$okio(), i, i3);
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "other");
        return i >= 0 && i <= getData$okio().length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && SegmentedByteString.arrayRangeEquals(getData$okio(), i, bArr, i2, i3);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return byteString.indexOf(byteString2, i);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
    }

    public final int indexOf(ByteString byteString, int i) {
        Intrinsics.checkNotNullParameter(byteString, "other");
        return indexOf(byteString.internalArray$okio(), i);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                i = SegmentedByteString.getDEFAULT__ByteString_size();
            }
            return byteString.lastIndexOf(byteString2, i);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
    }

    public final boolean startsWith(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "prefix");
        return rangeEquals(0, byteString, 0, byteString.size());
    }

    private final void readObject(ObjectInputStream objectInputStream) throws IOException {
        ByteString read = Companion.read(objectInputStream, objectInputStream.readInt());
        Field declaredField = ByteString.class.getDeclaredField(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        declaredField.setAccessible(true);
        declaredField.set(this, read.data);
    }

    public final boolean endsWith(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "suffix");
        return rangeEquals(size() - byteString.size(), byteString, 0, byteString.size());
    }

    public int indexOf(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "other");
        int length = getData$okio().length - bArr.length;
        int max = Math.max(i, 0);
        if (max <= length) {
            while (!SegmentedByteString.arrayRangeEquals(getData$okio(), max, bArr, 0, bArr.length)) {
                if (max != length) {
                    max++;
                }
            }
            return max;
        }
        return -1;
    }

    private final void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ ByteString of$default(Companion companion, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = SegmentedByteString.getDEFAULT__ByteString_size();
            }
            return companion.of(bArr, i, i2);
        }

        private Companion() {
        }

        public final ByteString encodeString(String str, Charset charset) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            Intrinsics.checkNotNullParameter(charset, "charset");
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            return new ByteString(bytes);
        }

        public final ByteString of(byte... bArr) {
            Intrinsics.checkNotNullParameter(bArr, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
            return new ByteString(copyOf);
        }

        public final ByteString of(byte[] bArr, int i, int i2) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(bArr, i2);
            SegmentedByteString.checkOffsetAndCount((long) bArr.length, (long) i, (long) resolveDefaultParameter);
            return new ByteString(ArraysKt.copyOfRange(bArr, i, resolveDefaultParameter + i));
        }

        public final ByteString read(InputStream inputStream, int i) {
            Intrinsics.checkNotNullParameter(inputStream, "<this>");
            if (i >= 0) {
                byte[] bArr = new byte[i];
                int i2 = 0;
                while (i2 < i) {
                    int read = inputStream.read(bArr, i2, i - i2);
                    if (read != -1) {
                        i2 += read;
                    } else {
                        throw new EOFException();
                    }
                }
                return new ByteString(bArr);
            }
            throw new IllegalArgumentException(("byteCount < 0: " + i).toString());
        }

        public final ByteString encodeUtf8(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            ByteString byteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
            byteString.setUtf8$okio(str);
            return byteString;
        }

        public final ByteString decodeBase64(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            byte[] decodeBase64ToArray = Base64.decodeBase64ToArray(str);
            if (decodeBase64ToArray != null) {
                return new ByteString(decodeBase64ToArray);
            }
            return null;
        }

        /* renamed from: -deprecated_decodeBase64  reason: not valid java name */
        public final ByteString m1066deprecated_decodeBase64(String str) {
            Intrinsics.checkNotNullParameter(str, "string");
            return decodeBase64(str);
        }

        public final ByteString decodeHex(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            if (str.length() % 2 == 0) {
                int length = str.length() / 2;
                byte[] bArr = new byte[length];
                for (int i = 0; i < length; i++) {
                    int i2 = i * 2;
                    bArr[i] = (byte) ((okio.internal.ByteString.decodeHexDigit(str.charAt(i2)) << 4) + okio.internal.ByteString.decodeHexDigit(str.charAt(i2 + 1)));
                }
                return new ByteString(bArr);
            }
            throw new IllegalArgumentException(("Unexpected hex string: " + str).toString());
        }
    }

    public final int lastIndexOf(ByteString byteString, int i) {
        Intrinsics.checkNotNullParameter(byteString, "other");
        return lastIndexOf(byteString.internalArray$okio(), i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "other");
        for (int min = Math.min(SegmentedByteString.resolveDefaultParameter(this, i), getData$okio().length - bArr.length); -1 < min; min--) {
            if (SegmentedByteString.arrayRangeEquals(getData$okio(), min, bArr, 0, bArr.length)) {
                return min;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == getData$okio().length && byteString.rangeEquals(0, getData$okio(), 0, getData$okio().length)) {
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
        int hashCode2 = Arrays.hashCode(getData$okio());
        setHashCode$okio(hashCode2);
        return hashCode2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r0 < r1) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0028, code lost:
        if (r7 < r8) goto L_0x002a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compareTo(okio.ByteString r10) {
        /*
            r9 = this;
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            int r0 = r9.size()
            int r1 = r10.size()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L_0x0013:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L_0x002e
            byte r7 = r9.getByte(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.getByte(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L_0x0028
            int r4 = r4 + 1
            goto L_0x0013
        L_0x0028:
            if (r7 >= r8) goto L_0x002c
        L_0x002a:
            r3 = r5
            goto L_0x0034
        L_0x002c:
            r3 = r6
            goto L_0x0034
        L_0x002e:
            if (r0 != r1) goto L_0x0031
            goto L_0x0034
        L_0x0031:
            if (r0 >= r1) goto L_0x002c
            goto L_0x002a
        L_0x0034:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.compareTo(okio.ByteString):int");
    }

    public String toString() {
        ByteString byteString;
        String str;
        if (getData$okio().length == 0) {
            str = "[size=0]";
        } else {
            int access$codePointIndexToCharIndex = okio.internal.ByteString.codePointIndexToCharIndex(getData$okio(), 64);
            if (access$codePointIndexToCharIndex != -1) {
                String utf82 = utf8();
                String substring = utf82.substring(0, access$codePointIndexToCharIndex);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null), ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
                if (access$codePointIndexToCharIndex < utf82.length()) {
                    return "[size=" + getData$okio().length + " text=" + replace$default + "…]";
                }
                return "[text=" + replace$default + ']';
            } else if (getData$okio().length <= 64) {
                str = "[hex=" + hex() + ']';
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[size=");
                sb.append(getData$okio().length);
                sb.append(" hex=");
                int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(this, 64);
                if (resolveDefaultParameter > getData$okio().length) {
                    throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + ')').toString());
                } else if (resolveDefaultParameter >= 0) {
                    if (resolveDefaultParameter == getData$okio().length) {
                        byteString = this;
                    } else {
                        byteString = new ByteString(ArraysKt.copyOfRange(getData$okio(), 0, resolveDefaultParameter));
                    }
                    sb.append(byteString.hex());
                    sb.append("…]");
                    return sb.toString();
                } else {
                    throw new IllegalArgumentException("endIndex < beginIndex");
                }
            }
        }
        return str;
    }
}
