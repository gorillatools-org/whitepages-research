package org.greenrobot.eventbus;

import java.io.PrintStream;
import java.util.logging.Level;
import org.greenrobot.eventbus.android.AndroidComponents;

public interface Logger {
    void log(Level level, String str);

    void log(Level level, String str, Throwable th);

    public static class SystemOutLogger implements Logger {
        public void log(Level level, String str) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
        }

        public void log(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
            th.printStackTrace(printStream);
        }
    }

    public static class Default {
        public static Logger get() {
            if (AndroidComponents.areAvailable()) {
                return AndroidComponents.get().logger;
            }
            return new SystemOutLogger();
        }
    }
}
