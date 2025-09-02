package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import androidx.fragment.app.FragmentActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import com.facebook.login.LoginMethodHandler;
import com.google.android.gms.common.Scopes;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class WebLoginMethodHandler extends LoginMethodHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String e2e;

    /* access modifiers changed from: protected */
    public String getSSODevice() {
        return null;
    }

    public abstract AccessTokenSource getTokenSource();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        Intrinsics.checkNotNullParameter(loginClient, "loginClient");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebLoginMethodHandler(Parcel parcel) {
        super(parcel);
        Intrinsics.checkNotNullParameter(parcel, "source");
    }

    /* access modifiers changed from: protected */
    public Bundle getParameters(LoginClient.Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        Bundle bundle = new Bundle();
        if (!Utility.isNullOrEmpty((Collection) request.getPermissions())) {
            String join = TextUtils.join(",", request.getPermissions());
            bundle.putString("scope", join);
            addLoggingExtra("scope", join);
        }
        DefaultAudience defaultAudience = request.getDefaultAudience();
        if (defaultAudience == null) {
            defaultAudience = DefaultAudience.NONE;
        }
        bundle.putString("default_audience", defaultAudience.getNativeProtocolAudience());
        bundle.putString(RemoteConfigConstants.ResponseFieldKey.STATE, getClientState(request.getAuthId()));
        AccessToken currentAccessToken = AccessToken.Companion.getCurrentAccessToken();
        String token = currentAccessToken != null ? currentAccessToken.getToken() : null;
        String str = "0";
        if (token == null || !Intrinsics.areEqual((Object) token, (Object) loadCookieToken())) {
            FragmentActivity activity = getLoginClient().getActivity();
            if (activity != null) {
                Utility.clearFacebookCookies(activity);
            }
            addLoggingExtra("access_token", str);
        } else {
            bundle.putString("access_token", token);
            addLoggingExtra("access_token", "1");
        }
        bundle.putString("cbt", String.valueOf(System.currentTimeMillis()));
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            str = "1";
        }
        bundle.putString("ies", str);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public Bundle addExtraParameters(Bundle bundle, LoginClient.Request request) {
        Intrinsics.checkNotNullParameter(bundle, "parameters");
        Intrinsics.checkNotNullParameter(request, "request");
        bundle.putString("redirect_uri", getRedirectUrl());
        if (request.isInstagramLogin()) {
            bundle.putString("app_id", request.getApplicationId());
        } else {
            bundle.putString("client_id", request.getApplicationId());
        }
        bundle.putString("e2e", LoginClient.Companion.getE2E());
        if (request.isInstagramLogin()) {
            bundle.putString("response_type", "token,signed_request,graph_domain,granted_scopes");
        } else {
            if (request.getPermissions().contains(Scopes.OPEN_ID)) {
                bundle.putString("nonce", request.getNonce());
            }
            bundle.putString("response_type", "id_token,token,signed_request,graph_domain");
        }
        bundle.putString("code_challenge", request.getCodeChallenge());
        CodeChallengeMethod codeChallengeMethod = request.getCodeChallengeMethod();
        bundle.putString("code_challenge_method", codeChallengeMethod != null ? codeChallengeMethod.name() : null);
        bundle.putString("return_scopes", "true");
        bundle.putString("auth_type", request.getAuthType());
        bundle.putString("login_behavior", request.getLoginBehavior().name());
        bundle.putString("sdk", "android-" + FacebookSdk.getSdkVersion());
        if (getSSODevice() != null) {
            bundle.putString("sso", getSSODevice());
        }
        String str = "0";
        bundle.putString("cct_prefetching", FacebookSdk.hasCustomTabsPrefetching ? "1" : str);
        if (request.isFamilyLogin()) {
            bundle.putString("fx_app", request.getLoginTargetApp().toString());
        }
        if (request.shouldSkipAccountDeduplication()) {
            bundle.putString("skip_dedupe", "true");
        }
        if (request.getMessengerPageId() != null) {
            bundle.putString("messenger_page_id", request.getMessengerPageId());
            if (request.getResetMessengerState()) {
                str = "1";
            }
            bundle.putString("reset_messenger_state", str);
        }
        return bundle;
    }

    public void onComplete(LoginClient.Request request, Bundle bundle, FacebookException facebookException) {
        LoginClient.Result result;
        String str;
        Intrinsics.checkNotNullParameter(request, "request");
        LoginClient loginClient = getLoginClient();
        this.e2e = null;
        if (bundle != null) {
            if (bundle.containsKey("e2e")) {
                this.e2e = bundle.getString("e2e");
            }
            try {
                LoginMethodHandler.Companion companion = LoginMethodHandler.Companion;
                AccessToken createAccessTokenFromWebBundle = companion.createAccessTokenFromWebBundle(request.getPermissions(), bundle, getTokenSource(), request.getApplicationId());
                result = LoginClient.Result.Companion.createCompositeTokenResult(loginClient.getPendingRequest(), createAccessTokenFromWebBundle, companion.createAuthenticationTokenFromWebBundle(bundle, request.getNonce()));
                if (loginClient.getActivity() != null) {
                    try {
                        CookieSyncManager.createInstance(loginClient.getActivity()).sync();
                    } catch (Exception unused) {
                    }
                    if (createAccessTokenFromWebBundle != null) {
                        saveCookieToken(createAccessTokenFromWebBundle.getToken());
                    }
                }
            } catch (FacebookException e) {
                result = LoginClient.Result.Companion.createErrorResult$default(LoginClient.Result.Companion, loginClient.getPendingRequest(), (String) null, e.getMessage(), (String) null, 8, (Object) null);
            }
        } else if (facebookException instanceof FacebookOperationCanceledException) {
            result = LoginClient.Result.Companion.createCancelResult(loginClient.getPendingRequest(), "User canceled log in.");
        } else {
            this.e2e = null;
            String message = facebookException != null ? facebookException.getMessage() : null;
            if (facebookException instanceof FacebookServiceException) {
                FacebookRequestError requestError = ((FacebookServiceException) facebookException).getRequestError();
                str = String.valueOf(requestError.getErrorCode());
                message = requestError.toString();
            } else {
                str = null;
            }
            result = LoginClient.Result.Companion.createErrorResult(loginClient.getPendingRequest(), (String) null, message, str);
        }
        if (!Utility.isNullOrEmpty(this.e2e)) {
            logWebLoginCompleted(this.e2e);
        }
        loginClient.completeAndValidate(result);
    }

    private final String loadCookieToken() {
        Context activity = getLoginClient().getActivity();
        if (activity == null) {
            activity = FacebookSdk.getApplicationContext();
        }
        return activity.getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
    }

    private final void saveCookieToken(String str) {
        Context activity = getLoginClient().getActivity();
        if (activity == null) {
            activity = FacebookSdk.getApplicationContext();
        }
        activity.getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit().putString("TOKEN", str).apply();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
