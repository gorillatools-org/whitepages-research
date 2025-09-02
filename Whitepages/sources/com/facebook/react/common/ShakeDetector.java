package com.facebook.react.common;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ShakeDetector implements SensorEventListener {
    private float accelerationX;
    private float accelerationY;
    private float accelerationZ;
    private long lastShakeTimestamp;
    private long lastTimestamp;
    private final int minNumShakes;
    private int numShakes;
    private SensorManager sensorManager;
    private final ShakeListener shakeListener;

    public interface ShakeListener {
        void onShake();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ShakeDetector(ShakeListener shakeListener2) {
        this(shakeListener2, 0, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(shakeListener2, "shakeListener");
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    public ShakeDetector(ShakeListener shakeListener2, int i) {
        Intrinsics.checkNotNullParameter(shakeListener2, "shakeListener");
        this.shakeListener = shakeListener2;
        this.minNumShakes = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ShakeDetector(ShakeListener shakeListener2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(shakeListener2, (i2 & 2) != 0 ? 1 : i);
    }

    public final void start(SensorManager sensorManager2) {
        Intrinsics.checkNotNullParameter(sensorManager2, "manager");
        Sensor defaultSensor = sensorManager2.getDefaultSensor(1);
        if (defaultSensor != null) {
            this.sensorManager = sensorManager2;
            this.lastTimestamp = -1;
            sensorManager2.registerListener(this, defaultSensor, 2);
            this.lastShakeTimestamp = 0;
            reset();
        }
    }

    public final void stop() {
        SensorManager sensorManager2 = this.sensorManager;
        if (sensorManager2 != null) {
            sensorManager2.unregisterListener(this);
        }
        this.sensorManager = null;
    }

    private final void reset() {
        this.numShakes = 0;
        this.accelerationX = 0.0f;
        this.accelerationY = 0.0f;
        this.accelerationZ = 0.0f;
    }

    private final boolean atLeastRequiredForce(float f) {
        return Math.abs(f) > 13.042845f;
    }

    private final void recordShake(long j) {
        this.lastShakeTimestamp = j;
        this.numShakes++;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        Intrinsics.checkNotNullParameter(sensorEvent, "sensorEvent");
        if (sensorEvent.timestamp - this.lastTimestamp >= ShakeDetectorKt.MIN_TIME_BETWEEN_SAMPLES_NS) {
            float[] fArr = sensorEvent.values;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[2] - 9.80665f;
            this.lastTimestamp = sensorEvent.timestamp;
            if (atLeastRequiredForce(f) && this.accelerationX * f <= 0.0f) {
                recordShake(sensorEvent.timestamp);
                this.accelerationX = f;
            } else if (atLeastRequiredForce(f2) && this.accelerationY * f2 <= 0.0f) {
                recordShake(sensorEvent.timestamp);
                this.accelerationY = f2;
            } else if (atLeastRequiredForce(f3) && this.accelerationZ * f3 <= 0.0f) {
                recordShake(sensorEvent.timestamp);
                this.accelerationZ = f3;
            }
            maybeDispatchShake(sensorEvent.timestamp);
        }
    }

    private final void maybeDispatchShake(long j) {
        if (this.numShakes >= this.minNumShakes * 8) {
            reset();
            this.shakeListener.onShake();
        }
        if (((float) (j - this.lastShakeTimestamp)) > ShakeDetectorKt.SHAKING_WINDOW_NS) {
            reset();
        }
    }
}
