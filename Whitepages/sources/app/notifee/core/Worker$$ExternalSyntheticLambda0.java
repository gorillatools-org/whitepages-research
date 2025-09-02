package app.notifee.core;

import androidx.concurrent.futures.CallbackToFutureAdapter;

public final /* synthetic */ class Worker$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Worker f$0;

    public /* synthetic */ Worker$$ExternalSyntheticLambda0(Worker worker) {
        this.f$0 = worker;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.a(completer);
    }
}
