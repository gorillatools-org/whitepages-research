package com.facebook.react.modules.debug;

import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import java.util.ArrayList;

public final class DidJSUpdateUiDuringFrameDetector implements NotThreadSafeBridgeIdleDebugListener, NotThreadSafeViewHierarchyUpdateDebugListener {
    private final ArrayList<Long> transitionToBusyEvents = new ArrayList<>(20);
    private final ArrayList<Long> transitionToIdleEvents = new ArrayList<>(20);
    private final ArrayList<Long> viewHierarchyUpdateEnqueuedEvents = new ArrayList<>(20);
    private final ArrayList<Long> viewHierarchyUpdateFinishedEvents = new ArrayList<>(20);
    private volatile boolean wasIdleAtEndOfLastFrame = true;

    public synchronized void onTransitionToBridgeIdle() {
        this.transitionToIdleEvents.add(Long.valueOf(System.nanoTime()));
    }

    public synchronized void onTransitionToBridgeBusy() {
        this.transitionToBusyEvents.add(Long.valueOf(System.nanoTime()));
    }

    public synchronized void onBridgeDestroyed() {
    }

    public synchronized void onViewHierarchyUpdateEnqueued() {
        this.viewHierarchyUpdateEnqueuedEvents.add(Long.valueOf(System.nanoTime()));
    }

    public synchronized void onViewHierarchyUpdateFinished() {
        this.viewHierarchyUpdateFinishedEvents.add(Long.valueOf(System.nanoTime()));
    }

    public final synchronized boolean getDidJSHitFrameAndCleanup(long j, long j2) {
        boolean z;
        try {
            boolean access$hasEventBetweenTimestamps = DidJSUpdateUiDuringFrameDetectorKt.hasEventBetweenTimestamps(this.viewHierarchyUpdateFinishedEvents, j, j2);
            boolean didEndFrameIdle = didEndFrameIdle(j, j2);
            z = true;
            if (!access$hasEventBetweenTimestamps) {
                if (!didEndFrameIdle || DidJSUpdateUiDuringFrameDetectorKt.hasEventBetweenTimestamps(this.viewHierarchyUpdateEnqueuedEvents, j, j2)) {
                    z = false;
                }
            }
            DidJSUpdateUiDuringFrameDetectorKt.cleanUp(this.transitionToIdleEvents, j2);
            DidJSUpdateUiDuringFrameDetectorKt.cleanUp(this.transitionToBusyEvents, j2);
            DidJSUpdateUiDuringFrameDetectorKt.cleanUp(this.viewHierarchyUpdateEnqueuedEvents, j2);
            DidJSUpdateUiDuringFrameDetectorKt.cleanUp(this.viewHierarchyUpdateFinishedEvents, j2);
            this.wasIdleAtEndOfLastFrame = didEndFrameIdle;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return z;
    }

    private final boolean didEndFrameIdle(long j, long j2) {
        long access$getLastEventBetweenTimestamps = DidJSUpdateUiDuringFrameDetectorKt.getLastEventBetweenTimestamps(this.transitionToIdleEvents, j, j2);
        long access$getLastEventBetweenTimestamps2 = DidJSUpdateUiDuringFrameDetectorKt.getLastEventBetweenTimestamps(this.transitionToBusyEvents, j, j2);
        if (access$getLastEventBetweenTimestamps == -1 && access$getLastEventBetweenTimestamps2 == -1) {
            return this.wasIdleAtEndOfLastFrame;
        }
        return access$getLastEventBetweenTimestamps > access$getLastEventBetweenTimestamps2;
    }
}
