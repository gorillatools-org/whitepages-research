package com.salesforce.marketingcloud;

import android.util.Log;
import com.facebook.react.devsupport.StackTraceHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.jvm.internal.Intrinsics;

@MCKeep
public interface MCLogListener {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    @MCKeep
    public static class AndroidLogListener implements MCLogListener {
        public void out(int i, String str, String str2, Throwable th) {
            Intrinsics.checkNotNullParameter(str, "tag");
            Intrinsics.checkNotNullParameter(str2, StackTraceHelper.MESSAGE_KEY);
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            if (i == 6) {
                                if (th == null) {
                                    Log.e(str, str2);
                                } else {
                                    Log.e(str, str2, th);
                                }
                            }
                        } else if (th == null) {
                            Log.w(str, str2);
                        } else {
                            Log.w(str, str2, th);
                        }
                    } else if (th == null) {
                        Log.i(str, str2);
                    } else {
                        Log.i(str, str2, th);
                    }
                } else if (th == null) {
                    Log.d(str, str2);
                } else {
                    Log.d(str, str2, th);
                }
            } else if (th == null) {
                Log.v(str, str2);
            } else {
                Log.v(str, str2, th);
            }
        }
    }

    @MCKeep
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int DEBUG = 3;
        public static final int ERROR = 6;
        public static final int INFO = 4;
        public static final int VERBOSE = 2;
        public static final int WARN = 5;

        private Companion() {
        }
    }

    @MCKeep
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LogLevel {
    }

    void out(@LogLevel int i, String str, String str2, Throwable th);
}
