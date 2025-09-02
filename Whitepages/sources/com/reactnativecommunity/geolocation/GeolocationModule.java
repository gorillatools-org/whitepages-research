package com.reactnativecommunity.geolocation;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.PromiseImpl;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.SystemClock;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.storage.db.h;

@SuppressLint({"MissingPermission"})
@ReactModule(name = "RNCGeolocation")
public class GeolocationModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RNCGeolocation";
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private final LocationListener mLocationListener = new LocationListener() {
        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onLocationChanged(Location location) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) GeolocationModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationDidChange", GeolocationModule.locationToMap(location));
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                GeolocationModule geolocationModule = GeolocationModule.this;
                int i2 = PositionError.POSITION_UNAVAILABLE;
                geolocationModule.emitError(i2, "Provider " + str + " is out of service.");
            } else if (i == 1) {
                GeolocationModule geolocationModule2 = GeolocationModule.this;
                int i3 = PositionError.TIMEOUT;
                geolocationModule2.emitError(i3, "Provider " + str + " is temporarily unavailable.");
            }
        }
    };
    private String mWatchedProvider;

    public GeolocationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return NAME;
    }

    private static class LocationOptions {
        /* access modifiers changed from: private */
        public final float distanceFilter;
        /* access modifiers changed from: private */
        public final boolean highAccuracy;
        /* access modifiers changed from: private */
        public final double maximumAge;
        /* access modifiers changed from: private */
        public final long timeout;

        private LocationOptions(long j, double d, boolean z, float f) {
            this.timeout = j;
            this.maximumAge = d;
            this.highAccuracy = z;
            this.distanceFilter = f;
        }

        /* access modifiers changed from: private */
        public static LocationOptions fromReactMap(ReadableMap readableMap) {
            return new LocationOptions(readableMap.hasKey("timeout") ? (long) readableMap.getDouble("timeout") : Long.MAX_VALUE, readableMap.hasKey("maximumAge") ? readableMap.getDouble("maximumAge") : Double.POSITIVE_INFINITY, readableMap.hasKey("enableHighAccuracy") && readableMap.getBoolean("enableHighAccuracy"), readableMap.hasKey("distanceFilter") ? (float) readableMap.getDouble("distanceFilter") : GeolocationModule.RCT_DEFAULT_LOCATION_ACCURACY);
        }
    }

    @ReactMethod
    public void getCurrentPosition(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        PermissionsModule permissionsModule = (PermissionsModule) getReactApplicationContext().getNativeModule(PermissionsModule.class);
        final AnonymousClass2 r4 = new Callback() {
            public void invoke(Object... objArr) {
                if (objArr[0] == "granted") {
                    GeolocationModule.this.getCurrentLocationData(readableMap, callback, callback2);
                } else {
                    callback2.invoke(PositionError.buildError(PositionError.PERMISSION_DENIED, "Location permission was not granted."));
                }
            }
        };
        final AnonymousClass3 r5 = new Callback() {
            public void invoke(Object... objArr) {
                callback2.invoke(PositionError.buildError(PositionError.PERMISSION_DENIED, "Failed to request location permission."));
            }
        };
        final PermissionsModule permissionsModule2 = permissionsModule;
        final ReadableMap readableMap2 = readableMap;
        final Callback callback3 = callback;
        final Callback callback4 = callback2;
        permissionsModule.checkPermission("android.permission.ACCESS_FINE_LOCATION", new PromiseImpl(new Callback() {
            public void invoke(Object... objArr) {
                if (!objArr[0].booleanValue()) {
                    permissionsModule2.requestPermission("android.permission.ACCESS_FINE_LOCATION", new PromiseImpl(r4, r5));
                } else {
                    GeolocationModule.this.getCurrentLocationData(readableMap2, callback3, callback4);
                }
            }
        }, new Callback() {
            public void invoke(Object... objArr) {
                callback2.invoke(PositionError.buildError(PositionError.PERMISSION_DENIED, "Failed to check location permission."));
            }
        }));
    }

    public void getCurrentLocationData(ReadableMap readableMap, Callback callback, Callback callback2) {
        LocationOptions access$300 = LocationOptions.fromReactMap(readableMap);
        try {
            LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService(FirebaseAnalytics.Param.LOCATION);
            String validProvider = getValidProvider(locationManager, access$300.highAccuracy);
            if (validProvider == null) {
                callback2.invoke(PositionError.buildError(PositionError.POSITION_UNAVAILABLE, "No location provider available."));
                return;
            }
            Location lastKnownLocation = locationManager.getLastKnownLocation(validProvider);
            if (lastKnownLocation == null || ((double) (SystemClock.currentTimeMillis() - lastKnownLocation.getTime())) >= access$300.maximumAge) {
                new SingleUpdateRequest(locationManager, validProvider, access$300.timeout, callback, callback2).invoke(lastKnownLocation);
            } else {
                callback.invoke(locationToMap(lastKnownLocation));
            }
        } catch (SecurityException e) {
            throwLocationPermissionMissing(e);
        }
    }

    @ReactMethod
    public void startObserving(ReadableMap readableMap) {
        if (!"gps".equals(this.mWatchedProvider)) {
            LocationOptions access$300 = LocationOptions.fromReactMap(readableMap);
            try {
                LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService(FirebaseAnalytics.Param.LOCATION);
                String validProvider = getValidProvider(locationManager, access$300.highAccuracy);
                if (validProvider == null) {
                    emitError(PositionError.POSITION_UNAVAILABLE, "No location provider available.");
                    return;
                }
                if (!validProvider.equals(this.mWatchedProvider)) {
                    locationManager.removeUpdates(this.mLocationListener);
                    locationManager.requestLocationUpdates(validProvider, 1000, access$300.distanceFilter, this.mLocationListener);
                }
                this.mWatchedProvider = validProvider;
            } catch (SecurityException e) {
                throwLocationPermissionMissing(e);
            }
        }
    }

    @ReactMethod
    public void stopObserving() {
        ((LocationManager) getReactApplicationContext().getSystemService(FirebaseAnalytics.Param.LOCATION)).removeUpdates(this.mLocationListener);
        this.mWatchedProvider = null;
    }

    private String getValidProvider(LocationManager locationManager, boolean z) {
        String str = "network";
        String str2 = z ? "gps" : str;
        if (!locationManager.isProviderEnabled(str2)) {
            if (!str2.equals("gps")) {
                str = "gps";
            }
            if (!locationManager.isProviderEnabled(str)) {
                return null;
            }
            str2 = str;
        }
        int checkSelfPermission = ContextCompat.checkSelfPermission(getReactApplicationContext(), "android.permission.ACCESS_FINE_LOCATION");
        if (!str2.equals("gps") || checkSelfPermission == 0) {
            return str2;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static WritableMap locationToMap(Location location) {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble(h.a.b, location.getLatitude());
        createMap2.putDouble(h.a.c, location.getLongitude());
        createMap2.putDouble("altitude", location.getAltitude());
        createMap2.putDouble("accuracy", (double) location.getAccuracy());
        createMap2.putDouble("heading", (double) location.getBearing());
        createMap2.putDouble("speed", (double) location.getSpeed());
        createMap.putMap("coords", createMap2);
        createMap.putDouble("timestamp", (double) location.getTime());
        createMap.putBoolean("mocked", location.isFromMockProvider());
        return createMap;
    }

    /* access modifiers changed from: private */
    public void emitError(int i, String str) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationError", PositionError.buildError(i, str));
    }

    private static void throwLocationPermissionMissing(SecurityException securityException) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", securityException);
    }

    private static class SingleUpdateRequest {
        /* access modifiers changed from: private */
        public final Callback mError;
        /* access modifiers changed from: private */
        public final Handler mHandler;
        /* access modifiers changed from: private */
        public final LocationListener mLocationListener;
        /* access modifiers changed from: private */
        public final LocationManager mLocationManager;
        /* access modifiers changed from: private */
        public Location mOldLocation;
        private final String mProvider;
        /* access modifiers changed from: private */
        public final Callback mSuccess;
        private final long mTimeout;
        /* access modifiers changed from: private */
        public final Runnable mTimeoutRunnable;
        /* access modifiers changed from: private */
        public boolean mTriggered;

        private SingleUpdateRequest(LocationManager locationManager, String str, long j, Callback callback, Callback callback2) {
            this.mHandler = new Handler();
            this.mTimeoutRunnable = new Runnable() {
                public void run() {
                    synchronized (SingleUpdateRequest.this) {
                        try {
                            if (!SingleUpdateRequest.this.mTriggered) {
                                SingleUpdateRequest.this.mError.invoke(PositionError.buildError(PositionError.TIMEOUT, "Location request timed out"));
                                SingleUpdateRequest.this.mLocationManager.removeUpdates(SingleUpdateRequest.this.mLocationListener);
                                FLog.i(ReactConstants.TAG, "LocationModule: Location request timed out");
                                boolean unused = SingleUpdateRequest.this.mTriggered = true;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            };
            this.mLocationListener = new LocationListener() {
                public void onProviderDisabled(String str) {
                }

                public void onProviderEnabled(String str) {
                }

                public void onStatusChanged(String str, int i, Bundle bundle) {
                }

                public void onLocationChanged(Location location) {
                    synchronized (SingleUpdateRequest.this) {
                        try {
                            if (!SingleUpdateRequest.this.mTriggered) {
                                SingleUpdateRequest singleUpdateRequest = SingleUpdateRequest.this;
                                if (singleUpdateRequest.isBetterLocation(location, singleUpdateRequest.mOldLocation)) {
                                    SingleUpdateRequest.this.mSuccess.invoke(GeolocationModule.locationToMap(location));
                                    SingleUpdateRequest.this.mHandler.removeCallbacks(SingleUpdateRequest.this.mTimeoutRunnable);
                                    boolean unused = SingleUpdateRequest.this.mTriggered = true;
                                    SingleUpdateRequest.this.mLocationManager.removeUpdates(SingleUpdateRequest.this.mLocationListener);
                                }
                            }
                            Location unused2 = SingleUpdateRequest.this.mOldLocation = location;
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            };
            this.mLocationManager = locationManager;
            this.mProvider = str;
            this.mTimeout = j;
            this.mSuccess = callback;
            this.mError = callback2;
        }

        public void invoke(Location location) {
            this.mOldLocation = location;
            this.mLocationManager.requestLocationUpdates(this.mProvider, 100, 1.0f, this.mLocationListener);
            this.mHandler.postDelayed(this.mTimeoutRunnable, this.mTimeout);
        }

        /* access modifiers changed from: private */
        public boolean isBetterLocation(Location location, Location location2) {
            if (location2 == null) {
                return true;
            }
            long time = location.getTime() - location2.getTime();
            boolean z = time > 120000;
            boolean z2 = time < -120000;
            boolean z3 = time > 0;
            if (z) {
                return true;
            }
            if (z2) {
                return false;
            }
            int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
            boolean z4 = accuracy > 0;
            boolean z5 = accuracy < 0;
            boolean z6 = accuracy > 200;
            boolean isSameProvider = isSameProvider(location.getProvider(), location2.getProvider());
            if (z5) {
                return true;
            }
            if (z3 && !z4) {
                return true;
            }
            if (!z3 || z6 || !isSameProvider) {
                return false;
            }
            return true;
        }

        private boolean isSameProvider(String str, String str2) {
            if (str == null) {
                return str2 == null;
            }
            return str.equals(str2);
        }
    }
}
