package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CommonUtils {
    static final String BUILD_IDS_ARCH_RESOURCE_NAME = "com.google.firebase.crashlytics.build_ids_arch";
    static final String BUILD_IDS_BUILD_ID_RESOURCE_NAME = "com.google.firebase.crashlytics.build_ids_build_id";
    static final String BUILD_IDS_LIB_NAMES_RESOURCE_NAME = "com.google.firebase.crashlytics.build_ids_lib";
    public static final int DEVICE_STATE_BETAOS = 8;
    public static final int DEVICE_STATE_COMPROMISEDLIBRARIES = 32;
    public static final int DEVICE_STATE_DEBUGGERATTACHED = 4;
    public static final int DEVICE_STATE_ISSIMULATOR = 1;
    public static final int DEVICE_STATE_JAILBROKEN = 2;
    public static final int DEVICE_STATE_VENDORINTERNAL = 16;
    private static final String GOLDFISH = "goldfish";
    private static final char[] HEX_VALUES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    static final String LEGACY_MAPPING_FILE_ID_RESOURCE_NAME = "com.crashlytics.android.build_id";
    public static final String LEGACY_SHARED_PREFS_NAME = "com.crashlytics.prefs";
    static final String MAPPING_FILE_ID_RESOURCE_NAME = "com.google.firebase.crashlytics.mapping_file_id";
    private static final String RANCHU = "ranchu";
    private static final String SDK = "sdk";
    private static final String SHA1_INSTANCE = "SHA-1";
    public static final String SHARED_PREFS_NAME = "com.google.firebase.crashlytics";
    static final String VERSION_CONTROL_INFO_RESOURCE_NAME = "com.google.firebase.crashlytics.version_control_info";

    @Deprecated
    public static boolean isLoggingEnabled(Context context) {
        return false;
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("com.google.firebase.crashlytics", 0);
    }

    public static SharedPreferences getLegacySharedPrefs(Context context) {
        return context.getSharedPreferences(LEGACY_SHARED_PREFS_NAME, 0);
    }

    public static int getCpuArchitectureInt() {
        return Architecture.getValue().ordinal();
    }

    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, Architecture> matcher = null;

        static {
            Architecture architecture;
            Architecture architecture2;
            Architecture architecture3;
            Architecture architecture4;
            HashMap hashMap = new HashMap(4);
            matcher = hashMap;
            hashMap.put("armeabi-v7a", architecture3);
            hashMap.put("armeabi", architecture2);
            hashMap.put("arm64-v8a", architecture4);
            hashMap.put("x86", architecture);
        }

        static Architecture getValue() {
            String str = Build.CPU_ABI;
            if (TextUtils.isEmpty(str)) {
                Logger.getLogger().v("Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture architecture = matcher.get(str.toLowerCase(Locale.US));
            return architecture == null ? UNKNOWN : architecture;
        }
    }

    public static String streamToString(InputStream inputStream) {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static String sha1(String str) {
        return hash(str, SHA1_INSTANCE);
    }

    private static String hash(String str, String str2) {
        return hash(str.getBytes(), str2);
    }

    private static String hash(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return hexify(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            Logger logger = Logger.getLogger();
            logger.e("Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return "";
        }
    }

    public static String createInstanceIdFrom(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (String append : arrayList) {
            sb.append(append);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            return sha1(sb2);
        }
        return null;
    }

    public static synchronized long calculateTotalRamInBytes(Context context) {
        long j;
        synchronized (CommonUtils.class) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            j = memoryInfo.totalMem;
        }
        return j;
    }

    public static long calculateFreeRamInBytes(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long calculateUsedDiskSpaceInBytes(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (blockSize * ((long) statFs.getAvailableBlocks()));
    }

    public static boolean getProximitySensorEnabled(Context context) {
        if (!isEmulator() && ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null) {
            return true;
        }
        return false;
    }

    public static boolean getBooleanResourceValue(Context context, String str, boolean z) {
        Resources resources;
        if (!(context == null || (resources = context.getResources()) == null)) {
            int resourcesIdentifier = getResourcesIdentifier(context, str, "bool");
            if (resourcesIdentifier > 0) {
                return resources.getBoolean(resourcesIdentifier);
            }
            int resourcesIdentifier2 = getResourcesIdentifier(context, str, "string");
            if (resourcesIdentifier2 > 0) {
                return Boolean.parseBoolean(context.getString(resourcesIdentifier2));
            }
        }
        return z;
    }

    public static int getResourcesIdentifier(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, getResourcePackageName(context));
    }

    public static boolean isEmulator() {
        if (!Build.PRODUCT.contains(SDK)) {
            String str = Build.HARDWARE;
            return str.contains(GOLDFISH) || str.contains(RANCHU);
        }
    }

    public static boolean isRooted() {
        boolean isEmulator = isEmulator();
        String str = Build.TAGS;
        if ((!isEmulator && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (isEmulator || !file.exists()) {
            return false;
        }
        return true;
    }

    public static boolean isDebuggerAttached() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static int getDeviceState() {
        boolean isEmulator = isEmulator();
        if (isRooted()) {
            isEmulator |= true;
        }
        return isDebuggerAttached() ? isEmulator | true ? 1 : 0 : isEmulator ? 1 : 0;
    }

    public static String hexify(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = HEX_VALUES;
            cArr[i2] = cArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static void closeOrLog(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Logger.getLogger().e(str, e);
            }
        }
    }

    public static String padWithZerosToMaxIntWidth(int i) {
        if (i >= 0) {
            return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(i)}).replace(' ', '0');
        }
        throw new IllegalArgumentException("value must be zero or greater");
    }

    public static String getResourcePackageName(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i <= 0) {
            return context.getPackageName();
        }
        try {
            String resourcePackageName = context.getResources().getResourcePackageName(i);
            if ("android".equals(resourcePackageName)) {
                return context.getPackageName();
            }
            return resourcePackageName;
        } catch (Resources.NotFoundException unused) {
            return context.getPackageName();
        }
    }

    public static String getMappingFileId(Context context) {
        int resourcesIdentifier = getResourcesIdentifier(context, MAPPING_FILE_ID_RESOURCE_NAME, "string");
        if (resourcesIdentifier == 0) {
            resourcesIdentifier = getResourcesIdentifier(context, LEGACY_MAPPING_FILE_ID_RESOURCE_NAME, "string");
        }
        if (resourcesIdentifier != 0) {
            return context.getResources().getString(resourcesIdentifier);
        }
        return null;
    }

    public static List<BuildIdInfo> getBuildIdInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        int resourcesIdentifier = getResourcesIdentifier(context, BUILD_IDS_LIB_NAMES_RESOURCE_NAME, "array");
        int resourcesIdentifier2 = getResourcesIdentifier(context, BUILD_IDS_ARCH_RESOURCE_NAME, "array");
        int resourcesIdentifier3 = getResourcesIdentifier(context, BUILD_IDS_BUILD_ID_RESOURCE_NAME, "array");
        if (resourcesIdentifier == 0 || resourcesIdentifier2 == 0 || resourcesIdentifier3 == 0) {
            Logger.getLogger().d(String.format("Could not find resources: %d %d %d", new Object[]{Integer.valueOf(resourcesIdentifier), Integer.valueOf(resourcesIdentifier2), Integer.valueOf(resourcesIdentifier3)}));
            return arrayList;
        }
        String[] stringArray = context.getResources().getStringArray(resourcesIdentifier);
        String[] stringArray2 = context.getResources().getStringArray(resourcesIdentifier2);
        String[] stringArray3 = context.getResources().getStringArray(resourcesIdentifier3);
        if (stringArray.length == stringArray3.length && stringArray2.length == stringArray3.length) {
            for (int i = 0; i < stringArray3.length; i++) {
                arrayList.add(new BuildIdInfo(stringArray[i], stringArray2[i], stringArray3[i]));
            }
            return arrayList;
        }
        Logger.getLogger().d(String.format("Lengths did not match: %d %d %d", new Object[]{Integer.valueOf(stringArray.length), Integer.valueOf(stringArray2.length), Integer.valueOf(stringArray3.length)}));
        return arrayList;
    }

    public static String getVersionControlInfo(Context context) {
        int resourcesIdentifier = getResourcesIdentifier(context, VERSION_CONTROL_INFO_RESOURCE_NAME, "string");
        if (resourcesIdentifier == 0) {
            return null;
        }
        return context.getResources().getString(resourcesIdentifier);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean canTryConnection(Context context) {
        if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    public static boolean nullSafeEquals(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }
}
