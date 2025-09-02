package io.branch.coroutines;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import io.branch.referral.Branch;
import io.branch.referral.BranchLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class DeviceSignalsKt$getUserAgentSync$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeviceSignalsKt$getUserAgentSync$2(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceSignalsKt$getUserAgentSync$2(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DeviceSignalsKt$getUserAgentSync$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!TextUtils.isEmpty(Branch._userAgentString)) {
                BranchLogger.v("UserAgent cached " + Branch._userAgentString);
                return Branch._userAgentString;
            }
            String str = null;
            try {
                BranchLogger.v("Begin getUserAgentSync " + Thread.currentThread());
                WebView webView = new WebView(this.$context);
                str = webView.getSettings().getUserAgentString();
                webView.destroy();
                BranchLogger.v("End getUserAgentSync " + Thread.currentThread() + ' ' + str);
                return str;
            } catch (Exception e) {
                BranchLogger.e("Failed to retrieve userAgent string. " + e.getMessage());
                return str;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
