package com.facebook.react.devsupport;

import com.facebook.react.devsupport.interfaces.ErrorType;
import com.facebook.react.devsupport.interfaces.StackFrame;

public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ DevSupportManagerBase f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ StackFrame[] f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ ErrorType f$4;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda3(DevSupportManagerBase devSupportManagerBase, String str, StackFrame[] stackFrameArr, int i, ErrorType errorType) {
        this.f$0 = devSupportManagerBase;
        this.f$1 = str;
        this.f$2 = stackFrameArr;
        this.f$3 = i;
        this.f$4 = errorType;
    }

    public final void run() {
        this.f$0.lambda$showNewError$1(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
