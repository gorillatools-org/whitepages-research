package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

public class CancellableContinuationImpl extends DispatchedTask implements CancellableContinuation, CoroutineStackFrame, Waiter {
    private static final AtomicIntegerFieldUpdater _decisionAndIndex$FU;
    private static final AtomicReferenceFieldUpdater _parentHandle$FU;
    private static final AtomicReferenceFieldUpdater _state$FU;
    private volatile int _decisionAndIndex = 536870911;
    private volatile Object _parentHandle;
    private volatile Object _state = Active.INSTANCE;
    private final CoroutineContext context;
    private final Continuation delegate;

    static {
        Class<CancellableContinuationImpl> cls = CancellableContinuationImpl.class;
        _decisionAndIndex$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_decisionAndIndex");
        Class<Object> cls2 = Object.class;
        _state$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_state");
        _parentHandle$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_parentHandle");
    }

    public final Continuation getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    public CancellableContinuationImpl(Continuation continuation, int i) {
        super(i);
        this.delegate = continuation;
        this.context = continuation.getContext();
    }

    public CoroutineContext getContext() {
        return this.context;
    }

    private final DisposableHandle getParentHandle() {
        return (DisposableHandle) _parentHandle$FU.get(this);
    }

    public final Object getState$kotlinx_coroutines_core() {
        return _state$FU.get(this);
    }

    public boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof NotCompleted);
    }

    private final String getStateDebugRepresentation() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof NotCompleted) {
            return "Active";
        }
        if (state$kotlinx_coroutines_core instanceof CancelledContinuation) {
            return "Cancelled";
        }
        return "Completed";
    }

    public void initCancellability() {
        DisposableHandle installParentHandle = installParentHandle();
        if (installParentHandle != null && isCompleted()) {
            installParentHandle.dispose();
            _parentHandle$FU.set(this, NonDisposableHandle.INSTANCE);
        }
    }

    private final boolean isReusable() {
        if (DispatchedTaskKt.isReusableMode(this.resumeMode)) {
            Continuation continuation = this.delegate;
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (((DispatchedContinuation) continuation).isReusable$kotlinx_coroutines_core()) {
                return true;
            }
        }
        return false;
    }

    public final boolean resetStateReusable() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if (!(obj instanceof CompletedContinuation) || ((CompletedContinuation) obj).idempotentResume == null) {
            _decisionAndIndex$FU.set(this, 536870911);
            atomicReferenceFieldUpdater.set(this, Active.INSTANCE);
            return true;
        }
        detachChild$kotlinx_coroutines_core();
        return false;
    }

    public CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    public Object takeState$kotlinx_coroutines_core() {
        return getState$kotlinx_coroutines_core();
    }

    public void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof NotCompleted) {
                throw new IllegalStateException("Not completed");
            } else if (!(obj2 instanceof CompletedExceptionally)) {
                if (obj2 instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                    if (!completedContinuation.getCancelled()) {
                        Throwable th2 = th;
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, CompletedContinuation.copy$default(completedContinuation, (Object) null, (CancelHandler) null, (Function1) null, (Object) null, th, 15, (Object) null))) {
                            completedContinuation.invokeHandlers(this, th2);
                            return;
                        }
                    } else {
                        throw new IllegalStateException("Must be called at most once");
                    }
                } else {
                    Throwable th3 = th;
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, new CompletedContinuation(obj2, (CancelHandler) null, (Function1) null, (Object) null, th, 14, (DefaultConstructorMarker) null))) {
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    private final boolean cancelLater(Throwable th) {
        if (!isReusable()) {
            return false;
        }
        Continuation continuation = this.delegate;
        Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return ((DispatchedContinuation) continuation).postponeCancellation$kotlinx_coroutines_core(th);
    }

    public boolean cancel(Throwable th) {
        Object obj;
        boolean z;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            z = false;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            if ((obj instanceof CancelHandler) || (obj instanceof Segment)) {
                z = true;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, new CancelledContinuation(this, th, z)));
        NotCompleted notCompleted = (NotCompleted) obj;
        if (notCompleted instanceof CancelHandler) {
            callCancelHandler((CancelHandler) obj, th);
        } else if (notCompleted instanceof Segment) {
            callSegmentOnCancellation((Segment) obj, th);
        }
        detachChildIfNonResuable();
        dispatchResume(this.resumeMode);
        return true;
    }

    public final void parentCancelled$kotlinx_coroutines_core(Throwable th) {
        if (!cancelLater(th)) {
            cancel(th);
            detachChildIfNonResuable();
        }
    }

    public final void callCancelHandler(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.invoke(th);
        } catch (Throwable th2) {
            CoroutineContext context2 = getContext();
            CoroutineExceptionHandlerKt.handleCoroutineException(context2, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    private final void callSegmentOnCancellation(Segment segment, Throwable th) {
        int i = _decisionAndIndex$FU.get(this) & 536870911;
        if (i != 536870911) {
            try {
                segment.onCancellation(i, th, getContext());
            } catch (Throwable th2) {
                CoroutineContext context2 = getContext();
                CoroutineExceptionHandlerKt.handleCoroutineException(context2, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
            }
        } else {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
    }

    public final void callOnCancellation(Function1 function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineContext context2 = getContext();
            CoroutineExceptionHandlerKt.handleCoroutineException(context2, new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public Throwable getContinuationCancellationCause(Job job) {
        return job.getCancellationException();
    }

    private final boolean trySuspend() {
        int i;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _decisionAndIndex$FU;
        do {
            i = atomicIntegerFieldUpdater.get(this);
            int i2 = i >> 29;
            if (i2 != 0) {
                if (i2 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended");
            }
        } while (!_decisionAndIndex$FU.compareAndSet(this, i, 536870912 + (536870911 & i)));
        return true;
    }

    private final boolean tryResume() {
        int i;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _decisionAndIndex$FU;
        do {
            i = atomicIntegerFieldUpdater.get(this);
            int i2 = i >> 29;
            if (i2 != 0) {
                if (i2 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed");
            }
        } while (!_decisionAndIndex$FU.compareAndSet(this, i, 1073741824 + (536870911 & i)));
        return true;
    }

    public final Object getResult() {
        Job job;
        boolean isReusable = isReusable();
        if (trySuspend()) {
            if (getParentHandle() == null) {
                installParentHandle();
            }
            if (isReusable) {
                releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            }
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (isReusable) {
            releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        }
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else if (!DispatchedTaskKt.isCancellableMode(this.resumeMode) || (job = (Job) getContext().get(Job.Key)) == null || job.isActive()) {
            return getSuccessfulResult$kotlinx_coroutines_core(state$kotlinx_coroutines_core);
        } else {
            CancellationException cancellationException = job.getCancellationException();
            cancelCompletedResult$kotlinx_coroutines_core(state$kotlinx_coroutines_core, cancellationException);
            throw cancellationException;
        }
    }

    private final DisposableHandle installParentHandle() {
        Job job = (Job) getContext().get(Job.Key);
        if (job == null) {
            return null;
        }
        DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ChildContinuation(this), 2, (Object) null);
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_parentHandle$FU, this, (Object) null, invokeOnCompletion$default);
        return invokeOnCompletion$default;
    }

    public final void releaseClaimedReusableContinuation$kotlinx_coroutines_core() {
        Throwable tryReleaseClaimedContinuation$kotlinx_coroutines_core;
        Continuation continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation != null && (tryReleaseClaimedContinuation$kotlinx_coroutines_core = dispatchedContinuation.tryReleaseClaimedContinuation$kotlinx_coroutines_core(this)) != null) {
            detachChild$kotlinx_coroutines_core();
            cancel(tryReleaseClaimedContinuation$kotlinx_coroutines_core);
        }
    }

    public void resumeWith(Object obj) {
        resumeImpl$default(this, CompletionStateKt.toState(obj, (CancellableContinuation) this), this.resumeMode, (Function1) null, 4, (Object) null);
    }

    public void resume(Object obj, Function1 function1) {
        resumeImpl(obj, this.resumeMode, function1);
    }

    public void invokeOnCancellation(Segment segment, int i) {
        int i2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _decisionAndIndex$FU;
        do {
            i2 = atomicIntegerFieldUpdater.get(this);
            if ((i2 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, ((i2 >> 29) << 29) + i));
        invokeOnCancellationImpl(segment);
    }

    public void invokeOnCancellation(Function1 function1) {
        invokeOnCancellationImpl(makeCancelHandler(function1));
    }

    private final void invokeOnCancellationImpl(Object obj) {
        Object obj2 = obj;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        while (true) {
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (!(obj3 instanceof Active)) {
                if (obj3 instanceof CancelHandler ? true : obj3 instanceof Segment) {
                    multipleHandlersError(obj2, obj3);
                } else if (obj3 instanceof CompletedExceptionally) {
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj3;
                    if (!completedExceptionally.makeHandled()) {
                        multipleHandlersError(obj2, obj3);
                    }
                    if (obj3 instanceof CancelledContinuation) {
                        Throwable th = null;
                        if (!(obj3 instanceof CompletedExceptionally)) {
                            completedExceptionally = null;
                        }
                        if (completedExceptionally != null) {
                            th = completedExceptionally.cause;
                        }
                        if (obj2 instanceof CancelHandler) {
                            callCancelHandler((CancelHandler) obj2, th);
                            return;
                        }
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                        callSegmentOnCancellation((Segment) obj2, th);
                        return;
                    }
                    return;
                } else if (obj3 instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj3;
                    if (completedContinuation.cancelHandler != null) {
                        multipleHandlersError(obj2, obj3);
                    }
                    if (!(obj2 instanceof Segment)) {
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                        CancelHandler cancelHandler = (CancelHandler) obj2;
                        if (completedContinuation.getCancelled()) {
                            callCancelHandler(cancelHandler, completedContinuation.cancelCause);
                            return;
                        } else {
                            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj3, CompletedContinuation.copy$default(completedContinuation, (Object) null, cancelHandler, (Function1) null, (Object) null, (Throwable) null, 29, (Object) null))) {
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                } else if (!(obj2 instanceof Segment)) {
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj3, new CompletedContinuation(obj3, (CancelHandler) obj2, (Function1) null, (Object) null, (Throwable) null, 28, (DefaultConstructorMarker) null))) {
                        return;
                    }
                } else {
                    return;
                }
            } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj3, obj2)) {
                return;
            }
        }
    }

    private final void multipleHandlersError(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    private final CancelHandler makeCancelHandler(Function1 function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
    }

    private final void dispatchResume(int i) {
        if (!tryResume()) {
            DispatchedTaskKt.dispatch(this, i);
        }
    }

    private final Object resumedState(NotCompleted notCompleted, Object obj, int i, Function1 function1, Object obj2) {
        if (obj instanceof CompletedExceptionally) {
            return obj;
        }
        if (!DispatchedTaskKt.isCancellableMode(i) && obj2 == null) {
            return obj;
        }
        if (function1 == null && !(notCompleted instanceof CancelHandler) && obj2 == null) {
            return obj;
        }
        return new CompletedContinuation(obj, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function1, obj2, (Throwable) null, 16, (DefaultConstructorMarker) null);
    }

    static /* synthetic */ void resumeImpl$default(CancellableContinuationImpl cancellableContinuationImpl, Object obj, int i, Function1 function1, int i2, Object obj2) {
        if (obj2 == null) {
            if ((i2 & 4) != 0) {
                function1 = null;
            }
            cancellableContinuationImpl.resumeImpl(obj, i, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
    }

    private final void resumeImpl(Object obj, int i, Function1 function1) {
        Object obj2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        do {
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof NotCompleted) {
            } else {
                if (obj2 instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                    if (cancelledContinuation.makeResumed()) {
                        if (function1 != null) {
                            callOnCancellation(function1, cancelledContinuation.cause);
                            return;
                        }
                        return;
                    }
                }
                alreadyResumedError(obj);
                throw new KotlinNothingValueException();
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, resumedState((NotCompleted) obj2, obj, i, function1, (Object) null)));
        detachChildIfNonResuable();
        dispatchResume(i);
    }

    private final Symbol tryResumeImpl(Object obj, Object obj2, Function1 function1) {
        Object obj3;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        do {
            obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj3 instanceof NotCompleted) {
            } else if (!(obj3 instanceof CompletedContinuation) || obj2 == null || ((CompletedContinuation) obj3).idempotentResume != obj2) {
                return null;
            } else {
                return CancellableContinuationImplKt.RESUME_TOKEN;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj3, resumedState((NotCompleted) obj3, obj, this.resumeMode, function1, obj2)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    private final Void alreadyResumedError(Object obj) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
    }

    private final void detachChildIfNonResuable() {
        if (!isReusable()) {
            detachChild$kotlinx_coroutines_core();
        }
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle parentHandle = getParentHandle();
        if (parentHandle != null) {
            parentHandle.dispose();
            _parentHandle$FU.set(this, NonDisposableHandle.INSTANCE);
        }
    }

    public Object tryResume(Object obj, Object obj2, Function1 function1) {
        return tryResumeImpl(obj, obj2, function1);
    }

    public void completeResume(Object obj) {
        dispatchResume(this.resumeMode);
    }

    public Object getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        return obj instanceof CompletedContinuation ? ((CompletedContinuation) obj).result : obj;
    }

    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        Throwable exceptionalResult$kotlinx_coroutines_core = super.getExceptionalResult$kotlinx_coroutines_core(obj);
        if (exceptionalResult$kotlinx_coroutines_core != null) {
            return exceptionalResult$kotlinx_coroutines_core;
        }
        return null;
    }

    public String toString() {
        return nameString() + '(' + DebugStringsKt.toDebugString(this.delegate) + "){" + getStateDebugRepresentation() + "}@" + DebugStringsKt.getHexAddress(this);
    }

    /* access modifiers changed from: protected */
    public String nameString() {
        return "CancellableContinuation";
    }
}
