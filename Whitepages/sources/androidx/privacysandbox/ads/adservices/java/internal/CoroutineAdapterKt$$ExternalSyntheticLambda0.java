package androidx.privacysandbox.ads.adservices.java.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import kotlinx.coroutines.Deferred;

public final /* synthetic */ class CoroutineAdapterKt$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Deferred f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CoroutineAdapterKt$$ExternalSyntheticLambda0(Deferred deferred, Object obj) {
        this.f$0 = deferred;
        this.f$1 = obj;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return CoroutineAdapterKt.asListenableFuture$lambda$0(this.f$0, this.f$1, completer);
    }
}
