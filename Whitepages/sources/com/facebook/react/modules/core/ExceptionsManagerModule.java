package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeExceptionsManagerSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.util.ExceptionDataHelper;
import com.facebook.react.util.JSStackTrace;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "ExceptionsManager")
public class ExceptionsManagerModule extends NativeExceptionsManagerSpec {
    private final DevSupportManager devSupportManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExceptionsManagerModule(DevSupportManager devSupportManager2) {
        super((ReactApplicationContext) null);
        Intrinsics.checkNotNullParameter(devSupportManager2, "devSupportManager");
        this.devSupportManager = devSupportManager2;
    }

    public void reportFatalException(String str, ReadableArray readableArray, double d) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putString(StackTraceHelper.MESSAGE_KEY, str);
        javaOnlyMap.putArray(StackTraceHelper.STACK_KEY, readableArray);
        javaOnlyMap.putInt("id", (int) d);
        javaOnlyMap.putBoolean(StackTraceHelper.IS_FATAL_KEY, true);
        reportException(javaOnlyMap);
    }

    public void reportSoftException(String str, ReadableArray readableArray, double d) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putString(StackTraceHelper.MESSAGE_KEY, str);
        javaOnlyMap.putArray(StackTraceHelper.STACK_KEY, readableArray);
        javaOnlyMap.putInt("id", (int) d);
        javaOnlyMap.putBoolean(StackTraceHelper.IS_FATAL_KEY, false);
        reportException(javaOnlyMap);
    }

    public void reportException(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        String string = readableMap.getString(StackTraceHelper.MESSAGE_KEY);
        if (string == null) {
            string = "";
        }
        ReadableArray array = readableMap.getArray(StackTraceHelper.STACK_KEY);
        if (array == null) {
            array = Arguments.createArray();
        }
        boolean z = readableMap.hasKey(StackTraceHelper.IS_FATAL_KEY) ? readableMap.getBoolean(StackTraceHelper.IS_FATAL_KEY) : false;
        String extraDataAsJson = ExceptionDataHelper.getExtraDataAsJson(readableMap);
        if (!z) {
            Intrinsics.checkNotNull(array);
            FLog.e(ReactConstants.TAG, JSStackTrace.format(string, array));
            if (extraDataAsJson != null) {
                FLog.d(ReactConstants.TAG, "extraData: %s", (Object) extraDataAsJson);
                return;
            }
            return;
        }
        Intrinsics.checkNotNull(array);
        JavascriptException javascriptException = new JavascriptException(JSStackTrace.format(string, array));
        javascriptException.setExtraDataAsJson(extraDataAsJson);
        throw javascriptException;
    }

    public void dismissRedbox() {
        if (this.devSupportManager.getDevSupportEnabled()) {
            this.devSupportManager.hideRedboxDialog();
        }
    }
}
