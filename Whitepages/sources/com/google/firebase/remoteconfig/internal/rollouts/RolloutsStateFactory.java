package com.google.firebase.remoteconfig.internal.rollouts;

import android.util.Log;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RolloutsStateFactory {
    ConfigCacheClient activatedConfigsCache;
    ConfigCacheClient defaultConfigsCache;

    RolloutsStateFactory(ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
        this.activatedConfigsCache = configCacheClient;
        this.defaultConfigsCache = configCacheClient2;
    }

    /* access modifiers changed from: package-private */
    public RolloutsState getActiveRolloutsState(ConfigContainer configContainer) throws FirebaseRemoteConfigClientException {
        JSONArray rolloutMetadata = configContainer.getRolloutMetadata();
        long templateVersionNumber = configContainer.getTemplateVersionNumber();
        HashSet hashSet = new HashSet();
        int i = 0;
        while (i < rolloutMetadata.length()) {
            try {
                JSONObject jSONObject = rolloutMetadata.getJSONObject(i);
                String string = jSONObject.getString(ConfigContainer.ROLLOUT_METADATA_ID);
                JSONArray jSONArray = jSONObject.getJSONArray(ConfigContainer.ROLLOUT_METADATA_AFFECTED_KEYS);
                if (jSONArray.length() > 1) {
                    Log.w(FirebaseRemoteConfig.TAG, String.format("Rollout has multiple affected parameter keys.Only the first key will be included in RolloutsState. rolloutId: %s, affectedParameterKeys: %s", new Object[]{string, jSONArray}));
                }
                String optString = jSONArray.optString(0, "");
                hashSet.add(RolloutAssignment.builder().setRolloutId(string).setVariantId(jSONObject.getString("variantId")).setParameterKey(optString).setParameterValue(getParameterValue(optString)).setTemplateVersion(templateVersionNumber).build());
                i++;
            } catch (JSONException e) {
                throw new FirebaseRemoteConfigClientException("Exception parsing rollouts metadata to create RolloutsState.", (Throwable) e);
            }
        }
        return RolloutsState.create(hashSet);
    }

    private String getParameterValue(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            return stringFromCache;
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            return stringFromCache2;
        }
        return "";
    }

    private static String getStringFromCache(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer blocking = configCacheClient.getBlocking();
        if (blocking == null) {
            return null;
        }
        try {
            return blocking.getConfigs().getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static RolloutsStateFactory create(ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
        return new RolloutsStateFactory(configCacheClient, configCacheClient2);
    }
}
