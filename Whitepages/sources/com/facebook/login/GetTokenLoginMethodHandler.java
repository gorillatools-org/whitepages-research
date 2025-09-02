package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import com.facebook.login.LoginMethodHandler;
import com.google.android.gms.common.Scopes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class GetTokenLoginMethodHandler extends LoginMethodHandler {
    public static final Parcelable.Creator<GetTokenLoginMethodHandler> CREATOR = new GetTokenLoginMethodHandler$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private GetTokenClient getTokenClient;
    private final String nameForLogging = "get_token";

    public int describeContents() {
        return 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetTokenLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        Intrinsics.checkNotNullParameter(loginClient, "loginClient");
    }

    public String getNameForLogging() {
        return this.nameForLogging;
    }

    public void cancel() {
        GetTokenClient getTokenClient2 = this.getTokenClient;
        if (getTokenClient2 != null) {
            getTokenClient2.cancel();
            getTokenClient2.setCompletedListener((PlatformServiceClient.CompletedListener) null);
            this.getTokenClient = null;
        }
    }

    public int tryAuthorize(LoginClient.Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        Context activity = getLoginClient().getActivity();
        if (activity == null) {
            activity = FacebookSdk.getApplicationContext();
        }
        GetTokenClient getTokenClient2 = new GetTokenClient(activity, request);
        this.getTokenClient = getTokenClient2;
        if (!getTokenClient2.start()) {
            return 0;
        }
        getLoginClient().notifyBackgroundProcessingStart();
        GetTokenLoginMethodHandler$$ExternalSyntheticLambda0 getTokenLoginMethodHandler$$ExternalSyntheticLambda0 = new GetTokenLoginMethodHandler$$ExternalSyntheticLambda0(this, request);
        GetTokenClient getTokenClient3 = this.getTokenClient;
        if (getTokenClient3 == null) {
            return 1;
        }
        getTokenClient3.setCompletedListener(getTokenLoginMethodHandler$$ExternalSyntheticLambda0);
        return 1;
    }

    /* access modifiers changed from: private */
    public static final void tryAuthorize$lambda$1(GetTokenLoginMethodHandler getTokenLoginMethodHandler, LoginClient.Request request, Bundle bundle) {
        Intrinsics.checkNotNullParameter(getTokenLoginMethodHandler, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        getTokenLoginMethodHandler.getTokenCompleted(request, bundle);
    }

    public final void getTokenCompleted(LoginClient.Request request, Bundle bundle) {
        Intrinsics.checkNotNullParameter(request, "request");
        GetTokenClient getTokenClient2 = this.getTokenClient;
        if (getTokenClient2 != null) {
            getTokenClient2.setCompletedListener((PlatformServiceClient.CompletedListener) null);
        }
        this.getTokenClient = null;
        getLoginClient().notifyBackgroundProcessingStop();
        if (bundle != null) {
            List stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
            if (stringArrayList == null) {
                stringArrayList = CollectionsKt.emptyList();
            }
            Set<String> permissions = request.getPermissions();
            if (permissions == null) {
                permissions = SetsKt.emptySet();
            }
            String string = bundle.getString("com.facebook.platform.extra.ID_TOKEN");
            if (permissions.contains(Scopes.OPEN_ID) && (string == null || string.length() == 0)) {
                getLoginClient().tryNextHandler();
                return;
            } else if (stringArrayList.containsAll(permissions)) {
                complete(request, bundle);
                return;
            } else {
                HashSet hashSet = new HashSet();
                for (String str : permissions) {
                    if (!stringArrayList.contains(str)) {
                        hashSet.add(str);
                    }
                }
                if (!hashSet.isEmpty()) {
                    addLoggingExtra("new_permissions", TextUtils.join(",", hashSet));
                }
                request.setPermissions(hashSet);
            }
        }
        getLoginClient().tryNextHandler();
    }

    public final void onComplete(LoginClient.Request request, Bundle bundle) {
        LoginClient.Result result;
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(bundle, "result");
        try {
            LoginMethodHandler.Companion companion = LoginMethodHandler.Companion;
            result = LoginClient.Result.Companion.createCompositeTokenResult(request, companion.createAccessTokenFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE, request.getApplicationId()), companion.createAuthenticationTokenFromNativeLogin(bundle, request.getNonce()));
        } catch (FacebookException e) {
            result = LoginClient.Result.Companion.createErrorResult$default(LoginClient.Result.Companion, getLoginClient().getPendingRequest(), (String) null, e.getMessage(), (String) null, 8, (Object) null);
        }
        getLoginClient().completeAndValidate(result);
    }

    public final void complete(LoginClient.Request request, Bundle bundle) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(bundle, "result");
        String string = bundle.getString("com.facebook.platform.extra.USER_ID");
        if (string == null || string.length() == 0) {
            getLoginClient().notifyBackgroundProcessingStart();
            String string2 = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
            if (string2 != null) {
                Intrinsics.checkNotNullExpressionValue(string2, "checkNotNull(result.getS…ocol.EXTRA_ACCESS_TOKEN))");
                Utility.getGraphMeRequestWithCacheAsync(string2, new GetTokenLoginMethodHandler$complete$1(bundle, this, request));
                return;
            }
            throw new IllegalStateException("Required value was null.");
        }
        onComplete(request, bundle);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetTokenLoginMethodHandler(Parcel parcel) {
        super(parcel);
        Intrinsics.checkNotNullParameter(parcel, "source");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
