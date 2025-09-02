package androidx.privacysandbox.ads.adservices.java.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Deferred;

final class CoroutineAdapterKt$asListenableFuture$1$1 extends Lambda implements Function1 {
    final /* synthetic */ CallbackToFutureAdapter.Completer $completer;
    final /* synthetic */ Deferred $this_asListenableFuture;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CoroutineAdapterKt$asListenableFuture$1$1(CallbackToFutureAdapter.Completer completer, Deferred deferred) {
        super(1);
        this.$completer = completer;
        this.$this_asListenableFuture = deferred;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        if (th == null) {
            this.$completer.set(this.$this_asListenableFuture.getCompleted());
        } else if (th instanceof CancellationException) {
            this.$completer.setCancelled();
        } else {
            this.$completer.setException(th);
        }
    }
}
