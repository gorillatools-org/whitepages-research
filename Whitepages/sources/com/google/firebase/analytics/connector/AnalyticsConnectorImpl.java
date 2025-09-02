package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzff;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzjt;
import com.google.android.gms.measurement.internal.zzmg;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.internal.zza;
import com.google.firebase.analytics.connector.internal.zzc;
import com.google.firebase.analytics.connector.internal.zze;
import com.google.firebase.analytics.connector.internal.zzg;
import com.google.firebase.events.Event;
import com.google.firebase.events.Subscriber;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzc;
    final AppMeasurementSdk zza;
    final Map zzb = new ConcurrentHashMap();

    AnalyticsConnectorImpl(AppMeasurementSdk appMeasurementSdk) {
        Preconditions.checkNotNull(appMeasurementSdk);
        this.zza = appMeasurementSdk;
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    static /* synthetic */ void zza(Event event) {
        boolean z = ((DataCollectionDefaultChange) event.getPayload()).enabled;
        synchronized (AnalyticsConnectorImpl.class) {
            ((AnalyticsConnectorImpl) Preconditions.checkNotNull(zzc)).zza.zza(z);
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzc(String str) {
        if (str.isEmpty()) {
            return false;
        }
        Map map = this.zzb;
        return map.containsKey(str) && map.get(str) != null;
    }

    @KeepForSdk
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        if (str2 == null || zzc.zzb(str2, bundle)) {
            this.zza.clearConditionalUserProperty(str, str2, bundle);
        }
    }

    @KeepForSdk
    public List<AnalyticsConnector.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        for (Bundle next : this.zza.getConditionalUserProperties(str, str2)) {
            int i = zzc.zza;
            Preconditions.checkNotNull(next);
            AnalyticsConnector.ConditionalUserProperty conditionalUserProperty = new AnalyticsConnector.ConditionalUserProperty();
            Class<String> cls = String.class;
            conditionalUserProperty.origin = (String) Preconditions.checkNotNull((String) zzjt.zza(next, "origin", cls, (Object) null));
            conditionalUserProperty.name = (String) Preconditions.checkNotNull((String) zzjt.zza(next, "name", cls, (Object) null));
            conditionalUserProperty.value = zzjt.zza(next, "value", Object.class, (Object) null);
            conditionalUserProperty.triggerEventName = (String) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, cls, (Object) null);
            Class<Long> cls2 = Long.class;
            conditionalUserProperty.triggerTimeout = ((Long) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, cls2, 0L)).longValue();
            conditionalUserProperty.timedOutEventName = (String) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, cls, (Object) null);
            Class<Bundle> cls3 = Bundle.class;
            conditionalUserProperty.timedOutEventParams = (Bundle) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, cls3, (Object) null);
            conditionalUserProperty.triggeredEventName = (String) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, cls, (Object) null);
            conditionalUserProperty.triggeredEventParams = (Bundle) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, cls3, (Object) null);
            conditionalUserProperty.timeToLive = ((Long) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, cls2, 0L)).longValue();
            conditionalUserProperty.expiredEventName = (String) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, cls, (Object) null);
            conditionalUserProperty.expiredEventParams = (Bundle) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, cls3, (Object) null);
            conditionalUserProperty.active = ((Boolean) zzjt.zza(next, "active", Boolean.class, Boolean.FALSE)).booleanValue();
            conditionalUserProperty.creationTimestamp = ((Long) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, cls2, 0L)).longValue();
            conditionalUserProperty.triggeredTimestamp = ((Long) zzjt.zza(next, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, cls2, 0L)).longValue();
            arrayList.add(conditionalUserProperty);
        }
        return arrayList;
    }

    @KeepForSdk
    public int getMaxUserProperties(String str) {
        return this.zza.getMaxUserProperties(str);
    }

    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        return this.zza.getUserProperties((String) null, (String) null, z);
    }

    @KeepForSdk
    public void logEvent(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (zzc.zzd(str) && zzc.zzb(str2, bundle) && zzc.zza(str, str2, bundle)) {
            if ("clx".equals(str) && "_ae".equals(str2)) {
                bundle.putLong(NotificationMessage.NOTIF_KEY_REQUEST_ID, 1);
            }
            this.zza.logEvent(str, str2, bundle);
        }
    }

    @KeepForSdk
    public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(final String str, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        Object obj;
        Preconditions.checkNotNull(analyticsConnectorListener);
        if (zzc.zzd(str) && !zzc(str)) {
            AppMeasurementSdk appMeasurementSdk = this.zza;
            if ("fiam".equals(str)) {
                obj = new zze(appMeasurementSdk, analyticsConnectorListener);
            } else {
                obj = "clx".equals(str) ? new zzg(appMeasurementSdk, analyticsConnectorListener) : null;
            }
            if (obj != null) {
                this.zzb.put(str, obj);
                return new AnalyticsConnector.AnalyticsConnectorHandle(this) {
                    final /* synthetic */ AnalyticsConnectorImpl zzb;

                    {
                        this.zzb = r1;
                    }

                    @KeepForSdk
                    public void registerEventNames(Set<String> set) {
                        AnalyticsConnectorImpl analyticsConnectorImpl = this.zzb;
                        String str = str;
                        if (analyticsConnectorImpl.zzc(str) && str.equals("fiam") && set != null && !set.isEmpty()) {
                            ((zza) analyticsConnectorImpl.zzb.get(str)).zzb(set);
                        }
                    }

                    public final void unregister() {
                        AnalyticsConnectorImpl analyticsConnectorImpl = this.zzb;
                        String str = str;
                        if (analyticsConnectorImpl.zzc(str)) {
                            Map map = analyticsConnectorImpl.zzb;
                            AnalyticsConnector.AnalyticsConnectorListener zza2 = ((zza) map.get(str)).zza();
                            if (zza2 != null) {
                                zza2.onMessageTriggered(0, (Bundle) null);
                            }
                            map.remove(str);
                        }
                    }

                    @KeepForSdk
                    public void unregisterEventNames() {
                        AnalyticsConnectorImpl analyticsConnectorImpl = this.zzb;
                        String str = str;
                        if (analyticsConnectorImpl.zzc(str) && str.equals("fiam")) {
                            ((zza) analyticsConnectorImpl.zzb.get(str)).zzc();
                        }
                    }
                };
            }
        }
        return null;
    }

    @KeepForSdk
    public void setConditionalUserProperty(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        String str;
        int i = zzc.zza;
        if (conditionalUserProperty != null && (str = conditionalUserProperty.origin) != null && !str.isEmpty()) {
            Object obj = conditionalUserProperty.value;
            if ((obj == null || zzmg.zza(obj) != null) && zzc.zzd(str) && zzc.zze(str, conditionalUserProperty.name)) {
                String str2 = conditionalUserProperty.expiredEventName;
                if (str2 == null || (zzc.zzb(str2, conditionalUserProperty.expiredEventParams) && zzc.zza(str, conditionalUserProperty.expiredEventName, conditionalUserProperty.expiredEventParams))) {
                    String str3 = conditionalUserProperty.triggeredEventName;
                    if (str3 == null || (zzc.zzb(str3, conditionalUserProperty.triggeredEventParams) && zzc.zza(str, conditionalUserProperty.triggeredEventName, conditionalUserProperty.triggeredEventParams))) {
                        String str4 = conditionalUserProperty.timedOutEventName;
                        if (str4 == null || (zzc.zzb(str4, conditionalUserProperty.timedOutEventParams) && zzc.zza(str, conditionalUserProperty.timedOutEventName, conditionalUserProperty.timedOutEventParams))) {
                            AppMeasurementSdk appMeasurementSdk = this.zza;
                            Bundle bundle = new Bundle();
                            String str5 = conditionalUserProperty.origin;
                            if (str5 != null) {
                                bundle.putString("origin", str5);
                            }
                            String str6 = conditionalUserProperty.name;
                            if (str6 != null) {
                                bundle.putString("name", str6);
                            }
                            Object obj2 = conditionalUserProperty.value;
                            if (obj2 != null) {
                                zzjt.zzb(bundle, obj2);
                            }
                            String str7 = conditionalUserProperty.triggerEventName;
                            if (str7 != null) {
                                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str7);
                            }
                            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, conditionalUserProperty.triggerTimeout);
                            String str8 = conditionalUserProperty.timedOutEventName;
                            if (str8 != null) {
                                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, str8);
                            }
                            Bundle bundle2 = conditionalUserProperty.timedOutEventParams;
                            if (bundle2 != null) {
                                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, bundle2);
                            }
                            String str9 = conditionalUserProperty.triggeredEventName;
                            if (str9 != null) {
                                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, str9);
                            }
                            Bundle bundle3 = conditionalUserProperty.triggeredEventParams;
                            if (bundle3 != null) {
                                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, bundle3);
                            }
                            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, conditionalUserProperty.timeToLive);
                            String str10 = conditionalUserProperty.expiredEventName;
                            if (str10 != null) {
                                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str10);
                            }
                            Bundle bundle4 = conditionalUserProperty.expiredEventParams;
                            if (bundle4 != null) {
                                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle4);
                            }
                            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, conditionalUserProperty.creationTimestamp);
                            bundle.putBoolean("active", conditionalUserProperty.active);
                            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, conditionalUserProperty.triggeredTimestamp);
                            appMeasurementSdk.setConditionalUserProperty(bundle);
                        }
                    }
                }
            }
        }
    }

    @KeepForSdk
    public void setUserProperty(String str, String str2, Object obj) {
        if (zzc.zzd(str) && zzc.zze(str, str2)) {
            this.zza.setUserProperty(str, str2, obj);
        }
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp) {
        return (AnalyticsConnector) firebaseApp.get(AnalyticsConnector.class);
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp, Context context, Subscriber subscriber) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(subscriber);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzc == null) {
            synchronized (AnalyticsConnectorImpl.class) {
                try {
                    if (zzc == null) {
                        Bundle bundle = new Bundle(1);
                        if (firebaseApp.isDefaultApp()) {
                            subscriber.subscribe(DataCollectionDefaultChange.class, new zza(), new zzb());
                            bundle.putBoolean("dataCollectionDefaultEnabled", firebaseApp.isDataCollectionDefaultEnabled());
                        }
                        zzc = new AnalyticsConnectorImpl(zzff.zzg(context, (String) null, (String) null, (String) null, bundle).zzd());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zzc;
    }
}
