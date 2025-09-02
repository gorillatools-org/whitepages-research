package com.facebook.react.modules.vibration;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import com.facebook.fbreact.specs.NativeVibrationSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SuppressLint({"MissingPermission"})
@ReactModule(name = "Vibration")
public final class VibrationModule extends NativeVibrationSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "Vibration";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VibrationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    public void vibrate(double d) {
        int i = (int) d;
        Vibrator vibrator = getVibrator();
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot((long) i, -1));
        }
    }

    public void vibrateByPattern(ReadableArray readableArray, double d) {
        Intrinsics.checkNotNullParameter(readableArray, "pattern");
        int i = (int) d;
        Vibrator vibrator = getVibrator();
        if (vibrator != null) {
            long[] jArr = new long[readableArray.size()];
            int size = readableArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                jArr[i2] = (long) readableArray.getInt(i2);
            }
            vibrator.vibrate(VibrationEffect.createWaveform(jArr, i));
        }
    }

    public void cancel() {
        Vibrator vibrator = getVibrator();
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    private final Vibrator getVibrator() {
        if (Build.VERSION.SDK_INT < 31) {
            return (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        }
        VibratorManager m = VibrationModule$$ExternalSyntheticApiModelOutline0.m(getReactApplicationContext().getSystemService("vibrator_manager"));
        if (m != null) {
            return m.getDefaultVibrator();
        }
        return null;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
