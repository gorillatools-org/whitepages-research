package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SpringAnimation extends AnimationDriver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final double MAX_DELTA_TIME_SEC = 0.064d;
    private int currentLoop;
    private final PhysicsState currentState;
    private double displacementFromRestThreshold;
    private double endValue;
    private double initialVelocity;
    private int iterations;
    private long lastTime;
    private double originalValue;
    private boolean overshootClampingEnabled;
    private double restSpeedThreshold;
    private double springDamping;
    private double springMass;
    private boolean springStarted;
    private double springStiffness;
    private double startValue;
    private double timeAccumulator;

    public SpringAnimation(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        PhysicsState physicsState = new PhysicsState(0.0d, 0.0d, 3, (DefaultConstructorMarker) null);
        this.currentState = physicsState;
        physicsState.setVelocity(readableMap.getDouble("initialVelocity"));
        resetConfig(readableMap);
    }

    private static final class PhysicsState {
        private double position;
        private double velocity;

        public PhysicsState() {
            this(0.0d, 0.0d, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ PhysicsState copy$default(PhysicsState physicsState, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                d = physicsState.position;
            }
            if ((i & 2) != 0) {
                d2 = physicsState.velocity;
            }
            return physicsState.copy(d, d2);
        }

        public final double component1() {
            return this.position;
        }

        public final double component2() {
            return this.velocity;
        }

        public final PhysicsState copy(double d, double d2) {
            return new PhysicsState(d, d2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PhysicsState)) {
                return false;
            }
            PhysicsState physicsState = (PhysicsState) obj;
            return Double.compare(this.position, physicsState.position) == 0 && Double.compare(this.velocity, physicsState.velocity) == 0;
        }

        public int hashCode() {
            return (Double.hashCode(this.position) * 31) + Double.hashCode(this.velocity);
        }

        public String toString() {
            double d = this.position;
            double d2 = this.velocity;
            return "PhysicsState(position=" + d + ", velocity=" + d2 + ")";
        }

        public PhysicsState(double d, double d2) {
            this.position = d;
            this.velocity = d2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ PhysicsState(double d, double d2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0.0d : d2);
        }

        public final double getPosition() {
            return this.position;
        }

        public final double getVelocity() {
            return this.velocity;
        }

        public final void setPosition(double d) {
            this.position = d;
        }

        public final void setVelocity(double d) {
            this.velocity = d;
        }
    }

    public void resetConfig(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        this.springStiffness = readableMap.getDouble("stiffness");
        this.springDamping = readableMap.getDouble("damping");
        this.springMass = readableMap.getDouble("mass");
        this.initialVelocity = this.currentState.getVelocity();
        this.endValue = readableMap.getDouble("toValue");
        this.restSpeedThreshold = readableMap.getDouble("restSpeedThreshold");
        this.displacementFromRestThreshold = readableMap.getDouble("restDisplacementThreshold");
        this.overshootClampingEnabled = readableMap.getBoolean("overshootClamping");
        boolean z = true;
        int i = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.iterations = i;
        if (i != 0) {
            z = false;
        }
        this.hasFinished = z;
        this.currentLoop = 0;
        this.timeAccumulator = 0.0d;
        this.springStarted = false;
    }

    public void runAnimationStep(long j) {
        ValueAnimatedNode valueAnimatedNode = this.animatedValue;
        if (valueAnimatedNode != null) {
            long j2 = j / ((long) 1000000);
            if (!this.springStarted) {
                if (this.currentLoop == 0) {
                    this.originalValue = valueAnimatedNode.nodeValue;
                    this.currentLoop = 1;
                }
                this.currentState.setPosition(valueAnimatedNode.nodeValue);
                this.startValue = this.currentState.getPosition();
                this.lastTime = j2;
                this.timeAccumulator = 0.0d;
                this.springStarted = true;
            }
            advance(((double) (j2 - this.lastTime)) / 1000.0d);
            this.lastTime = j2;
            valueAnimatedNode.nodeValue = this.currentState.getPosition();
            if (isAtRest()) {
                int i = this.iterations;
                if (i == -1 || this.currentLoop < i) {
                    this.springStarted = false;
                    valueAnimatedNode.nodeValue = this.originalValue;
                    this.currentLoop++;
                    return;
                }
                this.hasFinished = true;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Animated value should not be null");
    }

    private final double getDisplacementDistanceForState(PhysicsState physicsState) {
        return Math.abs(this.endValue - physicsState.getPosition());
    }

    private final boolean isAtRest() {
        return Math.abs(this.currentState.getVelocity()) <= this.restSpeedThreshold && (getDisplacementDistanceForState(this.currentState) <= this.displacementFromRestThreshold || this.springStiffness == 0.0d);
    }

    private final boolean isOvershooting() {
        return this.springStiffness > 0.0d && ((this.startValue < this.endValue && this.currentState.getPosition() > this.endValue) || (this.startValue > this.endValue && this.currentState.getPosition() < this.endValue));
    }

    private final void advance(double d) {
        double d2;
        double d3;
        if (!isAtRest()) {
            double d4 = MAX_DELTA_TIME_SEC;
            if (d <= MAX_DELTA_TIME_SEC) {
                d4 = d;
            }
            this.timeAccumulator += d4;
            double d5 = this.springDamping;
            double d6 = this.springMass;
            double d7 = this.springStiffness;
            double d8 = -this.initialVelocity;
            double sqrt = d5 / (((double) 2) * Math.sqrt(d7 * d6));
            double sqrt2 = Math.sqrt(d7 / d6);
            double sqrt3 = Math.sqrt(1.0d - (sqrt * sqrt)) * sqrt2;
            double d9 = this.endValue - this.startValue;
            double d10 = this.timeAccumulator;
            if (sqrt < 1.0d) {
                double exp = Math.exp((-sqrt) * sqrt2 * d10);
                double d11 = sqrt * sqrt2;
                double d12 = d8 + (d11 * d9);
                double d13 = d10 * sqrt3;
                double d14 = exp;
                d2 = this.endValue - ((((d12 / sqrt3) * Math.sin(d13)) + (Math.cos(d13) * d9)) * d14);
                d3 = ((d11 * d14) * (((Math.sin(d13) * d12) / sqrt3) + (Math.cos(d13) * d9))) - (((Math.cos(d13) * d12) - ((sqrt3 * d9) * Math.sin(d13))) * d14);
            } else {
                double exp2 = Math.exp((-sqrt2) * d10);
                d3 = exp2 * ((d8 * ((d10 * sqrt2) - ((double) 1))) + (d10 * d9 * sqrt2 * sqrt2));
                d2 = this.endValue - (((((sqrt2 * d9) + d8) * d10) + d9) * exp2);
            }
            this.currentState.setPosition(d2);
            this.currentState.setVelocity(d3);
            if (isAtRest() || (this.overshootClampingEnabled && isOvershooting())) {
                if (this.springStiffness > 0.0d) {
                    double d15 = this.endValue;
                    this.startValue = d15;
                    this.currentState.setPosition(d15);
                } else {
                    double position = this.currentState.getPosition();
                    this.endValue = position;
                    this.startValue = position;
                }
                this.currentState.setVelocity(0.0d);
            }
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
