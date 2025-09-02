package androidx.core.net;

import android.net.ConnectivityManager;

public abstract class ConnectivityManagerCompat {
    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        return connectivityManager.isActiveNetworkMetered();
    }
}
