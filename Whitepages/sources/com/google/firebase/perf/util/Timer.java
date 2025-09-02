package com.google.firebase.perf.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

public class Timer implements Parcelable {
    public static final Parcelable.Creator<Timer> CREATOR = new Parcelable.Creator<Timer>() {
        public Timer createFromParcel(Parcel parcel) {
            return new Timer(parcel);
        }

        public Timer[] newArray(int i) {
            return new Timer[i];
        }
    };
    private long elapsedRealtimeMicros;
    private long wallClockMicros;

    public int describeContents() {
        return 0;
    }

    public static Timer ofElapsedRealtime(long j) {
        long micros = TimeUnit.MILLISECONDS.toMicros(j);
        return new Timer(wallClockMicros() + (micros - elapsedRealtimeMicros()), micros);
    }

    private static long wallClockMicros() {
        return TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
    }

    private static long elapsedRealtimeMicros() {
        return TimeUnit.NANOSECONDS.toMicros(SystemClock.elapsedRealtimeNanos());
    }

    public Timer() {
        this(wallClockMicros(), elapsedRealtimeMicros());
    }

    Timer(long j, long j2) {
        this.wallClockMicros = j;
        this.elapsedRealtimeMicros = j2;
    }

    public Timer(long j) {
        this(j, j);
    }

    private Timer(Parcel parcel) {
        this(parcel.readLong(), parcel.readLong());
    }

    public void reset() {
        this.wallClockMicros = wallClockMicros();
        this.elapsedRealtimeMicros = elapsedRealtimeMicros();
    }

    public long getMicros() {
        return this.wallClockMicros;
    }

    public long getDurationMicros() {
        return getDurationMicros(new Timer());
    }

    public long getDurationMicros(Timer timer) {
        return timer.elapsedRealtimeMicros - this.elapsedRealtimeMicros;
    }

    public long getCurrentTimestampMicros() {
        return this.wallClockMicros + getDurationMicros();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.wallClockMicros);
        parcel.writeLong(this.elapsedRealtimeMicros);
    }
}
