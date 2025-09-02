package com.zoontek.rnpermissions;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

public final /* synthetic */ class RNPermissionsModuleImpl$$ExternalSyntheticLambda0 implements Callback {
    public final /* synthetic */ Promise f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ RNPermissionsModuleImpl$$ExternalSyntheticLambda0(Promise promise, String str) {
        this.f$0 = promise;
        this.f$1 = str;
    }

    public final void invoke(Object[] objArr) {
        RNPermissionsModuleImpl.request$lambda$5(this.f$0, this.f$1, objArr);
    }
}
