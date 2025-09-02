package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzio;
import com.google.android.gms.measurement.internal.zzjt;
import com.google.android.gms.measurement.internal.zzkb;
import com.google.android.gms.measurement.internal.zzkc;
import com.google.android.gms.measurement.internal.zzlx;
import com.google.android.gms.measurement.internal.zzmg;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ShowFirstParty
@KeepForSdk
@Deprecated
public class AppMeasurement {
    @ShowFirstParty
    @KeepForSdk
    public static final String CRASH_ORIGIN = "crash";
    @ShowFirstParty
    @KeepForSdk
    public static final String FCM_ORIGIN = "fcm";
    @ShowFirstParty
    @KeepForSdk
    public static final String FIAM_ORIGIN = "fiam";
    private static volatile AppMeasurement zza;
    private final zzc zzb;

    @ShowFirstParty
    @KeepForSdk
    public interface EventInterceptor extends zzkb {
        @ShowFirstParty
        @KeepForSdk
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    @ShowFirstParty
    @KeepForSdk
    public interface OnEventListener extends zzkc {
        @ShowFirstParty
        @KeepForSdk
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    public AppMeasurement(zzio zzio) {
        this.zzb = new zza(zzio);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @com.google.android.gms.common.internal.ShowFirstParty
    @androidx.annotation.Keep
    @com.google.android.gms.common.annotation.KeepForSdk
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.measurement.AppMeasurement getInstance(android.content.Context r14) {
        /*
            com.google.android.gms.measurement.AppMeasurement r0 = zza
            if (r0 != 0) goto L_0x0053
            java.lang.Class<com.google.android.gms.measurement.AppMeasurement> r0 = com.google.android.gms.measurement.AppMeasurement.class
            monitor-enter(r0)
            com.google.android.gms.measurement.AppMeasurement r1 = zza     // Catch:{ all -> 0x0027 }
            if (r1 != 0) goto L_0x004f
            r1 = 0
            java.lang.Class<com.google.firebase.analytics.FirebaseAnalytics> r2 = com.google.firebase.analytics.FirebaseAnalytics.class
            java.lang.String r3 = "getScionFrontendApiImplementation"
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            java.lang.Class<android.os.Bundle> r5 = android.os.Bundle.class
            java.lang.Class[] r4 = new java.lang.Class[]{r4, r5}     // Catch:{  }
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r3, r4)     // Catch:{  }
            java.lang.Object[] r3 = new java.lang.Object[]{r14, r1}     // Catch:{  }
            java.lang.Object r2 = r2.invoke(r1, r3)     // Catch:{  }
            com.google.android.gms.measurement.internal.zzlx r2 = (com.google.android.gms.measurement.internal.zzlx) r2     // Catch:{  }
            goto L_0x002a
        L_0x0027:
            r14 = move-exception
            goto L_0x0051
        L_0x0029:
            r2 = r1
        L_0x002a:
            if (r2 == 0) goto L_0x0034
            com.google.android.gms.measurement.AppMeasurement r14 = new com.google.android.gms.measurement.AppMeasurement     // Catch:{ all -> 0x0027 }
            r14.<init>((com.google.android.gms.measurement.internal.zzlx) r2)     // Catch:{ all -> 0x0027 }
            zza = r14     // Catch:{ all -> 0x0027 }
            goto L_0x004f
        L_0x0034:
            com.google.android.gms.internal.measurement.zzdh r13 = new com.google.android.gms.internal.measurement.zzdh     // Catch:{ all -> 0x0027 }
            r11 = 0
            r12 = 0
            r3 = 0
            r5 = 0
            r7 = 1
            r8 = 0
            r9 = 0
            r10 = 0
            r2 = r13
            r2.<init>(r3, r5, r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.measurement.internal.zzio r14 = com.google.android.gms.measurement.internal.zzio.zzp(r14, r13, r1)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.measurement.AppMeasurement r1 = new com.google.android.gms.measurement.AppMeasurement     // Catch:{ all -> 0x0027 }
            r1.<init>((com.google.android.gms.measurement.internal.zzio) r14)     // Catch:{ all -> 0x0027 }
            zza = r1     // Catch:{ all -> 0x0027 }
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            goto L_0x0053
        L_0x0051:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r14
        L_0x0053:
            com.google.android.gms.measurement.AppMeasurement r14 = zza
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.AppMeasurement.getInstance(android.content.Context):com.google.android.gms.measurement.AppMeasurement");
    }

    @Keep
    public void beginAdUnitExposure(String str) {
        this.zzb.zzp(str);
    }

    @ShowFirstParty
    @KeepForSdk
    @Keep
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        this.zzb.zzq(str, str2, bundle);
    }

    @Keep
    public void endAdUnitExposure(String str) {
        this.zzb.zzr(str);
    }

    @Keep
    public long generateEventId() {
        return this.zzb.zzb();
    }

    @Keep
    public String getAppInstanceId() {
        return this.zzb.zzh();
    }

    @KeepForSdk
    public Boolean getBoolean() {
        return this.zzb.zzc();
    }

    @ShowFirstParty
    @Keep
    @KeepForSdk
    public List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        int i;
        List<Bundle> zzm = this.zzb.zzm(str, str2);
        if (zzm == null) {
            i = 0;
        } else {
            i = zzm.size();
        }
        ArrayList arrayList = new ArrayList(i);
        for (Bundle conditionalUserProperty : zzm) {
            arrayList.add(new ConditionalUserProperty(conditionalUserProperty));
        }
        return arrayList;
    }

    @Keep
    public String getCurrentScreenClass() {
        return this.zzb.zzi();
    }

    @Keep
    public String getCurrentScreenName() {
        return this.zzb.zzj();
    }

    @KeepForSdk
    public Double getDouble() {
        return this.zzb.zzd();
    }

    @Keep
    public String getGmpAppId() {
        return this.zzb.zzk();
    }

    @KeepForSdk
    public Integer getInteger() {
        return this.zzb.zze();
    }

    @KeepForSdk
    public Long getLong() {
        return this.zzb.zzf();
    }

    @ShowFirstParty
    @KeepForSdk
    @Keep
    public int getMaxUserProperties(String str) {
        return this.zzb.zza(str);
    }

    @KeepForSdk
    public String getString() {
        return this.zzb.zzl();
    }

    /* access modifiers changed from: protected */
    @Keep
    public Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return this.zzb.zzo(str, str2, z);
    }

    @ShowFirstParty
    @Keep
    public void logEventInternal(String str, String str2, Bundle bundle) {
        this.zzb.zzs(str, str2, bundle);
    }

    @ShowFirstParty
    @KeepForSdk
    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        this.zzb.zzt(str, str2, bundle, j);
    }

    @ShowFirstParty
    @KeepForSdk
    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzb.zzu(onEventListener);
    }

    @ShowFirstParty
    @KeepForSdk
    @Keep
    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Bundle bundle = new Bundle();
        String str = conditionalUserProperty.mAppId;
        if (str != null) {
            bundle.putString("app_id", str);
        }
        String str2 = conditionalUserProperty.mOrigin;
        if (str2 != null) {
            bundle.putString("origin", str2);
        }
        String str3 = conditionalUserProperty.mName;
        if (str3 != null) {
            bundle.putString("name", str3);
        }
        Object obj = conditionalUserProperty.mValue;
        if (obj != null) {
            zzjt.zzb(bundle, obj);
        }
        String str4 = conditionalUserProperty.mTriggerEventName;
        if (str4 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str4);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, conditionalUserProperty.mTriggerTimeout);
        String str5 = conditionalUserProperty.mTimedOutEventName;
        if (str5 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, str5);
        }
        Bundle bundle2 = conditionalUserProperty.mTimedOutEventParams;
        if (bundle2 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, bundle2);
        }
        String str6 = conditionalUserProperty.mTriggeredEventName;
        if (str6 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, str6);
        }
        Bundle bundle3 = conditionalUserProperty.mTriggeredEventParams;
        if (bundle3 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, bundle3);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, conditionalUserProperty.mTimeToLive);
        String str7 = conditionalUserProperty.mExpiredEventName;
        if (str7 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str7);
        }
        Bundle bundle4 = conditionalUserProperty.mExpiredEventParams;
        if (bundle4 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle4);
        }
        zzc zzc = this.zzb;
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, conditionalUserProperty.mCreationTimestamp);
        bundle.putBoolean("active", conditionalUserProperty.mActive);
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, conditionalUserProperty.mTriggeredTimestamp);
        zzc.zzv(bundle);
    }

    @ShowFirstParty
    @KeepForSdk
    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        this.zzb.zzw(eventInterceptor);
    }

    @ShowFirstParty
    @KeepForSdk
    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzb.zzx(onEventListener);
    }

    public AppMeasurement(zzlx zzlx) {
        this.zzb = new zzb(zzlx);
    }

    @ShowFirstParty
    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        return this.zzb.zzn(z);
    }

    @ShowFirstParty
    @KeepForSdk
    public static class ConditionalUserProperty {
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public boolean mActive;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public String mAppId;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public long mCreationTimestamp;
        @Keep
        public String mExpiredEventName;
        @Keep
        public Bundle mExpiredEventParams;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public String mName;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public String mOrigin;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public long mTimeToLive;
        @Keep
        public String mTimedOutEventName;
        @Keep
        public Bundle mTimedOutEventParams;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public String mTriggerEventName;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public long mTriggerTimeout;
        @Keep
        public String mTriggeredEventName;
        @Keep
        public Bundle mTriggeredEventParams;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public long mTriggeredTimestamp;
        @ShowFirstParty
        @KeepForSdk
        @Keep
        public Object mValue;

        @KeepForSdk
        public ConditionalUserProperty() {
        }

        ConditionalUserProperty(Bundle bundle) {
            Preconditions.checkNotNull(bundle);
            Class<String> cls = String.class;
            this.mAppId = (String) zzjt.zza(bundle, "app_id", cls, (Object) null);
            this.mOrigin = (String) zzjt.zza(bundle, "origin", cls, (Object) null);
            this.mName = (String) zzjt.zza(bundle, "name", cls, (Object) null);
            this.mValue = zzjt.zza(bundle, "value", Object.class, (Object) null);
            this.mTriggerEventName = (String) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, cls, (Object) null);
            Class<Long> cls2 = Long.class;
            this.mTriggerTimeout = ((Long) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, cls2, 0L)).longValue();
            this.mTimedOutEventName = (String) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, cls, (Object) null);
            Class<Bundle> cls3 = Bundle.class;
            this.mTimedOutEventParams = (Bundle) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, cls3, (Object) null);
            this.mTriggeredEventName = (String) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, cls, (Object) null);
            this.mTriggeredEventParams = (Bundle) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, cls3, (Object) null);
            this.mTimeToLive = ((Long) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, cls2, 0L)).longValue();
            this.mExpiredEventName = (String) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, cls, (Object) null);
            this.mExpiredEventParams = (Bundle) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, cls3, (Object) null);
            this.mActive = ((Boolean) zzjt.zza(bundle, "active", Boolean.class, Boolean.FALSE)).booleanValue();
            this.mCreationTimestamp = ((Long) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, cls2, 0L)).longValue();
            this.mTriggeredTimestamp = ((Long) zzjt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, cls2, 0L)).longValue();
        }

        @KeepForSdk
        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
            Preconditions.checkNotNull(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            Object obj = conditionalUserProperty.mValue;
            if (obj != null) {
                Object zza = zzmg.zza(obj);
                this.mValue = zza;
                if (zza == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            Bundle bundle = conditionalUserProperty.mTimedOutEventParams;
            if (bundle != null) {
                this.mTimedOutEventParams = new Bundle(bundle);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            Bundle bundle2 = conditionalUserProperty.mTriggeredEventParams;
            if (bundle2 != null) {
                this.mTriggeredEventParams = new Bundle(bundle2);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            Bundle bundle3 = conditionalUserProperty.mExpiredEventParams;
            if (bundle3 != null) {
                this.mExpiredEventParams = new Bundle(bundle3);
            }
        }
    }
}
