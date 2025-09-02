package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import kotlin.jvm.internal.Intrinsics;

public class IllegalViewOperationException extends JSApplicationCausedNativeException {
    private View view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IllegalViewOperationException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "msg");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IllegalViewOperationException(String str, View view2, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "msg");
        Intrinsics.checkNotNullParameter(th, "cause");
        this.view = view2;
    }

    public final View getView() {
        return this.view;
    }
}
