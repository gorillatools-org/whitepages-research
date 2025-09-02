package com.facebook.react.bridge;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class ReadableNativeMap$entryIterator$1$1$next$1 implements Map.Entry<String, Object>, KMappedMarker {
    final /* synthetic */ int $index;
    final /* synthetic */ String[] $iteratorKeys;
    final /* synthetic */ Object[] $iteratorValues;

    ReadableNativeMap$entryIterator$1$1$next$1(String[] strArr, int i, Object[] objArr) {
        this.$iteratorKeys = strArr;
        this.$index = i;
        this.$iteratorValues = objArr;
    }

    public String getKey() {
        return this.$iteratorKeys[this.$index];
    }

    public Object getValue() {
        return this.$iteratorValues[this.$index];
    }

    public Object setValue(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "newValue");
        throw new UnsupportedOperationException("Can't set a value while iterating over a ReadableNativeMap");
    }
}
