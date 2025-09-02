package com.google.firebase.perf.metrics;

import android.util.SparseIntArray;

public class FrameMetricsCalculator {

    public static class PerfFrameMetrics {
        int frozenFrames;
        int slowFrames;
        int totalFrames;

        public PerfFrameMetrics(int i, int i2, int i3) {
            this.totalFrames = i;
            this.slowFrames = i2;
            this.frozenFrames = i3;
        }

        public int getFrozenFrames() {
            return this.frozenFrames;
        }

        public int getSlowFrames() {
            return this.slowFrames;
        }

        public int getTotalFrames() {
            return this.totalFrames;
        }

        public PerfFrameMetrics deltaFrameMetricsFromSnapshot(PerfFrameMetrics perfFrameMetrics) {
            return new PerfFrameMetrics(this.totalFrames - perfFrameMetrics.getTotalFrames(), this.slowFrames - perfFrameMetrics.getSlowFrames(), this.frozenFrames - perfFrameMetrics.getFrozenFrames());
        }
    }

    public static PerfFrameMetrics calculateFrameMetrics(SparseIntArray[] sparseIntArrayArr) {
        int i;
        int i2;
        SparseIntArray sparseIntArray;
        int i3 = 0;
        if (sparseIntArrayArr == null || (sparseIntArray = sparseIntArrayArr[0]) == null) {
            i2 = 0;
            i = 0;
        } else {
            int i4 = 0;
            i2 = 0;
            i = 0;
            while (i3 < sparseIntArray.size()) {
                int keyAt = sparseIntArray.keyAt(i3);
                int valueAt = sparseIntArray.valueAt(i3);
                i4 += valueAt;
                if (keyAt > 700) {
                    i += valueAt;
                }
                if (keyAt > 16) {
                    i2 += valueAt;
                }
                i3++;
            }
            i3 = i4;
        }
        return new PerfFrameMetrics(i3, i2, i);
    }
}
