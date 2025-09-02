package com.google.firebase.perf.application;

import android.app.Activity;
import android.os.Build;
import android.util.SparseIntArray;
import androidx.core.app.FrameMetricsAggregator;
import androidx.fragment.app.Fragment;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.metrics.FrameMetricsCalculator;
import com.google.firebase.perf.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class FrameMetricsRecorder {
    private static final String FRAME_METRICS_AGGREGATOR_CLASSNAME = "androidx.core.app.FrameMetricsAggregator";
    private static final AndroidLogger logger = AndroidLogger.getInstance();
    private final Activity activity;
    private final Map<Fragment, FrameMetricsCalculator.PerfFrameMetrics> fragmentSnapshotMap;
    private final FrameMetricsAggregator frameMetricsAggregator;
    private boolean isRecording;

    static boolean isFrameMetricsRecordingSupported() {
        return true;
    }

    public FrameMetricsRecorder(Activity activity2) {
        this(activity2, new FrameMetricsAggregator(), new HashMap());
    }

    FrameMetricsRecorder(Activity activity2, FrameMetricsAggregator frameMetricsAggregator2, Map<Fragment, FrameMetricsCalculator.PerfFrameMetrics> map) {
        this.isRecording = false;
        this.activity = activity2;
        this.frameMetricsAggregator = frameMetricsAggregator2;
        this.fragmentSnapshotMap = map;
    }

    public void start() {
        if (this.isRecording) {
            logger.debug("FrameMetricsAggregator is already recording %s", this.activity.getClass().getSimpleName());
            return;
        }
        this.frameMetricsAggregator.add(this.activity);
        this.isRecording = true;
    }

    public Optional<FrameMetricsCalculator.PerfFrameMetrics> stop() {
        if (!this.isRecording) {
            logger.debug("Cannot stop because no recording was started");
            return Optional.absent();
        }
        if (!this.fragmentSnapshotMap.isEmpty()) {
            logger.debug("Sub-recordings are still ongoing! Sub-recordings should be stopped first before stopping Activity screen trace.");
            this.fragmentSnapshotMap.clear();
        }
        Optional<FrameMetricsCalculator.PerfFrameMetrics> snapshot = snapshot();
        try {
            this.frameMetricsAggregator.remove(this.activity);
        } catch (IllegalArgumentException | NullPointerException e) {
            if (!(e instanceof NullPointerException) || Build.VERSION.SDK_INT <= 28) {
                logger.warn("View not hardware accelerated. Unable to collect FrameMetrics. %s", e.toString());
                snapshot = Optional.absent();
            } else {
                throw e;
            }
        }
        this.frameMetricsAggregator.reset();
        this.isRecording = false;
        return snapshot;
    }

    public void startFragment(Fragment fragment) {
        if (!this.isRecording) {
            logger.debug("Cannot start sub-recording because FrameMetricsAggregator is not recording");
        } else if (this.fragmentSnapshotMap.containsKey(fragment)) {
            logger.debug("Cannot start sub-recording because one is already ongoing with the key %s", fragment.getClass().getSimpleName());
        } else {
            Optional<FrameMetricsCalculator.PerfFrameMetrics> snapshot = snapshot();
            if (!snapshot.isAvailable()) {
                logger.debug("startFragment(%s): snapshot() failed", fragment.getClass().getSimpleName());
            } else {
                this.fragmentSnapshotMap.put(fragment, snapshot.get());
            }
        }
    }

    public Optional<FrameMetricsCalculator.PerfFrameMetrics> stopFragment(Fragment fragment) {
        if (!this.isRecording) {
            logger.debug("Cannot stop sub-recording because FrameMetricsAggregator is not recording");
            return Optional.absent();
        } else if (!this.fragmentSnapshotMap.containsKey(fragment)) {
            logger.debug("Sub-recording associated with key %s was not started or does not exist", fragment.getClass().getSimpleName());
            return Optional.absent();
        } else {
            FrameMetricsCalculator.PerfFrameMetrics remove = this.fragmentSnapshotMap.remove(fragment);
            Optional<FrameMetricsCalculator.PerfFrameMetrics> snapshot = snapshot();
            if (snapshot.isAvailable()) {
                return Optional.of(snapshot.get().deltaFrameMetricsFromSnapshot(remove));
            }
            logger.debug("stopFragment(%s): snapshot() failed", fragment.getClass().getSimpleName());
            return Optional.absent();
        }
    }

    private Optional<FrameMetricsCalculator.PerfFrameMetrics> snapshot() {
        if (!this.isRecording) {
            logger.debug("No recording has been started.");
            return Optional.absent();
        }
        SparseIntArray[] metrics = this.frameMetricsAggregator.getMetrics();
        if (metrics == null) {
            logger.debug("FrameMetricsAggregator.mMetrics is uninitialized.");
            return Optional.absent();
        } else if (metrics[0] != null) {
            return Optional.of(FrameMetricsCalculator.calculateFrameMetrics(metrics));
        } else {
            logger.debug("FrameMetricsAggregator.mMetrics[TOTAL_INDEX] is uninitialized.");
            return Optional.absent();
        }
    }
}
