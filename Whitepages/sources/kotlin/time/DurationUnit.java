package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.enums.EnumEntriesKt;

public enum DurationUnit {
    NANOSECONDS(TimeUnit.NANOSECONDS),
    MICROSECONDS(TimeUnit.MICROSECONDS),
    MILLISECONDS(TimeUnit.MILLISECONDS),
    SECONDS(TimeUnit.SECONDS),
    MINUTES(TimeUnit.MINUTES),
    HOURS(TimeUnit.HOURS),
    DAYS(TimeUnit.DAYS);
    
    private final TimeUnit timeUnit;

    private DurationUnit(TimeUnit timeUnit2) {
        this.timeUnit = timeUnit2;
    }

    public final TimeUnit getTimeUnit$kotlin_stdlib() {
        return this.timeUnit;
    }

    static {
        DurationUnit[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
