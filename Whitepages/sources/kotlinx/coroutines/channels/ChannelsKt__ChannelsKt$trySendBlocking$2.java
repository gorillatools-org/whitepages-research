package kotlinx.coroutines.channels;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class ChannelsKt__ChannelsKt$trySendBlocking$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object $element;
    final /* synthetic */ SendChannel $this_trySendBlocking;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__ChannelsKt$trySendBlocking$2(SendChannel sendChannel, Object obj, Continuation continuation) {
        super(2, continuation);
        this.$this_trySendBlocking = sendChannel;
        this.$element = obj;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        ChannelsKt__ChannelsKt$trySendBlocking$2 channelsKt__ChannelsKt$trySendBlocking$2 = new ChannelsKt__ChannelsKt$trySendBlocking$2(this.$this_trySendBlocking, this.$element, continuation);
        channelsKt__ChannelsKt$trySendBlocking$2.L$0 = obj;
        return channelsKt__ChannelsKt$trySendBlocking$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((ChannelsKt__ChannelsKt$trySendBlocking$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object obj3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            SendChannel sendChannel = this.$this_trySendBlocking;
            Object obj4 = this.$element;
            Result.Companion companion = Result.Companion;
            this.label = 1;
            if (sendChannel.send(obj4, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m866constructorimpl(ResultKt.createFailure(th));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        obj2 = Result.m866constructorimpl(Unit.INSTANCE);
        if (Result.m869isSuccessimpl(obj2)) {
            obj3 = ChannelResult.Companion.m917successJP2dKIU(Unit.INSTANCE);
        } else {
            obj3 = ChannelResult.Companion.m915closedJP2dKIU(Result.m867exceptionOrNullimpl(obj2));
        }
        return ChannelResult.m905boximpl(obj3);
    }
}
