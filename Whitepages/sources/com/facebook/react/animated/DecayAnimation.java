package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.Intrinsics;

public final class DecayAnimation extends AnimationDriver {
    private int currentLoop = 1;
    private double deceleration;
    private double fromValue;
    private int iterations = 1;
    private double lastValue;
    private long startFrameTimeMillis = -1;
    private double velocity;

    public DecayAnimation(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        resetConfig(readableMap);
    }

    public void resetConfig(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        this.velocity = readableMap.getDouble("velocity");
        this.deceleration = readableMap.getDouble("deceleration");
        this.startFrameTimeMillis = -1;
        this.fromValue = 0.0d;
        this.lastValue = 0.0d;
        boolean z = true;
        int i = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.iterations = i;
        this.currentLoop = 1;
        if (i != 0) {
            z = false;
        }
        this.hasFinished = z;
    }

    public void runAnimationStep(long j) {
        ValueAnimatedNode valueAnimatedNode = this.animatedValue;
        if (valueAnimatedNode != null) {
            long j2 = j / ((long) 1000000);
            if (this.startFrameTimeMillis == -1) {
                this.startFrameTimeMillis = j2 - ((long) 16);
                double d = this.fromValue;
                if (d == this.lastValue) {
                    this.fromValue = valueAnimatedNode.nodeValue;
                } else {
                    valueAnimatedNode.nodeValue = d;
                }
                this.lastValue = valueAnimatedNode.nodeValue;
            }
            double d2 = this.fromValue;
            double d3 = this.velocity;
            double d4 = (double) 1;
            double d5 = this.deceleration;
            double exp = d2 + ((d3 / (d4 - d5)) * (d4 - Math.exp((-(d4 - d5)) * ((double) (j2 - this.startFrameTimeMillis)))));
            if (Math.abs(this.lastValue - exp) < 0.1d) {
                int i = this.iterations;
                if (i == -1 || this.currentLoop < i) {
                    this.startFrameTimeMillis = -1;
                    this.currentLoop++;
                } else {
                    this.hasFinished = true;
                    return;
                }
            }
            this.lastValue = exp;
            valueAnimatedNode.nodeValue = exp;
            return;
        }
        throw new IllegalArgumentException("Animated value should not be null");
    }
}
