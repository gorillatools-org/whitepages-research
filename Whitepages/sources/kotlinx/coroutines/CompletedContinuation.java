package kotlinx.coroutines;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

final class CompletedContinuation {
    public final Throwable cancelCause;
    public final CancelHandler cancelHandler;
    public final Object idempotentResume;
    public final Function1 onCancellation;
    public final Object result;

    public static /* synthetic */ CompletedContinuation copy$default(CompletedContinuation completedContinuation, Object obj, CancelHandler cancelHandler2, Function1 function1, Object obj2, Throwable th, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = completedContinuation.result;
        }
        if ((i & 2) != 0) {
            cancelHandler2 = completedContinuation.cancelHandler;
        }
        CancelHandler cancelHandler3 = cancelHandler2;
        if ((i & 4) != 0) {
            function1 = completedContinuation.onCancellation;
        }
        Function1 function12 = function1;
        if ((i & 8) != 0) {
            obj2 = completedContinuation.idempotentResume;
        }
        Object obj4 = obj2;
        if ((i & 16) != 0) {
            th = completedContinuation.cancelCause;
        }
        return completedContinuation.copy(obj, cancelHandler3, function12, obj4, th);
    }

    public final CompletedContinuation copy(Object obj, CancelHandler cancelHandler2, Function1 function1, Object obj2, Throwable th) {
        return new CompletedContinuation(obj, cancelHandler2, function1, obj2, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedContinuation)) {
            return false;
        }
        CompletedContinuation completedContinuation = (CompletedContinuation) obj;
        return Intrinsics.areEqual(this.result, completedContinuation.result) && Intrinsics.areEqual((Object) this.cancelHandler, (Object) completedContinuation.cancelHandler) && Intrinsics.areEqual((Object) this.onCancellation, (Object) completedContinuation.onCancellation) && Intrinsics.areEqual(this.idempotentResume, completedContinuation.idempotentResume) && Intrinsics.areEqual((Object) this.cancelCause, (Object) completedContinuation.cancelCause);
    }

    public int hashCode() {
        Object obj = this.result;
        int i = 0;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        CancelHandler cancelHandler2 = this.cancelHandler;
        int hashCode2 = (hashCode + (cancelHandler2 == null ? 0 : cancelHandler2.hashCode())) * 31;
        Function1 function1 = this.onCancellation;
        int hashCode3 = (hashCode2 + (function1 == null ? 0 : function1.hashCode())) * 31;
        Object obj2 = this.idempotentResume;
        int hashCode4 = (hashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.cancelCause;
        if (th != null) {
            i = th.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.result + ", cancelHandler=" + this.cancelHandler + ", onCancellation=" + this.onCancellation + ", idempotentResume=" + this.idempotentResume + ", cancelCause=" + this.cancelCause + ')';
    }

    public CompletedContinuation(Object obj, CancelHandler cancelHandler2, Function1 function1, Object obj2, Throwable th) {
        this.result = obj;
        this.cancelHandler = cancelHandler2;
        this.onCancellation = function1;
        this.idempotentResume = obj2;
        this.cancelCause = th;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler2, Function1 function1, Object obj2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? null : cancelHandler2, (i & 4) != 0 ? null : function1, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? null : th);
    }

    public final boolean getCancelled() {
        return this.cancelCause != null;
    }

    public final void invokeHandlers(CancellableContinuationImpl cancellableContinuationImpl, Throwable th) {
        CancelHandler cancelHandler2 = this.cancelHandler;
        if (cancelHandler2 != null) {
            cancellableContinuationImpl.callCancelHandler(cancelHandler2, th);
        }
        Function1 function1 = this.onCancellation;
        if (function1 != null) {
            cancellableContinuationImpl.callOnCancellation(function1, th);
        }
    }
}
