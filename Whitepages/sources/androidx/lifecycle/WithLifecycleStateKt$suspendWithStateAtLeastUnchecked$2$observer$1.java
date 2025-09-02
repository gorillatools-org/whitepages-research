package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

public final class WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 implements LifecycleEventObserver {
    final /* synthetic */ Function0 $block;
    final /* synthetic */ CancellableContinuation $co;
    final /* synthetic */ Lifecycle.State $state;
    final /* synthetic */ Lifecycle $this_suspendWithStateAtLeastUnchecked;

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Object obj;
        Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.Companion.upTo(this.$state)) {
            this.$this_suspendWithStateAtLeastUnchecked.removeObserver(this);
            CancellableContinuation cancellableContinuation = this.$co;
            Function0 function0 = this.$block;
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.m866constructorimpl(function0.invoke());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.m866constructorimpl(ResultKt.createFailure(th));
            }
            cancellableContinuation.resumeWith(obj);
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            this.$this_suspendWithStateAtLeastUnchecked.removeObserver(this);
            CancellableContinuation cancellableContinuation2 = this.$co;
            Result.Companion companion3 = Result.Companion;
            cancellableContinuation2.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(new LifecycleDestroyedException())));
        }
    }
}
