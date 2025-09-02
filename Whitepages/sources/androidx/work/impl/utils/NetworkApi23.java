package androidx.work.impl.utils;

import android.net.ConnectivityManager;
import android.net.Network;
import kotlin.jvm.internal.Intrinsics;

public abstract class NetworkApi23 {
    public static final Network getActiveNetworkCompat(ConnectivityManager connectivityManager) {
        Intrinsics.checkNotNullParameter(connectivityManager, "<this>");
        return connectivityManager.getActiveNetwork();
    }
}
