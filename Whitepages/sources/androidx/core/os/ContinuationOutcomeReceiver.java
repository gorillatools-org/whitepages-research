package androidx.core.os;

import android.os.OutcomeReceiver;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;

final class ContinuationOutcomeReceiver extends AtomicBoolean implements OutcomeReceiver {
    private final Continuation continuation;

    public ContinuationOutcomeReceiver(Continuation continuation2) {
        super(false);
        this.continuation = continuation2;
    }

    public void onResult(Object obj) {
        if (compareAndSet(false, true)) {
            this.continuation.resumeWith(Result.m866constructorimpl(obj));
        }
    }

    public void onError(Throwable th) {
        if (compareAndSet(false, true)) {
            Continuation continuation2 = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation2.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(th)));
        }
    }

    public String toString() {
        return "ContinuationOutcomeReceiver(outcomeReceived = " + get() + ')';
    }
}
