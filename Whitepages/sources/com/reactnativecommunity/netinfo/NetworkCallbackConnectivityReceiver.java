package com.reactnativecommunity.netinfo;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Handler;
import android.os.Looper;
import com.facebook.react.bridge.ReactApplicationContext;

public class NetworkCallbackConnectivityReceiver extends ConnectivityReceiver {
    /* access modifiers changed from: private */
    public NetworkCapabilities mCapabilities = null;
    /* access modifiers changed from: private */
    public Network mNetwork = null;
    private final ConnectivityNetworkCallback mNetworkCallback = new ConnectivityNetworkCallback();

    public NetworkCallbackConnectivityReceiver(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void register() {
        try {
            this.mNetwork = getConnectivityManager().getActiveNetwork();
            asyncUpdateAndSend(0);
            getConnectivityManager().registerDefaultNetworkCallback(this.mNetworkCallback);
        } catch (SecurityException unused) {
        }
    }

    public void unregister() {
        try {
            getConnectivityManager().unregisterNetworkCallback(this.mNetworkCallback);
        } catch (IllegalArgumentException | SecurityException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateAndSend() {
        /*
            r10 = this;
            com.reactnativecommunity.netinfo.types.ConnectionType r0 = com.reactnativecommunity.netinfo.types.ConnectionType.UNKNOWN
            android.net.Network r1 = r10.mNetwork
            android.net.NetworkCapabilities r2 = r10.mCapabilities
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L_0x009a
            r5 = 2
            boolean r5 = r2.hasTransport(r5)
            r6 = 4
            r7 = 1
            if (r5 == 0) goto L_0x0016
            com.reactnativecommunity.netinfo.types.ConnectionType r0 = com.reactnativecommunity.netinfo.types.ConnectionType.BLUETOOTH
            goto L_0x003a
        L_0x0016:
            boolean r5 = r2.hasTransport(r4)
            if (r5 == 0) goto L_0x001f
            com.reactnativecommunity.netinfo.types.ConnectionType r0 = com.reactnativecommunity.netinfo.types.ConnectionType.CELLULAR
            goto L_0x003a
        L_0x001f:
            r5 = 3
            boolean r5 = r2.hasTransport(r5)
            if (r5 == 0) goto L_0x0029
            com.reactnativecommunity.netinfo.types.ConnectionType r0 = com.reactnativecommunity.netinfo.types.ConnectionType.ETHERNET
            goto L_0x003a
        L_0x0029:
            boolean r5 = r2.hasTransport(r7)
            if (r5 == 0) goto L_0x0032
            com.reactnativecommunity.netinfo.types.ConnectionType r0 = com.reactnativecommunity.netinfo.types.ConnectionType.WIFI
            goto L_0x003a
        L_0x0032:
            boolean r5 = r2.hasTransport(r6)
            if (r5 == 0) goto L_0x003a
            com.reactnativecommunity.netinfo.types.ConnectionType r0 = com.reactnativecommunity.netinfo.types.ConnectionType.VPN
        L_0x003a:
            if (r1 == 0) goto L_0x0045
            android.net.ConnectivityManager r5 = r10.getConnectivityManager()     // Catch:{ SecurityException -> 0x0045 }
            android.net.NetworkInfo r5 = r5.getNetworkInfo(r1)     // Catch:{ SecurityException -> 0x0045 }
            goto L_0x0046
        L_0x0045:
            r5 = r3
        L_0x0046:
            int r8 = android.os.Build.VERSION.SDK_INT
            r9 = 28
            if (r8 < r9) goto L_0x0054
            r8 = 21
            boolean r8 = r2.hasCapability(r8)
            r8 = r8 ^ r7
            goto L_0x0067
        L_0x0054:
            if (r1 == 0) goto L_0x0066
            if (r5 == 0) goto L_0x0066
            android.net.NetworkInfo$DetailedState r8 = r5.getDetailedState()
            android.net.NetworkInfo$DetailedState r9 = android.net.NetworkInfo.DetailedState.CONNECTED
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x0066
            r8 = r7
            goto L_0x0067
        L_0x0066:
            r8 = r4
        L_0x0067:
            r9 = 12
            boolean r9 = r2.hasCapability(r9)
            if (r9 == 0) goto L_0x007b
            r9 = 16
            boolean r9 = r2.hasCapability(r9)
            if (r9 == 0) goto L_0x007b
            if (r8 != 0) goto L_0x007b
            r8 = r7
            goto L_0x007c
        L_0x007b:
            r8 = r4
        L_0x007c:
            boolean r6 = r2.hasTransport(r6)
            if (r6 == 0) goto L_0x008c
            if (r8 == 0) goto L_0x008d
            int r2 = r2.getLinkDownstreamBandwidthKbps()
            if (r2 == 0) goto L_0x008d
            r4 = r7
            goto L_0x008d
        L_0x008c:
            r4 = r8
        L_0x008d:
            if (r1 == 0) goto L_0x009c
            com.reactnativecommunity.netinfo.types.ConnectionType r1 = com.reactnativecommunity.netinfo.types.ConnectionType.CELLULAR
            if (r0 != r1) goto L_0x009c
            if (r4 == 0) goto L_0x009c
            com.reactnativecommunity.netinfo.types.CellularGeneration r3 = com.reactnativecommunity.netinfo.types.CellularGeneration.fromNetworkInfo(r5)
            goto L_0x009c
        L_0x009a:
            com.reactnativecommunity.netinfo.types.ConnectionType r0 = com.reactnativecommunity.netinfo.types.ConnectionType.NONE
        L_0x009c:
            r10.updateConnectivity(r0, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.netinfo.NetworkCallbackConnectivityReceiver.updateAndSend():void");
    }

    /* access modifiers changed from: private */
    public void asyncUpdateAndSend(int i) {
        new Handler(Looper.getMainLooper()).postDelayed(new NetworkCallbackConnectivityReceiver$$ExternalSyntheticLambda0(this), (long) i);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$asyncUpdateAndSend$0() {
        try {
            this.mCapabilities = getConnectivityManager().getNetworkCapabilities(this.mNetwork);
            updateAndSend();
        } catch (SecurityException unused) {
        }
    }

    private class ConnectivityNetworkCallback extends ConnectivityManager.NetworkCallback {
        private ConnectivityNetworkCallback() {
        }

        public void onAvailable(Network network) {
            Network unused = NetworkCallbackConnectivityReceiver.this.mNetwork = network;
            NetworkCallbackConnectivityReceiver.this.asyncUpdateAndSend(250);
        }

        public void onLosing(Network network, int i) {
            Network unused = NetworkCallbackConnectivityReceiver.this.mNetwork = network;
            NetworkCallbackConnectivityReceiver.this.updateAndSend();
        }

        public void onLost(Network network) {
            Network unused = NetworkCallbackConnectivityReceiver.this.mNetwork = null;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.mCapabilities = null;
            NetworkCallbackConnectivityReceiver.this.updateAndSend();
        }

        public void onUnavailable() {
            Network unused = NetworkCallbackConnectivityReceiver.this.mNetwork = null;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.mCapabilities = null;
            NetworkCallbackConnectivityReceiver.this.updateAndSend();
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            Network unused = NetworkCallbackConnectivityReceiver.this.mNetwork = network;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.mCapabilities = networkCapabilities;
            NetworkCallbackConnectivityReceiver.this.updateAndSend();
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            if (NetworkCallbackConnectivityReceiver.this.mNetwork != null) {
                Network unused = NetworkCallbackConnectivityReceiver.this.mNetwork = network;
            }
            NetworkCallbackConnectivityReceiver.this.asyncUpdateAndSend(250);
        }
    }
}
