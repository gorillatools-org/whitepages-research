package com.facebook.react.bridge;

import android.os.SystemClock;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

@DoNotStrip
public class ReactMarker {
    private static final List<FabricMarkerListener> sFabricMarkerListeners = new CopyOnWriteArrayList();
    private static final List<MarkerListener> sListeners = new CopyOnWriteArrayList();
    private static Queue<ReactMarkerRecord> sNativeReactMarkerQueue = new ConcurrentLinkedQueue();

    public interface MarkerListener {
        void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i);
    }

    @DoNotStrip
    private static native void nativeLogMarker(String str, long j);

    private static class ReactMarkerRecord {
        private final String mMarkerName;
        private final long mMarkerTime;

        public ReactMarkerRecord(String str, long j) {
            this.mMarkerName = str;
            this.mMarkerTime = j;
        }

        public String getMarkerName() {
            return this.mMarkerName;
        }

        public long getMarkerTime() {
            return this.mMarkerTime;
        }
    }

    public interface FabricMarkerListener {
        void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i, long j);

        void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i, long j, int i2) {
            logFabricMarker(reactMarkerConstants, str, i, j);
        }
    }

    @DoNotStrip
    public static void addListener(MarkerListener markerListener) {
        List<MarkerListener> list = sListeners;
        if (!list.contains(markerListener)) {
            list.add(markerListener);
        }
    }

    @DoNotStrip
    public static void removeListener(MarkerListener markerListener) {
        sListeners.remove(markerListener);
    }

    @DoNotStrip
    public static void clearMarkerListeners() {
        sListeners.clear();
    }

    @DoNotStrip
    public static void addFabricListener(FabricMarkerListener fabricMarkerListener) {
        List<FabricMarkerListener> list = sFabricMarkerListeners;
        if (!list.contains(fabricMarkerListener)) {
            list.add(fabricMarkerListener);
        }
    }

    @DoNotStrip
    public static void removeFabricListener(FabricMarkerListener fabricMarkerListener) {
        sFabricMarkerListeners.remove(fabricMarkerListener);
    }

    @DoNotStrip
    public static void clearFabricMarkerListeners() {
        sFabricMarkerListeners.clear();
    }

    @DoNotStrip
    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i, long j, int i2) {
        for (FabricMarkerListener logFabricMarker : sFabricMarkerListeners) {
            logFabricMarker.logFabricMarker(reactMarkerConstants, str, i, j, i2);
        }
    }

    @DoNotStrip
    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i, long j) {
        for (FabricMarkerListener logFabricMarker : sFabricMarkerListeners) {
            logFabricMarker.logFabricMarker(reactMarkerConstants, str, i, j, 0);
        }
    }

    @DoNotStrip
    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i) {
        logFabricMarker(reactMarkerConstants, str, i, SystemClock.uptimeMillis(), 0);
    }

    @DoNotStrip
    public static void logMarker(String str) {
        logMarker(str, (String) null);
    }

    @DoNotStrip
    public static void logMarker(String str, int i) {
        logMarker(str, (String) null, i);
    }

    @DoNotStrip
    public static void logMarker(String str, String str2) {
        logMarker(str, str2, 0);
    }

    @DoNotStrip
    public static void logMarker(String str, String str2, int i) {
        logMarker(ReactMarkerConstants.valueOf(str), str2, i);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants) {
        logMarker(reactMarkerConstants, (String) null, 0);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, int i) {
        logMarker(reactMarkerConstants, (String) null, i);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str) {
        logMarker(reactMarkerConstants, str, 0);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, long j) {
        logMarker(reactMarkerConstants, (String) null, 0, Long.valueOf(j));
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i) {
        logMarker(reactMarkerConstants, str, i, (Long) null);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i, Long l) {
        logFabricMarker(reactMarkerConstants, str, i);
        for (MarkerListener logMarker : sListeners) {
            logMarker.logMarker(reactMarkerConstants, str, i);
        }
        notifyNativeMarker(reactMarkerConstants, l);
    }

    @DoNotStrip
    private static void notifyNativeMarker(ReactMarkerConstants reactMarkerConstants, Long l) {
        if (reactMarkerConstants.hasMatchingNameMarker()) {
            if (l == null) {
                l = Long.valueOf(SystemClock.uptimeMillis());
            }
            if (ReactBridge.isInitialized()) {
                nativeLogMarker(reactMarkerConstants.name(), l.longValue());
                while (true) {
                    ReactMarkerRecord poll = sNativeReactMarkerQueue.poll();
                    if (poll != null) {
                        nativeLogMarker(poll.getMarkerName(), poll.getMarkerTime());
                    } else {
                        return;
                    }
                }
            } else {
                sNativeReactMarkerQueue.add(new ReactMarkerRecord(reactMarkerConstants.name(), l.longValue()));
            }
        }
    }
}
