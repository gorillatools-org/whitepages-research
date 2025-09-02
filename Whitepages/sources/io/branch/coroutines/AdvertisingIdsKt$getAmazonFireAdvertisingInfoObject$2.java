package io.branch.coroutines;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import io.branch.referral.BranchLogger;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class AdvertisingIdsKt$getAmazonFireAdvertisingInfoObject$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AdvertisingIdsKt$getAmazonFireAdvertisingInfoObject$2(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AdvertisingIdsKt$getAmazonFireAdvertisingInfoObject$2(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AdvertisingIdsKt$getAmazonFireAdvertisingInfoObject$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ContentResolver contentResolver = this.$context.getContentResolver();
                return new Pair(Boxing.boxInt(Settings.Secure.getInt(contentResolver, "limit_ad_tracking")), Settings.Secure.getString(contentResolver, "advertising_id"));
            } catch (Exception e) {
                BranchLogger.w("Caught getAmazonFireAdvertisingInfo exception: " + e);
                return null;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
