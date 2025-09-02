package com.google.firebase.crashlytics.internal.common;

import com.google.auto.value.AutoValue;

public interface InstallIdProvider {
    InstallIds getInstallIds();

    @AutoValue
    public static abstract class InstallIds {
        public abstract String getCrashlyticsInstallId();

        public abstract String getFirebaseAuthenticationToken();

        public abstract String getFirebaseInstallationId();

        public static InstallIds createWithoutFid(String str) {
            return new AutoValue_InstallIdProvider_InstallIds(str, (String) null, (String) null);
        }

        static InstallIds create(String str, FirebaseInstallationId firebaseInstallationId) {
            return new AutoValue_InstallIdProvider_InstallIds(str, firebaseInstallationId.getFid(), firebaseInstallationId.getAuthToken());
        }
    }
}
