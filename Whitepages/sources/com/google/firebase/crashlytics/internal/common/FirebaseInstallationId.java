package com.google.firebase.crashlytics.internal.common;

import kotlin.jvm.internal.Intrinsics;

public final class FirebaseInstallationId {
    private final String authToken;
    private final String fid;

    public static /* synthetic */ FirebaseInstallationId copy$default(FirebaseInstallationId firebaseInstallationId, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = firebaseInstallationId.fid;
        }
        if ((i & 2) != 0) {
            str2 = firebaseInstallationId.authToken;
        }
        return firebaseInstallationId.copy(str, str2);
    }

    public final String component1() {
        return this.fid;
    }

    public final String component2() {
        return this.authToken;
    }

    public final FirebaseInstallationId copy(String str, String str2) {
        return new FirebaseInstallationId(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FirebaseInstallationId)) {
            return false;
        }
        FirebaseInstallationId firebaseInstallationId = (FirebaseInstallationId) obj;
        return Intrinsics.areEqual((Object) this.fid, (Object) firebaseInstallationId.fid) && Intrinsics.areEqual((Object) this.authToken, (Object) firebaseInstallationId.authToken);
    }

    public int hashCode() {
        String str = this.fid;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.authToken;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "FirebaseInstallationId(fid=" + this.fid + ", authToken=" + this.authToken + ')';
    }

    public FirebaseInstallationId(String str, String str2) {
        this.fid = str;
        this.authToken = str2;
    }

    public final String getAuthToken() {
        return this.authToken;
    }

    public final String getFid() {
        return this.fid;
    }
}
