package kotlin.time;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.http2.Http2Connection;

public final class Duration implements Comparable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long INFINITE = DurationKt.durationOfMillis(4611686018427387903L);
    private static final long NEG_INFINITE = DurationKt.durationOfMillis(-4611686018427387903L);
    private static final long ZERO = m875constructorimpl(0);
    private final long rawValue;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Duration m873boximpl(long j) {
        return new Duration(j);
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m876equalsimpl(long j, Object obj) {
        return (obj instanceof Duration) && j == ((Duration) obj).m900unboximpl();
    }

    /* renamed from: getValue-impl  reason: not valid java name */
    private static final long m888getValueimpl(long j) {
        return j >> 1;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m889hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* renamed from: isInMillis-impl  reason: not valid java name */
    private static final boolean m891isInMillisimpl(long j) {
        return (((int) j) & 1) == 1;
    }

    /* renamed from: isInNanos-impl  reason: not valid java name */
    private static final boolean m892isInNanosimpl(long j) {
        return (((int) j) & 1) == 0;
    }

    /* renamed from: isNegative-impl  reason: not valid java name */
    public static final boolean m894isNegativeimpl(long j) {
        return j < 0;
    }

    /* renamed from: isPositive-impl  reason: not valid java name */
    public static final boolean m895isPositiveimpl(long j) {
        return j > 0;
    }

    public boolean equals(Object obj) {
        return m876equalsimpl(this.rawValue, obj);
    }

    public int hashCode() {
        return m889hashCodeimpl(this.rawValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m900unboximpl() {
        return this.rawValue;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m899compareToLRDsOJo(((Duration) obj).m900unboximpl());
    }

    private /* synthetic */ Duration(long j) {
        this.rawValue = j;
    }

    /* renamed from: getStorageUnit-impl  reason: not valid java name */
    private static final DurationUnit m887getStorageUnitimpl(long j) {
        return m892isInNanosimpl(j) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m875constructorimpl(long j) {
        if (DurationJvmKt.getDurationAssertionsEnabled()) {
            if (m892isInNanosimpl(j)) {
                long r0 = m888getValueimpl(j);
                if (-4611686018426999999L > r0 || r0 >= 4611686018427000000L) {
                    throw new AssertionError(m888getValueimpl(j) + " ns is out of nanoseconds range");
                }
            } else {
                long r02 = m888getValueimpl(j);
                if (-4611686018427387903L > r02 || r02 >= 4611686018427387904L) {
                    throw new AssertionError(m888getValueimpl(j) + " ms is out of milliseconds range");
                }
                long r03 = m888getValueimpl(j);
                if (-4611686018426L <= r03 && r03 < 4611686018427L) {
                    throw new AssertionError(m888getValueimpl(j) + " ms is denormalized");
                }
            }
        }
        return j;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* renamed from: unaryMinus-UwyO8pc  reason: not valid java name */
    public static final long m898unaryMinusUwyO8pc(long j) {
        return DurationKt.durationOf(-m888getValueimpl(j), ((int) j) & 1);
    }

    /* renamed from: isInfinite-impl  reason: not valid java name */
    public static final boolean m893isInfiniteimpl(long j) {
        return j == INFINITE || j == NEG_INFINITE;
    }

    /* renamed from: isFinite-impl  reason: not valid java name */
    public static final boolean m890isFiniteimpl(long j) {
        return !m893isInfiniteimpl(j);
    }

    /* renamed from: getAbsoluteValue-UwyO8pc  reason: not valid java name */
    public static final long m877getAbsoluteValueUwyO8pc(long j) {
        return m894isNegativeimpl(j) ? m898unaryMinusUwyO8pc(j) : j;
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public int m899compareToLRDsOJo(long j) {
        return m874compareToLRDsOJo(this.rawValue, j);
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public static int m874compareToLRDsOJo(long j, long j2) {
        long j3 = j ^ j2;
        if (j3 < 0 || (((int) j3) & 1) == 0) {
            return Intrinsics.compare(j, j2);
        }
        int i = (((int) j) & 1) - (((int) j2) & 1);
        return m894isNegativeimpl(j) ? -i : i;
    }

    /* renamed from: getHoursComponent-impl  reason: not valid java name */
    public static final int m878getHoursComponentimpl(long j) {
        if (m893isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m880getInWholeHoursimpl(j) % ((long) 24));
    }

    /* renamed from: getMinutesComponent-impl  reason: not valid java name */
    public static final int m884getMinutesComponentimpl(long j) {
        if (m893isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m882getInWholeMinutesimpl(j) % ((long) 60));
    }

    /* renamed from: getSecondsComponent-impl  reason: not valid java name */
    public static final int m886getSecondsComponentimpl(long j) {
        if (m893isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m883getInWholeSecondsimpl(j) % ((long) 60));
    }

    /* renamed from: getNanosecondsComponent-impl  reason: not valid java name */
    public static final int m885getNanosecondsComponentimpl(long j) {
        long r2;
        if (m893isInfiniteimpl(j)) {
            return 0;
        }
        if (m891isInMillisimpl(j)) {
            r2 = DurationKt.millisToNanos(m888getValueimpl(j) % ((long) 1000));
        } else {
            r2 = m888getValueimpl(j) % ((long) Http2Connection.DEGRADED_PONG_TIMEOUT_NS);
        }
        return (int) r2;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    public static final long m896toLongimpl(long j, DurationUnit durationUnit) {
        Intrinsics.checkNotNullParameter(durationUnit, "unit");
        if (j == INFINITE) {
            return Long.MAX_VALUE;
        }
        if (j == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(m888getValueimpl(j), m887getStorageUnitimpl(j), durationUnit);
    }

    /* renamed from: getInWholeDays-impl  reason: not valid java name */
    public static final long m879getInWholeDaysimpl(long j) {
        return m896toLongimpl(j, DurationUnit.DAYS);
    }

    /* renamed from: getInWholeHours-impl  reason: not valid java name */
    public static final long m880getInWholeHoursimpl(long j) {
        return m896toLongimpl(j, DurationUnit.HOURS);
    }

    /* renamed from: getInWholeMinutes-impl  reason: not valid java name */
    public static final long m882getInWholeMinutesimpl(long j) {
        return m896toLongimpl(j, DurationUnit.MINUTES);
    }

    /* renamed from: getInWholeSeconds-impl  reason: not valid java name */
    public static final long m883getInWholeSecondsimpl(long j) {
        return m896toLongimpl(j, DurationUnit.SECONDS);
    }

    /* renamed from: getInWholeMilliseconds-impl  reason: not valid java name */
    public static final long m881getInWholeMillisecondsimpl(long j) {
        return (!m891isInMillisimpl(j) || !m890isFiniteimpl(j)) ? m896toLongimpl(j, DurationUnit.MILLISECONDS) : m888getValueimpl(j);
    }

    public String toString() {
        return m897toStringimpl(this.rawValue);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m897toStringimpl(long j) {
        if (j == 0) {
            return "0s";
        }
        if (j == INFINITE) {
            return "Infinity";
        }
        if (j == NEG_INFINITE) {
            return "-Infinity";
        }
        boolean r2 = m894isNegativeimpl(j);
        StringBuilder sb = new StringBuilder();
        if (r2) {
            sb.append('-');
        }
        long r3 = m877getAbsoluteValueUwyO8pc(j);
        long r5 = m879getInWholeDaysimpl(r3);
        int r7 = m878getHoursComponentimpl(r3);
        int r8 = m884getMinutesComponentimpl(r3);
        int r9 = m886getSecondsComponentimpl(r3);
        int r10 = m885getNanosecondsComponentimpl(r3);
        int i = 0;
        boolean z = r5 != 0;
        boolean z2 = r7 != 0;
        boolean z3 = r8 != 0;
        boolean z4 = (r9 == 0 && r10 == 0) ? false : true;
        if (z) {
            sb.append(r5);
            sb.append('d');
            i = 1;
        }
        if (z2 || (z && (z3 || z4))) {
            int i2 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(r7);
            sb.append('h');
            i = i2;
        }
        if (z3 || (z4 && (z2 || z))) {
            int i3 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(r8);
            sb.append('m');
            i = i3;
        }
        if (z4) {
            int i4 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            if (r9 != 0 || z || z2 || z3) {
                m872appendFractionalimpl(j, sb, r9, r10, 9, "s", false);
            } else if (r10 >= 1000000) {
                m872appendFractionalimpl(j, sb, r10 / 1000000, r10 % 1000000, 6, "ms", false);
            } else if (r10 >= 1000) {
                m872appendFractionalimpl(j, sb, r10 / 1000, r10 % 1000, 3, "us", false);
            } else {
                sb.append(r10);
                sb.append("ns");
            }
            i = i4;
        }
        if (r2 && i > 1) {
            sb.insert(1, '(').append(')');
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* renamed from: appendFractional-impl  reason: not valid java name */
    private static final void m872appendFractionalimpl(long j, StringBuilder sb, int i, int i2, int i3, String str, boolean z) {
        sb.append(i);
        if (i2 != 0) {
            sb.append('.');
            String padStart = StringsKt.padStart(String.valueOf(i2), i3, '0');
            int i4 = -1;
            int length = padStart.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i5 = length - 1;
                    if (padStart.charAt(length) != '0') {
                        i4 = length;
                        break;
                    } else if (i5 < 0) {
                        break;
                    } else {
                        length = i5;
                    }
                }
            }
            int i6 = i4 + 1;
            if (z || i6 >= 3) {
                sb.append(padStart, 0, ((i4 + 3) / 3) * 3);
                Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            } else {
                sb.append(padStart, 0, i6);
                Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            }
        }
        sb.append(str);
    }
}
