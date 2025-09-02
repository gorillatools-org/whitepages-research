package com.RNAppleAuthentication.webview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.fragment.app.DialogFragment;
import com.RNAppleAuthentication.FormInterceptorInterface;
import com.RNAppleAuthentication.R$style;
import com.RNAppleAuthentication.SignInWithAppleResult;
import com.RNAppleAuthentication.SignInWithAppleService;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SuppressLint({"SetJavaScriptEnabled"})
public final class SignInWebViewDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private SignInWithAppleService.AuthenticationAttempt authenticationAttempt;
    private Function1 callback;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SignInWebViewDialogFragment newInstance(SignInWithAppleService.AuthenticationAttempt authenticationAttempt) {
            Intrinsics.checkNotNullParameter(authenticationAttempt, "authenticationAttempt");
            SignInWebViewDialogFragment signInWebViewDialogFragment = new SignInWebViewDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("authenticationAttempt", authenticationAttempt);
            signInWebViewDialogFragment.setArguments(bundle);
            return signInWebViewDialogFragment;
        }
    }

    private final WebView getWebViewIfCreated() {
        View view = getView();
        if (view instanceof WebView) {
            return (WebView) view;
        }
        return null;
    }

    public final void configure(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "callback");
        this.callback = function1;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        SignInWithAppleService.AuthenticationAttempt authenticationAttempt2 = arguments != null ? (SignInWithAppleService.AuthenticationAttempt) arguments.getParcelable("authenticationAttempt") : null;
        Intrinsics.checkNotNull(authenticationAttempt2);
        this.authenticationAttempt = authenticationAttempt2;
        setStyle(0, R$style.sign_in_with_apple_button_DialogTheme);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        super.onCreateView(layoutInflater, viewGroup, bundle);
        WebView webView = new WebView(requireContext());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        SignInWithAppleService.AuthenticationAttempt authenticationAttempt2 = this.authenticationAttempt;
        SignInWithAppleService.AuthenticationAttempt authenticationAttempt3 = null;
        if (authenticationAttempt2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authenticationAttempt");
            authenticationAttempt2 = null;
        }
        webView.addJavascriptInterface(new FormInterceptorInterface(authenticationAttempt2.getState(), new SignInWebViewDialogFragment$onCreateView$formInterceptorInterface$1(this)), "FormInterceptorInterface");
        SignInWithAppleService.AuthenticationAttempt authenticationAttempt4 = this.authenticationAttempt;
        if (authenticationAttempt4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authenticationAttempt");
            authenticationAttempt4 = null;
        }
        webView.setWebViewClient(new SignInWebViewClient(authenticationAttempt4, FormInterceptorInterface.Companion.getJS_TO_INJECT()));
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("webView");
            if (bundle2 != null) {
                webView.restoreState(bundle2);
            }
        } else {
            SignInWithAppleService.AuthenticationAttempt authenticationAttempt5 = this.authenticationAttempt;
            if (authenticationAttempt5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("authenticationAttempt");
            } else {
                authenticationAttempt3 = authenticationAttempt5;
            }
            webView.loadUrl(authenticationAttempt3.getAuthenticationUri());
        }
        return webView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        Bundle bundle2 = new Bundle();
        WebView webViewIfCreated = getWebViewIfCreated();
        if (webViewIfCreated != null) {
            webViewIfCreated.saveState(bundle2);
        }
        Unit unit = Unit.INSTANCE;
        bundle.putBundle("webView", bundle2);
    }

    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setLayout(-1, -1);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        super.onCancel(dialogInterface);
        onCallback(SignInWithAppleResult.Cancel.INSTANCE);
    }

    /* access modifiers changed from: private */
    public final void onCallback(SignInWithAppleResult signInWithAppleResult) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.dismiss();
        }
        Function1 function1 = this.callback;
        if (function1 != null) {
            function1.invoke(signInWithAppleResult);
        }
    }
}
