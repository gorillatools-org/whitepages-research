package com.facebook.devicerequests.internal;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import kotlin.jvm.internal.Intrinsics;

public final class DeviceRequestsHelper$startAdvertisementServiceImpl$nsdRegistrationListener$1 implements NsdManager.RegistrationListener {
    final /* synthetic */ String $nsdServiceName;
    final /* synthetic */ String $userCode;

    public void onServiceUnregistered(NsdServiceInfo nsdServiceInfo) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "serviceInfo");
    }

    public void onUnregistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "serviceInfo");
    }

    DeviceRequestsHelper$startAdvertisementServiceImpl$nsdRegistrationListener$1(String str, String str2) {
        this.$nsdServiceName = str;
        this.$userCode = str2;
    }

    public void onServiceRegistered(NsdServiceInfo nsdServiceInfo) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "NsdServiceInfo");
        if (!Intrinsics.areEqual((Object) this.$nsdServiceName, (Object) nsdServiceInfo.getServiceName())) {
            DeviceRequestsHelper.cleanUpAdvertisementService(this.$userCode);
        }
    }

    public void onRegistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "serviceInfo");
        DeviceRequestsHelper.cleanUpAdvertisementService(this.$userCode);
    }
}
