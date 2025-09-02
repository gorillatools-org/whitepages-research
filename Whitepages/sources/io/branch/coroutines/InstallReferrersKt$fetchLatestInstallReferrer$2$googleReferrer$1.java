package io.branch.coroutines;

import android.content.Context;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class InstallReferrersKt$fetchLatestInstallReferrer$2$googleReferrer$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallReferrersKt$fetchLatestInstallReferrer$2$googleReferrer$1(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new InstallReferrersKt$fetchLatestInstallReferrer$2$googleReferrer$1(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((InstallReferrersKt$fetchLatestInstallReferrer$2$googleReferrer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.$context;
            this.label = 1;
            obj = InstallReferrersKt.getGooglePlayStoreReferrerDetails(context, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
