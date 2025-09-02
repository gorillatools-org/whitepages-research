package io.invertase.notifee;

import app.notifee.core.interfaces.MethodCallResult;
import com.facebook.react.bridge.Promise;

public final /* synthetic */ class NotifeeApiModule$$ExternalSyntheticLambda28 implements MethodCallResult {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ NotifeeApiModule$$ExternalSyntheticLambda28(Promise promise) {
        this.f$0 = promise;
    }

    public final void onComplete(Exception exc, Object obj) {
        NotifeeReactUtils.promiseResolver(this.f$0, exc);
    }
}
