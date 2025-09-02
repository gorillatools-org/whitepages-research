package com.swmansion.gesturehandler.core;

import android.view.VelocityTracker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Vector {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_DOWN = new Vector(0.0d, 1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_LEFT = new Vector(-1.0d, 0.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_LEFT_DOWN = new Vector(-1.0d, 1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_LEFT_UP = new Vector(-1.0d, -1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_RIGHT = new Vector(1.0d, 0.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_RIGHT_DOWN = new Vector(1.0d, 1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_RIGHT_UP = new Vector(1.0d, -1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_UP = new Vector(0.0d, -1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_ZERO = new Vector(0.0d, 0.0d);
    private final double magnitude;
    private final double unitX;
    private final double unitY;
    private final double x;
    private final double y;

    public Vector(double d, double d2) {
        this.x = d;
        this.y = d2;
        double hypot = Math.hypot(d, d2);
        this.magnitude = hypot;
        boolean z = hypot > 0.1d;
        double d3 = 0.0d;
        this.unitX = z ? d / hypot : 0.0d;
        this.unitY = z ? d2 / hypot : d3;
    }

    public final double getMagnitude() {
        return this.magnitude;
    }

    private final double computeSimilarity(Vector vector) {
        return (this.unitX * vector.unitX) + (this.unitY * vector.unitY);
    }

    public final boolean isSimilar(Vector vector, double d) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return computeSimilarity(vector) > d;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Vector fromDirection(int i) {
            switch (i) {
                case 1:
                    return Vector.VECTOR_RIGHT;
                case 2:
                    return Vector.VECTOR_LEFT;
                case 4:
                    return Vector.VECTOR_UP;
                case 5:
                    return Vector.VECTOR_RIGHT_UP;
                case 6:
                    return Vector.VECTOR_LEFT_UP;
                case 8:
                    return Vector.VECTOR_DOWN;
                case 9:
                    return Vector.VECTOR_RIGHT_DOWN;
                case 10:
                    return Vector.VECTOR_LEFT_DOWN;
                default:
                    return Vector.VECTOR_ZERO;
            }
        }

        public final Vector fromVelocity(VelocityTracker velocityTracker) {
            Intrinsics.checkNotNullParameter(velocityTracker, "tracker");
            velocityTracker.computeCurrentVelocity(1000);
            return new Vector((double) velocityTracker.getXVelocity(), (double) velocityTracker.getYVelocity());
        }
    }
}
