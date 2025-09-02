package com.google.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

abstract class UnsafeUtil {
    private static final long BOOLEAN_ARRAY_BASE_OFFSET;
    private static final long BOOLEAN_ARRAY_INDEX_SCALE;
    private static final long BUFFER_ADDRESS_OFFSET = fieldOffset(bufferAddressField());
    private static final int BYTE_ARRAY_ALIGNMENT;
    static final long BYTE_ARRAY_BASE_OFFSET;
    private static final long DOUBLE_ARRAY_BASE_OFFSET;
    private static final long DOUBLE_ARRAY_INDEX_SCALE;
    private static final long FLOAT_ARRAY_BASE_OFFSET;
    private static final long FLOAT_ARRAY_INDEX_SCALE;
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations();
    private static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = supportsUnsafeByteBufferOperations();
    private static final long INT_ARRAY_BASE_OFFSET;
    private static final long INT_ARRAY_INDEX_SCALE;
    private static final boolean IS_ANDROID_32 = determineAndroidSupportByAddressSize(Integer.TYPE);
    private static final boolean IS_ANDROID_64 = determineAndroidSupportByAddressSize(Long.TYPE);
    static final boolean IS_BIG_ENDIAN = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
    private static final long LONG_ARRAY_BASE_OFFSET;
    private static final long LONG_ARRAY_INDEX_SCALE;
    private static final MemoryAccessor MEMORY_ACCESSOR = getMemoryAccessor();
    private static final Class MEMORY_CLASS = Android.getMemoryClass();
    private static final long OBJECT_ARRAY_BASE_OFFSET;
    private static final long OBJECT_ARRAY_INDEX_SCALE;
    private static final Unsafe UNSAFE = getUnsafe();

    static {
        long arrayBaseOffset = (long) arrayBaseOffset(byte[].class);
        BYTE_ARRAY_BASE_OFFSET = arrayBaseOffset;
        Class<boolean[]> cls = boolean[].class;
        BOOLEAN_ARRAY_BASE_OFFSET = (long) arrayBaseOffset(cls);
        BOOLEAN_ARRAY_INDEX_SCALE = (long) arrayIndexScale(cls);
        Class<int[]> cls2 = int[].class;
        INT_ARRAY_BASE_OFFSET = (long) arrayBaseOffset(cls2);
        INT_ARRAY_INDEX_SCALE = (long) arrayIndexScale(cls2);
        Class<long[]> cls3 = long[].class;
        LONG_ARRAY_BASE_OFFSET = (long) arrayBaseOffset(cls3);
        LONG_ARRAY_INDEX_SCALE = (long) arrayIndexScale(cls3);
        Class<float[]> cls4 = float[].class;
        FLOAT_ARRAY_BASE_OFFSET = (long) arrayBaseOffset(cls4);
        FLOAT_ARRAY_INDEX_SCALE = (long) arrayIndexScale(cls4);
        Class<double[]> cls5 = double[].class;
        DOUBLE_ARRAY_BASE_OFFSET = (long) arrayBaseOffset(cls5);
        DOUBLE_ARRAY_INDEX_SCALE = (long) arrayIndexScale(cls5);
        Class<Object[]> cls6 = Object[].class;
        OBJECT_ARRAY_BASE_OFFSET = (long) arrayBaseOffset(cls6);
        OBJECT_ARRAY_INDEX_SCALE = (long) arrayIndexScale(cls6);
        BYTE_ARRAY_ALIGNMENT = (int) (arrayBaseOffset & 7);
    }

    static boolean hasUnsafeArrayOperations() {
        return HAS_UNSAFE_ARRAY_OPERATIONS;
    }

    static boolean hasUnsafeByteBufferOperations() {
        return HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    }

    static Object allocateInstance(Class cls) {
        try {
            return UNSAFE.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int arrayBaseOffset(Class cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return MEMORY_ACCESSOR.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int arrayIndexScale(Class cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return MEMORY_ACCESSOR.arrayIndexScale(cls);
        }
        return -1;
    }

    static int getInt(Object obj, long j) {
        return MEMORY_ACCESSOR.getInt(obj, j);
    }

    static void putInt(Object obj, long j, int i) {
        MEMORY_ACCESSOR.putInt(obj, j, i);
    }

    static long getLong(Object obj, long j) {
        return MEMORY_ACCESSOR.getLong(obj, j);
    }

    static void putLong(Object obj, long j, long j2) {
        MEMORY_ACCESSOR.putLong(obj, j, j2);
    }

    static boolean getBoolean(Object obj, long j) {
        return MEMORY_ACCESSOR.getBoolean(obj, j);
    }

    static void putBoolean(Object obj, long j, boolean z) {
        MEMORY_ACCESSOR.putBoolean(obj, j, z);
    }

    static float getFloat(Object obj, long j) {
        return MEMORY_ACCESSOR.getFloat(obj, j);
    }

    static void putFloat(Object obj, long j, float f) {
        MEMORY_ACCESSOR.putFloat(obj, j, f);
    }

    static double getDouble(Object obj, long j) {
        return MEMORY_ACCESSOR.getDouble(obj, j);
    }

    static void putDouble(Object obj, long j, double d) {
        MEMORY_ACCESSOR.putDouble(obj, j, d);
    }

    static Object getObject(Object obj, long j) {
        return MEMORY_ACCESSOR.getObject(obj, j);
    }

    static void putObject(Object obj, long j, Object obj2) {
        MEMORY_ACCESSOR.putObject(obj, j, obj2);
    }

    static byte getByte(byte[] bArr, long j) {
        return MEMORY_ACCESSOR.getByte(bArr, BYTE_ARRAY_BASE_OFFSET + j);
    }

    static void putByte(byte[] bArr, long j, byte b) {
        MEMORY_ACCESSOR.putByte(bArr, BYTE_ARRAY_BASE_OFFSET + j, b);
    }

    static void copyMemory(long j, byte[] bArr, long j2, long j3) {
        MEMORY_ACCESSOR.copyMemory(j, bArr, j2, j3);
    }

    static byte getByte(long j) {
        return MEMORY_ACCESSOR.getByte(j);
    }

    static long getLong(long j) {
        return MEMORY_ACCESSOR.getLong(j);
    }

    static long addressOffset(ByteBuffer byteBuffer) {
        return MEMORY_ACCESSOR.getLong(byteBuffer, BUFFER_ADDRESS_OFFSET);
    }

    static Unsafe getUnsafe() {
        try {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Unsafe run() {
                    Class<Unsafe> cls = Unsafe.class;
                    for (Field field : cls.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get((Object) null);
                        if (cls.isInstance(obj)) {
                            return cls.cast(obj);
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable unused) {
            return null;
        }
    }

    private static MemoryAccessor getMemoryAccessor() {
        Unsafe unsafe = UNSAFE;
        if (unsafe == null) {
            return null;
        }
        if (!Android.isOnAndroidDevice()) {
            return new JvmMemoryAccessor(unsafe);
        }
        if (IS_ANDROID_64) {
            return new Android64MemoryAccessor(unsafe);
        }
        if (IS_ANDROID_32) {
            return new Android32MemoryAccessor(unsafe);
        }
        return null;
    }

    private static boolean supportsUnsafeArrayOperations() {
        MemoryAccessor memoryAccessor = MEMORY_ACCESSOR;
        if (memoryAccessor == null) {
            return false;
        }
        return memoryAccessor.supportsUnsafeArrayOperations();
    }

    private static boolean supportsUnsafeByteBufferOperations() {
        MemoryAccessor memoryAccessor = MEMORY_ACCESSOR;
        if (memoryAccessor == null) {
            return false;
        }
        return memoryAccessor.supportsUnsafeByteBufferOperations();
    }

    static boolean determineAndroidSupportByAddressSize(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!Android.isOnAndroidDevice()) {
            return false;
        }
        try {
            Class cls3 = MEMORY_CLASS;
            Class cls4 = Boolean.TYPE;
            cls3.getMethod("peekLong", new Class[]{cls, cls4});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, cls4});
            Class cls5 = Integer.TYPE;
            cls3.getMethod("pokeInt", new Class[]{cls, cls5, cls4});
            cls3.getMethod("peekInt", new Class[]{cls, cls4});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, cls5, cls5});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, cls5, cls5});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static Field bufferAddressField() {
        Field field;
        Class<Buffer> cls = Buffer.class;
        if (Android.isOnAndroidDevice() && (field = field(cls, "effectiveDirectAddress")) != null) {
            return field;
        }
        Field field2 = field(cls, "address");
        if (field2 == null || field2.getType() != Long.TYPE) {
            return null;
        }
        return field2;
    }

    private static long fieldOffset(Field field) {
        MemoryAccessor memoryAccessor;
        if (field == null || (memoryAccessor = MEMORY_ACCESSOR) == null) {
            return -1;
        }
        return memoryAccessor.objectFieldOffset(field);
    }

    private static Field field(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static abstract class MemoryAccessor {
        Unsafe unsafe;

        public abstract void copyMemory(long j, byte[] bArr, long j2, long j3);

        public abstract boolean getBoolean(Object obj, long j);

        public abstract byte getByte(long j);

        public abstract byte getByte(Object obj, long j);

        public abstract double getDouble(Object obj, long j);

        public abstract float getFloat(Object obj, long j);

        public abstract long getLong(long j);

        public abstract void putBoolean(Object obj, long j, boolean z);

        public abstract void putByte(Object obj, long j, byte b);

        public abstract void putDouble(Object obj, long j, double d);

        public abstract void putFloat(Object obj, long j, float f);

        MemoryAccessor(Unsafe unsafe2) {
            this.unsafe = unsafe2;
        }

        public final long objectFieldOffset(Field field) {
            return this.unsafe.objectFieldOffset(field);
        }

        public final int arrayBaseOffset(Class cls) {
            return this.unsafe.arrayBaseOffset(cls);
        }

        public final int arrayIndexScale(Class cls) {
            return this.unsafe.arrayIndexScale(cls);
        }

        public boolean supportsUnsafeArrayOperations() {
            Class<Class> cls = Class.class;
            Class<Object> cls2 = Object.class;
            Unsafe unsafe2 = this.unsafe;
            if (unsafe2 == null) {
                return false;
            }
            try {
                Class<?> cls3 = unsafe2.getClass();
                cls3.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls3.getMethod("arrayBaseOffset", new Class[]{cls});
                cls3.getMethod("arrayIndexScale", new Class[]{cls});
                Class cls4 = Long.TYPE;
                cls3.getMethod("getInt", new Class[]{cls2, cls4});
                cls3.getMethod("putInt", new Class[]{cls2, cls4, Integer.TYPE});
                cls3.getMethod("getLong", new Class[]{cls2, cls4});
                cls3.getMethod("putLong", new Class[]{cls2, cls4, cls4});
                cls3.getMethod("getObject", new Class[]{cls2, cls4});
                cls3.getMethod("putObject", new Class[]{cls2, cls4, cls2});
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }

        public final int getInt(Object obj, long j) {
            return this.unsafe.getInt(obj, j);
        }

        public final void putInt(Object obj, long j, int i) {
            this.unsafe.putInt(obj, j, i);
        }

        public final long getLong(Object obj, long j) {
            return this.unsafe.getLong(obj, j);
        }

        public final void putLong(Object obj, long j, long j2) {
            this.unsafe.putLong(obj, j, j2);
        }

        public final Object getObject(Object obj, long j) {
            return this.unsafe.getObject(obj, j);
        }

        public final void putObject(Object obj, long j, Object obj2) {
            this.unsafe.putObject(obj, j, obj2);
        }

        public boolean supportsUnsafeByteBufferOperations() {
            Unsafe unsafe2 = this.unsafe;
            if (unsafe2 == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe2.getClass();
                cls.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                if (UnsafeUtil.bufferAddressField() == null) {
                    return false;
                }
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }
    }

    private static final class JvmMemoryAccessor extends MemoryAccessor {
        JvmMemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        public boolean supportsUnsafeArrayOperations() {
            Class<Object> cls = Object.class;
            if (!super.supportsUnsafeArrayOperations()) {
                return false;
            }
            try {
                Class<?> cls2 = this.unsafe.getClass();
                Class cls3 = Long.TYPE;
                cls2.getMethod("getByte", new Class[]{cls, cls3});
                cls2.getMethod("putByte", new Class[]{cls, cls3, Byte.TYPE});
                cls2.getMethod("getBoolean", new Class[]{cls, cls3});
                cls2.getMethod("putBoolean", new Class[]{cls, cls3, Boolean.TYPE});
                cls2.getMethod("getFloat", new Class[]{cls, cls3});
                cls2.getMethod("putFloat", new Class[]{cls, cls3, Float.TYPE});
                cls2.getMethod("getDouble", new Class[]{cls, cls3});
                cls2.getMethod("putDouble", new Class[]{cls, cls3, Double.TYPE});
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }

        public byte getByte(Object obj, long j) {
            return this.unsafe.getByte(obj, j);
        }

        public void putByte(Object obj, long j, byte b) {
            this.unsafe.putByte(obj, j, b);
        }

        public boolean getBoolean(Object obj, long j) {
            return this.unsafe.getBoolean(obj, j);
        }

        public void putBoolean(Object obj, long j, boolean z) {
            this.unsafe.putBoolean(obj, j, z);
        }

        public float getFloat(Object obj, long j) {
            return this.unsafe.getFloat(obj, j);
        }

        public void putFloat(Object obj, long j, float f) {
            this.unsafe.putFloat(obj, j, f);
        }

        public double getDouble(Object obj, long j) {
            return this.unsafe.getDouble(obj, j);
        }

        public void putDouble(Object obj, long j, double d) {
            this.unsafe.putDouble(obj, j, d);
        }

        public boolean supportsUnsafeByteBufferOperations() {
            Class<Object> cls = Object.class;
            if (!super.supportsUnsafeByteBufferOperations()) {
                return false;
            }
            try {
                Class<?> cls2 = this.unsafe.getClass();
                Class cls3 = Long.TYPE;
                cls2.getMethod("getByte", new Class[]{cls3});
                cls2.getMethod("putByte", new Class[]{cls3, Byte.TYPE});
                cls2.getMethod("getInt", new Class[]{cls3});
                cls2.getMethod("putInt", new Class[]{cls3, Integer.TYPE});
                cls2.getMethod("getLong", new Class[]{cls3});
                cls2.getMethod("putLong", new Class[]{cls3, cls3});
                cls2.getMethod("copyMemory", new Class[]{cls3, cls3, cls3});
                cls2.getMethod("copyMemory", new Class[]{cls, cls3, cls, cls3, cls3});
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }

        public byte getByte(long j) {
            return this.unsafe.getByte(j);
        }

        public long getLong(long j) {
            return this.unsafe.getLong(j);
        }

        public void copyMemory(long j, byte[] bArr, long j2, long j3) {
            this.unsafe.copyMemory((Object) null, j, bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j2, j3);
        }
    }

    private static final class Android64MemoryAccessor extends MemoryAccessor {
        public boolean supportsUnsafeByteBufferOperations() {
            return false;
        }

        Android64MemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        public byte getByte(Object obj, long j) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                return UnsafeUtil.getByteBigEndian(obj, j);
            }
            return UnsafeUtil.getByteLittleEndian(obj, j);
        }

        public void putByte(Object obj, long j, byte b) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, b);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, b);
            }
        }

        public boolean getBoolean(Object obj, long j) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                return UnsafeUtil.getBooleanBigEndian(obj, j);
            }
            return UnsafeUtil.getBooleanLittleEndian(obj, j);
        }

        public void putBoolean(Object obj, long j, boolean z) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putBooleanBigEndian(obj, j, z);
            } else {
                UnsafeUtil.putBooleanLittleEndian(obj, j, z);
            }
        }

        public float getFloat(Object obj, long j) {
            return Float.intBitsToFloat(getInt(obj, j));
        }

        public void putFloat(Object obj, long j, float f) {
            putInt(obj, j, Float.floatToIntBits(f));
        }

        public double getDouble(Object obj, long j) {
            return Double.longBitsToDouble(getLong(obj, j));
        }

        public void putDouble(Object obj, long j, double d) {
            putLong(obj, j, Double.doubleToLongBits(d));
        }

        public byte getByte(long j) {
            throw new UnsupportedOperationException();
        }

        public long getLong(long j) {
            throw new UnsupportedOperationException();
        }

        public void copyMemory(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }
    }

    private static final class Android32MemoryAccessor extends MemoryAccessor {
        public boolean supportsUnsafeByteBufferOperations() {
            return false;
        }

        Android32MemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        public byte getByte(Object obj, long j) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                return UnsafeUtil.getByteBigEndian(obj, j);
            }
            return UnsafeUtil.getByteLittleEndian(obj, j);
        }

        public void putByte(Object obj, long j, byte b) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, b);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, b);
            }
        }

        public boolean getBoolean(Object obj, long j) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                return UnsafeUtil.getBooleanBigEndian(obj, j);
            }
            return UnsafeUtil.getBooleanLittleEndian(obj, j);
        }

        public void putBoolean(Object obj, long j, boolean z) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putBooleanBigEndian(obj, j, z);
            } else {
                UnsafeUtil.putBooleanLittleEndian(obj, j, z);
            }
        }

        public float getFloat(Object obj, long j) {
            return Float.intBitsToFloat(getInt(obj, j));
        }

        public void putFloat(Object obj, long j, float f) {
            putInt(obj, j, Float.floatToIntBits(f));
        }

        public double getDouble(Object obj, long j) {
            return Double.longBitsToDouble(getLong(obj, j));
        }

        public void putDouble(Object obj, long j, double d) {
            putLong(obj, j, Double.doubleToLongBits(d));
        }

        public byte getByte(long j) {
            throw new UnsupportedOperationException();
        }

        public long getLong(long j) {
            throw new UnsupportedOperationException();
        }

        public void copyMemory(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: private */
    public static byte getByteBigEndian(Object obj, long j) {
        return (byte) ((getInt(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255);
    }

    /* access modifiers changed from: private */
    public static byte getByteLittleEndian(Object obj, long j) {
        return (byte) ((getInt(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255);
    }

    /* access modifiers changed from: private */
    public static void putByteBigEndian(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = getInt(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        putInt(obj, j2, ((255 & b) << i2) | (i & (~(255 << i2))));
    }

    /* access modifiers changed from: private */
    public static void putByteLittleEndian(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        putInt(obj, j2, ((255 & b) << i) | (getInt(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static boolean getBooleanBigEndian(Object obj, long j) {
        return getByteBigEndian(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean getBooleanLittleEndian(Object obj, long j) {
        return getByteLittleEndian(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static void putBooleanBigEndian(Object obj, long j, boolean z) {
        putByteBigEndian(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void putBooleanLittleEndian(Object obj, long j, boolean z) {
        putByteLittleEndian(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void logMissingMethod(Throwable th) {
        Logger logger = Logger.getLogger(UnsafeUtil.class.getName());
        Level level = Level.WARNING;
        logger.log(level, "platform method missing - proto runtime falling back to safer methods: " + th);
    }
}
