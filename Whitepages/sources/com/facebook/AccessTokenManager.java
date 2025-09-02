package com.facebook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.internal.Utility;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AccessTokenManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static AccessTokenManager instanceField;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessTokenField;
    private Date lastAttemptedTokenExtendDate = new Date(0);
    private final LocalBroadcastManager localBroadcastManager;
    private final AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);

    public interface RefreshTokenInfo {
        String getGrantType();

        String getGraphPath();
    }

    public AccessTokenManager(LocalBroadcastManager localBroadcastManager2, AccessTokenCache accessTokenCache2) {
        Intrinsics.checkNotNullParameter(localBroadcastManager2, "localBroadcastManager");
        Intrinsics.checkNotNullParameter(accessTokenCache2, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager2;
        this.accessTokenCache = accessTokenCache2;
    }

    public final AccessToken getCurrentAccessToken() {
        return this.currentAccessTokenField;
    }

    public final void setCurrentAccessToken(AccessToken accessToken) {
        setCurrentAccessToken(accessToken, true);
    }

    public static final class FacebookRefreshTokenInfo implements RefreshTokenInfo {
        private final String grantType = "fb_extend_sso_token";
        private final String graphPath = "oauth/access_token";

        public String getGraphPath() {
            return this.graphPath;
        }

        public String getGrantType() {
            return this.grantType;
        }
    }

    public static final class InstagramRefreshTokenInfo implements RefreshTokenInfo {
        private final String grantType = "ig_refresh_token";
        private final String graphPath = "refresh_access_token";

        public String getGraphPath() {
            return this.graphPath;
        }

        public String getGrantType() {
            return this.grantType;
        }
    }

    public final boolean loadCurrentAccessToken() {
        AccessToken load = this.accessTokenCache.load();
        if (load == null) {
            return false;
        }
        setCurrentAccessToken(load, false);
        return true;
    }

    public final void currentAccessTokenChanged() {
        sendCurrentAccessTokenChangedBroadcastIntent(getCurrentAccessToken(), getCurrentAccessToken());
    }

    private final void setCurrentAccessToken(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.currentAccessTokenField;
        this.currentAccessTokenField = accessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.accessTokenCache.save(accessToken);
            } else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(accessToken2, accessToken)) {
            sendCurrentAccessTokenChangedBroadcastIntent(accessToken2, accessToken);
            setTokenExpirationBroadcastAlarm();
        }
    }

    private final void sendCurrentAccessTokenChangedBroadcastIntent(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(FacebookSdk.getApplicationContext(), CurrentAccessTokenExpirationBroadcastReceiver.class);
        intent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", accessToken);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private final void setTokenExpirationBroadcastAlarm() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        AccessToken.Companion companion = AccessToken.Companion;
        AccessToken currentAccessToken = companion.getCurrentAccessToken();
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService("alarm");
        if (companion.isCurrentAccessTokenActive()) {
            if ((currentAccessToken != null ? currentAccessToken.getExpires() : null) != null && alarmManager != null) {
                Intent intent = new Intent(applicationContext, CurrentAccessTokenExpirationBroadcastReceiver.class);
                intent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
                try {
                    alarmManager.set(1, currentAccessToken.getExpires().getTime(), PendingIntent.getBroadcast(applicationContext, 0, intent, 67108864));
                } catch (Exception unused) {
                }
            }
        }
    }

    public final void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            refreshCurrentAccessToken((AccessToken.AccessTokenRefreshCallback) null);
        }
    }

    private final boolean shouldExtendAccessToken() {
        AccessToken currentAccessToken = getCurrentAccessToken();
        if (currentAccessToken == null) {
            return false;
        }
        long time = new Date().getTime();
        if (!currentAccessToken.getSource().canExtendToken() || time - this.lastAttemptedTokenExtendDate.getTime() <= 3600000 || time - currentAccessToken.getLastRefresh().getTime() <= NetworkManager.MAX_SERVER_RETRY) {
            return false;
        }
        return true;
    }

    private static final class RefreshResult {
        private String accessToken;
        private Long dataAccessExpirationTime;
        private int expiresAt;
        private int expiresIn;
        private String graphDomain;

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final void setAccessToken(String str) {
            this.accessToken = str;
        }

        public final int getExpiresAt() {
            return this.expiresAt;
        }

        public final void setExpiresAt(int i) {
            this.expiresAt = i;
        }

        public final int getExpiresIn() {
            return this.expiresIn;
        }

        public final void setExpiresIn(int i) {
            this.expiresIn = i;
        }

        public final Long getDataAccessExpirationTime() {
            return this.dataAccessExpirationTime;
        }

        public final void setDataAccessExpirationTime(Long l) {
            this.dataAccessExpirationTime = l;
        }

        public final String getGraphDomain() {
            return this.graphDomain;
        }

        public final void setGraphDomain(String str) {
            this.graphDomain = str;
        }
    }

    public final void refreshCurrentAccessToken(AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Intrinsics.areEqual((Object) Looper.getMainLooper(), (Object) Looper.myLooper())) {
            refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
        } else {
            new Handler(Looper.getMainLooper()).post(new AccessTokenManager$$ExternalSyntheticLambda0(this, accessTokenRefreshCallback));
        }
    }

    /* access modifiers changed from: private */
    public static final void refreshCurrentAccessToken$lambda$0(AccessTokenManager accessTokenManager, AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        Intrinsics.checkNotNullParameter(accessTokenManager, "this$0");
        accessTokenManager.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
    }

    private final void refreshCurrentAccessTokenImpl(AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        AccessToken currentAccessToken = getCurrentAccessToken();
        if (currentAccessToken == null) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        } else if (this.tokenRefreshInProgress.compareAndSet(false, true)) {
            this.lastAttemptedTokenExtendDate = new Date();
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            HashSet hashSet3 = new HashSet();
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            RefreshResult refreshResult = new RefreshResult();
            Companion companion = Companion;
            GraphRequestBatch graphRequestBatch = new GraphRequestBatch(companion.createGrantedPermissionsRequest(currentAccessToken, new AccessTokenManager$$ExternalSyntheticLambda1(atomicBoolean, hashSet, hashSet2, hashSet3)), companion.createExtendAccessTokenRequest(currentAccessToken, new AccessTokenManager$$ExternalSyntheticLambda2(refreshResult)));
            graphRequestBatch.addCallback(new AccessTokenManager$$ExternalSyntheticLambda3(refreshResult, currentAccessToken, accessTokenRefreshCallback, atomicBoolean, hashSet, hashSet2, hashSet3, this));
            graphRequestBatch.executeAsync();
        } else if (accessTokenRefreshCallback != null) {
            accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
        }
    }

    /* access modifiers changed from: private */
    public static final void refreshCurrentAccessTokenImpl$lambda$1(AtomicBoolean atomicBoolean, Set set, Set set2, Set set3, GraphResponse graphResponse) {
        JSONArray optJSONArray;
        Intrinsics.checkNotNullParameter(atomicBoolean, "$permissionsCallSucceeded");
        Intrinsics.checkNotNullParameter(set, "$permissions");
        Intrinsics.checkNotNullParameter(set2, "$declinedPermissions");
        Intrinsics.checkNotNullParameter(set3, "$expiredPermissions");
        Intrinsics.checkNotNullParameter(graphResponse, "response");
        JSONObject jsonObject = graphResponse.getJsonObject();
        if (jsonObject != null && (optJSONArray = jsonObject.optJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)) != null) {
            atomicBoolean.set(true);
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("permission");
                    String optString2 = optJSONObject.optString("status");
                    if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
                        Intrinsics.checkNotNullExpressionValue(optString2, "status");
                        Locale locale = Locale.US;
                        Intrinsics.checkNotNullExpressionValue(locale, "US");
                        String lowerCase = optString2.toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "status");
                        int hashCode = lowerCase.hashCode();
                        if (hashCode != -1309235419) {
                            if (hashCode != 280295099) {
                                if (hashCode == 568196142 && lowerCase.equals("declined")) {
                                    set2.add(optString);
                                }
                            } else if (lowerCase.equals("granted")) {
                                set.add(optString);
                            }
                        } else if (lowerCase.equals("expired")) {
                            set3.add(optString);
                        }
                        Log.w("AccessTokenManager", "Unexpected status: " + lowerCase);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void refreshCurrentAccessTokenImpl$lambda$2(RefreshResult refreshResult, GraphResponse graphResponse) {
        Intrinsics.checkNotNullParameter(refreshResult, "$refreshResult");
        Intrinsics.checkNotNullParameter(graphResponse, "response");
        JSONObject jsonObject = graphResponse.getJsonObject();
        if (jsonObject != null) {
            refreshResult.setAccessToken(jsonObject.optString("access_token"));
            refreshResult.setExpiresAt(jsonObject.optInt("expires_at"));
            refreshResult.setExpiresIn(jsonObject.optInt("expires_in"));
            refreshResult.setDataAccessExpirationTime(Long.valueOf(jsonObject.optLong("data_access_expiration_time")));
            refreshResult.setGraphDomain(jsonObject.optString("graph_domain", (String) null));
        }
    }

    /* access modifiers changed from: private */
    public static final void refreshCurrentAccessTokenImpl$lambda$3(RefreshResult refreshResult, AccessToken accessToken, AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback, AtomicBoolean atomicBoolean, Set set, Set set2, Set set3, AccessTokenManager accessTokenManager, GraphRequestBatch graphRequestBatch) {
        AccessToken accessToken2;
        boolean z;
        Set set4;
        Date dataAccessExpirationTime;
        AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback2 = accessTokenRefreshCallback;
        AccessTokenManager accessTokenManager2 = accessTokenManager;
        Intrinsics.checkNotNullParameter(refreshResult, "$refreshResult");
        Intrinsics.checkNotNullParameter(atomicBoolean, "$permissionsCallSucceeded");
        Set set5 = set;
        Intrinsics.checkNotNullParameter(set5, "$permissions");
        Set set6 = set2;
        Intrinsics.checkNotNullParameter(set6, "$declinedPermissions");
        Set set7 = set3;
        Intrinsics.checkNotNullParameter(set7, "$expiredPermissions");
        Intrinsics.checkNotNullParameter(accessTokenManager2, "this$0");
        Intrinsics.checkNotNullParameter(graphRequestBatch, "it");
        String accessToken3 = refreshResult.getAccessToken();
        int expiresAt = refreshResult.getExpiresAt();
        Long dataAccessExpirationTime2 = refreshResult.getDataAccessExpirationTime();
        String graphDomain = refreshResult.getGraphDomain();
        try {
            Companion companion = Companion;
            if (companion.getInstance().getCurrentAccessToken() != null) {
                AccessToken currentAccessToken = companion.getInstance().getCurrentAccessToken();
                try {
                    if ((currentAccessToken != null ? currentAccessToken.getUserId() : null) == accessToken.getUserId()) {
                        if (!atomicBoolean.get() && accessToken3 == null && expiresAt == 0) {
                            if (accessTokenRefreshCallback2 != null) {
                                accessTokenRefreshCallback2.OnTokenRefreshFailed(new FacebookException("Failed to refresh access token"));
                            }
                            accessTokenManager2.tokenRefreshInProgress.set(false);
                            return;
                        }
                        Date expires = accessToken.getExpires();
                        if (refreshResult.getExpiresAt() != 0) {
                            expires = new Date(((long) refreshResult.getExpiresAt()) * 1000);
                        } else if (refreshResult.getExpiresIn() != 0) {
                            expires = new Date((((long) refreshResult.getExpiresIn()) * 1000) + new Date().getTime());
                        }
                        Date date = expires;
                        if (accessToken3 == null) {
                            accessToken3 = accessToken.getToken();
                        }
                        String str = accessToken3;
                        String applicationId = accessToken.getApplicationId();
                        String userId = accessToken.getUserId();
                        Collection permissions = atomicBoolean.get() ? set5 : accessToken.getPermissions();
                        if (atomicBoolean.get()) {
                            set4 = set6;
                        } else {
                            set4 = accessToken.getDeclinedPermissions();
                        }
                        Collection collection = set4;
                        if (!atomicBoolean.get()) {
                            set7 = accessToken.getExpiredPermissions();
                        }
                        Collection collection2 = set7;
                        AccessTokenSource source = accessToken.getSource();
                        Date date2 = new Date();
                        if (dataAccessExpirationTime2 != null) {
                            dataAccessExpirationTime = new Date(dataAccessExpirationTime2.longValue() * 1000);
                        } else {
                            dataAccessExpirationTime = accessToken.getDataAccessExpirationTime();
                        }
                        Date date3 = dataAccessExpirationTime;
                        if (graphDomain == null) {
                            graphDomain = accessToken.getGraphDomain();
                        }
                        AccessToken accessToken4 = new AccessToken(str, applicationId, userId, permissions, collection, collection2, source, date, date2, date3, graphDomain);
                        try {
                            companion.getInstance().setCurrentAccessToken(accessToken4);
                            accessTokenManager2.tokenRefreshInProgress.set(false);
                            if (accessTokenRefreshCallback2 != null) {
                                accessTokenRefreshCallback2.OnTokenRefreshed(accessToken4);
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            accessToken2 = accessToken4;
                            z = false;
                            accessTokenManager2.tokenRefreshInProgress.set(z);
                            if (!(accessTokenRefreshCallback2 == null || accessToken2 == null)) {
                                accessTokenRefreshCallback2.OnTokenRefreshed(accessToken2);
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z = false;
                    accessToken2 = null;
                    accessTokenManager2.tokenRefreshInProgress.set(z);
                    accessTokenRefreshCallback2.OnTokenRefreshed(accessToken2);
                    throw th;
                }
            }
            if (accessTokenRefreshCallback2 != null) {
                accessTokenRefreshCallback2.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
            accessTokenManager2.tokenRefreshInProgress.set(false);
        } catch (Throwable th3) {
            th = th3;
            z = false;
            accessToken2 = null;
            accessTokenManager2.tokenRefreshInProgress.set(z);
            accessTokenRefreshCallback2.OnTokenRefreshed(accessToken2);
            throw th;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AccessTokenManager getInstance() {
            AccessTokenManager access$getInstanceField$cp;
            AccessTokenManager access$getInstanceField$cp2 = AccessTokenManager.instanceField;
            if (access$getInstanceField$cp2 != null) {
                return access$getInstanceField$cp2;
            }
            synchronized (this) {
                access$getInstanceField$cp = AccessTokenManager.instanceField;
                if (access$getInstanceField$cp == null) {
                    LocalBroadcastManager instance = LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext());
                    Intrinsics.checkNotNullExpressionValue(instance, "getInstance(applicationContext)");
                    AccessTokenManager accessTokenManager = new AccessTokenManager(instance, new AccessTokenCache());
                    AccessTokenManager.instanceField = accessTokenManager;
                    access$getInstanceField$cp = accessTokenManager;
                }
            }
            return access$getInstanceField$cp;
        }

        /* access modifiers changed from: private */
        public final GraphRequest createGrantedPermissionsRequest(AccessToken accessToken, GraphRequest.Callback callback) {
            Bundle bundle = new Bundle();
            bundle.putString("fields", "permission,status");
            GraphRequest newGraphPathRequest = GraphRequest.Companion.newGraphPathRequest(accessToken, "me/permissions", callback);
            newGraphPathRequest.setParameters(bundle);
            newGraphPathRequest.setHttpMethod(HttpMethod.GET);
            return newGraphPathRequest;
        }

        private final RefreshTokenInfo getRefreshTokenInfoForToken(AccessToken accessToken) {
            String graphDomain = accessToken.getGraphDomain();
            if (graphDomain == null) {
                graphDomain = "facebook";
            }
            if (Intrinsics.areEqual((Object) graphDomain, (Object) "instagram")) {
                return new InstagramRefreshTokenInfo();
            }
            return new FacebookRefreshTokenInfo();
        }

        /* access modifiers changed from: private */
        public final GraphRequest createExtendAccessTokenRequest(AccessToken accessToken, GraphRequest.Callback callback) {
            RefreshTokenInfo refreshTokenInfoForToken = getRefreshTokenInfoForToken(accessToken);
            Bundle bundle = new Bundle();
            bundle.putString("grant_type", refreshTokenInfoForToken.getGrantType());
            bundle.putString("client_id", accessToken.getApplicationId());
            bundle.putString("fields", "access_token,expires_at,expires_in,data_access_expiration_time,graph_domain");
            GraphRequest newGraphPathRequest = GraphRequest.Companion.newGraphPathRequest(accessToken, refreshTokenInfoForToken.getGraphPath(), callback);
            newGraphPathRequest.setParameters(bundle);
            newGraphPathRequest.setHttpMethod(HttpMethod.GET);
            return newGraphPathRequest;
        }
    }
}
