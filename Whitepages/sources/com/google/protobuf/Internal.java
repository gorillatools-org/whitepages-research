package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

public abstract class Internal {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final ByteBuffer EMPTY_BYTE_BUFFER;
    public static final CodedInputStream EMPTY_CODED_INPUT_STREAM;
    static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset US_ASCII = Charset.forName("US-ASCII");
    static final Charset UTF_8 = Charset.forName("UTF-8");

    public interface BooleanList extends ProtobufList {
        BooleanList mutableCopyWithCapacity(int i);
    }

    public interface DoubleList extends ProtobufList {
        DoubleList mutableCopyWithCapacity(int i);
    }

    public interface EnumLite {
        int getNumber();
    }

    public interface EnumLiteMap {
        EnumLite findValueByNumber(int i);
    }

    public interface EnumVerifier {
        boolean isInRange(int i);
    }

    public interface FloatList extends ProtobufList {
        FloatList mutableCopyWithCapacity(int i);
    }

    public interface IntList extends ProtobufList {
        void addInt(int i);

        int getInt(int i);

        IntList mutableCopyWithCapacity(int i);

        int setInt(int i, int i2);
    }

    public interface LongList extends ProtobufList {
        LongList mutableCopyWithCapacity(int i);
    }

    public interface ProtobufList extends List, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList mutableCopyWithCapacity(int i);
    }

    public static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    static {
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        EMPTY_BYTE_BUFFER = ByteBuffer.wrap(bArr);
        EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(bArr);
    }

    static Object checkNotNull(Object obj) {
        obj.getClass();
        return obj;
    }

    static Object checkNotNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return Utf8.isValidUtf8(bArr);
    }

    public static String toStringUtf8(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int hashCode(byte[] bArr) {
        return hashCode(bArr, 0, bArr.length);
    }

    static int hashCode(byte[] bArr, int i, int i2) {
        int partialHash = partialHash(i2, bArr, i, i2);
        if (partialHash == 0) {
            return 1;
        }
        return partialHash;
    }

    static int partialHash(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static Object mergeMessage(Object obj, Object obj2) {
        return ((MessageLite) obj).toBuilder().mergeFrom((MessageLite) obj2).buildPartial();
    }

    public static class ListAdapter extends AbstractList {
        private final Converter converter;
        private final List fromList;

        public interface Converter {
            Object convert(Object obj);
        }

        public ListAdapter(List list, Converter converter2) {
            this.fromList = list;
            this.converter = converter2;
        }

        public Object get(int i) {
            return this.converter.convert(this.fromList.get(i));
        }

        public int size() {
            return this.fromList.size();
        }
    }
}
