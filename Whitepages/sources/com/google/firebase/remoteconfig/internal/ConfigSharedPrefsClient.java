package com.google.firebase.remoteconfig.internal;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigSharedPrefsClient {
    private static final String BACKOFF_END_TIME_IN_MILLIS_KEY = "backoff_end_time_in_millis";
    private static final int CUSTOM_SIGNALS_MAX_COUNT = 100;
    private static final int CUSTOM_SIGNALS_MAX_KEY_LENGTH = 250;
    private static final int CUSTOM_SIGNALS_MAX_STRING_VALUE_LENGTH = 500;
    private static final String FETCH_TIMEOUT_IN_SECONDS_KEY = "fetch_timeout_in_seconds";
    private static final String LAST_FETCH_ETAG_KEY = "last_fetch_etag";
    private static final String LAST_FETCH_STATUS_KEY = "last_fetch_status";
    public static final long LAST_FETCH_TIME_IN_MILLIS_NO_FETCH_YET = -1;
    static final Date LAST_FETCH_TIME_NO_FETCH_YET = new Date(-1);
    private static final String LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY = "last_fetch_time_in_millis";
    private static final String LAST_TEMPLATE_VERSION = "last_template_version";
    private static final String MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY = "minimum_fetch_interval_in_seconds";
    static final Date NO_BACKOFF_TIME = new Date(-1);
    private static final long NO_BACKOFF_TIME_IN_MILLIS = -1;
    static final int NO_FAILED_FETCHES = 0;
    static final int NO_FAILED_REALTIME_STREAMS = 0;
    private static final String NUM_FAILED_FETCHES_KEY = "num_failed_fetches";
    private static final String NUM_FAILED_REALTIME_STREAMS_KEY = "num_failed_realtime_streams";
    private static final String REALTIME_BACKOFF_END_TIME_IN_MILLIS_KEY = "realtime_backoff_end_time_in_millis";
    private final Object backoffMetadataLock = new Object();
    private final Object customSignalsLock = new Object();
    private final Object frcInfoLock = new Object();
    private final SharedPreferences frcSharedPrefs;
    private final Object realtimeBackoffMetadataLock = new Object();

    public ConfigSharedPrefsClient(SharedPreferences sharedPreferences) {
        this.frcSharedPrefs = sharedPreferences;
    }

    public long getFetchTimeoutInSeconds() {
        return this.frcSharedPrefs.getLong(FETCH_TIMEOUT_IN_SECONDS_KEY, 60);
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.frcSharedPrefs.getLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS);
    }

    /* access modifiers changed from: package-private */
    public int getLastFetchStatus() {
        return this.frcSharedPrefs.getInt(LAST_FETCH_STATUS_KEY, 0);
    }

    /* access modifiers changed from: package-private */
    public Date getLastSuccessfulFetchTime() {
        return new Date(this.frcSharedPrefs.getLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, -1));
    }

    /* access modifiers changed from: package-private */
    public String getLastFetchETag() {
        return this.frcSharedPrefs.getString(LAST_FETCH_ETAG_KEY, (String) null);
    }

    /* access modifiers changed from: package-private */
    public long getLastTemplateVersion() {
        return this.frcSharedPrefs.getLong(LAST_TEMPLATE_VERSION, 0);
    }

    public FirebaseRemoteConfigInfo getInfo() {
        FirebaseRemoteConfigInfoImpl build;
        synchronized (this.frcInfoLock) {
            long j = this.frcSharedPrefs.getLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, -1);
            int i = this.frcSharedPrefs.getInt(LAST_FETCH_STATUS_KEY, 0);
            build = FirebaseRemoteConfigInfoImpl.newBuilder().withLastFetchStatus(i).withLastSuccessfulFetchTimeInMillis(j).withConfigSettings(new FirebaseRemoteConfigSettings.Builder().setFetchTimeoutInSeconds(this.frcSharedPrefs.getLong(FETCH_TIMEOUT_IN_SECONDS_KEY, 60)).setMinimumFetchIntervalInSeconds(this.frcSharedPrefs.getLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS)).build()).build();
        }
        return build;
    }

    public void clear() {
        synchronized (this.frcInfoLock) {
            this.frcSharedPrefs.edit().clear().commit();
        }
    }

    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.frcInfoLock) {
            this.frcSharedPrefs.edit().putLong(FETCH_TIMEOUT_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).commit();
        }
    }

    public void setConfigSettingsWithoutWaitingOnDiskWrite(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.frcInfoLock) {
            this.frcSharedPrefs.edit().putLong(FETCH_TIMEOUT_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLastFetchAsSuccessfulAt(Date date) {
        synchronized (this.frcInfoLock) {
            this.frcSharedPrefs.edit().putInt(LAST_FETCH_STATUS_KEY, -1).putLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, date.getTime()).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLastFetchAsFailed() {
        synchronized (this.frcInfoLock) {
            this.frcSharedPrefs.edit().putInt(LAST_FETCH_STATUS_KEY, 1).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLastFetchAsThrottled() {
        synchronized (this.frcInfoLock) {
            this.frcSharedPrefs.edit().putInt(LAST_FETCH_STATUS_KEY, 2).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public void setLastFetchETag(String str) {
        synchronized (this.frcInfoLock) {
            this.frcSharedPrefs.edit().putString(LAST_FETCH_ETAG_KEY, str).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public void setLastTemplateVersion(long j) {
        synchronized (this.frcInfoLock) {
            this.frcSharedPrefs.edit().putLong(LAST_TEMPLATE_VERSION, j).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public BackoffMetadata getBackoffMetadata() {
        BackoffMetadata backoffMetadata;
        synchronized (this.backoffMetadataLock) {
            backoffMetadata = new BackoffMetadata(this.frcSharedPrefs.getInt(NUM_FAILED_FETCHES_KEY, 0), new Date(this.frcSharedPrefs.getLong(BACKOFF_END_TIME_IN_MILLIS_KEY, -1)));
        }
        return backoffMetadata;
    }

    /* access modifiers changed from: package-private */
    public void setBackoffMetadata(int i, Date date) {
        synchronized (this.backoffMetadataLock) {
            this.frcSharedPrefs.edit().putInt(NUM_FAILED_FETCHES_KEY, i).putLong(BACKOFF_END_TIME_IN_MILLIS_KEY, date.getTime()).apply();
        }
    }

    public void setCustomSignals(Map<String, String> map) {
        synchronized (this.customSignalsLock) {
            try {
                Map<String, String> customSignals = getCustomSignals();
                boolean z = false;
                for (Map.Entry next : map.entrySet()) {
                    String str = (String) next.getKey();
                    String str2 = (String) next.getValue();
                    if (str.length() <= CUSTOM_SIGNALS_MAX_KEY_LENGTH) {
                        if (str2 == null || str2.length() <= 500) {
                            boolean z2 = true;
                            if (str2 != null) {
                                z |= !Objects.equals(customSignals.put(str, str2), str2);
                            } else {
                                if (customSignals.remove(str) == null) {
                                    z2 = false;
                                }
                                z |= z2;
                            }
                        }
                    }
                    Log.w(FirebaseRemoteConfig.TAG, String.format("Invalid custom signal: Custom signal keys must be %d characters or less, and values must be %d characters or less.", new Object[]{Integer.valueOf(CUSTOM_SIGNALS_MAX_KEY_LENGTH), 500}));
                    return;
                }
                if (z) {
                    if (customSignals.size() > 100) {
                        Log.w(FirebaseRemoteConfig.TAG, String.format("Invalid custom signal: Too many custom signals provided. The maximum allowed is %d.", new Object[]{100}));
                        return;
                    }
                    this.frcSharedPrefs.edit().putString(RemoteConfigConstants.RequestFieldKey.CUSTOM_SIGNALS, new JSONObject(customSignals).toString()).commit();
                    Log.d(FirebaseRemoteConfig.TAG, "Keys of updated custom signals: " + getCustomSignals().keySet());
                }
            } finally {
            }
        }
    }

    public Map<String, String> getCustomSignals() {
        try {
            JSONObject jSONObject = new JSONObject(this.frcSharedPrefs.getString(RemoteConfigConstants.RequestFieldKey.CUSTOM_SIGNALS, "{}"));
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.optString(next));
            }
            return hashMap;
        } catch (JSONException unused) {
            return new HashMap();
        }
    }

    /* access modifiers changed from: package-private */
    public void resetBackoff() {
        setBackoffMetadata(0, NO_BACKOFF_TIME);
    }

    static class BackoffMetadata {
        private Date backoffEndTime;
        private int numFailedFetches;

        BackoffMetadata(int i, Date date) {
            this.numFailedFetches = i;
            this.backoffEndTime = date;
        }

        /* access modifiers changed from: package-private */
        public int getNumFailedFetches() {
            return this.numFailedFetches;
        }

        /* access modifiers changed from: package-private */
        public Date getBackoffEndTime() {
            return this.backoffEndTime;
        }
    }

    public RealtimeBackoffMetadata getRealtimeBackoffMetadata() {
        RealtimeBackoffMetadata realtimeBackoffMetadata;
        synchronized (this.realtimeBackoffMetadataLock) {
            realtimeBackoffMetadata = new RealtimeBackoffMetadata(this.frcSharedPrefs.getInt(NUM_FAILED_REALTIME_STREAMS_KEY, 0), new Date(this.frcSharedPrefs.getLong(REALTIME_BACKOFF_END_TIME_IN_MILLIS_KEY, -1)));
        }
        return realtimeBackoffMetadata;
    }

    /* access modifiers changed from: package-private */
    public void setRealtimeBackoffMetadata(int i, Date date) {
        synchronized (this.realtimeBackoffMetadataLock) {
            this.frcSharedPrefs.edit().putInt(NUM_FAILED_REALTIME_STREAMS_KEY, i).putLong(REALTIME_BACKOFF_END_TIME_IN_MILLIS_KEY, date.getTime()).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public void resetRealtimeBackoff() {
        setRealtimeBackoffMetadata(0, NO_BACKOFF_TIME);
    }

    public static class RealtimeBackoffMetadata {
        private Date backoffEndTime;
        private int numFailedStreams;

        public RealtimeBackoffMetadata(int i, Date date) {
            this.numFailedStreams = i;
            this.backoffEndTime = date;
        }

        /* access modifiers changed from: package-private */
        public int getNumFailedStreams() {
            return this.numFailedStreams;
        }

        /* access modifiers changed from: package-private */
        public Date getBackoffEndTime() {
            return this.backoffEndTime;
        }
    }
}
