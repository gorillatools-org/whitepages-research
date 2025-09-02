package com.zoontek.rnpermissions;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;

public final /* synthetic */ class RNPermissionsModuleImpl$$ExternalSyntheticLambda1 implements Callback {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ Promise f$1;
    public final /* synthetic */ WritableMap f$2;

    public /* synthetic */ RNPermissionsModuleImpl$$ExternalSyntheticLambda1(ArrayList arrayList, Promise promise, WritableMap writableMap) {
        this.f$0 = arrayList;
        this.f$1 = promise;
        this.f$2 = writableMap;
    }

    public final void invoke(Object[] objArr) {
        RNPermissionsModuleImpl.requestMultiple$lambda$8(this.f$0, this.f$1, this.f$2, objArr);
    }
}
