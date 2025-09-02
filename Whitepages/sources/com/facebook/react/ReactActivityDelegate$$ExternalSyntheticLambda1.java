package com.facebook.react;

import com.facebook.react.bridge.Callback;

public final /* synthetic */ class ReactActivityDelegate$$ExternalSyntheticLambda1 implements Callback {
    public final /* synthetic */ ReactActivityDelegate f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String[] f$2;
    public final /* synthetic */ int[] f$3;

    public /* synthetic */ ReactActivityDelegate$$ExternalSyntheticLambda1(ReactActivityDelegate reactActivityDelegate, int i, String[] strArr, int[] iArr) {
        this.f$0 = reactActivityDelegate;
        this.f$1 = i;
        this.f$2 = strArr;
        this.f$3 = iArr;
    }

    public final void invoke(Object[] objArr) {
        this.f$0.lambda$onRequestPermissionsResult$1(this.f$1, this.f$2, this.f$3, objArr);
    }
}
