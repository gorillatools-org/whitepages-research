package io.branch.coroutines;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebSettings;
import io.branch.referral.Branch;
import io.branch.referral.BranchLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

final class DeviceSignalsKt$getUserAgentAsync$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeviceSignalsKt$getUserAgentAsync$2(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceSignalsKt$getUserAgentAsync$2(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DeviceSignalsKt$getUserAgentAsync$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Mutex mutex;
        Context context;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = DeviceSignalsKt.getMutex();
            Context context2 = this.$context;
            this.L$0 = mutex;
            this.L$1 = context2;
            this.label = 1;
            if (mutex.lock((Object) null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            context = context2;
        } else if (i == 1) {
            context = (Context) this.L$1;
            mutex = (Mutex) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            if (!TextUtils.isEmpty(Branch._userAgentString)) {
                BranchLogger.v("UserAgent cached " + Branch._userAgentString);
                str = Branch._userAgentString;
            } else {
                try {
                    BranchLogger.v("Begin getUserAgentAsync " + Thread.currentThread());
                    str = WebSettings.getDefaultUserAgent(context);
                    try {
                        BranchLogger.v("End getUserAgentAsync " + Thread.currentThread() + ' ' + str);
                    } catch (Exception e) {
                        e = e;
                    }
                } catch (Exception e2) {
                    e = e2;
                    str = null;
                    BranchLogger.e("Failed to retrieve userAgent string. " + e.getMessage());
                    return str;
                }
            }
            return str;
        } finally {
            mutex.unlock((Object) null);
        }
    }
}
