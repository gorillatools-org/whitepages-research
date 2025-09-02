package io.branch.coroutines;

import android.content.Context;
import io.branch.referral.BranchLogger;
import io.branch.referral.PrefHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

final class InstallReferrersKt$getMetaInstallReferrerDetails$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallReferrersKt$getMetaInstallReferrerDetails$2(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new InstallReferrersKt$getMetaInstallReferrerDetails$2(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((InstallReferrersKt$getMetaInstallReferrerDetails$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                String str = PrefHelper.fbAppId_;
                if (str != null) {
                    if (str.length() != 0) {
                        Context context = this.$context;
                        Intrinsics.checkNotNullExpressionValue(str, "fbAppID");
                        return InstallReferrersKt.queryMetaInstallReferrer(context, str);
                    }
                }
                BranchLogger.d("No Facebook App ID provided. Can't check for Meta Install Referrer");
                return null;
            } catch (Exception e) {
                BranchLogger.e("Exception in getMetaInstallReferrerDetails: " + e);
                return null;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
