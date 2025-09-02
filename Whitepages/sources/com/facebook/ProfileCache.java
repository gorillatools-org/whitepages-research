package com.facebook;

import android.content.SharedPreferences;
import com.google.android.gms.common.Scopes;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class ProfileCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final SharedPreferences sharedPreferences;

    public ProfileCache() {
        SharedPreferences sharedPreferences2 = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences2, "getApplicationContext()\n…ME, Context.MODE_PRIVATE)");
        this.sharedPreferences = sharedPreferences2;
    }

    public final Profile load() {
        String string = this.sharedPreferences.getString("com.facebook.ProfileManager.CachedProfile", (String) null);
        if (string != null) {
            try {
                return new Profile(new JSONObject(string));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public final void save(Profile profile) {
        Intrinsics.checkNotNullParameter(profile, Scopes.PROFILE);
        JSONObject jSONObject = profile.toJSONObject();
        if (jSONObject != null) {
            this.sharedPreferences.edit().putString("com.facebook.ProfileManager.CachedProfile", jSONObject.toString()).apply();
        }
    }

    public final void clear() {
        this.sharedPreferences.edit().remove("com.facebook.ProfileManager.CachedProfile").apply();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
