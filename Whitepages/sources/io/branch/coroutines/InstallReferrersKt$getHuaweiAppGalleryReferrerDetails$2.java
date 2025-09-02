package io.branch.coroutines;

import android.content.Context;
import com.huawei.hms.ads.installreferrer.api.InstallReferrerClient;
import com.huawei.hms.ads.installreferrer.api.InstallReferrerStateListener;
import io.branch.data.InstallReferrerResult;
import io.branch.referral.BranchLogger;
import io.branch.referral.util.DependencyUtilsKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

final class InstallReferrersKt$getHuaweiAppGalleryReferrerDetails$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallReferrersKt$getHuaweiAppGalleryReferrerDetails$2(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new InstallReferrersKt$getHuaweiAppGalleryReferrerDetails$2(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((InstallReferrersKt$getHuaweiAppGalleryReferrerDetails$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!DependencyUtilsKt.classExists("com.huawei.hms.ads.installreferrer.api.InstallReferrerClient")) {
                return null;
            }
            final CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
            final InstallReferrerClient build = InstallReferrerClient.newBuilder(this.$context).build();
            build.startConnection(new InstallReferrerStateListener() {
            });
            this.label = 1;
            obj = CompletableDeferred$default.await(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                BranchLogger.w("Caught getHuaweiAppGalleryReferrerDetails exception: " + e);
                return null;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return (InstallReferrerResult) obj;
    }
}
