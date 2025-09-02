package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.CancellationTokenSource;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class TasksKt$awaitImpl$2$2 extends Lambda implements Function1 {
    final /* synthetic */ CancellationTokenSource $cancellationTokenSource;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TasksKt$awaitImpl$2$2(CancellationTokenSource cancellationTokenSource) {
        super(1);
        this.$cancellationTokenSource = cancellationTokenSource;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.$cancellationTokenSource.cancel();
    }
}
