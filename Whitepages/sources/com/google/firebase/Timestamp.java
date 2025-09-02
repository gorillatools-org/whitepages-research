package com.google.firebase;

import android.os.Parcel;
import android.os.Parcelable;
import java.time.Instant;
import java.util.Date;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2Connection;

public final class Timestamp implements Comparable<Timestamp>, Parcelable {
    public static final Parcelable.Creator<Timestamp> CREATOR = new Timestamp$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int nanoseconds;
    private final long seconds;

    public static final Timestamp now() {
        return Companion.now();
    }

    public int describeContents() {
        return 0;
    }

    public final long getSeconds() {
        return this.seconds;
    }

    public final int getNanoseconds() {
        return this.nanoseconds;
    }

    public Timestamp(long j, int i) {
        Companion.validateRange(j, i);
        this.seconds = j;
        this.nanoseconds = i;
    }

    public Timestamp(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Companion companion = Companion;
        Pair access$toPreciseTime = companion.toPreciseTime(date);
        long longValue = ((Number) access$toPreciseTime.component1()).longValue();
        int intValue = ((Number) access$toPreciseTime.component2()).intValue();
        companion.validateRange(longValue, intValue);
        this.seconds = longValue;
        this.nanoseconds = intValue;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Timestamp(Instant instant) {
        this(instant.getEpochSecond(), instant.getNano());
        Intrinsics.checkNotNullParameter(instant, "time");
    }

    public final Date toDate() {
        return new Date((this.seconds * ((long) 1000)) + ((long) (this.nanoseconds / 1000000)));
    }

    public final Instant toInstant() {
        Instant ofEpochSecond = Instant.ofEpochSecond(this.seconds, (long) this.nanoseconds);
        Intrinsics.checkNotNullExpressionValue(ofEpochSecond, "ofEpochSecond(seconds, nanoseconds.toLong())");
        return ofEpochSecond;
    }

    public int compareTo(Timestamp timestamp) {
        Intrinsics.checkNotNullParameter(timestamp, "other");
        return ComparisonsKt.compareValuesBy(this, timestamp, Timestamp$compareTo$1.INSTANCE, Timestamp$compareTo$2.INSTANCE);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Timestamp) && compareTo((Timestamp) obj) == 0);
    }

    public int hashCode() {
        long j = this.seconds;
        return (((((int) j) * 1369) + ((int) (j >> 32))) * 37) + this.nanoseconds;
    }

    public String toString() {
        return "Timestamp(seconds=" + this.seconds + ", nanoseconds=" + this.nanoseconds + ')';
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeLong(this.seconds);
        parcel.writeInt(this.nanoseconds);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Timestamp now() {
            return new Timestamp(new Date());
        }

        /* access modifiers changed from: private */
        public final Pair toPreciseTime(Date date) {
            long j = (long) 1000;
            long time = date.getTime() / j;
            int time2 = (int) ((date.getTime() % j) * ((long) 1000000));
            if (time2 < 0) {
                return TuplesKt.to(Long.valueOf(time - 1), Integer.valueOf(time2 + Http2Connection.DEGRADED_PONG_TIMEOUT_NS));
            }
            return TuplesKt.to(Long.valueOf(time), Integer.valueOf(time2));
        }

        /* access modifiers changed from: private */
        public final void validateRange(long j, int i) {
            if (i < 0 || i >= 1000000000) {
                throw new IllegalArgumentException(("Timestamp nanoseconds out of range: " + i).toString());
            } else if (-62135596800L > j || j >= 253402300800L) {
                throw new IllegalArgumentException(("Timestamp seconds out of range: " + j).toString());
            }
        }
    }
}
