package com.facebook.login;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookSdk;
import com.facebook.internal.NativeProtocol;
import com.facebook.login.LoginClient;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class InstagramAppLoginMethodHandler extends NativeAppLoginMethodHandler {
    public static final Parcelable.Creator<InstagramAppLoginMethodHandler> CREATOR = new InstagramAppLoginMethodHandler$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String nameForLogging = "instagram_login";
    private final AccessTokenSource tokenSource = AccessTokenSource.INSTAGRAM_APPLICATION_WEB;

    public int describeContents() {
        return 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InstagramAppLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        Intrinsics.checkNotNullParameter(loginClient, "loginClient");
    }

    public String getNameForLogging() {
        return this.nameForLogging;
    }

    public AccessTokenSource getTokenSource() {
        return this.tokenSource;
    }

    public int tryAuthorize(LoginClient.Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        LoginClient.Companion companion = LoginClient.Companion;
        String e2e = companion.getE2E();
        Context activity = getLoginClient().getActivity();
        if (activity == null) {
            activity = FacebookSdk.getApplicationContext();
        }
        String applicationId = request.getApplicationId();
        Collection permissions = request.getPermissions();
        boolean isRerequest = request.isRerequest();
        boolean hasPublishPermission = request.hasPublishPermission();
        DefaultAudience defaultAudience = request.getDefaultAudience();
        if (defaultAudience == null) {
            defaultAudience = DefaultAudience.NONE;
        }
        Intent createInstagramIntent = NativeProtocol.createInstagramIntent(activity, applicationId, permissions, e2e, isRerequest, hasPublishPermission, defaultAudience, getClientState(request.getAuthId()), request.getAuthType(), request.getMessengerPageId(), request.getResetMessengerState(), request.isFamilyLogin(), request.shouldSkipAccountDeduplication());
        addLoggingExtra("e2e", e2e);
        return tryIntent(createInstagramIntent, companion.getLoginRequestCode()) ? 1 : 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InstagramAppLoginMethodHandler(Parcel parcel) {
        super(parcel);
        Intrinsics.checkNotNullParameter(parcel, "source");
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        super.writeToParcel(parcel, i);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
