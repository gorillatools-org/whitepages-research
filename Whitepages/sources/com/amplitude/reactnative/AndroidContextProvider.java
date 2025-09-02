package com.amplitude.reactnative;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AndroidContextProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private CachedInfo cachedInfo;
    /* access modifiers changed from: private */
    public final Context context;
    private boolean shouldTrackAdid;

    public AndroidContextProvider(Context context2, boolean z) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.shouldTrackAdid = z;
    }

    public final boolean getShouldTrackAdid() {
        return this.shouldTrackAdid;
    }

    private final CachedInfo getCachedInfo() {
        if (this.cachedInfo == null) {
            this.cachedInfo = new CachedInfo();
        }
        return this.cachedInfo;
    }

    public final class CachedInfo {
        private String advertisingId = fetchAdvertisingId();
        private String appSetId = fetchAppSetId();
        private final String brand = fetchBrand();
        private final String carrier = fetchCarrier();
        private final String country = fetchCountry();
        private final boolean gpsEnabled = checkGPSEnabled();
        private final String language = fetchLanguage();
        private boolean limitAdTrackingEnabled = true;
        private final String manufacturer = fetchManufacturer();
        private final String model = fetchModel();
        private final String osName = "android";
        private final String osVersion = fetchOsVersion();
        private final String platform = "Android";
        private final String versionName = fetchVersionName();

        public CachedInfo() {
        }

        public final String getAdvertisingId() {
            return this.advertisingId;
        }

        public final String getCountry() {
            return this.country;
        }

        public final String getVersionName() {
            return this.versionName;
        }

        public final String getOsName() {
            return this.osName;
        }

        public final String getPlatform() {
            return this.platform;
        }

        public final String getOsVersion() {
            return this.osVersion;
        }

        public final String getBrand() {
            return this.brand;
        }

        public final String getManufacturer() {
            return this.manufacturer;
        }

        public final String getModel() {
            return this.model;
        }

        public final String getCarrier() {
            return this.carrier;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final String getAppSetId() {
            return this.appSetId;
        }

        private final String fetchVersionName() {
            try {
                return AndroidContextProvider.this.context.getPackageManager().getPackageInfo(AndroidContextProvider.this.context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException | Exception unused) {
                return null;
            }
        }

        private final String fetchOsVersion() {
            String str = Build.VERSION.RELEASE;
            Intrinsics.checkNotNullExpressionValue(str, "RELEASE");
            return str;
        }

        private final String fetchBrand() {
            String str = Build.BRAND;
            Intrinsics.checkNotNullExpressionValue(str, "BRAND");
            return str;
        }

        private final String fetchManufacturer() {
            String str = Build.MANUFACTURER;
            Intrinsics.checkNotNullExpressionValue(str, "MANUFACTURER");
            return str;
        }

        private final String fetchModel() {
            String str = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str, "MODEL");
            return str;
        }

        private final String fetchCarrier() {
            try {
                Object systemService = AndroidContextProvider.this.context.getSystemService("phone");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
                return ((TelephonyManager) systemService).getNetworkOperatorName();
            } catch (Exception unused) {
                return null;
            }
        }

        private final String fetchCountry() {
            String countryFromNetwork = getCountryFromNetwork();
            return (countryFromNetwork == null || countryFromNetwork.length() == 0) ? getCountryFromLocale() : countryFromNetwork;
        }

        private final String getCountryFromNetwork() {
            String networkCountryIso;
            try {
                Object systemService = AndroidContextProvider.this.context.getSystemService("phone");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
                TelephonyManager telephonyManager = (TelephonyManager) systemService;
                if (telephonyManager.getPhoneType() == 2 || (networkCountryIso = telephonyManager.getNetworkCountryIso()) == null) {
                    return null;
                }
                Locale locale = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale, "US");
                String upperCase = networkCountryIso.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                return upperCase;
            } catch (Exception unused) {
                return null;
            }
        }

        private final Locale getLocale() {
            LocaleList locales = Resources.getSystem().getConfiguration().getLocales();
            Intrinsics.checkNotNullExpressionValue(locales, "getLocales(...)");
            if (locales.isEmpty()) {
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                return locale;
            }
            Locale locale2 = locales.get(0);
            Intrinsics.checkNotNullExpressionValue(locale2, "get(...)");
            return locale2;
        }

        private final String getCountryFromLocale() {
            String country2 = getLocale().getCountry();
            Intrinsics.checkNotNullExpressionValue(country2, "getCountry(...)");
            return country2;
        }

        private final String fetchLanguage() {
            String language2 = getLocale().getLanguage();
            Intrinsics.checkNotNullExpressionValue(language2, "getLanguage(...)");
            return language2;
        }

        private final String fetchAdvertisingId() {
            if (!AndroidContextProvider.this.getShouldTrackAdid()) {
                return null;
            }
            if (Intrinsics.areEqual((Object) "Amazon", (Object) fetchManufacturer())) {
                return getFetchAndCacheAmazonAdvertisingId();
            }
            return getFetchAndCacheGoogleAdvertisingId();
        }

        private final String fetchAppSetId() {
            try {
                Object invoke = Class.forName("com.google.android.gms.appset.AppSet").getMethod("getClient", new Class[]{Context.class}).invoke((Object) null, new Object[]{AndroidContextProvider.this.context});
                Object invoke2 = Tasks.class.getMethod("await", new Class[]{Task.class}).invoke((Object) null, new Object[]{invoke.getClass().getMethod("getAppSetIdInfo", (Class[]) null).invoke(invoke, (Object[]) null)});
                Object invoke3 = invoke2.getClass().getMethod("getId", (Class[]) null).invoke(invoke2, (Object[]) null);
                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type kotlin.String");
                this.appSetId = (String) invoke3;
            } catch (ClassNotFoundException unused) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services SDK not found for app set id!");
            } catch (InvocationTargetException unused2) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services not available for app set id");
            } catch (Exception unused3) {
                LogcatLogger.Companion.getLogger().error("Encountered an error connecting to Google Play Services for app set id");
            }
            return this.appSetId;
        }

        private final String getFetchAndCacheAmazonAdvertisingId() {
            ContentResolver contentResolver = AndroidContextProvider.this.context.getContentResolver();
            boolean z = false;
            if (Settings.Secure.getInt(contentResolver, "limit_ad_tracking", 0) == 1) {
                z = true;
            }
            this.limitAdTrackingEnabled = z;
            String string = Settings.Secure.getString(contentResolver, "advertising_id");
            this.advertisingId = string;
            return string;
        }

        private final String getFetchAndCacheGoogleAdvertisingId() {
            try {
                Object invoke = AdvertisingIdClient.class.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{AndroidContextProvider.this.context});
                Object invoke2 = invoke.getClass().getMethod("isLimitAdTrackingEnabled", (Class[]) null).invoke(invoke, (Object[]) null);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type kotlin.Boolean");
                this.limitAdTrackingEnabled = ((Boolean) invoke2).booleanValue();
                Object invoke3 = invoke.getClass().getMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type kotlin.String");
                this.advertisingId = (String) invoke3;
            } catch (ClassNotFoundException unused) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services SDK not found for advertising id!");
            } catch (InvocationTargetException unused2) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services not available for advertising id");
            } catch (Exception unused3) {
                LogcatLogger.Companion.getLogger().error("Encountered an error connecting to Google Play Services for advertising id");
            }
            return this.advertisingId;
        }

        private final boolean checkGPSEnabled() {
            Class<GooglePlayServicesUtil> cls = GooglePlayServicesUtil.class;
            try {
                String str = GooglePlayServicesUtil.GMS_ERROR_DIALOG;
                Object invoke = cls.getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke((Object) null, new Object[]{AndroidContextProvider.this.context});
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.Int");
                if (((Integer) invoke).intValue() == 0) {
                    return true;
                }
                return false;
            } catch (NoClassDefFoundError unused) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services Util not found!");
                return false;
            } catch (ClassNotFoundException unused2) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services Util not found!");
                return false;
            } catch (NoSuchMethodException unused3) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services not available");
                return false;
            } catch (InvocationTargetException unused4) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services not available");
                return false;
            } catch (IllegalAccessException unused5) {
                LogcatLogger.Companion.getLogger().warn("Google Play Services not available");
                return false;
            } catch (Exception e) {
                LogcatLogger logger = LogcatLogger.Companion.getLogger();
                logger.warn("Error when checking for Google Play Services: " + e);
                return false;
            }
        }
    }

    public final String getVersionName() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getVersionName();
    }

    public final String getOsName() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getOsName();
    }

    public final String getPlatform() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getPlatform();
    }

    public final String getOsVersion() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getOsVersion();
    }

    public final String getBrand() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getBrand();
    }

    public final String getManufacturer() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getManufacturer();
    }

    public final String getModel() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getModel();
    }

    public final String getCarrier() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getCarrier();
    }

    public final String getCountry() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getCountry();
    }

    public final String getLanguage() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getLanguage();
    }

    public final String getAdvertisingId() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getAdvertisingId();
    }

    public final String getAppSetId() {
        CachedInfo cachedInfo2 = getCachedInfo();
        Intrinsics.checkNotNull(cachedInfo2);
        return cachedInfo2.getAppSetId();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
