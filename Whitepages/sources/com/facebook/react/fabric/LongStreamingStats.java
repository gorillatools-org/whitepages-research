package com.facebook.react.fabric;

import java.util.PriorityQueue;
import java.util.Queue;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

public final class LongStreamingStats {
    private double average;
    private int len;
    private long max;
    private final Queue<Long> maxHeap = new PriorityQueue(11, ComparisonsKt.reverseOrder());
    private final Queue<Long> minHeap = new PriorityQueue(11);

    public final double getAverage() {
        return this.average;
    }

    public final long getMax() {
        return this.max;
    }

    public final void add(long j) {
        if (j != 0) {
            if (this.minHeap.size() == this.maxHeap.size()) {
                this.maxHeap.offer(Long.valueOf(j));
                this.minHeap.offer(this.maxHeap.poll());
            } else {
                this.minHeap.offer(Long.valueOf(j));
                this.maxHeap.offer(this.minHeap.poll());
            }
        }
        int i = this.len;
        int i2 = i + 1;
        this.len = i2;
        if (i2 == 1) {
            this.average = (double) j;
        } else {
            this.average = (this.average / ((double) (i2 / i))) + ((double) (j / ((long) i2)));
        }
        long j2 = this.max;
        if (j <= j2) {
            j = j2;
        }
        this.max = j;
    }

    public final double getMedian() {
        Long l;
        long j;
        if (this.minHeap.size() == 0 && this.maxHeap.size() == 0) {
            return 0.0d;
        }
        if (this.minHeap.size() > this.maxHeap.size()) {
            l = this.minHeap.peek();
        } else {
            Long peek = this.minHeap.peek();
            if (peek != null) {
                j = peek.longValue();
            } else {
                Long peek2 = this.maxHeap.peek();
                Intrinsics.checkNotNull(peek2);
                j = peek2.longValue();
            }
            l = Long.valueOf(j / ((long) 2));
        }
        return (double) l.longValue();
    }
}
