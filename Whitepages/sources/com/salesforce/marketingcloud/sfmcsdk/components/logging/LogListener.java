package com.salesforce.marketingcloud.sfmcsdk.components.logging;

import android.util.Log;
import com.facebook.react.devsupport.StackTraceHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.jvm.internal.Intrinsics;

public interface LogListener {
    void out(LogLevel logLevel, String str, String str2, Throwable th);

    public static class AndroidLogger implements LogListener {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[LogLevel.values().length];
                iArr[LogLevel.DEBUG.ordinal()] = 1;
                iArr[LogLevel.WARN.ordinal()] = 2;
                iArr[LogLevel.ERROR.ordinal()] = 3;
                iArr[LogLevel.NONE.ordinal()] = 4;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public void out(LogLevel logLevel, String str, String str2, Throwable th) {
            Intrinsics.checkNotNullParameter(logLevel, FirebaseAnalytics.Param.LEVEL);
            Intrinsics.checkNotNullParameter(str, "tag");
            Intrinsics.checkNotNullParameter(str2, StackTraceHelper.MESSAGE_KEY);
            int i = WhenMappings.$EnumSwitchMapping$0[logLevel.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
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
                Log.d(str, str2);
            } else {
                Log.d(str, str2, th);
            }
        }
    }
}
