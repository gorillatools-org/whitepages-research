package com.reactnativecommunity.netinfo;

import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.reactnativecommunity.netinfo.types.CellularGeneration;
import com.reactnativecommunity.netinfo.types.ConnectionType;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Iterator;
import java.util.Locale;

public abstract class ConnectivityReceiver {
    public boolean hasListener = false;
    private CellularGeneration mCellularGeneration = null;
    private ConnectionType mConnectionType = ConnectionType.UNKNOWN;
    private final ConnectivityManager mConnectivityManager;
    private boolean mIsInternetReachable = false;
    private Boolean mIsInternetReachableOverride;
    private final ReactApplicationContext mReactContext;
    private final TelephonyManager mTelephonyManager;
    private final WifiManager mWifiManager;

    public abstract void register();

    public abstract void unregister();

    private static String getSubnet(InetAddress inetAddress) {
        short s;
        Iterator<InterfaceAddress> it = NetworkInterface.getByInetAddress(inetAddress).getInterfaceAddresses().iterator();
        while (true) {
            if (!it.hasNext()) {
                s = 0;
                break;
            }
            InterfaceAddress next = it.next();
            if (next.getAddress().getAddress().length == 4) {
                s = next.getNetworkPrefixLength();
                break;
            }
        }
        int i = -1 << (32 - s);
        return String.format(Locale.US, "%d.%d.%d.%d", new Object[]{Integer.valueOf((i >> 24) & 255), Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf(i & 255)});
    }

    ConnectivityReceiver(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
        this.mConnectivityManager = (ConnectivityManager) reactApplicationContext.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) reactApplicationContext.getApplicationContext().getSystemService("wifi");
        this.mTelephonyManager = (TelephonyManager) reactApplicationContext.getSystemService("phone");
    }

    public void getCurrentState(String str, Promise promise) {
        promise.resolve(createConnectivityEventMap(str));
    }

    public void setIsInternetReachableOverride(boolean z) {
        this.mIsInternetReachableOverride = Boolean.valueOf(z);
        updateConnectivity(this.mConnectionType, this.mCellularGeneration, this.mIsInternetReachable);
    }

    /* access modifiers changed from: package-private */
    public ReactApplicationContext getReactContext() {
        return this.mReactContext;
    }

    /* access modifiers changed from: package-private */
    public ConnectivityManager getConnectivityManager() {
        return this.mConnectivityManager;
    }

    /* access modifiers changed from: package-private */
    public void updateConnectivity(ConnectionType connectionType, CellularGeneration cellularGeneration, boolean z) {
        Boolean bool = this.mIsInternetReachableOverride;
        if (bool != null) {
            z = bool.booleanValue();
        }
        boolean z2 = false;
        boolean z3 = connectionType != this.mConnectionType;
        boolean z4 = cellularGeneration != this.mCellularGeneration;
        if (z != this.mIsInternetReachable) {
            z2 = true;
        }
        if (z3 || z4 || z2) {
            this.mConnectionType = connectionType;
            this.mCellularGeneration = cellularGeneration;
            this.mIsInternetReachable = z;
            if (this.hasListener) {
                sendConnectivityChangedEvent();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void sendConnectivityChangedEvent() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("netInfo.networkStatusDidChange", createConnectivityEventMap((String) null));
    }

    /* access modifiers changed from: protected */
    public WritableMap createConnectivityEventMap(String str) {
        String str2;
        WritableMap createMap = Arguments.createMap();
        boolean z = false;
        if (NetInfoUtils.isAccessWifiStatePermissionGranted(getReactContext())) {
            WifiManager wifiManager = this.mWifiManager;
            createMap.putBoolean("isWifiEnabled", wifiManager != null ? wifiManager.isWifiEnabled() : false);
        }
        if (str != null) {
            str2 = str;
        } else {
            str2 = this.mConnectionType.label;
        }
        createMap.putString("type", str2);
        boolean z2 = true;
        boolean z3 = !this.mConnectionType.equals(ConnectionType.NONE) && !this.mConnectionType.equals(ConnectionType.UNKNOWN);
        createMap.putBoolean("isConnected", z3);
        if (this.mIsInternetReachable && (str == null || str.equals(this.mConnectionType.label))) {
            z = true;
        }
        createMap.putBoolean("isInternetReachable", z);
        if (str == null) {
            str = this.mConnectionType.label;
        }
        WritableMap createDetailsMap = createDetailsMap(str);
        if (z3) {
            if (getConnectivityManager() != null) {
                z2 = getConnectivityManager().isActiveNetworkMetered();
            }
            createDetailsMap.putBoolean("isConnectionExpensive", z2);
        }
        createMap.putMap("details", createDetailsMap);
        return createMap;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(22:18|19|(1:23)|24|25|(1:27)|28|29|30|31|32|33|34|35|(2:36|37)|38|40|41|(1:43)|44|45|(1:47)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0068 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0073 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0082 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x008b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00a6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x00c1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x00d9 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006e A[Catch:{ Exception -> 0x0073 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d0 A[Catch:{ Exception -> 0x00d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00dd A[Catch:{ Exception -> 0x013d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.react.bridge.WritableMap createDetailsMap(java.lang.String r7) {
        /*
            r6 = this;
            com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.createMap()
            r7.hashCode()
            java.lang.String r1 = "subnet"
            java.lang.String r2 = "ipAddress"
            r3 = -1
            int r4 = r7.hashCode()
            switch(r4) {
                case -1419358249: goto L_0x002a;
                case -916596374: goto L_0x001f;
                case 3649301: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0034
        L_0x0014:
            java.lang.String r4 = "wifi"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L_0x001d
            goto L_0x0034
        L_0x001d:
            r3 = 2
            goto L_0x0034
        L_0x001f:
            java.lang.String r4 = "cellular"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L_0x0028
            goto L_0x0034
        L_0x0028:
            r3 = 1
            goto L_0x0034
        L_0x002a:
            java.lang.String r4 = "ethernet"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L_0x0033
            goto L_0x0034
        L_0x0033:
            r3 = 0
        L_0x0034:
            switch(r3) {
                case 0: goto L_0x0100;
                case 1: goto L_0x00e7;
                case 2: goto L_0x0039;
                default: goto L_0x0037;
            }
        L_0x0037:
            goto L_0x013d
        L_0x0039:
            com.facebook.react.bridge.ReactApplicationContext r7 = r6.getReactContext()
            boolean r7 = com.reactnativecommunity.netinfo.NetInfoUtils.isAccessWifiStatePermissionGranted(r7)
            if (r7 == 0) goto L_0x013d
            android.net.wifi.WifiManager r7 = r6.mWifiManager
            if (r7 == 0) goto L_0x013d
            android.net.wifi.WifiInfo r7 = r7.getConnectionInfo()
            if (r7 == 0) goto L_0x013d
            java.lang.String r3 = r7.getSSID()     // Catch:{ Exception -> 0x0068 }
            if (r3 == 0) goto L_0x0068
            java.lang.String r4 = "<unknown ssid>"
            boolean r4 = r3.contains(r4)     // Catch:{ Exception -> 0x0068 }
            if (r4 != 0) goto L_0x0068
            java.lang.String r4 = "\""
            java.lang.String r5 = ""
            java.lang.String r3 = r3.replace(r4, r5)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r4 = "ssid"
            r0.putString(r4, r3)     // Catch:{ Exception -> 0x0068 }
        L_0x0068:
            java.lang.String r3 = r7.getBSSID()     // Catch:{ Exception -> 0x0073 }
            if (r3 == 0) goto L_0x0073
            java.lang.String r4 = "bssid"
            r0.putString(r4, r3)     // Catch:{ Exception -> 0x0073 }
        L_0x0073:
            int r3 = r7.getRssi()     // Catch:{ Exception -> 0x0082 }
            r4 = 100
            int r3 = android.net.wifi.WifiManager.calculateSignalLevel(r3, r4)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "strength"
            r0.putInt(r4, r3)     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            int r3 = r7.getFrequency()     // Catch:{ Exception -> 0x008b }
            java.lang.String r4 = "frequency"
            r0.putInt(r4, r3)     // Catch:{ Exception -> 0x008b }
        L_0x008b:
            int r3 = r7.getIpAddress()     // Catch:{ Exception -> 0x00a6 }
            long r3 = (long) r3     // Catch:{ Exception -> 0x00a6 }
            java.math.BigInteger r3 = java.math.BigInteger.valueOf(r3)     // Catch:{ Exception -> 0x00a6 }
            byte[] r3 = r3.toByteArray()     // Catch:{ Exception -> 0x00a6 }
            com.reactnativecommunity.netinfo.NetInfoUtils.reverseByteArray(r3)     // Catch:{ Exception -> 0x00a6 }
            java.net.InetAddress r3 = java.net.InetAddress.getByAddress(r3)     // Catch:{ Exception -> 0x00a6 }
            java.lang.String r3 = r3.getHostAddress()     // Catch:{ Exception -> 0x00a6 }
            r0.putString(r2, r3)     // Catch:{ Exception -> 0x00a6 }
        L_0x00a6:
            int r2 = r7.getIpAddress()     // Catch:{ Exception -> 0x00c1 }
            long r2 = (long) r2     // Catch:{ Exception -> 0x00c1 }
            java.math.BigInteger r2 = java.math.BigInteger.valueOf(r2)     // Catch:{ Exception -> 0x00c1 }
            byte[] r2 = r2.toByteArray()     // Catch:{ Exception -> 0x00c1 }
            com.reactnativecommunity.netinfo.NetInfoUtils.reverseByteArray(r2)     // Catch:{ Exception -> 0x00c1 }
            java.net.InetAddress r2 = java.net.InetAddress.getByAddress(r2)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r2 = getSubnet(r2)     // Catch:{ Exception -> 0x00c1 }
            r0.putString(r1, r2)     // Catch:{ Exception -> 0x00c1 }
        L_0x00c1:
            int r1 = r7.getLinkSpeed()     // Catch:{ Exception -> 0x00ca }
            java.lang.String r2 = "linkSpeed"
            r0.putInt(r2, r1)     // Catch:{ Exception -> 0x00ca }
        L_0x00ca:
            r1 = 29
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00d9 }
            if (r2 < r1) goto L_0x00d9
            int r2 = r7.getRxLinkSpeedMbps()     // Catch:{ Exception -> 0x00d9 }
            java.lang.String r3 = "rxLinkSpeed"
            r0.putInt(r3, r2)     // Catch:{ Exception -> 0x00d9 }
        L_0x00d9:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x013d }
            if (r2 < r1) goto L_0x013d
            int r7 = r7.getTxLinkSpeedMbps()     // Catch:{ Exception -> 0x013d }
            java.lang.String r1 = "txLinkSpeed"
            r0.putInt(r1, r7)     // Catch:{ Exception -> 0x013d }
            goto L_0x013d
        L_0x00e7:
            com.reactnativecommunity.netinfo.types.CellularGeneration r7 = r6.mCellularGeneration
            if (r7 == 0) goto L_0x00f2
            java.lang.String r1 = "cellularGeneration"
            java.lang.String r7 = r7.label
            r0.putString(r1, r7)
        L_0x00f2:
            android.telephony.TelephonyManager r7 = r6.mTelephonyManager
            java.lang.String r7 = r7.getNetworkOperatorName()
            if (r7 == 0) goto L_0x013d
            java.lang.String r1 = "carrier"
            r0.putString(r1, r7)
            goto L_0x013d
        L_0x0100:
            java.util.Enumeration r7 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Exception -> 0x0139 }
        L_0x0104:
            boolean r3 = r7.hasMoreElements()     // Catch:{ Exception -> 0x0139 }
            if (r3 == 0) goto L_0x013d
            java.lang.Object r3 = r7.nextElement()     // Catch:{ Exception -> 0x0139 }
            java.net.NetworkInterface r3 = (java.net.NetworkInterface) r3     // Catch:{ Exception -> 0x0139 }
            java.util.Enumeration r3 = r3.getInetAddresses()     // Catch:{ Exception -> 0x0139 }
        L_0x0114:
            boolean r4 = r3.hasMoreElements()     // Catch:{ Exception -> 0x0139 }
            if (r4 == 0) goto L_0x0104
            java.lang.Object r4 = r3.nextElement()     // Catch:{ Exception -> 0x0139 }
            java.net.InetAddress r4 = (java.net.InetAddress) r4     // Catch:{ Exception -> 0x0139 }
            boolean r5 = r4.isLoopbackAddress()     // Catch:{ Exception -> 0x0139 }
            if (r5 != 0) goto L_0x0114
            boolean r5 = r4 instanceof java.net.Inet4Address     // Catch:{ Exception -> 0x0139 }
            if (r5 == 0) goto L_0x0114
            java.lang.String r7 = r4.getHostAddress()     // Catch:{ Exception -> 0x0139 }
            r0.putString(r2, r7)     // Catch:{ Exception -> 0x0139 }
            java.lang.String r7 = getSubnet(r4)     // Catch:{ Exception -> 0x0139 }
            r0.putString(r1, r7)     // Catch:{ Exception -> 0x0139 }
            return r0
        L_0x0139:
            r7 = move-exception
            r7.printStackTrace()
        L_0x013d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.netinfo.ConnectivityReceiver.createDetailsMap(java.lang.String):com.facebook.react.bridge.WritableMap");
    }
}
