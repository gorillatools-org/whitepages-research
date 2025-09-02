package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GeneratedMessageLite extends AbstractMessageLite {
    private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
    private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
    static final int UNINITIALIZED_HASH_CODE = 0;
    static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
    private static Map<Object, GeneratedMessageLite> defaultInstanceMap = new ConcurrentHashMap();
    private int memoizedSerializedSize = -1;
    protected UnknownFieldSetLite unknownFields = UnknownFieldSetLite.getDefaultInstance();

    public enum MethodToInvoke {
        GET_MEMOIZED_IS_INITIALIZED,
        SET_MEMOIZED_IS_INITIALIZED,
        BUILD_MESSAGE_INFO,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER
    }

    /* access modifiers changed from: protected */
    public abstract Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public boolean isMutable() {
        return (this.memoizedSerializedSize & Integer.MIN_VALUE) != 0;
    }

    /* access modifiers changed from: package-private */
    public void markImmutable() {
        this.memoizedSerializedSize &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public int getMemoizedHashCode() {
        return this.memoizedHashCode;
    }

    /* access modifiers changed from: package-private */
    public void setMemoizedHashCode(int i) {
        this.memoizedHashCode = i;
    }

    /* access modifiers changed from: package-private */
    public void clearMemoizedHashCode() {
        this.memoizedHashCode = 0;
    }

    /* access modifiers changed from: package-private */
    public boolean hashCodeIsNotMemoized() {
        return getMemoizedHashCode() == 0;
    }

    public final Parser getParserForType() {
        return (Parser) dynamicMethod(MethodToInvoke.GET_PARSER);
    }

    public final GeneratedMessageLite getDefaultInstanceForType() {
        return (GeneratedMessageLite) dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE);
    }

    public final Builder newBuilderForType() {
        return (Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    /* access modifiers changed from: package-private */
    public GeneratedMessageLite newMutableInstance() {
        return (GeneratedMessageLite) dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
    }

    public String toString() {
        return MessageLiteToString.toString(this, super.toString());
    }

    public int hashCode() {
        if (isMutable()) {
            return computeHashCode();
        }
        if (hashCodeIsNotMemoized()) {
            setMemoizedHashCode(computeHashCode());
        }
        return getMemoizedHashCode();
    }

    /* access modifiers changed from: package-private */
    public int computeHashCode() {
        return Protobuf.getInstance().schemaFor((Object) this).hashCode(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Protobuf.getInstance().schemaFor((Object) this).equals(this, (GeneratedMessageLite) obj);
        }
        return false;
    }

    private void ensureUnknownFieldsInitialized() {
        if (this.unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
            this.unknownFields = UnknownFieldSetLite.newInstance();
        }
    }

    /* access modifiers changed from: protected */
    public boolean parseUnknownField(int i, CodedInputStream codedInputStream) throws IOException {
        if (WireFormat.getTagWireType(i) == 4) {
            return false;
        }
        ensureUnknownFieldsInitialized();
        return this.unknownFields.mergeFieldFrom(i, codedInputStream);
    }

    /* access modifiers changed from: protected */
    public void mergeVarintField(int i, int i2) {
        ensureUnknownFieldsInitialized();
        this.unknownFields.mergeVarintField(i, i2);
    }

    /* access modifiers changed from: protected */
    public void mergeLengthDelimitedField(int i, ByteString byteString) {
        ensureUnknownFieldsInitialized();
        this.unknownFields.mergeLengthDelimitedField(i, byteString);
    }

    /* access modifiers changed from: protected */
    public void makeImmutable() {
        Protobuf.getInstance().schemaFor((Object) this).makeImmutable(this);
        markImmutable();
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends GeneratedMessageLite, BuilderType extends Builder> BuilderType createBuilder() {
        return (Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends GeneratedMessageLite, BuilderType extends Builder> BuilderType createBuilder(MessageType messagetype) {
        return createBuilder().mergeFrom(messagetype);
    }

    public final boolean isInitialized() {
        return isInitialized(this, true);
    }

    public final Builder toBuilder() {
        return ((Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER)).mergeFrom(this);
    }

    /* access modifiers changed from: protected */
    public Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj) {
        return dynamicMethod(methodToInvoke, obj, (Object) null);
    }

    /* access modifiers changed from: protected */
    public Object dynamicMethod(MethodToInvoke methodToInvoke) {
        return dynamicMethod(methodToInvoke, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public void clearMemoizedSerializedSize() {
        setMemoizedSerializedSize(Integer.MAX_VALUE);
    }

    /* access modifiers changed from: package-private */
    public int getMemoizedSerializedSize() {
        return this.memoizedSerializedSize & Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public void setMemoizedSerializedSize(int i) {
        if (i >= 0) {
            this.memoizedSerializedSize = (i & Integer.MAX_VALUE) | (this.memoizedSerializedSize & Integer.MIN_VALUE);
            return;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        Protobuf.getInstance().schemaFor((Object) this).writeTo(this, CodedOutputStreamWriter.forCodedOutput(codedOutputStream));
    }

    /* access modifiers changed from: package-private */
    public int getSerializedSize(Schema schema) {
        if (isMutable()) {
            int computeSerializedSize = computeSerializedSize(schema);
            if (computeSerializedSize >= 0) {
                return computeSerializedSize;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + computeSerializedSize);
        } else if (getMemoizedSerializedSize() != Integer.MAX_VALUE) {
            return getMemoizedSerializedSize();
        } else {
            int computeSerializedSize2 = computeSerializedSize(schema);
            setMemoizedSerializedSize(computeSerializedSize2);
            return computeSerializedSize2;
        }
    }

    public int getSerializedSize() {
        return getSerializedSize((Schema) null);
    }

    private int computeSerializedSize(Schema schema) {
        if (schema == null) {
            return Protobuf.getInstance().schemaFor((Object) this).getSerializedSize(this);
        }
        return schema.getSerializedSize(this);
    }

    /* access modifiers changed from: package-private */
    public Object buildMessageInfo() throws Exception {
        return dynamicMethod(MethodToInvoke.BUILD_MESSAGE_INFO);
    }

    static <T extends GeneratedMessageLite> T getDefaultInstance(Class<T> cls) {
        T t = (GeneratedMessageLite) defaultInstanceMap.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (GeneratedMessageLite) defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = ((GeneratedMessageLite) UnsafeUtil.allocateInstance(cls)).getDefaultInstanceForType();
            if (t != null) {
                defaultInstanceMap.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends GeneratedMessageLite> void registerDefaultInstance(Class<T> cls, T t) {
        t.markImmutable();
        defaultInstanceMap.put(cls, t);
    }

    protected static Object newMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        return new RawMessageInfo(messageLite, str, objArr);
    }

    /* access modifiers changed from: protected */
    public final void mergeUnknownFields(UnknownFieldSetLite unknownFieldSetLite) {
        this.unknownFields = UnknownFieldSetLite.mutableCopyOf(this.unknownFields, unknownFieldSetLite);
    }

    public static abstract class Builder extends AbstractMessageLite.Builder {
        private final GeneratedMessageLite defaultInstance;
        protected GeneratedMessageLite instance;

        protected Builder(GeneratedMessageLite generatedMessageLite) {
            this.defaultInstance = generatedMessageLite;
            if (!generatedMessageLite.isMutable()) {
                this.instance = newMutableInstance();
                return;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        private GeneratedMessageLite newMutableInstance() {
            return this.defaultInstance.newMutableInstance();
        }

        /* access modifiers changed from: protected */
        public final void copyOnWrite() {
            if (!this.instance.isMutable()) {
                copyOnWriteInternal();
            }
        }

        /* access modifiers changed from: protected */
        public void copyOnWriteInternal() {
            GeneratedMessageLite newMutableInstance = newMutableInstance();
            mergeFromInstance(newMutableInstance, this.instance);
            this.instance = newMutableInstance;
        }

        public final boolean isInitialized() {
            return GeneratedMessageLite.isInitialized(this.instance, false);
        }

        public final Builder clear() {
            if (!this.defaultInstance.isMutable()) {
                this.instance = newMutableInstance();
                return this;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        public Builder clone() {
            Builder newBuilderForType = getDefaultInstanceForType().newBuilderForType();
            newBuilderForType.instance = buildPartial();
            return newBuilderForType;
        }

        public GeneratedMessageLite buildPartial() {
            if (!this.instance.isMutable()) {
                return this.instance;
            }
            this.instance.makeImmutable();
            return this.instance;
        }

        public final GeneratedMessageLite build() {
            GeneratedMessageLite buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(buildPartial);
        }

        /* access modifiers changed from: protected */
        public Builder internalMergeFrom(GeneratedMessageLite generatedMessageLite) {
            return mergeFrom(generatedMessageLite);
        }

        public Builder mergeFrom(GeneratedMessageLite generatedMessageLite) {
            if (getDefaultInstanceForType().equals(generatedMessageLite)) {
                return this;
            }
            copyOnWrite();
            mergeFromInstance(this.instance, generatedMessageLite);
            return this;
        }

        private static void mergeFromInstance(Object obj, Object obj2) {
            Protobuf.getInstance().schemaFor(obj).mergeFrom(obj, obj2);
        }

        public GeneratedMessageLite getDefaultInstanceForType() {
            return this.defaultInstance;
        }

        public Builder mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            copyOnWrite();
            try {
                Protobuf.getInstance().schemaFor((Object) this.instance).mergeFrom(this.instance, bArr, i, i + i2, new ArrayDecoders.Registers(extensionRegistryLite));
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IndexOutOfBoundsException unused) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            }
        }

        public Builder mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            return mergeFrom(bArr, i, i2, ExtensionRegistryLite.getEmptyRegistry());
        }

        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            copyOnWrite();
            try {
                Protobuf.getInstance().schemaFor((Object) this.instance).mergeFrom(this.instance, CodedInputStreamReader.forCodedInput(codedInputStream), extensionRegistryLite);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension newSingularGeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, Internal.EnumLiteMap enumLiteMap, int i, WireFormat.FieldType fieldType, Class cls) {
        return new GeneratedExtension(containingtype, type, messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, false, false), cls);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension newRepeatedGeneratedExtension(ContainingType containingtype, MessageLite messageLite, Internal.EnumLiteMap enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, Class cls) {
        return new GeneratedExtension(containingtype, Collections.emptyList(), messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, true, z), cls);
    }

    static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite {
        final Internal.EnumLiteMap enumTypeMap;
        final boolean isPacked;
        final boolean isRepeated;
        final int number;
        final WireFormat.FieldType type;

        ExtensionDescriptor(Internal.EnumLiteMap enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, boolean z2) {
            this.enumTypeMap = enumLiteMap;
            this.number = i;
            this.type = fieldType;
            this.isRepeated = z;
            this.isPacked = z2;
        }

        public int getNumber() {
            return this.number;
        }

        public WireFormat.FieldType getLiteType() {
            return this.type;
        }

        public WireFormat.JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        public boolean isRepeated() {
            return this.isRepeated;
        }

        public boolean isPacked() {
            return this.isPacked;
        }

        public Internal.EnumLiteMap getEnumType() {
            return this.enumTypeMap;
        }

        public MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Builder) builder).mergeFrom((GeneratedMessageLite) messageLite);
        }

        public int compareTo(ExtensionDescriptor extensionDescriptor) {
            return this.number - extensionDescriptor.number;
        }
    }

    static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    static Object invokeOrDie(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    public static class GeneratedExtension extends ExtensionLite {
        final MessageLite containingTypeDefaultInstance;
        final Object defaultValue;
        final ExtensionDescriptor descriptor;
        final MessageLite messageDefaultInstance;

        GeneratedExtension(MessageLite messageLite, Object obj, MessageLite messageLite2, ExtensionDescriptor extensionDescriptor, Class cls) {
            if (messageLite == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (extensionDescriptor.getLiteType() == WireFormat.FieldType.MESSAGE && messageLite2 == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.containingTypeDefaultInstance = messageLite;
                this.defaultValue = obj;
                this.messageDefaultInstance = messageLite2;
                this.descriptor = extensionDescriptor;
            }
        }

        public int getNumber() {
            return this.descriptor.getNumber();
        }

        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        public WireFormat.FieldType getLiteType() {
            return this.descriptor.getLiteType();
        }

        public boolean isRepeated() {
            return this.descriptor.isRepeated;
        }
    }

    /* access modifiers changed from: private */
    public static GeneratedExtension checkIsLite(ExtensionLite extensionLite) {
        if (extensionLite.isLite()) {
            return (GeneratedExtension) extensionLite;
        }
        throw new IllegalArgumentException("Expected a lite extension.");
    }

    protected static final <T extends GeneratedMessageLite> boolean isInitialized(T t, boolean z) {
        byte byteValue = ((Byte) t.dynamicMethod(MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean isInitialized = Protobuf.getInstance().schemaFor((Object) t).isInitialized(t);
        if (z) {
            t.dynamicMethod(MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED, isInitialized ? t : null);
        }
        return isInitialized;
    }

    protected static Internal.IntList emptyIntList() {
        return IntArrayList.emptyList();
    }

    protected static Internal.IntList mutableCopy(Internal.IntList intList) {
        int size = intList.size();
        return intList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static Internal.LongList emptyLongList() {
        return LongArrayList.emptyList();
    }

    protected static Internal.LongList mutableCopy(Internal.LongList longList) {
        int size = longList.size();
        return longList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static Internal.FloatList emptyFloatList() {
        return FloatArrayList.emptyList();
    }

    protected static Internal.FloatList mutableCopy(Internal.FloatList floatList) {
        int size = floatList.size();
        return floatList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static Internal.DoubleList emptyDoubleList() {
        return DoubleArrayList.emptyList();
    }

    protected static Internal.DoubleList mutableCopy(Internal.DoubleList doubleList) {
        int size = doubleList.size();
        return doubleList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static Internal.BooleanList emptyBooleanList() {
        return BooleanArrayList.emptyList();
    }

    protected static Internal.BooleanList mutableCopy(Internal.BooleanList booleanList) {
        int size = booleanList.size();
        return booleanList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static <E> Internal.ProtobufList emptyProtobufList() {
        return ProtobufArrayList.emptyList();
    }

    protected static <E> Internal.ProtobufList mutableCopy(Internal.ProtobufList protobufList) {
        int size = protobufList.size();
        return protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static class DefaultInstanceBasedParser extends AbstractParser {
        private final GeneratedMessageLite defaultInstance;

        public DefaultInstanceBasedParser(GeneratedMessageLite generatedMessageLite) {
            this.defaultInstance = generatedMessageLite;
        }
    }

    static <T extends GeneratedMessageLite> T parsePartialFrom(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        T newMutableInstance = t.newMutableInstance();
        try {
            Schema schemaFor = Protobuf.getInstance().schemaFor((Object) newMutableInstance);
            schemaFor.mergeFrom(newMutableInstance, CodedInputStreamReader.forCodedInput(codedInputStream), extensionRegistryLite);
            schemaFor.makeImmutable(newMutableInstance);
            return newMutableInstance;
        } catch (InvalidProtocolBufferException e) {
            e = e;
            if (e.getThrownFromInputStream()) {
                e = new InvalidProtocolBufferException((IOException) e);
            }
            throw e.setUnfinishedMessage(newMutableInstance);
        } catch (UninitializedMessageException e2) {
            throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(newMutableInstance);
        } catch (IOException e3) {
            if (e3.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e3.getCause());
            }
            throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(newMutableInstance);
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e4.getCause());
            }
            throw e4;
        }
    }

    /* access modifiers changed from: private */
    public static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite generatedMessageLite, byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
        GeneratedMessageLite newMutableInstance = generatedMessageLite.newMutableInstance();
        try {
            Schema schemaFor = Protobuf.getInstance().schemaFor((Object) newMutableInstance);
            schemaFor.mergeFrom(newMutableInstance, bArr, i, i + i2, new ArrayDecoders.Registers(extensionRegistryLite));
            schemaFor.makeImmutable(newMutableInstance);
            return newMutableInstance;
        } catch (InvalidProtocolBufferException e) {
            e = e;
            if (e.getThrownFromInputStream()) {
                e = new InvalidProtocolBufferException((IOException) e);
            }
            throw e.setUnfinishedMessage(newMutableInstance);
        } catch (UninitializedMessageException e2) {
            throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(newMutableInstance);
        } catch (IOException e3) {
            if (e3.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e3.getCause());
            }
            throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(newMutableInstance);
        } catch (IndexOutOfBoundsException unused) {
            throw InvalidProtocolBufferException.truncatedMessage().setUnfinishedMessage(newMutableInstance);
        }
    }

    protected static <T extends GeneratedMessageLite> T parsePartialFrom(T t, CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return parsePartialFrom(t, codedInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    private static GeneratedMessageLite checkMessageInitialized(GeneratedMessageLite generatedMessageLite) {
        if (generatedMessageLite == null || generatedMessageLite.isInitialized()) {
            return generatedMessageLite;
        }
        throw generatedMessageLite.newUninitializedMessageException().asInvalidProtocolBufferException().setUnfinishedMessage(generatedMessageLite);
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parseFrom(t, CodedInputStream.newInstance(byteBuffer), extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return parseFrom(t, byteBuffer, ExtensionRegistryLite.getEmptyRegistry());
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, ByteString byteString) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parseFrom(t, byteString, ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom((GeneratedMessageLite) t, byteString, extensionRegistryLite));
    }

    private static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite generatedMessageLite, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream newCodedInput = byteString.newCodedInput();
        GeneratedMessageLite parsePartialFrom = parsePartialFrom(generatedMessageLite, newCodedInput, extensionRegistryLite);
        try {
            newCodedInput.checkLastTagWas(0);
            return parsePartialFrom;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(parsePartialFrom);
        }
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, byte[] bArr) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom(t, bArr, 0, bArr.length, ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom(t, bArr, 0, bArr.length, extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, InputStream inputStream) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom(t, CodedInputStream.newInstance(inputStream), ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom(t, CodedInputStream.newInstance(inputStream), extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return parseFrom(t, codedInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    protected static <T extends GeneratedMessageLite> T parseFrom(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom(t, codedInputStream, extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite> T parseDelimitedFrom(T t, InputStream inputStream) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialDelimitedFrom(t, inputStream, ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite> T parseDelimitedFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialDelimitedFrom(t, inputStream, extensionRegistryLite));
    }

    private static GeneratedMessageLite parsePartialDelimitedFrom(GeneratedMessageLite generatedMessageLite, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        try {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            CodedInputStream newInstance = CodedInputStream.newInstance((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(read, inputStream)));
            GeneratedMessageLite parsePartialFrom = parsePartialFrom(generatedMessageLite, newInstance, extensionRegistryLite);
            try {
                newInstance.checkLastTagWas(0);
                return parsePartialFrom;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(parsePartialFrom);
            }
        } catch (InvalidProtocolBufferException e2) {
            e = e2;
            if (e.getThrownFromInputStream()) {
                e = new InvalidProtocolBufferException((IOException) e);
            }
            throw e;
        } catch (IOException e3) {
            throw new InvalidProtocolBufferException(e3);
        }
    }
}
