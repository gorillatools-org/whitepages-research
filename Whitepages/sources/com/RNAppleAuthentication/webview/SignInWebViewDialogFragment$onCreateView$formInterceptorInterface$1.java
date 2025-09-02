package com.RNAppleAuthentication.webview;

import com.RNAppleAuthentication.SignInWithAppleResult;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* synthetic */ class SignInWebViewDialogFragment$onCreateView$formInterceptorInterface$1 extends FunctionReferenceImpl implements Function1 {
    SignInWebViewDialogFragment$onCreateView$formInterceptorInterface$1(Object obj) {
        super(1, obj, SignInWebViewDialogFragment.class, "onCallback", "onCallback(Lcom/RNAppleAuthentication/SignInWithAppleResult;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SignInWithAppleResult) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(SignInWithAppleResult signInWithAppleResult) {
        Intrinsics.checkNotNullParameter(signInWithAppleResult, "p0");
        ((SignInWebViewDialogFragment) this.receiver).onCallback(signInWithAppleResult);
    }
}
