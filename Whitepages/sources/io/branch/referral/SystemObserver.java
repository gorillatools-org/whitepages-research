package io.branch.referral;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.ads.identifier.AdvertisingIdClient;
import io.branch.coroutines.AdvertisingIdsKt;
import io.branch.coroutines.InstallReferrersKt;
import io.branch.data.InstallReferrerResult;
import io.branch.referral.util.DependencyUtilsKt;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

abstract class SystemObserver {
    private String GAIDString_ = null;
    private int LATVal_ = 0;

    interface AdsParamsFetchEvents {
        void onAdsParamsFetchFinished();
    }

    interface InstallReferrerFetchEvents {
        void onInstallReferrersFinished();
    }

    SystemObserver() {
    }

    static UniqueId getUniqueID(Context context, boolean z) {
        return new UniqueId(context, z);
    }

    static String getAnonID(Context context) {
        String anonID = PrefHelper.getInstance(context).getAnonID();
        if (!TextUtils.isEmpty(anonID) && !anonID.equals("bnc_no_value")) {
            return anonID;
        }
        String uuid = UUID.randomUUID().toString();
        PrefHelper.getInstance(context).setAnonID(uuid);
        return uuid;
    }

    static String getPackageName(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            } catch (Exception e) {
                BranchLogger.e("Caught Exception, error obtaining PackageName " + e.getMessage());
            }
        }
        return "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0033 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String getAppVersion(android.content.Context r2) {
        /*
            if (r2 == 0) goto L_0x002b
            android.content.pm.PackageManager r0 = r2.getPackageManager()     // Catch:{ Exception -> 0x0012 }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ Exception -> 0x0012 }
            r1 = 0
            android.content.pm.PackageInfo r2 = r0.getPackageInfo(r2, r1)     // Catch:{ Exception -> 0x0012 }
            java.lang.String r2 = r2.versionName     // Catch:{ Exception -> 0x0012 }
            goto L_0x002d
        L_0x0012:
            r2 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Caught Exception, error obtaining AppVersion "
            r0.append(r1)
            java.lang.String r2 = r2.getMessage()
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            io.branch.referral.BranchLogger.e(r2)
        L_0x002b:
            java.lang.String r2 = ""
        L_0x002d:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 == 0) goto L_0x0035
            java.lang.String r2 = "bnc_no_value"
        L_0x0035:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.SystemObserver.getAppVersion(android.content.Context):java.lang.String");
    }

    static long getFirstInstallTime(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            } catch (Exception e) {
                BranchLogger.e("Caught Exception, error obtaining FirstInstallTime " + e.getMessage());
            }
        }
        return 0;
    }

    static boolean isPackageInstalled(Context context) {
        if (context == null) {
            return false;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage == null) {
                return false;
            }
            return !packageManager.queryIntentActivities(launchIntentForPackage, 65536).isEmpty();
        } catch (Exception e) {
            BranchLogger.e("Caught Exception, error obtaining PackageInfo " + e.getMessage());
            return false;
        }
    }

    static long getLastUpdateTime(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
            } catch (Exception e) {
                BranchLogger.e("Caught Exception, error obtaining LastUpdateTime " + e.getMessage());
            }
        }
        return 0;
    }

    static String getPhoneBrand() {
        return Build.MANUFACTURER;
    }

    static boolean isHuaweiDevice() {
        return getPhoneBrand().equalsIgnoreCase("huawei");
    }

    static String getPhoneModel() {
        return Build.MODEL;
    }

    static String getISO2CountryCode() {
        return Locale.getDefault().getCountry();
    }

    static String getISO2LanguageCode() {
        return Locale.getDefault().getLanguage();
    }

    static boolean isFireOSDevice() {
        return getPhoneBrand().equalsIgnoreCase("amazon");
    }

    static boolean isHuaweiMobileServicesAvailable(Context context) {
        return isHuaweiDevice() && !isGooglePlayServicesAvailable(context);
    }

    static boolean isGooglePlayServicesAvailable(Context context) {
        Class<GoogleApiAvailability> cls = GoogleApiAvailability.class;
        try {
            int i = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
            Object invoke = cls.getDeclaredMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(cls.getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null), new Object[]{context});
            if (!(invoke instanceof Integer) || ((Integer) invoke).intValue() != 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            BranchLogger.e("Caught Exception isGooglePlayServicesAvailable: " + e.getMessage());
            return false;
        }
    }

    static String getOS(Context context) {
        if (isFireOSDevice()) {
            return context == null ? getPhoneModel().contains("AFT") ? "AMAZON_FIRE_TV" : "AMAZON_FIRE" : context.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv") ? "AMAZON_FIRE_TV" : "AMAZON_FIRE";
        }
        return "Android";
    }

    static int getAPILevel() {
        return Build.VERSION.SDK_INT;
    }

    static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    static String getCPUType() {
        return System.getProperty("os.arch");
    }

    static String getDeviceBuildId() {
        return Build.DISPLAY;
    }

    static String getLocale() {
        return Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    }

    static String getConnectionType(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (context == null || context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0 || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return null;
        }
        if (activeNetworkInfo.getType() == 1) {
            return "wifi";
        }
        return "mobile";
    }

    static String getCarrier(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        String networkOperatorName = telephonyManager.getNetworkOperatorName();
        if (TextUtils.isEmpty(networkOperatorName)) {
            return null;
        }
        return networkOperatorName;
    }

    static DisplayMetrics getScreenDisplay(Context context) {
        DisplayManager displayManager;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display display = null;
        if (!(context == null || (displayManager = (DisplayManager) context.getSystemService("display")) == null)) {
            display = displayManager.getDisplay(0);
        }
        if (display != null) {
            display.getMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    static boolean getWifiConnected(Context context) {
        return "wifi".equalsIgnoreCase(getConnectionType(context));
    }

    public void fetchAdId(Context context, AdsParamsFetchEvents adsParamsFetchEvents) {
        if (isFireOSDevice()) {
            setFireAdId(context, adsParamsFetchEvents);
        } else if (isHuaweiMobileServicesAvailable(context)) {
            fetchHuaweiAdId(context, adsParamsFetchEvents);
        } else {
            fetchGoogleAdId(context, adsParamsFetchEvents);
        }
    }

    private void fetchHuaweiAdId(Context context, final AdsParamsFetchEvents adsParamsFetchEvents) {
        if (DependencyUtilsKt.classExists("com.huawei.hms.ads.identifier.AdvertisingIdClient")) {
            AdvertisingIdsKt.getHuaweiAdvertisingInfoObject(context, new Continuation() {
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }

                public void resumeWith(Object obj) {
                    AdsParamsFetchEvents adsParamsFetchEvents;
                    if (obj != null) {
                        try {
                            AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) obj;
                            boolean isLimitAdTrackingEnabled = info.isLimitAdTrackingEnabled();
                            String id = !isLimitAdTrackingEnabled ? info.getId() : null;
                            SystemObserver.this.setLAT(isLimitAdTrackingEnabled ? 1 : 0);
                            SystemObserver.this.setGAID(id);
                        } catch (Exception e) {
                            BranchLogger.e("Error in continuation: " + e);
                            adsParamsFetchEvents = adsParamsFetchEvents;
                            if (adsParamsFetchEvents == null) {
                                return;
                            }
                        } catch (Throwable th) {
                            AdsParamsFetchEvents adsParamsFetchEvents2 = adsParamsFetchEvents;
                            if (adsParamsFetchEvents2 != null) {
                                adsParamsFetchEvents2.onAdsParamsFetchFinished();
                            }
                            throw th;
                        }
                    }
                    adsParamsFetchEvents = adsParamsFetchEvents;
                    if (adsParamsFetchEvents == null) {
                        return;
                    }
                    adsParamsFetchEvents.onAdsParamsFetchFinished();
                }
            });
            return;
        }
        if (adsParamsFetchEvents != null) {
            adsParamsFetchEvents.onAdsParamsFetchFinished();
        }
        BranchLogger.v("Huawei advertising service not found. If not expected, import com.huawei.hms.ads.identifier.AdvertisingIdClient into your gradle dependencies");
    }

    private void fetchGoogleAdId(Context context, final AdsParamsFetchEvents adsParamsFetchEvents) {
        if (DependencyUtilsKt.classExists("com.google.android.gms.ads.identifier.AdvertisingIdClient")) {
            AdvertisingIdsKt.getGoogleAdvertisingInfoObject(context, new Continuation() {
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }

                public void resumeWith(Object obj) {
                    AdsParamsFetchEvents adsParamsFetchEvents;
                    if (obj != null) {
                        try {
                            AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) obj;
                            boolean isLimitAdTrackingEnabled = info.isLimitAdTrackingEnabled();
                            String id = !isLimitAdTrackingEnabled ? info.getId() : null;
                            SystemObserver.this.setLAT(isLimitAdTrackingEnabled ? 1 : 0);
                            SystemObserver.this.setGAID(id);
                        } catch (Exception e) {
                            BranchLogger.e("Error in continuation: " + e);
                            adsParamsFetchEvents = adsParamsFetchEvents;
                            if (adsParamsFetchEvents == null) {
                                return;
                            }
                        } catch (Throwable th) {
                            AdsParamsFetchEvents adsParamsFetchEvents2 = adsParamsFetchEvents;
                            if (adsParamsFetchEvents2 != null) {
                                adsParamsFetchEvents2.onAdsParamsFetchFinished();
                            }
                            throw th;
                        }
                    }
                    adsParamsFetchEvents = adsParamsFetchEvents;
                    if (adsParamsFetchEvents == null) {
                        return;
                    }
                    adsParamsFetchEvents.onAdsParamsFetchFinished();
                }
            });
            return;
        }
        if (adsParamsFetchEvents != null) {
            adsParamsFetchEvents.onAdsParamsFetchFinished();
        }
        BranchLogger.v("Play Store advertising service not found. If not expected, import com.google.android.gms.ads.identifier.AdvertisingIdClient into your gradle dependencies");
    }

    private void setFireAdId(Context context, final AdsParamsFetchEvents adsParamsFetchEvents) {
        BranchLogger.v("setFireAdId");
        AdvertisingIdsKt.getAmazonFireAdvertisingInfoObject(context, new Continuation() {
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            public void resumeWith(Object obj) {
                AdsParamsFetchEvents adsParamsFetchEvents;
                if (obj != null) {
                    try {
                        Pair pair = (Pair) obj;
                        SystemObserver.this.setLAT(((Integer) pair.component1()).intValue());
                        if (((Integer) pair.component1()).intValue() == 0) {
                            SystemObserver.this.setGAID((String) pair.component2());
                        } else {
                            SystemObserver.this.setGAID((String) pair.component2());
                        }
                    } catch (Exception e) {
                        BranchLogger.e("Error in continuation: " + e);
                        adsParamsFetchEvents = adsParamsFetchEvents;
                        if (adsParamsFetchEvents == null) {
                            return;
                        }
                    } catch (Throwable th) {
                        AdsParamsFetchEvents adsParamsFetchEvents2 = adsParamsFetchEvents;
                        if (adsParamsFetchEvents2 != null) {
                            adsParamsFetchEvents2.onAdsParamsFetchFinished();
                        }
                        throw th;
                    }
                }
                adsParamsFetchEvents = adsParamsFetchEvents;
                if (adsParamsFetchEvents == null) {
                    return;
                }
                adsParamsFetchEvents.onAdsParamsFetchFinished();
            }
        });
    }

    public void fetchInstallReferrer(final Context context, final InstallReferrerFetchEvents installReferrerFetchEvents) {
        try {
            InstallReferrersKt.fetchLatestInstallReferrer(context, new Continuation() {
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }

                public void resumeWith(Object obj) {
                    if (obj != null) {
                        BranchLogger.v("fetchInstallReferrer resumeWith got result: " + obj);
                        InstallReferrerResult installReferrerResult = (InstallReferrerResult) obj;
                        AppStoreReferrer.processReferrerInfo(context, installReferrerResult.getLatestRawReferrer(), installReferrerResult.getLatestClickTimestamp(), installReferrerResult.getLatestInstallTimestamp(), installReferrerResult.getAppStore(), Boolean.valueOf(installReferrerResult.isClickThrough()));
                    } else {
                        BranchLogger.v("fetchInstallReferrer resumeWith got null result");
                    }
                    InstallReferrerFetchEvents installReferrerFetchEvents = installReferrerFetchEvents;
                    if (installReferrerFetchEvents != null) {
                        installReferrerFetchEvents.onInstallReferrersFinished();
                    }
                }
            });
        } catch (Exception e) {
            BranchLogger.e("Caught Exception SystemObserver fetchInstallReferrer " + e.getMessage());
            if (installReferrerFetchEvents != null) {
                installReferrerFetchEvents.onInstallReferrersFinished();
            }
        }
    }

    static String getLocalIPAddress() {
        String str = "";
        try {
            for (T inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                Iterator<T> it = Collections.list(inetAddresses.getInetAddresses()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InetAddress inetAddress = (InetAddress) it.next();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (hostAddress.indexOf(58) < 0) {
                            str = hostAddress;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            BranchLogger.e("Caught Exception SystemObserver getLocalIPAddress: " + e.getMessage());
        }
        return str;
    }

    static String getUIMode(Context context) {
        UiModeManager uiModeManager;
        if (context != null) {
            try {
                uiModeManager = (UiModeManager) context.getSystemService("uimode");
            } catch (Exception e) {
                BranchLogger.e("Caught Exception SystemObserver getUIMode" + e.getMessage());
                return "UI_MODE_TYPE_UNDEFINED";
            }
        } else {
            uiModeManager = null;
        }
        if (uiModeManager == null) {
            return "UI_MODE_TYPE_UNDEFINED";
        }
        switch (uiModeManager.getCurrentModeType()) {
            case 1:
                return "UI_MODE_TYPE_NORMAL";
            case 2:
                return "UI_MODE_TYPE_DESK";
            case 3:
                return "UI_MODE_TYPE_CAR";
            case 4:
                return "UI_MODE_TYPE_TELEVISION";
            case 5:
                return "UI_MODE_TYPE_APPLIANCE";
            case 6:
                return "UI_MODE_TYPE_WATCH";
            default:
                return "UI_MODE_TYPE_UNDEFINED";
        }
    }

    static class UniqueId {
        private boolean isRealId;
        private String uniqueId = "bnc_no_value";

        UniqueId(Context context, boolean z) {
            String str;
            this.isRealId = !z;
            String string = (context == null || z || !TextUtils.isEmpty(DeviceInfo.getInstance().getSystemObserver().getAID())) ? null : Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                if (z) {
                    str = UUID.randomUUID().toString();
                } else {
                    String randomlyGeneratedUuid = PrefHelper.getInstance(context).getRandomlyGeneratedUuid();
                    if (TextUtils.isEmpty(randomlyGeneratedUuid) || randomlyGeneratedUuid.equals("bnc_no_value")) {
                        randomlyGeneratedUuid = UUID.randomUUID().toString();
                        PrefHelper.getInstance(context).setRandomlyGeneratedUuid(randomlyGeneratedUuid);
                    }
                    str = randomlyGeneratedUuid;
                }
                this.isRealId = false;
            }
            this.uniqueId = string;
        }

        /* access modifiers changed from: package-private */
        public String getId() {
            return this.uniqueId;
        }

        /* access modifiers changed from: package-private */
        public boolean isReal() {
            return this.isRealId;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UniqueId uniqueId2 = (UniqueId) obj;
            if (!this.uniqueId.equals(uniqueId2.uniqueId) || this.isRealId != uniqueId2.isRealId) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = ((this.isRealId ? 1 : 0) + true) * 31;
            String str = this.uniqueId;
            return i + (str == null ? 0 : str.hashCode());
        }
    }

    /* access modifiers changed from: package-private */
    public String getAID() {
        return this.GAIDString_;
    }

    /* access modifiers changed from: package-private */
    public int getLATVal() {
        return this.LATVal_;
    }

    /* access modifiers changed from: package-private */
    public void setGAID(String str) {
        this.GAIDString_ = str;
    }

    /* access modifiers changed from: package-private */
    public void setLAT(int i) {
        this.LATVal_ = i;
    }
}
