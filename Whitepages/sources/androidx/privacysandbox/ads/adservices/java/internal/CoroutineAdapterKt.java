package androidx.privacysandbox.ads.adservices.java.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Deferred;

public abstract class CoroutineAdapterKt {
    public static /* synthetic */ ListenableFuture asListenableFuture$default(Deferred deferred, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = "Deferred.asListenableFuture";
        }
        return asListenableFuture(deferred, obj);
    }

    public static final ListenableFuture asListenableFuture(Deferred deferred, Object obj) {
        Intrinsics.checkNotNullParameter(deferred, "<this>");
        ListenableFuture future = CallbackToFutureAdapter.getFuture(new CoroutineAdapterKt$$ExternalSyntheticLambda0(deferred, obj));
        Intrinsics.checkNotNullExpressionValue(future, "getFuture { completer ->…      }\n        tag\n    }");
        return future;
    }

    /* access modifiers changed from: private */
    public static final Object asListenableFuture$lambda$0(Deferred deferred, Object obj, CallbackToFutureAdapter.Completer completer) {
        Intrinsics.checkNotNullParameter(deferred, "$this_asListenableFuture");
        Intrinsics.checkNotNullParameter(completer, "completer");
        deferred.invokeOnCompletion(new CoroutineAdapterKt$asListenableFuture$1$1(completer, deferred));
        return obj;
    }
}
