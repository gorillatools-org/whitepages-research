package com.facebook.react.animated;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.build.ReactBuildConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FrameBasedAnimationDriver extends AnimationDriver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final double FRAME_TIME_MILLIS = 16.666666666666668d;
    private int currentLoop = 1;
    private double[] frames = new double[0];
    private double fromValue;
    private int iterations = 1;
    private int logCount;
    private long startFrameTimeNanos = -1;
    private double toValue;

    public FrameBasedAnimationDriver(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        resetConfig(readableMap);
    }

    public void resetConfig(ReadableMap readableMap) {
        int size;
        Intrinsics.checkNotNullParameter(readableMap, "config");
        ReadableArray array = readableMap.getArray("frames");
        boolean z = false;
        if (!(array == null || this.frames.length == (size = array.size()))) {
            double[] dArr = new double[size];
            for (int i = 0; i < size; i++) {
                dArr[i] = array.getDouble(i);
            }
            this.frames = dArr;
        }
        this.toValue = (!readableMap.hasKey("toValue") || readableMap.getType("toValue") != ReadableType.Number) ? 0.0d : readableMap.getDouble("toValue");
        int i2 = (!readableMap.hasKey("iterations") || readableMap.getType("iterations") != ReadableType.Number) ? 1 : readableMap.getInt("iterations");
        this.iterations = i2;
        this.currentLoop = 1;
        if (i2 == 0) {
            z = true;
        }
        this.hasFinished = z;
        this.startFrameTimeNanos = -1;
    }

    public void runAnimationStep(long j) {
        double d;
        ValueAnimatedNode valueAnimatedNode = this.animatedValue;
        if (valueAnimatedNode != null) {
            if (this.startFrameTimeNanos < 0) {
                this.startFrameTimeNanos = j;
                if (this.currentLoop == 1) {
                    this.fromValue = valueAnimatedNode.nodeValue;
                }
            }
            int round = (int) Math.round(((double) ((j - this.startFrameTimeNanos) / ((long) 1000000))) / FRAME_TIME_MILLIS);
            if (round < 0) {
                String str = "Calculated frame index should never be lower than 0. Called with frameTimeNanos " + j + " and mStartFrameTimeNanos " + this.startFrameTimeNanos;
                if (ReactBuildConfig.DEBUG) {
                    throw new IllegalStateException(str.toString());
                } else if (this.logCount < 100) {
                    FLog.w(ReactConstants.TAG, str);
                    this.logCount++;
                }
            } else if (!this.hasFinished) {
                double[] dArr = this.frames;
                if (round >= dArr.length - 1) {
                    int i = this.iterations;
                    if (i == -1 || this.currentLoop < i) {
                        double d2 = this.fromValue;
                        d = d2 + (dArr[dArr.length - 1] * (this.toValue - d2));
                        this.startFrameTimeNanos = -1;
                        this.currentLoop++;
                    } else {
                        d = this.toValue;
                        this.hasFinished = true;
                    }
                } else {
                    double d3 = this.fromValue;
                    d = d3 + (dArr[round] * (this.toValue - d3));
                }
                valueAnimatedNode.nodeValue = d;
            }
        } else {
            throw new IllegalArgumentException("Animated value should not be null");
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
