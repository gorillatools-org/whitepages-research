package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentActivity;
import com.facebook.FacebookSdk;
import com.facebook.internal.CustomTabUtils;
import com.facebook.internal.NativeProtocol;
import com.facebook.login.LoginClient;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class KatanaProxyLoginMethodHandler extends NativeAppLoginMethodHandler {
    public static final Parcelable.Creator<KatanaProxyLoginMethodHandler> CREATOR = new KatanaProxyLoginMethodHandler$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String nameForLogging = "katana_proxy_auth";

    public int describeContents() {
        return 0;
    }

    public boolean shouldKeepTrackOfMultipleIntents() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KatanaProxyLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        Intrinsics.checkNotNullParameter(loginClient, "loginClient");
    }

    public String getNameForLogging() {
        return this.nameForLogging;
    }

    public int tryAuthorize(LoginClient.Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        boolean z = FacebookSdk.ignoreAppSwitchToLoggedOut && CustomTabUtils.getChromePackage() != null && request.getLoginBehavior().allowsCustomTabAuth();
        String e2e = LoginClient.Companion.getE2E();
        FragmentActivity activity = getLoginClient().getActivity();
        String applicationId = request.getApplicationId();
        Collection permissions = request.getPermissions();
        boolean isRerequest = request.isRerequest();
        boolean hasPublishPermission = request.hasPublishPermission();
        DefaultAudience defaultAudience = request.getDefaultAudience();
        if (defaultAudience == null) {
            defaultAudience = DefaultAudience.NONE;
        }
        DefaultAudience defaultAudience2 = defaultAudience;
        String clientState = getClientState(request.getAuthId());
        String authType = request.getAuthType();
        String messengerPageId = request.getMessengerPageId();
        boolean resetMessengerState = request.getResetMessengerState();
        boolean isFamilyLogin = request.isFamilyLogin();
        boolean shouldSkipAccountDeduplication = request.shouldSkipAccountDeduplication();
        String nonce = request.getNonce();
        String codeChallenge = request.getCodeChallenge();
        CodeChallengeMethod codeChallengeMethod = request.getCodeChallengeMethod();
        List<Intent> createProxyAuthIntents = NativeProtocol.createProxyAuthIntents(activity, applicationId, permissions, e2e, isRerequest, hasPublishPermission, defaultAudience2, clientState, authType, z, messengerPageId, resetMessengerState, isFamilyLogin, shouldSkipAccountDeduplication, nonce, codeChallenge, codeChallengeMethod != null ? codeChallengeMethod.name() : null);
        addLoggingExtra("e2e", e2e);
        int i = 0;
        for (Intent tryIntent : createProxyAuthIntents) {
            i++;
            if (tryIntent(tryIntent, LoginClient.Companion.getLoginRequestCode())) {
                return i;
            }
        }
        return 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KatanaProxyLoginMethodHandler(Parcel parcel) {
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
