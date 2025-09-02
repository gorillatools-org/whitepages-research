package app.notifee.core;

import android.os.Bundle;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import app.notifee.core.interfaces.MethodCallResult;
import n.o.t.i.f.e.e.l;

public final /* synthetic */ class BlockStateBroadcastReceiver$$ExternalSyntheticLambda1 implements MethodCallResult {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;
    public final /* synthetic */ l.a f$1;

    public /* synthetic */ BlockStateBroadcastReceiver$$ExternalSyntheticLambda1(CallbackToFutureAdapter.Completer completer, l.a aVar) {
        this.f$0 = completer;
        this.f$1 = aVar;
    }

    public final void onComplete(Exception exc, Object obj) {
        BlockStateBroadcastReceiver.a(this.f$0, this.f$1, exc, (Bundle) obj);
    }
}
