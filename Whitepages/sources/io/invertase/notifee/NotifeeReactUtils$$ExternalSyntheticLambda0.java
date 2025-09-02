package io.invertase.notifee;

import com.facebook.react.bridge.WritableMap;
import io.invertase.notifee.NotifeeReactUtils;

public final /* synthetic */ class NotifeeReactUtils$$ExternalSyntheticLambda0 implements NotifeeReactUtils.GenericCallback {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ WritableMap f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ NotifeeReactUtils.GenericCallback f$3;

    public /* synthetic */ NotifeeReactUtils$$ExternalSyntheticLambda0(String str, WritableMap writableMap, long j, NotifeeReactUtils.GenericCallback genericCallback) {
        this.f$0 = str;
        this.f$1 = writableMap;
        this.f$2 = j;
        this.f$3 = genericCallback;
    }

    public final void call() {
        NotifeeReactUtils.lambda$startHeadlessTask$1(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
