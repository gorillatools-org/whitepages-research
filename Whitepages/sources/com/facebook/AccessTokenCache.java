package com.facebook;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessTokenCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final SharedPreferences sharedPreferences;
    private final SharedPreferencesTokenCachingStrategyFactory tokenCachingStrategyFactory;
    private LegacyTokenHelper tokenCachingStrategyField;

    public AccessTokenCache(SharedPreferences sharedPreferences2, SharedPreferencesTokenCachingStrategyFactory sharedPreferencesTokenCachingStrategyFactory) {
        Intrinsics.checkNotNullParameter(sharedPreferences2, "sharedPreferences");
        Intrinsics.checkNotNullParameter(sharedPreferencesTokenCachingStrategyFactory, "tokenCachingStrategyFactory");
        this.sharedPreferences = sharedPreferences2;
        this.tokenCachingStrategyFactory = sharedPreferencesTokenCachingStrategyFactory;
    }

    private final LegacyTokenHelper getTokenCachingStrategy() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            if (this.tokenCachingStrategyField == null) {
                synchronized (this) {
                    if (this.tokenCachingStrategyField == null) {
                        this.tokenCachingStrategyField = this.tokenCachingStrategyFactory.create();
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            LegacyTokenHelper legacyTokenHelper = this.tokenCachingStrategyField;
            if (legacyTokenHelper != null) {
                return legacyTokenHelper;
            }
            throw new IllegalStateException("Required value was null.");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AccessTokenCache() {
        /*
            r3 = this;
            android.content.Context r0 = com.facebook.FacebookSdk.getApplicationContext()
            java.lang.String r1 = "com.facebook.AccessTokenManager.SharedPreferences"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            java.lang.String r1 = "getApplicationContext()\n…ME, Context.MODE_PRIVATE)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.facebook.AccessTokenCache$SharedPreferencesTokenCachingStrategyFactory r1 = new com.facebook.AccessTokenCache$SharedPreferencesTokenCachingStrategyFactory
            r1.<init>()
            r3.<init>(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessTokenCache.<init>():void");
    }

    public final AccessToken load() {
        if (hasCachedAccessToken()) {
            return getCachedAccessToken();
        }
        if (!shouldCheckLegacyToken()) {
            return null;
        }
        AccessToken legacyAccessToken = getLegacyAccessToken();
        if (legacyAccessToken == null) {
            return legacyAccessToken;
        }
        save(legacyAccessToken);
        getTokenCachingStrategy().clear();
        return legacyAccessToken;
    }

    public final void save(AccessToken accessToken) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        try {
            this.sharedPreferences.edit().putString("com.facebook.AccessTokenManager.CachedAccessToken", accessToken.toJSONObject$facebook_core_release().toString()).apply();
        } catch (JSONException unused) {
        }
    }

    public final void clear() {
        this.sharedPreferences.edit().remove("com.facebook.AccessTokenManager.CachedAccessToken").apply();
        if (shouldCheckLegacyToken()) {
            getTokenCachingStrategy().clear();
        }
    }

    private final boolean hasCachedAccessToken() {
        return this.sharedPreferences.contains("com.facebook.AccessTokenManager.CachedAccessToken");
    }

    private final AccessToken getCachedAccessToken() {
        String string = this.sharedPreferences.getString("com.facebook.AccessTokenManager.CachedAccessToken", (String) null);
        if (string == null) {
            return null;
        }
        try {
            return AccessToken.Companion.createFromJSONObject$facebook_core_release(new JSONObject(string));
        } catch (JSONException unused) {
            return null;
        }
    }

    private final boolean shouldCheckLegacyToken() {
        return FacebookSdk.isLegacyTokenUpgradeSupported();
    }

    private final AccessToken getLegacyAccessToken() {
        Bundle load = getTokenCachingStrategy().load();
        if (load == null || !LegacyTokenHelper.Companion.hasTokenInformation(load)) {
            return null;
        }
        return AccessToken.Companion.createFromLegacyCache$facebook_core_release(load);
    }

    public static final class SharedPreferencesTokenCachingStrategyFactory {
        public final LegacyTokenHelper create() {
            return new LegacyTokenHelper(FacebookSdk.getApplicationContext(), (String) null, 2, (DefaultConstructorMarker) null);
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
