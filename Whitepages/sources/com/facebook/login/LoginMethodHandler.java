package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import com.amplitude.reactnative.AmplitudeReactNativeModule;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.AuthenticationToken;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import com.google.firebase.messaging.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class LoginMethodHandler implements Parcelable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public LoginClient loginClient;
    private Map methodLoggingExtras;

    public void cancel() {
    }

    public abstract String getNameForLogging();

    public boolean needsInternetPermission() {
        return false;
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        return false;
    }

    public void putChallengeParam(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "param");
    }

    public boolean shouldKeepTrackOfMultipleIntents() {
        return false;
    }

    public abstract int tryAuthorize(LoginClient.Request request);

    public final Map getMethodLoggingExtras() {
        return this.methodLoggingExtras;
    }

    public final LoginClient getLoginClient() {
        LoginClient loginClient2 = this.loginClient;
        if (loginClient2 != null) {
            return loginClient2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("loginClient");
        return null;
    }

    public final void setLoginClient(LoginClient loginClient2) {
        Intrinsics.checkNotNullParameter(loginClient2, "<set-?>");
        this.loginClient = loginClient2;
    }

    public LoginMethodHandler(LoginClient loginClient2) {
        Intrinsics.checkNotNullParameter(loginClient2, "loginClient");
        setLoginClient(loginClient2);
    }

    protected LoginMethodHandler(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "source");
        Map readStringMapFromParcel = Utility.readStringMapFromParcel(parcel);
        this.methodLoggingExtras = readStringMapFromParcel != null ? MapsKt.toMutableMap(readStringMapFromParcel) : null;
    }

    /* access modifiers changed from: protected */
    public String getRedirectUrl() {
        return "fb" + FacebookSdk.getApplicationId() + "://authorize/";
    }

    /* access modifiers changed from: protected */
    public String getClientState(String str) {
        Intrinsics.checkNotNullParameter(str, "authId");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0_auth_logger_id", str);
            jSONObject.put("3_method", getNameForLogging());
            putChallengeParam(jSONObject);
        } catch (JSONException e) {
            Log.w("LoginMethodHandler", "Error creating client state json: " + e.getMessage());
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "param.toString()");
        return jSONObject2;
    }

    /* access modifiers changed from: protected */
    public void addLoggingExtra(String str, Object obj) {
        if (this.methodLoggingExtras == null) {
            this.methodLoggingExtras = new HashMap();
        }
        Map map = this.methodLoggingExtras;
        if (map != null) {
            String str2 = (String) map.put(str, obj != null ? obj.toString() : null);
        }
    }

    /* access modifiers changed from: protected */
    public void logWebLoginCompleted(String str) {
        String str2;
        LoginClient.Request pendingRequest = getLoginClient().getPendingRequest();
        if (pendingRequest == null || (str2 = pendingRequest.getApplicationId()) == null) {
            str2 = FacebookSdk.getApplicationId();
        }
        InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(getLoginClient().getActivity(), str2);
        Bundle bundle = new Bundle();
        bundle.putString("fb_web_login_e2e", str);
        bundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
        bundle.putString("app_id", str2);
        internalAppEventsLogger.logEventImplicitly("fb_dialogs_web_login_dialog_complete", (Double) null, bundle);
    }

    /* access modifiers changed from: protected */
    public Bundle processCodeExchange(LoginClient.Request request, Bundle bundle) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(bundle, "values");
        String string = bundle.getString("code");
        if (!Utility.isNullOrEmpty(string)) {
            if (string != null) {
                String redirectUrl = getRedirectUrl();
                String codeVerifier = request.getCodeVerifier();
                if (codeVerifier == null) {
                    codeVerifier = "";
                }
                GraphRequest createCodeExchangeRequest = PKCEUtil.createCodeExchangeRequest(string, redirectUrl, codeVerifier);
                if (createCodeExchangeRequest != null) {
                    GraphResponse executeAndWait = createCodeExchangeRequest.executeAndWait();
                    FacebookRequestError error = executeAndWait.getError();
                    if (error == null) {
                        try {
                            JSONObject jSONObject = executeAndWait.getJSONObject();
                            String string2 = jSONObject != null ? jSONObject.getString("access_token") : null;
                            if (jSONObject == null || Utility.isNullOrEmpty(string2)) {
                                throw new FacebookException("No access token found from result");
                            }
                            bundle.putString("access_token", string2);
                            if (jSONObject.has("id_token")) {
                                bundle.putString("id_token", jSONObject.getString("id_token"));
                            }
                            return bundle;
                        } catch (JSONException e) {
                            throw new FacebookException("Fail to process code exchange response: " + e.getMessage());
                        }
                    } else {
                        throw new FacebookServiceException(error, error.getErrorMessage());
                    }
                }
            }
            throw new FacebookException("Failed to create code exchange request");
        }
        throw new FacebookException("No code param found from the request");
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        Utility.writeStringMapToParcel(parcel, this.methodLoggingExtras);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AuthenticationToken createAuthenticationTokenFromNativeLogin(Bundle bundle, String str) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            String string = bundle.getString("com.facebook.platform.extra.ID_TOKEN");
            if (string == null || string.length() == 0 || str == null || str.length() == 0) {
                return null;
            }
            try {
                return new AuthenticationToken(string, str);
            } catch (Exception e) {
                throw new FacebookException(e.getMessage());
            }
        }

        public final AccessToken createAccessTokenFromNativeLogin(Bundle bundle, AccessTokenSource accessTokenSource, String str) {
            String string;
            Bundle bundle2 = bundle;
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(str, "applicationId");
            Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH", new Date(0));
            ArrayList<String> stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
            String string2 = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
            Date bundleLongAsDate2 = Utility.getBundleLongAsDate(bundle, "com.facebook.platform.extra.EXTRA_DATA_ACCESS_EXPIRATION_TIME", new Date(0));
            if (string2 == null || string2.length() == 0 || (string = bundle.getString("com.facebook.platform.extra.USER_ID")) == null || string.length() == 0) {
                return null;
            }
            return new AccessToken(string2, str, string, stringArrayList, (Collection) null, (Collection) null, accessTokenSource, bundleLongAsDate, new Date(), bundleLongAsDate2, bundle.getString("graph_domain"));
        }

        public final AuthenticationToken createAuthenticationTokenFromWebBundle(Bundle bundle, String str) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            String string = bundle.getString("id_token");
            if (string == null || string.length() == 0 || str == null || str.length() == 0) {
                return null;
            }
            try {
                return new AuthenticationToken(string, str);
            } catch (Exception e) {
                throw new FacebookException(e.getMessage(), e);
            }
        }

        public final AccessToken createAccessTokenFromWebBundle(Collection collection, Bundle bundle, AccessTokenSource accessTokenSource, String str) {
            Collection collection2;
            ArrayList arrayList;
            ArrayList arrayList2;
            Bundle bundle2 = bundle;
            Intrinsics.checkNotNullParameter(bundle2, "bundle");
            Intrinsics.checkNotNullParameter(str, "applicationId");
            Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle2, "expires_in", new Date());
            String string = bundle2.getString("access_token");
            if (string == null) {
                return null;
            }
            Date bundleLongAsDate2 = Utility.getBundleLongAsDate(bundle2, "data_access_expiration_time", new Date(0));
            String string2 = bundle2.getString("granted_scopes");
            if (string2 == null || string2.length() <= 0) {
                collection2 = collection;
            } else {
                String[] strArr = (String[]) StringsKt.split$default((CharSequence) string2, new String[]{","}, false, 0, 6, (Object) null).toArray(new String[0]);
                collection2 = CollectionsKt.arrayListOf(Arrays.copyOf(strArr, strArr.length));
            }
            String string3 = bundle2.getString("denied_scopes");
            if (string3 == null || string3.length() <= 0) {
                arrayList = null;
            } else {
                String[] strArr2 = (String[]) StringsKt.split$default((CharSequence) string3, new String[]{","}, false, 0, 6, (Object) null).toArray(new String[0]);
                arrayList = CollectionsKt.arrayListOf(Arrays.copyOf(strArr2, strArr2.length));
            }
            String string4 = bundle2.getString("expired_scopes");
            if (string4 == null || string4.length() <= 0) {
                arrayList2 = null;
            } else {
                String[] strArr3 = (String[]) StringsKt.split$default((CharSequence) string4, new String[]{","}, false, 0, 6, (Object) null).toArray(new String[0]);
                arrayList2 = CollectionsKt.arrayListOf(Arrays.copyOf(strArr3, strArr3.length));
            }
            if (Utility.isNullOrEmpty(string)) {
                return null;
            }
            return new AccessToken(string, str, getUserIDFromSignedRequest(bundle2.getString("signed_request")), collection2, arrayList, arrayList2, accessTokenSource, bundleLongAsDate, new Date(), bundleLongAsDate2, bundle2.getString("graph_domain"));
        }

        public final String getUserIDFromSignedRequest(String str) {
            if (str == null || str.length() == 0) {
                throw new FacebookException("Authorization response does not contain the signed_request");
            }
            try {
                String[] strArr = (String[]) StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (strArr.length == 2) {
                    byte[] decode = Base64.decode(strArr[1], 0);
                    Intrinsics.checkNotNullExpressionValue(decode, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                    String string = new JSONObject(new String(decode, Charsets.UTF_8)).getString(AmplitudeReactNativeModule.USER_ID_KEY);
                    Intrinsics.checkNotNullExpressionValue(string, "jsonObject.getString(\"user_id\")");
                    return string;
                }
            } catch (UnsupportedEncodingException | JSONException unused) {
            }
            throw new FacebookException("Failed to retrieve user_id from signed_request");
        }
    }
}
