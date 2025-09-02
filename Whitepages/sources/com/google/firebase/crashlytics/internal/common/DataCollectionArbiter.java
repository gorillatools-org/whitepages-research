package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsTasks;

public class DataCollectionArbiter {
    private static final String FIREBASE_CRASHLYTICS_COLLECTION_ENABLED = "firebase_crashlytics_collection_enabled";
    private Boolean crashlyticsDataCollectionEnabled;
    TaskCompletionSource<Void> dataCollectionEnabledTask = new TaskCompletionSource<>();
    private final TaskCompletionSource<Void> dataCollectionExplicitlyApproved = new TaskCompletionSource<>();
    private final FirebaseApp firebaseApp;
    private boolean setInManifest = false;
    private final SharedPreferences sharedPreferences;
    private final Object taskLock;
    boolean taskResolved = false;

    public DataCollectionArbiter(FirebaseApp firebaseApp2) {
        Object obj = new Object();
        this.taskLock = obj;
        Context applicationContext = firebaseApp2.getApplicationContext();
        this.firebaseApp = firebaseApp2;
        this.sharedPreferences = CommonUtils.getSharedPrefs(applicationContext);
        Boolean dataCollectionValueFromSharedPreferences = getDataCollectionValueFromSharedPreferences();
        this.crashlyticsDataCollectionEnabled = dataCollectionValueFromSharedPreferences == null ? getDataCollectionValueFromManifest(applicationContext) : dataCollectionValueFromSharedPreferences;
        synchronized (obj) {
            try {
                if (isAutomaticDataCollectionEnabled()) {
                    this.dataCollectionEnabledTask.trySetResult(null);
                    this.taskResolved = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized boolean isAutomaticDataCollectionEnabled() {
        boolean z;
        try {
            Boolean bool = this.crashlyticsDataCollectionEnabled;
            if (bool != null) {
                z = bool.booleanValue();
            } else {
                z = isFirebaseDataCollectionDefaultEnabled();
            }
            logDataCollectionState(z);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return z;
    }

    private boolean isFirebaseDataCollectionDefaultEnabled() {
        try {
            return this.firebaseApp.isDataCollectionDefaultEnabled();
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    public synchronized void setCrashlyticsDataCollectionEnabled(Boolean bool) {
        Boolean bool2;
        if (bool != null) {
            try {
                this.setInManifest = false;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (bool != null) {
            bool2 = bool;
        } else {
            bool2 = getDataCollectionValueFromManifest(this.firebaseApp.getApplicationContext());
        }
        this.crashlyticsDataCollectionEnabled = bool2;
        storeDataCollectionValueInSharedPreferences(this.sharedPreferences, bool);
        synchronized (this.taskLock) {
            if (isAutomaticDataCollectionEnabled()) {
                if (!this.taskResolved) {
                    this.dataCollectionEnabledTask.trySetResult(null);
                    this.taskResolved = true;
                }
            } else if (this.taskResolved) {
                this.dataCollectionEnabledTask = new TaskCompletionSource<>();
                this.taskResolved = false;
            }
        }
    }

    public Task<Void> waitForAutomaticDataCollectionEnabled() {
        Task<Void> task;
        synchronized (this.taskLock) {
            task = this.dataCollectionEnabledTask.getTask();
        }
        return task;
    }

    public Task<Void> waitForDataCollectionPermission() {
        return CrashlyticsTasks.race(this.dataCollectionExplicitlyApproved.getTask(), waitForAutomaticDataCollectionEnabled());
    }

    public void grantDataCollectionPermission(boolean z) {
        if (z) {
            this.dataCollectionExplicitlyApproved.trySetResult(null);
            return;
        }
        throw new IllegalStateException("An invalid data collection token was used.");
    }

    private void logDataCollectionState(boolean z) {
        String str;
        String str2 = z ? "ENABLED" : "DISABLED";
        if (this.crashlyticsDataCollectionEnabled == null) {
            str = "global Firebase setting";
        } else {
            str = this.setInManifest ? "firebase_crashlytics_collection_enabled manifest flag" : "API";
        }
        Logger.getLogger().d(String.format("Crashlytics automatic data collection %s by %s.", new Object[]{str2, str}));
    }

    private Boolean getDataCollectionValueFromSharedPreferences() {
        if (!this.sharedPreferences.contains(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
            return null;
        }
        this.setInManifest = false;
        return Boolean.valueOf(this.sharedPreferences.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, true));
    }

    private Boolean getDataCollectionValueFromManifest(Context context) {
        Boolean readCrashlyticsDataCollectionEnabledFromManifest = readCrashlyticsDataCollectionEnabledFromManifest(context);
        if (readCrashlyticsDataCollectionEnabledFromManifest == null) {
            this.setInManifest = false;
            return null;
        }
        this.setInManifest = true;
        return Boolean.valueOf(Boolean.TRUE.equals(readCrashlyticsDataCollectionEnabledFromManifest));
    }

    private static Boolean readCrashlyticsDataCollectionEnabledFromManifest(Context context) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
                return null;
            }
            return Boolean.valueOf(applicationInfo.metaData.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED));
        } catch (PackageManager.NameNotFoundException e) {
            Logger.getLogger().e("Could not read data collection permission from manifest", e);
            return null;
        }
    }

    @SuppressLint({"ApplySharedPref"})
    private static void storeDataCollectionValueInSharedPreferences(SharedPreferences sharedPreferences2, Boolean bool) {
        SharedPreferences.Editor edit = sharedPreferences2.edit();
        if (bool != null) {
            edit.putBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, bool.booleanValue());
        } else {
            edit.remove(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED);
        }
        edit.apply();
    }
}
