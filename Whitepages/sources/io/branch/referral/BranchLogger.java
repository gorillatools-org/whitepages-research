package io.branch.referral;

import android.util.Log;
import com.facebook.react.devsupport.StackTraceHelper;
import io.branch.interfaces.IBranchLoggingCallbacks;
import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.jvm.internal.Intrinsics;

public final class BranchLogger {
    public static final BranchLogger INSTANCE = new BranchLogger();
    private static boolean loggingEnabled;
    private static BranchLogLevel loggingLevel = BranchLogLevel.DEBUG;

    public static final void setLoggerCallback(IBranchLoggingCallbacks iBranchLoggingCallbacks) {
    }

    private final boolean useCustomLogger() {
        return false;
    }

    private BranchLogger() {
    }

    public enum BranchLogLevel {
        ERROR(1),
        WARN(2),
        INFO(3),
        DEBUG(4),
        VERBOSE(5);
        
        private final int level;

        private BranchLogLevel(int i) {
            this.level = i;
        }

        public final int getLevel() {
            return this.level;
        }
    }

    public static final BranchLogLevel getLoggingLevel() {
        return loggingLevel;
    }

    public static final void setLoggingLevel(BranchLogLevel branchLogLevel) {
        Intrinsics.checkNotNullParameter(branchLogLevel, "<set-?>");
        loggingLevel = branchLogLevel;
    }

    public static final void setLoggingEnabled(boolean z) {
        loggingEnabled = z;
    }

    private final boolean shouldLog(BranchLogLevel branchLogLevel) {
        return branchLogLevel.getLevel() <= loggingLevel.getLevel();
    }

    public static final void e(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (loggingEnabled) {
            BranchLogger branchLogger = INSTANCE;
            if (branchLogger.shouldLog(BranchLogLevel.ERROR) && str.length() > 0 && !branchLogger.useCustomLogger()) {
                Log.e("BranchSDK", str);
            }
        }
    }

    public static final void w(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (loggingEnabled) {
            BranchLogger branchLogger = INSTANCE;
            if (branchLogger.shouldLog(BranchLogLevel.WARN) && str.length() > 0 && !branchLogger.useCustomLogger()) {
                Log.w("BranchSDK", str);
            }
        }
    }

    public static final void i(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (loggingEnabled) {
            BranchLogger branchLogger = INSTANCE;
            if (branchLogger.shouldLog(BranchLogLevel.INFO) && str.length() > 0 && !branchLogger.useCustomLogger()) {
                Log.i("BranchSDK", str);
            }
        }
    }

    public static final void d(String str) {
        if (loggingEnabled) {
            BranchLogger branchLogger = INSTANCE;
            if (branchLogger.shouldLog(BranchLogLevel.DEBUG) && str != null && str.length() > 0 && !branchLogger.useCustomLogger()) {
                Log.d("BranchSDK", str);
            }
        }
    }

    public static final void v(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (loggingEnabled) {
            BranchLogger branchLogger = INSTANCE;
            if (branchLogger.shouldLog(BranchLogLevel.VERBOSE) && str.length() > 0 && !branchLogger.useCustomLogger()) {
                Log.v("BranchSDK", str);
            }
        }
    }

    public static final void logAlways(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (str.length() > 0 && !INSTANCE.useCustomLogger()) {
            Log.i("BranchSDK", str);
        }
    }

    public static final String stackTraceToString(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "exception");
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
