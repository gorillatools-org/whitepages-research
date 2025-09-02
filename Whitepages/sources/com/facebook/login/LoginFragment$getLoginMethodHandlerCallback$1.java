package com.facebook.login;

import androidx.activity.result.ActivityResult;
import androidx.fragment.app.FragmentActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class LoginFragment$getLoginMethodHandlerCallback$1 extends Lambda implements Function1 {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ LoginFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LoginFragment$getLoginMethodHandlerCallback$1(LoginFragment loginFragment, FragmentActivity fragmentActivity) {
        super(1);
        this.this$0 = loginFragment;
        this.$activity = fragmentActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ActivityResult) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(activityResult, "result");
        if (activityResult.getResultCode() == -1) {
            this.this$0.getLoginClient().onActivityResult(LoginClient.Companion.getLoginRequestCode(), activityResult.getResultCode(), activityResult.getData());
        } else {
            this.$activity.finish();
        }
    }
}
