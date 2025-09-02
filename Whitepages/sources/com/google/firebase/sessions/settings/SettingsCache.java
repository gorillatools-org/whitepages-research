package com.google.firebase.sessions.settings;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

public final class SettingsCache {
    /* access modifiers changed from: private */
    public static final Preferences.Key CACHE_DURATION_SECONDS = PreferencesKeys.intKey("firebase_sessions_cache_duration");
    /* access modifiers changed from: private */
    public static final Preferences.Key CACHE_UPDATED_TIME = PreferencesKeys.longKey("firebase_sessions_cache_updated_time");
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Preferences.Key RESTART_TIMEOUT_SECONDS = PreferencesKeys.intKey("firebase_sessions_restart_timeout");
    /* access modifiers changed from: private */
    public static final Preferences.Key SAMPLING_RATE = PreferencesKeys.doubleKey(LocalOverrideSettings.SAMPLING_RATE);
    /* access modifiers changed from: private */
    public static final Preferences.Key SESSIONS_ENABLED = PreferencesKeys.booleanKey(LocalOverrideSettings.SESSIONS_ENABLED);
    @Deprecated
    public static final String TAG = "SettingsCache";
    /* access modifiers changed from: private */
    public final DataStore dataStore;
    private SessionConfigs sessionConfigs;

    public SettingsCache(DataStore dataStore2) {
        Intrinsics.checkNotNullParameter(dataStore2, "dataStore");
        this.dataStore = dataStore2;
        Object unused = BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new AnonymousClass1(this, (Continuation) null), 1, (Object) null);
    }

    @DebugMetadata(c = "com.google.firebase.sessions.settings.SettingsCache$1", f = "SettingsCache.kt", l = {46}, m = "invokeSuspend")
    /* renamed from: com.google.firebase.sessions.settings.SettingsCache$1  reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        Object L$0;
        int label;
        final /* synthetic */ SettingsCache this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            SettingsCache settingsCache;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SettingsCache settingsCache2 = this.this$0;
                Flow data = settingsCache2.dataStore.getData();
                this.L$0 = settingsCache2;
                this.label = 1;
                Object first = FlowKt.first(data, this);
                if (first == coroutine_suspended) {
                    return coroutine_suspended;
                }
                settingsCache = settingsCache2;
                obj = first;
            } else if (i == 1) {
                settingsCache = (SettingsCache) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            settingsCache.updateSessionConfigs(((Preferences) obj).toPreferences());
            return Unit.INSTANCE;
        }
    }

    /* access modifiers changed from: private */
    public final void updateSessionConfigs(Preferences preferences) {
        this.sessionConfigs = new SessionConfigs((Boolean) preferences.get(SESSIONS_ENABLED), (Double) preferences.get(SAMPLING_RATE), (Integer) preferences.get(RESTART_TIMEOUT_SECONDS), (Integer) preferences.get(CACHE_DURATION_SECONDS), (Long) preferences.get(CACHE_UPDATED_TIME));
    }

    public final boolean hasCacheExpired$com_google_firebase_firebase_sessions() {
        SessionConfigs sessionConfigs2 = this.sessionConfigs;
        SessionConfigs sessionConfigs3 = null;
        if (sessionConfigs2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionConfigs");
            sessionConfigs2 = null;
        }
        Long cacheUpdatedTime = sessionConfigs2.getCacheUpdatedTime();
        SessionConfigs sessionConfigs4 = this.sessionConfigs;
        if (sessionConfigs4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionConfigs");
        } else {
            sessionConfigs3 = sessionConfigs4;
        }
        Integer cacheDuration = sessionConfigs3.getCacheDuration();
        return cacheUpdatedTime == null || cacheDuration == null || (System.currentTimeMillis() - cacheUpdatedTime.longValue()) / ((long) 1000) >= ((long) cacheDuration.intValue());
    }

    public final Boolean sessionsEnabled() {
        SessionConfigs sessionConfigs2 = this.sessionConfigs;
        if (sessionConfigs2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionConfigs");
            sessionConfigs2 = null;
        }
        return sessionConfigs2.getSessionEnabled();
    }

    public final Double sessionSamplingRate() {
        SessionConfigs sessionConfigs2 = this.sessionConfigs;
        if (sessionConfigs2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionConfigs");
            sessionConfigs2 = null;
        }
        return sessionConfigs2.getSessionSamplingRate();
    }

    public final Integer sessionRestartTimeout() {
        SessionConfigs sessionConfigs2 = this.sessionConfigs;
        if (sessionConfigs2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionConfigs");
            sessionConfigs2 = null;
        }
        return sessionConfigs2.getSessionRestartTimeout();
    }

    public final Object updateSettingsEnabled(Boolean bool, Continuation continuation) {
        Object updateConfigValue = updateConfigValue(SESSIONS_ENABLED, bool, continuation);
        return updateConfigValue == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateConfigValue : Unit.INSTANCE;
    }

    public final Object updateSamplingRate(Double d, Continuation continuation) {
        Object updateConfigValue = updateConfigValue(SAMPLING_RATE, d, continuation);
        return updateConfigValue == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateConfigValue : Unit.INSTANCE;
    }

    public final Object updateSessionRestartTimeout(Integer num, Continuation continuation) {
        Object updateConfigValue = updateConfigValue(RESTART_TIMEOUT_SECONDS, num, continuation);
        return updateConfigValue == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateConfigValue : Unit.INSTANCE;
    }

    public final Object updateSessionCacheDuration(Integer num, Continuation continuation) {
        Object updateConfigValue = updateConfigValue(CACHE_DURATION_SECONDS, num, continuation);
        return updateConfigValue == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateConfigValue : Unit.INSTANCE;
    }

    public final Object updateSessionCacheUpdatedTime(Long l, Continuation continuation) {
        Object updateConfigValue = updateConfigValue(CACHE_UPDATED_TIME, l, continuation);
        return updateConfigValue == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateConfigValue : Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object removeConfigs$com_google_firebase_firebase_sessions(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.google.firebase.sessions.settings.SettingsCache$removeConfigs$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.google.firebase.sessions.settings.SettingsCache$removeConfigs$1 r0 = (com.google.firebase.sessions.settings.SettingsCache$removeConfigs$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.firebase.sessions.settings.SettingsCache$removeConfigs$1 r0 = new com.google.firebase.sessions.settings.SettingsCache$removeConfigs$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ IOException -> 0x0029 }
            goto L_0x005d
        L_0x0029:
            r6 = move-exception
            goto L_0x0047
        L_0x002b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.datastore.core.DataStore r6 = r5.dataStore     // Catch:{ IOException -> 0x0029 }
            com.google.firebase.sessions.settings.SettingsCache$removeConfigs$2 r2 = new com.google.firebase.sessions.settings.SettingsCache$removeConfigs$2     // Catch:{ IOException -> 0x0029 }
            r4 = 0
            r2.<init>(r5, r4)     // Catch:{ IOException -> 0x0029 }
            r0.label = r3     // Catch:{ IOException -> 0x0029 }
            java.lang.Object r6 = androidx.datastore.preferences.core.PreferencesKt.edit(r6, r2, r0)     // Catch:{ IOException -> 0x0029 }
            if (r6 != r1) goto L_0x005d
            return r1
        L_0x0047:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to remove config values: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r0 = "SettingsCache"
            android.util.Log.w(r0, r6)
        L_0x005d:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.settings.SettingsCache.removeConfigs$com_google_firebase_firebase_sessions(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> java.lang.Object updateConfigValue(androidx.datastore.preferences.core.Preferences.Key r6, T r7, kotlin.coroutines.Continuation r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.google.firebase.sessions.settings.SettingsCache$updateConfigValue$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.google.firebase.sessions.settings.SettingsCache$updateConfigValue$1 r0 = (com.google.firebase.sessions.settings.SettingsCache$updateConfigValue$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.firebase.sessions.settings.SettingsCache$updateConfigValue$1 r0 = new com.google.firebase.sessions.settings.SettingsCache$updateConfigValue$1
            r0.<init>(r5, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ IOException -> 0x0029 }
            goto L_0x005d
        L_0x0029:
            r6 = move-exception
            goto L_0x0047
        L_0x002b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.datastore.core.DataStore r8 = r5.dataStore     // Catch:{ IOException -> 0x0029 }
            com.google.firebase.sessions.settings.SettingsCache$updateConfigValue$2 r2 = new com.google.firebase.sessions.settings.SettingsCache$updateConfigValue$2     // Catch:{ IOException -> 0x0029 }
            r4 = 0
            r2.<init>(r7, r6, r5, r4)     // Catch:{ IOException -> 0x0029 }
            r0.label = r3     // Catch:{ IOException -> 0x0029 }
            java.lang.Object r6 = androidx.datastore.preferences.core.PreferencesKt.edit(r8, r2, r0)     // Catch:{ IOException -> 0x0029 }
            if (r6 != r1) goto L_0x005d
            return r1
        L_0x0047:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Failed to update cache config value: "
            r7.append(r8)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "SettingsCache"
            android.util.Log.w(r7, r6)
        L_0x005d:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.settings.SettingsCache.updateConfigValue(androidx.datastore.preferences.core.Preferences$Key, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Preferences.Key getSESSIONS_ENABLED() {
            return SettingsCache.SESSIONS_ENABLED;
        }

        public final Preferences.Key getSAMPLING_RATE() {
            return SettingsCache.SAMPLING_RATE;
        }

        public final Preferences.Key getRESTART_TIMEOUT_SECONDS() {
            return SettingsCache.RESTART_TIMEOUT_SECONDS;
        }

        public final Preferences.Key getCACHE_DURATION_SECONDS() {
            return SettingsCache.CACHE_DURATION_SECONDS;
        }

        public final Preferences.Key getCACHE_UPDATED_TIME() {
            return SettingsCache.CACHE_UPDATED_TIME;
        }
    }
}
