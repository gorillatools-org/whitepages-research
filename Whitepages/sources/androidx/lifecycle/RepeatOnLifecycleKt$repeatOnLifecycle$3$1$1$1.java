package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;

final class RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1 implements LifecycleEventObserver {
    final /* synthetic */ CoroutineScope $$this$coroutineScope;
    final /* synthetic */ Function2 $block;
    final /* synthetic */ Lifecycle.Event $cancelWorkEvent;
    final /* synthetic */ CancellableContinuation $cont;
    final /* synthetic */ Ref$ObjectRef $launchedJob;
    final /* synthetic */ Mutex $mutex;
    final /* synthetic */ Lifecycle.Event $startWorkEvent;

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == this.$startWorkEvent) {
            Ref$ObjectRef ref$ObjectRef = this.$launchedJob;
            CoroutineScope coroutineScope = this.$$this$coroutineScope;
            final Mutex mutex = this.$mutex;
            final Function2 function2 = this.$block;
            ref$ObjectRef.element = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation) null), 3, (Object) null);
            return;
        }
        if (event == this.$cancelWorkEvent) {
            Job job = (Job) this.$launchedJob.element;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.$launchedJob.element = null;
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            CancellableContinuation cancellableContinuation = this.$cont;
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m866constructorimpl(Unit.INSTANCE));
        }
    }

    /* renamed from: androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1  reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        Object L$0;
        Object L$1;
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(mutex, function2, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Throwable th;
            Mutex mutex;
            Mutex mutex2;
            Function2 function2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                mutex2 = mutex;
                function2 = function2;
                this.L$0 = mutex2;
                this.L$1 = function2;
                this.label = 1;
                if (mutex2.lock((Object) null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                function2 = (Function2) this.L$1;
                ResultKt.throwOnFailure(obj);
                mutex2 = (Mutex) this.L$0;
            } else if (i == 2) {
                mutex = (Mutex) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    Unit unit = Unit.INSTANCE;
                    mutex.unlock((Object) null);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$1 repeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$1 = new RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$1(function2, (Continuation) null);
                this.L$0 = mutex2;
                this.L$1 = null;
                this.label = 2;
                if (CoroutineScopeKt.coroutineScope(repeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutex = mutex2;
                Unit unit2 = Unit.INSTANCE;
                mutex.unlock((Object) null);
                return Unit.INSTANCE;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                mutex = mutex2;
                th = th4;
                mutex.unlock((Object) null);
                throw th;
            }
        }
    }
}
