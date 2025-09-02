package io.branch.coroutines;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import io.branch.referral.BranchLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class AdvertisingIdsKt$getGoogleAdvertisingInfoObject$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AdvertisingIdsKt$getGoogleAdvertisingInfoObject$2(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AdvertisingIdsKt$getGoogleAdvertisingInfoObject$2(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AdvertisingIdsKt$getGoogleAdvertisingInfoObject$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                return AdvertisingIdClient.getAdvertisingIdInfo(this.$context);
            } catch (Exception e) {
                BranchLogger.w("Caught getGoogleAdvertisingInfoObject exception: " + e);
                return null;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
