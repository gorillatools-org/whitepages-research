package com.learnium.RNDeviceInfo;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.hardware.camera2.CameraManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.PowerManager;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.learnium.RNDeviceInfo.resolver.DeviceIdResolver;
import com.learnium.RNDeviceInfo.resolver.DeviceTypeResolver;
import com.salesforce.marketingcloud.messages.iam.j;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ReactModule(name = "RNDeviceInfo")
public class RNDeviceModule extends ReactContextBaseJavaModule {
    /* access modifiers changed from: private */
    public static String BATTERY_LEVEL = "batteryLevel";
    /* access modifiers changed from: private */
    public static String BATTERY_STATE = "batteryState";
    /* access modifiers changed from: private */
    public static String LOW_POWER_MODE = "lowPowerMode";
    public static final String NAME = "RNDeviceInfo";
    private final DeviceIdResolver deviceIdResolver;
    private final DeviceTypeResolver deviceTypeResolver;
    private BroadcastReceiver headphoneConnectionReceiver;
    private RNInstallReferrerClient installReferrerClient;
    /* access modifiers changed from: private */
    public double mLastBatteryLevel = -1.0d;
    /* access modifiers changed from: private */
    public String mLastBatteryState = "";
    /* access modifiers changed from: private */
    public boolean mLastPowerSaveState = false;
    private BroadcastReceiver receiver;

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public RNDeviceModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.deviceTypeResolver = new DeviceTypeResolver(reactApplicationContext);
        this.deviceIdResolver = new DeviceIdResolver(reactApplicationContext);
        this.installReferrerClient = new RNInstallReferrerClient(reactApplicationContext.getBaseContext());
    }

    public void initialize() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        this.receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                WritableMap access$000 = RNDeviceModule.this.getPowerStateFromIntent(intent);
                if (access$000 != null) {
                    String string = access$000.getString(RNDeviceModule.BATTERY_STATE);
                    double d = access$000.getDouble(RNDeviceModule.BATTERY_LEVEL);
                    Double valueOf = Double.valueOf(d);
                    boolean z = access$000.getBoolean(RNDeviceModule.LOW_POWER_MODE);
                    if (!RNDeviceModule.this.mLastBatteryState.equalsIgnoreCase(string) || RNDeviceModule.this.mLastPowerSaveState != z) {
                        RNDeviceModule rNDeviceModule = RNDeviceModule.this;
                        rNDeviceModule.sendEvent(rNDeviceModule.getReactApplicationContext(), "RNDeviceInfo_powerStateDidChange", string);
                        String unused = RNDeviceModule.this.mLastBatteryState = string;
                        boolean unused2 = RNDeviceModule.this.mLastPowerSaveState = z;
                    }
                    if (RNDeviceModule.this.mLastBatteryLevel != d) {
                        RNDeviceModule rNDeviceModule2 = RNDeviceModule.this;
                        rNDeviceModule2.sendEvent(rNDeviceModule2.getReactApplicationContext(), "RNDeviceInfo_batteryLevelDidChange", valueOf);
                        if (d <= 0.15d) {
                            RNDeviceModule rNDeviceModule3 = RNDeviceModule.this;
                            rNDeviceModule3.sendEvent(rNDeviceModule3.getReactApplicationContext(), "RNDeviceInfo_batteryLevelIsLow", valueOf);
                        }
                        double unused3 = RNDeviceModule.this.mLastBatteryLevel = d;
                    }
                }
            }
        };
        getReactApplicationContext().registerReceiver(this.receiver, intentFilter);
        initializeHeadphoneConnectionReceiver();
    }

    private void initializeHeadphoneConnectionReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        this.headphoneConnectionReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                boolean isHeadphonesConnectedSync = RNDeviceModule.this.isHeadphonesConnectedSync();
                RNDeviceModule rNDeviceModule = RNDeviceModule.this;
                rNDeviceModule.sendEvent(rNDeviceModule.getReactApplicationContext(), "RNDeviceInfo_headphoneConnectionDidChange", Boolean.valueOf(isHeadphonesConnectedSync));
            }
        };
        getReactApplicationContext().registerReceiver(this.headphoneConnectionReceiver, intentFilter);
    }

    public void onCatalystInstanceDestroy() {
        getReactApplicationContext().unregisterReceiver(this.receiver);
        getReactApplicationContext().unregisterReceiver(this.headphoneConnectionReceiver);
    }

    public String getName() {
        return NAME;
    }

    public static SharedPreferences getRNDISharedPreferences(Context context) {
        return context.getSharedPreferences("react-native-device-info", 0);
    }

    @SuppressLint({"MissingPermission"})
    private WifiInfo getWifiInfo() {
        WifiManager wifiManager = (WifiManager) getReactApplicationContext().getApplicationContext().getSystemService("wifi");
        if (wifiManager != null) {
            return wifiManager.getConnectionInfo();
        }
        return null;
    }

    public Map<String, Object> getConstants() {
        String str;
        String str2;
        String str3;
        try {
            str3 = getPackageInfo().versionName;
            str2 = Integer.toString(getPackageInfo().versionCode);
            str = getReactApplicationContext().getApplicationInfo().loadLabel(getReactApplicationContext().getPackageManager()).toString();
        } catch (Exception unused) {
            str3 = j.h;
            str2 = str3;
            str = str2;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("deviceId", Build.BOARD);
        hashMap.put("bundleId", getReactApplicationContext().getPackageName());
        hashMap.put("systemName", "Android");
        hashMap.put("systemVersion", Build.VERSION.RELEASE);
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str3);
        hashMap.put("buildNumber", str2);
        hashMap.put("isTablet", Boolean.valueOf(this.deviceTypeResolver.isTablet()));
        hashMap.put("appName", str);
        hashMap.put("brand", Build.BRAND);
        hashMap.put("model", Build.MODEL);
        hashMap.put("deviceType", this.deviceTypeResolver.getDeviceType().getValue());
        return hashMap;
    }

    @ReactMethod
    public void isEmulator(Promise promise) {
        promise.resolve(Boolean.valueOf(isEmulatorSync()));
    }

    @SuppressLint({"HardwareIds"})
    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isEmulatorSync() {
        String str = Build.FINGERPRINT;
        if (!str.startsWith("generic") && !str.startsWith(j.h)) {
            String str2 = Build.MODEL;
            if (!str2.contains("google_sdk")) {
                Locale locale = Locale.ROOT;
                if (!str2.toLowerCase(locale).contains("droid4x") && !str2.contains("Emulator") && !str2.contains("Android SDK built for x86") && !Build.MANUFACTURER.contains("Genymotion")) {
                    String str3 = Build.HARDWARE;
                    if (!str3.contains("goldfish") && !str3.contains("ranchu") && !str3.contains("vbox86")) {
                        String str4 = Build.PRODUCT;
                        return str4.contains("sdk") || str4.contains("google_sdk") || str4.contains("sdk_google") || str4.contains("sdk_x86") || str4.contains("vbox86p") || str4.contains("emulator") || str4.contains("simulator") || Build.BOARD.toLowerCase(locale).contains("nox") || Build.BOOTLOADER.toLowerCase(locale).contains("nox") || str3.toLowerCase(locale).contains("nox") || str4.toLowerCase(locale).contains("nox") || Build.SERIAL.toLowerCase(locale).contains("nox") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
                    }
                }
            }
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public float getFontScaleSync() {
        return getReactApplicationContext().getResources().getConfiguration().fontScale;
    }

    @ReactMethod
    public void getFontScale(Promise promise) {
        promise.resolve(Float.valueOf(getFontScaleSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isPinOrFingerprintSetSync() {
        KeyguardManager keyguardManager = (KeyguardManager) getReactApplicationContext().getSystemService("keyguard");
        if (keyguardManager != null) {
            return keyguardManager.isKeyguardSecure();
        }
        System.err.println("Unable to determine keyguard status. KeyguardManager was null");
        return false;
    }

    @ReactMethod
    public void isPinOrFingerprintSet(Promise promise) {
        promise.resolve(Boolean.valueOf(isPinOrFingerprintSetSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getIpAddressSync() {
        try {
            return InetAddress.getByAddress(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(getWifiInfo().getIpAddress()).array()).getHostAddress();
        } catch (Exception unused) {
            return j.h;
        }
    }

    @ReactMethod
    public void getIpAddress(Promise promise) {
        promise.resolve(getIpAddressSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isCameraPresentSync() {
        try {
            if (((CameraManager) getReactApplicationContext().getSystemService("camera")).getCameraIdList().length > 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @ReactMethod
    public void isCameraPresent(Promise promise) {
        promise.resolve(Boolean.valueOf(isCameraPresentSync()));
    }

    @SuppressLint({"HardwareIds"})
    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getMacAddressSync() {
        String str;
        WifiInfo wifiInfo = getWifiInfo();
        if (wifiInfo != null) {
            str = wifiInfo.getMacAddress();
        } else {
            str = "";
        }
        if (getReactApplicationContext().checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
            try {
                for (T t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    if (t.getName().equalsIgnoreCase("wlan0")) {
                        byte[] hardwareAddress = t.getHardwareAddress();
                        if (hardwareAddress == null) {
                            str = "";
                        } else {
                            StringBuilder sb = new StringBuilder();
                            for (byte valueOf : hardwareAddress) {
                                sb.append(String.format("%02X:", new Object[]{Byte.valueOf(valueOf)}));
                            }
                            if (sb.length() > 0) {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            str = sb.toString();
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    @ReactMethod
    public void getMacAddress(Promise promise) {
        promise.resolve(getMacAddressSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getCarrierSync() {
        TelephonyManager telephonyManager = (TelephonyManager) getReactApplicationContext().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        System.err.println("Unable to get network operator name. TelephonyManager was null");
        return j.h;
    }

    @ReactMethod
    public void getCarrier(Promise promise) {
        promise.resolve(getCarrierSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getTotalDiskCapacitySync() {
        try {
            return getDirTotalCapacity(new StatFs(Environment.getRootDirectory().getAbsolutePath())).add(getDirTotalCapacity(new StatFs(Environment.getDataDirectory().getAbsolutePath()))).doubleValue();
        } catch (Exception unused) {
            return -1.0d;
        }
    }

    @ReactMethod
    public void getTotalDiskCapacity(Promise promise) {
        promise.resolve(Double.valueOf(getTotalDiskCapacitySync()));
    }

    private BigInteger getDirTotalCapacity(StatFs statFs) {
        return BigInteger.valueOf(statFs.getBlockCountLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getFreeDiskStorageSync() {
        try {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            return BigInteger.valueOf(getTotalAvailableBlocks(statFs, true)).multiply(BigInteger.valueOf(getBlockSize(statFs, true))).doubleValue() + BigInteger.valueOf(getTotalAvailableBlocks(statFs2, true)).multiply(BigInteger.valueOf(getBlockSize(statFs2, true))).doubleValue();
        } catch (Exception unused) {
            return -1.0d;
        }
    }

    @ReactMethod
    public void getFreeDiskStorage(Promise promise) {
        promise.resolve(Double.valueOf(getFreeDiskStorageSync()));
    }

    private long getTotalAvailableBlocks(StatFs statFs, Boolean bool) {
        return bool.booleanValue() ? statFs.getAvailableBlocksLong() : (long) statFs.getAvailableBlocks();
    }

    private long getBlockSize(StatFs statFs, Boolean bool) {
        return bool.booleanValue() ? statFs.getBlockSizeLong() : (long) statFs.getBlockSize();
    }

    @Deprecated
    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getTotalDiskCapacityOldSync() {
        try {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            return BigInteger.valueOf((long) statFs.getBlockCount()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).doubleValue();
        } catch (Exception unused) {
            return -1.0d;
        }
    }

    @ReactMethod
    public void getTotalDiskCapacityOld(Promise promise) {
        promise.resolve(Double.valueOf(getTotalDiskCapacityOldSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getFreeDiskStorageOldSync() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            return BigInteger.valueOf(statFs.getAvailableBlocksLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).doubleValue();
        } catch (Exception unused) {
            return -1.0d;
        }
    }

    @ReactMethod
    public void getFreeDiskStorageOld(Promise promise) {
        promise.resolve(Double.valueOf(getFreeDiskStorageOldSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isBatteryChargingSync() {
        Intent registerReceiver = getReactApplicationContext().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if ((registerReceiver != null ? registerReceiver.getIntExtra("status", -1) : 0) == 2) {
            return true;
        }
        return false;
    }

    @ReactMethod
    public void isBatteryCharging(Promise promise) {
        promise.resolve(Boolean.valueOf(isBatteryChargingSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getUsedMemorySync() {
        ActivityManager activityManager = (ActivityManager) getReactApplicationContext().getSystemService("activity");
        if (activityManager != null) {
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{Process.myPid()});
            if (processMemoryInfo.length == 1) {
                return ((double) processMemoryInfo[0].getTotalPss()) * 1024.0d;
            }
            System.err.println("Unable to getProcessMemoryInfo. getProcessMemoryInfo did not return any info for the PID");
            return -1.0d;
        }
        System.err.println("Unable to getProcessMemoryInfo. ActivityManager was null");
        return -1.0d;
    }

    @ReactMethod
    public void getUsedMemory(Promise promise) {
        promise.resolve(Double.valueOf(getUsedMemorySync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getPowerStateSync() {
        return getPowerStateFromIntent(getReactApplicationContext().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
    }

    @ReactMethod
    public void getPowerState(Promise promise) {
        promise.resolve(getPowerStateSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getBatteryLevelSync() {
        WritableMap powerStateFromIntent = getPowerStateFromIntent(getReactApplicationContext().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
        if (powerStateFromIntent == null) {
            return 0.0d;
        }
        return powerStateFromIntent.getDouble(BATTERY_LEVEL);
    }

    @ReactMethod
    public void getBatteryLevel(Promise promise) {
        promise.resolve(Double.valueOf(getBatteryLevelSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isAirplaneModeSync() {
        return Settings.Global.getInt(getReactApplicationContext().getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    @ReactMethod
    public void isAirplaneMode(Promise promise) {
        promise.resolve(Boolean.valueOf(isAirplaneModeSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean hasGmsSync() {
        Class<GoogleApiAvailability> cls = GoogleApiAvailability.class;
        try {
            int i = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
            Object invoke = cls.getMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
            if (((Integer) invoke.getClass().getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(invoke, new Object[]{getReactApplicationContext()})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @ReactMethod
    public void hasGms(Promise promise) {
        promise.resolve(Boolean.valueOf(hasGmsSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean hasHmsSync() {
        try {
            Object invoke = Class.forName("com.huawei.hms.api.HuaweiApiAvailability").getMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
            if (((Integer) invoke.getClass().getMethod("isHuaweiMobileServicesAvailable", new Class[]{Context.class}).invoke(invoke, new Object[]{getReactApplicationContext()})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @ReactMethod
    public void hasHms(Promise promise) {
        promise.resolve(Boolean.valueOf(hasHmsSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean hasSystemFeatureSync(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return getReactApplicationContext().getPackageManager().hasSystemFeature(str);
    }

    @ReactMethod
    public void hasSystemFeature(String str, Promise promise) {
        promise.resolve(Boolean.valueOf(hasSystemFeatureSync(str)));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableArray getSystemAvailableFeaturesSync() {
        FeatureInfo[] systemAvailableFeatures = getReactApplicationContext().getPackageManager().getSystemAvailableFeatures();
        WritableArray createArray = Arguments.createArray();
        for (FeatureInfo featureInfo : systemAvailableFeatures) {
            String str = featureInfo.name;
            if (str != null) {
                createArray.pushString(str);
            }
        }
        return createArray;
    }

    @ReactMethod
    public void getSystemAvailableFeatures(Promise promise) {
        promise.resolve(getSystemAvailableFeaturesSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isLocationEnabledSync() {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                return ((LocationManager) getReactApplicationContext().getSystemService(FirebaseAnalytics.Param.LOCATION)).isLocationEnabled();
            } catch (Exception unused) {
                System.err.println("Unable to determine if location enabled. LocationManager was null");
                return false;
            }
        } else {
            if (Settings.Secure.getInt(getReactApplicationContext().getContentResolver(), "location_mode", 0) != 0) {
                z = true;
            }
            return z;
        }
    }

    @ReactMethod
    public void isLocationEnabled(Promise promise) {
        promise.resolve(Boolean.valueOf(isLocationEnabledSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isHeadphonesConnectedSync() {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService("audio");
        return audioManager.isWiredHeadsetOn() || audioManager.isBluetoothA2dpOn();
    }

    @ReactMethod
    public void isHeadphonesConnected(Promise promise) {
        promise.resolve(Boolean.valueOf(isHeadphonesConnectedSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getAvailableLocationProvidersSync() {
        LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService(FirebaseAnalytics.Param.LOCATION);
        WritableMap createMap = Arguments.createMap();
        try {
            for (String next : locationManager.getProviders(false)) {
                createMap.putBoolean(next, locationManager.isProviderEnabled(next));
            }
        } catch (Exception unused) {
            System.err.println("Unable to get location providers. LocationManager was null");
        }
        return createMap;
    }

    @ReactMethod
    public void getAvailableLocationProviders(Promise promise) {
        promise.resolve(getAvailableLocationProvidersSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getInstallReferrerSync() {
        return getRNDISharedPreferences(getReactApplicationContext()).getString("installReferrer", j.h);
    }

    @ReactMethod
    public void getInstallReferrer(Promise promise) {
        promise.resolve(getInstallReferrerSync());
    }

    private PackageInfo getPackageInfo() throws Exception {
        return getReactApplicationContext().getPackageManager().getPackageInfo(getReactApplicationContext().getPackageName(), 0);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getInstallerPackageNameSync() {
        String installerPackageName = getReactApplicationContext().getPackageManager().getInstallerPackageName(getReactApplicationContext().getPackageName());
        return installerPackageName == null ? j.h : installerPackageName;
    }

    @ReactMethod
    public void getInstallerPackageName(Promise promise) {
        promise.resolve(getInstallerPackageNameSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getFirstInstallTimeSync() {
        try {
            return (double) getPackageInfo().firstInstallTime;
        } catch (Exception unused) {
            return -1.0d;
        }
    }

    @ReactMethod
    public void getFirstInstallTime(Promise promise) {
        promise.resolve(Double.valueOf(getFirstInstallTimeSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getLastUpdateTimeSync() {
        try {
            return (double) getPackageInfo().lastUpdateTime;
        } catch (Exception unused) {
            return -1.0d;
        }
    }

    @ReactMethod
    public void getLastUpdateTime(Promise promise) {
        promise.resolve(Double.valueOf(getLastUpdateTimeSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getDeviceNameSync() {
        String string;
        try {
            if (Build.VERSION.SDK_INT <= 31 && (string = Settings.Secure.getString(getReactApplicationContext().getContentResolver(), "bluetooth_name")) != null) {
                return string;
            }
            String string2 = Settings.Global.getString(getReactApplicationContext().getContentResolver(), "device_name");
            if (string2 != null) {
                return string2;
            }
            return j.h;
        } catch (Exception unused) {
            return j.h;
        }
    }

    @ReactMethod
    public void getDeviceName(Promise promise) {
        promise.resolve(getDeviceNameSync());
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getSerialNumberSync() {
        try {
            return Build.getSerial();
        } catch (Exception e) {
            PrintStream printStream = System.err;
            printStream.println("getSerialNumber failed, it probably should not be used: " + e.getMessage());
            return j.h;
        }
    }

    @ReactMethod
    public void getSerialNumber(Promise promise) {
        promise.resolve(getSerialNumberSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getDeviceSync() {
        return Build.DEVICE;
    }

    @ReactMethod
    public void getDevice(Promise promise) {
        promise.resolve(getDeviceSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getBuildIdSync() {
        return Build.ID;
    }

    @ReactMethod
    public void getBuildId(Promise promise) {
        promise.resolve(getBuildIdSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public int getApiLevelSync() {
        return Build.VERSION.SDK_INT;
    }

    @ReactMethod
    public void getApiLevel(Promise promise) {
        promise.resolve(Integer.valueOf(getApiLevelSync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getBootloaderSync() {
        return Build.BOOTLOADER;
    }

    @ReactMethod
    public void getBootloader(Promise promise) {
        promise.resolve(getBootloaderSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getDisplaySync() {
        return Build.DISPLAY;
    }

    @ReactMethod
    public void getDisplay(Promise promise) {
        promise.resolve(getDisplaySync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getFingerprintSync() {
        return Build.FINGERPRINT;
    }

    @ReactMethod
    public void getFingerprint(Promise promise) {
        promise.resolve(getFingerprintSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getHardwareSync() {
        return Build.HARDWARE;
    }

    @ReactMethod
    public void getHardware(Promise promise) {
        promise.resolve(getHardwareSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getHostSync() {
        return Build.HOST;
    }

    @ReactMethod
    public void getHost(Promise promise) {
        promise.resolve(getHostSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getProductSync() {
        return Build.PRODUCT;
    }

    @ReactMethod
    public void getProduct(Promise promise) {
        promise.resolve(getProductSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getTagsSync() {
        return Build.TAGS;
    }

    @ReactMethod
    public void getTags(Promise promise) {
        promise.resolve(getTagsSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getTypeSync() {
        return Build.TYPE;
    }

    @ReactMethod
    public void getType(Promise promise) {
        promise.resolve(getTypeSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getSystemManufacturerSync() {
        return Build.MANUFACTURER;
    }

    @ReactMethod
    public void getSystemManufacturer(Promise promise) {
        promise.resolve(getSystemManufacturerSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getCodenameSync() {
        return Build.VERSION.CODENAME;
    }

    @ReactMethod
    public void getCodename(Promise promise) {
        promise.resolve(getCodenameSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getIncrementalSync() {
        return Build.VERSION.INCREMENTAL;
    }

    @ReactMethod
    public void getIncremental(Promise promise) {
        promise.resolve(getIncrementalSync());
    }

    @SuppressLint({"HardwareIds"})
    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getUniqueIdSync() {
        return Settings.Secure.getString(getReactApplicationContext().getContentResolver(), "android_id");
    }

    @ReactMethod
    public void getUniqueId(Promise promise) {
        promise.resolve(getUniqueIdSync());
    }

    @SuppressLint({"HardwareIds"})
    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getAndroidIdSync() {
        return getUniqueIdSync();
    }

    @ReactMethod
    public void getAndroidId(Promise promise) {
        promise.resolve(getAndroidIdSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getMaxMemorySync() {
        return (double) Runtime.getRuntime().maxMemory();
    }

    @ReactMethod
    public void getMaxMemory(Promise promise) {
        promise.resolve(Double.valueOf(getMaxMemorySync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getTotalMemorySync() {
        ActivityManager activityManager = (ActivityManager) getReactApplicationContext().getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
            return (double) memoryInfo.totalMem;
        }
        System.err.println("Unable to getMemoryInfo. ActivityManager was null");
        return -1.0d;
    }

    @ReactMethod
    public void getTotalMemory(Promise promise) {
        promise.resolve(Double.valueOf(getTotalMemorySync()));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getInstanceIdSync() {
        return this.deviceIdResolver.getInstanceIdSync();
    }

    @ReactMethod
    public void getInstanceId(Promise promise) {
        promise.resolve(getInstanceIdSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getBaseOsSync() {
        return Build.VERSION.BASE_OS;
    }

    @ReactMethod
    public void getBaseOs(Promise promise) {
        promise.resolve(getBaseOsSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getPreviewSdkIntSync() {
        return Integer.toString(Build.VERSION.PREVIEW_SDK_INT);
    }

    @ReactMethod
    public void getPreviewSdkInt(Promise promise) {
        promise.resolve(getPreviewSdkIntSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getSecurityPatchSync() {
        return Build.VERSION.SECURITY_PATCH;
    }

    @ReactMethod
    public void getSecurityPatch(Promise promise) {
        promise.resolve(getSecurityPatchSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getUserAgentSync() {
        try {
            return WebSettings.getDefaultUserAgent(getReactApplicationContext());
        } catch (RuntimeException unused) {
            return System.getProperty("http.agent");
        }
    }

    @ReactMethod
    public void getUserAgent(Promise promise) {
        promise.resolve(getUserAgentSync());
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getPhoneNumberSync() {
        if (getReactApplicationContext() == null) {
            return j.h;
        }
        if (getReactApplicationContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0 && getReactApplicationContext().checkCallingOrSelfPermission("android.permission.READ_SMS") != 0 && getReactApplicationContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_NUMBERS") != 0) {
            return j.h;
        }
        TelephonyManager telephonyManager = (TelephonyManager) getReactApplicationContext().getSystemService("phone");
        if (telephonyManager != null) {
            try {
                return telephonyManager.getLine1Number();
            } catch (SecurityException e) {
                PrintStream printStream = System.err;
                printStream.println("getLine1Number called with permission, but threw anyway: " + e.getMessage());
                return j.h;
            }
        } else {
            System.err.println("Unable to getPhoneNumber. TelephonyManager was null");
            return j.h;
        }
    }

    @ReactMethod
    public void getPhoneNumber(Promise promise) {
        promise.resolve(getPhoneNumberSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableArray getSupportedAbisSync() {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (String pushString : Build.SUPPORTED_ABIS) {
            writableNativeArray.pushString(pushString);
        }
        return writableNativeArray;
    }

    @ReactMethod
    public void getSupportedAbis(Promise promise) {
        promise.resolve(getSupportedAbisSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableArray getSupported32BitAbisSync() {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (String pushString : Build.SUPPORTED_32_BIT_ABIS) {
            writableNativeArray.pushString(pushString);
        }
        return writableNativeArray;
    }

    @ReactMethod
    public void getSupported32BitAbis(Promise promise) {
        promise.resolve(getSupported32BitAbisSync());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableArray getSupported64BitAbisSync() {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (String pushString : Build.SUPPORTED_64_BIT_ABIS) {
            writableNativeArray.pushString(pushString);
        }
        return writableNativeArray;
    }

    @ReactMethod
    public void getSupported64BitAbis(Promise promise) {
        promise.resolve(getSupported64BitAbisSync());
    }

    /* access modifiers changed from: private */
    public WritableMap getPowerStateFromIntent(Intent intent) {
        String str;
        if (intent == null) {
            return null;
        }
        int intExtra = intent.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
        int intExtra2 = intent.getIntExtra("scale", -1);
        int intExtra3 = intent.getIntExtra("plugged", -1);
        int intExtra4 = intent.getIntExtra("status", -1);
        float f = ((float) intExtra) / ((float) intExtra2);
        if (intExtra3 == 0) {
            str = "unplugged";
        } else if (intExtra4 == 2) {
            str = "charging";
        } else if (intExtra4 == 5) {
            str = "full";
        } else {
            str = j.h;
        }
        boolean isPowerSaveMode = ((PowerManager) getReactApplicationContext().getSystemService("power")).isPowerSaveMode();
        WritableMap createMap = Arguments.createMap();
        createMap.putString(BATTERY_STATE, str);
        createMap.putDouble(BATTERY_LEVEL, (double) f);
        createMap.putBoolean(LOW_POWER_MODE, isPowerSaveMode);
        return createMap;
    }

    /* access modifiers changed from: private */
    public void sendEvent(ReactContext reactContext, String str, Object obj) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, obj);
    }
}
