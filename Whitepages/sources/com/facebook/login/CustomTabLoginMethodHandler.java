package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.CustomTabMainActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.internal.CustomTab;
import com.facebook.internal.CustomTabUtils;
import com.facebook.internal.InstagramCustomTab;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class CustomTabLoginMethodHandler extends WebLoginMethodHandler {
    public static final Parcelable.Creator<CustomTabLoginMethodHandler> CREATOR = new CustomTabLoginMethodHandler$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static boolean calledThroughLoggedOutAppSwitch;
    private String currentPackage;
    private String expectedChallenge;
    private final String nameForLogging;
    private final AccessTokenSource tokenSource;
    private String validRedirectURI;

    public int describeContents() {
        return 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CustomTabLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        Intrinsics.checkNotNullParameter(loginClient, "loginClient");
        this.nameForLogging = "custom_tab";
        this.tokenSource = AccessTokenSource.CHROME_CUSTOM_TAB;
        this.expectedChallenge = Utility.generateRandomString(20);
        calledThroughLoggedOutAppSwitch = false;
        this.validRedirectURI = CustomTabUtils.getValidRedirectURI(getDeveloperDefinedRedirectURI());
    }

    public String getNameForLogging() {
        return this.nameForLogging;
    }

    public AccessTokenSource getTokenSource() {
        return this.tokenSource;
    }

    private final String getDeveloperDefinedRedirectURI() {
        return super.getRedirectUrl();
    }

    /* access modifiers changed from: protected */
    public String getRedirectUrl() {
        return this.validRedirectURI;
    }

    /* access modifiers changed from: protected */
    public String getSSODevice() {
        return "chrome_custom_tab";
    }

    public int tryAuthorize(LoginClient.Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        LoginClient loginClient = getLoginClient();
        if (getRedirectUrl().length() == 0) {
            return 0;
        }
        Bundle addExtraParameters = addExtraParameters(getParameters(request), request);
        if (calledThroughLoggedOutAppSwitch) {
            addExtraParameters.putString("cct_over_app_switch", "1");
        }
        if (FacebookSdk.hasCustomTabsPrefetching) {
            if (request.isInstagramLogin()) {
                CustomTabPrefetchHelper.Companion.mayLaunchUrl(InstagramCustomTab.Companion.getURIForAction("oauth", addExtraParameters));
            } else {
                CustomTabPrefetchHelper.Companion.mayLaunchUrl(CustomTab.Companion.getURIForAction("oauth", addExtraParameters));
            }
        }
        FragmentActivity activity = loginClient.getActivity();
        if (activity == null) {
            return 0;
        }
        Intent intent = new Intent(activity, CustomTabMainActivity.class);
        intent.putExtra(CustomTabMainActivity.EXTRA_ACTION, "oauth");
        intent.putExtra(CustomTabMainActivity.EXTRA_PARAMS, addExtraParameters);
        intent.putExtra(CustomTabMainActivity.EXTRA_CHROME_PACKAGE, getChromePackage());
        intent.putExtra(CustomTabMainActivity.EXTRA_TARGET_APP, request.getLoginTargetApp().toString());
        Fragment fragment = loginClient.getFragment();
        if (fragment != null) {
            fragment.startActivityForResult(intent, 1);
        }
        return 1;
    }

    private final String getChromePackage() {
        String str = this.currentPackage;
        if (str != null) {
            return str;
        }
        String chromePackage = CustomTabUtils.getChromePackage();
        this.currentPackage = chromePackage;
        return chromePackage;
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (intent != null && intent.getBooleanExtra(CustomTabMainActivity.NO_ACTIVITY_EXCEPTION, false)) {
            return super.onActivityResult(i, i2, intent);
        }
        if (i != 1) {
            return super.onActivityResult(i, i2, intent);
        }
        LoginClient.Request pendingRequest = getLoginClient().getPendingRequest();
        if (pendingRequest == null) {
            return false;
        }
        String str = null;
        if (i2 == -1) {
            if (intent != null) {
                str = intent.getStringExtra(CustomTabMainActivity.EXTRA_URL);
            }
            onCustomTabComplete(str, pendingRequest);
            return true;
        }
        super.onComplete(pendingRequest, (Bundle) null, new FacebookOperationCanceledException());
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void onCustomTabComplete(java.lang.String r7, com.facebook.login.LoginClient.Request r8) {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x00ce
            java.lang.String r0 = "fbconnect://cct."
            r1 = 0
            r2 = 2
            r3 = 0
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r7, r0, r1, r2, r3)
            if (r0 != 0) goto L_0x0017
            java.lang.String r0 = super.getRedirectUrl()
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r7, r0, r1, r2, r3)
            if (r0 == 0) goto L_0x00ce
        L_0x0017:
            android.net.Uri r7 = android.net.Uri.parse(r7)
            java.lang.String r0 = r7.getQuery()
            android.os.Bundle r0 = com.facebook.internal.Utility.parseUrlQueryString(r0)
            java.lang.String r7 = r7.getFragment()
            android.os.Bundle r7 = com.facebook.internal.Utility.parseUrlQueryString(r7)
            r0.putAll(r7)
            boolean r7 = r6.validateChallengeParam(r0)
            if (r7 != 0) goto L_0x003f
            com.facebook.FacebookException r7 = new com.facebook.FacebookException
            java.lang.String r0 = "Invalid state parameter"
            r7.<init>((java.lang.String) r0)
            super.onComplete(r8, r3, r7)
            return
        L_0x003f:
            java.lang.String r7 = "error"
            java.lang.String r7 = r0.getString(r7)
            if (r7 != 0) goto L_0x004d
            java.lang.String r7 = "error_type"
            java.lang.String r7 = r0.getString(r7)
        L_0x004d:
            java.lang.String r1 = "error_msg"
            java.lang.String r1 = r0.getString(r1)
            if (r1 != 0) goto L_0x005b
            java.lang.String r1 = "error_message"
            java.lang.String r1 = r0.getString(r1)
        L_0x005b:
            if (r1 != 0) goto L_0x0063
            java.lang.String r1 = "error_description"
            java.lang.String r1 = r0.getString(r1)
        L_0x0063:
            java.lang.String r2 = "error_code"
            java.lang.String r2 = r0.getString(r2)
            r4 = -1
            if (r2 == 0) goto L_0x0071
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0071 }
            goto L_0x0072
        L_0x0071:
            r2 = r4
        L_0x0072:
            boolean r5 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r7)
            if (r5 == 0) goto L_0x0099
            boolean r5 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r1)
            if (r5 == 0) goto L_0x0099
            if (r2 != r4) goto L_0x0099
            java.lang.String r7 = "access_token"
            boolean r7 = r0.containsKey(r7)
            if (r7 == 0) goto L_0x008c
            super.onComplete(r8, r0, r3)
            return
        L_0x008c:
            java.util.concurrent.Executor r7 = com.facebook.FacebookSdk.getExecutor()
            com.facebook.login.CustomTabLoginMethodHandler$$ExternalSyntheticLambda0 r1 = new com.facebook.login.CustomTabLoginMethodHandler$$ExternalSyntheticLambda0
            r1.<init>(r6, r8, r0)
            r7.execute(r1)
            goto L_0x00ce
        L_0x0099:
            if (r7 == 0) goto L_0x00b4
            java.lang.String r0 = "access_denied"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r0)
            if (r0 != 0) goto L_0x00ab
            java.lang.String r0 = "OAuthAccessDeniedException"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r0)
            if (r0 == 0) goto L_0x00b4
        L_0x00ab:
            com.facebook.FacebookOperationCanceledException r7 = new com.facebook.FacebookOperationCanceledException
            r7.<init>()
            super.onComplete(r8, r3, r7)
            goto L_0x00ce
        L_0x00b4:
            r0 = 4201(0x1069, float:5.887E-42)
            if (r2 != r0) goto L_0x00c1
            com.facebook.FacebookOperationCanceledException r7 = new com.facebook.FacebookOperationCanceledException
            r7.<init>()
            super.onComplete(r8, r3, r7)
            goto L_0x00ce
        L_0x00c1:
            com.facebook.FacebookRequestError r0 = new com.facebook.FacebookRequestError
            r0.<init>(r2, r7, r1)
            com.facebook.FacebookServiceException r7 = new com.facebook.FacebookServiceException
            r7.<init>(r0, r1)
            super.onComplete(r8, r3, r7)
        L_0x00ce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.CustomTabLoginMethodHandler.onCustomTabComplete(java.lang.String, com.facebook.login.LoginClient$Request):void");
    }

    /* access modifiers changed from: private */
    public static final void onCustomTabComplete$lambda$0(CustomTabLoginMethodHandler customTabLoginMethodHandler, LoginClient.Request request, Bundle bundle) {
        Intrinsics.checkNotNullParameter(customTabLoginMethodHandler, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(bundle, "$values");
        try {
            customTabLoginMethodHandler.onComplete(request, customTabLoginMethodHandler.processCodeExchange(request, bundle), (FacebookException) null);
        } catch (FacebookException e) {
            customTabLoginMethodHandler.onComplete(request, (Bundle) null, e);
        }
    }

    public void putChallengeParam(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "param");
        jSONObject.put("7_challenge", this.expectedChallenge);
    }

    private final boolean validateChallengeParam(Bundle bundle) {
        try {
            String string = bundle.getString(RemoteConfigConstants.ResponseFieldKey.STATE);
            if (string == null) {
                return false;
            }
            return Intrinsics.areEqual((Object) new JSONObject(string).getString("7_challenge"), (Object) this.expectedChallenge);
        } catch (JSONException unused) {
            return false;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CustomTabLoginMethodHandler(Parcel parcel) {
        super(parcel);
        Intrinsics.checkNotNullParameter(parcel, "source");
        this.nameForLogging = "custom_tab";
        this.tokenSource = AccessTokenSource.CHROME_CUSTOM_TAB;
        this.expectedChallenge = parcel.readString();
        this.validRedirectURI = CustomTabUtils.getValidRedirectURI(getDeveloperDefinedRedirectURI());
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        super.writeToParcel(parcel, i);
        parcel.writeString(this.expectedChallenge);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
