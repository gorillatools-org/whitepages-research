package okhttp3.internal.platform.android;

import java.util.logging.Level;
import java.util.logging.LogRecord;

public final class AndroidLogKt {
    /* access modifiers changed from: private */
    public static final int getAndroidLevel(LogRecord logRecord) {
        int intValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        if (intValue > level.intValue()) {
            return 5;
        }
        return logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
    }
}
