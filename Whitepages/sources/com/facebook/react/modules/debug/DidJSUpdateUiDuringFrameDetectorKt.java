package com.facebook.react.modules.debug;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class DidJSUpdateUiDuringFrameDetectorKt {
    /* access modifiers changed from: private */
    public static final long getLastEventBetweenTimestamps(ArrayList<Long> arrayList, long j, long j2) {
        Iterator<Long> it = arrayList.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        long j3 = -1;
        while (it.hasNext()) {
            Long next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            long longValue = next.longValue();
            if (j <= longValue && longValue < j2) {
                j3 = longValue;
            } else if (longValue >= j2) {
                break;
            }
        }
        return j3;
    }

    /* access modifiers changed from: private */
    public static final void cleanUp(ArrayList<Long> arrayList, long j) {
        int size = arrayList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (arrayList.get(i2).longValue() < j) {
                i++;
            }
        }
        if (i > 0) {
            int i3 = size - i;
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.set(i4, arrayList.get(i4 + i));
            }
            CollectionsKt.dropLast(arrayList, i);
        }
    }

    /* access modifiers changed from: private */
    public static final boolean hasEventBetweenTimestamps(ArrayList<Long> arrayList, long j, long j2) {
        if (arrayList != null && arrayList.isEmpty()) {
            return false;
        }
        for (Number longValue : arrayList) {
            long longValue2 = longValue.longValue();
            if (j <= longValue2 && longValue2 < j2) {
                return true;
            }
        }
        return false;
    }
}
