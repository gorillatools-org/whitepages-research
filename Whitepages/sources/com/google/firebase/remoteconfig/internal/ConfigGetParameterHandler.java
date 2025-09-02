package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import com.google.android.gms.common.util.BiConsumer;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import org.json.JSONException;

public class ConfigGetParameterHandler {
    static final Pattern FALSE_REGEX = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
    public static final Charset FRC_BYTE_ARRAY_ENCODING = Charset.forName("UTF-8");
    static final Pattern TRUE_REGEX = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
    private final ConfigCacheClient activatedConfigsCache;
    private final ConfigCacheClient defaultConfigsCache;
    private final Executor executor;
    private final Set<BiConsumer<String, ConfigContainer>> listeners = new HashSet();

    public ConfigGetParameterHandler(Executor executor2, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
        this.executor = executor2;
        this.activatedConfigsCache = configCacheClient;
        this.defaultConfigsCache = configCacheClient2;
    }

    public String getString(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            callListeners(str, getConfigsFromCache(this.activatedConfigsCache));
            return stringFromCache;
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            return stringFromCache2;
        }
        logParameterValueDoesNotExist(str, "String");
        return "";
    }

    public boolean getBoolean(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            if (TRUE_REGEX.matcher(stringFromCache).matches()) {
                callListeners(str, getConfigsFromCache(this.activatedConfigsCache));
                return true;
            } else if (FALSE_REGEX.matcher(stringFromCache).matches()) {
                callListeners(str, getConfigsFromCache(this.activatedConfigsCache));
                return false;
            }
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            if (TRUE_REGEX.matcher(stringFromCache2).matches()) {
                return true;
            }
            if (FALSE_REGEX.matcher(stringFromCache2).matches()) {
                return false;
            }
        }
        logParameterValueDoesNotExist(str, "Boolean");
        return false;
    }

    public byte[] getByteArray(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            callListeners(str, getConfigsFromCache(this.activatedConfigsCache));
            return stringFromCache.getBytes(FRC_BYTE_ARRAY_ENCODING);
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            return stringFromCache2.getBytes(FRC_BYTE_ARRAY_ENCODING);
        }
        logParameterValueDoesNotExist(str, "ByteArray");
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY;
    }

    public double getDouble(String str) {
        Double doubleFromCache = getDoubleFromCache(this.activatedConfigsCache, str);
        if (doubleFromCache != null) {
            callListeners(str, getConfigsFromCache(this.activatedConfigsCache));
            return doubleFromCache.doubleValue();
        }
        Double doubleFromCache2 = getDoubleFromCache(this.defaultConfigsCache, str);
        if (doubleFromCache2 != null) {
            return doubleFromCache2.doubleValue();
        }
        logParameterValueDoesNotExist(str, "Double");
        return 0.0d;
    }

    public long getLong(String str) {
        Long longFromCache = getLongFromCache(this.activatedConfigsCache, str);
        if (longFromCache != null) {
            callListeners(str, getConfigsFromCache(this.activatedConfigsCache));
            return longFromCache.longValue();
        }
        Long longFromCache2 = getLongFromCache(this.defaultConfigsCache, str);
        if (longFromCache2 != null) {
            return longFromCache2.longValue();
        }
        logParameterValueDoesNotExist(str, "Long");
        return 0;
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            callListeners(str, getConfigsFromCache(this.activatedConfigsCache));
            return new FirebaseRemoteConfigValueImpl(stringFromCache, 2);
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            return new FirebaseRemoteConfigValueImpl(stringFromCache2, 1);
        }
        logParameterValueDoesNotExist(str, "FirebaseRemoteConfigValue");
        return new FirebaseRemoteConfigValueImpl("", 0);
    }

    public Set<String> getKeysByPrefix(String str) {
        if (str == null) {
            str = "";
        }
        TreeSet treeSet = new TreeSet();
        ConfigContainer configsFromCache = getConfigsFromCache(this.activatedConfigsCache);
        if (configsFromCache != null) {
            treeSet.addAll(getKeysByPrefix(str, configsFromCache));
        }
        ConfigContainer configsFromCache2 = getConfigsFromCache(this.defaultConfigsCache);
        if (configsFromCache2 != null) {
            treeSet.addAll(getKeysByPrefix(str, configsFromCache2));
        }
        return treeSet;
    }

    private static TreeSet<String> getKeysByPrefix(String str, ConfigContainer configContainer) {
        TreeSet<String> treeSet = new TreeSet<>();
        Iterator<String> keys = configContainer.getConfigs().keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (next.startsWith(str)) {
                treeSet.add(next);
            }
        }
        return treeSet;
    }

    public Map<String, FirebaseRemoteConfigValue> getAll() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(getKeySetFromCache(this.activatedConfigsCache));
        hashSet.addAll(getKeySetFromCache(this.defaultConfigsCache));
        HashMap hashMap = new HashMap();
        for (String str : hashSet) {
            hashMap.put(str, getValue(str));
        }
        return hashMap;
    }

    public void addListener(BiConsumer<String, ConfigContainer> biConsumer) {
        synchronized (this.listeners) {
            this.listeners.add(biConsumer);
        }
    }

    private void callListeners(String str, ConfigContainer configContainer) {
        if (configContainer != null) {
            synchronized (this.listeners) {
                try {
                    for (BiConsumer<String, ConfigContainer> configGetParameterHandler$$ExternalSyntheticLambda0 : this.listeners) {
                        this.executor.execute(new ConfigGetParameterHandler$$ExternalSyntheticLambda0(configGetParameterHandler$$ExternalSyntheticLambda0, str, configContainer));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private static String getStringFromCache(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer configsFromCache = getConfigsFromCache(configCacheClient);
        if (configsFromCache == null) {
            return null;
        }
        try {
            return configsFromCache.getConfigs().getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    private static Double getDoubleFromCache(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer configsFromCache = getConfigsFromCache(configCacheClient);
        if (configsFromCache == null) {
            return null;
        }
        try {
            return Double.valueOf(configsFromCache.getConfigs().getDouble(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    private static Long getLongFromCache(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer configsFromCache = getConfigsFromCache(configCacheClient);
        if (configsFromCache == null) {
            return null;
        }
        try {
            return Long.valueOf(configsFromCache.getConfigs().getLong(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    private static Set<String> getKeySetFromCache(ConfigCacheClient configCacheClient) {
        HashSet hashSet = new HashSet();
        ConfigContainer configsFromCache = getConfigsFromCache(configCacheClient);
        if (configsFromCache == null) {
            return hashSet;
        }
        Iterator<String> keys = configsFromCache.getConfigs().keys();
        while (keys.hasNext()) {
            hashSet.add(keys.next());
        }
        return hashSet;
    }

    private static ConfigContainer getConfigsFromCache(ConfigCacheClient configCacheClient) {
        return configCacheClient.getBlocking();
    }

    private static void logParameterValueDoesNotExist(String str, String str2) {
        Log.w(FirebaseRemoteConfig.TAG, String.format("No value of type '%s' exists for parameter key '%s'.", new Object[]{str2, str}));
    }
}
