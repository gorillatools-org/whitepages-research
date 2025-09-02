package com.facebook.react.modules.debug;

import android.view.Choreographer;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import java.util.Map;
import java.util.TreeMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FpsDebugFrameCallback implements Choreographer.FrameCallback {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final double DEFAULT_FPS = 60.0d;
    private Choreographer choreographer;
    private final DidJSUpdateUiDuringFrameDetector didJSUpdateUiDuringFrameDetector = new DidJSUpdateUiDuringFrameDetector();
    private int expectedNumFramesPrev;
    private long firstFrameTime = -1;
    private int fourPlusFrameStutters;
    private boolean isRecordingFpsInfoAtEachFrame;
    private long lastFrameTime = -1;
    private int numFrameCallbacks;
    private int numFrameCallbacksWithBatchDispatches;
    private final ReactContext reactContext;
    private double targetFps = DEFAULT_FPS;
    private TreeMap<Long, FpsInfo> timeToFps;
    private final UIManagerModule uiManagerModule;

    public final void start() {
        start$default(this, 0.0d, 1, (Object) null);
    }

    public FpsDebugFrameCallback(ReactContext reactContext2) {
        Intrinsics.checkNotNullParameter(reactContext2, "reactContext");
        this.reactContext = reactContext2;
        this.uiManagerModule = (UIManagerModule) reactContext2.getNativeModule(UIManagerModule.class);
    }

    public static final class FpsInfo {
        private final double fps;
        private final double jsFps;
        private final int total4PlusFrameStutters;
        private final int totalExpectedFrames;
        private final int totalFrames;
        private final int totalJsFrames;
        private final int totalTimeMs;

        public FpsInfo(int i, int i2, int i3, int i4, double d, double d2, int i5) {
            this.totalFrames = i;
            this.totalJsFrames = i2;
            this.totalExpectedFrames = i3;
            this.total4PlusFrameStutters = i4;
            this.fps = d;
            this.jsFps = d2;
            this.totalTimeMs = i5;
        }

        public final int getTotalFrames() {
            return this.totalFrames;
        }

        public final int getTotalJsFrames() {
            return this.totalJsFrames;
        }

        public final int getTotalExpectedFrames() {
            return this.totalExpectedFrames;
        }

        public final int getTotal4PlusFrameStutters() {
            return this.total4PlusFrameStutters;
        }

        public final double getFps() {
            return this.fps;
        }

        public final double getJsFps() {
            return this.jsFps;
        }

        public final int getTotalTimeMs() {
            return this.totalTimeMs;
        }
    }

    public void doFrame(long j) {
        if (this.firstFrameTime == -1) {
            this.firstFrameTime = j;
        }
        long j2 = this.lastFrameTime;
        this.lastFrameTime = j;
        if (this.didJSUpdateUiDuringFrameDetector.getDidJSHitFrameAndCleanup(j2, j)) {
            this.numFrameCallbacksWithBatchDispatches++;
        }
        this.numFrameCallbacks++;
        int expectedNumFrames = getExpectedNumFrames();
        if ((expectedNumFrames - this.expectedNumFramesPrev) - 1 >= 4) {
            this.fourPlusFrameStutters++;
        }
        if (this.isRecordingFpsInfoAtEachFrame) {
            Assertions.assertNotNull(this.timeToFps);
            FpsInfo fpsInfo = new FpsInfo(getNumFrames(), getNumJSFrames(), expectedNumFrames, this.fourPlusFrameStutters, getFps(), getJsFPS(), getTotalTimeMS());
            TreeMap<Long, FpsInfo> treeMap = this.timeToFps;
            if (treeMap != null) {
                FpsInfo put = treeMap.put(Long.valueOf(System.currentTimeMillis()), fpsInfo);
            }
        }
        this.expectedNumFramesPrev = expectedNumFrames;
        Choreographer choreographer2 = this.choreographer;
        if (choreographer2 != null) {
            choreographer2.postFrameCallback(this);
        }
    }

    public static /* synthetic */ void start$default(FpsDebugFrameCallback fpsDebugFrameCallback, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            d = fpsDebugFrameCallback.targetFps;
        }
        fpsDebugFrameCallback.start(d);
    }

    public final void start(double d) {
        if (!this.reactContext.isBridgeless()) {
            this.reactContext.getCatalystInstance().addBridgeIdleDebugListener(this.didJSUpdateUiDuringFrameDetector);
        }
        UIManagerModule uIManagerModule = this.uiManagerModule;
        if (uIManagerModule != null) {
            uIManagerModule.setViewHierarchyUpdateDebugListener(this.didJSUpdateUiDuringFrameDetector);
        }
        this.targetFps = d;
        UiThreadUtil.runOnUiThread(new FpsDebugFrameCallback$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void start$lambda$0(FpsDebugFrameCallback fpsDebugFrameCallback) {
        Choreographer instance = Choreographer.getInstance();
        fpsDebugFrameCallback.choreographer = instance;
        if (instance != null) {
            instance.postFrameCallback(fpsDebugFrameCallback);
        }
    }

    public final void startAndRecordFpsAtEachFrame() {
        this.timeToFps = new TreeMap<>();
        this.isRecordingFpsInfoAtEachFrame = true;
        start$default(this, 0.0d, 1, (Object) null);
    }

    public final void stop() {
        if (!this.reactContext.isBridgeless()) {
            this.reactContext.getCatalystInstance().removeBridgeIdleDebugListener(this.didJSUpdateUiDuringFrameDetector);
        }
        UIManagerModule uIManagerModule = this.uiManagerModule;
        if (uIManagerModule != null) {
            uIManagerModule.setViewHierarchyUpdateDebugListener((NotThreadSafeViewHierarchyUpdateDebugListener) null);
        }
        UiThreadUtil.runOnUiThread(new FpsDebugFrameCallback$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public static final void stop$lambda$1(FpsDebugFrameCallback fpsDebugFrameCallback) {
        Choreographer instance = Choreographer.getInstance();
        fpsDebugFrameCallback.choreographer = instance;
        if (instance != null) {
            instance.removeFrameCallback(fpsDebugFrameCallback);
        }
    }

    public final double getFps() {
        if (this.lastFrameTime == this.firstFrameTime) {
            return 0.0d;
        }
        return (((double) getNumFrames()) * 1.0E9d) / ((double) (this.lastFrameTime - this.firstFrameTime));
    }

    public final double getJsFPS() {
        if (this.lastFrameTime == this.firstFrameTime) {
            return 0.0d;
        }
        return (((double) getNumJSFrames()) * 1.0E9d) / ((double) (this.lastFrameTime - this.firstFrameTime));
    }

    public final int getNumFrames() {
        return this.numFrameCallbacks - 1;
    }

    public final int getNumJSFrames() {
        return this.numFrameCallbacksWithBatchDispatches - 1;
    }

    public final int getExpectedNumFrames() {
        return (int) (((this.targetFps * ((double) getTotalTimeMS())) / ((double) 1000)) + ((double) 1));
    }

    public final int get4PlusFrameStutters() {
        return this.fourPlusFrameStutters;
    }

    public final int getTotalTimeMS() {
        return (int) ((((double) this.lastFrameTime) - ((double) this.firstFrameTime)) / 1000000.0d);
    }

    public final FpsInfo getFpsInfo(long j) {
        Map.Entry<Long, FpsInfo> floorEntry;
        Assertions.assertNotNull(this.timeToFps, "FPS was not recorded at each frame!");
        TreeMap<Long, FpsInfo> treeMap = this.timeToFps;
        if (treeMap == null || (floorEntry = treeMap.floorEntry(Long.valueOf(j))) == null) {
            return null;
        }
        return floorEntry.getValue();
    }

    public final void reset() {
        this.firstFrameTime = -1;
        this.lastFrameTime = -1;
        this.numFrameCallbacks = 0;
        this.fourPlusFrameStutters = 0;
        this.numFrameCallbacksWithBatchDispatches = 0;
        this.isRecordingFpsInfoAtEachFrame = false;
        this.timeToFps = null;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
