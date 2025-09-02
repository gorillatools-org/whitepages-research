package io.invertase.notifee;

import app.notifee.core.interfaces.MethodCallResult;
import com.facebook.react.bridge.Promise;

public final /* synthetic */ class NotifeeApiModule$$ExternalSyntheticLambda22 implements MethodCallResult {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ NotifeeApiModule$$ExternalSyntheticLambda22(Promise promise) {
        this.f$0 = promise;
    }

    public final void onComplete(Exception exc, Object obj) {
        NotifeeReactUtils.promiseBooleanResolver(this.f$0, exc, (Boolean) obj);
    }
}
