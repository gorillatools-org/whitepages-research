package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public final class zzct extends zzbm implements zzcv {
    zzct(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public final void beginAdUnitExposure(String str, long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeLong(j);
        zzc(23, zza);
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zzd(zza, bundle);
        zzc(9, zza);
    }

    public final void clearMeasurementEnabled(long j) throws RemoteException {
        throw null;
    }

    public final void endAdUnitExposure(String str, long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeLong(j);
        zzc(24, zza);
    }

    public final void generateEventId(zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcy);
        zzc(22, zza);
    }

    public final void getAppInstanceId(zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcy);
        zzc(20, zza);
    }

    public final void getCachedAppInstanceId(zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcy);
        zzc(19, zza);
    }

    public final void getConditionalUserProperties(String str, String str2, zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zze(zza, zzcy);
        zzc(10, zza);
    }

    public final void getCurrentScreenClass(zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcy);
        zzc(17, zza);
    }

    public final void getCurrentScreenName(zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcy);
        zzc(16, zza);
    }

    public final void getGmpAppId(zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcy);
        zzc(21, zza);
    }

    public final void getMaxUserProperties(String str, zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzbo.zze(zza, zzcy);
        zzc(6, zza);
    }

    public final void getSessionId(zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcy);
        zzc(46, zza);
    }

    public final void getTestFlag(zzcy zzcy, int i) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcy);
        zza.writeInt(i);
        zzc(38, zza);
    }

    public final void getUserProperties(String str, String str2, boolean z, zzcy zzcy) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        int i = zzbo.zza;
        zza.writeInt(z ? 1 : 0);
        zzbo.zze(zza, zzcy);
        zzc(5, zza);
    }

    public final void initForTests(Map map) throws RemoteException {
        throw null;
    }

    public final void initialize(IObjectWrapper iObjectWrapper, zzdh zzdh, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zzbo.zzd(zza, zzdh);
        zza.writeLong(j);
        zzc(1, zza);
    }

    public final void isDataCollectionEnabled(zzcy zzcy) throws RemoteException {
        throw null;
    }

    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zzd(zza, bundle);
        zza.writeInt(z ? 1 : 0);
        zza.writeInt(z2 ? 1 : 0);
        zza.writeLong(j);
        zzc(2, zza);
    }

    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzcy zzcy, long j) throws RemoteException {
        throw null;
    }

    public final void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(5);
        zza.writeString(str);
        zzbo.zze(zza, iObjectWrapper);
        zzbo.zze(zza, iObjectWrapper2);
        zzbo.zze(zza, iObjectWrapper3);
        zzc(33, zza);
    }

    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        throw null;
    }

    public final void onActivityCreatedByScionActivityInfo(zzdj zzdj, Bundle bundle, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzdj);
        zzbo.zzd(zza, bundle);
        zza.writeLong(j);
        zzc(53, zza);
    }

    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        throw null;
    }

    public final void onActivityDestroyedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzdj);
        zza.writeLong(j);
        zzc(54, zza);
    }

    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        throw null;
    }

    public final void onActivityPausedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzdj);
        zza.writeLong(j);
        zzc(55, zza);
    }

    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        throw null;
    }

    public final void onActivityResumedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzdj);
        zza.writeLong(j);
        zzc(56, zza);
    }

    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzcy zzcy, long j) throws RemoteException {
        throw null;
    }

    public final void onActivitySaveInstanceStateByScionActivityInfo(zzdj zzdj, zzcy zzcy, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzdj);
        zzbo.zze(zza, zzcy);
        zza.writeLong(j);
        zzc(57, zza);
    }

    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        throw null;
    }

    public final void onActivityStartedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzdj);
        zza.writeLong(j);
        zzc(51, zza);
    }

    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        throw null;
    }

    public final void onActivityStoppedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzdj);
        zza.writeLong(j);
        zzc(52, zza);
    }

    public final void performAction(Bundle bundle, zzcy zzcy, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zzbo.zze(zza, zzcy);
        zza.writeLong(j);
        zzc(32, zza);
    }

    public final void registerOnMeasurementEventListener(zzde zzde) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzde);
        zzc(35, zza);
    }

    public final void resetAnalyticsData(long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j);
        zzc(12, zza);
    }

    public final void retrieveAndUploadBatches(zzdb zzdb) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzdb);
        zzc(58, zza);
    }

    public final void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zza.writeLong(j);
        zzc(8, zza);
    }

    public final void setConsent(Bundle bundle, long j) throws RemoteException {
        throw null;
    }

    public final void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zza.writeLong(j);
        zzc(45, zza);
    }

    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        throw null;
    }

    public final void setCurrentScreenByScionActivityInfo(zzdj zzdj, String str, String str2, long j) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzdj);
        zza.writeString(str);
        zza.writeString(str2);
        zza.writeLong(j);
        zzc(50, zza);
    }

    public final void setDataCollectionEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        int i = zzbo.zza;
        zza.writeInt(z ? 1 : 0);
        zzc(39, zza);
    }

    public final void setDefaultEventParameters(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zzc(42, zza);
    }

    public final void setEventInterceptor(zzde zzde) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzde);
        zzc(34, zza);
    }

    public final void setInstanceIdProvider(zzdg zzdg) throws RemoteException {
        throw null;
    }

    public final void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        Parcel zza = zza();
        int i = zzbo.zza;
        zza.writeInt(z ? 1 : 0);
        zza.writeLong(j);
        zzc(11, zza);
    }

    public final void setMinimumSessionDuration(long j) throws RemoteException {
        throw null;
    }

    public final void setSessionTimeoutDuration(long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j);
        zzc(14, zza);
    }

    public final void setSgtmDebugInfo(Intent intent) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, intent);
        zzc(48, zza);
    }

    public final void setUserId(String str, long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeLong(j);
        zzc(7, zza);
    }

    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zze(zza, iObjectWrapper);
        zza.writeInt(z ? 1 : 0);
        zza.writeLong(j);
        zzc(4, zza);
    }

    public final void unregisterOnMeasurementEventListener(zzde zzde) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzde);
        zzc(36, zza);
    }
}
