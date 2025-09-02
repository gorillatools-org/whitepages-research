package io.invertase.firebase.perf;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.core.app.FrameMetricsAggregator;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;
import com.google.firebase.perf.util.Constants;

public class ScreenTrace {
    private static final String FRAME_METRICS_AGGREGATOR_CLASSNAME = "androidx.core.app.FrameMetricsAggregator";
    private static final String TAG = "RNFirebasePerf";
    private final Activity activity;
    private final FrameMetricsAggregator frameMetricsAggregator;
    private Trace perfScreenTrace;
    private final String traceName;

    private static boolean checkFrameMetricsAggregatorClass() {
        return true;
    }

    public ScreenTrace(Activity activity2, String str) throws IllegalStateException {
        this.activity = activity2;
        this.traceName = str;
        if (checkScreenTraceSupport(activity2)) {
            this.frameMetricsAggregator = new FrameMetricsAggregator();
            return;
        }
        throw new IllegalStateException("Device does not support screen traces. Hardware acceleration must be enabled and Android must not be 8.0 or 8.1.");
    }

    public void recordScreenTrace() {
        Log.d(TAG, "Recording screen trace " + this.traceName);
        this.frameMetricsAggregator.add(this.activity);
        this.perfScreenTrace = FirebasePerformance.startTrace(getScreenTraceName());
    }

    public void sendScreenTrace() {
        int i;
        int i2;
        SparseIntArray sparseIntArray;
        if (this.perfScreenTrace != null) {
            SparseIntArray[] reset = this.frameMetricsAggregator.reset();
            int i3 = 0;
            if (reset == null || (sparseIntArray = reset[0]) == null) {
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
            if (i3 > 0) {
                this.perfScreenTrace.putMetric(Constants.CounterNames.FRAMES_TOTAL.toString(), (long) i3);
            }
            if (i2 > 0) {
                this.perfScreenTrace.putMetric(Constants.CounterNames.FRAMES_SLOW.toString(), (long) i2);
            }
            if (i > 0) {
                this.perfScreenTrace.putMetric(Constants.CounterNames.FRAMES_FROZEN.toString(), (long) i);
            }
            Log.d(TAG, "sendScreenTrace " + this.traceName + ", name: " + getScreenTraceName() + ", total_frames: " + i3 + ", slow_frames: " + i2 + ", frozen_frames: " + i);
            this.perfScreenTrace.stop();
        }
    }

    private static boolean checkScreenTraceSupport(Activity activity2) {
        boolean checkSDKVersion = checkSDKVersion();
        boolean checkFrameMetricsAggregatorClass = checkFrameMetricsAggregatorClass();
        boolean z = false;
        boolean z2 = (activity2.getWindow() == null || (activity2.getWindow().getAttributes().flags & 16777216) == 0) ? false : true;
        if (checkSDKVersion && checkFrameMetricsAggregatorClass && z2) {
            z = true;
        }
        Log.d(TAG, "isValidSDKVersion: " + checkSDKVersion + "isScreenTraceSupported(" + activity2 + "): " + z + " [hasFrameMetricsAggregatorClass: " + checkFrameMetricsAggregatorClass + ", isActivityHardwareAccelerated: " + z2 + "]");
        return z;
    }

    private static boolean checkSDKVersion() {
        int i = Build.VERSION.SDK_INT;
        return (i == 26 || i == 27) ? false : true;
    }

    private String getScreenTraceName() {
        return Constants.SCREEN_TRACE_PREFIX + this.traceName;
    }
}
