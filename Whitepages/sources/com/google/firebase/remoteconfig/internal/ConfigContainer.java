package com.google.firebase.remoteconfig.internal;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigContainer {
    static final String ABT_EXPERIMENTS_KEY = "abt_experiments_key";
    static final String CONFIGS_KEY = "configs_key";
    /* access modifiers changed from: private */
    public static final Date DEFAULTS_FETCH_TIME = new Date(0);
    static final String FETCH_TIME_KEY = "fetch_time_key";
    static final String PERSONALIZATION_METADATA_KEY = "personalization_metadata_key";
    public static final String ROLLOUT_METADATA_AFFECTED_KEYS = "affectedParameterKeys";
    public static final String ROLLOUT_METADATA_ID = "rolloutId";
    static final String ROLLOUT_METADATA_KEY = "rollout_metadata_key";
    public static final String ROLLOUT_METADATA_VARIANT_ID = "variantId";
    static final String TEMPLATE_VERSION_NUMBER_KEY = "template_version_number_key";
    private JSONArray abtExperiments;
    private JSONObject configsJson;
    private JSONObject containerJson;
    private Date fetchTime;
    private JSONObject personalizationMetadata;
    private JSONArray rolloutMetadata;
    private long templateVersionNumber;

    private ConfigContainer(JSONObject jSONObject, Date date, JSONArray jSONArray, JSONObject jSONObject2, long j, JSONArray jSONArray2) throws JSONException {
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(CONFIGS_KEY, jSONObject);
        jSONObject3.put(FETCH_TIME_KEY, date.getTime());
        jSONObject3.put(ABT_EXPERIMENTS_KEY, jSONArray);
        jSONObject3.put(PERSONALIZATION_METADATA_KEY, jSONObject2);
        jSONObject3.put(TEMPLATE_VERSION_NUMBER_KEY, j);
        jSONObject3.put(ROLLOUT_METADATA_KEY, jSONArray2);
        this.configsJson = jSONObject;
        this.fetchTime = date;
        this.abtExperiments = jSONArray;
        this.personalizationMetadata = jSONObject2;
        this.templateVersionNumber = j;
        this.rolloutMetadata = jSONArray2;
        this.containerJson = jSONObject3;
    }

    static ConfigContainer copyOf(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(PERSONALIZATION_METADATA_KEY);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        JSONObject jSONObject2 = optJSONObject;
        JSONArray optJSONArray = jSONObject.optJSONArray(ROLLOUT_METADATA_KEY);
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        return new ConfigContainer(jSONObject.getJSONObject(CONFIGS_KEY), new Date(jSONObject.getLong(FETCH_TIME_KEY)), jSONObject.getJSONArray(ABT_EXPERIMENTS_KEY), jSONObject2, jSONObject.optLong(TEMPLATE_VERSION_NUMBER_KEY), optJSONArray);
    }

    private static ConfigContainer deepCopyOf(JSONObject jSONObject) throws JSONException {
        return copyOf(new JSONObject(jSONObject.toString()));
    }

    public JSONObject getConfigs() {
        return this.configsJson;
    }

    public Date getFetchTime() {
        return this.fetchTime;
    }

    public JSONArray getAbtExperiments() {
        return this.abtExperiments;
    }

    public JSONObject getPersonalizationMetadata() {
        return this.personalizationMetadata;
    }

    public long getTemplateVersionNumber() {
        return this.templateVersionNumber;
    }

    public JSONArray getRolloutMetadata() {
        return this.rolloutMetadata;
    }

    public String toString() {
        return this.containerJson.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConfigContainer)) {
            return false;
        }
        return this.containerJson.toString().equals(((ConfigContainer) obj).toString());
    }

    private Map<String, Map<String, String>> createRolloutParameterKeyMap() throws JSONException {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < getRolloutMetadata().length(); i++) {
            JSONObject jSONObject = getRolloutMetadata().getJSONObject(i);
            String string = jSONObject.getString(ROLLOUT_METADATA_ID);
            String string2 = jSONObject.getString("variantId");
            JSONArray jSONArray = jSONObject.getJSONArray(ROLLOUT_METADATA_AFFECTED_KEYS);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                String string3 = jSONArray.getString(i2);
                if (!hashMap.containsKey(string3)) {
                    hashMap.put(string3, new HashMap());
                }
                Map map = (Map) hashMap.get(string3);
                if (map != null) {
                    map.put(string, string2);
                }
            }
        }
        return hashMap;
    }

    public Set<String> getChangedParams(ConfigContainer configContainer) throws JSONException {
        JSONObject configs = deepCopyOf(configContainer.containerJson).getConfigs();
        Map<String, Map<String, String>> createRolloutParameterKeyMap = createRolloutParameterKeyMap();
        Map<String, Map<String, String>> createRolloutParameterKeyMap2 = configContainer.createRolloutParameterKeyMap();
        HashSet hashSet = new HashSet();
        Iterator<String> keys = getConfigs().keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!configContainer.getConfigs().has(next)) {
                hashSet.add(next);
            } else if (!getConfigs().get(next).equals(configContainer.getConfigs().get(next))) {
                hashSet.add(next);
            } else if ((getPersonalizationMetadata().has(next) && !configContainer.getPersonalizationMetadata().has(next)) || (!getPersonalizationMetadata().has(next) && configContainer.getPersonalizationMetadata().has(next))) {
                hashSet.add(next);
            } else if (getPersonalizationMetadata().has(next) && configContainer.getPersonalizationMetadata().has(next) && !getPersonalizationMetadata().getJSONObject(next).toString().equals(configContainer.getPersonalizationMetadata().getJSONObject(next).toString())) {
                hashSet.add(next);
            } else if (createRolloutParameterKeyMap.containsKey(next) != createRolloutParameterKeyMap2.containsKey(next)) {
                hashSet.add(next);
            } else if (!createRolloutParameterKeyMap.containsKey(next) || !createRolloutParameterKeyMap2.containsKey(next) || createRolloutParameterKeyMap.get(next).equals(createRolloutParameterKeyMap2.get(next))) {
                configs.remove(next);
            } else {
                hashSet.add(next);
            }
        }
        Iterator<String> keys2 = configs.keys();
        while (keys2.hasNext()) {
            hashSet.add(keys2.next());
        }
        return hashSet;
    }

    public int hashCode() {
        return this.containerJson.hashCode();
    }

    public static class Builder {
        private JSONArray builderAbtExperiments;
        private JSONObject builderConfigsJson;
        private Date builderFetchTime;
        private JSONObject builderPersonalizationMetadata;
        private JSONArray builderRolloutMetadata;
        private long builderTemplateVersionNumber;

        private Builder() {
            this.builderConfigsJson = new JSONObject();
            this.builderFetchTime = ConfigContainer.DEFAULTS_FETCH_TIME;
            this.builderAbtExperiments = new JSONArray();
            this.builderPersonalizationMetadata = new JSONObject();
            this.builderTemplateVersionNumber = 0;
            this.builderRolloutMetadata = new JSONArray();
        }

        public Builder(ConfigContainer configContainer) {
            this.builderConfigsJson = configContainer.getConfigs();
            this.builderFetchTime = configContainer.getFetchTime();
            this.builderAbtExperiments = configContainer.getAbtExperiments();
            this.builderPersonalizationMetadata = configContainer.getPersonalizationMetadata();
            this.builderTemplateVersionNumber = configContainer.getTemplateVersionNumber();
            this.builderRolloutMetadata = configContainer.getRolloutMetadata();
        }

        public Builder replaceConfigsWith(Map<String, String> map) {
            this.builderConfigsJson = new JSONObject(map);
            return this;
        }

        public Builder replaceConfigsWith(JSONObject jSONObject) {
            try {
                this.builderConfigsJson = new JSONObject(jSONObject.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder withFetchTime(Date date) {
            this.builderFetchTime = date;
            return this;
        }

        public Builder withAbtExperiments(JSONArray jSONArray) {
            try {
                this.builderAbtExperiments = new JSONArray(jSONArray.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder withPersonalizationMetadata(JSONObject jSONObject) {
            try {
                this.builderPersonalizationMetadata = new JSONObject(jSONObject.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder withTemplateVersionNumber(long j) {
            this.builderTemplateVersionNumber = j;
            return this;
        }

        public Builder withRolloutMetadata(JSONArray jSONArray) {
            try {
                this.builderRolloutMetadata = new JSONArray(jSONArray.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public ConfigContainer build() throws JSONException {
            return new ConfigContainer(this.builderConfigsJson, this.builderFetchTime, this.builderAbtExperiments, this.builderPersonalizationMetadata, this.builderTemplateVersionNumber, this.builderRolloutMetadata);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ConfigContainer configContainer) {
        return new Builder(configContainer);
    }
}
