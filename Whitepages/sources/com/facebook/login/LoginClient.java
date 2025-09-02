package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.AccessToken;
import com.facebook.AuthenticationToken;
import com.facebook.CustomTabMainActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.common.R$string;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginClient implements Parcelable {
    public static final Parcelable.Creator<LoginClient> CREATOR = new LoginClient$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private BackgroundProcessingListener backgroundProcessingListener;
    private boolean checkedInternetPermission;
    private int currentHandler = -1;
    private Map extraData;
    private Fragment fragment;
    private LoginMethodHandler[] handlersToTry;
    private Map loggingExtras;
    private LoginLogger loginLogger;
    private int numActivitiesReturned;
    private int numTotalIntentsFired;
    private OnCompletedListener onCompletedListener;
    private Request pendingRequest;

    public interface BackgroundProcessingListener {
        void onBackgroundProcessingStarted();

        void onBackgroundProcessingStopped();
    }

    public interface OnCompletedListener {
        void onCompleted(Result result);
    }

    public int describeContents() {
        return 0;
    }

    public final Fragment getFragment() {
        return this.fragment;
    }

    public final void setFragment(Fragment fragment2) {
        if (this.fragment == null) {
            this.fragment = fragment2;
            return;
        }
        throw new FacebookException("Can't set fragment once it is already set.");
    }

    public final void setOnCompletedListener(OnCompletedListener onCompletedListener2) {
        this.onCompletedListener = onCompletedListener2;
    }

    public final void setBackgroundProcessingListener(BackgroundProcessingListener backgroundProcessingListener2) {
        this.backgroundProcessingListener = backgroundProcessingListener2;
    }

    public final Request getPendingRequest() {
        return this.pendingRequest;
    }

    public LoginClient(Fragment fragment2) {
        Intrinsics.checkNotNullParameter(fragment2, "fragment");
        setFragment(fragment2);
    }

    public final FragmentActivity getActivity() {
        Fragment fragment2 = this.fragment;
        if (fragment2 != null) {
            return fragment2.getActivity();
        }
        return null;
    }

    public final void startOrContinueAuth(Request request) {
        if (!getInProgress()) {
            authorize(request);
        }
    }

    public final void authorize(Request request) {
        if (request != null) {
            if (this.pendingRequest != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            } else if (!AccessToken.Companion.isCurrentAccessTokenActive() || checkInternetPermission()) {
                this.pendingRequest = request;
                this.handlersToTry = getHandlersToTry(request);
                tryNextHandler();
            }
        }
    }

    public final boolean getInProgress() {
        return this.pendingRequest != null && this.currentHandler >= 0;
    }

    public final void cancelCurrentHandler() {
        LoginMethodHandler currentHandler2 = getCurrentHandler();
        if (currentHandler2 != null) {
            currentHandler2.cancel();
        }
    }

    public final LoginMethodHandler getCurrentHandler() {
        LoginMethodHandler[] loginMethodHandlerArr;
        int i = this.currentHandler;
        if (i < 0 || (loginMethodHandlerArr = this.handlersToTry) == null) {
            return null;
        }
        return loginMethodHandlerArr[i];
    }

    public final boolean onActivityResult(int i, int i2, Intent intent) {
        this.numActivitiesReturned++;
        if (this.pendingRequest != null) {
            if (intent == null || !intent.getBooleanExtra(CustomTabMainActivity.NO_ACTIVITY_EXCEPTION, false)) {
                LoginMethodHandler currentHandler2 = getCurrentHandler();
                if (currentHandler2 != null && (!currentHandler2.shouldKeepTrackOfMultipleIntents() || intent != null || this.numActivitiesReturned >= this.numTotalIntentsFired)) {
                    return currentHandler2.onActivityResult(i, i2, intent);
                }
            } else {
                tryNextHandler();
                return false;
            }
        }
        return false;
    }

    public LoginMethodHandler[] getHandlersToTry(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        ArrayList arrayList = new ArrayList();
        LoginBehavior loginBehavior = request.getLoginBehavior();
        if (!request.isInstagramLogin()) {
            if (loginBehavior.allowsGetTokenAuth()) {
                arrayList.add(new GetTokenLoginMethodHandler(this));
            }
            if (!FacebookSdk.bypassAppSwitch && loginBehavior.allowsKatanaAuth()) {
                arrayList.add(new KatanaProxyLoginMethodHandler(this));
            }
        } else if (!FacebookSdk.bypassAppSwitch && loginBehavior.allowsInstagramAppAuth()) {
            arrayList.add(new InstagramAppLoginMethodHandler(this));
        }
        if (loginBehavior.allowsCustomTabAuth()) {
            arrayList.add(new CustomTabLoginMethodHandler(this));
        }
        if (loginBehavior.allowsWebViewAuth()) {
            arrayList.add(new WebViewLoginMethodHandler(this));
        }
        if (!request.isInstagramLogin() && loginBehavior.allowsDeviceAuth()) {
            arrayList.add(new DeviceAuthMethodHandler(this));
        }
        return (LoginMethodHandler[]) arrayList.toArray(new LoginMethodHandler[0]);
    }

    public final boolean checkInternetPermission() {
        if (this.checkedInternetPermission) {
            return true;
        }
        if (checkPermission("android.permission.INTERNET") != 0) {
            FragmentActivity activity = getActivity();
            String str = null;
            String string = activity != null ? activity.getString(R$string.com_facebook_internet_permission_error_title) : null;
            if (activity != null) {
                str = activity.getString(R$string.com_facebook_internet_permission_error_message);
            }
            complete(Result.Companion.createErrorResult$default(Result.Companion, this.pendingRequest, string, str, (String) null, 8, (Object) null));
            return false;
        }
        this.checkedInternetPermission = true;
        return true;
    }

    public final void tryNextHandler() {
        LoginMethodHandler currentHandler2 = getCurrentHandler();
        if (currentHandler2 != null) {
            logAuthorizationMethodComplete(currentHandler2.getNameForLogging(), "skipped", (String) null, (String) null, currentHandler2.getMethodLoggingExtras());
        }
        LoginMethodHandler[] loginMethodHandlerArr = this.handlersToTry;
        while (loginMethodHandlerArr != null) {
            int i = this.currentHandler;
            if (i >= loginMethodHandlerArr.length - 1) {
                break;
            }
            this.currentHandler = i + 1;
            if (tryCurrentHandler()) {
                return;
            }
        }
        if (this.pendingRequest != null) {
            completeWithFailure();
        }
    }

    private final void completeWithFailure() {
        complete(Result.Companion.createErrorResult$default(Result.Companion, this.pendingRequest, "Login attempt failed.", (String) null, (String) null, 8, (Object) null));
    }

    private final void addLoggingExtra(String str, String str2, boolean z) {
        Map map = this.loggingExtras;
        if (map == null) {
            map = new HashMap();
        }
        if (this.loggingExtras == null) {
            this.loggingExtras = map;
        }
        if (map.containsKey(str) && z) {
            str2 = ((String) map.get(str)) + ',' + str2;
        }
        map.put(str, str2);
    }

    public final boolean tryCurrentHandler() {
        String str;
        String str2;
        LoginMethodHandler currentHandler2 = getCurrentHandler();
        if (currentHandler2 == null) {
            return false;
        }
        if (!currentHandler2.needsInternetPermission() || checkInternetPermission()) {
            Request request = this.pendingRequest;
            if (request == null) {
                return false;
            }
            int tryAuthorize = currentHandler2.tryAuthorize(request);
            this.numActivitiesReturned = 0;
            if (tryAuthorize > 0) {
                LoginLogger logger = getLogger();
                String authId = request.getAuthId();
                String nameForLogging = currentHandler2.getNameForLogging();
                if (request.isFamilyLogin()) {
                    str2 = "foa_mobile_login_method_start";
                } else {
                    str2 = "fb_mobile_login_method_start";
                }
                logger.logAuthorizationMethodStart(authId, nameForLogging, str2);
                this.numTotalIntentsFired = tryAuthorize;
            } else {
                LoginLogger logger2 = getLogger();
                String authId2 = request.getAuthId();
                String nameForLogging2 = currentHandler2.getNameForLogging();
                if (request.isFamilyLogin()) {
                    str = "foa_mobile_login_method_not_tried";
                } else {
                    str = "fb_mobile_login_method_not_tried";
                }
                logger2.logAuthorizationMethodNotTried(authId2, nameForLogging2, str);
                addLoggingExtra("not_tried", currentHandler2.getNameForLogging(), true);
            }
            if (tryAuthorize > 0) {
                return true;
            }
            return false;
        }
        addLoggingExtra("no_internet_permission", "1", false);
        return false;
    }

    public final void completeAndValidate(Result result) {
        Intrinsics.checkNotNullParameter(result, "outcome");
        if (result.token == null || !AccessToken.Companion.isCurrentAccessTokenActive()) {
            complete(result);
        } else {
            validateSameFbidAndFinish(result);
        }
    }

    public final void complete(Result result) {
        Intrinsics.checkNotNullParameter(result, "outcome");
        LoginMethodHandler currentHandler2 = getCurrentHandler();
        if (currentHandler2 != null) {
            logAuthorizationMethodComplete(currentHandler2.getNameForLogging(), result, currentHandler2.getMethodLoggingExtras());
        }
        Map map = this.loggingExtras;
        if (map != null) {
            result.loggingExtras = map;
        }
        Map map2 = this.extraData;
        if (map2 != null) {
            result.extraData = map2;
        }
        this.handlersToTry = null;
        this.currentHandler = -1;
        this.pendingRequest = null;
        this.loggingExtras = null;
        this.numActivitiesReturned = 0;
        this.numTotalIntentsFired = 0;
        notifyOnCompleteListener(result);
    }

    public final int checkPermission(String str) {
        Intrinsics.checkNotNullParameter(str, "permission");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity.checkCallingOrSelfPermission(str);
        }
        return -1;
    }

    public final void validateSameFbidAndFinish(Result result) {
        Result result2;
        Intrinsics.checkNotNullParameter(result, "pendingResult");
        if (result.token != null) {
            AccessToken currentAccessToken = AccessToken.Companion.getCurrentAccessToken();
            AccessToken accessToken = result.token;
            if (currentAccessToken != null) {
                try {
                    if (Intrinsics.areEqual((Object) currentAccessToken.getUserId(), (Object) accessToken.getUserId())) {
                        result2 = Result.Companion.createCompositeTokenResult(this.pendingRequest, result.token, result.authenticationToken);
                        complete(result2);
                        return;
                    }
                } catch (Exception e) {
                    complete(Result.Companion.createErrorResult$default(Result.Companion, this.pendingRequest, "Caught exception", e.getMessage(), (String) null, 8, (Object) null));
                    return;
                }
            }
            result2 = Result.Companion.createErrorResult$default(Result.Companion, this.pendingRequest, "User logged in as different Facebook user.", (String) null, (String) null, 8, (Object) null);
            complete(result2);
            return;
        }
        throw new FacebookException("Can't validate without a token");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2 != null ? r2.getApplicationId() : null) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.facebook.login.LoginLogger getLogger() {
        /*
            r3 = this;
            com.facebook.login.LoginLogger r0 = r3.loginLogger
            if (r0 == 0) goto L_0x0018
            java.lang.String r1 = r0.getApplicationId()
            com.facebook.login.LoginClient$Request r2 = r3.pendingRequest
            if (r2 == 0) goto L_0x0011
            java.lang.String r2 = r2.getApplicationId()
            goto L_0x0012
        L_0x0011:
            r2 = 0
        L_0x0012:
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 != 0) goto L_0x0038
        L_0x0018:
            com.facebook.login.LoginLogger r0 = new com.facebook.login.LoginLogger
            androidx.fragment.app.FragmentActivity r1 = r3.getActivity()
            if (r1 == 0) goto L_0x0021
            goto L_0x0025
        L_0x0021:
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()
        L_0x0025:
            com.facebook.login.LoginClient$Request r2 = r3.pendingRequest
            if (r2 == 0) goto L_0x002f
            java.lang.String r2 = r2.getApplicationId()
            if (r2 != 0) goto L_0x0033
        L_0x002f:
            java.lang.String r2 = com.facebook.FacebookSdk.getApplicationId()
        L_0x0033:
            r0.<init>(r1, r2)
            r3.loginLogger = r0
        L_0x0038:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.LoginClient.getLogger():com.facebook.login.LoginLogger");
    }

    private final void notifyOnCompleteListener(Result result) {
        OnCompletedListener onCompletedListener2 = this.onCompletedListener;
        if (onCompletedListener2 != null) {
            onCompletedListener2.onCompleted(result);
        }
    }

    public final void notifyBackgroundProcessingStart() {
        BackgroundProcessingListener backgroundProcessingListener2 = this.backgroundProcessingListener;
        if (backgroundProcessingListener2 != null) {
            backgroundProcessingListener2.onBackgroundProcessingStarted();
        }
    }

    public final void notifyBackgroundProcessingStop() {
        BackgroundProcessingListener backgroundProcessingListener2 = this.backgroundProcessingListener;
        if (backgroundProcessingListener2 != null) {
            backgroundProcessingListener2.onBackgroundProcessingStopped();
        }
    }

    private final void logAuthorizationMethodComplete(String str, Result result, Map map) {
        logAuthorizationMethodComplete(str, result.code.getLoggingValue(), result.errorMessage, result.errorCode, map);
    }

    private final void logAuthorizationMethodComplete(String str, String str2, String str3, String str4, Map map) {
        Request request = this.pendingRequest;
        String str5 = "fb_mobile_login_method_complete";
        if (request == null) {
            getLogger().logUnexpectedError(str5, "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", str);
            return;
        }
        LoginLogger logger = getLogger();
        String authId = request.getAuthId();
        if (request.isFamilyLogin()) {
            str5 = "foa_mobile_login_method_complete";
        }
        logger.logAuthorizationMethodComplete(authId, str, str2, str3, str4, map, str5);
    }

    public static final class Request implements Parcelable {
        public static final Parcelable.Creator<Request> CREATOR = new LoginClient$Request$Companion$CREATOR$1();
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String applicationId;
        private String authId;
        private String authType;
        private final String codeChallenge;
        private final CodeChallengeMethod codeChallengeMethod;
        private final String codeVerifier;
        private final DefaultAudience defaultAudience;
        private String deviceAuthTargetUserId;
        private String deviceRedirectUriString;
        private boolean isFamilyLogin;
        private boolean isRerequest;
        private final LoginBehavior loginBehavior;
        private final LoginTargetApp loginTargetApp;
        private String messengerPageId;
        private final String nonce;
        private Set permissions;
        private boolean resetMessengerState;
        private boolean shouldSkipAccountDeduplication;

        public /* synthetic */ Request(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
            this(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public final LoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        public final Set getPermissions() {
            return this.permissions;
        }

        public final void setPermissions(Set set) {
            Intrinsics.checkNotNullParameter(set, "<set-?>");
            this.permissions = set;
        }

        public final DefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        public final String getApplicationId() {
            return this.applicationId;
        }

        public final String getAuthId() {
            return this.authId;
        }

        public final boolean isRerequest() {
            return this.isRerequest;
        }

        public final String getDeviceRedirectUriString() {
            return this.deviceRedirectUriString;
        }

        public final String getAuthType() {
            return this.authType;
        }

        public final String getDeviceAuthTargetUserId() {
            return this.deviceAuthTargetUserId;
        }

        public final String getMessengerPageId() {
            return this.messengerPageId;
        }

        public final boolean getResetMessengerState() {
            return this.resetMessengerState;
        }

        public final LoginTargetApp getLoginTargetApp() {
            return this.loginTargetApp;
        }

        public final boolean isFamilyLogin() {
            return this.isFamilyLogin;
        }

        public final String getNonce() {
            return this.nonce;
        }

        public final String getCodeVerifier() {
            return this.codeVerifier;
        }

        public final String getCodeChallenge() {
            return this.codeChallenge;
        }

        public final CodeChallengeMethod getCodeChallengeMethod() {
            return this.codeChallengeMethod;
        }

        public final boolean shouldSkipAccountDeduplication() {
            return this.shouldSkipAccountDeduplication;
        }

        public final boolean hasPublishPermission() {
            for (String isPublishPermission : this.permissions) {
                if (LoginManager.Companion.isPublishPermission(isPublishPermission)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isInstagramLogin() {
            return this.loginTargetApp == LoginTargetApp.INSTAGRAM;
        }

        private Request(Parcel parcel) {
            DefaultAudience defaultAudience2;
            LoginTargetApp loginTargetApp2;
            this.loginBehavior = LoginBehavior.valueOf(Validate.notNullOrEmpty(parcel.readString(), "loginBehavior"));
            ArrayList arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.permissions = new HashSet(arrayList);
            String readString = parcel.readString();
            if (readString != null) {
                defaultAudience2 = DefaultAudience.valueOf(readString);
            } else {
                defaultAudience2 = DefaultAudience.NONE;
            }
            this.defaultAudience = defaultAudience2;
            this.applicationId = Validate.notNullOrEmpty(parcel.readString(), "applicationId");
            this.authId = Validate.notNullOrEmpty(parcel.readString(), "authId");
            boolean z = false;
            this.isRerequest = parcel.readByte() != 0;
            this.deviceRedirectUriString = parcel.readString();
            this.authType = Validate.notNullOrEmpty(parcel.readString(), "authType");
            this.deviceAuthTargetUserId = parcel.readString();
            this.messengerPageId = parcel.readString();
            this.resetMessengerState = parcel.readByte() != 0;
            String readString2 = parcel.readString();
            if (readString2 != null) {
                loginTargetApp2 = LoginTargetApp.valueOf(readString2);
            } else {
                loginTargetApp2 = LoginTargetApp.FACEBOOK;
            }
            this.loginTargetApp = loginTargetApp2;
            this.isFamilyLogin = parcel.readByte() != 0;
            this.shouldSkipAccountDeduplication = parcel.readByte() != 0 ? true : z;
            this.nonce = Validate.notNullOrEmpty(parcel.readString(), "nonce");
            this.codeVerifier = parcel.readString();
            this.codeChallenge = parcel.readString();
            String readString3 = parcel.readString();
            this.codeChallengeMethod = readString3 != null ? CodeChallengeMethod.valueOf(readString3) : null;
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "dest");
            parcel.writeString(this.loginBehavior.name());
            parcel.writeStringList(new ArrayList(this.permissions));
            parcel.writeString(this.defaultAudience.name());
            parcel.writeString(this.applicationId);
            parcel.writeString(this.authId);
            parcel.writeByte(this.isRerequest ? (byte) 1 : 0);
            parcel.writeString(this.deviceRedirectUriString);
            parcel.writeString(this.authType);
            parcel.writeString(this.deviceAuthTargetUserId);
            parcel.writeString(this.messengerPageId);
            parcel.writeByte(this.resetMessengerState ? (byte) 1 : 0);
            parcel.writeString(this.loginTargetApp.name());
            parcel.writeByte(this.isFamilyLogin ? (byte) 1 : 0);
            parcel.writeByte(this.shouldSkipAccountDeduplication ? (byte) 1 : 0);
            parcel.writeString(this.nonce);
            parcel.writeString(this.codeVerifier);
            parcel.writeString(this.codeChallenge);
            CodeChallengeMethod codeChallengeMethod2 = this.codeChallengeMethod;
            parcel.writeString(codeChallengeMethod2 != null ? codeChallengeMethod2.name() : null);
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    public static final class Result implements Parcelable {
        public static final Parcelable.Creator<Result> CREATOR = new LoginClient$Result$Companion$CREATOR$1();
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public final AuthenticationToken authenticationToken;
        public final Code code;
        public final String errorCode;
        public final String errorMessage;
        public Map extraData;
        public Map loggingExtras;
        public final Request request;
        public final AccessToken token;

        public /* synthetic */ Result(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
            this(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public enum Code {
            SUCCESS(FirebaseAnalytics.Param.SUCCESS),
            CANCEL("cancel"),
            ERROR("error");
            
            private final String loggingValue;

            private Code(String str) {
                this.loggingValue = str;
            }

            public final String getLoggingValue() {
                return this.loggingValue;
            }
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Result(Request request2, Code code2, AccessToken accessToken, String str, String str2) {
            this(request2, code2, accessToken, (AuthenticationToken) null, str, str2);
            Intrinsics.checkNotNullParameter(code2, "code");
        }

        public Result(Request request2, Code code2, AccessToken accessToken, AuthenticationToken authenticationToken2, String str, String str2) {
            Intrinsics.checkNotNullParameter(code2, "code");
            this.request = request2;
            this.token = accessToken;
            this.authenticationToken = authenticationToken2;
            this.errorMessage = str;
            this.code = code2;
            this.errorCode = str2;
        }

        private Result(Parcel parcel) {
            String readString = parcel.readString();
            this.code = Code.valueOf(readString == null ? "error" : readString);
            this.token = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.authenticationToken = (AuthenticationToken) parcel.readParcelable(AuthenticationToken.class.getClassLoader());
            this.errorMessage = parcel.readString();
            this.errorCode = parcel.readString();
            this.request = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.loggingExtras = Utility.readNonnullStringMapFromParcel(parcel);
            this.extraData = Utility.readNonnullStringMapFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "dest");
            parcel.writeString(this.code.name());
            parcel.writeParcelable(this.token, i);
            parcel.writeParcelable(this.authenticationToken, i);
            parcel.writeString(this.errorMessage);
            parcel.writeString(this.errorCode);
            parcel.writeParcelable(this.request, i);
            Utility.writeNonnullStringMapToParcel(parcel, this.loggingExtras);
            Utility.writeNonnullStringMapToParcel(parcel, this.extraData);
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Result createTokenResult(Request request, AccessToken accessToken) {
                Intrinsics.checkNotNullParameter(accessToken, "token");
                return new Result(request, Code.SUCCESS, accessToken, (String) null, (String) null);
            }

            public final Result createCompositeTokenResult(Request request, AccessToken accessToken, AuthenticationToken authenticationToken) {
                return new Result(request, Code.SUCCESS, accessToken, authenticationToken, (String) null, (String) null);
            }

            public final Result createCancelResult(Request request, String str) {
                return new Result(request, Code.CANCEL, (AccessToken) null, str, (String) null);
            }

            public static /* synthetic */ Result createErrorResult$default(Companion companion, Request request, String str, String str2, String str3, int i, Object obj) {
                if ((i & 8) != 0) {
                    str3 = null;
                }
                return companion.createErrorResult(request, str, str2, str3);
            }

            public final Result createErrorResult(Request request, String str, String str2, String str3) {
                ArrayList arrayList = new ArrayList();
                if (str != null) {
                    arrayList.add(str);
                }
                if (str2 != null) {
                    arrayList.add(str2);
                }
                return new Result(request, Code.ERROR, (AccessToken) null, TextUtils.join(": ", arrayList), str3);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.util.Map] */
    /* JADX WARNING: type inference failed for: r5v3, types: [java.lang.Object, com.facebook.login.LoginMethodHandler] */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LoginClient(android.os.Parcel r9) {
        /*
            r8 = this;
            java.lang.String r0 = "source"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r8.<init>()
            r0 = -1
            r8.currentHandler = r0
            java.lang.Class<com.facebook.login.LoginMethodHandler> r0 = com.facebook.login.LoginMethodHandler.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable[] r0 = r9.readParcelableArray(r0)
            r1 = 0
            if (r0 != 0) goto L_0x001a
            android.os.Parcelable[] r0 = new android.os.Parcelable[r1]
        L_0x001a:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            int r3 = r0.length
            r4 = r1
        L_0x0021:
            r5 = 0
            if (r4 >= r3) goto L_0x003b
            r6 = r0[r4]
            boolean r7 = r6 instanceof com.facebook.login.LoginMethodHandler
            if (r7 == 0) goto L_0x002d
            r5 = r6
            com.facebook.login.LoginMethodHandler r5 = (com.facebook.login.LoginMethodHandler) r5
        L_0x002d:
            if (r5 != 0) goto L_0x0030
            goto L_0x0033
        L_0x0030:
            r5.setLoginClient(r8)
        L_0x0033:
            if (r5 == 0) goto L_0x0038
            r2.add(r5)
        L_0x0038:
            int r4 = r4 + 1
            goto L_0x0021
        L_0x003b:
            com.facebook.login.LoginMethodHandler[] r0 = new com.facebook.login.LoginMethodHandler[r1]
            java.lang.Object[] r0 = r2.toArray(r0)
            com.facebook.login.LoginMethodHandler[] r0 = (com.facebook.login.LoginMethodHandler[]) r0
            r8.handlersToTry = r0
            int r0 = r9.readInt()
            r8.currentHandler = r0
            java.lang.Class<com.facebook.login.LoginClient$Request> r0 = com.facebook.login.LoginClient.Request.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r9.readParcelable(r0)
            com.facebook.login.LoginClient$Request r0 = (com.facebook.login.LoginClient.Request) r0
            r8.pendingRequest = r0
            java.util.Map r0 = com.facebook.internal.Utility.readNonnullStringMapFromParcel(r9)
            if (r0 == 0) goto L_0x0064
            java.util.Map r0 = kotlin.collections.MapsKt.toMutableMap(r0)
            goto L_0x0065
        L_0x0064:
            r0 = r5
        L_0x0065:
            r8.loggingExtras = r0
            java.util.Map r9 = com.facebook.internal.Utility.readNonnullStringMapFromParcel(r9)
            if (r9 == 0) goto L_0x0071
            java.util.Map r5 = kotlin.collections.MapsKt.toMutableMap(r9)
        L_0x0071:
            r8.extraData = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.LoginClient.<init>(android.os.Parcel):void");
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeParcelableArray((Parcelable[]) this.handlersToTry, i);
        parcel.writeInt(this.currentHandler);
        parcel.writeParcelable(this.pendingRequest, i);
        Utility.writeNonnullStringMapToParcel(parcel, this.loggingExtras);
        Utility.writeNonnullStringMapToParcel(parcel, this.extraData);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getLoginRequestCode() {
            return CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode();
        }

        public final String getE2E() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("init", System.currentTimeMillis());
            } catch (JSONException unused) {
            }
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "e2e.toString()");
            return jSONObject2;
        }
    }
}
