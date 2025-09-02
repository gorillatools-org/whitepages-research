package com.swmansion.gesturehandler.core;

import android.view.MotionEvent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class StylusData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final double altitudeAngle;
    private final double azimuthAngle;
    private final double pressure;
    private final double tiltX;
    private final double tiltY;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StylusData)) {
            return false;
        }
        StylusData stylusData = (StylusData) obj;
        return Double.compare(this.tiltX, stylusData.tiltX) == 0 && Double.compare(this.tiltY, stylusData.tiltY) == 0 && Double.compare(this.altitudeAngle, stylusData.altitudeAngle) == 0 && Double.compare(this.azimuthAngle, stylusData.azimuthAngle) == 0 && Double.compare(this.pressure, stylusData.pressure) == 0;
    }

    public int hashCode() {
        return (((((((Double.hashCode(this.tiltX) * 31) + Double.hashCode(this.tiltY)) * 31) + Double.hashCode(this.altitudeAngle)) * 31) + Double.hashCode(this.azimuthAngle)) * 31) + Double.hashCode(this.pressure);
    }

    public String toString() {
        return "StylusData(tiltX=" + this.tiltX + ", tiltY=" + this.tiltY + ", altitudeAngle=" + this.altitudeAngle + ", azimuthAngle=" + this.azimuthAngle + ", pressure=" + this.pressure + ')';
    }

    public StylusData(double d, double d2, double d3, double d4, double d5) {
        this.tiltX = d;
        this.tiltY = d2;
        this.altitudeAngle = d3;
        this.azimuthAngle = d4;
        this.pressure = d5;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ StylusData(double r12, double r14, double r16, double r18, double r20, int r22, kotlin.jvm.internal.DefaultConstructorMarker r23) {
        /*
            r11 = this;
            r0 = r22 & 1
            r1 = 0
            if (r0 == 0) goto L_0x0008
            r3 = r1
            goto L_0x0009
        L_0x0008:
            r3 = r12
        L_0x0009:
            r0 = r22 & 2
            if (r0 == 0) goto L_0x000f
            r5 = r1
            goto L_0x0010
        L_0x000f:
            r5 = r14
        L_0x0010:
            r0 = r22 & 4
            if (r0 == 0) goto L_0x0016
            r7 = r1
            goto L_0x0018
        L_0x0016:
            r7 = r16
        L_0x0018:
            r0 = r22 & 8
            if (r0 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r1 = r18
        L_0x001f:
            r0 = r22 & 16
            if (r0 == 0) goto L_0x0026
            r9 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            goto L_0x0028
        L_0x0026:
            r9 = r20
        L_0x0028:
            r12 = r11
            r13 = r3
            r15 = r5
            r17 = r7
            r19 = r1
            r21 = r9
            r12.<init>(r13, r15, r17, r19, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.StylusData.<init>(double, double, double, double, double, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final double getPressure() {
        return this.pressure;
    }

    public final ReadableMap toReadableMap() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("tiltX", this.tiltX);
        createMap.putDouble("tiltY", this.tiltY);
        createMap.putDouble("altitudeAngle", this.altitudeAngle);
        createMap.putDouble("azimuthAngle", this.azimuthAngle);
        createMap.putDouble("pressure", this.pressure);
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Pair spherical2tilt(double d, double d2) {
            double d3;
            double d4;
            if (d < 1.0E-9d) {
                d4 = 1.5707963267948966d;
                double d5 = 0.0d;
                double d6 = (d2 < 1.0E-9d || Math.abs(d2 - 6.283185307179586d) < 1.0E-9d) ? 1.5707963267948966d : 0.0d;
                double d7 = d2 - 1.5707963267948966d;
                if (Math.abs(d7) < 1.0E-9d) {
                    d5 = 1.5707963267948966d;
                }
                double d8 = d2 - 3.141592653589793d;
                d3 = -1.5707963267948966d;
                if (Math.abs(d8) < 1.0E-9d) {
                    d6 = -1.5707963267948966d;
                }
                double d9 = d2 - 4.71238898038469d;
                if (Math.abs(d9) < 1.0E-9d) {
                    d5 = -1.5707963267948966d;
                }
                if (d2 > 1.0E-9d && Math.abs(d7) < 1.0E-9d) {
                    d5 = 1.5707963267948966d;
                    d6 = 1.5707963267948966d;
                }
                if (Math.abs(d7) > 1.0E-9d && Math.abs(d8) < 1.0E-9d) {
                    d5 = 1.5707963267948966d;
                    d6 = -1.5707963267948966d;
                }
                if (Math.abs(d8) > 1.0E-9d && Math.abs(d9) < 1.0E-9d) {
                    d5 = -1.5707963267948966d;
                    d6 = -1.5707963267948966d;
                }
                if (Math.abs(d9) <= 1.0E-9d || Math.abs(d2 - 6.283185307179586d) >= 1.0E-9d) {
                    d3 = d5;
                    d4 = d6;
                }
            } else {
                double tan = Math.tan(d);
                d4 = Math.atan(Math.cos(d2) / tan);
                d3 = Math.atan(Math.sin(d2) / tan);
            }
            return new Pair(Double.valueOf(Math.rint(d4 * 57.29577951308232d)), Double.valueOf(Math.rint(d3 * 57.29577951308232d)));
        }

        public final StylusData fromEvent(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = motionEvent;
            Intrinsics.checkNotNullParameter(motionEvent2, "event");
            double axisValue = 1.5707963267948966d - ((double) motionEvent2.getAxisValue(25));
            double pressure = (double) motionEvent2.getPressure(0);
            double orientation = (((double) motionEvent2.getOrientation(0)) + 1.5707963267948966d) % 6.283185307179586d;
            if (!(orientation == 0.0d || Math.signum(orientation) == Math.signum(6.283185307179586d))) {
                orientation += 6.283185307179586d;
            }
            double d = orientation;
            Pair spherical2tilt = spherical2tilt(axisValue, d);
            return new StylusData(((Number) spherical2tilt.getFirst()).doubleValue(), ((Number) spherical2tilt.getSecond()).doubleValue(), axisValue, d, pressure);
        }
    }
}
