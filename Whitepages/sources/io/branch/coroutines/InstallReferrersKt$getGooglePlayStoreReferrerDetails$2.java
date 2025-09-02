package io.branch.coroutines;

import android.content.Context;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import io.branch.data.InstallReferrerResult;
import io.branch.referral.BranchLogger;
import io.branch.referral.Defines$Jsonkey;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

final class InstallReferrersKt$getGooglePlayStoreReferrerDetails$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallReferrersKt$getGooglePlayStoreReferrerDetails$2(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new InstallReferrersKt$getGooglePlayStoreReferrerDetails$2(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((InstallReferrersKt$getGooglePlayStoreReferrerDetails$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
            final InstallReferrerClient build = InstallReferrerClient.newBuilder(this.$context.getApplicationContext()).build();
            build.startConnection(new InstallReferrerStateListener() {
                public void onInstallReferrerSetupFinished(int i) {
                    BranchLogger.w("Caught getGooglePlayStoreReferrerDetails onInstallReferrerSetupFinished response code: " + i);
                    InstallReferrerResult installReferrerResult = null;
                    if (i == 0) {
                        CompletableDeferred completableDeferred = CompletableDeferred$default;
                        try {
                            ReferrerDetails installReferrer = build.getInstallReferrer();
                            installReferrerResult = new InstallReferrerResult(Defines$Jsonkey.Google_Play_Store.getKey(), installReferrer.getInstallBeginTimestampSeconds(), installReferrer.getInstallReferrer(), installReferrer.getReferrerClickTimestampSeconds(), false, 16, (DefaultConstructorMarker) null);
                        } catch (Exception e) {
                            BranchLogger.w("Caught getGooglePlayStoreReferrerDetails installReferrer exception: " + e);
                        }
                        completableDeferred.complete(installReferrerResult);
                    } else {
                        CompletableDeferred$default.complete((Object) null);
                    }
                    build.endConnection();
                }

                public void onInstallReferrerServiceDisconnected() {
                    if (!CompletableDeferred$default.isCompleted()) {
                        CompletableDeferred$default.complete((Object) null);
                    }
                }
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
                BranchLogger.w("Caught getGooglePlayStoreReferrerDetails exception: " + e);
                return null;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return (InstallReferrerResult) obj;
    }
}
