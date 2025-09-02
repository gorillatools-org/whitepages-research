package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

abstract class SchemaUtil {
    private static final Class GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
    private static final UnknownFieldSchema UNKNOWN_FIELD_SET_FULL_SCHEMA = getUnknownFieldSetSchema();
    private static final UnknownFieldSchema UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();

    public static void requireGeneratedMessage(Class cls) {
        Class cls2;
        if (!GeneratedMessageLite.class.isAssignableFrom(cls) && (cls2 = GENERATED_MESSAGE_CLASS) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        }
    }

    public static void writeDoubleList(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeDoubleList(i, list, z);
        }
    }

    public static void writeFloatList(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeFloatList(i, list, z);
        }
    }

    public static void writeInt64List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeInt64List(i, list, z);
        }
    }

    public static void writeUInt64List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeUInt64List(i, list, z);
        }
    }

    public static void writeSInt64List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeSInt64List(i, list, z);
        }
    }

    public static void writeFixed64List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeFixed64List(i, list, z);
        }
    }

    public static void writeSFixed64List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeSFixed64List(i, list, z);
        }
    }

    public static void writeInt32List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeInt32List(i, list, z);
        }
    }

    public static void writeUInt32List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeUInt32List(i, list, z);
        }
    }

    public static void writeSInt32List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeSInt32List(i, list, z);
        }
    }

    public static void writeFixed32List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeFixed32List(i, list, z);
        }
    }

    public static void writeSFixed32List(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeSFixed32List(i, list, z);
        }
    }

    public static void writeEnumList(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeEnumList(i, list, z);
        }
    }

    public static void writeBoolList(int i, List list, Writer writer, boolean z) {
        if (list != null && !list.isEmpty()) {
            writer.writeBoolList(i, list, z);
        }
    }

    public static void writeStringList(int i, List list, Writer writer) {
        if (list != null && !list.isEmpty()) {
            writer.writeStringList(i, list);
        }
    }

    public static void writeBytesList(int i, List list, Writer writer) {
        if (list != null && !list.isEmpty()) {
            writer.writeBytesList(i, list);
        }
    }

    public static void writeMessageList(int i, List list, Writer writer, Schema schema) {
        if (list != null && !list.isEmpty()) {
            writer.writeMessageList(i, list, schema);
        }
    }

    public static void writeGroupList(int i, List list, Writer writer, Schema schema) {
        if (list != null && !list.isEmpty()) {
            writer.writeGroupList(i, list, schema);
        }
    }

    static int computeSizeInt64ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + CodedOutputStream.computeInt64SizeNoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int computeSizeInt64List(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        int computeSizeInt64ListNoTag = computeSizeInt64ListNoTag(list);
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeInt64ListNoTag);
        }
        return computeSizeInt64ListNoTag + (list.size() * CodedOutputStream.computeTagSize(i));
    }

    static int computeSizeUInt64ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + CodedOutputStream.computeUInt64SizeNoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int computeSizeUInt64List(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeUInt64ListNoTag = computeSizeUInt64ListNoTag(list);
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeUInt64ListNoTag);
        }
        return computeSizeUInt64ListNoTag + (size * CodedOutputStream.computeTagSize(i));
    }

    static int computeSizeSInt64ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + CodedOutputStream.computeSInt64SizeNoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int computeSizeSInt64List(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeSInt64ListNoTag = computeSizeSInt64ListNoTag(list);
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeSInt64ListNoTag);
        }
        return computeSizeSInt64ListNoTag + (size * CodedOutputStream.computeTagSize(i));
    }

    static int computeSizeEnumListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeEnumSizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + CodedOutputStream.computeEnumSizeNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int computeSizeEnumList(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeEnumListNoTag = computeSizeEnumListNoTag(list);
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeEnumListNoTag);
        }
        return computeSizeEnumListNoTag + (size * CodedOutputStream.computeTagSize(i));
    }

    static int computeSizeInt32ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + CodedOutputStream.computeInt32SizeNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int computeSizeInt32List(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeInt32ListNoTag = computeSizeInt32ListNoTag(list);
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeInt32ListNoTag);
        }
        return computeSizeInt32ListNoTag + (size * CodedOutputStream.computeTagSize(i));
    }

    static int computeSizeUInt32ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + CodedOutputStream.computeUInt32SizeNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int computeSizeUInt32List(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeUInt32ListNoTag = computeSizeUInt32ListNoTag(list);
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeUInt32ListNoTag);
        }
        return computeSizeUInt32ListNoTag + (size * CodedOutputStream.computeTagSize(i));
    }

    static int computeSizeSInt32ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + CodedOutputStream.computeSInt32SizeNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int computeSizeSInt32List(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeSInt32ListNoTag = computeSizeSInt32ListNoTag(list);
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeSInt32ListNoTag);
        }
        return computeSizeSInt32ListNoTag + (size * CodedOutputStream.computeTagSize(i));
    }

    static int computeSizeFixed32ListNoTag(List list) {
        return list.size() * 4;
    }

    static int computeSizeFixed32List(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(size * 4);
        }
        return size * CodedOutputStream.computeFixed32Size(i, 0);
    }

    static int computeSizeFixed64ListNoTag(List list) {
        return list.size() * 8;
    }

    static int computeSizeFixed64List(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(size * 8);
        }
        return size * CodedOutputStream.computeFixed64Size(i, 0);
    }

    static int computeSizeBoolListNoTag(List list) {
        return list.size();
    }

    static int computeSizeBoolList(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (z) {
            return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return size * CodedOutputStream.computeBoolSize(i, true);
    }

    static int computeSizeStringList(int i, List list) {
        int computeStringSizeNoTag;
        int computeStringSizeNoTag2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(i) * size;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i2 < size) {
                Object raw = lazyStringList.getRaw(i2);
                if (raw instanceof ByteString) {
                    computeStringSizeNoTag2 = CodedOutputStream.computeBytesSizeNoTag((ByteString) raw);
                } else {
                    computeStringSizeNoTag2 = CodedOutputStream.computeStringSizeNoTag((String) raw);
                }
                computeTagSize += computeStringSizeNoTag2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof ByteString) {
                    computeStringSizeNoTag = CodedOutputStream.computeBytesSizeNoTag((ByteString) obj);
                } else {
                    computeStringSizeNoTag = CodedOutputStream.computeStringSizeNoTag((String) obj);
                }
                computeTagSize += computeStringSizeNoTag;
                i2++;
            }
        }
        return computeTagSize;
    }

    static int computeSizeMessage(int i, Object obj, Schema schema) {
        return CodedOutputStream.computeMessageSize(i, (MessageLite) obj, schema);
    }

    static int computeSizeMessageList(int i, List list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            computeTagSize += CodedOutputStream.computeMessageSizeNoTag((MessageLite) list.get(i2), schema);
        }
        return computeTagSize;
    }

    static int computeSizeByteStringList(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = size * CodedOutputStream.computeTagSize(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            computeTagSize += CodedOutputStream.computeBytesSizeNoTag((ByteString) list.get(i2));
        }
        return computeTagSize;
    }

    static int computeSizeGroupList(int i, List list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += CodedOutputStream.computeGroupSize(i, (MessageLite) list.get(i3), schema);
        }
        return i2;
    }

    public static UnknownFieldSchema unknownFieldSetFullSchema() {
        return UNKNOWN_FIELD_SET_FULL_SCHEMA;
    }

    public static UnknownFieldSchema unknownFieldSetLiteSchema() {
        return UNKNOWN_FIELD_SET_LITE_SCHEMA;
    }

    private static UnknownFieldSchema getUnknownFieldSetSchema() {
        try {
            Class unknownFieldSetSchemaClass = getUnknownFieldSetSchemaClass();
            if (unknownFieldSetSchemaClass == null) {
                return null;
            }
            return (UnknownFieldSchema) unknownFieldSetSchemaClass.getConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class getGeneratedMessageClass() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class getUnknownFieldSetSchemaClass() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean safeEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static void mergeMap(MapFieldSchema mapFieldSchema, Object obj, Object obj2, long j) {
        UnsafeUtil.putObject(obj, j, mapFieldSchema.mergeFrom(UnsafeUtil.getObject(obj, j), UnsafeUtil.getObject(obj2, j)));
    }

    static void mergeExtensions(ExtensionSchema extensionSchema, Object obj, Object obj2) {
        FieldSet extensions = extensionSchema.getExtensions(obj2);
        if (!extensions.isEmpty()) {
            extensionSchema.getMutableExtensions(obj).mergeFrom(extensions);
        }
    }

    static void mergeUnknownFields(UnknownFieldSchema unknownFieldSchema, Object obj, Object obj2) {
        unknownFieldSchema.setToMessage(obj, unknownFieldSchema.merge(unknownFieldSchema.getFromMessage(obj), unknownFieldSchema.getFromMessage(obj2)));
    }

    static Object filterUnknownEnumList(Object obj, int i, List list, Internal.EnumLiteMap enumLiteMap, Object obj2, UnknownFieldSchema unknownFieldSchema) {
        if (enumLiteMap == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Integer num = (Integer) list.get(i3);
                int intValue = num.intValue();
                if (enumLiteMap.findValueByNumber(intValue) != null) {
                    if (i3 != i2) {
                        list.set(i2, num);
                    }
                    i2++;
                } else {
                    obj2 = storeUnknownEnum(obj, i, intValue, obj2, unknownFieldSchema);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (enumLiteMap.findValueByNumber(intValue2) == null) {
                    obj2 = storeUnknownEnum(obj, i, intValue2, obj2, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object filterUnknownEnumList(Object obj, int i, List list, Internal.EnumVerifier enumVerifier, Object obj2, UnknownFieldSchema unknownFieldSchema) {
        if (enumVerifier == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Integer num = (Integer) list.get(i3);
                int intValue = num.intValue();
                if (enumVerifier.isInRange(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, num);
                    }
                    i2++;
                } else {
                    obj2 = storeUnknownEnum(obj, i, intValue, obj2, unknownFieldSchema);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!enumVerifier.isInRange(intValue2)) {
                    obj2 = storeUnknownEnum(obj, i, intValue2, obj2, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object storeUnknownEnum(Object obj, int i, int i2, Object obj2, UnknownFieldSchema unknownFieldSchema) {
        if (obj2 == null) {
            obj2 = unknownFieldSchema.getBuilderFromMessage(obj);
        }
        unknownFieldSchema.addVarint(obj2, i, (long) i2);
        return obj2;
    }
}
