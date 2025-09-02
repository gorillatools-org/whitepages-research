package com.facebook.react.common.mapbuffer;

import android.util.SparseArray;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.mapbuffer.MapBuffer;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@DoNotStrip
public final class WritableMapBuffer implements MapBuffer {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final SparseArray<Object> values = new SparseArray<>();

    public final WritableMapBuffer put(int i, boolean z) {
        return putInternal(i, Boolean.valueOf(z));
    }

    public final WritableMapBuffer put(int i, int i2) {
        return putInternal(i, Integer.valueOf(i2));
    }

    public final WritableMapBuffer put(int i, long j) {
        return putInternal(i, Long.valueOf(j));
    }

    public final WritableMapBuffer put(int i, double d) {
        return putInternal(i, Double.valueOf(d));
    }

    public final WritableMapBuffer put(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        return putInternal(i, str);
    }

    public final WritableMapBuffer put(int i, MapBuffer mapBuffer) {
        Intrinsics.checkNotNullParameter(mapBuffer, "value");
        return putInternal(i, mapBuffer);
    }

    private final WritableMapBuffer putInternal(int i, Object obj) {
        IntRange kEY_RANGE$ReactAndroid_release = MapBuffer.Companion.getKEY_RANGE$ReactAndroid_release();
        int first = kEY_RANGE$ReactAndroid_release.getFirst();
        if (i > kEY_RANGE$ReactAndroid_release.getLast() || first > i) {
            throw new IllegalArgumentException("Only integers in [0;65535] range are allowed for keys.");
        }
        this.values.put(i, obj);
        return this;
    }

    public int getCount() {
        return this.values.size();
    }

    public boolean contains(int i) {
        return this.values.get(i) != null;
    }

    public int getKeyOffset(int i) {
        return this.values.indexOfKey(i);
    }

    public MapBuffer.Entry entryAt(int i) {
        return new MapBufferEntry(i);
    }

    public MapBuffer.DataType getType(int i) {
        Object obj = this.values.get(i);
        if (obj != null) {
            return dataType(obj, i);
        }
        throw new IllegalArgumentException(("Key not found: " + i).toString());
    }

    public boolean getBoolean(int i) {
        Object obj = this.values.get(i);
        if (obj == null) {
            throw new IllegalArgumentException(("Key not found: " + i).toString());
        } else if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        } else {
            Class<?> cls = obj.getClass();
            throw new IllegalStateException(("Expected " + Boolean.class + " for key: " + i + ", found " + cls + " instead.").toString());
        }
    }

    public int getInt(int i) {
        Object obj = this.values.get(i);
        if (obj == null) {
            throw new IllegalArgumentException(("Key not found: " + i).toString());
        } else if (obj instanceof Integer) {
            return ((Number) obj).intValue();
        } else {
            Class<?> cls = obj.getClass();
            throw new IllegalStateException(("Expected " + Integer.class + " for key: " + i + ", found " + cls + " instead.").toString());
        }
    }

    public long getLong(int i) {
        Object obj = this.values.get(i);
        if (obj == null) {
            throw new IllegalArgumentException(("Key not found: " + i).toString());
        } else if (obj instanceof Long) {
            return ((Number) obj).longValue();
        } else {
            Class<?> cls = obj.getClass();
            throw new IllegalStateException(("Expected " + Long.class + " for key: " + i + ", found " + cls + " instead.").toString());
        }
    }

    public double getDouble(int i) {
        Object obj = this.values.get(i);
        if (obj == null) {
            throw new IllegalArgumentException(("Key not found: " + i).toString());
        } else if (obj instanceof Double) {
            return ((Number) obj).doubleValue();
        } else {
            Class<?> cls = obj.getClass();
            throw new IllegalStateException(("Expected " + Double.class + " for key: " + i + ", found " + cls + " instead.").toString());
        }
    }

    public String getString(int i) {
        Object obj = this.values.get(i);
        if (obj == null) {
            throw new IllegalArgumentException(("Key not found: " + i).toString());
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            Class<?> cls = obj.getClass();
            throw new IllegalStateException(("Expected " + String.class + " for key: " + i + ", found " + cls + " instead.").toString());
        }
    }

    public MapBuffer getMapBuffer(int i) {
        Object obj = this.values.get(i);
        if (obj == null) {
            throw new IllegalArgumentException(("Key not found: " + i).toString());
        } else if (obj instanceof MapBuffer) {
            return (MapBuffer) obj;
        } else {
            Class<?> cls = obj.getClass();
            throw new IllegalStateException(("Expected " + MapBuffer.class + " for key: " + i + ", found " + cls + " instead.").toString());
        }
    }

    public List<MapBuffer> getMapBufferList(int i) {
        Object obj = this.values.get(i);
        if (obj == null) {
            throw new IllegalArgumentException(("Key not found: " + i).toString());
        } else if (obj instanceof List) {
            return (List) obj;
        } else {
            Class<?> cls = obj.getClass();
            throw new IllegalStateException(("Expected " + List.class + " for key: " + i + ", found " + cls + " instead.").toString());
        }
    }

    private final /* synthetic */ <T> T verifyValue(int i, Object obj) {
        if (obj != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (obj != null) {
                return obj;
            }
            Intrinsics.reifiedOperationMarker(4, "T");
            Class<?> cls = obj.getClass();
            throw new IllegalStateException(("Expected " + Object.class + " for key: " + i + ", found " + cls + " instead.").toString());
        }
        throw new IllegalArgumentException(("Key not found: " + i).toString());
    }

    /* access modifiers changed from: private */
    public final MapBuffer.DataType dataType(Object obj, int i) {
        if (obj instanceof Boolean) {
            return MapBuffer.DataType.BOOL;
        }
        if (obj instanceof Integer) {
            return MapBuffer.DataType.INT;
        }
        if (obj instanceof Long) {
            return MapBuffer.DataType.LONG;
        }
        if (obj instanceof Double) {
            return MapBuffer.DataType.DOUBLE;
        }
        if (obj instanceof String) {
            return MapBuffer.DataType.STRING;
        }
        if (obj instanceof MapBuffer) {
            return MapBuffer.DataType.MAP;
        }
        Class<?> cls = obj.getClass();
        throw new IllegalStateException("Key " + i + " has value of unknown type: " + cls);
    }

    public Iterator<MapBuffer.Entry> iterator() {
        return new WritableMapBuffer$iterator$1(this);
    }

    private final class MapBufferEntry implements MapBuffer.Entry {
        private final int index;
        private final int key;
        private final MapBuffer.DataType type;

        public MapBufferEntry(int i) {
            this.index = i;
            this.key = WritableMapBuffer.this.values.keyAt(i);
            Object valueAt = WritableMapBuffer.this.values.valueAt(i);
            Intrinsics.checkNotNullExpressionValue(valueAt, "valueAt(...)");
            this.type = WritableMapBuffer.this.dataType(valueAt, getKey());
        }

        public int getKey() {
            return this.key;
        }

        public MapBuffer.DataType getType() {
            return this.type;
        }

        public boolean getBooleanValue() {
            int key2 = getKey();
            Object valueAt = WritableMapBuffer.this.values.valueAt(this.index);
            if (valueAt == null) {
                throw new IllegalArgumentException(("Key not found: " + key2).toString());
            } else if (valueAt instanceof Boolean) {
                return ((Boolean) valueAt).booleanValue();
            } else {
                Class<?> cls = valueAt.getClass();
                throw new IllegalStateException(("Expected " + Boolean.class + " for key: " + key2 + ", found " + cls + " instead.").toString());
            }
        }

        public int getIntValue() {
            int key2 = getKey();
            Object valueAt = WritableMapBuffer.this.values.valueAt(this.index);
            if (valueAt == null) {
                throw new IllegalArgumentException(("Key not found: " + key2).toString());
            } else if (valueAt instanceof Integer) {
                return ((Number) valueAt).intValue();
            } else {
                Class<?> cls = valueAt.getClass();
                throw new IllegalStateException(("Expected " + Integer.class + " for key: " + key2 + ", found " + cls + " instead.").toString());
            }
        }

        public long getLongValue() {
            int key2 = getKey();
            Object valueAt = WritableMapBuffer.this.values.valueAt(this.index);
            if (valueAt == null) {
                throw new IllegalArgumentException(("Key not found: " + key2).toString());
            } else if (valueAt instanceof Long) {
                return ((Number) valueAt).longValue();
            } else {
                Class<?> cls = valueAt.getClass();
                throw new IllegalStateException(("Expected " + Long.class + " for key: " + key2 + ", found " + cls + " instead.").toString());
            }
        }

        public double getDoubleValue() {
            int key2 = getKey();
            Object valueAt = WritableMapBuffer.this.values.valueAt(this.index);
            if (valueAt == null) {
                throw new IllegalArgumentException(("Key not found: " + key2).toString());
            } else if (valueAt instanceof Double) {
                return ((Number) valueAt).doubleValue();
            } else {
                Class<?> cls = valueAt.getClass();
                throw new IllegalStateException(("Expected " + Double.class + " for key: " + key2 + ", found " + cls + " instead.").toString());
            }
        }

        public String getStringValue() {
            int key2 = getKey();
            Object valueAt = WritableMapBuffer.this.values.valueAt(this.index);
            if (valueAt == null) {
                throw new IllegalArgumentException(("Key not found: " + key2).toString());
            } else if (valueAt instanceof String) {
                return (String) valueAt;
            } else {
                Class<?> cls = valueAt.getClass();
                throw new IllegalStateException(("Expected " + String.class + " for key: " + key2 + ", found " + cls + " instead.").toString());
            }
        }

        public MapBuffer getMapBufferValue() {
            int key2 = getKey();
            Object valueAt = WritableMapBuffer.this.values.valueAt(this.index);
            if (valueAt == null) {
                throw new IllegalArgumentException(("Key not found: " + key2).toString());
            } else if (valueAt instanceof MapBuffer) {
                return (MapBuffer) valueAt;
            } else {
                Class<?> cls = valueAt.getClass();
                throw new IllegalStateException(("Expected " + MapBuffer.class + " for key: " + key2 + ", found " + cls + " instead.").toString());
            }
        }
    }

    @DoNotStrip
    private final int[] getKeys() {
        int size = this.values.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = this.values.keyAt(i);
        }
        return iArr;
    }

    @DoNotStrip
    private final Object[] getValues() {
        int size = this.values.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            Object valueAt = this.values.valueAt(i);
            Intrinsics.checkNotNullExpressionValue(valueAt, "valueAt(...)");
            objArr[i] = valueAt;
        }
        return objArr;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        MapBufferSoLoader.staticInit();
    }
}
