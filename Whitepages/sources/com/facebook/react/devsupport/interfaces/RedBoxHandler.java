package com.facebook.react.devsupport.interfaces;

import android.content.Context;
import android.text.SpannedString;

public interface RedBoxHandler {

    public interface ReportCompletedListener {
        void onReportError(SpannedString spannedString);

        void onReportSuccess(SpannedString spannedString);
    }

    void handleRedbox(String str, StackFrame[] stackFrameArr, ErrorType errorType);

    boolean isReportEnabled();

    void reportRedbox(Context context, String str, StackFrame[] stackFrameArr, String str2, ReportCompletedListener reportCompletedListener);
}
