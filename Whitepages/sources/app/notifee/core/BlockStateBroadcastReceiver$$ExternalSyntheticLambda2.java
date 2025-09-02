package app.notifee.core;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import app.notifee.core.interfaces.MethodCallResult;

public final /* synthetic */ class BlockStateBroadcastReceiver$$ExternalSyntheticLambda2 implements MethodCallResult {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;

    public /* synthetic */ BlockStateBroadcastReceiver$$ExternalSyntheticLambda2(CallbackToFutureAdapter.Completer completer) {
        this.f$0 = completer;
    }

    public final void onComplete(Exception exc, Object obj) {
        BlockStateBroadcastReceiver.a(this.f$0, exc, (Void) obj);
    }
}
