package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.ConfigUpdateListenerRegistration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

public class ConfigRealtimeHandler {
    private final ConfigCacheClient activatedCacheClient;
    private final ConfigFetchHandler configFetchHandler;
    private final ConfigRealtimeHttpClient configRealtimeHttpClient;
    private final Context context;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Set<ConfigUpdateListener> listeners;
    private final String namespace;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ConfigSharedPrefsClient sharedPrefsClient;

    public ConfigRealtimeHandler(FirebaseApp firebaseApp2, FirebaseInstallationsApi firebaseInstallationsApi, ConfigFetchHandler configFetchHandler2, ConfigCacheClient configCacheClient, Context context2, String str, ConfigSharedPrefsClient configSharedPrefsClient, ScheduledExecutorService scheduledExecutorService2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.listeners = linkedHashSet;
        this.configRealtimeHttpClient = new ConfigRealtimeHttpClient(firebaseApp2, firebaseInstallationsApi, configFetchHandler2, configCacheClient, context2, str, linkedHashSet, configSharedPrefsClient, scheduledExecutorService2);
        this.firebaseApp = firebaseApp2;
        this.configFetchHandler = configFetchHandler2;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.activatedCacheClient = configCacheClient;
        this.context = context2;
        this.namespace = str;
        this.sharedPrefsClient = configSharedPrefsClient;
        this.scheduledExecutorService = scheduledExecutorService2;
    }

    private synchronized void beginRealtime() {
        if (!this.listeners.isEmpty()) {
            this.configRealtimeHttpClient.startHttpConnection();
        }
    }

    public synchronized ConfigUpdateListenerRegistration addRealtimeConfigUpdateListener(ConfigUpdateListener configUpdateListener) {
        this.listeners.add(configUpdateListener);
        beginRealtime();
        return new ConfigUpdateListenerRegistrationInternal(configUpdateListener);
    }

    public synchronized void setBackgroundState(boolean z) {
        this.configRealtimeHttpClient.setRealtimeBackgroundState(z);
        if (!z) {
            beginRealtime();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void removeRealtimeConfigUpdateListener(ConfigUpdateListener configUpdateListener) {
        this.listeners.remove(configUpdateListener);
    }

    public class ConfigUpdateListenerRegistrationInternal implements ConfigUpdateListenerRegistration {
        private final ConfigUpdateListener listener;

        public ConfigUpdateListenerRegistrationInternal(ConfigUpdateListener configUpdateListener) {
            this.listener = configUpdateListener;
        }

        public void remove() {
            ConfigRealtimeHandler.this.removeRealtimeConfigUpdateListener(this.listener);
        }
    }
}
