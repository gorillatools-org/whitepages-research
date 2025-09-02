package com.facebook.react.common.mapbuffer;

import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class ReadableMapBuffer$iterator$1 implements Iterator<MapBuffer.Entry>, KMappedMarker {
    private int current;
    private final int last;
    final /* synthetic */ ReadableMapBuffer this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    ReadableMapBuffer$iterator$1(ReadableMapBuffer readableMapBuffer) {
        this.this$0 = readableMapBuffer;
        this.last = readableMapBuffer.getCount() - 1;
    }

    public final int getCurrent() {
        return this.current;
    }

    public final void setCurrent(int i) {
        this.current = i;
    }

    public final int getLast() {
        return this.last;
    }

    public boolean hasNext() {
        return this.current <= this.last;
    }

    public MapBuffer.Entry next() {
        ReadableMapBuffer readableMapBuffer = this.this$0;
        int i = this.current;
        this.current = i + 1;
        return new ReadableMapBuffer.MapBufferEntry(readableMapBuffer.getKeyOffsetForBucketIndex(i));
    }
}
