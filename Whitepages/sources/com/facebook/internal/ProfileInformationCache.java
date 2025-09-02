package com.facebook.internal;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class ProfileInformationCache {
    public static final ProfileInformationCache INSTANCE = new ProfileInformationCache();
    private static final ConcurrentHashMap infoCache = new ConcurrentHashMap();

    private ProfileInformationCache() {
    }

    public static final JSONObject getProfileInformation(String str) {
        Intrinsics.checkNotNullParameter(str, "accessToken");
        return (JSONObject) infoCache.get(str);
    }

    public static final void putProfileInformation(String str, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(jSONObject, "value");
        infoCache.put(str, jSONObject);
    }
}
