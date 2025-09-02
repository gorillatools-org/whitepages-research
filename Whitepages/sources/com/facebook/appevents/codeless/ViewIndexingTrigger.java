package com.facebook.appevents.codeless;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ViewIndexingTrigger implements SensorEventListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private OnShakeListener onShakeListener;

    public interface OnShakeListener {
        void onShake();
    }

    public final void setOnShakeListener(OnShakeListener onShakeListener2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                this.onShakeListener = onShakeListener2;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(sensorEvent, "event");
                OnShakeListener onShakeListener2 = this.onShakeListener;
                if (onShakeListener2 != null) {
                    float[] fArr = sensorEvent.values;
                    float f = fArr[0];
                    double d = (double) (f / 9.80665f);
                    double d2 = (double) (fArr[1] / 9.80665f);
                    double d3 = (double) (fArr[2] / 9.80665f);
                    if (Math.sqrt((d * d) + (d2 * d2) + (d3 * d3)) > 2.3d) {
                        onShakeListener2.onShake();
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(sensor, "sensor");
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
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
