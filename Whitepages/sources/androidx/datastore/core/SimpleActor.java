package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ClosedSendChannelException;

public final class SimpleActor {
    /* access modifiers changed from: private */
    public final Function2 consumeMessage;
    /* access modifiers changed from: private */
    public final Channel messageQueue = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
    /* access modifiers changed from: private */
    public final AtomicInt remainingMessages = new AtomicInt(0);
    /* access modifiers changed from: private */
    public final CoroutineScope scope;

    public SimpleActor(CoroutineScope coroutineScope, final Function1 function1, final Function2 function2, Function2 function22) {
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        Intrinsics.checkNotNullParameter(function1, "onComplete");
        Intrinsics.checkNotNullParameter(function2, "onUndeliveredElement");
        Intrinsics.checkNotNullParameter(function22, "consumeMessage");
        this.scope = coroutineScope;
        this.consumeMessage = function22;
        Job job = (Job) coroutineScope.getCoroutineContext().get(Job.Key);
        if (job != null) {
            job.invokeOnCompletion(new Function1() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    Unit unit;
                    function1.invoke(th);
                    this.messageQueue.close(th);
                    do {
                        Object r0 = ChannelResult.m909getOrNullimpl(this.messageQueue.m922tryReceivePtdJZtk());
                        if (r0 != null) {
                            function2.invoke(r0, th);
                            unit = Unit.INSTANCE;
                            continue;
                        } else {
                            unit = null;
                            continue;
                        }
                    } while (unit != null);
                }
            });
        }
    }

    public final void offer(Object obj) {
        Object r7 = this.messageQueue.m923trySendJP2dKIU(obj);
        if (r7 instanceof ChannelResult.Closed) {
            Throwable r72 = ChannelResult.m908exceptionOrNullimpl(r7);
            if (r72 == null) {
                r72 = new ClosedSendChannelException("Channel was closed normally");
            }
            throw r72;
        } else if (!ChannelResult.m912isSuccessimpl(r7)) {
            throw new IllegalStateException("Check failed.");
        } else if (this.remainingMessages.getAndIncrement() == 0) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new SimpleActor$offer$2(this, (Continuation) null), 3, (Object) null);
        }
    }
}
