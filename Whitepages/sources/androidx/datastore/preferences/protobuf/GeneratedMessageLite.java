package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.AbstractMessageLite;
import androidx.datastore.preferences.protobuf.Internal;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static class GeneratedExtension extends ExtensionLite {
    }

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

    /* access modifiers changed from: protected */
    public void makeImmutable() {
        Protobuf.getInstance().schemaFor((Object) this).makeImmutable(this);
        markImmutable();
    }

    /* access modifiers changed from: protected */
    public final Builder createBuilder() {
        return (Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    public final boolean isInitialized() {
        return isInitialized(this, true);
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

    public void writeTo(CodedOutputStream codedOutputStream) {
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
    public Object buildMessageInfo() {
        return dynamicMethod(MethodToInvoke.BUILD_MESSAGE_INFO);
    }

    static GeneratedMessageLite getDefaultInstance(Class cls) {
        GeneratedMessageLite generatedMessageLite = defaultInstanceMap.get(cls);
        if (generatedMessageLite == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                generatedMessageLite = defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (generatedMessageLite == null) {
            generatedMessageLite = ((GeneratedMessageLite) UnsafeUtil.allocateInstance(cls)).getDefaultInstanceForType();
            if (generatedMessageLite != null) {
                defaultInstanceMap.put(cls, generatedMessageLite);
            } else {
                throw new IllegalStateException();
            }
        }
        return generatedMessageLite;
    }

    protected static void registerDefaultInstance(Class cls, GeneratedMessageLite generatedMessageLite) {
        generatedMessageLite.markImmutable();
        defaultInstanceMap.put(cls, generatedMessageLite);
    }

    protected static Object newMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        return new RawMessageInfo(messageLite, str, objArr);
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

        private static void mergeFromInstance(Object obj, Object obj2) {
            Protobuf.getInstance().schemaFor(obj).mergeFrom(obj, obj2);
        }

        public GeneratedMessageLite getDefaultInstanceForType() {
            return this.defaultInstance;
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

    protected static final boolean isInitialized(GeneratedMessageLite generatedMessageLite, boolean z) {
        byte byteValue = ((Byte) generatedMessageLite.dynamicMethod(MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean isInitialized = Protobuf.getInstance().schemaFor((Object) generatedMessageLite).isInitialized(generatedMessageLite);
        if (z) {
            generatedMessageLite.dynamicMethod(MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED, isInitialized ? generatedMessageLite : null);
        }
        return isInitialized;
    }

    protected static Internal.ProtobufList emptyProtobufList() {
        return ProtobufArrayList.emptyList();
    }

    protected static Internal.ProtobufList mutableCopy(Internal.ProtobufList protobufList) {
        int size = protobufList.size();
        return protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static class DefaultInstanceBasedParser extends AbstractParser {
        private final GeneratedMessageLite defaultInstance;

        public DefaultInstanceBasedParser(GeneratedMessageLite generatedMessageLite) {
            this.defaultInstance = generatedMessageLite;
        }
    }

    static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite generatedMessageLite, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        GeneratedMessageLite newMutableInstance = generatedMessageLite.newMutableInstance();
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

    private static GeneratedMessageLite checkMessageInitialized(GeneratedMessageLite generatedMessageLite) {
        if (generatedMessageLite == null || generatedMessageLite.isInitialized()) {
            return generatedMessageLite;
        }
        throw generatedMessageLite.newUninitializedMessageException().asInvalidProtocolBufferException().setUnfinishedMessage(generatedMessageLite);
    }

    protected static GeneratedMessageLite parseFrom(GeneratedMessageLite generatedMessageLite, InputStream inputStream) {
        return checkMessageInitialized(parsePartialFrom(generatedMessageLite, CodedInputStream.newInstance(inputStream), ExtensionRegistryLite.getEmptyRegistry()));
    }
}
