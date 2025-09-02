package com.facebook.login;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.common.R$id;
import com.facebook.common.R$layout;
import com.facebook.login.LoginClient;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class LoginFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String callingPackage;
    private ActivityResultLauncher launcher;
    private LoginClient loginClient;
    private View progressBar;
    private LoginClient.Request request;

    /* access modifiers changed from: protected */
    public void onSpinnerHidden() {
    }

    /* access modifiers changed from: protected */
    public void onSpinnerShown() {
    }

    public final LoginClient getLoginClient() {
        LoginClient loginClient2 = this.loginClient;
        if (loginClient2 != null) {
            return loginClient2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("loginClient");
        return null;
    }

    public final ActivityResultLauncher getLauncher() {
        ActivityResultLauncher activityResultLauncher = this.launcher;
        if (activityResultLauncher != null) {
            return activityResultLauncher;
        }
        Intrinsics.throwUninitializedPropertyAccessException("launcher");
        return null;
    }

    public void onCreate(Bundle bundle) {
        Bundle bundleExtra;
        super.onCreate(bundle);
        LoginClient loginClient2 = bundle != null ? (LoginClient) bundle.getParcelable("loginClient") : null;
        if (loginClient2 != null) {
            loginClient2.setFragment(this);
        } else {
            loginClient2 = createLoginClient();
        }
        this.loginClient = loginClient2;
        getLoginClient().setOnCompletedListener(new LoginFragment$$ExternalSyntheticLambda0(this));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            initializeCallingPackage(activity);
            Intent intent = activity.getIntent();
            if (!(intent == null || (bundleExtra = intent.getBundleExtra("com.facebook.LoginFragment:Request")) == null)) {
                this.request = (LoginClient.Request) bundleExtra.getParcelable("request");
            }
            ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts$StartActivityForResult(), new LoginFragment$$ExternalSyntheticLambda1(getLoginMethodHandlerCallback(activity)));
            Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…andlerCallback(activity))");
            this.launcher = registerForActivityResult;
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(LoginFragment loginFragment, LoginClient.Result result) {
        Intrinsics.checkNotNullParameter(loginFragment, "this$0");
        Intrinsics.checkNotNullParameter(result, "outcome");
        loginFragment.onLoginClientCompleted(result);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(Function1 function1, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(activityResult);
    }

    private final Function1 getLoginMethodHandlerCallback(FragmentActivity fragmentActivity) {
        return new LoginFragment$getLoginMethodHandlerCallback$1(this, fragmentActivity);
    }

    /* access modifiers changed from: protected */
    public LoginClient createLoginClient() {
        return new LoginClient((Fragment) this);
    }

    public void onDestroy() {
        getLoginClient().cancelCurrentHandler();
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(getLayoutResId(), viewGroup, false);
        View findViewById = inflate.findViewById(R$id.com_facebook_login_fragment_progress_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById<View>(…in_fragment_progress_bar)");
        this.progressBar = findViewById;
        getLoginClient().setBackgroundProcessingListener(new LoginFragment$onCreateView$1(this));
        return inflate;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R$layout.com_facebook_login_fragment;
    }

    private final void onLoginClientCompleted(LoginClient.Result result) {
        this.request = null;
        int i = result.code == LoginClient.Result.Code.CANCEL ? 0 : -1;
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.facebook.LoginFragment:Result", result);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        FragmentActivity activity = getActivity();
        if (isAdded() && activity != null) {
            activity.setResult(i, intent);
            activity.finish();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.callingPackage == null) {
            Log.e("LoginFragment", "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
                return;
            }
            return;
        }
        getLoginClient().startOrContinueAuth(this.request);
    }

    public void onPause() {
        super.onPause();
        View view = getView();
        View findViewById = view != null ? view.findViewById(R$id.com_facebook_login_fragment_progress_bar) : null;
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        getLoginClient().onActivityResult(i, i2, intent);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("loginClient", getLoginClient());
    }

    /* access modifiers changed from: private */
    public final void showSpinner() {
        View view = this.progressBar;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            view = null;
        }
        view.setVisibility(0);
        onSpinnerShown();
    }

    /* access modifiers changed from: private */
    public final void hideSpinner() {
        View view = this.progressBar;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            view = null;
        }
        view.setVisibility(8);
        onSpinnerHidden();
    }

    private final void initializeCallingPackage(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            this.callingPackage = callingActivity.getPackageName();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
