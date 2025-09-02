package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import com.google.protobuf.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

final class CodedOutputStreamWriter implements Writer {
    private final CodedOutputStream output;

    public static CodedOutputStreamWriter forCodedOutput(CodedOutputStream codedOutputStream) {
        CodedOutputStreamWriter codedOutputStreamWriter = codedOutputStream.wrapper;
        if (codedOutputStreamWriter != null) {
            return codedOutputStreamWriter;
        }
        return new CodedOutputStreamWriter(codedOutputStream);
    }

    private CodedOutputStreamWriter(CodedOutputStream codedOutputStream) {
        CodedOutputStream codedOutputStream2 = (CodedOutputStream) Internal.checkNotNull(codedOutputStream, "output");
        this.output = codedOutputStream2;
        codedOutputStream2.wrapper = this;
    }

    public Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.ASCENDING;
    }

    public void writeSFixed32(int i, int i2) {
        this.output.writeSFixed32(i, i2);
    }

    public void writeInt64(int i, long j) {
        this.output.writeInt64(i, j);
    }

    public void writeSFixed64(int i, long j) {
        this.output.writeSFixed64(i, j);
    }

    public void writeFloat(int i, float f) {
        this.output.writeFloat(i, f);
    }

    public void writeDouble(int i, double d) {
        this.output.writeDouble(i, d);
    }

    public void writeEnum(int i, int i2) {
        this.output.writeEnum(i, i2);
    }

    public void writeUInt64(int i, long j) {
        this.output.writeUInt64(i, j);
    }

    public void writeInt32(int i, int i2) {
        this.output.writeInt32(i, i2);
    }

    public void writeFixed64(int i, long j) {
        this.output.writeFixed64(i, j);
    }

    public void writeFixed32(int i, int i2) {
        this.output.writeFixed32(i, i2);
    }

    public void writeBool(int i, boolean z) {
        this.output.writeBool(i, z);
    }

    public void writeString(int i, String str) {
        this.output.writeString(i, str);
    }

    public void writeBytes(int i, ByteString byteString) {
        this.output.writeBytes(i, byteString);
    }

    public void writeUInt32(int i, int i2) {
        this.output.writeUInt32(i, i2);
    }

    public void writeSInt32(int i, int i2) {
        this.output.writeSInt32(i, i2);
    }

    public void writeSInt64(int i, long j) {
        this.output.writeSInt64(i, j);
    }

    public void writeMessage(int i, Object obj, Schema schema) {
        this.output.writeMessage(i, (MessageLite) obj, schema);
    }

    public void writeGroup(int i, Object obj, Schema schema) {
        this.output.writeGroup(i, (MessageLite) obj, schema);
    }

    public void writeStartGroup(int i) {
        this.output.writeTag(i, 3);
    }

    public void writeEndGroup(int i) {
        this.output.writeTag(i, 4);
    }

    public final void writeMessageSetItem(int i, Object obj) {
        if (obj instanceof ByteString) {
            this.output.writeRawMessageSetExtension(i, (ByteString) obj);
        } else {
            this.output.writeMessageSetExtension(i, (MessageLite) obj);
        }
    }

    public void writeInt32List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeInt32SizeNoTag(((Integer) list.get(i4)).intValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeInt32NoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeInt32(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public void writeFixed32List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFixed32SizeNoTag(((Integer) list.get(i4)).intValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeFixed32NoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeFixed32(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public void writeInt64List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeInt64SizeNoTag(((Long) list.get(i4)).longValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeInt64NoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeInt64(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public void writeUInt64List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeUInt64SizeNoTag(((Long) list.get(i4)).longValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeUInt64NoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeUInt64(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public void writeFixed64List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFixed64SizeNoTag(((Long) list.get(i4)).longValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeFixed64NoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeFixed64(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public void writeFloatList(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFloatSizeNoTag(((Float) list.get(i4)).floatValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeFloatNoTag(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeFloat(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public void writeDoubleList(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeDoubleSizeNoTag(((Double) list.get(i4)).doubleValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeDoubleNoTag(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeDouble(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public void writeEnumList(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeEnumSizeNoTag(((Integer) list.get(i4)).intValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeEnumNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeEnum(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public void writeBoolList(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeBoolSizeNoTag(((Boolean) list.get(i4)).booleanValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeBoolNoTag(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeBool(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public void writeStringList(int i, List list) {
        int i2 = 0;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i2 < list.size()) {
                writeLazyString(i, lazyStringList.getRaw(i2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeString(i, (String) list.get(i2));
            i2++;
        }
    }

    private void writeLazyString(int i, Object obj) {
        if (obj instanceof String) {
            this.output.writeString(i, (String) obj);
        } else {
            this.output.writeBytes(i, (ByteString) obj);
        }
    }

    public void writeBytesList(int i, List list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.output.writeBytes(i, (ByteString) list.get(i2));
        }
    }

    public void writeUInt32List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeUInt32SizeNoTag(((Integer) list.get(i4)).intValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeUInt32NoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeUInt32(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public void writeSFixed32List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSFixed32SizeNoTag(((Integer) list.get(i4)).intValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeSFixed32NoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeSFixed32(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public void writeSFixed64List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSFixed64SizeNoTag(((Long) list.get(i4)).longValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeSFixed64NoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeSFixed64(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public void writeSInt32List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSInt32SizeNoTag(((Integer) list.get(i4)).intValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeSInt32NoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeSInt32(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public void writeSInt64List(int i, List list, boolean z) {
        int i2 = 0;
        if (z) {
            this.output.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSInt64SizeNoTag(((Long) list.get(i4)).longValue());
            }
            this.output.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.output.writeSInt64NoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.output.writeSInt64(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public void writeMessageList(int i, List list, Schema schema) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            writeMessage(i, list.get(i2), schema);
        }
    }

    public void writeGroupList(int i, List list, Schema schema) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            writeGroup(i, list.get(i2), schema);
        }
    }

    public void writeMap(int i, MapEntryLite.Metadata metadata, Map map) {
        if (this.output.isSerializationDeterministic()) {
            writeDeterministicMap(i, metadata, map);
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            this.output.writeTag(i, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, entry.getKey(), entry.getValue()));
            MapEntryLite.writeTo(this.output, metadata, entry.getKey(), entry.getValue());
        }
    }

    /* renamed from: com.google.protobuf.CodedOutputStreamWriter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.WireFormat$FieldType[] r0 = com.google.protobuf.WireFormat.FieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.BOOL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED32     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT32     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED32     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT32     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT32     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED64     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT64     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED64     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT64     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT64     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.STRING     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedOutputStreamWriter.AnonymousClass1.<clinit>():void");
        }
    }

    private void writeDeterministicMap(int i, MapEntryLite.Metadata metadata, Map map) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[metadata.keyType.ordinal()]) {
            case 1:
                Object obj = map.get(Boolean.FALSE);
                if (obj != null) {
                    writeDeterministicBooleanMapEntry(i, false, obj, metadata);
                }
                Object obj2 = map.get(Boolean.TRUE);
                if (obj2 != null) {
                    writeDeterministicBooleanMapEntry(i, true, obj2, metadata);
                    return;
                }
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                writeDeterministicIntegerMap(i, metadata, map);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                writeDeterministicLongMap(i, metadata, map);
                return;
            case 12:
                writeDeterministicStringMap(i, metadata, map);
                return;
            default:
                throw new IllegalArgumentException("does not support key type: " + metadata.keyType);
        }
    }

    private void writeDeterministicBooleanMapEntry(int i, boolean z, Object obj, MapEntryLite.Metadata metadata) {
        this.output.writeTag(i, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Boolean.valueOf(z), obj));
        MapEntryLite.writeTo(this.output, metadata, Boolean.valueOf(z), obj);
    }

    private void writeDeterministicIntegerMap(int i, MapEntryLite.Metadata metadata, Map map) {
        int size = map.size();
        int[] iArr = new int[size];
        int i2 = 0;
        for (Integer intValue : map.keySet()) {
            iArr[i2] = intValue.intValue();
            i2++;
        }
        Arrays.sort(iArr);
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = iArr[i3];
            Object obj = map.get(Integer.valueOf(i4));
            this.output.writeTag(i, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Integer.valueOf(i4), obj));
            MapEntryLite.writeTo(this.output, metadata, Integer.valueOf(i4), obj);
        }
    }

    private void writeDeterministicLongMap(int i, MapEntryLite.Metadata metadata, Map map) {
        int size = map.size();
        long[] jArr = new long[size];
        int i2 = 0;
        for (Long longValue : map.keySet()) {
            jArr[i2] = longValue.longValue();
            i2++;
        }
        Arrays.sort(jArr);
        for (int i3 = 0; i3 < size; i3++) {
            long j = jArr[i3];
            Object obj = map.get(Long.valueOf(j));
            this.output.writeTag(i, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Long.valueOf(j), obj));
            MapEntryLite.writeTo(this.output, metadata, Long.valueOf(j), obj);
        }
    }

    private void writeDeterministicStringMap(int i, MapEntryLite.Metadata metadata, Map map) {
        int size = map.size();
        String[] strArr = new String[size];
        int i2 = 0;
        for (String str : map.keySet()) {
            strArr[i2] = str;
            i2++;
        }
        Arrays.sort(strArr);
        for (int i3 = 0; i3 < size; i3++) {
            String str2 = strArr[i3];
            Object obj = map.get(str2);
            this.output.writeTag(i, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, str2, obj));
            MapEntryLite.writeTo(this.output, metadata, str2, obj);
        }
    }
}
