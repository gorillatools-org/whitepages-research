package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConfigAutoFetch {
    private static final int MAXIMUM_FETCH_ATTEMPTS = 3;
    private static final String REALTIME_DISABLED_KEY = "featureDisabled";
    private static final String TEMPLATE_VERSION_KEY = "latestTemplateVersionNumber";
    private final ConfigCacheClient activatedCache;
    private final ConfigFetchHandler configFetchHandler;
    private final Set<ConfigUpdateListener> eventListeners;
    private final HttpURLConnection httpURLConnection;
    private final Random random = new Random();
    private final ConfigUpdateListener retryCallback;
    private final ScheduledExecutorService scheduledExecutorService;

    public ConfigAutoFetch(HttpURLConnection httpURLConnection2, ConfigFetchHandler configFetchHandler2, ConfigCacheClient configCacheClient, Set<ConfigUpdateListener> set, ConfigUpdateListener configUpdateListener, ScheduledExecutorService scheduledExecutorService2) {
        this.httpURLConnection = httpURLConnection2;
        this.configFetchHandler = configFetchHandler2;
        this.activatedCache = configCacheClient;
        this.eventListeners = set;
        this.retryCallback = configUpdateListener;
        this.scheduledExecutorService = scheduledExecutorService2;
    }

    private synchronized void propagateErrors(FirebaseRemoteConfigException firebaseRemoteConfigException) {
        for (ConfigUpdateListener onError : this.eventListeners) {
            onError.onError(firebaseRemoteConfigException);
        }
    }

    private synchronized void executeAllListenerCallbacks(ConfigUpdate configUpdate) {
        for (ConfigUpdateListener onUpdate : this.eventListeners) {
            onUpdate.onUpdate(configUpdate);
        }
    }

    private synchronized boolean isEventListenersEmpty() {
        return this.eventListeners.isEmpty();
    }

    private String parseAndValidateConfigUpdateMessage(String str) {
        int indexOf = str.indexOf(123);
        int lastIndexOf = str.lastIndexOf(125);
        if (indexOf < 0 || lastIndexOf < 0 || indexOf >= lastIndexOf) {
            return "";
        }
        return str.substring(indexOf, lastIndexOf + 1);
    }

    public void listenForNotifications() {
        HttpURLConnection httpURLConnection2 = this.httpURLConnection;
        if (httpURLConnection2 != null) {
            try {
                InputStream inputStream = httpURLConnection2.getInputStream();
                handleNotifications(inputStream);
                inputStream.close();
            } catch (IOException e) {
                Log.d(FirebaseRemoteConfig.TAG, "Stream was cancelled due to an exception. Retrying the connection...", e);
            } catch (Throwable th) {
                this.httpURLConnection.disconnect();
                throw th;
            }
            this.httpURLConnection.disconnect();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0044, code lost:
        if (r5.has(REALTIME_DISABLED_KEY) == false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004a, code lost:
        if (r5.getBoolean(REALTIME_DISABLED_KEY) == false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004c, code lost:
        r9.retryCallback.onError(new com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException("The server is temporarily unavailable. Try again in a few minutes.", com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_UNAVAILABLE));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0061, code lost:
        if (isEventListenersEmpty() == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0068, code lost:
        if (r5.has(TEMPLATE_VERSION_KEY) == false) goto L_0x0012;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006a, code lost:
        r6 = r9.configFetchHandler.getTemplateVersionNumber();
        r4 = r5.getLong(TEMPLATE_VERSION_KEY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0076, code lost:
        if (r4 <= r6) goto L_0x0012;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0078, code lost:
        autoFetch(3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r5 = new org.json.JSONObject(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleNotifications(java.io.InputStream r10) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = "latestTemplateVersionNumber"
            java.lang.String r1 = "featureDisabled"
            java.io.BufferedReader r2 = new java.io.BufferedReader
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            java.lang.String r4 = "utf-8"
            r3.<init>(r10, r4)
            r2.<init>(r3)
            java.lang.String r3 = ""
        L_0x0012:
            r4 = r3
        L_0x0013:
            java.lang.String r5 = r2.readLine()
            if (r5 == 0) goto L_0x0096
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r4)
            r6.append(r5)
            java.lang.String r4 = r6.toString()
            java.lang.String r6 = "}"
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x0013
            java.lang.String r4 = r9.parseAndValidateConfigUpdateMessage(r4)
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x003b
            goto L_0x0013
        L_0x003b:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x005b }
            r5.<init>(r4)     // Catch:{ JSONException -> 0x005b }
            boolean r4 = r5.has(r1)     // Catch:{ JSONException -> 0x005b }
            if (r4 == 0) goto L_0x005d
            boolean r4 = r5.getBoolean(r1)     // Catch:{ JSONException -> 0x005b }
            if (r4 == 0) goto L_0x005d
            com.google.firebase.remoteconfig.ConfigUpdateListener r4 = r9.retryCallback     // Catch:{ JSONException -> 0x005b }
            com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException r5 = new com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException     // Catch:{ JSONException -> 0x005b }
            java.lang.String r6 = "The server is temporarily unavailable. Try again in a few minutes."
            com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r7 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_UNAVAILABLE     // Catch:{ JSONException -> 0x005b }
            r5.<init>((java.lang.String) r6, (com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code) r7)     // Catch:{ JSONException -> 0x005b }
            r4.onError(r5)     // Catch:{ JSONException -> 0x005b }
            goto L_0x0096
        L_0x005b:
            r4 = move-exception
            goto L_0x007d
        L_0x005d:
            boolean r4 = r9.isEventListenersEmpty()     // Catch:{ JSONException -> 0x005b }
            if (r4 == 0) goto L_0x0064
            goto L_0x0096
        L_0x0064:
            boolean r4 = r5.has(r0)     // Catch:{ JSONException -> 0x005b }
            if (r4 == 0) goto L_0x0012
            com.google.firebase.remoteconfig.internal.ConfigFetchHandler r4 = r9.configFetchHandler     // Catch:{ JSONException -> 0x005b }
            long r6 = r4.getTemplateVersionNumber()     // Catch:{ JSONException -> 0x005b }
            long r4 = r5.getLong(r0)     // Catch:{ JSONException -> 0x005b }
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0012
            r6 = 3
            r9.autoFetch(r6, r4)     // Catch:{ JSONException -> 0x005b }
            goto L_0x0012
        L_0x007d:
            com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException r5 = new com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException
            java.lang.Throwable r6 = r4.getCause()
            com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r7 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_MESSAGE_INVALID
            java.lang.String r8 = "Unable to parse config update message."
            r5.<init>(r8, r6, r7)
            r9.propagateErrors(r5)
            java.lang.String r5 = "FirebaseRemoteConfig"
            java.lang.String r6 = "Unable to parse latest config update message."
            android.util.Log.e(r5, r6, r4)
            goto L_0x0012
        L_0x0096:
            r2.close()
            r10.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.ConfigAutoFetch.handleNotifications(java.io.InputStream):void");
    }

    private void autoFetch(final int i, final long j) {
        if (i == 0) {
            propagateErrors(new FirebaseRemoteConfigServerException("Unable to fetch the latest version of the template.", FirebaseRemoteConfigException.Code.CONFIG_UPDATE_NOT_FETCHED));
            return;
        }
        this.scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                ConfigAutoFetch.this.fetchLatestConfig(i, j);
            }
        }, (long) this.random.nextInt(4), TimeUnit.SECONDS);
    }

    public synchronized Task<Void> fetchLatestConfig(int i, long j) {
        int i2;
        Task<ConfigFetchHandler.FetchResponse> fetchNowWithTypeAndAttemptNumber;
        Task<ConfigContainer> task;
        i2 = i - 1;
        fetchNowWithTypeAndAttemptNumber = this.configFetchHandler.fetchNowWithTypeAndAttemptNumber(ConfigFetchHandler.FetchType.REALTIME, 3 - i2);
        task = this.activatedCache.get();
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{fetchNowWithTypeAndAttemptNumber, task}).continueWithTask(this.scheduledExecutorService, new ConfigAutoFetch$$ExternalSyntheticLambda0(this, fetchNowWithTypeAndAttemptNumber, task, j, i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$fetchLatestConfig$0(Task task, Task task2, long j, int i, Task task3) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Failed to auto-fetch config update.", (Throwable) task.getException()));
        }
        if (!task2.isSuccessful()) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Failed to get activated config for auto-fetch", (Throwable) task2.getException()));
        }
        ConfigFetchHandler.FetchResponse fetchResponse = (ConfigFetchHandler.FetchResponse) task.getResult();
        ConfigContainer configContainer = (ConfigContainer) task2.getResult();
        if (!fetchResponseIsUpToDate(fetchResponse, j).booleanValue()) {
            Log.d(FirebaseRemoteConfig.TAG, "Fetched template version is the same as SDK's current version. Retrying fetch.");
            autoFetch(i, j);
            return Tasks.forResult(null);
        } else if (fetchResponse.getFetchedConfigs() == null) {
            Log.d(FirebaseRemoteConfig.TAG, "The fetch succeeded, but the backend had no updates.");
            return Tasks.forResult(null);
        } else {
            if (configContainer == null) {
                configContainer = ConfigContainer.newBuilder().build();
            }
            Set<String> changedParams = configContainer.getChangedParams(fetchResponse.getFetchedConfigs());
            if (changedParams.isEmpty()) {
                Log.d(FirebaseRemoteConfig.TAG, "Config was fetched, but no params changed.");
                return Tasks.forResult(null);
            }
            executeAllListenerCallbacks(ConfigUpdate.create(changedParams));
            return Tasks.forResult(null);
        }
    }

    private static Boolean fetchResponseIsUpToDate(ConfigFetchHandler.FetchResponse fetchResponse, long j) {
        boolean z = false;
        if (fetchResponse.getFetchedConfigs() != null) {
            if (fetchResponse.getFetchedConfigs().getTemplateVersionNumber() >= j) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
        if (fetchResponse.getStatus() == 1) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
