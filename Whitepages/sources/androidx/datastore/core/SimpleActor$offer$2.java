package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class SimpleActor$offer$2 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ SimpleActor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SimpleActor$offer$2(SimpleActor simpleActor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = simpleActor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SimpleActor$offer$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((SimpleActor$offer$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0050 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0069  */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x005d
        L_0x0012:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x001a:
            java.lang.Object r1 = r5.L$0
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0051
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.datastore.core.SimpleActor r6 = r5.this$0
            androidx.datastore.core.AtomicInt r6 = r6.remainingMessages
            int r6 = r6.get()
            if (r6 <= 0) goto L_0x006c
        L_0x0031:
            androidx.datastore.core.SimpleActor r6 = r5.this$0
            kotlinx.coroutines.CoroutineScope r6 = r6.scope
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r6)
            androidx.datastore.core.SimpleActor r6 = r5.this$0
            kotlin.jvm.functions.Function2 r1 = r6.consumeMessage
            androidx.datastore.core.SimpleActor r6 = r5.this$0
            kotlinx.coroutines.channels.Channel r6 = r6.messageQueue
            r5.L$0 = r1
            r5.label = r3
            java.lang.Object r6 = r6.receive(r5)
            if (r6 != r0) goto L_0x0051
            return r0
        L_0x0051:
            r4 = 0
            r5.L$0 = r4
            r5.label = r2
            java.lang.Object r6 = r1.invoke(r6, r5)
            if (r6 != r0) goto L_0x005d
            return r0
        L_0x005d:
            androidx.datastore.core.SimpleActor r6 = r5.this$0
            androidx.datastore.core.AtomicInt r6 = r6.remainingMessages
            int r6 = r6.decrementAndGet()
            if (r6 != 0) goto L_0x0031
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x006c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SimpleActor$offer$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
