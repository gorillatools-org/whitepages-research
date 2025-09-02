package com.google.firebase.analytics.ktx;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ConsentBuilder {
    private FirebaseAnalytics.ConsentStatus zza;
    private FirebaseAnalytics.ConsentStatus zzb;

    public final Map<FirebaseAnalytics.ConsentType, FirebaseAnalytics.ConsentStatus> asMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FirebaseAnalytics.ConsentStatus consentStatus = this.zza;
        if (consentStatus != null) {
            linkedHashMap.put(FirebaseAnalytics.ConsentType.AD_STORAGE, consentStatus);
        }
        FirebaseAnalytics.ConsentStatus consentStatus2 = this.zzb;
        if (consentStatus2 != null) {
            linkedHashMap.put(FirebaseAnalytics.ConsentType.ANALYTICS_STORAGE, consentStatus2);
        }
        return linkedHashMap;
    }

    public final FirebaseAnalytics.ConsentStatus getAdStorage() {
        return this.zza;
    }

    public final FirebaseAnalytics.ConsentStatus getAnalyticsStorage() {
        return this.zzb;
    }

    public final void setAdStorage(FirebaseAnalytics.ConsentStatus consentStatus) {
        this.zza = consentStatus;
    }

    public final void setAnalyticsStorage(FirebaseAnalytics.ConsentStatus consentStatus) {
        this.zzb = consentStatus;
    }
}
