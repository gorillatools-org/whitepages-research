package com.facebook.react.uimanager.events;

import android.util.SparseIntArray;
import okhttp3.internal.http2.Settings;

public final class TouchEventCoalescingKeyHelper {
    private final SparseIntArray downTimeToCoalescingKey = new SparseIntArray();

    public final void addCoalescingKey(long j) {
        this.downTimeToCoalescingKey.put((int) j, 0);
    }

    public final void incrementCoalescingKey(long j) {
        int i = (int) j;
        int i2 = this.downTimeToCoalescingKey.get(i, -1);
        if (i2 != -1) {
            this.downTimeToCoalescingKey.put(i, i2 + 1);
            return;
        }
        throw new RuntimeException("Tried to increment non-existent cookie");
    }

    public final short getCoalescingKey(long j) {
        int i = this.downTimeToCoalescingKey.get((int) j, -1);
        if (i != -1) {
            return (short) (i & Settings.DEFAULT_INITIAL_WINDOW_SIZE);
        }
        throw new RuntimeException("Tried to get non-existent cookie");
    }

    public final void removeCoalescingKey(long j) {
        this.downTimeToCoalescingKey.delete((int) j);
    }

    public final boolean hasCoalescingKey(long j) {
        return this.downTimeToCoalescingKey.get((int) j, -1) != -1;
    }
}
