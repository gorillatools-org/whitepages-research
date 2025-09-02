package org.greenrobot.eventbus.android;

import android.util.Log;
import java.util.logging.Level;
import org.greenrobot.eventbus.Logger;

public class AndroidLogger implements Logger {
    private final String tag;

    public AndroidLogger(String str) {
        this.tag = str;
    }

    public void log(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(mapLevel(level), this.tag, str);
        }
    }

    public void log(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            int mapLevel = mapLevel(level);
            String str2 = this.tag;
            Log.println(mapLevel, str2, str + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + Log.getStackTraceString(th));
        }
    }

    private int mapLevel(Level level) {
        int intValue = level.intValue();
        if (intValue < 800) {
            return intValue < 500 ? 2 : 3;
        }
        if (intValue < 900) {
            return 4;
        }
        return intValue < 1000 ? 5 : 6;
    }
}
