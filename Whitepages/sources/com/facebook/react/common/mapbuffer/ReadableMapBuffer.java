package com.facebook.react.common.mapbuffer;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.mapbuffer.MapBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UShort;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;

@DoNotStrip
public final class ReadableMapBuffer implements MapBuffer {
    private static final int ALIGNMENT = 254;
    private static final int BUCKET_SIZE = 12;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int HEADER_SIZE = 8;
    private static final int TYPE_OFFSET = 2;
    private static final int VALUE_OFFSET = 4;
    private final ByteBuffer buffer;
    private int count;
    @DoNotStrip
    private final HybridData mHybridData;
    private final int offsetToMapBuffer;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.facebook.react.common.mapbuffer.MapBuffer$DataType[] r0 = com.facebook.react.common.mapbuffer.MapBuffer.DataType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.common.mapbuffer.MapBuffer$DataType r1 = com.facebook.react.common.mapbuffer.MapBuffer.DataType.BOOL     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.common.mapbuffer.MapBuffer$DataType r1 = com.facebook.react.common.mapbuffer.MapBuffer.DataType.INT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.common.mapbuffer.MapBuffer$DataType r1 = com.facebook.react.common.mapbuffer.MapBuffer.DataType.LONG     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.common.mapbuffer.MapBuffer$DataType r1 = com.facebook.react.common.mapbuffer.MapBuffer.DataType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.facebook.react.common.mapbuffer.MapBuffer$DataType r1 = com.facebook.react.common.mapbuffer.MapBuffer.DataType.STRING     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.facebook.react.common.mapbuffer.MapBuffer$DataType r1 = com.facebook.react.common.mapbuffer.MapBuffer.DataType.MAP     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.common.mapbuffer.ReadableMapBuffer.WhenMappings.<clinit>():void");
        }
    }

    private final native ByteBuffer importByteBuffer();

    public int getCount() {
        return this.count;
    }

    @DoNotStrip
    private ReadableMapBuffer(HybridData hybridData) {
        this.mHybridData = hybridData;
        this.buffer = importByteBuffer();
        this.offsetToMapBuffer = 0;
        readHeader();
    }

    private ReadableMapBuffer(ByteBuffer byteBuffer) {
        this.mHybridData = null;
        this.buffer = byteBuffer;
        this.offsetToMapBuffer = 0;
        readHeader();
    }

    private ReadableMapBuffer(ByteBuffer byteBuffer, int i) {
        this.mHybridData = null;
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position(i);
        this.buffer = duplicate;
        this.offsetToMapBuffer = i;
        readHeader();
    }

    private final void readHeader() {
        if (this.buffer.getShort() != ALIGNMENT) {
            this.buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        this.count = m266readUnsignedShortBwKQO78(this.buffer.position()) & 65535;
    }

    private final int getOffsetForDynamicData() {
        return getKeyOffsetForBucketIndex(getCount());
    }

    private final int getBucketIndexForKey(int i) {
        IntRange kEY_RANGE$ReactAndroid_release = MapBuffer.Companion.getKEY_RANGE$ReactAndroid_release();
        int first = kEY_RANGE$ReactAndroid_release.getFirst();
        if (i <= kEY_RANGE$ReactAndroid_release.getLast() && first <= i) {
            short r8 = UShort.m870constructorimpl((short) i);
            int count2 = getCount() - 1;
            int i2 = 0;
            while (i2 <= count2) {
                int i3 = (i2 + count2) >>> 1;
                short r4 = m266readUnsignedShortBwKQO78(getKeyOffsetForBucketIndex(i3)) & 65535;
                short s = 65535 & r8;
                if (Intrinsics.compare((int) r4, (int) s) < 0) {
                    i2 = i3 + 1;
                } else if (Intrinsics.compare((int) r4, (int) s) <= 0) {
                    return i3;
                } else {
                    count2 = i3 - 1;
                }
            }
        }
        return -1;
    }

    private final MapBuffer.DataType readDataType(int i) {
        return MapBuffer.DataType.values()[m266readUnsignedShortBwKQO78(getKeyOffsetForBucketIndex(i) + 2) & 65535];
    }

    private final int getTypedValueOffsetForKey(int i, MapBuffer.DataType dataType) {
        int bucketIndexForKey = getBucketIndexForKey(i);
        if (bucketIndexForKey != -1) {
            MapBuffer.DataType readDataType = readDataType(bucketIndexForKey);
            if (readDataType == dataType) {
                return getKeyOffsetForBucketIndex(bucketIndexForKey) + 4;
            }
            throw new IllegalStateException(("Expected " + dataType + " for key: " + i + ", found " + readDataType + " instead.").toString());
        }
        throw new IllegalArgumentException(("Key not found: " + i).toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: readUnsignedShort-BwKQO78  reason: not valid java name */
    public final short m266readUnsignedShortBwKQO78(int i) {
        return UShort.m870constructorimpl(this.buffer.getShort(i));
    }

    /* access modifiers changed from: private */
    public final double readDoubleValue(int i) {
        return this.buffer.getDouble(i);
    }

    /* access modifiers changed from: private */
    public final int readIntValue(int i) {
        return this.buffer.getInt(i);
    }

    /* access modifiers changed from: private */
    public final long readLongValue(int i) {
        return this.buffer.getLong(i);
    }

    /* access modifiers changed from: private */
    public final boolean readBooleanValue(int i) {
        return readIntValue(i) == 1;
    }

    /* access modifiers changed from: private */
    public final String readStringValue(int i) {
        int offsetForDynamicData = getOffsetForDynamicData() + this.buffer.getInt(i);
        int i2 = this.buffer.getInt(offsetForDynamicData);
        byte[] bArr = new byte[i2];
        this.buffer.position(offsetForDynamicData + 4);
        this.buffer.get(bArr, 0, i2);
        return new String(bArr, Charsets.UTF_8);
    }

    /* access modifiers changed from: private */
    public final ReadableMapBuffer readMapBufferValue(int i) {
        return new ReadableMapBuffer(this.buffer, getOffsetForDynamicData() + this.buffer.getInt(i) + 4);
    }

    private final List<ReadableMapBuffer> readMapBufferListValue(int i) {
        ArrayList arrayList = new ArrayList();
        int offsetForDynamicData = getOffsetForDynamicData() + this.buffer.getInt(i);
        int i2 = this.buffer.getInt(offsetForDynamicData);
        int i3 = offsetForDynamicData + 4;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = this.buffer.getInt(i3 + i4);
            int i6 = i4 + 4;
            arrayList.add(new ReadableMapBuffer(this.buffer, i3 + i6));
            i4 = i6 + i5;
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final int getKeyOffsetForBucketIndex(int i) {
        return this.offsetToMapBuffer + 8 + (i * 12);
    }

    public boolean contains(int i) {
        return getBucketIndexForKey(i) != -1;
    }

    public int getKeyOffset(int i) {
        return getBucketIndexForKey(i);
    }

    public MapBuffer.Entry entryAt(int i) {
        return new MapBufferEntry(getKeyOffsetForBucketIndex(i));
    }

    public MapBuffer.DataType getType(int i) {
        int bucketIndexForKey = getBucketIndexForKey(i);
        if (bucketIndexForKey != -1) {
            return readDataType(bucketIndexForKey);
        }
        throw new IllegalArgumentException(("Key not found: " + i).toString());
    }

    public int getInt(int i) {
        return readIntValue(getTypedValueOffsetForKey(i, MapBuffer.DataType.INT));
    }

    public long getLong(int i) {
        return readLongValue(getTypedValueOffsetForKey(i, MapBuffer.DataType.LONG));
    }

    public double getDouble(int i) {
        return readDoubleValue(getTypedValueOffsetForKey(i, MapBuffer.DataType.DOUBLE));
    }

    public String getString(int i) {
        return readStringValue(getTypedValueOffsetForKey(i, MapBuffer.DataType.STRING));
    }

    public boolean getBoolean(int i) {
        return readBooleanValue(getTypedValueOffsetForKey(i, MapBuffer.DataType.BOOL));
    }

    public ReadableMapBuffer getMapBuffer(int i) {
        return readMapBufferValue(getTypedValueOffsetForKey(i, MapBuffer.DataType.MAP));
    }

    public List<ReadableMapBuffer> getMapBufferList(int i) {
        return readMapBufferListValue(getTypedValueOffsetForKey(i, MapBuffer.DataType.MAP));
    }

    public int hashCode() {
        this.buffer.rewind();
        return this.buffer.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ReadableMapBuffer)) {
            return false;
        }
        ByteBuffer byteBuffer = this.buffer;
        ByteBuffer byteBuffer2 = ((ReadableMapBuffer) obj).buffer;
        if (byteBuffer == byteBuffer2) {
            return true;
        }
        byteBuffer.rewind();
        byteBuffer2.rewind();
        return Intrinsics.areEqual((Object) byteBuffer, (Object) byteBuffer2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        CollectionsKt.joinTo$default(this, sb, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new ReadableMapBuffer$$ExternalSyntheticLambda0(), 62, (Object) null);
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* access modifiers changed from: private */
    public static final CharSequence toString$lambda$5(MapBuffer.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        StringBuilder sb = new StringBuilder();
        sb.append(entry.getKey());
        sb.append('=');
        switch (WhenMappings.$EnumSwitchMapping$0[entry.getType().ordinal()]) {
            case 1:
                sb.append(entry.getBooleanValue());
                break;
            case 2:
                sb.append(entry.getIntValue());
                break;
            case 3:
                sb.append(entry.getLongValue());
                break;
            case 4:
                sb.append(entry.getDoubleValue());
                break;
            case 5:
                sb.append('\"');
                sb.append(entry.getStringValue());
                sb.append('\"');
                break;
            case 6:
                sb.append(entry.getMapBufferValue().toString());
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return sb;
    }

    public Iterator<MapBuffer.Entry> iterator() {
        return new ReadableMapBuffer$iterator$1(this);
    }

    private final class MapBufferEntry implements MapBuffer.Entry {
        private final int bucketOffset;

        public MapBufferEntry(int i) {
            this.bucketOffset = i;
        }

        private final void assertType(MapBuffer.DataType dataType) {
            MapBuffer.DataType type = getType();
            if (dataType != type) {
                int key = getKey();
                throw new IllegalStateException(("Expected " + dataType + " for key: " + key + " found " + type + " instead.").toString());
            }
        }

        public int getKey() {
            return ReadableMapBuffer.this.m266readUnsignedShortBwKQO78(this.bucketOffset) & 65535;
        }

        public MapBuffer.DataType getType() {
            return MapBuffer.DataType.values()[ReadableMapBuffer.this.m266readUnsignedShortBwKQO78(this.bucketOffset + 2) & 65535];
        }

        public double getDoubleValue() {
            assertType(MapBuffer.DataType.DOUBLE);
            return ReadableMapBuffer.this.readDoubleValue(this.bucketOffset + 4);
        }

        public int getIntValue() {
            assertType(MapBuffer.DataType.INT);
            return ReadableMapBuffer.this.readIntValue(this.bucketOffset + 4);
        }

        public long getLongValue() {
            assertType(MapBuffer.DataType.LONG);
            return ReadableMapBuffer.this.readLongValue(this.bucketOffset + 4);
        }

        public boolean getBooleanValue() {
            assertType(MapBuffer.DataType.BOOL);
            return ReadableMapBuffer.this.readBooleanValue(this.bucketOffset + 4);
        }

        public String getStringValue() {
            assertType(MapBuffer.DataType.STRING);
            return ReadableMapBuffer.this.readStringValue(this.bucketOffset + 4);
        }

        public MapBuffer getMapBufferValue() {
            assertType(MapBuffer.DataType.MAP);
            return ReadableMapBuffer.this.readMapBufferValue(this.bucketOffset + 4);
        }
    }

    public static final class Companion {
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
