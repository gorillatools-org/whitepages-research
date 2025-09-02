package com.facebook.appevents.ondeviceprocessing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.FacebookSignatureValidator;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.ppml.receiver.IReceiverService;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class RemoteServiceWrapper {
    public static final RemoteServiceWrapper INSTANCE = new RemoteServiceWrapper();
    private static final String TAG = RemoteServiceWrapper.class.getSimpleName();
    private static Boolean isServiceAvailable;

    public enum ServiceResult {
        OPERATION_SUCCESS,
        SERVICE_NOT_AVAILABLE,
        SERVICE_ERROR
    }

    private RemoteServiceWrapper() {
    }

    public static final boolean isServiceAvailable() {
        Class<RemoteServiceWrapper> cls = RemoteServiceWrapper.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            if (isServiceAvailable == null) {
                isServiceAvailable = Boolean.valueOf(INSTANCE.getVerifiedServiceIntent(FacebookSdk.getApplicationContext()) != null);
            }
            Boolean bool = isServiceAvailable;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final ServiceResult sendInstallEvent(String str) {
        Class<RemoteServiceWrapper> cls = RemoteServiceWrapper.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "applicationId");
            return INSTANCE.sendEvents(EventType.MOBILE_APP_INSTALL, str, CollectionsKt.emptyList());
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final ServiceResult sendCustomEvents(String str, List list) {
        Class<RemoteServiceWrapper> cls = RemoteServiceWrapper.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "applicationId");
            Intrinsics.checkNotNullParameter(list, "appEvents");
            return INSTANCE.sendEvents(EventType.CUSTOM_APP_EVENTS, str, list);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    private final ServiceResult sendEvents(EventType eventType, String str, List list) {
        Context applicationContext;
        RemoteServiceConnection remoteServiceConnection;
        String str2;
        ServiceResult serviceResult;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            ServiceResult serviceResult2 = ServiceResult.SERVICE_NOT_AVAILABLE;
            AppEventUtility.assertIsNotMainThread();
            applicationContext = FacebookSdk.getApplicationContext();
            Intent verifiedServiceIntent = getVerifiedServiceIntent(applicationContext);
            if (verifiedServiceIntent == null) {
                return serviceResult2;
            }
            remoteServiceConnection = new RemoteServiceConnection();
            if (!applicationContext.bindService(verifiedServiceIntent, remoteServiceConnection, 1)) {
                return ServiceResult.SERVICE_ERROR;
            }
            try {
                IBinder binder = remoteServiceConnection.getBinder();
                if (binder != null) {
                    IReceiverService asInterface = IReceiverService.Stub.asInterface(binder);
                    Bundle buildEventsBundle = RemoteServiceParametersHelper.buildEventsBundle(eventType, str, list);
                    if (buildEventsBundle != null) {
                        asInterface.sendEvents(buildEventsBundle);
                        String str3 = TAG;
                        Utility.logd(str3, "Successfully sent events to the remote service: " + buildEventsBundle);
                    }
                    serviceResult2 = ServiceResult.OPERATION_SUCCESS;
                }
                applicationContext.unbindService(remoteServiceConnection);
                Utility.logd(TAG, "Unbound from the remote service");
                return serviceResult2;
            } catch (InterruptedException e) {
                serviceResult = ServiceResult.SERVICE_ERROR;
                str2 = TAG;
                Utility.logd(str2, (Exception) e);
                applicationContext.unbindService(remoteServiceConnection);
                Utility.logd(str2, "Unbound from the remote service");
                return serviceResult;
            } catch (RemoteException e2) {
                serviceResult = ServiceResult.SERVICE_ERROR;
                str2 = TAG;
                Utility.logd(str2, (Exception) e2);
                applicationContext.unbindService(remoteServiceConnection);
                Utility.logd(str2, "Unbound from the remote service");
                return serviceResult;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final Intent getVerifiedServiceIntent(Context context) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                Intent intent = new Intent("ReceiverService");
                intent.setPackage("com.facebook.katana");
                if (packageManager.resolveService(intent, 0) != null && FacebookSignatureValidator.validateSignature(context, "com.facebook.katana")) {
                    return intent;
                }
                Intent intent2 = new Intent("ReceiverService");
                intent2.setPackage("com.facebook.wakizashi");
                if (packageManager.resolveService(intent2, 0) == null || !FacebookSignatureValidator.validateSignature(context, "com.facebook.wakizashi")) {
                    return null;
                }
                return intent2;
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public enum EventType {
        MOBILE_APP_INSTALL("MOBILE_APP_INSTALL"),
        CUSTOM_APP_EVENTS("CUSTOM_APP_EVENTS");
        
        private final String eventType;

        private EventType(String str) {
            this.eventType = str;
        }

        public String toString() {
            return this.eventType;
        }
    }

    private static final class RemoteServiceConnection implements ServiceConnection {
        private IBinder binder;
        private final CountDownLatch latch = new CountDownLatch(1);

        public void onServiceDisconnected(ComponentName componentName) {
            Intrinsics.checkNotNullParameter(componentName, "name");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Intrinsics.checkNotNullParameter(componentName, "name");
            Intrinsics.checkNotNullParameter(iBinder, "serviceBinder");
            this.binder = iBinder;
            this.latch.countDown();
        }

        public void onNullBinding(ComponentName componentName) {
            Intrinsics.checkNotNullParameter(componentName, "name");
            this.latch.countDown();
        }

        public final IBinder getBinder() {
            this.latch.await(5, TimeUnit.SECONDS);
            return this.binder;
        }
    }
}
