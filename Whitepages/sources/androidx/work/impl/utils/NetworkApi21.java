package androidx.work.impl.utils;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import kotlin.jvm.internal.Intrinsics;

public abstract class NetworkApi21 {
    public static final void unregisterNetworkCallbackCompat(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback) {
        Intrinsics.checkNotNullParameter(connectivityManager, "<this>");
        Intrinsics.checkNotNullParameter(networkCallback, "networkCallback");
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    public static final NetworkCapabilities getNetworkCapabilitiesCompat(ConnectivityManager connectivityManager, Network network) {
        Intrinsics.checkNotNullParameter(connectivityManager, "<this>");
        return connectivityManager.getNetworkCapabilities(network);
    }

    public static final boolean hasCapabilityCompat(NetworkCapabilities networkCapabilities, int i) {
        Intrinsics.checkNotNullParameter(networkCapabilities, "<this>");
        return networkCapabilities.hasCapability(i);
    }
}
