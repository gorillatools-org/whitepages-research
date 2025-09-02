package com.RNAppleAuthentication;

import com.RNAppleAuthentication.SignInWithAppleResult;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public abstract class SignInWithAppleCallbackKt {
    public static final Function1 toFunction(SignInWithAppleCallback signInWithAppleCallback) {
        Intrinsics.checkNotNullParameter(signInWithAppleCallback, "<this>");
        return new SignInWithAppleCallbackKt$$ExternalSyntheticLambda0(signInWithAppleCallback);
    }

    /* access modifiers changed from: private */
    public static final Unit toFunction$lambda$0(SignInWithAppleCallback signInWithAppleCallback, SignInWithAppleResult signInWithAppleResult) {
        Intrinsics.checkNotNullParameter(signInWithAppleResult, "result");
        if (signInWithAppleResult instanceof SignInWithAppleResult.Success) {
            SignInWithAppleResult.Success success = (SignInWithAppleResult.Success) signInWithAppleResult;
            signInWithAppleCallback.onSignInWithAppleSuccess(success.getCode(), success.getId_token(), success.getState(), success.getUser());
        } else if (signInWithAppleResult instanceof SignInWithAppleResult.Failure) {
            signInWithAppleCallback.onSignInWithAppleFailure(((SignInWithAppleResult.Failure) signInWithAppleResult).getError());
        } else if (signInWithAppleResult instanceof SignInWithAppleResult.Cancel) {
            signInWithAppleCallback.onSignInWithAppleCancel();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }
}
