package com.facebook.devicerequests.internal;

import android.graphics.Bitmap;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.SmartLoginOption;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

public final class DeviceRequestsHelper {
    public static final DeviceRequestsHelper INSTANCE = new DeviceRequestsHelper();
    private static final String TAG = DeviceRequestsHelper.class.getCanonicalName();
    private static final HashMap deviceRequestsListeners = new HashMap();

    private DeviceRequestsHelper() {
    }

    public static final String getDeviceInfo(Map map) {
        Class<DeviceRequestsHelper> cls = DeviceRequestsHelper.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        if (map == null) {
            try {
                map = new HashMap();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
                return null;
            }
        }
        String str = Build.DEVICE;
        Intrinsics.checkNotNullExpressionValue(str, "DEVICE");
        map.put("device", str);
        String str2 = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(str2, "MODEL");
        map.put("model", str2);
        String jSONObject = new JSONObject(map).toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "JSONObject(deviceInfo as Map<*, *>).toString()");
        return jSONObject;
    }

    public static final boolean startAdvertisementService(String str) {
        Class<DeviceRequestsHelper> cls = DeviceRequestsHelper.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            if (isAvailable()) {
                return INSTANCE.startAdvertisementServiceImpl(str);
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final boolean isAvailable() {
        Class<DeviceRequestsHelper> cls = DeviceRequestsHelper.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery == null || !appSettingsWithoutQuery.getSmartLoginOptions().contains(SmartLoginOption.Enabled)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final Bitmap generateQRCode(String str) {
        Class<DeviceRequestsHelper> cls = DeviceRequestsHelper.class;
        Bitmap bitmap = null;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            EnumMap enumMap = new EnumMap(EncodeHintType.class);
            enumMap.put(EncodeHintType.MARGIN, 2);
            try {
                BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 200, 200, enumMap);
                int height = encode.getHeight();
                int width = encode.getWidth();
                int[] iArr = new int[(height * width)];
                for (int i = 0; i < height; i++) {
                    int i2 = i * width;
                    for (int i3 = 0; i3 < width; i3++) {
                        iArr[i2 + i3] = encode.get(i3, i) ? -16777216 : -1;
                    }
                }
                Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                try {
                    createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
                    return createBitmap;
                } catch (WriterException unused) {
                    bitmap = createBitmap;
                }
            } catch (WriterException unused2) {
                return bitmap;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final void cleanUpAdvertisementService(String str) {
        Class<DeviceRequestsHelper> cls = DeviceRequestsHelper.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                INSTANCE.cleanUpAdvertisementServiceImpl(str);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final boolean startAdvertisementServiceImpl(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            HashMap hashMap = deviceRequestsListeners;
            if (hashMap.containsKey(str)) {
                return true;
            }
            String str2 = "fbsdk_" + ("android-" + StringsKt.replace$default(FacebookSdk.getSdkVersion(), '.', '|', false, 4, (Object) null)) + '_' + str;
            NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
            nsdServiceInfo.setServiceType("_fb._tcp.");
            nsdServiceInfo.setServiceName(str2);
            nsdServiceInfo.setPort(80);
            Object systemService = FacebookSdk.getApplicationContext().getSystemService("servicediscovery");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.nsd.NsdManager");
            DeviceRequestsHelper$startAdvertisementServiceImpl$nsdRegistrationListener$1 deviceRequestsHelper$startAdvertisementServiceImpl$nsdRegistrationListener$1 = new DeviceRequestsHelper$startAdvertisementServiceImpl$nsdRegistrationListener$1(str2, str);
            hashMap.put(str, deviceRequestsHelper$startAdvertisementServiceImpl$nsdRegistrationListener$1);
            ((NsdManager) systemService).registerService(nsdServiceInfo, 1, deviceRequestsHelper$startAdvertisementServiceImpl$nsdRegistrationListener$1);
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final void cleanUpAdvertisementServiceImpl(String str) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                NsdManager.RegistrationListener registrationListener = (NsdManager.RegistrationListener) deviceRequestsListeners.get(str);
                if (registrationListener != null) {
                    Object systemService = FacebookSdk.getApplicationContext().getSystemService("servicediscovery");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.nsd.NsdManager");
                    ((NsdManager) systemService).unregisterService(registrationListener);
                    deviceRequestsListeners.remove(str);
                }
            } catch (IllegalArgumentException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }
}
