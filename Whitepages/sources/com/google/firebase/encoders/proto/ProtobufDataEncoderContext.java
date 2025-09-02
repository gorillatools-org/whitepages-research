package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    private static final ObjectEncoder<Map.Entry<Object, Object>> DEFAULT_MAP_ENCODER = new ProtobufDataEncoderContext$$ExternalSyntheticLambda0();
    private static final FieldDescriptor MAP_KEY_DESC = FieldDescriptor.builder("key").withProperty(AtProtobuf.builder().tag(1).build()).build();
    private static final FieldDescriptor MAP_VALUE_DESC = FieldDescriptor.builder("value").withProperty(AtProtobuf.builder().tag(2).build()).build();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final ObjectEncoder<Object> fallbackEncoder;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private OutputStream output;
    private final ProtobufValueEncoderContext valueEncoderContext = new ProtobufValueEncoderContext(this);
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(Map.Entry entry, ObjectEncoderContext objectEncoderContext) throws IOException {
        objectEncoderContext.add(MAP_KEY_DESC, entry.getKey());
        objectEncoderContext.add(MAP_VALUE_DESC, entry.getValue());
    }

    ProtobufDataEncoderContext(OutputStream outputStream, Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder) {
        this.output = outputStream;
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
    }

    public ObjectEncoderContext add(String str, Object obj) throws IOException {
        return add(FieldDescriptor.of(str), obj);
    }

    public ObjectEncoderContext add(String str, double d) throws IOException {
        return add(FieldDescriptor.of(str), d);
    }

    public ObjectEncoderContext add(String str, int i) throws IOException {
        return add(FieldDescriptor.of(str), i);
    }

    public ObjectEncoderContext add(String str, long j) throws IOException {
        return add(FieldDescriptor.of(str), j);
    }

    public ObjectEncoderContext add(String str, boolean z) throws IOException {
        return add(FieldDescriptor.of(str), z);
    }

    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj) throws IOException {
        return add(fieldDescriptor, obj, true);
    }

    /* access modifiers changed from: package-private */
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj, boolean z) throws IOException {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z && charSequence.length() == 0) {
                return this;
            }
            writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(UTF_8);
            writeVarInt32(bytes.length);
            this.output.write(bytes);
            return this;
        } else if (obj instanceof Collection) {
            for (Object add : (Collection) obj) {
                add(fieldDescriptor, add, false);
            }
            return this;
        } else if (obj instanceof Map) {
            for (Map.Entry doEncode : ((Map) obj).entrySet()) {
                doEncode(DEFAULT_MAP_ENCODER, fieldDescriptor, doEncode, false);
            }
            return this;
        } else if (obj instanceof Double) {
            return add(fieldDescriptor, ((Double) obj).doubleValue(), z);
        } else {
            if (obj instanceof Float) {
                return add(fieldDescriptor, ((Float) obj).floatValue(), z);
            }
            if (obj instanceof Number) {
                return add(fieldDescriptor, ((Number) obj).longValue(), z);
            }
            if (obj instanceof Boolean) {
                return add(fieldDescriptor, ((Boolean) obj).booleanValue(), z);
            }
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (z && bArr.length == 0) {
                    return this;
                }
                writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
                writeVarInt32(bArr.length);
                this.output.write(bArr);
                return this;
            }
            ObjectEncoder objectEncoder = this.objectEncoders.get(obj.getClass());
            if (objectEncoder != null) {
                return doEncode(objectEncoder, fieldDescriptor, obj, z);
            }
            ValueEncoder valueEncoder = this.valueEncoders.get(obj.getClass());
            if (valueEncoder != null) {
                return doEncode(valueEncoder, fieldDescriptor, obj, z);
            }
            if (obj instanceof ProtoEnum) {
                return add(fieldDescriptor, ((ProtoEnum) obj).getNumber());
            }
            if (obj instanceof Enum) {
                return add(fieldDescriptor, ((Enum) obj).ordinal());
            }
            return doEncode(this.fallbackEncoder, fieldDescriptor, obj, z);
        }
    }

    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d) throws IOException {
        return add(fieldDescriptor, d, true);
    }

    /* access modifiers changed from: package-private */
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d, boolean z) throws IOException {
        if (z && d == 0.0d) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 1);
        this.output.write(allocateBuffer(8).putDouble(d).array());
        return this;
    }

    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f) throws IOException {
        return add(fieldDescriptor, f, true);
    }

    /* access modifiers changed from: package-private */
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f, boolean z) throws IOException {
        if (z && f == 0.0f) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 5);
        this.output.write(allocateBuffer(4).putFloat(f).array());
        return this;
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, int i) throws IOException {
        return add(fieldDescriptor, i, true);
    }

    /* renamed from: com.google.firebase.encoders.proto.ProtobufDataEncoderContext$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.firebase.encoders.proto.Protobuf$IntEncoding[] r0 = com.google.firebase.encoders.proto.Protobuf.IntEncoding.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding = r0
                com.google.firebase.encoders.proto.Protobuf$IntEncoding r1 = com.google.firebase.encoders.proto.Protobuf.IntEncoding.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.encoders.proto.Protobuf$IntEncoding r1 = com.google.firebase.encoders.proto.Protobuf.IntEncoding.SIGNED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.encoders.proto.Protobuf$IntEncoding r1 = com.google.firebase.encoders.proto.Protobuf.IntEncoding.FIXED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.encoders.proto.ProtobufDataEncoderContext.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, int i, boolean z) throws IOException {
        if (z && i == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(fieldDescriptor);
        int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf.intEncoding().ordinal()];
        if (i2 == 1) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32(i);
        } else if (i2 == 2) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32((i << 1) ^ (i >> 31));
        } else if (i2 == 3) {
            writeVarInt32((protobuf.tag() << 3) | 5);
            this.output.write(allocateBuffer(4).putInt(i).array());
        }
        return this;
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, long j) throws IOException {
        return add(fieldDescriptor, j, true);
    }

    /* access modifiers changed from: package-private */
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, long j, boolean z) throws IOException {
        if (z && j == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(fieldDescriptor);
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf.intEncoding().ordinal()];
        if (i == 1) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt64(j);
        } else if (i == 2) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt64((j >> 63) ^ (j << 1));
        } else if (i == 3) {
            writeVarInt32((protobuf.tag() << 3) | 1);
            this.output.write(allocateBuffer(8).putLong(j).array());
        }
        return this;
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, boolean z) throws IOException {
        return add(fieldDescriptor, z, true);
    }

    /* access modifiers changed from: package-private */
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, boolean z, boolean z2) throws IOException {
        return add(fieldDescriptor, z ? 1 : 0, z2);
    }

    public ObjectEncoderContext inline(Object obj) throws IOException {
        return encode(obj);
    }

    /* access modifiers changed from: package-private */
    public ProtobufDataEncoderContext encode(Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        ObjectEncoder objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        throw new EncodingException("No encoder for " + obj.getClass());
    }

    public ObjectEncoderContext nested(String str) throws IOException {
        return nested(FieldDescriptor.of(str));
    }

    public ObjectEncoderContext nested(FieldDescriptor fieldDescriptor) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    private <T> ProtobufDataEncoderContext doEncode(ObjectEncoder<T> objectEncoder, FieldDescriptor fieldDescriptor, T t, boolean z) throws IOException {
        long determineSize = determineSize(objectEncoder, t);
        if (z && determineSize == 0) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
        writeVarInt64(determineSize);
        objectEncoder.encode(t, this);
        return this;
    }

    private <T> long determineSize(ObjectEncoder<T> objectEncoder, T t) throws IOException {
        OutputStream outputStream;
        LengthCountingOutputStream lengthCountingOutputStream = new LengthCountingOutputStream();
        try {
            outputStream = this.output;
            this.output = lengthCountingOutputStream;
            objectEncoder.encode(t, this);
            this.output = outputStream;
            long length = lengthCountingOutputStream.getLength();
            lengthCountingOutputStream.close();
            return length;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private <T> ProtobufDataEncoderContext doEncode(ValueEncoder<T> valueEncoder, FieldDescriptor fieldDescriptor, T t, boolean z) throws IOException {
        this.valueEncoderContext.resetContext(fieldDescriptor, z);
        valueEncoder.encode(t, this.valueEncoderContext);
        return this;
    }

    private static ByteBuffer allocateBuffer(int i) {
        return ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static int getTag(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf.tag();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private static Protobuf getProtobuf(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private void writeVarInt32(int i) throws IOException {
        while (((long) (i & -128)) != 0) {
            this.output.write((i & 127) | 128);
            i >>>= 7;
        }
        this.output.write(i & 127);
    }

    private void writeVarInt64(long j) throws IOException {
        while ((-128 & j) != 0) {
            this.output.write((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.output.write(((int) j) & 127);
    }
}
