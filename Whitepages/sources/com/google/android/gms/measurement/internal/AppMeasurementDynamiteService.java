package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzcu;
import com.google.android.gms.internal.measurement.zzcy;
import com.google.android.gms.internal.measurement.zzdb;
import com.google.android.gms.internal.measurement.zzde;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzdh;
import com.google.android.gms.internal.measurement.zzdj;
import java.util.Map;

@DynamiteApi
public class AppMeasurementDynamiteService extends zzcu {
    zzio zza = null;
    private final Map zzb = new ArrayMap();

    public static /* synthetic */ void $r8$lambda$W3cgi1t5N0SU6fYxM9Fsh5qQfPc(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdb zzdb) {
        try {
            zzdb.zze();
        } catch (RemoteException e) {
            ((zzio) Preconditions.checkNotNull(appMeasurementDynamiteService.zza)).zzaW().zzk().zzb("Failed to call IDynamiteUploadBatchesCallback", e);
        }
    }

    private final void zzb() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    private final void zzc(zzcy zzcy, String str) {
        zzb();
        this.zza.zzw().zzZ(zzcy, str);
    }

    public void beginAdUnitExposure(String str, long j) throws RemoteException {
        zzb();
        this.zza.zzd().zzd(str, j);
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        zzb();
        this.zza.zzq().zzJ(str, str2, bundle);
    }

    public void clearMeasurementEnabled(long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzai((Boolean) null);
    }

    public void endAdUnitExposure(String str, long j) throws RemoteException {
        zzb();
        this.zza.zzd().zze(str, j);
    }

    public void generateEventId(zzcy zzcy) throws RemoteException {
        zzb();
        long zzs = this.zza.zzw().zzs();
        zzb();
        this.zza.zzw().zzY(zzcy, zzs);
    }

    public void getAppInstanceId(zzcy zzcy) throws RemoteException {
        zzb();
        this.zza.zzaX().zzq(new zzj(this, zzcy));
    }

    public void getCachedAppInstanceId(zzcy zzcy) throws RemoteException {
        zzb();
        zzc(zzcy, this.zza.zzq().zzr());
    }

    public void getConditionalUserProperties(String str, String str2, zzcy zzcy) throws RemoteException {
        zzb();
        this.zza.zzaX().zzq(new zzn(this, zzcy, str, str2));
    }

    public void getCurrentScreenClass(zzcy zzcy) throws RemoteException {
        zzb();
        zzc(zzcy, this.zza.zzq().zzs());
    }

    public void getCurrentScreenName(zzcy zzcy) throws RemoteException {
        zzb();
        zzc(zzcy, this.zza.zzq().zzt());
    }

    public void getGmpAppId(zzcy zzcy) throws RemoteException {
        zzb();
        zzlw zzq = this.zza.zzq();
        zzio zzio = zzq.zzu;
        String str = null;
        if (zzio.zzf().zzx((String) null, zzgi.zzbp) || zzq.zzu.zzx() == null) {
            try {
                str = zzmg.zzc(zzio.zzaT(), "google_app_id", zzq.zzu.zzA());
            } catch (IllegalStateException e) {
                zzq.zzu.zzaW().zze().zzb("getGoogleAppId failed with exception", e);
            }
        } else {
            str = zzq.zzu.zzx();
        }
        zzc(zzcy, str);
    }

    public void getMaxUserProperties(String str, zzcy zzcy) throws RemoteException {
        zzb();
        this.zza.zzq().zzi(str);
        zzb();
        this.zza.zzw().zzX(zzcy, 25);
    }

    public void getSessionId(zzcy zzcy) throws RemoteException {
        zzb();
        zzlw zzq = this.zza.zzq();
        zzq.zzu.zzaX().zzq(new zzlj(zzq, zzcy));
    }

    public void getTestFlag(zzcy zzcy, int i) throws RemoteException {
        zzb();
        if (i == 0) {
            this.zza.zzw().zzZ(zzcy, this.zza.zzq().zzu());
        } else if (i == 1) {
            this.zza.zzw().zzY(zzcy, this.zza.zzq().zzq().longValue());
        } else if (i == 2) {
            zzqf zzw = this.zza.zzw();
            double doubleValue = this.zza.zzq().zzm().doubleValue();
            Bundle bundle = new Bundle();
            bundle.putDouble("r", doubleValue);
            try {
                zzcy.zze(bundle);
            } catch (RemoteException e) {
                zzw.zzu.zzaW().zzk().zzb("Error returning double value to wrapper", e);
            }
        } else if (i == 3) {
            this.zza.zzw().zzX(zzcy, this.zza.zzq().zzp().intValue());
        } else if (i == 4) {
            this.zza.zzw().zzT(zzcy, this.zza.zzq().zzl().booleanValue());
        }
    }

    public void getUserProperties(String str, String str2, boolean z, zzcy zzcy) throws RemoteException {
        zzb();
        this.zza.zzaX().zzq(new zzl(this, zzcy, str, str2, z));
    }

    public void initForTests(Map map) throws RemoteException {
        zzb();
    }

    public void initialize(IObjectWrapper iObjectWrapper, zzdh zzdh, long j) throws RemoteException {
        zzio zzio = this.zza;
        if (zzio == null) {
            this.zza = zzio.zzp((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(iObjectWrapper)), zzdh, Long.valueOf(j));
        } else {
            zzio.zzaW().zzk().zza("Attempting to initialize multiple times");
        }
    }

    public void isDataCollectionEnabled(zzcy zzcy) throws RemoteException {
        zzb();
        this.zza.zzaX().zzq(new zzo(this, zzcy));
    }

    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzP(str, str2, bundle, z, z2, j);
    }

    public void logEventAndBundle(String str, String str2, Bundle bundle, zzcy zzcy, long j) throws RemoteException {
        zzb();
        Preconditions.checkNotEmpty(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", "app");
        this.zza.zzaX().zzq(new zzk(this, zzcy, new zzbh(str2, new zzbf(bundle), "app", j), str));
    }

    public void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Object obj;
        Object obj2;
        zzb();
        Object obj3 = null;
        if (iObjectWrapper == null) {
            obj = null;
        } else {
            obj = ObjectWrapper.unwrap(iObjectWrapper);
        }
        if (iObjectWrapper2 == null) {
            obj2 = null;
        } else {
            obj2 = ObjectWrapper.unwrap(iObjectWrapper2);
        }
        if (iObjectWrapper3 != null) {
            obj3 = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        this.zza.zzaW().zzu(i, true, false, str, obj, obj2, obj3);
    }

    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        zzb();
        onActivityCreatedByScionActivityInfo(zzdj.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(iObjectWrapper))), bundle, j);
    }

    public void onActivityCreatedByScionActivityInfo(zzdj zzdj, Bundle bundle, long j) {
        zzb();
        zzlv zzlv = this.zza.zzq().zza;
        if (zzlv != null) {
            this.zza.zzq().zzK();
            zzlv.zza(zzdj, bundle);
        }
    }

    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        onActivityDestroyedByScionActivityInfo(zzdj.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(iObjectWrapper))), j);
    }

    public void onActivityDestroyedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        zzb();
        zzlv zzlv = this.zza.zzq().zza;
        if (zzlv != null) {
            this.zza.zzq().zzK();
            zzlv.zzb(zzdj);
        }
    }

    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        onActivityPausedByScionActivityInfo(zzdj.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(iObjectWrapper))), j);
    }

    public void onActivityPausedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        zzb();
        zzlv zzlv = this.zza.zzq().zza;
        if (zzlv != null) {
            this.zza.zzq().zzK();
            zzlv.zzc(zzdj);
        }
    }

    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        onActivityResumedByScionActivityInfo(zzdj.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(iObjectWrapper))), j);
    }

    public void onActivityResumedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        zzb();
        zzlv zzlv = this.zza.zzq().zza;
        if (zzlv != null) {
            this.zza.zzq().zzK();
            zzlv.zzd(zzdj);
        }
    }

    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzcy zzcy, long j) throws RemoteException {
        zzb();
        onActivitySaveInstanceStateByScionActivityInfo(zzdj.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(iObjectWrapper))), zzcy, j);
    }

    public void onActivitySaveInstanceStateByScionActivityInfo(zzdj zzdj, zzcy zzcy, long j) throws RemoteException {
        zzb();
        zzlv zzlv = this.zza.zzq().zza;
        Bundle bundle = new Bundle();
        if (zzlv != null) {
            this.zza.zzq().zzK();
            zzlv.zze(zzdj, bundle);
        }
        try {
            zzcy.zze(bundle);
        } catch (RemoteException e) {
            this.zza.zzaW().zzk().zzb("Error returning bundle value to wrapper", e);
        }
    }

    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        onActivityStartedByScionActivityInfo(zzdj.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(iObjectWrapper))), j);
    }

    public void onActivityStartedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        zzb();
        if (this.zza.zzq().zza != null) {
            this.zza.zzq().zzK();
        }
    }

    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        onActivityStoppedByScionActivityInfo(zzdj.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(iObjectWrapper))), j);
    }

    public void onActivityStoppedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        zzb();
        if (this.zza.zzq().zza != null) {
            this.zza.zzq().zzK();
        }
    }

    public void performAction(Bundle bundle, zzcy zzcy, long j) throws RemoteException {
        zzb();
        zzcy.zze((Bundle) null);
    }

    public void registerOnMeasurementEventListener(zzde zzde) throws RemoteException {
        zzkc zzkc;
        zzb();
        Map map = this.zzb;
        synchronized (map) {
            try {
                zzkc = (zzkc) map.get(Integer.valueOf(zzde.zze()));
                if (zzkc == null) {
                    zzkc = new zzq(this, zzde);
                    map.put(Integer.valueOf(zzde.zze()), zzkc);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.zza.zzq().zzV(zzkc);
    }

    public void resetAnalyticsData(long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzX(j);
    }

    public void retrieveAndUploadBatches(zzdb zzdb) {
        zzb();
        if (this.zza.zzf().zzx((String) null, zzgi.zzaR)) {
            this.zza.zzq().zzY(new zzi(this, zzdb));
        }
    }

    public void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        zzb();
        if (bundle == null) {
            this.zza.zzaW().zze().zza("Conditional user property must not be null");
        } else {
            this.zza.zzq().zzae(bundle, j);
        }
    }

    public void setConsent(Bundle bundle, long j) throws RemoteException {
        zzb();
        zzlw zzq = this.zza.zzq();
        zzq.zzu.zzaX().zzr(new zzkm(zzq, bundle, j));
    }

    public void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzaf(bundle, -20, j);
    }

    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        zzb();
        setCurrentScreenByScionActivityInfo(zzdj.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(iObjectWrapper))), str, str2, j);
    }

    public void setCurrentScreenByScionActivityInfo(zzdj zzdj, String str, String str2, long j) throws RemoteException {
        zzb();
        this.zza.zzt().zzx(zzdj, str, str2);
    }

    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        zzb();
        zzlw zzq = this.zza.zzq();
        zzq.zza();
        zzq.zzu.zzaX().zzq(new zzkv(zzq, z));
    }

    public void setDefaultEventParameters(Bundle bundle) {
        zzb();
        zzlw zzq = this.zza.zzq();
        zzq.zzu.zzaX().zzq(new zzkk(zzq, bundle == null ? new Bundle() : new Bundle(bundle)));
    }

    public void setEventInterceptor(zzde zzde) throws RemoteException {
        zzb();
        zzp zzp = new zzp(this, zzde);
        if (this.zza.zzaX().zzu()) {
            this.zza.zzq().zzah(zzp);
        } else {
            this.zza.zzaX().zzq(new zzm(this, zzp));
        }
    }

    public void setInstanceIdProvider(zzdg zzdg) throws RemoteException {
        zzb();
    }

    public void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzai(Boolean.valueOf(z));
    }

    public void setMinimumSessionDuration(long j) throws RemoteException {
        zzb();
    }

    public void setSessionTimeoutDuration(long j) throws RemoteException {
        zzb();
        zzlw zzq = this.zza.zzq();
        zzq.zzu.zzaX().zzq(new zzkx(zzq, j));
    }

    public void setSgtmDebugInfo(Intent intent) throws RemoteException {
        zzb();
        zzlw zzq = this.zza.zzq();
        Uri data = intent.getData();
        if (data == null) {
            zzq.zzu.zzaW().zzi().zza("Activity intent has no data. Preview Mode was not enabled.");
            return;
        }
        String queryParameter = data.getQueryParameter("sgtm_debug_enable");
        if (queryParameter == null || !queryParameter.equals("1")) {
            zzio zzio = zzq.zzu;
            zzio.zzaW().zzi().zza("[sgtm] Preview Mode was not enabled.");
            zzio.zzf().zzv((String) null);
            return;
        }
        String queryParameter2 = data.getQueryParameter("sgtm_preview_key");
        if (!TextUtils.isEmpty(queryParameter2)) {
            zzio zzio2 = zzq.zzu;
            zzio2.zzaW().zzi().zzb("[sgtm] Preview Mode was enabled. Using the sgtmPreviewKey: ", queryParameter2);
            zzio2.zzf().zzv(queryParameter2);
        }
    }

    public void setUserId(String str, long j) throws RemoteException {
        zzb();
        zzlw zzq = this.zza.zzq();
        if (str == null || !TextUtils.isEmpty(str)) {
            zzq.zzu.zzaX().zzq(new zzkg(zzq, str));
            zzq.zzam((String) null, "_id", str, true, j);
            return;
        }
        zzq.zzu.zzaW().zzk().zza("User ID must be non-empty or null");
    }

    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzam(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    public void unregisterOnMeasurementEventListener(zzde zzde) throws RemoteException {
        zzkc zzkc;
        zzb();
        Map map = this.zzb;
        synchronized (map) {
            zzkc = (zzkc) map.remove(Integer.valueOf(zzde.zze()));
        }
        if (zzkc == null) {
            zzkc = new zzq(this, zzde);
        }
        this.zza.zzq().zzao(zzkc);
    }
}
