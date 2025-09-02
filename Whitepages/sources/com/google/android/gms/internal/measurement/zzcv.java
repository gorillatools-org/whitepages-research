package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public interface zzcv extends IInterface {
    void beginAdUnitExposure(String str, long j) throws RemoteException;

    void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException;

    void clearMeasurementEnabled(long j) throws RemoteException;

    void endAdUnitExposure(String str, long j) throws RemoteException;

    void generateEventId(zzcy zzcy) throws RemoteException;

    void getAppInstanceId(zzcy zzcy) throws RemoteException;

    void getCachedAppInstanceId(zzcy zzcy) throws RemoteException;

    void getConditionalUserProperties(String str, String str2, zzcy zzcy) throws RemoteException;

    void getCurrentScreenClass(zzcy zzcy) throws RemoteException;

    void getCurrentScreenName(zzcy zzcy) throws RemoteException;

    void getGmpAppId(zzcy zzcy) throws RemoteException;

    void getMaxUserProperties(String str, zzcy zzcy) throws RemoteException;

    void getSessionId(zzcy zzcy) throws RemoteException;

    void getTestFlag(zzcy zzcy, int i) throws RemoteException;

    void getUserProperties(String str, String str2, boolean z, zzcy zzcy) throws RemoteException;

    void initForTests(Map map) throws RemoteException;

    void initialize(IObjectWrapper iObjectWrapper, zzdh zzdh, long j) throws RemoteException;

    void isDataCollectionEnabled(zzcy zzcy) throws RemoteException;

    void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException;

    void logEventAndBundle(String str, String str2, Bundle bundle, zzcy zzcy, long j) throws RemoteException;

    void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException;

    void onActivityCreatedByScionActivityInfo(zzdj zzdj, Bundle bundle, long j) throws RemoteException;

    void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException;

    void onActivityDestroyedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException;

    void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException;

    void onActivityPausedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException;

    void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException;

    void onActivityResumedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException;

    void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzcy zzcy, long j) throws RemoteException;

    void onActivitySaveInstanceStateByScionActivityInfo(zzdj zzdj, zzcy zzcy, long j) throws RemoteException;

    void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException;

    void onActivityStartedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException;

    void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException;

    void onActivityStoppedByScionActivityInfo(zzdj zzdj, long j) throws RemoteException;

    void performAction(Bundle bundle, zzcy zzcy, long j) throws RemoteException;

    void registerOnMeasurementEventListener(zzde zzde) throws RemoteException;

    void resetAnalyticsData(long j) throws RemoteException;

    void retrieveAndUploadBatches(zzdb zzdb) throws RemoteException;

    void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException;

    void setConsent(Bundle bundle, long j) throws RemoteException;

    void setConsentThirdParty(Bundle bundle, long j) throws RemoteException;

    void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException;

    void setCurrentScreenByScionActivityInfo(zzdj zzdj, String str, String str2, long j) throws RemoteException;

    void setDataCollectionEnabled(boolean z) throws RemoteException;

    void setDefaultEventParameters(Bundle bundle) throws RemoteException;

    void setEventInterceptor(zzde zzde) throws RemoteException;

    void setInstanceIdProvider(zzdg zzdg) throws RemoteException;

    void setMeasurementEnabled(boolean z, long j) throws RemoteException;

    void setMinimumSessionDuration(long j) throws RemoteException;

    void setSessionTimeoutDuration(long j) throws RemoteException;

    void setSgtmDebugInfo(Intent intent) throws RemoteException;

    void setUserId(String str, long j) throws RemoteException;

    void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException;

    void unregisterOnMeasurementEventListener(zzde zzde) throws RemoteException;
}
