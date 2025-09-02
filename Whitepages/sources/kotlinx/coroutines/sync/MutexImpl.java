package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

public class MutexImpl extends SemaphoreImpl implements Mutex {
    /* access modifiers changed from: private */
    public static final AtomicReferenceFieldUpdater owner$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "owner");
    private final Function3 onSelectCancellationUnlockConstructor;
    private volatile Object owner;

    public Object lock(Object obj, Continuation continuation) {
        return lock$suspendImpl(this, obj, continuation);
    }

    public MutexImpl(boolean z) {
        super(1, z ? 1 : 0);
        Symbol symbol;
        if (z) {
            symbol = null;
        } else {
            symbol = MutexKt.NO_OWNER;
        }
        this.owner = symbol;
        this.onSelectCancellationUnlockConstructor = new MutexImpl$onSelectCancellationUnlockConstructor$1(this);
    }

    public boolean isLocked() {
        return getAvailablePermits() == 0;
    }

    private final int holdsLockImpl(Object obj) {
        while (isLocked()) {
            Object obj2 = owner$FU.get(this);
            if (obj2 != MutexKt.NO_OWNER) {
                return obj2 == obj ? 1 : 2;
            }
        }
        return 0;
    }

    static /* synthetic */ Object lock$suspendImpl(MutexImpl mutexImpl, Object obj, Continuation continuation) {
        if (mutexImpl.tryLock(obj)) {
            return Unit.INSTANCE;
        }
        Object lockSuspend = mutexImpl.lockSuspend(obj, continuation);
        return lockSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? lockSuspend : Unit.INSTANCE;
    }

    public boolean tryLock(Object obj) {
        int tryLockImpl = tryLockImpl(obj);
        if (tryLockImpl == 0) {
            return true;
        }
        if (tryLockImpl == 1) {
            return false;
        }
        if (tryLockImpl != 2) {
            throw new IllegalStateException("unexpected");
        }
        throw new IllegalStateException(("This mutex is already locked by the specified owner: " + obj).toString());
    }

    private final int tryLockImpl(Object obj) {
        while (!tryAcquire()) {
            if (obj == null) {
                return 1;
            }
            int holdsLockImpl = holdsLockImpl(obj);
            if (holdsLockImpl == 1) {
                return 2;
            }
            if (holdsLockImpl == 2) {
                return 1;
            }
        }
        owner$FU.set(this, obj);
        return 0;
    }

    public void unlock(Object obj) {
        while (isLocked()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = owner$FU;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 != MutexKt.NO_OWNER) {
                if (obj2 != obj && obj != null) {
                    throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj2, MutexKt.NO_OWNER)) {
                    release();
                    return;
                }
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }

    private final class CancellableContinuationWithOwner implements CancellableContinuation, Waiter {
        public final CancellableContinuationImpl cont;
        public final Object owner;

        public boolean cancel(Throwable th) {
            return this.cont.cancel(th);
        }

        public void completeResume(Object obj) {
            this.cont.completeResume(obj);
        }

        public CoroutineContext getContext() {
            return this.cont.getContext();
        }

        public void invokeOnCancellation(Function1 function1) {
            this.cont.invokeOnCancellation(function1);
        }

        public void invokeOnCancellation(Segment segment, int i) {
            this.cont.invokeOnCancellation(segment, i);
        }

        public void resumeWith(Object obj) {
            this.cont.resumeWith(obj);
        }

        public CancellableContinuationWithOwner(CancellableContinuationImpl cancellableContinuationImpl, Object obj) {
            this.cont = cancellableContinuationImpl;
            this.owner = obj;
        }

        public Object tryResume(Unit unit, Object obj, Function1 function1) {
            Object tryResume = this.cont.tryResume(unit, obj, new MutexImpl$CancellableContinuationWithOwner$tryResume$token$1(MutexImpl.this, this));
            if (tryResume != null) {
                MutexImpl.owner$FU.set(MutexImpl.this, this.owner);
            }
            return tryResume;
        }

        public void resume(Unit unit, Function1 function1) {
            MutexImpl.owner$FU.set(MutexImpl.this, this.owner);
            this.cont.resume(unit, new MutexImpl$CancellableContinuationWithOwner$resume$2(MutexImpl.this, this));
        }
    }

    public String toString() {
        return "Mutex@" + DebugStringsKt.getHexAddress(this) + "[isLocked=" + isLocked() + ",owner=" + owner$FU.get(this) + ']';
    }

    private final Object lockSuspend(Object obj, Continuation continuation) {
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        try {
            acquire(new CancellableContinuationWithOwner(orCreateCancellableContinuation, obj));
            Object result = orCreateCancellableContinuation.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        } catch (Throwable th) {
            orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw th;
        }
    }
}
