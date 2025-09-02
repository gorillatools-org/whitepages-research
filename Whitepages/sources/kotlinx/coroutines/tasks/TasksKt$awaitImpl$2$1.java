package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;

final class TasksKt$awaitImpl$2$1 implements OnCompleteListener {
    final /* synthetic */ CancellableContinuation $cont;

    TasksKt$awaitImpl$2$1(CancellableContinuation cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public final void onComplete(Task task) {
        Exception exception = task.getException();
        if (exception != null) {
            CancellableContinuation cancellableContinuation = this.$cont;
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(exception)));
        } else if (task.isCanceled()) {
            CancellableContinuation.DefaultImpls.cancel$default(this.$cont, (Throwable) null, 1, (Object) null);
        } else {
            CancellableContinuation cancellableContinuation2 = this.$cont;
            Result.Companion companion2 = Result.Companion;
            cancellableContinuation2.resumeWith(Result.m866constructorimpl(task.getResult()));
        }
    }
}
