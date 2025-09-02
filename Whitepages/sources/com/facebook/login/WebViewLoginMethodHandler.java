package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.Utility;
import com.facebook.internal.WebDialog;
import com.facebook.login.LoginClient;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class WebViewLoginMethodHandler extends WebLoginMethodHandler {
    public static final Parcelable.Creator<WebViewLoginMethodHandler> CREATOR = new WebViewLoginMethodHandler$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String e2e;
    private WebDialog loginDialog;
    private final String nameForLogging = "web_view";
    private final AccessTokenSource tokenSource = AccessTokenSource.WEB_VIEW;

    public int describeContents() {
        return 0;
    }

    public boolean needsInternetPermission() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebViewLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        Intrinsics.checkNotNullParameter(loginClient, "loginClient");
    }

    public String getNameForLogging() {
        return this.nameForLogging;
    }

    public AccessTokenSource getTokenSource() {
        return this.tokenSource;
    }

    public void cancel() {
        WebDialog webDialog = this.loginDialog;
        if (webDialog != null) {
            if (webDialog != null) {
                webDialog.cancel();
            }
            this.loginDialog = null;
        }
    }

    public int tryAuthorize(LoginClient.Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        Bundle parameters = getParameters(request);
        WebViewLoginMethodHandler$tryAuthorize$listener$1 webViewLoginMethodHandler$tryAuthorize$listener$1 = new WebViewLoginMethodHandler$tryAuthorize$listener$1(this, request);
        String e2e2 = LoginClient.Companion.getE2E();
        this.e2e = e2e2;
        addLoggingExtra("e2e", e2e2);
        FragmentActivity activity = getLoginClient().getActivity();
        if (activity == null) {
            return 0;
        }
        boolean isChromeOS = Utility.isChromeOS(activity);
        AuthDialogBuilder authDialogBuilder = new AuthDialogBuilder(this, activity, request.getApplicationId(), parameters);
        String str = this.e2e;
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
        this.loginDialog = authDialogBuilder.setE2E(str).setIsChromeOS(isChromeOS).setAuthType(request.getAuthType()).setLoginBehavior(request.getLoginBehavior()).setLoginTargetApp(request.getLoginTargetApp()).setFamilyLogin(request.isFamilyLogin()).setShouldSkipDedupe(request.shouldSkipAccountDeduplication()).setOnCompleteListener(webViewLoginMethodHandler$tryAuthorize$listener$1).build();
        FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
        facebookDialogFragment.setRetainInstance(true);
        facebookDialogFragment.setInnerDialog(this.loginDialog);
        facebookDialogFragment.show(activity.getSupportFragmentManager(), "FacebookDialogFragment");
        return 1;
    }

    public final void onWebDialogComplete(LoginClient.Request request, Bundle bundle, FacebookException facebookException) {
        Intrinsics.checkNotNullParameter(request, "request");
        super.onComplete(request, bundle, facebookException);
    }

    public final class AuthDialogBuilder extends WebDialog.Builder {
        public String authType;
        public String e2e;
        private boolean isFamilyLogin;
        private LoginBehavior loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
        private String redirect_uri = "fbconnect://success";
        private boolean shouldSkipDedupe;
        private LoginTargetApp targetApp = LoginTargetApp.FACEBOOK;
        final /* synthetic */ WebViewLoginMethodHandler this$0;

        public final String getE2e() {
            String str = this.e2e;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("e2e");
            return null;
        }

        public final void setE2e(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.e2e = str;
        }

        public final String getAuthType() {
            String str = this.authType;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("authType");
            return null;
        }

        /* renamed from: setAuthType  reason: collision with other method in class */
        public final void m219setAuthType(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.authType = str;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AuthDialogBuilder(WebViewLoginMethodHandler webViewLoginMethodHandler, Context context, String str, Bundle bundle) {
            super(context, str, "oauth", bundle);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "applicationId");
            Intrinsics.checkNotNullParameter(bundle, "parameters");
            this.this$0 = webViewLoginMethodHandler;
        }

        public final AuthDialogBuilder setE2E(String str) {
            Intrinsics.checkNotNullParameter(str, "e2e");
            setE2e(str);
            return this;
        }

        public final AuthDialogBuilder setIsChromeOS(boolean z) {
            String str;
            if (z) {
                str = "fbconnect://chrome_os_success";
            } else {
                str = "fbconnect://success";
            }
            this.redirect_uri = str;
            return this;
        }

        public final AuthDialogBuilder setAuthType(String str) {
            Intrinsics.checkNotNullParameter(str, "authType");
            setAuthType(str);
            return this;
        }

        public final AuthDialogBuilder setLoginBehavior(LoginBehavior loginBehavior2) {
            Intrinsics.checkNotNullParameter(loginBehavior2, "loginBehavior");
            this.loginBehavior = loginBehavior2;
            return this;
        }

        public final AuthDialogBuilder setLoginTargetApp(LoginTargetApp loginTargetApp) {
            Intrinsics.checkNotNullParameter(loginTargetApp, "targetApp");
            this.targetApp = loginTargetApp;
            return this;
        }

        public final AuthDialogBuilder setFamilyLogin(boolean z) {
            this.isFamilyLogin = z;
            return this;
        }

        public final AuthDialogBuilder setShouldSkipDedupe(boolean z) {
            this.shouldSkipDedupe = z;
            return this;
        }

        public WebDialog build() {
            String str;
            Bundle parameters = getParameters();
            Intrinsics.checkNotNull(parameters, "null cannot be cast to non-null type android.os.Bundle");
            parameters.putString("redirect_uri", this.redirect_uri);
            parameters.putString("client_id", getApplicationId());
            parameters.putString("e2e", getE2e());
            if (this.targetApp == LoginTargetApp.INSTAGRAM) {
                str = "token,signed_request,graph_domain,granted_scopes";
            } else {
                str = "token,signed_request,graph_domain";
            }
            parameters.putString("response_type", str);
            parameters.putString("return_scopes", "true");
            parameters.putString("auth_type", getAuthType());
            parameters.putString("login_behavior", this.loginBehavior.name());
            if (this.isFamilyLogin) {
                parameters.putString("fx_app", this.targetApp.toString());
            }
            if (this.shouldSkipDedupe) {
                parameters.putString("skip_dedupe", "true");
            }
            WebDialog.Companion companion = WebDialog.Companion;
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.content.Context");
            return companion.newInstance(context, "oauth", parameters, getTheme(), this.targetApp, getListener());
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebViewLoginMethodHandler(Parcel parcel) {
        super(parcel);
        Intrinsics.checkNotNullParameter(parcel, "source");
        this.e2e = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        super.writeToParcel(parcel, i);
        parcel.writeString(this.e2e);
    }
}
