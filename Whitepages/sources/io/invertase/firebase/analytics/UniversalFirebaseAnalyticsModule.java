package io.invertase.firebase.analytics;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.invertase.firebase.common.UniversalFirebaseModule;
import java.util.EnumMap;
import java.util.Set;

public class UniversalFirebaseAnalyticsModule extends UniversalFirebaseModule {
    UniversalFirebaseAnalyticsModule(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: package-private */
    public Task<Void> logEvent(String str, Bundle bundle) {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda5(this, str, bundle));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$logEvent$0(String str, Bundle bundle) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).logEvent(str, bundle);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setAnalyticsCollectionEnabled(Boolean bool) {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda3(this, bool));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setAnalyticsCollectionEnabled$1(Boolean bool) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setAnalyticsCollectionEnabled(bool.booleanValue());
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setSessionTimeoutDuration(long j) {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda8(this, j));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setSessionTimeoutDuration$2(long j) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setSessionTimeoutDuration(j);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<String> getAppInstanceId() {
        return FirebaseAnalytics.getInstance(getContext()).getAppInstanceId();
    }

    /* access modifiers changed from: package-private */
    public Task<Long> getSessionId() {
        return FirebaseAnalytics.getInstance(getContext()).getSessionId();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setUserId(String str) {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda4(this, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setUserId$3(String str) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setUserId(str);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setUserProperty(String str, String str2) {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda6(this, str, str2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setUserProperty$4(String str, String str2) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setUserProperty(str, str2);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setUserProperties(Bundle bundle) {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda0(this, bundle));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setUserProperties$5(Bundle bundle) throws Exception {
        Set<String> keySet = bundle.keySet();
        FirebaseAnalytics instance = FirebaseAnalytics.getInstance(getContext());
        for (String next : keySet) {
            instance.setUserProperty(next, (String) bundle.get(next));
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> resetAnalyticsData() {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$resetAnalyticsData$6() throws Exception {
        FirebaseAnalytics.getInstance(getContext()).resetAnalyticsData();
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setDefaultEventParameters(Bundle bundle) {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda7(this, bundle));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setDefaultEventParameters$7(Bundle bundle) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setDefaultEventParameters(bundle);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setConsent(Bundle bundle) {
        return Tasks.call(new UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda2(this, bundle));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setConsent$8(Bundle bundle) throws Exception {
        boolean z = bundle.getBoolean("analytics_storage");
        boolean z2 = bundle.getBoolean("ad_storage");
        boolean z3 = bundle.getBoolean("ad_user_data");
        boolean z4 = bundle.getBoolean("ad_personalization");
        EnumMap enumMap = new EnumMap(FirebaseAnalytics.ConsentType.class);
        enumMap.put(FirebaseAnalytics.ConsentType.ANALYTICS_STORAGE, z ? FirebaseAnalytics.ConsentStatus.GRANTED : FirebaseAnalytics.ConsentStatus.DENIED);
        enumMap.put(FirebaseAnalytics.ConsentType.AD_STORAGE, z2 ? FirebaseAnalytics.ConsentStatus.GRANTED : FirebaseAnalytics.ConsentStatus.DENIED);
        enumMap.put(FirebaseAnalytics.ConsentType.AD_USER_DATA, z3 ? FirebaseAnalytics.ConsentStatus.GRANTED : FirebaseAnalytics.ConsentStatus.DENIED);
        enumMap.put(FirebaseAnalytics.ConsentType.AD_PERSONALIZATION, z4 ? FirebaseAnalytics.ConsentStatus.GRANTED : FirebaseAnalytics.ConsentStatus.DENIED);
        FirebaseAnalytics.getInstance(getContext()).setConsent(enumMap);
        return null;
    }
}
