package androidx.work;

import android.util.Log;

public abstract class Logger {
    private static final int MAX_PREFIXED_TAG_LENGTH = 20;
    private static final Object sLock = new Object();
    private static volatile Logger sLogger;

    public abstract void debug(String str, String str2);

    public abstract void debug(String str, String str2, Throwable th);

    public abstract void error(String str, String str2);

    public abstract void error(String str, String str2, Throwable th);

    public abstract void info(String str, String str2);

    public abstract void info(String str, String str2, Throwable th);

    public abstract void verbose(String str, String str2);

    public abstract void warning(String str, String str2);

    public abstract void warning(String str, String str2, Throwable th);

    public static void setLogger(Logger logger) {
        synchronized (sLock) {
            sLogger = logger;
        }
    }

    public static String tagWithPrefix(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(23);
        sb.append("WM-");
        int i = MAX_PREFIXED_TAG_LENGTH;
        if (length >= i) {
            sb.append(str.substring(0, i));
        } else {
            sb.append(str);
        }
        return sb.toString();
    }

    public static Logger get() {
        Logger logger;
        synchronized (sLock) {
            try {
                if (sLogger == null) {
                    sLogger = new LogcatLogger(3);
                }
                logger = sLogger;
            } catch (Throwable th) {
                throw th;
            }
        }
        return logger;
    }

    public Logger(int i) {
    }

    public static class LogcatLogger extends Logger {
        private final int mLoggingLevel;

        public LogcatLogger(int i) {
            super(i);
            this.mLoggingLevel = i;
        }

        public void verbose(String str, String str2) {
            if (this.mLoggingLevel <= 2) {
                Log.v(str, str2);
            }
        }

        public void debug(String str, String str2) {
            if (this.mLoggingLevel <= 3) {
                Log.d(str, str2);
            }
        }

        public void debug(String str, String str2, Throwable th) {
            if (this.mLoggingLevel <= 3) {
                Log.d(str, str2, th);
            }
        }

        public void info(String str, String str2) {
            if (this.mLoggingLevel <= 4) {
                Log.i(str, str2);
            }
        }

        public void info(String str, String str2, Throwable th) {
            if (this.mLoggingLevel <= 4) {
                Log.i(str, str2, th);
            }
        }

        public void warning(String str, String str2) {
            if (this.mLoggingLevel <= 5) {
                Log.w(str, str2);
            }
        }

        public void warning(String str, String str2, Throwable th) {
            if (this.mLoggingLevel <= 5) {
                Log.w(str, str2, th);
            }
        }

        public void error(String str, String str2) {
            if (this.mLoggingLevel <= 6) {
                Log.e(str, str2);
            }
        }

        public void error(String str, String str2, Throwable th) {
            if (this.mLoggingLevel <= 6) {
                Log.e(str, str2, th);
            }
        }
    }
}
