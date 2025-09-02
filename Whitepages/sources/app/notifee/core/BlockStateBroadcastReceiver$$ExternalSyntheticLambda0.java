package app.notifee.core;

import android.os.Bundle;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import app.notifee.core.event.BlockStateEvent;
import n.o.t.i.f.e.e.f;
import n.o.t.i.f.e.e.l;

public final /* synthetic */ class BlockStateBroadcastReceiver$$ExternalSyntheticLambda0 implements l.a {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ BlockStateBroadcastReceiver$$ExternalSyntheticLambda0(CallbackToFutureAdapter.Completer completer, int i, boolean z) {
        this.f$0 = completer;
        this.f$1 = i;
        this.f$2 = z;
    }

    public final void a(Object obj) {
        f.a(new BlockStateEvent(this.f$1, (Bundle) obj, this.f$2, new BlockStateBroadcastReceiver$$ExternalSyntheticLambda2(this.f$0)));
    }
}
